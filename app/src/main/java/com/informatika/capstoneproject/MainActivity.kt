package com.informatika.capstoneproject

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.informatika.capstoneproject.databinding.ActivityMainBinding
import com.informatika.capstoneproject.fragmentMenu.MenuActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private var user    :   String  =   ""
//    private var passUser    :   String  =   ""

//    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth    =   FirebaseAuth.getInstance()

//        checkIfUserisLogged()

        binding.buttonLogin.setOnClickListener {

            // Login w/ MySQL
//            user        =   binding!!.inputEmailLogin.text.toString()
//            passUser    =   binding!!.inputPasswordLogin.text.toString()
//
//            when {
//                user    ==  ""  ->  {
//                    binding!!.inputEmailLogin.error =   "Username tidak boleh kosong"
//                }
//                passUser    ==  ""  ->  {
//                    binding!!.inputPasswordLogin.error  =   "Password tidak boleh kosong"
//                } else  -> {
//                    getData()
//                }
//            }

            val email   =   binding.inputEmailLogin.text.toString()
            val pass    =   binding.inputPasswordLogin.text.toString()


            if (email.isEmpty())  {

                binding.inputEmailLogin.error   =   "Email tidak boleh kosong"
                binding.inputEmailLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())   {
                binding.inputEmailLogin.error   =   "Email tidak valid"
                binding.inputEmailLogin.requestFocus()
                return@setOnClickListener
            }

            if (pass.isEmpty())  {

                binding.inputPasswordLogin.error    =   "Password tidak boleh kosong"
                binding.inputPasswordLogin.requestFocus()
                return@setOnClickListener

            }

            if (pass.length    < 6)    {
                binding.inputPasswordLogin.error    =   "Password harus lebih dari 6 karakter"
                binding.inputPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            loginUserFirebase(email,pass)

            }

        binding.forgotPassword.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_forgotpass, null)
            val userEmail = view.findViewById<EditText>(R.id.editBox)

            builder.setView(view)
            val dialog = builder.create()

            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
                compareEmail(userEmail)
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }
        binding.tvLogin.setOnClickListener {
        val registIntent =   Intent(this, RegistActivity::class.java)
        startActivity(registIntent)
        }

//        val forgotPassword  =   findViewById<TextView>(R.id.forgotPassword)
//
//        forgotPassword.setOnClickListener {
//            Intent(this,    RegistActivity::class.java). also {
//                startActivity(it)
//            }
//        }

    }

//    private fun checkIfUserisLogged()   {
//        if (firebaseAuth.currentUser    !=null) {
//            val intent  =   Intent(this, MenuActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }

    private fun loginUserFirebase(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this,    "Berhasil masuk",   Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Gagal, periksa kembali email dan password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Outside onCreate
    private fun compareEmail(email: EditText){
        if (email.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            return
        }
        firebaseAuth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                }
            }
    }

//    private fun getData()   {
//        api.loginUser(user, passUser).enqueue(object :  Callback<ResponseLogin> {
//
//            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
//                if (response.isSuccessful) {
//                    if (response.body()?.response == true) {
//                        Toast.makeText(applicationContext, "Berhasil masuk", Toast.LENGTH_SHORT)
//                            .show()
//                        startActivity(Intent(this@MainActivity, MenuActivity::class.java))
//                        finish()
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "Login gagal periksa kembali username dan password",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "Login gagal",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                Log.e("pesan error","${t.message}")
//            }
//
//        })
//    }

}