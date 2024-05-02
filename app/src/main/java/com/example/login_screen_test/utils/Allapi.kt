package com.example.login_screen_test.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login_screen_test.databinding.FragmentAllapiBinding

class Allapi : Fragment() {

    private lateinit var binding: FragmentAllapiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllapiBinding.inflate(inflater, container, false)
        binding.textshow.text = "All Api"

        return binding.root
    }

}
