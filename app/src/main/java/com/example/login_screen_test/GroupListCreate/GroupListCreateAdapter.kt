package com.example.login_screen_test.GroupListCreate

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.MyFavoriteGroup.Favourite
import com.example.login_screen_test.databinding.ItemGroupListCreateBinding

class GroupListCreateAdapter(
    private var MyGroup: ArrayList<Favourite?>,
    ) : RecyclerView.Adapter<GroupListCreateAdapter.GroupListCreateViewHolder>() {
    private var selectedItems = ArrayList<Favourite>()

    @SuppressLint("NotifyDataSetChanged")
    fun setDatafavtable(wordGroupList: List<Favourite?>) {
        MyGroup.addAll(wordGroupList)
        notifyDataSetChanged()
    }

    class GroupListCreateViewHolder(val binding: ItemGroupListCreateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListCreateViewHolder {
        val binding = ItemGroupListCreateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return GroupListCreateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupListCreateViewHolder, position: Int) {
        val MyGroup = MyGroup[position]
        holder.binding.bignamegroup.text = MyGroup?.word?.word.toString()
        holder.binding.smallnamegroup.text = MyGroup?.word?.word_translation?.translation.toString()

    }

    override fun getItemCount(): Int {
        return MyGroup.size
    }

}