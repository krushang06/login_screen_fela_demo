package com.example.login_screen_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_screen_test.databinding.FragmentBannerBinding
import com.example.login_screen_test.home.BannerImages


class BannerAdapter (private val bannerImages: ArrayList<BannerImages>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val binding = FragmentBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BannerAdapter.BannerViewHolder, position: Int) {
        val bannerImage = bannerImages[position]

        Glide.with(holder.itemView.context)
            .load(bannerImages[position].bannerImage?.imageUrl)
            .into(holder.binding.bannerImage)

    }


    override fun getItemCount(): Int {
        return bannerImages.size
    }
    class BannerViewHolder(val binding:FragmentBannerBinding) : RecyclerView.ViewHolder(binding.root)
}
