package site.marqstree.kotlin.rice.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.config.constant.Const

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnOpenWeb.setOnClickListener{
            ARouter.getInstance()
                .build(Const.WebView.PATH_WEB_ACTIVITY)
                .withString("mUrl","https://www.baidu.com")
                .navigation()
        }
    }
}
