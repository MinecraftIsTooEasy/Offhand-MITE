/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemBoat
/*     */   extends Item
/*     */ {
/*     */   public ItemBoat(int par1) {
/*  10 */     super(par1, Material.wood, "boat");
/*     */     
/*  12 */     setMaxStackSize(1);
/*  13 */     setCreativeTab(CreativeTabs.tabTransport);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer par3EntityPlayer, float partial_tick, boolean ctrl_is_down) {
/*  22 */     World par2World = par3EntityPlayer.worldObj;
/*     */     
/*  24 */     float var4 = 1.0F;
/*  25 */     float var5 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * var4;
/*  26 */     float var6 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * var4;
/*  27 */     double var7 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * var4;
/*  28 */     double var9 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * var4 + 1.62D - par3EntityPlayer.yOffset;
/*  29 */     double var11 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * var4;
/*  30 */     Vec3 var13 = par2World.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
/*  31 */     float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
/*  32 */     float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
/*  33 */     float var16 = -MathHelper.cos(-var5 * 0.017453292F);
/*  34 */     float var17 = MathHelper.sin(-var5 * 0.017453292F);
/*  35 */     float var18 = var15 * var16;
/*  36 */     float var20 = var14 * var16;
/*  37 */     double var21 = 5.0D;
/*  38 */     Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
/*     */     
/*  40 */     RaycastCollision var24 = par2World.getBlockCollisionForSelection(var13, var23, true);
/*     */     
/*  42 */     if (var24 == null)
/*     */     {
/*     */       
/*  45 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  49 */     Vec3 var25 = par3EntityPlayer.getLook(var4);
/*  50 */     boolean var26 = false;
/*  51 */     float var27 = 1.0F;
/*  52 */     List<Entity> var28 = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.addCoord(var25.xCoord * var21, var25.yCoord * var21, var25.zCoord * var21).expand(var27, var27, var27));
/*     */     
/*     */     int var29;
/*  55 */     for (var29 = 0; var29 < var28.size(); var29++) {
/*     */       
/*  57 */       Entity var30 = var28.get(var29);
/*     */       
/*  59 */       if (var30.canBeCollidedWith()) {
/*     */ 
/*     */         
/*  62 */         float var31 = var30.getCollisionBorderSize(par3EntityPlayer);
/*  63 */         AxisAlignedBB var32 = var30.boundingBox.expand(var31, var31, var31);
/*     */         
/*  65 */         if (var32.isVecInside(var13))
/*     */         {
/*  67 */           var26 = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     if (var26)
/*     */     {
/*     */       
/*  75 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (var24.isBlock()) {
/*     */       
/*  82 */       var29 = var24.block_hit_x;
/*  83 */       int var33 = var24.block_hit_y;
/*  84 */       int var34 = var24.block_hit_z;
/*     */       
/*  86 */       if (par2World.getBlockId(var29, var33, var34) == Block.snow.blockID)
/*     */       {
/*  88 */         var33--;
/*     */       }
/*     */       
/*  91 */       EntityBoat var35 = new EntityBoat(par2World, (var29 + 0.5F), (var33 + 1.0F), (var34 + 0.5F));
/*  92 */       var35.rotationYaw = (((MathHelper.floor_double((par3EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) - 1) * 90);
/*     */       
/*  94 */       if (!par2World.getCollidingBoundingBoxes(var35, var35.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
/*     */       {
/*     */         
/*  97 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (par3EntityPlayer.onServer()) {
/*     */         
/* 115 */         par2World.spawnEntityInWorld(var35);
/*     */         
/* 117 */         if (!par3EntityPlayer.inCreativeMode()) {
/* 118 */           par3EntityPlayer.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 121 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBurnTime(ItemStack item_stack) {
/* 132 */     return 1200;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBoat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */