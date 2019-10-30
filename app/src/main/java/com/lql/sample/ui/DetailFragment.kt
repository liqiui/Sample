package com.lql.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lql.sample.MainActivity
import com.lql.sample.R
import com.lql.sample.data.Photo
import com.lql.sample.databinding.FragmentDetailBinding
import com.lql.sample.viewModel.PhotoItemViewModel
import com.squareup.picasso.Picasso

class DetailFragment (val photo: Photo) : Fragment() {

    private lateinit var viewModel: PhotoItemViewModel
    private lateinit var dataBinding : FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).run {
            viewModel = PhotoItemViewModel(this, photo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso
            .with(context)
            .load(viewModel.photo.url)
            .fit()
            .into(dataBinding.image)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }
}