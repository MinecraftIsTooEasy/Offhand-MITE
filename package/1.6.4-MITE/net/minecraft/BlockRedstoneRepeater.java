/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockRedstoneRepeater
/*     */   extends BlockRedstoneLogic
/*     */ {
/*   8 */   public static final double[] repeaterTorchOffset = new double[] { -0.0625D, 0.0625D, 0.1875D, 0.3125D };
/*     */ 
/*     */   
/*  11 */   private static final int[] repeaterState = new int[] { 1, 2, 3, 4 };
/*     */ 
/*     */   
/*     */   protected BlockRedstoneRepeater(int par1, boolean par2) {
/*  15 */     super(par1, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  20 */     return "Bits 1 and 2 used for orientation, bits 4 and 8 used for switch position";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  25 */     return (metadata >= 0 && metadata < 16);
/*     */   }
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  42 */     if (player.onServer()) {
/*     */       
/*  44 */       int metadata = world.getBlockMetadata(x, y, z);
/*  45 */       int var11 = (metadata & 0xC) >> 2;
/*     */       
/*  47 */       var11 = var11 + 1 << 2 & 0xC;
/*     */       
/*  49 */       world.setBlockMetadataWithNotify(x, y, z, var11 | metadata & 0x3, 3);
/*     */       
/*  51 */       world.playSoundAtBlock(x, y, z, "random.click", 0.3F, 0.5F);
/*     */     } 
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_94481_j_(int par1) {
/*  59 */     return repeaterState[(par1 & 0xC) >> 2] * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneLogic func_94485_e() {
/*  64 */     return Block.redstoneRepeaterActive;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneLogic func_94484_i() {
/*  69 */     return Block.redstoneRepeaterIdle;
/*     */   }
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/*  85 */     return Item.redstoneRepeater.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  93 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94476_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  98 */     return (func_94482_f(par1IBlockAccess, par2, par3, par4, par5) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_94477_d(int par1) {
/* 103 */     return isRedstoneRepeaterBlockID(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 111 */     if (this.isRepeaterPowered) {
/*     */       
/* 113 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 114 */       int var7 = j(var6);
/* 115 */       double var8 = (par2 + 0.5F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 116 */       double var10 = (par3 + 0.4F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 117 */       double var12 = (par4 + 0.5F) + (par5Random.nextFloat() - 0.5F) * 0.2D;
/* 118 */       double var14 = 0.0D;
/* 119 */       double var16 = 0.0D;
/*     */       
/* 121 */       if (par5Random.nextInt(2) == 0) {
/*     */         
/* 123 */         switch (var7) {
/*     */           
/*     */           case 0:
/* 126 */             var16 = -0.3125D;
/*     */             break;
/*     */           
/*     */           case 1:
/* 130 */             var14 = 0.3125D;
/*     */             break;
/*     */           
/*     */           case 2:
/* 134 */             var16 = 0.3125D;
/*     */             break;
/*     */           
/*     */           case 3:
/* 138 */             var14 = -0.3125D;
/*     */             break;
/*     */         } 
/*     */       
/*     */       } else {
/* 143 */         int var18 = (var6 & 0xC) >> 2;
/*     */         
/* 145 */         switch (var7) {
/*     */           
/*     */           case 0:
/* 148 */             var16 = repeaterTorchOffset[var18];
/*     */             break;
/*     */           
/*     */           case 1:
/* 152 */             var14 = -repeaterTorchOffset[var18];
/*     */             break;
/*     */           
/*     */           case 2:
/* 156 */             var16 = -repeaterTorchOffset[var18];
/*     */             break;
/*     */           
/*     */           case 3:
/* 160 */             var14 = repeaterTorchOffset[var18];
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/* 165 */       par1World.spawnParticle(EnumParticle.reddust, var8 + var14, var10, var12 + var16, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 176 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/* 177 */     func_94483_i_(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 187 */     return dropBlockAsEntityItem(info, Item.redstoneRepeater);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 192 */     return this.isRepeaterPowered ? "active" : "idle";
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 197 */     return getDirectionFacingStandard4(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 202 */     metadata &= 0xFFFFFFFC;
/* 203 */     metadata |= direction.isSouth() ? 0 : (direction.isWest() ? 1 : (direction.isNorth() ? 2 : (direction.isEast() ? 3 : -1)));
/*     */     
/* 205 */     return metadata;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneRepeater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */