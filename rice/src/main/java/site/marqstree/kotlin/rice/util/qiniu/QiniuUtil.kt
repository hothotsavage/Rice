package site.marqstree.kotlin.rice.util.qiniu

import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import org.json.JSONObject
import site.marqstree.kotlin.rice.config.AppConfig
import site.marqstree.kotlin.rice.config.ConfigKeys
import site.marqstree.kotlin.rice.config.constant.Const
import site.marqstree.kotlin.rice.net.extent.json2Bean
import site.marqstree.kotlin.rice.net.observer.RxObserver
import site.marqstree.kotlin.rice.net.request.RxRequest
import site.marqstree.kotlin.rice.util.LogUtil


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.util.photo
 * 文件名: QiniuUtil
 * 创建者: marqstree
 * 创建时间: 2020/3/5 12:13
 * 描述: TODO
 */
class QiniuUtil(val localFileUrl:String,
                val listener:IQiniu) {

    /*
        上传本地文件到七牛服务器
        1)获取七牛云上传凭证
        2)上传文件到七牛对象服务器
     */
    fun uploadFile(){
        //1)获取七牛云上传凭证
        RxRequest.builder()
            .setUrl(AppConfig.getConfiguration(ConfigKeys.QINIU_UPLOAD_TOKEN_URL))
            .get()
            .json2Bean(String::class.java)
            .subscribe(object : RxObserver<String>(){
                override fun onNext(data: String) {
                    //2)上传文件到七牛对象服务器
                    uploadFileByToken(data)
                }
            })
    }

    //成功获取七牛上传token的回调接口
    private fun uploadFileByToken(uploadToken: String) {
        //config配置上传参数
        //可以自定义zone
        //Zone zone = new FixedZone(new String[]{"domain1","domain2"});
        //手动指定上传区域
        //Zone zone = FixedZone.zone0;//华东
        //配置断点续传
        /**
         * FileRecorder fileRecorder = null;
         * try {
         * fileRecorder = new FileRecorder("directory");
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
        //config配置上传参数
        val configuration =
            Configuration.Builder()
                .connectTimeout(10) //.zone(zone)
                //.dns(buildDefaultDns())//指定dns服务器
                .responseTimeout(60).build()
        val uploadManager = UploadManager(configuration, 3)
        uploadManager.put(localFileUrl, null, uploadToken,
            object: UpCompletionHandler {
                override fun complete(
                    key: String?,
                    info: ResponseInfo?,
                    response: JSONObject?
                ) {
                    LogUtil.d("响应:$info")
                    LogUtil.d("响应Json:${response}")
                    //上传成功
                    if(info?.isOK()!!){
                        //获取七牛远程文件的url
                        val remoteFileUrl = AppConfig.getConfiguration<String>(ConfigKeys.QINIU_FILE_SERVER_ADDRESS) + response?.get("hash")
                        LogUtil.d(remoteFileUrl)
                        //执行回调
                        listener.success(remoteFileUrl)
                    }
                }
            }
            ,null)
    }

}