package com.androidwave8.dailygadget.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gadgets")
class Gadget(
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "price")
    var price: String = "",
    @ColumnInfo(name = "image")
    var image: String = "",
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
)