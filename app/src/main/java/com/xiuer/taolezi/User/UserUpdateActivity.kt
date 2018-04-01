package com.xiuer.taolezi.User

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.R

class UserUpdateActivity : BaseActivity() {

    val mPoistion = intent.getIntExtra(UserMeessageActivity.IndexTAG,0)
    val mTitles = mutableListOf<String>("个人头像","设置名字","xingbie")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update)
    }
}
