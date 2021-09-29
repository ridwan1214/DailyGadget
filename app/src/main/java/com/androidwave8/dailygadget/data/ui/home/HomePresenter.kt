package com.androidwave8.dailygadget.data.ui.home

import com.androidwave8.dailygadget.App
import com.androidwave8.dailygadget.data.db.Gadget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HomePresenter(private val v: HomeView) {
    fun getAllListGadget() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = App.db.gadgetsDao().getAllGadget()
                v.getListGadgetSuccess(data as MutableList<Gadget>)
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    v.getListGadgetFailed(e.message.toString())
                }
            }
        }
    }
}