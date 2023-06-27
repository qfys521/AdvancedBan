# AdvancedBan
XiaoMingBotPlugin (author:[qfys521](https://www.github.com/qfys521))

----

 *This Plugin has `no affiliation` with Minecraft Plugin `AdvancedBan`.*   

----
`Commands List`   

```textmate
1、AdvancedBan  AddThisGroupBanAutoKick
2、AdvancedBan  GlobalBanListMode  [mode]
3、AdvancedBan  RemoveThisGroupBanAutoKick
4、AdvancedBan  ban  [qq]
5、AdvancedBan  banlist  add  [qq]
6、AdvancedBan  banlist  remove  [qq]
7、AdvancedBan  global  ban  [qq]
8、AdvancedBan  global  banlist  add  [qq]
9、AdvancedBan  global  banlist  remove  [qq]
10、AdvancedBan  global  unban  [qq]
11、AdvancedBan  set  WhiteListMode  [mode]
12、AdvancedBan  set  banListMode  [mode]
13、AdvancedBan  unban  [qq]
14、AdvancedBan  whitelist  add  [qq]
15、AdvancedBan  whitelist  remove  [qq]
```


----
**command functions**
```kotlin
fun addBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun addBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun addWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun removeBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun removeBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun removeWhiteList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun changeBanlistMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String)
fun changeWhitelistMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String)
fun changeGlobalBanListMode(sender: XiaoMingUser<*>, @FilterParameter("mode") mode: String)
fun addGroupAutoKick(sender: GroupXiaoMingUser)
fun removeGroupAutoKick(sender: GroupXiaoMingUser)
fun addgBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun addgBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun removegBanList(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
fun removegBanList2(sender: XiaoMingUser<*>, @FilterParameter("qq") qq: Long)
```