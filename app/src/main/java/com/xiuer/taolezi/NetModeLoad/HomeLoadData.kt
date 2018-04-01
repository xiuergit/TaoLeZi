package com.xiuer.taolezi.NetModeLoad

import com.google.gson.Gson
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Home.HomeItemModel
import com.xiuer.taolezi.Home.HomeModel
import org.json.JSONObject

/**
 * Created by xiuer on 2018/3/16.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object HomeLoadData {
    ///加载首页数据
    fun  homeData(type: Int, page: Int, success:(models:MutableList<HomeItemModel>)->Unit, failed:()->Unit){
        val params = mapOf("userid" to 100, "page" to page, "type" to type)
        NetManger.postRequest(TLZConstant.ALLJOKECONTENTURL,params,{
            any ->
            val  json:String = any as String
            if (json == TLZConstant.TimeOut){
                failed()
            }else{
                val  gson =  Gson()
                val  j =  JSONObject(json)
                if  (j.opt("status") == 500){
                    failed()
                }
                else{
                    val model = gson.fromJson<HomeModel>(json,HomeModel::class.java)
                    success(model.data)
                }
            }
        })
    }

    fun  commentData(){

    }
}