package com.informatika.capstoneproject.profil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.informatika.capstoneproject.MainActivity
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.databinding.ActivityUserProfilBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity
import com.informatika.capstoneproject.model.NoteModel

class UserProfilActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserProfilBinding
//    private lateinit var firebaseAuth: FirebaseAuth

//    private lateinit var profil :   SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding =   ActivityUserProfilBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        profil  =   getSharedPreferences("login_session",   MODE_PRIVATE)

        //menampilkan data profil
//        binding.UnameProfil.text    =   profil.getString("username", null)
//        binding.nameProfil.text    =   profil.getString("username", null)
//        binding.telpProfil.text    =   profil.getString("telp_profil", null)
//        binding.nikProfil.text    =   profil.getString("nik_profil", null)
//        binding.passProfil.text    =   profil.getString("pass_user", null)

        //membuat logout
        binding.buttonLogout.setOnClickListener {
            startActivity(Intent(this@UserProfilActivity,   MainActivity::class.java))
            finish()
        }

//        firebaseAuth    =   FirebaseAuth.getInstance()

        binding.toolbarProfil.setOnClickListener {
            val intent  =   Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.editProfil.setOnClickListener {
            val intent  =   Intent(this,EditProfilActivity::class.java)
            startActivity(intent)
        }

//        binding.buttonLogout.setOnClickListener {
//            firebaseAuth.signOut()
//            val intent  =   Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

    }
}