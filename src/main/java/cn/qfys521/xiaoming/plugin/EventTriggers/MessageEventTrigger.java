package cn.qfys521.xiaoming.plugin.EventTriggers;

import cn.chuanwise.xiaoming.annotation.EventListener;
import cn.chuanwise.xiaoming.event.MessageEvent;
import cn.chuanwise.xiaoming.event.SimpleListeners;
import cn.chuanwise.xiaoming.listener.ListenerPriority;
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin;

import java.util.Objects;

public class MessageEventTrigger extends SimpleListeners<AdvancedBanPlugin> {
    @EventListener(priority = ListenerPriority.HIGHEST)
    void onCommand(MessageEvent event){
        boolean inWhiteList;
        boolean inBanList;
        try {
            inWhiteList = Objects.requireNonNull(plugin.getConfigurations()).getWhiteList().contains(event.getUser().getCode());
        }catch (NullPointerException e){
            inWhiteList = false;
        }
        try {
            inBanList = Objects.requireNonNull(plugin.getConfigurations()).getBanList().contains(event.getUser().getCode());
        }catch (NullPointerException e){
            inBanList = false;
        }

        if (!inWhiteList || inBanList) {
            event.cancel();
            return;
        }
    }
}
