/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockRedstoneOre
/*     */   extends Block
/*     */ {
/*     */   private boolean glowing;
/*     */   
/*     */   public BlockRedstoneOre(int par1, boolean par2) {
/*  12 */     super(par1, Material.stone, new BlockConstants());
/*     */     
/*  14 */     if (par2)
/*     */     {
/*  16 */       setTickRandomly(true);
/*     */     }
/*     */     
/*  19 */     this.glowing = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  27 */     return 30;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
/*  35 */     glow(par1World, par2, par3, par4);
/*  36 */     super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  44 */     glow(par1World, par2, par3, par4);
/*  45 */     super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  59 */     if (!this.glowing) {
/*     */       
/*  61 */       if (player.onClient()) {
/*  62 */         sparkle(world, x, y, z);
/*     */       } else {
/*  64 */         glow(world, x, y, z);
/*     */       } 
/*  66 */       return true;
/*     */     } 
/*     */     
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockDamageStageChange(int x, int y, int z, Entity entity, int damage_stage) {
/*  74 */     if (entity == null || entity.onClient()) {
/*     */       return;
/*     */     }
/*  77 */     if (damage_stage > -1 && entity.worldObj.getBlock(x, y, z) == oreRedstone) {
/*  78 */       glow(entity.worldObj, x, y, z);
/*     */     }
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
/*     */   private void glow(World world, int x, int y, int z) {
/*  96 */     if (!world.isRemote && this == Block.oreRedstone)
/*     */     {
/*  98 */       world.setBlock(x, y, z, Block.oreRedstoneGlowing.blockID, world.getBlockMetadata(x, y, z), 3);
/*     */     }
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 114 */     if (this == Block.oreRedstoneGlowing)
/*     */     {
/* 116 */       return world.setBlock(x, y, z, Block.oreRedstone.blockID, world.getBlockMetadata(x, y, z), 3);
/*     */     }
/* 118 */     return false;
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
/*     */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {
/* 161 */     if (info.block == oreRedstoneGlowing) {
/* 162 */       info.setBlock(oreRedstone, info.getMetadata(), 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canBeCarried() {
/* 167 */     return !this.glowing;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 172 */     return dropBlockAsEntityItem(info, Item.redstone.itemID, 0, 3 + info.world.rand.nextInt(3), 1.0F + info.getHarvesterFortune() * 0.1F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 180 */     if (this.glowing)
/*     */     {
/* 182 */       sparkle(par1World, par2, par3, par4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sparkle(World par1World, int par2, int par3, int par4) {
/* 191 */     if (!par1World.isRemote) {
/*     */       return;
/*     */     }
/* 194 */     Random var5 = par1World.rand;
/* 195 */     double var6 = 0.0625D;
/*     */     
/* 197 */     for (int var8 = 0; var8 < 6; var8++) {
/*     */       
/* 199 */       double var9 = (par2 + var5.nextFloat());
/* 200 */       double var11 = (par3 + var5.nextFloat());
/* 201 */       double var13 = (par4 + var5.nextFloat());
/*     */       
/* 203 */       if (var8 == 0 && !par1World.isBlockStandardFormOpaqueCube(par2, par3 + 1, par4))
/*     */       {
/* 205 */         var11 = (par3 + 1) + var6;
/*     */       }
/*     */       
/* 208 */       if (var8 == 1 && !par1World.isBlockStandardFormOpaqueCube(par2, par3 - 1, par4))
/*     */       {
/* 210 */         var11 = (par3 + 0) - var6;
/*     */       }
/*     */       
/* 213 */       if (var8 == 2 && !par1World.isBlockStandardFormOpaqueCube(par2, par3, par4 + 1))
/*     */       {
/* 215 */         var13 = (par4 + 1) + var6;
/*     */       }
/*     */       
/* 218 */       if (var8 == 3 && !par1World.isBlockStandardFormOpaqueCube(par2, par3, par4 - 1))
/*     */       {
/* 220 */         var13 = (par4 + 0) - var6;
/*     */       }
/*     */       
/* 223 */       if (var8 == 4 && !par1World.isBlockStandardFormOpaqueCube(par2 + 1, par3, par4))
/*     */       {
/* 225 */         var9 = (par2 + 1) + var6;
/*     */       }
/*     */       
/* 228 */       if (var8 == 5 && !par1World.isBlockStandardFormOpaqueCube(par2 - 1, par3, par4))
/*     */       {
/* 230 */         var9 = (par2 + 0) - var6;
/*     */       }
/*     */       
/* 233 */       if (var9 < par2 || var9 > (par2 + 1) || var11 < 0.0D || var11 > (par3 + 1) || var13 < par4 || var13 > (par4 + 1))
/*     */       {
/*     */         
/* 236 */         par1World.spawnParticle(EnumParticle.reddust, var9, var11, var13, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int par1) {
/* 247 */     return new ItemStack(Block.oreRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 252 */     return this.glowing ? "lit" : "unlit";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneOre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */