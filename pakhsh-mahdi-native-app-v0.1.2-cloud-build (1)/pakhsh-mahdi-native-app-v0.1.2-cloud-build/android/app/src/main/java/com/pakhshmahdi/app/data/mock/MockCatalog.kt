package com.pakhshmahdi.app.data.mock

import com.pakhshmahdi.app.data.model.Category
import com.pakhshmahdi.app.data.model.HomePayload
import com.pakhshmahdi.app.data.model.Product

object MockCatalog {
    val categories = listOf(
        Category(1, "قابلمه و تابه", "pots-pans"),
        Category(2, "سرو و پذیرایی", "serve"),
        Category(3, "سبد و نظم‌دهنده", "organizers"),
        Category(4, "ابزار آشپزخانه", "kitchen-tools")
    )

    val products = listOf(
        Product(101, "قابلمه گرانیتی سایز ۲۴", price = "890000", regularPrice = "1050000", salePrice = "890000", shortDescription = "قابلمه گرانیتی با پوشش نچسب و توزیع یکنواخت حرارت."),
        Product(102, "ست تابه گرانیتی ۲ عددی", price = "750000", shortDescription = "مناسب پخت روزمره و استفاده خانگی."),
        Product(103, "ست ظروف نگهدارنده ۳ عددی", price = "620000", shortDescription = "درب محکم و طراحی مناسب نظم‌دهی آشپزخانه."),
        Product(104, "ست کفگیر و ملاقه ۶ پارچه", price = "390000", shortDescription = "ابزار کاربردی آشپزخانه با طراحی مینیمال."),
        Product(105, "کتری استیل ۳ لیتری", price = "680000", shortDescription = "استیل مقاوم و مناسب استفاده روزانه."),
        Product(106, "آبچکان رومیزی دو طبقه", price = "990000", shortDescription = "طراحی جمع‌وجور برای آشپزخانه‌های مدرن.")
    )

    val home = HomePayload(categories, products.take(5), products.take(3))
}
