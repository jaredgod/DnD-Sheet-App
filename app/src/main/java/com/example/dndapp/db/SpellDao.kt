package com.example.dndapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.dndapp.data.SpellItemData
import com.example.dndapp.data.db.CharacterSheetData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.db.SpellDetailsData
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(repo: SpellDetailsData)

    @Delete
    suspend fun delete(repo: SpellDetailsData)

    @Query("SELECT name, ind FROM SpellDetailsData where sheet = :sheetName AND spellClass = :spellClass")
    fun getAllSpells(sheetName: String, spellClass: Int): Flow<List<SpellItemData>?>

    @Query("SELECT * FROM SpellDetailsData WHERE ind = :index LIMIT 1")
    fun getSpellByIndex(index: Int): Flow<SpellDetailsData?>

    @Query("DELETE FROM SpellDetailsData WHERE ind = :index")
    suspend fun deleteSpell(index: Int)

}