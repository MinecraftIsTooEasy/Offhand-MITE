/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapColor
/*    */ {
/*  9 */   public static final MapColor[] mapColorArray = new MapColor[32];
/*    */ 
/*    */   
/* 12 */   public static final MapColor airColor = new MapColor(0, 0);
/*    */ 
/*    */   
/* 15 */   public static final MapColor grassColor = new MapColor(1, 8368696);
/*    */ 
/*    */   
/* 18 */   public static final MapColor sandColor = new MapColor(2, 16247203);
/*    */ 
/*    */   
/* 21 */   public static final MapColor clothColor = new MapColor(3, 10987431);
/*    */ 
/*    */   
/* 24 */   public static final MapColor tntColor = new MapColor(4, 16711680);
/*    */ 
/*    */   
/* 27 */   public static final MapColor iceColor = new MapColor(5, 10526975);
/*    */ 
/*    */   
/* 30 */   public static final MapColor ironColor = new MapColor(6, 10987431);
/*    */ 
/*    */   
/* 33 */   public static final MapColor foliageColor = new MapColor(7, 31744);
/*    */ 
/*    */   
/* 36 */   public static final MapColor snowColor = new MapColor(8, 16777215);
/*    */ 
/*    */   
/* 39 */   public static final MapColor clayColor = new MapColor(9, 10791096);
/*    */ 
/*    */   
/* 42 */   public static final MapColor dirtColor = new MapColor(10, 12020271);
/*    */ 
/*    */   
/* 45 */   public static final MapColor stoneColor = new MapColor(11, 7368816);
/*    */ 
/*    */   
/* 48 */   public static final MapColor waterColor = new MapColor(12, 4210943);
/*    */ 
/*    */   
/* 51 */   public static final MapColor woodColor = new MapColor(13, 6837042);
/*    */   
/* 53 */   public static final MapColor copperColor = new MapColor(14, 10970880);
/* 54 */   public static final MapColor silverColor = new MapColor(15, 13092807);
/* 55 */   public static final MapColor goldColor = new MapColor(16, 10983168);
/* 56 */   public static final MapColor mithrilColor = new MapColor(17, 10991559);
/* 57 */   public static final MapColor adamantiumColor = new MapColor(18, 3090231);
/* 58 */   public static final MapColor rustedIronColor = new MapColor(19, 10854039);
/*    */   
/* 60 */   public static final MapColor emeraldColor = new MapColor(20, 3787611);
/* 61 */   public static final MapColor diamondColor = new MapColor(21, 6938586);
/* 62 */   public static final MapColor redstoneColor = new MapColor(22, 13708051);
/* 63 */   public static final MapColor obsidianColor = new MapColor(23, 3549776);
/* 64 */   public static final MapColor netherrackColor = new MapColor(24, 7288372);
/*    */   
/* 66 */   public static final MapColor leatherColor = new MapColor(25, 12999733);
/* 67 */   public static final MapColor quartzColor = new MapColor(26, 15394014);
/*    */   
/* 69 */   public static final MapColor ancientMetalColor = new MapColor(27, 10331545);
/*    */ 
/*    */   
/*    */   public final int colorValue;
/*    */ 
/*    */   
/*    */   public final int colorIndex;
/*    */ 
/*    */   
/*    */   private MapColor(int par1, int par2) {
/* 79 */     this.colorIndex = par1;
/* 80 */     this.colorValue = par2;
/* 81 */     mapColorArray[par1] = this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */