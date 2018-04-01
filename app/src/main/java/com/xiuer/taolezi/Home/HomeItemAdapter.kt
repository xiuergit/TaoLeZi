package com.xiuer.taolezi.Home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.Common.TlZHomeAdapterDataSet
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.advertisement_big_image.view.*
import kotlinx.android.synthetic.main.fragment_homeitem_full_right.view.*
import kotlinx.android.synthetic.main.fragment_homeitem_fullbelow.view.*
import kotlinx.android.synthetic.main.fragment_homeitem_nofullbelow.view.*
import kotlinx.android.synthetic.main.fragment_homeitem_nofullright.view.*
import kotlinx.android.synthetic.main.guanggao_smalls_image.view.*
import org.json.JSONObject

/**
 * Created by xiuer on 2018/3/14.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class HomeItemAdapter(context: Context?, objects: MutableList<HomeItemModel>?) : RecyclerArrayAdapter<HomeItemModel>(context, objects) {
    companion object {
        private  val  FULL_ITEM_VIEW_TAG_Right = 0
        private  val  FULL_ITEM_VIEW_TAG_Blow = 1
        private  val  NO_FULL_ITEM_VIEW_TAG_Right = 2
        private  val  NO_FULL_ITEM_VIEW_TAG_Blow = 3

        private  val  GUANGGao_BigImage = 4
        private  val  GUANGGao_SmallImage = 5

    }
   // val mObjects = objects

    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {

        var  holder:BaseViewHolder<*>?= null
         when(viewType){
             FULL_ITEM_VIEW_TAG_Blow -> holder = FullBlowViewHolder(parent!!)
             FULL_ITEM_VIEW_TAG_Right -> holder = FullRightViewHolder(parent!!)
             NO_FULL_ITEM_VIEW_TAG_Blow -> holder = NoFullBlowViewHolder(parent!!)
             NO_FULL_ITEM_VIEW_TAG_Right ->holder = NoFullRightViewHolder(parent!!)
             GUANGGao_BigImage->holder = GuangBigImageViewHolder(parent!!)
             GUANGGao_SmallImage ->holder = GuangSmallImageViewHolder(parent!!)

             else -> holder = HomeItemViewHolder(parent!!)
         }
        return holder!!
    }

    override fun getViewType(position: Int): Int {
         //if (mObjects[position].isad)
        val  homeModel = mObjects[position]
        var type:Int = 0
        if( homeModel.viewtype == 1 && homeModel.is_public == 1){
            if (homeModel.userName != null && homeModel.userName != ""){
                type = FULL_ITEM_VIEW_TAG_Right
            }else{
                type = NO_FULL_ITEM_VIEW_TAG_Right
            }
        }else{
            if (homeModel.userName != null && homeModel.userName != ""){
                type = FULL_ITEM_VIEW_TAG_Blow
            }else{
                type = NO_FULL_ITEM_VIEW_TAG_Blow
            }
        }


        if (homeModel.type == TLZConstant.AD_TYPE){
            type = GUANGGao_BigImage
        }

        if (homeModel.viewtype == TLZConstant.ADSMALL_TYPE){
            type = GUANGGao_SmallImage
        }

        return type
    }


    override fun add(`object`: HomeItemModel?) {
        super.add(`object`)
    }

    class FullRightViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.fragment_homeitem_full_right)
        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){
                    val  time:String = TLZTool.getStrTime(data.timestamp.toString())!!
                    userHead_r.setData(data.userName,time,data.userIcon)
                    mContext_FR.setData(data.content,data.img)
                    mCount_FB.setData(data)
                }
            }
        }

    }
    class NoFullRightViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.fragment_homeitem_nofullright)

        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){
                   // mCMContext1.text = data.content
                   // ImageNetLoad.loadImage(context,data.img,mCMImage1)
                    mContent_NR.setData(data.content,data.img)
                    mCount_NR.setData(data)

//                    mCMCommentCount.text = "${data.commentnum}评论"
//                    mCMReadCount.text = "${data.browsenum}评论"
                }
            }
        }
    }
    class FullBlowViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.fragment_homeitem_fullbelow)

        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){
                    val  time:String = TLZTool.getStrTime(data.timestamp.toString())!!
                    userHead_b.setData(data.userName,time,data.userIcon)
                    mContext_FB.setData(data.content,data.img)
                    mCount_FBB.setData(data)
                }
            }
        }
    }
    class NoFullBlowViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.fragment_homeitem_nofullbelow)
        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){
                    mContent_NB.setData(data.content,data.img)
                    mCountView_NB.setData(data)
                }
            }
        }

    }

    class GuangBigImageViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.advertisement_big_image)
        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){
                    Log.i("大图的url"," images： ${data.img} ")
                    ImageNetLoad.loadImageAD(context,data.img,mImage)
                    ad_WENZIbig.text = data.content
                }
            }
        }

    }


    class GuangSmallImageViewHolder:BaseViewHolder<HomeItemModel>{
        constructor(itemView:ViewGroup) : super(itemView,R.layout.guanggao_smalls_image)
        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if(data != null){

                        val json = JSONObject(data.img)
                        Log.i("分割的url"," images： ${json} ")
                        ImageNetLoad.loadImageAD(context,json.optString("urlOne"),mImage1)
                        ImageNetLoad.loadImageAD(context,json.optString("urltwo"),mImage2)
                        ImageNetLoad.loadImageAD(context,json.optString("urlthree"),mImage3)

                         ad_WENZI.text = data.content
                }
            }
        }

    }


    class  HomeItemViewHolder : BaseViewHolder<HomeItemModel> {

        constructor(itemView:ViewGroup) : super(itemView,R.layout.fragment_homeitem)


        override fun setData(data: HomeItemModel?) {
            super.setData(data)
            with(itemView){
                if (data != null){
                    TlZHomeAdapterDataSet.userContext(itemView,data,context,false)
                    TlZHomeAdapterDataSet.userHead(itemView,data,context)
                   // setComment(data,itemView,context)
                }

            }

        }



//     fun  setComment(homeModel:HomeItemModel,v:View,mContext:Context) {
//
//        with(v) {
//            if (homeModel.commentnum == 0) {
//
//                mCommentMessage.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//            } else {
//                if (homeModel.commentnum > 3 || homeModel.commentnum == 3) {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.text = "${homeModel.comments[1].username}:"
//                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}"
//                    mCommentThree.text = "${homeModel.comments[2].username}:"
//                    mCommentThreeContext.text = "   ${homeModel.comments[2].content}...."
//                } else if (homeModel.commentnum == 2) {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.text = "${homeModel.comments[1].username}:"
//                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}...."
//                    mCommentThree.text = ""
//                    mCommentThreeContext.text = ""
//                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                } else {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentTwoContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                }
//
//            }
//        }
//    }
//}
}
}




//    val  mContext = context
//    val  mItems = items
//    var  mJumpDetails:ITaoLeziClickListener? = null
//
//    override fun onBindViewHolder(holder: TLZViewHolder?, position: Int) {
//        val  homeModel = mItems[position]
//         val v = holder?.itemView
//        with(v!!) {
//            TlZHomeAdapterDataSet.userContext(v,homeModel,mContext,false)
//
//            TlZHomeAdapterDataSet.userHead(v,homeModel,mContext)
//
//            if (homeModel.commentnum == 0) {
//                mCommentMessage.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//            } else {
//                if (homeModel.commentnum > 3 || homeModel.commentnum == 3) {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.text = "${homeModel.comments[1].username}:"
//                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}"
//                    mCommentThree.text = "${homeModel.comments[2].username}:"
//                    mCommentThreeContext.text = "   ${homeModel.comments[2].content}...."
//                } else if (homeModel.commentnum == 2) {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.text = "${homeModel.comments[1].username}:"
//                    mCommentTwoContext.text = "   ${homeModel.comments[1].content}...."
//                    mCommentThree.text = ""
//                    mCommentThreeContext.text = ""
//                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                } else {
//                    mCommentOne.text = "${homeModel.comments[0].username}:"
//                    mCommentOneContext.text = "   ${homeModel.comments[0].content}"
//                    mCommentTwo.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentTwoContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                    mCommentThree.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//                    mCommentThreeContext.layoutParams = LinearLayout.LayoutParams((mContext as BaseActivity).screenRect()[0], 0)
//
//                }
//
//
//                v.setOnClickListener(object : View.OnClickListener {
//                    override fun onClick(p0: View?) {
//                        mJumpDetails?.onHomeItemClickListener(v, position, homeModel)
//                    }
//
//                })
//
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return  mItems.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TLZViewHolder {
//        val  v = LayoutInflater.from(mContext).inflate(R.layout.fragment_homeitem,null)
//        return  TLZViewHolder(v)
//    }


//}