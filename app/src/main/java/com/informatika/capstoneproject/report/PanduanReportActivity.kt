package com.informatika.capstoneproject.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.informatika.capstoneproject.databinding.ActivityPanduanReportBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity

class PanduanReportActivity : AppCompatActivity() {

    lateinit var binding: ActivityPanduanReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityPanduanReportBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarPanduan.setOnClickListener {
            startActivity(Intent(this@PanduanReportActivity, MenuActivity::class.java))
        }

    }
}