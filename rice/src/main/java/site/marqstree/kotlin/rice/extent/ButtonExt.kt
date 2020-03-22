package site.marqstree.kotlin.rice.extent

import android.widget.Button
import android.widget.EditText
import site.marqstree.kotlin.rice.widget.DefaultTextWatcher

/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.ext
 * 文件名: ButtonExt
 * 创建者: marqstree
 * 创建时间: 2020/2/28 16:08
 * 描述: TODO
 */
/*
    扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}