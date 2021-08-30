package com.androidwave8.dailygadget.model

import android.app.Application
import android.content.Context
import androidx.room.Room



class App : Application() {
    companion object {
        lateinit var context: Context
        var db: Database? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        db = Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "user"
        ).build()
    }
}