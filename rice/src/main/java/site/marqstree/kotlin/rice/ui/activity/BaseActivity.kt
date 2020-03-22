package site.marqstree.kotlin.rice.ui.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import site.marqstree.kotlin.rice.app.AppManager
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.ui.activity
 * 文件名: BaseActivity
 * 创建者: marqstree
 * 创建时间: 2020/2/17 14:30
 * 描述: TODO
 */
open class BaseActivity: RxAppCompatActivity() {

    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    //添加fragment到fragment栈
    //并绑定到指定容器中
    fun addFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerViewId,fragment)
        transaction.commit()

        mStack.add(fragment)
    }

    //切换显示fragment
    fun changeFragment(position: Int){
        val transaction = supportFragmentManager.beginTransaction()
        mStack.map {
            transaction.hide(it)
        }
        transaction.show(mStack[position])
        transaction.commit()
    }

    fun showFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(containerViewId,fragment)
        transaction.show(fragment)
        transaction.commit()
    }

    fun replaceFragment(@IdRes containerViewId: Int,newFragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, newFragment)
        transaction.show(newFragment)
        transaction.commit()
    }
}