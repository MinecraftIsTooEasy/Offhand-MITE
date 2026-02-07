/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
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
/*     */ public class EntityAITempt
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature temptedEntity;
/*     */   private double field_75282_b;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double field_75278_f;
/*     */   private double field_75279_g;
/*     */   private EntityPlayer temptingPlayer;
/*     */   private int delayTemptCounter;
/*     */   private boolean isRunning;
/*     */   private int breedingFood;
/*     */   private boolean scaredByPlayerMovement;
/*     */   private boolean field_75286_m;
/*     */   
/*     */   public EntityAITempt(EntityCreature par1EntityCreature, double par2, int par4, boolean par5) {
/*  47 */     this.temptedEntity = par1EntityCreature;
/*  48 */     this.field_75282_b = par2;
/*  49 */     this.breedingFood = par4;
/*  50 */     this.scaredByPlayerMovement = par5;
/*  51 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  59 */     if (this.delayTemptCounter > 0) {
/*     */       
/*  61 */       this.delayTemptCounter--;
/*  62 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     if (this.temptedEntity instanceof EntityLivestock && this.temptedEntity.hasFullHealth()) {
/*     */       
/*  68 */       EntityLivestock livestock = (EntityLivestock)this.temptedEntity;
/*     */       
/*  70 */       if (!livestock.isWell() && !livestock.isHungry()) {
/*  71 */         return false;
/*     */       }
/*     */     } 
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
/*  90 */     EntityPlayer closest_seen_player_holding_tempt_item = null;
/*  91 */     double distance_to_closest_seen_player_holding_tempt_item = 0.0D;
/*     */     
/*  93 */     Iterator<EntityPlayer> i = (this.temptedEntity.worldObj.getAsWorldServer()).playerEntities.iterator();
/*     */     
/*  95 */     while (i.hasNext()) {
/*     */       
/*  97 */       EntityPlayer player = i.next();
/*     */       
/*  99 */       double distance = this.temptedEntity.getDistanceToEntity(player);
/*     */       
/* 101 */       if (distance > 10.0D) {
/*     */         continue;
/*     */       }
/* 104 */       if (this.temptedEntity.canSeeEntity(player, true)) {
/*     */         
/* 106 */         ItemStack held_item_stack = player.getHeldItemStack();
/*     */         
/* 108 */         if (held_item_stack == null || held_item_stack.itemID != this.breedingFood) {
/*     */           continue;
/*     */         }
/* 111 */         if (player == this.temptingPlayer) {
/* 112 */           distance -= 4.0D;
/*     */         }
/* 114 */         if (closest_seen_player_holding_tempt_item == null || distance < distance_to_closest_seen_player_holding_tempt_item) {
/*     */           
/* 116 */           closest_seen_player_holding_tempt_item = player;
/* 117 */           distance_to_closest_seen_player_holding_tempt_item = distance;
/*     */         }  continue;
/*     */       } 
/* 120 */       if (player == this.temptingPlayer && this.temptedEntity.canPathTo(player.getBlockPosX(), player.getFootBlockPosY(), player.getBlockPosZ(), 16)) {
/*     */         
/* 122 */         distance += 4.0D;
/*     */         
/* 124 */         if (closest_seen_player_holding_tempt_item == null || distance < distance_to_closest_seen_player_holding_tempt_item) {
/*     */           
/* 126 */           closest_seen_player_holding_tempt_item = player;
/* 127 */           distance_to_closest_seen_player_holding_tempt_item = distance;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     this.temptingPlayer = closest_seen_player_holding_tempt_item;
/*     */     
/* 134 */     return (this.temptingPlayer != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 145 */     if (this.scaredByPlayerMovement) {
/*     */       
/* 147 */       if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D) {
/*     */         
/* 149 */         if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
/*     */         {
/* 151 */           return false;
/*     */         }
/*     */         
/* 154 */         if (Math.abs(this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0D)
/*     */         {
/* 156 */           return false;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 161 */         this.targetX = this.temptingPlayer.posX;
/* 162 */         this.targetY = this.temptingPlayer.posY;
/* 163 */         this.targetZ = this.temptingPlayer.posZ;
/*     */       } 
/*     */       
/* 166 */       this.field_75278_f = this.temptingPlayer.rotationPitch;
/* 167 */       this.field_75279_g = this.temptingPlayer.rotationYaw;
/*     */     } 
/*     */     
/* 170 */     return shouldExecute();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 178 */     this.targetX = this.temptingPlayer.posX;
/* 179 */     this.targetY = this.temptingPlayer.posY;
/* 180 */     this.targetZ = this.temptingPlayer.posZ;
/* 181 */     this.isRunning = true;
/* 182 */     this.field_75286_m = this.temptedEntity.getNavigator().getAvoidsWater();
/* 183 */     this.temptedEntity.getNavigator().setAvoidsWater(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 191 */     this.temptingPlayer = null;
/* 192 */     this.temptedEntity.getNavigator().clearPathEntity();
/* 193 */     this.delayTemptCounter = 100;
/* 194 */     this.isRunning = false;
/* 195 */     this.temptedEntity.getNavigator().setAvoidsWater(this.field_75286_m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 203 */     this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());
/*     */     
/* 205 */     if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D) {
/*     */       
/* 207 */       this.temptedEntity.getNavigator().clearPathEntity();
/*     */     }
/*     */     else {
/*     */       
/* 211 */       this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.field_75282_b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/* 220 */     return this.isRunning;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITempt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */