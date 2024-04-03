package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.MyFavoriteGroup.Favourite
import com.example.login_screen_test.databinding.ItemMyFavGroupsBinding

class MyFavGroupAdapter(
    private var MyGroup: ArrayList<Favourite?>,
) :
    RecyclerView.Adapter<MyFavGroupAdapter.MyFavGroupViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setDatafavtable(wordGroupList: List<Favourite?>) {
        MyGroup.addAll(wordGroupList)
        notifyDataSetChanged()
    }

    class MyFavGroupViewHolder(val binding: ItemMyFavGroupsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavGroupViewHolder {
        val binding = ItemMyFavGroupsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyFavGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyFavGroupViewHolder, position: Int) {
        val myGroup = MyGroup[position]
        holder.binding.newaddedwordmyfav.text = myGroup?.word?.word.toString()
        holder.binding.smallnamemyfav.text = myGroup?.word?.word_translation?.translation.toString()
        val isSelected = myGroup?.word?.word_translation ?: false
        if (isSelected == true) {
            holder.binding.checkboxmyfav.isChecked = true
        } else {
            holder.binding.checkboxmyfav.isChecked = false
        }
    }

    override fun getItemCount(): Int {
        return MyGroup.size
    }

}