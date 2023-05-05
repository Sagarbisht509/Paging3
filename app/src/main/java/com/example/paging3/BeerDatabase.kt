package com.example.paging3

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging3.models.Beer

@Database(
    entities = [Beer::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {
    abstract val beerDao : BeerDao
}