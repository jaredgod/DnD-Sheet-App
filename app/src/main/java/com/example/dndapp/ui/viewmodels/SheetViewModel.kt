package com.example.dndapp.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.dndapp.api.DndApiRepository
import com.example.dndapp.api.DndApiService
import com.example.dndapp.data.item.SheetItemData
import com.example.dndapp.data.db.*
import com.example.dndapp.data.db.update.*
import com.example.dndapp.data.responses.EquipmentDetailsResponse
import com.example.dndapp.data.responses.SpellDetailsResponse
import com.example.dndapp.db.AppDatabase
import com.example.dndapp.db.SavedSheetsRepository
import kotlinx.coroutines.launch

class SheetViewModel(application: Application): AndroidViewModel(application) {

    // API Functions
    private val apiRepository = DndApiRepository(DndApiService.create())


    private suspend fun fetchItemData(name: String, sheet: String): EquipmentDetailsData {
        val result = apiRepository.loadEquipmentNames(name)
        val nameError = result.exceptionOrNull()
        val nameResponse = result.getOrNull()

        if(nameError == null){
            if(nameResponse != null && nameResponse.count > 0 && nameResponse.list[0].name == name){
                val apiIndex = nameResponse.list[0]
                val equipmentDetailsResult = apiRepository.loadEquipmentDetails(apiIndex.index)

                val error = equipmentDetailsResult.exceptionOrNull()
                val response = equipmentDetailsResult.getOrNull()

                if(error == null){
                    if(response != null){
                        val details = buildEquipmentDetails(response)
                        val isWeapon = response.equipment_category.index == "weapon"
                        return EquipmentDetailsData(sheet, name, isWeapon, details)
                    }
                }
                else{
                    Log.d("ApiError", error.message!!)
                }
            }
        }
        else{
            Log.d("ApiError", nameError.message!!)
        }
        return EquipmentDetailsData(sheet, name)
    }

    private fun buildEquipmentDetails(response: EquipmentDetailsResponse) : String{
        var ret = ""

        for(line in response.desc){
            ret += line + "\n\n"
        }
        if(response.desc.isNotEmpty()) ret += "\n\n"

        ret += addField("Weapon Category", response.weapon_category)
        ret += addField("Range Type", response.weapon_range)
        ret += addField("Range", response.range?.normal)
        ret += addField("Long Range", response.range?.long)
        ret += addField("Damage", response.damage?.damage_dice, response.damage?.damage_type?.name)
        ret += addField("Two-Handed Damage", response.two_handed_damage?.damage_dice, response.two_handed_damage?.damage_type?.name)

        if(response.properties != null && response.properties.isNotEmpty()){
            ret += "\nProperties:\n"
            for(property in response.properties){
                ret += property.name + '\n'
            }
            ret += "\n"
        }

        ret += addField("Armor Category", response.armor_category)
        if(response.armor_class != null){
            ret += "AC: ${response.armor_class.base}"
            if(response.armor_class.dex_bonus != null && response.armor_class.dex_bonus) ret += "+dex"
            if(response.armor_class.max_bonus != null) ret += " (max ${response.armor_class.max_bonus})"
            ret += "\n"
        }

        ret += addField("Minimum Strength", response.str_minimum)

        if(response.stealth_disadvantage != null && response.stealth_disadvantage) ret += "Roll stealth with disadvantage\n"

        ret += addField("Gear Category", response.gear_category?.name)

        if(response.contents != null && response.contents.isNotEmpty()){
            ret += "\nContents:\n"
            for(content in response.contents){
                ret += content.name + '\n'
            }
            ret += "\n"
        }

        ret += "Cost: ${response.cost.quantity} ${response.cost.unit}\n"
        ret += "Weight: ${response.weight}\n"

        return ret
    }

    private suspend fun fetchSpellData(name: String, sheet: String, level: Int): SpellDetailsData {
        Log.d("test", "1")

        val result = apiRepository.loadSpellNames(name)
        val nameError = result.exceptionOrNull()
        val nameResponse = result.getOrNull()

        if(nameError == null){
            Log.d("test", "2")
            Log.d("test", if(nameResponse != null) "${nameResponse.count}" else "null")
            if(nameResponse != null && nameResponse.count > 0 && nameResponse.list[0].name == name){
                Log.d("test", "3")
                val apiIndex = nameResponse.list[0]
                val spellDetailsResult = apiRepository.loadSpellDetails(apiIndex.index)

                val error = spellDetailsResult.exceptionOrNull()
                val response = spellDetailsResult.getOrNull()

                if(error == null){
                    Log.d("test", "4")
                    if(response != null){
                        Log.d("test", "5")
                        val details = buildSpellDetails(response)
                        val loadedLevel = response.level
                        return SpellDetailsData(sheet, loadedLevel, name, details)
                    }
                }
                else{
                    Log.d("ApiError", error.message!!)
                }
            }
        }
        else{
            Log.d("ApiError", nameError.message!!)
        }
        return SpellDetailsData(sheet, level, name)
    }

    private fun buildSpellDetails(response: SpellDetailsResponse) : String{
        var ret = ""

        if(response.desc != null) {
            for (line in response.desc) {
                ret += line + "\n\n"
            }
        }
        if(response.higher_level != null) {
            for (line in response.higher_level) {
                ret += line + "\n\n"
            }
        }

        ret += addField("Attack Type", response.attack_type)
        ret += addField("Range", response.range)

        if(response.damage !== null){
            if(response.damage.damage_at_character_level != null){
                val damage = response.damage.damage_at_character_level
                ret += "Damage based on character level:\n"
                ret += addField("Level 1 Damage", damage.level1)
                ret += addField("Level 2 Damage", damage.level2)
                ret += addField("Level 3 Damage", damage.level3)
                ret += addField("Level 4 Damage", damage.level4)
                ret += addField("Level 5 Damage", damage.level5)
                ret += addField("Level 6 Damage", damage.level6)
                ret += addField("Level 7 Damage", damage.level7)
                ret += addField("Level 8 Damage", damage.level8)
                ret += addField("Level 9 Damage", damage.level9)
                ret += addField("Level 10 Damage", damage.level10)
                ret += addField("Level 11 Damage", damage.level11)
                ret += addField("Level 12 Damage", damage.level12)
                ret += addField("Level 13 Damage", damage.level13)
                ret += addField("Level 14 Damage", damage.level14)
                ret += addField("Level 15 Damage", damage.level15)
                ret += addField("Level 16 Damage", damage.level16)
                ret += addField("Level 17 Damage", damage.level17)
                ret += addField("Level 18 Damage", damage.level18)
                ret += addField("Level 19 Damage", damage.level19)
                ret += addField("Level 20 Damage", damage.level20)
            }

            if(response.damage.damage_at_slot_level != null){
                val damage = response.damage.damage_at_slot_level

                ret += "Damage based on spell slot level:\n"
                ret += addField("Level 1 Damage", damage.level1)
                ret += addField("Level 2 Damage", damage.level2)
                ret += addField("Level 3 Damage", damage.level3)
                ret += addField("Level 4 Damage", damage.level4)
                ret += addField("Level 5 Damage", damage.level5)
                ret += addField("Level 6 Damage", damage.level6)
                ret += addField("Level 7 Damage", damage.level7)
                ret += addField("Level 8 Damage", damage.level8)
                ret += addField("Level 9 Damage", damage.level9)
            }
            ret += addField("Damage Type", response.damage.damage_type.name)
        }

        ret += "\n"

        if(response.concentration != null && response.concentration) ret += "Spell Requires Concentration\n"
        ret += addField("Casting Time", response.casting_time)

        ret += "\n"

        if(response.components != null){
            ret += "Required Components:\n"
            for(component in response.components){
                ret += when(component){
                    "V" -> "Verbal\n"
                    "S" -> "Somatic\n"
                    "M" -> "Material\n"
                    else -> ""
                }
            }
        }

        ret += "\n"

        ret += addField("Material", response.material)
        if(response.ritual!= null && response.ritual) ret += "Spell Requires Ritual\n"

        return ret
    }

    private fun addField(title: String, value: String?): String{
        return if(value != null) "$title: $value\n"
        else ""
    }

    private fun addField(title: String, value: Int?): String{
        return if(value != null) "$title: $value\n"
        else ""
    }

    private fun addField(title: String, value: String?, unit: String?): String{
        return if(value != null && unit != null) "$title: $value $unit\n"
        else ""
    }

    // Database Functions
    private val databaseRepository = SavedSheetsRepository(
        AppDatabase.getInstance(application).sheetDao(),
        AppDatabase.getInstance(application).equipmentDao(),
        AppDatabase.getInstance(application).spellDao()
    )

    var currentSheet: SheetItemData? = null


    // Sheets
    val sheets = databaseRepository.getAllSheets().asLiveData()
    fun addSheet(sheet: CharacterSheetData) {
        viewModelScope.launch {
            databaseRepository.insertSheet(sheet)
        }
    }

    fun updateCurrentSheet(update: SheetNoteUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            databaseRepository.updateSheet(update)
        }
    }

    fun updateCurrentSheet(update: SheetStatUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            databaseRepository.updateSheet(update)
        }
    }

    fun updateCurrentSheet(update: SheetCombatUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            databaseRepository.updateSheet(update)
        }
    }

    fun removeSheet(sheet: String) {
        viewModelScope.launch {
            databaseRepository.deleteSheet(sheet)
        }
    }

    fun getCurrentSheet() =
        databaseRepository.getSheetByName(currentSheet?.name ?: "").asLiveData()


    // Equipment
    fun addEquipment(name: String, sheet: String) {
        viewModelScope.launch {
            val equipment = fetchItemData(name, sheet)
            databaseRepository.insertEquipment(equipment)
        }
    }

    fun updateEquipment(update: EquipmentUpdateData){
        viewModelScope.launch {
            databaseRepository.updateEquipment(update)
        }
    }

    fun removeEquipment(index: Int) {
        viewModelScope.launch {
            databaseRepository.deleteEquipment(index)
        }
    }

    fun getEquipment(currentSheet: String) =
        databaseRepository.getAllEquipment(currentSheet).asLiveData()

    fun getWeapons(currentSheet: String) =
        databaseRepository.getAllWeapons(currentSheet).asLiveData()

    fun getEquipmentByIndex(index: Int) =
        databaseRepository.getEquipmentByIndex(index).asLiveData()


    // Spells
    fun addSpell(name: String, sheet: String, level: Int) {
        viewModelScope.launch {
            val spell = fetchSpellData(name, sheet, level)
            databaseRepository.insertSpell(spell)
        }
    }

    fun updateSpell(update: SpellUpdateData){
        viewModelScope.launch {
            databaseRepository.updateSpell(update)
        }
    }

    fun removeSpell(index: Int) {
        viewModelScope.launch {
            databaseRepository.deleteSpell(index)
        }
    }

    fun getSpells(currentSheet: String, spellClass: Int) =
        databaseRepository.getAllSpells(currentSheet, spellClass).asLiveData()

    fun getSpellByIndex(index: Int) =
        databaseRepository.getSpellByIndex(index).asLiveData()
}