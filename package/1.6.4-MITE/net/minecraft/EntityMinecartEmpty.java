/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityMinecartEmpty
/*    */   extends EntityMinecart
/*    */ {
/*    */   public EntityMinecartEmpty(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartEmpty(World par1World, double par2, double par4, double par6) {
/* 12 */     super(par1World, par2, par4, par6);
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
/*    */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 41 */     if (this.riddenByEntity != null) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (player.onServer()) {
/* 45 */       player.mountEntity(this);
/*    */     }
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinecartType() {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getModelItem() {
/* 57 */     return Item.minecartEmpty;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */