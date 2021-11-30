package com.example.githubuserapp_putusub2dicoding.Respons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelUser(
    var login: String? = null,
    var type: String? = null,
    var avatar_url: String? = null,
    var name: String? = null,
    var location: String? = null,
    var company: String? = null,
    var followers: Int = 0,
    var following: Int = 0,
    var public_repos: Int = 0
): Parcelable


