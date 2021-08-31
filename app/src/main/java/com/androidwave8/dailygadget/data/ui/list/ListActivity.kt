package com.androidwave8.dailygadget.data.ui.list

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.androidwave8.dailygadget.data.db.Gadget
import com.androidwave8.dailygadget.databinding.ActivityListBinding
import com.androidwave8.dailygadget.data.ui.home.ListData

class ListActivity : AppCompatActivity(), ListView {

    private val presenter by lazy { ListPresenter(this) }
    private lateinit var binding: ActivityListBinding
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var price: EditText
    private lateinit var imageUrl: EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = binding.etTitle
        description = binding.etDescription
        price = binding.etPrice
        imageUrl = binding.etImageUrl

        val bundle = intent.getBundleExtra("Bundle")
        val objects = bundle?.getParcelable<ListData>("key")
        val editTitle = objects?.title ?: ""

        if (!editTitle.isBlank()) {
            binding.etTitle.setText(objects?.title)
            binding.etDescription.setText(objects?.description)
            binding.etPrice.setText(objects?.price)
            binding.etImageUrl.setText(objects?.imgUrl)

            binding.btnAction.setText("Edit List")

            binding.btnAction.setOnClickListener {
                title = binding.etTitle
                description = binding.etDescription
                price = binding.etPrice

                if (presenter.validasiDataIsEmpty(title, description, price)) {
                    Toast.makeText(this, "Silahkan isi data dengan benar", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val gadget =
                        Gadget(
                            title.text.toString(),
                            description.text.toString(),
                            price.text.toString(),
                            imageUrl.text.toString(),
                            objects!!.uid
                        )

                    presenter.editGadget(gadget)
                }
            }

        } else {

            binding.btnAction.setText("Add List")

            binding.btnAction.setOnClickListener {
//                title = binding.etTitle
//                description = binding.etDescription
//                price = binding.etPrice

                if (presenter.validasiDataIsEmpty(title, description, price)) {
                    Toast.makeText(this, "Silahkan isi data dengan benar", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val gadget = Gadget(
                        title.text.toString(),
                        description.text.toString(),
                        price.text.toString(),
                        imageUrl.text.toString(),
                    )
                    presenter.addGadget(gadget)
                    Toast.makeText(this, "Add Gadget Berhasil", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        title.setText("")
        description.setText("")
        price.setText("")
    }

    override fun addGadgetSuccess() {
        Toast.makeText(this, "Add Gadget Berhasil", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun editGadgetSuccess() {
        Toast.makeText(this, "Edit Gadget Berhasil", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun addGadgetFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun editGadgetFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}