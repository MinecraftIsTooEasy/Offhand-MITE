/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VillageCollection
/*     */   extends WorldSavedData
/*     */ {
/*     */   private World worldObj;
/*  17 */   private final List villagerPositionsList = new ArrayList();
/*  18 */   private final List newDoors = new ArrayList();
/*  19 */   private final List villageList = new ArrayList();
/*     */   private int tickCounter;
/*     */   
/*     */   public VillageCollection(String string) {
/*  23 */     super(string);
/*     */   }
/*     */   
/*     */   public VillageCollection(World world) {
/*  27 */     super("villages");
/*  28 */     this.worldObj = world;
/*  29 */     markDirty();
/*     */   }
/*     */   
/*     */   public void func_82566_a(World world) {
/*  33 */     this.worldObj = world;
/*     */     
/*  35 */     for (Village village : this.villageList) {
/*  36 */       village.func_82691_a(world);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addVillagerPosition(int i, int j, int k) {
/*  41 */     if (this.villagerPositionsList.size() > 64)
/*  42 */       return;  if (!isVillagerPositionPresent(i, j, k)) this.villagerPositionsList.add(new ChunkCoordinates(i, j, k)); 
/*     */   }
/*     */   
/*     */   public void tick() {
/*  46 */     this.tickCounter++;
/*  47 */     for (Village village : this.villageList)
/*  48 */       village.tick(this.tickCounter); 
/*  49 */     removeAnnihilatedVillages();
/*  50 */     dropOldestVillagerPosition();
/*  51 */     addNewDoorsToVillageOrCreateVillage();
/*     */     
/*  53 */     if (this.tickCounter % 400 == 0) {
/*  54 */       markDirty();
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeAnnihilatedVillages() {
/*  59 */     for (Iterator<Village> iterator = this.villageList.iterator(); iterator.hasNext(); ) {
/*  60 */       Village village = iterator.next();
/*  61 */       if (village.isAnnihilated()) {
/*  62 */         iterator.remove();
/*  63 */         markDirty();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getVillageList() {
/*  69 */     return this.villageList;
/*     */   }
/*     */   
/*     */   public Village findNearestVillage(int i, int j, int k, int l) {
/*  73 */     Village village = null;
/*  74 */     float f = Float.MAX_VALUE;
/*  75 */     for (Village village1 : this.villageList) {
/*     */       
/*  77 */       float f1 = village1.getCenter().getDistanceSquared(i, j, k);
/*  78 */       if (f1 >= f)
/*     */         continue; 
/*  80 */       float f2 = (l + village1.getVillageRadius());
/*  81 */       if (f1 > f2 * f2)
/*     */         continue; 
/*  83 */       village = village1;
/*  84 */       f = f1;
/*     */     } 
/*  86 */     return village;
/*     */   }
/*     */   
/*     */   private void dropOldestVillagerPosition() {
/*  90 */     if (this.villagerPositionsList.isEmpty())
/*  91 */       return;  addUnassignedWoodenDoorsAroundToNewDoorsList(this.villagerPositionsList.remove(0));
/*     */   }
/*     */ 
/*     */   
/*     */   private void addNewDoorsToVillageOrCreateVillage() {
/*  96 */     for (byte b = 0; b < this.newDoors.size(); b++) {
/*  97 */       VillageDoorInfo villageDoorInfo = this.newDoors.get(b);
/*  98 */       boolean bool = false;
/*  99 */       for (Village village : this.villageList) {
/* 100 */         int i = (int)village.getCenter().getDistanceSquared(villageDoorInfo.posX, villageDoorInfo.posY, villageDoorInfo.posZ);
/* 101 */         int j = 32 + village.getVillageRadius();
/* 102 */         if (i > j * j)
/* 103 */           continue;  village.addVillageDoorInfo(villageDoorInfo);
/* 104 */         bool = true;
/*     */       } 
/*     */       
/* 107 */       if (!bool) {
/*     */ 
/*     */         
/* 110 */         Village village = new Village(this.worldObj);
/* 111 */         village.addVillageDoorInfo(villageDoorInfo);
/* 112 */         this.villageList.add(village);
/* 113 */         markDirty();
/*     */       } 
/* 115 */     }  this.newDoors.clear();
/*     */   }
/*     */   
/*     */   private void addUnassignedWoodenDoorsAroundToNewDoorsList(ChunkCoordinates chunkCoordinates) {
/* 119 */     byte b1 = 16, b2 = 4, b3 = 16;
/* 120 */     for (int i = chunkCoordinates.posX - b1; i < chunkCoordinates.posX + b1; i++) {
/* 121 */       for (int j = chunkCoordinates.posY - b2; j < chunkCoordinates.posY + b2; j++) {
/* 122 */         for (int k = chunkCoordinates.posZ - b3; k < chunkCoordinates.posZ + b3; k++) {
/* 123 */           if (isWoodenDoorAt(i, j, k)) {
/*     */             
/* 125 */             VillageDoorInfo villageDoorInfo = getVillageDoorAt(i, j, k);
/* 126 */             if (villageDoorInfo == null) { addDoorToNewListIfAppropriate(i, j, k); }
/* 127 */             else { villageDoorInfo.lastActivityTimestamp = this.tickCounter; }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private VillageDoorInfo getVillageDoorAt(int i, int j, int k) {
/* 135 */     for (VillageDoorInfo villageDoorInfo : this.newDoors) {
/* 136 */       if (villageDoorInfo.posX == i && villageDoorInfo.posZ == k && Math.abs(villageDoorInfo.posY - j) <= 1) return villageDoorInfo; 
/* 137 */     }  for (Village village : this.villageList) {
/* 138 */       VillageDoorInfo villageDoorInfo = village.getVillageDoorAt(i, j, k);
/* 139 */       if (villageDoorInfo != null) return villageDoorInfo; 
/*     */     } 
/* 141 */     return null;
/*     */   }
/*     */   
/*     */   private void addDoorToNewListIfAppropriate(int i, int j, int k) {
/* 145 */     int m = ((BlockDoor)Block.doorWood).getDoorOrientation(this.worldObj, i, j, k);
/* 146 */     if (m == 0 || m == 2) {
/* 147 */       byte b = 0; byte b1;
/* 148 */       for (b1 = -5; b1 < 0; b1++) {
/* 149 */         if (this.worldObj.canBlockSeeTheSky(i + b1, j, k)) b--; 
/* 150 */       }  for (b1 = 1; b1 <= 5; b1++) {
/* 151 */         if (this.worldObj.canBlockSeeTheSky(i + b1, j, k)) b++; 
/* 152 */       }  if (b != 0) this.newDoors.add(new VillageDoorInfo(i, j, k, (b > 0) ? -2 : 2, 0, this.tickCounter)); 
/*     */     } else {
/* 154 */       byte b = 0; byte b1;
/* 155 */       for (b1 = -5; b1 < 0; b1++) {
/* 156 */         if (this.worldObj.canBlockSeeTheSky(i, j, k + b1)) b--; 
/* 157 */       }  for (b1 = 1; b1 <= 5; b1++) {
/* 158 */         if (this.worldObj.canBlockSeeTheSky(i, j, k + b1)) b++; 
/* 159 */       }  if (b != 0) this.newDoors.add(new VillageDoorInfo(i, j, k, 0, (b > 0) ? -2 : 2, this.tickCounter)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isVillagerPositionPresent(int i, int j, int k) {
/* 164 */     for (ChunkCoordinates chunkCoordinates : this.villagerPositionsList) {
/* 165 */       if (chunkCoordinates.posX == i && chunkCoordinates.posY == j && chunkCoordinates.posZ == k) return true; 
/* 166 */     }  return false;
/*     */   }
/*     */   
/*     */   private boolean isWoodenDoorAt(int i, int j, int k) {
/* 170 */     int m = this.worldObj.getBlockId(i, j, k);
/* 171 */     return (m == Block.doorWood.blockID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 177 */     this.tickCounter = nBTTagCompound.getInteger("Tick");
/*     */     
/* 179 */     NBTTagList nBTTagList = nBTTagCompound.getTagList("Villages");
/* 180 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/* 181 */       NBTTagCompound nBTTagCompound1 = (NBTTagCompound)nBTTagList.tagAt(b);
/* 182 */       Village village = new Village();
/* 183 */       village.readVillageDataFromNBT(nBTTagCompound1);
/* 184 */       this.villageList.add(village);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 191 */     nBTTagCompound.setInteger("Tick", this.tickCounter);
/* 192 */     NBTTagList nBTTagList = new NBTTagList("Villages");
/* 193 */     for (Village village : this.villageList) {
/* 194 */       NBTTagCompound nBTTagCompound1 = new NBTTagCompound("Village");
/* 195 */       village.writeVillageDataToNBT(nBTTagCompound1);
/* 196 */       nBTTagList.appendTag(nBTTagCompound1);
/*     */     } 
/* 198 */     nBTTagCompound.setTag("Villages", nBTTagList);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\VillageCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */