# Offhand Mod for MITE (FML-3.X)

English | [简体中文](README_zh.md)

## English

Offhand-MITE adds a dedicated offhand slot and offhand interaction flow to Minecraft 1.6.4-MITE on FishModLoader 3.x.

### Features

* Adds a dedicated offhand slot. The offhand item is saved together with the player's inventory.
* Shows the offhand slot in the HUD, survival inventory, and creative inventory. The HUD item slot is hidden when the offhand is empty.
* Press `F` to swap the main-hand and offhand items, with a confirmed HUD pickup animation.
* Open the survival or creative inventory, point at a valid slot, and press `F` to swap that slot with the offhand slot.
* Open a functional container such as a chest, furnace, crafting table, enchanting table, anvil, dispenser, dropper, hopper, or brewing stand and press `F` over a valid slot to interact with the offhand.
* Supports using offhand items with right-click, including blocks, bows, food, potions, milk, and items that require continuous use.
* Right-click follows vanilla priority: the main hand is tried first, and the offhand is not tried after the main hand successfully performs an action. For example, main-hand food takes priority over an offhand bow.
* Supports offhand rendering in first-person and third-person views, including bow drawing, arrow release, sword blocking, attack actions, mining actions, block placement, and synchronized use states.
* Maps use a two-handed display. While a map is being displayed, the ordinary item in the other hand is hidden to prevent leftover block or item rendering.
* Optional offhand attacks: offhand weapons and attack-capable tools such as warhammers, battle axes, daggers, clubs, and sickles can participate in attacks and consume durability normally.
* Optional offhand mining: when both hands hold tools suitable for the current block, the offhand tool contributes mining strength and consumes durability normally.
* Offhand mining supports pickaxes, warhammers, shovels, axes, shears, sickles, and other tools that are effective against the target block.
* Creative-mode offhand slot updates and pick-block results are validated to prevent duplication, cloned items, and client-side ghost items.
* The server validates offhand swap requests, container windows and slots, item validity, and the offhand network protocol version. Item state, use state, and initial state for newly tracked players are synchronized by the server.
* Provides a compatibility API for other mods to control offhand interaction, rendering, synchronization, and action interception.

### Usage

* Press `F` in game to swap the current hotbar item with the offhand item.
* Open an inventory or functional container, point at a valid slot, and press `F` to swap that slot with the offhand item. A swap is ignored while the cursor is holding an item.
* Right-click normally to use an offhand item. The mod first runs the main-hand logic and only temporarily selects the offhand when the main hand produces no effective action, then restores the selected main-hand slot.
* Offhand attacks and offhand mining are disabled by default and must be enabled in the ManyLib `Offhand` configuration.
* An empty offhand is excluded from normal empty-slot pickup. If the offhand already contains a matching stack that is not full, normal pickup can still prioritize replenishing that stack.

### Configuration

This mod uses ManyLib configuration and no longer uses the old `OffhandConfig` JSON configuration file.

Open `Offhand` / `Offhand configuration` in the ManyLib configuration screen. The current options are:

* `offhand.offhandAttack`: Offhand attacks. When enabled, offhand weapons and melee-damage tools provide additional attack damage and consume offhand durability normally.
* `offhand.offhandBreakBlocks`: Offhand block mining. When enabled, the offhand tool contributes mining strength when both hands hold tools suitable for the current block and consumes durability normally.
* `offhand.debugLogging`: Debug logging. Enable it while troubleshooting and disable it during normal play.
* `offhand.offhandBlacklist`: Offhand blacklist. Enter the item's `unlocalizedName` to prevent it from being placed in the offhand slot, for example `item.bow`.

The old `offhand.offhandPickup` option has been removed. Current versions do not use ManyLib automatic placement into an empty offhand slot; the existing matching-offhand replenishment behavior remains.

### Compatibility API

Other mods can integrate offhand compatibility logic via `OffhandCompatRegistry`:

* `IOffhandInteractionPolicy`: Controls whether the offhand can participate in block interactions.
* `IOffhandRenderPolicy`: Controls the rendering of the offhand in the HUD, first-person, and third-person views.
* `IOffhandSyncStrategy`: Customizes the synchronization of offhand items and usage states.
* `IOffhandActionFilter`: Intercepts offhand right-clicks, the swap-hand keypress, or server-side swap-hand packets.

The compatibility API version is `1`.

### Requirements

* Minecraft `1.6.4-MITE`
* FishModLoader `>= 3.4.2` (`3.4.3` is used for the project build)
* Rusted Iron Core `>= 1.5.7`
* ManyLib `>= 2.3.1`
* Java `>= 17`

### Notes

* When using an offhand item, the mod temporarily switches `InventoryPlayer.currentItem`, reuses the existing vanilla or MITE item logic to perform the action, and then restores the selected main-hand slot.
* The offhand slot is appended to the player's main inventory array, but it is isolated from normal pickup and automatic replenishment logic by default to prevent items from being placed in the offhand slot unexpectedly when the inventory is full. Existing matching offhand stacks can still be replenished.
* The offhand does not add a duplicate main-hand action. During attacks, mining, and block placement, it reuses or synchronizes the main-hand action when needed, reducing network synchronization and rendering state inconsistencies.
* The server is authoritative for offhand swaps, container interactions, and item use; client animations reflect confirmed item state.

### Build

On Windows, run:

```text
gradlew.bat build
```

### Credits

* This mod is a port of [Backhand Unofficial](https://github.com/GTNewHorizons/Backhand) by GTNH Team. Thanks to GTNH Team for the original work.
* I hope everyone will show plenty of support for GTNH Team.

This project is licensed under the [MIT License](LICENSE).
