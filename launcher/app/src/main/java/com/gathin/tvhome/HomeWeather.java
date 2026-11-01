package com.gathin.tvhome;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * The small weather line under the clock (weather_lat / weather_lon): the temperature and sky from
 * Open-Meteo, and the Indeks Standar Pencemar Udara (ISPU) from Open-Meteo Air Quality API
 * (PM2.5-based, mengikuti skala US AQI yang dipakai KLHK/BMKG untuk publik). The last line is kept,
 * so Home shows it at once and only refreshes it behind.
 */
final class HomeWeather {
    private static final String TAG = "TvHome.Weather";
    static final long REFRESH_MS = 15 * 60_000L;
    /** Open-Meteo Air Quality API (gratis, tanpa key). Sumber kualitas udara standar Indonesia (ISPU). */
    private static final String AQ = "https://air-quality-api.open-meteo.com/v1/air-quality?latitude=";
    /** Indonesian ISPU bands (merujuk PP No. 41/1999 KLHK, ekuivalen skala US AQI yang dipakai ISPU). */
    private static final int[] ISPU_TOP = {50, 100, 200, 300, 500};
    static final int[] ISPU_COLOR = {0xFF34C759, 0xFFFFD60A, 0xFFFF9F0A, 0xFFFF453A, 0xFF8E44AD};
    private static final String[] ISPU_NAME = {"Baik", "Sedang", "Tidak Sehat", "Sangat Tidak Sehat", "Berbahaya"};

    /** What the line says: the weather part, the ISPU part and its colour; either part may be empty. */
    static final class Line {
        final String weather, api;
        final int apiColor, icon;

        Line(String weather, String api, int apiColor, int icon) {
            this.weather = weather;
            this.api = api;
            this.apiColor = apiColor;
            this.icon = icon;
        }
    }

    private HomeWeather() {}

    /** Forgets the line, so a new location is fetched at once instead of the old place's weather showing. */
    static void forget(Context ctx) {
        prefs(ctx).edit().clear().apply();
    }

    static boolean due(Context ctx) {
        return Math.abs(System.currentTimeMillis() - prefs(ctx).getLong("at", 0)) >= REFRESH_MS;
    }

    /** The last line fetched, or null before the first. Reads storage only. */
    static Line last(Context ctx) {
        SharedPreferences p = prefs(ctx);
        String w = p.getString("weather", null), a = p.getString("api", "");
        if (w != null && !w.isEmpty() && !Character.isDigit(w.charAt(0))) {
            // An older line, with an emoji: dropped, and fetched again at once.
            w = "";
            p.edit().putLong("at", 0).putString("weather", "").apply();
        }
        return w == null && a.isEmpty() ? null : new Line(w == null ? "" : w, a, p.getInt("api_color", 0),
                p.getInt("icon_code", -1) < 0 ? 0 : icon(p.getInt("icon_code", 3), p.getBoolean("icon_day", true)));
    }

    /** Fetches both parts. Network; never on the UI thread. Returns null when neither could be read. */
    static Line refresh(Context ctx, double lat, double lon) {
        String weather = "", api = "";
        int color = 0, code = -1;
        boolean day = true;
        // 1) Cuaca: Open-Meteo dengan timezone WIB (Asia/Jakarta) agar waktu sunrise/sunset akurat untuk Indonesia.
        try {
            JSONObject now = new JSONObject(get("https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon
                    + "&current=temperature_2m,weather_code,is_day&timezone=Asia%2FJakarta")).getJSONObject("current");
            code = now.optInt("weather_code");
            day = now.optInt("is_day", 1) == 1;
            weather = Math.round(now.optDouble("temperature_2m")) + "\u00b0  " + describe(code);
        } catch (Exception e) {
            Log.w(TAG, "weather failed: " + e.getMessage());
        }
        // 2) Kualitas udara: Open-Meteo Air Quality API (US AQI, ekuivalen ISPU KLHK untuk rentang umum).
        //    Open-Meteo Air Quality — mencakup seluruh wilayah Indonesia dengan konversi ke ISPU.
        try {
            JSONObject aq = new JSONObject(get(AQ + lat + "&longitude=" + lon
                    + "&current=us_aqi,pm2_5&timezone=Asia%2FJakarta")).getJSONObject("current");
            int value = aq.optInt("us_aqi", -1);
            if (value >= 0) {
                int band = 0;
                while (band < ISPU_TOP.length - 1 && value > ISPU_TOP[band]) band++;
                api = "ISPU " + value + " " + ISPU_NAME[band];
                color = ISPU_COLOR[band];
            }
        } catch (Exception e) {
            Log.w(TAG, "ISPU failed: " + e.getMessage());
        }
        // A failed try waits for the next round too, rather than asking again every minute.
        SharedPreferences.Editor ed = prefs(ctx).edit().putLong("at", System.currentTimeMillis());
        if (weather.isEmpty() && api.isEmpty()) {
            ed.apply();
            return null;
        }
        ed.putString("weather", weather).putString("api", api).putInt("api_color", color)
                .putInt("icon_code", code).putBoolean("icon_day", day).apply();
        return new Line(weather, api, color, code < 0 ? 0 : icon(code, day));
    }

    private static String describe(int code) {
        if (code == 0) return "Cerah";
        if (code == 1) return "Hampir Cerah";
        if (code == 2) return "Berawan Sebagian";
        if (code == 3) return "Berawan";
        if (code == 45 || code == 48) return "Kabut";
        if (code >= 51 && code <= 57) return "Gerimis";
        if (code >= 61 && code <= 67) return code >= 65 ? "Hujan Lebat" : "Hujan";
        if (code >= 80 && code <= 82) return "Hujan Lokal";
        if (code >= 95) return "Hujan Petir";
        return "";
    }

    /** The sky as a small vector symbol (not an emoji: the colour emoji font alone costs Home megabytes). */
    static int icon(int code, boolean day) {
        if (code == 0) return day ? R.drawable.wx_sun : R.drawable.wx_moon;
        if (code == 1 || code == 2) return day ? R.drawable.wx_partly : R.drawable.wx_partly_night;
        if (code == 3) return R.drawable.wx_cloud;
        if (code == 45 || code == 48) return R.drawable.wx_fog;
        if (code >= 51 && code <= 57) return R.drawable.wx_drizzle;
        if (code >= 61 && code <= 67) return R.drawable.wx_rain;
        if (code >= 80 && code <= 82) return R.drawable.wx_rain;
        if (code >= 95) return R.drawable.wx_storm;
        return R.drawable.wx_cloud;
    }

    private static String get(String url) throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        c.setConnectTimeout(15000);
        c.setReadTimeout(20000);
        c.setRequestProperty("User-Agent", "GlassTV/1.0 (Android TV home screen)");
        try {
            if (c.getResponseCode() != 200) throw new IllegalStateException("HTTP " + c.getResponseCode());
            try (InputStream in = c.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buf = new byte[16 * 1024];
                int r;
                while ((r = in.read(buf)) > 0) {
                    out.write(buf, 0, r);
                    if (out.size() > 512 * 1024) throw new IllegalStateException("response too large");
                }
                return new String(out.toByteArray(), StandardCharsets.UTF_8);
            }
        } finally {
            c.disconnect();
        }
    }

    private static SharedPreferences prefs(Context ctx) {
        return ctx.getSharedPreferences("home_weather", Context.MODE_PRIVATE);
    }
}
