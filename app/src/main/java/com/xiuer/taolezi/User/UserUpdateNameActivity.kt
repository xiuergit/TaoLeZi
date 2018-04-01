package com.xiuer.taolezi.User

import android.content.res.Resources
import android.os.Bundle
import android.widget.RelativeLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.UserNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_user_update_name.*


class UserUpdateNameActivity : BaseActivity() {

    private  var  mUserModel = TLZTool.getUserMessage(this)!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update_name)
        mNav.backView().setOnClickListener({
            intent.putExtra(USER_NICKNAME,mUserModel.nickname)
            this.finish()
        })

        var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        params.alignWithParent = true
        val  width:Int = (Resources.getSystem().getDisplayMetrics().density*120).toInt()
        val  margin:Int = (Resources.getSystem().getDisplayMetrics().density*28).toInt()
        mNav.rightView().layoutParams = params
        mNav.rightView().text = "完成"
        mNav.rightView().setOnClickListener {
            //上传数据

            UserNetLoad.updateName(mUserModel.userid,mNickName.text.toString(),
                    {
                        m->
                        TLZTool.setUserMessage(m)
                        TLZTool.showMessageView(mNickName,"修改成功")
                        intent.putExtra(USER_NICKNAME,m.nickname)
                        this.finish()

                    })

        }

        mNickName.setText(mUserModel!!.nickname)
    }

    companion object {
        val USER_NICKNAME = "USER_NICKNAME"
    }
}
