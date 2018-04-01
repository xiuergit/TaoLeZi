package com.xiuer.taolezi.Common

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Home.HomeItemModel
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.Popularize.PopularizeTool
import kotlinx.android.synthetic.main.fragment_homeitem.view.*
import java.net.URI

/**
 * Created by xiuer on 2018/3/14.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object TlZHomeAdapterDataSet {

    //内容填充
    fun userContext(view: View, homeModel: HomeItemModel,mContext:Context,isSpread:Boolean){

        with(view){

            if (!isSpread && homeModel.viewtype == 1 && homeModel.is_public == 1){
                imageRightContext(view,homeModel,mContext)
            }else{
                imageBelowContext(view,homeModel,mContext,isSpread)
            }
            mCMReadCount.text = "${homeModel.browsenum}阅读"
            mCMCommentCount.text = "${homeModel.commentnum}评论"
        }
    }


    // 头部填充

    fun  userHead(view: View, homeModel: HomeItemModel,mContext:Context) {
        //头像
        with(view) {

            if (homeModel.userIcon != null && homeModel.userName != "" && homeModel.userName != null) {
                mUMName.text = homeModel.userName

                val  uri = URI.create(homeModel.userIcon)

                ImageNetLoad.loadImage(mContext,homeModel.userIcon,mUMImage)

                mUMTime.text = TLZTool.getStrTime(homeModel.timestamp.toString())
                mCMLastTime.text = homeModel.time

            } else {
                mUserMessage.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
                mCMLastTime.text = ""
            }
        }
    }


    //  图在内容 右边
    fun imageRightContext(v:View,homeModel:HomeItemModel,mContext:Context){
        with(v){
        root_CMContextNoAD.visibility = View.VISIBLE
        root_CMContextISAD.visibility = View.INVISIBLE
        mCMContext1.text = homeModel.content
        if (homeModel.img != "" && homeModel.img != null) {
            ImageNetLoad.loadImage(mContext,homeModel.img,mCMImage1)
        }
        }

    }
    //  图在内容 下边
    fun imageBelowContext(v:View,homeModel:HomeItemModel,mContext:Context,isSpread:Boolean){

        with(v) {
            root_CMContextNoAD.visibility = View.INVISIBLE
            root_CMContextISAD.visibility = View.VISIBLE
            if (isSpread){
                mCMContext.setSingleLine(false)
            }
            else {
                mCMContext.maxLines = 3
            }

            mCMContext.text = homeModel.content

            if (homeModel.img != "" && homeModel.img != null) {

                val scale = homeModel.imgW * 1f / homeModel.imgH * 1f
                val width = TLZTool.screenRect(mContext as Activity)[0]

                val imageW = width - 40
                val imageH = imageW / scale
                ImageNetLoad.loadImage(mContext,homeModel.img,mCMImage)

                PopularizeTool.adBannerImages(mContext,{

                },{
                    p0: View? ->

                    var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    //params.height = 120
                    params.topMargin = 20;

                    root_CMContextISAD.addView(p0,params)
                })


            }
        }
    }



}