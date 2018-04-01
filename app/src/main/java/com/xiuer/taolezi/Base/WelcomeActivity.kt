package com.xiuer.taolezi.Base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.xiuer.taolezi.Home.MainActivity
import com.xiuer.taolezi.Popularize.PopularizeTool
import com.xiuer.taolezi.R
import android.view.WindowManager
import android.view.Window.FEATURE_NO_TITLE



class WelcomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(FEATURE_NO_TITLE)
        //隐藏状态栏
        //定义全屏参数
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag)
        setContentView(R.layout.activity_welcome)

        Thread({
           // Thread.sleep(1000)
            PopularizeTool.homeAd(this,{
                startActivity(Intent(this,MainActivity::class.java))
            },{
                V ->

                setContentView(V)
            },{
                startActivity(Intent(this,MainActivity::class.java))
            })
        }).start()
    }

    override fun onStart() {
        super.onStart()
        Log.i("WelcomeActivity","onStart() {)")
    }

    override fun onStop() {
        super.onStop()
        Log.i("WelcomeActivity","onStOP() {)")
        this.finish()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i("WelcomeActivity","onAttachedToWindow()")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.i("WelcomeActivity","onDetachedFromWindow()")
    }

}
