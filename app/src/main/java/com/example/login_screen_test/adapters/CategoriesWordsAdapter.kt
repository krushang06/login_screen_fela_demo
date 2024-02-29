package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.CategoriesWords.CategoryWords
import com.example.login_screen_test.databinding.ItemCategoriesNewaddedwordsBinding

class CategoriesWordsAdapter(
    private var categorywords: ArrayList<CategoryWords>
) :
    RecyclerView.Adapter<CategoriesWordsAdapter.CategoryWordsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(categorywordsList: List<CategoryWords?>) {
        categorywords = categorywordsList as ArrayList<CategoryWords>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryWordsViewHolder {
        val binding = ItemCategoriesNewaddedwordsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CategoryWordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryWordsViewHolder, position: Int) {
        val categoryWord = categorywords[position]
//        holder.binding.word.text = categoryWord.data?.categoryWords?.toString()
        holder.binding.newaddedword.text = categoryWord.wordTranslation?.translation.toString()
    }


    override fun getItemCount(): Int {
        return categorywords.size
    }

    class CategoryWordsViewHolder(val binding: ItemCategoriesNewaddedwordsBinding) :
        RecyclerView.ViewHolder(binding.root)
}