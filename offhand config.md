# Offhand-MITE 配置说明（玩家版）

## 1. 配置文件位置
- 文件名：`Offhand-mite.json`
- 目录：`config/`
- 常见路径：
- 正常客户端：`游戏根目录/config/Offhand-mite.json`
- 开发环境：`run/config/Offhand-mite.json`

如果你第一次装模组还没看到这个文件：
1. 先启动一次游戏并进入世界。
2. 退出游戏。
3. 再去 `config` 目录查看。

## 2. 修改前注意
1. 先退出游戏再改配置，避免被运行中的程序覆盖。
2. 建议先备份一份原文件。
3. 用文本编辑器修改（记事本、VSCode 都行）。
4. 改完保存后重新进游戏。

## 3. 默认配置示例
```json
{
  "offhandAttack": false,
  "emptyOffhand": false,
  "offhandBreakBlocks": false,
  "offhandPickup": false,
  "debugLogging": false,
  "offhandBlacklist": []
}
```

## 4. 每个配置项是干什么的

### `offhandAttack`
- 类型：`true / false`
- 当前状态：预留项（目前版本未实际接入玩法逻辑）。
- 建议：保持默认 `false`。

### `emptyOffhand`
- 类型：`true / false`
- 当前状态：预留项（目前版本未实际接入玩法逻辑）。
- 建议：保持默认 `false`。

### `offhandBreakBlocks`
- 类型：`true / false`
- 当前状态：预留项（目前版本未实际接入玩法逻辑）。
- 建议：保持默认 `false`。

### `offhandPickup`
- 类型：`true / false`
- 当前状态：已接入。
- 作用：
- `false`：地上拾取时，副手槽不会被当成普通背包槽位（推荐，避免“背包满了还往副手塞东西”）。
- `true`：允许拾取流程把物品放进副手槽（包含空位放入或可堆叠合并）。

### `debugLogging`
- 类型：`true / false`
- 作用：输出更多调试日志，方便排查问题。
- 建议：平时 `false`，排查问题时临时改成 `true`。

### `offhandBlacklist`
- 类型：字符串数组
- 作用：黑名单里的物品不能放进副手。
- 写法示例：
```json
"offhandBlacklist": [
  "item.bow",
  "item.bucketMilk"
]
```

注意：
- 填的是物品内部名（`unlocalizedName`），不是中文显示名。
- 不确定内部名时，建议先开 `debugLogging` 看日志。

## 5. 常用改法

### 方案A：只开调试日志
```json
"debugLogging": true
```

### 方案B：禁止某些物品进副手
```json
"offhandBlacklist": [
  "item.bow",
  "item.bucketMilk"
]
```

### 方案C：允许拾取时使用副手槽
```json
"offhandPickup": true
```

## 6. 最容易写错的地方
1. JSON 里不能写注释（不要写 `//`）。
2. 最后一项后面不要多逗号。
3. 布尔值必须小写：`true / false`。
4. 字段名不要改拼写。

## 7. 配置改坏了怎么恢复
1. 关闭游戏。
2. 删除 `config/Offhand-mite.json`。
3. 重新启动游戏，模组会按默认值重建配置。

## 8. 给普通玩家的推荐
- `debugLogging = false`
- `offhandPickup = false`（更稳，不容易出现背包拾取的误会）
- 其他布尔项先保持默认
- 只在有明确需求时再改 `offhandBlacklist`
