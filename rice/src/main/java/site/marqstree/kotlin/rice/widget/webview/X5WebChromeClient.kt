package site.marqstree.kotlin.rice.widget.webview

import android.webkit.JsResult
import android.webkit.WebView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.tencent.smtt.sdk.WebChromeClient


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview
 * 文件名: X5WebChromeClient
 * 创建者: marqstree
 * 创建时间: 2020/3/22 13:19
 * 描述: WebView加载WebChromeClient,才能支持alert弹框
 */
class X5WebChromeClient: WebChromeClient(){
    override fun onJsAlert(
        view: com.tencent.smtt.sdk.WebView?,
        url: String?,
        message: String?,
        jsResult: com.tencent.smtt.export.external.interfaces.JsResult?
    ): Boolean {
        SweetAlertDialog(view!!.context, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText(message)
            .show()
        jsResult!!.confirm()
        return true
    }
}