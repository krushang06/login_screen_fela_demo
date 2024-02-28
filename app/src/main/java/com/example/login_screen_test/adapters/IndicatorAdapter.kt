package com.example.login_screen_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.ItemIndicatorSelectedBinding

class IndicatorAdapter(private val indicatorSize: Int) :
    RecyclerView.Adapter<SelectedIndicatorVH>() {

    private var selectedPosition = 0 //chnage & rv update


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedIndicatorVH {
        return SelectedIndicatorVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_indicator_selected,
                parent, false
            )
        )
    }

    fun updateSelected(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = indicatorSize

    override fun onBindViewHolder(holder: SelectedIndicatorVH, position: Int) {
        if (position == selectedPosition) {
            holder.binding.indicator.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.selected
                )
            )
        } else {
            holder.binding.indicator.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.unselected
                )
            )
        }
    }
}

class SelectedIndicatorVH(val binding: ItemIndicatorSelectedBinding) :
    ViewHolder(binding.root)