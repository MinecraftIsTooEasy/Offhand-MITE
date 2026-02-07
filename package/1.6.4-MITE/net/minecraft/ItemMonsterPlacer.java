/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMonsterPlacer
/*     */   extends Item
/*     */ {
/*     */   private Icon theIcon;
/*     */   
/*     */   public ItemMonsterPlacer(int par1) {
/*  14 */     super(par1, Material.iron, "spawn_egg");
/*     */ 
/*     */     
/*  17 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemDisplayName(ItemStack par1ItemStack) {
/*  22 */     String var2 = ("" + StatCollector.translateToLocal(getUnlocalizedName() + ".name")).trim();
/*     */     
/*  24 */     if (par1ItemStack == null) {
/*  25 */       return var2;
/*     */     }
/*  27 */     String var3 = EntityList.getStringFromID(par1ItemStack.getItemSubtype());
/*     */     
/*  29 */     if (var3 != null)
/*     */     {
/*  31 */       var2 = var2 + " " + StatCollector.translateToLocal("entity." + var3 + ".name");
/*     */     }
/*     */     
/*  34 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/*  39 */     EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(par1ItemStack.getItemSubtype()));
/*  40 */     return (var3 != null) ? ((par2 == 0) ? var3.primaryColor : var3.secondaryColor) : 16777215;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresMultipleRenderPasses() {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtypeForRenderPass(int par1, int par2) {
/*  53 */     return (par2 > 0) ? this.theIcon : super.getIconFromSubtypeForRenderPass(par1, par2);
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
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/* 195 */     if (entity instanceof EntityAgeable) {
/*     */       
/* 197 */       Class entity_class = EntityList.getClassFromID(item_stack.getItemSubtype());
/*     */       
/* 199 */       if (entity_class == null || !entity_class.isAssignableFrom(entity.getClass())) {
/* 200 */         return false;
/*     */       }
/* 202 */       if (player.onClient()) {
/* 203 */         return true;
/*     */       }
/* 205 */       EntityAgeable entity_ageable = (EntityAgeable)entity;
/*     */       
/* 207 */       EntityAgeable newborn = entity_ageable.createChild(entity_ageable);
/*     */       
/* 209 */       if (newborn == null) {
/* 210 */         return false;
/*     */       }
/* 212 */       newborn.setGrowingAgeToNewborn();
/* 213 */       newborn.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, 0.0F, 0.0F);
/* 214 */       entity.worldObj.spawnEntityInWorld(newborn);
/*     */       
/* 216 */       if (item_stack.hasDisplayName()) {
/* 217 */         newborn.setCustomNameTag(item_stack.getDisplayName());
/*     */       }
/* 219 */       if (!player.inCreativeMode()) {
/* 220 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */       }
/* 222 */       return true;
/*     */     } 
/*     */     
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 230 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/* 232 */     if (rc == null || !rc.isBlock()) {
/* 233 */       return false;
/*     */     }
/* 235 */     if (player.onClient()) {
/*     */       
/* 237 */       player.swingArm();
/*     */     } else {
/*     */       float offset_y;
/*     */ 
/*     */ 
/*     */       
/* 243 */       if (rc.face_hit.isTop()) {
/*     */         
/* 245 */         if (rc.getBlockHit() instanceof BlockFence || rc.getBlockHit() instanceof BlockWall)
/*     */         {
/* 247 */           offset_y = 0.5F;
/*     */         }
/* 249 */         else if (rc.getBlockHit() instanceof BlockFarmland)
/*     */         {
/* 251 */           offset_y = 0.0625F;
/*     */         }
/*     */         else
/*     */         {
/* 255 */           rc.getBlockHit().setBlockBoundsBasedOnStateAndNeighbors(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
/* 256 */           offset_y = (float)rc.getBlockHit().getBlockBoundsMaxY(Minecraft.getThreadIndex()) - 1.0F;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 261 */         offset_y = 0.0F;
/*     */       } 
/*     */       
/* 264 */       ItemStack item_stack = player.getHeldItemStack();
/*     */       
/* 266 */       if (rc.getBlockHit().isLiquid() && rc.face_hit.isTop() && EntityList.getClassFromID(item_stack.getItemSubtype()) == EntityEarthElemental.class) {
/* 267 */         rc.neighbor_block_y--;
/*     */       }
/* 269 */       Entity entity = spawnCreature(rc.world, item_stack.getItemSubtype(), (rc.neighbor_block_x + 0.5F), (rc.neighbor_block_y + offset_y), (rc.neighbor_block_z + 0.5F), (Minecraft.inDevMode() && !ctrl_is_down), rc.face_hit);
/*     */       
/* 271 */       if (entity != null) {
/*     */         
/* 273 */         if (entity instanceof EntityLivingBase && item_stack.hasDisplayName()) {
/* 274 */           ((EntityLiving)entity).setCustomNameTag(item_stack.getDisplayName());
/*     */         }
/* 276 */         if (!player.inCreativeMode()) {
/* 277 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/*     */     } 
/* 281 */     return true;
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
/*     */   public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6, boolean check_location, EnumFace face_hit) {
/* 299 */     if (!Minecraft.inDevMode()) {
/* 300 */       check_location = false;
/*     */     }
/* 302 */     if (!EntityList.entityEggs.containsKey(Integer.valueOf(par1)))
/*     */     {
/* 304 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 308 */     Entity var8 = null;
/*     */     
/* 310 */     for (int var9 = 0; var9 < 1; var9++) {
/*     */       
/* 312 */       var8 = EntityList.createEntityByID(par1, par0World);
/*     */       
/* 314 */       if (var8 != null && var8 instanceof EntityLivingBase) {
/*     */         
/* 316 */         if (face_hit == EnumFace.BOTTOM && var8.height > 1.0F) {
/* 317 */           par4 -= (var8.height - 1.0F);
/*     */         }
/* 319 */         EntityLiving var10 = (EntityLiving)var8;
/* 320 */         var8.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
/*     */         
/* 322 */         if (var10 instanceof EntityVillager) {
/* 323 */           check_location = false;
/*     */         }
/* 325 */         if (check_location) {
/*     */           
/* 327 */           double[] resulting_y_pos = new double[1];
/*     */           
/* 329 */           if (!SpawnerAnimals.canCreatureTypeSpawnAtLocation(var10.getCreatureType(), par0World, var10.getBlockPosX(), var10.getBlockPosY(), var10.getBlockPosZ(), false, resulting_y_pos)) {
/*     */             
/* 331 */             if (Minecraft.inDevMode()) {
/* 332 */               System.out.println("spawnCreature: canCreatureTypeSpawnAtLocation() returned false for " + var10.getBlockPosString());
/*     */             }
/* 334 */             return null;
/*     */           } 
/*     */           
/* 337 */           if (!var10.getCanSpawnHere(false)) {
/*     */             
/* 339 */             if (Minecraft.inDevMode()) {
/* 340 */               System.out.println("spawnCreature: getCanSpawnHere(false) returned false for " + var10.getBlockPosString());
/*     */             }
/* 342 */             return null;
/*     */           } 
/*     */           
/* 345 */           if (!var10.getCanSpawnHere(true)) {
/*     */             
/* 347 */             if (Minecraft.inDevMode()) {
/* 348 */               System.out.println("spawnCreature: getCanSpawnHere() returned false for " + var10.getBlockPosString() + " due to invalid light level (BLV=" + par0World.getBlockLightValue(var10.getBlockPosX(), var10.getBlockPosY(), var10.getBlockPosZ()) + ")");
/*     */             }
/* 350 */             return null;
/*     */           } 
/*     */         } 
/*     */         
/* 354 */         var10.rotationYawHead = var10.rotationYaw;
/* 355 */         var10.renderYawOffset = var10.rotationYaw;
/* 356 */         var10.onSpawnWithEgg((EntityLivingData)null);
/* 357 */         par0World.spawnEntityInWorld(var8);
/*     */         
/* 359 */         var10.makeLivingSound();
/*     */         
/* 361 */         if (Minecraft.inDevMode()) {
/* 362 */           System.out.println("Spawning " + var10.getEntityName() + " from egg, UUID=" + var10.getUniqueID());
/*     */         }
/*     */       } 
/*     */     } 
/* 366 */     return var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 375 */     Iterator<EntityEggInfo> var4 = EntityList.entityEggs.values().iterator();
/*     */     
/* 377 */     while (var4.hasNext()) {
/*     */       
/* 379 */       EntityEggInfo var5 = var4.next();
/* 380 */       par3List.add(new ItemStack(par1, 1, var5.spawnedID));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 386 */     super.registerIcons(par1IconRegister);
/* 387 */     this.theIcon = par1IconRegister.registerIcon(getIconString() + "_overlay");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMonsterPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */