package com.xiuer.taolezi.Base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import com.xiuer.taolezi.R

open class BaseActivity : AppCompatActivity() {

    val  TAG =  "BaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().statusBarColor = resources.getColor(R.color.colorMain)
        }
        else{

        }
    }

     fun screenRect(): IntArray {
            val screenValue = IntArray(2)
            //获取屏幕的宽与高
            val manager = this.getWindowManager()
            val outMetrics = DisplayMetrics()
            manager.getDefaultDisplay().getMetrics(outMetrics)
            val width = outMetrics.widthPixels
            val height = outMetrics.heightPixels
            screenValue[0] = width
            screenValue[1] = height
            return screenValue

    }
   public fun back(V: View){
        finish()
    }
//   val outMetrics = DisplayMetrics()
//    this.windowManager.defaultDisplay.getMetrics(outMetrics)
//    val width




}
