/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityClayGolem
/*    */   extends EntityEarthElemental
/*    */ {
/*    */   public EntityClayGolem(World world) {
/*  9 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 14 */     super.applyEntityAttributes();
/*    */     
/* 16 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 6.0D);
/* 17 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 30.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getNaturalDefense() {
/* 22 */     return isHardenedClay() ? 2.0F : 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidBlock(Block block) {
/* 27 */     return (block == Block.blockClay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTypeForBlock(Block block, boolean heated) {
/* 32 */     setType(heated ? CLAY_HARDENED : CLAY_NORMAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMagma() {
/* 37 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityClayGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */