package site.marqstree.kotlin.rice.net.convert

import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.parser.Feature
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
class RxFuncResponse2Bean<T>(val clazz: Class<T>): Function<StringRespBean, Observable<T>> {
    override fun apply(resp: StringRespBean): Observable<T> {
        LogUtil.d("测试","进入RxFuncResponse2Bean")

        if(resp.code!=0){
            return Observable.error(Exception(resp.message))
        }

        val dataStr:String = resp.data.trim()
        //采用fastjson转成目标类型
        if(clazz!=String::class.java) {
            var retBean: T
            try {
                //注意：实体类一定要声明为可选型，否则fastjson会报错
                retBean = JSONObject.parseObject(dataStr, clazz)
            } catch (e: Exception) {
                LogUtil.e("json转换错误", e.message)
                return Observable.error(Exception("数据转换失败"))
            }
            LogUtil.d("返回", retBean)
            //网络响应码为成功时，
            // 返回将Observable<String>转为Observable<T>
            return Observable.just(retBean)
        }
        //目标类型为字符串，则直接返回
        else {
            LogUtil.d("返回", dataStr)
            return Observable.just(dataStr as T)
        }

    }
}