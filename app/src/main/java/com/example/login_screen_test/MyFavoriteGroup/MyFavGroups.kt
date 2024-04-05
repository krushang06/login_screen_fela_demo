package com.example.login_screen_test.MyFavoriteGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.GroupListCreate.GLCRequest
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

        myFavGroupViewModel.fetchmyfavgroup(requireContext())


        binding.backbuttonmyfav.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.progressBarMyFav.visibility = View.VISIBLE

        recyclerView = binding.myfavRV
        myFavGroupAdapter = MyFavGroupAdapter(ArrayList())
        binding.myfavRV.adapter = myFavGroupAdapter

        binding.groupnameadd.text = args.enteredText  // args name pass send to recive text name

        binding.creatgroups.setOnClickListener {
            val selectedFavourites = myFavGroupAdapter.getSelectedItems() // adepter mathi data pass

            myFavGroupViewModel.myfavgroupWordss.value = ArrayList(selectedFavourites)  // user data pass in array

            val selectedWordIds: List<Int> = selectedFavourites.map { it.word_id }  // adepter mathi id pass krava kai id uprna all data lay java che

            myFavGroupViewModel.fetchcreategroup(GLCRequest(word_ids = selectedWordIds, name = args.enteredText), requireContext())   // api call function with request clas in mention that

            observemodelgroup()
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

    private fun observemodelgroup() {
        myFavGroupViewModel.createlistgroup.observe(this) { fwg2 ->
            if (fwg2 == true) {
                findNavController().popBackStack()
                binding.progressBarMyFav.visibility = View.GONE
            }
        }
    }
}