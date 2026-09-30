# ساخت APK بدون Android Studio

برای این پروژه نصب Android Studio لازم نیست. APK می‌تواند به‌صورت آنلاین با GitHub Actions ساخته شود.

## مراحل

1. وارد github.com شوید و یک Repository جدید بسازید.
2. محتوای این پوشه را داخل Repository آپلود کنید. پوشه `.github` نیز باید آپلود شود.
3. در GitHub وارد تب **Actions** شوید.
4. Workflow با نام **Build Android APK** را باز کنید.
5. روی **Run workflow** بزنید.
6. بعد از پایان Build، همان صفحه را باز کنید.
7. در بخش **Artifacts** روی **PakhshMahdi-Android-APK** بزنید.
8. فایل ZIP دانلودشده را Extract کنید.
9. فایل `PakhshMahdi-v0.1.2-debug.apk` را به گوشی منتقل و نصب کنید.

## نکته

این خروجی Debug برای نصب و تست مستقیم روی گوشی مناسب است. برای انتشار در مارکت‌ها باید نسخه Release با کلید امضای اختصاصی ساخته شود.

## ساخت خودکار

هر بار که فایل‌های پوشه `android` روی شاخه `main` یا `master` تغییر کنند، GitHub Actions به‌صورت خودکار APK جدید می‌سازد.
