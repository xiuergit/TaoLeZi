package com.xiuer.taolezi.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.Home.view.IBottomNavItemClickEvent
import com.xiuer.taolezi.Popularize.PopularizeTool
import com.xiuer.taolezi.R
import com.xiuer.taolezi.Send.SendActivity
import com.xiuer.taolezi.User.UserFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hongbao.view.*

class MainActivity : BaseActivity(), IBottomNavItemClickEvent {

    val  MainTAG =  "MainActivity"

    var  mHomeFragment = HomeFragment.newInstance(this)
    val  mUserFragment = UserFragment()
    var  mTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
                    mNavQuit.visibility = View.INVISIBLE
                    mBottomNavView.onClick = this@MainActivity
                    mBottomNavView.selectHome()


        PopularizeTool.hongbao(this,{
            view ->


            var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)

           // view!!.layoutParams = params

             var root =  LayoutInflater.from(this).inflate(R.layout.hongbao,null)
             with(root){
                hongbao.addView(view)
             }

            window.addContentView(root, params)

        })

    }
      override fun onItemClick(item: View) {

          if (item.tag == 101) {

              mNavTitle.text = TLZConstant.HOME_FRAGMENT_TAG
              mNavQuit.visibility = View.INVISIBLE

              Log.i(MainTAG, "点击首页${item.tag}：item ..${mHomeFragment}")
              // if (mTransaction == null) {
              mTransaction = supportFragmentManager.beginTransaction()
              //}
              // mTransaction?.remove(mHomeFragment)
              mHomeFragment = HomeFragment.newInstance(this)
              mTransaction?.replace(R.id.mHomeFragmentRoot, mHomeFragment)
              mTransaction?.commit()

          } else if (item.tag == 102) {
              Log.i(MainTAG, "${item.tag}：item")
              mNavQuit.visibility = View.VISIBLE
              val sendIntent = Intent(this, SendActivity::class.java)
              startActivity(sendIntent)
          } else {
              Log.i(MainTAG, "${item.tag}：item")
              if (TLZTool.isLogin(this)) {
                  mNavQuit.visibility = View.VISIBLE
                  mNavQuit.setOnClickListener(mUserFragment)
              }
                  mNavTitle.text = TLZConstant.USER_FRAGMENT_TAG
                  mTransaction = supportFragmentManager.beginTransaction()
                  mTransaction?.replace(R.id.mHomeFragmentRoot, mUserFragment)
                  mTransaction?.commit()

          }

      }


          fun topUserSet() {

              if (TLZTool.isLogin(this)) {
                  mNavQuit.visibility = View.VISIBLE
                  mNavQuit.setOnClickListener({
                      TLZTool.setUserLogin(this, false)
                  })
              } else {
                  mNavQuit.visibility = View.INVISIBLE
              }
          }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }


    override fun onStop() {
        super.onStop()

    }


}
