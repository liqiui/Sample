package com.lql.sample.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lql.sample.API
import com.lql.sample.MainActivity
import com.lql.sample.data.Photo
import com.lql.sample.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel(val context: Context?, val user: User?) : ViewModel(){

    var repository = (context as? MainActivity)?.repository ?: API.create()

    fun get(): LiveData<List<PhotoItemViewModel>> {
        val liveData = MutableLiveData<List<PhotoItemViewModel>>()
        repository.photos(user?.id).enqueue(object: Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()?.map { PhotoItemViewModel(context, it) }
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }
}