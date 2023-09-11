package com.informatika.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.informatika.capstoneproject.databinding.ActivityRegistBinding

class RegistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   ActivityRegistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth    =   FirebaseAuth.getInstance()

        binding.buttonRegist.setOnClickListener {
            val email    = binding.inputEmailRegist.text.toString()
            val pass    =   binding.inputPasswordRegist.text.toString()
            val confirmpass    =   binding.inputPasswordRegist.text.toString()

            if (email.isNotEmpty()   && email.isNotEmpty()   && confirmpass.isNotEmpty()) {
                if (pass == confirmpass)    {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else  {
                            Toast.makeText(this, it.exception.toString(),   Toast.LENGTH_SHORT).show()
                        }
                    }
                } else  {
                    Toast.makeText(this, "Password does not matched",   Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty",   Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvRegister.setOnClickListener {
            val loginIntent =   Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }
    }
}