package site.marqstree.kotlin.rice.ui.fragment

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.FragmentUtils
import com.trello.rxlifecycle3.components.support.RxFragment
import org.jetbrains.anko.contentView

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
open class BaseFragment : RxFragment(){

    //获取Activity中视图content
    val mContentView: View? = activity?.contentView

    fun showFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(containerViewId,fragment)
        transaction.show(fragment)
        transaction.commit()
    }

    fun replaceFragment(@IdRes containerViewId: Int,newFragment:Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(containerViewId, newFragment)
        transaction.show(newFragment)
        transaction.commit()
    }

    fun replaceFragment(oldFragment: Fragment, newFragment:Fragment){
        FragmentUtils.replace(oldFragment,newFragment)
    }
}