package com.example.dndapp.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class SpellNameListResponse (
    val count: Int,
    @Json(name = "results") val list: List<ApiIndex>
): Serializable