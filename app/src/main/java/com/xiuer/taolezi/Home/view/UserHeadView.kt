package com.xiuer.taolezi.Home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.user_head_view_layout.view.*

/**
 * Created by xiuer on 2018/3/20.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class UserHeadView:RelativeLayout {

    fun setData(title:String,time:String,iconUrl:String){
        mUMName.text = title
        mUMTime.text = time
        ImageNetLoad.loadImage(context,iconUrl,mUMImage)
    }
    init {
        LayoutInflater.from(context).inflate(R.layout.user_head_view_layout,this)
    }
    constructor(context: Context):this(context,null){}

    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        super.onLayout(p0, p1, p2, p3, p4)
    }
}