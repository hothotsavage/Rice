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
    //只能传一个字符串参数
    //可以通过解析json字符串，获取传入的参数明细
    //json中含默认字段：action，表示要调用的接口名称
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