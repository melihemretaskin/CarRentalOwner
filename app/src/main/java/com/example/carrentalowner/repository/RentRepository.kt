package com.example.carrentalowner.repository

import com.example.carrentalowner.network.service.RentService
import com.example.carrentalowner.utils.base.BaseRepository
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
class RentRepository @Inject constructor(private val rentService: RentService) : BaseRepository() {

    suspend fun getReservations(
        ownerId: String
    ) = safeApiRequest {
        rentService.getReservations(ownerId)
    }

    suspend fun acceptReservation(
        id: String,
        state: String
    ) = safeApiRequest {
        rentService.acceptReservation(id, state)
    }

    suspend fun fetchOwnerData(
        ownerId: String
    ) = safeApiRequest {
        rentService.fetchOwnerData(ownerId)
    }

    suspend fun addPost(
        ownerId: String,
        comment: String
    ) = safeApiRequest {
        rentService.addPost(ownerId,comment)
    }

    suspend fun addNewCar(
        ownerId: String,
        ownerName: String,
        adId : String,
        carPhotoUrl: String,
        carName: String,
        fuel: String,
        deposit: String,
        dailyFee: String,
    ) = safeApiRequest {
        rentService.addNewCar(ownerId,ownerName,adId,carPhotoUrl,carName,fuel,deposit,dailyFee
        )
    }

    suspend fun getOwnerCars(
        ownerId : String
    ) = safeApiRequest {
        rentService.getOwnerCars(ownerId)
    }
}