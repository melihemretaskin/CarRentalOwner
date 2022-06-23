package com.example.carrentalowner.model.rentResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rent(
    @SerializedName("ad_id")
    val adId: String?,
    @SerializedName("car_name")
    val carName: String?,
    @SerializedName("daily_fee")
    val dailyFee: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("state")
    val state: String?
) : Parcelable