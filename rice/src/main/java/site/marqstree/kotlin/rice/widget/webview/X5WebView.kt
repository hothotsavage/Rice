package site.marqstree.kotlin.rice.widget.webview

import android.content.Context
import android.util.AttributeSet
import com.tencent.smtt.sdk.*
import com.tencent.smtt.sdk.WebView
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import site.marqstree.kotlin.rice.config.constant.Const


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview
 * 文件名: X5WebView
 * 创建者: marqstree
 * 创建时间: 2020/3/22 10:19
 * 描述: TODO
 */
class X5WebView @JvmOverloads constructor(context: Context,
                                          attributeSet: AttributeSet?=null,
                                          i: Int=0,
                                          map: Map<String, Object>?=null,
                                          b: Boolean=false
                                          ): WebView(context,attributeSet,i,b)
{
    init {
        /**
         * 基础配置
         */
        initWebViewSettings()
        initWebViewClient()
        initChromeClient()
        initJsBridge()
    }

    /**
     * 对 webview 进行基础配置
     */
    private fun initWebViewSettings() {

        //layoutParams = LayoutParams(
        //    ViewGroup.LayoutParams.MATCH_PARENT,
        //    ViewGroup.LayoutParams.MATCH_PARENT)

        //开启调试模式，允许PC端chrome调试
        setWebContentsDebuggingEnabled(true)

        //允许跨域读取cookie
        //android5之后，默认不允许跨域读取cookie
        CookieManager.getInstance()
            .acceptThirdPartyCookies(this)

        //不能横向滚动
        isHorizontalScrollBarEnabled=false
        //不能纵向滚动
        isVerticalScrollBarEnabled=false
        //允许截图
        isDrawingCacheEnabled=true
        //屏蔽长按事件
        isLongClickable=false

        //允许加载的网页执行 JavaScript 方法
        settings.javaScriptEnabled = true
        //设置网页不允许缩放
        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        //设置网页缓存方式为不缓存，方便我们的调试
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        //文件权限
        settings.allowFileAccess = true
        settings.setAllowFileAccessFromFileURLs(true)
        settings.setAllowUniversalAccessFromFileURLs(true)
        settings.allowContentAccess = true
        //缓存相关
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT

    }

    /**
     * 设置 webviewClient ，如果不进行这层设置，则网页打开默认会使用
     * 系统中的浏览器进行打开，而不是在本 APP 中进行打开。
     */
    private fun initWebViewClient() {
        webViewClient = X5WebViewClient()
    }

    /**
     * 监听网页中的url加载事件
     */
    private fun initChromeClient() {
        webChromeClient = X5WebChromeClient() as WebChromeClient
    }

    /**
     * 构建 JSBridge 对象，这里提供的 JSBridge 字符串会被挂载到
     * 网页中的 window 对象下面。
     *
     * window.AndroidJSBridge
     */
    private fun initJsBridge(){
        addJavascriptInterface(
            WebInterface(),
            //从自定义配置中取js对象名
            //没有自定义配置则取默认配置
            AppConfig.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE)?: Const.WebView.ANDROID_JS_BRIDGE)
    }
}