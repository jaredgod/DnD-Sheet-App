package com.example.dndapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dndapp.data.SheetItemData
import com.example.dndapp.data.db.*
import com.example.dndapp.db.AppDatabase
import com.example.dndapp.db.SavedSheetsRepository
import kotlinx.coroutines.launch

class SheetViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SavedSheetsRepository(
        AppDatabase.getInstance(application).sheetDao(),
        AppDatabase.getInstance(application).equipmentDao(),
        AppDatabase.getInstance(application).spellDao()
    )

    var currentSheet: SheetItemData? = null

    //repository functions

    val sheets = repository.getAllSheets().asLiveData()

    fun getCurrentSheet() =
        repository.getSheetByName(currentSheet?.name ?: "").asLiveData()

    fun updateCurrentSheet(update: SheetNoteUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            repository.updateSheet(update)
        }
    }

    fun updateCurrentSheet(update: SheetStatUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            repository.updateSheet(update)
        }
    }

    fun updateCurrentSheet(update: SheetCombatUpdateData) {
        update.name = currentSheet?.name ?: ""
        viewModelScope.launch {
            repository.updateSheet(update)
        }
    }

    fun getSheetByName(name: String) =
        repository.getSheetByName(name).asLiveData()

    fun countSheet(name: String) =
        repository.countSheet(name).asLiveData()

    fun getEquipment(currentSheet: String) =
        repository.getAllEquipment(currentSheet).asLiveData()

    fun getWeapons(currentSheet: String) =
        repository.getAllWeapons(currentSheet).asLiveData()

    fun getEquipmentByIndex(index: Int) =
        repository.getEquipmentByIndex(index).asLiveData()

    fun getSpells(currentSheet: String, spellClass: Int) =
        repository.getAllSpells(currentSheet, spellClass).asLiveData()

    fun getSpellByIndex(index: Int) =
        repository.getSpellByIndex(index).asLiveData()

    fun addSheet(sheet: CharacterSheetData) {
        viewModelScope.launch {
            repository.insertSheet(sheet)
        }
    }

    fun removeSheet(sheet: String) {
        viewModelScope.launch {
            repository.deleteSheet(sheet)
        }
    }

    fun addEquipment(equipment: EquipmentDetailsData) {
        viewModelScope.launch {
            repository.insertEquipment(equipment)
        }
    }

    fun removeEquipment(index: Int) {
        viewModelScope.launch {
            repository.deleteEquipment(index)
        }
    }

    fun addSpell(spell: SpellDetailsData) {
        viewModelScope.launch {
            repository.insertSpell(spell)
        }
    }

    fun removeSpell(index: Int) {
        viewModelScope.launch {
            repository.deleteSpell(index)
        }
    }
}