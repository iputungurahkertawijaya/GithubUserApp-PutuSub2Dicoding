package com.example.githubuserapp_putusub2dicoding.api

import com.example.githubuserapp_putusub2dicoding.Respons.ModelUser
import com.example.githubuserapp_putusub2dicoding.Respons.UserRespon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String?
    ): Call<ModelUser>

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") q: String?
    ): Call<UserRespon>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
        @Path("username") username: String?
    ): Call<ArrayList<ModelUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String?
    ): Call<ArrayList<ModelUser>>
}