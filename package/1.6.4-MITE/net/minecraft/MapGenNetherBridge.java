/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MapGenNetherBridge
/*    */   extends MapGenStructure {
/*  8 */   private List spawnList = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MapGenNetherBridge() {
/* 21 */     this.spawnList.add(new SpawnListEntry(EntityBlaze.class, 100, 2, 3));
/* 22 */     this.spawnList.add(new SpawnListEntry(EntityPigZombie.class, 50, 4, 4));
/* 23 */     this.spawnList.add(new SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
/* 24 */     this.spawnList.add(new SpawnListEntry(EntityMagmaCube.class, 30, 4, 4));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_143025_a() {
/* 31 */     return "Fortress";
/*    */   }
/*    */ 
/*    */   
/*    */   public List getSpawnList() {
/* 36 */     return this.spawnList;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean canSpawnStructureAtCoords(int par1, int par2) {
/* 41 */     int var3 = par1 >> 4;
/* 42 */     int var4 = par2 >> 4;
/* 43 */     this.rand.setSeed((var3 ^ var4 << 4) ^ this.worldObj.getSeed());
/* 44 */     this.rand.nextInt();
/* 45 */     return (this.rand.nextInt(3) != 0) ? false : ((par1 != (var3 << 4) + 4 + this.rand.nextInt(8)) ? false : ((par2 == (var4 << 4) + 4 + this.rand.nextInt(8))));
/*    */   }
/*    */ 
/*    */   
/*    */   protected StructureStart getStructureStart(int par1, int par2) {
/* 50 */     return new StructureNetherBridgeStart(this.worldObj, this.rand, par1, par2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenNetherBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */