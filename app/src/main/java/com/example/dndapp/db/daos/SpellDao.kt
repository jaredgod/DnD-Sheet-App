package com.example.dndapp.db.daos

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.item.SpellItemData
import com.example.dndapp.data.db.SpellDetailsData
import com.example.dndapp.data.db.update.EquipmentUpdateData
import com.example.dndapp.data.db.update.SpellUpdateData
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(repo: SpellDetailsData)

    @Delete
    suspend fun delete(repo: SpellDetailsData)

    @Update(entity = SpellDetailsData::class)
    suspend fun update(obj: SpellUpdateData)

    @Query("SELECT name, ind FROM SpellDetailsData where sheet = :sheetName AND spellClass = :spellClass")
    fun getAllSpells(sheetName: String, spellClass: Int): Flow<List<SpellItemData>?>

    @Query("SELECT * FROM SpellDetailsData WHERE ind = :index LIMIT 1")
    fun getSpellByIndex(index: Int): Flow<SpellDetailsData?>

    @Query("DELETE FROM SpellDetailsData WHERE ind = :index")
    suspend fun deleteSpell(index: Int)

    @Query("DELETE FROM SpellDetailsData WHERE sheet = :sheet")
    suspend fun deleteSheetSpells(sheet: String)

}