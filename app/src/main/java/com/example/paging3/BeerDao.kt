package com.example.paging3

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3.models.Beer

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<Beer>)

    @Query("SELECT * FROM Beer")
    fun getBeers(): PagingSource<Int, Beer>

    @Query("DELETE FROM Beer")
    suspend fun clearAll()
}