/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class VillageSiege
/*     */ {
/*     */   private World worldObj;
/*     */   private boolean field_75535_b;
/*  10 */   private int field_75536_c = -1;
/*     */   
/*     */   private int field_75533_d;
/*     */   
/*     */   private int field_75534_e;
/*     */   
/*     */   private Village theVillage;
/*     */   private int field_75532_g;
/*     */   private int field_75538_h;
/*     */   private int field_75539_i;
/*     */   
/*     */   public VillageSiege(World par1World) {
/*  22 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  30 */     boolean var1 = false;
/*     */     
/*  32 */     if (var1) {
/*     */       
/*  34 */       if (this.field_75536_c == 2) {
/*     */         
/*  36 */         this.field_75533_d = 100;
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/*  42 */       if (this.worldObj.isDaytime()) {
/*     */         
/*  44 */         this.field_75536_c = 0;
/*     */         
/*     */         return;
/*     */       } 
/*  48 */       if (this.field_75536_c == 2) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  53 */       if (this.field_75536_c == 0) {
/*     */         
/*  55 */         float var2 = this.worldObj.getCelestialAngle(0.0F);
/*     */         
/*  57 */         if (var2 < 0.5D || var2 > 0.501D) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  62 */         this.field_75536_c = (this.worldObj.rand.nextInt(10) == 0) ? 1 : 2;
/*  63 */         this.field_75535_b = false;
/*     */         
/*  65 */         if (this.field_75536_c == 2) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  72 */     if (!this.field_75535_b) {
/*     */       
/*  74 */       if (!func_75529_b()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  79 */       this.field_75535_b = true;
/*     */     } 
/*     */     
/*  82 */     if (this.field_75534_e > 0) {
/*     */       
/*  84 */       this.field_75534_e--;
/*     */     }
/*     */     else {
/*     */       
/*  88 */       this.field_75534_e = 2;
/*     */       
/*  90 */       if (this.field_75533_d > 0) {
/*     */         
/*  92 */         spawnZombie();
/*  93 */         this.field_75533_d--;
/*     */       }
/*     */       else {
/*     */         
/*  97 */         this.field_75536_c = 2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_75529_b() {
/* 104 */     List var1 = this.worldObj.playerEntities;
/* 105 */     Iterator<EntityPlayer> var2 = var1.iterator();
/*     */     
/* 107 */     while (var2.hasNext()) {
/*     */       
/* 109 */       EntityPlayer var3 = var2.next();
/*     */       
/* 111 */       if (var3.isGhost() || var3.isZevimrgvInTournament()) {
/*     */         continue;
/*     */       }
/* 114 */       this.theVillage = this.worldObj.villageCollectionObj.findNearestVillage((int)var3.posX, (int)var3.posY, (int)var3.posZ, 1);
/*     */       
/* 116 */       if (this.theVillage != null && this.theVillage.getNumVillageDoors() >= 10 && this.theVillage.getTicksSinceLastDoorAdding() >= 20 && this.theVillage.getNumVillagers() >= 20) {
/*     */         
/* 118 */         ChunkCoordinates var4 = this.theVillage.getCenter();
/* 119 */         float var5 = this.theVillage.getVillageRadius();
/* 120 */         boolean var6 = false;
/* 121 */         int var7 = 0;
/*     */ 
/*     */ 
/*     */         
/* 125 */         while (var7 < 10) {
/*     */           
/* 127 */           this.field_75532_g = var4.posX + (int)((MathHelper.cos(this.worldObj.rand.nextFloat() * 3.1415927F * 2.0F) * var5) * 0.9D);
/* 128 */           this.field_75538_h = var4.posY;
/* 129 */           this.field_75539_i = var4.posZ + (int)((MathHelper.sin(this.worldObj.rand.nextFloat() * 3.1415927F * 2.0F) * var5) * 0.9D);
/* 130 */           var6 = false;
/* 131 */           Iterator<Village> var8 = this.worldObj.villageCollectionObj.getVillageList().iterator();
/*     */           
/* 133 */           while (var8.hasNext()) {
/*     */             
/* 135 */             Village var9 = var8.next();
/*     */             
/* 137 */             if (var9 != this.theVillage && var9.isInRange(this.field_75532_g, this.field_75538_h, this.field_75539_i)) {
/*     */               
/* 139 */               var6 = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 144 */           if (var6)
/*     */           {
/* 146 */             var7++;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 151 */         if (var6)
/*     */         {
/* 153 */           return false;
/*     */         }
/*     */         
/* 156 */         Vec3 var10 = func_75527_a(this.field_75532_g, this.field_75538_h, this.field_75539_i);
/*     */         
/* 158 */         if (var10 != null) {
/*     */           
/* 160 */           this.field_75534_e = 0;
/* 161 */           this.field_75533_d = 20;
/* 162 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   private boolean spawnZombie() {
/*     */     EntityZombie var2;
/* 175 */     Vec3 var1 = func_75527_a(this.field_75532_g, this.field_75538_h, this.field_75539_i);
/*     */     
/* 177 */     if (var1 == null)
/*     */     {
/* 179 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 187 */       var2 = new EntityZombie(this.worldObj);
/* 188 */       var2.onSpawnWithEgg((EntityLivingData)null);
/* 189 */       var2.setVillager(false, 0);
/*     */     }
/* 191 */     catch (Exception var4) {
/*     */       
/* 193 */       var4.printStackTrace();
/* 194 */       return false;
/*     */     } 
/*     */     
/* 197 */     var2.setLocationAndAngles(var1.xCoord, var1.yCoord, var1.zCoord, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
/* 198 */     this.worldObj.spawnEntityInWorld(var2);
/* 199 */     ChunkCoordinates var3 = this.theVillage.getCenter();
/* 200 */     var2.setHomeArea(var3.posX, var3.posY, var3.posZ, this.theVillage.getVillageRadius());
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Vec3 func_75527_a(int par1, int par2, int par3) {
/* 207 */     for (int var4 = 0; var4 < 10; var4++) {
/*     */       
/* 209 */       int var5 = par1 + this.worldObj.rand.nextInt(16) - 8;
/* 210 */       int var6 = par2 + this.worldObj.rand.nextInt(6) - 3;
/* 211 */       int var7 = par3 + this.worldObj.rand.nextInt(16) - 8;
/*     */       
/* 213 */       double[] resulting_y_pos = new double[1];
/*     */ 
/*     */       
/* 216 */       if (this.theVillage.isInRange(var5, var6, var7) && SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, this.worldObj, var5, var6, var7, false, resulting_y_pos) && this.worldObj.isAirOrPassableBlock(var5, var6 + 1, var7, false)) {
/*     */ 
/*     */         
/* 219 */         this.worldObj.getWorldVec3Pool().getVecFromPool(var5, resulting_y_pos[0], var7);
/*     */         
/* 221 */         if (Minecraft.inDevMode()) {
/* 222 */           Minecraft.setErrorMessage("VillageSiege: tried to spawn zombie at " + var5 + "," + var6 + "," + var7);
/*     */         }
/*     */       } 
/*     */     } 
/* 226 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\VillageSiege.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */