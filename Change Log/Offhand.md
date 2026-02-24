# Offhand-MITE

## 1. 项目目标与现状
- 当前状态：核心副手能力已可运行，包含槽位扩展、主副手切换、右键使用、网络同步、HUD 与第一/第三人称渲染。

## 2. 结构级改造
- 新的主入口与配置体系：
- `src/main/java/com/m/offhand/Offhand.java`
- `src/main/java/com/m/offhand/OffhandConfig.java`
- 新的 API 分层：
- `api/core`：副手槽位与手部语义核心接口
- `api/compat`：交互策略、渲染策略、同步策略扩展点
- 网络模块重建：
- `network/OffhandPacketHandler.java`
- `network/OffhandNetDispatch.java`
- `network/*Packet.java`
- Mixin 体系重建：
- `InventoryPlayer / ContainerPlayer / EntityPlayer / NetServerHandler / Minecraft / ItemRenderer` 等关键点重注入

## 3. 已实现与修复清单

### 3.1 副手槽与背包结构
- 为 `InventoryPlayer.mainInventory` 扩容 1 个副手槽。
- 副手槽读档恢复修复：解决“退出重进副手物品消失”。
- 副手槽黑名单校验生效：禁止配置中黑名单物品放入副手。

### 3.2 容器与槽位安全
- `ContainerPlayer` 注入副手槽，避免破坏原有槽位逻辑。
- 处理副手槽 `transferStackInSlot` 路径，防止越界与错误转移。
- 服务端 `handleBlockItemSwitch` 放行副手槽并保留热栏合法性检查。

### 3.3 右键交互链路
- 使用实际链路：`onPlayerRightClickChecked` + `Packet81RightClick`。
- 副手右键触发策略可通过 `IOffhandInteractionPolicy` 扩展。

### 3.4 网络同步与追踪同步
- 协议版本校验：`PROTOCOL_VERSION = 1`。
- 建立 C2S 换手请求包：`OffhandSwapRequestPacket`。
- 建立 S2C 同步包：副手物品、使用状态、动画、取消使用。
- 引入按追踪者发送（tracking）分发，避免全服广播开销。

### 3.5 动画与渲染
- 第一人称副手渲染链路已接通。
- HUD 显示副手槽与图标。
- 第三人称左手持物姿态/渲染链路接入。
- 创造中键拾取幽灵方块修复（容器槽位 size 兼容处理）。

### 3.6 近期问题修复（按现网反馈）
- 修复 `MixinInventoryPlayer` 构造注入点非法导致的 Mixin 启动失败。
- 修复“创造模式左键破坏偶发需双击”问题（加强槽位同步时机）。
- 修复“副手食物/药水持续使用被中断”问题（副手 itemInUse 路径保持与转换逻辑）。
- 修复“副手喝牛奶崩溃 NPE”问题（`clearActivePotions` 时序保护）。
- 修复“副手物品重进存档消失”问题（NBT Slot 36 捕获与回填）。
- 按最新需求切换为“全局主手 swing 拦截”策略，以压制主副手双动画。

## 4. 构建与依赖管理
- 本地依赖要求：
- `libs/FishModLoader-v${loader_version}.jar`
- `libs/RustedIronCore-${rusted_iron_core_version}.jar`
- 已在构建中加入 `verifyLibs` 任务，编译前校验依赖存在性。

相关文件：
- `build.gradle`

## 5. 当前已知风险
- 当前采用“全局 swing 拦截”来压制主手动画，可能对部分原版/创造模式动作反馈产生副作用，需继续实机回归。
- 大量改动尚未整理成原子提交，建议后续按模块拆分提交（槽位、网络、动画、右键链路）。

## 6. 建议下一步
- 建立最小回归测试清单（单人/联机、创造/生存、食物/药水/牛奶/工具/方块）。
- 将“动画策略”抽象为可配置策略（全局拦截 / 上下文拦截）以便快速切换。
- 补充协议与配置变更日志，形成可发布版本说明。
