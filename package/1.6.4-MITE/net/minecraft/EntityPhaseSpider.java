/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPhaseSpider
/*     */   extends EntityWoodSpider
/*     */ {
/*     */   int max_num_evasions;
/*     */   int num_evasions;
/*     */   
/*     */   public EntityPhaseSpider(World world) {
/*  12 */     super(world);
/*     */     
/*  14 */     if (world != null && onServer()) {
/*  15 */       this.max_num_evasions = this.num_evasions = this.rand.nextInt(3) + 2;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  20 */     super.applyEntityAttributes();
/*  21 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  26 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  27 */     par1NBTTagCompound.setByte("max_num_evasions", (byte)this.max_num_evasions);
/*  28 */     par1NBTTagCompound.setByte("num_evasions", (byte)this.num_evasions);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  33 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  34 */     this.max_num_evasions = par1NBTTagCompound.getByte("max_num_evasions");
/*  35 */     this.num_evasions = par1NBTTagCompound.getByte("num_evasions");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  40 */     super.onLivingUpdate();
/*     */     
/*  42 */     if (onServer() && getHealth() > 0.0F) {
/*     */       
/*  44 */       int ticks_existed_with_offset = getTicksExistedWithOffset();
/*     */       
/*  46 */       if (this.num_evasions < this.max_num_evasions && ticks_existed_with_offset % 100 == 0) {
/*  47 */         this.num_evasions++;
/*     */       }
/*  49 */       if (hasPath() && (getTarget() != null || this.fleeing) && ticks_existed_with_offset % 10 == 0 && this.rand.nextInt(3) == 0) {
/*     */         
/*  51 */         PathEntity path = getPathToEntity();
/*     */         
/*  53 */         if (!path.isFinished()) {
/*     */           
/*  55 */           int n = path.getNumRemainingPathPoints();
/*     */           
/*  57 */           if (n > 1) {
/*     */             
/*  59 */             int path_index_advancement = MathHelper.clamp_int(this.rand.nextInt(n), 1, 4);
/*     */             
/*  61 */             PathPoint path_point = path.getPathPointFromCurrentIndex(path_index_advancement);
/*     */             
/*  63 */             if (path_point.distanceSqTo(this) > 3.0D)
/*     */             {
/*  65 */               if (tryTeleportTo(path_point.xCoord + 0.5D, path_point.yCoord, path_point.zCoord + 0.5D)) {
/*  66 */                 path.setCurrentPathIndex(path.getCurrentPathIndex() + path_index_advancement - 1);
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean tryTeleportTo(double pos_x, double pos_y, double pos_z) {
/*  76 */     if (this.isDead || getHealth() <= 0.0F) {
/*  77 */       return false;
/*     */     }
/*  79 */     int x = MathHelper.floor_double(pos_x);
/*  80 */     int y = MathHelper.floor_double(pos_y);
/*  81 */     int z = MathHelper.floor_double(pos_z);
/*     */     
/*  83 */     if (y < 1 || !this.worldObj.blockExists(x, y, z)) {
/*  84 */       return false;
/*     */     }
/*  86 */     while (!this.worldObj.isBlockSolid(x, --y, z)) {
/*     */       
/*  88 */       if (y < 1) {
/*  89 */         return false;
/*     */       }
/*  91 */       pos_y--;
/*     */     } 
/*     */     
/*  94 */     y++;
/*     */     
/*  96 */     if (this.worldObj.isBlockSolid(x, y, z) || this.worldObj.isLiquidBlock(x, y, z)) {
/*  97 */       return false;
/*     */     }
/*  99 */     double delta_pos_x = pos_x - this.posX;
/* 100 */     double delta_pos_y = pos_y - this.posY;
/* 101 */     double delta_pos_z = pos_z - this.posZ;
/*     */     
/* 103 */     AxisAlignedBB bb = this.boundingBox.translateCopy(delta_pos_x, delta_pos_y, delta_pos_z);
/*     */     
/* 105 */     if (!this.worldObj.getCollidingBoundingBoxes(this, bb).isEmpty() || this.worldObj.isAnyLiquid(bb)) {
/* 106 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 110 */     double distance = World.getDistanceFromDeltas(delta_pos_x, delta_pos_y, delta_pos_z);
/*     */     
/* 112 */     this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.runegate.ordinal()).setShort((int)(16.0D * distance)).setApproxPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
/*     */     
/* 114 */     this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0F, 1.0F);
/*     */     
/* 116 */     setPosition(pos_x, pos_y, pos_z);
/*     */     
/* 118 */     this.send_position_update_immediately = true;
/*     */     
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryTeleportAwayFrom(Entity entity, double min_distance) {
/* 125 */     if (this.isDead || getHealth() <= 0.0F) {
/* 126 */       return false;
/*     */     }
/* 128 */     double min_distance_sq = min_distance * min_distance;
/*     */     
/* 130 */     int x = getBlockPosX();
/* 131 */     int y = getFootBlockPosY();
/* 132 */     int z = getBlockPosZ();
/*     */     
/* 134 */     double threat_pos_x = (entity == null) ? this.posX : entity.posX;
/* 135 */     double threat_pos_z = (entity == null) ? this.posZ : entity.posZ;
/*     */     
/* 137 */     for (int attempts = 0; attempts < 64; attempts++) {
/*     */       
/* 139 */       int dx = this.rand.nextInt(11) - 5;
/* 140 */       int dy = this.rand.nextInt(9) - 4;
/* 141 */       int dz = this.rand.nextInt(11) - 5;
/*     */       
/* 143 */       if (Math.abs(dx) >= 3 || Math.abs(dz) >= 3) {
/*     */ 
/*     */         
/* 146 */         int try_x = x + dx;
/* 147 */         int try_y = y + dy;
/* 148 */         int try_z = z + dz;
/*     */         
/* 150 */         double try_pos_x = try_x + 0.5D;
/* 151 */         double try_pos_z = try_z + 0.5D;
/*     */         
/* 153 */         if (World.getDistanceSqFromDeltas(try_pos_x - threat_pos_x, try_pos_z - threat_pos_z) >= min_distance_sq)
/*     */         {
/*     */           
/* 156 */           if (try_y >= 1 && this.worldObj.blockExists(try_x, try_y, try_z)) {
/*     */ 
/*     */             
/* 159 */             while (!this.worldObj.isBlockSolid(try_x, --try_y, try_z)) {
/*     */               
/* 161 */               if (try_y < 1) {
/*     */                 break;
/*     */               }
/*     */             } 
/* 165 */             if (try_y >= 1) {
/*     */ 
/*     */               
/* 168 */               try_y++;
/*     */               
/* 170 */               if (!this.worldObj.isBlockSolid(try_x, try_y, try_z) && !this.worldObj.isLiquidBlock(try_x, try_y, try_z))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 176 */                 if (tryTeleportTo(try_pos_x, try_y, try_pos_z))
/*     */                 
/* 178 */                 { EntityPlayer target = findPlayerToAttack(Math.min(getMaxTargettingRange(), 24.0F));
/*     */                   
/* 180 */                   if (target != null && target != getTarget()) {
/* 181 */                     setTarget(target);
/*     */                   }
/* 183 */                   return true; }  } 
/*     */             } 
/*     */           }  } 
/*     */       } 
/* 187 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 192 */     boolean can_evade = true;
/*     */     
/* 194 */     if (damage.isFallDamage() || damage.isFireDamage() || damage.isPoison()) {
/* 195 */       can_evade = false;
/*     */     }
/* 197 */     if (can_evade && this.num_evasions > 0) {
/*     */       
/* 199 */       this.num_evasions--;
/*     */       
/* 201 */       Entity entity = damage.getImmediateEntity();
/*     */       
/* 203 */       if (entity == null) {
/* 204 */         entity = damage.getResponsibleEntity();
/*     */       }
/* 206 */       if (tryTeleportAwayFrom(entity, 3.0D)) {
/* 207 */         return null;
/*     */       }
/*     */     } 
/* 210 */     return super.attackEntityFrom(damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 215 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPhaseSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */