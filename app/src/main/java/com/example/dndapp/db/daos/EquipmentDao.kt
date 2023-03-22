package com.example.dndapp.db.daos

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.example.dndapp.data.item.EquipmentItemData
import com.example.dndapp.data.item.WeaponItemData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.db.update.EquipmentUpdateData
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(repo: EquipmentDetailsData)

    @Delete
    suspend fun delete(repo: EquipmentDetailsData)

    @Update(entity = EquipmentDetailsData::class)
    suspend fun update(obj: EquipmentUpdateData)

    @Query("SELECT name, ind FROM EquipmentDetailsData where sheet = :sheetName")
    fun getAllEquipment(sheetName: String): Flow<List<EquipmentItemData>?>

    @Query("SELECT name, ind FROM EquipmentDetailsData where sheet = :sheetName AND isWeapon = true")
    fun getAllWeapons(sheetName: String): Flow<List<WeaponItemData>?>

    @Query("SELECT * FROM EquipmentDetailsData WHERE ind = :index LIMIT 1")
    fun getEquipmentByIndex(index: Int): Flow<EquipmentDetailsData?>

    @Query("DELETE FROM EquipmentDetailsData WHERE ind = :index")
    suspend fun deleteEquipment(index: Int)

}