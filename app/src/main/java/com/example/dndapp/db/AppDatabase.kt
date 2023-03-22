package com.example.dndapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dndapp.data.db.CharacterSheetData
import com.example.dndapp.data.db.EquipmentDetailsData
import com.example.dndapp.data.db.SpellDetailsData
import com.example.dndapp.db.daos.EquipmentDao
import com.example.dndapp.db.daos.SheetDao
import com.example.dndapp.db.daos.SpellDao

@Database(entities = [CharacterSheetData::class, EquipmentDetailsData::class, SpellDetailsData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sheetDao(): SheetDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun spellDao(): SpellDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "savedSheets.db"
            ).build()

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}