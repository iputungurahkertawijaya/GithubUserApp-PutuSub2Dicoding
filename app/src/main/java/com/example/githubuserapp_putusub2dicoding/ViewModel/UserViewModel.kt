package com.example.githubuserapp_putusub2dicoding.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp_putusub2dicoding.Respons.ModelUser
import com.example.githubuserapp_putusub2dicoding.Respons.UserRespon
import com.example.githubuserapp_putusub2dicoding.api.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    val listUsers = MutableLiveData<ArrayList<ModelUser>>()

    fun setSearchUser(query: String){
        Retrofit.instance.getSearchUser(query).enqueue(object : Callback<UserRespon> {
            override fun onResponse(call: Call<UserRespon>, response: Response<UserRespon>) {
                if (response.isSuccessful) {
                    listUsers.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserRespon>, t: Throwable) {
                t.message?.let { Log.d("onFailure", it) }
            }

        })
    }

    fun getSearchUser(): LiveData<ArrayList<ModelUser>> {
        return listUsers
    }
}