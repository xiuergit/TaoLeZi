package com.xiuer.taolezi.Home

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Common.TLZTool
import com.xiuer.taolezi.NetModeLoad.HomeLoadData
import com.xiuer.taolezi.NetModeLoad.UserNetLoad
import com.xiuer.taolezi.Popularize.PopularizeActivity
import com.xiuer.taolezi.Popularize.PopularizeTool
import com.xiuer.taolezi.R
import kotlinx.android.synthetic.main.fragment_homeitem_list.*
import java.util.*

class HomeItemFragment : Fragment(),RecyclerArrayAdapter.OnMoreListener,SwipeRefreshLayout.OnRefreshListener {

    private var mType = 1000
    private var mIsUserJoke = false
    private var mIsUserCollect = false
    private var mIsopenpop = false


    private var mPage = 0

    private var mListView: EasyRecyclerView? = null
    private var mHomeItemModels: MutableList<HomeItemModel>? = null
    var mAdapter: HomeItemAdapter? = null

    private var mLoadMessage: (() -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mType = arguments.getInt(Request_Type_TAG)
            mIsUserCollect = arguments.getBoolean(isUserCollect_TAG)
            mIsUserJoke = arguments.getBoolean(isUserJoke_TAG)
            mIsopenpop = arguments.getBoolean(PopularizeISOpenTag)
            Log.i(TAG,"\n\n\n\n 0 。onCreate(savedInstanceState: Bundle?):${mType} ${mIsopenpop}")
        }

    }



    val loadMainData:()->Unit ={
        Log.i("第一次加载数据", "page：${mPage} ，mtype：${mType}")
        HomeLoadData.homeData(mType, mPage, { models ->
            Log.i("数据加载完成", "models：size：= ${models.size}")
            addPopularize(models,{
                models ->
                Log.i("update", "page：${mPage} ，mtype：${mType}")
                updateUI(models)
                mPage++

            })
        }, {
            mListView?.post({
                mLoading.hide()
                mListView?.setErrorView(R.layout.view_error)
                //Toast.makeText(context, "服务器错误。。。", Toast.LENGTH_LONG).show()
            })

        })


    }

    val loadCollectData:()->Unit ={

        Log.i("loadCollectData:","page：${mPage}")
        val model = TLZTool.getUserMessage(context)
         if( model != null){

             UserNetLoad.collects(mPage,model.userid,{
                   models ->
                 updateUI(models)
                 mPage++
             },{
                  TLZTool.showErrView(context)
             } )
         }
        else{
             Toast.makeText(context,"请先登录",Toast.LENGTH_LONG).show()
         }


        }

    fun  addPopularize(models:MutableList<HomeItemModel>,sucess:(models:MutableList<HomeItemModel>)->Unit){
        var models = models
        var adIndexs = mutableListOf<Int>()
        if (models.count() != 0){
            for (i in 0..models.count()){
                if (i%5 == 0){
                    Log.i("插入：","广告位置 ${i}")
                    adIndexs.add(i)
                }
            }
        }
        PopularizeTool.primordialBigImage(context,{homeItemmodels->
            if( adIndexs.count() != 0) {
                for (i in 1..adIndexs.count() - 1) {
                    var d:Int = 0
//                    while (d  < 0) {
                        d =  Math.abs(Random().nextInt() % homeItemmodels.count())
                //}
                    Log.i("随机生成的数：","广告位置 ${d}")
                    models.add(adIndexs[i], homeItemmodels[d])
                }
                sucess(models)
            }

        })
    }


    fun addFooter(){
        mAdapter?.addFooter(object :RecyclerArrayAdapter.ItemView{
            override fun onBindView(headerView: View?) {
            }
            override fun onCreateView(parent: ViewGroup?): View {
                val v =  layoutInflater.inflate(R.layout.view_nomore,null)
                return  v
            }})
    }

    fun updateUI(models:MutableList<HomeItemModel>){

        Log.i("updateUI","page:${mPage}")
            if (mPage == 0){
                mHomeItemModels = models
                mAdapter = HomeItemAdapter(context,mHomeItemModels)
                mListView?.post(object :Runnable{
                    override fun run() {
                        mLoading.hide()
                        mListView?.adapter = mAdapter
                        adapterSet()

                    }
                })

            }
            else{

                          Log.i("updateUI","page:${mPage}modelssize:${models.size}")
                          mListView?.post {
                              if (models.size != 0){
                                  mAdapter?.addAll(models)

                              } else{
                                  mAdapter?.stopMore()
                                  mAdapter?.pauseMore()
                              }

                              for (model in models){
                                  mHomeItemModels?.add(model)
                              }


                          }

            }
    }




    val loadJokeData:()->Unit = {

        Log.i("loadCollectData:","page：${mPage}")
        val model = TLZTool.getUserMessage(context)
        if( model != null){

            UserNetLoad.dunzis(mPage,model.userid,{
                models ->
                updateUI(models)
                mPage++
            },{
                TLZTool.showErrView(context)
            } )
        }
        else{
            Toast.makeText(context,"请先登录",Toast.LENGTH_LONG).show()
        }


    }


    fun loadData(){

        if (!mIsUserCollect&&!mIsUserJoke){
            //加载 首页数据
            mLoadMessage = loadMainData

            loadMainData?.invoke()

        }
        if(mIsUserJoke&&!mIsUserCollect){
            //加载 我的段子
            mLoadMessage = loadJokeData
            loadJokeData?.invoke()


        }
        if(!mIsUserJoke&&mIsUserCollect){
            //加载 我的收藏
            mLoadMessage = loadCollectData
            loadCollectData?.invoke()
        }

    }


    //刷新数据
    fun  refreshData(){

    }



    override fun onRefresh() {
        Log.i(TAG,"onRefresh()")
        mPage = 0
        loadData()
    }


    override fun onMoreShow() {
        Log.i(TAG,"加载更多onMoreShow()")
        mLoadMessage?.invoke()
//        if (mIsopenpop && mPage == 1){
//            startActivity(Intent(context, PopularizeActivity::class.java))
//        }

    }

    override fun onMoreClick() {
        Log.i(TAG,"加载更多onMoreClick()")

    }


    fun  adapterSet(){

        mAdapter?.setMore(R.layout.view_more,this)

       // mListView?.setRefreshListener(this)

        mAdapter?.setOnItemClickListener( {
            position ->

            Log.i(TAG,"当前点击的位置OnItemClickL为：${position}")
            if ((position+1 > 10 )&& (position-10)%6 == 0 ){
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(mHomeItemModels!![position].userIcon));
                startActivity(intent);
            }else{
                val  intent =  Intent(context,HomeItemDetailActivity::class.java)
                val  bundle = Bundle()
                bundle.putParcelable(TLZConstant.HOME_MODEL_DETAIL,mHomeItemModels!![position])
                intent.putExtras(bundle)
                context.startActivity(intent)
            }

        })
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_homeitem_list, container, false)
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "2。。。onViewCreate:type:${mType} .....mpage:${mPage}")
        mPage = 0
        mHomeList.setLayoutManager(LinearLayoutManager(context))
        mListView = mHomeList
        loadData()
    }






    companion object {

        private val Request_Type_TAG = "type"
        private val isUserCollect_TAG = "isUserCollect_TAG"
        private val isUserJoke_TAG = "isUserJoke_TAG"
        private  val TAG = "HomeItemFragment"

        private  val PopularizeISOpenTag = "PopularizeISOpenTag"

        /**
         *  类型 请求数据的type 1000 推荐 。。。
         * @param type :   TLZConstant.HOmeTabType
         * @param isUserCollect :当前显示的是否是 用户的收藏
         * @param isUserJoke :当前显示的是否是 用户的段子
         */
        fun newInstance(type: Int,isUserJoke:Boolean,isUserCollect:Boolean): HomeItemFragment {
            val fragment = HomeItemFragment()
            val args = Bundle()
            args.putInt(Request_Type_TAG, type)
            args.putBoolean(isUserCollect_TAG,isUserCollect)
            args.putBoolean(isUserJoke_TAG,isUserJoke)
            fragment.arguments = args
            return fragment
        }


        /**
         *  类型 请求数据的type 1000 推荐 。。。
         * @param type :   TLZConstant.HOmeTabType
         * @param isOpenpop :是否开启广告
         *
         */
        fun newInstance(type: Int,isOpenpop:Boolean): HomeItemFragment {
            val fragment = HomeItemFragment()
            val args = Bundle()
            args.putInt(Request_Type_TAG, type)
            args.putBoolean(isUserCollect_TAG,false)
            args.putBoolean(isUserJoke_TAG,false)
            Log.i("ewInstance:","是否开广告 ：${isOpenpop}")
            args.putBoolean(PopularizeISOpenTag,isOpenpop)
            fragment.arguments = args
            return fragment
        }

    }
}
