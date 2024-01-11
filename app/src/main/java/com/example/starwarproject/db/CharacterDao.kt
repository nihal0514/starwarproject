package com.example.starwarproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.model.ResultsItemEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ResultsItemEntity>)

    @Query("SELECT * FROM RESULTS_ITEMS")
    fun getAllCharacters(): List<ResultsItemEntity>
}