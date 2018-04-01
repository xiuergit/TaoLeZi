package com.xiuer.taolezi.NetModeLoad

import com.xiuer.taolezi.Common.TLZConstant
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

/**
 * Created by xiuer on 2018/3/16.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object NetManger {
    val client = OkHttpClient()

    fun  postRequest(url:String,params: Map<String, Any>, successJsonString:(Any)->Unit){
        val  json = JSONObject()
        for (key in params.keys){
            json.put(key,params[key])
        }
        val  body  = RequestBody.create(MediaType.parse("application/json"),json.toString())
        val  request = Request.Builder().url("${TLZConstant.homeURL}${url}")
                .post(body)
                .build()
           client.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call?, e: IOException?) {
                  // e?.printStackTrace()
                  successJsonString(TLZConstant.TimeOut)
            }
            override fun onResponse(call: Call?, response: Response?) {
                val  jsonstring = String(response?.body()!!.bytes())
                successJsonString(jsonstring)

            }
        })
    }

    //上传 图片加 文字
    fun upload(url:String,params:Map<String,String>,imageString:String,successJsonString:(Any)->Unit){

        val  json = JSONObject()
        for (key in params.keys){
            json.put(key,params[key])
        }
        val body  = RequestBody.create(MediaType.parse("application/json"),json.toString())

        val headers = Headers.of("content-type","multipart/form-data")

        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", "HeadPortrait.jpeg",
                        RequestBody.create(MediaType.parse("image/png"), imageString))

          for (param in params){
              builder.addFormDataPart(param.key,param.value)
          }

        val  request = Request.Builder().url("${TLZConstant.homeURL}${url}")
                .addHeader("content-type","multipart/form-data")
                .post(builder.build())
                .build()

        client.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call?, e: IOException?) {
              //  e?.printStackTrace()
                successJsonString(TLZConstant.TimeOut)
            }
            override fun onResponse(call: Call?, response: Response?) {
                val  jsonstring = String(response?.body()!!.bytes())

                 successJsonString(jsonstring)


            }
        })


       // val body = RequestBody.create(MediaType.parse("multipart/form-data"),byteArray)

    }



}