package com.ebarim.admin.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.Utils.Utils
import com.ebarim.admin.Utils.Utils.Companion.USER_NAME
import com.ebarim.admin.Utils.Utils.Companion.getName
import com.ebarim.admin.Utils.Utils.Companion.setLogged
import com.ebarim.admin.Utils.Utils.Companion.toast
import com.ebarim.admin.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        binding.docRegister.setOnClickListener {
            startActivity(Intent(this, DocRegister::class.java))
        }

        binding.btnLogout.setOnClickListener {
            toast("Working on it!")
            setLogged(false)
            finishAffinity()
            startActivity(Intent(this, AdminLogin::class.java))
        }

        val userName = getName(USER_NAME)
        binding.userMsg.text =
            "Hello ${userName}. To register a doctor account, press the below button."
        val view = binding.root
        setContentView(view)
    }
}