/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityWeb
/*     */   extends EntityThrowable
/*     */ {
/*     */   public EntityWeb(World world) {
/*  12 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWeb(World world, EntityLivingBase thrower) {
/*  17 */     super(world, thrower);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWeb(World world, double pos_x, double pos_y, double pos_z) {
/*  22 */     super(world, pos_x, pos_y, pos_z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean onImpact(EntityLivingBase elb_hit, boolean burning) {
/*  28 */     if (burning) {
/*  29 */       elb_hit.setFire(5);
/*     */     }
/*  31 */     World worldObj = elb_hit.worldObj;
/*     */     
/*  33 */     int x = elb_hit.getBlockPosX();
/*  34 */     int y = elb_hit.getFootBlockPosY();
/*  35 */     int z = elb_hit.getBlockPosZ();
/*     */     
/*  37 */     int lead = 4;
/*     */     
/*  39 */     int predicted_x = MathHelper.floor_double(elb_hit.getPredictedPosX(lead));
/*  40 */     int predicted_z = MathHelper.floor_double(elb_hit.getPredictedPosZ(lead));
/*     */     
/*  42 */     if ((predicted_x != x || predicted_z != z) && setBlockToWebIfEmpty(worldObj, predicted_x, y, predicted_z, burning))
/*     */     {
/*  44 */       return true;
/*     */     }
/*  46 */     if (setBlockToWebIfEmpty(worldObj, x, y, z, burning))
/*     */     {
/*  48 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  52 */     int min_x = elb_hit.boundingBox.getBlockCoordForMinX();
/*  53 */     int min_y = elb_hit.boundingBox.getBlockCoordForMinY();
/*  54 */     int min_z = elb_hit.boundingBox.getBlockCoordForMinZ();
/*     */     
/*  56 */     int max_x = elb_hit.boundingBox.getBlockCoordForMaxX();
/*  57 */     int max_y = elb_hit.boundingBox.getBlockCoordForMaxY();
/*  58 */     int max_z = elb_hit.boundingBox.getBlockCoordForMaxZ();
/*     */     
/*  60 */     for (y = min_y; y <= max_y; y++) {
/*     */       
/*  62 */       for (x = min_x; x <= max_x; x++) {
/*     */         
/*  64 */         for (z = min_z; z <= max_z; z++) {
/*     */           
/*  66 */           if (setBlockToWebIfEmpty(worldObj, x, y, z, burning)) {
/*  67 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(RaycastCollision rc) {
/*  78 */     if (rc.isEntity()) {
/*     */       
/*  80 */       Entity entity_hit = rc.getEntityHit();
/*     */       
/*  82 */       if (entity_hit.isEntityLivingBase())
/*     */       {
/*  84 */         if (onServer() && onImpact(entity_hit.getAsEntityLivingBase(), isBurning())) {
/*  85 */           setDead();
/*     */         
/*     */         }
/*     */       }
/*     */     }
/*  90 */     else if (rc.getNeighborOfBlockHit() == Block.fire || rc.getBlockHitMaterial() == Material.lava || rc.getNeighborOfBlockHitMaterial() == Material.lava) {
/*     */       
/*  92 */       if (onServer()) {
/*  93 */         entityFX(EnumEntityFX.burned_up_in_lava);
/*     */       }
/*  95 */       setDead();
/*     */     }
/*  97 */     else if (rc.isLiquidBlock() || rc.getNeighborOfBlockHitMaterial() == Material.water) {
/*     */       
/*  99 */       if (onServer() && rc.isBlockHitFullWaterBlock(true)) {
/* 100 */         entityFX(EnumEntityFX.splash);
/*     */       }
/* 102 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/* 106 */       if (onServer()) {
/*     */         
/* 108 */         int x = rc.block_hit_x;
/* 109 */         int y = rc.block_hit_y;
/* 110 */         int z = rc.block_hit_z;
/*     */         
/* 112 */         if (!canReplaceBlockAt(this.worldObj, x, y, z)) {
/*     */           
/* 114 */           x = rc.neighbor_block_x;
/* 115 */           y = rc.neighbor_block_y;
/* 116 */           z = rc.neighbor_block_z;
/*     */         } 
/*     */         
/* 119 */         if (!setBlockToWebIfEmpty(rc.world, x, y, z, isBurning())) {
/* 120 */           setBlockToWebIfEmpty(rc.world, x, y + 1, z, isBurning());
/*     */         }
/*     */       } 
/* 123 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean canReplaceBlockAt(World world, int x, int y, int z) {
/* 130 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 132 */     if (block == null) {
/* 133 */       return true;
/*     */     }
/* 135 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 137 */     return (block == Block.snow && metadata == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean setBlockToWebIfEmpty(World worldObj, int x, int y, int z, boolean burning) {
/* 142 */     if (canReplaceBlockAt(worldObj, x, y, z) && worldObj.setBlock(x, y, z, Block.web.blockID)) {
/*     */       
/* 144 */       if (burning) {
/*     */         
/* 146 */         EnumDirection[] directions = new EnumDirection[6];
/*     */         
/* 148 */         int index = 0;
/*     */         
/* 150 */         if (worldObj.getBlockId(x, y + 1, z) == 0) {
/* 151 */           directions[index++] = EnumDirection.UP;
/*     */         }
/* 153 */         if (worldObj.getBlockId(x, y - 1, z) == 0) {
/* 154 */           directions[index++] = EnumDirection.DOWN;
/*     */         }
/* 156 */         if (worldObj.getBlockId(x, y, z + 1) == 0) {
/* 157 */           directions[index++] = EnumDirection.SOUTH;
/*     */         }
/* 159 */         if (worldObj.getBlockId(x, y, z - 1) == 0) {
/* 160 */           directions[index++] = EnumDirection.NORTH;
/*     */         }
/* 162 */         if (worldObj.getBlockId(x + 1, y, z) == 0) {
/* 163 */           directions[index++] = EnumDirection.EAST;
/*     */         }
/* 165 */         if (worldObj.getBlockId(x - 1, y, z) == 0) {
/* 166 */           directions[index++] = EnumDirection.WEST;
/*     */         }
/* 168 */         if (index > 0) {
/*     */           
/* 170 */           EnumDirection direction = directions[worldObj.rand.nextInt(index)];
/*     */           
/* 172 */           int[] coords = World.getNeighboringBlockCoords(x, y, z, direction.getFace());
/*     */           
/* 174 */           worldObj.setBlock(coords[0], coords[1], coords[2], Block.fire.blockID);
/*     */           
/* 176 */           List entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBoxFromPool(x, y, z, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D));
/*     */           
/* 178 */           Iterator<EntityLivingBase> i = entities.iterator();
/*     */           
/* 180 */           while (i.hasNext()) {
/*     */             
/* 182 */             EntityLivingBase elb = i.next();
/*     */             
/* 184 */             elb.setFire(5);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 189 */       return true;
/*     */     } 
/*     */     
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 197 */     return Item.thrownWeb;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */