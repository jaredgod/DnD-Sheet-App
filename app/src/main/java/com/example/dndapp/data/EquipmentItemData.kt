package com.example.dndapp.data

import androidx.room.Entity

@Entity
data class EquipmentItemData (
    val name: String,
    val ind: Int
) : java.io.Serializable