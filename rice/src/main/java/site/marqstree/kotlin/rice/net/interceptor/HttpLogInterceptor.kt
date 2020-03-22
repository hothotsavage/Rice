package site.marqstree.kotlin.rice.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.interceptor
 * 文件名: HttpLoggingInterceptor
 * 创建者: marqstree
 * 创建时间: 2020/2/27 20:29
 * 描述: http日志拦截器
 */
class HttpLogInterceptor(): Interceptor{

    private val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return httpLoggingInterceptor.intercept(chain)
    }


}