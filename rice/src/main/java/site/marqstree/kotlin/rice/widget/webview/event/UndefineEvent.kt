package site.marqstree.kotlin.rice.widget.webview.event

import site.marqstree.kotlin.rice.util.LogUtil


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview.event
 * 文件名: UndefineEvent
 * 创建者: marqstree
 * 创建时间: 2020/3/22 14:03
 * 描述: TODO
 */
class UndefineEvent : IEvent {
    override fun execute(params: String): String {
        LogUtil.v("UndefineEvent", params)
        return ""
    }
}