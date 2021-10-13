package com.hxl.miuibottomnavigation.build

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import com.hxl.miuibottomnavigation.Mode
import com.hxl.miuibottomnavigation.ViewItem
import com.hxl.miuibottomnavigation.extent.dp2Px
import com.hxl.miuibottomnavigation.mode.BaseMode
import com.hxl.miuibottomnavigation.utils.BitmapUtils

class NavigationBuild private constructor() {

    var mode: BaseMode? = null

    var modeValue: Int = Mode.MODE_MIUI.value

    var selectTextColor: Int = Color.BLACK

    var defaultTextColor: Int = Color.BLACK

    var itemList: MutableList<ViewItem> = mutableListOf()

    var defaultSelectIndex = 0
    var textSize = 0

    var fixedItems = mutableSetOf<Int>()

    class Builder(var context: Context) {
        private var instance = NavigationBuild()


        fun build(): NavigationBuild {
            if (instance.textSize == 0) {
                instance.textSize = (9).dp2Px(context)
            }
            return instance
        }

        fun setMode(mode: Mode): Builder {
            instance.modeValue = mode.value
            return this
        }

        fun setSelectTextColor(value: Int): Builder {
            instance.selectTextColor = value
            return this
        }

        fun setDefaultTextColor(value: Int): Builder {
            instance.defaultTextColor = value
            return this
        }

        fun addItem(title: String, resId: Int): Builder {
            return addItem(title, resId, resId)
        }

        fun addItem(title: String, defResId: Int, sedResId: Int): Builder {
            return addItem(
                title,
                BitmapUtils.loadBitmap(defResId, context.resources!!),
                BitmapUtils.loadBitmap(sedResId, context.resources!!)
            )
        }

        fun addItem(title: String, bitmap: Bitmap): Builder {
            return addItem(title, bitmap, bitmap)
        }

        fun addItem(title: String, defBitmap: Bitmap, selectBitmap: Bitmap): Builder {
            instance.itemList.add(ViewItem(title, defBitmap, selectBitmap))
            return this
        }

        fun setDefaultSelectIndex(index: Int): Builder {
            instance.defaultSelectIndex = index
            return this
        }

        fun setTextSize(int: Int): Builder {
            instance.textSize = int
            return this
        }

        fun setFixedItems(id: Set<Int>): Builder {
            instance.fixedItems.clear()
            instance.fixedItems.addAll(id)
            return this
        }


    }
}