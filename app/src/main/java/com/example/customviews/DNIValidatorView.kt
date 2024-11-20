package com.example.customviews

import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.regex.Pattern

class DNIValidatorView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs), TextWatcher {

    var successColor: Int
    var errorColor: Int

    var tvErrorCode: TextView
    var etDNI: EditText

    init {
        inflate(context, R.layout.dni_validator, this)

        tvErrorCode = findViewById(R.id.tvErrorCode)
        etDNI = findViewById(R.id.etDNI)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DNIValidator)
        tvErrorCode.text = attributes.getString(R.styleable.DNIValidator_textError)
        errorColor = attributes.getColor(
            R.styleable.DNIValidator_underlineErrorColor,
            ContextCompat.getColor(context, R.color.red)
        )
        successColor = attributes.getColor(
            R.styleable.DNIValidator_underlineSuccessColor,
            ContextCompat.getColor(context, R.color.green)
        )
        attributes.recycle()
        etDNI.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (DNIIsValid(s.toString())) {
            tvErrorCode.visibility = View.INVISIBLE
            etDNI.background.setColorFilter(successColor, PorterDuff.Mode.SRC_IN)
        } else {
            tvErrorCode.visibility = View.VISIBLE
            etDNI.background.setColorFilter(errorColor, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    private fun DNIIsValid(dni: String): Boolean {
        val dniPattern = Pattern.compile("^[0-9]{8}[A-Za-z]$")
        if (!dniPattern.matcher(dni).matches()) return false

        val dniNumber = dni.substring(0, 8).toIntOrNull() ?: return false
        val dniLetter = dni.last().uppercaseChar()

        val validLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
        val calculatedLetter = validLetters[dniNumber % 23]

        return dniLetter == calculatedLetter
    }
}
