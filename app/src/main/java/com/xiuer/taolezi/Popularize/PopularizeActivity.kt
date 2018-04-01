package com.xiuer.taolezi.Popularize

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_popularize.*

class PopularizeActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popularize)

        PopularizeTool.homeAd(this,{
           p ->
            if (p == 0){
                this.finish()
            }

        },{
            p0->
             p0?.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
             root_popularze.addView(p0)
        },{})

        close.setOnClickListener {
            this.finish()
        }


    }
}
