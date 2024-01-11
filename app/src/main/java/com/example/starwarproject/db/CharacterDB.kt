package com.example.starwarproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.model.ResultsItemEntity

@Database(entities = [ResultsItemEntity::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "character_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
