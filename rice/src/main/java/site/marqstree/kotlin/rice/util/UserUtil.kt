package site.marqstree.kotlin.rice.util

import com.blankj.utilcode.util.SPStaticUtils
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.net.bean.user.UserInfo


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.usercenter.util
 * 文件名: UserPrefsUtils
 * 创建者: marqstree
 * 创建时间: 2020/2/24 9:24
 * 描述: TODO
 */
/*
    本地存储用户相关信息
 */
object UserUtil {

    /*
        退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        SPStaticUtils.put(Const.SPKey.TOKEN, userInfo?.id ?: "")
        SPStaticUtils.put(Const.SPKey.USER_ID, userInfo?.id ?: "")
        SPStaticUtils.put(Const.SPKey.USER_ICON, userInfo?.icon ?: "")
        SPStaticUtils.put(Const.SPKey.USER_NAME, userInfo?.name ?: "")
        SPStaticUtils.put(Const.SPKey.USER_MOBILE, userInfo?.mobile ?: "")
        SPStaticUtils.put(Const.SPKey.USER_GENDER, userInfo?.gender ?: "")
        SPStaticUtils.put(Const.SPKey.USER_SIGN, userInfo?.sign ?: "")
        SPStaticUtils.put(Const.SPKey.USER_MD5_PASSWORD, userInfo?.md5Password ?: "")
        SPStaticUtils.put(Const.SPKey.USER_END_MILLI_SECOND, userInfo?.endMilliSecond ?: 0)
    }

    //清除用户信息
    fun clearUserInfo(){
        SPStaticUtils.put(Const.SPKey.TOKEN, "")
        SPStaticUtils.put(Const.SPKey.USER_ID, "")
        SPStaticUtils.put(Const.SPKey.USER_ICON, "")
        SPStaticUtils.put(Const.SPKey.USER_NAME, "")
        SPStaticUtils.put(Const.SPKey.USER_MOBILE, "")
        SPStaticUtils.put(Const.SPKey.USER_GENDER, "")
        SPStaticUtils.put(Const.SPKey.USER_SIGN, "")
        SPStaticUtils.put(Const.SPKey.USER_MD5_PASSWORD, "")
        SPStaticUtils.put(Const.SPKey.USER_END_MILLI_SECOND, 0)
    }

    //是否登陆
    fun isLogined():Boolean{
        return !SPStaticUtils.getString(Const.SPKey.USER_ID).isNullOrBlank()
    }

    fun getUserId():String{
        return SPStaticUtils.getString(Const.SPKey.USER_ID)
    }

    fun getUserIcon():String{
        return SPStaticUtils.getString(Const.SPKey.USER_ICON)
    }

    fun getUserName():String{
        return SPStaticUtils.getString(Const.SPKey.USER_NAME)
    }

    fun getUserMobile():String{
        return SPStaticUtils.getString(Const.SPKey.USER_MOBILE)
    }

    fun getUserGender():String{
        return SPStaticUtils.getString(Const.SPKey.USER_GENDER)
    }

    fun getUserSign():String{
        return SPStaticUtils.getString(Const.SPKey.USER_SIGN)
    }

    fun getUserMd5Password():String{
        return SPStaticUtils.getString(Const.SPKey.USER_MD5_PASSWORD)
    }

    fun getUserEndMilliSecond():Long{
        return SPStaticUtils.getLong(Const.SPKey.USER_END_MILLI_SECOND)
    }
}