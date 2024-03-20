package com.example.login_screen_test.CategoriesWords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.adapters.CategoriesWordsAdapter
import com.example.login_screen_test.databinding.FragmentCategoriesWordsBinding


class CategoriesWords : Fragment() {
    private lateinit var binding: FragmentCategoriesWordsBinding
    private lateinit var categoriesWordsViewModel: CategoriesWordsViewModel
//    private lateinit var favoriteWordViewModel: FavoriteWordViewModel
    private lateinit var categorieswordsadapter: CategoriesWordsAdapter
    private lateinit var recyclerView: RecyclerView

    private val args by navArgs<CategoriesWordsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view?.let { super.onViewCreated(it, savedInstanceState) }

        binding = FragmentCategoriesWordsBinding.inflate(inflater, container, false)

        categoriesWordsViewModel = ViewModelProvider(this)[CategoriesWordsViewModel::class.java]
//        favoriteWordViewModel = ViewModelProvider(this)[FavoriteWordViewModel::class.java]

        binding.backbutton.setOnClickListener {
            findNavController().popBackStack()
        }

        recyclerView = binding.ctgwordsRV

        categorieswordsadapter = CategoriesWordsAdapter(
            ArrayList(),
            recyclerView,
            onfavroiteclicklistener
        )
        binding.ctgwordsRV.adapter = categorieswordsadapter

        val itemTouchHelper = ItemTouchHelper(categorieswordsadapter.itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.ctgwordsRV)

        categoriesWordsViewModel.fetchCategories(requireContext(),id = args.imageId)


        observeCategoriesWords()

        observeFavoriteWords()

        return binding.root
    }

        private fun observeCategoriesWords() {
        categoriesWordsViewModel.CategoriesWord.observe(this)  { CategoriesWords ->
            CategoriesWords?.let {
                categorieswordsadapter.setData(CategoriesWords)
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

            override fun onFavoriteClicked(categoryWord: CategoryWords) {
                val favoriteData = categoryWord.word?.let { FavoriteRequest(word = it, faker = " ") }
                if (favoriteData != null) {
                    categoriesWordsViewModel.fetchFavourite(favoriteData, requireContext())
                }

                categoryWord.isFavourite
                if (categoryWord.isFavourite == false) {
//                    Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
                    categoryWord.isFavourite = true
                    categorieswordsadapter.notifyDataSetChanged()
                } else if (categoryWord.isFavourite == true) {
//                    Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
                    categoryWord.isFavourite = false
                    categorieswordsadapter.notifyDataSetChanged()
                }
            }

            override fun onCopyClicked(categoryWord: CategoryWords) {
                TODO("Not yet implemented")
            }
        }
    }
}


