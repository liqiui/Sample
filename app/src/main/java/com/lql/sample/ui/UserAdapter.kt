package com.lql.sample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lql.sample.R
import com.lql.sample.databinding.UserItemBinding
import com.lql.sample.viewModel.UserItemViewModel
import com.lql.sample.viewModel.UserViewModel

class UserAdapter(val viewModel: UserViewModel)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var viewModels: MutableList<UserItemViewModel> = arrayListOf()

    constructor(viewModel: UserViewModel, viewModels: List<UserItemViewModel>) : this(viewModel) {
        this.viewModels = viewModels as MutableList<UserItemViewModel>
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModels.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : UserItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModels.size

    inner class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: UserItemViewModel) {
            binding.viewModel = viewModel
        }
    }
}