package com.xiuer.taolezi.User

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.user_set_item_layout.view.*

/**
 * Created by xiuer on 2018/3/12.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
interface  IUserItemSetView{
    fun onItemClick()
}

 class UserItemSetView:LinearLayout {

     var onClick:IUserItemSetView? = null

     constructor(context: Context):this(context, null){}

    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet){
    }

    init {
      LayoutInflater.from(context).inflate(R.layout.user_set_item_layout,this)
    }

    fun  setItem(icon:Int,title:String,onItemClick:IUserItemSetView){
        onClick = onItemClick
        mUserSetTitle.text = title

        var leftDrawle:Drawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leftDrawle =  resources.getDrawable(R.drawable.right_arrow,context.theme)
        }
        else{
            leftDrawle = resources.getDrawable(R.drawable.right_arrow)
        }

        mUserSetIcon.setImageResource(icon)
        //mUserSetTitle.setCompoundDrawablesRelative(leftDrawle,null,leftDrawle, leftDrawle)
        //mUserSetTitle.setCompoundDrawables(leftDrawle,leftDrawle,leftDrawle,leftDrawle)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        onClick?.onItemClick()
        return false
    }
}