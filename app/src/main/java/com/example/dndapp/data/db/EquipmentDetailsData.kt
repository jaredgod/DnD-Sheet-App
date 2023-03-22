package com.example.dndapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class EquipmentDetailsData (
    val sheet: String,
    val name: String,
    val isWeapon: Boolean = false,
    var details: String = "",
    @PrimaryKey(autoGenerate = true)
    var ind: Int = 0
) : Serializable