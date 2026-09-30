package com.pakhshmahdi.app.data.repository

import com.pakhshmahdi.app.data.mock.MockCatalog
import com.pakhshmahdi.app.data.model.HomePayload
import com.pakhshmahdi.app.data.model.Product
import com.pakhshmahdi.app.data.remote.ApiClient

class CatalogRepository {
    suspend fun home(): HomePayload = runCatching { ApiClient.api.home() }.getOrElse { MockCatalog.home }

    suspend fun products(): List<Product> = runCatching { ApiClient.api.products().items }
        .getOrElse { MockCatalog.products }

    suspend fun product(id: Long): Product = runCatching { ApiClient.api.product(id) }
        .getOrElse { MockCatalog.products.firstOrNull { it.id == id } ?: MockCatalog.products.first() }
}
