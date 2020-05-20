package site.marqstree.kotlin.rice.net.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import java.util.concurrent.TimeUnit


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net
 * 文件名: RetrofitBuilder
 * 创建者: marqstree
 * 创建时间: 2020/2/27 20:06
 * 描述: TODO
 */
class RetrofitBuilder private constructor(){

    /*
        单例实现
     */
    companion object {
        val INSTANCE: RetrofitBuilder by lazy {
            RetrofitBuilder()
        }
    }

    //全局单例retrofit客户端
    val retrofitClient: Retrofit

    init {
        //构建全局Retrofit客户端
        val baseUrl: String = AppConfig.getConfiguration(ConfigKeys.API_HOST)!!
        retrofitClient = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(initOkHttpClient())
            //.addConverterFactory(GsonConverterFactory.create())
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /*
     * OKHttp创建
    */
    private fun initOkHttpClient(): OkHttpClient {
        //全局OkHttpClient
        val okHttpBuilder: OkHttpClient.Builder = okhttp3.OkHttpClient.Builder()

        val intercepters: ArrayList<Interceptor> =
            AppConfig.getConfiguration(ConfigKeys.INTERCEPTOR)

        intercepters.forEach {
            okHttpBuilder.addInterceptor(it)
        }

        val timeOut: Long = AppConfig.getConfiguration(ConfigKeys.HTTP_TIME_OUT)

        val okHttpClient = okHttpBuilder
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .build()
        return okHttpClient
    }

}