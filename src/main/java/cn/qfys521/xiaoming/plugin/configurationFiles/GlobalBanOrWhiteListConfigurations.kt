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
class GlobalBanOrWhiteListConfigurations : SimplePreservable<AdvancedBanPlugin>() {
    var globalBanListMode: Boolean = false
    var kickedOutOfGroupAfterBanMode: HashMap<Long, Boolean> = HashMap()
    var globalBanList: HashMap<Long, Boolean> = HashMap()


}