package com.example.login_screen_test.WordSets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.R
import com.example.login_screen_test.adapters.WordSetsAdapter
import com.example.login_screen_test.databinding.FragmentWordSetsBinding

class WordSets : Fragment() {
    private lateinit var binding: FragmentWordSetsBinding
    private lateinit var wordSetsViewModel: WordSetsViewModel
    private lateinit var wordSetsAdapter: WordSetsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        view?.let { super.onViewCreated(it, savedInstanceState) }

        binding = FragmentWordSetsBinding.inflate(inflater, container, false)
        wordSetsViewModel = ViewModelProvider(this)[WordSetsViewModel::class.java]

        binding.backbuttonwordset.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.groupadd.setOnClickListener {
            findNavController().navigate(WordSetsDirections.actionWordSetsToWordGroup())
        }
        binding.mostcommonly.setOnClickListener {
            val action = WordSetsDirections.actionWordSetsToAllapi()
            findNavController().navigate(action)
        }
        binding.progressBar.visibility = View.VISIBLE

        recyclerView = binding.circleRecyclerview
        wordSetsAdapter = WordSetsAdapter(ArrayList(), onCategoryImageClickListener)
        binding.circleRecyclerview.adapter = wordSetsAdapter

        wordSetsViewModel.fachwordsets(requireContext())
        observemodel()
        return binding.root
    }

    private fun observemodel() {
        wordSetsViewModel.categoryimage.observe(this) { categoryImages ->
            categoryImages?.let {
                wordSetsAdapter.setData(categoryImages)
                binding.circleRecyclerview.adapter = wordSetsAdapter
                binding.progressBar.visibility = View.GONE // Hide
            }
        }
    }

    private val onCategoryImageClickListener by lazy {
        object : WordSetsAdapter.OnUniversityClickListener {
            override fun onCategoryimageClicked(dataword: Dataword) {
                dataword.id?.let { id ->
                    val action = WordSetsDirections.actionWordSetsToCategoriesWords(dataword.id!!)
                    findNavController().navigate(action)
                }
            }
        }
    }
}