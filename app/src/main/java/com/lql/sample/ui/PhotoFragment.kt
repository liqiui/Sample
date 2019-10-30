package com.lql.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lql.sample.MainActivity
import com.lql.sample.R
import com.lql.sample.data.User
import com.lql.sample.databinding.FragmentPhotoBinding
import com.lql.sample.viewModel.PhotoViewModel
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment(val user: User): Fragment() {
    private lateinit var viewModel: PhotoViewModel
    private lateinit var dataBinding: FragmentPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = PhotoViewModel(activity as MainActivity, user)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = viewModel
        list?.run {
            viewModel.get().observe(viewLifecycleOwner, Observer {
                adapter = PhotoAdapter(context, viewModel, it)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        return dataBinding.root
    }
}