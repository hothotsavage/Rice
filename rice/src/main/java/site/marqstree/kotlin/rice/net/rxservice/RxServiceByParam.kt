package site.marqstree.kotlin.rice.net.rxservice

import io.reactivex.Observable
import retrofit2.http.*
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net
 * 文件名: RestService
 * 创建者: marqstree
 * 创建时间: 2020/2/27 9:41
 * 描述: TODO
 */
interface RxServiceByParam {
    @GET
    fun get(@Url url: String, @QueryMap params: WeakHashMap<String, Any>): Observable<String>

    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap params: WeakHashMap<String, Any>): Observable<String>

    @FormUrlEncoded
    @PUT
    fun put(@Url url: String, @FieldMap params: WeakHashMap<String, Any>): Observable<String>

    @DELETE
    fun delete(@Url url: String, @QueryMap params: WeakHashMap<String, Any>): Observable<String>

}
