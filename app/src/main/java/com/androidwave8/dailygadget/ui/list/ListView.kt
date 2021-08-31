package com.androidwave8.dailygadget.ui.list

interface ListView {
    fun addGadgetSuccess()
    fun editGadgetSuccess()
    fun addGadgetFailed(msg: String)
    fun editGadgetFailed(msg: String)
}