package site.marqstree.kotlin.rice.extent

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import org.jetbrains.anko.toast


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.extent
 * 文件名: ActivityExt
 * 创建者: marqstree
 * 创建时间: 2020/3/3 10:00
 * 描述: TODO
 */
//请求权限
@SuppressLint("CheckResult")
fun FragmentActivity.begPermissions(vararg permissions: String) {
    RxPermissions(this)
        .requestEach(*permissions)
        .subscribe{
            permission->run{
                LogUtils.v("授权:${permission.name}")
                if(permission.granted){
                    LogUtils.v("成功授权:${permission.name}")
                }else if (permission.shouldShowRequestPermissionRationale) {
                    LogUtils.v("授权失败:${permission.name}")
                    toast("授权失败，退出App!")
                    this.finish()
                } else {
                    LogUtils.v("授权失败:${permission.name}")
                    toast("授权失败，退出App!")
                    this.finish()
                }
            }
        }
}