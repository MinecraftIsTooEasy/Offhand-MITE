/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public final class BlockNetherStalk
/*     */   extends BlockPlant
/*     */ {
/*     */   private Icon[] iconArray;
/*     */   
/*     */   protected BlockNetherStalk(int par1) {
/*  12 */     super(par1);
/*  13 */     setTickRandomly(true);
/*  14 */     float var2 = 0.5F;
/*  15 */     setBlockBoundsForAllThreads((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), 0.25D, (0.5F + var2));
/*  16 */     setCreativeTab((CreativeTabs)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  21 */     return "Bits 1 and 2 used to track growth";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  26 */     return (metadata >= 0 && metadata < 4);
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
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  40 */     return (block_below == slowSand);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValue() {
/*  45 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxAllowedLightValue() {
/*  50 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedAt(World world, int x, int y, int z, int metadata) {
/*  55 */     if (world.isOutdoors(x, y, z)) {
/*  56 */       return false;
/*     */     }
/*  58 */     return super.canBePlacedAt(world, x, y, z, metadata);
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
/*     */   public int tickRate(World par1World) {
/*  72 */     return 200;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  93 */     if (super.updateTick(world, x, y, z, random)) {
/*  94 */       return true;
/*     */     }
/*  96 */     if (!world.provider.isHellWorld) {
/*     */       
/*  98 */       dropBlockAsEntityItem((new BlockBreakInfo(world, x, y, z)).setDroppedSelf());
/*  99 */       world.setBlockToAir(x, y, z);
/*     */       
/* 101 */       return true;
/*     */     } 
/*     */     
/* 104 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 106 */     if (metadata < 3 && random.nextInt(40) == 0) {
/* 107 */       return world.setBlockMetadataWithNotify(x, y, z, ++metadata, 2);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 117 */     return (par2 >= 3) ? this.iconArray[2] : ((par2 > 0) ? this.iconArray[1] : this.iconArray[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 125 */     return 6;
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
/*     */   public boolean canBeCarried() {
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 161 */     int quantity = 1;
/*     */     
/* 163 */     if (info.getMetadata() >= 3) {
/*     */       
/* 165 */       quantity = 2 + info.world.rand.nextInt(3);
/*     */       
/* 167 */       int fortune = info.getHarvesterFortune();
/*     */       
/* 169 */       if (fortune > 0) {
/* 170 */         quantity += info.world.rand.nextInt(fortune + 1);
/*     */       }
/*     */     } 
/* 173 */     return dropBlockAsEntityItem(info, Item.netherStalkSeeds.itemID, 0, quantity, 1.0F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 197 */     return Item.netherStalkSeeds.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 206 */     this.iconArray = new Icon[3];
/*     */     
/* 208 */     for (int var2 = 0; var2 < this.iconArray.length; var2++)
/*     */     {
/* 210 */       this.iconArray[var2] = par1IconRegister.registerIcon(getTextureName() + "_stage_" + var2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockNetherStalk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */