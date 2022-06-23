package com.example.carrentalowner.network.service

import com.example.carrentalowner.model.authResponse.LoginResponse
import com.example.carrentalowner.model.rentResponse.RentResponse
import com.example.carrentalowner.model.rentResponse.caraddition.AddPostResponse
import com.example.carrentalowner.model.rentResponse.caraddition.add.AddNewCarData
import com.example.carrentalowner.model.rentResponse.ownerResponse.OwnerCar
import com.example.carrentalowner.model.rentResponse.ownerResponse.OwnerData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
interface RentService {
    //Authentication
    @FormUrlEncoded
    @POST("/car_rental/app_owners/login.php")
    suspend fun login(
        @Field("owner_id")ownerId :String,
        @Field("password") password : String
    ): LoginResponse

    @FormUrlEncoded
    @POST("/car_rental/app_owners/rent_check.php")
    suspend fun getReservations(
        @Field("owner_id")ownerId : String
    ) : RentResponse

    @FormUrlEncoded
    @POST("/car_rental/app_owners/rent_confirmation.php")
    suspend fun acceptReservation(
        @Field("id") id : String,
        @Field("state") state : String
    )

    @FormUrlEncoded
    @POST("/car_rental/app_owners/post_add.php")
    suspend fun addPost(
        @Field("owner_id") ownerId: String,
        @Field("comment") comment : String
    ) : AddPostResponse


    @FormUrlEncoded
    @POST("/car_rental/app_owners/fetch_owners.php")
    suspend fun fetchOwnerData(
        @Field("owner_id") ownerId : String,
    ) : OwnerData

    @FormUrlEncoded
    @POST("/car_rental/app_owners/add_car.php")
    suspend fun addNewCar(
        @Field("owner_id") ownerId : String,
        @Field("owner_name") ownerName : String,
        @Field("ad_id") adId : String,
        @Field("car_photo") carPhotoUrl : String,
        @Field("car_name") carName : String,
        @Field("fuel") fuel : String,
        @Field("deposit") deposit : String,
        @Field("daily_fee") dailyFee : String,
    ) : AddNewCarData

    @FormUrlEncoded
    @POST("/car_rental/app_owners/get_cars.php")
    suspend fun getOwnerCars(
        @Field("owner_id")ownerId : String
    ) : OwnerCar

}