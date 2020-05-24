package site.marqstree.kotlin.rice.config

import android.content.Context
import android.os.Handler


/**
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.app
 * 文件名: Hou
 * 创建者: marqstree
 * 创建时间: 2020/2/25 14:53
 * 描述: 初始化配置工具类
 */
object AppConfig {

    //存储App上下文到全局配置工具类中
    //供集成进App的第三方框架使用
    fun init(context: Context): Configurator {
        Configurator.getInstance()
            .getConfigs()
            .put(ConfigKeys.APPLICATION_CONTEXT, context.applicationContext)
        return Configurator.getInstance()
    }

    //获取初始化配置建造者
    fun getConfigurator(): Configurator {
        return Configurator.getInstance()
    }

    //获取某一配置
    fun <T> getConfiguration(key: Any): T? {
        return getConfigurator().getConfiguration(key) as T?
    }

    //获取App上下文
    fun getApplicationContext(): Context {
        return getConfiguration<Any>(ConfigKeys.APPLICATION_CONTEXT) as Context
    }

    //获取全局Handler
    fun getHandler(): Handler? {
        return getConfiguration<Handler>(ConfigKeys.HANDLER)
    }

}