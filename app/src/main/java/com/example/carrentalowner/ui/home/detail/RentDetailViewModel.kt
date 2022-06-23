package com.example.carrentalowner.ui.home.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalowner.repository.RentRepository
import com.example.carrentalowner.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
@HiltViewModel
class RentDetailViewModel @Inject constructor(private val rentRepository: RentRepository): ViewModel() {
    var rentAcceptResponse : MutableLiveData<String>? = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun acceptReservation(
        id : String,
        state : String
    ) = viewModelScope.launch {
        val request = rentRepository.acceptReservation(id,state)

        when(request){
            is NetworkResult.Success ->{
                rentAcceptResponse?.value = "Successfully Rented."
            }
            is NetworkResult.Error ->{
                onError.value = "Something went wrong."
            }
        }
    }
}