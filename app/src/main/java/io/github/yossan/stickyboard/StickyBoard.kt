package io.github.yossan.stickyboard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import java.lang.Float.max
import java.lang.Float.min

class StickyBoard(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        TODO("Not yet implemented")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (child in children) {
            child.layout(0,
                0,
                100,
                100)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    var begin = PointF()
    var rect = RectF()

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when(e.action) {
            MotionEvent.ACTION_DOWN -> {
                begin = PointF(e.x, e.y)
            }
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_UP -> {
                val end = PointF(e.x, e.y)
                rect = RectF().apply {
                    left = min(begin.x, end.x)
                    top = min(begin.y, end.y)
                    right = max(begin.x, end.x)
                    bottom = max(begin.y, end.y)
                }
                invalidate()

                if(e.action == MotionEvent.ACTION_UP) {
                    val stickyView = StickyView.new(context).apply {
                        x = rect.left
                        y = rect.top
                        layoutParams = LayoutParams(rect.width().toInt(), rect.height().toInt())
                    }.also {
                        addView(it)
                    }
                }
            }
        }
        return true
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        val paint = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.RED
        }

        canvas.drawRect(rect, paint)
    }
}