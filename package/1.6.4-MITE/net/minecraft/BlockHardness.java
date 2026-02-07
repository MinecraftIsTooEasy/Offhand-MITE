/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockHardness
/*     */ {
/*   8 */   public static final float stone = Material.stone.getFullBlockHardness();
/*     */ 
/*     */   
/*  11 */   public static final float grass = Material.dirt.getFullBlockHardness();
/*  12 */   public static final float dirt = Material.dirt.getFullBlockHardness();
/*     */   
/*     */   public static final float cobblestone = 1.5F;
/*  15 */   public static final float planks = Material.wood.getFullBlockHardness() * 2.0F / 3.0F;
/*     */   
/*     */   public static final float sapling = 0.02F;
/*  18 */   public static final float sand = Material.sand.getFullBlockHardness();
/*     */   
/*     */   public static final float gravel = 0.8F;
/*     */   public static final float oreGold = 2.5F;
/*     */   public static final float oreIron = 3.0F;
/*     */   public static final float oreCoal = 1.5F;
/*  24 */   public static final float log = Material.wood.getFullBlockHardness();
/*     */   
/*  26 */   public static final float leaves = Material.tree_leaves.getFullBlockHardness();
/*     */   public static final float sponge = 0.2F;
/*     */   public static final float glass = 0.1F;
/*     */   public static final float oreLapis = 2.0F;
/*     */   public static final float blockLapis = 2.0F;
/*     */   public static final float dispenser = 0.5F;
/*     */   public static final float sandStone = 1.0F;
/*     */   public static final float music = 0.5F;
/*     */   public static final float bed = 0.5F;
/*     */   public static final float rail = 0.5F;
/*     */   public static final float piston = 0.5F;
/*     */   public static final float web = 0.1F;
/*     */   public static final float tallGrass = 0.02F;
/*     */   public static final float deadBush = 0.04F;
/*     */   public static final float cloth = 0.1F;
/*     */   public static final float flower = 0.0F;
/*     */   public static final float blockWood = 1.0F;
/*     */   public static final float slabWood = 0.5F;
/*     */   public static final float brick = 1.5F;
/*     */   public static final float tnt = 0.0F;
/*     */   public static final float bookShelf = 0.5F;
/*     */   public static final float obsidian = 8.0F;
/*     */   public static final float torch = 0.0F;
/*     */   public static final float spawner = 0.5F;
/*     */   public static final float stairsWood = 0.5F;
/*     */   public static final float chestWood = 0.5F;
/*     */   public static final float oreDiamond = 2.5F;
/*  53 */   public static final float workbench = planks * 2.0F;
/*     */   
/*     */   public static final float wheat = 0.02F;
/*     */   public static final float tilledField = 0.5F;
/*     */   public static final float furnace = 1.5F;
/*     */   public static final float sign = 0.5F;
/*     */   public static final float ladder = 0.5F;
/*     */   public static final float lever = 0.5F;
/*     */   public static final float pressurePlateWood = 0.25F;
/*     */   public static final float oreRedstone = 2.0F;
/*     */   public static final float buttonWood = 0.25F;
/*  64 */   public static final float snow = Material.snow.getFullBlockHardness() / 8.0F;
/*  65 */   public static final float blockIce = log;
/*     */   
/*  67 */   public static final float blockSnow = Material.craftedSnow.getFullBlockHardness();
/*     */   public static final float cactus = 0.5F;
/*     */   public static final float blockClay = 1.0F;
/*     */   public static final float reed = 0.04F;
/*     */   public static final float jukebox = 0.5F;
/*     */   public static final float pumpkin = 0.5F;
/*     */   public static final float netherrack = 4.0F;
/*     */   public static final float glowStone = 3.0F;
/*     */   public static final float pumpkinLantern = 0.25F;
/*     */   public static final float cake = 0.05F;
/*     */   public static final float trapdoorWood = 0.5F;
/*     */   public static final float silverfish = 0.75F;
/*     */   public static final float stoneBrick = 2.0F;
/*     */   public static final float mushroomCap = 0.2F;
/*     */   public static final float thinGlass = 0.02F;
/*     */   public static final float melon = 0.5F;
/*     */   public static final float pumpkinStem = 0.02F;
/*     */   public static final float melonStem = 0.02F;
/*     */   public static final float vine = 0.1F;
/*     */   public static final float mycelium = 0.1F;
/*     */   public static final float waterLily = 0.02F;
/*     */   public static final float netherBrick = 4.0F;
/*     */   public static final float netherFence = 1.6F;
/*     */   public static final float netherStairs = 2.0F;
/*     */   public static final float netherStalk = 0.02F;
/*  92 */   public static final float enchantmentTable = workbench;
/*  93 */   public static final float brewingStand = workbench;
/*  94 */   public static final float cauldron = workbench;
/*  95 */   public static final float whiteStone = stone;
/*     */   public static final float redstoneLamp = 0.2F;
/*     */   public static final float cocoaPlant = 0.1F;
/*     */   public static final float oreEmerald = 2.5F;
/*     */   public static final float beacon = 0.5F;
/*     */   public static final float wallWood = 0.5F;
/*     */   public static final float skull = 0.1F;
/* 102 */   public static final float anvil = workbench;
/*     */   public static final float daylightSensor = 0.2F;
/*     */   public static final float blockRedstone = 2.5F;
/*     */   public static final float oreNetherQuartz = 2.5F;
/* 106 */   public static final float hopperBlock = workbench;
/*     */   public static final float dropper = 0.5F;
/*     */   public static final float blockHay = 0.4F;
/*     */   public static final float carpet = 0.2F;
/*     */   public static final float hardenedClay = 2.0F;
/*     */   public static final float coalBlock = 1.0F;
/*     */   public static final float oreCopper = 2.5F;
/*     */   public static final float oreSilver = 2.5F;
/*     */   public static final float oreMithril = 3.5F;
/*     */   public static final float oreAdamantium = 4.0F;
/*     */   public static final float doorWood = 0.5F;
/*     */   public static final float fenceWood = 0.4F;
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockHardness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */