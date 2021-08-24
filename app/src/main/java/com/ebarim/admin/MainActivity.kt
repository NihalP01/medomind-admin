package com.ebarim.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.UI.AdminLogin
import com.ebarim.admin.UI.Home
import com.ebarim.admin.Utils.Utils.Companion.getLogged
import com.ebarim.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if (getLogged(context = this)) {
            startActivity(Intent(this, Home::class.java))
            finish()
        } else {
            startActivity(Intent(this, AdminLogin::class.java))
            finish()
        }

        val view = binding.root
        setContentView(view)
    }
}