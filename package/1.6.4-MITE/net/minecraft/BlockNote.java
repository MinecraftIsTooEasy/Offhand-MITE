/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class BlockNote
/*     */   extends BlockContainer
/*     */ {
/*     */   public BlockNote(int par1) {
/*   8 */     super(par1, Material.wood, new BlockConstants());
/*   9 */     setCreativeTab(CreativeTabs.tabRedstone);
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  34 */     boolean is_indirectly_powered = world.isBlockIndirectlyGettingPowered(x, y, z);
/*  35 */     TileEntityNote tile_entity = (TileEntityNote)world.getBlockTileEntity(x, y, z);
/*     */     
/*  37 */     if (tile_entity != null && tile_entity.previousRedstoneState != is_indirectly_powered) {
/*     */       
/*  39 */       if (is_indirectly_powered) {
/*  40 */         tile_entity.triggerNote(world, x, y, z);
/*     */       }
/*  42 */       tile_entity.previousRedstoneState = is_indirectly_powered;
/*     */     } 
/*     */     
/*  45 */     return false;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  73 */     if (player.onServer()) {
/*     */       
/*  75 */       TileEntityNote tile_entity = (TileEntityNote)world.getBlockTileEntity(x, y, z);
/*     */       
/*  77 */       if (tile_entity != null) {
/*     */         
/*  79 */         tile_entity.changePitch();
/*  80 */         tile_entity.triggerNote(world, x, y, z);
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
/*  92 */     if (!par1World.isRemote) {
/*     */       
/*  94 */       TileEntityNote var6 = (TileEntityNote)par1World.getBlockTileEntity(par2, par3, par4);
/*     */       
/*  96 */       if (var6 != null)
/*     */       {
/*  98 */         var6.triggerNote(par1World, par2, par3, par4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 108 */     return new TileEntityNote();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 117 */     float var7 = (float)Math.pow(2.0D, (par6 - 12) / 12.0D);
/* 118 */     String var8 = "harp";
/*     */     
/* 120 */     if (par5 == 1)
/*     */     {
/* 122 */       var8 = "bd";
/*     */     }
/*     */     
/* 125 */     if (par5 == 2)
/*     */     {
/* 127 */       var8 = "snare";
/*     */     }
/*     */     
/* 130 */     if (par5 == 3)
/*     */     {
/* 132 */       var8 = "hat";
/*     */     }
/*     */     
/* 135 */     if (par5 == 4)
/*     */     {
/* 137 */       var8 = "bassattack";
/*     */     }
/*     */     
/* 140 */     par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "note." + var8, 3.0F, var7);
/*     */     
/* 142 */     par1World.spawnParticle(EnumParticle.note, par2 + 0.5D, par3 + 1.2D, par4 + 0.5D, par6 / 24.0D, 0.0D, 0.0D);
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 148 */     item_block.addMaterial(new Material[] { Material.wood, Material.redstone });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */