package site.marqstree.kotlin.rice.util

import com.orhanobut.logger.Logger


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.baselibrary.util
 * 文件名: HouLogger
 * 创建者: marqstree
 * 创建时间: 2020/2/23 12:28
 * 描述: TODO
 */
object LogUtil {
    private const val VERBOSE = 1
    private const val DEBUG = 2
    private const val INFO = 3
    private const val WARN = 4
    private const val ERROR = 5
    private const val NOTHING = 6
    private const val TAG = "marqstree"
    //控制log等级
    private const val LEVEL = VERBOSE

    fun v(tag: String?, message: String?) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(message!!)
        }
    }

    fun v(message: String?) {
        if (LEVEL <= VERBOSE) {
            Logger.t(TAG).v(message!!)
        }
    }

    fun d(tag: String?, message: Any?) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(message)
        }
    }

    fun d(message: Any?) {
        if (LEVEL <= DEBUG) {
            Logger.t(TAG).d(message)
        }
    }

    fun i(tag: String?, message: String?) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(message!!)
        }
    }

    fun i(message: String?) {
        this.i(TAG,message)
    }

    fun w(tag: String?, message: String?) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(message!!)
        }
    }
    fun w(message: String?) {
        this.w(TAG,message)
    }

    fun json(tag: String?, message: String?) {
        if (LEVEL <= WARN) {
            Logger.t(tag).json(message)
        }
    }
    fun json(message: String?) {
        this.json(message)
    }

    fun e(tag: String?, message: String?) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(message!!)
        }
    }
    fun e(message: String?) {
        this.e(TAG,message)
    }
}