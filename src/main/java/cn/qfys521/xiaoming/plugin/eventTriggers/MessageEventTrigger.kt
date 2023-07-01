package cn.qfys521.xiaoming.plugin.eventTriggers

import cn.chuanwise.xiaoming.annotation.EventListener
import cn.chuanwise.xiaoming.event.MessageEvent
import cn.chuanwise.xiaoming.event.SimpleListeners
import cn.chuanwise.xiaoming.listener.ListenerPriority
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin


class MessageEventTrigger : SimpleListeners<AdvancedBanPlugin?>() {
    @EventListener(priority = ListenerPriority.HIGHEST)
    fun onCommand(event: MessageEvent) {

        val banListMode = plugin?.configurations?.BanListMode?:false
        val whiteListMode = plugin?.configurations?.WhiteListMode?:false
        val globalBanListMode = plugin?.globalBanOrWhiteListConfigurations?.globalBanListMode?:false
        val inBanList: Boolean = plugin?.configurations?.banList?.contains(event.user.code)?:false
        val inWhiteList: Boolean = plugin?.configurations?.whiteList?.contains(event.user.code)?:false
        val inGlobalBanList = plugin?.globalBanOrWhiteListConfigurations?.globalBanList?.get(event.user.code) ?:false

        if(banListMode and whiteListMode and globalBanListMode){
            if (!inWhiteList and  inBanList){
                event.cancel()
                return
            }else if(!inWhiteList and !inBanList and globalBanListMode){
                event.cancel()
            }else if(inBanList and inGlobalBanList){
                event.cancel()
            }
        }else if(!banListMode and whiteListMode and !globalBanListMode){
            if(!inWhiteList){
                event.cancel()
                return
            }
        }else if(banListMode and !whiteListMode and globalBanListMode){
            if (inBanList or inGlobalBanList or (inBanList and inGlobalBanList)){
                event.cancel()
                return

        }else if(!banListMode and !whiteListMode and !globalBanListMode){
            return
        }else{
            for (i in 0..14){
                logger.error("please check your plugin configurations.")
            }
            getXiaoMingBot().pluginManager.disablePlugin(plugin)
        }
    }
    }
}



