# VCamera
**This is an open source for android virtual camera.**  
*Just for protect your camera privacy.*


# demo video
[![Watch the video](https://img.youtube.com/vi/lT-MP9c7SbY/maxresdefault.jpg)](https://www.youtube.com/embed/lT-MP9c7SbY)


# Key Features
**1. Support replacing the camera with a photo.**
**2. Support replacing the camera with video.**
**3. Support to replace the camera with network video.**
**4. Support to replace the camera with network video.**
**4. Support resizing video, rotating, moving, zooming, flipping, etc.**


# Strategi Kompatibilitas dan Stabilitas (Android 15+)

1. **Prioritaskan jalur non-root (jika memungkinkan)**
   - Eksplorasi implementasi *Virtual Camera* melalui API resmi seperti **MediaProjection** untuk menangkap permukaan rendering atau memanfaatkan API Virtual Device/Display resmi Android.
   - Pendekatan ini memang tidak dapat mencegat seluruh aplikasi kamera, namun jauh lebih stabil, aman, serta kompatibel dengan kebijakan Google Play dibandingkan teknik hooking yang invasif.

2. **Riset MediaProjection dan opsi resmi lainnya**
   - Uji kombinasi MediaProjection dengan pipeline virtual display agar tetap dapat menyediakan feed kamera sintetis untuk aplikasi pihak ketiga.
   - Catat keterbatasan aplikasi yang tidak mengizinkan input dari sumber virtual lalu sediakan fallback atau panduan kompatibilitas bagi pengguna.

3. **Migrasi ke CameraX Extension untuk injeksi efek**
   - Jika target utama adalah aplikasi yang telah memakai CameraX, kembangkan **CameraX Extension** kustom sehingga efek dapat disisipkan tanpa perlu memodifikasi sistem.
   - Pertahankan isolasi kode agar ekstensi tidak mempengaruhi aplikasi lain dan tetap memenuhi standar keamanan Play Store.

4. **Optimasi kode native (wajib)**
   - Verifikasi ulang seluruh library **NDK/C++** dan lakukan kompilasi dengan dukungan ukuran halaman **16 KB** yang menjadi standar baru Android 15.
   - Kelalaian terhadap dukungan 16 KB page size merupakan risiko kompatibilitas terbesar; tambahkan otomatisasi build untuk mencegah regresi di masa depan.

5. **Manajemen performa video berbasis GPU**
   - Pindahkan pemrosesan frame utama (resizing, cropping, compositing) ke GPU dengan memanfaatkan **Vulkan** atau **OpenGL ES** guna mengurangi beban CPU dan meningkatkan efisiensi daya.
   - Profilkan pipeline video secara berkala untuk memastikan latensi tetap rendah ketika menangani resolusi tinggi.

6. **Gunakan mediaProcessing Foreground Service (FGS)**
   - Untuk proses streaming di latar belakang, gunakan tipe FGS `mediaProcessing` agar alokasi sumber daya tetap optimal dan mematuhi regulasi Android 15.
   - Pastikan dokumentasi aplikasi menjelaskan alasan penggunaan FGS demi transparansi ke pengguna.


# Contact Me
andvipgroup@gmail.com

# Building the APK

The project can be packaged into a debug APK with the included Gradle
wrapper. The helper script below pins the Java toolchain to JDK 17 so
that the legacy Android Gradle Plugin used by this project runs
successfully.

1. Ensure you have internet connectivity the first time you build so
   Gradle can download the Android Gradle Plugin and Kotlin compiler
   dependencies.
2. From the repository root execute:

   ```bash
   ./build_apk.sh
   ```

3. After a successful build the APK is generated at
   `app/build/outputs/apk/debug/app-debug.apk`.

> **Note:** The build will fail in fully offline environments because
> the required Gradle plugins cannot be downloaded.

# APK Download
**Latest version : 3.0.0**

[Github](https://github.com/andvipgroup/VCamera/releases)

[Google Play](https://play.google.com/store/apps/details?id=virtual.camera.app)

"# camera" 
"# camera" 
