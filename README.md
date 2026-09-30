# Pakhsh Mahdi Native Commerce — v0.1.0

شروع پروژه‌ی واقعی اپلیکیشن Native فروشگاهی **پخش مهدی** برای Android و iOS.

## خروجی این نسخه

- Android Native: Kotlin + Jetpack Compose + Material 3 + MVVM
- iOS Native: Swift + SwiftUI + NavigationStack
- WordPress/WooCommerce bridge plugin: REST API بدون قراردادن Consumer Key ووکامرس داخل موبایل
- Design System مطابق پالت تاییدشده:
  - `#01082B` — Primary Navy
  - `#07012B` — Secondary Navy/Violet
  - `#FFFFFF` — Surface
  - `#D2D2D4` — Border / Muted
  - `#F2F2F2` — Background
- RTL و متن فارسی
- Home، Catalog، Product Detail، Cart محلی و Navigation پایه
- اتصال واقعی Catalog به WooCommerce از طریق افزونه‌ی اختصاصی، با fallback داخلی برای زمان توسعه

## ساختار

```text
android/            پروژه Android Studio
ios/                پروژه Xcode/SwiftUI
wordpress-plugin/   افزونه REST Bridge برای WooCommerce
```

## راه‌اندازی Backend

1. فایل `wordpress-plugin/pakhsh-mahdi-app-core.zip` را در وردپرس نصب و فعال کنید.
2. مطمئن شوید WooCommerce فعال است.
3. آدرس API پایه:

```text
https://pakhshemahdi.com/wp-json/pm-app/v1/
```

4. در Android فایل `AppConfig.kt` و در iOS فایل `AppConfig.swift` دامنه را در صورت نیاز تغییر دهید.

## Android

پروژه‌ی `android` را در Android Studio باز کنید و Sync/Run بزنید. حداقل Android 8 (API 26) در نظر گرفته شده است.

## iOS

پروژه‌ی `ios/PakhshMahdi.xcodeproj` را با Xcode باز کنید و روی iOS 17+ اجرا کنید.

## محدوده نسخه 0.1.0

این نسخه **فونداسیون Production** است و برای توسعه‌ی مرحله‌ای طراحی شده، نه یک تصویر یا prototype. Catalog به WooCommerce وصل می‌شود؛ Cart در این نسخه داخل دستگاه نگهداری می‌شود. احراز هویت OTP، سبد سروری WooCommerce، Checkout/Payment، Push Notification، حساب کاربری و تاریخچه سفارش در نسخه‌های بعدی روی همین معماری اضافه می‌شوند.
