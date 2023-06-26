package cn.qfys521.xiaoming.plugin.eventTriggers

import cn.chuanwise.xiaoming.annotation.EventListener
import cn.chuanwise.xiaoming.event.MessageEvent
import cn.chuanwise.xiaoming.event.SimpleListeners
import cn.chuanwise.xiaoming.listener.ListenerPriority
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin
import java.util.*

class MessageEventTrigger : SimpleListeners<AdvancedBanPlugin?>() {
    @EventListener(priority = ListenerPriority.HIGHEST)
    fun onCommand(event: MessageEvent) {

        var banListMode = plugin!!.configurations!!.BanListMode
        var whiteListMode = plugin!!.configurations!!.WhiteListMode
        var globalBanListMode = plugin!!.globalBanOrWhiteListConfigurations!!.GlobalBanListMode
        var inBanList: Boolean = try {
            Objects.requireNonNull(plugin!!.configurations)!!.banList.contains(event.user.code)
        } catch (e: NullPointerException) {
            false
        }
        var inWhiteList: Boolean = try {
            Objects.requireNonNull(plugin!!.configurations)!!.whiteList.contains(event.user.code)
        } catch (e: NullPointerException) {
            false
        }
        var inGlobalBanList = try {
            Objects.requireNonNull(
                plugin!!.globalBanOrWhiteListConfigurations!!.GlobalBanList.getOrDefault(
                    event.user.code,
                    null
                )
            )
        } catch (e: NullPointerException) {
            false
        }

//        if (inBanList and plugin!!.configurations!!.BanListMode and !plugin!!.configurations!!.WhiteListMode) {//Only BanMode
//            event.cancel()
//            return
//        }else if(!inWhiteList and plugin!!.configurations!!.WhiteListMode and !plugin!!.configurations!!.BanListMode){//Only WhiteMode
//            event.cancel()
//            return
//        }else if(){
//
//        }
        if (banListMode and whiteListMode and globalBanListMode) {
            if (!inWhiteList and inBanList) {
                event.cancel()
                return
            } else if (!inWhiteList and !inBanList and globalBanListMode) {
                event.cancel()
            } else if (inBanList and inGlobalBanList) {
                event.cancel()
            }
        } else if (!banListMode and whiteListMode and !globalBanListMode) {
            if (!inWhiteList) {
                event.cancel()
                return
            }
        } else if (banListMode and !whiteListMode and globalBanListMode) {
            if (inBanList or inGlobalBanList or (inBanList and inGlobalBanList)) {
                event.cancel()
                return

            } else if (!banListMode and !whiteListMode and !globalBanListMode) {
                return
            } else {
                for (i in 0..14) {
                    logger.error("please check your plugin configurations.")
                }
                getXiaoMingBot().pluginManager.disablePlugin(plugin)
            }


        }
    }
}



