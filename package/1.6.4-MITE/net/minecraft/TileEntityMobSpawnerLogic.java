/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class TileEntityMobSpawnerLogic
/*    */   extends MobSpawnerBaseLogic
/*    */ {
/*    */   final TileEntityMobSpawner mobSpawnerEntity;
/*    */   
/*    */   TileEntityMobSpawnerLogic(TileEntityMobSpawner par1TileEntityMobSpawner) {
/* 10 */     this.mobSpawnerEntity = par1TileEntityMobSpawner;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_98267_a(EnumEntityState par1) {
/* 20 */     if (par1 == EnumEntityState.mob_spawn) {
/* 21 */       this.mobSpawnerEntity.worldObj.addBlockEvent(this.mobSpawnerEntity.xCoord, this.mobSpawnerEntity.yCoord, this.mobSpawnerEntity.zCoord, Block.mobSpawner.blockID, 1, 0);
/*    */     } else {
/* 23 */       Minecraft.setErrorMessage("func_98267_a: unhandled case, EnumEntityState ordinal=" + par1.ordinal());
/*    */     } 
/*    */   }
/*    */   
/*    */   public World getSpawnerWorld() {
/* 28 */     return this.mobSpawnerEntity.worldObj;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerX() {
/* 33 */     return this.mobSpawnerEntity.xCoord;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerY() {
/* 38 */     return this.mobSpawnerEntity.yCoord;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerZ() {
/* 43 */     return this.mobSpawnerEntity.zCoord;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRandomMinecart(WeightedRandomMinecart par1WeightedRandomMinecart) {
/* 48 */     super.setRandomMinecart(par1WeightedRandomMinecart);
/*    */     
/* 50 */     if (getSpawnerWorld() != null)
/*    */     {
/* 52 */       getSpawnerWorld().markBlockForUpdate(this.mobSpawnerEntity.xCoord, this.mobSpawnerEntity.yCoord, this.mobSpawnerEntity.zCoord);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRun() {
/* 58 */     if (getSpawnerWorld().getBlockMetadata(getSpawnerX(), getSpawnerY(), getSpawnerZ()) == 15) {
/* 59 */       return false;
/*    */     }
/* 61 */     return super.canRun();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityMobSpawnerLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */