package site.marqstree.kotlin.rice.config


/*
 * 项目名: KotlinMall
 * 包名: site.marqstree.kotlin.library.base.app
 * 文件名: ConfigKeys
 * 创建者: marqstree
 * 创建时间: 2020/2/25 15:11
 * 描述: TODO
 */
enum class ConfigKeys {
    API_HOST,  //后端服务器地址，OkHttp使用
    APPLICATION_CONTEXT,  //App上下文
    CONFIG_READY,  //初始化是否完成
    ICON,  //字体图标
    INTERCEPTOR,  //okHttp拦截器，做测试mock数据
    HTTP_TIME_OUT,   //okHttp网络超时时间，单位：s
    LOADER_STYLE,    //加载进度条图标
    LOADER_DELAYED,  //加载进度条关闭延迟(ms)
    WE_CHAT_APP_ID,  //微信AppID
    WE_CHAT_APP_SECRET,  //微信AppSecret
    ACTIVITY,  //微信集成用上下文
    HANDLER,  //全局handler
    JAVASCRIPT_INTERFACE,  //js调用java时，注入js中的接口对象名
    AVATAR_SIZE,    //剪切头像的最大尺寸
    IS_DEBUG,    //开启调试?
    QINIU_FILE_SERVER_ADDRESS,     //七牛文件服务地址
    QINIU_UPLOAD_TOKEN_URL     //获取七牛云上传凭证
}