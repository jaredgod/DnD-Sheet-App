package com.example.dndapp.db

import com.example.dndapp.data.db.*
import com.example.dndapp.data.db.update.*

class SavedSheetsRepository (
    private val sheetDao: SheetDao,
    private val equipmentDao: EquipmentDao,
    private val spellDao: SpellDao

) {
    // sheet
    suspend fun insertSheet(repo: CharacterSheetData) = sheetDao.insert(repo)
    suspend fun updateSheet(obj: SheetNoteUpdateData) = sheetDao.update(obj)
    suspend fun updateSheet(obj: SheetStatUpdateData) = sheetDao.update(obj)
    suspend fun updateSheet(obj: SheetCombatUpdateData) = sheetDao.update(obj)
    suspend fun deleteSheet(name: String) = sheetDao.deleteSheet(name)

    fun getAllSheets() = sheetDao.getAllSheets()

    fun getSheetByName(name: String) = sheetDao.getSheetByName(name)

    fun countSheet(name: String) = sheetDao.countSheet(name)

    // equipment
    suspend fun insertEquipment(repo: EquipmentDetailsData) = equipmentDao.insert(repo)
    suspend fun deleteEquipment(index: Int) = equipmentDao.deleteEquipment(index)
    suspend fun updateEquipment(obj: EquipmentUpdateData) = equipmentDao.update(obj)

    fun getAllEquipment(sheetName: String) = equipmentDao.getAllEquipment(sheetName)
    fun getAllWeapons(sheetName: String) = equipmentDao.getAllWeapons(sheetName)
    fun getEquipmentByIndex(index: Int) = equipmentDao.getEquipmentByIndex(index)

    // spells
    suspend fun insertSpell(repo: SpellDetailsData) = spellDao.insert(repo)
    suspend fun deleteSpell(index: Int) = spellDao.deleteSpell(index)
    suspend fun updateSpell(obj: SpellUpdateData) = spellDao.update(obj)

    fun getAllSpells(sheetName: String, spellClass: Int) = spellDao.getAllSpells(sheetName, spellClass)
    fun getSpellByIndex(index: Int) = spellDao.getSpellByIndex(index)
}