package com.example.dndapp.data.db

import androidx.room.PrimaryKey

data class SheetStatUpdateData(
    // stats
    var level: Int = 0,
    var proficiencyBonus: Int = 0,
    var strengthVal: Int = 0,
    var strengthMod: Int = 0,
    var dexterityVal: Int = 0,
    var dexterityMod: Int = 0,
    var constitutionVal: Int = 0,
    var constitutionMod: Int = 0,
    var intelligenceVal: Int = 0,
    var intelligenceMod: Int = 0,
    var wisdomVal: Int = 0,
    var wisdomMod: Int = 0,
    var charismaVal: Int = 0,
    var charismaMod: Int = 0,

    // proficiencies
    var acrobaticsToggle: Boolean = false,
    var acrobaticsMod: Int = 0,
    var animalHandlingToggle: Boolean = false,
    var animalHandlingMod: Int = 0,
    var arcanaToggle: Boolean = false,
    var arcanaMod: Int = 0,
    var athleticsToggle: Boolean = false,
    var athleticsMod: Int = 0,
    var deceptionToggle: Boolean = false,
    var deceptionMod: Int = 0,
    var historyToggle: Boolean = false,
    var historyMod: Int = 0,
    var insightToggle: Boolean = false,
    var insightMod: Int = 0,
    var intimidationToggle: Boolean = false,
    var intimidationMod: Int = 0,
    var investigationToggle: Boolean = false,
    var investigationMod: Int = 0,
    var medicineToggle: Boolean = false,
    var medicineMod: Int = 0,
    var natureToggle: Boolean = false,
    var natureMod: Int = 0,
    var perceptionToggle: Boolean = false,
    var perceptionMod: Int = 0,
    var performanceToggle: Boolean = false,
    var performanceMod: Int = 0,
    var persuasionToggle: Boolean = false,
    var persuasionMod: Int = 0,
    var religionToggle: Boolean = false,
    var religionMod: Int = 0,
    var sleightOfHandToggle: Boolean = false,
    var sleightOfHandMod: Int = 0,
    var stealthToggle: Boolean = false,
    var stealthMod: Int = 0,
    var survivalToggle: Boolean = false,
    var survivalMod: Int = 0,
)
{
    @PrimaryKey
    var name: String = ""

}