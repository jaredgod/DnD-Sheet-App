package com.example.dndapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class SpellDetailsData (
    val sheet: String,
    val spellClass: Int,
    val name: String,

    val details: String = "",
    @PrimaryKey(autoGenerate = true)
    var ind: Int = 0
) : Serializable