package com.xiuer.taolezi.User

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.widget.RelativeLayout
import android.widget.TextView
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_user_meessage.*

class UserMeessageActivity : BaseActivity() {

    var mUserModel:UserModel? = null
    var mAdapter:UserListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_meessage)
        topSet()
        mUserModel = TLZTool.getUserMessage(this)
        userList()
    }


    fun topSet(){
        mTopNav.setTitle("个人信息")
        mTopNav.rightView().text = ""
        mTopNav.backView().setOnClickListener({
            view ->
            this.finish()
        })
    }

    fun userList(){
        myMessage.setLayoutManager(LinearLayoutManager(this))

        mAdapter = UserListAdapter(this, mutableListOf<UserModel>(mUserModel!!,mUserModel!!,mUserModel!!,mUserModel!!))
        myMessage.adapter = mAdapter
        mAdapter!!.setOnItemClickListener {
           position: Int ->
Log.i("position","${position}")
            when (position){
                0 ->{
                    var  indentH = Intent(this,UserUpdateHeadActivity::class.java)
                    indentH.putExtra(IndexTAG,position)
                    startActivityForResult(indentH, RequestHeadCode)
                }
                1->{
                    var  indentN = Intent(this,UserUpdateNameActivity::class.java)
                    indentN.putExtra(IndexTAG,position)
                    startActivityForResult(indentN, RequestNameCode)
                }
                3->{
                    var  indentS = Intent(this,UserUpdateSexActivity::class.java)
                    indentS.putExtra(IndexTAG,position)
                    startActivityForResult(indentS, RequestSexCode)
                }

            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            RequestNameCode ->{
                if (data!= null) {
                    val name = data.getStringExtra(UserUpdateNameActivity.USER_NICKNAME)
                    if (name != null) {
                        mUserModel!!.nickname = name
                        mAdapter!!.update(mUserModel, 1)
                        mAdapter!!.notifyDataSetChanged()
                    }
                }
            }
            RequestSexCode ->{
                if (data!= null) {
                mUserModel!!.sex = data.getIntExtra(UserUpdateSexActivity.USER_SEX,1)
                mAdapter!!.update(mUserModel,3)
                mAdapter!!.notifyDataSetChanged()
                }
            }

        }


    }





    companion object {
        val IndexTAG = "IndexTAG"
        val RequestHeadCode = 100
        val RequestNameCode = 101
        val RequestSexCode = 102

    }

}
