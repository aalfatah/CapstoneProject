package com.informatika.capstoneproject.profil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.capstoneproject.databinding.ActivityEditProfilBinding

class EditProfilActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityEditProfilBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarEditProfil.setOnClickListener {
            val intent  =   Intent(this, UserProfilActivity::class.java)
            startActivity(intent)
        }
    }
}