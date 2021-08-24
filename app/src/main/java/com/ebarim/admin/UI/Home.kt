package com.ebarim.admin.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.databinding.ActivityHomeBinding
import com.ebarim.admin.databinding.ActivityMainBinding

class Home: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        binding.docRegister.setOnClickListener {
            startActivity(Intent(this, DocRegister::class.java))
        }

        val view = binding.root
        setContentView(view)
    }
}