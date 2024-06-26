package com.example.login_screen_test.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.login_screen_test.adapters.BannerAdapter
import androidx.navigation.fragment.findNavController
import com.example.login_screen_test.R
import com.example.login_screen_test.adapters.IndicatorAdapterHome
import com.example.login_screen_test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var indicatorAdapterHome: IndicatorAdapterHome

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.fetchHomeData(requireContext())



        viewModel.session.observe(viewLifecycleOwner) { session ->
            Log.e("HomeFragment", "Session updated: ${session?.bannerImages?.size ?: 0}")
            binding.boldTV.text = session?.user?.email
            binding.normalTV.text = session?.user?.name

            if (!session?.bannerImages.isNullOrEmpty()) {
                val fragmentList = ArrayList<Fragment>()

                for (bannerImage in session!!.bannerImages) {
                    val imageUrl = bannerImage.bannerImage?.imageUrl
                    val bannerFragment = BannerFragment.newInstance(imageUrl ?: "")
                    fragmentList.add(bannerFragment)
                }

                bannerAdapter = BannerAdapter(bannerImages = session.bannerImages)
                binding.homeViewPager.adapter = bannerAdapter

                binding.homeViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                binding.homeViewPager.offscreenPageLimit = 1

                indicatorAdapterHome = IndicatorAdapterHome(session.bannerImages.size)
                binding.indicatorhomeRV.adapter = indicatorAdapterHome
            }

            binding.homeViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicatorAdapterHome.updateSelectedHome(position)
                }
            })
        }
        binding.boldTV.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_wordSets)

        }
        viewModel.fetchHomeData(requireContext())

        return binding.root
    }
}