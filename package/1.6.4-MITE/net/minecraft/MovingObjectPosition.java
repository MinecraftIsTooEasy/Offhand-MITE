/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MovingObjectPosition
/*     */ {
/*     */   private Object object_hit;
/*     */   public int blockX;
/*     */   public int blockY;
/*     */   public int blockZ;
/*     */   public int sideHit;
/*     */   public Vec3 position_hit;
/*     */   public World world;
/*  55 */   public double distance_to_object_hit = Double.MAX_VALUE;
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition(World world, int x, int y, int z, int side_hit, Vec3 position_hit) {
/*  60 */     this.object_hit = world.getBlock(x, y, z);
/*  61 */     this.world = world;
/*  62 */     this.blockX = x;
/*  63 */     this.blockY = y;
/*  64 */     this.blockZ = z;
/*  65 */     this.sideHit = side_hit;
/*  66 */     this.position_hit = position_hit.myVec3LocalPool.getVecFromPool(position_hit.xCoord, position_hit.yCoord, position_hit.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public MovingObjectPosition(Entity entity, double distance_to_entity) {
/*  71 */     this.object_hit = entity;
/*  72 */     this.world = entity.worldObj;
/*  73 */     this.position_hit = entity.worldObj.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY, entity.posZ);
/*     */     
/*  75 */     this.distance_to_object_hit = distance_to_entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlock() {
/*  80 */     return this.object_hit instanceof Block;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockAt(int x, int y, int z) {
/*  86 */     if (!isBlock()) {
/*  87 */       return false;
/*     */     }
/*  89 */     return (x == this.blockX && y == this.blockY && z == this.blockZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntity() {
/*  94 */     return this.object_hit instanceof Entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlockHit() {
/*  99 */     if (isBlock())
/*     */     {
/* 101 */       return (Block)this.object_hit;
/*     */     }
/*     */     
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getEntityHit() {
/* 109 */     if (isEntity()) {
/* 110 */       return (Entity)this.object_hit;
/*     */     }
/*     */     
/* 113 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MovingObjectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */