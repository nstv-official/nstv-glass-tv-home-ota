# Panduan Pengguna Glass TV Home (Untuk Beginner) 📺✨

Selamat datang di **Glass TV Home**! Panduan ini khusus untuk pengguna baru (*beginner-friendly*) agar Anda bisa memasang, mengoptimalkan TV supaya ngebut tanpa iklan, menggunakan remote TV, dan menikmati semua fitur menarik dengan mudah tanpa pusing.

---

## 🌟 1. Apa Itu Glass TV Home?

Glass TV Home adalah pengganti layar utama (*launcher*) untuk Android TV dan Google TV (seperti Xiaomi Mi Box S, Mi TV Stick, Android TV Sharp, Sony, TCL, Philips, Mecool, dsb):
* 🚀 **Tanpa Iklan & Sangat Ringan**: Hanya berukuran ~453 KB, lancar dan tidak membebani TV (menghemat lebih dari 400 MB RAM dibanding layar asal).
* 🪟 **Gaya Kaca Frosted (tvOS)**: Desain modern seperti Apple TV dengan bayangan kaca mengambang dan susunan aplikasi yang rapi.
* 🕌 **Waktu Sholat & Ayat Harian**: Jadwal 6 waktu sholat Indonesia secara otomatis dengan ayat al-Quran pilihan harian.
* ⛅ **Dashboard Cuaca & Kabut Asap (API)**: Cek suhu terkini, prakiraan 12 jam, 7 hari, serta indeks pencemaran udara resmi (KLHK ISPU / Stasiun Pemantauan Kualitas Udara Kementerian Lingkungan Hidup).
* 🌙 **Screensaver Pintar & Hemat Listrik**: Masuk screensaver setelah 10 menit diam, dan TV mati otomatis (*sleep standby*) setelah 2 jam.

---

## 📦 Unduh File APK Resmi (Official Direct Downloads)

Silakan unduh file pemasangan resmi sesuai perangkat Anda:
* 📱 **Untuk Ponsel / Tablet Android (Aplikasi Pembantu Pemasangan — Direkomendasikan)**:  
  [**`glass-tv-installer.apk`** (6.6 MB)](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk) — **Pilihan Utama!** Pasang langsung ke TV dan kunci tombol Home lewat Wi-Fi tanpa perlu komputer (PC) atau kabel.
* 📺 **Untuk Android TV / Google TV (Launcher Utama v4.0)**:  
  [**`glass-tv-home.apk`** (1.5 MB)](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk) — Versi terbaru `v4.0.3` (Tipografi universal Inter, ikon avatar profil aktif di bar atas, mesin adaptasi mandiri 1080p/4K, server unggah wallpaper fail-safe).

> [!CAUTION]
> ### ⚠️ JANGAN PASANG APK SECARA SIDELOAD BIASA TANPA INSTALLER / AI!
> Banyak pengguna baru mengunduh file APK langsung memakai flashdisk atau *Downloader* di TV. Ini menimbulkan masalah:
> 1. **Tombol Home Tidak Berfungsi**: Android TV / Google TV memperlakukan Glass TV sebagai aplikasi sampingan biasa. Saat Anda menekan tombol **HOME** pada remote TV, layar tetap akan kembali ke peluncur lama pabrikan.
> 2. **Menu Pengaturan Disembunyikan**: Google TV sengaja menyembunyikan pilihan untuk mengganti default launcher dari pengguna.
> 3. **Solusi Paling Praktis**: Gunakan **Aplikasi Ponsel (`glass-tv-installer.apk`)** atau **Prompt AI 1-Baris** lewat komputer. Kedua cara ini mengunci tombol Home secara resmi melalui perintah sistem (`cmd package set-home-activity`).

> [!NOTE]
> ### 🇮🇩 Pengguna Android TV Box dari ISP (mis. IndiHome / Biznet Home): Bisa Dipakai & Apakah Garansi Hangus?
> * **Bisa dipakai? YA, 100% BISA!** Box IPTV dari ISP Indonesia umumnya berjalan di sistem Android TV resmi (*certified Android TV*). Aplikasi resmi TV streaming ISP Anda (siaran langsung & saluran langganan) tetap ada dalam daftar aplikasi dan bisa ditonton seperti biasa.
> * **Membatalkan garansi (void warranty)? SAMA SEKALI TIDAK!** Glass TV Home dipasang sebagai aplikasi biasa (*user-space APK*). Ia **TIDAK me-root (no root)** kotak TV Anda, **TIDAK membuka bootloader**, dan **TIDAK mengubah firmware internal ISP**. Anda bisa *uninstall* kapan saja melalui menu Pengaturan TV untuk kembali ke tampilan asal tanpa efek apa pun.

---

## 🚀 2. Panduan Pemasangan Menurut Prioritas Perangkat Anda

Ikuti panduan sesuai perangkat yang Anda miliki dengan urutan prioritas berikut:

### ⭐ Prioritas 1: Pengguna Ponsel Android (Fokus Utama — Paling Mudah & Direkomendasikan)

Jika Anda punya ponsel atau tablet Android, ini adalah metode **paling mudah, cepat, dan 100% otomatis** tanpa memerlukan komputer atau kabel USB!

1. **Unduh & Pasang APK Pembantu di Ponsel Android Anda**:
   - Unduh [**`glass-tv-installer.apk`**](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk) (~6.6 MB) langsung ke ponsel Anda dan pasang (*install*).
2. **Persiapan Singkat di TV (Cukup Sekali)**:
   - Pada remote TV: Buka **Settings (Pengaturan ⚙️)** $\rightarrow$ **System / Device Preferences** $\rightarrow$ **About (Tentang)** $\rightarrow$ Gulir ke **Build (Android TV OS Build)**.
   - Tekan tombol **OK remote sebanyak 7 kali berturut-turut** hingga muncul *"You are now a developer!"*.
   - Tekan *Back*, masuk ke menu baru **Developer options**, dan aktifkan:
     - ✅ **USB debugging** (Ubah ke ON).
     - *(Jika ada Network/Wireless debugging, nyalakan juga)*.
   - Pastikan ponsel Android dan TV Anda terhubung pada **Wi-Fi yang sama**.
3. **1-Klik Pasang dari Ponsel**:
   - Buka aplikasi **Glass TV Installer** di ponsel Anda.
   - Tekan tombol **`🔍 Pindai Android TV di Jaringan Wi-Fi`** — aplikasi akan mencari TV Anda secara otomatis!
   - Tekan tombol biru **`🚀 Pasang ke TV Sekarang`**.
   - Pada layar TV Anda, jendela *"Allow USB debugging?"* akan muncul. Centang kotak ☑ **Always allow from this computer** dan tekan **OK / Allow** menggunakan remote TV.
   - Aplikasi di ponsel akan mengirim dan memasang Glass TV Home langsung ke TV dalam beberapa detik!
4. **Jalankan Tampilan Baru**:
   - Tekan tombol **`▶️ Buka di TV`** di ponsel atau tekan tombol Home pada remote TV.
   - Selesai! TV Anda kini punya tampilan kaca modern yang sangat cepat.

> 💡 **Opsi Server Web Lokal (Jika TV Tanpa Debugging)**:  
> Jika TV Anda sulit menyalakan mode debugging, buka aplikasi di ponsel dan tekan tombol **`🌐 Server Web TV`**. Lalu buka aplikasi **Downloader** atau browser di TV dan masukkan alamat IP lokal yang tertera (mis.: `http://192.168.x.x:8080`) untuk mengunduh langsung ke TV.

---

### 💻 Prioritas 2: Jika Tidak Ada Ponsel Android — Pakai Laptop atau PC (Windows / Mac / Linux)

Jika Anda **tidak punya ponsel Android** (misalnya hanya ada laptop atau komputer di rumah), gunakan komputer Anda dengan bantuan AI atau terminal:

1. **Persiapan TV**: Nyalakan **USB debugging** di TV (langkah sama seperti di atas) dan hubungkan komputer ke Wi-Fi rumah yang sama.
2. **Bantuan AI Otomatis (1 Baris Prompt — Sangat Mudah)**:
   Buka asisten AI di komputer Anda (**Google Antigravity** [Gratis / Direkomendasikan], **Claude Code**, atau **Cursor**). Salin dan tempel satu baris ini saja:
   ```text
   install ini ke tv rumah saya: https://github.com/nstv-official/nstv-glass-tv-home-ota
   ```
   - AI akan terhubung ke TV melalui Wi-Fi (`adb connect <ip>:5555`).
   - AI membuat cadangan (*backup*), mematikan aplikasi bloatware pabrikan yang memperlambat TV, mengaktifkan pemblokir iklan, dan memasang Glass TV Home secara otomatis!
   - Tekan **Allow** pada layar TV ketika jendela izin debugging muncul.
3. **Pemasangan Manual Terminal (Alternatif Developer)**:
   ```bash
   adb connect <ALAMAT_IP_TV>:5555
   adb install -r release/glass-tv-home.apk
   adb shell am start -n com.gathin.tvhome/.MainActivity
   ```

---

### 🍏 Prioritas 3: Jika Tidak Ada Android & Tidak Ada Komputer (Hanya iPhone) — Pakai iPhone & Bantuan Muse.ai

Jika Anda **tidak punya ponsel Android dan tidak punya komputer** (hanya punya iPhone atau iPad):

1. **Gunakan Bantuan AI Melalui Muse.ai**:
   - Buka browser Safari di iPhone Anda dan kunjungi [**muse.ai**](https://muse.ai) (atau platform asisten web AI pilihan Anda).
   - Berikan prompt bantuan AI ini kepada Muse.ai:
     ```text
     Saya menggunakan iPhone dan punya Android TV model [merek TV Anda, mis.: Xiaomi Mi Box S / TCL / Sharp]. Saya ingin memasang launcher ringan ini: https://github.com/nstv-official/nstv-glass-tv-home-ota
     Bantu saya langkah demi langkah cara pasang file APK ini dari iPhone ke TV, dan berikan daftar aplikasi bawaan pabrik (bloatware) yang tidak berguna pada model TV saya untuk dihapus atau dinonaktifkan supaya TV ngebut tanpa iklan.
     ```
2. **Langkah Pemasangan APK dari iPhone ke TV (Panduan AI)**:
   - **Cara A (Aplikasi Downloader di TV)**: Di TV Anda, buka Google Play Store dan pasang aplikasi gratis **Downloader by AFTVnews**. Buka Downloader dan masukkan tautan file APK:  
     `https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk`  
     TV akan mengunduh dan memasang Glass TV Home langsung!
   - **Cara B (Pakai LocalSend di iPhone & TV)**: Unduh aplikasi gratis **LocalSend** di iPhone (App Store) dan di Android TV (Play Store). Unduh file `glass-tv-home.apk` di iPhone, lalu kirim file tersebut ke TV lewat LocalSend pada Wi-Fi yang sama dan tekan **Install**.
3. **Menghapus Aplikasi Tidak Berguna & Iklan (Debloat & AdBlock)**:
   - Minta Muse.ai menyebutkan nama-nama aplikasi pabrikan yang aman dimatikan sesuai merek TV Anda.
   - Pada remote TV: Masuk ke **Settings ⚙️** $\rightarrow$ **Apps** $\rightarrow$ **See all apps**, pilih aplikasi bawaan pabrik yang tidak berguna menurut saran Muse.ai dan pilih **Disable / Nonaktifkan**.
   - **Blokir Iklan Tanpa Komputer**: Pada remote TV, buka **Settings ⚙️** $\rightarrow$ **Network & Internet** $\rightarrow$ **Private DNS**, pilih *Private DNS provider hostname* dan ketik:
     ```text
     dns.adguard-dns.com
     ```
     Seketika semua iklan buatan layar utama, pelacak data pabrikan, dan analitik sistem diblokir total!

---

### 🔄 Untuk Pengguna Lama: Prompt Perbarui Patch Terbaru (Update Patch)
Jika Anda sudah memasang Glass TV Home dan ingin memperbaruinya ke versi atau patch terbaru tanpa ribet:
```text
update patch latest glass tv ke tv rumah saya: https://github.com/nstv-official/nstv-glass-tv-home-ota
```
*(Atau: `update glass tv ke tv saya: https://github.com/nstv-official/nstv-glass-tv-home-ota`)*

AI akan terhubung ke TV melalui Wi-Fi, memasang pembaruan (`adb install -r`), dan membuka kembali aplikasi dalam 5 detik. **Semua gambar wallpaper, susunan aplikasi di dock, dan pilihan zona sholat/suara Anda akan 100% tetap!**

> [!IMPORTANT]
> **🚨 Perintah Khusus Untuk Asisten AI (AI Assistant Rule)**:
> Ketika pengguna memberikan prompt `install ini ke tv rumah saya: https://github.com/nstv-official/nstv-glass-tv-home-ota`, AI **WAJIB** bertanya kepada pengguna:
> 1. *"Apa alamat IP TV Anda di menu Settings -> Network & Internet (atau merek TV Anda)?"* (jika belum terdeteksi otomatis)
> 2. *"Di mana lokasi Anda di Indonesia (contoh: Jakarta, Bandung, Surabaya, Semarang, Yogyakarta, Medan, Makassar, Denpasar, dll.) agar data kabut asap/ISPU resmi KLHK dan jadwal waktu sholat Kemenag RI dapat disinkronkan secara otomatis?"*
> 
> Setelah terhubung ke TV, AI bisa langsung memasang APK resmi yang tersedia dari `release/glass-tv-home.apk` atau meng-compile APK baru, menjalankan debloat, dan menetapkan lokasi pengguna via:
> ```bash
> adb shell am broadcast -a com.gathin.tvhome.SET_LOCATION -p com.gathin.tvhome --es query "<NAMA_LOKASI>"
> ```
> *(Jika pengguna tidak menyebutkan lokasi, sistem akan memakai lokasi bawaan: **Jakarta** / zona WIB Kemenag RI).*

---

### 📋 Langkah 3: Tekan "Allow" Pada Layar TV
Saat AI mulai menghubungkan diri ke TV, satu jendela konfirmasi akan muncul di layar TV Anda:
* *"Allow USB debugging from this computer?"*
* Centang kotak: ☑ **"Always allow from this computer"**
* Pilih dan tekan tombol **OK / Allow**.

AI akan mengurus sisanya: menghapus bloatware yang memperlambat TV, mengaktifkan pemblokir iklan, memasang Glass TV Home, dan menampilkan gambar tangkapan layar (*screenshot*) TV Anda yang kini sangat cepat dan bersih!

---

### ↩️ Cara Kembali Ke Semula (Undo / Restore)
Semua perubahan yang dilakukan AI **100% aman dan bisa dikembalikan**:
* AI tidak menghapus file sistem secara permanen, hanya menonaktifkannya.
* AI secara otomatis menghasilkan skrip pemulihan (`restore.sh` / `restore.ps1`).
* Jika Anda ingin kembali ke layar asal pabrikan di kemudian hari, Anda cukup memberi tahu AI:
  > *"Tolong restore semula TV saya kepada pengaturan dan launcher asal pabrik."*
  Semua aplikasi asal akan dipulihkan seketika seperti semula.

---

## 🎮 3. Cara Mengontrol dengan Remote TV (D-Pad)

Sangat mudah dan intuitif! Anda hanya butuh tombol arah pada remote TV Anda:

| Tombol Remote | Aksi / Fungsi |
| :--- | :--- |
| **Tombol HOME (Tekan Sekali)** | Kembali ke layar utama dari aplikasi mana pun. Jika sudah di layar utama, buka screensaver StandBy. |
| **Tekan & Tahan HOME (Long-Press)** | Buka Menu Pengaturan Kaca Mengambang (*Quick Settings Popup*) secara global dari aplikasi mana pun (termasuk Netflix, YouTube, dll.). |
| **Tombol ATAS (▲)** | Naik ke atas untuk memilih **Kapsul Cuaca & Kabut Asap**, menu Inputs, Wi-Fi, atau **Tombol Pengaturan (⚙️ Settings)**. |
| **Tombol TENGAH (OK)** | • Tekan pada aplikasi untuk membuka aplikasi tersebut.<br>• Tekan pada kapsul cuaca untuk membuka **Dashboard Cuaca & Kualitas Udara penuh**.<br>• Tekan pada tombol **Settings (⚙️)** untuk membuka **Menu Dropdown Kaca** (Wallpaper, Kabut Asap, Waktu Sholat, Android Settings).<br>• **Tekan & Tahan (Long-press)** pada aplikasi untuk menata atau memindahkan ikon ke dalam/luar dock. |
| **Tombol BAWAH (▼)** | Turun kembali ke baris aplikasi utama (*Dock*). |
| **Tombol KIRI / KANAN (◀ / ▶)** | Bergerak antar aplikasi atau berpindah antar menu atas. |
| **Tombol BACK (↩)** | Keluar dari Dashboard Cuaca, Dialog Pilihan, atau Screensaver dan kembali ke layar utama. |

---

## 📱 4. Penjelasan Fitur-Fitur Utama

### A. Kapsul Cuaca & Indeks Kabut Asap (Bawah Jam)
* Terletak tepat di bawah jam utama (contoh: `☁ 27° Overcast  •  ISPU 106 Tidak Sehat`).
* **Tekan tombol Atas (▲) lalu tekan OK** untuk membuka layar cuaca penuh:
  1. **Suhu Terkini & Angin**: Suhu, suhu terasa (*feels like*), kelembapan, kecepatan angin, indeks UV, waktu matahari terbit & terbenam.
  2. **Kualitas Udara Resmi (KLHK ISPU)**: Menggunakan data resmi stasiun Pemantauan Kualitas Udara Kementerian Lingkungan Hidup (KLHK) Indonesia dengan bar warna standar ISPU nasional (Baik: Hijau, Sedang: Biru Langit, Tidak Sehat: Jingga, Sangat Tidak Sehat: Merah, Berbahaya: Maroon) dan saran kesehatan sesuai level ISPU terkini.
  3. **Prakiraan 12 Jam**: Baris kartu jam demi jam dengan suhu dan persentase probabilitas hujan (`↑ %`).
  4. **Prakiraan 7 Hari**: pandangan cuaca sepanjang pekan dengan suhu maksimum/minimum.

📍 **Cara Ganti Lokasi Stasiun Kabut Asap & Waktu Sholat Manual Pakai Remote TV (Jika Pasang APK Sendiri):**
Pengguna yang memasang file APK secara manual atau pindah rumah **TIDAK PERLUKAN KOMPUTER ATAU AI** untuk mengganti lokasi:
1. Pada layar utama, tekan tombol **Atas (▲)** dan tekan **OK** pada kapsul cuaca untuk membuka Dashboard Cuaca.
2. Tekan tombol **Atas (▲)** sekali lagi ke tombol **📍 Ganti Stasiun / Change** (terletak di sebelah nama kota).
3. Tekan **OK**. Menu pilihan daftar stasiun akan muncul:
   - *Jakarta, Tangerang, South Tangerang, Bandung, Semarang, Surabaya, Yogyakarta, Medan, Palembang, Pekanbaru, Makassar, Denpasar, Banjarmasin, Balikpapan, Manado, dan puluhan stasiun KLHK/ISPU lainnya di seluruh Indonesia*.
4. Gunakan tombol **Atas / Bawah** remote untuk memilih stasiun Anda, lalu tekan **OK**.
5. Seketika bacaan Indeks Standar Pencemaran Udara (ISPU) dan zona waktu sholat (Kemenag RI: WIB/WITA/WIT) akan diperbarui sesuai kota pilihan Anda!

### B. Waktu Sholat & Ayat Al-Quran Harian
* **Jadwal Sholat 6 Waktu**: Subuh, Syuruq, Dzuhur, Ashar, Maghrib, Isya sesuai zona waktu Indonesia secara tepat.
* **Warna Hijau Zamrud**: Waktu sholat yang sedang berlangsung otomatis berubah hijau terang agar Anda selalu sadar waktu ibadah.
* **Ayat Al-Quran Pilihan**: Ayat al-Quran harian lengkap dengan teks Arab yang indah dan terjemahan artinya.

### C. Gambar Latar & Pilihan Kategori Wallpaper (4K Dynamic)
* **Koleksi Tema Beragam**: Kini Anda bisa memilih kategori tema wallpaper favorit langsung dari layar utama melalui menu Pengaturan (ikon ⚙️):
  - 🌿 **Alam & Lanskap**: Lanskap alam 4K kelas dunia dengan info nama lokasi, negara, dan keterangan singkat tiap minggu.
  - 🇮🇩 **Landmark & Destinasi Terkenal Indonesia**: 16 landmark dan destinasi ikonik Nusantara koleksi 4K dari Wikimedia Commons — Candi Borobudur, Kepulauan Pianemo Raja Ampat, Api Biru Kawah Ijen, Komodo, dan lainnya — diputar otomatis atau dipilih satu per satu.
  - 🏙️ **Pencakar Langit & Kota**: Pemandangan menara ikonik dunia (Jakarta, Manhattan, Chicago River, Dubai Marina, Hong Kong).
  - 🌌 **Luar Angkasa & Kosmos**: Galaksi, nebula, dan bintang yang menakjubkan.
  - 🌊 **Lautan & Pesisir Pantai**: Keindahan pantai berpasir putih, ombak, dan terumbu karang.
  - 🔲 **Minimalis Gelap (OLED)**: Latar gelap bertekstur elegan, sangat hemat energi dan ramah panel OLED.
* **🏷️ Info & Deskripsi Gambar**:
  - Nama lokasi, wilayah, dan sejarah tempat tersebut tampil otomatis di sisi kiri layar!
  - Bisa dimatikan lewat opsi **🏷️ Info & Deskripsi Gambar** bila Anda ingin tampilan murni tanpa teks.
* **📲 Unggah Gambar dari Ponsel (Pindai Kode QR)**:
  - Pilih **`📲 Upload Wallpaper via Phone (QR Code)`** pada menu wallpaper untuk menampilkan kode QR dan tautan web lokal.
  - Pindai dengan ponsel Anda (pada Wi-Fi yang sama) untuk mengunggah foto keluarga, hewan peliharaan, atau gambar favorit langsung ke TV tanpa perlu aplikasi pihak ketiga atau akun cloud.
* **📱 Pengelola Gambar TV & Mode 1 Gambar Tetap (Single Mode)**:
  - Setelah mengunggah gambar, Anda bisa mengelola gambar baik lewat ponsel maupun langsung di TV melalui menu **`📁 Kelola Gambar Unggahan`**.
  - **Kunci 1 Gambar Saja**: Pilih **`📌 Mode Satu`** jika Anda ingin menetapkan hanya 1 gambar tetap tanpa berputar.
  - **Putar Semua Gambar**: Pilih **`🔄 Putar Semua`** untuk memutar semua foto unggahan secara otomatis.
  - **Hapus Foto**: Hapus foto tertentu atau bersihkan semua gambar sekaligus.
* **🖼️ Tampilan Bersih (Clean View)**:
  - Saat memakai wallpaper sendiri (*custom*), rak teks deskripsi di kiri layar dimatikan sepenuhnya (`View.GONE`) supaya gambar Anda tampil jelas tanpa tertutup.
* **Ganti Gambar Seketika**: Pilih **⏭️ Ganti Gambar Sekarang** untuk beralih ke gambar berikutnya kapan saja.

### D. Screensaver StandBy & Auto-Off 2 Jam (Hemat Listrik)
* **10 Menit Dibiarkan Tanpa Tombol**: TV otomatis masuk mode *StandBy Screensaver*. Layar tidak lagi hitam polos: jam bisa tampil sebagai Digital Terpusat, Analog Minimalis (dengan cincin kemajuan hari), Editorial Kiri, atau Bento Widget, di atas wallpaper yang diburamkan (bawaan baru), cahaya aurora mengikuti jam, atau hitam OLED. Tanggal, tanggal Hijriah, kapsul sholat berikutnya, dan baris cuaca bisa dinyalakan atau dimatikan satu per satu.
* **Semua Diatur dari TV**: Buka **Settings (⚙️) $\rightarrow$ Mode StandBy** untuk memilih gaya, latar, elemen, format jam 12/24, ukuran digit, tingkat keredupan, mode malam merah, lama jeda sebelum screensaver muncul, lama jeda sebelum TV tidur, dan interval geser anti burn-in. Pilihan tersimpan di TV.
* **2 Jam Setelah Itu**: Jika TV terus tidak disentuh, sistem akan mematikan layar TV (*sleep standby*) secara otomatis.
* **Untuk Menyalakan Kembali**: Tekan tombol apa saja pada remote untuk membangunkan TV.

### E. Notifikasi Popup Adzan 16:9 & Pengaturan Audio Per Waktu Sholat 🕌
Fitur religius eksklusif untuk hunian Muslim pintar yang disinkronkan langsung dengan waktu sholat resmi Kemenag RI:
* **Mengambang di Atas Aplikasi Apa Pun**: Ketika masuk waktu sholat, kartu kaca 16:9 yang elegan muncul mengambang di layar secara otomatis meskipun Anda sedang menonton Netflix, YouTube, atau aplikasi TV lain tanpa mengganggu kestabilan sistem.
* **Pengaturan Lengkap (All-In-One Dialog)**: Buka melalui ikon **Candy Box (㗊)** di bar atas atau **Tekan & Tahan Tombol HOME**:
  1. **A. Saklar Utama Popup Adzan**: Nyalakan (*ON*) atau matikan (*OFF*) tampilan notifikasi mengambang.
  2. **B. Pengaturan Waktu Sholat Individual**: Kontrol notifikasi secara terpisah untuk tiap 6 waktu: *Subuh, Syuruq, Dzuhur, Ashar, Maghrib, Isya*. Setiap saklar bisa dinyalakan atau dimatikan sesuai kenyamanan keluarga Anda.
  3. **C. Pilihan Suara Adzan**: Pilih antara audio adzan penuh yang merdu: *Adzan Masjidil Haram Makkah, Adzan Masjid Nabawi Madinah*, nada chime lembut, atau mode hening.
  4. **D. Uji Popup Adzan Seketika**: Tekan tombol **▶️ Uji Popup Sekarang** untuk memicu pratinjau kartu mengambang dan mengecek volume audio dengan 1 klik.

### F. Menu Pengaturan Frosted Glass (Settings Dropdown Menu) ⚙️
Di bar atas layar utama, tekan tombol **Settings (ikon gear ⚙️)** untuk menampilkan menu mengambang gaya kaca Apple tvOS:
* 🖼️ **Wallpaper**: Membuka jendela pilihan kategori wallpaper (*Alam & Lanskap, Pencakar Langit, Luar Angkasa, Lautan, Minimalis Gelap, Ganti Gambar Sekarang*). Pilihan Anda tersimpan permanen meskipun TV dimatikan.
* 📍 **Location API Kabut Asap**: Membuka daftar puluhan stasiun kualitas udara resmi untuk mengganti stasiun pemantauan udara di seluruh Indonesia.
* 🕌 **Location Waktu Sholat**: Membuka daftar zona resmi Kemenag RI (WIB/WITA/WIT) untuk menyinkronkan jadwal sholat secara tepat sesuai kota Anda.
* 🔊 **Sound Effect**: Menampilkan tema suara aktif (contoh: `Suara: PlayStation 5`) dan membuka dialog pilihan tema audio konsol (PS5, Nintendo Switch, Xbox Series X/S, atau Bisu/Off) dengan pratinjau nada seketika.
* 🔄 **Check for Updates**: Memeriksa patch atau versi pembaruan baru langsung dari GitHub tanpa memerlukan komputer.
* ⚙️ **All Settings**: Membuka menu pengaturan penuh Android TV OS.
*(Jalan pintas: Menahan lama tombol Settings pada remote juga akan membuka pengaturan Android secara langsung).*

### G. Pilihan Suara Konsol & Saklar Bisu (Sound Effects) 🎮🔊
Kini Anda bisa memilih tema efek suara navigasi ala konsol game favorit atau mematikannya sepenuhnya:
* **PlayStation 5 (Bawaan)**: Suara pergerakan dan klik ikonik konsol PS5 generasi terbaru yang modern, lembut, dan rapi.
* **Nintendo Switch**: Suara klik mekanikal taktil dan 'snap' ceria yang sangat responsif setiap tombol ditekan.
* **Xbox Series X/S**: Suara 'blip' elektronik akustik lembut dengan nada dua-harmoni modern yang tenang.
* **Muted / Off (Bisu)**: Matikan semua efek suara navigasi jika Anda lebih suka suasana sunyi senyap di malam hari.
* **Pratinjau Suara Instan**: Setiap kali Anda mengklik pilihan di dalam dialog, sistem akan memainkan suara pilihan tersebut seketika sebagai contoh.
* **Tersimpan Permanen**: Pilihan Anda disimpan permanen di memori TV dan tidak hilang walau TV dimatikan atau dinyalakan ulang.

### H. Pemeriksaan & Auto-Update Langsung Dari TV (In-App OTA Updater) 🔄🚀
Glass TV Home dilengkapi mesin pembaruan pintar tanpa perlu komputer atau bantuan eksternal:
* **Pemeriksaan Manual**: Tekan **Settings (⚙️) $\rightarrow$ Check for Updates**. Sistem langsung terhubung ke server GitHub untuk memeriksa apakah ada versi terbaru.
* **Pemeriksaan Otomatis Latar Belakang**: Setiap kali aplikasi Glass TV dibuka, ia diam-diam memeriksa di latar belakang. Jika versi baru ditemukan, dialog pembaruan dengan daftar perubahan (*changelog*) akan ditampilkan.
* **Unduh & Pasang 1-Klik**: Klik tombol **Unduh & Pasang**, TV akan mengunduh file APK langsung ke perangkat dan membuka pemasang resmi Android TV untuk Anda konfirmasi dengan remote.
* **Aman & Tetap**: Proses pembaruan tidak menghapus file data, susunan aplikasi, atau pengaturan pribadi Anda.

---

## 📱 5. Cara Pakai Ponsel Pintar (Tanpa Komputer / Laptop Sama Sekali!)

**Sangat bisa!** Jika Anda tidak punya komputer atau laptop, Anda bisa memakai ponsel Android untuk memasang Glass TV ke Android TV secara otomatis tanpa kabel.

---

### Cara 1: Aplikasi Resmi 'Glass TV Installer' (Paling Mudah & 100% Otomatis) 🌟

Kini Anda tidak perlu aplikasi pihak ketiga yang rumit! Kami telah membangun aplikasi resmi **Glass TV Installer** khusus untuk ponsel dan tablet Android:

* **Unduh APK Pembantu**: [**`glass-tv-installer.apk`**](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk) (~6.6 MB)

#### Langkah Pemasangan:
1. **Persiapan TV**:
   - Di Android TV: Buka **Settings ⚙️** $\rightarrow$ **About** $\rightarrow$ Tekan **Android TV OS Build** sebanyak **7 kali** hingga bergelar developer.
   - Buka **Developer options** $\rightarrow$ Nyalakan **USB debugging**.
2. **Hubungkan Wi-Fi**: Pastikan ponsel dan TV terhubung ke jaringan Wi-Fi yang sama.
3. **Buka Aplikasi di Ponsel**:
   - Tekan **`🔍 Pindai Android TV di Jaringan Wi-Fi`** — aplikasi akan mencari TV Anda secara otomatis!
   - Atau masukkan IP TV Anda secara manual jika pemindaian otomatis diblokir router.
4. **Pasang 1-Klik**:
   - Tekan **`🚀 Pasang ke TV Sekarang`**.
   - Pada layar TV, tekan **Allow** pada popup *"Allow USB debugging"*.
   - Aplikasi akan mengirim dan memasang Glass TV Home langsung ke TV!
5. **Jalankan**:
   - Tekan **`▶️ Buka di TV`** untuk menikmati tampilan baru seketika.

> 💡 **Opsi Server Web Lokal**: Jika TV Anda tidak punya mode debugging, aktifkan **`🌐 Server Web TV`** di aplikasi ponsel, lalu buka aplikasi **Downloader** di TV dan masukkan URL yang ditampilkan untuk mengunduh langsung.

---

### Cara 2: Pakai Aplikasi 'Bugjaeger' (Untuk Pengguna Lanjut / Hapus Iklan Manual)
Aplikasi **Bugjaeger Mobile ADB** (gratis di Google Play Store ponsel) membuat ponsel Anda berperan seperti terminal komputer:

1. **Hubungkan Wi-Fi**: Pastikan ponsel dan TV berada di Wi-Fi yang sama.
2. **Pasang Bugjaeger**: Unduh aplikasi **Bugjaeger** di ponsel Anda dari Play Store.
3. **Hubungkan ke TV**:
   - Buka Bugjaeger, tekan ikon steker (**Connect 🔌**).
   - Masukkan Alamat IP TV Anda (contoh: `192.168.68.102`) dan port `5555`.
   - Tekan **Connect**.
   - Pada layar TV, tekan **Allow** saat muncul jendela *"Allow USB debugging"*.
4. **Pasang Glass TV Langsung dari Ponsel**:
   - Masuk tab **Packages (ikon kotak 📦)** di Bugjaeger.
   - Tekan tombol tambah (**+**) di atas, pilih file `app-release.apk` yang sudah diunduh ke ponsel Anda.
   - Bugjaeger akan memasang Glass TV langsung ke TV dalam beberapa detik!
5. **Hapus Iklan & Blokir Tracking dari Ponsel**:
   - Masuk tab **Commands (ikon terminal ⚡)** di Bugjaeger.
   - Salin dan tempel perintah ini untuk memblokir iklan sistem:
     ```bash
     settings put global private_dns_specifier dns.adguard-dns.com
     settings put global private_dns_mode hostname
     ```
   - Masukkan perintah untuk mematikan iklan pabrikan (contoh Xiaomi PatchWall):
     ```bash
     pm disable-user --user 0 com.mitv.tvhome
     pm disable-user --user 0 com.xiaomi.mitv.advertise
     ```
   - Selesai! TV Anda kini bebas iklan dan sangat ngebut.

---

### Cara 3: Paling Sederhana (Hanya Pasang APK Pakai 'Send Files to TV')
Jika Anda hanya ingin memasang Glass TV Home tanpa debloat teknis:
1. Pasang aplikasi gratis **Send Files to TV** di ponsel dan di Android TV Anda (dari Google Play Store).
2. Unduh `app-release.apk` ke ponsel Anda (dari tautan file GitHub).
3. Buka aplikasi di kedua perangkat, kirim APK dari ponsel ke TV lewat Wi-Fi.
4. Di TV Anda, buka aplikasi pengelola file (seperti *File Commander* atau *FX File Explorer*), klik file APK dan pilih **Install**.
5. Tekan tombol **Home** pada remote TV dan pilih **Glass TV Home** sebagai *Always / Selalu*.

---

### 🤖 Bisakah Tanya AI dari Ponsel?
Bisa! Anda bisa membuka aplikasi AI di ponsel (seperti Claude, ChatGPT, atau browser web):
1. Beritahu AI merek TV Anda:
   > *"Saya pakai ponsel dan app Bugjaeger. TV saya model Xiaomi Mi Box S. Berikan saya daftar perintah debloat untuk mematikan iklan dan tracking."*
2. AI akan memberikan daftar perintah `pm disable-user`.
3. Anda hanya perlu menyalin dan menempel perintah tersebut ke aplikasi **Bugjaeger** di ponsel Anda!

---

### 🍏 Khusus untuk Pengguna iPhone (iOS): Pakai iPhone & Bantuan Muse.ai

**Bagi pengguna iPhone (iOS): Anda bisa memasang APK dan membersihkan TV dengan bantuan asisten AI pintar Muse.ai!**

Karena kebijakan Apple (iOS) tidak mengizinkan aplikasi kontrol ADB langsung di App Store, cara terbaik dan paling cerdas untuk pengguna iPhone adalah memakai browser Safari dan kunjungi [**muse.ai**](https://muse.ai) (atau platform web AI) untuk memandu proses pemasangan dan debloat:

#### 1. Tanya Muse.ai di Safari iPhone Anda:
Buka Safari di iPhone, pergi ke **muse.ai** dan berikan pertanyaan ini:
> *"Saya pakai iPhone dan punya Android TV [merek TV Anda, mis.: Xiaomi / TCL / Sony]. Berikan saya langkah paling mudah untuk pasang APK dari https://github.com/nstv-official/nstv-glass-tv-home-ota dan sebutkan aplikasi bawaan pabrik yang tidak berguna (bloatware) untuk dimatikan di TV saya."*

Muse.ai akan memberikan panduan langkah demi langkah yang disesuaikan khusus untuk model TV Anda!

#### 2. Cara Kirim & Pasang APK dari iPhone ke TV:
* **Pakai Downloader (Paling Cepat di TV)**: Buka aplikasi **Downloader by AFTVnews** di TV dan masukkan tautan file APK:  
  `https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk`  
  TV akan mengunduh dan memasang Glass TV Home langsung tanpa perlu kabel atau perangkat lain.
* **Pakai LocalSend (Kirim dari iPhone)**: Unduh aplikasi gratis **LocalSend** di iPhone (App Store) dan di Android TV (Play Store). Unduh file `glass-tv-home.apk` di iPhone, lalu kirim file tersebut ke TV lewat LocalSend pada Wi-Fi yang sama dan tekan **Install**.

#### 3. Hapus Aplikasi Bawaan Tidak Berguna (Debloat) & Blokir Iklan:
* **Nonaktifkan Aplikasi Sesuai Daftar Muse.ai**: Masuk ke **Settings ⚙️** $\rightarrow$ **Apps** $\rightarrow$ **See all apps**, cari aplikasi bawaan pabrik yang tidak berguna seperti yang diidentifikasi Muse.ai, dan tekan **Disable / Nonaktifkan**.
* **Blokir Iklan & Pelacak Sistem (AdGuard Private DNS)**:
  1. Pada remote TV, buka **Settings ⚙️** $\rightarrow$ **Network & Internet**.
  2. Pilih **Private DNS** (ubah dari *Off/Automatic* ke *Private DNS provider hostname*).
  3. Masukkan alamat ini memakai keyboard di TV:
     ```text
     dns.adguard-dns.com
     ```
  4. Tekan **Save / Simpan**.
  5. Seketika semua iklan buatan layar utama dan pelacak pabrikan diblokir di level sistem TV Anda secara permanen!

#### 4. Ingin Hapus Bloatware Pabrikan Secara Total?
Jika Anda ingin menghapus aplikasi bawaan pabrikan (seperti Xiaomi PatchWall) sampai 100% bersih, Anda disarankan menggunakan **laptop/komputer** (atau meminjam ponsel Android teman sekali saja selama 2 menit). Setelah dihapus sekali, TV Anda akan kekal bersih selamanya!

---

## 🛠️ 6. Cara Pasang Manual Pakai Flashdisk (Alternatif)

Jika Anda lebih suka memakai flashdisk USB:
1. Unduh file [**glass-tv-home.apk**](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk) (tersedia di folder [`release/`](release/glass-tv-home.apk)).
2. Salin file `glass-tv-home.apk` tersebut ke flashdisk USB.
3. Colokkan flashdisk ke Android TV atau Xiaomi Mi Box Anda.
4. Buka aplikasi pengelola file di TV (contoh: *File Commander* atau *FX File Explorer* dari Google Play Store TV).
5. Klik `glass-tv-home.apk` dan tekan **Install**.
   *(Jika TV meminta izin "Install unknown apps", pilih **Allow/Izinkan**)*.
6. Setelah selesai dipasang, tekan tombol **Home** pada remote TV dan pilih **Glass TV Home** sebagai *Always / Selalu*.

---

## ❓ 7. Pertanyaan Umum (FAQ) Seputar AI, Ponsel & Remote TV

### T1: Bisakah menjalankan Claude Code di ponsel (iOS / Android) dan apakah masalah iOS Sandbox menghambat akses jaringan?
* **Realita Claude Code di Ponsel:** Claude Code adalah perangkat Terminal/CLI (`@anthropic-ai/claude-code`) yang membutuhkan mesin **Node.js 18+**. Di iPhone (iOS), sistem keamanan Sandbox Apple tidak mengizinkan eksekusi mesin Node.js atau shell secara langsung di dalam ponsel.
* **Masalah Akses Jaringan (Cloud vs Local Network):** Claude Code (atau AI cloud mana pun seperti Claude.ai / ChatGPT) berjalan di server cloud. Server cloud di internet **TIDAK BISA menembus Wi-Fi rumah Anda** untuk mencapai IP privat TV (`192.168.x.x`) karena diblokir oleh *firewall router* rumah Anda.
* **Solusi:** Perintah ADB harus dijalankan dari perangkat yang berada di dalam jaringan Wi-Fi rumah yang sama:
  - **Paling mudah:** Pakai laptop / komputer (Google Antigravity gratis atau Claude Code di terminal laptop).
  - **Pakai ponsel:** Pengguna Android bisa memakai aplikasi lokal seperti **Bugjaeger Mobile ADB**.

---

### T2: Adakah AI yang bisa memasang dan mengerjakan semua tugas langsung tanpa pengguna perlu pasang APK sendiri?
* **YA, jika menggunakan Komputer / Laptop (Google Antigravity / Claude Code / Cursor):**
  AI pada komputer bisa terhubung langsung ke TV melalui Wi-Fi rumah (`adb connect 192.168.x.x`). AI akan membangun APK $\rightarrow$ mengirim ke TV $\rightarrow$ memasang APK $\rightarrow$ menghapus iklan pabrikan $\rightarrow$ dan menguji layar secara otomatis 100%. Anda tidak perlu mengunduh atau sentuh file APK sama sekali!
* **Jika hanya memakai Ponsel:** Anda butuh bantuan aplikasi perantara lokal (Android pakai *Bugjaeger*, iPhone kirim file lewat *LocalSend*).

---

### T3: Jika pengguna hanya memasang file APK, bisakah akses penuh memakai remote TV atau tetap butuh akses AI juga?
* **100% BISA PAKAI REMOTE SAJA, TIDAK PERLU AI LAGI!**
* Glass TV Home dibangun sebagai aplikasi Android TV asli (*Native Android TV app*).
* Begitu file APK terpasang di TV:
  - **Remote TV berfungsi penuh**: Tombol D-Pad (Atas/Bawah/Kiri/Kanan/OK) untuk mengontrol aplikasi dan cuaca, serta tombol Home/Back berjalan seperti biasa.
  - **Semua fitur otomatis berjalan sendiri**: Jam, jadwal waktu sholat, cuaca & kualitas udara (ISPU), mode screensaver 10 menit, dan auto-sleep 2 jam diproduksi sepenuhnya oleh chip prosesor TV tanpa bergantung pada AI atau internet luar mana pun.
  - Anggota keluarga dan pengguna biasa bisa menikmati TV dengan lancar hanya memakai remote TV biasa.
* **Peran AI hanyalah seperti "insinyur"** yang membantu memasang dan membersihkan bloatware di tahap awal saja.

---

### T4: Saya pakai Android TV Box dari ISP (mis. IndiHome / Biznet Home), bisakah memakai launcher ini dan apakah garansinya hangus?
* **1. Bisakah dipasang pada Box IPTV ISP?**
  - **YA, SANGAT BISA!** Box Android TV dari ISP Indonesia (seperti perangkat Google TV/Android TV yang dipinjamkan IndiHome atau Biznet Home) beroperasi di atas sistem Android TV resmi.
  - Anda bisa memasang file APK Glass TV Home baik lewat flashdisk USB, aplikasi *Downloader / Send Files to TV*, maupun perintah Wi-Fi ADB.
  - **Aplikasi TV dari ISP tetap berfungsi penuh:** Anda tidak akan kehilangan siaran TV langsung (*Live TV*), olahraga, atau acara favorit karena aplikasi resmi ISP akan tetap muncul di daftar aplikasi Glass TV dan bisa diletakkan pada dock depan (*favorites*).
* **2. Apakah garansi ISP menjadi hangus?**
  - **TIDAK, GARANSI ANDA 100% TETAK AMAN.**
  - **Tanpa Root (Zero Root / No Modding):** Glass TV Home hanyalah aplikasi *launcher* biasa (sama seperti memasang Netflix, YouTube, atau Spotify dari Play Store). Ia tidak mengubah file sistem induk (*firmware / vendor partition*).
  - **Bisa Dibersihkan Kapan Saja (Reversible):** Jika Anda harus mengembalikan box ke ISP atau mengklaim garansi ketika rusak, Anda cukup membuka:
    `Settings (Pengaturan) -> Apps (Aplikasi) -> Glass TV Home -> Uninstall (Copot Pemasangan)`
    atau melakukan *Factory Reset*. Box akan kembali 100% ke kondisi asal pabrik tanpa meninggalkan jejak modifikasi apa pun.

---

## 📄 Lisensi & Apresiasi
* Dibangun berdasarkan [CleanYourTV](https://github.com/Gathin23/cleanyourtv) © Gathin (Lisensi MIT).
* Font Outfit & Inter oleh SIL Open Font License.
* Data cuaca oleh Open-Meteo & data kualitas udara mengacu standar ISPU Kementerian Lingkungan Hidup dan Kehutanan (KLHK) Indonesia.
