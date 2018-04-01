package com.xiuer.taolezi.NetModeLoad

import android.util.Log
import com.google.gson.Gson
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Home.HomeCommentModelS
import com.xiuer.taolezi.Home.HomeModel
import okhttp3.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import rx.Observable
import java.io.IOException

/**
 * Created by xiuer on 2018/3/5.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
//淘乐子 网络数据的加载
interface  IHomeNetLoad{

//    @POST("joke/content")
//    fun homeDataLoad(@Body requestBody: RequestBody):Call<HomeRequestModel>

    @POST("joke/content")
    fun homeDataLoad(@Body requestBody: RequestBody):Observable<HomeModel>

    @POST("joke/content")
    fun homeDataLoad(@Field("userid") userid:Int, @Field("type") type:Int, @Field("page") page:Int):Observable<HomeModel>
}


interface  IHomeRequestResult{
    fun  success(model:HomeModel)
    fun  faile(error:String)
}

interface  IHomeCommentRequestResult{
    fun  success(model:HomeCommentModelS)
    fun  faile(error:String)
}

interface IRequestResult<in T>{
    fun   success(model:T)
    fun  faile(error:String)
}


class HomeNetLoad {

    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl(TLZConstant.homeURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .build()


        val client = OkHttpClient()
    }

//    ///加载首页数据
//    fun homeLoadData(type: Int, page: Int, result: IHomeRequestResult) {
//
//        val params = mapOf("userid" to 100, "page" to page, "type" to type)
//        NetManger.postRequest(params, successJsonString = { any ->
//            val json: String = any as String
//            val gson = Gson()
//            val j = JSONObject(json)
//            Log.i("HomeNetLoad", "status:${j.opt("status")}")
//
//
//            val model = gson.fromJson<HomeModel>(json, HomeModel::class.java)
//            //val model = TLZTool.stringTojson(json,HomeModel::class.java)
//            result.success(model)
//
//        })
//     }
//    }
//
//    fun  loadHomeData(params:Map<String,Any>,success:(models:MutableList<HomeItemModel>)->Unit,failed:()->Unit){
//        NetManger.postRequest(params,
//                {
//                    any ->
//
//                    val  json:String = any as String
//                    val  gson =  Gson()
//                    val  j =  JSONObject(json)
//                    if  (j.opt("status") == 500){
//                        failed()
//                    }
//                    else{
//                        val model = gson.fromJson<HomeModel>(json,HomeModel::class.java)
//                        success(model.data)
//                    }
//                    Log.i("HomeNetLoad","status:${j.opt("status")}")
//
//
//
//                    //val model = TLZTool.stringTojson(json,HomeModel::class.java)
//
//                })
//
//    }


    ///加载评论数据
    fun commentLoadData(commentId: Int, page: Int, userId: Int, result: IHomeCommentRequestResult) {

        val json = JSONObject()
        json.put("id", commentId)
        json.put("page", page)
        json.put("userid", userId)
        val body = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val request = Request.Builder().url("${TLZConstant.homeURL}${TLZConstant.JOKECOMMENTURL}")
                .post(body)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val gson = Gson()
                //Log.i("success", "${"TEST:"} :${String(response?.body()!!.bytes())} ")
                val model = gson.fromJson<HomeCommentModelS>(String(response?.body()!!.bytes()), HomeCommentModelS::class.java)
                result.success(model)
            }

        })

    }


    //注册
    fun register(udid: String, pwd: String, result: IRequestResult<String>) {

        val json = JSONObject()
        json.put("password", pwd)
        json.put("usericon", "icon1.jpg")
        json.put("deviceid", udid)
        val body = RequestBody.create(MediaType.parse("application/json"), json.toString())
        val request = Request.Builder().url(TLZConstant.USERREGISTERURL)
                .post(body)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val gson = Gson()
                //  val model = gson.fromJson<HomeCommentModelS>(String(response?.body()!!.bytes()),HomeCommentModelS::class.java)
                Log.i("success", "${"TEST:"} :${String(response?.body()!!.bytes())} ")
                result.success("")
            }

        })

    }


}










