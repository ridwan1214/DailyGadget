package com.androidwave8.dailygadget.data.ui.list

import android.text.TextUtils
import android.widget.EditText
import com.androidwave8.dailygadget.utils.App
import com.androidwave8.dailygadget.data.db.Gadget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListPresenter(private val v: ListView) {

    fun validasiDataIsEmpty(
        title: EditText,
        description: EditText,
        price: EditText,
    ): Boolean = when {
        TextUtils.isEmpty(title.text.toString()) -> {
            title.error = "Title Tidak boleh kosong"
            true
        }
        TextUtils.isEmpty(description.text.toString()) -> {
            description.error = "Description Tidak boleh kosong"
            true
        }
        TextUtils.isEmpty(price.text.toString()) -> {
            price.error = "Price Tidak boleh kosong"
            true
        }
        else -> false
    }

    fun addGadget(gadget: Gadget) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                App.db
                    .gadgetsDao()
                    .insertGadget(gadget)

                launch(Dispatchers.Main) {
                    v.addGadgetSuccess()
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    v.addGadgetFailed(e.message.toString())
                }
            }
        }
    }

    fun editGadget(gadget: Gadget) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                App.db
                    .gadgetsDao()
                    .updateGadget(gadget)

                launch(Dispatchers.Main) {
                    v.editGadgetSuccess()
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    v.editGadgetFailed(e.message.toString())
                }
            }
        }
    }

}