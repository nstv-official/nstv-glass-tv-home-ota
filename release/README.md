# Glass TV Home APK Release 📦

File rilis resmi (*Official Release APK*) untuk **Glass TV Home** dan aplikasi pembantu ponsel **Glass TV Installer**.

---

## ⬇️ Unduh File APK (Official Direct Downloads)

### 1. 📺 Glass TV Home (Untuk Android TV & Google TV)
Peluncur layar utama (*home screen launcher*) ultra-ringan dengan desain *frosted glass* Apple TV, cuaca dinamik KLHK ISPU Indonesia, waktu sholat otomatis, RAM cleaner 2-klik, dan bebas iklan.

* **File APK**: [`glass-tv-home.apk`](glass-tv-home.apk)
* **Versi Saat Ini**: `v3.1.4` (☕ Kartu Traktir Kopi DuitNow QR `@imsomangge`, label tombol Candy Box OTA terbaru, dukungan tampilan logo resmi GXBank)
* **Tautan Unduh Langsung (Direct Link)**:  
  [https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk)
* **Ukuran File**: ~730 KB (Ultra-ringan, unduh dalam 1 detik & hemat memori TV)
* **Kompatibilitas**: Android TV & Google TV (Android 8.0 Oreo ke atas, API 26+)

---

### 2. 📱 Glass TV Installer (Untuk Ponsel & Tablet Android)
Aplikasi pembantu ponsel untuk memasang Glass TV Home langsung ke TV rumah Anda secara tanpa kabel (wireless Wi-Fi) **tanpa perlu komputer (PC), kabel USB, atau terminal**.

* **File APK**: [`glass-tv-installer.apk`](glass-tv-installer.apk)
* **Versi Saat Ini**: `v1.0.0`
* **Tautan Unduh Langsung (Direct Link)**:  
  [https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk)
* **Ukuran File**: ~5.2 MB (Sudah termasuk paket APK Glass TV di dalam aplikasi)
* **Kompatibilitas**: Android 8.0 ke atas (Ponsel & tablet Android)

---

## 📱 Panduan Pemasangan Menurut Prioritas Perangkat

Ikuti urutan metode berikut sesuai perangkat yang Anda miliki:

---

### ⭐ Prioritas 1: Pengguna Ponsel Android (Fokus Utama — Paling Mudah & Direkomendasikan)

Tidak perlu komputer atau flashdisk USB! Pasang APK pembantu langsung di perangkat Android Anda:

#### ⚙️ Persiapan Awal di TV (Cukup Sekali)
1. Pada remote TV Anda, buka **Pengaturan (Settings ⚙️)** $\rightarrow$ **System / Device Preferences** $\rightarrow$ **About**.
2. Tekan **Android TV OS Build** sebanyak **7 kali berturut-turut** hingga muncul notifikasi *"You are now a developer!"*.
3. Tekan kembali (Back), buka menu baru **Developer options**, dan aktifkan:
   - ✅ **USB debugging** $\rightarrow$ Nyalakan (*Enable*).
   - *(Jika ada pilihan Network Debugging / Wireless Debugging, nyalakan juga)*.
4. Hubungkan ponsel Android dan Android TV Anda ke **jaringan Wi-Fi yang sama**.

#### 🚀 Cara 1: Pasang Otomatis 1-Klik (Wireless ADB — Paling Direkomendasikan)
1. **Pasang & Buka Aplikasi**:  
   Unduh dan pasang [`glass-tv-installer.apk`](https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-installer.apk) di ponsel Android Anda.
2. **Deteksi TV Anda**:  
   Tekan tombol **`🔍 Pindai Android TV di Jaringan Wi-Fi`**. Aplikasi akan mencari Android TV Anda secara otomatis dan mengisi alamat IP (misalnya `192.168.68.110`).  
   *(Jika pemindaian otomatis tidak menemukan TV, Anda bisa mengetik alamat IP TV secara manual dari Pengaturan Wi-Fi TV Anda).*
3. **Pasang ke TV**:  
   Tekan tombol biru **`🚀 Pasang ke TV Sekarang`**.
4. **Konfirmasi di Layar TV**:  
   Jika pertama kali menghubungkan, layar TV Anda akan menampilkan notifikasi *"Allow USB debugging?"*. Centang kotak **Always allow from this computer** dan tekan **OK / Allow** menggunakan remote TV Anda.
5. **Selesai & Jalankan**:  
   Progress bar pemasangan akan berjalan hingga 100%. Setelah selesai, tekan tombol **`▶️ Buka di TV`**!

#### 🌐 Cara 2: Pakai Server Web Lokal (Jika TV Tanpa Debugging)
1. Di dalam aplikasi **Glass TV Installer** di ponsel, tekan tombol **`🌐 Server Web TV`**.
2. Ponsel akan menampilkan alamat URL lokal, misalnya: `http://192.168.68.105:8080/`
3. Di TV Anda, buka aplikasi **Downloader** atau browser web TV (*Chrome/Browser*), ketik alamat URL tersebut dan tekan **Go**.
4. File APK Glass TV akan langsung terunduh ke TV Anda untuk dipasang!

---

### 💻 Prioritas 2: Jika Tidak Ada Ponsel Android — Pakai Laptop atau PC (Windows / Mac / Linux)

Jika Anda tidak punya ponsel Android, gunakan laptop atau komputer Anda dengan bantuan AI atau terminal:

1. **Bantuan AI Otomatis 1-Baris Prompt**:
   Buka **Google Antigravity** [Gratis / Direkomendasikan], **Claude Code**, atau **Cursor** di komputer Anda. Tempel satu baris ini saja:
   ```text
   install ini ke tv rumah saya: https://github.com/nstv-official/nstv-glass-tv-home-ota
   ```
   AI akan terhubung ke TV via Wi-Fi (`adb connect <ip>:5555`), menonaktifkan bloatware/iklan yang memperlambat TV, dan memasang Glass TV Home secara otomatis!
2. **Terminal ADB Manual**:
   ```bash
   adb connect <ALAMAT_IP_TV>:5555
   adb install -r release/glass-tv-home.apk
   adb shell am start -n com.gathin.tvhome/.MainActivity
   ```

---

### 🍏 Prioritas 3: Jika Tidak Ada Android & Tidak Ada Komputer (Hanya iPhone) — Pakai iPhone & Bantuan Muse.ai

Jika Anda hanya punya iPhone/iPad:

1. **Bantuan AI Melalui Muse.ai**:
   Buka Safari di iPhone Anda dan kunjungi [**muse.ai**](https://muse.ai). Ajukan pertanyaan ini:
   > *"Saya pakai iPhone dan punya Android TV [merek TV Anda, mis. Xiaomi Mi Box / TCL / Sony]. Berikan panduan mudah pasang APK dari https://github.com/nstv-official/nstv-glass-tv-home-ota ke TV saya, dan sebutkan aplikasi bawaan pabrik yang tidak berguna (bloatware) untuk dimatikan di TV saya."*
2. **Pasang APK ke TV**:
   - Di TV, buka aplikasi **Downloader by AFTVnews** dan ketik tautan langsung:  
     `https://github.com/nstv-official/nstv-glass-tv-home-ota/raw/main/release/glass-tv-home.apk`  
     TV akan mengunduh dan memasang Glass TV Home langsung.
   - Atau gunakan aplikasi gratis **LocalSend** di iPhone & TV untuk mengirim APK lewat Wi-Fi.
3. **Nonaktifkan Aplikasi Tidak Berguna & Iklan**:
   - Di TV: **Settings ⚙️** $\rightarrow$ **Apps** $\rightarrow$ **See all apps**, nonaktifkan (*Disable*) aplikasi bawaan tidak berguna seperti yang diidentifikasi oleh Muse.ai.
   - Di TV: **Settings ⚙️** $\rightarrow$ **Network & Internet** $\rightarrow$ **Private DNS** $\rightarrow$ masukkan `dns.adguard-dns.com` untuk memblokir semua iklan secara sistematis.

---

### 🛠️ Opsi Lain: Pasang Manual Pakai Flashdisk (USB)
1. Salin `glass-tv-home.apk` ke flashdisk USB.
2. Colokkan ke Android TV Anda.
3. Buka pengelola file di TV (*File Commander* atau *FX File Explorer*), klik file APK dan tekan **Install**.
4. Tekan tombol **Home** pada remote TV dan pilih **Glass TV Home** sebagai *Always / Selalu*.

---

## 🔄 Cara Memperbarui Patch Terbaru (OTA Update)

Di layar utama TV:
1. Arahkan remote ke tombol **Settings (ikon gear ⚙️)**.
2. Pilih **`🔄 Check for Updates`**.
3. TV akan memeriksa versi terbaru dari server secara otomatis. Jika ada versi baru, tekan tombol **Unduh & Pasang** untuk memperbarui tanpa kehilangan pengaturan atau susunan aplikasi Anda!
