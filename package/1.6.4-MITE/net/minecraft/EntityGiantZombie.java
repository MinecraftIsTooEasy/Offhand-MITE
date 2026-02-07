/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityGiantZombie
/*    */   extends EntityMob
/*    */ {
/*    */   public EntityGiantZombie(World world) {
/*  8 */     super(world);
/*  9 */     this.yOffset *= 6.0F;
/* 10 */     setSize(this.width * 6.0F, this.height * 6.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 15 */     super.applyEntityAttributes();
/*    */     
/* 17 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
/* 18 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
/* 19 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(50.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBlockPathWeight(int i, int j, int k) {
/* 24 */     return this.worldObj.getLightBrightness(i, j, k) - 0.5F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityGiantZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */