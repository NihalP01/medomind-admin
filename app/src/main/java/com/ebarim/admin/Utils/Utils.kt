package com.ebarim.admin.Utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit

class Utils {
    companion object {
        private var _preferences: SharedPreferences? = null
        private const val LOGGED_KEY = "login"


        private fun getSharedPreferences(context: Context): SharedPreferences {
            if (_preferences == null)
                _preferences = context.applicationContext.getSharedPreferences(
                    "app_pref",
                    Context.MODE_PRIVATE
                )
            return _preferences!!
        }

        fun Context.toast(message: CharSequence) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        @JvmName("setLogged1")
        fun Context.setLogged(v: Boolean) {
            setLogged(this, v)
        }

        private fun setLogged(context: Context, v: Boolean) {
            getSharedPreferences(context).edit {
                putBoolean(LOGGED_KEY, v)
            }
        }

        fun getLogged(context: Context) =
            getSharedPreferences(context).getBoolean(LOGGED_KEY, false)

        @JvmName("getLogged1")
        fun Context.getLogged(): Boolean {
            return getLogged(this)
        }

    }
}