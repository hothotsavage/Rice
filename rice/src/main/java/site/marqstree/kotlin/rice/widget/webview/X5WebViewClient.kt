package site.marqstree.kotlin.rice.widget.webview

import android.graphics.Bitmap
import android.os.Handler
import com.blankj.utilcode.util.SPStaticUtils
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.widget.loader.Loader


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview
 * 文件名: WebViewClientImpl
 * 创建者: marqstree
 * 创建时间: 2020/3/22 12:59
 * 描述:
 * 设置 webviewClient ，如果不进行这层设置，则网页打开默认会使用
   系统中的浏览器进行打开，而不是在本 APP 中进行打开。
 */
class X5WebViewClient: WebViewClient() {

    //载入页面前事件
    //1）开启无限转圈进度对话框
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        if(view!=null)
            Loader.showLoading(view.getContext())
    }

    //载入页面后事件
    //1）调用回调接口
    //2）关闭无限转圈进度对话框
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        //拦截webview的cookie，并存入sp
        // 待原生的okhttp3 api发起http请求时，
        // 从sp中取出webview中的cookie,
        // 并附加到原生http请求头中
        //拦截webview的cookie，并存入sp
        // 待原生的okhttp3 api发起http请求时，
        // 从sp中取出webview中的cookie,
        // 并附加到原生http请求头中
        syncCookie()
        AppConfig.getHandler().postDelayed(
            Runnable { Loader.stopLoading() }, 1000
        )
    }

    //将http请求的cookie存入sp
    //注意：此处的http请求是WebView发起的
    // 待原生的okhttp3 api发起http请求时，将webview中cookie附加到原生http请求头中
    // 使得原生http api也能跨域向web服务器发请求
    private fun syncCookie() {
        val manager = CookieManager.getInstance()
        val webHost: String = AppConfig.getConfiguration(ConfigKeys.API_HOST)
        if (webHost != null) {
            if (manager.hasCookies()) {
                val cookieStr = manager.getCookie(webHost)
                if (cookieStr != null && cookieStr != "") {
                    SPStaticUtils.put(Const.SPKey.TOKEN, cookieStr)
                }
            }
        }
    }
}