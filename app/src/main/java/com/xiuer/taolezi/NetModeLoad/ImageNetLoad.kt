package com.xiuer.taolezi.NetModeLoad

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xiuer.taolezi.Common.CircleTransform
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.R
import com.xiuer.taolezi.User.UserModel

/**
 * Created by xiuer on 2018/3/20.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object ImageNetLoad {

    fun  loadImage(mContext:Context,url:String,imageView:ImageView){
        val  uri = Uri.parse(url)
        Glide.with(mContext)
                .load(uri)
                .placeholder(R.drawable.placeholder)
                .into(imageView)
    }

    fun  loadImageAD(mContext:Context,url:String,imageView:ImageView){
        val  uri = Uri.parse(url)
        Glide.with(mContext)
                .load(uri)
                .into(imageView)
    }

    fun  loadCircleImage(mContext:Context,url:String,imageView:ImageView){
        val  uri = Uri.parse(url)
        Glide.with(mContext)
                .load(uri)
                .bitmapTransform(CircleTransform(mContext,5))
                .crossFade(1000)
                .into(imageView)
    }

    fun updateHead(userid:String,imageString:String,success:(UserModel)->Unit){
        var  params = mapOf<String,String>("userid" to userid)
        NetManger.upload(TLZConstant.USERMODIFYPHOTOURL,params,imageString,{
             s: Any ->
            Log.i("上传头像","${s}")

        })
    }
}