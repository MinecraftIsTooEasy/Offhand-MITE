/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityOtherPlayerMP
/*     */   extends AbstractClientPlayer
/*     */ {
/*     */   protected boolean isItemInUse;
/*     */   private int otherPlayerMPPosRotationIncrements;
/*     */   private double otherPlayerMPX;
/*     */   private double otherPlayerMPY;
/*     */   private double otherPlayerMPZ;
/*     */   private double otherPlayerMPYaw;
/*     */   private double otherPlayerMPPitch;
/*     */   private boolean initial_position_sync = true;
/*     */   
/*     */   public EntityOtherPlayerMP(World par1World, String par2Str) {
/*  18 */     super(par1World, par2Str);
/*  19 */     this.yOffset = 0.0F;
/*  20 */     this.stepHeight = 0.0F;
/*  21 */     this.noClip = true;
/*  22 */     this.field_71082_cx = 0.25F;
/*  23 */     this.renderDistanceWeight = 10.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void resetHeight() {
/*  31 */     this.yOffset = 0.0F;
/*     */   }
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
/*     */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/*  48 */     this.otherPlayerMPX = par1;
/*  49 */     this.otherPlayerMPY = par3;
/*  50 */     this.otherPlayerMPZ = par5;
/*  51 */     this.otherPlayerMPYaw = par7;
/*  52 */     this.otherPlayerMPPitch = par8;
/*  53 */     this.otherPlayerMPPosRotationIncrements = par9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  61 */     this.field_71082_cx = 0.0F;
/*  62 */     super.onUpdate();
/*  63 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/*  64 */     double var1 = this.posX - this.prevPosX;
/*  65 */     double var3 = this.posZ - this.prevPosZ;
/*  66 */     float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3) * 4.0F;
/*     */     
/*  68 */     if (var5 > 1.0F)
/*     */     {
/*  70 */       var5 = 1.0F;
/*     */     }
/*     */     
/*  73 */     this.limbSwingAmount += (var5 - this.limbSwingAmount) * 0.4F;
/*  74 */     this.limbSwing += this.limbSwingAmount;
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
/*  93 */     if (this.itemInUseCount > 0) {
/*  94 */       this.itemInUseCount--;
/*     */     }
/*     */   }
/*     */   
/*     */   public float getShadowSize() {
/*  99 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 108 */     updateEntityActionState();
/*     */     
/* 110 */     if (this.otherPlayerMPPosRotationIncrements > 0) {
/*     */       
/* 112 */       if (this.initial_position_sync) {
/*     */         
/* 114 */         this.initial_position_sync = false;
/*     */         
/* 116 */         this.otherPlayerMPPosRotationIncrements = 1;
/*     */         
/* 118 */         this.prevRotationYaw = (float)this.otherPlayerMPYaw;
/* 119 */         this.prevRotationYawHead = (float)this.otherPlayerMPYaw;
/* 120 */         this.prevRotationPitch = (float)this.otherPlayerMPPitch;
/*     */       } 
/*     */       
/* 123 */       double var1 = this.posX + (this.otherPlayerMPX - this.posX) / this.otherPlayerMPPosRotationIncrements;
/* 124 */       double var3 = this.posY + (this.otherPlayerMPY - this.posY) / this.otherPlayerMPPosRotationIncrements;
/* 125 */       double var5 = this.posZ + (this.otherPlayerMPZ - this.posZ) / this.otherPlayerMPPosRotationIncrements;
/*     */       
/*     */       double var7;
/* 128 */       for (var7 = this.otherPlayerMPYaw - this.rotationYaw; var7 < -180.0D; var7 += 360.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       while (var7 >= 180.0D)
/*     */       {
/* 135 */         var7 -= 360.0D;
/*     */       }
/*     */       
/* 138 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.otherPlayerMPPosRotationIncrements);
/* 139 */       this.rotationPitch = (float)(this.rotationPitch + (this.otherPlayerMPPitch - this.rotationPitch) / this.otherPlayerMPPosRotationIncrements);
/* 140 */       this.otherPlayerMPPosRotationIncrements--;
/* 141 */       setPosition(var1, var3, var5);
/* 142 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */     } 
/*     */     
/* 145 */     this.prevCameraYaw = this.cameraYaw;
/* 146 */     float var9 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 147 */     float var2 = (float)Math.atan(-this.motionY * 0.20000000298023224D) * 15.0F;
/*     */     
/* 149 */     if (var9 > 0.1F)
/*     */     {
/* 151 */       var9 = 0.1F;
/*     */     }
/*     */     
/* 154 */     if (!this.onGround || getHealth() <= 0.0F)
/*     */     {
/* 156 */       var9 = 0.0F;
/*     */     }
/*     */     
/* 159 */     if (this.onGround || getHealth() <= 0.0F)
/*     */     {
/* 161 */       var2 = 0.0F;
/*     */     }
/*     */     
/* 164 */     this.cameraYaw += (var9 - this.cameraYaw) * 0.4F;
/* 165 */     this.cameraPitch += (var2 - this.cameraPitch) * 0.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
/* 173 */     if (par1 == 0) {
/*     */       
/* 175 */       this.inventory.mainInventory[this.inventory.currentItem] = par2ItemStack;
/*     */     }
/*     */     else {
/*     */       
/* 179 */       this.inventory.armorInventory[par1 - 1] = par2ItemStack;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 185 */     return 1.82F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFootPosY() {
/* 191 */     double foot_pos_y = this.posY;
/*     */     
/* 193 */     int foot_pos_y_int = (int)foot_pos_y;
/*     */     
/* 195 */     if (foot_pos_y < foot_pos_y_int && foot_pos_y_int - foot_pos_y < 9.999999747378752E-5D) {
/* 196 */       foot_pos_y = foot_pos_y_int;
/*     */     }
/* 198 */     return foot_pos_y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyePosY() {
/* 204 */     return getFootPosY() + EntityPlayer.y_offset_on_client_and_eye_height_on_server;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {
/* 209 */     (Minecraft.getMinecraft()).ingameGUI.getChatGUI().printChatMessage(par1ChatMessageComponent.toStringWithFormatting(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int par1, String par2Str) {
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getPlayerCoordinates() {
/* 225 */     return new ChunkCoordinates(MathHelper.floor_double(this.posX + 0.5D), MathHelper.floor_double(this.posY + 0.5D), MathHelper.floor_double(this.posZ + 0.5D));
/*     */   }
/*     */ 
/*     */   
/*     */   public INetworkManager getNetManager() {
/* 230 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityOtherPlayerMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */