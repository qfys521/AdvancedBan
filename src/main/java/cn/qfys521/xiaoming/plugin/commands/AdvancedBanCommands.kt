package cn.qfys521.xiaoming.plugin.commands

import cn.chuanwise.xiaoming.annotation.Filter
import cn.chuanwise.xiaoming.annotation.FilterParameter
import cn.chuanwise.xiaoming.annotation.Required
import cn.chuanwise.xiaoming.interactor.SimpleInteractors
import cn.chuanwise.xiaoming.user.GroupXiaoMingUser
import cn.chuanwise.xiaoming.user.XiaoMingUser
import cn.qfys521.xiaoming.plugin.AdvancedBanPlugin
import net.mamoe.mirai.contact.PermissionDeniedException
import java.lang.StringBuilder


class AdvancedBanCommands : SimpleInteractors<AdvancedBanPlugin>() {
    @Filter(COMMANDHEAD + "banlist add {qq}")
    @Required(PERMISSIONHEAD + "ban.add")
    fun addBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.add(qq)
        if (sender is GroupXiaoMingUser) {
            if (plugin.globalBanOrWhiteListConfigurations?.kickedOutOfGroupAfterBanMode?.get(sender.groupCode) == true) {
                try {
                    sender.memberContact.kick("kick by AdvancedBan")
                } catch (e: PermissionDeniedException) {
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是没有权限。")
                }catch (e: Exception){
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是发生了异常: ",e)
                }
            }
        }
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "ban {qq}")
    @Required(PERMISSIONHEAD + "ban.add")
    fun addBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.add(qq)
        if (sender is GroupXiaoMingUser) {
            if (plugin.globalBanOrWhiteListConfigurations?.kickedOutOfGroupAfterBanMode?.get(sender.groupCode) == true) {
                try {
                    sender.memberContact.kick("kick by AdvancedBan")
                } catch (e: PermissionDeniedException) {
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是没有权限。")
                }catch (e: Exception){
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是发生了异常: ",e)
                }
            }
        }
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "whitelist add {qq}")
    @Required(PERMISSIONHEAD + "whitelist.add")
    fun addWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.whiteList?.add(qq)
        sender.sendMessage("已将该用户添加至白名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "banlist remove {qq}")
    @Required(PERMISSIONHEAD + "ban.remove")
    fun removeBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "unban {qq}")
    @Required(PERMISSIONHEAD + "ban.remove")
    fun removeBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.banList?.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.configurations?.saveOrFail()
    }


    @Filter(COMMANDHEAD + "whitelist remove {qq}")
    @Required(PERMISSIONHEAD + "whitelist.remove")
    fun removeWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.configurations?.whiteList?.remove(qq)
        sender.sendMessage("已将该用户移除白名单。")
        plugin!!.configurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "set banListMode {mode}")
    @Required(PERMISSIONHEAD + "changemode.banlist")
    fun changeBanlistMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String) {
        if (mode.equals("启用") or mode.equals("true")) {
            plugin!!.configurations!!.BanListMode = true
            sender.sendMessage("已启用BanList.")
        } else if (mode.equals("停用") or mode.equals("false")) {
            plugin!!.configurations!!.BanListMode = false
            sender.sendMessage("已停用BanList.")
        } else {
            sender.sendError("mode只能为Boolean")
        }
        plugin!!.configurations!!.saveOrFail()

    }

    @Filter(COMMANDHEAD + "set WhiteListMode {mode}")
    @Required(PERMISSIONHEAD + "changemode.whitelist")
    fun changeWhitelistMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String) {
        if (mode.equals("启用") or mode.equals("true")) {
            plugin!!.configurations!!.WhiteListMode = true
            sender.sendMessage("已启用WhiteList.")
        } else if (mode.equals("停用") or mode.equals("false")) {
            plugin!!.configurations!!.WhiteListMode = false
            sender.sendMessage("已停用WhiteList.")
        } else {
            sender.sendError("mode只能为Boolean")
        }
        plugin!!.configurations!!.saveOrFail()

    }

    @Filter(COMMANDHEAD + "GlobalBanListMode {mode}")
    @Required(PERMISSIONHEAD + "changemode.globalbanlistmode")
    fun changeGlobalBanListMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String) {
        if (mode.equals("启用") or mode.equals("true")) {
            plugin!!.globalBanOrWhiteListConfigurations!!.globalBanListMode = true
            sender.sendMessage("已启用全局黑名单模式。")
            sender.sendMessage("在该模式下的拉黑将会自动进入全局拉黑模式。")
        } else if (mode.equals("停用") or mode.equals("false")) {
            plugin!!.globalBanOrWhiteListConfigurations!!.globalBanListMode = false
            sender.sendMessage("已停用全局黑名单模式。")
        } else {
            sender.sendError("mode只能为Boolean")
        }
        plugin!!.globalBanOrWhiteListConfigurations!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "AddThisGroupBanAutoKick")
    @Required(PERMISSIONHEAD + "groupautokick.add")
    fun addGroupAutoKick(sender: GroupXiaoMingUser) {
        plugin?.globalBanOrWhiteListConfigurations?.kickedOutOfGroupAfterBanMode?.put(sender.groupCode, true)
        sender.sendMessage("已在群[" + sender.groupInformation.alias + "]启用封禁自动踢出。")
        plugin!!.globalBanOrWhiteListConfigurations!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "RemoveThisGroupBanAutoKick")
    @Required(PERMISSIONHEAD + "groupautokick.remove")
    fun removeGroupAutoKick(sender: GroupXiaoMingUser) {
        plugin!!.globalBanOrWhiteListConfigurations!!.kickedOutOfGroupAfterBanMode.put(sender.groupCode, false)
        sender.sendMessage("已在群[" + sender.groupInformation.alias + "]停用封禁自动踢出。")
        plugin!!.globalBanOrWhiteListConfigurations!!.saveOrFail()
    }

    @Filter(COMMANDHEAD + "global banlist add {qq}")
    @Required(PERMISSIONHEAD + "global.ban.add")
    fun addgBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.globalBanOrWhiteListConfigurations?.globalBanList?.put(qq, true)
        if (sender is GroupXiaoMingUser) {
            if (plugin.globalBanOrWhiteListConfigurations?.kickedOutOfGroupAfterBanMode?.get(sender.groupCode) == true) {
                try {
                    sender.memberContact.kick("kick by AdvancedBan")
                } catch (e: PermissionDeniedException) {
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是没有权限。")
                }catch (e: Exception){
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是发生了异常: ",e)
                }
            }
        }
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.globalBanOrWhiteListConfigurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "global ban {qq}")
    @Required(PERMISSIONHEAD + "global.ban.add")
    fun addgBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.globalBanOrWhiteListConfigurations?.globalBanList?.put(qq, true)
        if (sender is GroupXiaoMingUser) {
            if (plugin.globalBanOrWhiteListConfigurations?.kickedOutOfGroupAfterBanMode?.get(sender.groupCode) == true) {
                try {
                    sender.memberContact.kick("kick by AdvancedBan")
                } catch (e: PermissionDeniedException) {
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是没有权限。")
                }catch (e: Exception){
                    logger.error("群聊[" + sender.groupCode + "]启用了封禁踢人选项，但是发生了异常: ",e)
                }
            }
        }
        sender.sendMessage("已将该用户添加至封禁名单。")
        plugin!!.globalBanOrWhiteListConfigurations?.saveOrFail()
    }


    @Filter(COMMANDHEAD + "global banlist remove {qq}")
    @Required(PERMISSIONHEAD + "global.ban.remove")
    fun removegBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.globalBanOrWhiteListConfigurations?.globalBanList?.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.globalBanOrWhiteListConfigurations?.saveOrFail()
    }

    @Filter(COMMANDHEAD + "global unban {qq}")
    @Required(PERMISSIONHEAD + "global.ban.remove")
    fun removegBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long) {
        plugin!!.globalBanOrWhiteListConfigurations?.globalBanList?.remove(qq)
        sender.sendMessage("已将该用户移出封禁名单。")
        plugin!!.globalBanOrWhiteListConfigurations?.saveOrFail()
    }

    @Filter(Companion.COMMANDHEAD+"banlist list")
    @Required(Companion.PERMISSIONHEAD+"banlist.check.group")
    fun banList(sender: XiaoMingUser<*>){
        val arrlist = plugin!!.configurations!!.banList
        if(arrlist.size <=20){
            var sb = StringBuilder()
            for (i in 0..arrlist.size){
                sb.append(arrlist.get(i))
            }
            sender.sendMessage("封禁列表为:"
            +"\n"+sb.toString())
        }else{
            sender.sendError("太长了，请手动在配置文件中查看。")
        }
    }

    @Filter(Companion.COMMANDHEAD+"banlist list global")
    @Required(Companion.PERMISSIONHEAD+"banlist.check.global")
    fun globalBanList(sender: XiaoMingUser<*>){
        val map = plugin!!.globalBanOrWhiteListConfigurations!!.globalBanList
        if(map.size <=20){
            sender.sendMessage("封禁列表为:"
                    +"\n"+ map.keys
            )
        }else{
            sender.sendError("太长了，请手动在配置文件中查看。")
        }
    }


    companion object {
        const val COMMANDHEAD = "AdvancedBan "
        const val PERMISSIONHEAD = "advanced."
    }
}
