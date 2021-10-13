package com.hxl.miuibottomnavigation.mode

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.hxl.miuibottomnavigation.BottomNavigationView
import kotlin.math.max
import kotlin.math.min

class ScrollMode(bottomNavigationView: BottomNavigationView) : BaseMode(bottomNavigationView) {

    private var currentStart = 0f
    private var currentEnd = 0f

    override fun draw(canvas: Canvas) {
        val path = Path()
        path.moveTo(currentStart, bodyMarginTop)
        currentEnd = currentStart + circleSize * 2
        path.quadTo(
            currentStart + circleSize,
            bezierMaxHeight,
            currentEnd,
            bodyMarginTop
        )
        canvas.drawPath(path, Paint().apply { color = Color.WHITE })

        for (i in bottomNavigationView.navigationBuild.itemList.indices) {
            drawIcon(
                bottomNavigationView.navigationBuild.itemList[i].defIcon,
                bottomNavigationView.navigationBuild.itemList[i].selectIcon,
                i,
                canvas
            )
            drawText(bottomNavigationView.navigationBuild.itemList[i].title, i, canvas)
        }
    }

    override fun handlerClick(index: Int) {
        if (isPlay) {
            return
        }
        if (currentIndex == index) {
            iconHeightMap[index] = iconDefaultTop - 1 * (iconDefaultTop - iconMaxTop)
        }
        val itemRect = getItemRect(currentIndex)
        val start = itemRect.mid - circleSize
        val end = getItemRect(index).mid - (circleSize)
        val valueAnimator = ValueAnimator.ofFloat(start, end)
        valueAnimator.addUpdateListener {
            val fl = it.animatedValue as Float

            val maxValue = max(start, end) - min(start, end)
            val currentValue = fl - min(start, end)
            val progress = 1 - currentValue / maxValue
            currentStart = fl

            if (currentIndex != index) {
                val va = iconMaxTop + progress * (iconDefaultTop - iconMaxTop)
                val vb = iconDefaultTop - progress * (iconDefaultTop - iconMaxTop)
                iconHeightMap[index] = if (start < end) va else vb
                iconHeightMap[currentIndex] = if (start > end) va else vb
            }
            bottomNavigationView.invalidate()
        }
        startValueAnimator(valueAnimator, index)
    }


}