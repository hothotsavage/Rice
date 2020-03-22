package site.marqstree.kotlin.rice.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.alibaba.fastjson.JSON
import java.lang.reflect.Type


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.arouter
 * 文件名: JsonServiceImpl
 * 创建者: marqstree
 * 创建时间: 2020/3/14 19:08
 * 描述: 需要注入自定义类时，需要实现此服务
 */
@Route(path = "/yourservicegroupname/json")
class JsonServiceImpl : SerializationService {
    override fun init(context: Context?) {}
    override fun <T> json2Object(text: String?, clazz: Class<T>?): T {
        return JSON.parseObject(text, clazz)
    }

    override fun object2Json(instance: Any?): String {
        return JSON.toJSONString(instance)
    }

    override fun <T> parseObject(input: String?, clazz: Type?): T {
        return JSON.parseObject(input, clazz)
    }
}