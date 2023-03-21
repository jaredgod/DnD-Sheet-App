package com.example.dndapp.data.db.update

data class SheetCombatUpdateData(
    var hpCurrent: Int = 0,
    var hpMax: Int = 0,
    var hitDiceCurrent: Int = 0,
    var hitDiceMax: Int = 0,
    var armorClass: Int = 0,
    var initiative: Int = 0,
    var speed: Int = 0,
    var spellAttackBonus: Int = 0,
    var name: String = ""
)