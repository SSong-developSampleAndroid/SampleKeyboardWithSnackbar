package com.ssong_develop.snackbarwithkeyboardtest

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

// adjustPan의 경우에 사실상 소프트 키보드 값이 정해져있다고 판단해야 한다

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val rootView = findViewById<ConstraintLayout>(R.id.root)
        val edtView = findViewById<EditText>(R.id.test_edt)
        val navigateButton = findViewById<Button>(R.id.navigation_btn)
        val snackBar = findViewById<FadingSnackBar>(R.id.test_snack_bar)

        fun getKeyboardHeight(): Int {
            var height = -1

            return height
        }

        navigateButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        rootView.doOnApplyWindowInsets { view, insets, padding ->
            val systemInsets = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            view.updatePadding(bottom = padding.bottom + systemInsets.bottom)
        }

        KeyboardVisibilityEvent.setEventListener(this, object: KeyboardVisibilityEventListener {
            override fun onVisibilityChanged(isOpen: Boolean) {
                if (isOpen) {
                    rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            val rect = Rect()
                            rootView.getWindowVisibleDisplayFrame(rect)

                            val screenHeight = rootView.height
                            val keypadHeight = screenHeight - rect.bottom

                            snackBar.updatePadding(bottom = keypadHeight + 320)
                            rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    })
                } else {
                    snackBar.updatePadding(bottom = 0)
                }
            }
        })

        edtView.doOnTextChanged { text, start, before, count ->
            snackBar.show(
                messageText = "hello!"
            )
        }
    }
}