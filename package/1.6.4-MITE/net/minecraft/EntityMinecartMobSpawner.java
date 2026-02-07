/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityMinecartMobSpawner
/*    */   extends EntityMinecart
/*    */ {
/*  6 */   private final MobSpawnerBaseLogic mobSpawnerLogic = new EntityMinecartMobSpawnerLogic(this);
/*    */ 
/*    */   
/*    */   public EntityMinecartMobSpawner(World par1World) {
/* 10 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartMobSpawner(World par1World, double par2, double par4, double par6) {
/* 15 */     super(par1World, par2, par4, par6);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinecartType() {
/* 20 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getDefaultDisplayTile() {
/* 25 */     return Block.mobSpawner;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 33 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 34 */     this.mobSpawnerLogic.readFromNBT(par1NBTTagCompound);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 42 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 43 */     this.mobSpawnerLogic.writeToNBT(par1NBTTagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleHealthUpdate(byte par1) {
/* 48 */     this.mobSpawnerLogic.setDelayToMin(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 56 */     super.onUpdate();
/* 57 */     this.mobSpawnerLogic.updateSpawner();
/*    */   }
/*    */ 
/*    */   
/*    */   public MobSpawnerBaseLogic func_98039_d() {
/* 62 */     return this.mobSpawnerLogic;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCatchFire() {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByFire() {
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByLava() {
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartMobSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */