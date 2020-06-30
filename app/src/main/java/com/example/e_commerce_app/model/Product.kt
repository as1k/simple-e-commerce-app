package com.example.e_commerce_app.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val title: String,

    @SerializedName("photo_url")
    val photoUrl: String,

    @SerializedName("isOnSale")
    val isOnSale: Boolean,

    @SerializedName("price")
    val price: Double
)