package com.example.mpmplab3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {
    private val sharedPreferences by lazy {
        getSharedPreferences("user_settings", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settingCheckBox: CheckBox = findViewById(R.id.settingCheckBox)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val saveButton: Button = findViewById(R.id.saveButton)

        val isNotificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", false)
        settingCheckBox.isChecked = isNotificationsEnabled

        val selectedOption = sharedPreferences.getInt("selected_option", R.id.radioOption1)
        radioGroup.check(selectedOption)

        saveButton.setOnClickListener {
            // Save the settings
            val editor = sharedPreferences.edit()
            editor.putBoolean("notifications_enabled", settingCheckBox.isChecked)

            // Save the selected radio button option
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            editor.putInt("selected_option", selectedRadioButtonId)

            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
