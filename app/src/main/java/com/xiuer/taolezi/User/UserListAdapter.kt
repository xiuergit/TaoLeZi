package com.xiuer.taolezi.User

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.ImageNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.user_message_itemview.view.*

/**
 * Created by xiuer on 2018/3/12.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
class UserListAdapter(context: Context?,objects: MutableList<UserModel>) : RecyclerArrayAdapter<UserModel>(context,objects)
{


    companion object {
        val names =  listOf<String>("头像","昵称","ID","性别")
        val viewtypes = listOf<Int>( 100, 101,102,103)
        val TAG = "UserListAdapter用户信息"
    }

    override fun getViewType(position: Int): Int {
        return viewtypes[position]
    }

    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
         return UserHolder(parent!!)
    }


    class  UserHolder:BaseViewHolder<UserModel> {
        constructor(itemView:ViewGroup) : super(itemView, R.layout.user_message_itemview)
        override fun setData(data: UserModel?) {
            super.setData(data)

            Log.i(TAG,"itemViewType:${itemViewType}pois:${dataPosition}")
            with(itemView){
                mUserTitle.text = names[dataPosition]
                mHead.layoutParams = RelativeLayout.LayoutParams(0,0)
                when (itemViewType){
                     100->  {mUserContent.visibility = View.INVISIBLE
                             mHead.visibility = View.VISIBLE
                             var params = RelativeLayout.LayoutParams(200,200)
                             params.alignWithParent = true
                             params.leftMargin = TLZTool.screenRect(context as Activity)[0]- 268
                             mHead.layoutParams = params
                             ImageNetLoad.loadCircleImage(context, data!!.usericon,mHead)
                            }

                     101->{mUserContent.text = data!!.nickname
                           }

                     102->{mUserContent.text = "${data!!.userid}"
                           mArrow.visibility = View.INVISIBLE
                          }
                     103->{
                         var  sex = ""
                         if (data!!.sex == 1){
                             sex = "男"

                         }
                         else if (data!!.sex == 0){
                             sex = "女"
                         }
                         mUserContent.text = sex}

                 }

            }


        }
    }

}
