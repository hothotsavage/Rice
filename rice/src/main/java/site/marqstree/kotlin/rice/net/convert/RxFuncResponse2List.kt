package site.marqstree.kotlin.rice.net.convert

import com.alibaba.fastjson.JSONObject
import io.reactivex.Observable
import io.reactivex.functions.Function
import site.marqstree.kotlin.rice.net.bean.response.StringRespBean
import site.marqstree.kotlin.rice.util.LogUtil
import java.lang.Exception


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.rx
 * 文件名: BaseJson2Bean
 * 创建者: marqstree
 * 创建时间: 2020/2/28 16:59
 * 描述: TODO
 */
class RxFuncResponse2List<T>(val clazz: Class<T>): Function<StringRespBean, Observable<List<T>>> {
    override fun apply(resp: StringRespBean): Observable<List<T>> {

        LogUtil.d("测试","进入RxFuncResponse2List")
        if(resp.code!=0){
            return Observable.error(Exception(resp.message))
        }

        val dataStr:String = resp.data?:""
        var retList: List<T>
        try {
            retList = JSONObject.parseArray(dataStr, clazz)
        }catch (e: Exception){
            return Observable.error(Exception("数据转换失败"))
        }
        //网络响应码为成功时，
        // 返回将Observable<String>转为Observable<List<T>>
        return Observable.just(retList)
    }
}