package woowacourse.paint.presentation.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import woowacourse.paint.R

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lines: MutableList<Line> by lazy { mutableListOf() }
    private var currentPath: Path = Path()
    private var currentPaint: Paint = Paint()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initializePaint()
    }

    private fun initializePaint() {
        currentPaint.run {
            color = ContextCompat.getColor(context, DEFAULT_COLOR_RES_ID)
            strokeWidth = DEFAULT_STROKE
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                lines.add(Line(currentPath, currentPaint))
                resetLine()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun resetLine() {
        currentPath = Path()
        currentPaint = Paint(currentPaint)
    }

    fun changePaintColor(
        @ColorRes colorResId: Int,
    ) {
        currentPaint.color = ContextCompat.getColor(context, colorResId)
    }

    fun changeOvalSize(ovalSize: Float) {
        currentPaint.strokeWidth = ovalSize
    }

    companion object {
        private const val DEFAULT_STROKE = 10.0f
        private val DEFAULT_COLOR_RES_ID = R.color.red
    }
}
