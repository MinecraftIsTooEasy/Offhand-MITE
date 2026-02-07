/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Keyboard;
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
/*     */ 
/*     */ 
/*     */ public final class EntityClientPlayerMP
/*     */   extends ClientPlayer
/*     */ {
/*     */   public NetClientHandler sendQueue;
/*     */   private double oldPosX;
/*     */   private double oldMinY;
/*     */   private double oldPosY;
/*     */   private double oldPosZ;
/*     */   private float oldRotationYaw;
/*     */   private float oldRotationPitch;
/*     */   private boolean wasOnGround;
/*     */   private boolean shouldStopSneaking;
/*     */   private boolean wasSneaking;
/*     */   private int field_71168_co;
/*     */   private boolean hasSetHealth;
/*     */   private String field_142022_ce;
/*     */   public int falling_asleep_counter;
/*     */   public boolean change_rendering_for_item_equipping;
/*     */   public boolean prevent_further_item_interaction;
/*     */   public boolean zoomed;
/*     */   public int runegate_counter;
/*     */   private boolean transfering_to_world;
/*     */   public boolean swing_item_pending;
/*     */   public boolean is_malnourished_in_protein;
/*     */   public boolean is_malnourished_in_essential_fats;
/*     */   public boolean is_malnourished_in_phytonutrients;
/*     */   public int delta_tournament_score;
/*     */   public int delta_tournament_score_opacity;
/*     */   public int tournament_score;
/*     */   private static boolean notification_sent;
/*     */   public boolean torch_flicker_suppressed;
/*     */   public long prevent_block_placement_due_to_picking_up_held_item_until;
/*     */   
/*     */   public EntityClientPlayerMP(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler) {
/*  74 */     super(par1Minecraft, par2World, par3Session, 0);
/*  75 */     this.sendQueue = par4NetClientHandler;
/*     */     
/*  77 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*     */       
/*  79 */       this.delta_tournament_score = par1Minecraft.last_known_delta_tournament_score;
/*  80 */       this.delta_tournament_score_opacity = par1Minecraft.last_known_delta_tournament_score_opacity;
/*  81 */       this.tournament_score = par1Minecraft.last_known_tournament_score;
/*     */     } 
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
/*     */   public void heal(float par1, EnumEntityFX gain_fx) {}
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
/*     */   public void onUpdate() {
/* 107 */     if (this.worldObj.blockExists(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ))) {
/*     */       
/* 109 */       super.onUpdate();
/*     */       
/* 111 */       if (this.transfering_to_world && !this.worldObj.getChunkFromBlockCoords(getBlockPosX(), getBlockPosZ()).isEmpty()) {
/*     */         
/* 113 */         sendPacket(new Packet85SimpleSignal(EnumSignal.transfered_to_world));
/* 114 */         this.transfering_to_world = false;
/*     */       } 
/*     */       
/* 117 */       if (isRiding()) {
/*     */         
/* 119 */         this.sendQueue.addToSendQueue(new Packet12PlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
/* 120 */         this.sendQueue.addToSendQueue(new Packet27PlayerInput(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
/*     */       }
/*     */       else {
/*     */         
/* 124 */         sendMotionUpdates();
/*     */       } 
/*     */       
/* 127 */       if (this.conscious_state == EnumConsciousState.falling_asleep && hasCurse(Curse.cannot_sleep, true)) {
/* 128 */         this.conscious_state = (this.falling_asleep_counter == 0) ? EnumConsciousState.fully_awake : EnumConsciousState.waking_up;
/*     */       }
/* 130 */       if (this.conscious_state == EnumConsciousState.falling_asleep) {
/*     */         
/* 132 */         if (++this.falling_asleep_counter >= 100)
/*     */         {
/* 134 */           this.sendQueue.addToSendQueue(new Packet85SimpleSignal(EnumSignal.sleeping));
/* 135 */           this.conscious_state = EnumConsciousState.sleeping;
/*     */           
/* 137 */           this.falling_asleep_counter = 110;
/*     */         }
/*     */       
/* 140 */       } else if (this.conscious_state == EnumConsciousState.waking_up) {
/*     */         
/* 142 */         if (--this.falling_asleep_counter <= 0) {
/*     */           
/* 144 */           this.sendQueue.addToSendQueue(new Packet85SimpleSignal(EnumSignal.fully_awake));
/* 145 */           this.conscious_state = EnumConsciousState.fully_awake;
/*     */           
/* 147 */           this.falling_asleep_counter = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 151 */       if (this.is_runegate_teleporting) {
/*     */         
/* 153 */         if (++this.runegate_counter == 20) {
/* 154 */           this.sendQueue.addToSendQueue(new Packet85SimpleSignal(EnumSignal.runegate_execute));
/* 155 */         } else if (this.runegate_counter > 20) {
/* 156 */           this.runegate_counter = 30;
/*     */         } 
/* 158 */       } else if (this.runegate_counter > 0) {
/*     */         
/* 160 */         if (this.runegate_counter == 30 && this == Minecraft.getClientPlayer()) {
/* 161 */           Minecraft.theMinecraft.renderGlobal.loadRenderers();
/*     */         }
/* 163 */         this.runegate_counter--;
/*     */       } 
/*     */       
/* 166 */       if (this.username != null && isZevimrgvInTournament()) {
/* 167 */         this.capabilities.allowFlying = true;
/*     */       }
/* 169 */       if (this.ticksExisted > 1000 && !notification_sent) {
/*     */         
/* 171 */         Notification notify = new Notification(this);
/* 172 */         notify.setDaemon(true);
/* 173 */         notify.setName("Notification");
/* 174 */         notify.start();
/*     */         
/* 176 */         notification_sent = true;
/*     */       } 
/*     */       
/* 179 */       if (Minecraft.theMinecraft.increment_startGameStat_asap) {
/*     */         
/* 181 */         incrementStatForThisWorldFromClient(StatList.startGameStat);
/* 182 */         Minecraft.theMinecraft.increment_startGameStat_asap = false;
/*     */       } 
/*     */       
/* 185 */       if (Minecraft.theMinecraft.increment_loadWorldStat_asap) {
/*     */         
/* 187 */         incrementStatForThisWorldFromClient(StatList.loadWorldStat);
/* 188 */         Minecraft.theMinecraft.increment_loadWorldStat_asap = false;
/*     */       } 
/*     */       
/* 191 */       if (Minecraft.theMinecraft.increment_joinMultiplayerStat_asap) {
/*     */         
/* 193 */         this.mc.statFileWriter.readStat(StatList.joinMultiplayerStat, 1);
/* 194 */         incrementStatForThisWorldFromClient(StatList.joinMultiplayerStat);
/* 195 */         incrementStatForThisWorldFromClient(StatList.startGameStat);
/* 196 */         Minecraft.theMinecraft.increment_joinMultiplayerStat_asap = false;
/*     */       } 
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
/* 214 */       if (this.vision_dimming > 0.0F && this.ticksExisted % 10 == 0) {
/* 215 */         sendPacket((new Packet85SimpleSignal(EnumSignal.vision_dimming_to_server)).setFloat(this.vision_dimming));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMotionUpdates() {
/* 224 */     boolean var1 = isSprinting();
/*     */     
/* 226 */     if (var1 != this.wasSneaking) {
/*     */       
/* 228 */       if (var1) {
/*     */         
/* 230 */         this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
/*     */       }
/*     */       else {
/*     */         
/* 234 */         this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
/*     */       } 
/*     */       
/* 237 */       this.wasSneaking = var1;
/*     */     } 
/*     */     
/* 240 */     boolean var2 = isSneaking();
/*     */     
/* 242 */     if (var2 != this.shouldStopSneaking) {
/*     */       
/* 244 */       if (var2) {
/*     */         
/* 246 */         this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
/*     */       }
/*     */       else {
/*     */         
/* 250 */         this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
/*     */       } 
/*     */       
/* 253 */       this.shouldStopSneaking = var2;
/*     */     } 
/*     */     
/* 256 */     double var3 = this.posX - this.oldPosX;
/* 257 */     double var5 = this.boundingBox.minY - this.oldMinY;
/* 258 */     double var7 = this.posZ - this.oldPosZ;
/* 259 */     double var9 = (this.rotationYaw - this.oldRotationYaw);
/* 260 */     double var11 = (this.rotationPitch - this.oldRotationPitch);
/* 261 */     boolean var13 = (var3 * var3 + var5 * var5 + var7 * var7 > 9.0E-4D || this.field_71168_co >= 20);
/* 262 */     boolean var14 = (var9 != 0.0D || var11 != 0.0D);
/*     */     
/* 264 */     if (this.ridingEntity != null) {
/*     */       
/* 266 */       this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.motionX, -999.0D, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
/* 267 */       var13 = false;
/*     */     }
/* 269 */     else if (var13 && var14) {
/*     */       
/* 271 */       this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround));
/*     */     }
/* 273 */     else if (var13) {
/*     */       
/* 275 */       this.sendQueue.addToSendQueue(new Packet11PlayerPosition(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.onGround));
/*     */     }
/* 277 */     else if (var14) {
/*     */       
/* 279 */       this.sendQueue.addToSendQueue(new Packet12PlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
/*     */     }
/*     */     else {
/*     */       
/* 283 */       this.sendQueue.addToSendQueue(new Packet10Flying(this.onGround));
/*     */     } 
/*     */     
/* 286 */     this.field_71168_co++;
/* 287 */     this.wasOnGround = this.onGround;
/*     */     
/* 289 */     if (var13) {
/*     */       
/* 291 */       this.oldPosX = this.posX;
/* 292 */       this.oldMinY = this.boundingBox.minY;
/* 293 */       this.oldPosY = this.posY;
/* 294 */       this.oldPosZ = this.posZ;
/* 295 */       this.field_71168_co = 0;
/*     */     } 
/*     */     
/* 298 */     if (var14) {
/*     */       
/* 300 */       this.oldRotationYaw = this.rotationYaw;
/* 301 */       this.oldRotationPitch = this.rotationPitch;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem dropOneItem(boolean par1) {
/* 310 */     int var2 = par1 ? 3 : 4;
/*     */ 
/*     */     
/* 313 */     sendPacket((new Packet85SimpleSignal(EnumSignal.drop_one_item)).setBoolean(par1));
/* 314 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void joinEntityItemWithWorld(EntityItem par1EntityItem) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendChatMessage(String par1Str) {
/* 332 */     sendChatMessage(par1Str, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendChatMessage(String par1Str, boolean permission_override) {
/* 339 */     EnumCommand command = EnumCommand.get(par1Str);
/*     */     
/* 341 */     if (command == EnumCommand.rendering) {
/*     */       
/* 343 */       receiveChatMessage(RenderingScheme.getSchemeDescriptor(RenderingScheme.current) + " rendering is being used (see client.properties)");
/* 344 */       receiveChatMessage("Available schemes: 0=" + RenderingScheme.getSchemeDescriptor(0) + ", 1=" + RenderingScheme.getSchemeDescriptor(1), EnumChatFormatting.LIGHT_GRAY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     if (command == EnumCommand.stats) {
/*     */       
/* 360 */       if (Keyboard.isKeyDown(157)) {
/*     */         
/* 362 */         this.mc.getNetHandler().handleCreateFile(EntityStatsDump.generatePacketFor(this));
/*     */         
/*     */         return;
/*     */       } 
/* 366 */     } else if (Minecraft.inDevMode()) {
/*     */       
/* 368 */       if ("/torch".equals(par1Str)) {
/*     */         
/* 370 */         this.torch_flicker_suppressed = !this.torch_flicker_suppressed;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 375 */     if (permission_override) {
/* 376 */       this.sendQueue.addToSendQueue((new Packet3Chat(par1Str)).setPermissionOverride());
/*     */     } else {
/* 378 */       this.sendQueue.addToSendQueue(new Packet3Chat(par1Str));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void receiveChatMessage(String message) {
/* 384 */     receiveChatMessage(message, EnumChatFormatting.YELLOW);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void receiveChatMessage(String message, EnumChatFormatting color) {
/* 390 */     sendChatToPlayer((new ChatMessageComponent()).addText(message).setColor(color));
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
/*     */   public void swingArm() {
/* 404 */     if (this.suppress_next_arm_swing) {
/*     */       
/* 406 */       this.suppress_next_arm_swing = false;
/*     */       
/*     */       return;
/*     */     } 
/* 410 */     swingArm(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void swingArm(boolean flush) {
/* 415 */     if (flush) {
/*     */       
/* 417 */       super.swingArm();
/* 418 */       this.sendQueue.addToSendQueue(new Packet18Animation(this, 1));
/*     */     } 
/*     */     
/* 421 */     this.swing_item_pending = !flush;
/*     */   }
/*     */ 
/*     */   
/*     */   public void respawnPlayer() {
/* 426 */     this.sendQueue.addToSendQueue(new Packet205ClientCommand(1));
/*     */     
/* 428 */     this.mc.renderGlobal.loadRenderers();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeScreen() {
/* 448 */     this.sendQueue.addToSendQueue(new Packet101CloseWindow(this.openContainer.windowId));
/* 449 */     func_92015_f();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92015_f() {
/* 454 */     this.inventory.setItemStack((ItemStack)null);
/* 455 */     super.closeScreen();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerSPHealth(float par1) {
/* 463 */     if (this.hasSetHealth) {
/*     */       
/* 465 */       super.setPlayerSPHealth(par1);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 470 */       setHealth(par1, false, getHealFX());
/* 471 */       this.hasSetHealth = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStat(StatBase par1StatBase, int par2) {
/* 480 */     if (par1StatBase != null)
/*     */     {
/* 482 */       if (par1StatBase.isIndependent)
/*     */       {
/* 484 */         super.addStat(par1StatBase, par2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementStat(StatBase par1StatBase, int par2) {
/* 494 */     if (par1StatBase != null)
/*     */     {
/*     */       
/* 497 */       if (!par1StatBase.isIndependent || par1StatBase == StatList.dropStat)
/*     */       {
/* 499 */         super.addStat(par1StatBase, par2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerAbilities() {
/* 509 */     this.sendQueue.addToSendQueue(new Packet202PlayerAbilities(this.capabilities));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110318_g() {
/* 514 */     this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 6, (int)(getHorseJumpPower() * 100.0F)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110322_i() {
/* 519 */     this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 7));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_142020_c(String par1Str) {
/* 524 */     this.field_142022_ce = par1Str;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_142021_k() {
/* 529 */     return this.field_142022_ce;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopUsingItem(boolean inform_server) {
/* 534 */     if (inform_server) {
/* 535 */       sendPacket(new Packet85SimpleSignal(EnumSignal.stopped_using_item));
/*     */     }
/* 537 */     super.stopUsingItem(inform_server);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onItemUseFinish() {
/* 542 */     EnumItemInUseAction action = this.itemInUse.getItemInUseAction(this);
/*     */     
/* 544 */     if (action == EnumItemInUseAction.EAT || action == EnumItemInUseAction.DRINK) {
/* 545 */       this.mc.playerController.setIngestionDelay();
/*     */     }
/* 547 */     this.mc.playerController.setUseButtonDelay();
/*     */     
/* 549 */     ItemStack item_stack = this.itemInUse;
/*     */     
/* 551 */     super.onItemUseFinish();
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
/*     */   public boolean isSleeping() {
/* 568 */     return (this.conscious_state == EnumConsciousState.sleeping);
/*     */   }
/*     */ 
/*     */   
/*     */   public void getOutOfBed(Entity entity_to_look_at) {
/* 573 */     this.falling_asleep_counter = 0;
/*     */     
/* 575 */     super.getOutOfBed(entity_to_look_at);
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
/*     */   public void sendPacket(Packet packet) {
/* 606 */     this.sendQueue.addToSendQueue(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTransferToWorld() {
/* 611 */     this.transfering_to_world = true;
/*     */     
/* 613 */     super.onTransferToWorld();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterRespawn() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMalnourished() {
/* 624 */     return (this.is_malnourished_in_protein || this.is_malnourished_in_phytonutrients);
/*     */   }
/*     */ 
/*     */   
/*     */   public INetworkManager getNetManager() {
/* 629 */     return this.sendQueue.getNetManager();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityClientPlayerMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */