package com.example.carrentalowner.data.local

import android.content.Context


class ClientPreferences(context: Context) {
    companion object{
        private const val PREFERENCES_NAME = "User"
        private const val OWNER_ID = "OwnerId"
        private const val OWNER_NAME = "OwnerName"
        private const val REMEMBER_ME = "RememberMe"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)
    }

    fun setOwnerId(ownerId : String){
        with(sharedPreferences.edit()){
            ownerId.let {
                putString(OWNER_ID,it)
                apply()
            }
        }
    }

    fun getOwnerId() = sharedPreferences.getString(OWNER_ID,"")

    fun setOwnerName(ownerName : String){
        with(sharedPreferences.edit()){
            ownerName.let {
                putString(OWNER_NAME,it)
                apply()
            }
        }
    }

    fun getOwnerName() = sharedPreferences.getString(OWNER_NAME,"")

    fun clearSharedPref(){
        with(sharedPreferences.edit()){
            clear()
            apply()
        }
    }

}