package com.example.odev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.odev.R
import com.example.odev.model.Kullanici
import com.example.odev.util.placeholderOlustur
import com.example.odev.util.resimler
import com.example.odev.view.KullaniciListFragmentDirections
import kotlinx.android.synthetic.main.fragment_kullanici_detay.view.*
import kotlinx.android.synthetic.main.kullanici_recycler_row.view.*
import java.util.zip.Inflater

// kullanici classındandan verileri Recylere aktar
class KullaniciAdapter(val kullaniciListesi:ArrayList<Kullanici>):RecyclerView.Adapter<KullaniciAdapter.KullaniciViewHolder>() {
    class KullaniciViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {

    }
            //reycler_row bağlanıyor
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KullaniciViewHolder {
        val inflater = LayoutInflater.from(parent.context)
                //gorunum oluşturuypruz rowlar geliyyor
        val view = inflater.inflate(R.layout.kullanici_recycler_row, parent, false)
        return KullaniciViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kullaniciListesi.size
    }

    override fun onBindViewHolder(holder: KullaniciViewHolder, position: Int) {
        holder.itemView.kullaniciid.text = kullaniciListesi.get(position).kullaniciId
        holder.itemView.isim.text = kullaniciListesi.get(position).kullaniciAd
        holder.itemView.email.text = kullaniciListesi.get(position).kullaniciMail


        // Tıklanınca ne oluyor
        holder.itemView.setOnClickListener {
            val action = KullaniciListFragmentDirections.actionKullaniciListFragmentToKullaniciDetayFragment(kullaniciListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.resimler(kullaniciListesi.get(position).kullaniciGorsel, placeholderOlustur(holder.itemView.context))

    }
    //adapter başlığında yazdık
    fun  kullaniciGuncelle(yeniKullaniciListesi:List<Kullanici>){
        kullaniciListesi.clear()
        kullaniciListesi.addAll(yeniKullaniciListesi)
        notifyDataSetChanged()
    }
}