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
 * 保存用户名和密码到SP:
 * @return -1:内部错误 0: 登录成功
 */
class SaveUserPasswordEvent:IEvent {
    override fun execute(params: String): String {
        try {
            val userInfo: UserInfo = JSONObject.parseObject(params, UserInfo::class.java)
            if (userInfo.name.isBlank() || userInfo.md5Password.isBlank())
                return "-1"
            userInfo.endMilliSecond = TimeUtils.getNowMills()
            UserUtil.putUserInfo(userInfo)
            return "0"
        }
        catch (e:Exception){
            LogUtil.e(e.message)
            return "-1"
        }
    }
}