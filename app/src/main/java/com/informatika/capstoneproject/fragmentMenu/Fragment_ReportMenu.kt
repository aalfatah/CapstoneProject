package com.informatika.capstoneproject.fragmentMenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.informatika.capstoneproject.databinding.FragmentReportMenuBinding
import com.informatika.capstoneproject.report.HistoryReportActivity
import com.informatika.capstoneproject.report.PanduanReportActivity
import com.informatika.capstoneproject.report.UploadReportActivity

class FragmentReportMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val report   =   FragmentReportMenuBinding.inflate(layoutInflater)

        report.lakukanPengaduan.setOnClickListener {
            val intent  =   Intent(this@FragmentReportMenu.requireContext(), UploadReportActivity::class.java)
            startActivity(intent)
        }
        report.pengaduanSaya.setOnClickListener {
            val intent  =   Intent(this@FragmentReportMenu.requireContext(), HistoryReportActivity::class.java)
            startActivity(intent)
        }
        report.panduanPengaduan.setOnClickListener {
            val intent  =   Intent(this@FragmentReportMenu.requireContext(), PanduanReportActivity::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return report.root
    }
}