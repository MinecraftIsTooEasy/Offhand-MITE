/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.client.main.Main;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ public abstract class EntityPlayer
/*      */   extends EntityLivingBase
/*      */   implements ICommandSender
/*      */ {
/*   16 */   public InventoryPlayer inventory = new InventoryPlayer(this);
/*   17 */   private InventoryEnderChest theInventoryEnderChest = new InventoryEnderChest();
/*      */ 
/*      */   
/*      */   public Container inventoryContainer;
/*      */ 
/*      */   
/*      */   public Container openContainer;
/*      */ 
/*      */   
/*      */   protected FoodStats foodStats;
/*      */ 
/*      */   
/*      */   protected int flyToggleTimer;
/*      */ 
/*      */   
/*      */   public float prevCameraYaw;
/*      */ 
/*      */   
/*      */   public float cameraYaw;
/*      */ 
/*      */   
/*      */   protected final String username;
/*      */ 
/*      */   
/*      */   public int xpCooldown;
/*      */ 
/*      */   
/*      */   public double field_71091_bM;
/*      */ 
/*      */   
/*      */   public double field_71096_bN;
/*      */   
/*      */   public double field_71097_bO;
/*      */   
/*      */   public double field_71094_bP;
/*      */   
/*      */   public double field_71095_bQ;
/*      */   
/*      */   public double field_71085_bR;
/*      */   
/*      */   public ChunkCoordinates bed_location;
/*      */   
/*      */   public int bed_direction;
/*      */   
/*   61 */   public EnumConsciousState conscious_state = EnumConsciousState.fully_awake;
/*      */ 
/*      */   
/*      */   public float field_71079_bU;
/*      */ 
/*      */   
/*      */   public float field_71082_cx;
/*      */ 
/*      */   
/*      */   public float field_71089_bV;
/*      */ 
/*      */   
/*      */   private ChunkCoordinates spawnChunk;
/*      */ 
/*      */   
/*      */   private boolean spawnForced;
/*      */ 
/*      */   
/*      */   private ChunkCoordinates startMinecartRidingCoordinate;
/*      */ 
/*      */   
/*   82 */   public PlayerCapabilities capabilities = new PlayerCapabilities(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int experience;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ItemStack itemInUse;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int itemInUseCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   protected float speedOnGround = 0.1F;
/*  111 */   protected float speedInAir = 0.02F;
/*      */ 
/*      */   
/*      */   private int field_82249_h;
/*      */ 
/*      */   
/*      */   public EntityFishHook fishEntity;
/*      */ 
/*      */   
/*      */   public float vision_dimming;
/*      */   
/*  122 */   public int countdown_to_mark_all_nearby_chunks_for_render_update = 20;
/*      */ 
/*      */ 
/*      */   
/*  126 */   public static float y_offset_on_client_and_eye_height_on_server = 1.62F;
/*      */   
/*      */   private boolean has_dedicated_server_ghost_check_been_made;
/*      */   
/*      */   private boolean is_dedicated_server_ghost;
/*      */   
/*      */   public boolean is_tournament_winner;
/*      */   
/*      */   public ItemArrow nocked_arrow;
/*      */   
/*      */   public boolean is_runegate_teleporting;
/*      */   
/*      */   public boolean prevent_runegate_achievement;
/*      */   
/*      */   public boolean is_cursed;
/*      */   
/*      */   public int curse_id;
/*      */   
/*      */   public boolean curse_effect_known;
/*      */   
/*      */   public boolean collided_with_gelatinous_cube;
/*      */   
/*      */   public boolean in_test_mode;
/*      */   
/*      */   private int times_renderUpdateMethod2_called;
/*      */   
/*      */   public boolean suppress_next_arm_swing;
/*      */   
/*      */   public boolean suppress_next_stat_increment;
/*      */   
/*      */   private boolean cancel_right_click;
/*      */   
/*      */   private boolean zevimrgv_check_made;
/*      */   private boolean is_zevimrgv;
/*  160 */   List tentative_bounding_boxes = new ArrayList();
/*      */   
/*      */   public double pos_x_before_bed;
/*      */   
/*      */   public double pos_y_before_bed;
/*      */   public double pos_z_before_bed;
/*      */   public int data_object_id_skills;
/*  167 */   public HashMap stats = new HashMap<Object, Object>();
/*      */   
/*  169 */   public long block_placement_tick = -1L;
/*      */   
/*      */   public double block_placement_pos_x;
/*      */   
/*      */   public double block_placement_pos_y;
/*      */   
/*      */   public double block_placement_pos_z;
/*      */   
/*      */   public World block_placement_world;
/*      */   
/*      */   private int insulin_resistance;
/*      */   
/*      */   public EnumInsulinResistanceLevel insulin_resistance_level;
/*      */ 
/*      */   
/*      */   public EntityPlayer(World par1World, String par2Str) {
/*  185 */     super(par1World);
/*  186 */     this.username = par2Str;
/*      */     
/*  188 */     this.inventoryContainer = new ContainerPlayer(this);
/*  189 */     this.openContainer = this.inventoryContainer;
/*      */     
/*  191 */     this.yOffset = y_offset_on_client_and_eye_height_on_server;
/*  192 */     ChunkCoordinates var3 = par1World.getSpawnPoint();
/*  193 */     setLocationAndAngles(var3.posX + 0.5D, (var3.posY + 1), var3.posZ + 0.5D, 0.0F, 0.0F);
/*      */ 
/*      */ 
/*      */     
/*  197 */     this.foodStats = new FoodStats(this);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  202 */     super.applyEntityAttributes();
/*      */     
/*  204 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  209 */     super.entityInit();
/*  210 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*  211 */     this.dataWatcher.addObject(17, Float.valueOf(0.0F));
/*  212 */     this.dataWatcher.addObject(18, Integer.valueOf(0));
/*      */     
/*  214 */     this.data_object_id_skills = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSkills(int skills) {
/*  219 */     this.dataWatcher.updateObject(this.data_object_id_skills, Integer.valueOf(skills));
/*      */   }
/*      */ 
/*      */   
/*      */   public void addSkill(Skill skill) {
/*  224 */     setSkills(getSkills() | skill.id);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeSkill(Skill skill) {
/*  229 */     setSkills(BitHelper.clearBit(getSkills(), skill.id));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSkills() {
/*  234 */     return this.dataWatcher.getWatchableObjectInt(this.data_object_id_skills);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasSkill(Skill skill) {
/*  239 */     if (!this.worldObj.areSkillsEnabled()) {
/*      */       
/*  241 */       Minecraft.setErrorMessage("hasSkill: skills aren't enabled");
/*  242 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  245 */     return Skill.skillExistsIn(skill, getSkills());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasAnyOfTheseSkills(int skills) {
/*  250 */     if (!this.worldObj.areSkillsEnabled()) {
/*      */       
/*  252 */       Minecraft.setErrorMessage("hasAnyOfTheseSkills: skills aren't enabled");
/*  253 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  256 */     return BitHelper.isAnyBitSet(getSkills(), skills);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasAllOfTheseSkills(int skills) {
/*  261 */     if (!this.worldObj.areSkillsEnabled()) {
/*      */       
/*  263 */       Minecraft.setErrorMessage("hasAllOfTheseSkills: skills aren't enabled");
/*  264 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  267 */     return BitHelper.isBitSet(getSkills(), skills);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasAnyOfTheseSkillsets(int[] skillsets) {
/*  272 */     if (!this.worldObj.areSkillsEnabled()) {
/*      */       
/*  274 */       Minecraft.setErrorMessage("hasAnyOfTheseSkillsets: skills aren't enabled");
/*  275 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  278 */     if (skillsets == null) {
/*  279 */       return true;
/*      */     }
/*  281 */     for (int i = 0; i < skillsets.length; i++) {
/*      */       
/*  283 */       if (hasAllOfTheseSkills(skillsets[i])) {
/*  284 */         return true;
/*      */       }
/*      */     } 
/*  287 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getItemInUse() {
/*  295 */     return this.itemInUse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemInUseCount() {
/*  303 */     return this.itemInUseCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUsingItem() {
/*  311 */     return (this.itemInUse != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemInUseDuration() {
/*  319 */     return isUsingItem() ? (this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount) : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopUsingItem() {
/*  324 */     stopUsingItem(onClient());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void stopUsingItem(boolean inform_server) {
/*  330 */     if (this.itemInUse != null)
/*      */     {
/*  332 */       this.itemInUse.onPlayerStoppedUsing(this.worldObj, this, this.itemInUseCount);
/*      */     }
/*      */     
/*  335 */     clearItemInUse();
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearItemInUse() {
/*  340 */     this.itemInUse = null;
/*  341 */     this.itemInUseCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  348 */     setEating(false);
/*      */     
/*  350 */     if (onServer()) {
/*  351 */       sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.item_in_use)).setInteger(0).setEntityID(this), false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlocking() {
/*  357 */     return (isUsingItem() && Item.itemsList[this.itemInUse.itemID].getItemInUseAction(this.itemInUse, this) == EnumItemInUseAction.BLOCK);
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkForArmorAchievements() {
/*  362 */     boolean wearing_leather = false;
/*  363 */     boolean wearing_full_suit_plate = true;
/*  364 */     boolean wearing_full_suit_adamantium_plate = true;
/*      */     
/*  366 */     for (int i = 0; i < 4; i++) {
/*      */       
/*  368 */       if (this.inventory.armorInventory[i] == null || !(this.inventory.armorInventory[i].getItem() instanceof ItemArmor)) {
/*      */         
/*  370 */         wearing_full_suit_plate = false;
/*  371 */         wearing_full_suit_adamantium_plate = false;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  376 */         ItemArmor armor = (ItemArmor)this.inventory.armorInventory[i].getItem();
/*  377 */         Material material = armor.getArmorMaterial();
/*      */         
/*  379 */         if (material == Material.leather) {
/*  380 */           wearing_leather = true;
/*      */         }
/*  382 */         if (material != Material.copper && material != Material.silver && material != Material.gold && material != Material.iron && material != Material.mithril && material != Material.adamantium && material != Material.ancient_metal) {
/*  383 */           wearing_full_suit_plate = false;
/*      */         }
/*  385 */         if (material != Material.adamantium)
/*  386 */           wearing_full_suit_adamantium_plate = false; 
/*      */       } 
/*      */     } 
/*  389 */     if (wearing_leather) {
/*  390 */       triggerAchievement(AchievementList.wearLeather);
/*      */     }
/*  392 */     if (wearing_full_suit_plate) {
/*  393 */       triggerAchievement(AchievementList.wearAllPlateArmor);
/*      */     }
/*  395 */     if (wearing_full_suit_adamantium_plate) {
/*  396 */       triggerAchievement(AchievementList.wearAllAdamantiumPlateArmor);
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
/*      */   public void markAllNearbyChunksForRenderUpdate(boolean report_issues) {
/*  408 */     int radius = 5;
/*      */     
/*  410 */     int chunk_currently_in_section_index = getChunkCurrentlyInSectionIndex();
/*      */     
/*  412 */     int chunk_currently_in_pos_x = getChunkPosX();
/*  413 */     int chunk_currently_in_pos_z = getChunkPosZ();
/*      */     
/*  415 */     for (int chunk_dy = -radius; chunk_dy <= radius; chunk_dy++) {
/*      */ 
/*      */       
/*  418 */       int chunk_y = chunk_currently_in_section_index + chunk_dy;
/*  419 */       int y = chunk_y * 16;
/*      */       
/*  421 */       if (y >= 0 || y <= 255)
/*      */       {
/*      */         
/*  424 */         for (int chunk_dx = -radius; chunk_dx <= radius; chunk_dx++) {
/*      */ 
/*      */           
/*  427 */           int chunk_x = chunk_currently_in_pos_x + chunk_dx;
/*  428 */           int x = chunk_x * 16;
/*      */           
/*  430 */           for (int chunk_dz = -radius; chunk_dz <= radius; chunk_dz++) {
/*      */ 
/*      */             
/*  433 */             int chunk_z = chunk_currently_in_pos_z + chunk_dz;
/*  434 */             int z = chunk_z * 16;
/*      */             
/*  436 */             if (report_issues)
/*      */             {
/*      */               
/*  439 */               if (!this.worldObj.chunkExists(chunk_x, chunk_z)) {
/*  440 */                 Minecraft.theMinecraft.thePlayer.receiveChatMessage("Chunk does not exist on client at " + x + ", " + z);
/*      */               }
/*      */             }
/*  443 */             this.worldObj.markBlockForRenderUpdate(x, y, z);
/*      */           } 
/*      */         }  } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderUpdateMethod2() {
/*      */     int chunk_dx, chunk_dz;
/*  451 */     if (Main.disable_render_update_method_2 || isGhost()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  461 */     int shell_radius = 6;
/*  462 */     int shell_size = shell_radius * 2 + 1;
/*  463 */     int shell_posts = shell_size * 4 - 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  468 */     int post_index = this.times_renderUpdateMethod2_called++ % shell_posts;
/*      */     
/*  470 */     if (post_index < shell_size) {
/*      */       
/*  472 */       chunk_dx = post_index - shell_radius;
/*  473 */       chunk_dz = -shell_radius;
/*      */     }
/*      */     else {
/*      */       
/*  477 */       post_index -= shell_size;
/*      */       
/*  479 */       if (post_index < shell_size) {
/*      */         
/*  481 */         chunk_dx = post_index - shell_radius;
/*  482 */         chunk_dz = shell_radius;
/*      */       }
/*      */       else {
/*      */         
/*  486 */         post_index -= shell_size;
/*      */         
/*  488 */         if (post_index < shell_size - 2) {
/*      */           
/*  490 */           chunk_dx = -shell_radius;
/*  491 */           chunk_dz = -shell_radius + 1 + post_index;
/*      */         }
/*      */         else {
/*      */           
/*  495 */           post_index -= shell_size - 2;
/*      */           
/*  497 */           chunk_dx = shell_radius;
/*  498 */           chunk_dz = -shell_radius + 1 + post_index;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     int chunk_x = getChunkPosX() + chunk_dx;
/*  507 */     int chunk_z = getChunkPosZ() + chunk_dz;
/*      */     
/*  509 */     int x = chunk_x * 16;
/*  510 */     int z = chunk_z * 16;
/*      */     
/*  512 */     for (int chunk_y = this.chunk_added_to_section_index - 2; chunk_y <= this.chunk_added_to_section_index + 2; chunk_y++) {
/*      */       
/*  514 */       int y = chunk_y * 16;
/*      */       
/*  516 */       if (y >= 0 && y <= 255)
/*      */       {
/*      */         
/*  519 */         this.worldObj.markBlockForRenderUpdate(x, y, z);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void checkBoundingBoxAgainstSolidBlocks() {
/*  525 */     if (this instanceof EntityOtherPlayerMP) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  542 */     List collisions = getCollidingBlockBounds();
/*      */     
/*  544 */     if (!collisions.isEmpty())
/*      */     {
/*  546 */       Minecraft.setErrorMessage("Player BB inside of solid block on " + (onClient() ? "client" : "server"));
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
/*      */   public void onUpdate() {
/*  610 */     if (onClient() && Minecraft.java_version_is_outdated) {
/*      */       
/*  612 */       Minecraft.theMinecraft.thePlayer = null;
/*  613 */       Minecraft.theMinecraft = null;
/*      */     } 
/*      */     
/*  616 */     if (!(this instanceof EntityOtherPlayerMP)) {
/*  617 */       this.openContainer.onUpdate();
/*      */     }
/*  619 */     int x = getBlockPosX();
/*  620 */     int y = getFootBlockPosY();
/*  621 */     int z = getBlockPosZ();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  796 */     if (Minecraft.inDevMode()) {
/*  797 */       checkBoundingBoxAgainstSolidBlocks();
/*      */     }
/*  799 */     if (!this.worldObj.isRemote) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  891 */       checkForArmorAchievements();
/*  892 */       this.worldObj.checkPendingEntitySpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  899 */       if (Minecraft.inDevMode())
/*      */       {
/*  901 */         BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
/*  902 */         Debug.biome_info = biome.biomeName + ", temp=" + biome.temperature + ", high humidity=" + biome.isHighHumidity();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  962 */     else if (this.countdown_to_mark_all_nearby_chunks_for_render_update > 0 && --this.countdown_to_mark_all_nearby_chunks_for_render_update == 0) {
/*      */       
/*  964 */       markAllNearbyChunksForRenderUpdate(false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1058 */     if (this.tentative_bounding_boxes.size() > 0) {
/*      */       
/* 1060 */       Iterator<TentativeBoundingBox> i = this.tentative_bounding_boxes.iterator();
/*      */       
/* 1062 */       while (i.hasNext()) {
/*      */         
/* 1064 */         TentativeBoundingBox tbb = i.next();
/*      */         
/* 1066 */         if (--tbb.countdown_for_clearing <= 0) {
/* 1067 */           i.remove();
/*      */         }
/*      */       } 
/*      */     } 
/* 1071 */     if (this.itemInUse != null) {
/*      */       
/* 1073 */       ItemStack var1 = this.inventory.getCurrentItemStack();
/*      */       
/* 1075 */       if (var1 == this.itemInUse) {
/*      */         
/* 1077 */         if (this.itemInUseCount <= 25 && this.itemInUseCount % 4 == 0)
/*      */         {
/* 1079 */           updateItemUse(var1, 5);
/*      */         }
/*      */ 
/*      */         
/* 1083 */         if (--this.itemInUseCount == 0)
/*      */         {
/* 1085 */           onItemUseFinish();
/*      */ 
/*      */         
/*      */         }
/* 1089 */         else if (isLocalClient())
/*      */         {
/* 1091 */           if (var1.getMaxItemUseDuration() - this.itemInUseCount == 1) {
/* 1092 */             sendPacket(new Packet85SimpleSignal(EnumSignal.confirm_or_cancel_item_in_use));
/*      */           }
/*      */           
/* 1095 */           if ((var1.getItemInUseAction(this) == EnumItemInUseAction.EAT || var1.getItemInUseAction(this) == EnumItemInUseAction.DRINK) && !canIngest(var1)) {
/* 1096 */             stopUsingItem();
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1102 */         clearItemInUse();
/*      */       } 
/*      */     } 
/*      */     
/* 1106 */     if (this.xpCooldown > 0)
/*      */     {
/* 1108 */       this.xpCooldown--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1142 */     if (!this.worldObj.isRemote && this.bed_location != null)
/*      */     {
/* 1144 */       if (!inBed()) {
/*      */         
/* 1146 */         wakeUpPlayer(true, (Entity)null);
/*      */       }
/* 1148 */       else if (getHostileEntityNearBed() != null) {
/*      */         
/* 1150 */         if (isSleeping()) {
/* 1151 */           addChatMessage("tile.bed.wakeMobs");
/*      */         } else {
/* 1153 */           addChatMessage("tile.bed.notSafe");
/*      */         } 
/* 1155 */         wakeUpPlayer(true, getHostileEntityNearBed());
/*      */       }
/* 1157 */       else if (isHostileEntityDiggingNearBed(this.bed_location.posX, this.bed_location.posY, this.bed_location.posZ)) {
/*      */         
/* 1159 */         if (isSleeping()) {
/* 1160 */           addChatMessage("tile.bed.wakeMobs");
/*      */         } else {
/* 1162 */           addChatMessage("tile.bed.mobsDigging");
/*      */         } 
/* 1164 */         wakeUpPlayer(true, (Entity)null);
/*      */       }
/* 1166 */       else if (isStarving()) {
/*      */         
/* 1168 */         if (isSleeping()) {
/* 1169 */           addChatMessage("tile.bed.wakeHungry");
/*      */         } else {
/* 1171 */           addChatMessage("tile.bed.tooHungry");
/*      */         } 
/* 1173 */         wakeUpPlayer(true, (Entity)null);
/*      */       } 
/*      */     }
/*      */     
/* 1177 */     super.onUpdate();
/*      */     
/* 1179 */     if (!this.worldObj.isRemote && this.openContainer != null && !this.openContainer.canInteractWith(this)) {
/*      */       
/* 1181 */       closeScreen();
/* 1182 */       this.openContainer = this.inventoryContainer;
/*      */     } 
/*      */     
/* 1185 */     if (isBurning() && this.capabilities.disableDamage)
/*      */     {
/* 1187 */       extinguish();
/*      */     }
/*      */     
/* 1190 */     this.field_71091_bM = this.field_71094_bP;
/* 1191 */     this.field_71096_bN = this.field_71095_bQ;
/* 1192 */     this.field_71097_bO = this.field_71085_bR;
/* 1193 */     double var9 = this.posX - this.field_71094_bP;
/* 1194 */     double var3 = this.posY - this.field_71095_bQ;
/* 1195 */     double var5 = this.posZ - this.field_71085_bR;
/* 1196 */     double var7 = 10.0D;
/*      */     
/* 1198 */     if (var9 > var7)
/*      */     {
/* 1200 */       this.field_71091_bM = this.field_71094_bP = this.posX;
/*      */     }
/*      */     
/* 1203 */     if (var5 > var7)
/*      */     {
/* 1205 */       this.field_71097_bO = this.field_71085_bR = this.posZ;
/*      */     }
/*      */     
/* 1208 */     if (var3 > var7)
/*      */     {
/* 1210 */       this.field_71096_bN = this.field_71095_bQ = this.posY;
/*      */     }
/*      */     
/* 1213 */     if (var9 < -var7)
/*      */     {
/* 1215 */       this.field_71091_bM = this.field_71094_bP = this.posX;
/*      */     }
/*      */     
/* 1218 */     if (var5 < -var7)
/*      */     {
/* 1220 */       this.field_71097_bO = this.field_71085_bR = this.posZ;
/*      */     }
/*      */     
/* 1223 */     if (var3 < -var7)
/*      */     {
/* 1225 */       this.field_71096_bN = this.field_71095_bQ = this.posY;
/*      */     }
/*      */     
/* 1228 */     this.field_71094_bP += var9 * 0.25D;
/* 1229 */     this.field_71085_bR += var5 * 0.25D;
/* 1230 */     this.field_71095_bQ += var3 * 0.25D;
/* 1231 */     addStat(StatList.minutesPlayedStat, 1);
/*      */     
/* 1233 */     if (this.ridingEntity == null)
/*      */     {
/* 1235 */       this.startMinecartRidingCoordinate = null;
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
/*      */   public int getMaxInPortalTime() {
/* 1250 */     return this.capabilities.disableDamage ? 0 : 80;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPortalCooldown() {
/* 1258 */     return 10;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(String par1Str, float par2, float par3) {
/* 1264 */     if (isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 1267 */     this.worldObj.playSoundToNearExcept(this, par1Str, par2, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateItemUse(ItemStack par1ItemStack, int par2) {
/* 1276 */     if (par1ItemStack.getItemInUseAction(this) == EnumItemInUseAction.DRINK)
/*      */     {
/* 1278 */       playSound("random.drink", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*      */     }
/*      */ 
/*      */     
/* 1282 */     if (par1ItemStack.getItemInUseAction(this) == EnumItemInUseAction.EAT) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1289 */       for (int var3 = 0; var3 < par2; var3++) {
/*      */         
/* 1291 */         Vec3 var4 = this.worldObj.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 1292 */         var4.rotateAroundX(-this.rotationPitch * 3.1415927F / 180.0F);
/* 1293 */         var4.rotateAroundY(-this.rotationYaw * 3.1415927F / 180.0F);
/* 1294 */         Vec3 var5 = this.worldObj.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.3D, -this.rand.nextFloat() * 0.6D - 0.3D, 0.6D);
/* 1295 */         var5.rotateAroundX(-this.rotationPitch * 3.1415927F / 180.0F);
/* 1296 */         var5.rotateAroundY(-this.rotationYaw * 3.1415927F / 180.0F);
/*      */         
/* 1298 */         var5 = var5.addVector(this.posX, getEyePosY(), this.posZ);
/*      */         
/* 1300 */         this.worldObj.spawnParticleEx(EnumParticle.iconcrack, (par1ItemStack.getItem()).itemID, 0, var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord);
/*      */       } 
/*      */       
/* 1303 */       playSound("random.eat", 0.5F + 0.5F * this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
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
/*      */   protected void onItemUseFinish() {
/* 1349 */     if (this.itemInUse != null) {
/*      */       
/* 1351 */       updateItemUse(this.itemInUse, 16);
/* 1352 */       this.itemInUse.onItemUseFinish(this.worldObj, this);
/*      */       
/* 1354 */       addStat(StatList.objectUseStats[this.itemInUse.itemID], 1);
/*      */       
/* 1356 */       clearItemInUse();
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
/*      */   protected boolean isMovementBlocked() {
/* 1385 */     return (getHealth() <= 0.0F || inBed());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void closeScreen() {
/* 1393 */     this.openContainer = this.inventoryContainer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity par1Entity) {
/* 1401 */     if (this.ridingEntity != null && par1Entity == null) {
/*      */       
/* 1403 */       if (!this.worldObj.isRemote)
/*      */       {
/* 1405 */         dismountEntity(this.ridingEntity);
/*      */       }
/*      */       
/* 1408 */       if (this.ridingEntity != null)
/*      */       {
/* 1410 */         this.ridingEntity.riddenByEntity = null;
/*      */       }
/*      */       
/* 1413 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 1417 */       super.mountEntity(par1Entity);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/* 1426 */     if (!this.worldObj.isRemote && isSneaking()) {
/*      */       
/* 1428 */       mountEntity((Entity)null);
/* 1429 */       setSneaking(false);
/*      */     }
/*      */     else {
/*      */       
/* 1433 */       double var1 = this.posX;
/* 1434 */       double var3 = this.posY;
/* 1435 */       double var5 = this.posZ;
/* 1436 */       float var7 = this.rotationYaw;
/* 1437 */       float var8 = this.rotationPitch;
/* 1438 */       super.updateRidden();
/* 1439 */       this.prevCameraYaw = this.cameraYaw;
/* 1440 */       this.cameraYaw = 0.0F;
/* 1441 */       addMountedMovementStat(this.posX - var1, this.posY - var3, this.posZ - var5);
/*      */       
/* 1443 */       if (this.ridingEntity instanceof EntityPig) {
/*      */         
/* 1445 */         this.rotationPitch = var8;
/* 1446 */         this.rotationYaw = var7;
/* 1447 */         this.renderYawOffset = ((EntityPig)this.ridingEntity).renderYawOffset;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1457 */   private static final int[] experience_for_level = new int[getHighestPossibleLevel() + 1];
/*      */   static {
/* 1459 */     int xp = 0;
/* 1460 */     int increase = 20;
/*      */     
/* 1462 */     for (int level = 1; level < experience_for_level.length; level++) {
/*      */       
/* 1464 */       xp += increase;
/* 1465 */       increase += 10;
/*      */       
/* 1467 */       experience_for_level[level] = xp;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static final int getHighestPossibleLevel() {
/* 1473 */     return 200;
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
/*      */   protected static final int getExperienceRequired(int level) {
/* 1488 */     return (level < 0) ? (level * 20) : ((level > getHighestPossibleLevel()) ? Integer.MAX_VALUE : experience_for_level[level]);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getExperienceLevel(int experience) {
/* 1493 */     if (experience < 0 && inCreativeMode()) {
/* 1494 */       return 0;
/*      */     }
/* 1496 */     if (experience < 0) {
/* 1497 */       return Math.max(-((-experience - 1) / 20 + 1), -40);
/*      */     }
/* 1499 */     int level = 0;
/*      */     
/*      */     do {
/*      */     
/* 1503 */     } while (getExperienceRequired(++level) <= experience);
/* 1504 */     return level - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getExperienceLevel() {
/* 1510 */     return getExperienceLevel(this.experience);
/*      */   }
/*      */ 
/*      */   
/*      */   public final float getLevelProgress() {
/* 1515 */     int bar_xp = this.experience - getExperienceRequired(getExperienceLevel());
/*      */     
/* 1517 */     return bar_xp / (getExperienceRequired(getExperienceLevel() + 1) - getExperienceRequired(getExperienceLevel()));
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getHealthLimit(int level) {
/* 1522 */     return Math.max(Math.min(6 + level / 5 * 2, 20), 6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHealthLimit() {
/* 1528 */     return getHealthLimit(getExperienceLevel());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getLevelModifier(int level, EnumLevelBonus kind) {
/* 1534 */     if (level > 0 && kind == EnumLevelBonus.MELEE_DAMAGE) {
/* 1535 */       return level * 0.005F;
/*      */     }
/* 1537 */     return level * 0.02F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLevelModifier(EnumLevelBonus kind) {
/* 1543 */     return getLevelModifier(getExperienceLevel(), kind);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void preparePlayerToSpawn() {
/* 1553 */     this.yOffset = y_offset_on_client_and_eye_height_on_server;
/*      */     
/* 1555 */     setSizeNormal();
/* 1556 */     super.preparePlayerToSpawn();
/* 1557 */     setHealth(getMaxHealth());
/* 1558 */     this.deathTime = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSizeNormal() {
/* 1563 */     setSize(0.6F, 1.8F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSizeProne() {
/* 1568 */     setSize(0.2F, 0.2F);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateEntityActionState() {
/* 1573 */     super.updateEntityActionState();
/* 1574 */     updateArmSwingProgress();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/* 1583 */     if (this.worldObj.isRemote)
/*      */     {
/* 1585 */       if (this.vision_dimming < 0.01F) {
/* 1586 */         this.vision_dimming = 0.0F;
/* 1587 */       } else if (this.vision_dimming > 2.0F) {
/* 1588 */         this.vision_dimming = 2.0F;
/*      */       } else {
/* 1590 */         this.vision_dimming -= 0.01F;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1596 */     if (this.flyToggleTimer > 0)
/*      */     {
/* 1598 */       this.flyToggleTimer--;
/*      */     }
/*      */     
/* 1601 */     if (this.worldObj.difficultySetting == 0 && getHealth() < getMaxHealth() && this.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.ticksExisted % 20 * 12 == 0)
/*      */     {
/* 1603 */       heal(1.0F);
/*      */     }
/*      */     
/* 1606 */     this.inventory.decrementAnimations();
/* 1607 */     this.prevCameraYaw = this.cameraYaw;
/* 1608 */     super.onLivingUpdate();
/* 1609 */     AttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */     
/* 1611 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1613 */       var1.setAttribute(this.capabilities.getWalkSpeed());
/*      */     }
/*      */     
/* 1616 */     this.jumpMovementFactor = this.speedInAir;
/*      */     
/* 1618 */     if (isSprinting())
/*      */     {
/* 1620 */       this.jumpMovementFactor = (float)(this.jumpMovementFactor + this.speedInAir * 0.3D);
/*      */     }
/*      */ 
/*      */     
/* 1624 */     if (!hasFoodEnergy()) {
/* 1625 */       this.jumpMovementFactor *= 0.75F;
/*      */     }
/* 1627 */     this.jumpMovementFactor *= getSpeedBoostOrSlowDownFactor();
/*      */     
/* 1629 */     setAIMoveSpeed((float)var1.getAttributeValue());
/* 1630 */     float var2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 1631 */     float var3 = (float)Math.atan(-this.motionY * 0.20000000298023224D) * 15.0F;
/*      */     
/* 1633 */     if (var2 > 0.1F)
/*      */     {
/* 1635 */       var2 = 0.1F;
/*      */     }
/*      */     
/* 1638 */     if (!this.onGround || getHealth() <= 0.0F)
/*      */     {
/* 1640 */       var2 = 0.0F;
/*      */     }
/*      */     
/* 1643 */     if (this.onGround || getHealth() <= 0.0F)
/*      */     {
/* 1645 */       var3 = 0.0F;
/*      */     }
/*      */     
/* 1648 */     this.cameraYaw += (var2 - this.cameraYaw) * 0.4F;
/* 1649 */     this.cameraPitch += (var3 - this.cameraPitch) * 0.8F;
/*      */     
/* 1651 */     if (getHealth() > 0.0F) {
/*      */       
/* 1653 */       AxisAlignedBB var4 = null;
/*      */       
/* 1655 */       if (this.ridingEntity != null && !this.ridingEntity.isDead) {
/*      */         
/* 1657 */         var4 = this.boundingBox.func_111270_a(this.ridingEntity.boundingBox).expand(1.0D, 0.0D, 1.0D);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1664 */         var4 = this.boundingBox.expand(1.0D, 0.5D, 1.0D);
/*      */       } 
/*      */       
/* 1667 */       this.collided_with_gelatinous_cube = false;
/*      */       
/* 1669 */       List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, var4);
/*      */       
/* 1671 */       if (var5 != null)
/*      */       {
/* 1673 */         for (int var6 = 0; var6 < var5.size(); var6++) {
/*      */           
/* 1675 */           Entity var7 = var5.get(var6);
/*      */           
/* 1677 */           if (!var7.isDead)
/*      */           {
/* 1679 */             collideWithPlayer(var7);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void collideWithPlayer(Entity par1Entity) {
/* 1688 */     par1Entity.onCollideWithPlayer(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getScore() {
/* 1693 */     return this.dataWatcher.getWatchableObjectInt(18);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScore(int par1) {
/* 1701 */     this.dataWatcher.updateObject(18, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addScore(int par1) {
/* 1709 */     int var2 = getScore();
/* 1710 */     this.dataWatcher.updateObject(18, Integer.valueOf(var2 + par1));
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
/*      */   public void causeBreakingItemEffect(Item item, int hotbar_slot_index) {
/* 1733 */     if (item.hasBreakingEffect())
/*      */     {
/* 1735 */       entityFX(EnumEntityFX.item_breaking, (new SignalData()).setByte(hotbar_slot_index).setShort(item.itemID));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource par1DamageSource) {
/* 1745 */     super.onDeath(par1DamageSource);
/*      */     
/* 1747 */     setSizeProne();
/* 1748 */     setPosition(this.posX, this.posY, this.posZ);
/* 1749 */     this.motionY = 0.10000000149011612D;
/*      */     
/* 1751 */     if (this.username.equals("Notch"))
/*      */     {
/* 1753 */       dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
/*      */     }
/*      */     
/* 1756 */     if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
/*      */     {
/* 1758 */       this.inventory.dropAllItems();
/*      */     }
/*      */     
/* 1761 */     if (par1DamageSource != null) {
/*      */       
/* 1763 */       this.motionX = (-MathHelper.cos((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
/* 1764 */       this.motionZ = (-MathHelper.sin((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
/*      */     }
/*      */     else {
/*      */       
/* 1768 */       this.motionX = this.motionZ = 0.0D;
/*      */     } 
/*      */     
/* 1771 */     this.yOffset = 0.1F;
/* 1772 */     addStat(StatList.deathsStat, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToPlayerScore(Entity par1Entity, int par2) {
/* 1781 */     addScore(par2);
/* 1782 */     Collection var3 = getWorldScoreboard().func_96520_a(ScoreObjectiveCriteria.totalKillCount);
/*      */     
/* 1784 */     if (par1Entity instanceof EntityPlayer) {
/*      */       
/* 1786 */       addStat(StatList.playerKillsStat, 1);
/* 1787 */       var3.addAll(getWorldScoreboard().func_96520_a(ScoreObjectiveCriteria.playerKillCount));
/*      */     }
/*      */     else {
/*      */       
/* 1791 */       addStat(StatList.mobKillsStat, 1);
/*      */     } 
/*      */     
/* 1794 */     Iterator<ScoreObjective> var4 = var3.iterator();
/*      */     
/* 1796 */     while (var4.hasNext()) {
/*      */       
/* 1798 */       ScoreObjective var5 = var4.next();
/* 1799 */       Score var6 = getWorldScoreboard().func_96529_a(getEntityName(), var5);
/* 1800 */       var6.func_96648_a();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropOneItem(boolean par1) {
/* 1809 */     return dropPlayerItemWithRandomChoice(this.inventory.decrStackSize(this.inventory.currentItem, (par1 && this.inventory.getCurrentItemStack() != null) ? (this.inventory.getCurrentItemStack()).stackSize : 1), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropPlayerItem(ItemStack par1ItemStack) {
/* 1818 */     return dropPlayerItemWithRandomChoice(par1ItemStack, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropPlayerItemWithRandomChoice(ItemStack par1ItemStack, boolean par2) {
/* 1826 */     if (par1ItemStack == null)
/*      */     {
/* 1828 */       return null;
/*      */     }
/* 1830 */     if (par1ItemStack.stackSize == 0)
/*      */     {
/* 1832 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1837 */     EntityItem var3 = new EntityItem(this.worldObj, this.posX, getEyePosY() - 0.30000001192092896D, this.posZ, par1ItemStack);
/* 1838 */     var3.delayBeforeCanPickup = 40;
/* 1839 */     float var4 = 0.1F;
/*      */ 
/*      */     
/* 1842 */     if (par2) {
/*      */       
/* 1844 */       float var5 = this.rand.nextFloat() * 0.5F;
/* 1845 */       float var6 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 1846 */       var3.motionX = (-MathHelper.sin(var6) * var5);
/* 1847 */       var3.motionZ = (MathHelper.cos(var6) * var5);
/* 1848 */       var3.motionY = 0.20000000298023224D;
/*      */     }
/*      */     else {
/*      */       
/* 1852 */       var4 = 0.3F;
/* 1853 */       var3.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var4);
/* 1854 */       var3.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var4);
/* 1855 */       var3.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * var4 + 0.1F);
/* 1856 */       var4 = 0.02F;
/* 1857 */       float var5 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 1858 */       var4 *= this.rand.nextFloat();
/* 1859 */       var3.motionX += Math.cos(var5) * var4;
/* 1860 */       var3.motionY += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
/* 1861 */       var3.motionZ += Math.sin(var5) * var4;
/*      */     } 
/*      */     
/* 1864 */     var3.age = -18000;
/* 1865 */     var3.dropped_by_player = true;
/*      */     
/* 1867 */     joinEntityItemWithWorld(var3);
/* 1868 */     addStat(StatList.dropStat, 1);
/* 1869 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropPlayerItemWithNoTrajectory(ItemStack par1ItemStack) {
/* 1875 */     if (par1ItemStack == null)
/*      */     {
/* 1877 */       return null;
/*      */     }
/* 1879 */     if (par1ItemStack.stackSize == 0)
/*      */     {
/* 1881 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1885 */     EntityItem var3 = new EntityItem(this.worldObj, this.posX, getEyePosY() - 0.30000001192092896D, this.posZ, par1ItemStack);
/* 1886 */     var3.delayBeforeCanPickup = 40;
/*      */     
/* 1888 */     var3.motionX = var3.motionY = var3.motionZ = 0.0D;
/*      */     
/* 1890 */     var3.age = -18000;
/* 1891 */     var3.dropped_by_player = true;
/*      */     
/* 1893 */     joinEntityItemWithWorld(var3);
/* 1894 */     addStat(StatList.dropStat, 1);
/* 1895 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void joinEntityItemWithWorld(EntityItem par1EntityItem) {
/* 1904 */     this.worldObj.spawnEntityInWorld(par1EntityItem);
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
/*      */   public float getCurrentPlayerStrVsBlock(int x, int y, int z, boolean apply_held_item) {
/*      */     float str_vs_block;
/* 1959 */     Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1964 */     if (block == null) {
/* 1965 */       return 0.0F;
/*      */     }
/* 1967 */     float block_hardness = this.worldObj.getBlockHardness(x, y, z);
/*      */ 
/*      */     
/* 1970 */     if (block_hardness == 0.0F) {
/* 1971 */       return 1.0F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1977 */     float min_str_vs_block = -3.4028235E38F;
/*      */     
/* 1979 */     Item held_item = getHeldItem();
/*      */     
/* 1981 */     if (block.isPortable(this.worldObj, this, x, y, z)) {
/*      */       
/* 1983 */       str_vs_block = min_str_vs_block = 4.0F * block_hardness;
/*      */     
/*      */     }
/* 1986 */     else if (apply_held_item && held_item != null) {
/*      */ 
/*      */ 
/*      */       
/* 1990 */       int metadata = this.worldObj.getBlockMetadata(x, y, z);
/*      */ 
/*      */       
/* 1993 */       str_vs_block = held_item.getStrVsBlock(block, metadata);
/*      */       
/* 1995 */       if (str_vs_block < 1.0F) {
/* 1996 */         return getCurrentPlayerStrVsBlock(x, y, z, false);
/*      */       }
/* 1998 */       int var4 = EnchantmentHelper.getEfficiencyModifier(this);
/*      */       
/* 2000 */       if (var4 > 0)
/*      */       {
/* 2002 */         float var6 = (var4 * var4 + 1);
/* 2003 */         str_vs_block += var6;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2008 */       int metadata = this.worldObj.getBlockMetadata(x, y, z);
/*      */       
/* 2010 */       if (block.blockMaterial.requiresTool(block, metadata)) {
/* 2011 */         str_vs_block = 0.0F;
/*      */       } else {
/* 2013 */         str_vs_block = 1.0F;
/*      */       } 
/*      */     } 
/* 2016 */     if (block == Block.web) {
/*      */       
/* 2018 */       boolean decrease_strength = true;
/*      */       
/* 2020 */       if (apply_held_item && held_item != null && held_item.isTool() && held_item.getAsTool().isEffectiveAgainstBlock(block, 0)) {
/* 2021 */         decrease_strength = false;
/*      */       }
/* 2023 */       if (decrease_strength) {
/* 2024 */         str_vs_block *= 0.2F;
/*      */       }
/*      */     } 
/* 2027 */     if (isPotionActive(Potion.digSpeed))
/*      */     {
/* 2029 */       str_vs_block *= 1.0F + (getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
/*      */     }
/*      */     
/* 2032 */     if (isPotionActive(Potion.digSlowdown))
/*      */     {
/* 2034 */       str_vs_block *= 1.0F - (getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
/*      */     }
/*      */     
/* 2037 */     if (isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this))
/*      */     {
/* 2039 */       str_vs_block /= 5.0F;
/*      */     }
/*      */     
/* 2042 */     if (!this.onGround)
/*      */     {
/* 2044 */       str_vs_block /= 5.0F;
/*      */     }
/*      */     
/* 2047 */     if (!hasFoodEnergy()) {
/* 2048 */       str_vs_block /= 5.0F;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2053 */     str_vs_block *= 1.0F + getLevelModifier(EnumLevelBonus.HARVESTING);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2064 */     return Math.max(str_vs_block, min_str_vs_block);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRelativeBlockHardness(int x, int y, int z, boolean apply_held_item) {
/* 2069 */     Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
/*      */     
/* 2071 */     if (block == null) {
/* 2072 */       return -1.0F;
/*      */     }
/* 2074 */     float block_hardness = this.worldObj.getBlockHardness(x, y, z);
/*      */     
/* 2076 */     if (block_hardness == 0.0F) {
/* 2077 */       return 0.0F;
/*      */     }
/* 2079 */     if (block_hardness < 0.0F || getCurrentPlayerStrVsBlock(x, y, z, apply_held_item) <= 0.0F) {
/* 2080 */       return -1.0F;
/*      */     }
/* 2082 */     float relative_hardness = block_hardness / getCurrentPlayerStrVsBlock(x, y, z, apply_held_item);
/*      */     
/* 2084 */     if (relative_hardness < 0.0029296875F) {
/* 2085 */       return 0.0F;
/*      */     }
/* 2087 */     return relative_hardness;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRelativeStrVsBlock(int x, int y, int z, boolean apply_held_item) {
/* 2092 */     float relative_hardness = getRelativeBlockHardness(x, y, z, apply_held_item);
/*      */     
/* 2094 */     if (relative_hardness < 0.0F)
/* 2095 */       return 0.0F; 
/* 2096 */     if (relative_hardness == 0.0F) {
/* 2097 */       return 512.0F;
/*      */     }
/* 2099 */     return 1.0F / relative_hardness;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getDamageVsBlock(int x, int y, int z, boolean apply_held_item) {
/* 2104 */     return getRelativeStrVsBlock(x, y, z, apply_held_item) / 512.0F;
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
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 2120 */     this.experience = par1NBTTagCompound.getInteger("experience");
/*      */     
/* 2122 */     if (par1NBTTagCompound.hasKey("XpTotal")) {
/* 2123 */       this.experience = par1NBTTagCompound.getInteger("XpTotal");
/*      */     }
/* 2125 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 2126 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Inventory");
/* 2127 */     this.inventory.readFromNBT(var2);
/* 2128 */     this.inventory.currentItem = par1NBTTagCompound.getInteger("SelectedItemSlot");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2134 */     setScore(par1NBTTagCompound.getInteger("Score"));
/*      */ 
/*      */     
/* 2137 */     if (inBed()) {
/*      */ 
/*      */       
/* 2140 */       this.bed_location = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */       
/* 2142 */       wakeUpPlayer(true, (Entity)null);
/*      */     } 
/*      */     
/* 2145 */     if (par1NBTTagCompound.hasKey("SpawnX") && par1NBTTagCompound.hasKey("SpawnY") && par1NBTTagCompound.hasKey("SpawnZ")) {
/*      */       
/* 2147 */       this.spawnChunk = new ChunkCoordinates(par1NBTTagCompound.getInteger("SpawnX"), par1NBTTagCompound.getInteger("SpawnY"), par1NBTTagCompound.getInteger("SpawnZ"));
/* 2148 */       this.spawnForced = par1NBTTagCompound.getBoolean("SpawnForced");
/*      */     } 
/*      */     
/* 2151 */     this.foodStats.readNBT(par1NBTTagCompound);
/* 2152 */     this.capabilities.readCapabilitiesFromNBT(par1NBTTagCompound);
/*      */     
/* 2154 */     if (par1NBTTagCompound.hasKey("EnderItems")) {
/*      */       
/* 2156 */       NBTTagList var3 = par1NBTTagCompound.getTagList("EnderItems");
/* 2157 */       this.theInventoryEnderChest.loadInventoryFromNBT(var3);
/*      */     } 
/*      */     
/* 2160 */     this.vision_dimming = par1NBTTagCompound.getFloat("vision_dimming");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 2168 */     par1NBTTagCompound.setInteger("experience", this.experience);
/* 2169 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 2170 */     par1NBTTagCompound.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
/* 2171 */     par1NBTTagCompound.setInteger("SelectedItemSlot", this.inventory.currentItem);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2177 */     par1NBTTagCompound.setInteger("Score", getScore());
/*      */     
/* 2179 */     if (this.spawnChunk != null) {
/*      */       
/* 2181 */       par1NBTTagCompound.setInteger("SpawnX", this.spawnChunk.posX);
/* 2182 */       par1NBTTagCompound.setInteger("SpawnY", this.spawnChunk.posY);
/* 2183 */       par1NBTTagCompound.setInteger("SpawnZ", this.spawnChunk.posZ);
/* 2184 */       par1NBTTagCompound.setBoolean("SpawnForced", this.spawnForced);
/*      */     } 
/*      */     
/* 2187 */     this.foodStats.writeNBT(par1NBTTagCompound);
/* 2188 */     this.capabilities.writeCapabilitiesToNBT(par1NBTTagCompound);
/* 2189 */     par1NBTTagCompound.setTag("EnderItems", this.theInventoryEnderChest.saveInventoryToNBT());
/*      */     
/* 2191 */     par1NBTTagCompound.setFloat("vision_dimming", this.vision_dimming);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIChestForMinecart(IInventory par1IInventory) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIChest(int x, int y, int z, IInventory par1IInventory) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIHopper(TileEntityHopper par1TileEntityHopper) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIHopperMinecart(EntityMinecartHopper par1EntityMinecartHopper) {}
/*      */ 
/*      */   
/*      */   public void displayGUIHorse(EntityHorse par1EntityHorse, IInventory par2IInventory) {}
/*      */ 
/*      */   
/*      */   public void displayGUIEnchantment(int par1, int par2, int par3, String par4Str) {}
/*      */ 
/*      */   
/*      */   public void displayGUIAnvil(int x, int y, int z) {}
/*      */ 
/*      */   
/*      */   public void displayGUIWorkbench(int par1, int par2, int par3) {}
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 2224 */     if (isSneaking()) {
/* 2225 */       return -0.1185F;
/*      */     }
/* 2227 */     return 0.12F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getFootPosY() {
/* 2233 */     double foot_pos_y = this.posY - y_offset_on_client_and_eye_height_on_server;
/*      */     
/* 2235 */     int foot_pos_y_int = (int)foot_pos_y;
/*      */     
/* 2237 */     if (foot_pos_y < foot_pos_y_int && foot_pos_y_int - foot_pos_y < 9.999999747378752E-5D) {
/* 2238 */       foot_pos_y = foot_pos_y_int;
/*      */     }
/* 2240 */     return foot_pos_y;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getEyePosY() {
/* 2246 */     if (isSneaking()) {
/* 2247 */       return this.posY + -0.23849999904632568D;
/*      */     }
/* 2249 */     return this.posY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetHeight() {
/* 2258 */     this.yOffset = y_offset_on_client_and_eye_height_on_server;
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
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 2347 */     if (this.ticksExisted < 1000 && Damage.wasCausedByPlayer(damage) && isWithinTournamentSafeZone())
/*      */     {
/*      */       
/* 2350 */       return null;
/*      */     }
/* 2352 */     if (this.capabilities.disableDamage && !damage.canHarmInCreative())
/*      */     {
/*      */       
/* 2355 */       return null;
/*      */     }
/* 2357 */     if (inBed()) {
/* 2358 */       wakeUpPlayer(true, damage.getResponsibleEntity());
/*      */     }
/* 2360 */     if (damage.isExplosion()) {
/* 2361 */       damage.scaleAmount(1.5F);
/*      */     }
/* 2363 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */     
/* 2365 */     if (result != null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2373 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canAttackPlayer(EntityPlayer par1EntityPlayer) {
/* 2378 */     Team var2 = getTeam();
/* 2379 */     Team var3 = par1EntityPlayer.getTeam();
/* 2380 */     return (var2 == null) ? true : (!var2.isSameTeam(var3) ? true : var2.getAllowFriendlyFire());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void tryDamageArmor(DamageSource damage_source, float amount, EntityDamageResult result) {
/* 2390 */     this.inventory.tryDamageArmor(damage_source, amount, result);
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
/*      */   public float getArmorVisibility() {
/* 2407 */     int var1 = 0;
/* 2408 */     ItemStack[] var2 = this.inventory.armorInventory;
/* 2409 */     int var3 = var2.length;
/*      */     
/* 2411 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/* 2413 */       ItemStack var5 = var2[var4];
/*      */       
/* 2415 */       if (var5 != null)
/*      */       {
/* 2417 */         var1++;
/*      */       }
/*      */     } 
/*      */     
/* 2421 */     return var1 / this.inventory.armorInventory.length;
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
/*      */   public void displayGUIFurnace(TileEntityFurnace par1TileEntityFurnace) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIDispenser(TileEntityDispenser par1TileEntityDispenser) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIEditSign(TileEntity par1TileEntity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBrewingStand(TileEntityBrewingStand par1TileEntityBrewingStand) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBeacon(TileEntityBeacon par1TileEntityBeacon) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIMerchant(IMerchant par1IMerchant, String par2Str) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBook(ItemStack par1ItemStack) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getYOffset() {
/* 2708 */     return (this.yOffset - 0.5F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean willDeliverCriticalStrike() {
/* 2713 */     return (this.fallDistance > 0.0F && !this.onGround && !isOnLadder() && !isInWater() && !isPotionActive(Potion.blindness) && this.ridingEntity == null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float calcRawMeleeDamageVs(Entity target) {
/* 2719 */     return calcRawMeleeDamageVs(target, willDeliverCriticalStrike(), isSuspendedInLiquid());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float calcRawMeleeDamageVs(Entity target, boolean critical, boolean suspended_in_liquid) {
/* 2725 */     float damage = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2730 */     if (damage <= 0.0F) {
/* 2731 */       return 0.0F;
/*      */     }
/* 2733 */     float enchantment_modifiers = 0.0F;
/*      */     
/* 2735 */     if (target == null || target instanceof EntityLivingBase) {
/* 2736 */       enchantment_modifiers = EnchantmentDamage.getDamageModifiers(getHeldItemStack(), (EntityLivingBase)target);
/*      */     }
/* 2738 */     if (damage > 0.0F || enchantment_modifiers > 0.0F) {
/*      */       
/* 2740 */       if (critical && damage > 0.0F) {
/* 2741 */         damage *= 1.5F;
/*      */       }
/* 2743 */       if (getLevelModifier(EnumLevelBonus.MELEE_DAMAGE) < 0.0F || !this.worldObj.areSkillsEnabled() || hasSkill(Skill.FIGHTING)) {
/* 2744 */         damage *= 1.0F + getLevelModifier(EnumLevelBonus.MELEE_DAMAGE);
/*      */       }
/* 2746 */       Item held_item = (getHeldItemStack() == null) ? null : getHeldItemStack().getItem();
/*      */       
/* 2748 */       if (held_item != null) {
/*      */         
/* 2750 */         if (target instanceof EntityLivingBase && ((EntityLivingBase)target).isEntityUndead())
/*      */         {
/* 2752 */           if (held_item.hasMaterial(Material.silver)) {
/* 2753 */             damage *= 1.25F;
/*      */           }
/*      */         }
/* 2756 */         if (target instanceof EntitySkeleton)
/*      */         {
/* 2758 */           if (held_item instanceof ItemCudgel || held_item instanceof ItemWarHammer) {
/* 2759 */             damage *= 2.0F;
/*      */           }
/*      */         }
/*      */       } 
/* 2763 */       damage += enchantment_modifiers;
/*      */       
/* 2765 */       if (suspended_in_liquid && damage > 1.0F) {
/* 2766 */         damage = 1.0F + (damage - 1.0F) * 0.5F;
/*      */       }
/*      */     } 
/* 2769 */     return damage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void attackTargetEntityWithCurrentItem(Entity target) {
/* 2780 */     if (isImmuneByGrace()) {
/*      */       return;
/*      */     }
/* 2783 */     if (target.canAttackWithItem()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2803 */       boolean critical = willDeliverCriticalStrike();
/*      */       
/* 2805 */       float damage = calcRawMeleeDamageVs(target, critical, isSuspendedInLiquid());
/*      */       
/* 2807 */       if (damage <= 0.0F) {
/*      */         return;
/*      */       }
/* 2810 */       int knockback = 0;
/*      */       
/* 2812 */       if (target instanceof EntityLivingBase) {
/* 2813 */         knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)target);
/*      */       }
/* 2815 */       if (isSprinting()) {
/* 2816 */         knockback++;
/*      */       }
/* 2818 */       boolean was_set_on_fire = false;
/* 2819 */       int fire_aspect = EnchantmentHelper.getFireAspectModifier(this);
/*      */       
/* 2821 */       if (target instanceof EntityLivingBase && fire_aspect > 0 && !target.isBurning()) {
/*      */         
/* 2823 */         was_set_on_fire = true;
/* 2824 */         target.setFire(1);
/*      */       } 
/*      */       
/* 2827 */       if (onServer() && target instanceof EntityLivingBase) {
/*      */         
/* 2829 */         EntityLivingBase entity_living_base = (EntityLivingBase)target;
/*      */         
/* 2831 */         ItemStack item_stack_to_drop = entity_living_base.getHeldItemStack();
/*      */         
/* 2833 */         if (item_stack_to_drop != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.disarming, getHeldItemStack()))
/*      */         {
/* 2835 */           if (entity_living_base instanceof EntityLiving) {
/*      */             
/* 2837 */             EntityLiving entity_living = (EntityLiving)entity_living_base;
/*      */             
/* 2839 */             entity_living.dropItemStack(item_stack_to_drop, entity_living.height / 2.0F);
/* 2840 */             entity_living.clearMatchingEquipmentSlot(item_stack_to_drop);
/*      */             
/* 2842 */             entity_living.ticks_disarmed = 40;
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2849 */       EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(this).setFireAspect((fire_aspect > 0)), damage));
/*      */       
/* 2851 */       boolean target_was_harmed = (result != null && result.entityWasNegativelyAffected());
/*      */       
/* 2853 */       target.onMeleeAttacked(this, result);
/*      */       
/* 2855 */       if (target_was_harmed) {
/*      */         
/* 2857 */         if (target instanceof EntityLivingBase) {
/*      */           
/* 2859 */           int stunning = EnchantmentHelper.getStunModifier(this, (EntityLivingBase)target);
/*      */           
/* 2861 */           if (stunning > Math.random() * 10.0D) {
/* 2862 */             ((EntityLivingBase)target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, stunning * 50, stunning * 5));
/*      */           }
/* 2864 */           heal(EnchantmentHelper.getVampiricTransfer(this, (EntityLivingBase)target, damage), EnumEntityFX.vampiric_gain);
/*      */         } 
/*      */         
/* 2867 */         if (knockback > 0) {
/*      */           
/* 2869 */           target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F));
/* 2870 */           this.motionX *= 0.6D;
/* 2871 */           this.motionZ *= 0.6D;
/* 2872 */           setSprinting(false);
/*      */         } 
/*      */         
/* 2875 */         if (critical) {
/* 2876 */           onCriticalHit(target);
/*      */         }
/* 2878 */         if (target instanceof EntityLivingBase && EnchantmentDamage.getDamageModifiers(getHeldItemStack(), (EntityLivingBase)target) > 0.0F) {
/* 2879 */           onEnchantmentCritical(target);
/*      */         }
/* 2881 */         if (damage >= 18.0F) {
/* 2882 */           triggerAchievement(AchievementList.overkill);
/*      */         }
/* 2884 */         setLastAttackTarget(target);
/*      */         
/* 2886 */         if (target instanceof EntityLivingBase) {
/*      */           
/* 2888 */           if (this.worldObj.isRemote) {
/*      */             
/* 2890 */             System.out.println("EntityPlayer.attackTargetEntityWithCurrentItem() is calling EnchantmentThorns.func_92096_a() on client");
/* 2891 */             Minecraft.temp_debug = "player";
/*      */           } 
/*      */           
/* 2894 */           EnchantmentThorns.func_92096_a(this, (EntityLivingBase)target, this.rand);
/*      */         } 
/*      */       } 
/*      */       
/* 2898 */       ItemStack held_item_stack = getHeldItemStack();
/* 2899 */       Object var10 = target;
/*      */       
/* 2901 */       if (target instanceof EntityDragonPart) {
/*      */         
/* 2903 */         IEntityMultiPart var11 = ((EntityDragonPart)target).entityDragonObj;
/*      */         
/* 2905 */         if (var11 != null && var11 instanceof EntityLivingBase) {
/* 2906 */           var10 = var11;
/*      */         }
/*      */       } 
/* 2909 */       if (target_was_harmed && held_item_stack != null && var10 instanceof EntityLivingBase) {
/* 2910 */         held_item_stack.hitEntity((EntityLivingBase)var10, this);
/*      */       }
/* 2912 */       if (target instanceof EntityLivingBase) {
/*      */         
/* 2914 */         addStat(StatList.damageDealtStat, Math.round(damage * 10.0F));
/*      */         
/* 2916 */         if (fire_aspect > 0 && target_was_harmed) {
/* 2917 */           target.setFire(fire_aspect * 4);
/* 2918 */         } else if (was_set_on_fire) {
/* 2919 */           target.extinguish();
/*      */         } 
/*      */       } 
/* 2922 */       if (onServer())
/*      */       {
/*      */         
/* 2925 */         addHungerServerSide(0.3F * EnchantmentHelper.getEnduranceModifier(this));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCriticalHit(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEnchantmentCritical(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void respawnPlayer() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDead() {
/* 3124 */     super.setDead();
/* 3125 */     this.inventoryContainer.onContainerClosed(this);
/*      */     
/* 3127 */     if (this.openContainer != null)
/*      */     {
/* 3129 */       this.openContainer.onContainerClosed(this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/* 3139 */     return (!inBed() && super.isEntityInsideOpaqueBlock());
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLiving getHostileEntityNearBed() {
/* 3144 */     return getHostileEntityNearBed(this.bed_location.posX, this.bed_location.posY, this.bed_location.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLiving getHostileEntityNearBed(int x, int y, int z) {
/* 3149 */     double var4 = 8.0D;
/* 3150 */     double var6 = 5.0D;
/* 3151 */     List var8 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(x - var4, y - var6, z - var4, x + var4, y + var6, z + var4));
/*      */     
/* 3153 */     if (var8.size() == 0) {
/* 3154 */       return null;
/*      */     }
/* 3156 */     EntityLiving nearest_pathable_hostile_entity_living = null;
/* 3157 */     double distanc_sq_to_nearest_pathable_hostile_entity_living = 1000.0D;
/*      */     
/* 3159 */     Iterator<EntityLiving> i = var8.iterator();
/*      */     
/* 3161 */     while (i.hasNext()) {
/*      */       
/* 3163 */       EntityLiving entity_living = i.next();
/*      */       
/* 3165 */       boolean is_considered_hostile = entity_living instanceof IMob;
/*      */       
/* 3167 */       if (is_considered_hostile) {
/*      */         
/* 3169 */         double distance_sq_to_entity_living = World.getDistanceSqFromDeltas((MathHelper.floor_double(entity_living.posX) - x), (MathHelper.floor_double(entity_living.posY) - y), (MathHelper.floor_double(entity_living.posZ) - z));
/*      */         
/* 3171 */         if (nearest_pathable_hostile_entity_living == null || distance_sq_to_entity_living < distanc_sq_to_nearest_pathable_hostile_entity_living) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3176 */           PathEntity path = entity_living.getNavigator().getPathToXYZ(x, y, z, 24);
/*      */           
/* 3178 */           if (path != null) {
/*      */             
/* 3180 */             PathPoint final_point = path.getFinalPathPoint();
/*      */             
/* 3182 */             int abs_dx = Math.abs(final_point.xCoord - x);
/* 3183 */             int abs_dy = Math.abs(final_point.yCoord - y);
/* 3184 */             int abs_dz = Math.abs(final_point.zCoord - z);
/*      */             
/* 3186 */             if (abs_dx <= 1 && abs_dy <= 1 && abs_dz <= 1) {
/*      */               
/* 3188 */               nearest_pathable_hostile_entity_living = entity_living;
/* 3189 */               distanc_sq_to_nearest_pathable_hostile_entity_living = distance_sq_to_entity_living;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3196 */     return nearest_pathable_hostile_entity_living;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHostileEntityDiggingNearBed(int x, int y, int z) {
/* 3201 */     double var4 = 8.0D;
/* 3202 */     double var6 = 5.0D;
/* 3203 */     List var8 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(x - var4, y - var6, z - var4, x + var4, y + var6, z + var4));
/*      */     
/* 3205 */     if (var8.size() == 0) {
/* 3206 */       return false;
/*      */     }
/* 3208 */     Iterator<EntityLiving> i = var8.iterator();
/*      */     
/* 3210 */     while (i.hasNext()) {
/*      */       
/* 3212 */       EntityLiving entity_living = i.next();
/*      */       
/* 3214 */       if (entity_living instanceof EntityZombie) {
/*      */         
/* 3216 */         EntityZombie entity_zombie = (EntityZombie)entity_living;
/*      */         
/* 3218 */         if (entity_zombie.is_destroying_block) {
/* 3219 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/* 3223 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static float getYawForDirection(int direction) {
/* 3228 */     return direction * 90.0F - 180.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotationInBed() {
/*      */     float offset_x, offset_z;
/* 3236 */     switch (this.bed_direction) {
/*      */       
/*      */       case 0:
/* 3239 */         offset_x = 0.5F;
/* 3240 */         offset_z = 0.9F;
/*      */         break;
/*      */       
/*      */       case 1:
/* 3244 */         offset_x = 0.1F;
/* 3245 */         offset_z = 0.5F;
/*      */         break;
/*      */       
/*      */       case 2:
/* 3249 */         offset_x = 0.5F;
/* 3250 */         offset_z = 0.1F;
/*      */         break;
/*      */       
/*      */       default:
/* 3254 */         offset_x = 0.9F;
/* 3255 */         offset_z = 0.5F;
/*      */         break;
/*      */     } 
/* 3258 */     float x = this.bed_location.posX + offset_x;
/* 3259 */     float y = this.bed_location.posY + 0.7626F;
/* 3260 */     float z = this.bed_location.posZ + offset_z;
/*      */     
/* 3262 */     setPosition(x, y, z, true);
/*      */     
/* 3264 */     if (onServer()) {
/* 3265 */       (getAsEntityPlayerMP()).set_position_in_bed_next_tick = true;
/*      */     }
/* 3267 */     this.lastTickPosX = x;
/* 3268 */     this.lastTickPosY = y;
/* 3269 */     this.lastTickPosZ = z;
/*      */     
/* 3271 */     this.rotationYaw = getYawForDirection(this.bed_direction);
/* 3272 */     this.rotationPitch = 0.0F;
/*      */     
/* 3274 */     this.rotationYawHead = this.rotationYaw;
/*      */     
/* 3276 */     this.motionX = this.motionZ = this.motionY = 0.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public void getIntoBed(int x, int y, int z, int direction) {
/* 3281 */     if (isRiding()) {
/* 3282 */       mountEntity((Entity)null);
/*      */     }
/* 3284 */     if (onServer()) {
/*      */       
/* 3286 */       this.pos_x_before_bed = this.posX;
/* 3287 */       this.pos_y_before_bed = this.posY;
/* 3288 */       this.pos_z_before_bed = this.posZ;
/*      */     } 
/*      */ 
/*      */     
/* 3292 */     setSizeProne();
/* 3293 */     this.yOffset = 0.2F;
/*      */     
/* 3295 */     func_71013_b(direction);
/*      */ 
/*      */     
/* 3298 */     this.bed_location = new ChunkCoordinates(x, y, z);
/* 3299 */     this.bed_direction = direction;
/*      */     
/* 3301 */     setPositionAndRotationInBed();
/*      */     
/* 3303 */     if (!this.worldObj.isRemote) {
/* 3304 */       BlockBed.setBedOccupied(this.worldObj, x, y, z, true);
/*      */     }
/* 3306 */     setSpawnChunk(this.bed_location, false);
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
/*      */   public void tryToSleepInBedAt(int x, int y, int z) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_71013_b(int par1) {
/* 3404 */     this.field_71079_bU = 0.0F;
/* 3405 */     this.field_71089_bV = 0.0F;
/*      */     
/* 3407 */     switch (par1) {
/*      */       
/*      */       case 0:
/* 3410 */         this.field_71089_bV = -1.8F;
/*      */         break;
/*      */       
/*      */       case 1:
/* 3414 */         this.field_71079_bU = 1.8F;
/*      */         break;
/*      */       
/*      */       case 2:
/* 3418 */         this.field_71089_bV = 1.8F;
/*      */         break;
/*      */       
/*      */       case 3:
/* 3422 */         this.field_71079_bU = -1.8F;
/*      */         break;
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
/*      */   public void getOutOfBed(Entity entity_to_look_at) {
/* 3474 */     setSizeNormal();
/* 3475 */     resetHeight();
/*      */     
/* 3477 */     if (this.bed_location != null) {
/*      */       ChunkCoordinates new_location;
/* 3479 */       int x = this.bed_location.posX;
/* 3480 */       int y = this.bed_location.posY;
/* 3481 */       int z = this.bed_location.posZ;
/*      */ 
/*      */ 
/*      */       
/* 3485 */       if (this.worldObj.getBlockId(x, y, z) == Block.bed.blockID) {
/*      */         
/* 3487 */         BlockBed.setBedOccupied(this.worldObj, x, y, z, false);
/* 3488 */         new_location = BlockBed.getNearestEmptyChunkCoordinates(this.worldObj, x, y, z, 0, this.worldObj.getVec3(this.pos_x_before_bed, this.pos_y_before_bed, this.pos_z_before_bed));
/*      */         
/* 3490 */         if (new_location == null) {
/* 3491 */           new_location = new ChunkCoordinates(x, y + 1, z);
/*      */         }
/*      */       } else {
/*      */         
/* 3495 */         new_location = new ChunkCoordinates(x, y, z);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3500 */       if (new_location.posX == MathHelper.floor_double(this.pos_x_before_bed) && new_location.posY == MathHelper.floor_double(this.pos_y_before_bed + 0.949999988079071D) && new_location.posZ == MathHelper.floor_double(this.pos_z_before_bed)) {
/* 3501 */         setPosition(this.pos_x_before_bed, this.pos_y_before_bed + this.yOffset, this.pos_z_before_bed, true);
/*      */       } else {
/* 3503 */         setPosition((new_location.posX + 0.5F), (new_location.posY + this.yOffset), (new_location.posZ + 0.5F), true);
/*      */       } 
/*      */     } 
/* 3506 */     if (entity_to_look_at == null) {
/*      */       
/* 3508 */       if (this.bed_location != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3514 */         setRotationForLookingAt(this.worldObj.getBlockCenterPos(this.bed_location.posX, this.bed_location.posY, this.bed_location.posZ));
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 3523 */       setRotationForLookingAt((entity_to_look_at instanceof EntityLivingBase) ? entity_to_look_at.getAsEntityLivingBase().getFootPosPlusFractionOfHeight(0.75F) : entity_to_look_at.getCenterPoint());
/*      */     } 
/*      */     
/* 3526 */     this.bed_location = null;
/*      */     
/* 3528 */     this.conscious_state = EnumConsciousState.fully_awake;
/*      */     
/* 3530 */     if (this.worldObj.isRemote) {
/*      */ 
/*      */ 
/*      */       
/* 3534 */       this.lastTickPosX = this.posX;
/* 3535 */       this.lastTickPosY = this.posY;
/* 3536 */       this.lastTickPosZ = this.posZ;
/*      */     }
/*      */     else {
/*      */       
/* 3540 */       (getAsEntityPlayerMP()).try_push_out_of_blocks = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void wakeUpPlayer(boolean get_out_of_bed, Entity entity_to_look_at) {
/* 3546 */     if (get_out_of_bed) {
/* 3547 */       getOutOfBed(entity_to_look_at);
/*      */     } else {
/* 3549 */       this.conscious_state = EnumConsciousState.waking_up;
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
/*      */   public boolean inBed() {
/* 3562 */     return (this.bed_location != null && (this.worldObj.isRemote || this.worldObj.getBlockId(this.bed_location.posX, this.bed_location.posY, this.bed_location.posZ) == Block.bed.blockID));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2) {
/* 3571 */     IChunkProvider var3 = par0World.getChunkProvider();
/* 3572 */     var3.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
/* 3573 */     var3.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
/* 3574 */     var3.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
/* 3575 */     var3.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
/*      */     
/* 3577 */     if (par0World.getBlockId(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ) == Block.bed.blockID) {
/*      */ 
/*      */       
/* 3580 */       ChunkCoordinates var8 = BlockBed.getNearestEmptyChunkCoordinates(par0World, par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ, 0, (Vec3)null);
/* 3581 */       return var8;
/*      */     } 
/*      */ 
/*      */     
/* 3585 */     Material var4 = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ);
/* 3586 */     Material var5 = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY + 1, par1ChunkCoordinates.posZ);
/* 3587 */     boolean var6 = (!var4.isSolid() && !var4.isLiquid());
/* 3588 */     boolean var7 = (!var5.isSolid() && !var5.isLiquid());
/* 3589 */     return (par2 && var6 && var7) ? par1ChunkCoordinates : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBedOrientationInDegrees() {
/* 3599 */     if (this.bed_location != null) {
/*      */ 
/*      */       
/* 3602 */       int var1 = this.worldObj.getBlockMetadata(this.bed_location.posX, this.bed_location.posY, this.bed_location.posZ);
/* 3603 */       int var2 = BlockBed.j(var1);
/*      */       
/* 3605 */       switch (var2) {
/*      */         
/*      */         case 0:
/* 3608 */           return 90.0F;
/*      */         
/*      */         case 1:
/* 3611 */           return 0.0F;
/*      */         
/*      */         case 2:
/* 3614 */           return 270.0F;
/*      */         
/*      */         case 3:
/* 3617 */           return 180.0F;
/*      */       } 
/*      */     
/*      */     } 
/* 3621 */     return 0.0F;
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
/*      */   public boolean isSleeping() {
/* 3650 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getHideCape(int par1) {
/* 3660 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 1 << par1) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setHideCape(int par1, boolean par2) {
/* 3665 */     byte var3 = this.dataWatcher.getWatchableObjectByte(16);
/*      */     
/* 3667 */     if (par2) {
/*      */       
/* 3669 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var3 | 1 << par1)));
/*      */     }
/*      */     else {
/*      */       
/* 3673 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var3 & (1 << par1 ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addChatMessage(String par1Str) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkCoordinates getBedLocation() {
/* 3687 */     return this.spawnChunk;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSpawnForced() {
/* 3692 */     return this.spawnForced;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpawnChunk(ChunkCoordinates par1ChunkCoordinates, boolean par2) {
/* 3700 */     if (par1ChunkCoordinates != null) {
/*      */       
/* 3702 */       this.spawnChunk = new ChunkCoordinates(par1ChunkCoordinates);
/* 3703 */       this.spawnForced = par2;
/*      */     }
/*      */     else {
/*      */       
/* 3707 */       this.spawnChunk = null;
/* 3708 */       this.spawnForced = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void triggerAchievement(StatBase par1StatBase) {
/* 3717 */     addStat(par1StatBase, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addStat(StatBase par1StatBase, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void jump() {
/* 3732 */     super.jump();
/* 3733 */     addStat(StatList.jumpStat, 1);
/*      */     
/* 3735 */     if (this instanceof EntityClientPlayerMP) {
/* 3736 */       getAsEntityClientPlayerMP().sendPacket((new Packet85SimpleSignal(EnumSignal.increment_stat_for_this_world_only)).setInteger(StatList.jumpStat.statId));
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
/*      */   public void moveEntityWithHeading(float par1, float par2) {
/* 3763 */     double var3 = this.posX;
/* 3764 */     double var5 = this.posY;
/* 3765 */     double var7 = this.posZ;
/*      */     
/* 3767 */     if (this.capabilities.isFlying && this.ridingEntity == null) {
/*      */       
/* 3769 */       double var9 = this.motionY;
/* 3770 */       float var11 = this.jumpMovementFactor;
/* 3771 */       this.jumpMovementFactor = this.capabilities.getFlySpeed();
/* 3772 */       super.moveEntityWithHeading(par1, par2);
/* 3773 */       this.motionY = var9 * 0.6D;
/* 3774 */       this.jumpMovementFactor = var11;
/*      */     }
/*      */     else {
/*      */       
/* 3778 */       if (this.collided_with_gelatinous_cube) {
/*      */         
/* 3780 */         par1 *= 0.4F;
/* 3781 */         par2 *= 0.4F;
/*      */       } 
/*      */       
/* 3784 */       super.moveEntityWithHeading(par1, par2);
/*      */     } 
/*      */     
/* 3787 */     addMovementStat(this.posX - var3, this.posY - var5, this.posZ - var7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAIMoveSpeed() {
/* 3795 */     return (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addMovementStat(double par1, double par3, double par5) {
/* 3803 */     if (this.ridingEntity == null) {
/*      */ 
/*      */ 
/*      */       
/* 3807 */       if (isInsideOfMaterial(Material.water)) {
/*      */         
/* 3809 */         int var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5) * 100.0F);
/*      */         
/* 3811 */         if (var7 > 0) {
/*      */           
/* 3813 */           addStat(StatList.distanceDoveStat, var7);
/*      */ 
/*      */           
/* 3816 */           if (onServer()) {
/* 3817 */             addHungerServerSide(0.015F * var7 * 0.01F);
/*      */           }
/*      */         } 
/* 3820 */       } else if (isInWater()) {
/*      */         
/* 3822 */         int var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100.0F);
/*      */         
/* 3824 */         if (var7 > 0) {
/*      */           
/* 3826 */           addStat(StatList.distanceSwumStat, var7);
/*      */ 
/*      */           
/* 3829 */           if (onServer()) {
/* 3830 */             addHungerServerSide(0.015F * var7 * 0.01F);
/*      */           }
/*      */         } 
/* 3833 */       } else if (isOnLadder()) {
/*      */         
/* 3835 */         if (par3 > 0.0D) {
/*      */           
/* 3837 */           addStat(StatList.distanceClimbedStat, (int)Math.round(par3 * 100.0D));
/*      */           
/* 3839 */           if (onServer()) {
/* 3840 */             addHungerServerSide((float)par3 / 10.0F);
/*      */           }
/*      */         } 
/* 3843 */       } else if (this.onGround) {
/*      */         
/* 3845 */         int var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100.0F);
/*      */         
/* 3847 */         if (inBed()) {
/* 3848 */           var7 = 0;
/*      */         }
/* 3850 */         if (var7 > 0) {
/*      */           
/* 3852 */           addStat(StatList.distanceWalkedStat, var7);
/*      */           
/* 3854 */           if (isSprinting()) {
/*      */ 
/*      */ 
/*      */             
/* 3858 */             if (onServer()) {
/* 3859 */               addHungerServerSide(0.05F * var7 * 0.01F);
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 3865 */           else if (onServer()) {
/* 3866 */             addHungerServerSide(0.01F * var7 * 0.01F);
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 3872 */         int var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par5 * par5) * 100.0F);
/*      */         
/* 3874 */         if (var7 > 25)
/*      */         {
/* 3876 */           addStat(StatList.distanceFlownStat, var7);
/*      */         }
/*      */       }
/*      */     
/* 3880 */     } else if (this.ridingEntity instanceof EntityBoat) {
/*      */       
/* 3882 */       if (onClient())
/*      */       {
/* 3884 */         if (this.moveForward != 0.0F) {
/* 3885 */           addHungerClientSide(Math.abs(this.moveForward) * 0.01F);
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addMountedMovementStat(double par1, double par3, double par5) {
/* 3895 */     if (this.ridingEntity != null) {
/*      */       
/* 3897 */       int var7 = Math.round(MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5) * 100.0F);
/*      */       
/* 3899 */       if (var7 > 0)
/*      */       {
/* 3901 */         if (this.ridingEntity instanceof EntityMinecart) {
/*      */           
/* 3903 */           addStat(StatList.distanceByMinecartStat, var7);
/*      */           
/* 3905 */           if (this.startMinecartRidingCoordinate == null)
/*      */           {
/* 3907 */             this.startMinecartRidingCoordinate = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */           }
/* 3909 */           else if (this.startMinecartRidingCoordinate.getDistanceSquared(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) >= 1000000.0D)
/*      */           {
/* 3911 */             addStat(AchievementList.onARail, 1);
/*      */           }
/*      */         
/* 3914 */         } else if (this.ridingEntity instanceof EntityBoat) {
/*      */           
/* 3916 */           addStat(StatList.distanceByBoatStat, var7);
/*      */         }
/* 3918 */         else if (this.ridingEntity instanceof EntityPig) {
/*      */           
/* 3920 */           addStat(StatList.distanceByPigStat, var7);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fall(float par1) {
/* 3931 */     if (!this.capabilities.allowFlying) {
/*      */       
/* 3933 */       if (par1 >= 2.0F)
/*      */       {
/* 3935 */         addStat(StatList.distanceFallenStat, (int)Math.round(par1 * 100.0D));
/*      */       }
/*      */       
/* 3938 */       super.fall(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 3947 */     if (par1EntityLivingBase instanceof IMob)
/*      */     {
/* 3949 */       triggerAchievement(AchievementList.killEnemy);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInWeb() {
/* 3958 */     if (!this.capabilities.isFlying)
/*      */     {
/* 3960 */       super.setInWeb();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getItemIcon(ItemStack par1ItemStack, int par2) {
/* 3969 */     Icon var3 = super.getItemIcon(par1ItemStack, par2);
/*      */ 
/*      */     
/* 3972 */     if (par1ItemStack.getItem() instanceof ItemFishingRod && this.fishEntity != null) {
/*      */ 
/*      */       
/* 3975 */       var3 = ((ItemFishingRod)par1ItemStack.getItem()).func_94597_g();
/*      */     }
/*      */     else {
/*      */       
/* 3979 */       if (par1ItemStack.getItem().requiresMultipleRenderPasses())
/*      */       {
/* 3981 */         return par1ItemStack.getItem().getIconFromSubtypeForRenderPass(par1ItemStack.getItemSubtype(), par2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4004 */       if (this.itemInUse != null && par1ItemStack.getItem() instanceof ItemBow) {
/*      */         
/* 4006 */         float fraction_pulled = ItemBow.getFractionPulled(par1ItemStack, this.itemInUseCount);
/*      */         
/* 4008 */         ItemBow item_bow = (ItemBow)par1ItemStack.getItem();
/*      */         
/* 4010 */         if (fraction_pulled >= 0.9F)
/* 4011 */           return item_bow.getItemIconForUseDuration(2, this); 
/* 4012 */         if (fraction_pulled >= 0.65F)
/* 4013 */           return item_bow.getItemIconForUseDuration(1, this); 
/* 4014 */         if (fraction_pulled > 0.0F) {
/* 4015 */           return item_bow.getItemIconForUseDuration(0, this);
/*      */         }
/*      */       } 
/*      */     } 
/* 4019 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentArmor(int par1) {
/* 4025 */     return this.inventory.armorItemInSlot(par1);
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
/*      */   public void addExperience(int amount) {
/* 4052 */     addExperience(amount, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addExperience(int amount, boolean suppress_healing) {
/* 4057 */     addExperience(amount, suppress_healing, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addExperience(int amount, boolean suppress_healing, boolean suppress_sound) {
/* 4062 */     suppress_healing = true;
/*      */     
/* 4064 */     if (amount < 0) {
/*      */       
/* 4066 */       if (!suppress_sound) {
/* 4067 */         this.worldObj.playSoundAtEntity(this, "imported.random.level_drain");
/*      */       }
/* 4069 */     } else if (amount > 0) {
/*      */       
/* 4071 */       addScore(amount);
/*      */       
/* 4073 */       if (!suppress_sound) {
/* 4074 */         this.worldObj.playSoundAtEntity(this, "random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
/*      */       }
/*      */     } 
/* 4077 */     float health_limit_before = getHealthLimit();
/*      */     
/* 4079 */     int level_before = getExperienceLevel();
/*      */     
/* 4081 */     this.experience += amount;
/*      */     
/* 4083 */     if (this.experience < getExperienceRequired(-40)) {
/* 4084 */       this.experience = getExperienceRequired(-40);
/*      */     }
/* 4086 */     int level_after = getExperienceLevel();
/*      */     
/* 4088 */     int level_change = level_after - level_before;
/*      */     
/* 4090 */     if (level_change < 0) {
/*      */       
/* 4092 */       setHealth(getHealth());
/* 4093 */       this.foodStats.setSatiation(this.foodStats.getSatiation(), true);
/* 4094 */       this.foodStats.setNutrition(this.foodStats.getNutrition(), true);
/*      */     }
/* 4096 */     else if (level_change > 0) {
/*      */ 
/*      */       
/* 4099 */       if (getHealthLimit() > health_limit_before && this.field_82249_h < this.ticksExisted - 100.0F) {
/*      */         
/* 4101 */         float volume = (level_after > 30) ? 1.0F : (level_after / 30.0F);
/*      */         
/* 4103 */         if (!suppress_sound) {
/* 4104 */           this.worldObj.playSoundAtEntity(this, "random.levelup", volume * 0.75F, 1.0F);
/*      */         }
/* 4106 */         this.field_82249_h = this.ticksExisted;
/*      */       } 
/*      */       
/* 4109 */       if (!suppress_healing) {
/* 4110 */         setHealth(getHealth() + getHealthLimit() - health_limit_before);
/*      */       }
/*      */     } 
/* 4113 */     if (level_change != 0 && !this.worldObj.isRemote) {
/* 4114 */       MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).sendPlayerInfoToAllPlayers(true);
/*      */     }
/* 4116 */     if (this instanceof ServerPlayer && DedicatedServer.tournament_type == EnumTournamentType.score) {
/*      */       
/* 4118 */       (DedicatedServer.getOrCreateTournamentStanding(this)).experience = this.experience;
/* 4119 */       DedicatedServer.updateTournamentScoreOnClient(this, true);
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
/*      */   public void addHungerClientSide(float hunger) {
/* 4190 */     if (this.capabilities.isCreativeMode || this.capabilities.disableDamage) {
/*      */       return;
/*      */     }
/* 4193 */     this.foodStats.addHungerClientSide(hunger);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addHungerServerSide(float hunger) {
/* 4198 */     if (this.capabilities.isCreativeMode || this.capabilities.disableDamage) {
/*      */       return;
/*      */     }
/* 4201 */     this.foodStats.addHungerServerSide(hunger);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FoodStats getFoodStats() {
/* 4209 */     return this.foodStats;
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
/*      */   public boolean canEatAndDrink() {
/* 4223 */     if (isInsideOfMaterial(Material.water) && !canBreatheUnderwater()) {
/* 4224 */       return false;
/*      */     }
/* 4226 */     if (isPotionActive(Potion.confusion)) {
/* 4227 */       return false;
/*      */     }
/*      */     
/* 4230 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean isIngestionForbiddenByCurse(Item ingestable_item, int item_subtype) {
/* 4235 */     if (ingestable_item.isAnimalProduct() && hasCurse(Curse.cannot_eat_animals, true)) {
/* 4236 */       return true;
/*      */     }
/* 4238 */     if (ingestable_item.isPlantProduct() && hasCurse(Curse.cannot_eat_plants, true)) {
/* 4239 */       return true;
/*      */     }
/* 4241 */     if (ingestable_item.isDrinkable(item_subtype) && hasCurse(Curse.cannot_drink, true) && ingestable_item != Item.bottleOfDisenchanting) {
/* 4242 */       return true;
/*      */     }
/* 4244 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSatiation() {
/* 4249 */     return this.foodStats.getSatiation();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNutrition() {
/* 4254 */     return this.foodStats.getNutrition();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSatiationLimit() {
/* 4259 */     return this.foodStats.getSatiationLimit();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNutritionLimit() {
/* 4264 */     return this.foodStats.getNutritionLimit();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHungry() {
/* 4269 */     return (getSatiation() == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isStarving() {
/* 4274 */     return (getNutrition() == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasFoodEnergy() {
/* 4279 */     return (getSatiation() + getNutrition() != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMaxFoodEnergy() {
/* 4284 */     return (getSatiation() >= getSatiationLimit() && getNutrition() >= getNutritionLimit());
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
/*      */   public final boolean canIngest(ItemStack item_stack) {
/* 4330 */     return canIngest(item_stack.getItem(), item_stack.getItemSubtype());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean canIngest(Item item, int item_subtype) {
/* 4336 */     if (!canEatAndDrink()) {
/* 4337 */       return false;
/*      */     }
/* 4339 */     if (item == null || !item.isIngestable(item_subtype)) {
/* 4340 */       return false;
/*      */     }
/* 4342 */     if (item.isAlwaysEdible()) {
/* 4343 */       return !isIngestionForbiddenByCurse(item, item_subtype);
/*      */     }
/* 4345 */     if (!canIngest(item.getSatiation(this), item.getNutrition(), item.getSugarContent(), item.getProtein(), item.getPhytonutrients())) {
/* 4346 */       return false;
/*      */     }
/* 4348 */     return !isIngestionForbiddenByCurse(item, item_subtype);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canIngest(int satiation, int nutrition, int sugar_content, int protein, int phytonutrients) {
/* 4353 */     if (!canEatAndDrink()) {
/* 4354 */       return false;
/*      */     }
/* 4356 */     if (satiation == 0 && nutrition == 0 && sugar_content == 0) {
/* 4357 */       return true;
/*      */     }
/* 4359 */     if (this instanceof EntityClientPlayerMP) {
/*      */       
/* 4361 */       if ((getAsEntityClientPlayerMP()).is_malnourished_in_protein && protein > 0) {
/* 4362 */         return true;
/*      */       }
/* 4364 */       if ((getAsEntityClientPlayerMP()).is_malnourished_in_phytonutrients && phytonutrients > 0) {
/* 4365 */         return true;
/*      */       }
/* 4367 */     } else if (this instanceof ServerPlayer) {
/*      */       
/* 4369 */       if (getAsEntityPlayerMP().getProtein() < 1 && protein > 0) {
/* 4370 */         return true;
/*      */       }
/* 4372 */       if (getAsEntityPlayerMP().getPhytonutrients() < 1 && phytonutrients > 0) {
/* 4373 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4378 */     if (getSatiation() < getSatiationLimit()) {
/*      */       
/* 4380 */       if (satiation > 0)
/* 4381 */         return true; 
/* 4382 */       if (getNutrition() >= getNutritionLimit()) {
/* 4383 */         return false;
/*      */       }
/* 4385 */     } else if (satiation > 0 && getNutrition() > 0) {
/*      */       
/* 4387 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4394 */     return (nutrition > 0 && getNutrition() < getNutritionLimit());
/*      */   }
/*      */ 
/*      */   
/*      */   public void addFoodValue(Item item) {
/* 4399 */     this.foodStats.addFoodValue(item);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldHeal() {
/* 4407 */     return (getHealth() > 0.0F && getHealth() < getMaxHealth());
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
/*      */   public boolean isCurrentToolAdventureModeExempt(int par1, int par2, int par3) {
/* 4455 */     if (this.capabilities.allowEdit)
/*      */     {
/* 4457 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 4461 */     int var4 = this.worldObj.getBlockId(par1, par2, par3);
/*      */     
/* 4463 */     if (var4 > 0) {
/*      */       
/* 4465 */       Block var5 = Block.blocksList[var4];
/*      */       
/* 4467 */       if (var5.blockMaterial.isAdventureModeExempt())
/*      */       {
/* 4469 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 4473 */       if (getHeldItemStack() != null) {
/*      */ 
/*      */         
/* 4476 */         ItemStack var6 = getHeldItemStack();
/*      */         
/* 4478 */         int metadata = this.worldObj.getBlockMetadata(par1, par2, par3);
/*      */ 
/*      */         
/* 4481 */         if (var6.getStrVsBlock(var5, metadata) > 0.0F)
/*      */         {
/* 4483 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 4488 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean canPlayerEdit(int par1, int par2, int par3, ItemStack par5ItemStack) {
/* 4495 */     return this.capabilities.allowEdit ? true : ((par5ItemStack != null) ? par5ItemStack.canEditBlocks() : false);
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
/*      */   public int getExperienceValue() {
/* 4518 */     if (this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
/* 4519 */       return 0;
/*      */     }
/* 4521 */     return this.experience / 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isEntityPlayer() {
/* 4530 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/* 4538 */     return this.username;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 4543 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clonePlayer(EntityPlayer par1EntityPlayer, boolean par2) {
/* 4552 */     if (par2) {
/*      */       
/* 4554 */       this.inventory.copyInventory(par1EntityPlayer.inventory);
/* 4555 */       this.experience = par1EntityPlayer.experience;
/* 4556 */       setHealth(par1EntityPlayer.getHealth());
/* 4557 */       this.foodStats = par1EntityPlayer.foodStats;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4562 */       setScore(par1EntityPlayer.getScore());
/* 4563 */       this.teleportDirection = par1EntityPlayer.teleportDirection;
/*      */     }
/* 4565 */     else if (this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
/*      */       
/* 4567 */       this.inventory.copyInventory(par1EntityPlayer.inventory);
/*      */ 
/*      */ 
/*      */       
/* 4571 */       this.experience = par1EntityPlayer.experience;
/* 4572 */       setScore(par1EntityPlayer.getScore());
/*      */     } 
/*      */     
/* 4575 */     this.theInventoryEnderChest = par1EntityPlayer.theInventoryEnderChest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/* 4584 */     return !this.capabilities.isFlying;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPlayerAbilities() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(EnumGameType par1EnumGameType) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCommandSenderName() {
/* 4602 */     return this.username;
/*      */   }
/*      */ 
/*      */   
/*      */   public World getEntityWorld() {
/* 4607 */     return this.worldObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InventoryEnderChest getInventoryEnderChest() {
/* 4615 */     return this.theInventoryEnderChest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentItemOrArmor(int par1) {
/* 4624 */     return (par1 == 0) ? this.inventory.getCurrentItemStack() : this.inventory.armorInventory[par1 - 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getHeldItemStack() {
/* 4632 */     return this.inventory.getCurrentItemStack();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
/* 4641 */     this.inventory.armorInventory[par1] = par2ItemStack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvisibleToPlayer(EntityPlayer par1EntityPlayer) {
/* 4651 */     if (!isInvisible())
/*      */     {
/* 4653 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 4657 */     Team var2 = getTeam();
/* 4658 */     return (var2 == null || par1EntityPlayer == null || par1EntityPlayer.getTeam() != var2 || !var2.func_98297_h());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack[] getLastActiveItems() {
/* 4664 */     return this.inventory.armorInventory;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHideCape() {
/* 4669 */     return getHideCape(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPushedByWater() {
/* 4674 */     return !this.capabilities.isFlying;
/*      */   }
/*      */ 
/*      */   
/*      */   public Scoreboard getWorldScoreboard() {
/* 4679 */     return this.worldObj.getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public Team getTeam() {
/* 4684 */     return getWorldScoreboard().getPlayersTeam(this.username);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTranslatedEntityName() {
/* 4692 */     return ScorePlayerTeam.formatPlayerName(getTeam(), this.username);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAbsorptionAmount(float par1) {
/* 4697 */     if (par1 < 0.0F)
/*      */     {
/* 4699 */       par1 = 0.0F;
/*      */     }
/*      */     
/* 4702 */     getDataWatcher().updateObject(17, Float.valueOf(par1));
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAbsorptionAmount() {
/* 4707 */     return getDataWatcher().getWatchableObjectFloat(17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearCrafting() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetCraftingProgress() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacket(Packet packet) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasExtendedReach() {
/* 4743 */     return this.capabilities.isCreativeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final float getReach(Block block, int metadata) {
/* 4749 */     if (hasExtendedReach()) {
/* 4750 */       return 5.0F;
/*      */     }
/* 4752 */     float block_reach = 2.75F;
/*      */     
/* 4754 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 4756 */     if (item_stack == null) {
/* 4757 */       return block_reach;
/*      */     }
/* 4759 */     return block_reach + item_stack.getItem().getReachBonus(block, metadata);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getReach(EnumEntityReachContext context, Entity entity) {
/*      */     float height_advantage;
/* 4765 */     if (hasExtendedReach()) {
/* 4766 */       return 5.0F;
/*      */     }
/* 4768 */     float elevation_difference = (float)(this.posY - this.yOffset - entity.posY - entity.yOffset);
/*      */ 
/*      */ 
/*      */     
/* 4772 */     if (elevation_difference < -0.5F) {
/*      */       
/* 4774 */       height_advantage = (elevation_difference + 0.5F) * 0.5F;
/*      */       
/* 4776 */       if (height_advantage < -1.0F) {
/* 4777 */         height_advantage = -1.0F;
/*      */       }
/* 4779 */     } else if (elevation_difference > 0.5F) {
/*      */       
/* 4781 */       height_advantage = (elevation_difference - 0.5F) * 0.5F;
/*      */       
/* 4783 */       if (height_advantage > 1.0F) {
/* 4784 */         height_advantage = 1.0F;
/*      */       }
/*      */     } else {
/*      */       
/* 4788 */       height_advantage = 0.0F;
/*      */     } 
/*      */     
/* 4791 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 4793 */     if (context == EnumEntityReachContext.FOR_MELEE_ATTACK)
/*      */     {
/*      */       
/* 4796 */       return entity.adjustPlayerReachForAttacking(this, 1.5F + height_advantage + ((item_stack == null) ? 0.0F : item_stack.getItem().getReachBonus()));
/*      */     }
/* 4798 */     if (context == EnumEntityReachContext.FOR_INTERACTION)
/*      */     {
/* 4800 */       return entity.adjustPlayerReachForInteraction(this, 2.5F + height_advantage + ((item_stack == null) ? 0.0F : item_stack.getItem().getReachBonus(entity)));
/*      */     }
/*      */ 
/*      */     
/* 4804 */     Minecraft.setErrorMessage("getReach: invalid context");
/* 4805 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3[] getBlockReachFromPoints(float partial_tick_time) {
/* 4811 */     Vec3 camera_position = getEyePosition(partial_tick_time);
/* 4812 */     Vec3 player_center = camera_position.addVector(0.0D, -0.25D, 0.0D);
/*      */     
/* 4814 */     Vec3 player_center_lower = player_center.addVector(0.0D, -0.375D, 0.0D);
/*      */     
/* 4816 */     Vec3 player_center_upper = player_center.addVector(0.0D, 0.0D, 0.0D);
/*      */     
/* 4818 */     return new Vec3[] { player_center_lower, player_center_upper };
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final RaycastCollision getSelectedObject(float partial_tick, boolean hit_liquids) {
/* 4824 */     return getSelectedObject(partial_tick, hit_liquids, false, (EnumEntityReachContext)null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final RaycastCollision getSelectedObject(float partial_tick, boolean hit_liquids, boolean include_non_collidable_entities, EnumEntityReachContext entity_reach_context) {
/* 4830 */     if (inBed() || isGhost() || isZevimrgvInTournament()) {
/* 4831 */       return null;
/*      */     }
/* 4833 */     double reach_distance_limit = 16.0D;
/*      */     
/* 4835 */     RaycastPolicies policies = RaycastPolicies.for_selection(hit_liquids);
/*      */     
/* 4837 */     if (include_non_collidable_entities) {
/* 4838 */       policies = policies.getMutableCopy().setIncludeNonCollidableEntities(include_non_collidable_entities);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4843 */     Raycast raycast = (new Raycast(this, partial_tick, 16.0D)).setPolicies(policies).setForPlayerSelection().performVsBlocksAndEntities();
/*      */     
/* 4845 */     return raycast.getNearestCollisionReachableByObserver(entity_reach_context, partial_tick);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canReachEntity(RaycastCollision rc, EnumEntityReachContext context) {
/* 4853 */     return (rc.getDistanceFromOriginToCollisionPoint() <= getReach(context, rc.getEntityHit()));
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
/*      */   public void dismountEntity(Entity par1Entity) {
/* 4910 */     if (!this.worldObj.isRemote)
/*      */     {
/* 4912 */       for (int i = 0; i < 2; i++) {
/*      */ 
/*      */         
/* 4915 */         RaycastCollision rc = getSelectedObject(1.0F, (i > 0), false, (EnumEntityReachContext)null);
/*      */         
/* 4917 */         if (rc != null && rc.isBlock()) {
/*      */           int x;
/*      */           
/*      */           int y;
/*      */           
/*      */           int z;
/* 4923 */           if ((rc.block_hit_y - 0.5F) < this.posY && this.worldObj.isAirOrPassableBlock(rc.block_hit_x, rc.block_hit_y + 1, rc.block_hit_z, true)) {
/*      */ 
/*      */             
/* 4926 */             x = rc.block_hit_x;
/* 4927 */             y = rc.block_hit_y + 1;
/* 4928 */             z = rc.block_hit_z;
/*      */           }
/* 4930 */           else if (this.worldObj.isAirOrPassableBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, true)) {
/*      */             
/* 4932 */             x = rc.block_hit_x;
/* 4933 */             y = rc.block_hit_y;
/* 4934 */             z = rc.block_hit_z;
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 4939 */             x = rc.neighbor_block_x;
/* 4940 */             y = rc.neighbor_block_y;
/* 4941 */             z = rc.neighbor_block_z;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4947 */           if ((x != par1Entity.getBlockPosX() || y != par1Entity.getBlockPosY() || z != par1Entity.getBlockPosZ()) && this.worldObj.isAirOrPassableBlock(x, y, z, true)) {
/*      */ 
/*      */ 
/*      */             
/* 4951 */             setPositionAndUpdate((x + 0.5F), (y + 0.2F), (z + 0.5F));
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/* 4958 */     super.dismountEntity(par1Entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isEntityInvulnerable() {
/* 4963 */     if (isGhost() || isZevimrgvInTournament())
/*      */     {
/* 4965 */       return true;
/*      */     }
/* 4967 */     return super.isEntityInvulnerable();
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isGhost() {
/* 4972 */     if (!this.has_dedicated_server_ghost_check_been_made) {
/*      */       
/* 4974 */       this.is_dedicated_server_ghost = this.username.equals("Dedicated_Server");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4979 */       this.has_dedicated_server_ghost_check_been_made = true;
/*      */     } 
/*      */     
/* 4982 */     return this.is_dedicated_server_ghost;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void applyEntityCollision(Entity par1Entity) {
/* 4987 */     if (isGhost() || isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 4990 */     super.applyEntityCollision(par1Entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack[] getWornItems() {
/* 4995 */     return this.inventory.armorInventory;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setWornItem(int slot_index, ItemStack item_stack) {
/* 5000 */     return this.inventory.setWornItem(slot_index, item_stack);
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
/*      */   public boolean isLockedInFirstPersonView() {
/* 5015 */     return (isGhost() || isWearingPumpkinHelmet());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLocalClient() {
/* 5020 */     return (this == Minecraft.getClientPlayer());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean drawBackFaces() {
/* 5025 */     return (!isGhost() || isWearingItems(true));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasItems() {
/*      */     int i;
/* 5031 */     for (i = 0; i < this.inventory.mainInventory.length; i++) {
/*      */       
/* 5033 */       if (this.inventory.mainInventory[i] != null) {
/* 5034 */         return true;
/*      */       }
/*      */     } 
/* 5037 */     for (i = 0; i < this.inventory.armorInventory.length; i++) {
/*      */       
/* 5039 */       if (this.inventory.armorInventory[i] != null) {
/* 5040 */         return true;
/*      */       }
/*      */     } 
/* 5043 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Curse getCurse() {
/* 5048 */     if (this.worldObj.isRemote)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 5053 */       return Curse.cursesList[this.curse_id];
/*      */     }
/*      */     
/* 5056 */     return ((WorldServer)this.worldObj).getCurseForPlayer(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCurse(Curse curse, boolean learn_effect_if_so) {
/* 5061 */     boolean has_curse = (this.is_cursed && this.curse_id == curse.id);
/*      */     
/* 5063 */     if (has_curse && learn_effect_if_so && !this.curse_effect_known) {
/* 5064 */       learnCurseEffect();
/*      */     }
/* 5066 */     return has_curse;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCurse(Curse curse) {
/* 5071 */     return hasCurse(curse, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void learnCurseEffect() {
/* 5076 */     if (!this.is_cursed || this.curse_id == 0) {
/*      */       
/* 5078 */       Minecraft.setErrorMessage("learnCurseEffect: player is not cursed!");
/*      */       
/*      */       return;
/*      */     } 
/* 5082 */     if (this.worldObj.isRemote) {
/*      */       
/* 5084 */       this.curse_effect_known = true;
/* 5085 */       sendPacket(new Packet85SimpleSignal(EnumSignal.curse_effect_learned));
/*      */     }
/*      */     else {
/*      */       
/* 5089 */       (getCurse()).effect_known = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void onCurseRealized(int curse_id) {
/* 5095 */     if (curse_id == Curse.cannot_wear_armor.id)
/*      */     {
/* 5097 */       if (!this.worldObj.isRemote)
/*      */       {
/* 5099 */         if (this.inventory.dropAllArmor()) {
/* 5100 */           learnCurseEffect();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setAir(int par1) {
/* 5107 */     if (hasCurse(Curse.cannot_hold_breath) && par1 > 90) {
/* 5108 */       par1 = 90;
/*      */     }
/* 5110 */     super.setAir(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean inCreativeMode() {
/* 5115 */     return (this.capabilities != null && this.capabilities.isCreativeMode);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumQuality getMinCraftingQuality(Item item, int[] applicable_skillsets) {
/* 5121 */     if (!this.worldObj.areSkillsEnabled()) {
/* 5122 */       applicable_skillsets = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5128 */     int effective_experience_level = getExperienceLevel();
/*      */     
/* 5130 */     if (hasCurse(Curse.clumsiness, true)) {
/* 5131 */       effective_experience_level -= 20;
/*      */     }
/*      */ 
/*      */     
/* 5135 */     EnumQuality quality = EnumQuality.get(MathHelper.clamp_int(EnumQuality.average.ordinal() + effective_experience_level / 10, 0, EnumQuality.average.ordinal()));
/*      */     
/* 5137 */     if (applicable_skillsets != null)
/*      */     {
/* 5139 */       if (!hasAnyOfTheseSkillsets(applicable_skillsets)) {
/* 5140 */         return EnumQuality.getLowest(quality, EnumQuality.poor);
/*      */       }
/*      */     }
/* 5143 */     return quality;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumQuality getMaxCraftingQuality(float unadjusted_crafting_difficulty_to_produce, Item item, int[] applicable_skillsets) {
/* 5149 */     if (!this.worldObj.areSkillsEnabled()) {
/* 5150 */       applicable_skillsets = null;
/*      */     }
/* 5152 */     if (this.experience <= 0) {
/* 5153 */       return getMinCraftingQuality(item, applicable_skillsets);
/*      */     }
/* 5155 */     if (applicable_skillsets != null)
/*      */     {
/* 5157 */       if (!hasAnyOfTheseSkillsets(applicable_skillsets)) {
/* 5158 */         return getMinCraftingQuality(item, applicable_skillsets);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5167 */     if (item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
/* 5168 */       Minecraft.setErrorMessage("getMaxCraftingQuality: item has no recipes! " + item.getItemDisplayName(null));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5177 */     for (int i = item.getMaxQuality().ordinal(); i > EnumQuality.average.ordinal(); i--) {
/*      */       
/* 5179 */       if (getCraftingExperienceCost(CraftingResult.getQualityAdjustedDifficulty(unadjusted_crafting_difficulty_to_produce, EnumQuality.values()[i])) <= this.experience) {
/* 5180 */         return EnumQuality.values()[i];
/*      */       }
/*      */     } 
/* 5183 */     return getMinCraftingQuality(item, applicable_skillsets);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCraftingExperienceCost(float quality_adjusted_crafting_difficulty) {
/* 5189 */     int cost = Math.round(quality_adjusted_crafting_difficulty / 5.0F);
/*      */     
/* 5191 */     if (hasCurse(Curse.clumsiness, true)) {
/* 5192 */       cost *= 2;
/*      */     }
/* 5194 */     return cost;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isImmuneByGrace() {
/* 5200 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFireResistance() {
/* 5211 */     return 10;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToAssociatedPlayers(Packet packet, boolean include_sender) {
/* 5217 */     if (this.worldObj.isRemote) {
/*      */       
/* 5219 */       sendPacket(new Packet90BroadcastToAssociatedPlayers(packet, include_sender));
/*      */     }
/*      */     else {
/*      */       
/* 5223 */       WorldServer world_server = (WorldServer)this.worldObj;
/* 5224 */       EntityTracker entity_tracker = world_server.getEntityTracker();
/*      */       
/* 5226 */       if (include_sender) {
/* 5227 */         entity_tracker.sendPacketToAllAssociatedPlayers(this, packet);
/*      */       } else {
/* 5229 */         entity_tracker.sendPacketToAllPlayersTrackingEntity(this, packet);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean checkForBlockActivation(RaycastCollision rc) {
/* 5235 */     if (rc == null || !rc.isBlock()) {
/* 5236 */       return false;
/*      */     }
/* 5238 */     World world = this.worldObj;
/*      */     
/* 5240 */     if (isSneaking() && hasHeldItem()) {
/* 5241 */       return false;
/*      */     }
/* 5243 */     Block block = rc.getBlockHit();
/*      */     
/* 5245 */     int x = rc.block_hit_x;
/* 5246 */     int y = rc.block_hit_y;
/* 5247 */     int z = rc.block_hit_z;
/*      */     
/* 5249 */     if (block.onBlockActivated(world, x, y, z, this, rc.face_hit, rc.block_hit_offset_x, rc.block_hit_offset_y, rc.block_hit_offset_z)) {
/*      */       
/* 5251 */       if (world.isRemote && !rightClickCancelled()) {
/*      */         
/* 5253 */         if (block.playerSwingsOnBlockActivated(!hasHeldItem())) {
/* 5254 */           swingArm();
/*      */         }
/* 5256 */         Minecraft.theMinecraft.right_click_counter = 10;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5262 */       return true;
/*      */     } 
/*      */     
/* 5265 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean checkForEntityInteraction(RaycastCollision rc) {
/* 5270 */     if (rc == null || !rc.isEntity() || !rc.getEntityHit().isEntityAlive()) {
/* 5271 */       return false;
/*      */     }
/* 5273 */     if (!canReachEntity(rc, EnumEntityReachContext.FOR_INTERACTION)) {
/* 5274 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5296 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 5298 */     if (item_stack != null && item_stack.tryEntityInteraction(rc.getEntityHit(), this)) {
/*      */       
/* 5300 */       if (onClient() && !rightClickCancelled()) {
/* 5301 */         swingArm();
/*      */       }
/* 5303 */       return true;
/*      */     } 
/*      */     
/* 5306 */     return rc.getEntityHit().onEntityRightClicked(this, item_stack);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean checkForIngestion() {
/* 5311 */     ItemStack held_item_stack = getHeldItemStack();
/*      */     
/* 5313 */     if (held_item_stack == null) {
/* 5314 */       return false;
/*      */     }
/* 5316 */     EnumItemInUseAction action = held_item_stack.getItemInUseAction(this);
/*      */     
/* 5318 */     if (action == null || !action.isIngestion()) {
/* 5319 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5332 */     if (canIngest(held_item_stack)) {
/*      */       
/* 5334 */       if (isLocalClient())
/*      */       {
/* 5336 */         if (!Minecraft.theMinecraft.playerController.ingestionEnabled()) {
/*      */ 
/*      */ 
/*      */           
/* 5340 */           cancelRightClick();
/* 5341 */           return true;
/*      */         } 
/*      */       }
/*      */       
/* 5345 */       setHeldItemInUse();
/* 5346 */       return true;
/*      */     } 
/*      */     
/* 5349 */     return false;
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
/*      */   private final RightClickFilter onPlayerRightClick(RaycastCollision rc, RightClickFilter filter, float partial_tick, boolean ctrl_is_down) {
/* 5397 */     World world = this.worldObj;
/*      */     
/* 5399 */     if (isLocalClient()) {
/* 5400 */       Minecraft.theMinecraft.playerController.syncCurrentPlayItem();
/*      */     }
/*      */     
/* 5403 */     if (filter.allowsBlockActivation() && checkForBlockActivation(rc))
/*      */     {
/* 5405 */       return filter.setExclusive(1);
/*      */     }
/* 5407 */     if (filter.allowsEntityInteraction() && checkForEntityInteraction(rc)) {
/* 5408 */       return filter.setExclusive(2);
/*      */     }
/* 5410 */     ItemStack held_item_stack = getHeldItemStack();
/*      */     
/* 5412 */     if (rc == null && held_item_stack == null)
/*      */     {
/* 5414 */       return filter.setNoActionAllowed();
/*      */     }
/* 5416 */     if (held_item_stack != null) {
/*      */       
/* 5418 */       if (held_item_stack.stackSize < 1) {
/*      */         
/* 5420 */         Minecraft.setErrorMessage("onPlayerRightClick: stack size is " + held_item_stack.stackSize);
/*      */         
/* 5422 */         return filter.setNoActionAllowed();
/*      */       } 
/*      */ 
/*      */       
/* 5426 */       if (filter.allowsIngestion() && held_item_stack.hasIngestionPriority(ctrl_is_down) && checkForIngestion()) {
/* 5427 */         return filter.setExclusive(4);
/*      */       }
/* 5429 */       if (filter.allowsOnItemRightClick() && held_item_stack.getItem().onItemRightClick(this, partial_tick, ctrl_is_down)) {
/* 5430 */         return filter.setExclusive(8);
/*      */       }
/* 5432 */       if (filter.allowsIngestion() && !held_item_stack.hasIngestionPriority(ctrl_is_down) && checkForIngestion()) {
/* 5433 */         return filter.setExclusive(4);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5439 */     return filter.setNoActionAllowed();
/*      */   }
/*      */ 
/*      */   
/*      */   public void bobItem() {
/* 5444 */     if (isLocalClient()) {
/* 5445 */       Minecraft.theMinecraft.entityRenderer.itemRenderer.resetEquippedProgress();
/*      */     } else {
/* 5447 */       Minecraft.setErrorMessage("bobItem: only meant to be called on client");
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
/*      */   public final RightClickFilter onPlayerRightClickChecked(RaycastCollision rc, RightClickFilter filter, float partial_tick, boolean ctrl_is_down) {
/* 5459 */     if (!isEntityAlive()) {
/* 5460 */       return filter.setNoActionAllowed();
/*      */     }
/* 5462 */     ItemStack held_item_stack_before = getHeldItemStack();
/* 5463 */     ItemStack held_item_stack_before_copy = (held_item_stack_before == null) ? null : held_item_stack_before.copy();
/*      */     
/* 5465 */     ItemStack item_in_use_before = this.itemInUse;
/*      */     
/* 5467 */     filter = onPlayerRightClick(rc, filter, partial_tick, ctrl_is_down);
/*      */     
/* 5469 */     if (rightClickCancelled()) {
/* 5470 */       filter.setNoActionAllowed();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5480 */     ItemStack held_item_stack_after = getHeldItemStack();
/* 5481 */     ItemStack item_in_use_after = this.itemInUse;
/*      */     
/* 5483 */     boolean held_item_stack_changed = (held_item_stack_after != held_item_stack_before || !ItemStack.areItemStacksEqual(held_item_stack_after, held_item_stack_before_copy));
/*      */     
/* 5485 */     if ((held_item_stack_changed || item_in_use_after != item_in_use_before) && filter.allowsNoActions()) {
/* 5486 */       Minecraft.setErrorMessage("onPlayerRightClickChecked: onPlayerRightClick returned no action but stack was modified or item was set in use");
/*      */     }
/* 5488 */     if (onClient()) {
/*      */       
/* 5490 */       if (held_item_stack_changed) {
/* 5491 */         Minecraft.setErrorMessage("onPlayerRightClickChecked: why did held item stack change on client?");
/*      */       }
/*      */     } else {
/*      */       
/* 5495 */       if (held_item_stack_after != held_item_stack_before || (held_item_stack_after != null && held_item_stack_after.stackSize < 1)) {
/* 5496 */         setHeldItemStack((held_item_stack_after == null || held_item_stack_after.stackSize < 1) ? null : held_item_stack_after);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5508 */       if (held_item_stack_before_copy != null && held_item_stack_changed && !inCreativeMode() && !this.suppress_next_stat_increment) {
/* 5509 */         addStat(StatList.objectUseStats[held_item_stack_before_copy.itemID], 1);
/*      */       } else {
/* 5511 */         this.suppress_next_stat_increment = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5520 */     return filter;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHeldItemStack(ItemStack item_stack) {
/* 5525 */     this.inventory.setInventorySlotContents(this.inventory.currentItem, item_stack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void convertOneOfHeldItem(ItemStack created_item_stack) {
/* 5530 */     this.inventory.convertOneOfCurrentItem(created_item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryPlaceHeldItemAsBlock(RaycastCollision rc, Block block) {
/* 5541 */     block.is_being_placed = true;
/* 5542 */     boolean result = getHeldItem().tryPlaceAsBlock(rc, block, this, getHeldItemStack());
/* 5543 */     block.is_being_placed = false;
/*      */     
/* 5545 */     if (!result) {
/*      */       
/* 5547 */       block = block.getAlternativeBlockForPlacement();
/*      */       
/* 5549 */       if (block != null) {
/*      */         
/* 5551 */         block.is_being_placed = true;
/* 5552 */         result = getHeldItem().tryPlaceAsBlock(rc, block, this, getHeldItemStack());
/* 5553 */         block.is_being_placed = false;
/*      */       } 
/*      */     } 
/*      */     
/* 5557 */     if (result)
/*      */     {
/* 5559 */       if (onClient()) {
/*      */         
/* 5561 */         swingArm();
/* 5562 */         Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(250);
/*      */       }
/* 5564 */       else if (!inCreativeMode()) {
/*      */ 
/*      */         
/* 5567 */         addHungerServerSide(Math.min(block.getBlockHardness(0), 20.0F) * EnchantmentHelper.getEnduranceModifier(this));
/*      */         
/* 5569 */         convertOneOfHeldItem((ItemStack)null);
/*      */       } 
/*      */     }
/*      */     
/* 5573 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setHeldItemInUse() {
/* 5579 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 5581 */     if (item_stack == null) {
/*      */       
/* 5583 */       Minecraft.setErrorMessage("setHeldItemInUse: no item held");
/*      */     }
/* 5585 */     else if (item_stack.getItemInUseAction(this) == null) {
/*      */       
/* 5587 */       Minecraft.setErrorMessage("setHeldItemInUse: item has no inUseAction");
/*      */     }
/* 5589 */     else if (item_stack != this.itemInUse) {
/*      */       
/* 5591 */       this.itemInUse = item_stack;
/* 5592 */       this.itemInUseCount = item_stack.getMaxItemUseDuration();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5597 */       if (onServer() && this.itemInUse.getItemInUseAction(this) == EnumItemInUseAction.EAT) {
/*      */         
/* 5599 */         setEating(true);
/* 5600 */         getWorldServer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(this, 5));
/*      */       } 
/*      */       
/* 5603 */       if (onServer()) {
/* 5604 */         sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.item_in_use)).setInteger(this.itemInUseCount).setEntityID(this), false);
/*      */       }
/* 5606 */       return true;
/*      */     } 
/*      */     
/* 5609 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerControllerMP getController() {
/* 5614 */     if (isLocalClient()) {
/* 5615 */       return Minecraft.theMinecraft.playerController;
/*      */     }
/* 5617 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void cancelRightClick() {
/* 5623 */     this.cancel_right_click = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean rightClickCancelled() {
/* 5628 */     return this.cancel_right_click;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearRightClickCancelled() {
/* 5633 */     this.cancel_right_click = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMineAndEditBlock(int x, int y, int z) {
/* 5638 */     return (this.worldObj.canMineBlock(this, x, y, z) && canPlayerEdit(x, y, z, getHeldItemStack()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ItemDamageResult tryDamageHeldItem(DamageSource damage_source, int amount) {
/* 5644 */     if (onClient()) {
/* 5645 */       Minecraft.setErrorMessage("tryDamageHeldItem: why calling this on client?");
/*      */     }
/* 5647 */     if (inCreativeMode())
/*      */     {
/* 5649 */       return null;
/*      */     }
/* 5651 */     ItemStack item_stack = getHeldItemStack();
/*      */     
/* 5653 */     if (item_stack == null)
/*      */     {
/* 5655 */       return null;
/*      */     }
/* 5657 */     return item_stack.tryDamageItem(damage_source, amount, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityClientPlayerMP getAsEntityClientPlayerMP() {
/* 5662 */     return (EntityClientPlayerMP)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ownsEntity(EntityOwnable entity_ownable) {
/* 5672 */     return getCommandSenderName().equalsIgnoreCase(entity_ownable.getOwnerName());
/*      */   }
/*      */ 
/*      */   
/*      */   public void suppressNextArmSwing() {
/* 5677 */     if (onServer()) {
/* 5678 */       Minecraft.setErrorMessage("suppressNextArmSwing: not meant to be called on server");
/*      */     }
/* 5680 */     this.suppress_next_arm_swing = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void suppressNextStatIncrement() {
/* 5685 */     if (onClient()) {
/* 5686 */       Minecraft.setErrorMessage("suppressNextStatIncrement: not meant to be called on client");
/*      */     }
/* 5688 */     this.suppress_next_stat_increment = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerControllerMP getPlayerController() {
/* 5693 */     return isLocalClient() ? Minecraft.theMinecraft.playerController : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMITEmigo() {
/* 5698 */     if (this.username == null) {
/* 5699 */       return false;
/*      */     }
/* 5701 */     return (this.username.equals("Roninpawn") || this.username.equals("ShadowKnight1234") || this.username.equals("xXLeGoldFishXx"));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasUsername(String username) {
/* 5706 */     if (username == null) {
/* 5707 */       return (this.username == null);
/*      */     }
/* 5709 */     return username.equals(this.username);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isZevimrgv() {
/* 5714 */     if (!this.zevimrgv_check_made) {
/*      */       
/* 5716 */       this.is_zevimrgv = "zevimrgv".equals(StringHelper.mirrorString(this.username));
/* 5717 */       this.zevimrgv_check_made = true;
/*      */     } 
/*      */     
/* 5720 */     return this.is_zevimrgv;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isZevimrgvInTournament() {
/* 5725 */     return (DedicatedServer.isTournament() && isZevimrgv());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isZevimrgv(String username) {
/* 5730 */     return "zevimrgv".equals(StringHelper.mirrorString(username));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTentativeBoundingBoxCountdownForClearing(int x, int y, int z, int countdown_for_clearing) {
/* 5741 */     Iterator<TentativeBoundingBox> i = this.tentative_bounding_boxes.iterator();
/*      */     
/* 5743 */     while (i.hasNext()) {
/*      */       
/* 5745 */       TentativeBoundingBox tbb = i.next();
/*      */       
/* 5747 */       if (tbb.matches(x, y, z)) {
/* 5748 */         tbb.countdown_for_clearing = countdown_for_clearing;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean canOnlyPerformWeakStrike() {
/* 5754 */     return (!isHoldingItemThatPreventsHandDamage() && (getHealth() < 2.0F || !hasFoodEnergy() || getEntityAttributeValue(SharedMonsterAttributes.attackDamage) < 1.0D));
/*      */   }
/*      */ 
/*      */   
/*      */   public String getSkillsString(boolean profession_names) {
/* 5759 */     return Skill.getSkillsString(getSkills(), profession_names, profession_names ? " / " : ", ");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumSkills() {
/* 5764 */     return Skill.getNumSkills(getSkills());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasSkills() {
/* 5769 */     return (getSkills() != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasSkillsForCraftingResult(CraftingResult crafting_result) {
/* 5775 */     if (!this.worldObj.areSkillsEnabled()) {
/*      */       
/* 5777 */       Minecraft.setErrorMessage("hasSkillsForCraftingResult: skills aren't enabled");
/* 5778 */       return true;
/*      */     } 
/*      */     
/* 5781 */     return hasAnyOfTheseSkillsets(crafting_result.applicable_skillsets);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWearing(ItemStack item_stack) {
/* 5786 */     return this.inventory.isWearing(item_stack);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void incrementStatForThisWorldFromClient(StatBase stat) {
/* 5792 */     if (this instanceof EntityClientPlayerMP) {
/* 5793 */       sendPacket((new Packet85SimpleSignal(EnumSignal.increment_stat_for_this_world_only)).setInteger(stat.statId));
/* 5794 */     } else if (this instanceof ServerPlayer) {
/* 5795 */       Minecraft.setErrorMessage("incrementStatForThisWorldFromClient: not meant to be called on server - use incrementStatForThisWorldOnServer instead");
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void incrementStatForThisWorldOnServer(StatBase stat) {
/* 5800 */     if (this instanceof ServerPlayer) {
/* 5801 */       getAsEntityPlayerMP().addStatForThisWorldOnly(stat, 1);
/*      */     } else {
/* 5803 */       Minecraft.setErrorMessage("incrementStatForThisWorldOnServer: not meant to be called on client - use incrementStatForThisWorldFromClient instead");
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void incrementStatForThisWorldOnServer(int id) {
/* 5808 */     incrementStatForThisWorldOnServer(StatList.getStat(id));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean haveAchievementsBeenUnlockedByOtherPlayers() {
/* 5813 */     return this.worldObj.worldInfo.haveAchievementsBeenUnlockedByOtherPlayers(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumItems(Item item) {
/* 5819 */     return this.inventory.getNumItems(item);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isPlayerInCreative() {
/* 5824 */     return inCreativeMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isUsingMemoryConnection() {
/* 5833 */     return getNetManager() instanceof MemoryConnection;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canCastFishingRod() {
/* 5838 */     if (this.fishEntity != null) {
/* 5839 */       return false;
/*      */     }
/* 5841 */     if (this.ridingEntity instanceof EntityBoat) {
/* 5842 */       return true;
/*      */     }
/* 5844 */     if (this.ridingEntity instanceof EntityHorse) {
/* 5845 */       return true;
/*      */     }
/* 5847 */     if (!this.onGround || isSuspendedInLiquid()) {
/* 5848 */       return false;
/*      */     }
/* 5850 */     if (this.worldObj.getBlockMaterial(getBlockPosX(), getHeadBlockPosY(), getBlockPosZ()).isLiquid()) {
/* 5851 */       return false;
/*      */     }
/* 5853 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInsulinResistance(int insulin_resistance) {
/* 5859 */     this.insulin_resistance = insulin_resistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInsulinResistance() {
/* 5864 */     return this.insulin_resistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumInsulinResistanceLevel getInsulinResistanceLevel() {
/* 5869 */     return this.insulin_resistance_level;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInsulinResistant() {
/* 5874 */     return (this.insulin_resistance_level != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMetabolizeFoodSugars() {
/* 5879 */     return (this.insulin_resistance_level == null || this.insulin_resistance_level.canMetabolizeFoodSugars());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean dealDamageToInventory(DamageSource damage_source, float chance_per_item, float amount, boolean include_worn_items) {
/* 5884 */     return this.inventory.takeDamage(damage_source, chance_per_item, amount, include_worn_items);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isUpperBodyInWeb() {
/* 5889 */     return this.worldObj.isMaterialInBB(this.boundingBox.copy().setMinY(getFootPosY() + 1.0D), Material.web);
/*      */   }
/*      */   
/*      */   public abstract INetworkManager getNetManager();
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */