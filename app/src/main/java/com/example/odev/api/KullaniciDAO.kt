package com.example.odev.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.odev.model.Kullanici

@Dao
interface KullaniciDAO {
    @Insert
    suspend fun insert(vararg kullanici : Kullanici) : List<Long>
    @Query("SELECT * FROM kullanici")
    suspend fun getKullanicilar() : List<Kullanici>

    @Query("SELECT * FROM kullanici WHERE uuid = :kullaniciID")
    suspend fun getkullanici(kullaniciID:Int) : Kullanici

    @Query("DELETE FROM kullanici")
    suspend fun deleteALL()

}