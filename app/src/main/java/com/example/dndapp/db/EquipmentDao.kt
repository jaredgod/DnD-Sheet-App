package com.example.dndapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.dndapp.data.EquipmentItemData
import com.example.dndapp.data.WeaponItemData
import com.example.dndapp.data.db.CharacterSheetData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.db.SpellDetailsData
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(repo: EquipmentDetailsData)

    @Delete
    suspend fun delete(repo: EquipmentDetailsData)

    @Query("SELECT name, ind FROM EquipmentDetailsData where sheet = :sheetName")
    fun getAllEquipment(sheetName: String): Flow<List<EquipmentItemData>?>

    @Query("SELECT name, ind FROM EquipmentDetailsData where sheet = :sheetName AND isWeapon = true")
    fun getAllWeapons(sheetName: String): Flow<List<WeaponItemData>?>

    @Query("SELECT * FROM EquipmentDetailsData WHERE ind = :index LIMIT 1")
    fun getEquipmentByIndex(index: Int): Flow<EquipmentDetailsData?>

    @Query("DELETE FROM EquipmentDetailsData WHERE ind = :index")
    suspend fun deleteEquipment(index: Int)

}