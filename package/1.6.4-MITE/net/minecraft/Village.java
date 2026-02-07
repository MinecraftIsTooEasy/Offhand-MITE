/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Village
/*     */ {
/*     */   private World worldObj;
/*  13 */   private final List villageDoorInfoList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  19 */   private final ChunkCoordinates centerHelper = new ChunkCoordinates(0, 0, 0);
/*     */ 
/*     */   
/*  22 */   private final ChunkCoordinates center = new ChunkCoordinates(0, 0, 0);
/*     */   
/*     */   private int villageRadius;
/*     */   
/*     */   private int lastAddDoorTimestamp;
/*     */   
/*     */   private int tickCounter;
/*     */   
/*     */   private int numVillagers;
/*     */   private int noBreedTicks;
/*  32 */   private TreeMap playerReputation = new TreeMap<Object, Object>();
/*  33 */   private List villageAgressors = new ArrayList();
/*     */   
/*     */   private int numIronGolems;
/*     */   
/*     */   public Village() {}
/*     */   
/*     */   public Village(World par1World) {
/*  40 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82691_a(World par1World) {
/*  45 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(int par1) {
/*  53 */     this.tickCounter = par1;
/*  54 */     removeDeadAndOutOfRangeDoors();
/*  55 */     removeDeadAndOldAgressors();
/*     */     
/*  57 */     if (par1 % 20 == 0)
/*     */     {
/*  59 */       updateNumVillagers();
/*     */     }
/*     */     
/*  62 */     if (par1 % 30 == 0)
/*     */     {
/*  64 */       updateNumIronGolems();
/*     */     }
/*     */     
/*  67 */     int var2 = this.numVillagers / 10;
/*     */     
/*  69 */     if (this.numIronGolems < var2 && this.villageDoorInfoList.size() > 20 && this.worldObj.rand.nextInt(7000) == 0) {
/*     */       
/*  71 */       Vec3 var3 = tryGetIronGolemSpawningLocation(MathHelper.floor_float(this.center.posX), MathHelper.floor_float(this.center.posY), MathHelper.floor_float(this.center.posZ), 2, 4, 2);
/*     */       
/*  73 */       if (var3 != null) {
/*     */         
/*  75 */         EntityIronGolem var4 = new EntityIronGolem(this.worldObj);
/*  76 */         var4.setPosition(var3.xCoord, var3.yCoord, var3.zCoord);
/*  77 */         this.worldObj.spawnEntityInWorld(var4);
/*  78 */         this.numIronGolems++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Vec3 tryGetIronGolemSpawningLocation(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  88 */     for (int var7 = 0; var7 < 10; var7++) {
/*     */       
/*  90 */       int var8 = par1 + this.worldObj.rand.nextInt(16) - 8;
/*  91 */       int var9 = par2 + this.worldObj.rand.nextInt(6) - 3;
/*  92 */       int var10 = par3 + this.worldObj.rand.nextInt(16) - 8;
/*     */       
/*  94 */       if (isInRange(var8, var9, var10) && isValidIronGolemSpawningLocation(var8, var9, var10, par4, par5, par6))
/*     */       {
/*  96 */         return this.worldObj.getWorldVec3Pool().getVecFromPool(var8, var9, var10);
/*     */       }
/*     */     } 
/*     */     
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidIronGolemSpawningLocation(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 106 */     if (!this.worldObj.isBlockTopFlatAndSolid(par1, par2 - 1, par3))
/*     */     {
/* 108 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 112 */     int var7 = par1 - par4 / 2;
/* 113 */     int var8 = par3 - par6 / 2;
/*     */     
/* 115 */     for (int var9 = var7; var9 < var7 + par4; var9++) {
/*     */       
/* 117 */       for (int var10 = par2; var10 < par2 + par5; var10++) {
/*     */         
/* 119 */         for (int var11 = var8; var11 < var8 + par6; var11++) {
/*     */           
/* 121 */           if (this.worldObj.isBlockNormalCube(var9, var10, var11))
/*     */           {
/* 123 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateNumIronGolems() {
/* 135 */     List var1 = this.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, AxisAlignedBB.getAABBPool().getAABB((this.center.posX - this.villageRadius), (this.center.posY - 4), (this.center.posZ - this.villageRadius), (this.center.posX + this.villageRadius), (this.center.posY + 4), (this.center.posZ + this.villageRadius)));
/* 136 */     this.numIronGolems = var1.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateNumVillagers() {
/* 141 */     List var1 = this.worldObj.getEntitiesWithinAABB(EntityVillager.class, AxisAlignedBB.getAABBPool().getAABB((this.center.posX - this.villageRadius), (this.center.posY - 4), (this.center.posZ - this.villageRadius), (this.center.posX + this.villageRadius), (this.center.posY + 4), (this.center.posZ + this.villageRadius)));
/* 142 */     this.numVillagers = var1.size();
/*     */     
/* 144 */     if (this.numVillagers == 0)
/*     */     {
/* 146 */       this.playerReputation.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getCenter() {
/* 152 */     return this.center;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillageRadius() {
/* 157 */     return this.villageRadius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumVillageDoors() {
/* 166 */     return this.villageDoorInfoList.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSinceLastDoorAdding() {
/* 171 */     return this.tickCounter - this.lastAddDoorTimestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumVillagers() {
/* 176 */     return this.numVillagers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRange(int par1, int par2, int par3) {
/* 184 */     return (this.center.getDistanceSquared(par1, par2, par3) < (this.villageRadius * this.villageRadius));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getVillageDoorInfoList() {
/* 192 */     return this.villageDoorInfoList;
/*     */   }
/*     */ 
/*     */   
/*     */   public VillageDoorInfo findNearestDoor(int par1, int par2, int par3) {
/* 197 */     VillageDoorInfo var4 = null;
/* 198 */     int var5 = Integer.MAX_VALUE;
/* 199 */     Iterator<VillageDoorInfo> var6 = this.villageDoorInfoList.iterator();
/*     */     
/* 201 */     while (var6.hasNext()) {
/*     */       
/* 203 */       VillageDoorInfo var7 = var6.next();
/* 204 */       int var8 = var7.getDistanceSquared(par1, par2, par3);
/*     */       
/* 206 */       if (var8 < var5) {
/*     */         
/* 208 */         var4 = var7;
/* 209 */         var5 = var8;
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VillageDoorInfo findNearestDoorUnrestricted(int par1, int par2, int par3) {
/* 223 */     VillageDoorInfo var4 = null;
/* 224 */     int var5 = Integer.MAX_VALUE;
/* 225 */     Iterator<VillageDoorInfo> var6 = this.villageDoorInfoList.iterator();
/*     */     
/* 227 */     while (var6.hasNext()) {
/*     */       
/* 229 */       VillageDoorInfo var7 = var6.next();
/* 230 */       int var8 = var7.getDistanceSquared(par1, par2, par3);
/*     */       
/* 232 */       if (var8 > 256) {
/*     */         
/* 234 */         var8 *= 1000;
/*     */       }
/*     */       else {
/*     */         
/* 238 */         var8 = var7.getDoorOpeningRestrictionCounter();
/*     */       } 
/*     */       
/* 241 */       if (var8 < var5) {
/*     */         
/* 243 */         var4 = var7;
/* 244 */         var5 = var8;
/*     */       } 
/*     */     } 
/*     */     
/* 248 */     return var4;
/*     */   }
/*     */   
/*     */   public VillageDoorInfo getVillageDoorAt(int par1, int par2, int par3) {
/*     */     VillageDoorInfo var5;
/* 253 */     if (this.center.getDistanceSquared(par1, par2, par3) > (this.villageRadius * this.villageRadius))
/*     */     {
/* 255 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 259 */     Iterator<VillageDoorInfo> var4 = this.villageDoorInfoList.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 264 */       if (!var4.hasNext())
/*     */       {
/* 266 */         return null;
/*     */       }
/*     */       
/* 269 */       var5 = var4.next();
/*     */     }
/* 271 */     while (var5.posX != par1 || var5.posZ != par3 || Math.abs(var5.posY - par2) > 1);
/*     */     
/* 273 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVillageDoorInfo(VillageDoorInfo par1VillageDoorInfo) {
/* 279 */     this.villageDoorInfoList.add(par1VillageDoorInfo);
/* 280 */     this.centerHelper.posX += par1VillageDoorInfo.posX;
/* 281 */     this.centerHelper.posY += par1VillageDoorInfo.posY;
/* 282 */     this.centerHelper.posZ += par1VillageDoorInfo.posZ;
/* 283 */     updateVillageRadiusAndCenter();
/* 284 */     this.lastAddDoorTimestamp = par1VillageDoorInfo.lastActivityTimestamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnnihilated() {
/* 292 */     return this.villageDoorInfoList.isEmpty();
/*     */   }
/*     */   
/*     */   public void addOrRenewAgressor(EntityLivingBase par1EntityLivingBase) {
/*     */     VillageAgressor var3;
/* 297 */     Iterator<VillageAgressor> var2 = this.villageAgressors.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 302 */       if (!var2.hasNext()) {
/*     */         
/* 304 */         this.villageAgressors.add(new VillageAgressor(this, par1EntityLivingBase, this.tickCounter));
/*     */         
/*     */         return;
/*     */       } 
/* 308 */       var3 = var2.next();
/*     */     }
/* 310 */     while (var3.agressor != par1EntityLivingBase);
/*     */     
/* 312 */     var3.agressionTime = this.tickCounter;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase findNearestVillageAggressor(EntityLivingBase par1EntityLivingBase) {
/* 317 */     double var2 = Double.MAX_VALUE;
/* 318 */     VillageAgressor var4 = null;
/*     */     
/* 320 */     for (int var5 = 0; var5 < this.villageAgressors.size(); var5++) {
/*     */       
/* 322 */       VillageAgressor var6 = this.villageAgressors.get(var5);
/* 323 */       double var7 = var6.agressor.getDistanceSqToEntity(par1EntityLivingBase);
/*     */       
/* 325 */       if (var7 <= var2) {
/*     */         
/* 327 */         var4 = var6;
/* 328 */         var2 = var7;
/*     */       } 
/*     */     } 
/*     */     
/* 332 */     return (var4 != null) ? var4.agressor : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer func_82685_c(EntityLivingBase par1EntityLivingBase) {
/* 337 */     double var2 = Double.MAX_VALUE;
/* 338 */     EntityPlayer var4 = null;
/* 339 */     Iterator<String> var5 = this.playerReputation.keySet().iterator();
/*     */     
/* 341 */     while (var5.hasNext()) {
/*     */       
/* 343 */       String var6 = var5.next();
/*     */       
/* 345 */       if (isPlayerReputationTooLow(var6)) {
/*     */         
/* 347 */         EntityPlayer var7 = this.worldObj.getPlayerEntityByName(var6);
/*     */         
/* 349 */         if (var7 != null) {
/*     */           
/* 351 */           double var8 = var7.getDistanceSqToEntity(par1EntityLivingBase);
/*     */           
/* 353 */           if (var8 <= var2) {
/*     */             
/* 355 */             var4 = var7;
/* 356 */             var2 = var8;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 362 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeDeadAndOldAgressors() {
/* 367 */     Iterator<VillageAgressor> var1 = this.villageAgressors.iterator();
/*     */     
/* 369 */     while (var1.hasNext()) {
/*     */       
/* 371 */       VillageAgressor var2 = var1.next();
/*     */       
/* 373 */       if (!var2.agressor.isEntityAlive() || Math.abs(this.tickCounter - var2.agressionTime) > 300)
/*     */       {
/* 375 */         var1.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeDeadAndOutOfRangeDoors() {
/* 382 */     boolean var1 = false;
/* 383 */     boolean var2 = (this.worldObj.rand.nextInt(50) == 0);
/* 384 */     Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator();
/*     */     
/* 386 */     while (var3.hasNext()) {
/*     */       
/* 388 */       VillageDoorInfo var4 = var3.next();
/*     */       
/* 390 */       if (var2)
/*     */       {
/* 392 */         var4.resetDoorOpeningRestrictionCounter();
/*     */       }
/*     */       
/* 395 */       if (!isBlockDoor(var4.posX, var4.posY, var4.posZ) || Math.abs(this.tickCounter - var4.lastActivityTimestamp) > 1200) {
/*     */         
/* 397 */         this.centerHelper.posX -= var4.posX;
/* 398 */         this.centerHelper.posY -= var4.posY;
/* 399 */         this.centerHelper.posZ -= var4.posZ;
/* 400 */         var1 = true;
/* 401 */         var4.isDetachedFromVillageFlag = true;
/* 402 */         var3.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 406 */     if (var1)
/*     */     {
/* 408 */       updateVillageRadiusAndCenter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockDoor(int par1, int par2, int par3) {
/* 414 */     int var4 = this.worldObj.getBlockId(par1, par2, par3);
/* 415 */     return (var4 <= 0) ? false : ((var4 == Block.doorWood.blockID));
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateVillageRadiusAndCenter() {
/* 420 */     int var1 = this.villageDoorInfoList.size();
/*     */     
/* 422 */     if (var1 == 0) {
/*     */       
/* 424 */       this.center.set(0, 0, 0);
/* 425 */       this.villageRadius = 0;
/*     */     }
/*     */     else {
/*     */       
/* 429 */       this.center.set(this.centerHelper.posX / var1, this.centerHelper.posY / var1, this.centerHelper.posZ / var1);
/* 430 */       int var2 = 0;
/*     */ 
/*     */       
/* 433 */       for (Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator(); var3.hasNext(); var2 = Math.max(var4.getDistanceSquared(this.center.posX, this.center.posY, this.center.posZ), var2))
/*     */       {
/* 435 */         VillageDoorInfo var4 = var3.next();
/*     */       }
/*     */       
/* 438 */       this.villageRadius = Math.max(32, (int)Math.sqrt(var2) + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReputationForPlayer(String par1Str) {
/* 447 */     Integer var2 = (Integer)this.playerReputation.get(par1Str);
/* 448 */     return (var2 != null) ? var2.intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setReputationForPlayer(String par1Str, int par2) {
/* 456 */     int var3 = getReputationForPlayer(par1Str);
/* 457 */     int var4 = MathHelper.clamp_int(var3 + par2, -30, 10);
/* 458 */     this.playerReputation.put(par1Str, Integer.valueOf(var4));
/* 459 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerReputationTooLow(String par1Str) {
/* 467 */     return (getReputationForPlayer(par1Str) <= -15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readVillageDataFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 475 */     this.numVillagers = par1NBTTagCompound.getInteger("PopSize");
/* 476 */     this.villageRadius = par1NBTTagCompound.getInteger("Radius");
/* 477 */     this.numIronGolems = par1NBTTagCompound.getInteger("Golems");
/* 478 */     this.lastAddDoorTimestamp = par1NBTTagCompound.getInteger("Stable");
/* 479 */     this.tickCounter = par1NBTTagCompound.getInteger("Tick");
/* 480 */     this.noBreedTicks = par1NBTTagCompound.getInteger("MTick");
/* 481 */     this.center.posX = par1NBTTagCompound.getInteger("CX");
/* 482 */     this.center.posY = par1NBTTagCompound.getInteger("CY");
/* 483 */     this.center.posZ = par1NBTTagCompound.getInteger("CZ");
/* 484 */     this.centerHelper.posX = par1NBTTagCompound.getInteger("ACX");
/* 485 */     this.centerHelper.posY = par1NBTTagCompound.getInteger("ACY");
/* 486 */     this.centerHelper.posZ = par1NBTTagCompound.getInteger("ACZ");
/* 487 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Doors");
/*     */     
/* 489 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 491 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 492 */       VillageDoorInfo var5 = new VillageDoorInfo(var4.getInteger("X"), var4.getInteger("Y"), var4.getInteger("Z"), var4.getInteger("IDX"), var4.getInteger("IDZ"), var4.getInteger("TS"));
/* 493 */       this.villageDoorInfoList.add(var5);
/*     */     } 
/*     */     
/* 496 */     NBTTagList var6 = par1NBTTagCompound.getTagList("Players");
/*     */     
/* 498 */     for (int var7 = 0; var7 < var6.tagCount(); var7++) {
/*     */       
/* 500 */       NBTTagCompound var8 = (NBTTagCompound)var6.tagAt(var7);
/* 501 */       this.playerReputation.put(var8.getString("Name"), Integer.valueOf(var8.getInteger("S")));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeVillageDataToNBT(NBTTagCompound par1NBTTagCompound) {
/* 510 */     par1NBTTagCompound.setInteger("PopSize", this.numVillagers);
/* 511 */     par1NBTTagCompound.setInteger("Radius", this.villageRadius);
/* 512 */     par1NBTTagCompound.setInteger("Golems", this.numIronGolems);
/* 513 */     par1NBTTagCompound.setInteger("Stable", this.lastAddDoorTimestamp);
/* 514 */     par1NBTTagCompound.setInteger("Tick", this.tickCounter);
/* 515 */     par1NBTTagCompound.setInteger("MTick", this.noBreedTicks);
/* 516 */     par1NBTTagCompound.setInteger("CX", this.center.posX);
/* 517 */     par1NBTTagCompound.setInteger("CY", this.center.posY);
/* 518 */     par1NBTTagCompound.setInteger("CZ", this.center.posZ);
/* 519 */     par1NBTTagCompound.setInteger("ACX", this.centerHelper.posX);
/* 520 */     par1NBTTagCompound.setInteger("ACY", this.centerHelper.posY);
/* 521 */     par1NBTTagCompound.setInteger("ACZ", this.centerHelper.posZ);
/* 522 */     NBTTagList var2 = new NBTTagList("Doors");
/* 523 */     Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator();
/*     */     
/* 525 */     while (var3.hasNext()) {
/*     */       
/* 527 */       VillageDoorInfo var4 = var3.next();
/* 528 */       NBTTagCompound var5 = new NBTTagCompound("Door");
/* 529 */       var5.setInteger("X", var4.posX);
/* 530 */       var5.setInteger("Y", var4.posY);
/* 531 */       var5.setInteger("Z", var4.posZ);
/* 532 */       var5.setInteger("IDX", var4.insideDirectionX);
/* 533 */       var5.setInteger("IDZ", var4.insideDirectionZ);
/* 534 */       var5.setInteger("TS", var4.lastActivityTimestamp);
/* 535 */       var2.appendTag(var5);
/*     */     } 
/*     */     
/* 538 */     par1NBTTagCompound.setTag("Doors", var2);
/* 539 */     NBTTagList var7 = new NBTTagList("Players");
/* 540 */     Iterator<String> var8 = this.playerReputation.keySet().iterator();
/*     */     
/* 542 */     while (var8.hasNext()) {
/*     */       
/* 544 */       String var9 = var8.next();
/* 545 */       NBTTagCompound var6 = new NBTTagCompound(var9);
/* 546 */       var6.setString("Name", var9);
/* 547 */       var6.setInteger("S", ((Integer)this.playerReputation.get(var9)).intValue());
/* 548 */       var7.appendTag(var6);
/*     */     } 
/*     */     
/* 551 */     par1NBTTagCompound.setTag("Players", var7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endMatingSeason() {
/* 559 */     this.noBreedTicks = this.tickCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMatingSeason() {
/* 567 */     return (this.noBreedTicks == 0 || this.tickCounter - this.noBreedTicks >= 3600);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82683_b(int par1) {
/* 572 */     Iterator<String> var2 = this.playerReputation.keySet().iterator();
/*     */     
/* 574 */     while (var2.hasNext()) {
/*     */       
/* 576 */       String var3 = var2.next();
/* 577 */       setReputationForPlayer(var3, par1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Village.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */