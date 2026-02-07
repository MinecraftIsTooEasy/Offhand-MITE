/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class BehaviorDispenseArmor
/*    */   extends BehaviorDefaultDispenseItem
/*    */ {
/*    */   protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
/* 12 */     EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
/* 13 */     int var4 = par1IBlockSource.getXInt() + var3.getFrontOffsetX();
/* 14 */     int var5 = par1IBlockSource.getYInt() + var3.getFrontOffsetY();
/* 15 */     int var6 = par1IBlockSource.getZInt() + var3.getFrontOffsetZ();
/* 16 */     AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB(var4, var5, var6, (var4 + 1), (var5 + 1), (var6 + 1));
/* 17 */     List<EntityLivingBase> var8 = par1IBlockSource.getWorld().selectEntitiesWithinAABB(EntityLivingBase.class, var7, new EntitySelectorArmoredMob(par2ItemStack));
/*    */     
/* 19 */     if (var8.size() > 0) {
/*    */       
/* 21 */       EntityLivingBase var9 = var8.get(0);
/* 22 */       int var10 = (var9 instanceof EntityPlayer) ? 1 : 0;
/*    */       
/* 24 */       int var11 = EntityLiving.getEquipmentPosition(par2ItemStack);
/* 25 */       ItemStack var12 = par2ItemStack.copy();
/* 26 */       var12.stackSize = 1;
/* 27 */       var9.setCurrentItemOrArmor(var11 - var10, var12);
/*    */       
/* 29 */       if (var9 instanceof EntityLiving)
/*    */       {
/* 31 */         ((EntityLiving)var9).setEquipmentDropChance(var11, 2.0F);
/*    */       }
/*    */       
/* 34 */       par2ItemStack.stackSize--;
/* 35 */       return par2ItemStack;
/*    */     } 
/*    */ 
/*    */     
/* 39 */     return super.dispenseStack(par1IBlockSource, par2ItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BehaviorDispenseArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */