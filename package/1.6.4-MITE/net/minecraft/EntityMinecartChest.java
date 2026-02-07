/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityMinecartChest
/*    */   extends EntityMinecartContainer
/*    */ {
/*    */   public EntityMinecartChest(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartChest(World par1World, double par2, double par4, double par6) {
/* 12 */     super(par1World, par2, par4, par6);
/*    */   }
/*    */ 
/*    */   
/*    */   public void killMinecart(DamageSource par1DamageSource) {
/* 17 */     super.killMinecart(par1DamageSource);
/* 18 */     dropItem(Block.chest.blockID, 1, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSizeInventory() {
/* 26 */     return 27;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinecartType() {
/* 31 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getDefaultDisplayTile() {
/* 36 */     return Block.chest;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDefaultDisplayTileOffset() {
/* 41 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getModelItem() {
/* 46 */     return Item.minecartCrate;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */