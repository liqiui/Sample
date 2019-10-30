package com.lql.sample.viewModel

import android.content.Context
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.lql.sample.MainActivity
import com.lql.sample.R
import com.lql.sample.data.User
import com.lql.sample.ui.PhotoFragment

class UserItemViewModel(val context: Context?, val user: User) : ViewModel() {

    fun onClick(viewModel: UserItemViewModel) {
        (context as MainActivity).run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, PhotoFragment(viewModel.user))
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}