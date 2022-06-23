package com.example.carrentalowner.model.rentResponse.caraddition.add


import com.google.gson.annotations.SerializedName

data class AddNewCarData(
    @SerializedName("data")
    val `data`: List<Any>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)