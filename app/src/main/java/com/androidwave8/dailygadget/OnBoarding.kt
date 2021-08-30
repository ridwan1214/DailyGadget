package com.androidwave8.dailygadget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.androidwave8.dailygadget.databinding.ActivityOnBoardingBinding
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var itemList = ArrayList<OnBoardingData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        itemList = getItems()
        binding.pager.adapter = ViewPagerAdapter(itemList)
        binding.springDotsIndicator.setViewPager2(binding.pager)
        binding.pager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private var pageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == itemList.size - 1) {
                    binding.tvNext.text = "LET'S START"
                    binding.tvSkip.visibility = View.GONE
                } else {
                    binding.tvNext.text = "NEXT"
                    binding.tvSkip.visibility = View.VISIBLE
                }
            }
        }

    private fun getItems(): ArrayList<OnBoardingData> {
        val items = ArrayList<OnBoardingData>()
        items.add(
            OnBoardingData(
                "One Apps Gadget Store",
                "Satu Aplikasi untuk segala kebutuhan Toko Gadget mu",
                R.drawable.ic_store_1mdpi
            )
        )

        items.add(
            OnBoardingData(
                "Manage Your Gadget Store",
                "DailyGadget memberikan kemudahan mengatur Tokomu. Lebih praktis atur produk dan kelola toko",
                R.drawable.ic_manage_storemdpi
            )
        )

        items.add(
            OnBoardingData(
                "Chat with WhatsApp",
                "kelola pesanan melalui WhatsApp langsung kepada konsumen",
                R.drawable.ic_msgmdpi
            )
        )
        return items
    }

    fun onClick(view: View) {
        when (view) {
            binding.tvNext -> {
                val currentItemPosition = binding.pager.currentItem
                if (currentItemPosition == itemList.size - 1) {
                    Toast.makeText(this, "Start Login Activity", Toast.LENGTH_SHORT).show()
                    /*Intent(this, LoginActivity::class.java).apply {
                        startActivity(this)
                    }*/
                    return
                }
                binding.pager.setCurrentItem(currentItemPosition+1,true)
            }
            binding.tvSkip -> {
                Toast.makeText(this, "Start Login Activity", Toast.LENGTH_SHORT).show()
                /*Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }*/
            }
        }
    }
}
