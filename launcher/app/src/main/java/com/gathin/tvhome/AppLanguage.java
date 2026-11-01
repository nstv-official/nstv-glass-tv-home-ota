package com.gathin.tvhome;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Multi-language support for Glass TV Home:
 * - Bahasa Indonesia (id) [Default]
 * - English (en)
 */
final class AppLanguage {
    public static final String LANG_ID = "id"; // Bahasa Indonesia (default)
    public static final String LANG_EN = "en"; // English

    private static final String PREFS_NAME = "language_prefs";
    private static final String KEY_LANG = "selected_lang";

    private AppLanguage() {}

    public static String getLanguage(Context ctx) {
        if (ctx == null) return LANG_ID;
        return ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_LANG, LANG_ID);
    }

    public static void setLanguage(Context ctx, String lang) {
        if (ctx == null || lang == null) return;
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LANG, lang)
                .apply();
    }

    public static String getLanguageDisplayName(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "English";
            case LANG_ID:
            default:
                return "Bahasa Indonesia";
        }
    }

    public static String getLanguageBadge(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "🇬🇧 EN";
            case LANG_ID:
            default:
                return "🇮🇩 ID";
        }
    }

    public static String[] getPrayerNames(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return new String[]{"Fajr", "Sunrise", "Dhuhr", "Asr", "Maghrib", "Isha"};
            case LANG_ID:
            default:
                return new String[]{"Subuh", "Syuruq", "Dzuhur", "Ashar", "Maghrib", "Isya"};
        }
    }

    public static String getPrayerHeaderPrefix(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "PRAYER TIMES";
            case LANG_ID:
            default:
                return "WAKTU SHOLAT";
        }
    }

    public static String getAyahLabel(Context ctx, String reference) {
        String lang = getLanguage(ctx);
        String prefix;
        switch (lang) {
            case LANG_EN:
                prefix = "DAILY PRAYER";
                break;
            case LANG_ID:
            default:
                prefix = "DOA HARIAN";
                break;
        }
        if (reference != null && !reference.isEmpty()) {
            return prefix + "  •  " + reference.toUpperCase(Locale.ROOT);
        }
        switch (lang) {
            case LANG_EN:
                return prefix + "  •  VERSE OF THE DAY";
            case LANG_ID:
            default:
                return prefix + "  •  AYAT PILIHAN";
        }
    }

    public static String getNextPrayerPrefix(Context ctx) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return "NEXT";
            case LANG_ID:
            default:
                return "SELANJUTNYA";
        }
    }

    public static String getPeriodString(Context ctx, int hour) {
        String lang = getLanguage(ctx);
        switch (lang) {
            case LANG_EN:
                return hour < 12 ? "AM" : "PM";
            case LANG_ID:
            default:
                if (hour >= 1 && hour < 11) return "PAGI";
                else if (hour >= 11 && hour < 15) return "SIANG";
                else if (hour >= 15 && hour < 18) return "SORE";
                else return "MLM";
        }
    }

    // --- UI Labels for Popups & Settings ---

    public static String strRestart(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Restart TV";
            case LANG_ID: default: return "Mulai Ulang TV";
        }
    }

    public static String strTurnOff(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Turn Off TV";
            case LANG_ID: default: return "Matikan TV";
        }
    }

    public static String strCleanCache(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Clean TV Cache";
            case LANG_ID: default: return "Bersihkan Cache TV";
        }
    }

    public static String strWallpaper(Context ctx) {
        return "Wallpaper";
    }

    public static String strLanguage(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Language Selection";
            case LANG_ID: default: return "Pilihan Bahasa";
        }
    }

    public static String strAllSettings(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "All Android Settings";
            case LANG_ID: default: return "Semua Pengaturan Android";
        }
    }

    public static String strClose(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Close";
            case LANG_ID: default: return "Tutup";
        }
    }

    // --- Modern Settings UI Strings ---
    public static String strSettingsSearch(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Search";
            case LANG_ID: default: return "Cari";
        }
    }

    public static String strProfileTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Profile";
            case LANG_ID: default: return "Profil";
        }
    }

    public static String strProfileSubtitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "User Profiles";
            case LANG_ID: default: return "Profil Pengguna";
        }
    }

    public static String strAvatars(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Avatars";
            case LANG_ID: default: return "Avatar";
        }
    }

    public static String strYourPhotos(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Your Photos";
            case LANG_ID: default: return "Foto Anda";
        }
    }

    public static String strYourPhotosDesc(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Add photos to the TV's Pictures or Download folder.";
            case LANG_ID: default: return "Tambahkan foto ke folder Gambar atau Unduhan TV.";
        }
    }

    public static String strEditName(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Edit Name";
            case LANG_ID: default: return "Ubah Nama";
        }
    }

    public static String strHomeScreen(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Home Screen";
            case LANG_ID: default: return "Layar Utama";
        }
    }

    public static String strAppearance(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Appearance";
            case LANG_ID: default: return "Tampilan";
        }
    }

    public static String strStatusBarWeather(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Status Bar & Weather";
            case LANG_ID: default: return "Bilah Status & Cuaca";
        }
    }

    public static String strStandByMode(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "StandBy Mode";
            case LANG_ID: default: return "Mode StandBy";
        }
    }

    public static String strRemoteAccessibility(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Remote & Accessibility";
            case LANG_ID: default: return "Remote & Aksesibilitas";
        }
    }

    public static String strChildMode(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Child Mode";
            case LANG_ID: default: return "Mode Anak";
        }
    }

    public static String strGeneral(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "General";
            case LANG_ID: default: return "Umum";
        }
    }

    public static String strTv(Context ctx) {
        return "TV";
    }

    public static String strBack(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Back";
            case LANG_ID: default: return "Kembali";
        }
    }

    public static String strCancel(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Cancel";
            case LANG_ID: default: return "Batal";
        }
    }

    public static String strLocation(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Location";
            case LANG_ID: default: return "Lokasi";
        }
    }

    public static String strLocationRow(Context ctx, String zoneName) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "📍 Location (Weather & Prayer): " + zoneName;
            case LANG_ID: default: return "📍 Lokasi (Cuaca & Sholat): " + zoneName;
        }
    }

    public static String strDynamicWeather(Context ctx, boolean on) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Dynamic Weather: " + state;
            case LANG_ID: default: return "Cuaca Dinamis: " + state;
        }
    }

    public static String strAzanRow(Context ctx, boolean on) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Adhan Settings: " + state;
            case LANG_ID: default: return "Pengaturan Adzan: " + state;
        }
    }

    public static String strDailyDoaRow(Context ctx, boolean on) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Daily Prayer: " + state;
            case LANG_ID: default: return "Doa Harian: " + state;
        }
    }

    public static String strSoundRow(Context ctx, String themeName) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Sound: " + themeName;
            case LANG_ID: default: return "Efek Suara: " + themeName;
        }
    }

    public static String strAutoUpdateRow(Context ctx, boolean on, String version) {
        String state = on ? "ON" : "OFF";
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Auto-Update: " + state + " (v" + version + ")";
            case LANG_ID: default: return "Pembaruan Otomatis: " + state + " (v" + version + ")";
        }
    }

    public static String strScreensaver(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Nightstand Screensaver";
            case LANG_ID: default: return "Screensaver Meja Tidur";
        }
    }

    public static String strAzanSettings(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Adhan Settings";
            case LANG_ID: default: return "Pengaturan Adzan";
        }
    }

    public static String strSoundEffects(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Sound Effects";
            case LANG_ID: default: return "Efek Suara";
        }
    }

    public static String strAutoUpdate(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Auto-Update";
            case LANG_ID: default: return "Pembaruan Otomatis";
        }
    }

    public static String strLanguageChangedToast(Context ctx, String lang) {
        switch (lang) {
            case LANG_EN:
                return "Language switched to English 🇬🇧";
            case LANG_ID:
            default:
                return "Bahasa diubah ke Bahasa Indonesia 🇮🇩";
        }
    }

    public static String strNowBadge(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Now";
            case LANG_ID: default: return "Aktif";
        }
    }

    // --- Restart Dialog ---
    public static String strRestartTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔄 Restart TV";
            case LANG_ID: default: return "🔄 Mulai Ulang TV";
        }
    }

    public static String strRestartMsg(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Are you sure you want to restart this TV?";
            case LANG_ID: default: return "Apakah Anda yakin ingin memulai ulang (restart) TV ini?";
        }
    }

    public static String strRestartConfirm(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Yes, Restart";
            case LANG_ID: default: return "Ya, Restart";
        }
    }

    public static String strRestartToast(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Restarting TV...";
            case LANG_ID: default: return "Memulai ulang TV...";
        }
    }

    // --- Turn Off Dialog ---
    public static String strTurnOffTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔌 Turn Off TV";
            case LANG_ID: default: return "🔌 Matikan TV";
        }
    }

    public static String strTurnOffMsg(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Would you like to turn off or put the TV into sleep mode?";
            case LANG_ID: default: return "Apakah Anda ingin mematikan atau menidurkan TV?";
        }
    }

    public static String strTurnOffConfirm(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Turn Off";
            case LANG_ID: default: return "Matikan TV";
        }
    }

    // --- Clean Cache Dialog ---
    public static String strCleanCacheTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🧹 Clean TV Cache";
            case LANG_ID: default: return "🧹 Bersihkan Cache TV";
        }
    }

    public static String strCleanCacheMsg(Context ctx, String freeStr, String totalStr, int freePct, int usedPct) {
        switch (getLanguage(ctx)) {
            case LANG_EN:
                return "Clear app cache files, temporary download fragments, and refresh TV memory.\n\n"
                        + "📊 TV Storage Status:\n"
                        + "• Free Space: " + freeStr + " of " + totalStr + " (" + freePct + "% free)\n"
                        + "• Usage: " + usedPct + "%";
            case LANG_ID:
            default:
                return "Bersihkan file cache aplikasi, file unduhan sementara, dan segarkan memori TV.\n\n"
                        + "📊 Status Penyimpanan TV:\n"
                        + "• Ruang Bebas: " + freeStr + " dari " + totalStr + " (" + freePct + "% bebas)\n"
                        + "• Penggunaan: " + usedPct + "%";
        }
    }

    public static String strCleanCacheStart(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🧹 Start Cleaning";
            case LANG_ID: default: return "🧹 Mulai Bersihkan";
        }
    }

    public static String strCleanCacheToast(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Cleaning TV cache...";
            case LANG_ID: default: return "Sedang membersihkan cache TV...";
        }
    }

    public static String strCleanCacheDoneTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "✨ Cleaning Complete";
            case LANG_ID: default: return "✨ Pembersihan Selesai";
        }
    }

    public static String strCleanCacheDoneMsg(Context ctx, String freedStr, boolean hasFreed) {
        switch (getLanguage(ctx)) {
            case LANG_EN:
                return hasFreed
                        ? "✅ Successfully cleaned " + freedStr + " of cache files!\nTV storage is now lighter and smoother."
                        : "✅ TV cache is already completely clean.\nTV storage and memory are at optimal levels.";
            case LANG_ID:
            default:
                return hasFreed
                        ? "✅ Berhasil membersihkan " + freedStr + " file cache!\nPenyimpanan TV kini lebih lega dan lancar."
                        : "✅ Cache TV sudah bersih sepenuhnya.\nPenyimpanan dan memori TV berada pada tingkat optimal.";
        }
    }

    public static String strCleanCacheUsageLine(Context ctx, int before, int after) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "\n\n📊 Storage Usage: " + before + "% → " + after + "%";
            case LANG_ID: default: return "\n\n📊 Penggunaan Penyimpanan: " + before + "% → " + after + "%";
        }
    }

    public static String strDone(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Done";
            case LANG_ID: default: return "Selesai";
        }
    }

    // --- Azan Settings Dialog ---
    public static String strAzanSettingsHeaderTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🕌 Adhan Settings & Options";
            case LANG_ID: default: return "🕌 Pengaturan & Pilihan Adzan";
        }
    }

    public static String strAzanSettingsHeaderSub(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Floating glass popup card over all apps & adhan audio settings";
            case LANG_ID: default: return "Notifikasi popup kartu kaca melayang di atas semua aplikasi & pengaturan audio";
        }
    }

    public static String strAzanSectionMaster(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "A. ADHAN POPUP MASTER SWITCH";
            case LANG_ID: default: return "A. STATUS UTAMA POPUP ADZAN";
        }
    }

    public static String strAzanMasterTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔔 TV Adhan Popup Notification";
            case LANG_ID: default: return "🔔 Notifikasi Popup Adzan TV";
        }
    }

    public static String strAzanMasterDesc(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Show floating prayer card over Netflix, YouTube & all apps";
            case LANG_ID: default: return "Tampilkan kartu waktu sholat melayang di atas Netflix, YouTube & semua app";
        }
    }

    public static String strAzanSectionPrayers(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "B. PRAYER TIME SELECTION (ON / OFF)";
            case LANG_ID: default: return "B. PILIHAN WAKTU SHOLAT (ON / OFF)";
        }
    }

    public static String strAzanSectionAudio(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "C. ADHAN AUDIO SELECTION";
            case LANG_ID: default: return "C. PILIHAN SUARA ADZAN";
        }
    }

    public static String strAzanAudioCurrent(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔊 Current Adhan Audio";
            case LANG_ID: default: return "🔊 Suara Adzan Saat Ini";
        }
    }

    public static String strAzanAudioChangeBtn(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "CHANGE ❯";
            case LANG_ID: default: return "UBAH ❯";
        }
    }

    public static String strAzanSectionTest(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "D. TEST ADHAN POPUP";
            case LANG_ID: default: return "D. UJI POPUP ADZAN";
        }
    }

    public static String strAzanTestTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "▶️ Test Adhan Popup Now";
            case LANG_ID: default: return "▶️ Uji Popup Adzan Sekarang";
        }
    }

    public static String strAzanTestDesc(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Trigger floating card & play selected audio";
            case LANG_ID: default: return "Tampilkan kartu melayang & putar audio pilihan";
        }
    }

    public static String strAzanTestBtn(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "TEST ▶";
            case LANG_ID: default: return "UJI ▶";
        }
    }

    public static String strAzanInfoText(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "💡 Prayer times are automatically synchronized based on official schedules. The adhan card appears over Netflix, YouTube & streaming apps.";
            case LANG_ID: default: return "💡 Waktu sholat disinkronkan secara otomatis sesuai jadwal resmi. Kartu adzan tetap muncul di atas Netflix, YouTube & aplikasi streaming.";
        }
    }

    public static String strAzanCloseBtn(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "✕ Close Settings";
            case LANG_ID: default: return "✕ Tutup Pengaturan";
        }
    }

    public static String strAzanChooseAudioTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🔊 Choose Adhan Audio";
            case LANG_ID: default: return "🔊 Pilih Suara Adzan";
        }
    }

    public static String[] strAzanSoundOptions(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN:
                return new String[]{
                        "🕋 Masjid al-Haram Makkah Adhan (Full)",
                        "🕌 Al-Masjid an-Nabawi Madinah Adhan (Full)",
                        "🔔 Soft Chime Tone (Brief ~5s)",
                        "🔕 Silent Mode (Visual Only, No Audio)"
                };
            case LANG_ID:
            default:
                return new String[]{
                        "🕋 Adzan Masjidil Haram Makkah (Penuh)",
                        "🕌 Adzan Masjid Nabawi Madinah (Penuh)",
                        "🔔 Nada Chime Lembut (Singkat ~5 detik)",
                        "🔕 Mode Hening (Hanya Visual Tanpa Suara)"
                };
        }
    }

    // --- Wallpaper Dialog ---
    public static String strWallpaperDialogTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "🖼️ Select 4K Wallpaper Collection";
            case LANG_ID: default: return "🖼️ Pilih Koleksi Wallpaper 4K";
        }
    }

    public static String strWallpaperDialogSub(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Pexels & Unsplash CDN • Ultra HD 3840×2160 Resolution";
            case LANG_ID: default: return "Pexels & Unsplash CDN • Resolusi Ultra HD 3840×2160";
        }
    }

    public static String strWallpaperIntervalTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "⏱️ Wallpaper Shuffle Interval";
            case LANG_ID: default: return "⏱️ Interval Shuffle Wallpaper";
        }
    }

    public static String strWallpaperIntervalSub(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Select how often wallpaper changes automatically";
            case LANG_ID: default: return "Pilih frekuensi wallpaper berganti otomatis";
        }
    }

    public static String strWallpaperIntervalText(Context ctx, int curInterval) {
        String lang = getLanguage(ctx);
        if (curInterval == 1) {
            return LANG_EN.equals(lang) ? "Every 1 Minute" : "Setiap 1 Menit";
        } else if (curInterval == 5) {
            return LANG_EN.equals(lang) ? "Every 5 Minutes (Recommended)" : "Setiap 5 Menit (Disarankan)";
        } else if (curInterval == 10) {
            return LANG_EN.equals(lang) ? "Every 10 Minutes (Recommended)" : "Setiap 10 Menit (Disarankan)";
        } else if (curInterval == 15) {
            return LANG_EN.equals(lang) ? "Every 15 Minutes" : "Setiap 15 Menit";
        } else if (curInterval == 30) {
            return LANG_EN.equals(lang) ? "Every 30 Minutes" : "Setiap 30 Menit";
        } else if (curInterval == 60) {
            return LANG_EN.equals(lang) ? "Every 1 Hour" : "Setiap 1 Jam";
        } else if (curInterval == 0) {
            return LANG_EN.equals(lang) ? "Manual Only (Off)" : "Manual Saja (Mati)";
        } else {
            return LANG_EN.equals(lang) ? "Every " + curInterval + " Minutes" : "Setiap " + curInterval + " Menit";
        }
    }

    // --- Azan Popup Card Strings ---
    public static String strAzanPopupTitle(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "ADHAN";
            case LANG_ID: default: return "ADZAN";
        }
    }

    public static String strAzanPopupHint(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Press Back / OK to dismiss";
            case LANG_ID: default: return "Tekan Kembali / OK untuk menutup";
        }
    }

    public static String strAzanPopupSilentHint(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Silent Mode • Press Back to dismiss";
            case LANG_ID: default: return "Mode Hening • Tekan Kembali untuk menutup";
        }
    }

    public static String strAzanPopupChimeHint(Context ctx) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Short Chime Tone • Press Back to dismiss";
            case LANG_ID: default: return "Nada Dering Singkat • Tekan Kembali untuk menutup";
        }
    }

    public static String strAzanPopupDownloadingHint(Context ctx, String soundLabel) {
        switch (getLanguage(ctx)) {
            case LANG_EN: return "Downloading audio " + soundLabel + "...";
            case LANG_ID: default: return "Mengunduh audio " + soundLabel + "...";
        }
    }
}
