package com.example.myapplication.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.common.Result
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        initObserver()

    }

    private fun setupForm() : Boolean{
        var form = false

        val name = binding.edtNameRegister.text.toString()
        val username = binding.edtUsernameRegister.text.toString()
        val email = binding.edtEmailRegister.text.toString()
        val password = binding.edtPasswordRegister.text.toString()
        val role = binding.edtRole.text.toString()

        when {
            name.isEmpty() -> {
                binding.edtNameRegister.error = "Please fill your name."
            }
            username.isEmpty() -> {
                binding.edtUsernameRegister.error = "Please fill your username."
            }
            email.isEmpty() -> {
                binding.tflEmailRegister.error = "Please fill your email address."
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tflEmailRegister.error = "Please enter a valid email address."
            }
            password.isEmpty() -> {
                binding.tflPassword.error = "Please enter your password."
            }
            password.length < 6 -> {
                binding.tflPassword.error = "Password length must be 6 character."
            }
            role.isEmpty() -> {
                binding.edtRole.error = "Please fill your role"
            }
            else -> form = true
        }
        return form
    }

    private fun initObserver() {
        viewModel.state.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Register Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is Result.Error -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "The password confirmation does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            btnRegister.setOnClickListener {
                if (setupForm()) {
                    viewModel.register(
                        name = edtNameRegister.text.toString(),
                        username = edtUsernameRegister.text.toString(),
                        email = edtEmailRegister.text.toString(),
                        password = edtPasswordRegister.text.toString(),
                        role = edtRole.text.toString()
                    )
                }
            }
            btnLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}