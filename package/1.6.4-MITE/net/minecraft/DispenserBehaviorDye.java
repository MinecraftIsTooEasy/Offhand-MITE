/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class DispenserBehaviorDye
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   private boolean field_96461_b = true;
/*    */   
/*    */   protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 12 */     if (par2ItemStack.getItemSubtype() == 15) {
/*    */       
/* 14 */       EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 15 */       World var4 = par1IBlockSource.getWorld();
/* 16 */       int var5 = par1IBlockSource.getXInt() + var3.getFrontOffsetX();
/* 17 */       int var6 = par1IBlockSource.getYInt() + var3.getFrontOffsetY();
/* 18 */       int var7 = par1IBlockSource.getZInt() + var3.getFrontOffsetZ();
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
/* 32 */       boolean fertilization_successful = false;
/*    */       
/* 34 */       for (int dy = 0; dy >= -1; dy--) {
/*    */         
/* 36 */         if (dy == 0 && !var4.isAirOrPassableBlock(var5, var6 + dy, var7, false)) {
/*    */           break;
/*    */         }
/* 39 */         if (ItemDye.tryFertilize(par2ItemStack, var4, var5, var6 + dy, var7, EnumFace.TOP)) {
/*    */           
/* 41 */           fertilization_successful = true;
/* 42 */           var6 += dy;
/*    */           
/*    */           break;
/*    */         } 
/* 46 */         if (dy < 0 && !var4.isAirOrPassableBlock(var5, var6 + dy, var7, false)) {
/*    */           break;
/*    */         }
/*    */       } 
/* 50 */       if (fertilization_successful) {
/*    */         
/* 52 */         par2ItemStack.stackSize--;
/*    */         
/* 54 */         if (!var4.isRemote)
/*    */         {
/* 56 */           if (var4.getBlockId(var5, var6, var7) == Block.tilledField.blockID) {
/* 57 */             var6++;
/*    */           }
/* 59 */           var4.playAuxSFX(2005, var5, var6, var7, 0);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 64 */         this.field_96461_b = false;
/*    */       } 
/*    */       
/* 67 */       return par2ItemStack;
/*    */     } 
/*    */ 
/*    */     
/* 71 */     return super.dispenseStack(par1IBlockSource, par2ItemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void playDispenseSound(IBlockSource par1IBlockSource) {
/* 80 */     if (this.field_96461_b) {
/*    */       
/* 82 */       par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */     }
/*    */     else {
/*    */       
/* 86 */       par1IBlockSource.getWorld().playAuxSFX(1001, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviorDye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */