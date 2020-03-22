package site.marqstree.kotlin.rice.extent

import android.view.View


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.ext
 * 文件名: ViewExt
 * 创建者: marqstree
 * 创建时间: 2020/2/28 16:07
 * 描述: TODO
 */
/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method:() -> Unit): View {
    setOnClickListener { method() }
    return this
}