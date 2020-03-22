package site.marqstree.kotlin.rice.net.rxservice

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.net.rxservice
 * 文件名: RxServiceUpload
 * 创建者: marqstree
 * 创建时间: 2020/2/28 15:35
 * 描述: TODO
 */
interface RxServiceUpload{
    @Multipart
    @POST
    fun upload(@Url url: String, @Part file: MultipartBody.Part): Observable<String>
}