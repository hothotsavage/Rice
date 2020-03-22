package site.marqstree.kotlin.rice.net.interceptor

import androidx.annotation.RawRes
import com.blankj.utilcode.util.ResourceUtils
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import okhttp3.ResponseBody.Companion.toResponseBody

/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.interceptor
 * 文件名: DebugInterceptor
 * 创建者: marqstree
 * 创建时间: 2020/2/28 11:24
 * 描述: 生成Mock数据的测试用拦截器
 */
//构造OkHttp3的拦截器
//第一个参数:url
//第二个参数:资源文件id。资源文件中存放json响应字符串
class DebugInterceptor(private val debugUrl: String,
                       private val debugRawId: Int) : BaseInterceptor() {

    //构造json响应体
    private fun getResponse(chain: Interceptor.Chain, json: String): Response {

        val responseBody:ResponseBody = json.toResponseBody("application/json;charset=utf-8".toMediaType())

        return Response.Builder()
            .code(200)
            .addHeader("Content-Type", "application/json")
            .body(responseBody)
            .message("OK")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .build()
    }

    //用res/raw目录中的json文件，生成mock数据
    //@RawRes:检查ID是否是在res目录中自动生成的id
    //android自动将res目录中的文件生成唯一id
    private fun debugResponse(chain: Interceptor.Chain, @RawRes rawId: Int): Response {
        val json: String = ResourceUtils.readRaw2String(rawId)
        return getResponse(chain, json)
    }

    //拦截器接口函数
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: String = chain.request().url.toString()
        //如果url中含有debug拦截器指定的url字符串，则返回资源文件中定义的json
        return if (url.contains(debugUrl)) {
            debugResponse(chain, debugRawId)
        }
        //若url中不含debug拦截器指定的url字符串，则执行默认操作
        else chain.proceed(chain.request())
    }

}