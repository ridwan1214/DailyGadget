package com.androidwave8.dailygadget.data.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM Users Where email = :email and password = :password limit 1")
    fun getUser(email: String, password: String): User

    @Insert(onConflict = REPLACE)
    fun insertUser(student: User): Long

    @Update
    fun updateUser(student: User): Int

    @Delete
    fun deleteUser(student: User): Int
}