package com.xiuer.taolezi.Home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Home.HomeItemModel
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.count_view_layout.view.*


/**
 * Created by xiuer on 2018/3/20.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class CountTextView :RelativeLayout{
    fun  setData(homeModel: HomeItemModel){

        mCMReadCount.text = "${homeModel.browsenum}阅读"
        mCMCommentCount.text = "${homeModel.commentnum}评论"
        mCMLastTime.text = "${homeModel.time}"

        setComment(homeModel,mCommentMessage,context)


    }
    init {
        LayoutInflater.from(context).inflate(R.layout.count_view_layout,this)
    }
    constructor(context: Context):this(context,null){}

    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet){}

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        super.onLayout(p0, p1, p2, p3, p4)
    }


    fun  setComment(homeModel:HomeItemModel, v: View, mContext:Context) {

        with(v) {
            if (homeModel.commentnum == 0) {
                mCommentMessage.layoutParams = RelativeLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
            } else {
                if (homeModel.commentnum > 3 || homeModel.commentnum == 3) {
                    mCommentOne.text = "${homeModel.comments[0].username}:"
                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
                    mCommentTwo.text = "${homeModel.comments[1].username}:"
                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}"
                    mCommentThree.text = "${homeModel.comments[2].username}:"
                    mCommentThreeContext.text = "   ${homeModel.comments[2].content}...."
                } else if (homeModel.commentnum == 2) {
                    mCommentOne.text = "${homeModel.comments[0].username}:"
                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
                    mCommentTwo.text = "${homeModel.comments[1].username}:"
                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}...."
                    mCommentThree.text = ""
                    mCommentThreeContext.text = ""
                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)

                } else {
                    mCommentOne.text = "${homeModel.comments[0].username}:"
                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
                    mCommentTwo.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
                    mCommentTwoContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)

                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)

                }

            }
        }
    }
}