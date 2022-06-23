package com.example.carrentalowner.utils.extension

import com.example.carrentalowner.model.rentResponse.ownerResponse.Car


interface OnItemClickListener {
    fun onClick(car : Car)
}