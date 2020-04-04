package site.marqstree.kotlin.rice.widget.webview.event

import com.alibaba.fastjson.JSONObject
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
 * 从SP中删除用户信息
 * 成功：返回:0
 */
class ClearUserInfoEvent:IEvent {
    override fun execute(params: String): String {
        UserUtil.clearUserInfo()
        return "0"
    }
}