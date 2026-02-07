/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCauldron
/*     */   extends Block
/*     */ {
/*     */   private Icon field_94378_a;
/*     */   private Icon cauldronTopIcon;
/*     */   private Icon cauldronBottomIcon;
/*  12 */   private static final AxisAlignedBB[] multiple_bounds = getMultipleBounds();
/*  13 */   private static final AxisAlignedBB[] multiple_bounds_for_player_selection = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockCauldron(int par1) {
/*  18 */     super(par1, Material.iron, new BlockConstants());
/*     */     
/*  20 */     setMaxStackSize(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  25 */     return "Bits 1 and 2 used for water height";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  30 */     return (metadata >= 0 && metadata < 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  38 */     return (par1 == 1) ? this.cauldronTopIcon : ((par1 == 0) ? this.cauldronBottomIcon : this.blockIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  47 */     this.field_94378_a = par1IconRegister.registerIcon(getTextureName() + "_" + "inner");
/*  48 */     this.cauldronTopIcon = par1IconRegister.registerIcon(getTextureName() + "_top");
/*  49 */     this.cauldronBottomIcon = par1IconRegister.registerIcon(getTextureName() + "_" + "bottom");
/*  50 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName() + "_side");
/*     */   }
/*     */ 
/*     */   
/*     */   public static Icon getCauldronIcon(String par0Str) {
/*  55 */     return par0Str.equals("inner") ? Block.cauldron.field_94378_a : (par0Str.equals("bottom") ? Block.cauldron.cauldronBottomIcon : null);
/*     */   }
/*     */ 
/*     */   
/*     */   private static AxisAlignedBB[] getMultipleBounds() {
/*  60 */     float var8 = 0.125F;
/*     */     
/*  62 */     return new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, var8, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var8), new AxisAlignedBB((1.0F - var8), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, (1.0F - var8), 1.0D, 1.0D, 1.0D) };
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
/*     */   public Object getCollisionBounds(World world, int x, int y, int z, Entity entity) {
/*  74 */     return multiple_bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastCollision tryRaycastVsBlock(Raycast raycast, int x, int y, int z, Vec3 origin, Vec3 limit) {
/*  79 */     return raycast.isForPlayerSelection() ? tryRaycastVsStandardFormBounds(raycast, x, y, z, origin, limit) : super.tryRaycastVsBlock(raycast, x, y, z, origin, limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender(int item_damage) {
/*  88 */     setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
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
/* 105 */     return 24;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 198 */     if (world.isBlockFaceFlatAndSolid(x, y + 1, z, EnumFace.BOTTOM)) {
/* 199 */       return false;
/*     */     }
/* 201 */     ItemStack held_item = player.getHeldItemStack();
/*     */     
/* 203 */     if (held_item == null) {
/* 204 */       return false;
/*     */     }
/* 206 */     int volume_in_cauldron = func_111045_h_(world.getBlockMetadata(x, y, z));
/* 207 */     int volume_in_cauldron_before = volume_in_cauldron;
/*     */     
/* 209 */     int cauldron_max_volume = 3;
/*     */     
/* 211 */     boolean action_performed = false;
/*     */     
/* 213 */     Item item = held_item.getItem();
/*     */     
/* 215 */     if (item instanceof ItemVessel) {
/*     */       
/* 217 */       ItemVessel vessel = (ItemVessel)item;
/*     */       
/* 219 */       int vessel_volume = vessel.getStandardVolume();
/*     */       
/* 221 */       if (vessel_volume > 3) {
/* 222 */         vessel_volume = 3;
/*     */       }
/* 224 */       if (vessel.isEmpty()) {
/*     */         
/* 226 */         if (volume_in_cauldron >= vessel_volume)
/*     */         {
/* 228 */           if (player.onClient()) {
/* 229 */             return true;
/*     */           }
/* 231 */           if (!player.inCreativeMode()) {
/* 232 */             player.inventory.convertOneOfCurrentItem(new ItemStack(vessel.getPeerForContents(Material.water)));
/*     */           }
/* 234 */           volume_in_cauldron -= vessel_volume;
/* 235 */           action_performed = true;
/*     */         }
/*     */       
/* 238 */       } else if (vessel.contains(Material.water)) {
/*     */         
/* 240 */         if (volume_in_cauldron < 3)
/*     */         {
/* 242 */           if (player.onClient()) {
/* 243 */             return true;
/*     */           }
/* 245 */           if (!player.inCreativeMode()) {
/* 246 */             player.inventory.convertOneOfCurrentItem(new ItemStack(vessel.getEmptyVessel()));
/*     */           }
/* 248 */           volume_in_cauldron = MathHelper.clamp_int(volume_in_cauldron + vessel_volume, 0, 3);
/* 249 */           action_performed = true;
/*     */         }
/*     */       
/*     */       } 
/* 253 */     } else if (item == Item.glassBottle) {
/*     */       
/* 255 */       if (volume_in_cauldron > 0)
/*     */       {
/* 257 */         if (player.onClient()) {
/* 258 */           return true;
/*     */         }
/* 260 */         if (!player.inCreativeMode()) {
/* 261 */           player.inventory.convertOneOfCurrentItem(new ItemStack(Item.potion, 1, 0));
/*     */         }
/* 263 */         volume_in_cauldron--;
/* 264 */         action_performed = true;
/*     */       }
/*     */     
/* 267 */     } else if (item == Item.potion && held_item.getItemSubtype() == 0) {
/*     */       
/* 269 */       if (volume_in_cauldron < 3)
/*     */       {
/* 271 */         if (player.onClient()) {
/* 272 */           return true;
/*     */         }
/* 274 */         if (!player.inCreativeMode()) {
/* 275 */           player.inventory.convertOneOfCurrentItem(new ItemStack(Item.glassBottle));
/*     */         }
/* 277 */         volume_in_cauldron++;
/* 278 */         action_performed = true;
/*     */       }
/*     */     
/* 281 */     } else if (item instanceof ItemArmor) {
/*     */       
/* 283 */       ItemArmor armor = (ItemArmor)item;
/*     */       
/* 285 */       if (armor.hasColor(held_item) && volume_in_cauldron > 0) {
/*     */         
/* 287 */         if (player.onClient()) {
/* 288 */           return true;
/*     */         }
/* 290 */         armor.removeColor(held_item);
/*     */         
/* 292 */         volume_in_cauldron--;
/* 293 */         action_performed = true;
/*     */       } 
/*     */     } 
/*     */     
/* 297 */     if (player.onServer() && volume_in_cauldron != volume_in_cauldron_before) {
/*     */       
/* 299 */       world.setBlockMetadataWithNotify(x, y, z, volume_in_cauldron, 2);
/* 300 */       world.func_96440_m(x, y, z, this.blockID);
/*     */     } 
/*     */     
/* 303 */     return action_performed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillWithRain(World par1World, int par2, int par3, int par4) {
/* 311 */     if (par1World.rand.nextInt(20) == 1) {
/*     */       
/* 313 */       int var5 = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/* 315 */       if (var5 < 3)
/*     */       {
/* 317 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var5 + 1, 2);
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 335 */     return Item.cauldron.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 344 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
/* 353 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 354 */     return func_111045_h_(var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_111045_h_(int par0) {
/* 359 */     return par0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 369 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 374 */     return dropBlockAsEntityItem(info, Item.cauldron);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 379 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePlacedOnBlock(int metadata, Block block_below, int block_below_metadata, double block_below_bounds_max_y) {
/* 384 */     return ((block_below.isTopFlatAndSolid(block_below_metadata) || block_below == tilledField) && super.canBePlacedOnBlock(metadata, block_below, block_below_metadata, block_below_bounds_max_y));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hidesAdjacentSide(IBlockAccess block_access, int x, int y, int z, Block neighbor, int side) {
/* 389 */     return (side == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 394 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean blocksPrecipitation(boolean[] blocks_precipitation, int metadata) {
/* 399 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCauldron.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */