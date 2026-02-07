/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BlockPortal
/*      */   extends BlockBreakable
/*      */ {
/*      */   private Icon runegate_icon;
/*      */   private Icon nether_portal_icon;
/*      */   public static final int DESTINATION_OVERWORLD = 0;
/*      */   public static final int DESTINATION_UNDERWORLD = 1;
/*      */   public static final int DESTINATION_NETHER = 2;
/*      */   public static final int IS_RUNEGATE = 8;
/*      */   
/*      */   public BlockPortal(int par1) {
/*   21 */     super(par1, "portal", Material.portal, false, (new BlockConstants()).setUsesAlphaBlending());
/*   22 */     setTickRandomly(true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMetadataNotes() {
/*   28 */     return "0=To Overworld, 1=To Underworld, 2=To Nether, bit 8 set if Runegate";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isValidMetadata(int metadata) {
/*   34 */     return ((metadata >= 0 && metadata < 3) || (metadata >= 8 && metadata < 11));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/*   73 */     boolean changed_state = updateType(world, x, y, z);
/*      */     
/*   75 */     Class<EntityPigZombie> entity_class = null;
/*      */     
/*   77 */     if (world.isOverworld()) {
/*      */       
/*   79 */       if (isPortalToUnderworld(world.getBlockMetadata(x, y, z), false) && random.nextInt(100) == 0) {
/*   80 */         entity_class = random.nextBoolean() ? EntityCaveSpider.class : EntityVampireBat.class;
/*      */       }
/*   82 */     } else if (world.isUnderworld()) {
/*      */       
/*   84 */       if (isPortalToNether(world.getBlockMetadata(x, y, z), false) && random.nextInt(100) == 0) {
/*   85 */         entity_class = EntityPigZombie.class;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*   90 */     boolean flag = true;
/*      */     
/*   92 */     if (flag) {
/*   93 */       entity_class = null;
/*      */     }
/*      */ 
/*      */     
/*   97 */     if (entity_class != null) {
/*      */       
/*   99 */       List<ServerPlayer> players = (world.getAsWorldServer()).playerEntities;
/*      */       
/*  101 */       Iterator<ServerPlayer> i = players.iterator();
/*      */       
/*  103 */       while (i.hasNext()) {
/*      */         
/*  105 */         ServerPlayer player = i.next();
/*      */         
/*  107 */         if (player.ticks_since_portal_teleport < 1000) {
/*      */           
/*  109 */           entity_class = null;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  114 */       if (entity_class != null && world.getEntitiesWithinAABB(entity_class, world.getBoundingBoxFromPool(x, y, z).scale(16.0D)).size() > 3) {
/*  115 */         entity_class = null;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  121 */     if (entity_class != null) {
/*      */       
/*  123 */       int entity_id = EntityList.getEntityID(entity_class);
/*      */       
/*  125 */       if (entity_class == EntityVampireBat.class) {
/*      */         
/*  127 */         Entity var7 = ItemMonsterPlacer.spawnCreature(world, entity_id, x + 0.5D, y + 0.5D, z + 0.5D, false, EnumFace.TOP);
/*  128 */         var7.timeUntilPortal = var7.getPortalCooldown();
/*  129 */         var7.refreshDespawnCounter(-9600);
/*      */       } else {
/*      */         int var6;
/*      */ 
/*      */ 
/*      */         
/*  135 */         for (var6 = y; !world.isBlockTopFlatAndSolid(x, var6, z) && var6 > 0; var6--);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  140 */         if (var6 > 0 && !world.isBlockNormalCube(x, var6 + 1, z)) {
/*      */ 
/*      */           
/*  143 */           Entity var7 = ItemMonsterPlacer.spawnCreature(world, entity_id, x + 0.5D, var6 + 1.1D, z + 0.5D, false, EnumFace.TOP);
/*      */           
/*  145 */           if (var7 != null) {
/*      */             
/*  147 */             var7.timeUntilPortal = var7.getPortalCooldown();
/*  148 */             var7.refreshDespawnCounter(-9600);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  154 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  174 */     if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID && par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID) {
/*      */       
/*  176 */       float var5 = 0.125F;
/*  177 */       float var6 = 0.5F;
/*  178 */       setBlockBoundsForCurrentThread((0.5F - var5), 0.0D, (0.5F - var6), (0.5F + var5), 1.0D, (0.5F + var6));
/*      */     }
/*      */     else {
/*      */       
/*  182 */       float var5 = 0.5F;
/*  183 */       float var6 = 0.125F;
/*  184 */       setBlockBoundsForCurrentThread((0.5F - var5), 0.0D, (0.5F - var6), (0.5F + var5), 1.0D, (0.5F + var6));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
/*  210 */     if (par1World.isTheEnd()) {
/*  211 */       return false;
/*      */     }
/*  213 */     byte var5 = 0;
/*  214 */     byte var6 = 0;
/*      */     
/*  216 */     if (par1World.getBlockId(par2 - 1, par3, par4) == Block.obsidian.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.obsidian.blockID)
/*      */     {
/*  218 */       var5 = 1;
/*      */     }
/*      */     
/*  221 */     if (par1World.getBlockId(par2, par3, par4 - 1) == Block.obsidian.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.obsidian.blockID)
/*      */     {
/*  223 */       var6 = 1;
/*      */     }
/*      */     
/*  226 */     if (var5 == var6)
/*      */     {
/*  228 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  232 */     if (par1World.getBlockId(par2 - var5, par3, par4 - var6) == 0) {
/*      */       
/*  234 */       par2 -= var5;
/*  235 */       par4 -= var6;
/*      */     } 
/*      */ 
/*      */     
/*      */     int var7;
/*      */     
/*  241 */     for (var7 = -1; var7 <= 2; var7++) {
/*      */       
/*  243 */       for (int var8 = -1; var8 <= 3; var8++) {
/*      */         
/*  245 */         boolean var9 = (var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3);
/*      */         
/*  247 */         if ((var7 != -1 && var7 != 2) || (var8 != -1 && var8 != 3)) {
/*      */           
/*  249 */           int var10 = par1World.getBlockId(par2 + var5 * var7, par3 + var8, par4 + var6 * var7);
/*      */           
/*  251 */           if (var9) {
/*      */             
/*  253 */             if (var10 != Block.obsidian.blockID)
/*      */             {
/*  255 */               return false;
/*      */             
/*      */             }
/*      */           }
/*  259 */           else if (var10 != 0 && var10 != Block.fire.blockID && var10 != Block.spark.blockID) {
/*      */             
/*  261 */             return false;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  267 */     for (var7 = 0; var7 < 2; var7++) {
/*      */       
/*  269 */       for (int var8 = 0; var8 < 3; var8++)
/*      */       {
/*  271 */         par1World.setBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, Block.portal.blockID, 0, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  285 */     int metadata = getPortalTypeBasedOnLocation(par1World, par2, par3, par4, true);
/*      */     
/*  287 */     for (var7 = 0; var7 < 2; var7++) {
/*      */       
/*  289 */       for (int var8 = 0; var8 < 3; var8++)
/*      */       {
/*  291 */         par1World.setBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, Block.portal.blockID, metadata, 2);
/*      */       }
/*      */     } 
/*      */     
/*  295 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  370 */     byte var6 = 0;
/*  371 */     byte var7 = 1;
/*      */     
/*  373 */     if (world.getBlockId(x - 1, y, z) == this.blockID || world.getBlockId(x + 1, y, z) == this.blockID) {
/*      */       
/*  375 */       var6 = 1;
/*  376 */       var7 = 0;
/*      */     } 
/*      */     
/*      */     int var8;
/*      */     
/*  381 */     for (var8 = y; world.getBlockId(x, var8 - 1, z) == this.blockID; var8--);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  386 */     if (world.getBlockId(x, var8 - 1, z) != Block.obsidian.blockID)
/*      */     {
/*  388 */       return world.setBlockToAir(x, y, z);
/*      */     }
/*      */ 
/*      */     
/*      */     int var9;
/*      */     
/*  394 */     for (var9 = 1; var9 < 4 && world.getBlockId(x, var8 + var9, z) == this.blockID; var9++);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  399 */     if (var9 == 3 && world.getBlockId(x, var8 + var9, z) == Block.obsidian.blockID) {
/*      */       
/*  401 */       boolean var10 = (world.getBlockId(x - 1, y, z) == this.blockID || world.getBlockId(x + 1, y, z) == this.blockID);
/*  402 */       boolean var11 = (world.getBlockId(x, y, z - 1) == this.blockID || world.getBlockId(x, y, z + 1) == this.blockID);
/*      */       
/*  404 */       if (var10 && var11)
/*      */       {
/*  406 */         return world.setBlockToAir(x, y, z);
/*      */       }
/*      */ 
/*      */       
/*  410 */       if ((world.getBlockId(x + var6, y, z + var7) != Block.obsidian.blockID || world.getBlockId(x - var6, y, z - var7) != this.blockID) && (world.getBlockId(x - var6, y, z - var7) != Block.obsidian.blockID || world.getBlockId(x + var6, y, z + var7) != this.blockID))
/*      */       {
/*  412 */         return world.setBlockToAir(x, y, z);
/*      */       }
/*      */ 
/*      */       
/*  416 */       return updateType(world, x, y, z);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     return world.setBlockToAir(x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  433 */     if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID)
/*      */     {
/*  435 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  439 */     boolean var6 = (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID);
/*  440 */     boolean var7 = (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID);
/*  441 */     boolean var8 = (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID);
/*  442 */     boolean var9 = (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID);
/*  443 */     boolean var10 = (var6 || var7);
/*  444 */     boolean var11 = (var8 || var9);
/*  445 */     return (var10 && par5 == 4) ? true : ((var10 && par5 == 5) ? true : ((var11 && par5 == 2) ? true : ((var11 && par5 == 3))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderBlockPass() {
/*  462 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMinX(World world, int x, int y, int z) {
/*  467 */     boolean x_aligned = (world.getBlockId(x + 1, y, z) == this.blockID);
/*      */     
/*  469 */     int min_x = x;
/*      */     
/*  471 */     while (world.getBlockId(min_x - 1, y, z) == this.blockID) {
/*      */       
/*  473 */       min_x--;
/*  474 */       x_aligned = true;
/*      */     } 
/*      */ 
/*      */     
/*  478 */     return x_aligned ? (min_x - 1) : min_x;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMaxX(World world, int x, int y, int z) {
/*  483 */     boolean x_aligned = (world.getBlockId(x - 1, y, z) == this.blockID);
/*      */     
/*  485 */     int max_x = x;
/*      */     
/*  487 */     while (world.getBlockId(max_x + 1, y, z) == this.blockID) {
/*      */       
/*  489 */       max_x++;
/*  490 */       x_aligned = true;
/*      */     } 
/*      */ 
/*      */     
/*  494 */     return x_aligned ? (max_x + 1) : max_x;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMinY(World world, int x, int y, int z) {
/*  499 */     int min_y = y;
/*      */     
/*  501 */     while (world.getBlockId(x, min_y - 1, z) == this.blockID) {
/*  502 */       min_y--;
/*      */     }
/*  504 */     return min_y - 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMaxY(World world, int x, int y, int z) {
/*  509 */     int max_y = y;
/*      */     
/*  511 */     while (world.getBlockId(x, max_y + 1, z) == this.blockID) {
/*  512 */       max_y++;
/*      */     }
/*  514 */     return max_y + 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMinZ(World world, int x, int y, int z) {
/*  519 */     boolean z_aligned = (world.getBlockId(x, y, z + 1) == this.blockID);
/*      */     
/*  521 */     int min_z = z;
/*      */     
/*  523 */     while (world.getBlockId(x, y, min_z - 1) == this.blockID) {
/*      */       
/*  525 */       min_z--;
/*  526 */       z_aligned = true;
/*      */     } 
/*      */ 
/*      */     
/*  530 */     return z_aligned ? (min_z - 1) : min_z;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getFrameMaxZ(World world, int x, int y, int z) {
/*  535 */     boolean z_aligned = (world.getBlockId(x, y, z - 1) == this.blockID);
/*      */     
/*  537 */     int max_z = z;
/*      */     
/*  539 */     while (world.getBlockId(x, y, max_z + 1) == this.blockID) {
/*      */       
/*  541 */       max_z++;
/*  542 */       z_aligned = true;
/*      */     } 
/*      */ 
/*      */     
/*  546 */     return z_aligned ? (max_z + 1) : max_z;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getDestinationBit(int metadata) {
/*  552 */     return BitHelper.clearBit(metadata, 8);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMetadataForDestination(int destination, boolean is_runegate) {
/*  557 */     return destination | (is_runegate ? 8 : 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPortalToOverworld(int metadata, boolean include_runegates) {
/*  563 */     return ((include_runegates ? getDestinationBit(metadata) : metadata) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPortalToUnderworld(int metadata, boolean include_runegates) {
/*  569 */     return ((include_runegates ? getDestinationBit(metadata) : metadata) == true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPortalToNether(int metadata, boolean include_runegates) {
/*  575 */     return ((include_runegates ? getDestinationBit(metadata) : metadata) == 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRunegate(int metadata) {
/*  580 */     return BitHelper.isBitSet(metadata, 8);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPortalToOverworldSpawn(World world, int metadata) {
/*  586 */     return (world.isOverworld() && metadata == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockRunestone getRunegateType(World world, int x, int y, int z) {
/*  611 */     int frame_min_x = getFrameMinX(world, x, y, z);
/*  612 */     int frame_max_x = getFrameMaxX(world, x, y, z);
/*  613 */     int frame_min_y = getFrameMinY(world, x, y, z);
/*  614 */     int frame_max_y = getFrameMaxY(world, x, y, z);
/*  615 */     int frame_min_z = getFrameMinZ(world, x, y, z);
/*  616 */     int frame_max_z = getFrameMaxZ(world, x, y, z);
/*      */     
/*  618 */     if (frame_max_x - frame_min_x > frame_max_z - frame_min_z) {
/*      */       
/*  620 */       if (world.getBlock(frame_min_x, frame_min_y, z) == runestoneMithril && world.getBlock(frame_max_x, frame_min_y, z) == runestoneMithril && world.getBlock(frame_min_x, frame_max_y, z) == runestoneMithril && world.getBlock(frame_max_x, frame_max_y, z) == runestoneMithril)
/*  621 */         return runestoneMithril; 
/*  622 */       if (world.getBlock(frame_min_x, frame_min_y, z) == runestoneAdamantium && world.getBlock(frame_max_x, frame_min_y, z) == runestoneAdamantium && world.getBlock(frame_min_x, frame_max_y, z) == runestoneAdamantium && world.getBlock(frame_max_x, frame_max_y, z) == runestoneAdamantium) {
/*  623 */         return runestoneAdamantium;
/*      */       }
/*  625 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  629 */     if (world.getBlock(x, frame_min_y, frame_min_z) == runestoneMithril && world.getBlock(x, frame_min_y, frame_max_z) == runestoneMithril && world.getBlock(x, frame_max_y, frame_min_z) == runestoneMithril && world.getBlock(x, frame_max_y, frame_max_z) == runestoneMithril)
/*  630 */       return runestoneMithril; 
/*  631 */     if (world.getBlock(x, frame_min_y, frame_min_z) == runestoneAdamantium && world.getBlock(x, frame_min_y, frame_max_z) == runestoneAdamantium && world.getBlock(x, frame_max_y, frame_min_z) == runestoneAdamantium && world.getBlock(x, frame_max_y, frame_max_z) == runestoneAdamantium) {
/*  632 */       return runestoneAdamantium;
/*      */     }
/*  634 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunegate(World world, int x, int y, int z, boolean intensive_check) {
/*  640 */     if (!intensive_check) {
/*  641 */       return isRunegate(world.getBlockMetadata(x, y, z));
/*      */     }
/*  643 */     return (getRunegateType(world, x, y, z) != null);
/*      */   }
/*      */ 
/*      */   
/*      */   private int getRunegateSeed(World world, int x, int y, int z) {
/*  648 */     int frame_min_x = getFrameMinX(world, x, y, z);
/*  649 */     int frame_max_x = getFrameMaxX(world, x, y, z);
/*  650 */     int frame_min_y = getFrameMinY(world, x, y, z);
/*  651 */     int frame_max_y = getFrameMaxY(world, x, y, z);
/*  652 */     int frame_min_z = getFrameMinZ(world, x, y, z);
/*  653 */     int frame_max_z = getFrameMaxZ(world, x, y, z);
/*      */     
/*  655 */     if (frame_max_x - frame_min_x > frame_max_z - frame_min_z) {
/*  656 */       return world.getBlockMetadata(frame_min_x, frame_min_y, z) + (world.getBlockMetadata(frame_max_x, frame_min_y, z) << 4) + (world.getBlockMetadata(frame_min_x, frame_max_y, z) << 8) + (world.getBlockMetadata(frame_max_x, frame_max_y, z) << 12);
/*      */     }
/*  658 */     return world.getBlockMetadata(x, frame_min_y, frame_min_z) + (world.getBlockMetadata(x, frame_min_y, frame_max_z) << 4) + (world.getBlockMetadata(x, frame_max_y, frame_min_z) << 8) + (world.getBlockMetadata(x, frame_max_y, frame_max_z) << 12);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTouchingBottomBedrock(World world, int x, int y, int z) {
/*  664 */     int frame_min_y = getFrameMinY(world, x, y, z);
/*      */     
/*  666 */     if (frame_min_y > 8) {
/*  667 */       return false;
/*      */     }
/*  669 */     int frame_min_x = getFrameMinX(world, x, y, z);
/*  670 */     int frame_max_x = getFrameMaxX(world, x, y, z);
/*      */     
/*  672 */     int frame_min_z = getFrameMinZ(world, x, y, z);
/*  673 */     int frame_max_z = getFrameMaxZ(world, x, y, z);
/*      */     
/*  675 */     for (int frame_x = frame_min_x; frame_x <= frame_max_x; frame_x++) {
/*      */       
/*  677 */       for (int frame_z = frame_min_z; frame_z <= frame_max_z; frame_z++) {
/*      */ 
/*      */         
/*  680 */         if (world.isBottomBlock(frame_x, frame_min_y - 1, frame_z)) {
/*  681 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*  685 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getDestinationBitForDimensionId(int destination_dimension_id) {
/*  690 */     if (destination_dimension_id == 0) {
/*  691 */       return 0;
/*      */     }
/*  693 */     if (destination_dimension_id == -2) {
/*  694 */       return 1;
/*      */     }
/*  696 */     if (destination_dimension_id == -1) {
/*  697 */       return 2;
/*      */     }
/*  699 */     Minecraft.setErrorMessage("getDestinationBitForDimensionId: destination_dimension_id not handled " + destination_dimension_id);
/*  700 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getDestinationBit(World world) {
/*  705 */     return getDestinationBitForDimensionId(world.getDimensionId());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPortalTypeBasedOnLocation(World world, int x, int y, int z, boolean test_for_runegate) {
/*  711 */     if (test_for_runegate && isRunegate(world, x, y, z, true)) {
/*  712 */       return 0x8 | getDestinationBit(world);
/*      */     }
/*  714 */     if (world.isTheNether()) {
/*  715 */       return 1;
/*      */     }
/*  717 */     if (isTouchingBottomBedrock(world, x, y, z)) {
/*  718 */       return world.isOverworld() ? 1 : 2;
/*      */     }
/*  720 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getDestinationDimensionIdForMetadata(int metadata) {
/*  725 */     int destination_bit = getDestinationBit(metadata);
/*      */     
/*  727 */     if (destination_bit == 0) {
/*  728 */       return 0;
/*      */     }
/*  730 */     if (destination_bit == 1) {
/*  731 */       return -2;
/*      */     }
/*  733 */     if (destination_bit == 2) {
/*  734 */       return -1;
/*      */     }
/*  736 */     Minecraft.setErrorMessage("getDestinationDimensionIdForMetadata: no handler for destination_bit " + destination_bit);
/*  737 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDestinationDimensionID(World world, int x, int y, int z) {
/*  743 */     return getDestinationDimensionIdForMetadata(world.getBlockMetadata(x, y, z));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGoodSpotForPlayerToAppearAt(World world, int x, int y, int z) {
/*  795 */     return (world.isAirOrPassableBlock(x, y, z, false) && world.isAirOrPassableBlock(x, y + 1, z, false) && !world.isAirOrPassableBlock(x, y - 1, z, false) && !world.isLavaBlock(x, y - 1, z) && !world.isCeilingBedrock(x, y - 1, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] getRunegateDestinationCoords(WorldServer world, int x, int y, int z) {
/*  800 */     int seed = getRunegateSeed(world, x, y, z);
/*      */     
/*  802 */     BlockRunestone block_runestone = getRunegateType(world, x, y, z);
/*      */     
/*  804 */     if (seed == 0) {
/*      */       
/*  806 */       x = 0;
/*  807 */       z = 0;
/*      */     }
/*      */     else {
/*      */       
/*  811 */       Random random = new Random(seed);
/*      */       
/*  813 */       for (int attempts = 0; attempts < 4; attempts++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  824 */         int runegate_domain_radius = world.getRunegateDomainRadius((block_runestone == Block.runestoneAdamantium) ? Material.adamantium : Material.mithril);
/*      */         
/*  826 */         x = random.nextInt(runegate_domain_radius * 2) - runegate_domain_radius;
/*  827 */         z = random.nextInt(runegate_domain_radius * 2) - runegate_domain_radius;
/*      */         
/*  829 */         while (block_runestone == Block.runestoneAdamantium) { if (WorldServer.getDistanceFromDeltas(x, z) < (runegate_domain_radius / 2)) {
/*      */             
/*  831 */             x = random.nextInt(runegate_domain_radius * 2) - runegate_domain_radius;
/*  832 */             z = random.nextInt(runegate_domain_radius * 2) - runegate_domain_radius;
/*      */           }  }
/*      */ 
/*      */ 
/*      */         
/*  837 */         if (world.getBiomeGenForCoords(x, z) != BiomeGenBase.ocean) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*  842 */     int chunk_x = x >> 4;
/*  843 */     int chunk_z = z >> 4;
/*      */     
/*  845 */     for (int dx = -1; dx <= 1; dx++) {
/*      */       
/*  847 */       for (int dz = -1; dz <= 1; dz++)
/*      */       {
/*  849 */         world.chunkProvider.provideChunk(chunk_x + dx, chunk_z + dz);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     if (world.isTheNether()) {
/*      */       
/*  860 */       y = 0;
/*      */       
/*  862 */       while (++y < 254)
/*      */       {
/*  864 */         if (world.isAirOrPassableBlock(x, y, z, false))
/*      */         {
/*  866 */           if (world.isAirOrPassableBlock(x, ++y, z, false))
/*      */           {
/*      */             
/*  869 */             if (!world.isAirOrPassableBlock(x, y - 2, z, false) && !world.isLavaBlock(x, y - 2, z) && !world.isLavaBlock(x, y - 1, z))
/*      */             {
/*  871 */               return new int[] { x, y - 1, z };
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     
/*  877 */     } else if (world.isUnderworld()) {
/*      */ 
/*      */ 
/*      */       
/*  881 */       y = 254;
/*      */       
/*  883 */       while (--y > 0) {
/*      */         
/*  885 */         if (isGoodSpotForPlayerToAppearAt(world, x, y, z)) {
/*  886 */           return new int[] { x, y, z };
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/*  891 */       y = 256;
/*      */       
/*  893 */       while (--y > 0) {
/*      */         
/*  895 */         if (world.isAirOrPassableBlock(x, y, z, false)) {
/*      */           
/*  897 */           y--;
/*      */           
/*  899 */           if (world.isAirOrPassableBlock(x, y, z, false)) {
/*      */ 
/*      */             
/*  902 */             while (y > 0 && world.isAirOrPassableBlock(x, y - 1, z, false)) {
/*  903 */               y--;
/*      */             }
/*  905 */             if (y == 0) {
/*  906 */               y = 64;
/*      */             }
/*  908 */             return new int[] { x, y, z };
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  918 */     if (!world.isAirOrPassableBlock(x, 64, z, true)) {
/*  919 */       world.setBlockToAir(x, 64, z);
/*      */     }
/*  921 */     if (!world.isAirOrPassableBlock(x, 65, z, true)) {
/*  922 */       world.setBlockToAir(x, 65, z);
/*      */     }
/*      */ 
/*      */     
/*  926 */     return new int[] { x, 64, z };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean updateType(World world, int x, int y, int z) {
/*  934 */     int metadata = world.getBlockMetadata(x, y, z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     int new_metadata = getPortalTypeBasedOnLocation(world, x, y, z, true);
/*      */     
/*  945 */     if (new_metadata == metadata || isRunegate(new_metadata) == isRunegate(metadata)) {
/*  946 */       return false;
/*      */     }
/*  948 */     return world.setBlockMetadataWithNotify(x, y, z, new_metadata, 3);
/*      */   }
/*      */ 
/*      */   
/*      */   private void initiateRunegateTeleport(WorldServer world, int x, int y, int z, ServerPlayer player, boolean is_portal_to_world_spawn) {
/*  953 */     player.is_runegate_teleporting = true;
/*      */     
/*  955 */     (new int[3])[0] = world.getSpawnX(); (new int[3])[1] = world.getTopSolidOrLiquidBlockMITE(world.getSpawnX(), world.getSpawnZ(), false) + 1; (new int[3])[2] = world.getSpawnZ(); player.runegate_destination_coords = is_portal_to_world_spawn ? new int[3] : getRunegateDestinationCoords(world, x, y, z);
/*  956 */     player.playerNetServerHandler.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.runegate_start));
/*      */     
/*  958 */     player.prevent_runegate_achievement = is_portal_to_world_spawn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  966 */     EntityPlayer player = (par5Entity instanceof EntityPlayer) ? (EntityPlayer)par5Entity : null;
/*      */     
/*  968 */     if (player != null && player.is_runegate_teleporting) {
/*      */       return;
/*      */     }
/*  971 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*      */     
/*  973 */     boolean is_runegate = isRunegate(metadata);
/*  974 */     boolean is_portal_to_world_spawn = isPortalToOverworldSpawn(par1World, metadata);
/*      */ 
/*      */     
/*  977 */     if (is_runegate || is_portal_to_world_spawn) {
/*      */       
/*  979 */       if (par1World.isRemote || player == null) {
/*      */         return;
/*      */       }
/*  982 */       if (player.ridingEntity != null || player.riddenByEntity != null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  987 */       initiateRunegateTeleport((WorldServer)par1World, par2, par3, par4, (ServerPlayer)player, is_portal_to_world_spawn);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  992 */     if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
/*      */     {
/*      */ 
/*      */       
/*  996 */       par5Entity.setInPortal(getDestinationDimensionIdForMetadata(metadata));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 1009 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*      */     
/* 1011 */     boolean is_runegate = isRunegate(metadata);
/*      */     
/* 1013 */     boolean is_portal_to_overworld = isPortalToOverworld(metadata, true);
/* 1014 */     boolean is_portal_to_underworld = isPortalToUnderworld(metadata, true);
/* 1015 */     boolean is_portal_to_nether = isPortalToNether(metadata, true);
/*      */ 
/*      */ 
/*      */     
/* 1019 */     if (par5Random.nextInt(100) == 0)
/*      */     {
/*      */ 
/*      */       
/* 1023 */       par1World.playSound(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, ((is_runegate && is_portal_to_overworld) || isPortalToOverworldSpawn(par1World, metadata)) ? "imported.portal.runegate" : "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
/*      */     }
/*      */     
/* 1026 */     for (int var6 = 0; var6 < 4; var6++) {
/*      */       
/* 1028 */       double var7 = (par2 + par5Random.nextFloat());
/* 1029 */       double var9 = (par3 + par5Random.nextFloat());
/* 1030 */       double var11 = (par4 + par5Random.nextFloat());
/* 1031 */       double var13 = 0.0D;
/* 1032 */       double var15 = 0.0D;
/* 1033 */       double var17 = 0.0D;
/* 1034 */       int var19 = par5Random.nextInt(2) * 2 - 1;
/* 1035 */       var13 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/* 1036 */       var15 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/* 1037 */       var17 = (par5Random.nextFloat() - 0.5D) * 0.5D;
/*      */       
/* 1039 */       if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID) {
/*      */         
/* 1041 */         var7 = par2 + 0.5D + 0.25D * var19;
/* 1042 */         var13 = (par5Random.nextFloat() * 2.0F * var19);
/*      */       }
/*      */       else {
/*      */         
/* 1046 */         var11 = par4 + 0.5D + 0.25D * var19;
/* 1047 */         var17 = (par5Random.nextFloat() * 2.0F * var19);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1052 */       par1World.spawnParticle(is_portal_to_overworld ? EnumParticle.runegate : (is_portal_to_nether ? EnumParticle.portal_nether : EnumParticle.portal_underworld), var7, var9, var11, var13, var15, var17);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int idPicked(World par1World, int par2, int par3, int par4) {
/* 1061 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeCarried() {
/* 1066 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 1071 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getIcon(int side, int metadata) {
/* 1078 */     int destination_bit = getDestinationBit(metadata);
/*      */     
/* 1080 */     return (destination_bit == 0) ? this.runegate_icon : ((destination_bit == 1) ? this.blockIcon : this.nether_portal_icon);
/*      */   }
/*      */ 
/*      */   
/*      */   public void registerIcons(IconRegister par1IconRegister) {
/* 1085 */     super.registerIcons(par1IconRegister);
/*      */     
/* 1087 */     this.runegate_icon = par1IconRegister.registerIcon("runegate");
/* 1088 */     this.nether_portal_icon = par1IconRegister.registerIcon("portal_nether");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 1093 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 1098 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
/* 1103 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */