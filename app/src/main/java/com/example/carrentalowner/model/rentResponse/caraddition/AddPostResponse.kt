package com.example.carrentalowner.model.rentResponse.caraddition


import com.google.gson.annotations.SerializedName

data class AddPostResponse(
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)