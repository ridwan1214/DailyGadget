package com.androidwave8.dailygadget.data.ui.home

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidwave8.dailygadget.App
import com.androidwave8.dailygadget.data.db.Gadget
import com.androidwave8.dailygadget.databinding.ListGadgetItemBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import java.lang.Exception


class HomeAdapter(
    private val data: MutableList<Gadget> = mutableListOf()
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private lateinit var listenClickEdit: (Gadget, Int) -> Unit
    private lateinit var listenClickItem: (Gadget) -> Unit

    fun setListenerClickItem(listenClickItem: (Gadget) -> Unit) {
        this.listenClickItem = listenClickItem
    }

    fun setListenClickEdit(listenClickEdit: (Gadget, Int) -> Unit) {
        this.listenClickEdit = listenClickEdit
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun addData(gadget: Gadget) {
//        this.data.add(gadget)
//        notifyDataSetChanged()
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Gadget>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun deleteData(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)
    }

//    fun editData(gadget: Gadget, position: Int) {
//        this.data[position] = gadget
//        notifyItemChanged(position)
//    }


    // method ini fungsinya untuk set layout item nya ke adapter biar bisa muncul di recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ListGadgetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    // method ini fungsinya untuk nge set mutablelist datanya ke view
    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data[position]
        holder.setData(item) // set data ke view

        holder.itemView.setOnClickListener {
            listenClickItem(item)
        }

    }

    // method ini fungsinya untuk, berapa item yang mau kita tampilin karna mau semua item
    // makanya aku ambil dari data.size -> (mutablelist)
    override fun getItemCount(): Int = data.size


    // class ini tujuannya untuk penghubung antara datanya dan view biar bisa di set datanya ke view
    inner class HomeViewHolder(private val v: ListGadgetItemBinding) :
        RecyclerView.ViewHolder(v.root) {


        @SuppressLint("SetTextI18n")
        @DelicateCoroutinesApi
        fun setData(data: Gadget) {
            v.title.text = data.title
            v.description.text = data.description
            v.price.text = "Rp ${data.price}"

            Glide.with(v.root)
                .load(data.image)
                .into(v.image)


            v.imgEditList.setOnClickListener {
                listenClickEdit(data, absoluteAdapterPosition)
            }

            v.imgDeleteList.setOnClickListener {
                deleteData(absoluteAdapterPosition)
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        App.db.gadgetsDao().deleteGadget(data)
                    } catch (e: Exception) {
                        launch(Dispatchers.Main) {
//                        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }

        }
    }
}


@Parcelize
data class ListData(
    var title: String,
    var description: String,
    var price: String,
    var imgUrl: String,
    var uid: Int
) :
    Parcelable