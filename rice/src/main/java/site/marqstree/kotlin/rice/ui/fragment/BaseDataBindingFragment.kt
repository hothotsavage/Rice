package site.marqstree.kotlin.rice.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.ui.fragment
 * 文件名: BaseDataBindingFragment
 * 创建者: marqstree
 * 创建时间: 2020/3/19 15:45
 * 描述: TODO
 */
abstract class BaseDataBindingFragment<T:ViewDataBinding, B:Any, P:Any>:BaseARouterFragment() {
    //DataBinding对象
    lateinit var mDataBinding:T
    //布局文件id
    abstract val mLayoutId: Int
    //DataBinding BRDataid
    abstract val BRDataId: Int
    //DataBinding BRPresenterId
    abstract val BRPresenterId: Int
    //数据对象
    abstract val mData: B
    //Presenter对象
    abstract val mPresenter: P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //根据布局文件获得DataBinding对象
        mDataBinding = DataBindingUtil.inflate<T>(inflater, mLayoutId,
                                    container,
                                    false)
        //绑定布局文件中的数据对象
        mDataBinding.setVariable(BRDataId, mData)
        //绑定布局文件中的presenter对象
        mDataBinding.setVariable(BRPresenterId, mPresenter)
        return mDataBinding.root
    }
}