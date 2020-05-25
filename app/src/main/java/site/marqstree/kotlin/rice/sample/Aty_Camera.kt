package site.marqstree.kotlin.rice.sample

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.aty_camera.*
import org.devio.takephoto.model.TResult
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import site.marqstree.kotlin.rice.R
import site.marqstree.kotlin.rice.net.extent.json2Bean
import site.marqstree.kotlin.rice.net.observer.RxObserver
import site.marqstree.kotlin.rice.net.request.RxRequest
import site.marqstree.kotlin.rice.ui.activity.BaseTakePhotoActivity
import site.marqstree.kotlin.rice.util.GlideUtil


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.sample
 * 文件名: Aty_Camera
 * 创建者: marqstree
 * 创建时间: 2020/5/25 10:41
 * 描述: TODO
 */
@Route(path = "/rice/mycamera")
class Aty_Camera: BaseTakePhotoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_camera)

        mBtnLocalCamera.setOnClickListener{
            takePhoto2Local()
        }

        mBtnRemoteCamera.setOnClickListener{
            takePhoto2Local()
        }
    }

    override fun takeSuccess(result: TResult?) {
        super.takeSuccess(result)
        if(this.mLocalFileUrl.isNullOrBlank()) {
            toast("没有找到图片文件")
            return
        }

        RxRequest.builder()
            .setUrl("/api/user/avatar")
            .setFile(this.mLocalFileUrl)
            .upload()
            .json2Bean(String::class.java)
            ?.subscribe(object : RxObserver<String>() {
                override fun onNext(remoteImgUrl: String) {
                    super.onNext(remoteImgUrl)

                    if(remoteImgUrl.isNullOrBlank()){
                        toast("上传头像图片失败")
                        return
                    }

                    Glide.with(this@Aty_Camera)
                        .load(remoteImgUrl)
                        .into(mIvPhoto)
                }

                override fun onError(e: Throwable) {
                    toast(e.message.toString())
                    super.onError(e)
                }
            })
    }

}