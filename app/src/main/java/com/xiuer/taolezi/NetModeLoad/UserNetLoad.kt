package com.xiuer.taolezi.NetModeLoad

import android.util.Log
import com.google.gson.Gson
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Home.HomeCommontModel
import com.xiuer.taolezi.Home.HomeItemModel
import com.xiuer.taolezi.Home.HomeModel
import com.xiuer.taolezi.User.LoginActivity
import com.xiuer.taolezi.User.UserModel
import com.xiuer.taolezi.User.UserRegisterM
import org.json.JSONObject

/**
 * Created by xiuer on 2018/3/16.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object UserNetLoad {

        //NetManger.client.
        //注册
        fun register(params:Map<String,Any>,success:(UserModel)->Unit,failed:(String)->Unit){

            NetManger.postRequest(TLZConstant.USERREGISTERURL,params,{
                any ->
                 val r:String = any as String
                Gson().fromJson(r,UserRegisterM::class.java)
                 Log.i("register",r)

                val  gson =  Gson()
                val  j =  JSONObject(r)
                if  (j.opt("status") == 500){
                    failed(TLZConstant.netErr)
                }
                else{
                    val model = gson.fromJson<UserRegisterM>(r, UserRegisterM::class.java)
                    success(model.data)
                }
                Log.i("HomeNetLoad","status:${j.opt("status")}")

            } )
        }



        fun login(userId:String,pwd:String,success:(UserModel)->Unit,failed:(String)->Unit){

            //["userid":Int(account!)!,"pwd":password!],

            val params =  mapOf<String,Any>("userid" to userId.toInt(),"pwd" to pwd);
            NetManger.postRequest(TLZConstant.USERLOGINURL,params,{
                any ->
                val r:String = any as String
                val  j =  JSONObject(r)
                Log.i("login","${j}")
                val  gson =  Gson()
                if  (j.opt("code") == 102){
                    failed(TLZConstant.netErr)
                }
                else if(j.opt("code") == 100){
                    val model = gson.fromJson<UserRegisterM>(r, UserRegisterM::class.java)
                    success(model.data)
                }
                else{
                    failed(TLZConstant.netErr)
                }
            })
        }



        fun  collect(userId:Int,jokeid:Int, success: () -> Unit){
           // "userid":MConfig.userInfo.mUserID,"jokeid":self.mQuestionModel.mId
            val params =  mapOf<String,Any>("userid" to userId,"jokeid" to jokeid);

            NetManger.postRequest(TLZConstant.COLLECTJOKEURL,params,{
              any ->
                val r:String = any as String
                val  j =  JSONObject(r)
                if(j.opt("code") == 100){
                    Log.i("收藏","${j}")
                     success()
                }


            })
        }


        fun  collects(page:Int,userId:Int, success: (MutableList<HomeItemModel>) -> Unit, failed: (String) -> Unit){
            val params: Map<String, Any> = mapOf("userid" to userId,"page" to page )

            NetManger.postRequest(TLZConstant.MYCOLLECTJOKEURL,params,{
                any ->
                val r:String = any as String
                val  j =  JSONObject(r)
                Log.i("login","${j}")
                val  gson =  Gson()
                if  (j.opt("status") == 500){
                    failed("服务器错误")
                }
                else{
                    val model = gson.fromJson<HomeModel>(r, HomeModel::class.java)
                    success(model.data)
                }

            })
        }

       fun  dunzis(page:Int,userId:Int, success: (MutableList<HomeItemModel>) -> Unit, failed: (String) -> Unit){
           val params: Map<String, Any> = mapOf("userid" to userId,"page" to page )

           NetManger.postRequest(TLZConstant.MYJOKEURL,params,{
            any ->
            val r:String = any as String
            val  j =  JSONObject(r)
            Log.i("login","${j}")
            val  gson =  Gson()
            if  (j.opt("status") == 500){
                failed("服务器错误")
            }
            else if(j.opt("code") == 100){

                val model = gson.fromJson<HomeModel>(r, HomeModel::class.java)
                success(model.data)
            }

        })
    }


    /**
     *  评论
     */
    fun  comment(userId:Int,jokeid:Int,content:String, success: (HomeCommontModel) -> Unit, failed: (String) -> Unit){

         val params =  mapOf<String,Any>("userid" to userId,"jokeid" to jokeid,"content" to content,"timestamp" to System.currentTimeMillis().toInt() );

        NetManger.postRequest(TLZConstant.COMMENTADDURL,params,{
            any ->
            val r:String = any as String
            val  j =  JSONObject(r)
            Log.i("comment 结果","${j}")
            if(j.opt("code") == 100){
            val  gson =  Gson()
            val model = gson.fromJson<HomeCommontModel>(j.get("data").toString(), HomeCommontModel::class.java)
                success(model)
            }



        })
    }


    /**
     * 上传名字
     */

    fun updateName(userId: Int,name:String,success:(UserModel)->Unit){

        var parames = mapOf<String,Any>("userid" to userId,"nickname" to name)
        NetManger.postRequest(TLZConstant.USERMODIFYUSERINFO,parames,{
             any->
            val r:String = any as String
            val  j =  JSONObject(r)
            Log.i("上传名字","${j}")
            if(j.opt("code") == 100){
                val  gson =  Gson()
                val model = gson.fromJson<UserModel>(j.get("data").toString(), UserModel::class.java)
                success(model)
            }

        })

    }

    /**
     * 上传性别
     */
   fun updateSex(userId: Int,sex:Int,success:(UserModel)->Unit,failed: (String) -> Unit){

        var parames = mapOf<String,Any>("userid" to userId,"sex" to sex)
        NetManger.postRequest(TLZConstant.USERMODIFYUSERINFO,parames,{
            any->
            val r:String = any as String
            val  j =  JSONObject(r)

            Log.i("上传性别","${j}")
            if(j.opt("code") == 100){
                val  gson =  Gson()
                val model = gson.fromJson<UserModel>(j.get("data").toString(), UserModel::class.java)
                success(model)
            }
            else{
                failed(TLZConstant.netErr)
            }

        })
    }

    /**
     * 提交意见
     */
    fun updateSuggest(content:String,userId: Int,contact_num:String,success:(UserModel)->Unit,failed: (String) -> Unit){

        val params = mapOf<String,Any>("content" to content,"userid" to userId,"contact_num" to contact_num)
        NetManger.postRequest(TLZConstant.SUGGEST,params,{
            any ->
            val r:String = any as String
            val  j =  JSONObject(r)
            Log.i("提交意见","${j}")
            if(j.opt("code") == 100){

            }
        })

    }





}
