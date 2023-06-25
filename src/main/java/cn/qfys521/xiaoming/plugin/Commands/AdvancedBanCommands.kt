package cn.qfys521.xiaoming.plugin.Commands

import cn.chuanwise.xiaoming.annotation.Filter
import cn.chuanwise.xiaoming.annotation.FilterParameter
import cn.chuanwise.xiaoming.annotation.Required
import cn.chuanwise.xiaoming.interactor.SimpleInteractors
import cn.chuanwise.xiaoming.user.XiaoMingUser
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin

class AdvancedBanCommands : SimpleInteractors<AdvancedBanPlugin?>() {
    @Filter(COMMANDHEAD + "ban {QQ}")
    @Required(PERMISSIONHEAD + "ban.add")
    fun AddBanList(sender: XiaoMingUser<*>, @FilterParameter("QQ") qq: Long) {
        plugin!!.banList!!.banList.add(qq)
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.banList!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "whitelist add {QQ}")
    @Required(PERMISSIONHEAD + "whitelist.add")
    fun AddWhiteList(sender: XiaoMingUser<*>, @FilterParameter("QQ") qq: Long) {
        plugin!!.whiteList!!.whiteList.add(qq)
        sender.sendMessage("已将该用户添加至白名单。")
        plugin!!.whiteList!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "unabn {QQ}")
    @Required(PERMISSIONHEAD + "ban.remove")
    fun RemoveBanList(sender: XiaoMingUser<*>, @FilterParameter("QQ") qq: Long) {
        plugin!!.banList!!.banList.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.banList!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "whitelist remove {QQ}")
    @Required(PERMISSIONHEAD + "whitelist.remove")
    fun RemoveWhiteList(sender: XiaoMingUser<*>, @FilterParameter("QQ") qq: Long) {
        plugin!!.whiteList!!.whiteList.remove(qq)
        sender.sendMessage("已将该用户移除白名单。")
        plugin!!.whiteList!!.saveOrFail()
    }

    companion object {
        const val COMMANDHEAD = "AdvancedBan:"
        const val PERMISSIONHEAD = "advanced."
    }
}
