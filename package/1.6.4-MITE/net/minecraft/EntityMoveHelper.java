/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMoveHelper
/*     */ {
/*     */   private EntityLiving entity;
/*     */   private double posX;
/*     */   private double posY;
/*     */   private double posZ;
/*     */   private double speed;
/*     */   private boolean update;
/*     */   
/*     */   public EntityMoveHelper(EntityLiving par1EntityLiving) {
/*  20 */     this.entity = par1EntityLiving;
/*  21 */     this.posX = par1EntityLiving.posX;
/*  22 */     this.posY = par1EntityLiving.posY;
/*  23 */     this.posZ = par1EntityLiving.posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUpdating() {
/*  28 */     return this.update;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpeed() {
/*  33 */     return this.speed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMoveTo(double par1, double par3, double par5, double par7) {
/*  41 */     this.posX = par1;
/*  42 */     this.posY = par3;
/*  43 */     this.posZ = par5;
/*  44 */     this.speed = par7;
/*  45 */     this.update = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateMoveHelper() {
/*  50 */     this.entity.setMoveForward(0.0F);
/*     */     
/*  52 */     if (this.update) {
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
/*  87 */       double step_height = getStepHeightSimply();
/*     */ 
/*     */ 
/*     */       
/*  91 */       this.update = false;
/*  92 */       int var1 = MathHelper.floor_double(this.entity.boundingBox.minY + 0.5D);
/*     */       
/*  94 */       double var2 = this.posX - this.entity.posX;
/*  95 */       double var4 = this.posZ - this.entity.posZ;
/*  96 */       double var6 = this.posY - var1;
/*     */       
/*  98 */       double var8 = var2 * var2 + var6 * var6 + var4 * var4;
/*     */       
/* 100 */       if (var8 >= 2.500000277905201E-7D) {
/*     */         
/* 102 */         float var10 = (float)(Math.atan2(var4, var2) * 180.0D / Math.PI) - 90.0F;
/* 103 */         this.entity.rotationYaw = limitAngle(this.entity.rotationYaw, var10, 30.0F);
/*     */ 
/*     */         
/* 106 */         float speed_boost = 1.0F;
/*     */         
/* 108 */         if (this.entity.isFrenzied()) {
/* 109 */           speed_boost *= 1.2F;
/*     */         }
/* 111 */         this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * speed_boost));
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
/* 123 */         if (!this.entity.onGround) {
/*     */           return;
/*     */         }
/* 126 */         boolean in_cauldron = (this.entity.worldObj.getBlock(this.entity.getBlockPosX(), this.entity.getBlockPosY(), this.entity.getBlockPosZ()) == Block.cauldron);
/*     */         
/* 128 */         boolean should_jump = (in_cauldron || (step_height > this.entity.stepHeight && step_height <= 1.25D && var2 * var2 + var4 * var4 < 1.0D));
/*     */         
/* 130 */         if (should_jump) {
/* 131 */           this.entity.getJumpHelper().setJumping();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float limitAngle(float par1, float par2, float par3) {
/* 143 */     float var4 = MathHelper.wrapAngleTo180_float(par2 - par1);
/*     */     
/* 145 */     if (var4 > par3)
/*     */     {
/* 147 */       var4 = par3;
/*     */     }
/*     */     
/* 150 */     if (var4 < -par3)
/*     */     {
/* 152 */       var4 = -par3;
/*     */     }
/*     */     
/* 155 */     return par1 + var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double getStepHeightSimply() {
/* 161 */     double dx = this.posX - this.entity.posX;
/* 162 */     double dz = this.posZ - this.entity.posZ;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     AxisAlignedBB bb = this.entity.boundingBox.copy();
/*     */     
/* 170 */     bb.minY -= 2.0D;
/* 171 */     bb.maxY++;
/*     */     
/* 173 */     List list = this.entity.worldObj.getCollidingBlockBounds(bb.translate(dx, 0.0D, dz), this.entity);
/*     */ 
/*     */ 
/*     */     
/* 177 */     double block_top_y = (MathHelper.floor_double(this.posY) - 2);
/*     */     
/* 179 */     Iterator<AxisAlignedBB> i = list.iterator();
/*     */     
/* 181 */     while (i.hasNext()) {
/*     */       
/* 183 */       bb = i.next();
/*     */       
/* 185 */       if (bb.maxY > block_top_y) {
/* 186 */         block_top_y = bb.maxY;
/*     */       }
/*     */     } 
/* 189 */     double step_height = block_top_y - this.entity.boundingBox.minY;
/*     */     
/* 191 */     return step_height;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMoveHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */