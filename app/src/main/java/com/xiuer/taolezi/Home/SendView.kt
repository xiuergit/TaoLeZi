package com.xiuer.taolezi.Home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by xiuer on 2018/3/2.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class  SendView:View{

    var mPaint:Paint;
    init {
          mPaint = Paint()
          mPaint.strokeWidth = 10f
          mPaint.isAntiAlias = true
    }
    constructor(context:Context) : super(context){}
    constructor(context: Context,attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.drawCircle(0f,0f,100f,mPaint)

    }

}