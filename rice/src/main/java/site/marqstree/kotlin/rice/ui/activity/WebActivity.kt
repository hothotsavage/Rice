package site.marqstree.kotlin.rice.ui.activity

import android.Manifest
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.aty_web.*
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.extent.begPermissions
import site.marqstree.kotlin.rice.widget.webview.event.*


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

    /**
     * 记录用户点击后退按钮的时间差
     */
    private var mEndTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_web)
        //初始化
        init()
    }

    fun init(){
        //获取必要权限
        requestPermission()
        //加载供js调用的接口事件
        loadEvents()
        //载入web页面
        mWebView.loadUrl(mUrl)
    }

    //加载供js调用的接口事件
    private fun loadEvents() {
        EventManager.INSTANCE.addEvent(Const.WebActivity.LOAD_USER_PASSWORD_ACTION, LoadUserPasswordEvent())
        EventManager.INSTANCE.addEvent(Const.WebActivity.SAVE_USER_PASSWORD_ACTION, SaveUserPasswordEvent())
        EventManager.INSTANCE.addEvent(Const.WebActivity.CLEAR_USER_PASSWORD_ACTION, ClearUserInfoEvent())
    }

    fun requestPermission(){
        //申请权限
        begPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.CAMERA
            //不能申请，不会弹申请对话框
            // 调用系统安装服务时，系统自动弹申请框
            //Manifest.permission.REQUEST_INSTALL_PACKAGES
        )
    }

    /**
     * 监听 android 后退按钮点击事件。
     * 1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
     * 2、如果网页已经不可以进行后退操作了（即：网页在首页中，虚拟任务栈中，只包含了 imooc 。）
     * 在这种情况下，则会提示 "再按一次退出程序" ， 用户 两秒内再次点击后退按钮，则退出应用
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //监听 android 后退按钮点击事件。
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
            if (mWebView.canGoBack()) {
                mWebView.goBack()
                return true
            }
            //2、如果网页已经不可以进行后退操作了（即：网页在首页中，虚拟任务栈中，只包含了 首页：main 。）
            //在这种情况下，则会提示 "再按一次退出程序" ， 用户 两秒内再次点击后退按钮，则退出应用
            if (System.currentTimeMillis() - mEndTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mEndTime = System.currentTimeMillis()
            } else {
                //关闭activity
                finish()
            }
        }
        return true
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