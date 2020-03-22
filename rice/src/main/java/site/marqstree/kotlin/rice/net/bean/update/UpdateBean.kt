package site.marqstree.kotlin.rice.net.bean.update

import java.io.Serializable


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.data.protocal.update
 * 文件名: CommonUddateBean
 * 创建者: marqstree
 * 创建时间: 2020/3/3 9:01
 * 描述: 升级网络请求响应数据体
 */
class UpdateBean(
    //最新版本标题
    var title: String,
    //最新版本升级内容
    var content: String,
    //apk文件url
    var url: String,
    //apk文件md5校验字符串
    var md5: String,
    //最新版本码
    var versionCode: Int,
    //最新版本名称
    var versionName: String
): Serializable