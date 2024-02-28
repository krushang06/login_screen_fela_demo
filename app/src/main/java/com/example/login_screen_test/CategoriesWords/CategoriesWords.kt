package com.example.login_screen_test.CategoriesWords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.login_screen_test.databinding.FragmentCategoriesWordsBinding


class CategoriesWords : Fragment() {
    private lateinit var binding: FragmentCategoriesWordsBinding
    private lateinit var categoriesWordsViewModel: CategoriesWordsViewModel

    private val args by navArgs<CategoriesWordsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesWordsBinding.inflate(inflater, container, false)

        categoriesWordsViewModel = ViewModelProvider(this)[CategoriesWordsViewModel::class.java]
//        getCompletedTaskById()

//        categoriesWordsViewModel.getCompletedTaskById(id)

        return binding.root
    }

//    private fun getCompletedTaskById(){
//        categoriesWordsViewModel.getCompletedTaskById(id = args.imageId)
//    }

}