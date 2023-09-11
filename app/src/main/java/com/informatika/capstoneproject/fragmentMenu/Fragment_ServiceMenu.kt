package com.informatika.capstoneproject.fragmentMenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.informatika.capstoneproject.databinding.FragmentServiceMenuBinding
import com.informatika.capstoneproject.service.PengajuanKTPActivity
import com.informatika.capstoneproject.service.PengajuanSKCKActivity
import com.informatika.capstoneproject.service.PengajuanSKUActivity

class FragmentServiceMenu : Fragment() {

    private lateinit var binding: FragmentServiceMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentServiceMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Consul.setOnClickListener {
            val phoneNumber = "+6285161202138"
            val contactNumber = "Admin"
            val message = "Halo ${contactNumber}!, saya membutuhkan bantuan."

            val url = "https://api.whatsapp.com/send?phone=${phoneNumber}&text=${message}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)

            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            } else {
                val playStoreUri = Uri.parse("market://details?id=com.whatsapp")
                val playStoreIntent = Intent(Intent.ACTION_VIEW, playStoreUri)
                startActivity(playStoreIntent)
            }
        }

        binding.sku.setOnClickListener {
            val intent = Intent(requireContext(), PengajuanSKUActivity::class.java)
            startActivity(intent)
        }

        binding.skck.setOnClickListener {
            val intent = Intent(requireContext(), PengajuanSKCKActivity::class.java)
            startActivity(intent)
        }

        binding.ktp.setOnClickListener {
            val intent = Intent(requireContext(), PengajuanKTPActivity::class.java)
            startActivity(intent)
        }
    }
}
