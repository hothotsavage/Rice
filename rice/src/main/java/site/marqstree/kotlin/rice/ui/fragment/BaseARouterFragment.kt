package site.marqstree.kotlin.rice.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.ui.fragment
 * 文件名: BaseFragment
 * 创建者: marqstree
 * 创建时间: 2020/2/20 19:58
 * 描述: TODO
 */
/*
    Fragment基类，业务无关
 */
open class BaseARouterFragment : BaseFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

}