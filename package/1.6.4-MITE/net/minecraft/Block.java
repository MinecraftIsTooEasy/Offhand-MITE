/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Block
/*      */ {
/*      */   private CreativeTabs displayOnCreativeTab;
/*      */   protected String textureName;
/*   17 */   public static final StepSound soundPowderFootstep = new StepSound("stone", 1.0F, 1.0F);
/*   18 */   public static final StepSound soundWoodFootstep = new StepSound("wood", 1.0F, 1.0F);
/*   19 */   public static final StepSound soundGravelFootstep = new StepSound("gravel", 1.0F, 1.0F);
/*   20 */   public static final StepSound soundGrassFootstep = new StepSound("grass", 1.0F, 1.0F);
/*   21 */   public static final StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
/*   22 */   public static final StepSound soundMetalFootstep = new StepSound("stone", 1.0F, 1.5F);
/*   23 */   public static final StepSound soundGlassFootstep = new StepSoundStone("stone", 1.0F, 1.0F);
/*   24 */   public static final StepSound soundClothFootstep = new StepSound("cloth", 1.0F, 1.0F);
/*   25 */   public static final StepSound soundSandFootstep = new StepSound("sand", 1.0F, 1.0F);
/*   26 */   public static final StepSound soundSnowFootstep = new StepSound("snow", 1.0F, 1.0F);
/*   27 */   public static final StepSound soundLadderFootstep = new StepSoundSand("ladder", 1.0F, 1.0F);
/*      */   
/*   29 */   public static final StepSound soundAnvilFootstep = new StepSoundAnvil("stone", 0.3F, 1.0F);
/*      */ 
/*      */   
/*   32 */   public static final Block[] blocksList = new Block[4096];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   37 */   public static final boolean[] opaqueCubeLookup = new boolean[4096];
/*      */ 
/*      */   
/*   40 */   public static final int[] lightOpacity = new int[4096];
/*      */ 
/*      */ 
/*      */   
/*   44 */   public static final boolean[] canHaveLightValue = new boolean[4096];
/*      */ 
/*      */   
/*   47 */   public static final int[] lightValue = new int[4096];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   52 */   public static boolean[] useNeighborBrightness = new boolean[4096];
/*      */ 
/*      */   
/*   55 */   public static final boolean[] is_normal_cube_lookup = new boolean[256];
/*      */ 
/*      */   
/*   58 */   public static final Block stone = (new BlockStone(1)).setHardness(BlockHardness.stone).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stone").setTextureName("stone");
/*      */   
/*   60 */   public static final BlockGrass grass = (BlockGrass)(new BlockGrass(2)).setHardness(BlockHardness.grass).setStepSound(soundGrassFootstep).setUnlocalizedName("grass").setTextureName("grass");
/*      */ 
/*      */   
/*   63 */   public static final Block dirt = (new BlockDirt(3)).setHardness(BlockHardness.dirt).setStepSound(soundGravelFootstep).setUnlocalizedName("dirt").setTextureName("dirt");
/*      */ 
/*      */   
/*   66 */   public static final Block cobblestone = (new Block(4, Material.stone, new BlockConstants())).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stonebrick").setCreativeTab(CreativeTabs.tabBlock).setTextureName("cobblestone");
/*      */ 
/*      */   
/*   69 */   public static final BlockWood planks = (BlockWood)(new BlockWood(5)).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("wood").setTextureName("planks");
/*      */   
/*   71 */   public static final Block sapling = (new BlockSapling(6)).setHardness(0.02F).setStepSound(soundGrassFootstep).setUnlocalizedName("sapling").setTextureName("sapling");
/*      */   
/*   73 */   public static final Block bedrock = (new BlockBedrock(7, Material.stone, (new BlockConstants()).setAlwaysImmutable())).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("bedrock").disableStats().setCreativeTab(CreativeTabs.tabBlock).setTextureName("bedrock");
/*   74 */   public static final BlockFluid waterMoving = (BlockFluid)(new BlockFlowing(8, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats().setTextureName("water_flow");
/*   75 */   public static final Block waterStill = (new BlockStationary(9, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats().setTextureName("water_still");
/*      */   
/*   77 */   public static final BlockFluid lavaMoving = (BlockFluid)(new BlockFlowing(10, Material.lava)).setHardness(0.0F).setLightValue(0.9F).setUnlocalizedName("lava").disableStats().setTextureName("lava_flow");
/*      */ 
/*      */ 
/*      */   
/*   81 */   public static final Block lavaStill = (new BlockStationary(11, Material.lava)).setHardness(100.0F).setLightValue(0.9F).setUnlocalizedName("lava").disableStats().setTextureName("lava_still");
/*      */ 
/*      */ 
/*      */   
/*   85 */   public static final BlockSand sand = (BlockSand)(new BlockSand(12)).setHardness(0.4F).setCushioning(0.4F).setStepSound(soundSandFootstep).setUnlocalizedName("sand").setTextureName("sand");
/*      */ 
/*      */   
/*   88 */   public static final BlockGravel gravel = (BlockGravel)(new BlockGravel(13)).setHardness(0.6F).setStepSound(soundGravelFootstep).setUnlocalizedName("gravel").setTextureName("gravel");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   public static final Block oreGold = (new BlockGoldOre(14, Material.gold, 2)).setHardness(2.4F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreGold").setTextureName("gold_ore");
/*   94 */   public static final Block oreIron = (new BlockOre(15, Material.iron, 2)).setHardness(3.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreIron").setTextureName("iron_ore");
/*      */ 
/*      */   
/*   97 */   public static final Block oreCoal = (new BlockOre(16, Material.coal, 2)).setHardness(1.2F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreCoal").setTextureName("coal_ore");
/*      */ 
/*      */   
/*  100 */   public static final BlockLog wood = (BlockLog)(new BlockLog(17)).setStepSound(soundWoodFootstep).setUnlocalizedName("log").setTextureName("log");
/*      */   
/*  102 */   public static final BlockLeaves leaves = (BlockLeaves)(new BlockLeaves(18)).setHardness(0.2F).setLightOpacity(1).setStepSound(soundGrassFootstep).setUnlocalizedName("leaves").setTextureName("leaves");
/*      */   
/*  104 */   public static final Block sponge = (new BlockSponge(19)).setHardness(0.6F).setCushioning(1.0F).setStepSound(soundClothFootstep).setUnlocalizedName("sponge").setTextureName("sponge");
/*      */   
/*  106 */   public static final Block glass = (new BlockGlass(20, Material.glass, false)).setHardness(2.0F).setStepSound(soundGlassFootstep).setUnlocalizedName("glass").setTextureName("glass");
/*      */   
/*  108 */   public static final Block oreLapis = (new BlockOre(21, Material.lapis_lazuli, 2)).setHardness(3.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreLapis").setTextureName("lapis_ore");
/*      */   
/*  110 */   public static final Block blockLapis = (new Block(22, Material.stone, new BlockConstants())).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("blockLapis").setCreativeTab(CreativeTabs.tabBlock).setTextureName("lapis_block");
/*      */   
/*  112 */   public static final BlockDispenser dispenser = (BlockDispenser)(new BlockDispenser(23)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("dispenser").setTextureName("dispenser");
/*  113 */   public static final Block sandStone = (new BlockSandStone(24)).setStepSound(soundStoneFootstep).setHardness(0.8F).setUnlocalizedName("sandStone").setTextureName("sandstone");
/*  114 */   public static final Block music = (new BlockNote(25)).setHardness(0.8F).setUnlocalizedName("musicBlock").setTextureName("noteblock");
/*      */   
/*  116 */   public static final BlockBed bed = (BlockBed)(new BlockBed(26)).setHardness(0.2F).setCushioning(0.6F).setUnlocalizedName("bed").disableStats().setTextureName("bed");
/*  117 */   public static final Block railPowered = (new BlockRailPowered(27)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("goldenRail").setTextureName("rail_golden");
/*  118 */   public static final Block railDetector = (new BlockDetectorRail(28)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("detectorRail").setTextureName("rail_detector");
/*  119 */   public static final BlockPistonBase pistonStickyBase = (BlockPistonBase)(new BlockPistonBase(29, true)).setUnlocalizedName("pistonStickyBase");
/*      */   
/*  121 */   public static final Block web = (new BlockWeb(30)).setLightOpacity(1).setHardness(0.1F).setUnlocalizedName("web").setTextureName("web");
/*      */   
/*  123 */   public static final BlockTallGrass tallGrass = (BlockTallGrass)(new BlockTallGrass(31)).setHardness(0.02F).setCushioning(0.2F).setStepSound(soundGrassFootstep).setUnlocalizedName("tallgrass");
/*      */   
/*  125 */   public static final BlockDeadBush deadBush = (BlockDeadBush)(new BlockDeadBush(32)).setHardness(0.02F).setStepSound(soundGrassFootstep).setUnlocalizedName("deadbush").setTextureName("deadbush");
/*  126 */   public static final BlockPistonBase pistonBase = (BlockPistonBase)(new BlockPistonBase(33, false)).setUnlocalizedName("pistonBase");
/*  127 */   public static final BlockPistonExtension pistonExtension = new BlockPistonExtension(34);
/*      */   
/*  129 */   public static final Block cloth = (new BlockColored(35, Material.cloth, (new BlockConstants()).setNeverConnectsWithFence())).setMaxStackSize(8).setHardness(0.8F).setCushioning(0.8F).setStepSound(soundClothFootstep).setUnlocalizedName("cloth").setTextureName("wool_colored");
/*  130 */   public static final BlockPistonMoving pistonMoving = new BlockPistonMoving(36);
/*      */   
/*  132 */   public static final BlockFlower plantYellow = (BlockFlower)(new BlockFlower(37)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("dandelion").setTextureName("flowers/dandelion");
/*      */   
/*  134 */   public static final BlockFlowerMulti plantRed = (BlockFlowerMulti)(new BlockFlowerMulti(38)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("flower").setTextureName("flowers/");
/*      */   
/*  136 */   public static final BlockMushroom mushroomBrown = (BlockMushroom)(new BlockMushroom(39)).setHardness(0.0F).setStepSound(soundGrassFootstep).setLightValue(0.15F).setUnlocalizedName("mushroom").setTextureName("mushroom_brown");
/*  137 */   public static final BlockMushroom mushroomRed = (BlockMushroom)(new BlockMushroom(40)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("mushroom").setTextureName("mushroom_red");
/*      */ 
/*      */   
/*  140 */   public static final Block blockGold = (new BlockOreStorage(41, Material.gold)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockGold").setTextureName("gold_block");
/*  141 */   public static final Block blockIron = (new BlockOreStorage(42, Material.iron)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockIron").setTextureName("iron_block");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   public static final BlockSlabGroup1 stoneSingleSlab = (BlockSlabGroup1)(new BlockSlabGroup1(44, Material.stone)).setStepSound(soundStoneFootstep);
/*  152 */   public static final BlockDoubleSlab stoneDoubleSlab = (BlockDoubleSlab)(new BlockDoubleSlab(43, stoneSingleSlab)).setStepSound(soundStoneFootstep);
/*      */   
/*  154 */   public static final Block brick = (new Block(45, Material.stone, new BlockConstants())).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabBlock).setTextureName("brick");
/*      */   
/*  156 */   public static final BlockTNT tnt = (BlockTNT)(new BlockTNT(46)).setHardness(1.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("tnt").setTextureName("tnt");
/*  157 */   public static final Block bookShelf = (new BlockBookshelf(47)).setHardness(1.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("bookshelf").setTextureName("bookshelf");
/*      */   
/*  159 */   public static final Block cobblestoneMossy = (new Block(48, Material.stone, new BlockConstants())).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock).setTextureName("cobblestone_mossy");
/*      */ 
/*      */   
/*  162 */   public static final Block obsidian = (new BlockObsidian(49)).setHardness(2.4F).setResistance(20.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("obsidian").setTextureName("obsidian");
/*      */   
/*  164 */   public static final Block torchWood = (new BlockTorch(50)).setHardness(0.0F).setLightValue(0.9375F).setStepSound(soundWoodFootstep).setUnlocalizedName("torch").setTextureName("torch_on");
/*  165 */   public static final BlockFire fire = (BlockFire)(new BlockFire(51)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fire").disableStats().setTextureName("fire");
/*      */   
/*  167 */   public static final Block mobSpawner = (new BlockMobSpawner(52)).setHardness(3.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("mobSpawner").disableStats().setTextureName("mob_spawner");
/*  168 */   public static final Block stairsWoodOak = (new BlockStairs(53, planks, 0)).setUnlocalizedName("stairsWood");
/*      */   
/*  170 */   public static final BlockChest chest = (BlockChest)(new BlockChest(54, EnumChestType.normal, Material.wood)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("chest");
/*  171 */   public static final BlockRedstoneWire redstoneWire = (BlockRedstoneWire)(new BlockRedstoneWire(55)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("redstoneDust").disableStats().setTextureName("redstone_dust");
/*      */   
/*  173 */   public static final Block oreDiamond = (new BlockOre(56, Material.diamond, 4)).setHardness(3.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreDiamond").setTextureName("diamond_ore");
/*      */   
/*  175 */   public static final Block blockDiamond = (new BlockOreStorage(57, Material.diamond)).setMaxStackSize(4).setStepSound(soundMetalFootstep).setUnlocalizedName("blockDiamond").setTextureName("diamond_block");
/*      */   
/*  177 */   public static final Block workbench = (new BlockWorkbench(58)).setStepSound(soundWoodFootstep).setUnlocalizedName("workbench");
/*      */   
/*  179 */   public static final Block crops = (new BlockCrops(59, 8)).setUnlocalizedName("crops").setTextureName("wheat");
/*  180 */   public static final Block tilledField = (new BlockFarmland(60)).setHardness(0.6F).setStepSound(soundGravelFootstep).setUnlocalizedName("farmland").setTextureName("farmland");
/*      */ 
/*      */   
/*  183 */   public static final Block furnaceIdle = (new BlockFurnaceCobblestone(61, false)).setHardness(2.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnace").setCreativeTab(CreativeTabs.tabDecorations);
/*  184 */   public static final Block furnaceBurning = (new BlockFurnaceCobblestone(62, true)).setHardness(2.0F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnace");
/*      */ 
/*      */   
/*  187 */   public static final Block signPost = (new BlockSign(63, TileEntitySign.class, true)).setStepSound(soundWoodFootstep).setUnlocalizedName("sign").disableStats();
/*      */   
/*  189 */   public static final Block doorWood = (new BlockDoor(64, Material.wood)).setStepSound(soundWoodFootstep).setUnlocalizedName("doorWood").disableStats().setTextureName("door_wood");
/*      */   
/*  191 */   public static final Block ladder = (new BlockLadder(65)).setStepSound(soundLadderFootstep).setUnlocalizedName("ladder").setTextureName("ladder");
/*  192 */   public static final Block rail = (new BlockRail(66)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("rail").setTextureName("rail_normal");
/*  193 */   public static final Block stairsCobblestone = (new BlockStairs(67, cobblestone, 0)).setUnlocalizedName("stairsStone");
/*      */   
/*  195 */   public static final Block signWall = (new BlockSign(68, TileEntitySign.class, false)).setStepSound(soundWoodFootstep).setUnlocalizedName("sign").disableStats();
/*  196 */   public static final Block lever = (new BlockLever(69)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("lever").setTextureName("lever");
/*  197 */   public static final Block pressurePlateStone = (new BlockPressurePlate(70, "stone", Material.stone, EnumMobType.mobs)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("pressurePlate");
/*      */   
/*  199 */   public static final Block doorIron = (new BlockDoor(71, Material.iron)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorIron").disableStats().setTextureName("door_iron");
/*  200 */   public static final Block pressurePlatePlanks = (new BlockPressurePlate(72, "planks_oak", Material.wood, EnumMobType.everything)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("pressurePlate");
/*  201 */   public static final Block oreRedstone = (new BlockRedstoneOre(73, false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").setCreativeTab(CreativeTabs.tabBlock).setTextureName("redstone_ore");
/*  202 */   public static final Block oreRedstoneGlowing = (new BlockRedstoneOre(74, true)).setLightValue(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").setTextureName("redstone_ore");
/*  203 */   public static final Block torchRedstoneIdle = (new BlockRedstoneTorch(75, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("notGate").setTextureName("redstone_torch_off");
/*  204 */   public static final Block torchRedstoneActive = (new BlockRedstoneTorch(76, true)).setHardness(0.0F).setLightValue(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("notGate").setCreativeTab(CreativeTabs.tabRedstone).setTextureName("redstone_torch_on");
/*  205 */   public static final Block stoneButton = (new BlockButtonStone(77)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("button");
/*      */   
/*  207 */   public static final Block snow = (new BlockSnow(78)).setMaxStackSize(32).setHardness(0.05F).setStepSound(soundSnowFootstep).setUnlocalizedName("snow").setLightOpacity(0).setTextureName("snow");
/*      */   
/*  209 */   public static final Block ice = (new BlockIce(79)).setHardness(1.0F).setLightOpacity(3).setStepSound(soundGlassFootstep).setUnlocalizedName("ice").setTextureName("ice");
/*      */   
/*  211 */   public static final Block blockSnow = (new BlockSnowBlock(80)).setStepSound(soundSnowFootstep).setUnlocalizedName("snow").setTextureName("snow");
/*  212 */   public static final Block cactus = (new BlockCactus(81)).setHardness(0.4F).setStepSound(soundClothFootstep).setUnlocalizedName("cactus").setTextureName("cactus");
/*      */   
/*  214 */   public static final Block blockClay = (new BlockClay(82)).setHardness(0.8F).setStepSound(soundGravelFootstep).setUnlocalizedName("clay").setTextureName("clay");
/*      */   
/*  216 */   public static final Block reed = (new BlockReed(83)).setHardness(0.08F).setStepSound(soundGrassFootstep).setUnlocalizedName("reeds").disableStats().setTextureName("reeds");
/*      */   
/*  218 */   public static final BlockJukeBox jukebox = (BlockJukeBox)(new BlockJukeBox(84)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("jukebox").setTextureName("jukebox");
/*      */   
/*  220 */   public static final Block fence = (new BlockFence(85, "planks_oak", Material.wood)).setHardness(0.4F).setResistance(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fence");
/*      */   
/*  222 */   public static final Block pumpkin = (new BlockPumpkin(86, false)).setHardness(0.6F).setStepSound(soundWoodFootstep).setUnlocalizedName("pumpkin").setTextureName("pumpkin");
/*      */   
/*  224 */   public static final Block netherrack = (new BlockNetherrack(87)).setHardness(1.6F).setStepSound(soundStoneFootstep).setUnlocalizedName("hellrock").setTextureName("netherrack");
/*  225 */   public static final Block slowSand = (new BlockSoulSand(88)).setHardness(0.5F).setStepSound(soundSandFootstep).setUnlocalizedName("hellsand").setTextureName("soul_sand");
/*  226 */   public static final Block glowStone = (new BlockGlowStone(89, Material.glass)).setHardness(0.3F).setStepSound(soundGlassFootstep).setLightValue(1.0F).setUnlocalizedName("lightgem").setTextureName("glowstone");
/*      */ 
/*      */   
/*  229 */   public static final BlockPortal portal = (BlockPortal)(new BlockPortal(90)).setHardness(-1.0F).setStepSound(soundGlassFootstep).setLightValue(0.75F).setUnlocalizedName("portal").setTextureName("portal");
/*      */   
/*  231 */   public static final Block pumpkinLantern = (new BlockPumpkin(91, true)).setHardness(1.0F).setStepSound(soundWoodFootstep).setLightValue(0.8F).setUnlocalizedName("litpumpkin").setTextureName("pumpkin");
/*  232 */   public static final Block cake = (new BlockCake(92)).setHardness(0.5F).setStepSound(soundClothFootstep).setUnlocalizedName("cake").disableStats().setTextureName("cake");
/*  233 */   public static final BlockRedstoneRepeater redstoneRepeaterIdle = (BlockRedstoneRepeater)(new BlockRedstoneRepeater(93, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("diode").disableStats().setTextureName("repeater_off");
/*  234 */   public static final BlockRedstoneRepeater redstoneRepeaterActive = (BlockRedstoneRepeater)(new BlockRedstoneRepeater(94, true)).setHardness(0.0F).setLightValue(0.625F).setStepSound(soundWoodFootstep).setUnlocalizedName("diode").disableStats().setTextureName("repeater_on");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  241 */   public static final Block trapdoor = (new BlockTrapDoor(96, Material.wood)).setStepSound(soundWoodFootstep).setUnlocalizedName("trapdoor").disableStats().setTextureName("trapdoor");
/*  242 */   public static final BlockSilverfish silverfish = (BlockSilverfish)(new BlockSilverfish(97)).setHardness(0.75F).setUnlocalizedName("monsterStoneEgg");
/*  243 */   public static final Block stoneBrick = (new BlockStoneBrick(98)).setHardness(1.5F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stonebricksmooth").setTextureName("stonebrick");
/*      */ 
/*      */   
/*  246 */   public static final Block mushroomCapBrown = (new BlockMushroomCap(99, Material.wood, 0)).setHardness(0.2F).setStepSound(soundClothFootstep).setUnlocalizedName("mushroom").setTextureName("mushroom_block");
/*  247 */   public static final Block mushroomCapRed = (new BlockMushroomCap(100, Material.wood, 1)).setHardness(0.2F).setStepSound(soundClothFootstep).setUnlocalizedName("mushroom").setTextureName("mushroom_block");
/*      */   
/*  249 */   public static final Block fenceIron = (new BlockPane(101, "iron_bars", "iron_bars", Material.iron, true)).setHardness(5.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceIron");
/*      */   
/*  251 */   public static final Block thinGlass = (new BlockPane(102, "glass", "glass_pane_top", Material.glass, false)).setHardness(0.1F).setStepSound(soundGlassFootstep).setUnlocalizedName("thinGlass");
/*      */   
/*  253 */   public static final Block melon = (new BlockMelon(103)).setHardness(0.6F).setStepSound(soundWoodFootstep).setUnlocalizedName("melon").setTextureName("melon");
/*      */   
/*  255 */   public static final Block pumpkinStem = (new BlockStem(104, pumpkin)).setHardness(0.02F).setStepSound(soundGrassFootstep).setUnlocalizedName("pumpkinStem").setTextureName("pumpkin_stem");
/*      */   
/*  257 */   public static final Block melonStem = (new BlockStem(105, melon)).setHardness(0.02F).setStepSound(soundGrassFootstep).setUnlocalizedName("pumpkinStem").setTextureName("melon_stem");
/*      */   
/*  259 */   public static final BlockVine vine = (BlockVine)(new BlockVine(106)).setHardness(0.2F).setStepSound(soundGrassFootstep).setUnlocalizedName("vine").setTextureName("vine");
/*  260 */   public static final Block fenceGate = (new BlockFenceGate(107)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fenceGate");
/*  261 */   public static final Block stairsBrick = (new BlockStairs(108, brick, 0)).setUnlocalizedName("stairsBrick");
/*  262 */   public static final Block stairsStoneBrick = (new BlockStairs(109, stoneBrick, 0)).setUnlocalizedName("stairsStoneBrickSmooth");
/*  263 */   public static final BlockMycelium mycelium = (BlockMycelium)(new BlockMycelium(110)).setHardness(0.6F).setStepSound(soundGrassFootstep).setUnlocalizedName("mycel").setTextureName("mycelium");
/*      */   
/*  265 */   public static final Block waterlily = (new BlockLilyPad(111)).setHardness(0.02F).setStepSound(soundGrassFootstep).setUnlocalizedName("waterlily").setTextureName("waterlily");
/*      */   
/*  267 */   public static final Block netherBrick = (new Block(112, Material.netherrack, new BlockConstants())).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherBrick").setCreativeTab(CreativeTabs.tabBlock).setTextureName("nether_brick");
/*      */   
/*  269 */   public static final Block netherFence = (new BlockFence(113, "nether_brick", Material.netherrack)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherFence");
/*  270 */   public static final Block stairsNetherBrick = (new BlockStairs(114, netherBrick, 0)).setUnlocalizedName("stairsNetherBrick");
/*  271 */   public static final Block netherStalk = (new BlockNetherStalk(115)).setUnlocalizedName("netherStalk").setTextureName("nether_wart");
/*      */   
/*  273 */   public static final Block enchantmentTable = (new BlockEnchantmentTable(116, Material.diamond)).setHardness(2.4F).setResistance(20.0F).setUnlocalizedName("enchantmentTable").setTextureName("enchanting_table");
/*  274 */   public static final Block brewingStand = (new BlockBrewingStand(117)).setHardness(0.5F).setLightValue(0.125F).setUnlocalizedName("brewingStand").setTextureName("brewing_stand");
/*  275 */   public static final BlockCauldron cauldron = (BlockCauldron)(new BlockCauldron(118)).setHardness(2.0F).setUnlocalizedName("cauldron").setTextureName("cauldron");
/*  276 */   public static final Block endPortal = (new BlockEndPortal(119, Material.portal)).setHardness(-1.0F).setResistance(6000000.0F);
/*  277 */   public static final Block endPortalFrame = (new BlockEndPortalFrame(120)).setStepSound(soundGlassFootstep).setLightValue(0.125F).setHardness(-1.0F).setUnlocalizedName("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabDecorations).setTextureName("endframe");
/*      */ 
/*      */ 
/*      */   
/*  281 */   public static final Block whiteStone = (new Block(121, Material.stone, new BlockConstants())).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("whiteStone").setCreativeTab(CreativeTabs.tabBlock).setTextureName("end_stone");
/*  282 */   public static final Block dragonEgg = (new BlockDragonEgg(122)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setLightValue(0.125F).setUnlocalizedName("dragonEgg").setTextureName("dragon_egg");
/*  283 */   public static final Block redstoneLampIdle = (new BlockRedstoneLight(123, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone).setTextureName("redstone_lamp_off");
/*  284 */   public static final Block redstoneLampActive = (new BlockRedstoneLight(124, true)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight").setTextureName("redstone_lamp_on");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  290 */   public static final BlockSlabGroup2 woodSingleSlab = (BlockSlabGroup2)(new BlockSlabGroup2(126, Material.wood)).setResistance(5.0F).setStepSound(soundWoodFootstep);
/*  291 */   public static final BlockDoubleSlab woodDoubleSlab = (BlockDoubleSlab)(new BlockDoubleSlab(125, woodSingleSlab)).setResistance(5.0F).setStepSound(soundWoodFootstep);
/*      */   
/*  293 */   public static final Block cocoaPlant = (new BlockCocoa(127)).setHardness(0.2F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("cocoa").setTextureName("cocoa");
/*  294 */   public static final Block stairsSandStone = (new BlockStairs(128, sandStone, 0)).setUnlocalizedName("stairsSandStone");
/*      */   
/*  296 */   public static final Block oreEmerald = (new BlockOre(129, Material.emerald, 3)).setHardness(3.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreEmerald").setTextureName("emerald_ore");
/*  297 */   public static final Block enderChest = (new BlockEnderChest(130)).setHardness(22.5F).setResistance(1000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("enderChest").setLightValue(0.5F);
/*  298 */   public static final BlockTripWireSource tripWireSource = (BlockTripWireSource)(new BlockTripWireSource(131)).setUnlocalizedName("tripWireSource").setTextureName("trip_wire_source");
/*  299 */   public static final Block tripWire = (new BlockTripWire(132)).setUnlocalizedName("tripWire").setTextureName("trip_wire");
/*      */   
/*  301 */   public static final Block blockEmerald = (new BlockOreStorage(133, Material.emerald)).setMaxStackSize(4).setStepSound(soundMetalFootstep).setUnlocalizedName("blockEmerald").setTextureName("emerald_block");
/*  302 */   public static final Block stairsWoodSpruce = (new BlockStairs(134, planks, 1)).setUnlocalizedName("stairsWoodSpruce");
/*  303 */   public static final Block stairsWoodBirch = (new BlockStairs(135, planks, 2)).setUnlocalizedName("stairsWoodBirch");
/*  304 */   public static final Block stairsWoodJungle = (new BlockStairs(136, planks, 3)).setUnlocalizedName("stairsWoodJungle");
/*  305 */   public static final Block commandBlock = (new BlockCommandBlock(137)).setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("commandBlock").setTextureName("command_block");
/*  306 */   public static final BlockBeacon beacon = (BlockBeacon)(new BlockBeacon(138)).setUnlocalizedName("beacon").setLightValue(1.0F).setTextureName("beacon");
/*  307 */   public static final Block cobblestoneWall = (new BlockWall(139, cobblestone)).setUnlocalizedName("cobbleWall");
/*  308 */   public static final Block flowerPot = (new BlockFlowerPot(140)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("flowerPot").setTextureName("flower_pot");
/*  309 */   public static final Block carrot = (new BlockCarrot(141)).setUnlocalizedName("carrots").setTextureName("carrots");
/*  310 */   public static final Block potato = (new BlockPotato(142)).setUnlocalizedName("potatoes").setTextureName("potatoes");
/*  311 */   public static final Block woodenButton = (new BlockButtonWood(143)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("button");
/*  312 */   public static final Block skull = (new BlockSkull(144)).setHardness(1.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("skull").setTextureName("skull");
/*      */   
/*  314 */   public static final BlockAnvil anvil = (BlockAnvil)(new BlockAnvil(145, Material.iron)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilIron");
/*      */   
/*  316 */   public static final Block chestTrapped = (new BlockChest(146, EnumChestType.trapped, Material.wood)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("chestTrap");
/*  317 */   public static final Block pressurePlateGold = (new BlockPressurePlateWeighted(147, "gold_block", Material.gold, 64)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("weightedPlate_light");
/*  318 */   public static final Block pressurePlateIron = (new BlockPressurePlateWeighted(148, "iron_block", Material.iron, 640)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("weightedPlate_heavy");
/*  319 */   public static final BlockComparator redstoneComparatorIdle = (BlockComparator)(new BlockComparator(149, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats().setTextureName("comparator_off");
/*  320 */   public static final BlockComparator redstoneComparatorActive = (BlockComparator)(new BlockComparator(150, true)).setHardness(0.0F).setLightValue(0.625F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats().setTextureName("comparator_on");
/*  321 */   public static final BlockDaylightDetector daylightSensor = (BlockDaylightDetector)(new BlockDaylightDetector(151)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("daylightDetector").setTextureName("daylight_detector");
/*  322 */   public static final Block blockRedstone = (new BlockPoweredOre(152)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockRedstone").setTextureName("redstone_block");
/*      */   
/*  324 */   public static final Block oreNetherQuartz = (new BlockOre(153, Material.quartz, 2)).setHardness(3.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherquartz").setTextureName("quartz_ore");
/*  325 */   public static final BlockHopper hopperBlock = (BlockHopper)(new BlockHopper(154)).setHardness(3.0F).setResistance(8.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("hopper").setTextureName("hopper");
/*  326 */   public static final Block blockNetherQuartz = (new BlockQuartz(155)).setStepSound(soundStoneFootstep).setHardness(0.8F).setUnlocalizedName("quartzBlock").setTextureName("quartz_block");
/*  327 */   public static final Block stairsNetherQuartz = (new BlockStairs(156, blockNetherQuartz, 0)).setUnlocalizedName("stairsQuartz");
/*  328 */   public static final Block railActivator = (new BlockRailPowered(157)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("activatorRail").setTextureName("rail_activator");
/*  329 */   public static final Block dropper = (new BlockDropper(158)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("dropper").setTextureName("dropper");
/*      */   
/*  331 */   public static final Block hay = (new BlockHay(170)).setHardness(0.5F).setStepSound(soundGrassFootstep).setUnlocalizedName("hayBlock").setCreativeTab(CreativeTabs.tabBlock).setTextureName("hay_block");
/*  332 */   public static final Block carpet = (new BlockCarpet(171)).setHardness(0.1F).setStepSound(soundClothFootstep).setUnlocalizedName("woolCarpet").setLightOpacity(0);
/*      */ 
/*      */   
/*  335 */   public static final Block coalBlock = (new Block(173, Material.coal, new BlockConstants())).setHardness(1.2F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("blockCoal").setCreativeTab(CreativeTabs.tabBlock).setTextureName("coal_block");
/*      */ 
/*      */   
/*  338 */   public static final Block stainedClay = (new BlockColored(159, Material.hardened_clay, new BlockConstants())).setHardness(1.8F).setMinHarvestLevel(1).setStepSound(soundStoneFootstep).setUnlocalizedName("clayHardenedStained").setTextureName("hardened_clay_stained");
/*      */   
/*  340 */   public static final Block hardenedClay = (new Block(172, Material.hardened_clay, new BlockConstants())).setHardness(1.8F).setMinHarvestLevel(1).setStepSound(soundStoneFootstep).setUnlocalizedName("clayHardened").setCreativeTab(CreativeTabs.tabBlock).setTextureName("hardened_clay");
/*  341 */   public static final Block fenceAncientMetal = (new BlockPane(199, "ancient_metal_bars", "ancient_metal_bars", Material.ancient_metal, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceAncientMetal");
/*  342 */   public static final Block oreCopper = (new BlockOre(200, Material.copper, 2)).setHardness(2.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreCopper").setTextureName("copper_ore");
/*  343 */   public static final Block oreSilver = (new BlockOre(201, Material.silver, 2)).setHardness(2.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreSilver").setTextureName("silver_ore");
/*  344 */   public static final Block oreMithril = (new BlockOre(202, Material.mithril, 3)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreMithril").setTextureName("mithril_ore");
/*  345 */   public static final Block oreAdamantium = (new BlockOre(203, Material.adamantium, 4)).setHardness(4.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreAdamantium").setTextureName("adamantium_ore");
/*  346 */   public static final Block blockCopper = (new BlockOreStorage(204, Material.copper)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockCopper").setTextureName("copper_block");
/*  347 */   public static final Block blockSilver = (new BlockOreStorage(205, Material.silver)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockSilver").setTextureName("silver_block");
/*  348 */   public static final Block blockMithril = (new BlockOreStorage(206, Material.mithril)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockMithril").setTextureName("mithril_block");
/*  349 */   public static final Block blockAdamantium = (new BlockOreStorage(207, Material.adamantium)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockAdamantium").setTextureName("adamantium_block");
/*  350 */   public static final Block doorCopper = (new BlockDoor(208, Material.copper)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorCopper").disableStats().setTextureName("door_copper");
/*  351 */   public static final Block doorSilver = (new BlockDoor(209, Material.silver)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorSilver").disableStats().setTextureName("door_silver");
/*  352 */   public static final Block doorGold = (new BlockDoor(210, Material.gold)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorGold").disableStats().setTextureName("door_gold");
/*  353 */   public static final Block doorMithril = (new BlockDoor(211, Material.mithril)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorMithril").disableStats().setTextureName("door_mithril");
/*  354 */   public static final Block doorAdamantium = (new BlockDoor(212, Material.adamantium)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorAdamantium").disableStats().setTextureName("door_adamantium");
/*  355 */   public static final Block fenceCopper = (new BlockPane(213, "copper_bars", "copper_bars", Material.copper, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceCopper");
/*  356 */   public static final Block fenceSilver = (new BlockPane(214, "silver_bars", "silver_bars", Material.silver, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceSilver");
/*  357 */   public static final Block fenceGold = (new BlockPane(215, "gold_bars", "gold_bars", Material.gold, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceGold");
/*  358 */   public static final Block fenceMithril = (new BlockPane(216, "mithril_bars", "mithril_bars", Material.mithril, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceMithril");
/*  359 */   public static final Block fenceAdamantium = (new BlockPane(217, "adamantium_bars", "adamantium_bars", Material.adamantium, true)).setHardnessRelativeToWood(0.4F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceAdamantium");
/*  360 */   public static final Block furnaceClayIdle = (new BlockFurnaceClay(218, false)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnaceClay").setCreativeTab(CreativeTabs.tabDecorations);
/*  361 */   public static final Block furnaceClayBurning = (new BlockFurnaceClay(219, true)).setHardness(0.5F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnaceClay");
/*  362 */   public static final Block furnaceSandstoneIdle = (new BlockFurnaceSandstone(220, false)).setHardness(1.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnaceSandstone").setCreativeTab(CreativeTabs.tabDecorations);
/*  363 */   public static final Block furnaceSandstoneBurning = (new BlockFurnaceSandstone(221, true)).setHardness(1.0F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnaceSandstone");
/*  364 */   public static final Block furnaceObsidianIdle = (new BlockFurnaceObsidian(222, false)).setHardness(4.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnaceObsidian").setCreativeTab(CreativeTabs.tabDecorations);
/*  365 */   public static final Block furnaceObsidianBurning = (new BlockFurnaceObsidian(223, true)).setHardness(4.0F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnaceObsidian");
/*  366 */   public static final Block furnaceNetherrackIdle = (new BlockFurnaceNetherrack(224, false)).setHardness(8.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnaceNetherrack").setCreativeTab(CreativeTabs.tabDecorations);
/*  367 */   public static final Block furnaceNetherrackBurning = (new BlockFurnaceNetherrack(225, true)).setHardness(8.0F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnaceNetherrack");
/*      */ 
/*      */   
/*  370 */   public static final BlockSlabGroup3 obsidianSingleSlab = (BlockSlabGroup3)(new BlockSlabGroup3(227, Material.obsidian)).setStepSound(soundStoneFootstep);
/*  371 */   public static final BlockDoubleSlab obsidianDoubleSlab = (BlockDoubleSlab)(new BlockDoubleSlab(226, obsidianSingleSlab)).setStepSound(soundStoneFootstep);
/*      */   
/*  373 */   public static final Block stairsObsidian = (new BlockStairs(228, obsidian, 0)).setUnlocalizedName("stairsObsidian");
/*      */   
/*  375 */   public static final BlockAnvil anvilCopper = (BlockAnvil)(new BlockAnvil(229, Material.copper)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilCopper");
/*  376 */   public static final BlockAnvil anvilSilver = (BlockAnvil)(new BlockAnvil(230, Material.silver)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilSilver");
/*  377 */   public static final BlockAnvil anvilGold = (BlockAnvil)(new BlockAnvil(231, Material.gold)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilGold");
/*  378 */   public static final BlockAnvil anvilMithril = (BlockAnvil)(new BlockAnvil(232, Material.mithril)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilMithril");
/*  379 */   public static final BlockAnvil anvilAdamantium = (BlockAnvil)(new BlockAnvil(233, Material.adamantium)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilAdamantium");
/*      */   
/*  381 */   public static final Block onions = (new BlockOnion(234)).setUnlocalizedName("onions").setTextureName("onions");
/*      */   
/*  383 */   public static final Block cropsDead = (new BlockCropsDead(235, 7)).setUnlocalizedName("crops").setTextureName("wheat");
/*  384 */   public static final Block carrotDead = (new BlockCarrotDead(236)).setUnlocalizedName("carrots").setTextureName("carrots");
/*  385 */   public static final Block potatoDead = (new BlockPotatoDead(237)).setUnlocalizedName("potatoes").setTextureName("potatoes");
/*  386 */   public static final Block onionsDead = (new BlockOnionDead(238)).setUnlocalizedName("onions").setTextureName("onions");
/*      */   
/*  388 */   public static final BlockStrongbox chestCopper = (BlockStrongbox)(new BlockStrongbox(239, Material.copper)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestCopper");
/*  389 */   public static final BlockStrongbox chestSilver = (BlockStrongbox)(new BlockStrongbox(240, Material.silver)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestSilver");
/*  390 */   public static final BlockStrongbox chestGold = (BlockStrongbox)(new BlockStrongbox(241, Material.gold)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestGold");
/*  391 */   public static final BlockStrongbox chestIron = (BlockStrongbox)(new BlockStrongbox(242, Material.iron)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestIron");
/*  392 */   public static final BlockStrongbox chestMithril = (BlockStrongbox)(new BlockStrongbox(243, Material.mithril)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestMithril");
/*  393 */   public static final BlockStrongbox chestAdamantium = (BlockStrongbox)(new BlockStrongbox(244, Material.adamantium)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestAdamantium");
/*      */   
/*  395 */   public static final Block enchantmentTableEmerald = (new BlockEnchantmentTable(245, Material.emerald)).setHardness(2.4F).setResistance(20.0F).setUnlocalizedName("enchantmentTable").setTextureName("emerald_enchanting_table");
/*      */   
/*  397 */   public static final BlockSpark spark = (BlockSpark)(new BlockSpark(246)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("spark").disableStats().setTextureName("invisible");
/*  398 */   public static final BlockRunestone runestoneMithril = (BlockRunestone)(new BlockRunestone(247, Material.mithril)).setHardness(2.4F).setResistance(20.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("runestone").setTextureName("obsidian");
/*      */   
/*  400 */   public static final Block flowerPotMulti = (new BlockFlowerPotMulti(248)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("flowerPot").setTextureName("flower_pot");
/*      */   
/*  402 */   public static final BlockBush bush = (BlockBush)(new BlockBush(249)).setHardness(0.05F).setStepSound(soundGrassFootstep).setUnlocalizedName("bush").setTextureName("bushes");
/*      */   
/*  404 */   public static final Block furnaceHardenedClayIdle = (new BlockFurnaceHardenedClay(250, false)).setHardness(1.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnaceHardenedClay").setCreativeTab(CreativeTabs.tabDecorations);
/*  405 */   public static final Block furnaceHardenedClayBurning = (new BlockFurnaceHardenedClay(251, true)).setHardness(1.0F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnaceHardenedClay");
/*      */   
/*  407 */   public static final Block blockAncientMetal = (new BlockOreStorage(252, Material.ancient_metal)).setStepSound(soundMetalFootstep).setUnlocalizedName("blockAncientMetal").setTextureName("ancient_metal_block");
/*  408 */   public static final Block doorAncientMetal = (new BlockDoor(253, Material.ancient_metal)).setStepSound(soundMetalFootstep).setUnlocalizedName("doorAncientMetal").disableStats().setTextureName("door_ancient_metal");
/*  409 */   public static final BlockAnvil anvilAncientMetal = (BlockAnvil)(new BlockAnvil(254, Material.ancient_metal)).setStepSound(soundAnvilFootstep).setUnlocalizedName("anvilAncientMetal");
/*  410 */   public static final BlockStrongbox chestAncientMetal = (BlockStrongbox)(new BlockStrongbox(255, Material.ancient_metal)).setStepSound(soundMetalFootstep).setUnlocalizedName("chestAncientMetal");
/*      */   
/*  412 */   public static final BlockRunestone runestoneAdamantium = (BlockRunestone)(new BlockRunestone(198, Material.adamantium)).setHardness(2.4F).setResistance(20.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("runestone").setTextureName("obsidian");
/*      */   
/*  414 */   public static final BlockMantleOrCore mantleOrCore = (BlockMantleOrCore)(new BlockMantleOrCore(95, Material.stone, (new BlockConstants()).setAlwaysImmutable())).setBlockUnbreakable().setResistance(6000000.0F).setLightValue(0.9F).setStepSound(soundStoneFootstep).disableStats().setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("mantle").setTextureName("mantle");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int blockID;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float blockHardness;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean blockConstructorCalled = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean enableStats = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean needsRandomTick;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  459 */   protected final double[] minX = new double[2];
/*      */ 
/*      */ 
/*      */   
/*  463 */   protected final double[] minY = new double[2];
/*      */ 
/*      */ 
/*      */   
/*  467 */   protected final double[] minZ = new double[2];
/*      */ 
/*      */ 
/*      */   
/*  471 */   protected final double[] maxX = new double[2];
/*      */ 
/*      */ 
/*      */   
/*  475 */   protected final double[] maxY = new double[2];
/*      */ 
/*      */ 
/*      */   
/*  479 */   protected final double[] maxZ = new double[2];
/*      */ 
/*      */   
/*      */   public StepSound stepSound;
/*      */   
/*      */   public float blockParticleGravity;
/*      */   
/*      */   public final Material blockMaterial;
/*      */   
/*      */   public float slipperiness;
/*      */   
/*      */   private String unlocalizedName;
/*      */   
/*      */   protected Icon blockIcon;
/*      */   
/*      */   private int min_harvest_level;
/*      */   
/*      */   private final boolean has_item_subtypes;
/*      */   
/*      */   private float cushioning;
/*      */ 
/*      */   
/*      */   public Block setMinHarvestLevel(int min_harvest_level) {
/*  502 */     this.min_harvest_level = min_harvest_level;
/*  503 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block modifyMinHarvestLevel(int change) {
/*  508 */     this.min_harvest_level += change;
/*      */     
/*  510 */     if (this.min_harvest_level < 0) {
/*      */       
/*  512 */       Debug.setErrorMessage("modifyMinHarvestLevel: min_harvest_level was set to less than 0");
/*  513 */       Debug.printStackTrace();
/*      */       
/*  515 */       this.min_harvest_level = 0;
/*      */     } 
/*      */     
/*  518 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   private int max_stack_size = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean has_grass_top_icon;
/*      */ 
/*      */   
/*  532 */   private final boolean[] is_solid = new boolean[16];
/*      */   
/*      */   public final boolean is_always_solid;
/*      */   
/*      */   public final boolean is_never_solid;
/*  537 */   private final boolean[] is_standard_form_cube = new boolean[16];
/*      */   
/*      */   public final boolean is_always_standard_form_cube;
/*      */   public final boolean is_never_standard_form_cube;
/*  541 */   private final boolean[] is_solid_and_standard_form_cube = new boolean[16];
/*      */   
/*      */   public final boolean is_always_solid_standard_form_cube;
/*      */   
/*      */   public final boolean is_never_solid_standard_form_cube;
/*      */   
/*      */   public final boolean is_always_opaque_standard_form_cube;
/*      */   
/*      */   public final boolean is_never_opaque_standard_form_cube;
/*      */   
/*      */   public final boolean is_always_solid_opaque_standard_form_cube;
/*      */   
/*      */   public final boolean is_never_solid_opaque_standard_form_cube;
/*      */   public final boolean never_hides_adjacent_faces;
/*      */   public final boolean is_always_legal;
/*      */   public final boolean is_always_immutable;
/*  557 */   private final boolean[] blocks_precipitation = new boolean[16];
/*      */   
/*      */   public final boolean always_blocks_precipitation;
/*      */   public final boolean never_blocks_precipitation;
/*  561 */   private final boolean[] blocks_fluids = new boolean[16];
/*      */ 
/*      */   
/*      */   public final boolean always_blocks_fluids;
/*      */ 
/*      */   
/*      */   public final boolean never_blocks_fluids;
/*      */ 
/*      */   
/*      */   public final boolean connects_with_fence;
/*      */ 
/*      */   
/*      */   public boolean is_being_placed;
/*      */ 
/*      */   
/*      */   public final boolean is_normal_cube;
/*      */ 
/*      */   
/*      */   public final boolean uses_new_sand_physics;
/*      */ 
/*      */   
/*      */   private final int num_item_subtypes;
/*      */ 
/*      */   
/*      */   private final int[] item_subtypes;
/*      */ 
/*      */   
/*      */   private final boolean is_tree_leaves;
/*      */   
/*  590 */   private final boolean[] use_neighbor_brightness = new boolean[96];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block(int par1, Material par2Material, BlockConstants constants) {
/*  597 */     this.stepSound = soundPowderFootstep;
/*  598 */     this.blockParticleGravity = 1.0F;
/*  599 */     this.slipperiness = 0.6F;
/*      */     
/*  601 */     if (blocksList[par1] != null)
/*      */     {
/*  603 */       throw new IllegalArgumentException("Slot " + par1 + " is already occupied by " + blocksList[par1] + " when adding " + this);
/*      */     }
/*      */ 
/*      */     
/*  607 */     this.blockMaterial = par2Material;
/*  608 */     blocksList[par1] = this;
/*  609 */     this.blockID = par1;
/*      */ 
/*      */     
/*  612 */     constants.validate(this);
/*      */ 
/*      */ 
/*      */     
/*  616 */     boolean is_always_solid = true;
/*  617 */     boolean is_never_solid = true;
/*      */     
/*  619 */     boolean is_always_standard_form = true;
/*  620 */     boolean is_never_standard_form = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  628 */     boolean always_blocks_precipitation = true;
/*  629 */     boolean never_blocks_precipitation = true;
/*      */     
/*  631 */     boolean always_blocks_fluids = true;
/*  632 */     boolean never_blocks_fluids = true;
/*      */     int metadata;
/*  634 */     for (metadata = 0; metadata < 16; metadata++) {
/*      */       
/*  636 */       this.is_solid[metadata] = isSolid(this.is_solid, metadata); if (isSolid(this.is_solid, metadata)) {
/*  637 */         is_never_solid = false;
/*      */       } else {
/*  639 */         is_always_solid = false;
/*      */       } 
/*  641 */       this.is_standard_form_cube[metadata] = isStandardFormCube(this.is_standard_form_cube, metadata); if (isStandardFormCube(this.is_standard_form_cube, metadata)) {
/*  642 */         is_never_standard_form = false;
/*      */       } else {
/*  644 */         is_always_standard_form = false;
/*      */       } 
/*  646 */       this.is_solid_and_standard_form_cube[metadata] = (this.is_solid[metadata] && this.is_standard_form_cube[metadata]);
/*      */     } 
/*      */     
/*  649 */     this.is_always_solid = is_always_solid;
/*  650 */     this.is_never_solid = is_never_solid;
/*      */     
/*  652 */     this.is_always_standard_form_cube = is_always_standard_form;
/*  653 */     this.is_never_standard_form_cube = is_never_standard_form;
/*      */     
/*  655 */     this.is_always_solid_standard_form_cube = (is_always_solid && is_always_standard_form);
/*  656 */     this.is_never_solid_standard_form_cube = (is_never_solid || is_never_standard_form);
/*      */     
/*  658 */     for (metadata = 0; metadata < 16; metadata++) {
/*      */       
/*  660 */       this.blocks_precipitation[metadata] = blocksPrecipitation(this.blocks_precipitation, metadata); if (blocksPrecipitation(this.blocks_precipitation, metadata)) {
/*  661 */         never_blocks_precipitation = false;
/*      */       } else {
/*  663 */         always_blocks_precipitation = false;
/*      */       } 
/*  665 */       this.blocks_fluids[metadata] = blocksFluids(this.blocks_fluids, metadata); if (blocksFluids(this.blocks_fluids, metadata)) {
/*  666 */         never_blocks_fluids = false;
/*      */       } else {
/*  668 */         always_blocks_fluids = false;
/*      */       } 
/*      */     } 
/*  671 */     this.always_blocks_precipitation = always_blocks_precipitation;
/*  672 */     this.never_blocks_precipitation = never_blocks_precipitation;
/*      */     
/*  674 */     this.always_blocks_fluids = always_blocks_fluids;
/*  675 */     this.never_blocks_fluids = never_blocks_fluids;
/*      */     
/*  677 */     this.is_tree_leaves = this instanceof BlockLeavesBase;
/*      */     
/*  679 */     if (this instanceof BlockLeavesBase) {
/*      */       
/*  681 */       this.is_always_opaque_standard_form_cube = false;
/*      */       
/*  683 */       this.is_never_opaque_standard_form_cube = true;
/*      */     }
/*  685 */     else if (this instanceof BlockPistonBase) {
/*      */       
/*  687 */       this.is_always_opaque_standard_form_cube = false;
/*  688 */       this.is_never_opaque_standard_form_cube = true;
/*      */     }
/*      */     else {
/*      */       
/*  692 */       this.is_always_opaque_standard_form_cube = (this.is_always_standard_form_cube && !constants.neverHidesAdjacentFaces());
/*  693 */       this.is_never_opaque_standard_form_cube = (this.is_never_standard_form_cube || constants.neverHidesAdjacentFaces());
/*      */     } 
/*      */     
/*  696 */     this.is_always_solid_opaque_standard_form_cube = (is_always_solid && this.is_always_opaque_standard_form_cube);
/*  697 */     this.is_never_solid_opaque_standard_form_cube = (is_never_solid || this.is_never_opaque_standard_form_cube);
/*      */     
/*  699 */     if (constants.connectsWithFence() == null) {
/*      */       
/*  701 */       this.connects_with_fence = (this.is_always_solid_opaque_standard_form_cube && !hasTileEntity());
/*      */     }
/*      */     else {
/*      */       
/*  705 */       this.connects_with_fence = constants.connectsWithFence().booleanValue();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  739 */     this.never_hides_adjacent_faces = constants.neverHidesAdjacentFaces();
/*      */     
/*  741 */     this.is_always_legal = constants.isAlwaysLegal();
/*      */     
/*  743 */     this.is_always_immutable = constants.isAlwaysImmutable();
/*      */     
/*  745 */     if (!this.is_always_standard_form_cube && getClass() == Block.class) {
/*  746 */       Minecraft.setErrorMessage("Block[" + par1 + "] must be standard form cube if it doesn't override Block");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  758 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */     
/*  760 */     opaqueCubeLookup[par1] = isAlwaysOpaqueStandardFormCube();
/*      */ 
/*      */ 
/*      */     
/*  764 */     setLightOpacity(isAlwaysOpaqueStandardFormCube() ? 255 : 0);
/*      */     
/*  766 */     this.item_subtypes = getItemSubtypes();
/*      */     
/*  768 */     this.num_item_subtypes = this.item_subtypes.length;
/*      */ 
/*      */     
/*  771 */     this.has_item_subtypes = (this.num_item_subtypes > 1);
/*      */     
/*  773 */     this.min_harvest_level = this.blockMaterial.min_harvest_level;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  780 */     this.is_normal_cube = (this.is_always_solid_opaque_standard_form_cube && !canProvidePower());
/*  781 */     is_normal_cube_lookup[this.blockID] = this.is_normal_cube;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  788 */     if (constants.usesNewSandPhysics() && !(this instanceof BlockFalling)) {
/*  789 */       Minecraft.setErrorMessage("Block cannot use new sand physics if it is not an instanceof BlockFalling");
/*      */     }
/*  791 */     if (constants.usesNewSandPhysics() && !Minecraft.allow_new_sand_physics) {
/*  792 */       Minecraft.setErrorMessage("BlockConstants uses new sand physics but Minecraft.allow_new_sand_physics is set to false");
/*      */     }
/*  794 */     this.uses_new_sand_physics = (Minecraft.allow_new_sand_physics && constants.usesNewSandPhysics());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeBlock() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void validate() {
/*  821 */     if (this.is_always_solid && this.is_never_solid) {
/*  822 */       Minecraft.setErrorMessage("validate: is_always_solid and is_never_solid");
/*      */     }
/*  824 */     if (this.is_always_standard_form_cube && this.is_never_standard_form_cube) {
/*  825 */       Minecraft.setErrorMessage("validate: is_always_standard_form_cube and is_never_standard_form_cube");
/*      */     }
/*  827 */     if (this.is_always_solid_standard_form_cube && !this.is_always_solid) {
/*  828 */       Minecraft.setErrorMessage("validate: is_always_solid_standard_form_cube and !is_always_solid " + this);
/*      */     }
/*  830 */     if (this.is_always_solid_standard_form_cube && !this.is_always_standard_form_cube) {
/*  831 */       Minecraft.setErrorMessage("validate: is_always_solid_standard_form_cube and !is_always_standard_form_cube " + this);
/*      */     }
/*  833 */     if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_solid) {
/*  834 */       Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_solid");
/*      */     }
/*  836 */     if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_standard_form_cube) {
/*  837 */       Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_standard_form_cube");
/*      */     }
/*  839 */     if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_solid_standard_form_cube) {
/*  840 */       Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_solid_standard_form_cube");
/*      */     }
/*      */ 
/*      */     
/*  844 */     if (this.num_item_subtypes != this.item_subtypes.length) {
/*  845 */       Debug.setErrorMessage("validate: num_item_subtypes=" + this.num_item_subtypes + " vs item_subtypes.length=" + this.item_subtypes.length + " for " + this + " (id=" + this.blockID + ")");
/*      */     }
/*  847 */     if (this.num_item_subtypes != getItemStacks().size()) {
/*  848 */       Debug.setErrorMessage("validate: num_item_subtypes=" + this.num_item_subtypes + " vs " + getItemStacks().size() + " for " + this + " (id=" + this.blockID + ")");
/*      */     }
/*  850 */     if (this.num_item_subtypes < 1) {
/*      */       
/*  852 */       Debug.setErrorMessage("validate: num_item_subtypes==" + this.num_item_subtypes + " for " + this + "?");
/*      */     }
/*      */     else {
/*      */       
/*  856 */       List<ItemStack> list = getItemStacks();
/*      */       
/*  858 */       for (int k = 0; k < this.item_subtypes.length; k++) {
/*      */         
/*  860 */         ItemStack item_stack = list.get(k);
/*      */         
/*  862 */         if (item_stack.getItemSubtype() != this.item_subtypes[k]) {
/*  863 */           Debug.setErrorMessage("validate: subtype value mismatch, " + item_stack.getItemSubtype() + " vs " + this.item_subtypes[k]);
/*      */         }
/*      */       } 
/*      */     } 
/*  867 */     if (this.has_item_subtypes)
/*      */     {
/*  869 */       for (int k = 0; k < this.item_subtypes.length; k++) {
/*      */         
/*  871 */         if (!isValidMetadata(this.item_subtypes[k])) {
/*  872 */           Debug.setErrorMessage("validate: " + this + " has a subtype metadata of " + this.item_subtypes[k] + " but isValidMetadata() returns false for it");
/*      */         }
/*      */       } 
/*      */     }
/*  876 */     if (canBeCarried()) {
/*      */       
/*  878 */       if (getCreativeTabToDisplayOn() == null) {
/*  879 */         Minecraft.setErrorMessage("No creative tab for [" + this.blockID + "] " + this);
/*      */       }
/*  881 */     } else if (this != bedrock && this != mobSpawner && this != dragonEgg && this != endPortalFrame && this != mantleOrCore) {
/*      */       
/*  883 */       if (getCreativeTabToDisplayOn() != null) {
/*  884 */         Minecraft.setErrorMessage("Creative tab for [" + this.blockID + "] " + this);
/*      */       }
/*      */     } 
/*  887 */     for (int metadata = 0; metadata < 16; metadata++) {
/*      */       
/*  889 */       if (isValidMetadata(metadata)) {
/*      */ 
/*      */         
/*  892 */         ItemStack item_stack = createStackedBlock(metadata);
/*      */         
/*  894 */         if (item_stack == null) {
/*      */           
/*  896 */           if (canBeCarried()) {
/*      */             
/*  898 */             Minecraft.setErrorMessage("validate: " + this + " can be carried but createStackedBlock() returns null");
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*  904 */         } else if (item_stack.isBlock() && !item_stack.getItemAsBlock().getBlock().canBeCarried()) {
/*      */           
/*  906 */           Minecraft.setErrorMessage("validate: createStackedBlock() returns a block that cannot be carried for " + this);
/*      */           
/*      */           break;
/*      */         } 
/*  910 */         if (canSilkHarvest(metadata) && !canBeCarried()) {
/*  911 */           Minecraft.setErrorMessage("validate: " + this + " canSilkHarvest but cannot be carried");
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  940 */     boolean can_be_solid = false;
/*  941 */     boolean can_be_not_solid = false;
/*      */     
/*  943 */     for (int j = 0; j < 16; j++) {
/*      */       
/*  945 */       if (isValidMetadata(j))
/*      */       {
/*      */         
/*  948 */         if (isSolid(j)) {
/*  949 */           can_be_solid = true;
/*      */         } else {
/*  951 */           can_be_not_solid = true;
/*      */         }  } 
/*      */     } 
/*  954 */     if (!can_be_solid && !can_be_not_solid) {
/*      */       
/*  956 */       Minecraft.setErrorMessage("validate: " + this + " can neither be solid or not solid?");
/*      */     }
/*  958 */     else if (can_be_solid && can_be_not_solid) {
/*      */       
/*  960 */       if (this.is_always_solid) {
/*  961 */         Minecraft.setErrorMessage("validate: " + this + " can be solid or not solid but is_always_solid==true");
/*  962 */       } else if (this.is_never_solid) {
/*  963 */         Minecraft.setErrorMessage("validate: " + this + " can be solid or not solid but is_never_solid==true");
/*      */       }
/*      */     
/*      */     }
/*  967 */     else if (can_be_solid) {
/*      */       
/*  969 */       if (this.is_never_solid) {
/*  970 */         Minecraft.setErrorMessage("validate: " + this + " can only be solid but is_never_solid==true");
/*  971 */       } else if (!this.is_always_solid) {
/*  972 */         Minecraft.setErrorMessage("validate: " + this + " can only be solid but is_always_solid==false");
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  978 */     else if (this.is_always_solid) {
/*  979 */       Minecraft.setErrorMessage("validate: " + this + " can only be not solid but is_always_solid==true");
/*  980 */     } else if (!this.is_never_solid) {
/*  981 */       Minecraft.setErrorMessage("validate: " + this + " can only be not solid but is_never_solid==false");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1004 */     if (getRenderType() == 1) {
/*      */       
/* 1006 */       if (!neverHidesAdjacentFaces()) {
/* 1007 */         Minecraft.setErrorMessage("validate: " + this + " has render type " + getRenderType() + " but never_hides_adjacent_faces==false");
/*      */       }
/* 1009 */       if (!this.is_never_standard_form_cube) {
/* 1010 */         Minecraft.setErrorMessage("validate: " + this + " has render type " + getRenderType() + " but is_never_standard_form_cube==false");
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1016 */     if (!this.is_always_standard_form_cube && renderAsNormalBlock()) {
/* 1017 */       Minecraft.setErrorMessage("validate: " + this + " renders as normal block but is_always_standard_form_cube==false");
/*      */     }
/* 1019 */     if (is_normal_cube_lookup[this.blockID] != this.is_normal_cube) {
/* 1020 */       Minecraft.setErrorMessage("validate: " + this + " normal cube lookup discrepency");
/*      */     }
/* 1022 */     if (!this.is_always_opaque_standard_form_cube && !this.is_never_opaque_standard_form_cube) {
/* 1023 */       Minecraft.setErrorMessage("validate: " + this + " is neither always opaque standard form cube or never opaque standard form cube");
/*      */     }
/* 1025 */     if (renderAsNormalBlock() != isAlwaysStandardFormCube()) {
/* 1026 */       Debug.println("validate: " + this + ", renderAsNormalBlock=" + renderAsNormalBlock() + " vs isAlwaysStandardFormCube=" + isAlwaysStandardFormCube());
/*      */     }
/* 1028 */     if (lightOpacity[this.blockID] == 0 && !canHaveLightValue[this.blockID]) {
/* 1029 */       Debug.println("validate: " + this + " has light opacity 0 but canBlockGrass==false");
/*      */     }
/* 1031 */     if (lightOpacity[this.blockID] >= 15 && canHaveLightValue[this.blockID]) {
/* 1032 */       Debug.println("validate: " + this + " has light opacity 255 but canBlockGrass==true");
/*      */     }
/* 1034 */     if (lightOpacity[this.blockID] >= 15 && isAlwaysStandardFormCube() && !isAlwaysOpaqueStandardFormCube()) {
/* 1035 */       Debug.println("validate: " + this + " has light opacity >= 15 and is always standard form but is_always_opaque_standard_form_cube==false");
/*      */     }
/* 1037 */     if (lightOpacity[this.blockID] >= 15 && !isAlwaysStandardFormCube() && !useNeighborBrightness[this.blockID]) {
/* 1038 */       Debug.println("validate: " + this + " has light opacity 255 and is not always standard form but useNeighborBrightness==false");
/*      */     }
/* 1040 */     if (lightOpacity[this.blockID] >= 15 && isAlwaysStandardFormCube() && useNeighborBrightness[this.blockID]) {
/* 1041 */       Debug.println("validate: " + this + " has light opacity 255 and is always standard form but useNeighborBrightness==true");
/*      */     }
/* 1043 */     if (canHaveLightValue[this.blockID] && useNeighborBrightness[this.blockID]) {
/* 1044 */       Debug.println("validate: " + this + " canHaveLightValue and useNeighborBrightness are mutually exclusive");
/*      */     }
/* 1046 */     if (useNeighborBrightness[this.blockID] && neverHidesAdjacentFaces()) {
/* 1047 */       Debug.println("validate: " + this + " uses neighbor brightness but neverHidesAdjacentFaces");
/*      */     }
/* 1049 */     boolean uses_neighbor_brightness = false;
/* 1050 */     boolean always_uses_neighbor_brightness = true;
/*      */     
/* 1052 */     for (int i = 0; i < 96; i++) {
/*      */       
/* 1054 */       if (this.use_neighbor_brightness[i]) {
/* 1055 */         uses_neighbor_brightness = true;
/*      */       } else {
/* 1057 */         always_uses_neighbor_brightness = false;
/*      */       } 
/*      */     } 
/* 1060 */     if (useNeighborBrightness[this.blockID] != uses_neighbor_brightness) {
/* 1061 */       Debug.println("validate: " + this + " useNeighborBrightness mismatch");
/*      */     }
/* 1063 */     if (always_uses_neighbor_brightness) {
/* 1064 */       Debug.println("validate: " + this + " always uses neighbor brightness");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTreeLeaves() {
/* 1073 */     return this.is_tree_leaves;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setStepSound(StepSound par1StepSound) {
/* 1081 */     this.stepSound = par1StepSound;
/* 1082 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setLightOpacity(int par1) {
/* 1090 */     if (par1 == 0) {
/* 1091 */       canHaveLightValue[this.blockID] = true;
/*      */     }
/* 1093 */     else if (par1 >= 15) {
/* 1094 */       canHaveLightValue[this.blockID] = false;
/*      */     } 
/* 1096 */     lightOpacity[this.blockID] = par1;
/* 1097 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setLightValue(float par1) {
/* 1106 */     lightValue[this.blockID] = (int)(15.0F * par1);
/* 1107 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setResistance(float par1) {
/* 1116 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isNormalCube(int block_id) {
/* 1126 */     return is_normal_cube_lookup[block_id];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isNormalCube(Block block) {
/* 1132 */     return (block != null && block.is_normal_cube);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean renderAsNormalBlock() {
/* 1148 */     return isAlwaysStandardFormCube();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isWoodenPortal() {
/* 1172 */     return (this == doorWood || this == trapdoor || this == fenceGate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePathedInto(World world, int x, int y, int z, Entity entity, boolean allow_closed_wooden_portals) {
/* 1180 */     if (isNeverSolid()) {
/* 1181 */       return true;
/*      */     }
/* 1183 */     if (isPortal()) {
/*      */       
/* 1185 */       if (isOpenPortal(world, x, y, z)) {
/* 1186 */         return true;
/*      */       }
/* 1188 */       if (allow_closed_wooden_portals && isWoodenPortal()) {
/* 1189 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1194 */     if (isAlwaysSolid()) {
/* 1195 */       return false;
/*      */     }
/* 1197 */     return !isSolid(world, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderType() {
/* 1205 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setHardness(float par1) {
/* 1213 */     this.blockHardness = par1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1221 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setHardnessRelativeToWood(float wood_block_hardness) {
/*      */     Material material;
/* 1228 */     if (this instanceof BlockAnvil) {
/* 1229 */       material = ((BlockAnvil)this).metal_type;
/*      */     } else {
/* 1231 */       material = this.blockMaterial;
/*      */     } 
/* 1233 */     this.blockHardness = wood_block_hardness * material.durability;
/* 1234 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setBlockUnbreakable() {
/* 1255 */     setHardness(-1.0F);
/* 1256 */     setMinHarvestLevel(100);
/* 1257 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBlockHardness(int metadata) {
/* 1271 */     return this.blockHardness;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setTickRandomly(boolean par1) {
/* 1279 */     this.needsRandomTick = par1;
/* 1280 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean getTickRandomly() {
/* 1290 */     return this.needsRandomTick;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTileEntity() {
/* 1296 */     return this instanceof ITileEntityProvider;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setBlockBoundsForCurrentThread(double par1, double par2, double par3, double par4, double par5, double par6) {
/* 1323 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 1325 */     this.minX[index] = par1;
/* 1326 */     this.minY[index] = par2;
/* 1327 */     this.minZ[index] = par3;
/* 1328 */     this.maxX[index] = par4;
/* 1329 */     this.maxY[index] = par5;
/* 1330 */     this.maxZ[index] = par6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setBlockBoundsForAllThreads(double par1, double par2, double par3, double par4, double par5, double par6) {
/* 1344 */     for (int i = 0; i < this.minX.length; i++) {
/*      */       
/* 1346 */       this.minX[i] = par1;
/* 1347 */       this.minY[i] = par2;
/* 1348 */       this.minZ[i] = par3;
/* 1349 */       this.maxX[i] = par4;
/* 1350 */       this.maxY[i] = par5;
/* 1351 */       this.maxZ[i] = par6;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setBlockBounds(double par1, double par2, double par3, double par4, double par5, double par6, boolean for_all_threads) {
/* 1358 */     if (for_all_threads) {
/* 1359 */       setBlockBoundsForAllThreads(par1, par2, par3, par4, par5, par6);
/*      */     } else {
/* 1361 */       setBlockBoundsForCurrentThread(par1, par2, par3, par4, par5, par6);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void setBlockBoundsForCurrentThread(AxisAlignedBB bb) {
/* 1366 */     setBlockBoundsForCurrentThread(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 1374 */     return par1IBlockAccess.getBrightness(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 1382 */     return par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 1394 */     return isOpaqueStandardFormCube(block_access, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 1426 */     if (isAlwaysStandardFormCube())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1446 */       return !doesBlockHideAdjacentSide(par1IBlockAccess, par2, par3, par4, this, par5);
/*      */     }
/*      */     
/* 1449 */     int index = Minecraft.getThreadIndex();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1455 */     if (par5 == 0) {
/*      */       
/* 1457 */       if (this.minY[index] > 0.0D) {
/* 1458 */         return true;
/*      */       }
/* 1460 */     } else if (par5 == 1) {
/*      */       
/* 1462 */       if (this.maxY[index] < 1.0D) {
/* 1463 */         return true;
/*      */       }
/* 1465 */     } else if (par5 == 2) {
/*      */       
/* 1467 */       if (this.minZ[index] > 0.0D) {
/* 1468 */         return true;
/*      */       }
/* 1470 */     } else if (par5 == 3) {
/*      */       
/* 1472 */       if (this.maxZ[index] < 1.0D) {
/* 1473 */         return true;
/*      */       }
/* 1475 */     } else if (par5 == 4) {
/*      */       
/* 1477 */       if (this.minX[index] > 0.0D) {
/* 1478 */         return true;
/*      */       }
/* 1480 */     } else if (par5 == 5) {
/*      */       
/* 1482 */       if (this.maxX[index] < 1.0D) {
/* 1483 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1501 */     return !doesBlockHideAdjacentSide(par1IBlockAccess, par2, par3, par4, this, par5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 1520 */     return getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getIcon(int par1, int par2) {
/* 1528 */     return this.blockIcon;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Icon getBlockTextureFromSide(int par1) {
/* 1536 */     return getIcon(par1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCollideWithEntity(Entity entity) {
/* 1542 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean useFullBlockForCollisions(Entity entity) {
/* 1548 */     if (entity instanceof EntityArachnid || entity instanceof EntityOoze) {
/* 1549 */       return false;
/*      */     }
/* 1551 */     if (entity instanceof EntityBat) {
/* 1552 */       return false;
/*      */     }
/* 1554 */     if (entity instanceof EntityPudding) {
/* 1555 */       return false;
/*      */     }
/* 1557 */     return (entity instanceof EntityLiving) ? ((this instanceof BlockFence || this instanceof BlockFenceGate || this instanceof BlockWall || this instanceof BlockPane)) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
/* 1567 */     if (isAlwaysStandardFormCube())
/*      */     {
/* 1569 */       return getStandardFormBoundingBoxFromPool(par2, par3, par4);
/*      */     }
/* 1571 */     setBlockBoundsBasedOnStateAndNeighbors(par1World, par2, par3, par4);
/*      */     
/* 1573 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 1575 */     return AxisAlignedBB.getAABBPool().getAABB(par2 + this.minX[index], par3 + this.minY[index], par4 + this.minZ[index], par2 + this.maxX[index], par3 + this.maxY[index], par4 + this.maxZ[index]);
/*      */   }
/*      */ 
/*      */   
/*      */   public static final AxisAlignedBB getBoundingBoxFromPool(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
/* 1580 */     return AxisAlignedBB.getBoundingBoxFromPool(min_x, min_y, min_z, max_x, max_y, max_z);
/*      */   }
/*      */ 
/*      */   
/*      */   public static final AxisAlignedBB getBoundingBoxFromPool(int x, int y, int z, double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
/* 1585 */     return AxisAlignedBB.getBoundingBoxFromPool(x, y, z, min_x, min_y, min_z, max_x, max_y, max_z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final void addIntersectingBoundsToList(AxisAlignedBB bb, List<AxisAlignedBB> list, AxisAlignedBB mask) {
/* 1591 */     if (bb != null && mask.intersectsWith(bb)) {
/* 1592 */       list.add(bb);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static final AxisAlignedBB getStandardFormBoundingBoxFromPool() {
/* 1598 */     return AxisAlignedBB.getBoundingBoxFromPool(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final AxisAlignedBB getStandardFormBoundingBoxFromPool(int x, int y, int z) {
/* 1604 */     return AxisAlignedBB.getBoundingBoxFromPool(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public static final void addIntersectingStandardFormBoundsToList(int x, int y, int z, AxisAlignedBB mask, List list) {
/* 1609 */     addIntersectingBoundsToList(getStandardFormBoundingBoxFromPool(x, y, z), list, mask);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCollidingBoundsToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
/* 1660 */     Object collision_bounds = getCollisionBounds(world, x, y, z, entity);
/*      */     
/* 1662 */     if (collision_bounds == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1667 */     if (collision_bounds instanceof AxisAlignedBB) {
/*      */       
/* 1669 */       addIntersectingBoundsToList((AxisAlignedBB)collision_bounds, list, mask);
/*      */     }
/*      */     else {
/*      */       
/* 1673 */       AxisAlignedBB[] array = (AxisAlignedBB[])collision_bounds;
/*      */       
/* 1675 */       for (int i = 0; i < array.length; i++) {
/*      */         
/* 1677 */         if (array[i] != null)
/*      */         {
/*      */           
/* 1680 */           addIntersectingBoundsToList(array[i].translateCopy(x, y, z), list, mask);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final AxisAlignedBB getBoundsFromPool(int x, int y, int z) {
/* 1721 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 1723 */     return AxisAlignedBB.getAABBPool().getAABB(x + this.minX[index], y + this.minY[index], z + this.minZ[index], x + this.maxX[index], y + this.maxY[index], z + this.maxZ[index]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/* 1757 */     if (isNeverSolid()) {
/* 1758 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1769 */     if (isAlwaysStandardFormCube()) {
/* 1770 */       return AxisAlignedBB.getAABBPool().getAABB(x, y, z, (x + 1), (y + 1), (z + 1));
/*      */     }
/* 1772 */     setBlockBoundsBasedOnStateAndNeighbors(world, x, y, z);
/*      */     
/* 1774 */     return getBoundsFromPool(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getRenderBounds(World world, int x, int y, int z, Entity entity) {
/* 1779 */     if (isAlwaysStandardFormCube()) {
/* 1780 */       return AxisAlignedBB.getAABBPool().getAABB(x, y, z, (x + 1), (y + 1), (z + 1));
/*      */     }
/* 1782 */     setBlockBoundsBasedOnStateAndNeighbors(world, x, y, z);
/*      */     
/* 1784 */     return getBoundsFromPool(x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final AxisAlignedBB getCollisionBoundsCombined(World world, int x, int y, int z, Entity entity, boolean as_non_local) {
/* 1818 */     Object collision_bounds = getCollisionBounds(world, x, y, z, entity);
/*      */     
/* 1820 */     if (collision_bounds == null) {
/* 1821 */       return null;
/*      */     }
/* 1823 */     if (collision_bounds instanceof AxisAlignedBB) {
/* 1824 */       return as_non_local ? (AxisAlignedBB)collision_bounds : ((AxisAlignedBB)collision_bounds).translate(-x, -y, -z);
/*      */     }
/* 1826 */     AxisAlignedBB[] array = (AxisAlignedBB[])collision_bounds;
/* 1827 */     AxisAlignedBB combined = null;
/*      */     
/* 1829 */     for (int i = 0; i < array.length; i++) {
/*      */       
/* 1831 */       AxisAlignedBB bb = array[i];
/*      */       
/* 1833 */       if (bb != null)
/*      */       {
/*      */         
/* 1836 */         if (combined == null) {
/* 1837 */           combined = bb.copy();
/*      */         } else {
/* 1839 */           combined.include(bb);
/*      */         }  } 
/*      */     } 
/* 1842 */     return (combined == null) ? null : (as_non_local ? combined.translate(x, y, z) : combined);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final AxisAlignedBB getBoundsFromPool() {
/* 1848 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 1850 */     return AxisAlignedBB.getAABBPool().getAABB(this.minX[index], this.minY[index], this.minZ[index], this.maxX[index], this.maxY[index], this.maxZ[index]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isOpaqueStandardFormCube(int metadata) {
/* 1868 */     Minecraft.setErrorMessage("isOpaqueStandardFormCube: This is not supposed to be based on metadata yet " + this);
/*      */ 
/*      */ 
/*      */     
/* 1872 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysOpaqueStandardFormCube() {
/* 1877 */     return this.is_always_opaque_standard_form_cube;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isNeverOpaqueStandardFormCube() {
/* 1882 */     return this.is_never_opaque_standard_form_cube;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysStandardFormCube() {
/* 1894 */     return this.is_always_standard_form_cube;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysSolid() {
/* 1906 */     return this.is_always_solid;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isNeverSolid() {
/* 1911 */     return this.is_never_solid;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isNeverStandardFormCube() {
/* 1916 */     return this.is_never_standard_form_cube;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isNeverSolidStandardFormCube() {
/* 1921 */     return this.is_never_solid_standard_form_cube;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysSolidStandardFormCube() {
/* 1928 */     return this.is_always_solid_standard_form_cube;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isSolidStandardFormCube(int metadata) {
/* 1934 */     if (isAlwaysSolidStandardFormCube()) {
/* 1935 */       return true;
/*      */     }
/* 1937 */     if (isNeverSolidStandardFormCube()) {
/* 1938 */       return false;
/*      */     }
/* 1940 */     return (isSolid(metadata) && isStandardFormCube(metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isAlwaysSolidStandardFormCube(Block block) {
/* 1946 */     return (block != null && block.isAlwaysSolidStandardFormCube());
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysSolidOpaqueStandardFormCube() {
/* 1951 */     return this.is_always_solid_opaque_standard_form_cube;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isNeverSolidOpaqueStandardFormCube() {
/* 1956 */     return this.is_never_solid_opaque_standard_form_cube;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isSolidOpaqueStandardFormCube(int metadata) {
/* 1961 */     Minecraft.setErrorMessage("isSolidOpaqueStandardFormCube: This is not supposed to be based on metadata yet " + this);
/*      */ 
/*      */     
/* 1964 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean neverHidesAdjacentFaces() {
/* 1981 */     return this.never_hides_adjacent_faces;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFaceFlatAndSolid(int metadata, EnumFace face) {
/* 1987 */     return (isAlwaysSolidStandardFormCube() || isSolidStandardFormCube(metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCollideCheck(int par1, boolean par2) {
/* 2009 */     return isCollidable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCollidable() {
/* 2017 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 2023 */     if (world.isRemote) {
/* 2024 */       Minecraft.setErrorMessage("onNotLegal: not meant to be called on client");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2029 */     dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setWasNotLegal());
/* 2030 */     world.setBlockToAir(x, y, z);
/*      */     
/* 2032 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean checkIfNotLegal(World world, int x, int y, int z) {
/* 2038 */     if (isAlwaysLegal()) {
/* 2039 */       return false;
/*      */     }
/* 2041 */     int metadata = world.getBlockMetadata(x, y, z);
/*      */     
/* 2043 */     if (isLegalAt(world, x, y, z, metadata)) {
/* 2044 */       return false;
/*      */     }
/* 2046 */     return onNotLegal(world, x, y, z, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 2056 */     return checkIfNotLegal(world, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/* 2082 */     if (world.isRemote) {
/* 2083 */       Minecraft.setErrorMessage("onNeighborBlockChange: called on client?");
/*      */     }
/* 2085 */     if (Minecraft.allow_new_sand_physics) {
/*      */       
/* 2087 */       Block block_above = world.getBlock(x, y + 1, z);
/*      */       
/* 2089 */       if (block_above != null && block_above.usesNewSandPhysics()) {
/* 2090 */         block_above.checkIfNotLegal(world, x, y + 1, z);
/*      */       }
/*      */     } 
/* 2093 */     return checkIfNotLegal(world, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int tickRate(World par1World) {
/* 2101 */     return 10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onTrampledBy(World world, int x, int y, int z, Entity entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDislodgedOrCrushedByFallingBlock(int metadata, Block falling_block, int falling_block_metadata) {
/* 2164 */     return (!isSolid(metadata) || falling_block.canReplaceBlock(falling_block_metadata, this, metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int dropBlockAsItself(BlockBreakInfo info) {
/* 2170 */     if (info.block != this) {
/* 2171 */       Minecraft.setErrorMessage("dropBlockAsItself: info.block!=this");
/*      */     }
/* 2173 */     if (!info.block.canBeCarried()) {
/* 2174 */       Minecraft.setErrorMessage("dropBlockAsItself: " + this + " cannot be carried");
/*      */     }
/*      */     
/* 2177 */     return dropBlockAsEntityItem(info, createStackedBlock(info.getMetadata()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int dropBlockAsEntityItem(Block block, BlockBreakInfo info) {
/* 2191 */     return info.wasSilkHarvested() ? block.dropBlockAsItself(info) : block.dropBlockAsEntityItem(info);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 2197 */     if (info.block != this) {
/* 2198 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/* 2200 */     if (info.wasSilkHarvested()) {
/*      */       
/* 2202 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: This function is not meant to be used for silk harvested blocks " + this);
/* 2203 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 2206 */     if (info.wasCrushed()) {
/* 2207 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2211 */     if (info.wasExploded()) {
/*      */       
/* 2213 */       if (this == brick)
/* 2214 */         return dropBlockAsEntityItem(info, Item.brick.itemID, 0, 1, 1.5F); 
/* 2215 */       if (this == cobblestone || this == cobblestoneMossy)
/* 2216 */         return dropBlockAsEntityItem(info, gravel); 
/* 2217 */       if (this == blockLapis)
/* 2218 */         return dropBlockAsEntityItem(info, Item.dyePowder.itemID, 4, 9, 0.5F); 
/* 2219 */       if (this.blockMaterial == Material.cloth)
/* 2220 */         return dropBlockAsEntityItem(info, Item.silk); 
/* 2221 */       if (this.blockMaterial == Material.wood)
/* 2222 */         return dropBlockAsEntityItem(info, Item.stick); 
/* 2223 */       if (this.blockMaterial == Material.hardened_clay)
/* 2224 */         return 0; 
/* 2225 */       if (this.blockMaterial == Material.stone)
/* 2226 */         return dropBlockAsEntityItem(info, cobblestone); 
/* 2227 */       if (this.blockMaterial == Material.netherrack) {
/* 2228 */         return 0;
/*      */       }
/*      */     } 
/* 2231 */     if (this == coalBlock) {
/* 2232 */       return dropBlockAsEntityItem(info, Item.coal.itemID, 0, 9, info.wasExploded() ? 0.5F : 1.0F);
/*      */     }
/*      */     
/* 2235 */     return dropBlockAsEntityItem(info, createStackedBlock(info.getMetadata()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, Block block) {
/* 2241 */     if (info.block != this)
/*      */     {
/* 2243 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/*      */ 
/*      */     
/* 2247 */     if (block == null) {
/* 2248 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2252 */     return dropBlockAsEntityItem(info, block.blockID, 0, 1, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, Item item) {
/* 2258 */     if (info.block != this) {
/* 2259 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/* 2261 */     if (item == null) {
/* 2262 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2266 */     return dropBlockAsEntityItem(info, item.itemID, 0, 1, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped) {
/* 2272 */     if (info.block != this) {
/* 2273 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/* 2275 */     if (id_dropped < 1) {
/* 2276 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2280 */     return dropBlockAsEntityItem(info, id_dropped, 0, 1, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, ItemStack item_stack) {
/* 2286 */     if (info.block != this) {
/* 2287 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/* 2289 */     if (item_stack == null)
/*      */     {
/*      */       
/* 2292 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2297 */     if (item_stack.isItemDamaged() && info.damage != item_stack.getItemDamage()) {
/*      */       
/* 2299 */       if (info.damage != 0) {
/* 2300 */         Minecraft.setErrorMessage("dropBlockAsEntityItem: info.damage was already set to " + info.damage + " (vs " + item_stack.getItemDamage() + ")");
/*      */       }
/* 2302 */       info.damage = item_stack.getItemDamage();
/*      */     } 
/*      */     
/* 2305 */     return dropBlockAsEntityItem(info, item_stack.itemID, item_stack.getItemSubtype(), item_stack.stackSize, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped, int subtype) {
/* 2339 */     if (info.block != this) {
/* 2340 */       Minecraft.setErrorMessage("dropBlockAsItself: info.block!=this");
/*      */     }
/* 2342 */     return dropBlockAsEntityItem(info, id_dropped, subtype, 1, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped, int subtype, int quantity, float chance) {
/* 2347 */     if (info.world.isRemote) {
/*      */       
/* 2349 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: not meant to be called on client " + info.x + "," + info.y + "," + info.z);
/* 2350 */       return 0;
/*      */     } 
/*      */     
/* 2353 */     if (info.block != this) {
/* 2354 */       Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2359 */     if (id_dropped < 1 || quantity < 1 || chance <= 0.0F) {
/* 2360 */       return 0;
/*      */     }
/* 2362 */     if (info.wasExploded())
/*      */     {
/* 2364 */       if (this.blockMaterial == Material.snow || this.blockMaterial == Material.craftedSnow || this.blockMaterial == Material.glass) {
/* 2365 */         chance *= 0.4F;
/* 2366 */       } else if (this.blockMaterial == Material.plants || this.blockMaterial == Material.cactus || this.blockMaterial == Material.vine) {
/* 2367 */         chance = 0.0F;
/* 2368 */       } else if (this.blockMaterial == Material.cake) {
/* 2369 */         chance = 0.0F;
/*      */       } 
/*      */     }
/* 2372 */     if (chance <= 0.0F) {
/* 2373 */       return 0;
/*      */     }
/* 2375 */     int damage = info.damage;
/*      */     
/* 2377 */     int num_drops = 0;
/*      */     
/* 2379 */     for (int i = 0; i < quantity; i++) {
/*      */       
/* 2381 */       if (info.world.rand.nextFloat() < chance) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2387 */         ItemStack item_stack = new ItemStack(id_dropped, 1, subtype);
/*      */ 
/*      */         
/* 2390 */         if (item_stack.isBlock() && !item_stack.getItemAsBlock().getBlock().canBeCarried()) {
/*      */           
/* 2392 */           ItemStack substitute = item_stack.getItemAsBlock().getBlock().createStackedBlock(subtype);
/*      */           
/* 2394 */           Minecraft.setErrorMessage("dropBlockAsEntityItem: " + item_stack + " can not be carried, substituting with " + substitute);
/*      */           
/* 2396 */           if (substitute == null) {
/*      */             
/* 2398 */             Minecraft.setErrorMessage("dropBlockAsEntityItem: createStackedBlock returned null for " + item_stack);
/* 2399 */             return num_drops;
/*      */           } 
/* 2401 */           if (substitute.isBlock() && !substitute.getItemAsBlock().getBlock().canBeCarried()) {
/*      */             
/* 2403 */             Minecraft.setErrorMessage("dropBlockAsEntityItem: substitute " + substitute + " can not be carried either. Aborting");
/* 2404 */             return num_drops;
/*      */           } 
/*      */           
/* 2407 */           item_stack = substitute;
/*      */         } 
/*      */         
/* 2410 */         if (id_dropped > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2420 */           EntityItem entity_item = dropBlockAsItem_do(info, item_stack.copy());
/*      */           
/* 2422 */           if (damage != 0) {
/* 2423 */             entity_item.getEntityItem().setItemDamage(damage);
/*      */           }
/* 2425 */           if (quantity == 1 && chance <= 1.0F && info.tile_entity != null && info.tile_entity.getCustomInvName() != null) {
/* 2426 */             entity_item.getEntityItem().setItemName(info.tile_entity.getCustomInvName());
/*      */           }
/* 2428 */           if (chance > 1.0F && info.world.rand.nextFloat() < chance - 1.0F) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2438 */             entity_item = dropBlockAsItem_do(info, item_stack.copy());
/*      */             
/* 2440 */             if (damage != 0) {
/* 2441 */               entity_item.getEntityItem().setItemDamage(damage);
/*      */             }
/*      */           } 
/* 2444 */           num_drops++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2449 */     if (canDropExperienceOrbs()) {
/*      */       
/* 2451 */       int xp_reward_per_unit = Item.getItem(id_dropped).getExperienceReward(subtype);
/*      */       
/* 2453 */       if (xp_reward_per_unit > 0) {
/* 2454 */         dropXpOnBlockBreak(info.world, info.x, info.y, info.z, xp_reward_per_unit * num_drops);
/*      */       }
/*      */     } 
/* 2457 */     return num_drops;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canDropExperienceOrbs() {
/* 2462 */     if (this == blockLapis) {
/* 2463 */       return false;
/*      */     }
/* 2465 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private EntityItem dropBlockAsItem_do(BlockBreakInfo info, ItemStack item_stack) {
/* 2487 */     World world = info.world;
/*      */     
/* 2489 */     if (world.isRemote) {
/*      */       
/* 2491 */       Minecraft.setErrorMessage("dropBlockAsItem_do: not meant to be called on client");
/* 2492 */       return null;
/*      */     } 
/*      */     
/* 2495 */     if (world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2505 */       EntityItem entity_item = info.createEntityItem(item_stack);
/*      */       
/* 2507 */       entity_item.delayBeforeCanPickup = 10;
/*      */ 
/*      */ 
/*      */       
/* 2511 */       EntityPlayer player = info.getResponsiblePlayer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2523 */       entity_item.motionX = entity_item.motionY = entity_item.motionZ = 0.0D;
/*      */       
/* 2525 */       Vec3 player_center = player.getCenterPoint();
/*      */       
/* 2527 */       entity_item.setPosition(player_center.xCoord, player_center.yCoord, player_center.zCoord);
/*      */       
/* 2529 */       entity_item.delayBeforeCanPickup = 0;
/*      */       
/* 2531 */       if (info.wasWindfall())
/*      */       {
/* 2533 */         entity_item.motionX = entity_item.motionY = entity_item.motionZ = 0.0D;
/*      */       }
/*      */       
/* 2536 */       if (info.wasExploded()) {
/* 2537 */         world.addToSpawnPendingList(entity_item, world.getTotalWorldTime() + 1L);
/*      */       } else {
/* 2539 */         world.spawnEntityInWorld(entity_item);
/*      */       } 
/* 2541 */       if (info.wasExploded()) {
/* 2542 */         entity_item.applyExplosionMotion(info.explosion);
/*      */       }
/* 2544 */       return entity_item;
/*      */     } 
/*      */     
/* 2547 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
/* 2555 */     if (!par1World.isRemote)
/*      */     {
/* 2557 */       while (par5 > 0) {
/*      */         
/* 2559 */         int var6 = EntityXPOrb.getXPSplit(par5);
/* 2560 */         par5 -= var6;
/* 2561 */         par1World.spawnEntityInWorld(new EntityXPOrb(par1World, par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, var6));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getExplosionResistance(Explosion explosion) {
/* 2581 */     return this.blockHardness;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision tryRaycastVsBlock(Raycast raycast, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
/* 2719 */     World par1World = raycast.getWorld();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2746 */     Object object = getCollisionBounds(par1World, par2, par3, par4, null);
/*      */     
/* 2748 */     if (object instanceof AxisAlignedBB[]) {
/*      */       
/* 2750 */       AxisAlignedBB[] multiple_bounds = (AxisAlignedBB[])object;
/*      */       
/* 2752 */       RaycastCollision[] rc = new RaycastCollision[multiple_bounds.length];
/*      */       
/* 2754 */       for (int i = 0; i < rc.length; i++) {
/*      */         
/* 2756 */         if (multiple_bounds[i] != null)
/*      */         {
/*      */           
/* 2759 */           rc[i] = tryRaycastVsBlock(raycast, par2, par3, par4, par5Vec3, par6Vec3, multiple_bounds[i]);
/*      */         }
/*      */       } 
/* 2762 */       return Raycast.getShortestRaycastCollision(rc);
/*      */     } 
/*      */ 
/*      */     
/* 2766 */     setBlockBoundsBasedOnStateAndNeighbors(par1World, par2, par3, par4);
/* 2767 */     return tryRaycastVsBlock(raycast, par2, par3, par4, par5Vec3, par6Vec3, getBoundsFromPool());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2997 */   private static AxisAlignedBB standard_form_bounding_box = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */ 
/*      */ 
/*      */   
/*      */   public final RaycastCollision tryRaycastVsStandardFormBounds(Raycast raycast, int x, int y, int z, Vec3 origin, Vec3 limit) {
/* 3002 */     return tryRaycastVsBlock(raycast, x, y, z, origin, limit, standard_form_bounding_box);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final RaycastCollision tryRaycastVsBlock(Raycast raycast, int x, int y, int z, Vec3 origin, Vec3 limit, AxisAlignedBB bounds) {
/* 3008 */     World world = raycast.getWorld();
/*      */     
/* 3010 */     if (raycast.ignoreBlock(this, world, x, y, z)) {
/* 3011 */       return null;
/*      */     }
/* 3013 */     origin = origin.addVector(-x, -y, -z);
/* 3014 */     limit = limit.addVector(-x, -y, -z);
/*      */     
/* 3016 */     AABBIntercept intercept = bounds.calculateIntercept(world, origin, limit);
/*      */     
/* 3018 */     return (intercept == null) ? null : new RaycastCollision(raycast, x, y, z, intercept.face_hit, intercept.position_hit.translate(x, y, z));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPortal() {
/* 3041 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOpenPortal(World world, int x, int y, int z) {
/* 3047 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isVecInsideYZBounds(Vec3 par1Vec3) {
/* 3366 */     if (isAlwaysStandardFormCube()) {
/* 3367 */       return (par1Vec3.yCoord >= 0.0D && par1Vec3.yCoord <= 1.0D && par1Vec3.zCoord >= 0.0D && par1Vec3.zCoord <= 1.0D);
/*      */     }
/* 3369 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 3371 */     return (par1Vec3.yCoord >= this.minY[index] && par1Vec3.yCoord <= this.maxY[index] && par1Vec3.zCoord >= this.minZ[index] && par1Vec3.zCoord <= this.maxZ[index]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isVecInsideXZBounds(Vec3 par1Vec3) {
/* 3386 */     if (isAlwaysStandardFormCube()) {
/* 3387 */       return (par1Vec3.xCoord >= 0.0D && par1Vec3.xCoord <= 1.0D && par1Vec3.zCoord >= 0.0D && par1Vec3.zCoord <= 1.0D);
/*      */     }
/* 3389 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 3391 */     return (par1Vec3.xCoord >= this.minX[index] && par1Vec3.xCoord <= this.maxX[index] && par1Vec3.zCoord >= this.minZ[index] && par1Vec3.zCoord <= this.maxZ[index]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isVecInsideXYBounds(Vec3 par1Vec3) {
/* 3406 */     if (isAlwaysStandardFormCube()) {
/* 3407 */       return (par1Vec3.xCoord >= 0.0D && par1Vec3.xCoord <= 1.0D && par1Vec3.yCoord >= 0.0D && par1Vec3.yCoord <= 1.0D);
/*      */     }
/* 3409 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 3411 */     return (par1Vec3.xCoord >= this.minX[index] && par1Vec3.xCoord <= this.maxX[index] && par1Vec3.yCoord >= this.minY[index] && par1Vec3.yCoord <= this.maxY[index]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
/* 3426 */     Block block_above = blocksList[par1World.getBlockId(par2, par3 + 1, par4)];
/*      */     
/* 3428 */     if (block_above instanceof BlockUnderminable) {
/* 3429 */       ((BlockUnderminable)block_above).scheduleUndermine(par1World, par2, par3 + 1, par4);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderBlockPass() {
/* 3437 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isTopFlatAndSolid(int metadata) {
/* 3473 */     return isFaceFlatAndSolid(metadata, EnumFace.TOP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
/* 3491 */     if (block_below == null) {
/* 3492 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3509 */     if (block_below == tilledField || block_below instanceof BlockWall) {
/* 3510 */       return isLegalOn(metadata, block_below, block_below_metadata);
/*      */     }
/* 3512 */     if (block_below == snow && BlockSnow.getDepth(block_below_metadata) == BlockSnow.getMaxDepth()) {
/* 3513 */       return isLegalOn(metadata, block_below, block_below_metadata);
/*      */     }
/* 3515 */     return (block_below_bounds_max_y == 1.0D && block_below.isSolid(block_below_metadata) && isLegalOn(metadata, block_below, block_below_metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isAlwaysLegal() {
/* 3536 */     return this.is_always_legal;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean usesNewSandPhysics() {
/* 3541 */     return this.uses_new_sand_physics;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 3547 */     if (isAlwaysLegal()) {
/* 3548 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3554 */     return isLegalOn(metadata, world.getBlock(x, y - 1, z), world.getBlockMetadata(x, y - 1, z));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 3560 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/* 3566 */     Block existing_block = world.getBlock(x, y, z);
/*      */     
/* 3568 */     if (existing_block != null && !canReplaceBlock(metadata, existing_block, world.getBlockMetadata(x, y, z))) {
/* 3569 */       return false;
/*      */     }
/* 3571 */     return isLegalAt(world, x, y, z, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
/* 3577 */     return (world.isAirBlock(x, y, z) && canBePlacedAt(world, x, y, z, metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 3583 */     if (item_stack.getHasSubtypes()) {
/* 3584 */       return item_stack.getItemSubtype();
/*      */     }
/* 3586 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAlwaysReplaceable() {
/* 3591 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 3610 */     return (other_block != null && other_block != this && (isLiquid() || isAlwaysReplaceable() || getBlockHardness(metadata) == 0.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canReplaceBlock(int metadata, Block existing_block, int existing_block_metadata) {
/* 3616 */     if (existing_block == null || existing_block.blockMaterial == Material.air) {
/*      */       
/* 3618 */       Minecraft.setErrorMessage("canReplaceBlock: there is no existing block for " + getLocalizedName() + " to replace");
/* 3619 */       return false;
/*      */     } 
/*      */     
/* 3622 */     return existing_block.canBeReplacedBy(existing_block_metadata, this, metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 3635 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinX(int index) {
/* 3716 */     return this.minX[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxX(int index) {
/* 3721 */     return this.maxX[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinY(int index) {
/* 3726 */     return this.minY[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxY(int index) {
/* 3731 */     return this.maxY[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinZ(int index) {
/* 3736 */     return this.minZ[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxZ(int index) {
/* 3741 */     return this.maxZ[index];
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockColor() {
/* 3746 */     return 16777215;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderColor(int par1) {
/* 3754 */     return 16777215;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 3763 */     return 16777215;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 3773 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canProvidePower() {
/* 3781 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/* 3795 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsForItemRender(int item_damage) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCarried() {
/* 3832 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSilkHarvest(int metadata) {
/* 3841 */     return canBeCarried();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack createStackedBlock(int metadata) {
/* 3864 */     return canBeCarried() ? new ItemStack(this, 1, getItemSubtype(metadata)) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUnderminedByPlayer(World world, EntityPlayer player, int x, int y, int z) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Block setUnlocalizedName(String par1Str) {
/* 3907 */     this.unlocalizedName = par1Str;
/* 3908 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalizedName() {
/* 3916 */     return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnlocalizedName() {
/* 3924 */     return "tile." + this.unlocalizedName;
/*      */   }
/*      */ 
/*      */   
/*      */   public final String getNameForReferenceFile(int metadata, boolean include_disambiguation) {
/* 3929 */     if (include_disambiguation) {
/*      */       
/* 3931 */       String disambiguation = getNameDisambiguationForReferenceFile(metadata);
/*      */       
/* 3933 */       return (disambiguation == null) ? getLocalizedName() : (getLocalizedName() + " (" + disambiguation + ")");
/*      */     } 
/*      */     
/* 3936 */     return getLocalizedName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 3945 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEnableStats() {
/* 3953 */     return this.enableStats;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block disableStats() {
/* 3961 */     this.enableStats = false;
/* 3962 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMobilityFlag() {
/* 3971 */     return this.blockMaterial.getMaterialMobility();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final float getAmbientOcclusionLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 3984 */     int block_id = par1IBlockAccess.getBlockId(par2, par3, par4);
/*      */     
/* 3986 */     if (block_id == 0) {
/* 3987 */       return 1.0F;
/*      */     }
/*      */ 
/*      */     
/* 3991 */     if (is_normal_cube_lookup[block_id]) {
/* 3992 */       return 0.2F;
/*      */     }
/* 3994 */     if ((getBlock(block_id)).is_tree_leaves) {
/* 3995 */       return 0.4F;
/*      */     }
/* 3997 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 4012 */     return this.blockID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMetadataNotes() {
/* 4025 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isValidMetadata(int metadata) {
/* 4031 */     return (metadata == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockSubtypeUnchecked(int metadata) {
/* 4037 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockSubtype(int metadata) {
/* 4045 */     if (!isValidMetadata(metadata)) {
/*      */       
/* 4047 */       reportInvalidMetadata(metadata);
/* 4048 */       return 0;
/*      */     } 
/*      */     
/* 4051 */     return getBlockSubtypeUnchecked(metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemSubtype(int metadata) {
/* 4057 */     return getBlockSubtype(metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getItemStacks(int id, CreativeTabs creative_tabs, List<ItemStack> list) {
/* 4076 */     for (int i = 0; i < this.item_subtypes.length; i++) {
/* 4077 */       list.add(new ItemStack(id, 1, this.item_subtypes[i]));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final List getItemStacks() {
/* 4083 */     List list = new ArrayList();
/*      */     
/* 4085 */     getItemStacks(this.blockID, null, list);
/*      */     
/* 4087 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public void reportInvalidMetadata(int metadata) {
/* 4092 */     Minecraft.setErrorMessage("Block: invalid metadata value of " + metadata + " for Block[" + this.blockID + "]");
/* 4093 */     (new Exception()).printStackTrace();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final int[] getItemSubtypes() {
/* 4099 */     boolean[] subtype_exists = new boolean[16];
/*      */     
/* 4101 */     for (int metadata = 0; metadata < 16; metadata++) {
/*      */       
/* 4103 */       if (isValidMetadata(metadata)) {
/* 4104 */         subtype_exists[getItemSubtype(metadata)] = true;
/*      */       }
/*      */     } 
/* 4107 */     int num = 0;
/*      */     
/* 4109 */     for (int j = 0; j < 16; j++) {
/*      */       
/* 4111 */       if (subtype_exists[j]) {
/* 4112 */         num++;
/*      */       }
/*      */     } 
/* 4115 */     int[] subtypes = new int[num];
/*      */     
/* 4117 */     int i = 0;
/*      */     
/* 4119 */     for (int k = 0; k < 16; k++) {
/*      */       
/* 4121 */       if (subtype_exists[k]) {
/* 4122 */         subtypes[i++] = k;
/*      */       }
/*      */     } 
/* 4125 */     return subtypes;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getNumSubBlocks() {
/* 4131 */     return this.num_item_subtypes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CreativeTabs getCreativeTabToDisplayOn() {
/* 4139 */     return this.displayOnCreativeTab;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Block setCreativeTab(CreativeTabs par1CreativeTabs) {
/* 4147 */     this.displayOnCreativeTab = par1CreativeTabs;
/* 4148 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockPreDestroy(World par1World, int par2, int par3, int par4, int par5) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fillWithRain(World par1World, int par2, int par3, int par4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFlowerPot() {
/* 4172 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_82506_l() {
/* 4177 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canDropFromExplosion(Explosion par1Explosion) {
/* 4185 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAssociatedBlockID(int par1) {
/* 4194 */     return (this.blockID == par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAssociatedBlockID(int par0, int par1) {
/* 4202 */     return (par0 == par1) ? true : ((par0 != 0 && par1 != 0 && blocksList[par0] != null && blocksList[par1] != null) ? blocksList[par0].isAssociatedBlockID(par1) : false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasComparatorInputOverride() {
/* 4211 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 4220 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Block setTextureName(String par1Str) {
/* 4225 */     this.textureName = par1Str;
/* 4226 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getTextureName() {
/* 4231 */     return (this.textureName == null) ? ("MISSING_ICON_TILE_" + this.blockID + "_" + this.unlocalizedName) : this.textureName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerIcons(IconRegister par1IconRegister) {
/* 4240 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon[] registerIcons(IconRegister par1IconRegister, String[] types) {
/* 4246 */     return registerIcons(par1IconRegister, types, null, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon[] registerIcons(IconRegister par1IconRegister, String[] types, String prefix) {
/* 4252 */     return registerIcons(par1IconRegister, types, prefix, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon[] registerIcons(IconRegister par1IconRegister, String[] types, String prefix, String suffix) {
/* 4258 */     Icon[] icons = new Icon[types.length];
/*      */     
/* 4260 */     for (int i = 0; i < icons.length; i++) {
/*      */       
/* 4262 */       if (types[i] != null) {
/*      */         
/* 4264 */         StringBuilder sb = new StringBuilder();
/*      */         
/* 4266 */         if (prefix != null) {
/* 4267 */           sb.append(prefix);
/*      */         }
/* 4269 */         sb.append(types[i]);
/*      */         
/* 4271 */         if (suffix != null) {
/* 4272 */           sb.append(suffix);
/*      */         }
/* 4274 */         icons[i] = par1IconRegister.registerIcon(sb.toString());
/*      */       } 
/*      */     } 
/*      */     
/* 4278 */     return icons;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItemIconName() {
/* 4286 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/* 4291 */     for (int j = 0; j < 256; j++) {
/*      */       
/* 4293 */       Block block = getBlock(j);
/*      */       
/* 4295 */       if (block != null)
/*      */       {
/*      */         
/* 4298 */         block.initializeBlock();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4304 */     Item.itemsList[cloth.blockID] = (new ItemCloth(cloth)).setUnlocalizedName("cloth");
/* 4305 */     Item.itemsList[stainedClay.blockID] = (new ItemCloth(stainedClay)).setUnlocalizedName("clayHardenedStained");
/* 4306 */     Item.itemsList[carpet.blockID] = (new ItemCloth(carpet)).setUnlocalizedName("woolCarpet");
/*      */     
/* 4308 */     Item.itemsList[wood.blockID] = (new ItemMultiTextureTile(wood, wood.getNames())).setUnlocalizedName("log");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4315 */     Item.itemsList[planks.blockID] = (new ItemMultiTextureTile(planks, planks.getNames())).setUnlocalizedName("wood");
/* 4316 */     Item.itemsList[silverfish.blockID] = (new ItemMultiTextureTile(silverfish, BlockSilverfish.silverfishStoneTypes)).setUnlocalizedName("monsterStoneEgg");
/* 4317 */     Item.itemsList[stoneBrick.blockID] = (new ItemMultiTextureTile(stoneBrick, BlockStoneBrick.STONE_BRICK_TYPES)).setUnlocalizedName("stonebricksmooth");
/* 4318 */     Item.itemsList[sandStone.blockID] = (new ItemMultiTextureTile(sandStone, BlockSandStone.SAND_STONE_TYPES)).setUnlocalizedName("sandStone");
/* 4319 */     Item.itemsList[blockNetherQuartz.blockID] = (new ItemMultiTextureTile(blockNetherQuartz, BlockQuartz.quartzBlockTypes)).setUnlocalizedName("quartzBlock");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4324 */     Item.itemsList[stoneSingleSlab.blockID] = (new ItemSlab(stoneSingleSlab, stoneDoubleSlab, false)).setUnlocalizedName("stoneSlab");
/* 4325 */     Item.itemsList[stoneDoubleSlab.blockID] = (new ItemSlab(stoneSingleSlab, stoneDoubleSlab, true)).setUnlocalizedName("stoneSlab");
/* 4326 */     Item.itemsList[woodSingleSlab.blockID] = (new ItemSlab(woodSingleSlab, woodDoubleSlab, false)).setUnlocalizedName("woodSlab");
/* 4327 */     Item.itemsList[woodDoubleSlab.blockID] = (new ItemSlab(woodSingleSlab, woodDoubleSlab, true)).setUnlocalizedName("woodSlab");
/*      */ 
/*      */     
/* 4330 */     Item.itemsList[sapling.blockID] = (new ItemMultiTextureTile(sapling, BlockSapling.WOOD_TYPES)).setUnlocalizedName("sapling");
/*      */     
/* 4332 */     Item.itemsList[leaves.blockID] = (new ItemLeaves(leaves)).setUnlocalizedName("leaves");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4338 */     Item.itemsList[vine.blockID] = new ItemColored(vine);
/* 4339 */     Item.itemsList[tallGrass.blockID] = (new ItemColored(tallGrass)).setBlockNames(new String[] { "shrub", "grass", "fern" });
/*      */     
/* 4341 */     Item.itemsList[snow.blockID] = new ItemSnow(snow);
/* 4342 */     Item.itemsList[blockSnow.blockID] = new ItemSnowBlock(blockSnow);
/*      */     
/* 4344 */     Item.itemsList[waterlily.blockID] = new ItemLilyPad(waterlily);
/*      */ 
/*      */     
/* 4347 */     Item.itemsList[pistonBase.blockID] = new ItemPiston(pistonBase);
/* 4348 */     Item.itemsList[pistonStickyBase.blockID] = new ItemPiston(pistonStickyBase);
/*      */     
/* 4350 */     Item.itemsList[cobblestoneWall.blockID] = (new ItemMultiTextureTile(cobblestoneWall, BlockWall.types)).setUnlocalizedName("cobbleWall");
/* 4351 */     Item.itemsList[anvil.blockID] = (new ItemAnvilBlock(anvil)).setUnlocalizedName("anvil");
/*      */ 
/*      */ 
/*      */     
/* 4355 */     Item.itemsList[gravel.blockID] = new ItemGravel(gravel, gravel.getNames());
/* 4356 */     Item.itemsList[deadBush.blockID] = new ItemDeadBush(deadBush, BlockDeadBush.types);
/*      */     
/* 4358 */     Item.itemsList[plantRed.blockID] = (new ItemMultiTextureTile(plantRed, BlockFlowerMulti.types)).setUnlocalizedName("flower");
/*      */ 
/*      */ 
/*      */     
/* 4362 */     Item.itemsList[obsidianSingleSlab.blockID] = (new ItemSlab(obsidianSingleSlab, obsidianDoubleSlab, false)).setUnlocalizedName("obsidianSlab");
/* 4363 */     Item.itemsList[obsidianDoubleSlab.blockID] = (new ItemSlab(obsidianSingleSlab, obsidianDoubleSlab, true)).setUnlocalizedName("obsidianSlab");
/*      */     
/* 4365 */     Item.itemsList[anvilCopper.blockID] = (new ItemAnvilBlock(anvilCopper)).setUnlocalizedName("anvilCopper");
/* 4366 */     Item.itemsList[anvilSilver.blockID] = (new ItemAnvilBlock(anvilSilver)).setUnlocalizedName("anvilSilver");
/* 4367 */     Item.itemsList[anvilGold.blockID] = (new ItemAnvilBlock(anvilGold)).setUnlocalizedName("anvilGold");
/* 4368 */     Item.itemsList[anvilMithril.blockID] = (new ItemAnvilBlock(anvilMithril)).setUnlocalizedName("anvilMithril");
/* 4369 */     Item.itemsList[anvilAdamantium.blockID] = (new ItemAnvilBlock(anvilAdamantium)).setUnlocalizedName("anvilAdamantium");
/* 4370 */     Item.itemsList[anvilAncientMetal.blockID] = (new ItemAnvilBlock(anvilAncientMetal)).setUnlocalizedName("anvilAncientMetal");
/*      */     
/* 4372 */     Item.itemsList[runestoneMithril.blockID] = (new ItemRunestone(runestoneMithril)).setUnlocalizedName("runestone");
/* 4373 */     Item.itemsList[runestoneAdamantium.blockID] = (new ItemRunestone(runestoneAdamantium)).setUnlocalizedName("runestone");
/*      */     
/* 4375 */     Item.itemsList[bush.blockID] = (new ItemMultiTextureTile(bush, BlockBush.types)).setUnlocalizedName("bush");
/*      */     
/* 4377 */     Item.itemsList[mantleOrCore.blockID] = new ItemMantleOrCore(mantleOrCore, mantleOrCore.getNames());
/*      */ 
/*      */ 
/*      */     
/* 4381 */     for (int var0 = 0; var0 < 256; var0++) {
/*      */       
/* 4383 */       Block block = getBlock(var0);
/*      */ 
/*      */       
/* 4386 */       if (block != null) {
/*      */         
/* 4388 */         if (Item.itemsList[var0] == null)
/*      */         {
/*      */           
/* 4391 */           Item.itemsList[var0] = new ItemBlock(block);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4431 */         for (int direction = 0; direction < 6; direction++) {
/*      */           
/* 4433 */           for (int metadata = 0; metadata < 16; metadata++) {
/*      */             
/* 4435 */             if (block.isValidMetadata(metadata)) {
/*      */               
/* 4437 */               block.use_neighbor_brightness[metadata + direction * 16] = block.useNeighborBrightness(block.use_neighbor_brightness, metadata, EnumDirection.get(direction)); if (block.useNeighborBrightness(block.use_neighbor_brightness, metadata, EnumDirection.get(direction))) {
/* 4438 */                 useNeighborBrightness[var0] = true;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 4445 */         if (var0 > 0) {
/*      */ 
/*      */           
/* 4448 */           Item item = Item.itemsList[var0];
/*      */           
/* 4450 */           item.setCraftingDifficultyAsComponent(block.getCraftingDifficultyAsComponent(0));
/* 4451 */           item.setMaxStackSize(block.getItemStackLimit());
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4458 */         for (int k = 1; k < block.minX.length; k++) {
/*      */           
/* 4460 */           if (block.minX[k] != block.minX[0]) {
/* 4461 */             Minecraft.setErrorMessage("minX discrepency for " + block);
/*      */           }
/* 4463 */           if (block.minY[k] != block.minY[0]) {
/* 4464 */             Minecraft.setErrorMessage("minY discrepency for " + block);
/*      */           }
/* 4466 */           if (block.minZ[k] != block.minZ[0]) {
/* 4467 */             Minecraft.setErrorMessage("minZ discrepency for " + block);
/*      */           }
/* 4469 */           if (block.maxX[k] != block.maxX[0]) {
/* 4470 */             Minecraft.setErrorMessage("maxX discrepency for " + block);
/*      */           }
/* 4472 */           if (block.maxY[k] != block.maxY[0]) {
/* 4473 */             Minecraft.setErrorMessage("maxY discrepency for " + block);
/*      */           }
/* 4475 */           if (block.maxZ[k] != block.maxZ[0]) {
/* 4476 */             Minecraft.setErrorMessage("maxZ discrepency for " + block);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4485 */     Item.itemsList[mushroomBrown.blockID].setFoodValue(1, 1, false, false, false).setPlantProduct();
/* 4486 */     Item.itemsList[mushroomRed.blockID].setFoodValue(1, 1, false, false, false).setPlantProduct();
/*      */     
/* 4488 */     Item.itemsList[cake.blockID].setFoodValue(2, 2, true, MITEConstant.egg_has_essential_fats, false).setPlantProduct().setAnimalProduct();
/*      */ 
/*      */ 
/*      */     
/* 4492 */     canHaveLightValue[0] = true;
/* 4493 */     StatList.initBreakableStats();
/*      */     
/* 4495 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 4497 */       Block block = getBlock(i);
/*      */       
/* 4499 */       if (block != null)
/*      */       {
/*      */         
/* 4502 */         block.validate();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public Block setCushioning(float cushioning) {
/* 4508 */     this.cushioning = cushioning;
/* 4509 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCushioning(int metadata) {
/* 4514 */     return this.cushioning;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 4519 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block setMaxStackSize(int max_stack_size) {
/* 4524 */     this.max_stack_size = max_stack_size;
/* 4525 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemStackLimit() {
/* 4530 */     return this.max_stack_size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 4537 */     float hardness = getBlockHardness(metadata);
/*      */     
/* 4539 */     return (hardness < 0.0F) ? -1.0F : (hardness * 100.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 4544 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getBlock(int block_id) {
/* 4549 */     return (block_id > 0 && block_id < blocksList.length) ? blocksList[block_id] : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getBlock(String localized_name) {
/* 4554 */     for (int i = 1; i < 256; i++) {
/*      */       
/* 4556 */       Block block = getBlock(i);
/*      */       
/* 4558 */       if (block != null)
/*      */       {
/*      */         
/* 4561 */         if (localized_name.equalsIgnoreCase(block.getLocalizedName()))
/* 4562 */           return block; 
/*      */       }
/*      */     } 
/* 4565 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 4570 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 4575 */     item_block.addMaterial(new Material[] { this.blockMaterial });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isLiquid() {
/* 4586 */     return this.blockMaterial.isLiquid();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getStageOfBlockDestruction(float block_damage) {
/* 4592 */     return (int)(block_damage * 10.0F) - 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean doesNeighborBlockExist(World world, int x, int y, int z, EnumFace face) {
/* 4597 */     x = face.getNeighborX(x);
/* 4598 */     y = face.getNeighborY(y);
/* 4599 */     z = face.getNeighborZ(z);
/*      */     
/* 4601 */     if (world.getBlock(x, y, z) == null) {
/*      */       
/* 4603 */       Minecraft.setErrorMessage("doesNeighborBlockExist: face hit was " + face + " but no neighbor block exists at " + x + "," + y + "," + z);
/* 4604 */       return false;
/*      */     } 
/*      */     
/* 4607 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPlaceBlock(World world, int x, int y, int z, EnumFace face, int metadata, Entity placer, boolean perform_placement_check, boolean drop_existing_block, boolean test_only) {
/* 4613 */     if (world.isRemote && !test_only) {
/*      */       
/* 4615 */       Minecraft.setErrorMessage("tryPlaceBlock: must be test only on client");
/* 4616 */       return false;
/*      */     } 
/*      */     
/* 4619 */     if (!world.isRemote && placer instanceof EntityPlayer) {
/* 4620 */       placer.getAsPlayer().sendPacket((new Packet85SimpleSignal(EnumSignal.clear_tentative_bounding_box)).setBlockCoords(x, y, z));
/*      */     }
/* 4622 */     if (y < 0) {
/* 4623 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4642 */     int saved_metadata = world.getBlockMetadata(x, y, z);
/*      */     
/* 4644 */     world.setBlockMetadataWithNotify(x, y, z, metadata, 0);
/*      */ 
/*      */     
/* 4647 */     AxisAlignedBB bounding_box = getCollisionBoundsCombined(world, x, y, z, null, true);
/*      */     
/* 4649 */     world.setBlockMetadataWithNotify(x, y, z, saved_metadata, 0);
/*      */     
/* 4651 */     if (this instanceof BlockLadder) {
/* 4652 */       bounding_box = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4668 */     if (bounding_box != null && !world.checkNoEntityCollision(bounding_box)) {
/* 4669 */       return false;
/*      */     }
/* 4671 */     if (this instanceof BlockTorch) {
/*      */       
/* 4673 */       AxisAlignedBB bb = world.getBoundingBoxFromPool(x, y, z);
/*      */       
/* 4675 */       List gelatinous_cubes = world.getEntitiesWithinAABB(EntityGelatinousCube.class, bb);
/*      */       
/* 4677 */       if (!gelatinous_cubes.isEmpty()) {
/* 4678 */         return false;
/*      */       }
/*      */     } 
/* 4681 */     if (perform_placement_check) {
/*      */       
/* 4683 */       if (face.isTop()) {
/*      */         
/* 4685 */         Block block_below = world.getBlock(x, y - 1, z);
/* 4686 */         int block_below_metadata = world.getBlockMetadata(x, y - 1, z);
/*      */         
/* 4688 */         block_below.setBlockBoundsBasedOnStateAndNeighbors(world, x, y - 1, z);
/*      */         
/* 4690 */         if (!canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below.maxY[Minecraft.getThreadIndex()])) {
/* 4691 */           return false;
/*      */         }
/*      */       } 
/* 4694 */       if (!canBePlacedAt(world, x, y, z, metadata)) {
/* 4695 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4704 */     if (test_only) {
/*      */       
/* 4706 */       boolean result = onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*      */       
/* 4708 */       if (result && bounding_box != null && placer instanceof EntityPlayer && placer.onClient()) {
/* 4709 */         (placer.getAsPlayer()).tentative_bounding_boxes.add(new TentativeBoundingBox(x, y, z, new AxisAlignedBB(bounding_box)));
/*      */       }
/* 4711 */       return result;
/*      */     } 
/*      */     
/* 4714 */     if (y >= MinecraftServer.getServer().getBuildLimit() - 1) {
/* 4715 */       return false;
/*      */     }
/* 4717 */     Block existing_block = drop_existing_block ? world.getBlock(x, y, z) : null;
/*      */     
/* 4719 */     BlockBreakInfo info = (existing_block == null) ? null : (new BlockBreakInfo(world, x, y, z)).setReplaced();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4741 */     if (existing_block != null && existing_block != this && existing_block.showDestructionParticlesWhenReplacedBy(info.getMetadata(), this, metadata)) {
/* 4742 */       world.blockFX(EnumBlockFX.destroy, x, y, z, (new SignalData()).setInteger(existing_block.blockID + (info.getMetadata() << 8) + (this.blockID << 12) + (metadata << 20)));
/*      */     }
/* 4744 */     if (world.setBlock(x, y, z, this.blockID, metadata, 3)) {
/*      */       
/* 4746 */       if (existing_block != null) {
/* 4747 */         existing_block.dropBlockAsEntityItem(info);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4763 */       if (placer instanceof EntityPlayer) {
/*      */         
/* 4765 */         EntityPlayer player = placer.getAsPlayer();
/*      */         
/* 4767 */         player.block_placement_tick = world.getTotalWorldTime();
/* 4768 */         player.block_placement_pos_x = player.posX;
/* 4769 */         player.block_placement_pos_y = player.posY;
/* 4770 */         player.block_placement_pos_z = player.posZ;
/* 4771 */         player.block_placement_world = world;
/*      */       } 
/*      */       
/* 4774 */       makeSoundWhenPlaced(world, x, y, z, metadata);
/*      */       
/* 4776 */       return onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*      */     } 
/*      */     
/* 4779 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeSoundWhenPlaced(World world, int x, int y, int z, int metadata) {
/* 4784 */     if (this.stepSound != null) {
/* 4785 */       world.playSoundAtBlock(x, y, z, this.stepSound.getPlaceSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
/* 4791 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean tryPlaceBlock(World world, int x, int y, int z, EnumFace face, int metadata, Entity placer, boolean perform_placement_check, boolean drop_existing_block) {
/* 4796 */     return tryPlaceBlock(world, x, y, z, face, metadata, placer, perform_placement_check, drop_existing_block, world.isRemote);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean tryPlaceFromHeldItem(int x, int y, int z, EnumFace face, ItemStack item_stack, EntityPlayer player, float offset_x, float offset_y, float offset_z, boolean perform_placement_check, boolean drop_existing_block, boolean test_only) {
/* 4801 */     if (player.onServer()) {
/* 4802 */       player.sendPacket((new Packet85SimpleSignal(EnumSignal.clear_tentative_bounding_box)).setBlockCoords(x, y, z));
/*      */     }
/* 4804 */     if (player.worldObj.getBlockMaterial(x, y, z) == Material.lava) {
/*      */       
/* 4806 */       World world = player.worldObj;
/*      */       
/* 4808 */       if (this == blockSnow || this == ice) {
/*      */         
/* 4810 */         if (player.onServer()) {
/* 4811 */           world.tryConvertLavaToCobblestoneOrObsidian(x, y, z);
/*      */         }
/* 4813 */         return true;
/*      */       } 
/* 4815 */       if (this == snow)
/*      */       {
/* 4817 */         if (player.onServer()) {
/* 4818 */           world.blockFX(EnumBlockFX.steam, x, y, z);
/*      */         }
/* 4820 */         return true;
/*      */       }
/*      */     
/* 4823 */     } else if (player.isInNether() || player.worldObj.getBlock(x, y - 1, z) == mantleOrCore) {
/*      */       
/* 4825 */       if (this == blockSnow || this == ice || this == snow) {
/*      */         
/* 4827 */         if (player.onServer())
/*      */         {
/*      */ 
/*      */           
/* 4831 */           if (player.worldObj.getBlock(x, y, z) == fire) {
/* 4832 */             player.worldObj.douseFire(x, y, z, null);
/*      */           } else {
/* 4834 */             player.worldObj.blockFX(EnumBlockFX.steam, x, y, z);
/*      */           } 
/*      */         }
/* 4837 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 4841 */     int metadata = getMetadataForPlacement(player.worldObj, x, y, z, item_stack, player, face, offset_x, offset_y, offset_z);
/*      */ 
/*      */ 
/*      */     
/* 4845 */     if (metadata < 0) {
/* 4846 */       return false;
/*      */     }
/* 4848 */     if (getBlockHardness(metadata) > 0.0F && !player.hasFoodEnergy() && !player.inCreativeMode()) {
/* 4849 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4855 */     return tryPlaceBlock(player.worldObj, x, y, z, face, metadata, player, perform_placement_check, drop_existing_block, test_only);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean tryPlaceFromHeldItem(int x, int y, int z, EnumFace face, ItemStack item_stack, EntityPlayer player, float offset_x, float offset_y, float offset_z, boolean perform_placement_check, boolean drop_existing_block) {
/* 4860 */     return tryPlaceFromHeldItem(x, y, z, face, item_stack, player, offset_x, offset_y, offset_z, perform_placement_check, drop_existing_block, player.onClient());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 4867 */     if (!test_only)
/*      */     {
/* 4869 */       if (placer instanceof EntityLivingBase) {
/*      */         
/* 4871 */         ItemStack item_stack = placer.getAsEntityLivingBase().getHeldItemStack();
/*      */         
/* 4873 */         if (item_stack.hasDisplayName()) {
/*      */           
/* 4875 */           TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
/*      */           
/* 4877 */           if (tile_entity != null) {
/* 4878 */             tile_entity.setCustomInvName(item_stack.getDisplayName());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/* 4883 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultMetadata(World world, int x, int y, int z) {
/* 4889 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean hasItemSubtypes() {
/* 4894 */     return this.has_item_subtypes;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumDirection getDirectionFacing(int metadata) {
/* 4900 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 4905 */     return metadata;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction, int base_coord_mode) {
/* 4910 */     return getMetadataForDirectionFacing(metadata, direction);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getAlternativeBlockForPlacement() {
/* 4916 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockDamageStageChange(int x, int y, int z, Entity entity, int damage_stage) {}
/*      */ 
/*      */   
/*      */   public static final boolean isBlockSingleSlab(int id) {
/* 4925 */     return getBlock(id) instanceof BlockSlab;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isSingleSlab() {
/* 4931 */     return this instanceof BlockSlab;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isSingleSlabLower(int metadata) {
/* 4936 */     return (isSingleSlab() && BlockSlab.isBottom(metadata));
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isSingleSlabUpper(int metadata) {
/* 4941 */     return (isSingleSlab() && BlockSlab.isTop(metadata));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinHarvestLevel(int metadata) {
/* 4946 */     return this.min_harvest_level;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 4952 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isSolid(int metadata) {
/* 4958 */     return this.is_solid[metadata];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 4964 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isStandardFormCube(int metadata) {
/* 4970 */     return this.is_standard_form_cube[metadata];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 4986 */     return (isLiquid() || (isStandardFormCube(metadata) && isSolid(metadata)));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean blocksPrecipitation(int metadata) {
/* 4992 */     return this.blocks_precipitation[metadata];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 4998 */     return isSolid(metadata);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean blocksFluids(int metadata) {
/* 5004 */     return this.blocks_fluids[metadata];
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSupportEntityShadow(int metadata) {
/* 5009 */     if (this instanceof BlockCactus || this instanceof BlockCauldron || this instanceof BlockHopper || this instanceof BlockPistonExtension) {
/* 5010 */       return false;
/*      */     }
/* 5012 */     return !neverHidesAdjacentFaces();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean doesBlockHideAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 5018 */     int block_id = block_access.getBlockId(x, y, z);
/*      */     
/* 5020 */     if (block_id == 0) {
/* 5021 */       return false;
/*      */     }
/* 5023 */     Block block = getBlock(block_id);
/*      */     
/* 5025 */     if (block.neverHidesAdjacentFaces()) {
/* 5026 */       return false;
/*      */     }
/* 5028 */     return block.hidesAdjacentSide(block_access, x, y, z, neighbor, side);
/*      */   }
/*      */ 
/*      */   
/*      */   public static final boolean isBlockSolid(IBlockAccess block_access, int x, int y, int z) {
/* 5033 */     return isBlockSolid(block_access, getBlock(block_access.getBlockId(x, y, z)), x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isBlockSolid(IBlockAccess block_access, Block block, int x, int y, int z) {
/* 5039 */     return (block != null && block.isSolid(block_access, x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isSolid(IBlockAccess block_access, int x, int y, int z) {
/* 5044 */     if (isAlwaysSolid()) {
/* 5045 */       return true;
/*      */     }
/* 5047 */     if (isNeverSolid()) {
/* 5048 */       return false;
/*      */     }
/* 5050 */     return isSolid(block_access.getBlockMetadata(x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public static final boolean isBlockOpaqueStandardFormCube(IBlockAccess block_access, int x, int y, int z) {
/* 5055 */     return isBlockOpaqueStandardFormCube(block_access, getBlock(block_access.getBlockId(x, y, z)), x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isBlockOpaqueStandardFormCube(IBlockAccess block_access, Block block, int x, int y, int z) {
/* 5061 */     return (block != null && block.isOpaqueStandardFormCube(block_access, x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isOpaqueStandardFormCube(IBlockAccess block_access, int x, int y, int z) {
/* 5066 */     if (isAlwaysOpaqueStandardFormCube()) {
/* 5067 */       return true;
/*      */     }
/* 5069 */     if (isNeverOpaqueStandardFormCube()) {
/* 5070 */       return false;
/*      */     }
/* 5072 */     return isOpaqueStandardFormCube(block_access.getBlockMetadata(x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public static final boolean isBlockSolidOpaqueStandardFormCube(IBlockAccess block_access, int x, int y, int z) {
/* 5077 */     return isBlockSolidOpaqueStandardFormCube(block_access, getBlock(block_access.getBlockId(x, y, z)), x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isBlockSolidOpaqueStandardFormCube(IBlockAccess block_access, Block block, int x, int y, int z) {
/* 5083 */     return (block != null && block.isSolidOpaqueStandardFormCube(block_access, x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isSolidOpaqueStandardFormCube(IBlockAccess block_access, int x, int y, int z) {
/* 5088 */     if (isAlwaysSolidOpaqueStandardFormCube()) {
/* 5089 */       return true;
/*      */     }
/* 5091 */     if (isNeverSolidOpaqueStandardFormCube()) {
/* 5092 */       return false;
/*      */     }
/* 5094 */     return isSolidOpaqueStandardFormCube(block_access.getBlockMetadata(x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean connectsWithFence() {
/* 5099 */     return this.connects_with_fence;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean doesBlockConnectWithFence(IBlockAccess block_access, int x, int y, int z) {
/* 5104 */     Block block = block_access.getBlock(x, y, z);
/*      */     
/* 5106 */     return (block != null && block.connectsWithFence());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPartnerX(int x, int metadata) {
/* 5112 */     if (this instanceof IBlockWithPartner) {
/* 5113 */       return x + ((IBlockWithPartner)this).getPartnerOffsetX(metadata);
/*      */     }
/* 5115 */     Minecraft.setErrorMessage("getPartnerX: " + this + " is not an instanceof IBlockWithPartner");
/* 5116 */     return x;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPartnerY(int y, int metadata) {
/* 5122 */     if (this instanceof IBlockWithPartner) {
/* 5123 */       return y + ((IBlockWithPartner)this).getPartnerOffsetY(metadata);
/*      */     }
/* 5125 */     Minecraft.setErrorMessage("getPartnerY: " + this + " is not an instanceof IBlockWithPartner");
/* 5126 */     return y;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPartnerZ(int z, int metadata) {
/* 5132 */     if (this instanceof IBlockWithPartner) {
/* 5133 */       return z + ((IBlockWithPartner)this).getPartnerOffsetZ(metadata);
/*      */     }
/* 5135 */     Minecraft.setErrorMessage("getPartnerZ: " + this + " is not an instanceof IBlockWithPartner");
/* 5136 */     return z;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isPartnerPresent(World world, int x, int y, int z) {
/* 5142 */     if (this instanceof IBlockWithPartner) {
/*      */       
/* 5144 */       int metadata = world.getBlockMetadata(x, y, z);
/*      */       
/* 5146 */       x = getPartnerX(x, metadata);
/* 5147 */       y = getPartnerY(y, metadata);
/* 5148 */       z = getPartnerZ(z, metadata);
/*      */       
/* 5150 */       return ((IBlockWithPartner)this).isPartner(metadata, world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
/*      */     } 
/*      */ 
/*      */     
/* 5154 */     Minecraft.setErrorMessage("isPartnerPresent: " + this + " is not an instanceof IBlockWithPartner");
/* 5155 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doCollisionBoundsIntersectWith(World world, int x, int y, int z, AxisAlignedBB bb) {
/* 5161 */     Object collision_bounds = getCollisionBounds(world, x, y, z, null);
/*      */     
/* 5163 */     if (collision_bounds == null) {
/* 5164 */       return false;
/*      */     }
/* 5166 */     if (collision_bounds instanceof AxisAlignedBB)
/*      */     {
/* 5168 */       return bb.intersectsWith((AxisAlignedBB)collision_bounds);
/*      */     }
/*      */ 
/*      */     
/* 5172 */     bb = bb.translateCopy(-x, -y, -z);
/*      */     
/* 5174 */     AxisAlignedBB[] multiple_bounds = (AxisAlignedBB[])collision_bounds;
/*      */     
/* 5176 */     for (int i = 0; i < multiple_bounds.length; i++) {
/*      */       
/* 5178 */       if (multiple_bounds[i] != null && bb.intersectsWith(multiple_bounds[i])) {
/* 5179 */         return true;
/*      */       }
/*      */     } 
/* 5182 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doRenderBoundsIntersectWith(World world, int x, int y, int z, AxisAlignedBB bb) {
/* 5188 */     Object render_bounds = getRenderBounds(world, x, y, z, null);
/*      */     
/* 5190 */     if (render_bounds == null) {
/* 5191 */       return false;
/*      */     }
/* 5193 */     if (render_bounds instanceof AxisAlignedBB)
/*      */     {
/* 5195 */       return bb.intersectsWith((AxisAlignedBB)render_bounds);
/*      */     }
/*      */ 
/*      */     
/* 5199 */     bb = bb.translateCopy(-x, -y, -z);
/*      */     
/* 5201 */     AxisAlignedBB[] multiple_bounds = (AxisAlignedBB[])render_bounds;
/*      */     
/* 5203 */     for (int i = 0; i < multiple_bounds.length; i++) {
/*      */       
/* 5205 */       if (multiple_bounds[i] != null && bb.intersectsWith(multiple_bounds[i])) {
/* 5206 */         return true;
/*      */       }
/*      */     } 
/* 5209 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doCollisionBoundsContain(World world, int x, int y, int z, Vec3 point) {
/* 5215 */     Object collision_bounds = getCollisionBounds(world, x, y, z, null);
/*      */     
/* 5217 */     if (collision_bounds == null) {
/* 5218 */       return false;
/*      */     }
/* 5220 */     if (collision_bounds instanceof AxisAlignedBB)
/*      */     {
/* 5222 */       return ((AxisAlignedBB)collision_bounds).isVecInside(point);
/*      */     }
/*      */ 
/*      */     
/* 5226 */     point = point.translateCopy(-x, -y, -z);
/*      */     
/* 5228 */     AxisAlignedBB[] multiple_bounds = (AxisAlignedBB[])collision_bounds;
/*      */     
/* 5230 */     for (int i = 0; i < multiple_bounds.length; i++) {
/*      */       
/* 5232 */       if (multiple_bounds[i] != null && point.isInsideBB(multiple_bounds[i])) {
/* 5233 */         return true;
/*      */       }
/*      */     } 
/* 5236 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isWater() {
/* 5242 */     return (this.blockMaterial == Material.water);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean useNeighborBrightness(World world, int x, int y, int z, EnumDirection direction) {
/* 5250 */     return (neverHidesAdjacentFaces() || !hidesAdjacentSide(world, x, y, z, null, direction.ordinal()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean useNeighborBrightness(boolean[] use_neighbor_brightness, int metadata, EnumDirection direction) {
/* 5256 */     return (lightOpacity[this.blockID] >= 15 && !isStandardFormCube(metadata) && !neverHidesAdjacentFaces() && !isFaceFlatAndSolid(metadata, direction.getFace()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean useNeighborBrightness(int metadata, EnumDirection direction) {
/* 5262 */     return this.use_neighbor_brightness[metadata + direction.ordinal() * 16];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean melt(World world, int x, int y, int z) {
/* 5279 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 5284 */     int index = Minecraft.getThreadIndex();
/*      */     
/* 5286 */     return "" + getClass() + " [" + this.minX[index] + "," + this.minY[index] + "," + this.minZ[index] + "->" + this.maxX[index] + "," + this.maxY[index] + "," + this.maxZ[index] + "]";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onContactWithPepsin(World world, int x, int y, int z, EnumFace face, boolean show_particle_fx_for_gradual) {
/* 5291 */     boolean on_server = !world.isRemote;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5308 */     int period = getDissolvePeriod(world, x, y, z, DamageSource.pepsin);
/*      */     
/* 5310 */     if (period >= 0) {
/*      */       
/* 5312 */       if (on_server) {
/*      */         
/* 5314 */         if (period == 0 || show_particle_fx_for_gradual) {
/* 5315 */           world.blockFX(EnumBlockFX.steam, x, y, z);
/*      */         }
/* 5317 */         if (period == 0) {
/* 5318 */           return world.setBlockToAir(x, y, z);
/*      */         }
/*      */       } 
/* 5321 */       return (period == 0);
/*      */     } 
/*      */     
/* 5324 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onContactWithAcid(World world, int x, int y, int z, EnumFace face, boolean show_particle_fx_for_gradual) {
/* 5331 */     boolean on_server = !world.isRemote;
/*      */     
/* 5333 */     if (this == grass || this == mycelium) {
/*      */       
/* 5335 */       if (face == null || face.isTop())
/*      */       {
/* 5337 */         if (on_server) {
/*      */           
/* 5339 */           world.setBlock(x, y, z, dirt.blockID);
/* 5340 */           world.blockFX(EnumBlockFX.smoke_and_steam, x, y, z);
/*      */         } 
/*      */         
/* 5343 */         return true;
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5358 */       int period = getDissolvePeriod(world, x, y, z, DamageSource.acid);
/*      */       
/* 5360 */       if (period >= 0) {
/*      */         
/* 5362 */         if (on_server) {
/*      */           
/* 5364 */           if (period == 0 || show_particle_fx_for_gradual) {
/* 5365 */             world.blockFX((period == 0) ? EnumBlockFX.smoke_and_steam : EnumBlockFX.steam, x, y, z);
/*      */           }
/* 5367 */           if (period == 0) {
/* 5368 */             return world.setBlockToAir(x, y, z);
/*      */           }
/*      */         } 
/* 5371 */         return (period == 0);
/*      */       } 
/*      */     } 
/*      */     
/* 5375 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDissolvePeriod(int metadata, DamageSource damage_source) {
/* 5381 */     if (damage_source == DamageSource.pepsin) {
/*      */       
/* 5383 */       if (this == tripWire) {
/* 5384 */         return 400;
/*      */       }
/* 5386 */       if (this.blockMaterial.isHarmedByPepsin())
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 5391 */         if (this.blockMaterial == Material.cloth || this.blockMaterial == Material.materialCarpet || this.blockMaterial == Material.cake) {
/* 5392 */           return 400;
/*      */         }
/* 5394 */         return 0;
/*      */       }
/*      */     
/* 5397 */     } else if (damage_source == DamageSource.acid) {
/*      */       
/* 5399 */       if (this instanceof BlockDoor || this instanceof BlockChest || this instanceof BlockBasePressurePlate || this instanceof BlockPane || this instanceof BlockOreStorage) {
/* 5400 */         return this.blockMaterial.isHarmedByAcid() ? 400 : -1;
/*      */       }
/* 5402 */       if (this instanceof BlockAnvil) {
/* 5403 */         return ((BlockAnvil)this).getMetalType().isHarmedByAcid() ? 400 : -1;
/*      */       }
/* 5405 */       if (this instanceof BlockRailBase || this instanceof BlockButtonWood || this instanceof BlockLever || this instanceof BlockSign || this instanceof BlockLadder || this instanceof BlockBed || this instanceof BlockPistonBase || this instanceof BlockPistonExtension || this instanceof BlockCactus || this instanceof BlockMelon || this instanceof BlockPumpkin || this instanceof BlockRedstoneRepeater || this instanceof BlockTrapDoor || this instanceof BlockEnchantmentTable || this == skull || this instanceof BlockComparator || this == daylightSensor || this instanceof BlockHopper || this == hay) {
/* 5406 */         return 400;
/*      */       }
/* 5408 */       if (this.blockMaterial.isHarmedByAcid()) {
/*      */         
/* 5410 */         if (this instanceof BlockButtonStone || this instanceof BlockRedstoneWire) {
/* 5411 */           return -1;
/*      */         }
/* 5413 */         if (this instanceof BlockFence || this instanceof BlockFenceGate || this instanceof BlockCauldron || this == cocoaPlant) {
/* 5414 */           return 400;
/*      */         }
/* 5416 */         if (this instanceof BlockLilyPad || this instanceof BlockLeaves || this.blockMaterial == Material.cloth || this.blockMaterial == Material.cake) {
/* 5417 */           return 0;
/*      */         }
/* 5419 */         if (!isSolid(metadata)) {
/*      */           
/* 5421 */           if (this == tripWireSource) {
/* 5422 */             return 400;
/*      */           }
/* 5424 */           return 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5429 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDissolvePeriod(World world, int x, int y, int z, DamageSource damage_source) {
/* 5435 */     boolean dissolve_period_depends_on_metadata = false;
/*      */     
/* 5437 */     return getDissolvePeriod(dissolve_period_depends_on_metadata ? world.getBlockMetadata(x, y, z) : 0, damage_source);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDissolvedInstantly(World world, int x, int y, int z, DamageSource damage_source) {
/* 5442 */     return (getDissolvePeriod(world, x, y, z, damage_source) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBedrockOrMantleOrCore(Block block) {
/* 5457 */     return (block == bedrock || block == mantleOrCore);
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Block.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */