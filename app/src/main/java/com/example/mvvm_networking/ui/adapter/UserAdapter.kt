package com.example.mvvm_networking.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mvvm_networking.databinding.ItemUserBinding
import com.example.mvvm_networking.model.UserResponse

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var list = ArrayList<UserResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponse)
    }
    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<UserResponse>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //name username phone email
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productResponse = list[position]
        val name = productResponse.name
        val username = productResponse.username
        val phone = productResponse.phone
        val email = productResponse.email


        holder.binding.apply {
            tvName.text = name
            tvEmail.text = email
            tvPhone.text = phone
            tvUN.text = username


//            ivCardView.setOnClickListener {
//                onItemClickCallback.onItemClicked(productResponse)
//            }

        }
    }

    override fun getItemCount() = list.size

}
