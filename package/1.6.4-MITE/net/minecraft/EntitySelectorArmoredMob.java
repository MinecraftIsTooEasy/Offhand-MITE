/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntitySelectorArmoredMob
/*    */   implements IEntitySelector
/*    */ {
/*    */   private final ItemStack field_96567_c;
/*    */   
/*    */   public EntitySelectorArmoredMob(ItemStack par1ItemStack) {
/*  9 */     this.field_96567_c = par1ItemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEntityApplicable(Entity par1Entity) {
/* 17 */     if (!par1Entity.isEntityAlive())
/*    */     {
/* 19 */       return false;
/*    */     }
/* 21 */     if (!(par1Entity instanceof EntityLivingBase))
/*    */     {
/* 23 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 27 */     EntityLivingBase var2 = (EntityLivingBase)par1Entity;
/*    */     
/* 29 */     return (var2.getCurrentItemOrArmor(EntityLiving.getEquipmentPosition(this.field_96567_c)) != null) ? false : ((var2 instanceof EntityLiving) ? ((EntityLiving)var2).canPickUpLoot() : (var2 instanceof EntityPlayer));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySelectorArmoredMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */