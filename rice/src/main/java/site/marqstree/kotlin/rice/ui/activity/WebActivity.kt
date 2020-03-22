package site.marqstree.kotlin.rice.ui.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.aty_web.*
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.widget.webview.X5WebView


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.ui.activity
 * 文件名: WebActivity
 * 创建者: marqstree
 * 创建时间: 2020/3/22 15:34
 * 描述: 支持拍照,ARouter，含一个全屏WebView的Activity
 */
@Route(path = Const.WebView.PATH_WEB_ACTIVITY)
class WebActivity: BaseTakePhotoActivity() {
    @Autowired
    @JvmField var mUrl :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_web)
        mWebView.loadUrl(mUrl)
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView.removeAllViews()
        mWebView.destroy()
    }
}