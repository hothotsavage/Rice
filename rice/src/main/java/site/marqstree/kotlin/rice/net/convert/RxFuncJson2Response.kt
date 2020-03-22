package site.marqstree.kotlin.rice.net.convert

import com.alibaba.fastjson.JSONObject
import io.reactivex.Observable
import io.reactivex.functions.Function
import site.marqstree.kotlin.rice.net.bean.response.StringRespBean


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.rx
 * 文件名: BaseJson2Bean
 * 创建者: marqstree
 * 创建时间: 2020/2/28 16:59
 * 描述: TODO
 */
class RxFuncJson2Response: Function<String, Observable<StringRespBean>> {
    override fun apply(json: String): Observable<StringRespBean> {
        val retResp: StringRespBean = JSONObject.parseObject(json, StringRespBean::class.java)

        //网络响应码为成功时，
        // 返回将Observable<String>转为Observable<CommonResp>
        return Observable.just(retResp)
    }
}