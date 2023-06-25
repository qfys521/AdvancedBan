package cn.qfys521.xiaoming.plugin.EventTriggers

import cn.chuanwise.xiaoming.annotation.EventListener
import cn.chuanwise.xiaoming.event.MessageEvent
import cn.chuanwise.xiaoming.event.SimpleListeners
import cn.chuanwise.xiaoming.listener.ListenerPriority
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin
import java.util.*

class MessageEventTrigger : SimpleListeners<AdvancedBanPlugin?>() {
    @EventListener(priority = ListenerPriority.HIGHEST)
    fun onCommand(event: MessageEvent) {
        val inBanList: Boolean
        inBanList = try {
            Objects.requireNonNull(plugin!!.configurations)!!.banList.contains(event.user.code)
        } catch (e: NullPointerException) {
            false
        }
        if (inBanList) {
            event.cancel()
            return
        }
    }
}
