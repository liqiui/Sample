package com.lql.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lql.sample.MainActivity
import com.lql.sample.R
import com.lql.sample.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = UserViewModel(activity as MainActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.list?.run {
            viewModel.get().observe(viewLifecycleOwner, Observer {
                adapter = UserAdapter(viewModel, it)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }
}