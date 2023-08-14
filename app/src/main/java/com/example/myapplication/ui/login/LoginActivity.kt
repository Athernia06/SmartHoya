package com.example.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.example.myapplication.common.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupLogin()
        setupViewModel()

    }

    private fun setupForm() : Boolean{
        var form = false

        val email = binding.edtEmailLogin.text.toString()
        val password = binding.edtPasswordLogin.text.toString()

        when {
            email.isEmpty() -> {
                binding.tflEmailLogin.error = "Please fill your email address."
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tflEmailLogin.error = "Please enter a valid email address."
            }
            password.isEmpty() -> {
                binding.tflPassword.error = "Please enter your password."
            }
            password.length < 6 -> {
                binding.tflPassword.error = "Password length must be 6 character."
            }
            else -> form = true
        }
        return form
    }

    private fun setupViewModel() {
        viewModel.state.observe(this@LoginActivity) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                is Result.Error -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupLogin() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (setupForm()) {
                    viewModel.login(
                        email = edtEmailLogin.text.toString(),
                        password = edtPasswordLogin.text.toString(),
                    )
                }
            }
        }
    }
}