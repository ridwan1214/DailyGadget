package com.androidwave8.dailygadget.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.androidwave8.dailygadget.data.db.DB

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var db: DB
        //lateinit var dbUser: Database
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        db = Room.databaseBuilder(
            context.applicationContext,
            DB::class.java, "DailyGadget"
        ).build()
    }
}