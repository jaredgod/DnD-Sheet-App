package com.example.dndapp.data.responses

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class EquipmentNameListResponse (
    val count: Int,
    @Json(name = "results") val list: List<ApiIndex>
) : Serializable

@JsonClass(generateAdapter = true)
data class ApiIndex(
    val index: String,
    val name: String,
    val url: String
) : Serializable