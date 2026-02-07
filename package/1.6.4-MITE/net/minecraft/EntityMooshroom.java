/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityMooshroom
/*    */   extends EntityCow
/*    */ {
/*    */   public EntityMooshroom(World par1World) {
/*  7 */     super(par1World);
/*  8 */     setSize(0.9F, 1.3F);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityMooshroom func_94900_c(EntityAgeable par1EntityAgeable) {
/* 65 */     return new EntityMooshroom(this.worldObj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityCow spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 73 */     return func_94900_c(par1EntityAgeable);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 78 */     return func_94900_c(par1EntityAgeable);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMooshroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */