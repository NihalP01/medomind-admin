package com.ebarim.admin.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.Utils.Utils.Companion.USER_NAME
import com.ebarim.admin.Utils.Utils.Companion.getName
import com.ebarim.admin.databinding.ActivityDoctorsListBinding

class DoctorsList : AppCompatActivity() {
    lateinit var binding: ActivityDoctorsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorsListBinding.inflate(layoutInflater)

        binding.docName.text = getName(USER_NAME)

        val view = binding.root
        setContentView(view)
    }
}