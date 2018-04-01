package com.xiuer.taolezi.User

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.UserNetLoad
import com.xiuer.taolezi.R

import kotlinx.android.synthetic.main.activity_user_update_sex.*

class UserUpdateSexActivity : BaseActivity() {

    var mIsNan:Int = 1

    private  var  mUserModel = TLZTool.getUserMessage(this)!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update_sex)
        mNav.setTitle("设置性别")
        mNav.backView().setOnClickListener(
                {
                    intent.putExtra(USER_SEX,mUserModel.sex)
                    finish()
                }
        )

        var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        params.alignWithParent = true

        val  width:Int = (Resources.getSystem().getDisplayMetrics().density*120).toInt()
        val  margin:Int = (Resources.getSystem().getDisplayMetrics().density*28).toInt()

       // params.setMargins(TLZTool.screenRect(this)[0]-120,margin,0,margin)
        mNav.rightView().layoutParams = params


        mNav.rightView().text = "完成"

        mNav.rightView().setTextColor(Color.WHITE)
        mNav.rightView().setOnClickListener {

             UserNetLoad.updateSex(mUserModel.userid,mIsNan,{
                 m->
                 TLZTool.setUserMessage(m)
                 TLZTool.showMessageView(mNv,"修改成功")
                 intent.putExtra(USER_SEX,m.sex)
                 this.finish()
             },{

             })
        }

        mNan.setOnClickListener {
            mIsNan = 1
            mNanSelect.background = resources.getDrawable(R.drawable.selected)
            mNvSelect.setBackgroundColor(Color.WHITE)
        }
        mNv.setOnClickListener {
            mIsNan = 0
            mNvSelect.background = resources.getDrawable(R.drawable.selected)
            mNanSelect.setBackgroundColor(Color.WHITE)
        }


    }
    companion object {
        val  USER_SEX = "USER_SEX"
    }
}
