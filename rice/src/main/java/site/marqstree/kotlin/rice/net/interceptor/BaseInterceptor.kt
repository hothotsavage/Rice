package site.marqstree.kotlin.rice.net.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.interceptor
 * 文件名: BaseInterceptor
 * 创建者: marqstree
 * 创建时间: 2020/2/27 22:13
 * 描述: TODO
 */
abstract class BaseInterceptor : Interceptor {
    //获取url中的所有参数
    protected fun getUrlParameters(chain: Interceptor.Chain): LinkedHashMap<String, String?> {
        val url = chain.request().url
        val size = url.querySize
        val params =
            LinkedHashMap<String, String?>()
        for (i in 0 until size) {
            params[url.queryParameterName(i)] = url.queryParameterValue(i)
        }
        return params
    }

    //获取url中的指定参数
    protected fun getUrlParameters(
        chain: Interceptor.Chain,
        key: String?
    ): String? {
        val request = chain.request()
        return request.url.queryParameter(key!!)
    }

    //获取post的body中的参数
    protected fun getBodyParameters(chain: Interceptor.Chain): LinkedHashMap<String, String> {
        val formBody = chain.request().body as FormBody?
        val params =
            LinkedHashMap<String, String>()
        var size = 0
        if (formBody != null) {
            size = formBody.size
        }
        for (i in 0 until size) {
            params[formBody!!.name(i)] = formBody.value(i)
        }
        return params
    }

    //获取post的body中的指定参数
    protected fun getBodyParameters(
        chain: Interceptor.Chain,
        key: String?
    ): String? {
        return getBodyParameters(chain)[key]
    }
}