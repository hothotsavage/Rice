package site.marqstree.kotlin.rice.util.photo

import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.model.TResult
import site.marqstree.kotlin.rice.util.LogUtil


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.util
 * 文件名: TakePhotoResultListener
 * 创建者: marqstree
 * 创建时间: 2020/3/5 11:05
 * 描述: TODO
 */
class TakePhotoResultListener(val takePhotoListener:ITakePhoto):TakePhoto.TakeResultListener {

    private var mLocalFileUrl:String = ""

    fun getLocalPhoto():String{
        return mLocalFileUrl
    }

    override fun takeSuccess(result: TResult?) {
        LogUtil.d(
            "takephoto",
            result?.image?.originalPath
        )
        LogUtil.d(
            "takephoto",
            result?.image?.compressPath
        )
        mLocalFileUrl = result?.image?.compressPath?:""

        takePhotoListener.success(mLocalFileUrl)
    }

    override fun takeCancel() {
        LogUtil.d("取消拍/选取照片")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        LogUtil.e("拍/选取照片失败")
        LogUtil.e(msg)
    }
}