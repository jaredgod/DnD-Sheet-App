package com.example.dndapp.db

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.dndapp.data.db.CharacterSheetData
import com.example.dndapp.data.db.SheetCombatUpdateData
import com.example.dndapp.data.db.SheetNoteUpdateData
import com.example.dndapp.data.db.SheetStatUpdateData
import kotlinx.coroutines.flow.Flow

@Dao
interface SheetDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(repo: CharacterSheetData)

    @Update(entity = CharacterSheetData::class)
    suspend fun update(obj: SheetNoteUpdateData)

    @Update(entity = CharacterSheetData::class)
    suspend fun update(obj: SheetStatUpdateData)

    @Update(entity = CharacterSheetData::class)
    suspend fun update(obj: SheetCombatUpdateData)

    @Query("SELECT name FROM CharacterSheetData")
    fun getAllSheets(): Flow<List<String>>

    @Query("SELECT * FROM CharacterSheetData WHERE name = :name LIMIT 1")
    fun getSheetByName(name: String): Flow<CharacterSheetData?>

    @Query("SELECT count(*) FROM CharacterSheetData WHERE name = :name LIMIT 1")
    fun countSheet(name: String): Flow<Int>

    @Query("DELETE FROM CharacterSheetData WHERE name = :name")
    suspend fun deleteSheet(name: String)
}