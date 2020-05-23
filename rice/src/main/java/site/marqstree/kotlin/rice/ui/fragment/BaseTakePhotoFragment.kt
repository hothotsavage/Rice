package site.marqstree.kotlin.rice.ui.fragment

import android.content.Intent
import android.net.Uri
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.TimeUtils
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.app.TakePhotoFragment
import org.devio.takephoto.app.TakePhotoImpl
import org.devio.takephoto.compress.CompressConfig
import org.devio.takephoto.model.CropOptions
import org.devio.takephoto.model.TResult
import org.devio.takephoto.model.TakePhotoOptions
import site.marqstree.kotlin.rice.util.LogUtil
import site.marqstree.kotlin.rice.util.qiniu.IQiniu
import site.marqstree.kotlin.rice.util.qiniu.QiniuUtil
import java.io.File


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.ui.fragment
 * 文件名: BaseTake
 * 创建者: marqstree
 * 创建时间: 2020/5/23 14:12
 * 描述: TODO
 */
abstract open class BaseTakePhotoFragment: BaseARouterFragment(),
    TakePhoto.TakeResultListener{

    //TakePhoto对象
    private val mTakePhoto: TakePhoto by lazy{
        //调整照片角度
        //否则照片横着放
        val takePhotoOptions = TakePhotoOptions.Builder()
            .setCorrectImage(true)
            .create()
        val takePhoto = TakePhotoImpl(this,this)
        takePhoto.setTakePhotoOptions(takePhotoOptions)
        takePhoto
    }

    //本地照片url
    protected var mLocalFileUrl:String = ""
    //远程照片url
    private var mRemoteFileUrl:String  = ""
    //临时存储拍照文件url
    private val mTempFile: File by lazy{
        //生成临时照片文件名
        //规则："时间戳"+".png"
        val tempFileName = PathUtils.getExternalPicturesPath()+"/"+"${TimeUtils.getNowMills()}.png"
        File(tempFileName)
    }
    //七牛上传文件回调接口
    private var mQiniuListner: IQiniu? = null

    //TakePhoto拍/选取照片成功的回调
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

        if(mQiniuListner!=null){
            QiniuUtil(mLocalFileUrl,
                mQiniuListner!!)
                .uploadFile()
        }
    }

    //TakePhoto拍/选取照片取消的回调
    override fun takeCancel() {
        LogUtil.d("取消拍/选取照片")
    }

    //TakePhoto拍/选取照片失败的回调
    override fun takeFail(result: TResult?, msg: String?) {
        LogUtil.e("拍/选取照片失败")
        LogUtil.e(msg)
    }

    /*
        TakePhoto默认实现
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }

    //拍/选区照片，并上传七牛
    //返回远程文件url
    fun takePhoto2Qiniu(listener: IQiniu){
        mQiniuListner = listener
        showTakePhotoView()
    }

    //拍/选区照片，返回本地文件
    fun takePhoto2Local(){
        showTakePhotoView()
    }

    //弹出选择对话框，并拍/选取照片
    private fun showTakePhotoView() {
        //或者builder模式创建
        AlertView.Builder()
            .setContext(activity)
            .setStyle(AlertView.Style.ActionSheet)
            .setTitle("选择图片")
            .setMessage(null)
            .setCancelText("取消")
            .setDestructive("拍照", "从相册中选择")
            .setOthers(null)
            .setOnItemClickListener(object: OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                    val cropOptions: CropOptions = CropOptions.Builder()
                        .setAspectX(1)
                        .setAspectY(1)
                        .setWithOwnCrop(true)
                        .create()
                    when(position){
                        0 -> {
                            mTakePhoto
                                .onPickFromCaptureWithCrop(Uri.fromFile(mTempFile),cropOptions)

                        }
                        1 -> mTakePhoto.onPickFromGalleryWithCrop(Uri.fromFile(mTempFile),cropOptions)
                    }
                }

            })
            .build()
            .show()
    }

}