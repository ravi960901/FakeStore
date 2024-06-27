package com.image.fakestore.ui.model

import com.google.gson.annotations.SerializedName


data class FakeStoreData(
    @SerializedName("category")
    val category: String?="",
    @SerializedName("description")
    val description: String?="",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: String?="",
    @SerializedName("price")
    val price: Double? = 0.0,
    @SerializedName("rating")
    val rating: Rating?=null,
    @SerializedName("title")
    val title: String?=""
) {
    data class Rating(
        @SerializedName("count")
        val count: Int?=0,
        @SerializedName("rate")
        val rate: Double?=0.0
    )
}