package site.marqstree.kotlin.rice.net.rxservice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.rxservice
 * 文件名: RxServiceNoParam
 * 创建者: marqstree
 * 创建时间: 2020/2/29 15:26
 * 描述: TODO
 */
interface RxServiceNoParam {
    @GET
    fun get(@Url url: String): Observable<String>
}