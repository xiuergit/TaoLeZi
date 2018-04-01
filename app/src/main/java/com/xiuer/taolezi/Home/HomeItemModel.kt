package com.xiuer.taolezi.Home

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by xiuer on 2018/3/5.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */

//
//commentid = 0;
//content = "\U54c8\U54c8\U54c8\U54c8\U5bb6";
//id = 173;
//jokeid = 210;
//replycontent = "<null>";
//replycount = 0;
//replyuserid = 0;
//replyusername = "<null>";
//time = "12\U5929\U524d";
//timestamp = 1520058804000;
//usericon = "http://139.196.185.36:8888/imgs/usericon?imgName=icon1.jpg";
//userid = 100;
//username = "HaleWang ";
data class HomeCommontModel(
    var commentid:Int,
    var content:String,
    var id :Int,
    var jokeid :Int,
    var replycontent:String,
    var replycount :Int,
    var replyuserid :Int,
    var replyusername :String,
    var time :String,
    var timestamp:Long,
    var usericon :String,
    var userid :Int,
    var username :String
): Parcelable {

    init {
        commentid  = 0
        content = ""
        id = 0
        jokeid = 0
        replycontent = ""
        replycount = 0

    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(commentid)
        parcel.writeString(content)
        parcel.writeInt(id)
        parcel.writeInt(jokeid)
        if (replycontent == null){
            replycontent = ""
        }
        parcel.writeString(replycontent)
        parcel.writeInt(replycount)
        parcel.writeInt(replyuserid)
        if (replyusername== null){
            replyusername = ""
        }
        parcel.writeString(replyusername)
        parcel.writeString(time)
        parcel.writeLong(timestamp)
        parcel.writeString(usericon)
        parcel.writeInt(userid)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeCommontModel> {
        override fun createFromParcel(parcel: Parcel): HomeCommontModel {
            return HomeCommontModel(parcel)
        }

        override fun newArray(size: Int): Array<HomeCommontModel?> {
            return arrayOfNulls(size)
        }
    }

}

  class HomeItemModel(
        var id: Int,
        var content: String,
        var userId: String,
        var userIcon: String,
        var userName: String,
        var is_public: Int,
        var browsenum: Int,
        var img: String,
        var imgW: Int,
        var imgH: Int,
        var praisenum: Int,
        var commentnum: Int,
        var type: Int,
        var timestamp: Long,
        var order: Int,
        var isad: Boolean,
        var comments: List<HomeCommontModel>,
        var time: String,
        var viewtype: Int,
        var collected: Boolean
) : Parcelable {


      constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.createTypedArrayList(HomeCommontModel),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        if (content == null){
            content = ""
        }
        parcel.writeString(content)
        if (userId == null){
            userId = ""
        }
        parcel.writeString(userId)
        if (userIcon == null){
            userIcon = ""
        }
        parcel.writeString(userIcon)
        if (userName == null){
            userName = ""
        }
        parcel.writeString(userName)
        parcel.writeInt(is_public)
        parcel.writeInt(browsenum)
        if (img == null){
            img = ""
        }
        parcel.writeString(img)
        parcel.writeInt(imgW)
        parcel.writeInt(imgH)
        parcel.writeInt(praisenum)
        parcel.writeInt(commentnum)
        parcel.writeInt(type)
        parcel.writeLong(timestamp)
        parcel.writeInt(order)
        parcel.writeByte(if (isad) 1 else 0)
        parcel.writeTypedList(comments)
        if (time == null){
            time = ""
        }
        parcel.writeString(time)
        parcel.writeInt(viewtype)
        parcel.writeByte(if (collected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeItemModel> {
        override fun createFromParcel(parcel: Parcel): HomeItemModel {
            return HomeItemModel(parcel)
        }

        override fun newArray(size: Int): Array<HomeItemModel?> {
            return arrayOfNulls(size)
        }
    }

}

data class HomeModel(
        var code: Int,
        var timestamp: Int,
        var data: MutableList<HomeItemModel>,
        var otherData: List<Int>
)

data class HomeCommentModelS(
        var code: Int,
        var timestamp: Int,
        var data: MutableList<HomeCommontModel>,
        var otherData: List<Int>
)
