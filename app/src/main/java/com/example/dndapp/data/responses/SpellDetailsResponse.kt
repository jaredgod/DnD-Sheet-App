package com.example.dndapp.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpellDetailsResponse (
    val index: String,
    val name: String,
    val url: String,
    val level: Int,

    val desc: List<String>?,
    val higher_level: List<String>?,

    val attack_type: String?,
    val damage: SpellDamage?,
    val area_of_effect: SpellAoe?,
    val range: String?,
    val duration: String?,

    val concentration: Boolean?,
    val casting_time: String?,

    val components: List<String>?,
    val material: String?,
    val ritual: Boolean?,

    val school: ApiIndex?,
    val classes: List<ApiIndex>?,
    val subClasses: List<ApiIndex>?,
)

@JsonClass(generateAdapter = true)
data class SpellAoe(
    val size: Int,
    val type: Int
)

@JsonClass(generateAdapter = true)
data class SpellDamage(
    val damage_at_character_level: SpellDamageByLevel?,
    val damage_at_slot_level: SpellDamageBySlotLevel?,
    val damage_type: ApiIndex
)

@JsonClass(generateAdapter = true)
data class SpellDamageByLevel (
    @Json(name = "1") val level1: String?,
    @Json(name = "2") val level2: String?,
    @Json(name = "3") val level3: String?,
    @Json(name = "4") val level4: String?,
    @Json(name = "5") val level5: String?,
    @Json(name = "6") val level6: String?,
    @Json(name = "7") val level7: String?,
    @Json(name = "8") val level8: String?,
    @Json(name = "9") val level9: String?,
    @Json(name = "10") val level10: String?,
    @Json(name = "11") val level11: String?,
    @Json(name = "12") val level12: String?,
    @Json(name = "13") val level13: String?,
    @Json(name = "14") val level14: String?,
    @Json(name = "15") val level15: String?,
    @Json(name = "16") val level16: String?,
    @Json(name = "17") val level17: String?,
    @Json(name = "18") val level18: String?,
    @Json(name = "19") val level19: String?,
    @Json(name = "20") val level20: String?
)

@JsonClass(generateAdapter = true)
data class SpellDamageBySlotLevel (
    @Json(name = "1") val level1: String?,
    @Json(name = "2") val level2: String?,
    @Json(name = "3") val level3: String?,
    @Json(name = "4") val level4: String?,
    @Json(name = "5") val level5: String?,
    @Json(name = "6") val level6: String?,
    @Json(name = "7") val level7: String?,
    @Json(name = "8") val level8: String?,
    @Json(name = "9") val level9: String?
)