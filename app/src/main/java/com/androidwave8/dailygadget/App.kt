package com.androidwave8.dailygadget

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.androidwave8.dailygadget.data.db.Database

class App: Application() {

    companion object {
        lateinit var context: Context
        lateinit var dbUser: Database
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        dbUser = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java, "user"
        ).build()
    }
}