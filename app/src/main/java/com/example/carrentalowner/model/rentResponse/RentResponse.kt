package com.example.carrentalowner.model.rentResponse


import com.google.gson.annotations.SerializedName

data class RentResponse(
    @SerializedName("rent")
    val rent: List<Rent>?,
    @SerializedName("success")
    val success: Int?
)