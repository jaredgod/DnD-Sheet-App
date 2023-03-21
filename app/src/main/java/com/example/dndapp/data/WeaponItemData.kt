package com.example.dndapp.data

import androidx.room.Entity

@Entity
data class WeaponItemData(
    val name: String,
    val ind: Int
) : java.io.Serializable
