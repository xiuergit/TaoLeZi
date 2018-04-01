package com.xiuer.taolezi.Base

import android.app.Application
import com.mc.ra.a.McInit
import com.mob.MobSDK

/**
 * Created by xiuer on 2018/3/1.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class  TLZApp:Application(){


    override fun onCreate() {
        super.onCreate()
        McInit.getInstance().init(this.applicationContext,"2c3657d13ebbbe5c5c1a6db02aef3864",true)

       // McInit.getInstance().init(this.applicationContext,"65c4ecb593b8b1bb1238722457feb6b9",true)
        MobSDK.init(this)



    }




}