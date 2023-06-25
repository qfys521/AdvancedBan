package cn.qfys521.xiaoming.plugin.Commands

import cn.chuanwise.xiaoming.annotation.Filter
import cn.chuanwise.xiaoming.annotation.FilterParameter
import cn.chuanwise.xiaoming.annotation.Required
import cn.chuanwise.xiaoming.interactor.SimpleInteractors
import cn.chuanwise.xiaoming.user.XiaoMingUser
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin

class AdvancedBanCommands : SimpleInteractors<AdvancedBanPlugin>() {
    @Filter(COMMANDHEAD + "ban {qq}")
    @Required(PERMISSIONHEAD + "ban.add")
    fun AddBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.add(qq)
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "whitelist add {qq}")
    @Required(PERMISSIONHEAD + "whitelist.add")
    fun AddWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.whiteList?.add(qq)
        sender.sendMessage("已将该用户添加至白名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "unabn {qq}")
    @Required(PERMISSIONHEAD + "ban.remove")
    fun RemoveBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "whitelist remove {qq}")
    @Required(PERMISSIONHEAD + "whitelist.remove")
    fun RemoveWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.whiteList?.remove(qq)
        sender.sendMessage("已将该用户移除白名单。")
        plugin!!.configurations?.saveOrFail()
    }

    companion object {
        const val COMMANDHEAD = "AdvancedBan_"
        const val PERMISSIONHEAD = "advanced."
    }
}
