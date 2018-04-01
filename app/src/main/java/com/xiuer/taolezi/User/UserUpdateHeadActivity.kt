package com.xiuer.taolezi.User

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_user_update_head.*
import java.io.ByteArrayOutputStream

class UserUpdateHeadActivity : BaseActivity() {
    private  var  mBitmap:Bitmap? = null

    private  var mUserModel:UserModel = TLZTool.getUserMessage(this)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update_head)
        mNav.setTitle("个人头像")
        mNav.backView().setOnClickListener({
            this.finish()
        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mNav.rightView().background = resources.getDrawable(R.drawable.edit,resources.newTheme())
            mNav.rightView().text = ""
        }
        else{
            mNav.rightView().background = resources.getDrawable(R.drawable.edit)
            mNav.rightView().text = ""

        }

        mNav.rightView().setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, SelectImageCode);
        }
        mNav.backView().setOnClickListener(
                {
                    this.finish()
                }
        )

        ImageNetLoad.loadImage(this,mUserModel.usericon,mHead)






    }

    /*
        * 剪切图片
        */
    private fun crop( uri: Uri) {
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
        startActivityForResult(intent, CropImageCode);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SelectImageCode){

            if (data != null) {
                // 得到图片的全路径
                var uri = data.getData();
                crop(uri);
            }
        }
        if (requestCode == CropImageCode){
            if (data != null) {
                val bitmap = data.getParcelableExtra<Bitmap>("data")
                mHead.setImageBitmap(bitmap)
                val model = TLZTool.getUserMessage(this)!!

                val os = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG,80,os)
                val byteArray =os.toByteArray()
                val imageString = String(Base64.encode(byteArray, Base64.DEFAULT))
                ImageNetLoad.updateHead("${model.userid}",imageString,{

                })


                mBitmap = bitmap
            }
        }
    }

    companion object {
        val SelectImageCode = 100
        val CropImageCode = 101
    }
}
