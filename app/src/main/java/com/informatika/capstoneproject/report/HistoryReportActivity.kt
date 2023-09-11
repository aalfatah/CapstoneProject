package com.informatika.capstoneproject.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.databinding.ActivityHistoryReportBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity
import com.informatika.capstoneproject.model.NoteModel
import com.informatika.capstoneproject.model.SubmitModel
import com.informatika.capstoneproject.networking.ApiRetrofit
import com.informatika.capstoneproject.report.adapter.ReportAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryReportActivity : AppCompatActivity() {

    lateinit var binding: ActivityHistoryReportBinding

    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var reportAdapter: ReportAdapter
    private lateinit var listReport:    RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityHistoryReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupList()

        binding.toolbarRiwayat.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun setupList() {
        listReport  =   findViewById(R.id.rvHistory)
        reportAdapter   =   ReportAdapter(arrayListOf(), object : ReportAdapter.OnAdapterListener {
            override fun onUpdate(riwayatLapor: NoteModel.Data) {
                startActivity(
                    Intent(this@HistoryReportActivity,  EditReportActivity::class.java)
                        .putExtra("nama",   riwayatLapor)
                        .putExtra("telp",   riwayatLapor)
                        .putExtra("tgl",   riwayatLapor)
                        .putExtra("lokasi",   riwayatLapor)
                        .putExtra("isi",   riwayatLapor)
                )
            }

            override fun onDelete(Hapusriwayat: NoteModel.Data) {
                api.delete(Hapusriwayat.id_laporan!!)
                    .enqueue(object :   Callback<SubmitModel>   {
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: Response<SubmitModel>
                        ) {
                            if (response.isSuccessful)  {
                                val hapus  =   response.body()
                                Toast.makeText(
                                    applicationContext, hapus!!.message,
                                    Toast.LENGTH_SHORT).show()
                                getNote()
                            }
                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                        }

                    })
            }

        })
        listReport.adapter  =   reportAdapter
    }

    private fun getNote()   {
        api.data().enqueue(object : Callback<NoteModel> {

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("HistoryReportActivity",   t.toString())
            }

            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                if (response.isSuccessful)  {
                    val listData    =   response.body()!!.tb_laporan
                    reportAdapter.setData(listData)
//                    listData.forEach {
//                        Log.e("HistoryReportActivity", "nama    ${it.nama_pelapor}")
//                    }
                }
            }

        })
    }

}