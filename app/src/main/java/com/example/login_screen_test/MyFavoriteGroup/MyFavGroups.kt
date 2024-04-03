package com.example.login_screen_test.MyFavoriteGroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView

import com.example.login_screen_test.adapters.MyFavGroupAdapter
import com.example.login_screen_test.databinding.FragmentMyFavGroupsBinding


class MyFavGroups : Fragment() {
    private lateinit var binding: FragmentMyFavGroupsBinding
    private lateinit var myFavGroupViewModel: MyFavGroupViewModel
    private lateinit var myFavGroupAdapter: MyFavGroupAdapter
    private lateinit var recyclerView: RecyclerView

    private val args by navArgs<MyFavGroupsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyFavGroupsBinding.inflate(inflater, container, false)
        myFavGroupViewModel = ViewModelProvider(this).get(MyFavGroupViewModel::class.java)
        myFavGroupViewModel.fetchmyfavgroup(requireContext(),args.enteredText)
        binding.backbuttonmyfav.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.progressBarMyFav.visibility = View.VISIBLE

        recyclerView = binding.myfavRV
        myFavGroupAdapter = MyFavGroupAdapter(ArrayList())
        binding.myfavRV.adapter = myFavGroupAdapter

        binding.groupnameadd.text = args.enteredText  // args name pass send to recive text name
        binding.creatgroups.setOnClickListener {
            findNavController().navigate(MyFavGroupsDirections.actionMyFavGroupsToGroupListCreate(toString()))
        }
        observemodel()
        return binding.root
    }
    private fun observemodel() {
        myFavGroupViewModel.myfavgroupWordss.observe(this) { fwg ->
            fwg?.let {
                myFavGroupAdapter.setDatafavtable(fwg)
                binding.myfavRV.adapter = myFavGroupAdapter
                binding.progressBarMyFav.visibility = View.GONE

            }
        }
    }

}