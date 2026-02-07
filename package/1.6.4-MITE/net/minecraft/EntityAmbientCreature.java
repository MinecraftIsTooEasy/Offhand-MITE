/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class EntityAmbientCreature
/*    */   extends EntityLiving
/*    */   implements IAnimals {
/*    */   public EntityAmbientCreature(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowLeashing() {
/* 12 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAmbientCreature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */