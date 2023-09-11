package com.informatika.capstoneproject.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.capstoneproject.databinding.ActivityPengajuanSkuActivityBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity

class PengajuanSKUActivity : AppCompatActivity() {

    lateinit var binding: ActivityPengajuanSkuActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityPengajuanSkuActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarSKU.setOnClickListener {
            val intent  =   Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

    }
}