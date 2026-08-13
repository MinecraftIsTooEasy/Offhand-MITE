# Offhand Mod for MITE (FML-3.X)

[English](README_en.md) | [简体中文](README.md)

## 中文

**当前版本：3.0.9**

Offhand-MITE 为 Minecraft 1.6.4-MITE / FishModLoader 3.x 添加了一个真正可用的副手槽，以及围绕副手使用、渲染、同步和兼容 API 的完整流程。

### 功能

* 新增独立副手槽，副手物品会跟随玩家背包一起保存。
* 副手槽会显示在 HUD、生存背包和创造背包中；副手为空时不会在 HUD 中显示物品槽。
* 按 `F` 可以交换主手和副手物品，并支持物品交换时的 HUD 弹起动画。
* 打开生存背包或创造背包时，把鼠标悬停在合法槽位上并按 `F`，可以让该槽位和副手槽互换物品。
* 打开箱子、熔炉、工作台、附魔台、铁砧、发射器、投掷器、漏斗、酿造台等功能性容器界面时，也可以在合法槽位上按 `F` 与副手交互。
* 支持副手右键使用物品，例如方块、弓、食物、药水、牛奶，以及需要持续使用的物品。
* 右键使用遵循原版优先级：先尝试主手；主手成功执行动作后不会再尝试副手。例如主手食物和副手弓同时存在时，会优先进食。
* 支持第一人称和第三人称副手渲染，包括弓拉弓、射箭、剑格挡、攻击、挖掘、放置方块和持续使用状态同步。
* 地图属于双手展示物品；展示地图时会隐藏另一只手的普通物品，避免方块或物品残留渲染。
* 可选开启副手攻击：副手武器以及战锤、战斧、匕首、棍棒、镰刀等具有近战伤害的攻击类工具可以参与攻击，并正常消耗耐久。
* 可选开启副手挖掘：当主手和副手都拿着适合当前方块的工具时，副手工具会一起提供挖掘强度，并正常消耗耐久。
* 副手挖掘支持镐子、战锤、铲子、斧子、剪刀、镰刀等对目标方块有效的工具。
* 创造模式下会校验副手槽更新和中键取方块结果，避免副手物品被复制、刷出或产生客户端幽灵物品。
* 服务端会校验副手交换请求、容器窗口和槽位合法性，以及副手网络协议版本；物品状态、使用状态和追踪玩家加入后的初始状态由服务端同步。
* 提供兼容 API，方便其他 MOD 控制副手交互、渲染、同步和动作拦截。

### 使用方法

* 游戏中按 `F`：交换当前快捷栏物品和副手物品。
* 打开背包或功能性容器界面后，鼠标指向可交互槽位并按 `F`：交换该槽位物品和副手物品。鼠标光标持有物品时不会执行交换。
* 正常右键即可尝试使用副手物品。模组会先执行主手逻辑，只有主手没有产生有效动作时才临时切到副手执行，然后恢复主手选择。
* 副手攻击和副手挖掘默认关闭，需要在 ManyLib 的 `Offhand` 配置中手动开启。
* 没有任何物品的副手不会参与普通空槽拾取；如果副手已有同类可堆叠物品且尚未达到堆叠上限，普通拾取仍可以优先补充该物品堆。

### 配置

本模组使用 ManyLib 配置，不再使用旧版 `OffhandConfig` JSON 配置文件。

在 ManyLib 配置界面中打开 `Offhand` / `Offhand 副手模组配置`，当前配置项如下：

* `offhand.offhandAttack`：副手攻击。开启后，副手武器和具有近战伤害的攻击类工具会在攻击时额外提供伤害，并正常消耗副手耐久。
* `offhand.offhandBreakBlocks`：副手挖掘方块。开启后，主手和副手都拿着适合当前方块的工具时，副手工具会一起提供挖掘强度，并正常消耗耐久。
* `offhand.debugLogging`：调试日志。排查问题时开启，正常游玩建议关闭。
* `offhand.offhandBlacklist`：副手黑名单。填写禁止放入副手槽的物品 `unlocalizedName`，例如 `item.bow`。

旧配置项 `offhand.offhandPickup` 已移除，当前版本不会再使用 ManyLib 自动将物品放入空副手槽；已有同类副手物品的优先补充逻辑仍然保留。

### 兼容 API

其他 MOD 可以通过 `OffhandCompatRegistry` 接入副手兼容逻辑。API 版本为 `1`：

Other mods can integrate offhand compatibility logic via `OffhandCompatRegistry`:

* `IOffhandInteractionPolicy`: Controls whether the offhand can participate in block interactions.
* `IOffhandRenderPolicy`: Controls the rendering of the offhand in the HUD, first-person, and third-person views.
* `IOffhandSyncStrategy`: Customizes the synchronization of offhand items and usage states.
* `IOffhandActionFilter`: Intercepts offhand right-clicks, the swap-hand keypress, or server-side swap-hand packets.

对应中文含义：

* `IOffhandInteractionPolicy`：控制副手是否可以参与方块交互。
* `IOffhandRenderPolicy`：控制 HUD、第一人称和第三人称视角下的副手渲染。
* `IOffhandSyncStrategy`：自定义副手物品和使用状态的同步方式。
* `IOffhandActionFilter`：拦截副手右键、交换双手按键或服务端交换双手数据包。

### 运行环境

* Minecraft `1.6.4-MITE`
* FishModLoader `>= 3.4.2`（项目构建使用 `3.4.3`）
* Rusted Iron Core `>= 1.5.7`
* ManyLib `>= 2.3.1`
* Java `>= 17`

### 说明

* 副手使用物品时，模组会临时切换 `InventoryPlayer.currentItem`，借用原版或 MITE 现有物品逻辑完成动作，然后恢复主手选择。
* 副手槽被追加到玩家主背包数组中，但默认会和普通拾取、自动补货逻辑隔离，避免背包满时物品被意外塞进副手；已有同类副手物品仍可被优先补充。
* 副手不会额外叠加一套重复的主手动作；攻击、挖掘和放置时会在需要时复用或同步主手动作，减少网络同步和渲染状态错乱。
* 副手交换、容器交互和物品使用的最终状态以服务端为准，客户端动画只反映已确认的物品状态。

### 构建

Windows 下可以执行：

```text
gradlew.bat build
```

### 致谢

* This mod is a port of [Backhand Unofficial](https://github.com/GTNewHorizons/Backhand) by GTNH Team. Thanks to GTNH Team for the original work.
* I hope everyone will show plenty of support for GTNH Team.

本项目使用 MIT License。
