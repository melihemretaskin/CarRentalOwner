package com.example.carrentalowner.model.rentResponse.ownerResponse


import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("car_name")
    val carName: String?,
    @SerializedName("car_photo")
    val carPhoto: String?,
    @SerializedName("daily_fee")
    val dailyFee: String?,
    @SerializedName("deposit")
    val deposit: String?,
    @SerializedName("fuel")
    val fuel: String?,
    @SerializedName("owner_id")
    val ownerId: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("time_to_be_added")
    val timeToBeAdded: String?
)