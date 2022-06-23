package com.example.carrentalowner.ui.home.rentstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalowner.model.rentResponse.RentResponse
import com.example.carrentalowner.repository.RentRepository
import com.example.carrentalowner.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
@HiltViewModel
class RentHistoryViewModel @Inject constructor(private val rentRepository: RentRepository) :
    ViewModel() {
    var rentHistoryListResponse: MutableLiveData<RentResponse> = MutableLiveData()
    var onLoading: MutableLiveData<Boolean> = MutableLiveData()
    var onError: MutableLiveData<String> = MutableLiveData()

    fun getRentHistory(
        ownerId : String
    ) = viewModelScope.launch {
        onLoading.value = true

        val request = rentRepository.getReservations(ownerId)
        when(request){
            is NetworkResult.Success ->{
                onLoading.value = false
                rentHistoryListResponse.value = request.data!!
            }
            is NetworkResult.Error ->{
                onLoading.value = false
                onError.value = request.message
            }
        }
    }

}