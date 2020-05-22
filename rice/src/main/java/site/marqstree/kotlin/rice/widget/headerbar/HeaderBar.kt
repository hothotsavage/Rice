package site.marqstree.kotlin.rice.widget.headerbar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_header_bar.view.*
import site.marqstree.kotlin.rice.R


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.headerbar
 * 文件名: HeaderBar
 * 创建者: marqstree
 * 创建时间: 2020/5/22 15:07
 * 描述: 顶部导航栏
 * 包含：
 * 左侧：回退按钮
 * 中间：标题
 * 右侧：文本
 */
//@JvmOverloads这个注解相当于声明了三个构造函数
class HeaderBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isShowBack = true
    private var titleText:String? = ""
    private var rightText:String? = ""

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        typedArray.recycle()

        initView()
    }

    private fun initView() {
        View.inflate(context,R.layout.layout_header_bar,this)
        //显隐左侧回退按钮
        mLeftIv.visibility = if(isShowBack) View.VISIBLE else View.GONE

        //返回图标默认实现（关闭Activity）
        mLeftIv.setOnClickListener {
            if (context is Activity){
                (context as Activity).finish()
            }
        }
        mTitleTv.setText(titleText)
        mRightTv.setText(rightText)
    }

    /*
        获取左侧返回按钮
     */
    fun getLeftView(): ImageView {
        return mLeftIv
    }

    /*
        获取右侧文本
     */
    fun getRightView(): TextView {
        return mRightTv
    }

    /*
        获取右侧文字
     */
    fun getRightText():String{
        return mRightTv.text.toString()
    }

    //支持DataBinding
    //在布局文件中给app:rightText绑定动态表达式
    fun setRightText(text: String){
        mRightTv.setText(text)
    }

}