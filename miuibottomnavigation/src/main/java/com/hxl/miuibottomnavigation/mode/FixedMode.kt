package com.hxl.miuibottomnavigation.mode

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import com.hxl.miuibottomnavigation.BottomNavigationView

class FixedMode(bottomNavigationView: BottomNavigationView) : BaseMode(bottomNavigationView) {
    override fun draw(canvas: Canvas) {
        Log.i("TAG", "draw: ${bottomNavigationView.navigationBuild.fixedItems}")
        for (i in bottomNavigationView.navigationBuild.itemList.indices) {
            if (bottomNavigationView.navigationBuild.fixedItems.contains(i)) {
                val itemRect = getItemRect(i)
                val path = Path()
                path.moveTo(itemRect.mid - circleSize, bodyMarginTop)
                path.quadTo(
                    itemRect.mid.toFloat(),
                    bezierMaxHeight,
                    itemRect.mid + circleSize,
                    bodyMarginTop
                )
                canvas.drawPath(path, Paint().apply { color = Color.WHITE })
            }
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
        currentIndex = index
        bottomNavigationView.invalidate()
    }

}