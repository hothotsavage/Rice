package site.marqstree.kotlin.rice.util

import cn.pedant.SweetAlert.SweetAlertDialog
import site.marqstree.kotlin.rice.app.AppManager


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.util
 * 文件名: AlertDialogUtil
 * 创建者: marqstree
 * 创建时间: 2020/3/4 16:45
 * 描述: 报错对话框
 */
class DialogUtil{
    companion object{
        fun showError(content:String?,title:String="晕..."){
            //显示错误提示
            val activity = AppManager.instance.currentActivity()
            if(activity!=null)
                SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title)
                    .setContentText(content?:"出错了")
                    .show()
        }
    }
}