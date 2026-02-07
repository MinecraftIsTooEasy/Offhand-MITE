/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockBrewingStand
/*     */   extends BlockContainer
/*     */ {
/*   8 */   private Random rand = new Random();
/*     */   
/*     */   private Icon theIcon;
/*  11 */   private static final AxisAlignedBB[] multiple_bounds = new AxisAlignedBB[] { new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockBrewingStand(int par1) {
/*  20 */     super(par1, Material.iron, (new BlockConstants()).setNeverHidesAdjacentFaces());
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
/*     */   public int getRenderType() {
/*  37 */     return 25;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/*  45 */     return new TileEntityBrewingStand();
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  58 */     return multiple_bounds;
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
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/*  94 */     if (player.onServer()) {
/*     */       
/*  96 */       TileEntityBrewingStand tile_entity = (TileEntityBrewingStand)world.getBlockTileEntity(x, y, z);
/*     */       
/*  98 */       if (tile_entity != null) {
/*  99 */         player.displayGUIBrewingStand(tile_entity);
/*     */       }
/*     */     } 
/* 102 */     return true;
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
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 140 */     double var6 = (par2 + 0.4F + par5Random.nextFloat() * 0.2F);
/* 141 */     double var8 = (par3 + 0.7F + par5Random.nextFloat() * 0.3F);
/* 142 */     double var10 = (par4 + 0.4F + par5Random.nextFloat() * 0.2F);
/*     */     
/* 144 */     par1World.spawnParticle(EnumParticle.smoke, var6, var8, var10, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 154 */     TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);
/*     */     
/* 156 */     if (var7 instanceof TileEntityBrewingStand) {
/*     */       
/* 158 */       TileEntityBrewingStand var8 = (TileEntityBrewingStand)var7;
/*     */       
/* 160 */       for (int var9 = 0; var9 < var8.getSizeInventory(); var9++) {
/*     */         
/* 162 */         ItemStack var10 = var8.getStackInSlot(var9);
/*     */         
/* 164 */         if (var10 != null) {
/*     */           
/* 166 */           float var11 = this.rand.nextFloat() * 0.8F + 0.1F;
/* 167 */           float var12 = this.rand.nextFloat() * 0.8F + 0.1F;
/* 168 */           float var13 = this.rand.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 170 */           while (var10.stackSize > 0) {
/*     */             
/* 172 */             int var14 = this.rand.nextInt(21) + 10;
/*     */             
/* 174 */             if (var14 > var10.stackSize)
/*     */             {
/* 176 */               var14 = var10.stackSize;
/*     */             }
/*     */             
/* 179 */             var10.stackSize -= var14;
/*     */ 
/*     */             
/* 182 */             ItemStack item_stack = new ItemStack(var10.itemID, var14, var10.getItemSubtype());
/*     */             
/* 184 */             if (var10.isItemDamaged()) {
/* 185 */               item_stack.setItemDamage(var10.getItemDamage());
/*     */             }
/* 187 */             EntityItem var15 = new EntityItem(par1World, (par2 + var11), (par3 + var12), (par4 + var13), item_stack);
/* 188 */             float var16 = 0.05F;
/* 189 */             var15.motionX = ((float)this.rand.nextGaussian() * var16);
/* 190 */             var15.motionY = ((float)this.rand.nextGaussian() * var16 + 0.2F);
/* 191 */             var15.motionZ = ((float)this.rand.nextGaussian() * var16);
/* 192 */             par1World.spawnEntityInWorld(var15);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
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
/* 214 */     return Item.brewingStand.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 232 */     return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 241 */     super.registerIcons(par1IconRegister);
/* 242 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_base");
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getBrewingStandIcon() {
/* 247 */     return this.theIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 257 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 262 */     return dropBlockAsEntityItem(info, Item.brewingStand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 267 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBrewingStand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */