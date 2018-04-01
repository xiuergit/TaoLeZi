package com.xiuer.taolezi.Popularize

import android.content.Context
import android.util.Log
import android.view.View
import com.mc.ra.a.*
import com.xiuer.taolezi.Common.TLZConstant
import com.xiuer.taolezi.Home.HomeItemModel
import org.json.JSONObject

/**
 * Created by xiuer on 2018/3/25.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
object PopularizeTool {

    /**
     *  原生广告 一个大图
     */

    fun  primordialBigImage(context:Context,sucess:(home:MutableList<HomeItemModel>)->Unit){

        var  models:MutableList<HomeItemModel> = mutableListOf()

//
//            primordialBigImageM(context,{
//                model ->
//                models.add(model)
//                primordialImages(context,{
//                    model ->
//                    models.add(model)
//                    sucess(models)
//                })
//
//            })

            primordialBigImageM(context,{
                model ->
                models.add(model)

                primordialBigImageM(context,{
                    model ->
                    models.add(model)
                    primordialBigImageM(context,{
                        model ->
                        models.add(model)
                        primordialBigImageM(context,{
                            model ->
                            models.add(model)
                            primordialBigImageM(context,{
                                model ->
                                models.add(model)

                                primordialImages(context,{
                                    model ->
                                    models.add(model)

                                    primordialImages(context,{
                                        model ->
                                        models.add(model)

                                        primordialImages(context,{
                                            model ->
                                            models.add(model)

                                        })
                                        primordialImages(context,{
                                            model ->
                                            models.add(model)

                                        })
                                        primordialImages(context,{
                                            model ->
                                            models.add(model)
                                            sucess(models)
                                        })
                                    })
                                })


                            })


                        })

                    })

                })

            })


    }

    fun primordialBigImageM(context:Context,sucess:(model:HomeItemModel)->Unit){

        McInit.getInstance().getOpenNativeAd(context, McType.MESSAGE_BIG_IMG,object : IMcNativeSuccessBack {

            override fun onFailed(p0: String?) {
                Log.i("广告","onError：${p0}")
            }
            override fun onSuccess(p0: McSDKInfo?) {
                Log.i("广告","${p0} OnSuccess")
                if (p0 != null){
                    val model = HomeItemModel(0,p0.wenzi,"",p0.gotourl,"",
                            0,0,p0.imgurl,0,0,
                            0,0,TLZConstant.AD_TYPE,0,0,
                            false, listOf(),"",0,false)
                    sucess(model)
                    //McInit.getInstance().uploadShow(context, p0);
                }
            }
        })
    }




    /**
     *   原生广告  三小图
     */
    fun  primordialImages(context:Context,sucess:(model:HomeItemModel)->Unit){

        McInit.getInstance().getOpenNativeAd(context, McType.MESSAGE_IMGS,object : IMcNativeSuccessBack {
            override fun onFailed(p0: String?) {
                Log.i("广告","onError：${p0}")

            }
            override fun onSuccess(p0: McSDKInfo?) {

                if (p0 != null){

                    val url = "S${p0.imgurl}*${p0.imgurl2}*${p0.imgurl3}"

                    val json = JSONObject()
                    json.putOpt("urlOne",p0.imgurl)
                    json.putOpt("urltwo",p0.imgurl2)
                    json.putOpt("urlthree",p0.imgurl3)

                    val model = HomeItemModel(0,p0.wenzi,"",p0.gotourl,"",
                            0,0,json.toString(),0,0,
                            0,0,TLZConstant.AD_TYPE,0,0,
                            false, listOf(),"",TLZConstant.ADSMALL_TYPE,false)
                    sucess(model)
                    //McInit.getInstance().uploadShow(context, p0);
                }

            }
        })
    }



    /**
     *    横幅广告
     */
    fun adBannerImages(context:Context,sucess:()->Unit,sucessLoad: (p0:View?) -> Unit){
        McInit.getInstance().getOpenView(context, McType.BANNER,true,object: IMcSuccessBack {
            override fun OnSuccess(p0: String?) {
                Log.i("广告","OnSuccess：${p0}")
                sucess()
            }
            override fun onClick(p0: String?) {
                Log.i("广告点击"," onClic：${p0}")
                sucess()
            }
            override fun onError(p0: String?) {
                Log.i("广告广告加载失败","onErro：${p0}")
            }
            override fun OnLoadAd(p0: View?) {
                Log.i("广告","onLoadAd：${p0}")
                sucessLoad(p0)
            }

        })

    }
    /*
    * 首页广告
    */
    fun homeAd(context:Context,sucess:(message:Int)->Unit,sucessLoad: (p0:View?) -> Unit,error:()->Unit){
        McInit.getInstance().getOpenView(context, McType.FULL_SCREEN,true,object: IMcSuccessBack {
            override fun OnSuccess(p0: String?) {
                Log.i("广告","OnSuccess：${p0}")
                sucess(0)
            }

            override fun onClick(p0: String?) {
                Log.i("广告被点击"," onClic：${p0}")
                sucess(1)
            }

            override fun onError(p0: String?) {
                Log.i("广告","onErro：${p0}")
                error()

            }

            override fun OnLoadAd(p0: View?) {
                Log.i("广告","onLoadAd：${p0}")
//                p0?.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
//                setContentView(p0)
                sucessLoad(p0)
                //mRootHomeView.addView(p0)
            }

        })

    }


    /**
     * 红包
     */

    fun hongbao(context:Context,success:(view:View?)->Unit){

        McInit.getInstance().getOpenView(context,McType.hongbaoAd,true,object:IMcSuccessBack{
            override fun OnLoadAd(p0: View?) {
                success(p0)
            }

            override fun onClick(p0: String?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun OnSuccess(p0: String?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(p0: String?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }



}