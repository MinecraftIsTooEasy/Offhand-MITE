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
/*     */ public class EntityAIOcelotSit
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityOcelot theOcelot;
/*     */   private final double field_75404_b;
/*     */   private int currentTick;
/*     */   private int field_75402_d;
/*     */   private int maxSittingTicks;
/*     */   private int sitableBlockX;
/*     */   private int sitableBlockY;
/*     */   private int sitableBlockZ;
/*     */   
/*     */   public EntityAIOcelotSit(EntityOcelot par1EntityOcelot, double par2) {
/*  26 */     this.theOcelot = par1EntityOcelot;
/*  27 */     this.field_75404_b = par2;
/*  28 */     setMutexBits(5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  36 */     return (this.theOcelot.isTamed() && !this.theOcelot.isSitting() && this.theOcelot.getRNG().nextDouble() <= 0.006500000134110451D && getNearbySitableBlockDistance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  44 */     return (this.currentTick <= this.maxSittingTicks && this.field_75402_d <= 60 && isSittableBlock(this.theOcelot.worldObj, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  52 */     this.theOcelot.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, (this.sitableBlockY + 1), this.sitableBlockZ + 0.5D, this.field_75404_b);
/*  53 */     this.currentTick = 0;
/*  54 */     this.field_75402_d = 0;
/*  55 */     this.maxSittingTicks = this.theOcelot.getRNG().nextInt(this.theOcelot.getRNG().nextInt(1200) + 1200) + 1200;
/*  56 */     this.theOcelot.func_70907_r().setSitting(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  64 */     this.theOcelot.setSitting(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  72 */     this.currentTick++;
/*  73 */     this.theOcelot.func_70907_r().setSitting(false);
/*     */     
/*  75 */     if (this.theOcelot.getDistanceSq(this.sitableBlockX, (this.sitableBlockY + 1), this.sitableBlockZ) > 1.0D) {
/*     */       
/*  77 */       this.theOcelot.setSitting(false);
/*  78 */       this.theOcelot.getNavigator().tryMoveToXYZ(this.sitableBlockX + 0.5D, (this.sitableBlockY + 1), this.sitableBlockZ + 0.5D, this.field_75404_b);
/*  79 */       this.field_75402_d++;
/*     */     }
/*  81 */     else if (!this.theOcelot.isSitting()) {
/*     */       
/*  83 */       this.theOcelot.setSitting(true);
/*     */     }
/*     */     else {
/*     */       
/*  87 */       this.field_75402_d--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean getNearbySitableBlockDistance() {
/*  96 */     int var1 = (int)this.theOcelot.posY;
/*  97 */     double var2 = 2.147483647E9D;
/*     */     
/*  99 */     for (int var4 = (int)this.theOcelot.posX - 8; var4 < this.theOcelot.posX + 8.0D; var4++) {
/*     */       
/* 101 */       for (int var5 = (int)this.theOcelot.posZ - 8; var5 < this.theOcelot.posZ + 8.0D; var5++) {
/*     */         
/* 103 */         if (isSittableBlock(this.theOcelot.worldObj, var4, var1, var5) && this.theOcelot.worldObj.isAirBlock(var4, var1 + 1, var5)) {
/*     */           
/* 105 */           double var6 = this.theOcelot.getDistanceSq(var4, var1, var5);
/*     */           
/* 107 */           if (var6 < var2) {
/*     */             
/* 109 */             this.sitableBlockX = var4;
/* 110 */             this.sitableBlockY = var1;
/* 111 */             this.sitableBlockZ = var5;
/* 112 */             var2 = var6;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     return (var2 < 2.147483647E9D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSittableBlock(World par1World, int par2, int par3, int par4) {
/* 126 */     int var5 = par1World.getBlockId(par2, par3, par4);
/* 127 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/* 129 */     if (var5 == Block.chest.blockID) {
/*     */       
/* 131 */       TileEntityChest var7 = (TileEntityChest)par1World.getBlockTileEntity(par2, par3, par4);
/*     */       
/* 133 */       if (var7.numUsingPlayers < 1)
/*     */       {
/* 135 */         return true;
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 145 */       if (var5 > 0) {
/*     */         
/* 147 */         Block block = Block.blocksList[var5];
/*     */         
/* 149 */         if (block instanceof BlockFurnace && ((BlockFurnace)block).isActive) {
/* 150 */           return true;
/*     */         }
/*     */       } 
/* 153 */       if (var5 == Block.bed.blockID && !BlockBed.isBlockHeadOfBed(var6))
/*     */       {
/* 155 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 159 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIOcelotSit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */