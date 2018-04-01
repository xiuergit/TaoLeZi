package com.xiuer.taolezi.User

import java.io.Serializable

/**
 * Created by xiuer on 2018/3/16.
 * @emil: 15536849144@163.com
 * @desc:
 *
 */
data class UserModel(
		var userid: Int,
		var nickname: String,
		var usericon: String,
		var id: Int,
		var deviceid: String,
		var password: String,
		var sex: Int
):Serializable{}

data class UserRegisterM(
		var code: Int,
		var timestamp: Int,
		var data: UserModel,
		var otherData: Any
)


