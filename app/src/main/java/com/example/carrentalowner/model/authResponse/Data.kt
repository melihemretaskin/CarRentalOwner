package com.example.carrentalowner.model.authResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("owner_name")
    val ownerName: String?,
    @SerializedName("owner_id")
    val ownerId: String?
) : Parcelable