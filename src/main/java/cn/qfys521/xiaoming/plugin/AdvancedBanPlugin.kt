package cn.qfys521.xiaoming.plugin

import cn.chuanwise.xiaoming.plugin.JavaPlugin
import cn.qfys521.xiaoming.plugin.Commands.AdvancedBanCommands
import cn.qfys521.xiaoming.plugin.ConfigurationFiles.Configurations
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
    var configurations: Configurations? = null

    override fun onLoad() {
        val dataFolder = dataFolder
        dataFolder.mkdirs()
        configurations = setupConfiguration(Configurations::class.java) { Configurations() }
        getXiaoMingBot().interactorManager.registerInteractors(AdvancedBanCommands(), this)
        getXiaoMingBot().eventManager.registerListeners<AdvancedBanPlugin>(InteractEventTrigger(), this.getINSTANCE())

    }

    fun getINSTANCE(): AdvancedBanPlugin {
        return INSTANCE;
    }

    companion object {
        val INSTANCE = AdvancedBanPlugin()
    }
}
