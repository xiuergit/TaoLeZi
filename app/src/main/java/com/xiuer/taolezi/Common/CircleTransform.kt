package com.xiuer.taolezi.Common

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation



/**
 * Created by xiuer on 2018/4/1.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class CircleTransform(context: Context, dp: Int = 4):BitmapTransformation(context) {

        val radius = Resources.getSystem().getDisplayMetrics().density*dp

        override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
            return roundCrop(pool, toTransform)
        }

        override fun getId(): String {
            return javaClass.name + Math.round(radius)
        }



            private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
                if (source == null) return null

                var result: Bitmap? = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)
                if (result == null) {
                    result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
                }

                val canvas = Canvas(result)
                val paint = Paint()
                paint.setShader(BitmapShader(source,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP))


                //paint.setShader(BitmapShader(source, , ))
                paint.setAntiAlias(true)
                val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
                canvas.drawRoundRect(rectF, radius, radius, paint)
                paint.strokeWidth = 20f
                canvas.drawLine(0F,0F,10F,10F,paint)
                return result
            }


}