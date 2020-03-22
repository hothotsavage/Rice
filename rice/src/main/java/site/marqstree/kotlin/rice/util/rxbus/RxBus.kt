package site.marqstree.kotlin.rice.util.rxbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.ReplaySubject
import org.jetbrains.annotations.NotNull
import site.marqstree.kotlin.rice.util.LogUtil


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.util.rxbus
 * 文件名: RxBus
 * 创建者: marqstree
 * 创建时间: 2020/3/12 9:05
 * 描述: RxBus全局事件总线
 */
class RxBus private constructor(): LifecycleObserver {

    companion object{
        //全局单例
        val INSTANCE: RxBus by lazy{
            RxBus()
        }

        /**
         * 向RxBus发送一个事件，这个事件可以来自任意一个线程
         */
        fun send(event: Any) {
            LogUtil.v("发送事件:${event}")
            RxBus.INSTANCE.rxBus.onNext(event)
        }

        /**
         * 订阅特定类型T的事件。可以从任何线程调用
         */
        inline fun <reified T : Any> observe(): Observable<T> {
            return RxBus.INSTANCE.rxBus.ofType(T::class.java)
        }
    }

    /**
     * 用于保存RxBus事件的所有订阅，并在需要时正确的取消订阅。
     */
    private val disposablesMap: HashMap<Any, CompositeDisposable?> by lazy {
        HashMap<Any, CompositeDisposable?>()
    }

    /**
     * 避免直接使用此属性，因为它仅在内联函数中使用而暴露
     */
    val rxBus = ReplaySubject.create<Any>().toSerialized()

    /**
     * 从RxBus事件中取消注册订阅者
     * 调用订阅的取消订阅方法
     */
    private fun unRegister(lifecycleOwner: LifecycleOwner) {
        //根据activity/fragment对象，从disposable数组中取出disposable容器
        val compositeDisposable = disposablesMap[lifecycleOwner]
        if (compositeDisposable == null) {
            LogUtil.w("RxBus注销订阅者失败！订阅者还未注册。")
        } else {
            //切断disposable容器中所有订阅事件，防止内存泄漏
            compositeDisposable.clear()
            //在disposable数组中删除activity/fragment对象对应的条目
            disposablesMap.remove(lifecycleOwner)
            LogUtil.v("Rxbus成功注销事件订阅者:${lifecycleOwner}")
        }
    }

    internal fun register(disposable: Disposable, lifecycleOwner: LifecycleOwner) {
        //创建disposable容器
        //一个Activity/Fragment对应一个disposable容器
        var compositeDisposable = disposablesMap[lifecycleOwner]
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        //在disposable容器中放入disposable对象
        compositeDisposable.add(disposable)
        //将容器放入容器数组
        //容器数组
        //key:Activity/Fragment对象
        //value:disposable容器
        disposablesMap[lifecycleOwner] = compositeDisposable

        //与Activity/Fragment绑定生命周期
        lifecycleOwner.lifecycle.addObserver(INSTANCE)
    }

    //与Activity/Fragment绑定关闭事件
    //当Activity/Fragment关闭时，注销对应的订阅者
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@NotNull lifecycleOwner: LifecycleOwner){
        //从RxBus事件中取消注册订阅者
        unRegister(lifecycleOwner)
    }
}

/**
 * 注册订阅以便以后正确注销它以避免内存泄漏
 * @param disposable 拥有此订阅的订阅对象
 */
fun Disposable.registerInBus(lifecycleOwner: LifecycleOwner) {
    RxBus.INSTANCE.register(this, lifecycleOwner)
}
