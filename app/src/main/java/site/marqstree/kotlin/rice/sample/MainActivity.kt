package site.marqstree.kotlin.rice.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.net.extent.json2Bean
import site.marqstree.kotlin.rice.net.observer.RxObserver
import site.marqstree.kotlin.rice.net.request.RxRequest
import site.marqstree.kotlin.rice.ui.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnOpenWeb.setOnClickListener{
            ARouter.getInstance()
                .build(Const.WebView.PATH_WEB_ACTIVITY)
                .withString("mUrl","https://www.baidu.com")
                .navigation()
        }

        RxRequest.builder()
            .setUrl("http://192.168.0.102:4400/api/user/register")
            .setParam("name","rr")
            .setParam("password","123456")
            .setParam("gender",1)
            .setParam("age",123)
            .setParam("height",14.5f)
            .setParam("weight",12.2f)
            .setParam("email","xxoo@qq.com")
            .setParam("question","q")
            .setParam("answer","a")
            .post()
            .json2Bean(UserBean::class.java)
            .subscribe(object : RxObserver<UserBean>(){
                override fun onNext(data: UserBean) {
                    super.onNext(data)
                    toast("登陆成功")
                }

                override fun onError(e: Throwable) {
                    toast("登陆失败")
                    super.onError(e)
                }
            })
    }
}
