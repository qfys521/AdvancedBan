package cn.qfys521.xiaoming.plugin

import cn.chuanwise.xiaoming.plugin.JavaPlugin
import cn.qfys521.xiaoming.plugin.commands.AdvancedBanCommands
import cn.qfys521.xiaoming.plugin.configurationFiles.Configurations
import cn.qfys521.xiaoming.plugin.configurationFiles.GlobalBanOrWhiteListConfigurations
import cn.qfys521.xiaoming.plugin.eventTriggers.MessageEventTrigger
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
class AdvancedBanPlugin : JavaPlugin() {
    var configurations: Configurations? = null
    var globalBanOrWhiteListConfigurations:GlobalBanOrWhiteListConfigurations? = null

    override fun onLoad() {
        val dataFolder = dataFolder
        dataFolder.mkdirs()
        configurations = setupConfiguration(Configurations::class.java) { Configurations() }
        globalBanOrWhiteListConfigurations = setupConfiguration(GlobalBanOrWhiteListConfigurations::class.java,"globalBanOrWhiteListConfigurations.json")
        getXiaoMingBot().interactorManager.registerInteractors(AdvancedBanCommands(), this)
        getXiaoMingBot().eventManager.registerListeners<AdvancedBanPlugin>(MessageEventTrigger(), this.getINSTANCE())

    }

    fun getINSTANCE(): AdvancedBanPlugin {
        return INSTANCE;
    }

    companion object {
        val INSTANCE = AdvancedBanPlugin()
    }
}
