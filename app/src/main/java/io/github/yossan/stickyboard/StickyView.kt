package io.github.yossan.stickyboard

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class StickyView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    companion object {
        fun new(context: Context): StickyView {
            return inflate(context, R.layout.sticky, null) as StickyView
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setBackgroundColor(Color.YELLOW)
    }
}