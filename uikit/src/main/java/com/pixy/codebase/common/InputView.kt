package com.pixy.codebase.common

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import com.pixy.codebase.extensions.shape
import com.pixy.codebase.providers.StyleInterface
import com.pixy.codebase.styleProvider
import com.pixy.codebase.utils.OneArgCallback
import com.pixy.codebase.utils.dpToPx
import com.pixy.codebase.utils.emptyString

/**
 * Created by emadmahouti on 6/9/23
 */
class InputView(context: Context) : AppCompatEditText(context) {
    enum class Type { TEXT, PASSWORD, EMAIL, NUMBER, DECIMAL }

    private var tempText: String? = null

    val text: String = this.getText().toString()

    init {
        setStyle(styleProvider.input())
        setPadding(8.dpToPx)
    }

    fun setStyle(style: StyleInterface) {
        val shapeDrawable = style.shapeDrawable
        setTextColor(style.textColor)
        shape(
            shapeDrawable.backgroundColor,
            shapeDrawable.borderColor,
            shapeDrawable.borderSize,
            shapeDrawable.corners
        )
    }

    fun setType(type: Type) {
        inputType = when (type) {
            Type.PASSWORD -> (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            Type.EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            Type.NUMBER -> InputType.TYPE_CLASS_NUMBER
            Type.TEXT -> InputType.TYPE_CLASS_TEXT
            Type.DECIMAL -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        }
    }

    fun clear() {
        setText(emptyString)
    }

    fun addOnTextChangeListener(onChange: OneArgCallback<String>) {
        addTextChangedListener {
            if(it.toString() != tempText)
                onChange.invoke("$it")

            tempText = it.toString()
        }
    }

    fun setLinesCount(linesCount: Int) {
        maxLines = linesCount
        isSingleLine = linesCount == 1
        setLines(linesCount)
        imeOptions = EditorInfo.IME_ACTION_DONE
        setRawInputType(InputType.TYPE_CLASS_TEXT)
    }

    override fun onSaveInstanceState(): Parcelable {
        val state = super.onSaveInstanceState()
        return HelperTextViewSaveState(state).also {
            it.text = getText().toString()
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as HelperTextViewSaveState
        tempText = savedState.text

        super.onRestoreInstanceState(savedState.superState)
    }

    private class HelperTextViewSaveState(parcelable: Parcelable?): BaseSavedState(parcelable) {
        var text: String? = null
    }
}