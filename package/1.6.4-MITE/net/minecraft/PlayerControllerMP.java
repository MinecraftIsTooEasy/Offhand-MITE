/*      */ package net.minecraft;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PlayerControllerMP
/*      */ {
/*      */   private final Minecraft mc;
/*      */   protected final NetClientHandler netClientHandler;
/*   11 */   private int currentBlockX = -1;
/*      */ 
/*      */   
/*   14 */   private int currentBlockY = -1;
/*      */ 
/*      */   
/*   17 */   private int currentblockZ = -1;
/*      */   
/*      */   private ItemStack field_85183_f;
/*      */   
/*      */   public float curBlockDamageMP;
/*      */   
/*      */   private Coords last_block_destruction_coords;
/*      */   
/*   25 */   private int last_block_destruction_stage = -1;
/*      */   
/*      */   private float stepSoundTickCounter;
/*      */   
/*      */   private int blockHitDelay;
/*      */   
/*      */   public boolean isHittingBlock;
/*      */   
/*      */   private EnumGameType currentGameType;
/*      */   
/*      */   private int currentPlayerItem;
/*      */   
/*      */   private long use_button_unlock_ms;
/*      */   
/*      */   private long use_button_unlock_ms_override;
/*      */   
/*      */   private long ingestion_unlock_ms;
/*      */   
/*      */   protected Item last_used_item;
/*      */   
/*      */   protected int last_used_item_subtype;
/*      */   
/*      */   private long last_used_item_reset_ms;
/*      */   
/*      */   public boolean item_switch_or_restock_pending;
/*      */   
/*      */   private boolean run_toggled_on;
/*      */   
/*      */   public boolean listening_for_auto_harvest_mode_click;
/*      */   
/*      */   public Block auto_harvest_block;
/*      */   
/*      */   private int auto_harvest_block_metadata;
/*      */   
/*      */   public boolean cancel_auto_harvest_on_next_click;
/*      */   
/*      */   public long last_auto_harvest_ms;
/*      */   
/*      */   public boolean listening_for_auto_use_mode_click;
/*      */   
/*      */   public Item auto_use_mode_item;
/*      */   
/*      */   public boolean cancel_auto_use_mode_on_next_click;
/*      */   
/*   69 */   private static final Block[] blocks_for_which_metadata_must_match_for_automatic_harvest_mode = new Block[] { Block.tallGrass };
/*      */   
/*      */   protected boolean cancel_swing;
/*      */ 
/*      */   
/*      */   public PlayerControllerMP(Minecraft par1Minecraft, NetClientHandler par2NetClientHandler) {
/*   75 */     this.currentGameType = EnumGameType.SURVIVAL;
/*   76 */     this.mc = par1Minecraft;
/*   77 */     this.netClientHandler = par2NetClientHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void clickBlockCreative(Minecraft par0Minecraft, PlayerControllerMP par1PlayerControllerMP, int x, int y, int z, EnumFace face) {
/*   93 */     if (!par0Minecraft.theWorld.extinguishFire(par0Minecraft.thePlayer, x, y, z, face)) {
/*   94 */       par1PlayerControllerMP.onPlayerDestroyBlock(x, y, z, face);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerCapabilities(EntityPlayer par1EntityPlayer) {
/*  102 */     this.currentGameType.configurePlayerCapabilities(par1EntityPlayer.capabilities);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean enableEverythingIsScrewedUpMode() {
/*  113 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(EnumGameType par1EnumGameType) {
/*  121 */     this.currentGameType = par1EnumGameType;
/*  122 */     this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.capabilities);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flipPlayer(EntityPlayer par1EntityPlayer) {
/*  130 */     par1EntityPlayer.rotationYaw = -180.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldDrawHUD() {
/*  135 */     return this.currentGameType.isSurvivalOrAdventure();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean onPlayerDestroyBlock(int par1, int par2, int par3, EnumFace face) {
/*  144 */     if (this.currentGameType.isAdventure() && !this.mc.thePlayer.isCurrentToolAdventureModeExempt(par1, par2, par3))
/*      */     {
/*  146 */       return false;
/*      */     }
/*  148 */     if (this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItemStack() != null && this.mc.thePlayer.getHeldItemStack().getItem() instanceof ItemSword)
/*      */     {
/*  150 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  154 */     WorldClient var5 = this.mc.theWorld;
/*  155 */     Block var6 = Block.blocksList[var5.getBlockId(par1, par2, par3)];
/*      */     
/*  157 */     if (var6 == null)
/*      */     {
/*  159 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  167 */     int metadata = var5.getBlockMetadata(par1, par2, par3);
/*  168 */     int data = var6.blockID + (metadata << 12);
/*      */ 
/*      */     
/*  171 */     if (this.mc.thePlayer.canSilkHarvestBlock(var6, metadata))
/*      */     {
/*  173 */       data |= RenderGlobal.SFX_2001_WAS_SILK_HARVESTED;
/*      */     }
/*      */     
/*  176 */     var5.playAuxSFX(2001, par1, par2, par3, data);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  181 */     boolean is_partner_present = (var6 instanceof IBlockWithPartner && var6.isPartnerPresent(var5, par1, par2, par3));
/*      */     
/*  183 */     boolean var8 = var5.setBlockToAir(par1, par2, par3);
/*      */     
/*  185 */     if (var8)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  190 */       if (is_partner_present) {
/*      */         
/*  192 */         int x = var6.getPartnerX(par1, metadata);
/*  193 */         int y = var6.getPartnerY(par2, metadata);
/*  194 */         int z = var6.getPartnerZ(par3, metadata);
/*      */         
/*  196 */         Block partner_block = var5.getBlock(x, y, z);
/*      */         
/*  198 */         if (partner_block instanceof IBlockWithPartner && ((IBlockWithPartner)partner_block).requiresPartner(var5.getBlockMetadata(x, y, z))) {
/*  199 */           var5.setBlockToAir(x, y, z, 2);
/*      */         }
/*      */       } 
/*      */     }
/*  203 */     this.currentBlockY = -1;
/*      */     
/*  205 */     if (!this.currentGameType.isCreative());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  226 */     return var8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendDiggingPacket(EnumSignal kind, int x, int y, int z, EnumFace face) {
/*  233 */     if (kind == EnumSignal.digging_block_start) {
/*  234 */       sendPacket((new Packet85SimpleSignal(kind)).setBlockCoords(x, y, z).setByte(face.ordinal()));
/*      */     } else {
/*  236 */       sendPacket((new Packet85SimpleSignal(kind)).setBlockCoords(x, y, z));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateBlockDestruction(boolean client_only) {
/*  241 */     if (client_only) {
/*      */       
/*  243 */       this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.entityId, this.currentBlockX, this.currentBlockY, this.currentblockZ, Block.getStageOfBlockDestruction(this.curBlockDamageMP));
/*      */       
/*      */       return;
/*      */     } 
/*  247 */     int stage = Block.getStageOfBlockDestruction(this.curBlockDamageMP);
/*      */     
/*  249 */     if (this.last_block_destruction_stage != stage || this.last_block_destruction_coords == null || !this.last_block_destruction_coords.equals(this.currentBlockX, this.currentBlockY, this.currentblockZ)) {
/*      */       
/*  251 */       this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.entityId, this.currentBlockX, this.currentBlockY, this.currentblockZ, Block.getStageOfBlockDestruction(this.curBlockDamageMP));
/*      */       
/*  253 */       sendPacket(new Packet55BlockDestroy(this.mc.thePlayer.entityId, this.currentBlockX, this.currentBlockY, this.currentblockZ, stage));
/*      */       
/*  255 */       this.last_block_destruction_stage = stage;
/*      */       
/*  257 */       if (this.last_block_destruction_coords == null) {
/*  258 */         this.last_block_destruction_coords = new Coords(this.currentBlockX, this.currentBlockY, this.currentblockZ);
/*      */       } else {
/*  260 */         this.last_block_destruction_coords.set(this.currentBlockX, this.currentBlockY, this.currentblockZ);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clickBlock(int par1, int par2, int par3, EnumFace face) {
/*  270 */     if (!this.currentGameType.isAdventure() || this.mc.thePlayer.isCurrentToolAdventureModeExempt(par1, par2, par3))
/*      */     {
/*  272 */       if (this.currentGameType.isCreative()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  278 */         sendDiggingPacket(EnumSignal.digging_block_start, par1, par2, par3, face);
/*  279 */         clickBlockCreative(this.mc, this, par1, par2, par3, face);
/*  280 */         this.blockHitDelay = 5;
/*      */       }
/*  282 */       else if (!this.isHittingBlock || !sameToolAndBlock(par1, par2, par3)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  289 */         if (this.isHittingBlock) {
/*  290 */           sendDiggingPacket(EnumSignal.digging_block_cancel, this.currentBlockX, this.currentBlockY, this.currentblockZ, null);
/*      */         }
/*  292 */         int var5 = this.mc.theWorld.getBlockId(par1, par2, par3);
/*  293 */         float damage_vs_block = this.mc.thePlayer.getDamageVsBlock(par1, par2, par3, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  299 */         if (damage_vs_block > 0.0F) {
/*      */ 
/*      */           
/*  302 */           sendDiggingPacket(EnumSignal.digging_block_start, par1, par2, par3, face);
/*      */         }
/*  304 */         else if (this.mc.theWorld.getNeighborBlock(par1, par2, par3, face) == Block.fire) {
/*      */           
/*  306 */           this.mc.thePlayer.sendPacket(new Packet85SimpleSignal(EnumSignal.put_out_fire));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  315 */         if (var5 > 0 && this.curBlockDamageMP == 0.0F)
/*      */         {
/*  317 */           Block.blocksList[var5].onBlockClicked(this.mc.theWorld, par1, par2, par3, this.mc.thePlayer);
/*      */         }
/*      */ 
/*      */         
/*  321 */         if (var5 > 0 && damage_vs_block >= 1.0F) {
/*      */ 
/*      */           
/*  324 */           onPlayerDestroyBlock(par1, par2, par3, face);
/*  325 */           this.mc.thePlayer.addHungerClientSide(0.01F * EnchantmentHelper.getEnduranceModifier(this.mc.thePlayer));
/*      */         }
/*      */         else {
/*      */           
/*  329 */           this.isHittingBlock = true;
/*  330 */           this.currentBlockX = par1;
/*  331 */           this.currentBlockY = par2;
/*  332 */           this.currentblockZ = par3;
/*  333 */           this.field_85183_f = this.mc.thePlayer.getHeldItemStack();
/*  334 */           this.curBlockDamageMP = 0.0F;
/*  335 */           this.stepSoundTickCounter = 0.0F;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetBlockRemoving() {
/*  350 */     if (this.isHittingBlock)
/*      */     {
/*      */ 
/*      */       
/*  354 */       sendDiggingPacket(EnumSignal.digging_block_cancel, this.currentBlockX, this.currentBlockY, this.currentblockZ, null);
/*      */     }
/*      */     
/*  357 */     this.isHittingBlock = false;
/*  358 */     this.curBlockDamageMP = 0.0F;
/*      */     
/*  360 */     updateBlockDestruction(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onPlayerDamageBlock(int par1, int par2, int par3, EnumFace face_hit) {
/*  369 */     syncCurrentPlayItem();
/*      */     
/*  371 */     if (this.blockHitDelay > 0) {
/*      */       
/*  373 */       this.blockHitDelay--;
/*      */     }
/*  375 */     else if (this.currentGameType.isCreative()) {
/*      */       
/*  377 */       this.blockHitDelay = 5;
/*      */ 
/*      */ 
/*      */       
/*  381 */       sendDiggingPacket(EnumSignal.digging_block_start, par1, par2, par3, face_hit);
/*  382 */       clickBlockCreative(this.mc, this, par1, par2, par3, face_hit);
/*      */ 
/*      */     
/*      */     }
/*  386 */     else if (sameToolAndBlock(par1, par2, par3)) {
/*      */       
/*  388 */       int var5 = this.mc.theWorld.getBlockId(par1, par2, par3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  395 */       if (var5 == 0) {
/*      */         
/*  397 */         this.isHittingBlock = false;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  402 */       Block var6 = Block.blocksList[var5];
/*      */       
/*  404 */       this.curBlockDamageMP += this.mc.thePlayer.getDamageVsBlock(par1, par2, par3, true);
/*  405 */       this.mc.thePlayer.addHungerClientSide(0.01F * EnchantmentHelper.getEnduranceModifier(this.mc.thePlayer));
/*      */       
/*  407 */       if (this.stepSoundTickCounter % 4.0F == 0.0F && var6 != null) {
/*      */         
/*  409 */         this.mc.sndManager.playSound(var6.stepSound.getStepSound(), par1 + 0.5F, par2 + 0.5F, par3 + 0.5F, (var6.stepSound.getVolume() + 1.0F) / 8.0F, var6.stepSound.getPitch() * 0.5F);
/*  410 */         this.mc.thePlayer.sendPacket((new Packet85SimpleSignal(EnumSignal.block_hit_sound)).setBlockCoords(par1, par2, par3));
/*      */       } 
/*      */       
/*  413 */       this.stepSoundTickCounter++;
/*      */       
/*  415 */       if (this.curBlockDamageMP >= 1.0F)
/*      */       {
/*  417 */         this.isHittingBlock = false;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  422 */         sendDiggingPacket(EnumSignal.digging_block_complete, par1, par2, par3, null);
/*  423 */         onPlayerDestroyBlock(par1, par2, par3, face_hit);
/*  424 */         this.curBlockDamageMP = 0.0F;
/*  425 */         this.stepSoundTickCounter = 0.0F;
/*  426 */         this.blockHitDelay = 5;
/*      */ 
/*      */         
/*  429 */         updateBlockDestruction(true);
/*      */       }
/*      */       else
/*      */       {
/*  433 */         updateBlockDestruction(false);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  443 */       clickBlock(par1, par2, par3, face_hit);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateController() {
/*  458 */     syncCurrentPlayItem();
/*      */ 
/*      */     
/*  461 */     if (!this.mc.theWorld.isTheEnd()) {
/*  462 */       this.mc.sndManager.playRandomMusicIfReady();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean sameBlock(int x, int y, int z) {
/*  480 */     return (x == this.currentBlockX && y == this.currentBlockY && z == this.currentblockZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean sameToolAndBlock(int x, int y, int z) {
/*  492 */     if (!sameBlock(x, y, z)) {
/*  493 */       return false;
/*      */     }
/*  495 */     ItemStack initial_item_stack = this.field_85183_f;
/*  496 */     ItemStack current_item_stack = this.mc.thePlayer.getHeldItemStack();
/*      */     
/*  498 */     if (current_item_stack == initial_item_stack) {
/*  499 */       return true;
/*      */     }
/*  501 */     if (ItemStack.areItemStacksEqual(current_item_stack, initial_item_stack, true, false, true, false)) {
/*  502 */       return true;
/*      */     }
/*  504 */     Item previous_item = (initial_item_stack == null) ? null : initial_item_stack.getItem();
/*  505 */     Item current_item = (current_item_stack == null) ? null : current_item_stack.getItem();
/*      */     
/*  507 */     Block block = this.mc.theWorld.getBlock(x, y, z);
/*  508 */     int metadata = this.mc.theWorld.getBlockMetadata(x, y, z);
/*      */     
/*  510 */     if (previous_item != null && previous_item.isEffectiveAgainstBlock(block, metadata)) {
/*  511 */       return false;
/*      */     }
/*  513 */     if (current_item != null && current_item.isEffectiveAgainstBlock(block, metadata)) {
/*  514 */       return false;
/*      */     }
/*  516 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncCurrentPlayItem() {
/*  525 */     int var1 = this.mc.thePlayer.inventory.currentItem;
/*      */     
/*  527 */     if (var1 != this.currentPlayerItem) {
/*      */       
/*  529 */       this.currentPlayerItem = var1;
/*  530 */       this.netClientHandler.addToSendQueue(new Packet16BlockItemSwitch(this.currentPlayerItem));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityClientPlayerMP func_78754_a(World par1World) {
/*  848 */     return new EntityClientPlayerMP(this.mc, par1World, this.mc.getSession(), this.netClientHandler);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLastUsedItem(Item item, int item_subtype) {
/*  854 */     this.last_used_item = item;
/*  855 */     this.last_used_item_subtype = item_subtype;
/*      */     
/*  857 */     if (item != null) {
/*  858 */       this.last_used_item_reset_ms = System.currentTimeMillis() + 3000L;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void leftClickEntity(Entity target) {
/*  911 */     syncCurrentPlayItem();
/*  912 */     sendPacket((new Packet85SimpleSignal(EnumSignal.left_click_entity)).setEntityID(target));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack windowClick(int par1, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
/*  924 */     if (par2 >= 0 && par2 < par5EntityPlayer.openContainer.inventorySlots.size() && (par5EntityPlayer.openContainer.getSlot(par2)).locked) {
/*  925 */       return null;
/*      */     }
/*  927 */     short var6 = par5EntityPlayer.openContainer.getNextTransactionID(par5EntityPlayer.inventory);
/*      */     
/*  929 */     ItemStack var7 = par5EntityPlayer.openContainer.slotClick(par2, par3, par4, GuiScreen.isShiftKeyDown(), par5EntityPlayer);
/*      */     
/*  931 */     this.netClientHandler.addToSendQueue((new Packet102WindowClick(par1, par2, par3, par4, var7, var6)).setHoldingShift(GuiScreen.isShiftKeyDown()));
/*  932 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendEnchantPacket(int par1, int par2) {
/*  941 */     this.netClientHandler.addToSendQueue(new Packet108EnchantItem(par1, par2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSlotPacket(ItemStack par1ItemStack, int par2) {
/*  949 */     if (this.currentGameType.isCreative())
/*      */     {
/*  951 */       this.netClientHandler.addToSendQueue(new Packet107CreativeSetSlot(par2, par1ItemStack));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_78752_a(ItemStack par1ItemStack) {
/*  957 */     if (this.currentGameType.isCreative() && par1ItemStack != null)
/*      */     {
/*  959 */       this.netClientHandler.addToSendQueue(new Packet107CreativeSetSlot(-1, par1ItemStack));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_78763_f() {
/*  974 */     return this.currentGameType.isSurvivalOrAdventure();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNotCreative() {
/*  982 */     return !this.currentGameType.isCreative();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInCreativeMode() {
/*  990 */     return this.currentGameType.isCreative();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_110738_j() {
/* 1003 */     return (this.mc.thePlayer.isRiding() && this.mc.thePlayer.ridingEntity instanceof EntityHorse);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacket(Packet packet) {
/* 1008 */     this.netClientHandler.addToSendQueue(packet);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseButtonDelay() {
/* 1014 */     if (System.currentTimeMillis() + 500L > this.use_button_unlock_ms) {
/* 1015 */       this.use_button_unlock_ms = System.currentTimeMillis() + 500L;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseButtonDelayOverride(int milliseconds) {
/* 1030 */     this.use_button_unlock_ms_override = System.currentTimeMillis() + milliseconds;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean useButtonEnabled() {
/* 1035 */     if (this.use_button_unlock_ms_override > 0L) {
/*      */       
/* 1037 */       if (System.currentTimeMillis() >= this.use_button_unlock_ms_override) {
/*      */         
/* 1039 */         this.use_button_unlock_ms_override = 0L;
/* 1040 */         this.use_button_unlock_ms = 0L;
/* 1041 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1045 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1049 */     return (System.currentTimeMillis() >= this.use_button_unlock_ms);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIngestionDelay() {
/* 1054 */     this.ingestion_unlock_ms = System.currentTimeMillis() + 1000L;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ingestionEnabled() {
/* 1059 */     return (System.currentTimeMillis() >= this.ingestion_unlock_ms);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean autoStockEnabled() {
/* 1067 */     long milliseconds_remaining = this.last_used_item_reset_ms - System.currentTimeMillis();
/*      */     
/* 1069 */     long restock_delay_ms = 250L;
/*      */     
/* 1071 */     return (milliseconds_remaining > 0L && milliseconds_remaining <= 3000L - restock_delay_ms);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setListeningForAutoHarvestMode() {
/* 1076 */     this.listening_for_auto_harvest_mode_click = true;
/* 1077 */     this.listening_for_auto_use_mode_click = false;
/* 1078 */     this.cancel_auto_harvest_on_next_click = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoHarvestMode(int x, int y, int z) {
/* 1083 */     this.auto_harvest_block = this.mc.theWorld.getBlock(x, y, z);
/*      */     
/* 1085 */     if (this.auto_harvest_block == null) {
/*      */       
/* 1087 */       clearAutoHarvestMode();
/*      */       
/*      */       return;
/*      */     } 
/* 1091 */     this.auto_harvest_block_metadata = this.mc.theWorld.getBlockMetadata(x, y, z);
/* 1092 */     this.cancel_auto_harvest_on_next_click = false;
/* 1093 */     this.last_auto_harvest_ms = System.currentTimeMillis();
/*      */     
/* 1095 */     clearAutoUseMode();
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearAutoHarvestMode() {
/* 1100 */     this.auto_harvest_block = null;
/* 1101 */     this.auto_harvest_block_metadata = 0;
/*      */     
/* 1103 */     this.cancel_auto_harvest_on_next_click = false;
/* 1104 */     this.last_auto_harvest_ms = 0L;
/*      */     
/* 1106 */     if (!this.mc.gameSettings.keyBindAttack.pressed) {
/* 1107 */       resetBlockRemoving();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean autoHarvestModeHasExpired() {
/* 1112 */     return (System.currentTimeMillis() - this.last_auto_harvest_ms > 5000L);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean matchesAutoHarvestBlock(int x, int y, int z) {
/* 1117 */     if (this.auto_harvest_block == null) {
/* 1118 */       return false;
/*      */     }
/* 1120 */     if (!this.mc.thePlayer.hasFoodEnergy() || this.mc.thePlayer.isDead || this.mc.thePlayer.inBed() || autoHarvestModeHasExpired()) {
/*      */       
/* 1122 */       clearAutoHarvestMode();
/* 1123 */       return false;
/*      */     } 
/*      */     
/* 1126 */     Block block = this.mc.theWorld.getBlock(x, y, z);
/*      */     
/* 1128 */     if ((this.auto_harvest_block == Block.dirt || this.auto_harvest_block == Block.grass) && (
/* 1129 */       block == Block.dirt || block == Block.grass)) {
/* 1130 */       return true;
/*      */     }
/* 1132 */     if (this.auto_harvest_block == Block.oreRedstoneGlowing && block == Block.oreRedstone) {
/* 1133 */       return true;
/*      */     }
/* 1135 */     if (block != this.auto_harvest_block) {
/* 1136 */       return false;
/*      */     }
/* 1138 */     int metadata = this.mc.theWorld.getBlockMetadata(x, y, z);
/*      */     
/* 1140 */     if (metadata == this.auto_harvest_block_metadata) {
/* 1141 */       return true;
/*      */     }
/* 1143 */     for (int i = 0; i < blocks_for_which_metadata_must_match_for_automatic_harvest_mode.length; i++) {
/*      */       
/* 1145 */       if (blocks_for_which_metadata_must_match_for_automatic_harvest_mode[i] == block) {
/* 1146 */         return false;
/*      */       }
/*      */     } 
/* 1149 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setListeningForAutoUseMode() {
/* 1156 */     this.listening_for_auto_use_mode_click = true;
/* 1157 */     this.listening_for_auto_harvest_mode_click = false;
/* 1158 */     this.cancel_auto_use_mode_on_next_click = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemStackEligibleForAUM(ItemStack item_stack) {
/* 1179 */     EnumItemInUseAction enum_item_in_use_action = item_stack.getItemInUseAction(this.mc.thePlayer);
/* 1180 */     return (enum_item_in_use_action != null && enum_item_in_use_action.isIngestion());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setAutoUseMode(ItemStack item_stack) {
/* 1192 */     this.auto_use_mode_item = isItemStackEligibleForAUM(item_stack) ? item_stack.getItem() : null;
/*      */     
/* 1194 */     this.listening_for_auto_use_mode_click = false;
/* 1195 */     this.cancel_auto_use_mode_on_next_click = false;
/*      */ 
/*      */ 
/*      */     
/* 1199 */     resetBlockRemoving();
/* 1200 */     clearAutoHarvestMode();
/*      */ 
/*      */     
/* 1203 */     return (this.auto_use_mode_item != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean inAutoUseMode() {
/* 1208 */     return (this.auto_use_mode_item != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearAutoUseMode() {
/* 1213 */     this.auto_use_mode_item = null;
/*      */ 
/*      */     
/* 1216 */     this.cancel_auto_use_mode_on_next_click = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void toggleRun(EntityPlayer player) {
/* 1221 */     if (this.run_toggled_on) {
/* 1222 */       this.mc.thePlayer.setSprinting(false);
/*      */     }
/*      */     
/* 1225 */     this.run_toggled_on = !this.run_toggled_on;
/*      */     
/* 1227 */     if (this.run_toggled_on && player.hasCurse(Curse.cannot_run, true)) {
/* 1228 */       this.run_toggled_on = false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunToggledOn(EntityPlayer player) {
/* 1235 */     if (this.run_toggled_on && player.hasCurse(Curse.cannot_run, true)) {
/* 1236 */       this.run_toggled_on = false;
/*      */     }
/* 1238 */     return this.run_toggled_on;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerControllerMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */