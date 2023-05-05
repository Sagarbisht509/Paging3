package com.example.paging3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Beer")
data class Beer(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val description: String,
    val first_brewed: String,
    val image_url: String,
    val name: String,
    val tagline: String
)