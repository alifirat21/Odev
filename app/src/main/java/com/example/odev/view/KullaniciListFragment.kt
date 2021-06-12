package com.example.odev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odev.R
import com.example.odev.adapter.KullaniciAdapter
import com.example.odev.viewmodel.KullaniciListesiVM
import kotlinx.android.synthetic.main.fragment_kullanici_list.*

class KullaniciListFragment : Fragment() {
            //Classtan new yapar gibi viewmodel kullanıcaz
    private lateinit var viewModel : KullaniciListesiVM
    private val  reyclerKullaniciAdapter = KullaniciAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kullanici_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //View modeli tanıtıryorum ve viewModel değişkenime atıyorum
        viewModel = ViewModelProviders.of(this).get(KullaniciListesiVM::class.java)
        viewModel.refresh()

        //reycler çağırıyorum
        kullaniciListRecycler.layoutManager = LinearLayoutManager(context)
        //adapterı bağlıyorum
        kullaniciListRecycler.adapter = reyclerKullaniciAdapter

        swipeRefresh.setOnRefreshListener {
            yukleniyor.visibility = View.VISIBLE
            hataMesaj.visibility = View.GONE
            kullaniciListRecycler.visibility = View.GONE
            viewModel.refresh()
            swipeRefresh.isRefreshing = false
        }

        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.kullanicilar.observe(viewLifecycleOwner, Observer { kullanicilar->
            kullanicilar?.let {
                kullaniciListRecycler.visibility = View.VISIBLE
                reyclerKullaniciAdapter.kullaniciGuncelle(kullanicilar)
            }
        })
        viewModel.hataMesaj.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    hataMesaj.visibility = View.VISIBLE
                    kullaniciListRecycler.visibility = View.GONE
                }
                else{
                    hataMesaj.visibility = View.GONE
                }
            }
        })
        viewModel.yukleniyor.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    kullaniciListRecycler.visibility = View.GONE
                    hataMesaj.visibility = View.GONE
                    yukleniyor.visibility = View.VISIBLE
                }
                else{
                    yukleniyor.visibility = View.GONE
                }
            }
        })
    }

}