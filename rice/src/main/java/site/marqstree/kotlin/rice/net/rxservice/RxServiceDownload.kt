package site.marqstree.kotlin.rice.net.rxservice

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.rxservice
 * 文件名: RxServiceDownload
 * 创建者: marqstree
 * 创建时间: 2020/2/28 15:36
 * 描述: 暂不使用，用RxDownload代替
 */
interface RxServiceDownload{
    @Streaming //@Streaming：表示一边下载，一边写磁盘，此时必须使用异步调用。若不用Streaming，则全部写入内存，可能导致内存溢出
    @GET
    fun download(@Url url: String, @QueryMap params: WeakHashMap<String, Any>): Observable<ResponseBody>
}