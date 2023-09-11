package com.informatika.capstoneproject.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.capstoneproject.databinding.ActivityPengajuanKtpActivityBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity

class PengajuanKTPActivity : AppCompatActivity() {

    lateinit var binding: ActivityPengajuanKtpActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityPengajuanKtpActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarKTP.setOnClickListener {
            val intent  =   Intent(this,    MenuActivity::class.java)
            startActivity(intent)
        }

    }
}