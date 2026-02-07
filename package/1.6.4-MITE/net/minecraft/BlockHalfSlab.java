/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockHalfSlab
/*     */   extends Block
/*     */ {
/*     */   protected final boolean isDoubleSlab;
/*     */   
/*     */   public BlockHalfSlab(int i, boolean bl, Material material) {
/*  19 */     super(i, material);
/*  20 */     this.isDoubleSlab = bl;
/*     */     
/*  22 */     if (bl) {
/*  23 */       opaqueCubeLookup[i] = true;
/*     */     } else {
/*  25 */       a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*  27 */     setLightOpacity(255);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int i, int j, int k) {
/*  32 */     if (this.isDoubleSlab) {
/*  33 */       a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } else {
/*  35 */       boolean bool = ((iBlockAccess.getBlockMetadata(i, j, k) & 0x8) != 0) ? true : false;
/*  36 */       if (bool) {
/*  37 */         a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       } else {
/*  39 */         a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  46 */     if (this.isDoubleSlab) {
/*  47 */       a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } else {
/*  49 */       a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisAlignedBB, List list, Entity entity) {
/*  55 */     setBlockBoundsBasedOnState(world, i, j, k);
/*  56 */     a(world, i, j, k, axisAlignedBB, list, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  61 */     return this.isDoubleSlab;
/*     */   }
/*     */ 
/*     */   
/*     */   public int onBlockPlaced(World world, int i, int j, int k, int l, float f, float g, float h, int m) {
/*  66 */     if (this.isDoubleSlab) return m;
/*     */     
/*  68 */     if (l == 0 || (l != 1 && g > 0.5D)) {
/*  69 */       return m | 0x8;
/*     */     }
/*  71 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  76 */     if (this.isDoubleSlab) {
/*  77 */       return 2;
/*     */     }
/*  79 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int damageDropped(int i) {
/*  84 */     return i & 0x7;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderAsNormalBlock() {
/*  89 */     return this.isDoubleSlab;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int i, int j, int k, int l) {
/*  94 */     if (this.isDoubleSlab) return super.shouldSideBeRendered(iBlockAccess, i, j, k, l);
/*     */     
/*  96 */     if (l != 1 && l != 0 && !super.shouldSideBeRendered(iBlockAccess, i, j, k, l)) {
/*  97 */       return false;
/*     */     }
/*     */     
/* 100 */     int m = i, n = j, i1 = k;
/* 101 */     m += Facing.offsetsXForSide[Facing.oppositeSide[l]];
/* 102 */     n += Facing.offsetsYForSide[Facing.oppositeSide[l]];
/* 103 */     i1 += Facing.offsetsZForSide[Facing.oppositeSide[l]];
/*     */     
/* 105 */     boolean bool = ((iBlockAccess.getBlockMetadata(m, n, i1) & 0x8) != 0) ? true : false;
/* 106 */     if (bool) {
/* 107 */       if (l == 0) return true; 
/* 108 */       if (l == 1 && super.shouldSideBeRendered(iBlockAccess, i, j, k, l)) return true; 
/* 109 */       return (!isBlockSingleSlab(iBlockAccess.getBlockId(i, j, k)) || (iBlockAccess.getBlockMetadata(i, j, k) & 0x8) == 0);
/*     */     } 
/* 111 */     if (l == 1) return true; 
/* 112 */     if (l == 0 && super.shouldSideBeRendered(iBlockAccess, i, j, k, l)) return true; 
/* 113 */     return (!isBlockSingleSlab(iBlockAccess.getBlockId(i, j, k)) || (iBlockAccess.getBlockMetadata(i, j, k) & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isBlockSingleSlab(int i) {
/* 118 */     return (i == Block.ap.blockID || i == Block.bT.blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract String getFullSlabName(int paramInt);
/*     */   
/*     */   public int getDamageValue(World world, int i, int j, int k) {
/* 125 */     return h(world, i, j, k) & 0x7;
/*     */   }
/*     */ 
/*     */   
/*     */   public int idPicked(World world, int i, int j, int k) {
/* 130 */     if (isBlockSingleSlab(this.blockID)) {
/* 131 */       return this.blockID;
/*     */     }
/* 133 */     if (this.blockID == Block.ao.blockID) {
/* 134 */       return Block.ap.blockID;
/*     */     }
/* 136 */     if (this.blockID == Block.bS.blockID) {
/* 137 */       return Block.bT.blockID;
/*     */     }
/* 139 */     return Block.ap.blockID;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockHalfSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */