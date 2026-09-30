import Foundation

enum MockCatalog {
    static let categories = [
        Category(id: 1, name: "قابلمه و تابه", slug: "pots-pans"),
        Category(id: 2, name: "سرو و پذیرایی", slug: "serve"),
        Category(id: 3, name: "سبد و نظم‌دهنده", slug: "organizers"),
        Category(id: 4, name: "ابزار آشپزخانه", slug: "kitchen-tools")
    ]

    static let products = [
        Product(id: 101, name: "قابلمه گرانیتی سایز ۲۴", price: "890000", regularPrice: "1050000", salePrice: "890000", shortDescription: "قابلمه گرانیتی با پوشش نچسب و توزیع یکنواخت حرارت."),
        Product(id: 102, name: "ست تابه گرانیتی ۲ عددی", price: "750000"),
        Product(id: 103, name: "ست ظروف نگهدارنده ۳ عددی", price: "620000"),
        Product(id: 104, name: "ست کفگیر و ملاقه ۶ پارچه", price: "390000"),
        Product(id: 105, name: "کتری استیل ۳ لیتری", price: "680000"),
        Product(id: 106, name: "آبچکان رومیزی دو طبقه", price: "990000")
    ]

    static let home = HomePayload(categories: categories, latestProducts: products, featuredProducts: Array(products.prefix(3)))
}
