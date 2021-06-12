package com.example.odev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.odev.adapter.KullaniciAdapter
import com.example.odev.api.KullaniciDB
import com.example.odev.model.Kullanici
import kotlinx.coroutines.launch
import java.util.*

class KullaniciDetayVM(application: Application) : BaseVM(application) {

    val kullaniciLiveData = MutableLiveData<Kullanici>()
    private val  reyclerKullaniciAdapter = KullaniciAdapter(arrayListOf())


    fun veriAl(id: Int){
        //val ali = Kullanici("1", "Ali", "ali@gmail.com", "t.jpg")
        //kullaniciLiveData.value = ali
    launch {
        val dao = KullaniciDB(getApplication()).kullaniciDao()
        val kullanici = dao.getkullanici(id)
        kullaniciLiveData.value = kullanici
    }

    }
}