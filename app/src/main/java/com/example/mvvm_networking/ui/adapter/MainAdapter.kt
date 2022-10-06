package com.example.mvvm_networking.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_networking.databinding.ItemFakeBinding
import com.example.mvvm_networking.model.FakeResponse

class MainAdapter(private val listFake: List<FakeResponse>) :
RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemFakeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fakeResponse = listFake[position]

        val id = fakeResponse.id
        val userId = fakeResponse.userId
        val title = fakeResponse.title
        val body = fakeResponse.body

        Log.d("adapter", "onBindViewHolder")
        holder.binding.apply {
            tvTitle.text = title
            tvBody.text = body
            tvID.text = id.toString()
            tvUserID.text = userId.toString()
        }
    }

    override fun getItemCount() = listFake.size
}