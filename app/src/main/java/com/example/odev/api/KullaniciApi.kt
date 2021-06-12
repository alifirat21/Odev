package com.example.odev.api

import com.example.odev.model.Kullanici
import io.reactivex.Single
import retrofit2.http.GET

interface KullaniciApi {
    //users
    @GET("users")
    fun getKullanici() : Single<List<Kullanici>>
}