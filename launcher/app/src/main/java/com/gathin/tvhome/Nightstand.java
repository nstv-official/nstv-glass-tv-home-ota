package com.gathin.tvhome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

/**
 * Satu-satunya tempat screensaver StandBy disusun, baik saat muncul sebagai hamparan di atas
 * Home ({@code MainActivity}) maupun saat dijalankan sistem sebagai dream ({@link ClockDream}).
 * Dulu keduanya punya salinan kode warna yang sama persis, sehingga setiap perubahan harus
 * ditulis dua kali.
 *
 * <p>Semua pilihan dibaca dari {@link ScreensaverSettings} saat layar akan ditampilkan, jadi
 * perubahan pengaturan langsung terlihat pada screensaver berikutnya tanpa memasang ulang.
 */
final class Nightstand {

    /**
     * Wallpaper yang sedang tampil. Diisi {@code MainActivity} setiap kali gambar berganti;
     * dream berjalan di proses yang sama, jadi {@link ClockDream} cukup membacanya.
     */
    static volatile Bitmap wallpaper;

    private static final int[] DIM_ALPHA = {0x00000000, 0x4D000000, 0x8C000000, 0xBF000000};
    /** Wallpaper terang membuat tulisan putih hilang: pada latar gambar keredupannya dinaikkan. */
    private static final int[] DIM_ALPHA_WALLPAPER = {0x59000000, 0x8C000000, 0xB3000000, 0xD9000000};

    /** Salinan buram wallpaper disimpan: bind() dijalankan tiap menit, tidak perlu mengalokasikan ulang. */
    private static Bitmap softCache;
    private static Bitmap softSource;

    private Nightstand() {}

    // ============================ INFLATE ============================

    /** Menata letak yang cocok dengan gaya terpilih. Keduanya memakai id yang sama. */
    static View inflate(Context ctx, LayoutInflater inflater, ViewGroup parent) {
        int layout = ScreensaverSettings.STYLE_BENTO.equals(ScreensaverSettings.style(ctx))
                ? R.layout.view_nightstand_bento : R.layout.view_nightstand;
        return inflater.inflate(layout, parent, false);
    }

    // ============================ BIND ============================

    /** Menerapkan seluruh pengaturan dan isi teks ke layar yang sedang tampil. */
    static void bind(View root, Context ctx) {
        if (root == null) return;

        String style = ScreensaverSettings.style(ctx);
        boolean night = nightActive(ctx);
        Typeface font = ctx.getResources().getFont(R.font.inter);

        LinearLayout content = root.findViewById(R.id.nightstandContent);
        LinearLayout card = root.findViewById(R.id.nightstandCard);
        View clockRow = root.findViewById(R.id.nightstandClockRow);
        View dial = root.findViewById(R.id.nightstandDial);
        View rule = root.findViewById(R.id.nightstandRule);
        TextView clock = root.findViewById(R.id.nightstandClock);
        TextClock ampm = root.findViewById(R.id.nightstandAmPm);
        TextView date = root.findViewById(R.id.nightstandDate);
        TextView hijri = root.findViewById(R.id.nightstandHijri);
        TextView weather = root.findViewById(R.id.nightstandWeather);
        TextView prayer = root.findViewById(R.id.nightstandPrayer);
        TextView hint = root.findViewById(R.id.nightstandHint);

        applyBackground(root, ctx);

        boolean glass = ScreensaverSettings.glassCard(ctx);
        if (card != null) card.setBackground(glass ? drawable(ctx, R.drawable.bg_nightstand_card) : null);
        if (ScreensaverSettings.STYLE_BENTO.equals(style)) {
            int tileBg = glass ? R.drawable.bg_nightstand_tile : 0;
            setTileBackground(root, R.id.nightstandTileClock, ctx, tileBg);
            setTileBackground(root, R.id.nightstandTileWeather, ctx, tileBg);
            setTileBackground(root, R.id.nightstandTileHijri, ctx, tileBg);
            setTileBackground(root, R.id.nightstandTilePrayer, ctx, tileBg);
        }

        // ----- gaya -----
        boolean bento = ScreensaverSettings.STYLE_BENTO.equals(style);
        boolean edit = ScreensaverSettings.STYLE_EDIT.equals(style);
        boolean analog = ScreensaverSettings.STYLE_ANALOG.equals(style);

        if (dial != null) dial.setVisibility(analog ? View.VISIBLE : View.GONE);
        if (clockRow != null) clockRow.setVisibility(analog ? View.GONE : View.VISIBLE);
        if (rule != null) rule.setVisibility(edit ? View.VISIBLE : View.GONE);

        if (content != null && !bento) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) content.getLayoutParams();
            lp.gravity = edit ? (Gravity.START | Gravity.CENTER_VERTICAL) : Gravity.CENTER;
            content.setLayoutParams(lp);
            DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
            int pad = (int) (78 * dm.density);
            content.setPadding(edit ? pad : 0, 0, 0, 0);
        }
        int align = edit ? Gravity.START : Gravity.CENTER;
        if (card != null) card.setGravity(align);
        if (clockRow != null) ((LinearLayout) clockRow).setGravity(align);
        setChildGravity(card, align);

        // ----- ukuran & format jam -----
        int sp = ScreensaverSettings.clockSizeSp(ctx);
        if (bento) sp = Math.round(sp * 0.68f);
        else if (edit) sp += 14;
        if (clock != null) {
            clock.setTextSize(sp);
            if (clock instanceof TextClock) applyClockFormat((TextClock) clock, ctx);
            clock.setTypeface(font, Typeface.NORMAL);
            clock.setFontVariationSettings(edit ? "'wght' 200" : "'wght' 400");
            clock.setLetterSpacing(edit ? -0.045f : -0.03f);
        }
        if (ampm != null) {
            // Penanda AM/PM hanya ada kalau jam memang tampil 12 jam.
            boolean twelve = usesTwelveHour(ctx, ampm.getResources());
            ampm.setVisibility(twelve ? View.VISIBLE : View.GONE);
            if (twelve) ampm.setFormat12Hour("a");
        }

        // ----- isi teks -----
        if (hijri != null) {
            hijri.setText(ScreensaverSettings.showHijri(ctx) ? "🌙 " + PrayerTimes.getHijriDate() : "");
            hijri.setVisibility(ScreensaverSettings.showHijri(ctx) ? View.VISIBLE : View.GONE);
        }
        if (date != null) date.setVisibility(ScreensaverSettings.showDate(ctx) ? View.VISIBLE : View.GONE);
        if (hint != null) hint.setVisibility(ScreensaverSettings.showHint(ctx) ? View.VISIBLE : View.GONE);

        if (prayer != null) {
            boolean show = ScreensaverSettings.showPrayer(ctx);
            prayer.setVisibility(show ? View.VISIBLE : View.GONE);
            if (show) {
                String summary = PrayerTimes.getNextPrayerSummary(ctx);
                prayer.setText(bento ? summary : "🕌 " + summary);
            }
        }

        if (weather != null) {
            boolean show = ScreensaverSettings.showWeather(ctx);
            weather.setVisibility(show ? View.VISIBLE : View.GONE);
            if (show) bindWeather(weather, ctx, bento, night);
        }

        applyInk(root, ctx, night);
    }

    private static void setTileBackground(View root, int id, Context ctx, int drawableId) {
        View v = root.findViewById(id);
        if (v != null) v.setBackground(drawableId == 0 ? null : drawable(ctx, drawableId));
    }

    private static android.graphics.drawable.Drawable drawable(Context ctx, int id) {
        return ctx.getResources().getDrawable(id, ctx.getTheme());
    }

    /** Apakah jam akan tampil 12 jam, termasuk saat pengaturannya "ikuti sistem". */
    private static boolean usesTwelveHour(Context ctx, android.content.res.Resources res) {
        String fmt = ScreensaverSettings.clockFormat(ctx);
        if (ScreensaverSettings.FMT_12.equals(fmt)) return true;
        if (ScreensaverSettings.FMT_24.equals(fmt)) return false;
        return !android.text.format.DateFormat.is24HourFormat(ctx);
    }

    private static void setChildGravity(ViewGroup parent, int gravity) {
        if (parent == null) return;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof LinearLayout && child.getId() == R.id.nightstandClockRow) continue;
            if (child.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
                lp.gravity = gravity;
                child.setLayoutParams(lp);
            }
        }
    }

    /** TextClock hanya mau satu format bila pengguna memilih paksa 12 atau 24 jam. */
    private static void applyClockFormat(TextClock clock, Context ctx) {
        String fmt = ScreensaverSettings.clockFormat(ctx);
        switch (fmt) {
            case ScreensaverSettings.FMT_12:
                clock.setFormat12Hour("h:mm");
                clock.setFormat24Hour(null);
                break;
            case ScreensaverSettings.FMT_24:
                clock.setFormat12Hour(null);
                clock.setFormat24Hour("HH:mm");
                break;
            case ScreensaverSettings.FMT_AUTO:
            default:
                clock.setFormat12Hour("h:mm");
                clock.setFormat24Hour("HH:mm");
                break;
        }
    }

    private static void bindWeather(TextView weather, Context ctx, boolean bento, boolean night) {
        HomeWeather.Line line = HomeWeather.last(ctx);
        if (line == null || (line.weather.isEmpty() && line.api.isEmpty())) {
            weather.setVisibility(View.GONE);
            return;
        }
        SpannableStringBuilder sb = new SpannableStringBuilder();
        if (!line.weather.isEmpty()) sb.append(line.weather);
        if (!line.api.isEmpty()) {
            if (sb.length() > 0) sb.append(bento ? "\n" : "   •   ");
            int start = sb.length();
            sb.append(line.api);
            sb.setSpan(new ForegroundColorSpan(night ? 0xFFFF453A : line.apiColor),
                    start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        weather.setText(sb);
    }

    // ============================ LATAR ============================

    private static void applyBackground(View root, Context ctx) {
        ImageView backdrop = root.findViewById(R.id.nightstandBackdrop);
        View aurora = root.findViewById(R.id.nightstandAurora);
        View scrim = root.findViewById(R.id.nightstandScrim);
        String bg = ScreensaverSettings.background(ctx);

        if (backdrop != null) {
            boolean wantWallpaper = ScreensaverSettings.BG_WALLPAPER.equals(bg);
            backdrop.setVisibility(wantWallpaper ? View.VISIBLE : View.GONE);
            if (wantWallpaper) backdrop.setImageBitmap(softCopy(wallpaper));
        }
        if (aurora != null) {
            boolean wantAurora = ScreensaverSettings.BG_AURORA.equals(bg);
            aurora.setVisibility(wantAurora ? View.VISIBLE : View.GONE);
            if (wantAurora) aurora.post(() -> aurora.setBackground(auroraBrush(ctx, aurora.getWidth(), aurora.getHeight())));
        }
        if (scrim != null) {
            int level = Math.max(0, Math.min(DIM_ALPHA.length - 1, ScreensaverSettings.dimLevel(ctx)));
            scrim.setBackgroundColor(ScreensaverSettings.BG_WALLPAPER.equals(bg)
                    ? DIM_ALPHA_WALLPAPER[level] : DIM_ALPHA[level]);
        }
    }

    /**
     * Wallpaper dikecilkan ekstrem lalu dibesarkan kembali dengan interpolasi bilinear: hasilnya
     * bidang warna lembut seperti kaca buram, tanpa menyentuh buffer blur milik {@link Backdrop}
     * yang dipakai kaca Home setiap saat. Dua tahap diperlukan: sekali kecil-kecil, sekali lagi
     * sampai seperempat layar supaya peregangan terakhir ImageView tinggal 4x dan tidak berbentuk
     * kotak-kotak besar.
     */
    private static Bitmap softCopy(Bitmap src) {
        if (src == null || src.isRecycled()) return null;
        if (softCache != null && softSource == src && !softCache.isRecycled()) return softCache;
        try {
            int tw = Math.max(8, src.getWidth() / 16);
            int th = Math.max(8, src.getHeight() / 16);
            Bitmap tiny = Bitmap.createScaledBitmap(src, tw, th, true);
            softCache = Bitmap.createScaledBitmap(tiny, Math.max(64, tw * 4), Math.max(64, th * 4), true);
            if (tiny != src) tiny.recycle();
            softSource = src;
            return softCache;
        } catch (Exception e) {
            return null;
        }
    }

    /** Warna cahaya latar mengikuti jam: indigo subuh, biru pagi, terang siang, jingga senja, malam. */
    private static GradientDrawable auroraBrush(Context ctx, int w, int h) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int glow;
        if (hour >= 3 && hour < 5) glow = 0xFF1B2A5B;
        else if (hour >= 5 && hour < 11) glow = 0xFF3E6990;
        else if (hour >= 11 && hour < 15) glow = 0xFF2E7A9E;
        else if (hour >= 15 && hour < 18) glow = 0xFF9A4B2F;
        else if (hour >= 18 && hour < 21) glow = 0xFF14402F;
        else glow = 0xFF101A3A;
        // Cincin cahaya di bagian atas yang meluruh ke hitam: GradientDrawable tidak menyediakan
        // pusat radial publik, dan setColors(int[], float[]) baru ada sejak API 29 (minSdk 26),
        // jadi dipakai konstruktor gradien linear empat warna yang rata jaraknya.
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{glow, glow, 0xFF0A1224, 0xFF04070D});
    }

    // ============================ WARNA ============================

    /** Mode malam: otomatis (22.00 - 06.00), selalu menyala, atau dimatikan. */
    static boolean nightActive(Context ctx) {
        String mode = ScreensaverSettings.nightMode(ctx);
        if (ScreensaverSettings.NIGHT_ON.equals(mode)) return true;
        if (ScreensaverSettings.NIGHT_OFF.equals(mode)) return false;
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour >= 22 || hour < 6;
    }

    private static void applyInk(View root, Context ctx, boolean night) {
        int white = 0xFFFFFFFF;
        int clockInk = night ? 0xFFFF453A : white;
        int bodyInk = night ? 0xE6FF453A : 0xE6FFFFFF;
        int faintInk = night ? 0x99FF453A : 0xB3FFFFFF;
        int hijriInk = night ? 0xE6FF453A : 0xD9E6E0D2;
        int hintInk = night ? 0x55FF453A : 0x4DFFFFFF;
        float density = ctx.getResources().getDisplayMetrics().density;

        setText(root, R.id.nightstandClock, clockInk);
        setText(root, R.id.nightstandAmPm, faintInk);
        setText(root, R.id.nightstandDate, bodyInk);
        setText(root, R.id.nightstandHijri, hijriInk);
        setText(root, R.id.nightstandWeather, faintInk);
        setText(root, R.id.nightstandHint, hintInk);

        TextView prayer = root.findViewById(R.id.nightstandPrayer);
        if (prayer != null) {
            prayer.setTextColor(clockInk);
            boolean bento = ScreensaverSettings.STYLE_BENTO.equals(ScreensaverSettings.style(ctx));
            if (bento) {
                prayer.setBackgroundResource(0);
            } else if (night) {
                GradientDrawable redPill = new GradientDrawable();
                redPill.setShape(GradientDrawable.RECTANGLE);
                redPill.setColor(0x26FF453A);
                redPill.setStroke((int) (1 * density), 0x59FF453A);
                redPill.setCornerRadius(21 * density);
                prayer.setBackground(redPill);
            } else {
                prayer.setBackgroundResource(R.drawable.pill_bg);
            }
        }

        View rule = root.findViewById(R.id.nightstandRule);
        if (rule != null) rule.setBackgroundColor(night ? 0x40FF453A : 0x4DFFFFFF);

        View dial = root.findViewById(R.id.nightstandDial);
        if (dial instanceof AnalogDialView) {
            ((AnalogDialView) dial).setInk(clockInk, night ? 0xFFFF8A80 : 0xFF38BDF8, night);
        }
    }

    private static void setText(View root, int id, int color) {
        TextView tv = root.findViewById(id);
        if (tv != null) tv.setTextColor(color);
    }

    // ============================ DRIFT ============================

    /**
     * Geser kecil untuk mencegah burn-in OLED. {@code drift_seconds} 0 berarti diam di tengah,
     * sama seperti perilaku hamparan Home yang lama.
     */
    static void drift(View root, Random random) {
        if (root == null) return;
        int seconds = ScreensaverSettings.driftSeconds(root.getContext());
        View content = root.findViewById(R.id.nightstandContent);
        View parent = content == null ? null : (View) content.getParent();
        if (content == null || parent == null) return;
        if (parent.getWidth() <= 0 || parent.getHeight() <= 0) return;
        if (seconds <= 0) {
            content.animate().cancel();
            content.setTranslationX(0f);
            content.setTranslationY(0f);
            return;
        }
        float nudgeX = parent.getWidth() * 0.015f * (2f * random.nextFloat() - 1f);
        float nudgeY = parent.getHeight() * 0.015f * (2f * random.nextFloat() - 1f);
        content.setTranslationX(nudgeX);
        content.setTranslationY(nudgeY);
    }
}
