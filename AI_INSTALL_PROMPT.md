# 🤖 Glass TV v4.0 — Prompt Install & Audit Lingkungan AI Agent Khusus

> Salin dan tempel (copy & paste) seluruh prompt di bawah ke asisten AI coding mana pun (**Google Antigravity**, **Claude Code**, **Cursor**, atau **Copilot**) yang terhubung ke jaringan Wi-Fi yang sama dengan Android TV Anda.

---

```text
Saya ingin memasang Glass TV Home v4.0 pada Android TV rumah saya dari repositori:
https://github.com/nstv-official/nstv-glass-tv-home-ota

PANDUAN KHUSUS UNTUK AI:
Jangan memasang APK secara sembarangan tanpa mempelajari lingkungan TV terlebih dahulu. Mohon ikuti 4 Fase terperinci di bawah langkah demi langkah:

========================================================================
FASE 1: AUDIT LINGKUNGAN TV SECARA MENDALAM (DEEP TV ENVIRONMENT AUDIT)
========================================================================
1. Minta IP TV dari saya jika belum diketahui, dan hubungkan melalui ADB:
   adb connect <TV_IP>:5555

2. Investigasi Versi Sistem Operasi & Model TV:
   - Jalankan: adb shell getprop ro.build.version.release (Versi Android: misalnya Android 9, 10, 11, 12, atau 14)
   - Jalankan: adb shell getprop ro.build.version.sdk (API Level)
   - Jalankan: adb shell getprop ro.product.model & adb shell getprop ro.product.manufacturer
   * PERHATIAN: Jika perangkat adalah Samsung Tizen atau LG webOS, informasikan bahwa tidak didukung dan hentikan pemasangan.

3. Investigasi Resolusi Layar TV (1080p vs 4K UHD vs 720p) & Refresh Rate:
   - Jalankan: adb shell dumpsys display | grep -E "mBaseDisplayInfo|supportedModes|mode"
   - Jalankan: adb shell wm size (Resolusi kanvas UI/framebuffer)
   - Jalankan: adb shell wm density (DPI kepadatan layar)
   * Laporkan kepada saya apakah panel TV adalah 4K fisik dengan framebuffer 1080p 60fps yang lancar.

4. Investigasi IP Jaringan Lokal (LAN / Wi-Fi) TV:
   - Jalankan: adb shell ip -f inet addr show wlan0 (atau eth0)
   - Pastikan alamat IP berada dalam subnet Wi-Fi rumah yang sama agar fungsi Unggah Wallpaper Ponsel (QR HTTP Server) berjalan tanpa hambatan firewall.

5. Identifikasi Peluncur Asal (Stock Launcher) Saat Ini:
   - Jalankan: adb shell cmd package resolve-activity -c android.intent.category.HOME -a android.intent.action.MAIN
   - Catat package name peluncur asal (misalnya: com.google.android.apps.tv.launcherx, com.google.android.tvlauncher, com.xiaomi.mitv.tvhome, dsb).

========================================================================
FASE 2: KONFIRMASI KEMAMPUAN SYSTEM SELF-ADAPTATION (GLASS TV v4.0)
========================================================================
Glass TV v4.0 dilengkapi mesin penyesuaian mandiri (self-adapting) secara otomatis:
- Resolusi & DPI: Menggunakan vektor dinamik dan dp-scaling yang menyesuaikan ukuran UI secara otomatis pada TV 720p, 1080p, dan 4K tanpa terpotong (zero clipping).
- Pengelolaan Memori Wallpaper: Foto ponsel beresolusi tinggi (48MP/8K) yang diunggah akan diperkecil sesuai ukuran layar menggunakan inSampleSize dan RGB_565 supaya TV dengan RAM 1GB / 2GB tidak mengalami Out-Of-Memory.
- Dynamic Port Failover HTTP Server: Server unggah wallpaper QR mencoba port 8080 -> 8888 -> 8081 -> dynamic OS port 0 jika port tertentu diblokir aplikasi lain di TV.
- Universal Typography: Antarmuka v4.0 menggunakan font modern "Inter" secara penuh.

========================================================================
FASE 3: PEMASANGAN, SYSTEM DEBLOAT & KUNCI TOMBOL HOME REMOTE
========================================================================
PERINGATAN: Jangan biarkan TV memperlakukan Glass TV sebagai aplikasi biasa! Pengguna yang memasang APK biasa akan mendapati tombol Home remote tetap kembali ke peluncur asal Android TV / Google TV.

1. Cadangkan (Backup) daftar paket TV sebelum mengubah apa pun:
   adb shell pm list packages -f > backup_packages.txt

2. Pasang APK versi 4.0:
   adb install -r release/glass-tv-home.apk
   (Atau bangun menggunakan ./gradlew :app:assembleRelease jika repo diklon)

3. Kunci Glass TV Home sebagai Peluncur Utama Permanen (Bypass Google TV Home Hijack):
   - Jalankan perintah resmi Android:
     adb shell cmd package set-home-activity com.gathin.tvhome/.MainActivity
   - Jika firmware pabrikan (seperti Xiaomi PatchWall atau Google TV LauncherX) masih mencoba mengambil alih tombol Home, nonaktifkan peluncur asal:
     adb shell pm disable-user --user 0 <stock_launcher_pkg_yang_terdeteksi_di_Fase_1>

4. Lakukan Debloat Iklan & Telemetri Pabrikan:
   - Nonaktifkan layanan periklanan dan pelacakan pihak ketiga untuk membebaskan RAM TV sebanyak 300MB - 600MB.
   - PENTING: Jangan sentuh layanan dasar seperti HDMI/Input TV, Bluetooth Remote, Wi-Fi, Audio, dan Google Play Store.

5. Konfigurasi Lokasi Pengguna (Stasiun KLHK ISPU & Waktu Sholat Kemenag RI):
   - Tanya saya lokasi di Indonesia (contoh: Jakarta, Bandung, Surabaya, Semarang, Yogyakarta, Medan, Makassar, Denpasar).
   - Kirim broadcast konfigurasi:
     adb shell am broadcast -a com.gathin.tvhome.SET_LOCATION -p com.gathin.tvhome --es query "<LOKASI>"

========================================================================
FASE 4: PELUNCURAN & KONFIRMASI VISUAL (VERIFICATION)
========================================================================
1. Jalankan Glass TV Home:
   adb shell am start -n com.gathin.tvhome/.MainActivity

2. Ambil tangkapan layar (screenshot) dari TV dan periksa secara visual:
   adb shell screencap -p /sdcard/tv_verify.png && adb pull /sdcard/tv_verify.png ./tv_verify.png
   - Pastikan tampilan jam, tanggal, widget cuaca, dan ayat harian dirender dengan font Inter.
   - Pastikan tombol profil pengguna di bar atas menampilkan ikon avatar hewan bulat yang aktif.
   - Pastikan dock aplikasi tersusun rapi.

3. Uji tombol Home remote:
   adb shell input keyevent KEYCODE_HOME
   - Pastikan layar tetap berada di Glass TV Home dan tidak kembali ke peluncur lama.

4. Berikan saya ringkasan status pemasangan dan metode pemulihan (restore command) jika diperlukan.
```
