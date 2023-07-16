package com.ssong_develop.snackbarwithkeyboardtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtView = findViewById<EditText>(R.id.test_edt)
        val navigateButton = findViewById<Button>(R.id.navigate_btn)
        val snackBar = findViewById<FadingSnackBar>(R.id.test_snack_bar)

        navigateButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        edtView.doOnTextChanged { text, start, before, count ->
            snackBar.show(
                messageText = "키보드 입력이다아ㅏㅏ~~"
            )
        }
    }
}