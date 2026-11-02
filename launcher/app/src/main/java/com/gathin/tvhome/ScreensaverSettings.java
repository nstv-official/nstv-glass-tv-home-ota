package com.gathin.tvhome;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preferensi layar screensaver StandBy. Semua nilai dibaca dari satu berkas
 * {@code screensaver_prefs} supaya {@link Nightstand} (overlay Home maupun {@link ClockDream})
 * dan dialog pengaturan di {@link ModernSettingsDialog} selalu melihat keadaan yang sama.
 *
 * <p>Default-nya dibuat seperti tampilan StandBy yang baru: jam digital terpusat di atas kartu
 * kaca dengan wallpaper hari itu diburamkan sebagai latar. Pilihan {@code BG_OLED} tetap ada
 * untuk panel yang ingin hemat energi, dan {@code idle 10 menit / sleep 2 jam / drift 1 menit}
 * sengaja sama dengan perilaku lama supaya TV yang sudah terpasang tidak berubah ritmenya.
 */
final class ScreensaverSettings {

    private static final String PREFS = "screensaver_prefs";

    // ===== Gaya jam =====
    static final String STYLE_DIGITAL = "digital";
    static final String STYLE_ANALOG = "analog";
    static final String STYLE_EDIT = "edit";
    static final String STYLE_BENTO = "bento";

    // ===== Latar belakang =====
    static final String BG_OLED = "oled";
    static final String BG_WALLPAPER = "wallpaper";
    static final String BG_AURORA = "aurora";

    // ===== Format jam =====
    static final String FMT_AUTO = "auto";
    static final String FMT_12 = "12";
    static final String FMT_24 = "24";

    // ===== Mode malam merah (Wind Down) =====
    static final String NIGHT_AUTO = "auto";
    static final String NIGHT_ON = "on";
    static final String NIGHT_OFF = "off";

    static final int[] CLOCK_SIZES = {80, 110, 140, 170};
    static final int[] IDLE_MINUTES = {1, 5, 10, 15, 30, 60};
    static final int[] SLEEP_MINUTES = {30, 60, 120, 180, 240};
    static final int[] DRIFT_SECONDS = {0, 60, 300, 600};

    private ScreensaverSettings() {}

    private static SharedPreferences p(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    // ============================ GETTERS ============================

    static String style(Context ctx) {
        return p(ctx).getString("style", STYLE_DIGITAL);
    }

    /** Wallpaper diburamkan sebagai bawaan: screensaver tidak lagi hitam polos. */
    static String background(Context ctx) {
        return p(ctx).getString("background", BG_WALLPAPER);
    }

    static boolean glassCard(Context ctx) {
        return p(ctx).getBoolean("glass_card", true);
    }

    static String clockFormat(Context ctx) {
        return p(ctx).getString("clock_format", FMT_AUTO);
    }

    static int clockSizeSp(Context ctx) {
        return p(ctx).getInt("clock_size_sp", 110);
    }

    /** 0 = terang penuh, 3 = paling redup. Yang lama (setScreenBright false) tetap redup wajar. */
    static int dimLevel(Context ctx) {
        return p(ctx).getInt("dim_level", 1);
    }

    static String nightMode(Context ctx) {
        return p(ctx).getString("night_mode", NIGHT_AUTO);
    }

    static boolean showDate(Context ctx) {
        return p(ctx).getBoolean("show_date", true);
    }

    static boolean showHijri(Context ctx) {
        return p(ctx).getBoolean("show_hijri", true);
    }

    static boolean showPrayer(Context ctx) {
        return p(ctx).getBoolean("show_prayer", true);
    }

    static boolean showWeather(Context ctx) {
        return p(ctx).getBoolean("show_weather", true);
    }

    static boolean showHint(Context ctx) {
        return p(ctx).getBoolean("show_hint", true);
    }

    /** Geser posisi tiap N detik untuk mencegah burn-in OLED. 0 = diam di tengah. */
    static int driftSeconds(Context ctx) {
        return p(ctx).getInt("drift_seconds", 60);
    }

    static int idleMinutes(Context ctx) {
        return p(ctx).getInt("idle_minutes", 10);
    }

    static int sleepMinutes(Context ctx) {
        return p(ctx).getInt("sleep_minutes", 120);
    }

    // ============================ SETTERS ============================

    static void setStyle(Context ctx, String value) {
        p(ctx).edit().putString("style", value).apply();
    }

    static void setBackground(Context ctx, String value) {
        p(ctx).edit().putString("background", value).apply();
    }

    static void setGlassCard(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("glass_card", value).apply();
    }

    static void setClockFormat(Context ctx, String value) {
        p(ctx).edit().putString("clock_format", value).apply();
    }

    static void setClockSizeSp(Context ctx, int value) {
        p(ctx).edit().putInt("clock_size_sp", value).apply();
    }

    static void setDimLevel(Context ctx, int value) {
        p(ctx).edit().putInt("dim_level", value).apply();
    }

    static void setNightMode(Context ctx, String value) {
        p(ctx).edit().putString("night_mode", value).apply();
    }

    static void setShowDate(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("show_date", value).apply();
    }

    static void setShowHijri(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("show_hijri", value).apply();
    }

    static void setShowPrayer(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("show_prayer", value).apply();
    }

    static void setShowWeather(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("show_weather", value).apply();
    }

    static void setShowHint(Context ctx, boolean value) {
        p(ctx).edit().putBoolean("show_hint", value).apply();
    }

    static void setDriftSeconds(Context ctx, int value) {
        p(ctx).edit().putInt("drift_seconds", value).apply();
    }

    static void setIdleMinutes(Context ctx, int value) {
        p(ctx).edit().putInt("idle_minutes", value).apply();
    }

    static void setSleepMinutes(Context ctx, int value) {
        p(ctx).edit().putInt("sleep_minutes", value).apply();
    }

    // ==================== RINGKASAN UNTUK BARIS PENGATURAN ====================

    static String styleLabel(Context ctx) {
        switch (style(ctx)) {
            case STYLE_ANALOG: return "Analog Minimalis";
            case STYLE_EDIT: return "Editorial (kiri, tipografi besar)";
            case STYLE_BENTO: return "Bento Widget (kartu-kartu)";
            case STYLE_DIGITAL:
            default: return "Digital Terpusat";
        }
    }

    static String backgroundLabel(Context ctx) {
        switch (background(ctx)) {
            case BG_WALLPAPER: return "Wallpaper blur";
            case BG_AURORA: return "Cahaya aurora sesuai jam";
            case BG_OLED:
            default: return "Hitam OLED";
        }
    }

    static String clockFormatLabel(Context ctx) {
        switch (clockFormat(ctx)) {
            case FMT_12: return "12 Jam (AM/PM)";
            case FMT_24: return "24 Jam";
            case FMT_AUTO:
            default: return "Ikuti pengaturan TV";
        }
    }

    static String dimLabel(Context ctx) {
        switch (dimLevel(ctx)) {
            case 0: return "Terang";
            case 2: return "Redup";
            case 3: return "Sangat redup";
            case 1:
            default: return "Sedang";
        }
    }

    static String nightModeLabel(Context ctx) {
        switch (nightMode(ctx)) {
            case NIGHT_ON: return "Selalu merah";
            case NIGHT_OFF: return "Tidak pernah";
            case NIGHT_AUTO:
            default: return "Otomatis (22.00 - 06.00)";
        }
    }

    static String driftLabel(Context ctx) {
        int s = driftSeconds(ctx);
        if (s == 0) return "Diam di tengah";
        if (s < 60) return s + " detik";
        int m = s / 60;
        return m == 1 ? "1 menit" : m + " menit";
    }

    static String minutesLabel(int minutes) {
        if (minutes < 60) return minutes + " Menit";
        int h = minutes / 60;
        int rest = minutes % 60;
        String hours = h == 1 ? "1 Jam" : h + " Jam";
        return rest == 0 ? hours : hours + " " + rest + " Menit";
    }
}
