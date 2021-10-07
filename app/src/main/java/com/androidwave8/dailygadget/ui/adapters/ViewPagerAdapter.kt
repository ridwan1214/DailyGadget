package com.androidwave8.dailygadget.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidwave8.dailygadget.data.model.OnBoardingData
import com.androidwave8.dailygadget.databinding.ItemOnboardingScreen2Binding
import com.androidwave8.dailygadget.databinding.ItemOnboardingScreenBinding

class ViewPagerAdapter(private val items: ArrayList<OnBoardingData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SHOW_IMAGE_ON_TOP -> {
                TopViewHolder(
                    ItemOnboardingScreenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                BottomViewHolder(
                    ItemOnboardingScreen2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is TopViewHolder -> holder.bind(data)
            is BottomViewHolder -> holder.bind(data)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return SHOW_IMAGE_ON_TOP.takeIf { position % 2 == 0 } ?: SHOW_IMAGE_ON_BOTTOM
    }

    companion object {
        const val SHOW_IMAGE_ON_TOP = 1
        const val SHOW_IMAGE_ON_BOTTOM = 2
    }

    class TopViewHolder(private val binding: ItemOnboardingScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OnBoardingData) {
            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.description
            binding.ivImage.setImageResource(data.image)
        }
    }

    class BottomViewHolder(private val binding: ItemOnboardingScreen2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OnBoardingData) {
            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.description
            binding.ivImage.setImageResource(data.image)
        }
    }

}