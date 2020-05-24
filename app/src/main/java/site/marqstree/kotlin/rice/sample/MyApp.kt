package site.marqstree.kotlin.rice.sample

import android.app.Application
import com.joanzapata.iconify.fonts.FontAwesomeModule
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.net.extent.json2Bean
import site.marqstree.kotlin.rice.net.observer.RxObserver
import site.marqstree.kotlin.rice.net.request.RxRequest
import site.marqstree.kotlin.rice.widget.loader.LoaderStyle


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.sample
 * 文件名: MyApp
 * 创建者: marqstree
 * 创建时间: 2020/3/22 16:11
 * 描述: TODO
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //初始化App配置
        AppConfig.init(this)    //记录App上下文
            .withIcon(FontAwesomeModule())      //记录FontAwesome字体
            .withLoaderStyle(LoaderStyle.PacmanIndicator)   //初始化加载进度对话框默认图标
            .withLoaderDelayed(500)               //记录进度对话框延迟关闭时间，单位:ms
            .withHttpTimeOut(3000)
            .withWebHost("https://www.baidu.com")
            .withJavascriptInterface("AndroidJSBridge")
            .withDebug(true)        //开启调试模式
            .configure();   //加载配置项
    }
}