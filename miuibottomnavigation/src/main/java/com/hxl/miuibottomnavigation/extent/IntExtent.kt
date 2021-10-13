package com.hxl.miuibottomnavigation.extent

import android.content.Context

fun Int.dp2Px(context: Context): Int {
    return (this * (context.resources.displayMetrics.density) + 0.5f).toInt()
}