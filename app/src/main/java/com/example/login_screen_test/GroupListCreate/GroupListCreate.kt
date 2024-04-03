package com.example.login_screen_test.GroupListCreate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.login_screen_test.MyFavoriteGroup.MyFavGroupViewModel
import com.example.login_screen_test.MyFavoriteGroup.MyFavGroupsArgs
import com.example.login_screen_test.R
import com.example.login_screen_test.adapters.MyFavGroupAdapter
import com.example.login_screen_test.databinding.FragmentGroupListCreateBinding

class GroupListCreate : Fragment() {
    private lateinit var binding: FragmentGroupListCreateBinding
    private lateinit var grouplistcreateviewmodel: GroupListCreateViewModel
    private lateinit var grouplistcreateAdapter: GroupListCreateAdapter
    private lateinit var recyclerView: RecyclerView

    private val args by navArgs<GroupListCreateArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGroupListCreateBinding.inflate(inflater, container, false)
        grouplistcreateviewmodel = ViewModelProvider(this).get(GroupListCreateViewModel::class.java)

        binding.backbuttongroup.setOnClickListener {
            findNavController().popBackStack()
        }
        recyclerView = binding.grouplistRV
        grouplistcreateAdapter = GroupListCreateAdapter(ArrayList())
        binding.grouplistRV.adapter = grouplistcreateAdapter

        binding.mygroup.text = args.enteredText2



        return binding.root
    }

}