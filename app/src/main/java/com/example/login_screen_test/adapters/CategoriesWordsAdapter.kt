package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.CategoriesWords.CategoriesWords
import com.example.login_screen_test.CategoriesWords.CategoriesWordsDirections
import com.example.login_screen_test.CategoriesWords.CategoryWords
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.ItemCategoriesNewaddedwordsBinding
import com.example.login_screen_test.databinding.ItemSlaidBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class CategoriesWordsAdapter(
    var categoryWords: ArrayList<CategoryWords>,
    private val listener: CategoriesWords,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NORMAL = 0
    private val VIEW_TYPE_SWIPEABLE = 1

    @SuppressLint("NotifyDataSetChanged")
    fun setData(categoryWordsList: List<CategoryWords?>) {
        categoryWordsList.forEach { it?.let { categoryWords.add(it) } }
        notifyDataSetChanged()
    }

    interface CategoryWordsListener {
        fun onFavoriteClicked(categoryWord: CategoryWords)
        fun onCopyClicked(categoryWord: CategoryWords)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ItemCategoriesNewaddedwordsBinding.inflate(inflater, parent, false)
                CategoryWordsViewHolder(binding)
            }
            VIEW_TYPE_SWIPEABLE -> {
                val binding = ItemSlaidBinding.inflate(inflater, parent, false)
                SwipeableCategoryWordsViewHolder(binding)
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryWord = categoryWords[position]
        when (holder) {
            is CategoryWordsViewHolder -> {
                holder.bind(categoryWord)
            }
            is SwipeableCategoryWordsViewHolder -> {
                holder.bind(categoryWord)
            }
        }
    }
    override fun getItemCount(): Int {
        return categoryWords.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == categoryWords.size - 1) {
            VIEW_TYPE_SWIPEABLE
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    inner class CategoryWordsViewHolder(private val binding: ItemCategoriesNewaddedwordsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val categoryWord = categoryWords[position]

                }
            }
        }

        fun bind(categoryWord: CategoryWords) {
            binding.apply {
                newaddedword.text = categoryWord.word
                smallname.text = categoryWord.wordTranslation?.translation?.toString()
            }
        }
    }

    inner class SwipeableCategoryWordsViewHolder(private val binding: ItemSlaidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val categoryWord = categoryWords[position]
//                    val action = CategoriesWordsDirections.actionCategoriesWordsToSwipe()
//                    binding.root.findNavController().navigate(action)
                }
            }
        }

        fun bind(categoryWord: CategoryWords) {
            binding.apply {
                newaddedword2.text = categoryWord.word
                smallname2.text = categoryWord.wordTranslation?.translation?.toString()
            }
        }
    }

    var itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val categoryWord = categoryWords[position]
            if (direction == ItemTouchHelper.RIGHT) {

            }
            else if (direction == ItemTouchHelper.LEFT) {

            }
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            RecyclerViewSwipeDecorator.Builder(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
//                .addBackgroundColor(
//                    ContextCompat.getColor(
//                        recyclerView.context,
//                        R.color.purple
//                    )
//                )
                .addActionIcon(R.layout.item_slaid)
                .create()
                .decorate()

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}