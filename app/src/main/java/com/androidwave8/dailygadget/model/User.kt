package com.androidwave8.dailygadget.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Users")
data class User(

    @ColumnInfo(name = "Username")
    var username: String = "",
    @ColumnInfo(name = "Password")
    var password: String = "",
    @ColumnInfo(name = "Address")
    var address: String,
    @ColumnInfo(name = "Email")
    var email: String = "",
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
)