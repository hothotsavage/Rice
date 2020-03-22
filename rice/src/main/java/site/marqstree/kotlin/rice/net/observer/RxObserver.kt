package site.marqstree.kotlin.rice.net.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import site.marqstree.kotlin.rice.util.DialogUtil
import site.marqstree.kotlin.rice.widget.loader.Loader


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.rx
 * 文件名: BaseSubscriber
 * 创建者: marqstree
 * 创建时间: 2020/2/17 18:17
 * 描述: 通用RxObserver
 */
open class RxObserver<T>(): Observer<T> {
    override fun onComplete() {
        //关闭进度条对话框
        Loader.stopLoading()
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(data: T) {

    }

    override fun onError(e: Throwable) {
        //关闭进度条对话框
        Loader.stopLoading()

        //显示错误提示
        DialogUtil.showError("网络请求错误：${e.message}")
    }

}