package cn.qfys521.xiaoming.plugin.ConfigurationFiles

import cn.chuanwise.xiaoming.preservable.SimplePreservable
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Getter
class WhiteList : SimplePreservable<AdvancedBanPlugin?>() {
    var whiteList = ArrayList<Long>()
}