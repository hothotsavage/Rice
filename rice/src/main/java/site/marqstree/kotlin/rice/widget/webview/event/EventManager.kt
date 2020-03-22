package site.marqstree.kotlin.rice.widget.webview.event

import java.util.*


/*
 * 项目名: Rice
 * 包名: site.marqstree.kotlin.rice.widget.webview.event
 * 文件名: EventManager
 * 创建者: marqstree
 * 创建时间: 2020/3/22 14:02
 * 描述: TODO
 */
class EventManager private constructor(){
    companion object{
        val INSTANCE = EventManager()
        val EVENTS: HashMap<String, IEvent> = HashMap<String, IEvent>()
    }

    fun addEvent(name: String, event: IEvent): EventManager {
        EVENTS[name] = event
        return this
    }

    fun createEvent(action: String): IEvent {
        return EVENTS[action] ?: return UndefineEvent()
    }
}