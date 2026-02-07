/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public abstract class MobSpawnerBaseLogic
/*     */ {
/*  10 */   public int spawnDelay = 20;
/*  11 */   private String mobID = "Pig";
/*     */   
/*     */   private List minecartToSpawn;
/*     */   
/*     */   private WeightedRandomMinecart randomMinecart;
/*     */   public double field_98287_c;
/*     */   public double field_98284_d;
/*  18 */   private int minSpawnDelay = 200;
/*  19 */   private int maxSpawnDelay = 800;
/*     */ 
/*     */   
/*  22 */   private int spawnCount = 4;
/*     */   private Entity field_98291_j;
/*  24 */   private int maxNearbyEntities = 6;
/*     */ 
/*     */   
/*  27 */   private int activatingRangeFromPlayer = 16;
/*     */ 
/*     */   
/*  30 */   private int spawnRange = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityNameToSpawn() {
/*  37 */     if (getRandomMinecart() == null) {
/*     */       
/*  39 */       if (this.mobID.equals("Minecart"))
/*     */       {
/*  41 */         this.mobID = "MinecartRideable";
/*     */       }
/*     */       
/*  44 */       return this.mobID;
/*     */     } 
/*     */ 
/*     */     
/*  48 */     return (getRandomMinecart()).minecartName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMobID(String par1Str) {
/*  54 */     this.mobID = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRun() {
/*  63 */     return (getSpawnerWorld().getClosestPlayer(getSpawnerX() + 0.5D, getSpawnerY() + 0.5D, getSpawnerZ() + 0.5D, this.activatingRangeFromPlayer, true) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSpawner() {
/*  68 */     if (canRun())
/*     */     {
/*     */ 
/*     */       
/*  72 */       if ((getSpawnerWorld()).isRemote) {
/*     */         
/*  74 */         double var1 = (getSpawnerX() + (getSpawnerWorld()).rand.nextFloat());
/*  75 */         double var3 = (getSpawnerY() + (getSpawnerWorld()).rand.nextFloat());
/*  76 */         double var5 = (getSpawnerZ() + (getSpawnerWorld()).rand.nextFloat());
/*     */ 
/*     */ 
/*     */         
/*  80 */         getSpawnerWorld().spawnParticle(EnumParticle.smoke, var1, var3, var5, 0.0D, 0.0D, 0.0D);
/*  81 */         getSpawnerWorld().spawnParticle(EnumParticle.flame, var1, var3, var5, 0.0D, 0.0D, 0.0D);
/*     */         
/*  83 */         if (this.spawnDelay > 0)
/*     */         {
/*  85 */           this.spawnDelay--;
/*     */         }
/*     */         
/*  88 */         this.field_98284_d = this.field_98287_c;
/*  89 */         this.field_98287_c = (this.field_98287_c + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
/*     */       }
/*     */       else {
/*     */         
/*  93 */         if (this.spawnDelay == -1)
/*     */         {
/*  95 */           func_98273_j();
/*     */         }
/*     */         
/*  98 */         if (this.spawnDelay > 0) {
/*     */           
/* 100 */           this.spawnDelay--;
/*     */           
/*     */           return;
/*     */         } 
/* 104 */         boolean var12 = false;
/*     */         
/* 106 */         for (int var2 = 0; var2 < this.spawnCount; var2++) {
/*     */           
/* 108 */           Entity var13 = EntityList.createEntityByName(getEntityNameToSpawn(), getSpawnerWorld());
/*     */           
/* 110 */           if (var13 == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 115 */           int var4 = getSpawnerWorld().getEntitiesWithinAABB(var13.getClass(), AxisAlignedBB.getAABBPool().getAABB(getSpawnerX(), getSpawnerY(), getSpawnerZ(), (getSpawnerX() + 1), (getSpawnerY() + 1), (getSpawnerZ() + 1)).expand((this.spawnRange * 2), 4.0D, (this.spawnRange * 2))).size();
/*     */           
/* 117 */           if (var4 >= this.maxNearbyEntities) {
/*     */             
/* 119 */             func_98273_j();
/*     */             
/*     */             return;
/*     */           } 
/* 123 */           double var5 = getSpawnerX() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange;
/* 124 */           double var7 = (getSpawnerY() + (getSpawnerWorld()).rand.nextInt(3) - 1);
/* 125 */           double var9 = getSpawnerZ() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange;
/* 126 */           EntityLiving var11 = (var13 instanceof EntityLiving) ? (EntityLiving)var13 : null;
/* 127 */           var13.setLocationAndAngles(var5, var7, var9, (getSpawnerWorld()).rand.nextFloat() * 360.0F, 0.0F);
/*     */           
/* 129 */           if (var11 != null) {
/*     */             
/* 131 */             var11.came_from_spawner = true;
/*     */             
/* 133 */             if (this instanceof TileEntityMobSpawnerLogic) {
/* 134 */               var11.setSpawnBlock(getSpawnerX(), getSpawnerY(), getSpawnerZ());
/*     */             }
/*     */           } 
/*     */           
/* 138 */           if (var11 == null || var11.getCanSpawnHere(true)) {
/*     */             
/* 140 */             func_98265_a(var13);
/* 141 */             getSpawnerWorld().playAuxSFX(2004, getSpawnerX(), getSpawnerY(), getSpawnerZ(), 0);
/*     */             
/* 143 */             if (var11 != null)
/*     */             {
/* 145 */               var11.spawnExplosionParticle();
/*     */             }
/*     */             
/* 148 */             var12 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 152 */         if (var12) {
/*     */           
/* 154 */           func_98273_j();
/*     */         }
/*     */         else {
/*     */           
/* 158 */           this.spawnDelay += 10;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity func_98265_a(Entity par1Entity) {
/* 166 */     if (getRandomMinecart() != null) {
/*     */       
/* 168 */       NBTTagCompound var2 = new NBTTagCompound();
/* 169 */       par1Entity.writeToNBTOptional(var2);
/* 170 */       Iterator<NBTBase> var3 = (getRandomMinecart()).field_98222_b.getTags().iterator();
/*     */       
/* 172 */       while (var3.hasNext()) {
/*     */         
/* 174 */         NBTBase var4 = var3.next();
/* 175 */         var2.setTag(var4.getName(), var4.copy());
/*     */       } 
/*     */       
/* 178 */       par1Entity.readFromNBT(var2);
/*     */       
/* 180 */       if (par1Entity.worldObj != null)
/*     */       {
/* 182 */         par1Entity.worldObj.spawnEntityInWorld(par1Entity);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 187 */       for (Entity var9 = par1Entity; var2.hasKey("Riding"); var2 = var10)
/*     */       {
/* 189 */         NBTTagCompound var10 = var2.getCompoundTag("Riding");
/* 190 */         Entity var5 = EntityList.createEntityByName(var10.getString("id"), par1Entity.worldObj);
/*     */         
/* 192 */         if (var5 != null) {
/*     */           
/* 194 */           NBTTagCompound var6 = new NBTTagCompound();
/* 195 */           var5.writeToNBTOptional(var6);
/* 196 */           Iterator<NBTBase> var7 = var10.getTags().iterator();
/*     */           
/* 198 */           while (var7.hasNext()) {
/*     */             
/* 200 */             NBTBase var8 = var7.next();
/* 201 */             var6.setTag(var8.getName(), var8.copy());
/*     */           } 
/*     */           
/* 204 */           var5.readFromNBT(var6);
/* 205 */           var5.setLocationAndAngles(var9.posX, var9.posY, var9.posZ, var9.rotationYaw, var9.rotationPitch);
/*     */           
/* 207 */           if (par1Entity.worldObj != null)
/*     */           {
/* 209 */             par1Entity.worldObj.spawnEntityInWorld(var5);
/*     */           }
/*     */           
/* 212 */           var9.mountEntity(var5);
/*     */         } 
/*     */         
/* 215 */         var9 = var5;
/*     */       }
/*     */     
/* 218 */     } else if (par1Entity instanceof EntityLivingBase && par1Entity.worldObj != null) {
/*     */       
/* 220 */       ((EntityLiving)par1Entity).onSpawnWithEgg((EntityLivingData)null);
/* 221 */       getSpawnerWorld().spawnEntityInWorld(par1Entity);
/*     */     } 
/*     */     
/* 224 */     return par1Entity;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_98273_j() {
/* 229 */     if (this.maxSpawnDelay <= this.minSpawnDelay) {
/*     */       
/* 231 */       this.spawnDelay = this.minSpawnDelay;
/*     */     }
/*     */     else {
/*     */       
/* 235 */       int var10003 = this.maxSpawnDelay - this.minSpawnDelay;
/* 236 */       this.spawnDelay = this.minSpawnDelay + (getSpawnerWorld()).rand.nextInt(var10003);
/*     */     } 
/*     */     
/* 239 */     if (this.minecartToSpawn != null && this.minecartToSpawn.size() > 0)
/*     */     {
/* 241 */       setRandomMinecart((WeightedRandomMinecart)WeightedRandom.getRandomItem((getSpawnerWorld()).rand, this.minecartToSpawn));
/*     */     }
/*     */ 
/*     */     
/* 245 */     func_98267_a(EnumEntityState.mob_spawn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 250 */     this.mobID = par1NBTTagCompound.getString("EntityId");
/* 251 */     this.spawnDelay = par1NBTTagCompound.getShort("Delay");
/*     */     
/* 253 */     if (par1NBTTagCompound.hasKey("SpawnPotentials")) {
/*     */       
/* 255 */       this.minecartToSpawn = new ArrayList();
/* 256 */       NBTTagList var2 = par1NBTTagCompound.getTagList("SpawnPotentials");
/*     */       
/* 258 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*     */       {
/* 260 */         this.minecartToSpawn.add(new WeightedRandomMinecart(this, (NBTTagCompound)var2.tagAt(var3)));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 265 */       this.minecartToSpawn = null;
/*     */     } 
/*     */     
/* 268 */     if (par1NBTTagCompound.hasKey("SpawnData")) {
/*     */       
/* 270 */       setRandomMinecart(new WeightedRandomMinecart(this, par1NBTTagCompound.getCompoundTag("SpawnData"), this.mobID));
/*     */     }
/*     */     else {
/*     */       
/* 274 */       setRandomMinecart((WeightedRandomMinecart)null);
/*     */     } 
/*     */     
/* 277 */     if (par1NBTTagCompound.hasKey("MinSpawnDelay")) {
/*     */       
/* 279 */       this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
/* 280 */       this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
/* 281 */       this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
/*     */     } 
/*     */     
/* 284 */     if (par1NBTTagCompound.hasKey("MaxNearbyEntities")) {
/*     */       
/* 286 */       this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
/* 287 */       this.activatingRangeFromPlayer = par1NBTTagCompound.getShort("RequiredPlayerRange");
/*     */     } 
/*     */     
/* 290 */     if (par1NBTTagCompound.hasKey("SpawnRange"))
/*     */     {
/* 292 */       this.spawnRange = par1NBTTagCompound.getShort("SpawnRange");
/*     */     }
/*     */     
/* 295 */     if (getSpawnerWorld() != null && (getSpawnerWorld()).isRemote)
/*     */     {
/* 297 */       this.field_98291_j = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 303 */     par1NBTTagCompound.setString("EntityId", getEntityNameToSpawn());
/* 304 */     par1NBTTagCompound.setShort("Delay", (short)this.spawnDelay);
/* 305 */     par1NBTTagCompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
/* 306 */     par1NBTTagCompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
/* 307 */     par1NBTTagCompound.setShort("SpawnCount", (short)this.spawnCount);
/* 308 */     par1NBTTagCompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
/* 309 */     par1NBTTagCompound.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
/* 310 */     par1NBTTagCompound.setShort("SpawnRange", (short)this.spawnRange);
/*     */     
/* 312 */     if (getRandomMinecart() != null)
/*     */     {
/* 314 */       par1NBTTagCompound.setCompoundTag("SpawnData", (NBTTagCompound)(getRandomMinecart()).field_98222_b.copy());
/*     */     }
/*     */     
/* 317 */     if (getRandomMinecart() != null || (this.minecartToSpawn != null && this.minecartToSpawn.size() > 0)) {
/*     */       
/* 319 */       NBTTagList var2 = new NBTTagList();
/*     */       
/* 321 */       if (this.minecartToSpawn != null && this.minecartToSpawn.size() > 0) {
/*     */         
/* 323 */         Iterator<WeightedRandomMinecart> var3 = this.minecartToSpawn.iterator();
/*     */         
/* 325 */         while (var3.hasNext())
/*     */         {
/* 327 */           WeightedRandomMinecart var4 = var3.next();
/* 328 */           var2.appendTag(var4.func_98220_a());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 333 */         var2.appendTag(getRandomMinecart().func_98220_a());
/*     */       } 
/*     */       
/* 336 */       par1NBTTagCompound.setTag("SpawnPotentials", var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity func_98281_h() {
/* 342 */     if (this.field_98291_j == null) {
/*     */       
/* 344 */       Entity var1 = EntityList.createEntityByName(getEntityNameToSpawn(), (World)null);
/* 345 */       var1 = func_98265_a(var1);
/* 346 */       this.field_98291_j = var1;
/*     */     } 
/*     */     
/* 349 */     return this.field_98291_j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDelayToMin(int par1) {
/* 357 */     if (par1 == 1 && (getSpawnerWorld()).isRemote) {
/*     */       
/* 359 */       this.spawnDelay = this.minSpawnDelay;
/* 360 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 364 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedRandomMinecart getRandomMinecart() {
/* 370 */     return this.randomMinecart;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRandomMinecart(WeightedRandomMinecart par1WeightedRandomMinecart) {
/* 375 */     this.randomMinecart = par1WeightedRandomMinecart;
/*     */   }
/*     */   
/*     */   public abstract void func_98267_a(EnumEntityState paramEnumEntityState);
/*     */   
/*     */   public abstract World getSpawnerWorld();
/*     */   
/*     */   public abstract int getSpawnerX();
/*     */   
/*     */   public abstract int getSpawnerY();
/*     */   
/*     */   public abstract int getSpawnerZ();
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MobSpawnerBaseLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */