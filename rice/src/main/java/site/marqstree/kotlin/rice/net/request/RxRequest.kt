package site.marqstree.kotlin.rice.net.request

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.NetworkUtils
import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import site.marqstree.kotlin.rice.app.AppManager
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import site.marqstree.kotlin.rice.net.exception.NetUnavailableException
import site.marqstree.kotlin.rice.net.method.HttpMethod
import site.marqstree.kotlin.rice.net.retrofit.RetrofitBuilder
import site.marqstree.kotlin.rice.net.rxservice.*
import site.marqstree.kotlin.rice.widget.loader.Loader
import site.marqstree.kotlin.rice.widget.loader.LoaderStyle
import java.io.File
import java.util.*
import kotlin.properties.Delegates

/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.client
 * 文件名: RetrofitRequest
 * 创建者: marqstree
 * 创建时间: 2020/2/28 9:30
 * 描述: retrofit发起http请求的客户端
 */
class RxRequest private constructor(){

    //懒加载获取Retrofit的无参get方式RxService实例
    //返回json字符串
    private val rxServiceNoParam: RxServiceNoParam by lazy{
        RetrofitBuilder.INSTANCE
            .retrofitClient
            .create(RxServiceNoParam::class.java)
    }

    //懒加载获取Retrofit的参数数组方式传参RxService实例
    //返回json字符串
    private val rxServiceByParam: RxServiceByParam by lazy {
        RetrofitBuilder.INSTANCE
            .retrofitClient
            .create(RxServiceByParam::class.java)
    }

    //懒加载获取Retrofit的json方式传参RxService实例
    //返回json字符串
    private val rxServiceByJson: RxServiceByJson by lazy {
        RetrofitBuilder.INSTANCE
            .retrofitClient
            .create(RxServiceByJson::class.java)
    }

    //懒加载获取Retrofit的上传RxService实例
    //返回json字符串
    private val rxServiceUpload: RxServiceUpload by lazy {
        RetrofitBuilder.INSTANCE
            .retrofitClient
            .create(RxServiceUpload::class.java)
    }

    //懒加载获取Retrofit的下载RxService实例
    //返回json字符串
    private val rxServiceDownload: RxServiceDownload by lazy {
        RetrofitBuilder.INSTANCE
            .retrofitClient
            .create(RxServiceDownload::class.java)
    }

    //延迟初始化
    private var url by Delegates.notNull<String>()
    private var params = WeakHashMap<String, Any>()
    private var body by Delegates.notNull<RequestBody>()
    private lateinit var context:Context
    private var loaderStyle: LoaderStyle = AppConfig.getConfiguration(ConfigKeys.LOADER_STYLE)!!
    private var file by Delegates.notNull<File>()

    companion object{
        fun builder(): Builder {
            return Builder()
        }
    }

    private fun request(method: HttpMethod):Observable<String>{
        //检查网络是否可用
        if( !NetworkUtils.isConnected() ){
            return Observable.error(
                NetUnavailableException(
                    -1,
                    "网络不可用"
                )
            )
        }

        //显示网络加载进度对话框
        showLoading()

        //发起网络请求
        //耗时操作
        val retObs:Observable<String> = when(method){
            HttpMethod.GET -> {
                if(params.isEmpty()){
                    return rxServiceNoParam.get(url)
                }else {
                    return rxServiceByParam.get(url,params)
                }
            }
            HttpMethod.POST -> {
                if (!params.isEmpty()) {
                    return rxServiceByParam.post(url, params)
                }else{
                    return rxServiceByJson.post(url,body)
                }
            }
            HttpMethod.PUT -> {
                if (!params.isEmpty()) {
                    return rxServiceByParam.put(url, params)
                }else{
                    return rxServiceByJson.put(url,body)
                }
            }
            HttpMethod.DELETE -> {
                if (!params.isEmpty()) {
                    return rxServiceByParam.delete(url, params)
                }else{
                    return rxServiceByJson.delete(url,body)
                }
            }
        }

        //将rxJava与Activity/Fragment绑定生命周期
        //解决Rx在Activity关闭时，仍在订阅，导致内存泄漏问题
        //在Activity/Fragment的关闭事件中，关闭RxJava订阅事件
        return retObs.bindUntilEvent(
                    context as LifecycleOwner,
                    Lifecycle.Event.ON_STOP)
    }

    fun upload():Observable<String>{
        //显示网络加载进度对话框
        showLoading()

        //发起网络请求
        //耗时操作
        val requestBody: RequestBody = file.asRequestBody("text/x-markdown; charset=utf-8".toMediaType())
        val partBody: MultipartBody.Part = MultipartBody.Part.
            createFormData("file", file.getName(), requestBody)
        val retObs:Observable<String> = rxServiceUpload.upload(url, partBody)

        return retObs
    }

    //显示网络加载进度对话框
    private fun showLoading(){
        //传入当前栈顶的Activity的作为显示进度对话框的上下文
        if(!this::context.isInitialized) {
            context = AppManager.instance.currentActivity()
        }
        //显示自定义网络加载进度对话框
        if(this::context.isInitialized) {
            Loader.showLoading(context, loaderStyle)
        }
    }

    //建造者
    class Builder{
        private val rxRequest = RxRequest()

        fun setUrl(url: String):Builder{
            rxRequest.url = url
            return this
        }

        fun setParams(params: Map<String, Object>):Builder{
            rxRequest.params.putAll(params)
            return this
        }

        fun setParam(key: String, value: Any):Builder{
            rxRequest.params.put(key,value)
            return this
        }

        fun setFile(file: File):Builder{
            rxRequest.file = file
            return this
        }

        fun setFile(file: String):Builder{
            rxRequest.file = File(file)
            return this
        }

        fun setBodyByJson(json: String):Builder{
            //将json字符串转为okhttp的body对象
            val body: RequestBody = json.toRequestBody("application/json;charset=utf-8".toMediaType())
            rxRequest.body = body
            return this
        }

        //设置进度对话框的上下文
        //必须是Activity/Fragment的上下文
        fun setContext(context: Context):Builder{
            rxRequest.context = context
            return this
        }

        //设置进度对话图标
        fun setLoaderStyle(loaderStyle: LoaderStyle):Builder{
            rxRequest.loaderStyle = loaderStyle
            return this
        }

        fun get():Observable<String>{
            return rxRequest.request(HttpMethod.GET)
        }

        fun post():Observable<String>{
            return rxRequest.request(HttpMethod.POST)
        }

        fun put():Observable<String>{
            return rxRequest.request(HttpMethod.PUT)
        }

        fun delete():Observable<String>{
            return rxRequest.request(HttpMethod.DELETE)
        }

        fun upload():Observable<String>{
            if( !NetworkUtils.isConnected() ){
                return Observable.error(
                    NetUnavailableException(
                        -1,
                        "网络不可用"
                    )
                )
            }
            return rxRequest.upload()
        }

    }

}