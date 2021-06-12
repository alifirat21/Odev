package com.example.odev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Kullanici (
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val kullaniciId: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val kullaniciAd: String,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val kullaniciMail: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    val kullaniciGorsel: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}