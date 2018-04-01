package com.xiuer.taolezi.User

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.Home.MainActivity
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * Created by xiuer on 2018/3/8.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class UserFragment(): Fragment(),View.OnClickListener {

    override fun onClick(p0: View?) {
        TLZTool.setUserLogin(context,false)
        p0?.visibility = View.INVISIBLE
        setHead()
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i("UserFragment","setUserVisibleHint")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_user, container, false)
        return v;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mUserDuanzi.setItem(R.drawable.my_question,resources.getString(R.string.duanzi),object:IUserItemSetView{
             override fun onItemClick() {
                 //段子
                 Log.i(TLZConstant.USER_FRAGMENT_TAG,"段子")
                 val intent = Intent(context,UserCollectActivity::class.java)
                 intent.putExtra(TLZConstant.USER_DETAILS_TITLE,TLZConstant.MY_DUANZI_TAG)
                 context.startActivity(intent)
            }
        })

        mUserShouCang.setItem(R.drawable.my_collect,resources.getString(R.string.shouCang),object:IUserItemSetView{
            override fun onItemClick() {
                //收藏
                Log.i(TLZConstant.USER_FRAGMENT_TAG,"收藏")
                val intent = Intent(context,UserCollectActivity::class.java)
                intent.putExtra(TLZConstant.USER_DETAILS_TITLE,TLZConstant.MY_Collect_TAG)
                context.startActivity(intent)
            }
        })

        mUserDongTai.setItem(R.drawable.my_notice,resources.getString(R.string.dongtai),object:IUserItemSetView{
            override fun onItemClick() {
                //动态
                val intent = Intent(context,UserCollectActivity::class.java)
                intent.putExtra(TLZConstant.USER_DETAILS_TITLE,TLZConstant.MY_DONGTAI_TAG)
                context.startActivity(intent)
            }
        })

        mUserYijianfankui.setItem(R.drawable.report,resources.getString(R.string.yijianfankui),object:IUserItemSetView{
            override fun onItemClick() {
                //收藏
                val intent = Intent(context,UserCollectActivity::class.java)
                intent.putExtra(TLZConstant.USER_DETAILS_TITLE,"意见反馈")
                context.startActivity(intent)
            }
        })
        mUserAbout.setItem(R.drawable.about,resources.getString(R.string.about),object:IUserItemSetView{
            override fun onItemClick() {
                //收藏
                val intent = Intent(context,UserCollectActivity::class.java)
                intent.putExtra(TLZConstant.USER_DETAILS_TITLE,resources.getString(R.string.about))
                context.startActivity(intent)
            }
        })
        mUserGuanggao.setItem(R.drawable.business,resources.getString(R.string.guanggao),object:IUserItemSetView{
            override fun onItemClick() {
                //收藏
                val intent = Intent(context,UserCollectActivity::class.java)
                intent.putExtra(TLZConstant.USER_DETAILS_TITLE,resources.getString(R.string.guanggao))
                context.startActivity(intent)
            }
        })


        if (TLZTool.isLogin(context)){
            val model = TLZTool.getUserMessage(context)
            ImageNetLoad.loadImage(context,model!!.usericon,userHeadIcon)
            userHeadTitle.text = model!!.nickname + "\n\nID:"+model!!.userid
            userHeadTitle.setLines(2)
        }
        else{
            userHeadTitle.text = "点击头像登录"
            userHeadTitle.setLines(1)
        }

        userHead.setOnClickListener {
           // Log.i("头像点击:Usermodel:","MODLE:${model} ISLOGIN:${TLZTool.isLogin(context)}")
            if ( TLZTool.isLogin(context)){
                val intent = Intent(context,UserMeessageActivity::class.java)
                context.startActivity(intent)
            }
            else{
                val intent = Intent(context,LoginActivity::class.java)
                activity.startActivityFromFragment(this,intent,102)
            }

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("onActivityResult","requestCode:${requestCode}")
        setHead()
         var activity =  activity as MainActivity
         activity.topUserSet()
    }

    fun setHead(){

        val model = TLZTool.getUserMessage(context)

        if (model!=null && TLZTool.isLogin(context)){
            Log.i("tag","model")
            ImageNetLoad.loadImage(context,model.usericon,userHeadIcon)
            userHeadTitle.text = model.nickname + "\n ID:"+model.userid
            userHeadTitle.setLines(2)
        }
        else{
            Log.i("tag","model 头像登")
            userHeadTitle.text = "点击头像登录"
            userHeadTitle.setLines(1)
            //userHead.setBackgroundResource(R.drawable.default_icon1)
        }
    }


}