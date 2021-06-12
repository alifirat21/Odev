package com.example.odev.view

import android.app.Application
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.odev.R

class SplashActivity : AppCompatActivity() {
    //public var netkontrol : Boolean =true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            //imageView2.isVisible = true;

            if(internetKontrol(application) == true){
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                }, 3000)
            }else{
                Toast.makeText(getApplicationContext(),"LÜTFEN İNTERNET BAĞLANTINI KONTROL EDİNİZ",Toast.LENGTH_LONG).show();
                finish()
            }
        },1000)


    }

    fun internetKontrol(application: Application): Boolean {
        val baglanti = application.getSystemService(
            Application.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = baglanti.getNetworkCapabilities(baglanti.activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->    true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->   true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->   true
                else ->     false
            }
        }
        else {
            if (baglanti.activeNetworkInfo != null && baglanti.activeNetworkInfo!!.isConnectedOrConnecting) {
                return true
            }
        }
        return false
    }



}