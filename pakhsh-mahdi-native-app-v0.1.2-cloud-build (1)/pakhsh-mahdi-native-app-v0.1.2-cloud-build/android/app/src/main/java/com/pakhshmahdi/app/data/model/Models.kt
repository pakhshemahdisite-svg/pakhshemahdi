package com.pakhshmahdi.app.data.model

data class Category(
    val id: Long,
    val name: String,
    val slug: String,
    val image: String? = null,
    val count: Int = 0
)

data class Product(
    val id: Long,
    val name: String,
    val slug: String = "",
    val price: String,
    val regularPrice: String = "",
    val salePrice: String = "",
    val image: String? = null,
    val gallery: List<String> = emptyList(),
    val shortDescription: String = "",
    val stockStatus: String = "instock",
    val averageRating: String = "0",
    val ratingCount: Int = 0,
    val categories: List<Category> = emptyList()
) {
    val isInStock: Boolean get() = stockStatus == "instock"
    val hasSale: Boolean get() = salePrice.isNotBlank() && salePrice != regularPrice
}

data class HomePayload(
    val categories: List<Category> = emptyList(),
    val latestProducts: List<Product> = emptyList(),
    val featuredProducts: List<Product> = emptyList()
)

data class ProductsPayload(
    val items: List<Product> = emptyList(),
    val page: Int = 1,
    val total: Int = 0,
    val totalPages: Int = 1
)
