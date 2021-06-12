package com.example.odev.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.odev.model.Kullanici

@Database(entities = arrayOf(Kullanici::class),version = 1)
abstract class KullaniciDB : RoomDatabase(){
    abstract fun kullaniciDao() : KullaniciDAO

    companion object{
        @Volatile private var instance : KullaniciDB? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: dbOlustur(context).also {
                instance = it
            }
        }

        private fun dbOlustur(context : Context) = Room.databaseBuilder(context.applicationContext, KullaniciDB::class.java, "kullanicidb").build()

    }



}