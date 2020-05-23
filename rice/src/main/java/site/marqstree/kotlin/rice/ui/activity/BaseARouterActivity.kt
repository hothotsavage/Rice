package site.marqstree.kotlin.rice.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.ui.activity
 * 文件名: BaseActivity
 * 创建者: marqstree
 * 创建时间: 2020/2/17 14:30
 * 描述: 带拍照功能的Activity基类
 */
open class BaseARouterActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

}