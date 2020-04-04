package site.marqstree.kotlin.rice.config.constant


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.config.constant
 * 文件名: Const
 * 创建者: marqstree
 * 创建时间: 2020/3/21 22:01
 * 描述: TODO
 */
object Const {
    class ResultCode {
        companion object {
            const val SUCCESS = 0
        }
    }

    class SPKey{
        companion object{
            //搜索历史在SP中的key
            const val SEARCH_HISTORY = "SP_SEARCH_HISTORY"

            //token存入sp中的key
            //将UserId作为token放入http head
            //在okhttp拦截器中取出sp中的token，并写入http head
            const val TOKEN = "TOKEN"

            //WebView的cookie存入sp的key
            const val COOKIE = "COOKIE"

            //用户信息在SP中的key
            //用户id
            const val USER_ID = "SP_USER_ID"
            //用户名称
            const val USER_NAME = "SP_USER_NAME"
            //用户电话
            const val USER_MOBILE = "SP_USER_MOBILE"
            //用户头像
            const val USER_ICON = "SP_USER_ICON"
            //用户性别
            const val USER_GENDER = "SP_USER_GENDER"
            //用户签名
            const val USER_SIGN = "SP_USER_SIGN"
            //用户md5密码
            const val USER_MD5_PASSWORD = "SP_USER_MD5_PASSWORD"
            //过期时间(单位：毫秒)
            const val USER_END_MILLI_SECOND = "SP_USER_END_MILLI_SECOND"
        }
    }

    //X5WebView
    class WebView{
        companion object{
            const val PATH_WEB_ACTIVITY = "/rice/activity/web"
            //浏览器中注入的java对象名
            //此对象挂在window下
            const val ANDROID_JS_BRIDGE = "AndroidJSBridge"
        }
    }

    class WebActivity{
        companion object{
            //保存用户密码事件名
            const val SAVE_USER_PASSWORD_ACTION = "SAVE_USER_PASSWORD_ACTION"
            //清除用户信息事件名
            const val CLEAR_USER_PASSWORD_ACTION = "CLEAR_USER_PASSWORD_ACTION"
            //载入用户密码
            const val LOAD_USER_PASSWORD_ACTION = "LOAD_USER_PASSWORD_ACTION"
        }
    }
}