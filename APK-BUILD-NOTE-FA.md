# ساخت فایل APK اندروید

برای تست روی گوشی اندرویدی، پوشه `android` را در ویندوز باز کنید و فایل `BUILD-APK-WINDOWS.bat` را اجرا کنید.

پیش‌نیاز: Android Studio و Android SDK Platform 35.

اسکریپت Gradle 8.9 را در صورت نیاز دانلود می‌کند و فایل زیر را می‌سازد:

`android/PakhshMahdi-v0.1.1-debug.apk`

این فایل Debug برای نصب مستقیم و تست روی گوشی است. برای انتشار در Google Play باید نسخه Release با keystore اختصاصی و خروجی AAB ساخته شود.
