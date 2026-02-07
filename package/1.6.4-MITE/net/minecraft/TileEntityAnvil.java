/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityAnvil
/*    */   extends TileEntity
/*    */ {
/*    */   public int damage;
/*    */   
/*    */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 11 */     super.readFromNBT(par1NBTTagCompound);
/* 12 */     this.damage = par1NBTTagCompound.getInteger("damage");
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 17 */     super.writeToNBT(par1NBTTagCompound);
/* 18 */     par1NBTTagCompound.setInteger("damage", this.damage);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedInvName() {
/* 23 */     return "container.repair";
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDamage(World world, int x, int y, int z, int amount) {
/* 28 */     if (world.isRemote) {
/* 29 */       Minecraft.setErrorMessage("addDamage: why adding damage to anvil on client?");
/*    */     }
/* 31 */     this.damage += amount;
/*    */     
/* 33 */     int damage_stage = ((BlockAnvil)world.getBlock(this.xCoord, this.yCoord, this.zCoord)).getDamageStage(this.damage);
/*    */     
/* 35 */     if (damage_stage == 3) {
/*    */       
/* 37 */       world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setDroppedSelf(), false);
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     int metadata = world.getBlockMetadata(x, y, z) & 0x3;
/*    */     
/* 43 */     world.setBlockMetadataWithNotify(x, y, z, metadata | damage_stage << 2, 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityAnvil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */