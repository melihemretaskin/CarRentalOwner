package com.example.carrentalowner.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalowner.model.rentResponse.ownerResponse.OwnerCar
import com.example.carrentalowner.model.rentResponse.ownerResponse.OwnerData
import com.example.carrentalowner.repository.RentRepository
import com.example.carrentalowner.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val rentRepository: RentRepository): ViewModel() {
    var rentResponse : MutableLiveData<OwnerCar>? = MutableLiveData()
    var fetchResponse : MutableLiveData<OwnerData>? = MutableLiveData()

    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun getReservations(
        ownerId : String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = rentRepository.getOwnerCars(ownerId)

        when(request){
            is NetworkResult.Success ->{
                isLoading.value = false
                rentResponse?.value = request.data!!
            }
            is NetworkResult.Error ->{
                isLoading.value = false
                onError.value = request.message
            }
        }
    }

    fun fetchOwnerData(
        ownerId : String
    ) = viewModelScope.launch {
        val request = rentRepository.fetchOwnerData(ownerId)

        when(request){
            is NetworkResult.Success ->{
                fetchResponse?.value = request.data!!
            }
        }
    }


}