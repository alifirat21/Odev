package com.example.odev.api

import com.example.odev.model.Kullanici
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class KullaniciApiServis  {
    private val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    private val  api = Retrofit.Builder()
       .baseUrl(BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
       .build()
       .create(KullaniciApi::class.java)

    fun getData():Single<List<Kullanici>>{
        return api.getKullanici()
    }
}