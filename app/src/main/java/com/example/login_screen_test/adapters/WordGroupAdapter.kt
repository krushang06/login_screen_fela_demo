package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.FavoriteWordGroups.Datat
import com.example.login_screen_test.FavoriteWordGroups.FavWordGroup
import com.example.login_screen_test.databinding.ItemWordGroupBinding

class WordGroupAdapter(
    private var wordGroup: ArrayList<Datat>,
) : RecyclerView.Adapter<WordGroupAdapter.WordGroupViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setDatafavtable(wordGroupList: List<Datat?>) {
        wordGroup = wordGroupList as ArrayList<Datat>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordGroupViewHolder {
        val binding = ItemWordGroupBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return WordGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordGroupViewHolder, position: Int) {
        val wordGroup = wordGroup[position]

        holder.binding.favoritegroup.text = wordGroup.name.toString()
        holder.binding.cauntnumber.text = wordGroup.words_count.toString()

    }

    override fun getItemCount(): Int {
        return wordGroup.size
    }

    class WordGroupViewHolder(val binding: ItemWordGroupBinding) :
        RecyclerView.ViewHolder(binding.root)
}
