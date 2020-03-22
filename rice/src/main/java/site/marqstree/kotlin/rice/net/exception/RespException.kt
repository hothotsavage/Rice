package site.marqstree.kotlin.rice.net.exception


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.rx
 * 文件名: BaseException
 * 创建者: marqstree
 * 创建时间: 2020/2/20 19:26
 * 描述: TODO
 */
/*
    网络请求异常
 */
class RespException(val status:Int, val msg:String) :Throwable()