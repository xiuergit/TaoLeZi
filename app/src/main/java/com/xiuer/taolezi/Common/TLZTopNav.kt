package com.xiuer.taolezi.Common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.top_nav_layout.view.*

/**
 * Created by xiuer on 2018/3/19.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class TLZTopNav : LinearLayout{

    fun  setTitle(title:String){
        mNavTitle.text = title
    }

    fun  backView():ImageButton{
        return mBack
    }

    fun  rightView():TextView{
        return mNavQuit
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.top_nav_layout,this)
    }
    constructor(context: Context):this(context,null){}

    constructor(context: Context,attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        super.onLayout(p0, p1, p2, p3, p4)
    }
}