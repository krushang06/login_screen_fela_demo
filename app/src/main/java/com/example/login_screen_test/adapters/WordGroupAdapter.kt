package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.FavoriteWordGroups.Datat
import com.example.login_screen_test.FavoriteWordGroups.FavWordGroup
import com.example.login_screen_test.databinding.ItemSlaidSelectBinding
import com.example.login_screen_test.databinding.ItemWordGroupBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class WordGroupAdapter(
    var wordGroup: ArrayList<Datat>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var menuPoss = -1
    private val VIEW_TYPE_NORMAL_1 = 0
    private val VIEW_TYPE_SWIPEABLE_1 = 1

    @SuppressLint("NotifyDataSetChanged")
    fun setDatafavtable(wordGroupList: List<Datat?>) {
        wordGroup = wordGroupList as ArrayList<Datat>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL_1 -> {
                val binding = ItemWordGroupBinding.inflate(inflater, parent, false)
                WordGroupViewHolder(binding)
            }
            VIEW_TYPE_SWIPEABLE_1 -> {
                val binding = ItemSlaidSelectBinding.inflate(inflater, parent, false)
                SwipeableWordGroupViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wordGroup = wordGroup[position]
        when (holder) {
            is WordGroupViewHolder -> {
                holder.bind(wordGroup)
            }
            is SwipeableWordGroupViewHolder -> {
                holder.bind(wordGroup)
            }
        }
//        holder.binding.favoritegroup.text = wordGroup.name.toString()
//        holder.binding.cauntnumber.text = wordGroup.words_count.toString()

    }

    override fun getItemCount(): Int {
        return wordGroup.size
    }

    inner class WordGroupViewHolder(val binding: ItemWordGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val wordGroup = wordGroup[position]
                    notifyItemChanged(position)
                }
            }
        }

        fun bind(wordGroup: Datat) {
            binding.favoritegroup.text = wordGroup.name
            binding.cauntnumber.text = wordGroup.words_count.toString()
        }
    }

    inner class SwipeableWordGroupViewHolder(val binding: ItemSlaidSelectBinding) :
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.checkboxgp.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    closeOpenMenu()
                    menuPoss = absoluteAdapterPosition
                }
            }
        }


        fun bind(wordGroup: Datat) {
                binding.favoritegroup.text = wordGroup.name
                binding.cauntnumber.text = wordGroup.words_count.toString()
            }
        }

    private fun closeOpenMenu() {
        if (menuPoss != -1) {
            menuPoss = -1
            notifyItemChanged(menuPoss)
        }
    }

    var itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val favwordgroup = wordGroup.get(position)
                if (direction == ItemTouchHelper.RIGHT) {
                    menuPoss = position
                    notifyDataSetChanged()
                    false
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
                )

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
    }