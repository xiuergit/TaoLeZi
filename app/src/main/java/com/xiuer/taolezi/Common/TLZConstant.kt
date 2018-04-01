package com.xiuer.taolezi.Common

/**
 * Created by xiuer on 2018/3/2.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
 class  TLZConstant {


 //推荐 1000
//热门 1001
//段子 1002
//冷笑话 1003
//顺口溜 1004
//歇后语 1005
//原创   1006
//综合   1007
//娱问   1008
 companion object {
  val HomeTabNames:Array<String> = arrayOf("推荐", "热门", "段子", "娱问", "冷笑话", "顺口溜", "歇后语", "原创", "综合")
  val HomeTabTypes:Array<Int> = arrayOf(1000,1001,1002,1008,1003,1004,1005,1006,1007)

  val homeURL = "http://139.196.185.36:8888"
  val homeRequestURL = "http://139.196.185.36:8888/joke/content"



  // static let SERVERURL = "http://172.16.50.175:8888";

  val ALLJOKECONTENTURL  = "/joke/content" //首页界面 所有数据

  val JOKECOMMENTURL     = "/joke/comment";//评论

  val POSTQUESTIONURL    = "/joke/release"; //发布
  val JOKETYPESURL       = "/joke/type";
  val MYJOKEURL          = "/joke/my";
  val COLLECTJOKEURL     = "/joke/collect";  //点击收藏
  val MYCOLLECTJOKEURL   = "/joke/mycollect"

  val USERLOGINURL = "/user/login";
  val USERREGISTERURL = "/user/register"
  val USERMODIFYUSERINFO = "/user/modify"
  val USERMODIFYPHOTOURL = "/user/modifyPhoto"


  val COMMENTADDURL = "/comment/add"
  val COMMENTDETAILURL = "/comment/detail"
  val COMMENTRELATEURL = "/comment/relate"

  val SUGGEST = "/suggest";


  val USER_FRAGMENT_TAG = "我的"
  val HOME_FRAGMENT_TAG = "淘乐子"
  val SEND_FRAGMENT_TAG = "发布"

  val HOME_MODEL_DETAIL = "HOME_MODEL_DETAIL"

  val netErr = "服务器开小差了。。。。。"

  val TimeOut = "检查当前的网络连接情况"


  val USER_DETAILS_TITLE = "title"

  val MY_DONGTAI_TAG = "我的动态"
  val MY_Collect_TAG = "我的收藏"
  val MY_DUANZI_TAG = "我的段子"


  val AD_TYPE = 500; //广告类型
  val ADSMALL_TYPE = 500; //广告类型 3小图




//  WindowManager manager = this.getWindowManager();
//  DisplayMetrics outMetrics = new DisplayMetrics();
//  manager.getDefaultDisplay().getMetrics(outMetrics);
//  int width = outMetrics.widthPixels;
//  int height = outMetrics.heightPixels;

 }



 //val HomeRequestType = mapOf(HomeTabNames[0] to 1000,HomeTabNames[0] to 1000,HomeTabNames[0] to 1000,HomeTabNames[0] to 1000,HomeTabNames[0] to 1000,HomeTabNames[0] to 1000,HomeTabNames[0] to 1000)
}