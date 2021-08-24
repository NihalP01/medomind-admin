package com.ebarim.admin.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.databinding.ActivityDocRegisterBinding

class DocRegister: AppCompatActivity() {
    lateinit var binding:ActivityDocRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDocRegisterBinding.inflate(layoutInflater)



        val view = binding.root
        setContentView(view)
    }
}