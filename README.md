# Offhand Mod for MITE (FML-3.X)

Offhand-MITE 为 Minecraft 1.6.4-MITE / FishModLoader 3.x 添加了一个真正可用的副手槽，以及围绕副手使用、渲染、同步和兼容 API 的完整流程。

Offhand-MITE adds a dedicated offhand slot and offhand interaction flow to Minecraft 1.6.4-MITE on FishModLoader 3.x.

## 功能

* 新增独立副手槽，副手物品会跟随玩家背包一起保存。
* 副手槽会显示在 HUD、生存背包和创造背包中。
* 按 `F` 可以交换主手和副手物品。
* 打开背包时，把鼠标悬停在某个槽位上并按 `F`，可以让该槽位和副手槽互换物品。
* 支持副手右键使用物品，例如方块、弓、食物、药水、牛奶，以及需要持续使用的物品。
* 支持第一人称和第三人称副手渲染，包括弓拉弓、剑格挡、攻击动作、挖掘动作和使用状态同步。
* 可选开启副手攻击：副手武器和战锤、战斧、匕首、棍棒、镰刀等有近战伤害的攻击类工具可以一起参与攻击，并正常消耗耐久。
* 可选开启副手挖掘：当主手和副手都拿着适合当前方块的挖掘工具时，副手工具会一起提供挖掘强度，并正常消耗耐久。
* 副手挖掘支持镐子、战锤、铲子、斧子、剪刀、镰刀等对目标方块有效的工具。
* 服务端会校验副手交换请求和副手网络协议版本，减少客户端状态错乱。
* 提供兼容 API，方便其他 MOD 控制副手交互、渲染、同步和动作拦截。

## 使用方法

* 游戏中按 `F`：交换当前快捷栏物品和副手物品。
* 打开背包后，鼠标指向某个槽位并按 `F`：交换该槽位物品和副手物品。
* 正常右键即可尝试使用副手物品。如果主手物品不适合当前右键动作，模组会临时切到副手执行动作，然后自动切回主手。
* 副手攻击和副手挖掘默认关闭，需要在 ManyLib 配置里手动开启。

## 配置

本模组使用 ManyLib 配置，不再使用旧版 `OffhandConfig` JSON 配置文件。

* `offhand.offhandAttack`：副手攻击。开启后，副手武器和战锤、战斧等攻击类工具会在攻击时额外提供伤害，并正常消耗副手耐久。
* `offhand.offhandBreakBlocks`：副手挖掘方块。开启后，主手和副手都拿着适合当前方块的挖掘工具时，副手工具会一起提供挖掘强度，并正常消耗耐久。
* `offhand.offhandPickup`：副手自动拾取。控制拾取物品时是否允许自动放入副手槽；背包满时建议保持关闭。
* `offhand.debugLogging`：调试日志。排查问题时开启，正常游玩建议关闭。
* `offhand.offhandBlacklist`：副手黑名单。填写禁止放入副手槽的物品 `unlocalizedName`，例如 `item.bow`。

## Compatibility API

Other mods can integrate offhand compatibility logic via `OffhandCompatRegistry`:

* `IOffhandInteractionPolicy`: Controls whether the offhand can participate in block interactions.
* `IOffhandRenderPolicy`: Controls the rendering of the offhand in the HUD, first-person, and third-person views.
* `IOffhandSyncStrategy`: Customizes the synchronization of offhand items and usage states.
* `IOffhandActionFilter`: Intercepts offhand right-clicks, the swap-hand keypress, or server-side swap-hand packets.

## 说明

* 副手使用物品时，模组会临时切换 `InventoryPlayer.currentItem`，借用原版或 MITE 现有物品逻辑完成动作，然后恢复主手选择。
* 副手槽被追加到玩家主背包数组中，但默认会和普通拾取、自动补货逻辑隔离，避免背包满时物品被意外塞进副手槽。
* 独立副手挥动动画已经移除；攻击和挖掘时，副手会在需要时复用主手动作，减少网络同步和渲染状态错乱。

## Credits
* This mod is a port of [Backhand Unofficial](https://github.com/GTNewHorizons/Backhand) by GTNH Team. Thanks to GTNH Team for the original work.
* I hope everyone will show plenty of support for GTNH Team.
