package com.example.dndapp.api

import com.example.dndapp.data.responses.EquipmentDetailsResponse
import com.example.dndapp.data.responses.EquipmentNameListResponse
import com.example.dndapp.data.responses.SpellDetailsResponse
import com.example.dndapp.data.responses.SpellNameListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DndApiRepository (
    private val service: DndApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun loadEquipmentNames(
        name: String
    ) : Result<EquipmentNameListResponse?> {
        return withContext(ioDispatcher) {
            try {
                val response = service.loadEquipmentNames(name)
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loadEquipmentDetails(
        index: String
    ) : Result<EquipmentDetailsResponse?> {

        return withContext(ioDispatcher) {
            try {
                val response = service.loadEquipmentDetails(index)
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loadSpellNames(
        name: String
    ) : Result<SpellNameListResponse?> {
        return withContext(ioDispatcher) {
            try {
                val response = service.loadSpellNames(name)
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun loadSpellDetails(
        index: String
    ) : Result<SpellDetailsResponse?> {

        return withContext(ioDispatcher) {
            try {
                val response = service.loadSpellDetails(index)
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}