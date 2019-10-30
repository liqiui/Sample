package com.lql.sample.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lql.sample.API
import com.lql.sample.MainActivity
import com.lql.sample.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(val context: Context?) : ViewModel() {

    var repository = (context as? MainActivity)?.repository ?: API.create()

    fun get(): LiveData<List<UserItemViewModel>> {
        val liveData = MutableLiveData<List<UserItemViewModel>>()
        repository.users().enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()?.map { UserItemViewModel(context, it) }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }
}