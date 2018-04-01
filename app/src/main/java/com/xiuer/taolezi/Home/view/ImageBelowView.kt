package com.xiuer.taolezi.Home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.image_below.view.*

/**
 * Created by xiuer on 2018/3/20.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class ImageBelowView:LinearLayout {


    fun  setData(title:String,imageUrl:String){
        mCMContext.text = title
        ImageNetLoad.loadImage(context,imageUrl,mCMImage)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.image_below,this)
    }
    constructor(context: Context):this(context,null){}

    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        super.onLayout(p0, p1, p2, p3, p4)
    }
}