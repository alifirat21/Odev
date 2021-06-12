package com.example.odev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.odev.R
import com.example.odev.viewmodel.KullaniciDetayVM
import kotlinx.android.synthetic.main.fragment_kullanici_detay.*

class KullaniciDetayFragment : Fragment() {
    //VM tanıttık
    private lateinit var viewModel : KullaniciDetayVM
    private var  kullaniciId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kullanici_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            kullaniciId =
                KullaniciDetayFragmentArgs.fromBundle(it).kullaniciİd
        }

        viewModel = ViewModelProviders.of(this).get(KullaniciDetayVM::class.java)
        viewModel.veriAl(kullaniciId)


            observeLiveData()

    }
    fun observeLiveData(){
        viewModel.kullaniciLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                kul_id.text = it.kullaniciId
                kullaniciIsim.text = it.kullaniciAd
                kullaniciMail.text = it.kullaniciMail

            }
        })
    }

}