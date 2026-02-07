/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityEnderChest
/*     */   extends TileEntity
/*     */ {
/*     */   public float lidAngle;
/*     */   public float prevLidAngle;
/*     */   public int numUsingPlayers;
/*     */   private int ticksSinceSync;
/*     */   
/*     */   public String getUnlocalizedInvName() {
/*  19 */     return "container.enderchest";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/*  28 */     super.updateEntity();
/*     */     
/*  30 */     if (++this.ticksSinceSync % 20 * 4 == 0)
/*     */     {
/*  32 */       this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, Block.enderChest.blockID, 1, this.numUsingPlayers);
/*     */     }
/*     */     
/*  35 */     this.prevLidAngle = this.lidAngle;
/*  36 */     float var1 = 0.1F;
/*     */ 
/*     */     
/*  39 */     if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F) {
/*     */       
/*  41 */       double var2 = this.xCoord + 0.5D;
/*  42 */       double var4 = this.zCoord + 0.5D;
/*  43 */       this.worldObj.playSoundEffect(var2, this.yCoord + 0.5D, var4, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */     
/*  46 */     if ((this.numUsingPlayers == 0 && this.lidAngle > 0.0F) || (this.numUsingPlayers > 0 && this.lidAngle < 1.0F)) {
/*     */       
/*  48 */       float var8 = this.lidAngle;
/*     */       
/*  50 */       if (this.numUsingPlayers > 0) {
/*     */         
/*  52 */         this.lidAngle += var1;
/*     */       }
/*     */       else {
/*     */         
/*  56 */         this.lidAngle -= var1;
/*     */       } 
/*     */       
/*  59 */       if (this.lidAngle > 1.0F)
/*     */       {
/*  61 */         this.lidAngle = 1.0F;
/*     */       }
/*     */       
/*  64 */       float var3 = 0.5F;
/*     */       
/*  66 */       if (this.lidAngle < var3 && var8 >= var3) {
/*     */         
/*  68 */         double var4 = this.xCoord + 0.5D;
/*  69 */         double var6 = this.zCoord + 0.5D;
/*  70 */         this.worldObj.playSoundEffect(var4, this.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */       
/*  73 */       if (this.lidAngle < 0.0F)
/*     */       {
/*  75 */         this.lidAngle = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int par1, int par2) {
/*  85 */     if (par1 == 1) {
/*     */       
/*  87 */       this.numUsingPlayers = par2;
/*  88 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  92 */     return super.receiveClientEvent(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 101 */     updateContainingBlockInfo();
/* 102 */     super.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void openChest() {
/* 107 */     this.numUsingPlayers++;
/* 108 */     this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, Block.enderChest.blockID, 1, this.numUsingPlayers);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeChest() {
/* 113 */     this.numUsingPlayers--;
/* 114 */     this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, Block.enderChest.blockID, 1, this.numUsingPlayers);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 119 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityEnderChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */