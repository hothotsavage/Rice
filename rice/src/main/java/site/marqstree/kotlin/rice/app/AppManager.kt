package site.marqstree.kotlin.rice.app

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.common
 * 文件名: AppManager
 * 创建者: marqstree
 * 创建时间: 2020/2/21 9:12
 * 描述: TODO
 */

/*
    Activity管理器
 */
class AppManager private constructor(){

    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    /*
        Activity入栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /*
        Activity出栈
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /*
        获取当前栈顶
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /*
        清理栈
     */
    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /*
        退出应用程序
     */
    fun exitApp(context: Context){
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //杀死后台进程
        //activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}