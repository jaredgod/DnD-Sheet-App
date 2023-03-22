package com.example.dndapp.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class EquipmentDetailsResponse (
    val index: String,
    val name: String,
    val url: String,
    val desc: List<String>,
    val equipment_category: ApiIndex,
    val cost: EquipmentCost,
    val weight: Int,

    //weapon
    val weapon_category: String?,
    val weapon_range: String?,
    val category_range: String?,
    val range: WeaponRange?,
    val damage: WeaponDamage?,
    val two_handed_damage: WeaponDamage?,
    val properties: List<ApiIndex>?,

    //armor
    val armor_category: String?,
    val armor_class: ArmorClass?,
    val str_minimum: Int?,
    val stealth_disadvantage: Boolean?,

    //Gear + Equipment Pack
    val gear_category: ApiIndex?,
    val contents: List<ApiIndex>?

) : Serializable

@JsonClass(generateAdapter = true)
data class EquipmentCost(
    val quantity: Int,
    val unit: String
) : Serializable

@JsonClass(generateAdapter = true)
data class WeaponRange(
    val normal: Int,
    val long: Int?
) : Serializable

@JsonClass(generateAdapter = true)
data class WeaponDamage(
    val damage_dice: String,
    val damage_type: ApiIndex
) : Serializable

@JsonClass(generateAdapter = true)
data class ArmorClass(
    val base: Int,
    val dex_bonus: Boolean?,
    val max_bonus: Int?
) : Serializable