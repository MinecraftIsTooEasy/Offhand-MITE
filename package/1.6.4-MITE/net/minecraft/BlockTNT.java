/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTNT
/*     */   extends BlockFalling
/*     */ {
/*     */   private Icon field_94393_a;
/*     */   private Icon field_94392_b;
/*     */   
/*     */   public BlockTNT(int par1) {
/*  15 */     super(par1, Material.tnt, (new BlockConstants()).setNeverConnectsWithFence());
/*  16 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  24 */     return (par1 == 0) ? this.field_94392_b : ((par1 == 1) ? this.field_94393_a : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  32 */     super.onBlockAdded(par1World, par2, par3, par4);
/*     */     
/*  34 */     if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
/*     */     {
/*     */ 
/*     */       
/*  38 */       ignite(par1World, par2, par3, par4, (EntityLivingBase)null);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  59 */     if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
/*     */       
/*  61 */       ignite(world, x, y, z, (EntityLivingBase)null);
/*  62 */       return true;
/*     */     } 
/*     */     
/*  65 */     return false;
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
/*     */   public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
/*  81 */     if (!par1World.isRemote) {
/*     */       
/*  83 */       EntityTNTPrimed var6 = new EntityTNTPrimed(par1World, (par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), par5Explosion.getExplosivePlacedBy());
/*  84 */       var6.fuse = par1World.rand.nextInt(var6.fuse / 4) + var6.fuse / 8;
/*  85 */       par1World.spawnEntityInWorld(var6);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void primeTnt(World par1World, int par2, int par3, int par4, int par5, EntityLivingBase par6EntityLivingBase) {
/* 108 */     if (!par1World.isRemote)
/*     */     {
/* 110 */       if ((par5 & 0x1) == 1) {
/*     */         
/* 112 */         EntityTNTPrimed var7 = new EntityTNTPrimed(par1World, (par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), par6EntityLivingBase);
/* 113 */         par1World.spawnEntityInWorld(var7);
/* 114 */         par1World.playSoundAtEntity(var7, "random.fuse", 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ignite(World world, int x, int y, int z, EntityLivingBase igniter) {
/* 122 */     if (!world.isRemote) {
/*     */       
/* 124 */       primeTnt(world, x, y, z, 1, igniter);
/* 125 */       world.setBlockToAir(x, y, z);
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
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 170 */     if (par5Entity instanceof EntityArrow && !par1World.isRemote) {
/*     */       
/* 172 */       EntityArrow var6 = (EntityArrow)par5Entity;
/*     */       
/* 174 */       if (var6.isBurning()) {
/*     */         
/* 176 */         this; primeTnt(par1World, par2, par3, par4, 1, (var6.shootingEntity instanceof EntityLivingBase) ? (EntityLivingBase)var6.shootingEntity : null);
/* 177 */         par1World.setBlockToAir(par2, par3, par4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDropFromExplosion(Explosion par1Explosion) {
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 196 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/* 197 */     this.field_94393_a = par1IconRegister.registerIcon(getTextureName() + "_top");
/* 198 */     this.field_94392_b = par1IconRegister.registerIcon(getTextureName() + "_bottom");
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 203 */     if (info.getResponsiblePlayer() != null) {
/* 204 */       primeTnt(info.world, info.x, info.y, info.z, info.getMetadata(), info.getResponsiblePlayer());
/*     */     }
/* 206 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 211 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTNT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */