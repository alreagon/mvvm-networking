package com.example.mvvm_networking.ui.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvm_networking.R
import com.example.mvvm_networking.api.ApiConfig
import com.example.mvvm_networking.api.ApiConfig2
import com.example.mvvm_networking.api.FakeAPI
import com.example.mvvm_networking.databinding.FragmentMainBinding
import com.example.mvvm_networking.databinding.FragmentUserBinding
import com.example.mvvm_networking.local.UserRepository
import com.example.mvvm_networking.model.UserResponse
import com.example.mvvm_networking.ui.adapter.UserAdapter
import com.example.mvvm_networking.ui.viewmmodel.MainViewModel
import com.example.mvvm_networking.ui.viewmmodel.UserViewModel
import com.example.mvvm_networking.utils.Status
import com.example.mvvm_networking.utils.errorToast
import retrofit2.create

class UserFragment : Fragment(R.layout.fragment_user) {


    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
//    private lateinit var userViewModel: UserViewModel
//    private val homeViewModel: HomeViewModel by viewModel()
    private val userViewModel by viewModels<UserViewModel>()
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
        buttonCategoryAll()
//        userRepository = ViewModelProvider(this,UserViewModelFactory(userRepository)).get(UserViewModel::class.java)
    }

    private fun setupRecycler() {
//        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: UserResponse) {
//                findNavController().navigate(HomeDirections.actionHome2ToBuyerDetailProduk(data.id))
//            }
//        })
        recyclerView = binding.rvUser
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = userAdapter
        binding.progressBar.visibility = View.GONE
    }


    private fun buttonCategoryAll() {

        userViewModel.getProductOfflineAll()
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


}