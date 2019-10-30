package com.lql.sample.viewModel

import android.content.Context
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.lql.sample.MainActivity
import com.lql.sample.R
import com.lql.sample.data.Photo
import com.lql.sample.ui.DetailFragment

class PhotoItemViewModel(val context: Context?, val photo: Photo) : ViewModel() {

    fun onClick(viewModel: PhotoItemViewModel) {
        (context as MainActivity).run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, DetailFragment(viewModel.photo))
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}