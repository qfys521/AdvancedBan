package cn.qfys521.xiaoming.plugin

import cn.chuanwise.xiaoming.plugin.JavaPlugin
import cn.qfys521.xiaoming.plugin.Commands.AdvancedBanCommands
import cn.qfys521.xiaoming.plugin.ConfigurationFiles.BanList
import cn.qfys521.xiaoming.plugin.ConfigurationFiles.WhiteList
import cn.qfys521.xiaoming.plugin.EventTriggers.InteractEventTrigger
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
class AdvancedBanPlugin : JavaPlugin() {
    var banList: BanList? = null
    var whiteList: WhiteList? = null
    override fun onLoad() {
        getXiaoMingBot().interactorManager.registerInteractors(AdvancedBanCommands(), this)
        getXiaoMingBot().eventManager.registerListeners<AdvancedBanPlugin>(InteractEventTrigger(), this.getINSTANCE())
        val dataFolder = dataFolder
        dataFolder.mkdirs()
        banList = setupConfiguration(BanList::class.java) { BanList() }
        whiteList = setupConfiguration(WhiteList::class.java) { WhiteList() }
    }

    public fun getINSTANCE(): AdvancedBanPlugin {
        return INSTANCE;
    }

    companion object {
        val INSTANCE = AdvancedBanPlugin()
    }
}
