package com.informatika.capstoneproject.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.capstoneproject.databinding.ActivityPengajuanSkckActivityBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity

class PengajuanSKCKActivity : AppCompatActivity() {

    lateinit var binding: ActivityPengajuanSkckActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityPengajuanSkckActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarSKCK.setOnClickListener {
            val intent   =   Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }
}