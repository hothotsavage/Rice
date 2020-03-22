package site.marqstree.kotlin.rice.net.rxservice

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
interface RxServiceByJson {

    @POST
    fun post(@Url url: String, @Body body: RequestBody): Observable<String>

    @PUT
    fun put(@Url url: String, @Body body: RequestBody): Observable<String>

    @DELETE
    fun delete(@Url url: String, @Body body: RequestBody): Observable<String>
}
