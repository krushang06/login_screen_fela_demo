package com.example.login_screen_test.FavoriteWordGroups

import android.R
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.adapters.WordGroupAdapter
import com.example.login_screen_test.databinding.CustomDialogLayoutBinding
import com.example.login_screen_test.databinding.FragmentWordGroupBinding


class WordGroup : Fragment() {
    private lateinit var binding: FragmentWordGroupBinding
    private lateinit var bindingcdl: CustomDialogLayoutBinding
    private lateinit var wordGroupViewModel: WordGroupViewModel
    private lateinit var wordGroupAdapter: WordGroupAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWordGroupBinding.inflate(inflater, container, false)
        bindingcdl = CustomDialogLayoutBinding.inflate(inflater, container, false)

        wordGroupViewModel = ViewModelProvider(this).get(WordGroupViewModel::class.java)
        wordGroupViewModel.fachwordsets(requireContext())
        binding.backbuttonfavorite.setOnClickListener {
            findNavController().popBackStack()
        }



        val progressBar: ProgressBar = binding.progressBar
        val animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        animation.setDuration(5000) // 5000ms = 5s
        binding.progressBar.visibility = View.VISIBLE

        animation.start()

        recyclerView = binding.wordgrouprecyclerview
        wordGroupAdapter = WordGroupAdapter(ArrayList())
        binding.wordgrouprecyclerview.adapter = wordGroupAdapter

        val itemTouchHelper = ItemTouchHelper(wordGroupAdapter.itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.wordgrouprecyclerview)

        binding.include.root.visibility = View.GONE
        binding.createanewgroup.setOnClickListener {
            binding.include.root.visibility = View.VISIBLE
        }
        binding.include.clopopupbg.setOnClickListener {
            binding.include.root.visibility = View.GONE
        }

        binding.include.selectwordid.setOnClickListener {
            val name = binding.include.editTextid.text.toString().trim()
            val action = WordGroupDirections.actionWordGroupToMyFavGroups(enteredText = name)
            findNavController().navigate(action)
        }

        observemodel()

        return binding.root
    }

    private fun observemodel() {
        wordGroupViewModel.favwordgroup.observe(this) { favwordgroup ->
            favwordgroup?.let {
                wordGroupAdapter.setDatafavtable(favwordgroup)
                binding.wordgrouprecyclerview.adapter = wordGroupAdapter
                binding.progressBar.visibility = View.GONE
            }
        }
    }


}

