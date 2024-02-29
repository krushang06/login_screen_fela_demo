package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_screen_test.WordSets.Dataword
import com.example.login_screen_test.databinding.ItemCircularImageBinding

class WordSetsAdapter(
    private var categoryimage: ArrayList<Dataword>,
    private val onUniversityClickListener: OnUniversityClickListener
) :
    RecyclerView.Adapter<WordSetsAdapter.CategoryViewHolder>() {

        @SuppressLint("NotifyDataSetChanged")
        fun setData(categoryimageList: List<Dataword?>) {
            categoryimage = categoryimageList as ArrayList<Dataword>
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemCircularImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryImage = categoryimage[position]

        Glide.with(holder.itemView.context)
            .load(categoryimage[position].categoryImage?.imageUrl)
            .into(holder.binding.circleimage)
        holder.binding.countnumber.text = categoryImage.categoryWordsCount?.toString()

        holder.binding.circleimage.setOnClickListener {
            onUniversityClickListener.onCategoryimageClicked(categoryimage[position])

        }

    }

    override fun getItemCount(): Int {
        return categoryimage.size
    }

    class CategoryViewHolder(val binding: ItemCircularImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnUniversityClickListener {
        fun onCategoryimageClicked(dataword: Dataword)
    }
}
