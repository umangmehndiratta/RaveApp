package com.ravetest.events.ui.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravetest.events.data.model.Banner
import com.ravetest.events.databinding.ItemBannerBinding
import com.ravetest.events.databinding.ItemBannerBinding.inflate

class BannerViewHolder(private val binding: ItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Banner) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): BannerViewHolder =
            BannerViewHolder(inflate(from(parent.context)))
    }
}
