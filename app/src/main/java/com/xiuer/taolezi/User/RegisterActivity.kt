package com.xiuer.taolezi.User

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.UserNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    companion object {
        val TAGR = "注册Activity"
        val  RegisterRelult = "当前设备已经注册"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun register(v:View){

         Log.i(TAGR,"${TLZTool.getFileUdid(this)}")

          var  uuid =  TLZTool.getFileUdid(this)
          if (!TextUtils.isEmpty(mInputPwd.text)&&mInputPwd.length() > 6){
              if (uuid != null){
                  Log.i(TAGR,"RegisterRelult${TLZTool.getFileUdid(this)}")
                  Toast.makeText(this, RegisterRelult,Toast.LENGTH_LONG).show()
              }
              else {
                  var u = TLZTool.getUdid()
                  UserNetLoad.register(mapOf("password" to mInputPwd.text.toString(),
                "usericon" to "icon1.jpg","deviceid" to u), {
            userModel ->

             v.post({
                 Toast.makeText(this,"注册成功,您的id为：${userModel.userid}",Toast.LENGTH_LONG).show()
                  TLZTool.saveFileUdid(u)
                  TLZTool.setUserMessage(userModel)
                  intent.putExtra("userid",userModel.userid)
             })
            this.finish()
        },{
            err->
            Toast.makeText(this,err,Toast.LENGTH_LONG).show()
        })

              }

          } else{
              Log.i(TAGR,"密码长度不小于6 ${TLZTool.getFileUdid(this)}")
              TLZTool.showMessageView(mRegister, "密码长度不小于6")
              Toast.makeText(this, "密码长度不小于6",Toast.LENGTH_LONG).show()
               mInputPwd.error = getString(R.string.error_invalid_password)
          }




    }



}
