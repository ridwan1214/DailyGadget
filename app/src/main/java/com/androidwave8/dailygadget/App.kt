package com.androidwave8.dailygadget

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room

class App : Application() {

    /*companion object {
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
    }*/
}