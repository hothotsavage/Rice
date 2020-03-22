package site.marqstree.kotlin.rice.config

import android.app.Application
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.Iconify
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.Interceptor
import site.marqstree.kotlin.rice.util.LogUtil
import site.marqstree.kotlin.rice.widget.loader.LoaderStyle
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.app
 * 文件名: Configurator
 * 创建者: marqstree
 * 创建时间: 2020/2/25 15:00
 * 描述: 初始化配置工具类的建造者
 */
class Configurator private constructor(){

    //存储配置科目的HashMap
    //static final对象名要大写
    private object CONFIGS: HashMap<Any, Any>()

    //存储android-iconify字体的数组
    //iconify是一个矢量图标库
    //矢量字体图标可以无限放大而不会失真，模糊
    private object ICONS: ArrayList<IconFontDescriptor>()
    //存储Mock拦截器的数组
    private object INTERCEPTORS: ArrayList<Interceptor>()
    //全局Handler
    private object HANDLER: Handler()

    init {
        //实例化建造者时，标识开始初始化
        CONFIGS.put(ConfigKeys.CONFIG_READY, false)
        CONFIGS.put(ConfigKeys.HANDLER, Configurator.HANDLER)
    }

    //通过伴生对象获取初始化配置工具类的建造者的单例
    //等价于java中的静态方法
    companion object{
        fun getInstance(): Configurator{
            return Holder.instance
        }
    }

    //声明静态内部类Holder
    private object Holder{
        val instance = Configurator()
    }

    //加载配置科目
    fun configure() {
        //初始化Iconify字体
        initIcons()
        //初始化日志工具
        Logger.addLogAdapter(AndroidLogAdapter())
        //记录应用内初始化完成
        CONFIGS[ConfigKeys.CONFIG_READY] = true
        //工具集合类初始化
        Utils.init(AppConfig.getApplicationContext())
        //解决RxDownload停止下载时的崩溃问题
        handleRxError()
        //初始化ARouter
        initARouter()
    }

    //初始化Iconify字体
    private fun initIcons() { //从HashMap中取出需要加载的字体库，逐一加载
        if (Configurator.ICONS.size > 0) {
            val initializer: Iconify.IconifyInitializer = Iconify.with(Configurator.ICONS.get(0))
            for (i in 1 until Configurator.ICONS.size) {
                initializer.with(Configurator.ICONS.get(i))
            }
        }
    }

    //存储android-iconify字体
    fun withIcon(descriptor: IconFontDescriptor): Configurator {
        ICONS.add(descriptor)
        return this
    }

    //设置加载进度对话框默认图标
    fun withLoaderStyle(loaderStyle: LoaderStyle): Configurator {
        CONFIGS.put(ConfigKeys.LOADER_STYLE, loaderStyle)
        return this
    }

    //记录网络加载对话框的进度条延迟关闭时间
    //delayed：网络加载对话框的进度条延迟关闭时间，单位:ms
    fun withLoaderDelayed(delayed: Long): Configurator {
        CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed)
        return this
    }

    //指定webview的后端web服务器域名
    //webview拦截器会将该域名的cookie存入sp
    fun withWebHost(host: String): Configurator{
        CONFIGS.put(ConfigKeys.API_HOST, host)
        return this
    }

    //记录http请求网络超时时间，单位:s
    fun withHttpTimeOut(timeout: Long): Configurator {
        CONFIGS.put(ConfigKeys.HTTP_TIME_OUT, timeout)
        return this
    }

    //给okhttp3添加拦截器
    //在okhttp3请求头中注入webview访问的后端web服务器的cookie
    //使得okhttp3能跨域访问webview的后端web服务器
    fun withInterceptor(interceptor: Interceptor): Configurator {
        INTERCEPTORS.add(interceptor)
        CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS)
        return this
    }

    //开启调试模式?
    fun withDebug(isDebug: Boolean): Configurator {
        CONFIGS.put(ConfigKeys.IS_DEBUG, isDebug)
        return this
    }

    //配置七牛文件服务地址
    //"http://q65j2lskn.bkt.clouddn.com/"
    fun withQiniuFileServerAddress(url: String) {
        CONFIGS.put(ConfigKeys.QINIU_FILE_SERVER_ADDRESS, url)
    }

    //获取七牛云上传凭证
    //"/mock/kotlin/get_qiniu_upload_token"
    fun withQiniuUploadTokenUrl(url: String){
        CONFIGS.put(ConfigKeys.QINIU_UPLOAD_TOKEN_URL, url)
    }

    //取出某一配置项
    //取出某一配置项
    fun <T> getConfiguration(key: Any): T {
        //检测初始化配置是否已完成
        checkConfiguration()
        val value: Any =
            CONFIGS.get(key) ?: throw NullPointerException("$key IS NULL")
        return CONFIGS.get(key) as T
    }

    //检测初始化配置是否已完成
    //若还未完成初始化，抛出异常
    private fun checkConfiguration() {
        val isReady =
            CONFIGS.get(ConfigKeys.CONFIG_READY) as Boolean
        if (!isReady) throw RuntimeException("Configuration is not ready,call configure")
    }

    //解决RxDownload停止下载时的崩溃问题
    private fun handleRxError(){
        //解决停止下载时的崩溃问题
        //Rxjava2的异常.最简单的方法就是给RxJavaPlugins设置errorHandler
        RxJavaPlugins.setErrorHandler {
            if (it is UndeliverableException) {
                //do nothing
            } else {
                LogUtil.d(it.toString())
            }
        }
    }

    //初始化ARouter
    private fun initARouter(){
        //Debug模式下，开启日志
        if(AppConfig.getConfiguration(ConfigKeys.IS_DEBUG)) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(AppConfig.getApplicationContext() as Application);
    }

    //获取所有配置科目
    fun getConfigs(): HashMap<Any, Any> {
        return CONFIGS
    }
}