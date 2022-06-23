package com.example.carrentalowner.model.rentResponse.ownerResponse


import com.google.gson.annotations.SerializedName

data class OwnerCar(
    @SerializedName("cars")
    val cars: List<Car>?,
    @SerializedName("success")
    val success: Int?
)