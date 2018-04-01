package com.xiuer.taolezi.User

import android.os.Bundle
import android.util.Log
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Home.HomeItemFragment
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_user_meessage.*

class UserCollectActivity : BaseActivity() {


    private  val mSets = mapOf("我的收藏" to R.layout.activity_user_collect,"我的段子" to R.layout.activity_user_collect,"我的动态" to R.layout.activity_user_collect,"意见反馈" to R.layout.user_suggest_view,
            "关于淘乐子" to R.layout.user_about_view,"广告合作" to R.layout.user_guanggao_view)
    private  var mTitle:String = ""

    private  var mTransaction = supportFragmentManager.beginTransaction()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTitle = intent.getStringExtra(TLZConstant.USER_DETAILS_TITLE)
        Log.i("UserCollectActivity","${mTitle}")
        val  id = mSets[mTitle]
        setContentView(id!!)
        topSet()
        loadData()

    }


    fun topSet(){
        mTopNav.setTitle(intent.getStringExtra(TLZConstant.USER_DETAILS_TITLE))
        mTopNav.rightView().text = ""
        mTopNav.backView().setOnClickListener({
            view ->
            this.finish()
        })
    }


    fun  loadData() {
        when (mTitle) {
            "我的段子" -> {
                val homeItemFragment =  HomeItemFragment.newInstance(1000,true,false)
                mTransaction.replace(R.id.mRootView,homeItemFragment)
                mTransaction.commitNow()
            }
            "我的收藏" -> {
                val homeItemFragment =  HomeItemFragment.newInstance(1000,false,true)
                mTransaction.replace(R.id.mRootView,homeItemFragment)
                mTransaction.commitNow()
            }
            "我的动态" -> {
                val homeItemFragment =  HomeItemFragment.newInstance(1000,false,false)
                mTransaction.replace(R.id.mRootView,homeItemFragment)
                mTransaction.commitNow()
            }
        }
    }
}
//        when (mTitle){
//             "我的段子"-> {
//                val model =  TLZTool.getUserMessage(this)
//                if (model!= null) {
//
////                    UserNetLoad.dunzis(0,model.userid{
////
////
////                    },{
////
////                    })
////                }
//                }
//              "我的收藏"->
//
////                  val model =  TLZTool.getUserMessage(this)
////                  if (model!= null){
////                      val params = mapOf<String,Any>("userid" to 219119,"page" to 0)
////                      UserNetLoad.collects(params,{
////
////                      },{
////
////                      })
//
//                }
//
//
//
//
//            }
//
//        }
//
//    }
