package com.xiuer.taolezi.Home

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import cn.sharesdk.onekeyshare.OnekeyShare
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.Base.BaseActivity
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.Common.TlZHomeAdapterDataSet
import com.xiuer.taolezi.NetModeLoad.HomeNetLoad
import com.xiuer.taolezi.NetModeLoad.IHomeCommentRequestResult
import com.xiuer.taolezi.NetModeLoad.UserNetLoad
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.activity_home_item_detail.*
import kotlinx.android.synthetic.main.fragment_homeitem.view.*

class HomeItemDetailActivity : BaseActivity() {

    var  mComments:MutableList<HomeCommontModel> = mutableListOf()
    var  mItemModel: HomeItemModel? = null
    var  mAdapter:DetailsAdapter? = null
    var  mPage:Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_item_detail)
        initData()

        mSend.setOnClickListener {
            v->


            val context = mEditComment.text.toString()
            if (TLZTool.isLogin(this)){
               //TLZTool.showMessageView(v,"发送")
                UserNetLoad.comment(TLZTool.getUserId(this)!!,mItemModel!!.id,context,
                        {
                            model ->
                            mAdapter?.add(model)
                            mEditComment.post {
                                mEditComment.clearFocus()
                                TLZTool.showMessageView(v,"评论成功")
                            }

                        },{
                })
            }
            else{
                TLZTool.showMessageView(v,"请先登录")

            }


        }

        //评论
        mEditComment.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {

                Log.i(TAG,"${p0?.text}")

                return true
            }

        })

        mEditComment.setOnFocusChangeListener { v, b ->

            if (b){
                val  rect = Rect()
                v.getWindowVisibleDisplayFrame(rect)
                 Log.i("详情true","${rect.height()}")
                mEditComment.setCompoundDrawables(null,null,null,null)
                mSend.visibility = View.VISIBLE
                mCollection.visibility = View.INVISIBLE
                mRepor.visibility = View.INVISIBLE
            }
            else{
                val  rect = Rect()
                v.getWindowVisibleDisplayFrame(rect)
                Log.i("详情false","${rect.height()}")
                mSend.visibility = View.INVISIBLE
                mCollection.visibility = View.VISIBLE
                mRepor.visibility = View.VISIBLE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mEditComment.setCompoundDrawables(getDrawable(R.drawable.edit),null,null, null)
                }
                else{
                    mEditComment.setCompoundDrawables(resources.getDrawable(R.drawable.edit),null,null, null)
                }
            }

        }



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG,"${"testonTouchEvent"}")
        mEditComment.clearFocus()
        return true
    }

    fun addHeader(){
        mAdapter?.addHeader(object :RecyclerArrayAdapter.ItemView{
            override fun onBindView(headerView: View?) {
            }
            override fun onCreateView(parent: ViewGroup?): View {
                val  head = LayoutInflater.from(this@HomeItemDetailActivity).inflate(R.layout.fragment_homeitem,null)
                with(head){
                    mCommentMessage.removeAllViews()
                    TlZHomeAdapterDataSet.userContext(head,mItemModel!!,this@HomeItemDetailActivity,true)
                    TlZHomeAdapterDataSet.userHead(head,mItemModel!!,this@HomeItemDetailActivity)
                }

                return  head
            }
        })
    }


    fun loadCommentDataFirst(){
       // && mItemModel!!.commentnum > 3
           if(mItemModel!!.comments.count() != 0  && mItemModel!!.userId != "" && mItemModel!!.userName != null){

               HomeNetLoad().commentLoadData(mItemModel!!.id,mPage,mItemModel!!.userId.toInt(),object : IHomeCommentRequestResult {
                override fun success(model: HomeCommentModelS) {
                    val  comments = model.data
                    for (c in comments){
                        mComments?.add(c)
                    }
                    mHomeDetails.post(object :Runnable{
                        override fun run() {
                            Log.i(TAG,"comment:${mComments}")
                            mAdapter = DetailsAdapter(this@HomeItemDetailActivity, mItemModel!!,mComments)
                            mHomeDetails.adapter = mAdapter
                            addHeader()
                           // mAdapter?.setMore(R.layout.view_more,this)
                        }
                    })
                }
                override fun faile(error: String) {

                }
            })
           }
           else{
               mAdapter = DetailsAdapter(this@HomeItemDetailActivity, mItemModel!!,mItemModel!!.comments)
               mHomeDetails.adapter = mAdapter
               addHeader()
           }


        }

    fun  initData(){
        mItemModel =  intent.extras.getParcelable<HomeItemModel>(TLZConstant.HOME_MODEL_DETAIL)
        mHomeDetails.setLayoutManager(LinearLayoutManager(this))

        loadCommentDataFirst()

}

    /**
     * 分享
     */
    fun  share(V:View){

//        val oks = OnekeyShare()
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("分享");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//       // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        oks.show(this)

    }

    /**
     * 收藏
     */
    fun collect(V: View){

       // TLZTool.showMessageView(V,"收藏")
        val model = TLZTool.getUserMessage(this)
        if (model!=null){
               V.isSelected = !V.isSelected
               if (V.isSelected){

                   UserNetLoad.collect(model.userid,mItemModel!!.id, {
                       V.post {
                           V.setBackgroundResource(R.drawable.collect_selected)
                       }

                   })



               }
               else{
                   V.setBackgroundResource(R.drawable.collect)
               }
        }
        else {
            Snackbar.make(mCollection,"请先登录",Snackbar.LENGTH_SHORT).show()
        }

    }

    fun  jubao(v: View){
        Snackbar.make(mCollection,"举报成功",Snackbar.LENGTH_SHORT).show()
    }
}
