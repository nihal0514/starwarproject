package com.example.starwarproject.di

import android.content.Context
import androidx.room.Room
import com.example.starwarproject.db.CharacterDao
import com.example.starwarproject.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            "character_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }
}
