/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ class EntityMinecartMobSpawnerLogic
/*    */   extends MobSpawnerBaseLogic
/*    */ {
/*    */   final EntityMinecartMobSpawner spawnerMinecart;
/*    */   
/*    */   EntityMinecartMobSpawnerLogic(EntityMinecartMobSpawner par1EntityMinecartMobSpawner) {
/* 10 */     this.spawnerMinecart = par1EntityMinecartMobSpawner;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_98267_a(EnumEntityState par1) {
/* 20 */     this.spawnerMinecart.worldObj.setEntityState(this.spawnerMinecart, par1);
/*    */   }
/*    */ 
/*    */   
/*    */   public World getSpawnerWorld() {
/* 25 */     return this.spawnerMinecart.worldObj;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerX() {
/* 30 */     return MathHelper.floor_double(this.spawnerMinecart.posX);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerY() {
/* 35 */     return MathHelper.floor_double(this.spawnerMinecart.posY);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSpawnerZ() {
/* 40 */     return MathHelper.floor_double(this.spawnerMinecart.posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartMobSpawnerLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */