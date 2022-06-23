package com.example.carrentalowner.ui.auth.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalowner.model.authResponse.LoginResponse
import com.example.carrentalowner.repository.AuthRepository
import com.example.carrentalowner.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    var loginResponse : MutableLiveData<LoginResponse>? = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun login(
        email : String,
        password : String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = authRepository.login(email,password)
        when(request){
            is NetworkResult.Success<*> ->{
                loginResponse?.value = request.data!!
                isLoading.value = false
            }
            is NetworkResult.Error<*> ->{
                isLoading.value = false
                onError.value = request.message
            }
        }
    }
}