package com.xiuer.taolezi.Home.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.xiuer.taolezi.R
import com.xiuer.taolezi.R.drawable.*
import kotlinx.android.synthetic.main.home_bottom_layout.view.*



/**
 * Created by xiuer on 2018/3/1.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */

 interface  IBottomNavItemClickEvent{
    fun onItemClick(item:View)
}

 class  BottomNavView:LinearLayout{

    var onClick: IBottomNavItemClickEvent? = null
     lateinit var bitmap:Bitmap
    init {
        LayoutInflater.from(context).inflate(R.layout.home_bottom_layout,this)
        mBottomItemHome.setOnClickListener{
           // Toast.makeText(context,"点击 home", Toast.LENGTH_LONG).show()
            selectHome()
        }

        mBottomItemSend.setOnClickListener(){
           // Toast.makeText(context,"点击 send", Toast.LENGTH_LONG).show()
             mBottomItemSend.tag = 102
             onClick?.onItemClick(mBottomItemSend)
        }

        mBottomItemUser.setOnClickListener {
           // Toast.makeText(context,"点击 user", Toast.LENGTH_LONG).show()
            selectUser()
        }

//        bitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_4444)
//        var canvas = Canvas(bitmap)
//
//        var  paint = Paint()
//        paint.isAntiAlias = true
//        paint.strokeWidth = 10f
//        // paint.color = 0xff44b391.toInt()
//        paint.color = Color.CYAN
//        canvas.drawCircle(0f,10f,200f,paint)

        this.setBackgroundColor(Color.WHITE)

    }

     fun selectHome(){
        // mBottomItemHome.isSelected = !mBottomItemHome.isSelected
         //if (mBottomItemHome.isSelected){
             mHomeIcon.setImageResource(home_selected)
             mHomeTitle.setTextColor(resources.getColor(R.color.colorMain))
             mBottomItemHome.setBackgroundColor(Color.parseColor("#1F81d4fa"))
             mUserIcon.setImageResource(my)
             mUserTitle.setTextColor(resources.getColor(R.color.black))
             mBottomItemUser.setBackgroundColor(Color.parseColor("#FAF7F7"))

         if (onClick != null){
             mBottomItemHome.tag = 101
             onClick?.onItemClick(mBottomItemHome)
         }
     }



     fun selectUser(){
             mHomeIcon.setImageResource(home)
             mHomeTitle.setTextColor(resources.getColor(R.color.black))
             mBottomItemHome.setBackgroundColor(Color.parseColor("#FAF7F7"))
             mUserIcon.setImageResource(my_selected)
             mUserTitle.setTextColor(resources.getColor(R.color.colorMain))
             mBottomItemUser.setBackgroundColor(Color.parseColor("#2281d4fa"))
         if (onClick != null){
             mBottomItemUser.tag = 103
             onClick?.onItemClick(mBottomItemUser)
         }
     }

    constructor(context: Context):this(context,null){}

    constructor(context: Context,attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
         super.onLayout(p0, p1, p2, p3, p4)
    }

    override fun measureChild(child: View?, parentWidthMeasureSpec: Int, parentHeightMeasureSpec: Int) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec)

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        var  paint = Paint()
         paint.isAntiAlias = true
        paint.color = Color.RED
        paint.color = Color.LTGRAY
        paint.strokeWidth = 2f
        canvas?.drawLine(0f,0f,measuredWidth*0.5f,0f,paint)
        canvas?.drawLine(measuredWidth*0.6f,0f,measuredWidth*1f,0f,paint)

        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.release)
        val width = bitmap.getWidth()
        val height = bitmap.getHeight()
        // 计算缩放比例
        val scaleWidth = measuredHeight*1f/ width*0.8f
        val scaleHeight = measuredHeight*1f/ height*0.8f
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // 得到新的图片
        val newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)

        paint.color = Color.parseColor("#FF8C00")
        paint.style = Paint.Style.STROKE

        paint.color = Color.parseColor("#FFFFFF")
        paint.style = Paint.Style.FILL
        val radius =  newbm.width*0.75f
        val circleX =  measuredWidth*0.5f
        val circleY =  0f
        paint.color = Color.parseColor("#FF8C00")
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE
        var rect = Rect((circleX-radius).toInt(),(circleY-radius).toInt(),(circleX+radius).toInt(),(circleY+radius).toInt())
        canvas?.drawArc(RectF(rect),0f,-180f,true,paint)
        paint.color = Color.parseColor("#FFFAF7F7")
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(measuredWidth*0.5f,0f,radius-2f,paint)
        canvas?.drawBitmap(newbm,measuredWidth*0.5f-newbm.width*0.5f,-newbm.height*0.5f,paint)

    }


}