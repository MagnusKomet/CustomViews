package com.example.customviews

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat


class OddEvenEditText : AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inputType = InputType.TYPE_CLASS_NUMBER

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if(!p0.isNullOrEmpty()){
                    if(p0.toString().toDouble()%2 == 0.0){
                        background.setTint(ContextCompat.getColor(context, R.color.blue))
                    }else{
                        background.setTint(ContextCompat.getColor(context, R.color.red))
                    }
                }

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

}