package com.example.dndapp.data

import androidx.room.Entity

@Entity
class SpellItemData (
    val name: String,
    val ind: Int
) : java.io.Serializable