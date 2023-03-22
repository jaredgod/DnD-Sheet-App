package com.example.dndapp.api
import com.example.dndapp.data.responses.EquipmentDetailsResponse
import com.example.dndapp.data.responses.EquipmentNameListResponse
import com.example.dndapp.data.responses.SpellDetailsResponse
import com.example.dndapp.data.responses.SpellNameListResponse
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DndApiService {
    @GET("equipment")
    suspend fun loadEquipmentNames(
        @Query("name") name: String?
    ) : Response<EquipmentNameListResponse>

    @GET("equipment/{index}")
    suspend fun loadEquipmentDetails(
        @Path(value = "index", encoded = true) index: String
    ) : Response<EquipmentDetailsResponse>

    @GET("spells")
    suspend fun loadSpellNames(
        @Query("name") name: String?
    ) : Response<SpellNameListResponse>

    @GET("spells/{index}")
    suspend fun loadSpellDetails(
        @Path(value = "index", encoded = true) index: String
    ) : Response<SpellDetailsResponse>

    companion object {
        private const val BASE_URL = "https://www.dnd5eapi.co/api/"

        /**
         * This method can be called as `OpenWeatherService.create()` to create an object
         * implementing the OpenWeatherService interface and which can be used to make calls to
         * the OpenWeather API.
         */
        fun create() : DndApiService {
            val moshi = Moshi.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(DndApiService::class.java)
        }
    }
}