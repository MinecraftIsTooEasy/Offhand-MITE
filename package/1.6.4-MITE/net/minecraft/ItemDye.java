/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class ItemDye
/*     */   extends Item
/*     */ {
/*   8 */   public static final String[] dyeColorNames = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
/*   9 */   public static final String[] dyeItemNames = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white" }; private Icon[] dyeIcons; public static boolean suppress_standard_particle_effect; public static final int BLACK = 0; public static final int RED = 1; public static final int GREEN = 2;
/*  10 */   public static final int[] dyeColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
/*     */   
/*     */   public static final int BROWN = 3;
/*     */   
/*     */   public static final int BLUE = 4;
/*     */   
/*     */   public static final int PURPLE = 5;
/*     */   public static final int CYAN = 6;
/*     */   
/*     */   public ItemDye(int par1) {
/*  20 */     super(par1, Material.dye, "dye_powder");
/*     */ 
/*     */ 
/*     */     
/*  24 */     setCreativeTab(CreativeTabs.tabMaterials);
/*     */     
/*  26 */     setCraftingDifficultyAsComponent(25.0F);
/*     */   }
/*     */   public static final int SILVER = 7;
/*     */   public static final int GRAY = 8;
/*     */   public static final int PINK = 9;
/*     */   public static final int LIME = 10;
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/*  34 */     int var2 = MathHelper.clamp_int(par1, 0, 15);
/*  35 */     return this.dyeIcons[var2];
/*     */   }
/*     */   public static final int YELLOW = 11;
/*     */   public static final int LIGHT_BLUE = 12;
/*     */   public static final int MAGENTA = 13;
/*     */   public static final int ORANGE = 14;
/*     */   public static final int WHITE = 15;
/*     */   
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/*  44 */     if (par1ItemStack == null) {
/*  45 */       return getUnlocalizedName();
/*     */     }
/*  47 */     int var2 = MathHelper.clamp_int(par1ItemStack.getItemSubtype(), 0, 15);
/*  48 */     return getUnlocalizedName() + "." + dyeColorNames[var2];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceReward(int subtype) {
/*  53 */     return (subtype == 4) ? 5 : 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/* 185 */     if (entity instanceof EntitySheep) {
/*     */       
/* 187 */       EntitySheep sheep = (EntitySheep)entity;
/*     */       
/* 189 */       if (sheep.tryDyeing(item_stack)) {
/*     */         
/* 191 */         if (player.onServer())
/*     */         {
/* 193 */           if (!player.inCreativeMode()) {
/* 194 */             player.convertOneOfHeldItem(null);
/*     */           }
/*     */         }
/* 197 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 206 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/* 208 */     if (rc == null || !rc.isBlock()) {
/*     */       
/* 210 */       ItemStack itemStack = player.getHeldItemStack();
/*     */       
/* 212 */       if (itemStack.getItemSubtype() == 4) {
/* 213 */         return ItemRock.onItemRightClick(player, itemStack, partial_tick, ctrl_is_down);
/*     */       }
/* 215 */       return false;
/*     */     } 
/*     */     
/* 218 */     World world = rc.world;
/*     */     
/* 220 */     int x = rc.block_hit_x;
/* 221 */     int y = rc.block_hit_y;
/* 222 */     int z = rc.block_hit_z;
/*     */     
/* 224 */     ItemStack item_stack = player.getHeldItemStack();
/*     */     
/* 226 */     if (!player.canPlayerEdit(x, y, z, item_stack)) {
/* 227 */       return false;
/*     */     }
/* 229 */     if (item_stack.getItemSubtype() == 15) {
/*     */       
/* 231 */       if (tryFertilize(item_stack, world, x, y, z, rc.face_hit))
/*     */       {
/* 233 */         if (player.onServer() && !player.inCreativeMode()) {
/* 234 */           player.convertOneOfHeldItem(null);
/*     */         }
/* 236 */         if (suppress_standard_particle_effect) {
/*     */           
/* 238 */           suppress_standard_particle_effect = false;
/* 239 */           return true;
/*     */         } 
/*     */         
/* 242 */         if (player.onServer()) {
/*     */           
/* 244 */           Block block = rc.getBlockHit();
/*     */           
/* 246 */           if (block == Block.grass || block == Block.tilledField || block == Block.mycelium) {
/* 247 */             y++;
/*     */           }
/* 249 */           world.playAuxSFX(2005, x, y, z, 0);
/*     */         } 
/*     */         
/* 252 */         if (world.getBlock(x, y, z) instanceof BlockCrops) {
/* 253 */           player.triggerAchievement(AchievementList.plantDoctor);
/*     */         }
/* 255 */         return true;
/*     */       }
/*     */     
/* 258 */     } else if (item_stack.getItemSubtype() == 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       return player.tryPlaceHeldItemAsBlock(rc, Block.cocoaPlant);
/*     */     } 
/*     */     
/* 301 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean tryFertilize(ItemStack item_stack, World world, int x, int y, int z, EnumFace face) {
/* 473 */     Block block = Block.blocksList[world.getBlockId(x, y, z)];
/* 474 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 476 */     if (block == Block.grass) {
/*     */       
/* 478 */       BlockGrass grass = (BlockGrass)block;
/*     */       
/* 480 */       return grass.fertilize(world, x, y, z, item_stack);
/*     */     } 
/* 482 */     if (block instanceof BlockCrops) {
/*     */       
/* 484 */       BlockCrops crops = (BlockCrops)block;
/*     */       
/* 486 */       return crops.fertilize(world, x, y, z, item_stack);
/*     */     } 
/*     */ 
/*     */     
/* 490 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void func_96603_a(World par0World, int par1, int par2, int par3, int par4) {
/* 705 */     int var5 = par0World.getBlockId(par1, par2, par3);
/*     */     
/* 707 */     if (par4 == 0) {
/* 708 */       par4 = 15;
/*     */     }
/* 710 */     Block var6 = (var5 > 0 && var5 < Block.blocksList.length) ? Block.blocksList[var5] : null;
/*     */     
/* 712 */     if (var6 != null) {
/* 713 */       var6.setBlockBoundsBasedOnStateAndNeighbors(par0World, par1, par2, par3);
/*     */     }
/* 715 */     int index = Minecraft.getThreadIndex();
/*     */     
/* 717 */     for (int var7 = 0; var7 < par4; var7++) {
/*     */       
/* 719 */       double var8 = itemRand.nextGaussian() * 0.02D;
/* 720 */       double var10 = itemRand.nextGaussian() * 0.02D;
/* 721 */       double var12 = itemRand.nextGaussian() * 0.02D;
/* 722 */       par0World.spawnParticle(EnumParticle.happyVillager, (par1 + itemRand.nextFloat()), par2 + itemRand.nextFloat() * ((var6 == null) ? 1.0D : var6.getBlockBoundsMaxY(index)), (par3 + itemRand.nextFloat()), var8, var10, var12);
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
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 755 */     for (int var4 = 0; var4 < 16; var4++)
/*     */     {
/* 757 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 763 */     this.dyeIcons = new Icon[dyeItemNames.length];
/*     */     
/* 765 */     for (int var2 = 0; var2 < dyeItemNames.length; var2++)
/*     */     {
/* 767 */       this.dyeIcons[var2] = par1IconRegister.registerIcon(getIconString() + "_" + dyeItemNames[var2]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 773 */     int xp_value = ItemRock.getExperienceValueWhenSacrificed(item_stack);
/*     */     
/* 775 */     if (extended_info && xp_value > 0)
/*     */     {
/* 777 */       info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("item.tooltip.XPEach", new Object[] { Integer.valueOf(xp_value) }));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemDye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */