package com.informatika.capstoneproject.fragmentMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.databinding.ActivityMenuBinding
import com.informatika.capstoneproject.profil.UserProfilActivity

class MenuActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding     =   ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var viewPager   =   findViewById(R.id.view_pager) as ViewPager
        var tablayout   =   findViewById(R.id.tab_layout) as TabLayout

        val viewPagerAdapter    =   ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(FragmentInformationMenu(),    "Informasi")
        viewPagerAdapter.addFragment(FragmentReportMenu(),    "Pelaporan")
        viewPagerAdapter.addFragment(FragmentServiceMenu(),    "Pelayanan")

        viewPager.adapter   =   viewPagerAdapter
        tablayout.setupWithViewPager(viewPager)

        binding.profileAvatar.setOnClickListener {
            val intent  =   Intent(this, UserProfilActivity::class.java)
            startActivity(intent)
        }

    }
}

