package cn.qfys521.xiaoming.plugin.EventTriggers

import cn.chuanwise.xiaoming.event.InteractEvent
import cn.chuanwise.xiaoming.event.SimpleListeners
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin

class InteractEventTrigger : SimpleListeners<AdvancedBanPlugin?>() {
    fun onCommand(e: InteractEvent) {
        val inWhiteList = plugin!!.whiteList?.whiteList?.contains(e.context.user.code)
        val inBanList = plugin!!.banList?.banList?.contains(e.context.user.code)
        if (inWhiteList != true || inBanList == true) {
            e.cancel()
            return
        }
    }
}
