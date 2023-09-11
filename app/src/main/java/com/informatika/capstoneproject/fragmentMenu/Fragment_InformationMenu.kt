package com.informatika.capstoneproject.fragmentMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.info.adapter.InfoAdapter
import com.informatika.capstoneproject.model.ModelArticle
import com.informatika.capstoneproject.model.ModelNews
import com.informatika.capstoneproject.networking.ApiEndpoint.getApiClient
import com.informatika.capstoneproject.networking.ApiInterface
import com.informatika.capstoneproject.util.Utils.getCountry
import kotlinx.android.synthetic.main.fragment_information_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FragmentInformationMenu : Fragment() {

    companion object {
        const val API_KEY   =   "f3aa09e2f1d7494e9fded68f30d4e4c1"
    }

    var strCategory =   "technology"
    var strCountry: String? =   null
    var modelArticle: MutableList<ModelArticle> =   ArrayList()
    var infoAdapter: InfoAdapter?   =   null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvInformasi.setLayoutManager(LinearLayoutManager(context))
        rvInformasi.setHasFixedSize(true)

        //Info
        getInfo()

    }

    private fun getInfo() {

        //getCountry
        strCountry  =   getCountry()

        //set api
        val apiInterface    =   getApiClient().create(ApiInterface::class.java)
        val call = apiInterface.getTechnology(strCountry, strCategory, API_KEY)
        call.enqueue(object : Callback<ModelNews>   {
            override fun onResponse(call: Call<ModelNews>, response: Response<ModelNews>) {
                if (response.isSuccessful   &&  response.body() !=  null)   {
                    modelArticle    =   response.body()?.modelArticle   as  MutableList<ModelArticle>
                    infoAdapter =   InfoAdapter(modelArticle, context!!)
                    rvInformasi.adapter =   infoAdapter
                    infoAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ModelNews>, t: Throwable) {
                Toast.makeText(context, "Jaringan anda bermasalah.",    Toast.LENGTH_SHORT).show()
            }

    })

    }
}