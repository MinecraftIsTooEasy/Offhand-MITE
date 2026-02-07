/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRedstoneLight
/*     */   extends Block
/*     */ {
/*     */   private final boolean powered;
/*     */   
/*     */   public BlockRedstoneLight(int par1, boolean par2) {
/*  13 */     super(par1, Material.redstoneLight, (new BlockConstants()).setNeverConnectsWithFence());
/*  14 */     this.powered = par2;
/*     */     
/*  16 */     if (par2)
/*     */     {
/*  18 */       setLightValue(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  27 */     if (!par1World.isRemote)
/*     */     {
/*  29 */       if (this.powered && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
/*     */         
/*  31 */         par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 4);
/*     */       }
/*  33 */       else if (!this.powered && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
/*     */         
/*  35 */         par1World.setBlock(par2, par3, par4, Block.redstoneLampActive.blockID, 0, 2);
/*     */       } 
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  61 */     if (!world.isRemote)
/*     */     {
/*  63 */       if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
/*     */         
/*  65 */         world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
/*     */       }
/*  67 */       else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
/*     */         
/*  69 */         return world.setBlock(x, y, z, Block.redstoneLampActive.blockID, 0, 2);
/*     */       } 
/*     */     }
/*     */     
/*  73 */     return false;
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*  89 */     if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
/*  90 */       return world.setBlock(x, y, z, Block.redstoneLampIdle.blockID, 0, 2);
/*     */     }
/*  92 */     return false;
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
/* 108 */     return Block.redstoneLampIdle.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 113 */     return !this.powered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 120 */     if (info.wasExploded()) {
/* 121 */       return dropBlockAsEntityItem(info, Item.redstone.itemID, 0, 4, 0.25F);
/*     */     }
/* 123 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack createStackedBlock(int par1) {
/* 128 */     return new ItemStack(redstoneLampIdle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 133 */     item_block.addMaterial(new Material[] { Material.redstone, Material.glowstone });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 138 */     return this.powered ? "lit" : "unlit";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRedstoneLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */