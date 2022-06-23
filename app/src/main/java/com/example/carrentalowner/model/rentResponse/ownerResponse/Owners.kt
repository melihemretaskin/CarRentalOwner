package com.example.carrentalowner.model.rentResponse.ownerResponse


import com.google.gson.annotations.SerializedName

data class Owners(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("owner_name")
    val ownerName: String?,
    @SerializedName("owner_id")
    val ownerId: String?
)