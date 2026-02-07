/*    */ package net.minecraft;
/*    */ 
/*    */ public enum EnumArt
/*    */ {
/*  5 */   Kebab("Kebab", 16, 16, 0, 0),
/*  6 */   Aztec("Aztec", 16, 16, 16, 0),
/*  7 */   Alban("Alban", 16, 16, 32, 0),
/*  8 */   Aztec2("Aztec2", 16, 16, 48, 0),
/*  9 */   Bomb("Bomb", 16, 16, 64, 0),
/* 10 */   Plant("Plant", 16, 16, 80, 0),
/* 11 */   Wasteland("Wasteland", 16, 16, 96, 0),
/* 12 */   Pool("Pool", 32, 16, 0, 32),
/* 13 */   Courbet("Courbet", 32, 16, 32, 32),
/* 14 */   Sea("Sea", 32, 16, 64, 32),
/* 15 */   Sunset("Sunset", 32, 16, 96, 32),
/* 16 */   Creebet("Creebet", 32, 16, 128, 32),
/* 17 */   Wanderer("Wanderer", 16, 32, 0, 64),
/* 18 */   Graham("Graham", 16, 32, 16, 64),
/* 19 */   Match("Match", 32, 32, 0, 128),
/* 20 */   Bust("Bust", 32, 32, 32, 128),
/* 21 */   Stage("Stage", 32, 32, 64, 128),
/* 22 */   Void("Void", 32, 32, 96, 128),
/* 23 */   SkullAndRoses("SkullAndRoses", 32, 32, 128, 128),
/* 24 */   Wither("Wither", 32, 32, 160, 128),
/* 25 */   Fighters("Fighters", 64, 32, 0, 96),
/* 26 */   Pointer("Pointer", 64, 64, 0, 192),
/* 27 */   Pigscene("Pigscene", 64, 64, 64, 192),
/* 28 */   BurningSkull("BurningSkull", 64, 64, 128, 192),
/* 29 */   Skeleton("Skeleton", 64, 48, 192, 64),
/* 30 */   DonkeyKong("DonkeyKong", 64, 48, 192, 112),
/*    */ 
/*    */ 
/*    */   
/* 34 */   Abyss("Abyss", 48, 32, "abyss.png", 32),
/* 35 */   BaronAlmric("BaronAlmric", 48, 32, "baron_almric.png", 64),
/* 36 */   Boat("Boat", 48, 32, "boat.png", 16),
/* 37 */   Castle("Castle", 32, 32, "castle.png", 8),
/* 38 */   CastleBritannia("CastleBritannia", 64, 32, "castle_britannia.png", 8),
/* 39 */   Darklands("Darklands", 32, 32, "darklands.png", 8),
/* 40 */   DeathtrapDungeon("DeathtrapDungeon", 48, 48, "deathtrap_dungeon.png", 64),
/* 41 */   DnDBasic("DnDBasic", 32, 32, "dnd_basic.png", 64),
/* 42 */   Draracle("Draracle", 48, 32, "draracle.png", 16),
/* 43 */   EldenGrove("EldenGrove", 64, 32, "elden_grove.png", 8),
/* 44 */   FairDay("FairDay", 64, 32, "fair_day.png", 8),
/* 45 */   FallenBridge("FallenBridge", 48, 32, "fallen_bridge.png", 16),
/* 46 */   GateClosing("GateClosing", 64, 32, "gate_closing.png", 16),
/* 47 */   Ghoul("Ghoul", 32, 48, "ghoul.png", 16),
/* 48 */   GladstoneKeep("GladstoneKeep", 48, 32, "gladstone_keep.png", 16),
/* 49 */   Graves("Graves", 32, 32, "graves.png", 8),
/* 50 */   KingRichard("KingRichard", 32, 32, "king_richard.png", 64),
/* 51 */   Messenger("Messenger", 48, 32, "messenger.png", 16),
/* 52 */   Mountains("Mountains", 32, 32, "mountains.png", 8),
/* 53 */   RolandsManor("RolandsManor", 48, 32, "rolands_manor.png", 8),
/* 54 */   Scotia("Scotia", 32, 32, "scotia.png", 64),
/* 55 */   Ship("Ship", 48, 32, "ship.png", 8),
/* 56 */   Sunlight("Sunlight", 32, 32, "sunlight.png", 8),
/* 57 */   Titan("Titan", 48, 32, "titan.png", 8),
/* 58 */   Wolves("Wolves", 32, 32, "wolves.png", 8);
/*    */   public static final int maxArtTitleLength;
/*    */   public final String title;
/*    */   
/*    */   static {
/* 63 */     maxArtTitleLength = "SkullAndRoses".length();
/*    */   }
/*    */ 
/*    */   
/*    */   public final int sizeX;
/*    */   
/*    */   public final int sizeY;
/*    */   
/*    */   public final int offsetX;
/*    */   public final int offsetY;
/*    */   public ResourceLocation special_texture;
/*    */   public int rarity;
/*    */   
/*    */   EnumArt(String par3Str, int par4, int par5, int par6, int par7) {
/* 77 */     this.title = par3Str;
/* 78 */     this.sizeX = par4;
/* 79 */     this.sizeY = par5;
/* 80 */     this.offsetX = par6;
/* 81 */     this.offsetY = par7;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   EnumArt(String title, int width, int height, String filename, int rarity) {
/* 87 */     this.special_texture = new ResourceLocation("textures/painting/" + filename);
/* 88 */     this.rarity = rarity;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumArt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */