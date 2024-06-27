package com.image.fakestore.apiService


import com.image.fakestore.ui.model.FakeStoreData
import retrofit2.http.GET

interface FakeStoreService {
    @GET("/products")
    suspend fun getStoreItems(): List<FakeStoreData>

}