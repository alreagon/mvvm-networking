package com.example.mvvm_networking.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_networking.R
import com.example.mvvm_networking.databinding.FragmentMainBinding
import com.example.mvvm_networking.model.FakeResponse
import com.example.mvvm_networking.ui.adapter.MainAdapter


class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.fakeResponse.observe(viewLifecycleOwner) { setFakeData(it) }

    }

    private fun setFakeData(fakeData: List<FakeResponse>) {
        binding.apply {
            val movieAdapter = MainAdapter(fakeData)
            rvFake.setHasFixedSize(true)
            rvFake.layoutManager = LinearLayoutManager(activity)
            rvFake.adapter = movieAdapter
        }
    }













}

