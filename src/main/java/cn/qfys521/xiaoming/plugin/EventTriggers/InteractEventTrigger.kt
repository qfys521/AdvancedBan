package cn.qfys521.xiaoming.plugin.EventTriggers

import cn.chuanwise.xiaoming.annotation.EventListener
import cn.chuanwise.xiaoming.event.InteractEvent
import cn.chuanwise.xiaoming.event.MessageEvent
import cn.chuanwise.xiaoming.event.SimpleListeners
import cn.chuanwise.xiaoming.listener.ListenerPriority
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin

class InteractEventTrigger : SimpleListeners<AdvancedBanPlugin>() {
    @EventListener(priority = ListenerPriority.HIGHEST)
    fun onCommand(e: MessageEvent) {
        val inWhiteList = plugin.configurations?.whiteList?.contains(e.user.code)
        val inBanList = plugin.configurations?.banList?.contains(e.user.code)
        if (((inWhiteList != null)or(inWhiteList != true)) and ((inBanList != null)or(inBanList == true))) {
            e.cancel()
            return
        }
    }
}
