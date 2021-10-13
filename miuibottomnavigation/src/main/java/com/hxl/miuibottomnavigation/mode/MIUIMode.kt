package com.hxl.miuibottomnavigation.mode

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.hxl.miuibottomnavigation.BottomNavigationView
import kotlin.math.abs
import kotlin.math.min

class MIUIMode(bottomNavigationView: BottomNavigationView) : BaseMode(bottomNavigationView) {

    override fun draw(canvas: Canvas) {
        for (i in bottomNavigationView.navigationBuild.itemList.indices) {
            val itemRect = getItemRect(i)
            val path = Path()
            path.moveTo(itemRect.mid - circleSize, bodyMarginTop)
            path.quadTo(
                itemRect.mid.toFloat(),
                bezierHeightMap[i]!!.toFloat(),
                itemRect.mid + circleSize,
                bodyMarginTop
            )
            canvas.drawPath(path, Paint().apply { color = Color.WHITE })
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
        val start = bezierHeightMap[index]!!.toFloat()
        val end = bezierMaxHeight
        val valueAnimator = ValueAnimator.ofFloat(start, end)

        valueAnimator.addUpdateListener {
            val fl = it.animatedValue as Float
            bezierHeightMap[currentIndex] = (bezierMaxHeight + bodyMarginTop - fl)
            bezierHeightMap[index] = fl
            val progress = 1 - (fl + ((abs(min(start, end))))) / (abs(start) + abs(end))

            iconHeightMap[currentIndex] =
                iconMaxTop + progress * (iconDefaultTop - iconMaxTop)
            iconHeightMap[index] = iconDefaultTop - progress * (iconDefaultTop - iconMaxTop)
            bottomNavigationView.invalidate()
        }
        startValueAnimator(valueAnimator,index)
    }


}