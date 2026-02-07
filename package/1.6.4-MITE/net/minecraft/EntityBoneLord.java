/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityBoneLord
/*     */   extends EntitySkeleton
/*     */ {
/*     */   int num_troops_summoned;
/*     */   
/*     */   public EntityBoneLord(World world) {
/*  15 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  20 */     super.applyEntityAttributes();
/*     */     
/*  22 */     setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
/*  23 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
/*  24 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
/*     */     
/*  26 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRandomWeapon() {
/*  31 */     List<RandomItemListEntry> items = new ArrayList();
/*     */     
/*  33 */     items.add(new RandomItemListEntry(Item.swordRustedIron, 2));
/*     */     
/*  35 */     if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
/*  36 */       items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
/*     */     }
/*  38 */     if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
/*  39 */       items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1));
/*     */     }
/*  41 */     RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
/*     */     
/*  43 */     setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addRandomEquipment() {
/*  48 */     addRandomWeapon();
/*     */     
/*  50 */     setBoots((new ItemStack(Item.bootsRustedIron)).randomizeForMob(this, true));
/*  51 */     setLeggings((new ItemStack(Item.legsRustedIron)).randomizeForMob(this, true));
/*  52 */     setCuirass((new ItemStack(Item.plateRustedIron)).randomizeForMob(this, true));
/*  53 */     setHelmet((new ItemStack(Item.helmetRustedIron)).randomizeForMob(this, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  58 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/*  60 */     this.num_troops_summoned = par1NBTTagCompound.getByte("num_troops_summoned");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  65 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  67 */     if (this.num_troops_summoned > 0) {
/*  68 */       par1NBTTagCompound.setByte("num_troops_summoned", (byte)this.num_troops_summoned);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getExperienceValue() {
/*  73 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/*  78 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getTroopClass() {
/*  83 */     return isAncientBoneLord() ? EntityLongdead.class : EntitySkeleton.class;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  88 */     super.onLivingUpdate();
/*     */     
/*  90 */     if (onServer()) {
/*     */       
/*  92 */       if (!isNoDespawnRequired() && getTarget() instanceof EntityPlayer) {
/*  93 */         func_110163_bv();
/*     */       }
/*  95 */       if (getTicksExistedWithOffset() % 20 == 0) {
/*     */         
/*  97 */         EntityLivingBase target = getTarget();
/*     */         
/*  99 */         if (target != null) {
/*     */           
/* 101 */           if (target.isDead || target.getHealth() <= 0.0F || getDistanceToEntity(target) > 16.0D || !canSeeEntity(target)) {
/* 102 */             target = null;
/*     */           }
/*     */           
/* 105 */           if (this.num_troops_summoned < 6 && target instanceof EntityPlayer && this.rand.nextInt(8) < 7 - this.num_troops_summoned) {
/*     */             
/* 107 */             this.num_troops_summoned += trySummonTroop(target);
/*     */             
/* 109 */             if (this.num_troops_summoned < 6 && this.rand.nextBoolean()) {
/* 110 */               this.num_troops_summoned += trySummonTroop(target);
/*     */             }
/*     */           } 
/*     */         } 
/* 114 */         List nearby_skeletons = this.worldObj.getEntitiesWithinAABB(EntitySkeleton.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
/*     */         
/* 116 */         Class<?> troop_class = getTroopClass();
/*     */         
/* 118 */         Iterator<EntitySkeleton> i = nearby_skeletons.iterator();
/*     */         
/* 120 */         while (i.hasNext()) {
/*     */           
/* 122 */           EntitySkeleton entity_skeleton = i.next();
/*     */           
/* 124 */           if (entity_skeleton == this || entity_skeleton.canSeeEntity(this, true)) {
/*     */             
/* 126 */             if (entity_skeleton.getHealth() < entity_skeleton.getMaxHealth()) {
/* 127 */               entity_skeleton.heal(1.0F);
/*     */             }
/* 129 */             if (!entity_skeleton.isBoneLord()) {
/*     */               
/* 131 */               entity_skeleton.setFrenziedByBoneLord(target);
/*     */               
/* 133 */               if (this.num_troops_summoned > 0 && entity_skeleton.getClass() == troop_class)
/*     */               {
/* 135 */                 if ((entity_skeleton.getAttackTarget() == null && entity_skeleton.getHealthFraction() >= 1.0F && this.rand.nextFloat() < 0.05F) || entity_skeleton.despawn_counter >= 0) {
/*     */                   
/* 137 */                   int despawn_counter = entity_skeleton.despawn_counter;
/* 138 */                   entity_skeleton.despawn_counter = Integer.MAX_VALUE;
/*     */                   
/* 140 */                   if (entity_skeleton.canDespawn()) {
/*     */                     
/* 142 */                     entity_skeleton.tryDespawnEntity();
/*     */                     
/* 144 */                     if (entity_skeleton.isDead) {
/* 145 */                       this.num_troops_summoned--; continue;
/*     */                     } 
/* 147 */                     entity_skeleton.despawn_counter = despawn_counter;
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int trySummonTroop(EntityLivingBase target) {
/* 160 */     int bone_lord_x = getBlockPosX();
/* 161 */     int bone_lord_y = getFootBlockPosY();
/* 162 */     int bone_lord_z = getBlockPosZ();
/*     */     
/* 164 */     int target_x = target.getBlockPosX();
/* 165 */     int target_y = target.getFootBlockPosY();
/* 166 */     int target_z = target.getBlockPosZ();
/*     */     
/* 168 */     Vec3 bone_lord_leg_pos = getFootPosPlusFractionOfHeight(0.25F);
/* 169 */     Vec3 bone_lord_head_pos = getFootPosPlusFractionOfHeight(0.75F);
/*     */     
/* 171 */     Class troop_class = getTroopClass();
/*     */     
/* 173 */     int max_num_attempts = 48 - this.num_troops_summoned * 8;
/*     */ 
/*     */     
/* 176 */     for (int attempts = 0; attempts < max_num_attempts; attempts++) {
/*     */       
/* 178 */       EntitySkeleton skeleton = (EntitySkeleton)((WorldServer)this.worldObj).tryCreateNewLivingEntityCloseTo(bone_lord_x, bone_lord_y, bone_lord_z, 2, 12, troop_class, EnumCreatureType.monster);
/*     */       
/* 180 */       if (skeleton == null) {
/*     */         continue;
/*     */       }
/* 183 */       int skeleton_type = skeleton.getRandomSkeletonType(this.worldObj);
/*     */       
/* 185 */       skeleton.forced_skeleton_type = skeleton_type;
/*     */       
/* 187 */       if (this.worldObj.getClosestVulnerablePlayer(skeleton, 4.0D, false) == null && (skeleton_type != 0 || skeleton.canSeeEntity(target, true))) {
/*     */ 
/*     */ 
/*     */         
/* 191 */         double distance_sq_to_summoning_bone_lord = skeleton.getDistanceSqToEntity(this);
/*     */         
/* 193 */         Iterator<EntityPlayer> i = (this.worldObj.getAsWorldServer()).playerEntities.iterator();
/*     */         
/* 195 */         while (i.hasNext()) {
/*     */           
/* 197 */           EntityPlayer entity_player = i.next();
/*     */           
/* 199 */           if (entity_player.getHealth() <= 0.0F) {
/*     */             continue;
/*     */           }
/* 202 */           if (skeleton.getDistanceSqToEntity(entity_player) < distance_sq_to_summoning_bone_lord) {
/*     */             
/* 204 */             skeleton = null;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 209 */         if (skeleton == null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 218 */         PathNavigate navigator = skeleton.getNavigator();
/*     */         
/* 220 */         Vec3 skeleton_eye_pos = skeleton.getEyePos();
/*     */         
/* 222 */         boolean can_raycast_to_summoner = (skeleton_eye_pos.squareDistanceTo(bone_lord_leg_pos) < 256.0D && this.worldObj.checkForNoBlockCollision(skeleton_eye_pos, bone_lord_leg_pos, RaycastPolicies.for_piercing_projectile));
/*     */         
/* 224 */         if (!can_raycast_to_summoner) {
/*     */           
/* 226 */           can_raycast_to_summoner = (skeleton_eye_pos.squareDistanceTo(bone_lord_head_pos) < 256.0D && this.worldObj.checkForNoBlockCollision(skeleton_eye_pos, bone_lord_head_pos, RaycastPolicies.for_piercing_projectile));
/*     */           
/* 228 */           if (!can_raycast_to_summoner) {
/*     */             
/* 230 */             PathEntity path = this.worldObj.getEntityPathToXYZ(skeleton, bone_lord_x, bone_lord_y, bone_lord_z, 16.0F, navigator.canPassOpenWoodenDoors, false, navigator.avoidsWater, navigator.canSwim);
/*     */             
/* 232 */             if (path == null) {
/*     */               continue;
/*     */             }
/* 235 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/* 237 */             if (World.getDistanceSqFromDeltas((final_point.xCoord - bone_lord_x), (final_point.yCoord - bone_lord_y), (final_point.zCoord - bone_lord_z)) > 2.0D) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 255 */         if (skeleton_type != 0)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 260 */           if (skeleton_type == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 265 */             PathEntity path = this.worldObj.getEntityPathToXYZ(skeleton, target_x, target_y, target_z, 16.0F, navigator.canPassOpenWoodenDoors, false, navigator.avoidsWater, navigator.canSwim);
/*     */             
/* 267 */             if (path == null) {
/*     */               continue;
/*     */             }
/* 270 */             PathPoint final_point = path.getFinalPathPoint();
/*     */             
/* 272 */             if (World.getDistanceSqFromDeltas((final_point.xCoord - target_x), (final_point.yCoord - target_y), (final_point.zCoord - target_z)) > 2.0D)
/*     */               continue; 
/*     */           } 
/*     */         }
/* 276 */         skeleton.refreshDespawnCounter(-9600);
/*     */         
/* 278 */         this.worldObj.spawnEntityInWorld(skeleton);
/* 279 */         skeleton.onSpawnWithEgg((EntityLivingData)null);
/*     */         
/* 281 */         skeleton.setAttackTarget(target);
/*     */         
/* 283 */         skeleton.entityFX(EnumEntityFX.summoned);
/*     */         
/* 285 */         return 1;
/*     */       } 
/*     */       continue;
/*     */     } 
/* 289 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBoneLord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */