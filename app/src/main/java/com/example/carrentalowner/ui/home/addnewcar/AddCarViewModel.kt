package com.example.carrentalowner.ui.home.addnewcar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalowner.model.rentResponse.caraddition.AddPostResponse
import com.example.carrentalowner.model.rentResponse.caraddition.add.AddNewCarData
import com.example.carrentalowner.repository.RentRepository
import com.example.carrentalowner.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 19.06.2022.
 */
@HiltViewModel
class AddCarViewModel @Inject constructor(private val rentRepository: RentRepository): ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    var addPostResponse : MutableLiveData<AddPostResponse>? = MutableLiveData()
    var addCarResponse : MutableLiveData<AddNewCarData>? = MutableLiveData()


    fun addNewCar(
        ownerId : String,
        ownerName : String,
        adId : String,
        carPhotoUrl : String,
        carName : String,
        fuel : String,
        deposit : String,
        dailyFee : String,
    ) = viewModelScope.launch {
        isLoading.value = true

        val request = rentRepository.addNewCar(ownerId,ownerName,adId,carPhotoUrl,carName,fuel,deposit,dailyFee)
        when(request){
            is NetworkResult.Success ->{
                isLoading.value = false
                addCarResponse?.value = request.data!!
            }
            is NetworkResult.Error ->{
                isLoading.value = false
                onError.value = request.message
            }
        }
    }

    fun addPost(
        ownerId: String,
        comment: String
    ) = viewModelScope.launch {
        val request = rentRepository.addPost(ownerId,comment)
        when(request){
            is NetworkResult.Success ->{
                addPostResponse?.value = request.data!!
            }
        }
    }

}