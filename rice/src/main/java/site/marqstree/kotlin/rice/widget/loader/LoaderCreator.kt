package site.marqstree.kotlin.rice.widget.loader

import android.content.Context
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.widget.loader
 * 文件名: LoaderCreator
 * 创建者: marqstree
 * 创建时间: 2020/2/26 17:14
 * 描述: 进度对话框建造器
 */
object LoaderCreator {
    //将所有用到的图标缓存起来
    private val LOADING_MAP = WeakHashMap<String, Indicator>()

    //创建进度动画图标对象
    fun create(
        type: String,
        context: Context
    ): AVLoadingIndicatorView {
        val avLoadingIndicatorView = AVLoadingIndicatorView(context)
        if (LOADING_MAP[type] == null) {
            val indicator = getIndicator(type)
            LOADING_MAP[type] = indicator
        }
        avLoadingIndicatorView.indicator = LOADING_MAP[type]
        return avLoadingIndicatorView
    }

    //创建进度图标对象
    private fun getIndicator(name: String?): Indicator? {
        if (name == null || name.isEmpty()) {
            return null
        }
        //采用反射创建图标对象
        //图标类在AVLoadingIndicatorView的包的indicator包下
        val drawableClassName = StringBuilder()
        if (!name.contains(".")) {
            val defaultPackageName =
                AVLoadingIndicatorView::class.java.getPackage()!!.name
            drawableClassName.append(defaultPackageName)
                .append(".indicators")
                .append(".")
        }
        drawableClassName.append(name)
        return try {
            val drawableClass =
                Class.forName(drawableClassName.toString())
            drawableClass.newInstance() as Indicator
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}