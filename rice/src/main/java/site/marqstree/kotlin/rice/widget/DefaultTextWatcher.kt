package site.marqstree.kotlin.rice.widget

import android.text.Editable
import android.text.TextWatcher


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.widget
 * 文件名: DefaultTextWatcher
 * 创建者: marqstree
 * 创建时间: 2020/2/22 11:21
 * 描述: TODO
 */
/*
    默认TextWatcher，空实现
 */
open class DefaultTextWatcher: TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}