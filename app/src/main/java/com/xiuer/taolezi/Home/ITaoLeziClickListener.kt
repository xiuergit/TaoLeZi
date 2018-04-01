package com.xiuer.taolezi.Home

import android.view.View

/**
 * Created by xiuer on 2018/3/14.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
interface ITaoLeziClickListener {
    /**
     * 首页 点击后 传回 homeItemModel
     */
    fun onHomeItemClickListener(v: View,position:Int,homeModel: HomeItemModel)
}