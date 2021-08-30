package com.androidwave8.dailygadget.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidwave8.dailygadget.data.db.Gadget
import com.androidwave8.dailygadget.data.ui.DetailActivity
import com.androidwave8.dailygadget.databinding.ActivityHomeBinding
import com.androidwave8.dailygadget.ui.list.ListActivity

class HomeActivity : AppCompatActivity(), HomeView {

    private val presenter by lazy { HomePresenter(this) }
    private lateinit var binding: ActivityHomeBinding
    private val adapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.getAllListGadget()
        binding.rvListGadget.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvListGadget.adapter = adapter

        adapter.setListenerClickItem { gadget ->
            Intent(this, DetailActivity::class.java).apply {
                startActivity(this)
            }
        }

        adapter.setListenClickEdit { gadget, index ->
            val myIntent = Intent(this, ListActivity::class.java)
            val args = Bundle()
            val list = ListData(gadget.title, gadget.description,gadget.price, gadget.image, gadget.uid)
            args.putParcelable("key", list)
            myIntent.putExtra("Bundle", args)
            startActivity(myIntent)

        }

        binding.fbAddGadget.setOnClickListener {
            Intent(this, ListActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.getAllListGadget()
    }

    override fun getListGadgetSuccess(data: MutableList<Gadget>) {
        adapter.setData(data)
    }

    override fun getListGadgetFailed(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}