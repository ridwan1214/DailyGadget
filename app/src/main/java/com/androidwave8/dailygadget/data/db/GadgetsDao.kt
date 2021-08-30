package com.androidwave8.dailygadget.data.db

import androidx.room.*

@Dao
interface GadgetsDao {
    @Query("SELECT * FROM Gadgets")
    fun getAllGadget(): List<Gadget>

    @Query("SELECT * FROM Gadgets Where title = :title limit 1")
    fun getListGadget(title: String): Gadget

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGadget(gadget: Gadget): Long

//    @Query("UPDATE Gadgets SET title = :title, description= :description, price = :price Where uid = :uid")
    @Update
    fun updateGadget(gadget: Gadget): Int

    @Delete
    fun deleteGadget(gadget: Gadget): Int
}
