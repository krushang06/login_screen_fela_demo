package com.example.login_screen_test.on_boarding

import com.example.login_screen_test.adapters.ViewPagerAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.login_screen_test.adapters.IndicatorAdapter
import com.example.login_screen_test.utils.PreferencesManager
import com.example.login_screen_test.databinding.FragmentOneBinding

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("OneFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        // login hoy to jase nae to one fragment avse
        val preferencesManager = PreferencesManager(requireContext())
        val getSavedToken = preferencesManager.getSavedToken()
        if (!getSavedToken.isNullOrEmpty()) {
            findNavController().navigate(OnBoardingFragmentDirections.actionOneFragmentToHomeFragment())
        } else {
            binding.LoginBTN.setOnClickListener {
                findNavController().navigate(OnBoardingFragmentDirections.oneFragmentToLoginFragment())
            }
        }

        Log.e("OneFragment", "onCreateView")

        val fragment = listOf(
            OnBoarding1Fragment(),
            OnBoarding2Fragment(),
            OnBoarding3Fragment(),
            OnBoarding4Fragment()
        )
        val adapter = ViewPagerAdapter(fragment, childFragmentManager, lifecycle)
        binding.onBoardingViewPager.adapter = adapter

        val indicatorAdapter = IndicatorAdapter(fragment.size)
        binding.indicator.adapter = indicatorAdapter

        binding.onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorAdapter.updateSelected(position)
            }

        })
        return binding.root

    }

    override fun onPause() {
        super.onPause()
        Log.e("OneFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("OneFragment", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("OneFragment", "onDestroy")
    }

}