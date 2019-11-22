package com.example.darknightsandbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val prefs by lazy { getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTheme()

        dark_theme.setOnClickListener {
            when (getSavedTheme()) {
                THEME_DARK -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                else -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
            }
        }
    }

    private fun initTheme() {
        when (getSavedTheme()) {
            THEME_DARK -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
            else -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
        }
    }

    private fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    private fun saveTheme(theme: Int) = prefs.edit().putInt(KEY_THEME, theme).apply()

    private fun getSavedTheme() = prefs.getInt(KEY_THEME, THEME_UNDEFINED)

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_THEME = "theme"
        const val THEME_UNDEFINED = -1
        const val THEME_LIGHT = 0
        const val THEME_DARK = 1
    }

    override fun recreate() {
        super.recreate()
        Log.d("debug", "color changed!")
    }
}
