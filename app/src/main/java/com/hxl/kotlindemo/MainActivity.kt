package com.hxl.kotlindemo


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hxl.miuibottomnavigation.BottomNavigationView
import com.hxl.miuibottomnavigation.IItemClickListener
import com.hxl.miuibottomnavigation.Mode
import com.hxl.miuibottomnavigation.build.NavigationBuild
import com.hxl.miuibottomnavigation.extent.dp2Px


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        findViewById<BottomNavigationView>(R.id.bottom)
            .setClickListener(object : IItemClickListener {
                override fun click(index: Int) {

                }
            })
            .init(
                NavigationBuild.Builder(this)
                    .addItem("首页", R.drawable.ic_home, R.drawable.ic_home_s)
                    .addItem("娱乐", R.drawable.ic_game, R.drawable.ic_game_s)
                    .addItem("我的", R.drawable.ic_me, R.drawable.ic_me_s)
                    .setMode(Mode.MODE_MIUI)
                    .setSelectTextColor(Color.RED)
                    .setTextSize(12.dp2Px(this))
                    .setFixedItems(mutableSetOf(1))
                    .build()
            )

    }


}

