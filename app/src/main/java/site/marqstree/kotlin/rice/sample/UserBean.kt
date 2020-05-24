package site.marqstree.kotlin.rice.sample


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.sample
 * 文件名: UserBean
 * 创建者: marqstree
 * 创建时间: 2020/5/24 7:41
 * 描述: TODO
 */
//注意：实体类一定要声明为可选型，否则fastjson会报错
data class UserBean(
    var id: Int?,
    var name: String?,
    var avatar: String?,
    var gender: Int?,
    var age: Int?,
    var height: Float?,
    var weight: Float?,
    var email: String?,
    var question: String?,
    var answer: String?,
    var score: Int?
)