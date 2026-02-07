/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ClientPlayer
/*     */   extends AbstractClientPlayer
/*     */ {
/*     */   public MovementInput movementInput;
/*     */   protected Minecraft mc;
/*     */   protected int sprintToggleTimer;
/*     */   public int sprintingTicksLeft;
/*     */   public float renderArmYaw;
/*     */   public float renderArmPitch;
/*     */   public float prevRenderArmYaw;
/*     */   public float prevRenderArmPitch;
/*     */   private int horseJumpPowerCounter;
/*     */   private float horseJumpPower;
/*  23 */   private MouseFilter field_71162_ch = new MouseFilter();
/*  24 */   private MouseFilter field_71160_ci = new MouseFilter();
/*  25 */   private MouseFilter field_71161_cj = new MouseFilter();
/*     */ 
/*     */   
/*     */   public float timeInPortal;
/*     */ 
/*     */   
/*     */   public float prevTimeInPortal;
/*     */ 
/*     */   
/*     */   public Item crafting_item;
/*     */ 
/*     */   
/*     */   public int crafting_period;
/*     */   
/*     */   public boolean crafting_proceed;
/*     */   
/*     */   public int crafting_ticks;
/*     */   
/*     */   public int crafting_experience_cost;
/*     */   
/*     */   public int crafting_experience_cost_tentative;
/*     */   
/*     */   public int open_inventory_suppressed_countdown;
/*     */ 
/*     */   
/*     */   public ClientPlayer(Minecraft par1Minecraft, World par2World, Session par3Session, int par4) {
/*  51 */     super(par2World, par3Session.getUsername());
/*  52 */     this.mc = par1Minecraft;
/*  53 */     this.dimension = par4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEntityActionState() {
/*  58 */     super.updateEntityActionState();
/*  59 */     this.moveStrafing = this.movementInput.moveStrafe;
/*  60 */     this.moveForward = this.movementInput.moveForward;
/*  61 */     this.isJumping = this.movementInput.jump;
/*  62 */     this.prevRenderArmYaw = this.renderArmYaw;
/*  63 */     this.prevRenderArmPitch = this.renderArmPitch;
/*  64 */     this.renderArmPitch = (float)(this.renderArmPitch + (this.rotationPitch - this.renderArmPitch) * 0.5D);
/*  65 */     this.renderArmYaw = (float)(this.renderArmYaw + (this.rotationYaw - this.renderArmYaw) * 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  74 */     if (this.sprintingTicksLeft > 0) {
/*     */       
/*  76 */       this.sprintingTicksLeft--;
/*     */       
/*  78 */       if (this.sprintingTicksLeft == 0)
/*     */       {
/*  80 */         setSprinting(false);
/*     */       }
/*     */     } 
/*     */     
/*  84 */     if (this.sprintToggleTimer > 0)
/*     */     {
/*  86 */       this.sprintToggleTimer--;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.posX = this.posZ = 0.5D;
/*  92 */     this.posX = 0.0D;
/*  93 */     this.posZ = 0.0D;
/*  94 */     this.rotationYaw = this.ticksExisted / 12.0F;
/*  95 */     this.rotationPitch = 10.0F;
/*  96 */     this.posY = 68.5D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (this.open_inventory_suppressed_countdown == 0 && !PlayerStatsHelper.hasAchievementUnlocked(AchievementList.openInventory))
/*     */     {
/* 103 */       this.mc.guiAchievement.queueAchievementInformation(AchievementList.openInventory);
/*     */     }
/*     */     
/* 106 */     if (this.open_inventory_suppressed_countdown > 0) {
/* 107 */       this.open_inventory_suppressed_countdown--;
/*     */     }
/* 109 */     this.prevTimeInPortal = this.timeInPortal;
/*     */     
/* 111 */     if (this.inPortal) {
/*     */       
/* 113 */       if (this.mc.currentScreen != null)
/*     */       {
/* 115 */         this.mc.displayGuiScreen((GuiScreen)null);
/*     */       }
/*     */       
/* 118 */       if (this.mc.imposed_gui_chat != null) {
/* 119 */         this.mc.closeImposedChat();
/*     */       }
/* 121 */       if (this.timeInPortal == 0.0F)
/*     */       {
/* 123 */         this.mc.sndManager.playSoundFX("portal.trigger", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
/*     */       }
/*     */       
/* 126 */       this.timeInPortal += 0.0125F;
/*     */       
/* 128 */       if (this.timeInPortal >= 1.0F)
/*     */       {
/* 130 */         this.timeInPortal = 1.0F;
/*     */       }
/*     */       
/* 133 */       this.inPortal = false;
/*     */     
/*     */     }
/* 136 */     else if (isPotionActive(Potion.confusion) && getActivePotionEffect(Potion.confusion).getAmplifier() > 0 && getActivePotionEffect(Potion.confusion).getDuration() > 60) {
/*     */       
/* 138 */       this.timeInPortal += 0.006666667F;
/*     */       
/* 140 */       if (this.timeInPortal > 1.0F)
/*     */       {
/* 142 */         this.timeInPortal = 1.0F;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 147 */       if (this.timeInPortal > 0.0F)
/*     */       {
/* 149 */         this.timeInPortal -= 0.05F;
/*     */       }
/*     */       
/* 152 */       if (this.timeInPortal < 0.0F)
/*     */       {
/* 154 */         this.timeInPortal = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 158 */     if (this.timeUntilPortal > 0)
/*     */     {
/* 160 */       this.timeUntilPortal--;
/*     */     }
/*     */     
/* 163 */     boolean var1 = this.movementInput.jump;
/* 164 */     float var2 = 0.8F;
/* 165 */     boolean var3 = (this.movementInput.moveForward >= var2);
/* 166 */     this.movementInput.updatePlayerMoveState();
/*     */     
/* 168 */     if (isUsingItem() && !isRiding()) {
/*     */       
/* 170 */       this.movementInput.moveStrafe *= 0.2F;
/* 171 */       this.movementInput.moveForward *= 0.2F;
/* 172 */       this.sprintToggleTimer = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     pushOutOfBlocks(this.posX - this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + this.width * 0.35D);
/* 182 */     pushOutOfBlocks(this.posX - this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - this.width * 0.35D);
/* 183 */     pushOutOfBlocks(this.posX + this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - this.width * 0.35D);
/* 184 */     pushOutOfBlocks(this.posX + this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + this.width * 0.35D);
/*     */ 
/*     */     
/* 187 */     boolean var4 = (hasFoodEnergy() || this.capabilities.allowFlying);
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (this.onGround && (Minecraft.theMinecraft.playerController.isRunToggledOn(this) || !var3) && this.movementInput.moveForward >= var2 && !isSprinting() && var4 && !isUsingItem() && !isPotionActive(Potion.blindness))
/*     */     {
/* 193 */       if (this.sprintToggleTimer == 0) {
/*     */         
/* 195 */         this.sprintToggleTimer = 7;
/*     */       }
/*     */       else {
/*     */         
/* 199 */         setSprinting(true);
/* 200 */         this.sprintToggleTimer = 0;
/*     */       } 
/*     */     }
/*     */     
/* 204 */     if (isSneaking())
/*     */     {
/* 206 */       this.sprintToggleTimer = 0;
/*     */     }
/*     */     
/* 209 */     if (isSprinting() && (this.movementInput.moveForward < var2 || this.isCollidedHorizontally || !var4))
/*     */     {
/* 211 */       setSprinting(false);
/*     */     }
/*     */     
/* 214 */     if (this.capabilities.allowFlying && !var1 && this.movementInput.jump)
/*     */     {
/* 216 */       if (this.flyToggleTimer == 0) {
/*     */         
/* 218 */         this.flyToggleTimer = 7;
/*     */       }
/*     */       else {
/*     */         
/* 222 */         this.capabilities.isFlying = !this.capabilities.isFlying;
/* 223 */         sendPlayerAbilities();
/* 224 */         this.flyToggleTimer = 0;
/*     */       } 
/*     */     }
/*     */     
/* 228 */     if (this.capabilities.isFlying) {
/*     */       
/* 230 */       if (this.movementInput.sneak)
/*     */       {
/* 232 */         this.motionY -= 0.15D;
/*     */       }
/*     */       
/* 235 */       if (this.movementInput.jump)
/*     */       {
/* 237 */         this.motionY += 0.15D;
/*     */       }
/*     */     } 
/*     */     
/* 241 */     if (isRidingHorse()) {
/*     */       
/* 243 */       if (this.horseJumpPowerCounter < 0) {
/*     */         
/* 245 */         this.horseJumpPowerCounter++;
/*     */         
/* 247 */         if (this.horseJumpPowerCounter == 0)
/*     */         {
/* 249 */           this.horseJumpPower = 0.0F;
/*     */         }
/*     */       } 
/*     */       
/* 253 */       if (var1 && !this.movementInput.jump) {
/*     */         
/* 255 */         this.horseJumpPowerCounter = -10;
/* 256 */         func_110318_g();
/*     */       }
/* 258 */       else if (!var1 && this.movementInput.jump) {
/*     */         
/* 260 */         this.horseJumpPowerCounter = 0;
/* 261 */         this.horseJumpPower = 0.0F;
/*     */       }
/* 263 */       else if (var1) {
/*     */         
/* 265 */         this.horseJumpPowerCounter++;
/*     */         
/* 267 */         if (this.horseJumpPowerCounter < 10)
/*     */         {
/* 269 */           this.horseJumpPower = this.horseJumpPowerCounter * 0.1F;
/*     */         }
/*     */         else
/*     */         {
/* 273 */           this.horseJumpPower = 0.8F + 2.0F / (this.horseJumpPowerCounter - 9) * 0.1F;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 279 */       this.horseJumpPower = 0.0F;
/*     */     } 
/*     */     
/* 282 */     super.onLivingUpdate();
/*     */     
/* 284 */     if (this.onGround && this.capabilities.isFlying) {
/*     */       
/* 286 */       this.capabilities.isFlying = false;
/* 287 */       sendPlayerAbilities();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFOVMultiplier() {
/* 297 */     float var1 = 1.0F;
/*     */     
/* 299 */     if (this.capabilities.isFlying)
/*     */     {
/* 301 */       var1 *= 1.1F;
/*     */     }
/*     */     
/* 304 */     AttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 305 */     var1 = (float)(var1 * (var2.getAttributeValue() / this.capabilities.getWalkSpeed() + 1.0D) / 2.0D);
/*     */     
/* 307 */     var1 = Math.max(var1, 1.0F);
/*     */     
/* 309 */     var1 = 1.0F;
/*     */     
/* 311 */     boolean sneak_delta_y_enabled = true;
/*     */     
/* 313 */     if (isSprinting() || this.mc.playerController.isRunToggledOn(this)) {
/*     */ 
/*     */ 
/*     */       
/* 317 */       var1 *= 1.22F;
/* 318 */     } else if (isSneaking() && !sneak_delta_y_enabled) {
/*     */       
/* 320 */       var1 *= 0.88F;
/*     */     } 
/* 322 */     if (this.mc.thePlayer.zoomed) {
/* 323 */       var1 /= 4.0F;
/*     */     }
/*     */     
/* 326 */     if (isUsingItem() && getItemInUse().getItem() instanceof ItemBow) {
/*     */       
/* 328 */       int var3 = getItemInUseDuration();
/*     */       
/* 330 */       float var4 = var3 / ItemBow.getTicksForMaxPull(getItemInUse());
/*     */       
/* 332 */       if (var4 > 1.0F) {
/*     */         
/* 334 */         var4 = 1.0F;
/*     */       }
/*     */       else {
/*     */         
/* 338 */         var4 *= var4;
/*     */       } 
/*     */       
/* 341 */       var1 *= 1.0F - var4 * 0.15F;
/*     */     } 
/*     */     
/* 344 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeScreen() {
/* 352 */     super.closeScreen();
/* 353 */     this.mc.displayGuiScreen((GuiScreen)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIEditSign(TileEntity par1TileEntity) {
/* 361 */     if (par1TileEntity instanceof TileEntitySign) {
/*     */       
/* 363 */       this.mc.displayGuiScreen(new GuiEditSign((TileEntitySign)par1TileEntity));
/*     */     }
/* 365 */     else if (par1TileEntity instanceof TileEntityCommandBlock) {
/*     */       
/* 367 */       this.mc.displayGuiScreen(new GuiCommandBlock((TileEntityCommandBlock)par1TileEntity));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIBook(ItemStack par1ItemStack) {
/* 376 */     Item var2 = par1ItemStack.getItem();
/*     */ 
/*     */     
/* 379 */     if (var2 instanceof ItemEditableBook) {
/*     */       
/* 381 */       this.mc.displayGuiScreen(new GuiScreenBook(this, par1ItemStack, false));
/*     */     }
/* 383 */     else if (var2 == Item.writableBook) {
/*     */       
/* 385 */       this.mc.displayGuiScreen(new GuiScreenBook(this, par1ItemStack, true));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayGUIChestForMinecart(IInventory par1IInventory) {
/* 391 */     this.mc.displayGuiScreen(new GuiChest(this, par1IInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIChest(int x, int y, int z, IInventory par1IInventory) {
/* 401 */     this.mc.displayGuiScreen(new GuiChest(this, par1IInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIHopper(TileEntityHopper par1TileEntityHopper) {
/* 407 */     this.mc.displayGuiScreen(new GuiHopper(this, par1TileEntityHopper));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIHopperMinecart(EntityMinecartHopper par1EntityMinecartHopper) {
/* 413 */     this.mc.displayGuiScreen(new GuiHopper(this, par1EntityMinecartHopper));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIHorse(EntityHorse par1EntityHorse, IInventory par2IInventory) {
/* 419 */     this.mc.displayGuiScreen(new GuiScreenHorseInventory(this, par2IInventory, par1EntityHorse));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIWorkbench(int par1, int par2, int par3) {
/* 428 */     this.mc.displayGuiScreen(new GuiCrafting(this, this.worldObj, par1, par2, par3));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIEnchantment(int par1, int par2, int par3, String par4Str) {
/* 434 */     this.mc.displayGuiScreen(new GuiEnchantment(this, this.worldObj, par1, par2, par3, par4Str));
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
/*     */   public void displayGUIAnvil(int x, int y, int z) {
/* 450 */     this.mc.displayGuiScreen(new GuiRepair(this, x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIFurnace(TileEntityFurnace par1TileEntityFurnace) {
/* 459 */     this.mc.displayGuiScreen(new GuiFurnace(this, par1TileEntityFurnace));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIBrewingStand(TileEntityBrewingStand par1TileEntityBrewingStand) {
/* 468 */     this.mc.displayGuiScreen(new GuiBrewingStand(this, par1TileEntityBrewingStand));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIBeacon(TileEntityBeacon par1TileEntityBeacon) {
/* 477 */     this.mc.displayGuiScreen(new GuiBeacon(this, par1TileEntityBeacon));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIDispenser(TileEntityDispenser par1TileEntityDispenser) {
/* 486 */     this.mc.displayGuiScreen(new GuiDispenser(this, par1TileEntityDispenser));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIMerchant(IMerchant par1IMerchant, String par2Str) {
/* 492 */     this.mc.displayGuiScreen(new GuiMerchant(this, par1IMerchant, par2Str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCriticalHit(Entity par1Entity) {
/* 500 */     this.mc.effectRenderer.addEffect(new EntityCrit2FX(this.mc.theWorld, par1Entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnchantmentCritical(Entity par1Entity) {
/* 506 */     EntityCrit2FX var2 = new EntityCrit2FX(this.mc.theWorld, par1Entity, EnumParticle.magicCrit);
/* 507 */     this.mc.effectRenderer.addEffect(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onItemPickup(Entity par1Entity, int par2) {
/* 515 */     this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, par1Entity, this, -0.5F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSneaking() {
/* 525 */     return (this.movementInput.sneak && !inBed() && !this.capabilities.isFlying);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerSPHealth(float par1) {
/* 533 */     float var2 = getHealth() - par1;
/*     */     
/* 535 */     if (var2 <= 0.0F) {
/*     */       
/* 537 */       setHealth(par1);
/*     */       
/* 539 */       if (var2 < 0.0F)
/*     */       {
/* 541 */         this.hurtResistantTime = this.maxHurtResistantTime / 2;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 546 */       this.lastDamage = var2;
/*     */       
/* 548 */       this.hurtResistantTime = this.maxHurtResistantTime;
/*     */       
/* 550 */       this.hurtTime = this.maxHurtTime = 10;
/*     */       
/* 552 */       setHealth(par1);
/*     */     } 
/*     */     
/* 555 */     if (getHealth() <= 0.0F && this.mc.currentScreen != null) {
/* 556 */       this.mc.displayGuiScreen(null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChatMessage(String par1Str) {
/* 564 */     this.mc.ingameGUI.getChatGUI().addTranslatedMessage(par1Str, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStat(StatBase par1StatBase, int par2) {
/* 572 */     if (par1StatBase != null)
/*     */     {
/* 574 */       if (par1StatBase.isAchievement()) {
/*     */         
/* 576 */         Achievement var3 = (Achievement)par1StatBase;
/*     */ 
/*     */ 
/*     */         
/* 580 */         if (!this.mc.statFileWriter.hasAchievementUnlocked(var3))
/*     */         {
/*     */           
/* 583 */           this.mc.thePlayer.sendQueue.addToSendQueue((new Packet85SimpleSignal(EnumSignal.achievement_unlocked)).setInteger(var3.statId));
/*     */         }
/*     */         
/* 586 */         this.mc.statFileWriter.readStat(par1StatBase, par2);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 591 */         this.mc.statFileWriter.readStat(par1StatBase, par2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockTranslucent(int par1, int par2, int par3) {
/* 598 */     return this.worldObj.isBlockNormalCube(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
/* 606 */     int var7 = MathHelper.floor_double(par1);
/* 607 */     int var8 = MathHelper.floor_double(par3);
/* 608 */     int var9 = MathHelper.floor_double(par5);
/* 609 */     double var10 = par1 - var7;
/* 610 */     double var12 = par5 - var9;
/*     */     
/* 612 */     if (isBlockTranslucent(var7, var8, var9) || isBlockTranslucent(var7, var8 + 1, var9)) {
/*     */       
/* 614 */       boolean var14 = (!isBlockTranslucent(var7 - 1, var8, var9) && !isBlockTranslucent(var7 - 1, var8 + 1, var9));
/* 615 */       boolean var15 = (!isBlockTranslucent(var7 + 1, var8, var9) && !isBlockTranslucent(var7 + 1, var8 + 1, var9));
/* 616 */       boolean var16 = (!isBlockTranslucent(var7, var8, var9 - 1) && !isBlockTranslucent(var7, var8 + 1, var9 - 1));
/* 617 */       boolean var17 = (!isBlockTranslucent(var7, var8, var9 + 1) && !isBlockTranslucent(var7, var8 + 1, var9 + 1));
/* 618 */       byte var18 = -1;
/* 619 */       double var19 = 9999.0D;
/*     */       
/* 621 */       if (var14 && var10 < var19) {
/*     */         
/* 623 */         var19 = var10;
/* 624 */         var18 = 0;
/*     */       } 
/*     */       
/* 627 */       if (var15 && 1.0D - var10 < var19) {
/*     */         
/* 629 */         var19 = 1.0D - var10;
/* 630 */         var18 = 1;
/*     */       } 
/*     */       
/* 633 */       if (var16 && var12 < var19) {
/*     */         
/* 635 */         var19 = var12;
/* 636 */         var18 = 4;
/*     */       } 
/*     */       
/* 639 */       if (var17 && 1.0D - var12 < var19) {
/*     */         
/* 641 */         var19 = 1.0D - var12;
/* 642 */         var18 = 5;
/*     */       } 
/*     */       
/* 645 */       float var21 = 0.1F;
/*     */       
/* 647 */       if (var18 == 0)
/*     */       {
/* 649 */         this.motionX = -var21;
/*     */       }
/*     */       
/* 652 */       if (var18 == 1)
/*     */       {
/* 654 */         this.motionX = var21;
/*     */       }
/*     */       
/* 657 */       if (var18 == 4)
/*     */       {
/* 659 */         this.motionZ = -var21;
/*     */       }
/*     */       
/* 662 */       if (var18 == 5)
/*     */       {
/* 664 */         this.motionZ = var21;
/*     */       }
/*     */     } 
/*     */     
/* 668 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSprinting(boolean par1) {
/* 676 */     if (par1 && hasCurse(Curse.cannot_run, true)) {
/* 677 */       par1 = false;
/*     */     }
/* 679 */     super.setSprinting(par1);
/* 680 */     this.sprintingTicksLeft = par1 ? 600 : 0;
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
/*     */   public void setXPStats(int experience) {
/* 695 */     this.experience = experience;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {
/* 700 */     this.mc.ingameGUI.getChatGUI().printChatMessage(par1ChatMessageComponent.toStringWithFormatting(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int par1, String par2Str) {
/* 708 */     return (par1 <= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getPlayerCoordinates() {
/* 716 */     return new ChunkCoordinates(MathHelper.floor_double(this.posX + 0.5D), MathHelper.floor_double(this.posY + 0.5D), MathHelper.floor_double(this.posZ + 0.5D));
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
/*     */   public void playSound(String par1Str, float par2, float par3) {
/* 729 */     this.worldObj.playSound(this.posX, this.posY - this.yOffset, this.posZ, par1Str, par2, par3, false);
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
/*     */   public boolean isRidingHorse() {
/* 742 */     return (this.ridingEntity != null && this.ridingEntity instanceof EntityHorse);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHorseJumpPower() {
/* 747 */     return this.horseJumpPower;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110318_g() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calcUnmodifiedCraftingPeriod(float quality_adjusted_crafting_difficulty) {
/* 760 */     if (quality_adjusted_crafting_difficulty < 25.0F)
/* 761 */       return 25; 
/* 762 */     if (quality_adjusted_crafting_difficulty > 100.0F) {
/* 763 */       return (int)Math.round(Math.pow((quality_adjusted_crafting_difficulty - 100.0F), 0.800000011920929D)) + 100;
/*     */     }
/* 765 */     return Math.round(quality_adjusted_crafting_difficulty);
/*     */   }
/*     */ 
/*     */   
/*     */   private float getBenchAndToolsModifier(Container container) {
/* 770 */     if (!(container instanceof ContainerWorkbench)) {
/* 771 */       return 0.0F;
/*     */     }
/* 773 */     ContainerWorkbench container_workbench = (ContainerWorkbench)container;
/*     */     
/* 775 */     SlotCrafting slot_crafting = (SlotCrafting)container_workbench.getSlot(0);
/* 776 */     ItemStack item_stack = slot_crafting.getStack();
/* 777 */     Item item = (item_stack == null) ? null : item_stack.getItem();
/*     */     
/* 779 */     IRecipe recipe = container_workbench.getRecipe();
/*     */     
/* 781 */     Material material_to_check_tool_bench_hardness_against = (recipe == null) ? item.getHardestMetalMaterial() : recipe.getMaterialToCheckToolBenchHardnessAgainst();
/*     */     
/* 783 */     if (material_to_check_tool_bench_hardness_against == null) {
/* 784 */       return 0.2F;
/*     */     }
/* 786 */     Material tool_material = BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata());
/*     */     
/* 788 */     if (tool_material == Material.flint || tool_material == Material.obsidian) {
/* 789 */       return 0.2F;
/*     */     }
/* 791 */     if (tool_material == Material.copper || tool_material == Material.silver || tool_material == Material.gold) {
/* 792 */       return 0.3F;
/*     */     }
/* 794 */     if (tool_material == Material.iron) {
/* 795 */       return 0.4F;
/*     */     }
/* 797 */     if (tool_material == Material.ancient_metal) {
/* 798 */       return 0.5F;
/*     */     }
/* 800 */     if (tool_material == Material.mithril) {
/* 801 */       return 0.6F;
/*     */     }
/* 803 */     if (tool_material == Material.adamantium) {
/* 804 */       return 0.7F;
/*     */     }
/* 806 */     Minecraft.setErrorMessage("getBenchAndToolsModifier: unrecognized tool material " + tool_material);
/*     */     
/* 808 */     return 0.0F;
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
/*     */   public int getCraftingPeriod(float quality_adjusted_crafting_difficulty) {
/* 823 */     int period = calcUnmodifiedCraftingPeriod(quality_adjusted_crafting_difficulty);
/*     */     
/* 825 */     if (hasCurse(Curse.clumsiness)) {
/* 826 */       period *= 2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 835 */     float bench_and_tools_modifier = getBenchAndToolsModifier(this.openContainer);
/*     */     
/* 837 */     return (int)Math.max(period / (1.0F + getLevelModifier(EnumLevelBonus.CRAFTING) + bench_and_tools_modifier), 25.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCrafting() {
/* 844 */     this.crafting_item = null;
/* 845 */     this.crafting_period = 0;
/* 846 */     this.crafting_proceed = false;
/* 847 */     this.crafting_ticks = 0;
/*     */     
/* 849 */     this.crafting_experience_cost = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetCraftingProgress() {
/* 854 */     this.crafting_ticks = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ClientPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */