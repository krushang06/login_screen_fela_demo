package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.MyFavoriteGroup.Favourite
import com.example.login_screen_test.databinding.ItemMyFavGroupsBinding

class MyFavGroupAdapter(private var MyGroup: MutableList<Favourite?>) :
    RecyclerView.Adapter<MyFavGroupAdapter.MyFavGroupViewHolder>() {
        val selecteditem: MutableList<Favourite> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setDatafavtable(wordGroupList: List<Favourite?>) {
        MyGroup.clear()
        MyGroup.addAll(wordGroupList)
        selecteditem.clear()
        notifyDataSetChanged()
    }
    fun setEditMode(isEdit: Boolean) {
        if (isEdit) {
            selecteditem.clear()
        }
    }

    class MyFavGroupViewHolder(val binding: ItemMyFavGroupsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favourite: Favourite?, selecteditem: MutableList<Favourite>) {
            favourite?.let {
                binding.newaddedwordmyfav.text = it.word.word.toString()
                binding.smallnamemyfav.text = it.word.word_translation.translation.toString()

                binding.checkboxmyfav.isChecked = selecteditem.any{it.word_id == favourite.word_id}
                binding.checkboxmyfav.setOnCheckedChangeListener { _, isChecked ->
                   val selectedItems = selecteditem
                    if (isChecked) {
                        selectedItems.add(favourite)
                    } else {
                        selectedItems.remove(favourite)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavGroupViewHolder {
        val binding = ItemMyFavGroupsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyFavGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyFavGroupViewHolder, position: Int) {
        val myGroup = MyGroup[position]
        holder.bind(myGroup, selecteditem)
    }

    internal fun getSelectedItems(): List<Favourite> {
        return selecteditem.toList()
    }

    override fun getItemCount(): Int {
        return MyGroup.size
    }

}