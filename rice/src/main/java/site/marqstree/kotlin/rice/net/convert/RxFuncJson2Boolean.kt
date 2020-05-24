package site.marqstree.kotlin.rice.net.convert

import com.alibaba.fastjson.JSONObject
import io.reactivex.Observable
import io.reactivex.functions.Function
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.net.bean.response.BaseRespBean
import site.marqstree.kotlin.rice.net.bean.response.StringRespBean
import site.marqstree.kotlin.rice.net.exception.RespException


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.rx
 * 文件名: RxFuncJson2Boolean
 * 创建者: marqstree
 * 创建时间: 2020/2/28 20:09
 * 描述: TODO
 */
class RxFuncJson2Boolean: Function<String, Observable<Boolean>> {
    override fun apply(json: String): Observable<Boolean> {
        val retData: BaseRespBean = JSONObject.parseObject(json, BaseRespBean::class.java)

        if(retData.code!= Const.ResultCode.SUCCESS){
            return Observable.error(
                RespException(
                    retData.code,
                    retData.message
                )
            )
        }

        //网络响应码为成功时，
        // 返回将Observable<String>转为Observable<Boolean>
        return Observable.just(true)
    }
}