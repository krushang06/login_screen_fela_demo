package com.example.login_screen_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.ItemIndicatorSelectedBinding


class IndicatorAdapterHome(private val indicatorSize: Int) :
    RecyclerView.Adapter<IndicatorAdapterHome.IndicatorViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        return IndicatorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_indicator_selected, // Use the correct layout here
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        if (position == selectedPosition) {
            Glide.with(holder.itemView.context)
                .load(R.drawable.selected) // Use your selected indicator drawable
                .into(holder.binding.indicator)
        } else {
            Glide.with(holder.itemView.context)
                .load(R.drawable.unselected) // Use your unselected indicator drawable
                .into(holder.binding.indicator)
        }
    }

    fun updateSelectedHome(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = indicatorSize

    class IndicatorViewHolder(val binding: ItemIndicatorSelectedBinding) :
        RecyclerView.ViewHolder(binding.root)
}
