package com.example.login_screen_test.adapters

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.CategoriesWords.CategoryWords
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.ItemCategoriesNewaddedwordsBinding
import com.example.login_screen_test.databinding.ItemSlaidBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@SuppressLint("ClickableViewAccessibility")
class CategoriesWordsAdapter(
    var categoryWords: ArrayList<CategoryWords>,
    private val recyclerView: RecyclerView,
    private val onfavroiteclicklistener: OnFavoriteClickListener,
    private val onknowitclicklistener: OnKnowItClickListener,
    private var isEdit: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_NORMAL = 0
    private val VIEW_TYPE_SWIPEABLE = 1
    private var menuPos = -1
    private var filteredList: ArrayList<CategoryWords> = ArrayList()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(categoryWordsList: List<CategoryWords?>) {
        categoryWordsList.forEach { it?.let { categoryWords.add(it) } }
        notifyDataSetChanged()
        filteredList.addAll(categoryWords)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filter(filteredWords: List<CategoryWords>) {
        categoryWords.clear()
        categoryWords.addAll(filteredWords)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setfavData(favlist: String) {
        favlist.forEach { it.let { categoryWords } }
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setEditMode(enabled: Boolean) {
        isEdit = enabled
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleFavoriteAction(categoryWord: CategoryWords) {
        categoryWord.isFavourite
        onfavroiteclicklistener.onFavoriteClicked(categoryWord)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun handleKnowitAction(categoryWord: CategoryWords) {
        categoryWord.isKnowIt == true
        onknowitclicklistener.onKnowItClicked(categoryWord)
        val position = categoryWords.indexOf(categoryWord)
        categoryWords.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()

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
        return if (position == menuPos) {
            VIEW_TYPE_SWIPEABLE
        } else {
            VIEW_TYPE_NORMAL
        }
    }


    inner class CategoryWordsViewHolder(private val binding: ItemCategoriesNewaddedwordsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val categoryWord = categoryWords[position]
                    notifyItemChanged(position)
                }
            }
        }

        fun bind(categoryWord: CategoryWords) {
            binding.apply {
                newaddedword.text = categoryWord.word
                smallname.text = categoryWord.wordTranslation?.translation?.toString()

                smallfavimg.visibility = if (categoryWord.isFavourite == true)
                    View.VISIBLE else View.GONE
                imageView99.visibility = if (categoryWord.isFavourite == true)
                    View.VISIBLE else View.GONE



            }
        }
    }

    inner class SwipeableCategoryWordsViewHolder(private val binding: ItemSlaidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryWord: CategoryWords) {
            binding.apply {
                newaddedword2.text = categoryWord.word
                smallname2.text = categoryWord.wordTranslation?.translation?.toString()
                textView8.text = if (categoryWord.isFavourite == true) "Unfavorites" else "Favorites"

                Favoriteimage.setImageResource(if (categoryWord.isFavourite == true)(R.drawable.unfavorite)else R.drawable.mi_favorite)

                Favoriteimage.setOnClickListener {handleFavoriteAction(categoryWord)
                    closeOpenMenu()
                }

                newaddedword2.setOnClickListener {
                    closeOpenMenu()
                }

                knowitbg.setOnClickListener { handleKnowitAction(categoryWord)
                    closeOpenMenu()
                }
            }
        }

        private fun closeOpenMenu() {
            if (menuPos != -1) { menuPos = -1
                notifyItemChanged(menuPos)
            }
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

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val categoryWord = categoryWords[position]
                if (direction == ItemTouchHelper.RIGHT) {
                    menuPos = position
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
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
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

    interface OnFavoriteClickListener {
        fun onFavoriteClicked(categoryWord: CategoryWords)
    }
    interface OnKnowItClickListener {
        fun onKnowItClicked(categoryWord: CategoryWords)
    }

}