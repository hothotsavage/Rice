package site.marqstree.kotlin.rice.widget.webview.event

import com.alibaba.fastjson.JSONObject
import com.blankj.utilcode.util.TimeUtils
import site.marqstree.kotlin.rice.net.bean.user.UserInfo
import site.marqstree.kotlin.rice.util.LogUtil
import site.marqstree.kotlin.rice.util.UserUtil


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview.event
 * 文件名: LoginEvent
 * 创建者: marqstree
 * 创建时间: 2020/4/3 22:42
 * 描述: TODO
 */
/**
 * 从SP中读取用户名和md5密码
 */
class LoadUserPasswordEvent:IEvent {
    override fun execute(params: String): String {
        //若用户信息还未过期
        //返回用户名和md5密码
        if(TimeUtils.getNowMills()<=UserUtil.getUserEndMilliSecond()) {
            return """
                {
                    "name":${UserUtil.getUserName()}
                    "md5Password":${UserUtil.getUserMd5Password()}
                }
            """.trimIndent()
        }
        //若用户信息已过期
        //返回空用户名和md5密码
        else{
            return """
                {
                    "name": "",
                    "md5Password": ""
                }
            """.trimIndent()
        }
    }
}