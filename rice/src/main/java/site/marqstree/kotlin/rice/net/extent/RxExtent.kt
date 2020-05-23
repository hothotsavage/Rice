package site.marqstree.kotlin.rice.net.extent

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import site.marqstree.kotlin.rice.net.convert.RxFuncJson2Boolean
import site.marqstree.kotlin.rice.net.convert.RxFuncJson2Response
import site.marqstree.kotlin.rice.net.convert.RxFuncResponse2Bean
import site.marqstree.kotlin.rice.net.convert.RxFuncResponse2List


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.ext
 * 文件名: CommonExt
 * 创建者: marqstree
 * 创建时间: 2020/2/17 18:25
 * 描述: RxJava扩展
 */
//json字符串转list<T>
fun <T> Observable<String>.json2List(
    clazz: Class<T>
):Observable<List<T>>{
    return this.flatMap(RxFuncJson2Response())    //将Json字符串转为CommonResp
        //将CommonResp转为List<T>
        .flatMap(
            RxFuncResponse2List<T>(
                clazz
            )
        )
        //在新线程中执行此行之上的操作
        //包括：
        // 1)网络请求
        // 2)请求回的字符串转CommonResp
        // 3)CommonResp转为List<T>
        .subscribeOn(Schedulers.io())
        //在UI主线程中执行此行只有的操作
        //包括：
        // 1)刷新UI
        // 2)处理异常
        // 3)将Rx与Activity/Fragment绑定生命周期
        .observeOn(AndroidSchedulers.mainThread())
}

//json字符串转Bean
fun <T> Observable<String>.json2Bean(
    clazz: Class<T>
):Observable<T?>{
    return this.flatMap(RxFuncJson2Response())    //将Json字符串转为CommonResp
        //将CommonResp转为Bean
        .flatMap(
            RxFuncResponse2Bean<T>(
                clazz
            )
        )
        //在新线程中执行此行之上的操作
        //包括：
        // 1)网络请求
        // 2)请求回的字符串转CommonResp
        // 3)CommonResp转为List<T>
        .subscribeOn(Schedulers.io())
        //在UI主线程中执行此行只有的操作
        //包括：
        // 1)刷新UI
        // 2)处理异常
        // 3)将Rx与Activity/Fragment绑定生命周期
        .observeOn(AndroidSchedulers.mainThread())
}

//json转Boolean
fun Observable<String>.json2Boolean():Observable<Boolean>{
    return this.flatMap(RxFuncJson2Boolean())    //将Json字符串转为Boolean
        //在新线程中执行此行之上的操作
        //包括：
        // 1)网络请求
        // 2)请求回的字符串转CommonResp
        // 3)CommonResp转为List<T>
        .subscribeOn(Schedulers.io())
        //在UI主线程中执行此行只有的操作
        //包括：
        // 1)刷新UI
        // 2)处理异常
        // 3)将Rx与Activity/Fragment绑定生命周期
        .observeOn(AndroidSchedulers.mainThread())
}
