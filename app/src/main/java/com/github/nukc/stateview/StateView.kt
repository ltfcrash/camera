package com.github.nukc.stateview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import virtual.camera.app.R

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar = ProgressBar(context).apply {
        isIndeterminate = true
        contentDescription = context.getString(R.string.state_loading)
    }
    private val emptyTextView: TextView = TextView(context).apply {
        text = context.getString(R.string.state_empty)
        setTextAppearance(android.R.style.TextAppearance_Medium)
        gravity = Gravity.CENTER
        isVisible = false
    }

    init {
        isClickable = true
        isFocusable = true

        addView(progressBar, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        })
        addView(emptyTextView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        })

        showContent()
    }

    fun showLoading() {
        visibility = View.VISIBLE
        progressBar.isVisible = true
        emptyTextView.isVisible = false
    }

    fun showEmpty() {
        visibility = View.VISIBLE
        progressBar.isVisible = false
        emptyTextView.isVisible = true
    }

    fun showContent() {
        visibility = View.GONE
        progressBar.isVisible = false
        emptyTextView.isVisible = false
    }
}
