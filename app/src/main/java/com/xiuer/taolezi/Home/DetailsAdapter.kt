package com.xiuer.taolezi.Home

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.home_detail_item.view.*

/**
 * Created by xiuer on 2018/3/14.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class DetailsAdapter(context: Context, detailsUser:HomeItemModel, objects: List<HomeCommontModel>): RecyclerArrayAdapter<HomeCommontModel>(context, objects) {
    val mdetailsUser = detailsUser
    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
        return  DetailsHolder(parent!!)
    }

    override fun OnBindViewHolder(holder: BaseViewHolder<*>?, position: Int) {
        super.OnBindViewHolder(holder, position)
//        with(holder?.itemView){
//            val v = holder?.itemView
//            val  head = LayoutInflater.from(context).inflate(R.layout.fragment_homeitem,null)
//            if (position == 0){
//
//                this?.root_details_item?.removeAllViews()
//                this?.root_details_item?.addView(head)
//                with(head){
//                    mCommentMessage.removeAllViews()
//                    TlZHomeAdapterDataSet.userContext(head,mdetailsUser,context,true)
//                    TlZHomeAdapterDataSet.userHead(head,mdetailsUser,context)
//                }
//            }
//            else {
//                this?.mCommentName?.text = mdetailsUser.comments[position - 1].username
//                this?.mCommentContext?.text = mdetailsUser.comments[position - 1].content
//                this?.mCommentTime?.text = mdetailsUser.comments[position - 1].time
//                Picasso.with(context).load(mdetailsUser.comments[position - 1].usericon)
//                        .config(Bitmap.Config.RGB_565)
//                        .placeholder(R.drawable.default_icon2)
//                        .into(this?.mCommentIcon)
//
//
//            }}
//        }
    }

//    val mContext = context
//
//     var mdetailsUser:HomeItemModel = homeModel
//
//    override fun getItemCount(): Int {
//
//        return   mdetailsUser.comments.size+1
//    }
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailsHolder {
//        val  v = LayoutInflater.from(mContext).inflate(R.layout.home_detail_item,null)
//        return DetailsHolder(v)
//    }
//    override fun onBindViewHolder(holder: DetailsHolder?, position: Int) {
//        with(holder?.itemView!!){
//            val v = holder.itemView
//            val  head = LayoutInflater.from(mContext).inflate(R.layout.fragment_homeitem,null)
//            if (position == 0){
//                root_details_item.removeAllViews()
//                root_details_item.addView(head)
//                with(head){
//                    mCommentMessage.removeAllViews()
//                    TlZHomeAdapterDataSet.userContext(head,mdetailsUser,mContext,true)
//                    TlZHomeAdapterDataSet.userHead(head,mdetailsUser,mContext)
//                }
//            }
//            else{
//                mCommentName.text =  mdetailsUser.comments[position-1].username
//                mCommentContext.text = mdetailsUser.comments[position-1].content
//                mCommentTime.text = mdetailsUser.comments[position-1].time
//                Picasso.with(mContext).load(mdetailsUser.comments[position-1].usericon)
//                        .config(Bitmap.Config.RGB_565)
//                        .placeholder(R.drawable.default_icon2)
//                        .into(mCommentIcon)
//
//
//            }
//        }
//
//    }

    class  DetailsHolder : BaseViewHolder<HomeCommontModel> {
        constructor(itemView: ViewGroup) : super(itemView, R.layout.home_detail_item)

        override fun setData(data: HomeCommontModel?) {
            super.setData(data)
            with(itemView) {
                mCommentName?.text = data?.username
                mCommentContext?.text = data?.content
                this?.mCommentTime?.text = data?.time
//                Picasso.with(context).load(data?.usericon)
//                        .config(Bitmap.Config.RGB_565)
//                        .placeholder(R.drawable.default_icon2)
//                        .into(this?.mCommentIcon)
                ImageNetLoad.loadImage(context,data!!.usericon,mCommentIcon)

            }
        }
    }}



