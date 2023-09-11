package com.informatika.capstoneproject.report

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.databinding.ActivityUploadReportBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity
import com.informatika.capstoneproject.model.SubmitModel
import com.informatika.capstoneproject.networking.ApiRetrofit
import kotlinx.android.synthetic.main.activity_upload_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class UploadReportActivity : AppCompatActivity() {

    lateinit var binding: ActivityUploadReportBinding

    /*private lateinit var imgPelapor: ImageView*/
    private lateinit var namaPelapor: EditText
    private lateinit var telpPelapor: EditText
    private lateinit var lokasiLaporan: EditText
    private lateinit var tglLapor: EditText
    private lateinit var btnTgl: Button
    private lateinit var isiLaporan: EditText
    private lateinit var btnLapor: Button

    private val api by lazy { ApiRetrofit().endpoint }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUploadReportBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setupListener()

        binding.toolbarlapor.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        //Calender
        tglLapor = binding.inputTglLapor
        btnTgl = binding.btnTglLapor

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        btnTgl.setOnClickListener {
            DatePickerDialog(this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Upload Gambar

        imageLaporan.isEnabled = true

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                100
            )
        } else {
            imageLaporan.isEnabled = true
        }

        imageLaporan.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }

    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tglLapor.setText(sdf.format(myCalendar.time))
    }

    //Upload Gambar

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val imageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            imageLaporan.setImageBitmap(bitmap)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            imageLaporan.isEnabled = true
        }
    }

    private fun setView() {
        /*imgPelapor  = findViewById(R.id.imageLaporan)*/
        namaPelapor = findViewById(R.id.inputNamaPelapor)
        telpPelapor = findViewById(R.id.inputTeleponPelapor)
        tglLapor = findViewById(R.id.inputTglLapor)
        lokasiLaporan = findViewById(R.id.inputLokasiLapor)
        isiLaporan = findViewById(R.id.inputLaporan)
        btnLapor = findViewById((R.id.btnLapor))
    }

    private fun setupListener() {
        btnLapor.setOnClickListener {
            if (namaPelapor.text.toString().isNotEmpty() && telpPelapor.text.toString()
                    .isNotEmpty() && tglLapor.text.toString()
                    .isNotEmpty() && lokasiLaporan.text.toString()
                    .isNotEmpty() && isiLaporan.text.toString().isNotEmpty()
            ) {
                Log.e("UploadReportActivity", namaPelapor.text.toString())
                Log.e("UploadReportActivity", telpPelapor.text.toString())
                Log.e("UploadReportActivity", tglLapor.text.toString())
                Log.e("UploadReportActivity", lokasiLaporan.text.toString())
                Log.e("UploadReportActivity", isiLaporan.text.toString())

                api.create(
                    namaPelapor.text.toString(),
                    telpPelapor.text.toString(),
                    tglLapor.text.toString(),
                    lokasiLaporan.text.toString(),
                    isiLaporan.text.toString()
                )
                    .enqueue(object : Callback<SubmitModel> {
                        override fun onResponse(
                            call: Call<SubmitModel>,
                            response: Response<SubmitModel>,
                        ) {
                            if (response.isSuccessful) {
                                val upload = response.body()
                                Toast.makeText(
                                    applicationContext, upload!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Gagal mengirim laporan!!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                        }

                    })
            } else {
                Toast.makeText(
                    applicationContext, "Laporan tidak boleh kosong!!!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}