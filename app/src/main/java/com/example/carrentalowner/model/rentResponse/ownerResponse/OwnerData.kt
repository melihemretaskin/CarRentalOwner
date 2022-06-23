package com.example.carrentalowner.model.rentResponse.ownerResponse


import com.google.gson.annotations.SerializedName

data class OwnerData(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("owners")
    val owners: Owners?
)