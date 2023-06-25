package cn.qfys521.xiaoming.plugin.configurationFiles

import cn.chuanwise.xiaoming.preservable.SimplePreservable
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
class Configurations : SimplePreservable<AdvancedBanPlugin>() {
        var BanListMode:Boolean = true;
        var WhiteListMode:Boolean = false
        var banList = ArrayList<Long>()
        var whiteList = ArrayList<Long>()

}