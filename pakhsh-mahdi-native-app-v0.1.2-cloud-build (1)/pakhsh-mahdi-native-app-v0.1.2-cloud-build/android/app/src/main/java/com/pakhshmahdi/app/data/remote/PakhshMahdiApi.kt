package com.pakhshmahdi.app.data.remote

import com.pakhshmahdi.app.data.model.HomePayload
import com.pakhshmahdi.app.data.model.Product
import com.pakhshmahdi.app.data.model.ProductsPayload
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PakhshMahdiApi {
    @GET("home")
    suspend fun home(): HomePayload

    @GET("products")
    suspend fun products(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("category") category: Long? = null,
        @Query("search") search: String? = null
    ): ProductsPayload

    @GET("products/{id}")
    suspend fun product(@Path("id") id: Long): Product
}
