package com.xiuer.taolezi.Send

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.NetManger
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_send.*
import java.io.ByteArrayOutputStream


class SendActivity : BaseActivity() {


    private  var mBitmap:Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)



//        McInit.getInstance().getOpenNativeAd(this, McType.MESSAGE_SMALL_IMG,object : IMcNativeSuccessBack {
//
//            override fun onFailed(p0: String?) {
//                Log.i("广告","onError：${p0}")
//            }
//
//            override fun onSuccess(p0: McSDKInfo?) {
//                Log.i("广告","OnSuccess")
//
//                mSendImage.post {
//
//                    Glide.with(this@SendActivity).load(p0?.imgurl).into(mSendImage)
//                   // tv_ad_text.setText(advertisement.getWenzi())
//
//                    McInit.getInstance().uploadShow(this@SendActivity, p0)
//
//                  //  McWebActivity()
//                }
//            }
//        })


    }
    fun quite(v: View){
        finish()
    }
    fun send(v:View){
        if (mBitmap != null){

            if  (TLZTool.isLogin(this)){
                val userid = TLZTool.getUserMessage(this)!!.userid
                val width = mBitmap!!.width
                val height = mBitmap!!.height

                val time = System.currentTimeMillis()
                val params = mapOf<String,String>("userid" to "${userid}" ,"width" to "${width}","height" to "${height}",
                        "timestamp" to  "${time.toInt()}","question" to mSendContext.text.toString(),"is_public" to "${0}")

                val os = ByteArrayOutputStream()
                mBitmap!!.compress(Bitmap.CompressFormat.PNG,80,os)
                val byteArray =os.toByteArray()
                val imageString = String(Base64.encode(byteArray, Base64.DEFAULT))

                NetManger.upload(TLZConstant.POSTQUESTIONURL,params,imageString,{
                    json ->
                    Log.i(TAG ,"  发布成功：${json}")
                    v.post({
                        Toast.makeText(this," 发布成功",Toast.LENGTH_SHORT).show()
                        this.finish()
                    })

                })
            }
            else {

                Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show()
            }



        }


    }

    fun selectImage(v:View){
        // 激活系统图库，选择一张图片
         val intent = Intent(Intent.ACTION_PICK);
         intent.setType("image/*");
         startActivityForResult(intent, PHOTO_REQUEST_GALLERY );
    }

    /*
65      * 剪切图片
66      */
     private fun crop( uri:Uri) {
                  // 裁剪图片意图
                val intent = Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                 intent.putExtra("crop", "true");
                 // 裁剪框的比例，1：1
                  intent.putExtra("aspectX", 1);
                  intent.putExtra("aspectY", 1);
                // 裁剪后输出图片的尺寸大小
                  intent.putExtra("outputX", TLZTool.screenRect(this)[0]*0.8);
                  intent.putExtra("outputY", TLZTool.screenRect(this)[0]*0.6);

                  intent.putExtra("outputFormat", "JPEG");// 图片格式
                  intent.putExtra("noFaceDetection", true);// 取消人脸识别
                 intent.putExtra("return-data", true);
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
                    startActivityForResult(intent, PHOTO_REQUEST_CUT);
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                var uri = data.getData();
                crop(uri);
            }
        }
        else if(requestCode == PHOTO_REQUEST_CUT){
            if (data != null) {
                val bitmap = data.getParcelableExtra<Bitmap>("data")
                mSendImage.setImageBitmap(bitmap)

                mBitmap = bitmap
            }
        }

    }

    companion object {
        val PHOTO_REQUEST_GALLERY = 2
        //val PHOTO_REQUEST_CAREMA = 1
        val PHOTO_REQUEST_CUT = 3;// 结果
    }
}
