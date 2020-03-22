package site.marqstree.kotlin.rice.net.bean.response


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.data.protocal
 * 文件名: BaseResp
 * 创建者: marqstree
 * 创建时间: 2020/2/18 10:03
 * 描述: TODO
 */
/*
    能用响应对象
    @code:响应状态码
    @message:响应文字消息
    @data:具体响应业务对象
 */
data class BaseResp<T>(val code:Int,
                           val message:String,
                           val data:T)