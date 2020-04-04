package site.marqstree.kotlin.rice.net.bean.user

import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.usercenter.data.protocol
 * 文件名: UserInfo
 * 创建者: marqstree
 * 创建时间: 2020/2/22 13:27
 * 描述: TODO
 */
/*
    用户实体类
 */
data class UserInfo(val id:String,
                    val icon:String,
                    val name:String,
                    val gender:String,
                    val mobile:String,
                    val sign:String,
                    val md5Password:String,
                    var endMilliSecond:Long)