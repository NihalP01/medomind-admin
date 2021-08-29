package com.ebarim.admin.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.Network.API.ApiClient
import com.ebarim.admin.Utils.Utils.Companion.setAuthType
import com.ebarim.admin.Utils.Utils.Companion.setLogged
import com.ebarim.admin.Utils.Utils.Companion.setName
import com.ebarim.admin.Utils.Utils.Companion.setToken
import com.ebarim.admin.Utils.Utils.Companion.toast
import com.ebarim.admin.databinding.ActivityAdminLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdminLogin : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)

        binding.btnAdminLogin.setOnClickListener {
            doValidation()
        }
        val view = binding.root
        setContentView(view)
    }

    private fun doValidation() {
        if (binding.adminEmail.text.toString()
                .isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.adminEmail.text.toString())
                .matches()
        ) {
            binding.adminEmail.error = "Please enter a valid email id"
            binding.adminEmail.requestFocus()
            return
        }
        if (binding.adminPassword.text.toString().isEmpty()) {
            binding.adminPassword.error = "Please enter your password"
            binding.adminPassword.requestFocus()
            return
        }
        doLogin()
    }

    private fun doLogin() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                binding.adminSigninProgress.visibility = View.VISIBLE
                binding.btnAdminLogin.visibility = View.INVISIBLE
                val response = ApiClient.ApiAdapter.apiClient.adminLogin(
                    binding.adminEmail.text.toString(),
                    binding.adminPassword.text.toString()
                )
                Log.d("myTag", response.raw().toString())
                if (response.isSuccessful && response.body() != null) {
                    binding.adminSigninProgress.visibility = View.INVISIBLE
                    binding.btnAdminLogin.visibility = View.VISIBLE
                    checkRole(response.body()!!.type, response.body()!!.token)
                } else {
                    toast(response.message().toString())
                }
            } catch (e: Exception) {
                Log.d("myTag", e.message.toString())
            }
        }
    }

    private fun checkRole(type: String, token: String) {
        GlobalScope.launch {
            try {
                val response = ApiClient.ApiAdapter.apiClient.role("$type $token")
                if (response.isSuccessful && response.body() != null) {
                    val role = response.body()!!.data.user.role
                    val name = response.body()!!.data.user.name
                    if (role == "admin") {
                        setLogged(true)
                        setAuthType(type)
                        setToken(token)
                        setName(name)
                        startActivity(Intent(this@AdminLogin, Home::class.java))
                        finish()
                    } else {
                        toast("This is not an admin account. Please check your login info")
                    }
                } else {
                    Log.d("myTag", response.message().toString())
                }
            } catch (e: Exception) {
                toast("Something went wrong, try again later.")
            }
        }
    }
}
