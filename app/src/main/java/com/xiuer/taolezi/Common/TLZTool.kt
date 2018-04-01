package com.xiuer.taolezi.Common

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Environment
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.xiuer.taolezi.User.UserModel
import java.io.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*






/**
 * Created by xiuer on 2018/3/13.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object TLZTool {
    ///字符串转时间戳
    fun getTime(timeString: String): String {
        var timeStamp: String? = null
        val sdf = SimpleDateFormat("MM-DD hh:mm")
        val d: Date
        try {
            d = sdf.parse(timeString)
            val l = d.getTime()
            timeStamp = l.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return timeStamp!!
    }

    ///时间戳转字符串
    fun getStrTime(timeStamp: String): String? {
        var timeString: String? = null
        val sdf = SimpleDateFormat("MM-DD hh:mm")
        val l = java.lang.Long.valueOf(timeStamp)!!
        timeString = sdf.format(Date(l))//单位秒
        return timeString
    }

    fun screenRect(context: Activity): IntArray {
        val screenValue = IntArray(2)
        //获取屏幕的宽与高
        val manager = context.windowManager
        val outMetrics = DisplayMetrics()
        manager.getDefaultDisplay().getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        val height = outMetrics.heightPixels
        screenValue[0] = width
        screenValue[1] = height
        return screenValue

    }

    fun setUserLogin(context: Context,isLogin:Boolean) {
        val share = context.getSharedPreferences("Data", Context.MODE_PRIVATE).edit()
        share.putBoolean("isLogin",isLogin)
        share.commit()
    }

    fun isLogin(context: Context):Boolean{
        val share = context.getSharedPreferences("Data", Context.MODE_PRIVATE)
        val  islogin = share.getBoolean("isLogin",false)
        return islogin
    }





    fun getFileUdid(context: Context):String? {

        var udid =  readFileFromLocal("uuid")
        if (udid != null){

            return udid.toString();
        }
        else{
            return   null
        }

    }
    fun saveFileUdid(obj: String) {
      fileSave2Local(obj,"uuid")
    }


    fun  getUdid():String{
         return  UUID.randomUUID().toString();
    }


    //将 string to json
    fun  <T>stringTojson(src:String,mclass:Class<T>):T{
        val  gson =  Gson()
        val model = gson.fromJson<T>(src, mclass.javaClass)
        return  model
    }


    //用户信息
    fun setUserMessage(user:UserModel){
        fileSave2Local(user,"user")
//        val share = context.getSharedPreferences("UserData", Context.MODE_PRIVATE).edit()
//        var arrayByte = ByteArrayOutputStream()
//        val outPutStream = ObjectOutputStream(arrayByte )
//        outPutStream.writeObject(user)
//        val  userString = android.util.Base64.encodeToString(arrayByte.toByteArray(),android.util.Base64.DEFAULT)
//        share.putString("User",userString)
//        share.commit()
    }


    fun  getUserMessage(context: Context):UserModel?{
        // var model = readFileFromLocal("obj") as UserModel
        //return  model
          var user = readFileFromLocal("user")
        if (user != null){
            return user as UserModel
        }
        else{
            return null
        }

//        val sharedPreferences = context.getSharedPreferences("UserData", MODE_PRIVATE);
//        val  userS = sharedPreferences.getString("User","")
//        if  (!TextUtils.isEmpty(userS)){
//        val  base64Product =   android.util.Base64.decode(userS,android.util.Base64.DEFAULT)
//        val bais = ByteArrayInputStream(base64Product);
//        val ois =  ObjectInputStream(bais);
//          return   ois.readObject() as UserModel
//        }
//        else{
//            return  null
//        }
    }


    fun fileSave2Local(obj: Any,fileName: String) {
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            //通过openFileOutput方法得到一个输出流
            var path =  "${Environment.getExternalStorageDirectory().getAbsolutePath()}/${fileName}"

            fos = FileOutputStream(File(path))
            oos = ObjectOutputStream(fos)
            oos.writeObject(obj) //写入
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (oos != null) oos.close()
                if (fos != null) fos!!.close() //最后关闭输出流
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }


    fun readFileFromLocal(fileName: String): Any? {
        var fileInputStream: FileInputStream? = null
        var objectInputStream: ObjectInputStream? = null
        try {
           var path =  Environment.getExternalStorageDirectory().getAbsolutePath()
            val file = File(path,fileName)
            if (file.exists()){
                fileInputStream =   FileInputStream(file)
                objectInputStream = ObjectInputStream(fileInputStream)
                return objectInputStream.readObject()
            }
            else{
                return null
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close()
                if (objectInputStream != null) objectInputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return null
    }


    fun  getUserId(context: Context):Int?{

        var model =  getUserMessage(context)
        if (model != null){
            return  model.userid
        }else{
            return null
        }
    }



    fun  showErrView(context:Context){
        Toast.makeText(context,"服务器开小差了。。。",Toast.LENGTH_LONG).show()
    }


    fun  showMessageView(v:View,m:String){
        Snackbar.make(v,m, Snackbar.LENGTH_SHORT).show()
    }



}