package site.marqstree.kotlin.rice.widget.webview

import android.webkit.JavascriptInterface
import com.alibaba.fastjson.JSON
import site.marqstree.kotlin.rice.util.LogUtil
import site.marqstree.kotlin.rice.widget.webview.event.EventManager
import site.marqstree.kotlin.rice.widget.webview.event.IEvent


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview
 * 文件名: WebInterface
 * 创建者: marqstree
 * 创建时间: 2020/3/22 13:58
 * 描述: 注入js的java接口类
 */
class WebInterface {
    //供js调用的java通用接口
    @JavascriptInterface
    fun event(params: String): String {
        val action = JSON.parseObject(params).getString("action")
        val event: IEvent = EventManager.INSTANCE.createEvent(action)
        LogUtil.d("WEB_EVENT", params)
        if (event != null) {
            return event.execute(params)
        }
        return ""
    }
}