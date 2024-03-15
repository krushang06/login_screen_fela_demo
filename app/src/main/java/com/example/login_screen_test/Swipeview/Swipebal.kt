package com.example.login_screen_test.Swipeview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login_screen_test.databinding.FragmentSwipeBinding

class Swipebal : Fragment() {

    private lateinit var binding: FragmentSwipeBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?,

        ): View {

        binding = FragmentSwipeBinding.inflate(layoutInflater)

        return binding.root

    }

}