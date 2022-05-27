package com.example.mindjoy.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.mindjoy.R

class ButtonCheckNow : AppCompatButton {
    private lateinit var checkNowButton: Drawable

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = checkNowButton
    }

    private fun init(){
        checkNowButton = ContextCompat.getDrawable(context, R.drawable.bg_button_checknow) as Drawable
    }
}