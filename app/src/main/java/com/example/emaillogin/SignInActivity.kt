package com.example.emaillogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.emaillogin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

lateinit var firebaseAuth: FirebaseAuth
lateinit var binding:ActivityLoginBinding
class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.textView.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPasswordTextView.setOnClickListener {
            // Open the ForgotPasswordActivity when the TextView is clicked
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            else {
            Toast.makeText(this, "Enter details", Toast.LENGTH_SHORT).show()

        }
        }
    }
    override fun onStart(){
        super.onStart()
        if(firebaseAuth.currentUser!=null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}