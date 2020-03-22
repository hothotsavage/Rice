package site.marqstree.kotlin.rice.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.util
 * 文件名: GlideUtils
 * 创建者: marqstree
 * 创建时间: 2020/2/24 10:02
 * 描述: TODO
 */
/*
    Glide工具类
 */
object GlideUtil {

    val OPTIONS = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .dontAnimate()

    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(OPTIONS)
                .centerCrop()
                .into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(OPTIONS)
                .fitCenter()
                .into(imageView)
    }

    /*
        当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    //fun loadUrlImage(context: Context, url: String, imageView: ImageView){
    //    Glide.with(context)
    //        .load(url)
    //        .placeholder(R.drawable.icon_back)
    //        .error(R.drawable.icon_back)
    //        .centerCrop()
    //        .into(
    //            object : SimpleTarget<GlideDrawable>() {
    //                override fun onResourceReady(resource: GlideDrawable,
    //                                             glideAnimation: GlideAnimation<in GlideDrawable>) {
    //                    imageView.setImageDrawable(resource)
    //                }
    //        })
    //}
}