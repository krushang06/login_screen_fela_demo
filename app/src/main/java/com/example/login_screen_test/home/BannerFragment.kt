package com.example.login_screen_test.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.login_screen_test.R
import com.example.login_screen_test.databinding.FragmentBannerBinding

class BannerFragment : Fragment() {
    private var imageUrl: String? = null

    private lateinit var binding:FragmentBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(IMG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_banner, container, false)

        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onResume() {
        super.onResume()
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.bannerImage)
    }

    companion object {

        private const val IMG_URL = "img_url"

        @JvmStatic
        fun newInstance(imageUrl: String) =
            BannerFragment().apply {
                arguments = Bundle().apply {
                    putString(IMG_URL, imageUrl)
                }
            }
    }
}