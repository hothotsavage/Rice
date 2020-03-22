package site.marqstree.kotlin.rice.net.interceptor

import com.blankj.utilcode.util.SPStaticUtils
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.interceptor
 * 文件名: AddCookieIntercepter
 * 创建者: marqstree
 * 创建时间: 2020/2/28 9:16
 * 描述: 从sp中取出webview中拦截到的后端web服务器的cookie
 * 将该cookie附加到原生http请求中，使得原生api能跨域访问webview的后端web服务器
 */
class AddCookieIntercepter : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //从sp中获取WebView访问后端web服务器时拦截到的cookie
        val cookieWebView: String = SPStaticUtils.getString("cookie")
        if (cookieWebView != "") {
            Observable.just(cookieWebView)
                .subscribe { cookie ->
                    //给原生API请求附加上WebView拦截下来的cookie
                    builder.addHeader("Cookie", cookie)
                }
        }
        return chain.proceed(builder.build())
    }
}