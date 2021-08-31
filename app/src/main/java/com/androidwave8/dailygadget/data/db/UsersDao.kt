package com.androidwave8.dailygadget.data.db

import androidx.room.*

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM Users Where email = :email and password = :password limit 1")
    fun getUser(email: String, password: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int
}