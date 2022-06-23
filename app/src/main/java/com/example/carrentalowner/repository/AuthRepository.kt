package com.example.carrentalowner.repository

import com.example.carrentalowner.network.service.RentService
import com.example.carrentalowner.utils.base.BaseRepository
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
class AuthRepository @Inject constructor(private val rentService: RentService) : BaseRepository() {
    suspend fun login(
        ownerId : String,
        password :String
    ) = safeApiRequest {
        rentService.login(ownerId,password)
    }

}