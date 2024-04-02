package com.example.login_screen_test.CategoriesWords

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.R
import com.example.login_screen_test.utils.WordListTypes
import com.example.login_screen_test.adapters.CategoriesWordsAdapter
import com.example.login_screen_test.databinding.FragmentCategoriesWordsBinding


class CategoriesWords : Fragment() {
    private lateinit var binding: FragmentCategoriesWordsBinding
    private lateinit var categoriesWordsViewModel: CategoriesWordsViewModel
    private lateinit var categorieswordsadapter: CategoriesWordsAdapter
    private lateinit var recyclerView: RecyclerView
    private var allWordList = ArrayList<CategoryWords>()
    private var isEdit: Boolean = false
    private var searchText = ""
    private var type = WordListTypes.Learning
    val selectedBackground = R.drawable.selectedbg
    val unselectedBackground = R.drawable.unselectedbg

    private val args by navArgs<CategoriesWordsArgs>()

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        view?.let { super.onViewCreated(it, savedInstanceState) }

        binding = FragmentCategoriesWordsBinding.inflate(inflater, container, false)

        categoriesWordsViewModel = ViewModelProvider(this)[CategoriesWordsViewModel::class.java]

        binding.backbutton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.progressBaraddedwords.visibility = View.VISIBLE
        binding.knowitwordsimg.setOnClickListener {
            type = WordListTypes.KnowIt
            isEdit = !isEdit
            categorieswordsadapter.setEditMode(isEdit)
            handleKnowItWordsImgClick()
            binding.knowitwordsimg.setBackgroundResource(selectedBackground)
            binding.learnitwordsimg.setBackgroundResource(unselectedBackground)
            binding.allwordsimg.setBackgroundResource(unselectedBackground)
        }
        binding.learnitwordsimg.setOnClickListener {
            type = WordListTypes.Learning
            isEdit = !isEdit
            categorieswordsadapter.setEditMode(isEdit)
            handlelearnItWordsImgClick()
            binding.learnitwordsimg.setBackgroundResource(selectedBackground)
            binding.knowitwordsimg.setBackgroundResource(unselectedBackground)
            binding.allwordsimg.setBackgroundResource(unselectedBackground)

        }
        binding.allwordsimg.setOnClickListener {
            type = WordListTypes.All   // sarching sort mate enum class
            isEdit = !isEdit
            categorieswordsadapter.setEditMode(isEdit)
            handleAllWordsImgClick()
            binding.allwordsimg.setBackgroundResource(selectedBackground)
            binding.knowitwordsimg.setBackgroundResource(unselectedBackground)
            binding.learnitwordsimg.setBackgroundResource(unselectedBackground)

        }
        binding.searchView.ETAutoComplete.visibility = View.GONE
        binding.searchView.cancelsmallimg.visibility = View.GONE

        binding.searchView.cancelsmallimg.setOnClickListener {  // cancel btn close
            binding.searchView.ETAutoComplete.visibility = View.GONE
            binding.searchView.cancelsmallimg.visibility = View.GONE

            // If the keyboard is open, hide it
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            if (imm?.isActive == true) {
                imm.hideSoftInputFromWindow(binding.searchView.ETAutoComplete.windowToken, 0)
            }

        }
        binding.searchwordimg.setOnClickListener {
            binding.searchView.ETAutoComplete.visibility = View.VISIBLE
            binding.searchView.cancelsmallimg.visibility = View.VISIBLE
        }
        binding.run {
            searchView.ETAutoComplete.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchText = p0.toString()
                    searchIncompleteTask(searchText)
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

        recyclerView = binding.ctgwordsRV
        categorieswordsadapter = CategoriesWordsAdapter(
            ArrayList(),
            recyclerView,
            onfavroiteclicklistener, onKnowItClickListener
        )
        binding.ctgwordsRV.adapter = categorieswordsadapter

        val itemTouchHelper = ItemTouchHelper(categorieswordsadapter.itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.ctgwordsRV)

        categoriesWordsViewModel.fetchCategories(requireContext(), id = args.imageId)

        observeCategoriesWords()

        observeFavoriteWords()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchIncompleteTask(s: String) {
        val filteredWords = allWordList.filter { it.word?.contains(s, true) == true }
        when (type) {
            WordListTypes.KnowIt -> {
                val knowItItems = filteredWords.filter { it.isKnowIt == true }
                categorieswordsadapter.categoryWords.clear()
                categorieswordsadapter.categoryWords.addAll(knowItItems)
            }

            WordListTypes.All -> {
                categorieswordsadapter.categoryWords.clear()
                categorieswordsadapter.categoryWords.addAll(filteredWords)
            }

            WordListTypes.Learning -> {
                val learnItItems = filteredWords.filter { it.isKnowIt == false }
                categorieswordsadapter.categoryWords.clear()
                categorieswordsadapter.categoryWords.addAll(learnItItems)
            }
        }
        categorieswordsadapter.notifyDataSetChanged()
    }

    private fun observeCategoriesWords() {
        categoriesWordsViewModel.CategoriesWord.observe(this) { CategoriesWords ->
            CategoriesWords?.let {
                binding.learnitwordsimg.setBackgroundResource(selectedBackground)
                categorieswordsadapter.setData(CategoriesWords)
                allWordList.addAll(CategoriesWords as ArrayList<CategoryWords>)
                binding.progressBaraddedwords.visibility = View.GONE
            }
        }
    }

    private fun observeFavoriteWords() {
        categoriesWordsViewModel.favoriteWord.observe(viewLifecycleOwner) { isFavorite ->
            isFavorite?.let {
                categorieswordsadapter.setfavData(toString())
            }
        }
    }

    val onfavroiteclicklistener by lazy {
        object : CategoriesWordsAdapter.OnFavoriteClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onFavoriteClicked(categoryWord: CategoryWords) {
                val favoriteData =
                    categoryWord.word?.let { FavoriteRequest(word = it, faker = " ") }
                if (favoriteData != null) {
                    categoriesWordsViewModel.fetchFavourite(favoriteData, requireContext())
                }
                categoryWord.isFavourite
                if (categoryWord.isFavourite == false) {
                    categoryWord.isFavourite = true
                    categorieswordsadapter.notifyDataSetChanged()
                } else if (categoryWord.isFavourite == true) {
                    categoryWord.isFavourite = false
                    categorieswordsadapter.notifyDataSetChanged()
                }
            }
        }
    }
    val onKnowItClickListener by lazy {
        object : CategoriesWordsAdapter.OnKnowItClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onKnowItClicked(categoryWord: CategoryWords) {
                categoryWord.isKnowIt?.let { isKnowIt ->
                    categoryWord.isKnowIt = !isKnowIt
                    allWordList.find { categoryWord.word == it.word }?.isKnowIt = !isKnowIt
                    if (isKnowIt == false) {
                        Toast.makeText(requireContext(), "Know It Done", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Not Know It", Toast.LENGTH_SHORT).show()
                    }
                    categorieswordsadapter.notifyDataSetChanged()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleKnowItWordsImgClick() {
        val knowItItems = allWordList.filter { it.isKnowIt == true }
        categorieswordsadapter.categoryWords.clear()
        categorieswordsadapter.categoryWords.addAll(knowItItems)
        categorieswordsadapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handlelearnItWordsImgClick() {
        val learnItItems = allWordList.filter { it.isKnowIt == false }
        categorieswordsadapter.categoryWords.clear()
        categorieswordsadapter.categoryWords.addAll(learnItItems)
        categorieswordsadapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleAllWordsImgClick() {
        categorieswordsadapter.categoryWords.clear()
        categorieswordsadapter.categoryWords.addAll(allWordList)
        categorieswordsadapter.notifyDataSetChanged()
    }
}


