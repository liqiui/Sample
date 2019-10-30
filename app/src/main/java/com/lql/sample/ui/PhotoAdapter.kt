package com.lql.sample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lql.sample.R
import com.lql.sample.databinding.PhotoItemBinding
import com.lql.sample.viewModel.PhotoItemViewModel
import com.lql.sample.viewModel.PhotoViewModel
import com.squareup.picasso.Picasso

class PhotoAdapter(val context: Context, val viewModel: PhotoViewModel): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private var viewModels: MutableList<PhotoItemViewModel> = arrayListOf()

    constructor(context: Context, viewModel: PhotoViewModel, viewModels: List<PhotoItemViewModel>) : this(context, viewModel) {
        this.viewModels = viewModels as MutableList<PhotoItemViewModel>
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding : PhotoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = viewModels.get(position)
        holder.bind(context, photo)
    }

    override fun getItemCount(): Int = viewModels.size

    inner class ViewHolder(val dataBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(context: Context, viewModel: PhotoItemViewModel) {
            dataBinding.viewModel = viewModel
            Picasso
                .with(context)
                .load(viewModel.photo.thumbnailUrl)
                .fit()
                .into(dataBinding.image)
        }
    }
}
