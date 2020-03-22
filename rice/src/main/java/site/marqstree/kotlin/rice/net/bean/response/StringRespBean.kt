package site.marqstree.kotlin.rice.net.bean.response


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.protocal
 * 文件名: BooleanResp
 * 创建者: marqstree
 * 创建时间: 2020/2/28 20:11
 * 描述: 网络请求通用相应体
 */
data class StringRespBean (val code:Int,
                           val message:String,
                           val data: String)