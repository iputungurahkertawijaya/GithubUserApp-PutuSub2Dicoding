package com.example.githubuserapp_putusub2dicoding.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp_putusub2dicoding.Respons.ModelUser
import com.example.githubuserapp_putusub2dicoding.api.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    val users = MutableLiveData<ModelUser>()
    fun setUserDetail(username: String){
        Retrofit.instance
            .getDetailUser(username)
            .enqueue(object : Callback<ModelUser> {
                override fun onResponse(call: Call<ModelUser>, response: Response<ModelUser>) {
                    if (response.isSuccessful){
                        users.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ModelUser>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }

            })
    }

    fun getUserDetails(): LiveData<ModelUser> {
        return users
    }
}