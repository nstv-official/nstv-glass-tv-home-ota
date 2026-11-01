package com.gathin.tvhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Clean Waktu Sholat untuk Indonesia (default zona Kemenag RI: Jakarta, ID_JKT).
 * Menghitung waktu sholat secara astronomis berbasis koordinat aktif dengan zona waktu
 * WIB/WITA/WIT yang tepat, menggunakan sudut Kemenag RI (20° Subuh / 18° Isya) plus ihtiyat,
 * sehingga akurat dan selalu tersedia bahkan saat offline.
 */
final class PrayerTimes {
    private static final String TAG = "TvHome.Prayer";
    private static final String PREFS_NAME = "prayer_times_cache";
    private static final String KEY_JSON = "kemenag_id_jkt";
    private static final String KEY_MONTH = "kemenag_month";

    // Koordinat default: Jakarta, DKI Jakarta (WIB / UTC+7)
    private static final double LATITUDE = -6.2088;
    private static final double LONGITUDE = 106.8456;
    private static final double TIMEZONE = 7.0;

    static final String[] NAMES = {"Subuh", "Syuruq", "Dzuhur", "Ashar", "Maghrib", "Isya"};
    private static final String[] PRAYER_NAMES = NAMES;
    private static final String[] PRAYER_KEYS = {"fajr", "syuruk", "dhuhr", "asr", "maghrib", "isha"};

    static final class Day {
        final int[] minutes;
        final String hijri;

        Day(int[] minutes, String hijri) {
            this.minutes = minutes;
            this.hijri = hijri;
        }
    }

    static Day day(Context ctx, String zone, Calendar date) {
        if (zone == null || zone.isEmpty()) zone = getKemenagZone(ctx);
        long[] times = getTimesForToday(ctx, date.get(Calendar.DAY_OF_MONTH));
        if (times == null || times.length < 6) {
            times = calculateFallback(ctx, date);
        }
        if (times == null || times.length < 6) return null;
        int[] minutes = new int[6];
        for (int i = 0; i < 6; i++) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(times[i]);
            minutes[i] = c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
        }
        return new Day(minutes, getHijriDate());
    }

    static boolean due(Context ctx, String zone) {
        return day(ctx, zone, Calendar.getInstance()) == null;
    }

    static boolean refresh(Context ctx, String zone) {
        sync(ctx, null);
        return true;
    }

    private PrayerTimes() {
    }

    /** Forces an immediate background sync for the active location/zone. */
    static void sync(Context context, Runnable onUpdated) {
        syncInternal(context, onUpdated, true);
    }

    /** Refresh timetable in background if needed (once a month / day). */
    static void syncIfDue(Context context, Runnable onUpdated) {
        syncInternal(context, onUpdated, false);
    }

    private static void syncInternal(Context context, Runnable onUpdated, boolean force) {
        // Waktu sholat dihitung secara astronomis per koordinat aktif (lihat calculateFallback),
        // jadi tidak perlu fetch API eksternal. Sinkronisasi hanya membangunkan UI.
        if (onUpdated != null) {
            new Handler(Looper.getMainLooper()).post(onUpdated);
        }
    }

    public static class PrayerZone {
        public final String code;
        public final String state;
        public final String name;
        public final double lat;
        public final double lon;

        public PrayerZone(String code, String state, String name, double lat, double lon) {
            this.code = code;
            this.state = state;
            this.name = name;
            this.lat = lat;
            this.lon = lon;
        }

        public String getDisplayName() {
            return name + " (" + state + " • " + code + ")";
        }
    }

    private static final List<PrayerZone> PRESET_ZONES = new ArrayList<>();

    static {
        // 🇮🇩 INDONESIA — zona Kemenag RI (dihitung astronomis per koordinat, WIB/WITA/WIT)
        PRESET_ZONES.add(new PrayerZone("ID_JKT", "DKI Jakarta", "Jakarta", -6.2088, 106.8456));
        PRESET_ZONES.add(new PrayerZone("ID_BKR", "Jawa Barat", "Depok, Bekasi & Bogor", -6.2383, 106.9756));
        PRESET_ZONES.add(new PrayerZone("ID_BDG", "Jawa Barat", "Bandung", -6.9175, 107.6191));
        PRESET_ZONES.add(new PrayerZone("ID_SMG", "Jawa Tengah", "Semarang", -6.9667, 110.4167));
        PRESET_ZONES.add(new PrayerZone("ID_SOC", "Jawa Tengah", "Surakarta (Solo)", -7.5755, 110.8243));
        PRESET_ZONES.add(new PrayerZone("ID_JOG", "D.I. Yogyakarta", "Yogyakarta", -7.7956, 110.3695));
        PRESET_ZONES.add(new PrayerZone("ID_SBY", "Jawa Timur", "Surabaya", -7.2575, 112.7521));
        PRESET_ZONES.add(new PrayerZone("ID_MLG", "Jawa Timur", "Malang", -7.9797, 112.6304));
        PRESET_ZONES.add(new PrayerZone("ID_MDN", "Sumatera Utara", "Medan", 3.5952, 98.6722));
        PRESET_ZONES.add(new PrayerZone("ID_BTJ", "Aceh", "Banda Aceh", 5.5483, 95.3238));
        PRESET_ZONES.add(new PrayerZone("ID_PDG", "Sumatera Barat", "Padang", -0.9471, 100.4172));
        PRESET_ZONES.add(new PrayerZone("ID_PKU", "Riau", "Pekanbaru", 0.5071, 101.4478));
        PRESET_ZONES.add(new PrayerZone("ID_BTH", "Kepulauan Riau", "Batam", 1.1301, 104.0529));
        PRESET_ZONES.add(new PrayerZone("ID_PLM", "Sumatera Selatan", "Palembang", -2.9761, 104.7754));
        PRESET_ZONES.add(new PrayerZone("ID_BDJ", "Kalimantan Selatan", "Banjarmasin", -3.3194, 114.5908));
        PRESET_ZONES.add(new PrayerZone("ID_BPN", "Kalimantan Timur", "Balikpapan & IKN", -1.2379, 116.8529));
        PRESET_ZONES.add(new PrayerZone("ID_SMD", "Kalimantan Timur", "Samarinda", -0.5022, 117.1536));
        PRESET_ZONES.add(new PrayerZone("ID_PNK", "Kalimantan Barat", "Pontianak", -0.0263, 109.3425));
        PRESET_ZONES.add(new PrayerZone("ID_DPS", "Bali", "Denpasar", -8.6705, 115.2126));
        PRESET_ZONES.add(new PrayerZone("ID_MTR", "Nusa Tenggara Barat", "Mataram", -8.5833, 116.1167));
        PRESET_ZONES.add(new PrayerZone("ID_UPG", "Sulawesi Selatan", "Makassar", -5.1477, 119.4327));
        PRESET_ZONES.add(new PrayerZone("ID_MDC", "Sulawesi Utara", "Manado", 1.4748, 124.8421));
    }

    public static List<PrayerZone> getPresetZones() {
        return Collections.unmodifiableList(PRESET_ZONES);
    }

    public static String getActiveZone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.contains("manual_prayer_zone")) {
            return prefs.getString("manual_prayer_zone", "ID_JKT");
        }
        return getKemenagZone(context);
    }

    public static String getActiveZoneName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.contains("manual_prayer_zone_name")) {
            return prefs.getString("manual_prayer_zone_name", "Jakarta");
        }
        String zone = getActiveZone(context);
        for (PrayerZone pz : PRESET_ZONES) {
            if (pz.code.equalsIgnoreCase(zone)) return pz.name;
        }
        return AppLocationManager.getLocationName(context);
    }

    public static String getShortZoneName(Context context) {
        String name = getActiveZoneName(context);
        if (name == null || name.trim().isEmpty()) return "Jakarta";
        int cut = name.indexOf(',');
        if (cut > 0) name = name.substring(0, cut);
        cut = name.indexOf('&');
        if (cut > 0) name = name.substring(0, cut);
        cut = name.indexOf('(');
        if (cut > 0) name = name.substring(0, cut);
        name = name.trim();
        return name.isEmpty() ? "Jakarta" : name;
    }

    public static void clearManualZone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .remove("manual_prayer_zone")
                .remove("manual_prayer_zone_name")
                .remove("manual_lat")
                .remove("manual_lon")
                .apply();
    }

    public static void setZone(Context context, String zoneCode, String name, double lat, double lon) {
        if (zoneCode == null) return;
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putString("manual_prayer_zone", zoneCode)
                .putString("manual_prayer_zone_name", name != null ? name : zoneCode)
                .putString("manual_lat", String.valueOf(lat))
                .putString("manual_lon", String.valueOf(lon))
                .remove(KEY_JSON)
                .remove("cached_zone")
                .apply();
        sync(context, null);
    }

    public static void setZone(Context context, String zoneCode) {
        if (zoneCode == null) return;
        Location.Zone z = Location.byCode(zoneCode);
        if (z != null) {
            setZone(context, z.code, z.town, z.lat, z.lon);
        } else {
            setZone(context, zoneCode, zoneCode, 0, 0);
        }
    }

    public static void setPrayerZone(Context context, PrayerZone zone) {
        if (zone == null) return;
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putString("manual_prayer_zone", zone.code)
                .putString("manual_prayer_zone_name", zone.name)
                .putString("manual_lat", String.valueOf(zone.lat))
                .putString("manual_lon", String.valueOf(zone.lon))
                .apply();
        sync(context, null);
    }

    private static String getKemenagZone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.contains("manual_prayer_zone")) {
            return prefs.getString("manual_prayer_zone", "ID_JKT");
        }
        String loc = AppLocationManager.getLocationName(context).toLowerCase(Locale.ROOT);

        // Pemetaan kota Indonesia ke zona Kemenag RI (dihitung astronomis per koordinat).
        if (loc.contains("jakarta")) return "ID_JKT";
        if (loc.contains("depok") || loc.contains("bekasi") || loc.contains("bogor")) return "ID_BKR";
        if (loc.contains("bandung") || loc.contains("cimahi") || loc.contains("garut")) return "ID_BDG";
        if (loc.contains("semarang") || loc.contains("salatiga") || loc.contains("kendal")) return "ID_SMG";
        if (loc.contains("solo") || loc.contains("surakarta") || loc.contains("sukoharjo") || loc.contains("boyolali")) return "ID_SOC";
        if (loc.contains("yogyakarta") || loc.contains("jogja") || loc.contains("sleman") || loc.contains("bantul")) return "ID_JOG";
        if (loc.contains("surabaya") || loc.contains("sidoarjo") || loc.contains("gresik")) return "ID_SBY";
        if (loc.contains("malang") || loc.contains("batu")) return "ID_MLG";
        if (loc.contains("medan") || loc.contains("binjai") || loc.contains("deli")) return "ID_MDN";
        if (loc.contains("aceh") || loc.contains("banda")) return "ID_BTJ";
        if (loc.contains("padang") || loc.contains("bukittinggi") || loc.contains("pariaman")) return "ID_PDG";
        if (loc.contains("pekanbaru") || loc.contains("dumai") || loc.contains("riau")) return "ID_PKU";
        if (loc.contains("batam") || loc.contains("tanjung pinang") || loc.contains("bintan")) return "ID_BTH";
        if (loc.contains("palembang") || loc.contains("banyuasin") || loc.contains("ogan")) return "ID_PLM";
        if (loc.contains("banjarmasin") || loc.contains("banjarbaru")) return "ID_BDJ";
        if (loc.contains("balikpapan") || loc.contains("ikn") || loc.contains("penajam")) return "ID_BPN";
        if (loc.contains("samarinda") || loc.contains("kutai")) return "ID_SMD";
        if (loc.contains("pontianak") || loc.contains("kubu raya")) return "ID_PNK";
        if (loc.contains("denpasar") || loc.contains("bali") || loc.contains("badung") || loc.contains("ubud")) return "ID_DPS";
        if (loc.contains("mataram") || loc.contains("lombok")) return "ID_MTR";
        if (loc.contains("makassar") || loc.contains("gowa") || loc.contains("maros")) return "ID_UPG";
        if (loc.contains("manado") || loc.contains("minahasa") || loc.contains("bitung")) return "ID_MDC";

        return "ID_JKT"; // Default: Jakarta (WIB)
    }

    static class PrayerItem {
        final String name;
        final String timeStr;
        final boolean isNext;
        final boolean isPast;

        PrayerItem(String name, String timeStr, boolean isNext, boolean isPast) {
            this.name = name;
            this.timeStr = timeStr;
            this.isNext = isNext;
            this.isPast = isPast;
        }
    }

    /** Returns all 6 prayers for today with formatted times and status. */
    static PrayerItem[] getPrayerItems(Context context) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        long[] times = getTimesForToday(context, day);
        if (times == null || times.length < 6) {
            times = calculateFallback(context, now);
        }
        long nowMs = now.getTimeInMillis();
        int nextIdx = -1;
        for (int i = 0; i < times.length; i++) {
            if (i == 1) continue;
            if (times[i] > nowMs) {
                nextIdx = i;
                break;
            }
        }
        if (nextIdx == -1) nextIdx = 0;

        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String[] prayerNames = AppLanguage.getPrayerNames(context);
        PrayerItem[] items = new PrayerItem[6];
        for (int i = 0; i < 6; i++) {
            items[i] = new PrayerItem(
                    prayerNames[i],
                    timeFmt.format(new Date(times[i])),
                    i == nextIdx,
                    times[i] < nowMs
            );
        }
        return items;
    }

    /** Returns formatted clean UI CharSequence with highlighted next prayer, bold and legible. */
    static CharSequence getFormatted(Context context) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        long[] times = getTimesForToday(context, day);

        if (times == null || times.length < 6) {
            times = calculateFallback(context, now);
        }

        long nowMs = now.getTimeInMillis();
        int nextIdx = -1;

        // Find next prayer (skip Syuruq idx 1)
        for (int i = 0; i < times.length; i++) {
            if (i == 1) continue;
            if (times[i] > nowMs) {
                nextIdx = i;
                break;
            }
        }
        if (nextIdx == -1) nextIdx = 0; // After Isya -> Subuh next morning

        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String[] prayerNames = AppLanguage.getPrayerNames(context);
        String nextWord = AppLanguage.getNextPrayerPrefix(context);

        SpannableStringBuilder sb = new SpannableStringBuilder();

        // LINE 1: LOCATION  •  NEXT / BERIKUTNYA: ASHAR 16:18
        String nextName = prayerNames[nextIdx].toUpperCase(Locale.ROOT);
        long displayTimeMs = times[nextIdx];
        if (nextIdx == 0 && times[0] < nowMs) {
            Calendar tomorrow = (Calendar) now.clone();
            tomorrow.add(Calendar.DAY_OF_MONTH, 1);
            long[] tomTimes = getTimesForToday(context, tomorrow.get(Calendar.DAY_OF_MONTH));
            if (tomTimes == null || tomTimes.length < 6) tomTimes = calculateFallback(context, tomorrow);
            if (tomTimes != null && tomTimes.length > 0) displayTimeMs = tomTimes[0];
        }
        String nextTime = timeFmt.format(new Date(displayTimeMs));

        int l1Start = sb.length();
        String zoneHeader = getShortZoneName(context).toUpperCase(Locale.ROOT);
        sb.append(zoneHeader).append("  •  ").append(nextWord).append(": ").append(nextName).append(" ").append(nextTime);
        int l1End = sb.length();
        sb.setSpan(new ForegroundColorSpan(0xFFFFFFFF), l1Start, l1End, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new StyleSpan(Typeface.BOLD), l1Start, l1End, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sb.append("\n");

        // LINE 2: SUBUH 05:49  •  SYURUQ 06:55  •  DZUHUR 13:01  •  ASHAR 16:18  •  MAGHRIB 19:00  •  ISYA 20:10
        int[] displayIndices = {0, 1, 2, 3, 4, 5}; // Subuh, Syuruq, Dzuhur, Ashar, Maghrib, Isya
        for (int i = 0; i < displayIndices.length; i++) {
            int idx = displayIndices[i];
            boolean isNext = (idx == nextIdx);
            int start = sb.length();

            String name = prayerNames[idx].toUpperCase(Locale.ROOT);
            String timeStr = timeFmt.format(new Date(times[idx]));

            sb.append(name).append(" ").append(timeStr);
            if (isNext) {
                sb.setSpan(new ForegroundColorSpan(0xFF4ADE80), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb.setSpan(new StyleSpan(Typeface.BOLD), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                sb.setSpan(new ForegroundColorSpan(0xE6FFFFFF), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb.setSpan(new StyleSpan(Typeface.BOLD), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            if (i < displayIndices.length - 1) {
                int dotStart = sb.length();
                sb.append("  •  ");
                sb.setSpan(new ForegroundColorSpan(0x80FFFFFF), dotStart, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return sb;
    }

    private static long[] getTimesForToday(Context context, int day) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String jsonStr = prefs.getString(KEY_JSON, null);
            if (jsonStr == null) return null;

            JSONObject obj = new JSONObject(jsonStr);
            JSONArray prayers = obj.getJSONArray("prayers");
            for (int i = 0; i < prayers.length(); i++) {
                JSONObject p = prayers.getJSONObject(i);
                if (p.optInt("day") == day) {
                    long[] res = new long[6];
                    for (int k = 0; k < 6; k++) {
                        res[k] = p.getLong(PRAYER_KEYS[k]) * 1000L;
                    }
                    return res;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /** Perhitungan astronomis fallback berbasis koordinat aktif pengguna di Indonesia (WIB/WITA/WIT). */
    private static long[] calculateFallback(Context context, Calendar cal) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        double latitude = prefs.contains("manual_lat")
                ? Double.parseDouble(prefs.getString("manual_lat", String.valueOf(AppLocationManager.getLatitude(context))))
                : AppLocationManager.getLatitude(context);
        double longitude = prefs.contains("manual_lon")
                ? Double.parseDouble(prefs.getString("manual_lon", String.valueOf(AppLocationManager.getLongitude(context))))
                : AppLocationManager.getLongitude(context);

        double tz;
        // Zona waktu Indonesia dihitung dari bujur: WIB (UTC+7), WITA (UTC+8), WIT (UTC+9).
        if (longitude < 114.0) {
            tz = 7.0; // WIB (Sumatera, Jawa, Kalimantan Barat, Kalimantan Tengah)
        } else if (longitude < 125.0) {
            tz = 8.0; // WITA (Kalimantan Selatan/Timur, Sulawesi, Bali, NTB)
        } else {
            tz = 9.0; // WIT (Maluku, Papua)
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        double d = 367.0 * year - Math.floor((7.0 * (year + Math.floor((month + 9.0) / 12.0))) / 4.0)
                + Math.floor((275.0 * month) / 9.0) + day - 730531.5;

        double L = (280.461 + 0.9856474 * d) % 360.0;
        if (L < 0) L += 360.0;
        double M = (357.528 + 0.9856003 * d) % 360.0;
        if (M < 0) M += 360.0;

        double lambda = L + 1.915 * Math.sin(Math.toRadians(M)) + 0.020 * Math.sin(Math.toRadians(2 * M));
        double epsilon = 23.439 - 0.0000004 * d;
        double alpha = Math.toDegrees(Math.atan2(Math.cos(Math.toRadians(epsilon)) * Math.sin(Math.toRadians(lambda)), Math.cos(Math.toRadians(lambda)))) / 15.0;
        double delta = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(epsilon)) * Math.sin(Math.toRadians(lambda))));

        double noon = 12.0 + tz - (longitude / 15.0) - (L / 15.0 - alpha);
        if (noon < 0) noon += 24.0;
        if (noon >= 24) noon -= 24.0;

        double fajrHour = noon - sunAngleHour(-20.0, latitude, delta);
        double sunriseHour = noon - sunAngleHour(-0.833, latitude, delta);
        double asrHour = noon + asrHour(1, latitude, delta);
        double sunsetHour = noon + sunAngleHour(-0.833, latitude, delta);
        double ishaHour = noon + sunAngleHour(-18.0, latitude, delta);

        // Pembulatan standar Kemenag RI & ihtiyat +2 menit
        double[] hours = {fajrHour + 0.05, sunriseHour, noon + 0.04, asrHour + 0.04, sunsetHour + 0.05, ishaHour + 0.05};
        long[] res = new long[6];
        Calendar base = (Calendar) cal.clone();
        base.set(Calendar.SECOND, 0);
        base.set(Calendar.MILLISECOND, 0);

        for (int i = 0; i < 6; i++) {
            int h = (int) hours[i];
            int m = (int) ((hours[i] - h) * 60.0);
            base.set(Calendar.HOUR_OF_DAY, h);
            base.set(Calendar.MINUTE, m);
            res[i] = base.getTimeInMillis();
        }
        return res;
    }

    private static double sunAngleHour(double angle, double lat, double delta) {
        double cosH = (Math.sin(Math.toRadians(angle)) - Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(delta)))
                / (Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(delta)));
        cosH = Math.max(-1.0, Math.min(1.0, cosH));
        return Math.toDegrees(Math.acos(cosH)) / 15.0;
    }

    private static double asrHour(int shadowFactor, double lat, double delta) {
        double phi = Math.abs(lat - delta);
        double cotA = shadowFactor + Math.tan(Math.toRadians(phi));
        double angle = Math.toDegrees(Math.atan(1.0 / cotA));
        return sunAngleHour(angle, lat, delta);
    }

    static class ScheduleItem {
        final String name;
        final String timeFormatted;
        final boolean isNext;
        final boolean isPast;

        ScheduleItem(String name, String timeFormatted, boolean isNext, boolean isPast) {
            this.name = name;
            this.timeFormatted = timeFormatted;
            this.isNext = isNext;
            this.isPast = isPast;
        }
    }

    /** Returns full day prayer list for popup display. */
    static java.util.List<ScheduleItem> getFullSchedule(Context context) {
        java.util.List<ScheduleItem> list = new java.util.ArrayList<>();
        Calendar now = Calendar.getInstance();
        long[] times = getTimesForToday(context, now.get(Calendar.DAY_OF_MONTH));
        if (times == null || times.length < 6) times = calculateFallback(context, now);

        long nowMs = now.getTimeInMillis();
        int nextIdx = -1;
        for (int i = 0; i < times.length; i++) {
            if (i == 1) continue; // Skip Syuruq (sunrise)
            if (times[i] > nowMs) {
                nextIdx = i;
                break;
            }
        }
        if (nextIdx == -1) nextIdx = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        String[] prayerNames = AppLanguage.getPrayerNames(context);
        for (int i = 0; i < times.length; i++) {
            boolean isNext = (i == nextIdx);
            boolean isPast = (times[i] < nowMs);
            list.add(new ScheduleItem(prayerNames[i], sdf.format(new Date(times[i])), isNext, isPast));
        }
        return list;
    }

    /** Returns short summary of next prayer (e.g. for screensaver). */
    static String getNextPrayerSummary(Context context) {
        Calendar now = Calendar.getInstance();
        long[] times = getTimesForToday(context, now.get(Calendar.DAY_OF_MONTH));
        if (times == null || times.length < 6) times = calculateFallback(context, now);

        long nowMs = now.getTimeInMillis();
        int nextIdx = -1;
        for (int i = 0; i < times.length; i++) {
            if (i == 1) continue; // Skip Syuruq (sunrise)
            if (times[i] > nowMs) {
                nextIdx = i;
                break;
            }
        }

        String[] prayerNames = AppLanguage.getPrayerNames(context);
        long nextTimeMs;
        String prayerName;
        if (nextIdx != -1) {
            prayerName = prayerNames[nextIdx];
            nextTimeMs = times[nextIdx];
        } else {
            // All prayers today have passed (after Isya) -> Next is tomorrow's Subuh
            prayerName = prayerNames[0];
            Calendar tomorrow = (Calendar) now.clone();
            tomorrow.add(Calendar.DAY_OF_MONTH, 1);
            long[] tomTimes = getTimesForToday(context, tomorrow.get(Calendar.DAY_OF_MONTH));
            if (tomTimes == null || tomTimes.length < 6) {
                tomTimes = calculateFallback(context, tomorrow);
            }
            nextTimeMs = (tomTimes != null && tomTimes.length > 0) ? tomTimes[0] : (times[0] + 86400000L);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        String zone = getShortZoneName(context);
        return prayerName + " • " + sdf.format(new Date(nextTimeMs)) + " (" + zone + ")";
    }

    /** Returns Islamic Hijrah date localized according to active language (e.g. "11 Jamadilawal 1448H"). */
    static String getHijriDate(Context context) {
        try {
            java.time.chrono.HijrahDate h = java.time.chrono.HijrahDate.now();
            int day = h.get(java.time.temporal.ChronoField.DAY_OF_MONTH);
            int month = h.get(java.time.temporal.ChronoField.MONTH_OF_YEAR);
            int year = h.get(java.time.temporal.ChronoField.YEAR);
            String lang = context != null ? AppLanguage.getLanguage(context) : AppLanguage.LANG_ID;
            String[] months;
            switch (lang) {
                case AppLanguage.LANG_EN:
                    months = new String[]{
                        "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
                        "Jumada al-Awwal", "Jumada al-Thani", "Rajab", "Sha'ban",
                        "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
                    };
                    break;
                case AppLanguage.LANG_ID:
                default:
                    months = new String[]{
                        "Muharram", "Safar", "Rabiul Awal", "Rabiul Akhir",
                        "Jumadil Awal", "Jumadil Akhir", "Rajab", "Sya'ban",
                        "Ramadhan", "Syawal", "Zulkaidah", "Zulhijjah"
                    };
                    break;
            }
            String monthName = (month >= 1 && month <= 12) ? months[month - 1] : "Hijrah";
            return day + " " + monthName + " " + year + "H";
        } catch (Exception e) {
            return "";
        }
    }

    static String getHijriDate() {
        return getHijriDate(null);
    }
}
