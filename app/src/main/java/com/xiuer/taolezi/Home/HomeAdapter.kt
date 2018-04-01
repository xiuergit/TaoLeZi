package com.xiuer.taolezi.Home

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

/**
 * Created by xiuer on 2018/3/2.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class  HomeAdapter(fm: FragmentManager?,tabTypes:Array<Int>) : FragmentStatePagerAdapter(fm) {

    var  mTabTypes = tabTypes
    var  mFragments = mutableListOf<HomeItemFragment>()

    init {
        for (i in mTabTypes){
            var  fragmnet:HomeItemFragment

            if (i == 1000){
                fragmnet = HomeItemFragment.newInstance(i,true)
                //fragmnet.view?.setBackgroundColor(Color.BLUE)
            }
            else{
                fragmnet = HomeItemFragment.newInstance(i,false,false)
            }
            mFragments.add(fragmnet)
        }
        Log.i("HomeAdapter","INIT  ${mFragments.count()}")

//        mFragments[0].loadData()


    }

    override fun getItem(position: Int): Fragment {
       Log.i("HomeAdapter","${position}")
       // var homeFragment = HomeItemFragment.newInstance(mTabTypes[position])
        return mFragments[position];
    }


    override fun getCount(): Int {

      return mTabTypes.size
    }


}