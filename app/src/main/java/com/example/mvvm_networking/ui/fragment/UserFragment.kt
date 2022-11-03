package com.example.mvvm_networking.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvm_networking.R
import com.example.mvvm_networking.databinding.FragmentUserBinding
import com.example.mvvm_networking.model.UserResponse
import com.example.mvvm_networking.ui.adapter.UserAdapter
import com.example.mvvm_networking.ui.viewmmodel.UserViewModel
import com.example.mvvm_networking.utils.Status
import com.example.mvvm_networking.utils.errorToast

class UserFragment : Fragment(R.layout.fragment_user) {


    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var userViewModel: UserViewModel
//    private val homeViewModel: HomeViewModel by viewModel()
//    private val userDao = UserDatabase.getDatabase(requireActivity()).getUserDao()
//    private val userRepository = UserRepository(userDao)

    //    private val userViewModel = ViewModelProvider(this,UserViewModelFactory(userRepository)).get(UserViewModel::class.java)
//    private val userViewModel: UserViewModel by viewModels {
//        UserViewModelFactory(userRepository)
//    }

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private var userAdapter = UserAdapter()
    private var list = ArrayList<UserResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupUser()
//        buttonCategoryAll()
    }

    private fun setupRecycler() {
//        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: UserResponse) {
//                findNavController().navigate(HomeDirections.actionHome2ToBuyerDetailProduk(data.id))
//            }
//        })
        recyclerView = binding.rvUser
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = userAdapter
        binding.progressBar.visibility = View.GONE
    }

//    private fun setupUser() {
//        userViewModel.getUser().observe(viewLifecycleOwner) {
//            Log.e("UserFragment", "UserFragment")
//            it.forEach { user ->
//                userViewModel.insertUser(user)
//
//            }
//        }
//    }


    private fun setupUser() {

        userViewModel.getUserOfflineAll()
        userViewModel.gettProductOffline.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { listProduct ->
                        list.clear()
                        userAdapter.submitData(listProduct.take(40))
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast(requireContext()).errorToast(
                        it.message.toString(),
                        requireContext()
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}