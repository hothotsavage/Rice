package site.marqstree.kotlin.rice.net.interceptor

import com.blankj.utilcode.util.SPStaticUtils
import okhttp3.Interceptor
import okhttp3.Response
import site.marqstree.kotlin.rice.config.constant.Const


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.interceptor
 * 文件名: TokenInterceptor
 * 创建者: marqstree
 * 创建时间: 2020/2/27 22:07
 * 描述: 将sp中的token存入http请求头
 */
class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content_Type","application/json")
            .addHeader("charset","UTF-8")
            //将UserId作为token放入head
            .addHeader("token", SPStaticUtils.getString(Const.SPKey.TOKEN))
            .build()
        return chain.proceed(request)
    }
}