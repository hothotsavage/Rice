package site.marqstree.kotlin.rice.util.photo


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.util.photo
 * 文件名: ITakePhoto
 * 创建者: marqstree
 * 创建时间: 2020/3/5 11:27
 * 描述: 拍/选取本地照片回调接口
 */
interface ITakePhoto {
    //成功获取本地照片
    //本地照片文件url
    fun success(localFileUrl: String)
}