/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDragonPart
/*    */   extends Entity
/*    */ {
/*    */   public final IEntityMultiPart entityDragonObj;
/*    */   public final String name;
/*    */   
/*    */   public EntityDragonPart(IEntityMultiPart par1IEntityMultiPart, String par2Str, float par3, float par4) {
/* 13 */     super(par1IEntityMultiPart.func_82194_d());
/* 14 */     setSize(par3, par4);
/* 15 */     this.entityDragonObj = par1IEntityMultiPart;
/* 16 */     this.name = par2Str;
/*    */     
/* 18 */     this.renderDistanceWeight = 10.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void entityInit() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeCollidedWith() {
/* 38 */     return true;
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 57 */     return this.entityDragonObj.attackEntityFromPart(this, damage);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEntityEqual(Entity par1Entity) {
/* 65 */     return (this == par1Entity || this.entityDragonObj == par1Entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCatchFire() {
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByFire() {
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByLava() {
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDragonPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */