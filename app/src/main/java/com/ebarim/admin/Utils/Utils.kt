package com.ebarim.admin.Utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit

class Utils {
    companion object {
        private var _preferences: SharedPreferences? = null
        private const val LOGGED_KEY = "login"
        const val TOKEN_KEY = "token"
        const val AUTH_TYPE = "type"
        const val USER_NAME = "userName"

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

        @JvmName("setToken1")
        fun Context.setToken(token: CharSequence) {
            setToken(this, token)
        }

        private fun setToken(context: Context, token: CharSequence) {
            getSharedPreferences(context).edit {
                putString(TOKEN_KEY, token.toString())
            }
        }

        @JvmName("setAuthType1")
        fun Context.setAuthType(type: CharSequence) {
            setAuthType(this, type)
        }

        private fun setAuthType(context: Context, type: CharSequence) {
            getSharedPreferences(context).edit {
                putString(AUTH_TYPE, type.toString())
            }
        }

        fun Context.getToken(token: String): String {
            val pref = getSharedPreferences(this)
            if (!pref.contains(token)) throw Exception("Token not found")
            return pref.getString(token, "").toString()
        }

        fun Context.getAuthType(type: String): String {
            val pref = getSharedPreferences(this)
            if (!pref.contains(type)) throw Exception("Auth type not found")
            return pref.getString(type, "").toString()
        }

        @JvmName("setName1")
        fun Context.setName(userName: CharSequence) {
            setName(this, userName)
        }

        private fun setName(context: Context, userName: CharSequence) {
            getSharedPreferences(context).edit {
                putString(USER_NAME, userName.toString())
            }
        }

        fun Context.getName(userName: String): String {
            val pref = getSharedPreferences(this)
            if (!pref.contains(userName)) throw Exception("Username not found")
            return pref.getString(userName, "").toString()
        }
    }
}