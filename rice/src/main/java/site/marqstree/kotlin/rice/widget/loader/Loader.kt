package site.marqstree.kotlin.rice.widget.loader

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatDialog
import com.blankj.utilcode.util.ScreenUtils
import com.wang.avi.AVLoadingIndicatorView
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.widget.loader
 * 文件名: LatteLoader
 * 创建者: marqstree
 * 创建时间: 2020/2/26 17:01
 * 描述: 进度对话框工具类
 */
object Loader{

    //懒加载默认图标名
    val DEFAULT_LOADER: LoaderStyle by lazy {
        //从全局配置中取默认图标，
        //否则用静态值初始化
        val loaderStyle: LoaderStyle = AppConfig.getConfiguration(ConfigKeys.LOADER_STYLE)?:LoaderStyle.PacmanIndicator
        loaderStyle
    }

    //对话框相对屏幕缩小8倍
    private val LOADER_SIZE_SCALE = 8
    private val LOADER_OFFSET_SCALE = 10

    //缓存所有已用过的进度对话框实例
    //优点：不用重复创建
    private val LOADERS = ArrayList<AppCompatDialog>()

    //显示默认图标
    //对话框的context最好传当前Activity或Fragment的context
    fun showLoading(context: Context) {
        showLoading(context, DEFAULT_LOADER)
    }

    //对话框的context最好传当前Activity或Fragment的context
    fun showLoading(context: Context, type: Enum<LoaderStyle>) {
        showLoading(context, type.name)
    }

    //显示进度对话框
    fun showLoading(context: Context, type: String) {
        //创建对话框
        val dialog = AppCompatDialog(context, R.style.dialogloader)
        //创建进度图标
        val avLoadingIndicatorView: AVLoadingIndicatorView = LoaderCreator.create(type, context)
        //将进度图标放入对话框
        dialog.setContentView(avLoadingIndicatorView)
        //获取屏幕宽高
        val deviceWidth: Int = ScreenUtils.getScreenWidth()
        val deviceHeight: Int = ScreenUtils.getScreenHeight()
        //修改对话框尺寸
        //宽高为：屏幕的1/8
        //向下移动屏幕高度1/10
        val dialogWindow = dialog.window
        if (dialogWindow != null) {
            val lp = dialogWindow.attributes
            lp.width = deviceWidth / LOADER_SIZE_SCALE
            lp.height = deviceHeight / LOADER_SIZE_SCALE
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE
            lp.gravity = Gravity.CENTER
        }
        LOADERS.add(dialog)
        dialog.show()
    }

    //关闭进度对话框
    fun stopLoading() {
        for (dialog in LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing) {
                    //cancel与dismiss区别：
                    //cancel:关闭dialog，还会执行回调
                    //dismiss:仅关闭dialog
                    dialog.cancel()
                }
            }
        }
    }

}