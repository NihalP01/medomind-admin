package com.ebarim.admin.UI

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.ebarim.admin.Network.API.ApiClient
import com.ebarim.admin.Utils.Utils.Companion.AUTH_TYPE
import com.ebarim.admin.Utils.Utils.Companion.TOKEN_KEY
import com.ebarim.admin.Utils.Utils.Companion.getAuthType
import com.ebarim.admin.Utils.Utils.Companion.getToken
import com.ebarim.admin.Utils.Utils.Companion.toast
import com.ebarim.admin.databinding.ActivityDocRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DocRegister: AppCompatActivity() {
    lateinit var binding:ActivityDocRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDocRegisterBinding.inflate(layoutInflater)

        binding.btnDocRegister.setOnClickListener {
            doValidation()
        }

        val view = binding.root
        setContentView(view)
    }

    private fun doValidation(){
        if (binding.userName.text.toString().isEmpty() || binding.userName.text.toString().length < 5){
            binding.userName.error = "Enter a valid name"
            binding.userName.requestFocus()
            return
        }
        if (binding.userPhoneNumber.text.toString().isEmpty() || !Patterns.PHONE.matcher(binding.userPhoneNumber.text.toString()).matches()){
            binding.userPhoneNumber.error = "Enter a valid phone number"
            binding.userPhoneNumber.requestFocus()
            return
        }
        if (binding.userSignupEmail.text.toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.userSignupEmail.text.toString()).matches()){
            binding.userSignupEmail.error = "Please enter a valid email"
            binding.userSignupEmail.requestFocus()
            return
        }
        if (binding.userSignupPass.text.toString().isEmpty()){
            binding.userSignupPass.error = "Please enter you password"
            binding.userSignupPass.requestFocus()
            return
        }
        if (binding.userConfirmPass.text.toString().isEmpty() || binding.userSignupPass.text.toString() != binding.userConfirmPass.text.toString()){
            binding.userConfirmPass.error = "Password did not match"
            binding.userConfirmPass.requestFocus()
            return
        }
        docRegister()
    }

    private fun docRegister(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val type = getAuthType(AUTH_TYPE)
                val token = getToken(TOKEN_KEY)
                val response = ApiClient.ApiAdapter.apiClient.register("$type $token",
                    binding.userName.text.toString(),
                    binding.userPhoneNumber.text.toString(),
                    binding.userSignupEmail.text.toString(),
                    binding.userSignupPass.text.toString()
                )
                if (response.isSuccessful && response.body() != null){
                    //show success message
                    toast("Registered Successfully")
                    startActivity(Intent(this@DocRegister, Home::class.java))
                    finish()
                }else{
                    //show error
                    toast("Logged in failed")
                }
            }catch (e: Exception){
                toast("Something went wrong, try again later.")
            }
        }
    }
}