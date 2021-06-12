package com.example.odev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.odev.api.KullaniciApiServis
import com.example.odev.api.KullaniciDB
import com.example.odev.model.Kullanici
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class KullaniciListesiVM(application: Application) : BaseVM(application) {
    val kullanicilar = MutableLiveData<List<Kullanici>>()
    val hataMesaj = MutableLiveData<Boolean>()
    val yukleniyor = MutableLiveData<Boolean>()

    private val  kullaniciapiservis = KullaniciApiServis()
    private val disposable = CompositeDisposable()


    fun refresh(){
        //val ali = Kullanici("1", "Ali", "ali@gmail.com", "t.jpg")
        // val kullaniciListesi = arrayListOf<Kullanici>(ali)
        //kullanicilar.value = kullaniciListesi
        //hataMesaj.value = false
        //yukleniyor.value = false

        veriCek()
    }
     private fun veriCek(){
        yukleniyor.value = true
        disposable.add(
            kullaniciapiservis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Kullanici>>(){
                    override fun onSuccess(t: List<Kullanici>) {
                        sql(t)
                    }

                    override fun onError(e: Throwable) {
                        hataMesaj.value = true
                        yukleniyor.value = false

                    }

                })

        )
    }

    private fun kullaniciGoster(kullaniciListesi : List<Kullanici>){
        kullanicilar.value=kullaniciListesi
        hataMesaj.value = false
        yukleniyor.value = false
    }
    private fun sql(kullaniciListesi : List<Kullanici>){
        launch {
            val dao = KullaniciDB(getApplication()).kullaniciDao()
            dao.deleteALL()
            val idListesi =  dao.insert(*kullaniciListesi.toTypedArray())
            var i = 0
            while (i < kullaniciListesi.size){
                kullaniciListesi[i].uuid = idListesi[i].toInt()
                i= i + 1
                kullaniciGoster(kullaniciListesi)
            }
        }
    }
}