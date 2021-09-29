package com.androidwave8.dailygadget.data.ui.home

import com.androidwave8.dailygadget.data.db.Gadget

interface HomeView {
    fun getListGadgetSuccess(data: MutableList<Gadget>)
    fun getListGadgetFailed(msg: String)
}