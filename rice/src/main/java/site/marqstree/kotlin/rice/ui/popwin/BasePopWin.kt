package site.marqstree.kotlin.rice.ui.popwin

import android.app.Activity
import android.view.*
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.jetbrains.anko.contentView
import site.marqstree.kotlin.rice.R


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.ui.popwin
 * 文件名: BasePopWin
 * 创建者: marqstree
 * 创建时间: 2020/3/16 10:05
 * 描述: POPWindow基类
 * 从底部滑出，顶部半透明
 */
open class BasePopWin<T: ViewDataBinding>(val mActivity: Activity,
                                          @LayoutRes layoutId: Int) :PopupWindow(mActivity) {
    val mRootView: View
    val mDataBinding: T

    init {
        val inflater = LayoutInflater.from(mActivity)
        mDataBinding = DataBindingUtil.inflate<T>(inflater,
            layoutId,
            null,false)
        mRootView = mDataBinding.root

        // 设置SelectPicPopupWindow的View
        this.contentView = mRootView
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.animationStyle = R.style.AnimBottom
        background.alpha = 150
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mRootView.setOnTouchListener { _, event ->
            val height = mRootView.top
            val y = event.y.toInt()
            if (event.action == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss()
                }
            }
            true
        }
    }

    fun show(){
        showAtLocation(
            (mActivity as Activity).contentView,
            Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
            0,0)
    }
}