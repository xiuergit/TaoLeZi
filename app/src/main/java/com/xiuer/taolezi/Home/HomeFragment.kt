package com.xiuer.taolezi.Home


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by xiuer on 2018/3/8.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class HomeFragment:Fragment() {


    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        Log.i(TLZConstant.HOME_FRAGMENT_TAG,"onAttachFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i(TLZConstant.HOME_FRAGMENT_TAG,"onCreate(sa")

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
       // Log.i(TLZConstant.HOME_FRAGMENT_TAG,"setUserVisibleHint")

    }

    var mAdapter:HomeAdapter? = null
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TLZConstant.HOME_FRAGMENT_TAG,"初始化首页的数据：view 创建完成")
        initTab()
        initContent()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
        Log.i(TLZConstant.HOME_FRAGMENT_TAG,"onCreate(saView")
        return view
    }

    fun initTab(){
        mHomeTab.setTabTextColors(Color.BLACK,Color.parseColor("#FF8C00"))
        mAdapter = HomeAdapter(activity.supportFragmentManager, TLZConstant.HomeTabTypes)

        mHomeContext.adapter = mAdapter
        mHomeContext.offscreenPageLimit = 1
        mHomeTab.setupWithViewPager(mHomeContext)
        mHomeTab.tabMode = TabLayout.MODE_SCROLLABLE
        mHomeTab.tabGravity = Gravity.CENTER
       // mHomeTab.width = TLZTool.screenRect(context as BaseActivity)[0]/6


        mHomeTab.setSelectedTabIndicatorColor(Color.parseColor("#FF8C00"))

    }
    fun initContent(){
        for (i in 1..TLZConstant.HomeTabNames.size){
            val title = TLZConstant.HomeTabNames[i-1]
            mHomeTab.getTabAt(i-1)?.text = title
        }
    }



    companion object {
        /**
         *  类型 请求数据的type 1000 推荐 。。。
         * @param type :   TLZConstant.HOmeTabType
         * @param isUserCollect :当前显示的是否是 用户的收藏
         * @param isUserJoke :当前显示的是否是 用户的段子
         */
        fun newInstance(activity:BaseActivity): HomeFragment {
            val fragment = HomeFragment()
            fragment.mAdapter = HomeAdapter(activity.supportFragmentManager, TLZConstant.HomeTabTypes)
            return fragment
        }

    }





}