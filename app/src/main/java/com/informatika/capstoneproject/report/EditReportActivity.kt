package com.informatika.capstoneproject.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.databinding.ActivityEditReportBinding
import com.informatika.capstoneproject.model.NoteModel
import com.informatika.capstoneproject.model.SubmitModel
import com.informatika.capstoneproject.networking.ApiRetrofit
import com.informatika.capstoneproject.profil.UserProfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditReportActivity : AppCompatActivity() {

    lateinit var binding : ActivityEditReportBinding

    private lateinit var ubahnamaPelapor: EditText
    private lateinit var ubahtelpPelapor: EditText
    private lateinit var ubahlokasiLaporan: EditText
    private lateinit var ubahtglLapor: EditText
    private lateinit var ubahlaporan: EditText
    private lateinit var btnubah: Button

    private val api by lazy { ApiRetrofit().endpoint }
    private val ubahNama by lazy { intent.getSerializableExtra("nama") as NoteModel.Data }
    private val ubahTelp by lazy { intent.getSerializableExtra("telp") as NoteModel.Data }
    private val ubahTgl by lazy { intent.getSerializableExtra("tgl") as NoteModel.Data }
    private val ubahLokasi by lazy { intent.getSerializableExtra("lokasi") as NoteModel.Data }
    private val ubahIsi by lazy { intent.getSerializableExtra("isi") as NoteModel.Data  }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityEditReportBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarUbah.setOnClickListener {
            val intent  =   Intent(this, HistoryReportActivity::class.java)
            startActivity(intent)
        }

        setupView()
        setupListener()

    }

    private fun setupView()   {
        ubahnamaPelapor     =   findViewById(R.id.UbahNamaPelapor)
        ubahtelpPelapor     =   findViewById(R.id.UbahTeleponPelapor)
        ubahtglLapor        =   findViewById(R.id.UbahTglLapor)
        ubahlokasiLaporan   =   findViewById(R.id.UbahLokasiLapor)
        ubahlaporan         =   findViewById(R.id.UbahLaporan)
        btnubah             =   findViewById(R.id.btnUbah)

        ubahnamaPelapor.setText(ubahNama.nama_pelapor)
        ubahtelpPelapor.setText(ubahTelp.telp_pelapor)
        ubahtglLapor.setText(ubahTgl.tgl_lapor)
        ubahlokasiLaporan.setText(ubahLokasi.lokasi_laporan)
        ubahlaporan.setText(ubahIsi.isi_laporan)
    }

    private fun setupListener() {
        btnubah.setOnClickListener {
            api.update(ubahNama.id_laporan!!,
                ubahnamaPelapor.text.toString(),
                ubahtelpPelapor.text.toString(),
                ubahtglLapor.text.toString(),
                ubahlokasiLaporan.text.toString(),
                ubahlaporan.text.toString())
                .enqueue(object :   Callback<SubmitModel>   {
                    override fun onResponse(
                        call: Call<SubmitModel>,
                        response: Response<SubmitModel>
                    ) {
                        if (response.isSuccessful)  {
                            val ubah  =   response.body()
                            Toast.makeText(
                                applicationContext, ubah!!.message,
                                Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                }  )

        }
    }
}