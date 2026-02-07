/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class ServerPlayer
/*      */   extends EntityPlayer
/*      */   implements ICrafting
/*      */ {
/*   21 */   private String translator = "en_US";
/*      */ 
/*      */ 
/*      */   
/*      */   public NetServerHandler playerNetServerHandler;
/*      */ 
/*      */ 
/*      */   
/*      */   public MinecraftServer mcServer;
/*      */ 
/*      */   
/*      */   public ItemInWorldManager theItemInWorldManager;
/*      */ 
/*      */   
/*      */   public double managedPosX;
/*      */ 
/*      */   
/*      */   public double managedPosZ;
/*      */ 
/*      */   
/*   41 */   public final List loadedChunks = new LinkedList();
/*      */ 
/*      */   
/*   44 */   public final List destroyedItemsNetCache = new LinkedList();
/*   45 */   private float field_130068_bO = Float.MIN_VALUE;
/*      */ 
/*      */   
/*   48 */   private float lastHealth = -1.0E8F;
/*      */   
/*   50 */   private int last_satiation = -99999999;
/*      */ 
/*      */ 
/*      */   
/*   54 */   private int last_nutrition = -99999999;
/*      */ 
/*      */   
/*      */   private int protein;
/*      */ 
/*      */   
/*      */   private int essential_fats;
/*      */ 
/*      */   
/*      */   private int phytonutrients;
/*      */   
/*   65 */   private int last_experience = -99999999;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   72 */   private int initialInvulnerability = 0;
/*      */   
/*      */   private int renderDistance;
/*      */   
/*      */   private int chatVisibility;
/*      */   private boolean chatColours = true;
/*   78 */   private long field_143005_bX = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int currentWindowId;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean playerInventoryBeingManipulated;
/*      */ 
/*      */ 
/*      */   
/*      */   public int ping;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean playerConqueredTheEnd;
/*      */ 
/*      */ 
/*      */   
/*  100 */   public boolean[] Sr = new boolean[64];
/*      */   
/*      */   public boolean raS;
/*  103 */   protected int respawn_experience = 0;
/*      */ 
/*      */   
/*      */   private double pos_x_at_last_world_map_update;
/*      */ 
/*      */   
/*      */   private double pos_z_at_last_world_map_update;
/*      */ 
/*      */   
/*      */   public int ticks_logged_in;
/*      */   
/*      */   public int[] runegate_destination_coords;
/*      */   
/*      */   public boolean sync_client_player_on_next_tick;
/*      */   
/*      */   public int portal_grace_ticks;
/*      */   
/*      */   private boolean is_not_on_hit_list;
/*      */   
/*      */   private boolean initial_on_update = true;
/*      */   
/*      */   public boolean master_hash_received;
/*      */   
/*      */   public boolean master_hash_validated;
/*      */   
/*      */   public int sacred_stones_placed;
/*      */   
/*  130 */   public int allotted_time = -1;
/*      */ 
/*      */   
/*      */   public boolean is_disconnecting_while_in_bed;
/*      */ 
/*      */   
/*      */   public boolean try_push_out_of_blocks;
/*      */ 
/*      */   
/*      */   public int last_skill_learned_on_day;
/*      */   
/*  141 */   public short respawn_countdown = -1;
/*      */   
/*  143 */   public List referenced_books_read = new ArrayList();
/*      */ 
/*      */   
/*      */   public float vision_dimming_on_client;
/*      */ 
/*      */   
/*      */   public long prevent_item_pickup_due_to_held_item_breaking_until;
/*      */ 
/*      */   
/*      */   public double last_received_motion_x;
/*      */   
/*      */   public double last_received_motion_z;
/*      */   
/*      */   public boolean set_position_in_bed_next_tick;
/*      */ 
/*      */   
/*      */   public ServerPlayer(MinecraftServer par1MinecraftServer, World par2World, String par3Str, ItemInWorldManager par4ItemInWorldManager) {
/*  160 */     super(par2World, par3Str);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  170 */     this.sync_client_player_on_next_tick = true;
/*      */     
/*  172 */     par4ItemInWorldManager.thisPlayerMP = this;
/*  173 */     this.theItemInWorldManager = par4ItemInWorldManager;
/*  174 */     this.renderDistance = par1MinecraftServer.getConfigurationManager().getViewDistance();
/*  175 */     ChunkCoordinates var5 = par2World.getSpawnPoint();
/*  176 */     int var6 = var5.posX;
/*  177 */     int var7 = var5.posZ;
/*  178 */     int var8 = var5.posY;
/*      */     
/*  180 */     if (!par2World.provider.hasNoSky && par2World.getWorldInfo().getGameType() != EnumGameType.ADVENTURE) {
/*      */       
/*  182 */       int var9 = Math.max(5, par1MinecraftServer.getSpawnProtectionSize() - 6);
/*  183 */       var6 += this.rand.nextInt(var9 * 2) - var9;
/*  184 */       var7 += this.rand.nextInt(var9 * 2) - var9;
/*      */       
/*  186 */       var8 = par2World.getTopSolidOrLiquidBlockMITE(var6, var7, true);
/*      */     } 
/*      */     
/*  189 */     this.mcServer = par1MinecraftServer;
/*  190 */     this.stepHeight = 0.0F;
/*  191 */     this.yOffset = 0.0F;
/*  192 */     setLocationAndAngles(var6 + 0.5D, var8, var7 + 0.5D, 0.0F, 0.0F);
/*      */     
/*  194 */     while (!par2World.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty())
/*      */     {
/*  196 */       setPosition(this.posX, this.posY + 1.0D, this.posZ);
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
/*  207 */     this.protein = this.essential_fats = this.phytonutrients = 160000;
/*      */     
/*  209 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*  210 */       DedicatedServer.getOrCreateTournamentStanding(this);
/*      */     }
/*      */     
/*  213 */     if (DedicatedServer.isTournamentThatUsesAllottedTimes()) {
/*  214 */       this.allotted_time = DedicatedServer.allotted_time;
/*      */     }
/*      */   }
/*      */   
/*      */   private int calcChecksum(int for_release_number) {
/*  219 */     int checksum = 0;
/*      */     
/*  221 */     checksum += this.inventory.calcChecksum(for_release_number) * 2;
/*  222 */     checksum = (int)(checksum + getHealth() * 3.0F);
/*  223 */     checksum += this.dimension * 5;
/*  224 */     checksum += this.experience * 7;
/*  225 */     checksum += getSatiation() * 11;
/*  226 */     checksum += getNutrition() * 13;
/*  227 */     checksum = (int)(checksum + this.posX * 17.0D);
/*  228 */     checksum = (int)(checksum + this.posY * 19.0D);
/*  229 */     checksum = (int)(checksum + this.posZ * 23.0D);
/*      */     
/*  231 */     checksum += this.respawn_experience * 29;
/*  232 */     checksum += this.theItemInWorldManager.getGameType().getID() * 31;
/*      */ 
/*      */     
/*  235 */     checksum *= 657;
/*      */     
/*  237 */     checksum = Integer.MAX_VALUE - Math.abs(checksum);
/*      */     
/*  239 */     return Math.abs(checksum);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  247 */     super.readEntityFromNBT(par1NBTTagCompound);
/*      */     
/*  249 */     this.respawn_experience = par1NBTTagCompound.getInteger("respawn_experience");
/*      */     
/*  251 */     if (par1NBTTagCompound.hasKey("playerGameType"))
/*      */     {
/*  253 */       if (MinecraftServer.getServer().getForceGamemode()) {
/*      */         
/*  255 */         this.theItemInWorldManager.setGameType(MinecraftServer.getServer().getGameType());
/*      */       }
/*      */       else {
/*      */         
/*  259 */         this.theItemInWorldManager.setGameType(EnumGameType.getByID(par1NBTTagCompound.getInteger("playerGameType")));
/*      */       } 
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
/*  286 */     if (par1NBTTagCompound.hasKey("LMRN")) {
/*      */       
/*  288 */       int LMRN = par1NBTTagCompound.getInteger("LMRN");
/*      */       
/*  290 */       if (LMRN < 81) {
/*  291 */         MinecraftServer.setTreacheryDetected();
/*      */       }
/*  293 */       int checksum = par1NBTTagCompound.getInteger(obf("mzmlgrnv"));
/*      */ 
/*      */ 
/*      */       
/*  297 */       if (Minecraft.inDevMode()) {
/*  298 */         System.out.println(obf("Kozbvi MYG xsvxphfn ezorwzgrlm, xlnkzirmt ") + checksum + " vs " + calcChecksum(LMRN) + " (R" + LMRN + " used)");
/*      */       }
/*  300 */       if (checksum != calcChecksum(LMRN)) {
/*  301 */         MinecraftServer.setTreacheryDetected();
/*      */       }
/*  303 */     } else if (Minecraft.inDevMode()) {
/*      */       
/*  305 */       System.out.println(obf("Hprkkrmt kozbvi xsvxphfn ezorwzgrlm (ONIM mlg ulfmw)"));
/*      */     }
/*      */     else {
/*      */       
/*  309 */       MinecraftServer.setTreacheryDetected();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  315 */     if (par1NBTTagCompound.hasKey("protein")) {
/*  316 */       this.protein = par1NBTTagCompound.getInteger("protein");
/*      */     } else {
/*  318 */       this.protein = 160000;
/*      */     } 
/*  320 */     if (par1NBTTagCompound.hasKey("essential_fats")) {
/*  321 */       this.essential_fats = par1NBTTagCompound.getInteger("essential_fats");
/*      */     } else {
/*  323 */       this.essential_fats = 160000;
/*      */     } 
/*  325 */     if (par1NBTTagCompound.hasKey("phytonutrients")) {
/*  326 */       this.phytonutrients = par1NBTTagCompound.getInteger("phytonutrients");
/*      */     } else {
/*  328 */       this.phytonutrients = 160000;
/*      */     } 
/*  330 */     setInsulinResistance(par1NBTTagCompound.getInteger("insulin_resistance"));
/*  331 */     this.insulin_resistance_level = EnumInsulinResistanceLevel.getByTransmittedOrdinal(par1NBTTagCompound.getByte("insulin_resistance_level"));
/*      */     
/*  333 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*  334 */       (DedicatedServer.getOrCreateTournamentStanding(this).readFromNBT(par1NBTTagCompound)).experience = this.experience;
/*      */     }
/*  336 */     this.sacred_stones_placed = par1NBTTagCompound.getInteger("sacred_stones_placed");
/*      */     
/*  338 */     if (DedicatedServer.isTournamentThatUsesAllottedTimes()) {
/*      */       
/*  340 */       this.allotted_time = par1NBTTagCompound.getInteger("allotted_time");
/*  341 */       this.allotted_time = (int)(this.allotted_time + (this.worldObj.getTotalWorldTime() - par1NBTTagCompound.getLong("last_total_world_time")) / 3L);
/*      */       
/*  343 */       if (this.allotted_time > DedicatedServer.allotted_time) {
/*  344 */         this.allotted_time = DedicatedServer.allotted_time;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  349 */     if (par1NBTTagCompound.getBoolean("disconnected_while_in_bed")) {
/*      */       
/*  351 */       this.pos_x_before_bed = par1NBTTagCompound.getDouble("pos_x_before_bed");
/*  352 */       this.pos_y_before_bed = par1NBTTagCompound.getDouble("pos_y_before_bed");
/*  353 */       this.pos_z_before_bed = par1NBTTagCompound.getDouble("pos_z_before_bed");
/*      */       
/*  355 */       setPosition(this.pos_x_before_bed, this.pos_y_before_bed, this.pos_z_before_bed);
/*      */ 
/*      */       
/*  358 */       this.try_push_out_of_blocks = true;
/*      */     } 
/*      */     
/*  361 */     setSkills(par1NBTTagCompound.getInteger("skills"));
/*  362 */     this.last_skill_learned_on_day = par1NBTTagCompound.getInteger("last_skill_learned_on_day");
/*      */     
/*  364 */     if (par1NBTTagCompound.hasKey("respawn_countdown")) {
/*  365 */       this.respawn_countdown = par1NBTTagCompound.getShort("respawn_countdown");
/*      */     } else {
/*  367 */       this.respawn_countdown = 120;
/*      */     } 
/*  369 */     if (par1NBTTagCompound.hasKey("stats")) {
/*  370 */       readStatsFromNBT(par1NBTTagCompound.getCompoundTag("stats"));
/*      */     }
/*  372 */     if (par1NBTTagCompound.hasKey("referenced_books_read")) {
/*      */       
/*  374 */       int[] rbr = par1NBTTagCompound.getIntArray("referenced_books_read");
/*      */       
/*  376 */       this.referenced_books_read.clear();
/*      */       
/*  378 */       for (int i = 0; i < rbr.length; i++) {
/*  379 */         this.referenced_books_read.add(Integer.valueOf(rbr[i]));
/*      */       }
/*      */     } 
/*  382 */     if (par1NBTTagCompound.hasKey("vision_dimming_on_client")) {
/*  383 */       this.vision_dimming = par1NBTTagCompound.getFloat("vision_dimming_on_client");
/*      */     }
/*  385 */     if (par1NBTTagCompound.hasKey("ticks_since_portal_teleport")) {
/*  386 */       this.ticks_since_portal_teleport = par1NBTTagCompound.getShort("ticks_since_portal_teleport");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  394 */     super.writeEntityToNBT(par1NBTTagCompound);
/*      */     
/*  396 */     par1NBTTagCompound.setInteger("respawn_experience", this.respawn_experience);
/*      */     
/*  398 */     par1NBTTagCompound.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
/*      */     
/*  400 */     par1NBTTagCompound.setInteger("LMRN", 196);
/*  401 */     par1NBTTagCompound.setInteger("nanotime", calcChecksum(196));
/*      */ 
/*      */ 
/*      */     
/*  405 */     par1NBTTagCompound.setInteger("protein", this.protein);
/*  406 */     par1NBTTagCompound.setInteger("essential_fats", this.essential_fats);
/*  407 */     par1NBTTagCompound.setInteger("phytonutrients", this.phytonutrients);
/*      */     
/*  409 */     par1NBTTagCompound.setInteger("insulin_resistance", getInsulinResistance());
/*      */     
/*  411 */     if (isInsulinResistant()) {
/*  412 */       par1NBTTagCompound.setByte("insulin_resistance_level", (byte)getInsulinResistanceLevel().getOrdinalForTransmission());
/*      */     }
/*  414 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*  415 */       DedicatedServer.getOrCreateTournamentStanding(this).writeToNBT(par1NBTTagCompound);
/*      */     }
/*  417 */     par1NBTTagCompound.setInteger("sacred_stones_placed", this.sacred_stones_placed);
/*      */     
/*  419 */     par1NBTTagCompound.setInteger("allotted_time", this.allotted_time);
/*  420 */     par1NBTTagCompound.setLong("last_total_world_time", this.worldObj.getTotalWorldTime());
/*      */     
/*  422 */     par1NBTTagCompound.setBoolean("disconnected_while_in_bed", this.is_disconnecting_while_in_bed);
/*      */     
/*  424 */     if (this.is_disconnecting_while_in_bed) {
/*      */       
/*  426 */       par1NBTTagCompound.setDouble("pos_x_before_bed", this.pos_x_before_bed);
/*  427 */       par1NBTTagCompound.setDouble("pos_y_before_bed", this.pos_y_before_bed);
/*  428 */       par1NBTTagCompound.setDouble("pos_z_before_bed", this.pos_z_before_bed);
/*      */     } 
/*      */     
/*  431 */     if (hasSkills()) {
/*  432 */       par1NBTTagCompound.setInteger("skills", getSkills());
/*      */     }
/*  434 */     if (this.last_skill_learned_on_day != 0) {
/*  435 */       par1NBTTagCompound.setInteger("last_skill_learned_on_day", this.last_skill_learned_on_day);
/*      */     }
/*  437 */     par1NBTTagCompound.setShort("respawn_countdown", this.respawn_countdown);
/*      */     
/*  439 */     if (!par1NBTTagCompound.hasKey("stats")) {
/*  440 */       par1NBTTagCompound.setTag("stats", new NBTTagCompound());
/*      */     }
/*  442 */     writeStatsToNBT(par1NBTTagCompound.getCompoundTag("stats"));
/*      */     
/*  444 */     if (this.referenced_books_read.size() > 0) {
/*      */       
/*  446 */       int[] rbr = new int[this.referenced_books_read.size()];
/*      */       
/*  448 */       for (int i = 0; i < rbr.length; i++) {
/*  449 */         rbr[i] = ((Integer)this.referenced_books_read.get(i)).intValue();
/*      */       }
/*  451 */       par1NBTTagCompound.setIntArray("referenced_books_read", rbr);
/*      */     } 
/*      */     
/*  454 */     if (this.vision_dimming_on_client >= 0.2F) {
/*  455 */       par1NBTTagCompound.setFloat("vision_dimming_on_client", this.vision_dimming_on_client);
/*      */     }
/*  457 */     if (this.ticks_since_portal_teleport < 24000) {
/*  458 */       par1NBTTagCompound.setShort("ticks_since_portal_teleport", (short)this.ticks_since_portal_teleport);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean hasReadReferencedBook(int index) {
/*  463 */     return this.referenced_books_read.contains(Integer.valueOf(index));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToReferencedBooksRead(ItemStack item_stack) {
/*  469 */     int index = ItemReferencedBook.getReferenceIndex(item_stack);
/*      */     
/*  471 */     if (!hasReadReferencedBook(index)) {
/*      */       
/*  473 */       this.referenced_books_read.add(Integer.valueOf(index));
/*      */       
/*  475 */       int xp_reward = ItemReferencedBook.getXPReward(item_stack);
/*      */       
/*  477 */       if (xp_reward != 0) {
/*  478 */         addExperience(xp_reward);
/*      */       }
/*  480 */       boolean has_read_the_collected_works_of_father_phoonzang = true;
/*      */       
/*  482 */       for (int i = 1; i <= 9; i++) {
/*      */         
/*  484 */         if (!hasReadReferencedBook(i)) {
/*      */           
/*  486 */           has_read_the_collected_works_of_father_phoonzang = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  491 */       if (has_read_the_collected_works_of_father_phoonzang) {
/*  492 */         triggerAchievement(AchievementList.enlightenment);
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
/*      */   public void addSelfToInternalCraftingInventory() {
/*  507 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetHeight() {
/*  515 */     this.yOffset = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/*  520 */     if (isSneaking()) {
/*  521 */       return 1.3815F;
/*      */     }
/*  523 */     return 1.62F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getFootPosY() {
/*  529 */     return this.posY;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getEyePosY() {
/*  535 */     return this.posY + getEyeHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   public void decrementNutrients() {
/*  540 */     if (inCreativeMode()) {
/*      */       return;
/*      */     }
/*  543 */     if (this.protein > 0) {
/*  544 */       this.protein--;
/*      */     }
/*  546 */     if (this.essential_fats > 0) {
/*  547 */       this.essential_fats--;
/*      */     }
/*  549 */     if (this.phytonutrients > 0) {
/*  550 */       this.phytonutrients--;
/*      */     }
/*      */   }
/*      */   
/*      */   public void decrementInsulinResistance() {
/*  555 */     if (inCreativeMode()) {
/*      */       return;
/*      */     }
/*  558 */     int insulin_resistance = getInsulinResistance();
/*      */     
/*  560 */     if (insulin_resistance > 0) {
/*  561 */       setInsulinResistance(--insulin_resistance);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setProtein(int protein) {
/*  566 */     this.protein = MathHelper.clamp_int(protein, 0, 160000);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEssentialFats(int essential_fats) {
/*  571 */     this.essential_fats = MathHelper.clamp_int(essential_fats, 0, 160000);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPhytonutrients(int phytonutrients) {
/*  576 */     this.phytonutrients = MathHelper.clamp_int(phytonutrients, 0, 160000);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInsulinResistance(int insulin_resistance) {
/*  582 */     insulin_resistance = MathHelper.clamp_int(insulin_resistance, 0, 192000);
/*      */     
/*  584 */     super.setInsulinResistance(insulin_resistance);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  610 */     if (insulin_resistance == 0) {
/*      */       
/*  612 */       this.insulin_resistance_level = null;
/*      */     }
/*      */     else {
/*      */       
/*  616 */       EnumInsulinResistanceLevel insulin_resistance_level = EnumInsulinResistanceLevel.getInsulinResistanceLevel(insulin_resistance);
/*      */       
/*  618 */       if (insulin_resistance_level == null) {
/*      */         
/*  620 */         if (this.insulin_resistance_level != null) {
/*  621 */           this.insulin_resistance_level = EnumInsulinResistanceLevel.mild;
/*      */         }
/*  623 */       } else if (this.insulin_resistance_level == null) {
/*      */         
/*  625 */         this.insulin_resistance_level = insulin_resistance_level;
/*      */       }
/*  627 */       else if (this.insulin_resistance_level.isLessSevereThan(insulin_resistance_level)) {
/*      */         
/*  629 */         this.insulin_resistance_level = insulin_resistance_level;
/*      */       }
/*  631 */       else if (this.insulin_resistance_level.isMoreSevereThan(insulin_resistance_level)) {
/*      */         
/*  633 */         this.insulin_resistance_level = insulin_resistance_level.getNext();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getProtein() {
/*  640 */     return this.protein;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEssentialFats() {
/*  645 */     return this.essential_fats;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPhytonutrients() {
/*  650 */     return this.phytonutrients;
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
/*      */   public void addNutrients(Item item) {
/*  667 */     if (item == Item.seeds) {
/*  668 */       addEssentialFats(2000);
/*      */     }
/*  670 */     addProtein(item.getProtein());
/*  671 */     addEssentialFats(item.getEssentialFats());
/*  672 */     addPhytonutrients(item.getPhytonutrients());
/*      */   }
/*      */ 
/*      */   
/*      */   public void addProtein(int protein) {
/*  677 */     setProtein(this.protein + protein);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEssentialFats(int essential_fats) {
/*  682 */     setEssentialFats(this.essential_fats + essential_fats);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addPhytonutrients(int phytonutrients) {
/*  687 */     setPhytonutrients(this.phytonutrients + phytonutrients);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addInsulinResistance(int insulin_resistance) {
/*  692 */     setInsulinResistance(getInsulinResistance() + insulin_resistance);
/*      */     
/*  694 */     if (insulin_resistance > 0 && isInsulinResistant()) {
/*      */       
/*  696 */       addPotionEffect(new PotionEffect(Potion.confusion.id, 400, getInsulinResistanceLevel().ordinal()));
/*      */       
/*  698 */       if (getInsulinResistanceLevel().isSevere()) {
/*  699 */         addPotionEffect(new PotionEffect(Potion.poison.id, Math.max((int)(insulin_resistance / 48.0F), 100), 0));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected int pushOutOfBlocks() {
/*  705 */     int result = super.pushOutOfBlocks();
/*      */     
/*  707 */     if (result == 1) {
/*  708 */       setPositionAndUpdate(this.posX, this.posY, this.posZ);
/*      */     }
/*  710 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRespawnCountdown() {
/*  719 */     if (this.respawn_countdown > 0 && this.worldObj.getTotalWorldTime() % 20L == 0L) {
/*  720 */       this.respawn_countdown = (short)(this.respawn_countdown - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendAllStatsToClient() {
/*  728 */     Iterator<Map.Entry> i = this.stats.entrySet().iterator();
/*      */     
/*  730 */     while (i.hasNext()) {
/*      */       
/*  732 */       Map.Entry entry = i.next();
/*      */       
/*  734 */       int id = ((Integer)entry.getKey()).intValue();
/*      */       
/*  736 */       StatBase stat = StatList.getStat(id);
/*      */       
/*  738 */       if (StatList.isEitherZeroOrOne(stat)) {
/*  739 */         sendPacket(new Packet91PlayerStat(stat, ((Byte)entry.getValue()).byteValue())); continue;
/*  740 */       }  if (StatList.hasLongValue(stat)) {
/*  741 */         sendPacket(new Packet91PlayerStat(stat, ((Long)entry.getValue()).longValue())); continue;
/*      */       } 
/*  743 */       sendPacket(new Packet91PlayerStat(stat, ((Integer)entry.getValue()).intValue()));
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
/*      */   private void tickPlayerInventory() {
/*  782 */     boolean players_eyes_inside_water = isInsideOfMaterial(Material.water);
/*  783 */     boolean steam_and_hiss = false;
/*      */     
/*  785 */     for (int i = 0; i < this.inventory.mainInventory.length; i++) {
/*      */       
/*  787 */       ItemStack item_stack = this.inventory.getInventorySlotContents(i);
/*      */       
/*  789 */       if (item_stack != null) {
/*      */ 
/*      */         
/*  792 */         Item item = item_stack.getItem();
/*      */         
/*  794 */         if (players_eyes_inside_water)
/*      */         {
/*  796 */           if (item instanceof ItemVessel)
/*      */           {
/*  798 */             if (item instanceof ItemBucket) {
/*      */               
/*  800 */               ItemBucket bucket = (ItemBucket)item;
/*      */               
/*  802 */               if (bucket.contains(Material.lava)) {
/*      */                 
/*  804 */                 this.inventory.convertAllItemsInSlot(i, bucket.getPeerForContents(Material.stone));
/*  805 */                 steam_and_hiss = true;
/*      */               } 
/*      */             } 
/*      */           }
/*      */         }
/*      */       } 
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
/*  823 */     if (steam_and_hiss) {
/*  824 */       entityFX(EnumEntityFX.steam_with_hiss);
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean isUnderworldBottomBedrockVisible() {
/*  829 */     if (!this.worldObj.isUnderworld() || getFootBlockPosY() > 20) {
/*  830 */       return false;
/*      */     }
/*  832 */     int raycast_seed_offset = this.raycast_seed_offset;
/*  833 */     this.raycast_seed_offset = this.worldObj.getTimeOfDay();
/*      */     
/*  835 */     boolean result = false;
/*      */     
/*  837 */     for (int i = 0; i < 1; i++) {
/*      */       
/*  839 */       float rotationYaw = this.rotationYaw;
/*  840 */       float rotationPitch = this.rotationPitch;
/*      */       
/*  842 */       this.rotationYaw = (float)(this.rotationYaw + Math.random() * 181.0D - 90.0D);
/*  843 */       this.rotationPitch = (float)(this.rotationPitch + Math.random() * 181.0D - 90.0D);
/*      */       
/*  845 */       Raycast raycast = new Raycast(this, 1.0F, 16.0D);
/*      */       
/*  847 */       raycast.setForVision(false);
/*      */       
/*  849 */       RaycastCollision rc = raycast.performVsBlocksSingle().getBlockCollision();
/*      */       
/*  851 */       this.rotationYaw = rotationYaw;
/*  852 */       this.rotationPitch = rotationPitch;
/*      */       
/*  854 */       if (rc != null && rc.isBlock() && rc.getBlockHit() == Block.mantleOrCore && rc.block_hit_y < 5) {
/*      */         
/*  856 */         result = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  861 */     this.raycast_seed_offset = raycast_seed_offset;
/*      */     
/*  863 */     return result;
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
/*      */   public void onUpdate() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield in_test_mode : Z
/*      */     //   4: ifeq -> 335
/*      */     //   7: aload_0
/*      */     //   8: getfield ticksExisted : I
/*      */     //   11: bipush #20
/*      */     //   13: irem
/*      */     //   14: ifne -> 169
/*      */     //   17: aload_0
/*      */     //   18: getfield worldObj : Lnet/minecraft/World;
/*      */     //   21: aload_0
/*      */     //   22: new net/minecraft/Packet85SimpleSignal
/*      */     //   25: dup
/*      */     //   26: getstatic net/minecraft/EnumSignal.boolean_test : Lnet/minecraft/EnumSignal;
/*      */     //   29: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   32: iconst_1
/*      */     //   33: invokevirtual setBoolean : (Z)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   36: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   39: aload_0
/*      */     //   40: getfield worldObj : Lnet/minecraft/World;
/*      */     //   43: aload_0
/*      */     //   44: new net/minecraft/Packet85SimpleSignal
/*      */     //   47: dup
/*      */     //   48: getstatic net/minecraft/EnumSignal.byte_test : Lnet/minecraft/EnumSignal;
/*      */     //   51: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   54: iconst_3
/*      */     //   55: invokevirtual setByte : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   58: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   61: aload_0
/*      */     //   62: getfield worldObj : Lnet/minecraft/World;
/*      */     //   65: aload_0
/*      */     //   66: new net/minecraft/Packet85SimpleSignal
/*      */     //   69: dup
/*      */     //   70: getstatic net/minecraft/EnumSignal.short_test : Lnet/minecraft/EnumSignal;
/*      */     //   73: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   76: bipush #42
/*      */     //   78: invokevirtual setShort : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   81: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   84: aload_0
/*      */     //   85: getfield worldObj : Lnet/minecraft/World;
/*      */     //   88: aload_0
/*      */     //   89: new net/minecraft/Packet85SimpleSignal
/*      */     //   92: dup
/*      */     //   93: getstatic net/minecraft/EnumSignal.integer_test : Lnet/minecraft/EnumSignal;
/*      */     //   96: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   99: bipush #101
/*      */     //   101: invokevirtual setInteger : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   104: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   107: aload_0
/*      */     //   108: getfield worldObj : Lnet/minecraft/World;
/*      */     //   111: aload_0
/*      */     //   112: new net/minecraft/Packet85SimpleSignal
/*      */     //   115: dup
/*      */     //   116: getstatic net/minecraft/EnumSignal.float_test : Lnet/minecraft/EnumSignal;
/*      */     //   119: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   122: ldc_w 0.2
/*      */     //   125: invokevirtual setFloat : (F)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   128: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   131: aload_0
/*      */     //   132: getfield worldObj : Lnet/minecraft/World;
/*      */     //   135: aload_0
/*      */     //   136: new net/minecraft/Packet85SimpleSignal
/*      */     //   139: dup
/*      */     //   140: getstatic net/minecraft/EnumSignal.complex_test : Lnet/minecraft/EnumSignal;
/*      */     //   143: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   146: iconst_3
/*      */     //   147: invokevirtual setByte : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   150: bipush #42
/*      */     //   152: invokevirtual setShort : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   155: bipush #101
/*      */     //   157: invokevirtual setInteger : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   160: ldc_w 0.2
/*      */     //   163: invokevirtual setFloat : (F)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   166: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   169: aload_0
/*      */     //   170: getfield ticksExisted : I
/*      */     //   173: bipush #20
/*      */     //   175: irem
/*      */     //   176: ifne -> 216
/*      */     //   179: aload_0
/*      */     //   180: getfield worldObj : Lnet/minecraft/World;
/*      */     //   183: aload_0
/*      */     //   184: new net/minecraft/Packet85SimpleSignal
/*      */     //   187: dup
/*      */     //   188: getstatic net/minecraft/EnumSignal.approx_pos_test : Lnet/minecraft/EnumSignal;
/*      */     //   191: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   194: aload_0
/*      */     //   195: getfield posX : D
/*      */     //   198: aload_0
/*      */     //   199: getfield posY : D
/*      */     //   202: ldc2_w 2.0
/*      */     //   205: dadd
/*      */     //   206: aload_0
/*      */     //   207: getfield posZ : D
/*      */     //   210: invokevirtual setApproxPosition : (DDD)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   213: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   216: aload_0
/*      */     //   217: getfield ticksExisted : I
/*      */     //   220: bipush #20
/*      */     //   222: irem
/*      */     //   223: bipush #10
/*      */     //   225: if_icmpne -> 265
/*      */     //   228: aload_0
/*      */     //   229: getfield worldObj : Lnet/minecraft/World;
/*      */     //   232: aload_0
/*      */     //   233: new net/minecraft/Packet85SimpleSignal
/*      */     //   236: dup
/*      */     //   237: getstatic net/minecraft/EnumSignal.exact_pos_test : Lnet/minecraft/EnumSignal;
/*      */     //   240: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   243: aload_0
/*      */     //   244: getfield posX : D
/*      */     //   247: aload_0
/*      */     //   248: getfield posY : D
/*      */     //   251: ldc2_w 2.0
/*      */     //   254: dadd
/*      */     //   255: aload_0
/*      */     //   256: getfield posZ : D
/*      */     //   259: invokevirtual setExactPosition : (DDD)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   262: invokevirtual sendPacketToAllAssociatedPlayers : (Lnet/minecraft/Entity;Lnet/minecraft/Packet;)V
/*      */     //   265: aload_0
/*      */     //   266: getfield ticksExisted : I
/*      */     //   269: bipush #60
/*      */     //   271: irem
/*      */     //   272: ifne -> 299
/*      */     //   275: aload_0
/*      */     //   276: getfield worldObj : Lnet/minecraft/World;
/*      */     //   279: getstatic net/minecraft/EnumBlockFX.lava_mixing_with_water : Lnet/minecraft/EnumBlockFX;
/*      */     //   282: aload_0
/*      */     //   283: invokevirtual getBlockPosX : ()I
/*      */     //   286: aload_0
/*      */     //   287: invokevirtual getFootBlockPosY : ()I
/*      */     //   290: iconst_1
/*      */     //   291: isub
/*      */     //   292: aload_0
/*      */     //   293: invokevirtual getBlockPosZ : ()I
/*      */     //   296: invokevirtual blockFX : (Lnet/minecraft/EnumBlockFX;III)V
/*      */     //   299: aload_0
/*      */     //   300: getfield ticksExisted : I
/*      */     //   303: bipush #20
/*      */     //   305: irem
/*      */     //   306: ifne -> 316
/*      */     //   309: aload_0
/*      */     //   310: getstatic net/minecraft/EnumEntityFX.curse_effect_learned : Lnet/minecraft/EnumEntityFX;
/*      */     //   313: invokevirtual entityFX : (Lnet/minecraft/EnumEntityFX;)V
/*      */     //   316: aload_0
/*      */     //   317: getfield ticksExisted : I
/*      */     //   320: sipush #200
/*      */     //   323: irem
/*      */     //   324: ifne -> 335
/*      */     //   327: aload_0
/*      */     //   328: getstatic net/minecraft/Item.ingotCopper : Lnet/minecraft/Item;
/*      */     //   331: iconst_m1
/*      */     //   332: invokevirtual causeBreakingItemEffect : (Lnet/minecraft/Item;I)V
/*      */     //   335: aload_0
/*      */     //   336: getfield set_position_in_bed_next_tick : Z
/*      */     //   339: ifeq -> 362
/*      */     //   342: aload_0
/*      */     //   343: invokevirtual inBed : ()Z
/*      */     //   346: ifeq -> 357
/*      */     //   349: aload_0
/*      */     //   350: invokevirtual setSizeProne : ()V
/*      */     //   353: aload_0
/*      */     //   354: invokevirtual setPositionAndRotationInBed : ()V
/*      */     //   357: aload_0
/*      */     //   358: iconst_0
/*      */     //   359: putfield set_position_in_bed_next_tick : Z
/*      */     //   362: aload_0
/*      */     //   363: getfield try_push_out_of_blocks : Z
/*      */     //   366: ifeq -> 390
/*      */     //   369: aload_0
/*      */     //   370: getfield ticksExisted : I
/*      */     //   373: iconst_1
/*      */     //   374: if_icmple -> 390
/*      */     //   377: aload_0
/*      */     //   378: invokevirtual pushOutOfBlocks : ()I
/*      */     //   381: iconst_m1
/*      */     //   382: if_icmpne -> 385
/*      */     //   385: aload_0
/*      */     //   386: iconst_0
/*      */     //   387: putfield try_push_out_of_blocks : Z
/*      */     //   390: aload_0
/*      */     //   391: getfield username : Ljava/lang/String;
/*      */     //   394: ifnull -> 412
/*      */     //   397: aload_0
/*      */     //   398: invokevirtual isZevimrgvInTournament : ()Z
/*      */     //   401: ifeq -> 412
/*      */     //   404: aload_0
/*      */     //   405: getfield capabilities : Lnet/minecraft/PlayerCapabilities;
/*      */     //   408: iconst_1
/*      */     //   409: putfield allowFlying : Z
/*      */     //   412: invokestatic inDevMode : ()Z
/*      */     //   415: ifne -> 425
/*      */     //   418: aload_0
/*      */     //   419: invokevirtual isZevimrgvInTournament : ()Z
/*      */     //   422: ifeq -> 469
/*      */     //   425: aload_0
/*      */     //   426: getfield mcServer : Lnet/minecraft/server/MinecraftServer;
/*      */     //   429: invokevirtual getLoadOnServer : ()F
/*      */     //   432: ldc_w 100.0
/*      */     //   435: fmul
/*      */     //   436: f2i
/*      */     //   437: istore_1
/*      */     //   438: aload_0
/*      */     //   439: new net/minecraft/Packet85SimpleSignal
/*      */     //   442: dup
/*      */     //   443: getstatic net/minecraft/EnumSignal.server_load : Lnet/minecraft/EnumSignal;
/*      */     //   446: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   449: iload_1
/*      */     //   450: sipush #1000
/*      */     //   453: if_icmpge -> 460
/*      */     //   456: iload_1
/*      */     //   457: goto -> 463
/*      */     //   460: sipush #1000
/*      */     //   463: invokevirtual setShort : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   466: invokevirtual sendPacket : (Lnet/minecraft/Packet;)V
/*      */     //   469: aload_0
/*      */     //   470: getfield initial_on_update : Z
/*      */     //   473: ifeq -> 512
/*      */     //   476: getstatic net/minecraft/DedicatedServer.tournament_type : Lnet/minecraft/EnumTournamentType;
/*      */     //   479: getstatic net/minecraft/EnumTournamentType.score : Lnet/minecraft/EnumTournamentType;
/*      */     //   482: if_acmpne -> 497
/*      */     //   485: aload_0
/*      */     //   486: invokestatic generatePrizeKeyFile : (Lnet/minecraft/ServerPlayer;)V
/*      */     //   489: aload_0
/*      */     //   490: iconst_0
/*      */     //   491: invokestatic updateTournamentScoreOnClient : (Lnet/minecraft/EntityPlayer;Z)V
/*      */     //   494: goto -> 507
/*      */     //   497: invokestatic isTournament : ()Z
/*      */     //   500: ifeq -> 507
/*      */     //   503: aload_0
/*      */     //   504: invokestatic generatePrizeKeyFile : (Lnet/minecraft/ServerPlayer;)V
/*      */     //   507: aload_0
/*      */     //   508: iconst_0
/*      */     //   509: putfield initial_on_update : Z
/*      */     //   512: aload_0
/*      */     //   513: getfield ticksExisted : I
/*      */     //   516: bipush #24
/*      */     //   518: irem
/*      */     //   519: ifne -> 553
/*      */     //   522: aload_0
/*      */     //   523: getfield username : Ljava/lang/String;
/*      */     //   526: invokestatic getSoonestReconnectionTime : (Ljava/lang/String;)Lnet/minecraft/SoonestReconnectionTime;
/*      */     //   529: astore_1
/*      */     //   530: aload_1
/*      */     //   531: ifnull -> 553
/*      */     //   534: aload_1
/*      */     //   535: getfield ticks_disconnected : J
/*      */     //   538: lconst_0
/*      */     //   539: lcmp
/*      */     //   540: ifle -> 553
/*      */     //   543: aload_1
/*      */     //   544: dup
/*      */     //   545: getfield ticks_disconnected : J
/*      */     //   548: lconst_1
/*      */     //   549: lsub
/*      */     //   550: putfield ticks_disconnected : J
/*      */     //   553: aload_0
/*      */     //   554: getfield master_hash_received : Z
/*      */     //   557: ifne -> 657
/*      */     //   560: aload_0
/*      */     //   561: getfield ticks_logged_in : I
/*      */     //   564: bipush #20
/*      */     //   566: if_icmplt -> 657
/*      */     //   569: aload_0
/*      */     //   570: getfield ticks_logged_in : I
/*      */     //   573: bipush #20
/*      */     //   575: irem
/*      */     //   576: ifne -> 657
/*      */     //   579: aload_0
/*      */     //   580: getfield ticks_logged_in : I
/*      */     //   583: sipush #1000
/*      */     //   586: if_icmpge -> 617
/*      */     //   589: aload_0
/*      */     //   590: new net/minecraft/Packet85SimpleSignal
/*      */     //   593: dup
/*      */     //   594: getstatic net/minecraft/EnumSignal.mh : Lnet/minecraft/EnumSignal;
/*      */     //   597: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   600: aload_0
/*      */     //   601: getfield worldObj : Lnet/minecraft/World;
/*      */     //   604: invokevirtual getSeed : ()J
/*      */     //   607: l2i
/*      */     //   608: invokevirtual setInteger : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   611: invokevirtual sendPacket : (Lnet/minecraft/Packet;)V
/*      */     //   614: goto -> 657
/*      */     //   617: aload_0
/*      */     //   618: getfield mcServer : Lnet/minecraft/server/MinecraftServer;
/*      */     //   621: invokevirtual getLogAgent : ()Lnet/minecraft/ILogAgent;
/*      */     //   624: new java/lang/StringBuilder
/*      */     //   627: dup
/*      */     //   628: invokespecial <init> : ()V
/*      */     //   631: aload_0
/*      */     //   632: getfield username : Ljava/lang/String;
/*      */     //   635: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   638: ldc_w ' never sent a master hash!'
/*      */     //   641: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   644: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   647: invokeinterface logWarning : (Ljava/lang/String;)V
/*      */     //   652: aload_0
/*      */     //   653: iconst_1
/*      */     //   654: putfield master_hash_received : Z
/*      */     //   657: aload_0
/*      */     //   658: new net/minecraft/Packet85SimpleSignal
/*      */     //   661: dup
/*      */     //   662: getstatic net/minecraft/EnumSignal.malnourished : Lnet/minecraft/EnumSignal;
/*      */     //   665: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   668: aload_0
/*      */     //   669: getfield protein : I
/*      */     //   672: ifne -> 679
/*      */     //   675: iconst_1
/*      */     //   676: goto -> 680
/*      */     //   679: iconst_0
/*      */     //   680: aload_0
/*      */     //   681: getfield phytonutrients : I
/*      */     //   684: ifne -> 691
/*      */     //   687: iconst_4
/*      */     //   688: goto -> 692
/*      */     //   691: iconst_0
/*      */     //   692: ior
/*      */     //   693: aload_0
/*      */     //   694: getfield insulin_resistance_level : Lnet/minecraft/EnumInsulinResistanceLevel;
/*      */     //   697: invokestatic getOrdinalForTransmission : (Lnet/minecraft/EnumInsulinResistanceLevel;)I
/*      */     //   700: iconst_3
/*      */     //   701: ishl
/*      */     //   702: ior
/*      */     //   703: aload_0
/*      */     //   704: invokevirtual getInsulinResistance : ()I
/*      */     //   707: bipush #8
/*      */     //   709: ishl
/*      */     //   710: ior
/*      */     //   711: invokevirtual setInteger : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   714: invokevirtual sendPacket : (Lnet/minecraft/Packet;)V
/*      */     //   717: aload_0
/*      */     //   718: getfield sync_client_player_on_next_tick : Z
/*      */     //   721: ifeq -> 733
/*      */     //   724: aload_0
/*      */     //   725: invokevirtual syncClientPlayer : ()V
/*      */     //   728: aload_0
/*      */     //   729: iconst_0
/*      */     //   730: putfield sync_client_player_on_next_tick : Z
/*      */     //   733: aload_0
/*      */     //   734: invokespecial updateMinecartFuelAmounts : ()V
/*      */     //   737: aload_0
/*      */     //   738: dup
/*      */     //   739: getfield ticks_logged_in : I
/*      */     //   742: iconst_1
/*      */     //   743: iadd
/*      */     //   744: putfield ticks_logged_in : I
/*      */     //   747: aload_0
/*      */     //   748: getfield portal_grace_ticks : I
/*      */     //   751: ifle -> 764
/*      */     //   754: aload_0
/*      */     //   755: dup
/*      */     //   756: getfield portal_grace_ticks : I
/*      */     //   759: iconst_1
/*      */     //   760: isub
/*      */     //   761: putfield portal_grace_ticks : I
/*      */     //   764: aload_0
/*      */     //   765: invokespecial isUnderworldBottomBedrockVisible : ()Z
/*      */     //   768: ifeq -> 778
/*      */     //   771: aload_0
/*      */     //   772: getstatic net/minecraft/AchievementList.portalToNether : Lnet/minecraft/Achievement;
/*      */     //   775: invokevirtual triggerAchievement : (Lnet/minecraft/StatBase;)V
/*      */     //   778: aload_0
/*      */     //   779: getfield itemInUse : Lnet/minecraft/ItemStack;
/*      */     //   782: ifnull -> 810
/*      */     //   785: aload_0
/*      */     //   786: getfield itemInUse : Lnet/minecraft/ItemStack;
/*      */     //   789: invokevirtual getItem : ()Lnet/minecraft/Item;
/*      */     //   792: instanceof net/minecraft/ItemBow
/*      */     //   795: ifeq -> 810
/*      */     //   798: aload_0
/*      */     //   799: ldc_w 0.01
/*      */     //   802: aload_0
/*      */     //   803: invokestatic getEnduranceModifier : (Lnet/minecraft/EntityLivingBase;)F
/*      */     //   806: fmul
/*      */     //   807: invokevirtual addHungerServerSide : (F)V
/*      */     //   810: aload_0
/*      */     //   811: getfield worldObj : Lnet/minecraft/World;
/*      */     //   814: checkcast net/minecraft/WorldServer
/*      */     //   817: astore_1
/*      */     //   818: aload_0
/*      */     //   819: getfield foodStats : Lnet/minecraft/FoodStats;
/*      */     //   822: aload_0
/*      */     //   823: invokevirtual onUpdate : (Lnet/minecraft/ServerPlayer;)V
/*      */     //   826: aload_0
/*      */     //   827: invokevirtual isBurning : ()Z
/*      */     //   830: ifeq -> 839
/*      */     //   833: ldc_w 20.0
/*      */     //   836: goto -> 846
/*      */     //   839: aload_0
/*      */     //   840: invokevirtual getBiome : ()Lnet/minecraft/BiomeGenBase;
/*      */     //   843: getfield temperature : F
/*      */     //   846: invokestatic getChanceOfSnowAndIceItemsMelting : (F)F
/*      */     //   849: fstore_2
/*      */     //   850: fload_2
/*      */     //   851: fconst_0
/*      */     //   852: fcmpl
/*      */     //   853: ifle -> 956
/*      */     //   856: iconst_0
/*      */     //   857: istore_3
/*      */     //   858: iload_3
/*      */     //   859: aload_0
/*      */     //   860: getfield inventory : Lnet/minecraft/InventoryPlayer;
/*      */     //   863: getfield mainInventory : [Lnet/minecraft/ItemStack;
/*      */     //   866: arraylength
/*      */     //   867: if_icmpge -> 956
/*      */     //   870: aload_0
/*      */     //   871: getfield inventory : Lnet/minecraft/InventoryPlayer;
/*      */     //   874: iload_3
/*      */     //   875: invokevirtual getInventorySlotContents : (I)Lnet/minecraft/ItemStack;
/*      */     //   878: astore #4
/*      */     //   880: aload #4
/*      */     //   882: ifnonnull -> 888
/*      */     //   885: goto -> 950
/*      */     //   888: aload #4
/*      */     //   890: getstatic net/minecraft/Material.snow : Lnet/minecraft/Material;
/*      */     //   893: iconst_1
/*      */     //   894: invokevirtual hasMaterial : (Lnet/minecraft/Material;Z)Z
/*      */     //   897: ifne -> 924
/*      */     //   900: aload #4
/*      */     //   902: getstatic net/minecraft/Material.craftedSnow : Lnet/minecraft/Material;
/*      */     //   905: iconst_1
/*      */     //   906: invokevirtual hasMaterial : (Lnet/minecraft/Material;Z)Z
/*      */     //   909: ifne -> 924
/*      */     //   912: aload #4
/*      */     //   914: getstatic net/minecraft/Material.ice : Lnet/minecraft/Material;
/*      */     //   917: iconst_1
/*      */     //   918: invokevirtual hasMaterial : (Lnet/minecraft/Material;Z)Z
/*      */     //   921: ifeq -> 950
/*      */     //   924: aload #4
/*      */     //   926: fload_2
/*      */     //   927: aload_0
/*      */     //   928: getfield rand : Ljava/util/Random;
/*      */     //   931: invokevirtual subjectToChanceOfDisappearing : (FLjava/util/Random;)Lnet/minecraft/ItemStack;
/*      */     //   934: getfield stackSize : I
/*      */     //   937: iconst_1
/*      */     //   938: if_icmpge -> 950
/*      */     //   941: aload_0
/*      */     //   942: getfield inventory : Lnet/minecraft/InventoryPlayer;
/*      */     //   945: iload_3
/*      */     //   946: aconst_null
/*      */     //   947: invokevirtual setInventorySlotContents : (ILnet/minecraft/ItemStack;)V
/*      */     //   950: iinc #3, 1
/*      */     //   953: goto -> 858
/*      */     //   956: aload_0
/*      */     //   957: invokespecial tickPlayerInventory : ()V
/*      */     //   960: aload_0
/*      */     //   961: getfield ticksExisted : I
/*      */     //   964: bipush #100
/*      */     //   966: irem
/*      */     //   967: ifne -> 1789
/*      */     //   970: invokestatic random : ()D
/*      */     //   973: ldc2_w 0.009999999776482582
/*      */     //   976: dcmpg
/*      */     //   977: ifge -> 1068
/*      */     //   980: aload_0
/*      */     //   981: invokevirtual isOnHitList : ()Z
/*      */     //   984: ifeq -> 1068
/*      */     //   987: aload_1
/*      */     //   988: iconst_1
/*      */     //   989: invokevirtual isThundering : (Z)Z
/*      */     //   992: ifeq -> 1068
/*      */     //   995: aload_1
/*      */     //   996: aload_0
/*      */     //   997: invokevirtual getBlockPosX : ()I
/*      */     //   1000: aload_0
/*      */     //   1001: invokevirtual getBlockPosY : ()I
/*      */     //   1004: aload_0
/*      */     //   1005: invokevirtual getBlockPosZ : ()I
/*      */     //   1008: invokevirtual canLightningStrikeAt : (III)Z
/*      */     //   1011: ifeq -> 1068
/*      */     //   1014: aload_1
/*      */     //   1015: new net/minecraft/EntityLightningBolt
/*      */     //   1018: dup
/*      */     //   1019: aload_1
/*      */     //   1020: aload_0
/*      */     //   1021: getfield posX : D
/*      */     //   1024: aload_0
/*      */     //   1025: getfield posY : D
/*      */     //   1028: aload_0
/*      */     //   1029: getfield posZ : D
/*      */     //   1032: invokespecial <init> : (Lnet/minecraft/World;DDD)V
/*      */     //   1035: invokevirtual addWeatherEffect : (Lnet/minecraft/Entity;)Z
/*      */     //   1038: pop
/*      */     //   1039: aload_0
/*      */     //   1040: getstatic net/minecraft/DamageSource.generic : Lnet/minecraft/DamageSource;
/*      */     //   1043: ldc_w 20.0
/*      */     //   1046: aconst_null
/*      */     //   1047: invokevirtual tryDamageArmor : (Lnet/minecraft/DamageSource;FLnet/minecraft/EntityDamageResult;)V
/*      */     //   1050: aload_0
/*      */     //   1051: new net/minecraft/Damage
/*      */     //   1054: dup
/*      */     //   1055: getstatic net/minecraft/DamageSource.divine_lightning : Lnet/minecraft/DamageSource;
/*      */     //   1058: ldc_w 20.0
/*      */     //   1061: invokespecial <init> : (Lnet/minecraft/DamageSource;F)V
/*      */     //   1064: invokevirtual attackEntityFrom : (Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;
/*      */     //   1067: pop
/*      */     //   1068: aload_0
/*      */     //   1069: getfield ticksExisted : I
/*      */     //   1072: sipush #1000
/*      */     //   1075: irem
/*      */     //   1076: ifne -> 1789
/*      */     //   1079: aconst_null
/*      */     //   1080: astore_3
/*      */     //   1081: aload_0
/*      */     //   1082: invokevirtual getBlockPosX : ()I
/*      */     //   1085: istore #4
/*      */     //   1087: aload_0
/*      */     //   1088: invokevirtual getBlockPosY : ()I
/*      */     //   1091: istore #5
/*      */     //   1093: aload_0
/*      */     //   1094: invokevirtual getBlockPosZ : ()I
/*      */     //   1097: istore #6
/*      */     //   1099: iconst_5
/*      */     //   1100: istore #7
/*      */     //   1102: iload #7
/*      */     //   1104: iload #7
/*      */     //   1106: imul
/*      */     //   1107: istore #8
/*      */     //   1109: iload #7
/*      */     //   1111: ineg
/*      */     //   1112: istore #9
/*      */     //   1114: iload #9
/*      */     //   1116: iload #7
/*      */     //   1118: if_icmpgt -> 1602
/*      */     //   1121: iload #7
/*      */     //   1123: ineg
/*      */     //   1124: istore #10
/*      */     //   1126: iload #10
/*      */     //   1128: iload #7
/*      */     //   1130: if_icmpgt -> 1596
/*      */     //   1133: iload #7
/*      */     //   1135: ineg
/*      */     //   1136: istore #11
/*      */     //   1138: iload #11
/*      */     //   1140: iload #7
/*      */     //   1142: if_icmpgt -> 1590
/*      */     //   1145: iload #4
/*      */     //   1147: iload #9
/*      */     //   1149: iadd
/*      */     //   1150: istore #12
/*      */     //   1152: iload #5
/*      */     //   1154: iload #11
/*      */     //   1156: iadd
/*      */     //   1157: istore #13
/*      */     //   1159: iload #6
/*      */     //   1161: iload #10
/*      */     //   1163: iadd
/*      */     //   1164: istore #14
/*      */     //   1166: aload_1
/*      */     //   1167: iload #12
/*      */     //   1169: iconst_1
/*      */     //   1170: isub
/*      */     //   1171: iload #13
/*      */     //   1173: iload #14
/*      */     //   1175: iconst_0
/*      */     //   1176: invokevirtual isAirOrPassableBlock : (IIIZ)Z
/*      */     //   1179: ifne -> 1233
/*      */     //   1182: aload_1
/*      */     //   1183: iload #12
/*      */     //   1185: iconst_1
/*      */     //   1186: iadd
/*      */     //   1187: iload #13
/*      */     //   1189: iload #14
/*      */     //   1191: iconst_0
/*      */     //   1192: invokevirtual isAirOrPassableBlock : (IIIZ)Z
/*      */     //   1195: ifne -> 1233
/*      */     //   1198: aload_1
/*      */     //   1199: iload #12
/*      */     //   1201: iload #13
/*      */     //   1203: iload #14
/*      */     //   1205: iconst_1
/*      */     //   1206: isub
/*      */     //   1207: iconst_0
/*      */     //   1208: invokevirtual isAirOrPassableBlock : (IIIZ)Z
/*      */     //   1211: ifne -> 1233
/*      */     //   1214: aload_1
/*      */     //   1215: iload #12
/*      */     //   1217: iload #13
/*      */     //   1219: iload #14
/*      */     //   1221: iconst_1
/*      */     //   1222: iadd
/*      */     //   1223: iconst_0
/*      */     //   1224: invokevirtual isAirOrPassableBlock : (IIIZ)Z
/*      */     //   1227: ifne -> 1233
/*      */     //   1230: goto -> 1584
/*      */     //   1233: iload #12
/*      */     //   1235: i2f
/*      */     //   1236: ldc_w 0.5
/*      */     //   1239: fadd
/*      */     //   1240: fstore #15
/*      */     //   1242: iload #13
/*      */     //   1244: i2f
/*      */     //   1245: ldc_w 0.5
/*      */     //   1248: fadd
/*      */     //   1249: fstore #16
/*      */     //   1251: iload #14
/*      */     //   1253: i2f
/*      */     //   1254: ldc_w 0.5
/*      */     //   1257: fadd
/*      */     //   1258: fstore #17
/*      */     //   1260: aload_0
/*      */     //   1261: fload #15
/*      */     //   1263: fconst_1
/*      */     //   1264: fsub
/*      */     //   1265: f2d
/*      */     //   1266: fload #16
/*      */     //   1268: f2d
/*      */     //   1269: fload #17
/*      */     //   1271: f2d
/*      */     //   1272: iload #8
/*      */     //   1274: i2d
/*      */     //   1275: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1278: ifne -> 1389
/*      */     //   1281: aload_0
/*      */     //   1282: fload #15
/*      */     //   1284: fconst_1
/*      */     //   1285: fadd
/*      */     //   1286: f2d
/*      */     //   1287: fload #16
/*      */     //   1289: f2d
/*      */     //   1290: fload #17
/*      */     //   1292: f2d
/*      */     //   1293: iload #8
/*      */     //   1295: i2d
/*      */     //   1296: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1299: ifne -> 1389
/*      */     //   1302: aload_0
/*      */     //   1303: fload #15
/*      */     //   1305: f2d
/*      */     //   1306: fload #16
/*      */     //   1308: fconst_1
/*      */     //   1309: fsub
/*      */     //   1310: f2d
/*      */     //   1311: fload #17
/*      */     //   1313: f2d
/*      */     //   1314: iload #8
/*      */     //   1316: i2d
/*      */     //   1317: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1320: ifne -> 1389
/*      */     //   1323: aload_0
/*      */     //   1324: fload #15
/*      */     //   1326: f2d
/*      */     //   1327: fload #16
/*      */     //   1329: fconst_1
/*      */     //   1330: fadd
/*      */     //   1331: f2d
/*      */     //   1332: fload #17
/*      */     //   1334: f2d
/*      */     //   1335: iload #8
/*      */     //   1337: i2d
/*      */     //   1338: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1341: ifne -> 1389
/*      */     //   1344: aload_0
/*      */     //   1345: fload #15
/*      */     //   1347: f2d
/*      */     //   1348: fload #16
/*      */     //   1350: f2d
/*      */     //   1351: fload #17
/*      */     //   1353: fconst_1
/*      */     //   1354: fsub
/*      */     //   1355: f2d
/*      */     //   1356: iload #8
/*      */     //   1358: i2d
/*      */     //   1359: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1362: ifne -> 1389
/*      */     //   1365: aload_0
/*      */     //   1366: fload #15
/*      */     //   1368: f2d
/*      */     //   1369: fload #16
/*      */     //   1371: f2d
/*      */     //   1372: fload #17
/*      */     //   1374: fconst_1
/*      */     //   1375: fadd
/*      */     //   1376: f2d
/*      */     //   1377: iload #8
/*      */     //   1379: i2d
/*      */     //   1380: invokevirtual canEntityBeSeenFrom : (DDDD)Z
/*      */     //   1383: ifne -> 1389
/*      */     //   1386: goto -> 1584
/*      */     //   1389: aload_0
/*      */     //   1390: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1393: iload #12
/*      */     //   1395: iload #13
/*      */     //   1397: iload #14
/*      */     //   1399: invokevirtual getBlock : (III)Lnet/minecraft/Block;
/*      */     //   1402: astore #18
/*      */     //   1404: aload #18
/*      */     //   1406: instanceof net/minecraft/BlockSilverfish
/*      */     //   1409: ifeq -> 1584
/*      */     //   1412: aload_0
/*      */     //   1413: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1416: invokevirtual isOverworld : ()Z
/*      */     //   1419: ifeq -> 1552
/*      */     //   1422: aload_0
/*      */     //   1423: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1426: getstatic net/minecraft/EnumSkyBlock.Sky : Lnet/minecraft/EnumSkyBlock;
/*      */     //   1429: iload #12
/*      */     //   1431: iconst_1
/*      */     //   1432: isub
/*      */     //   1433: iload #13
/*      */     //   1435: iload #14
/*      */     //   1437: invokevirtual getSkyBlockTypeBrightness : (Lnet/minecraft/EnumSkyBlock;III)I
/*      */     //   1440: bipush #7
/*      */     //   1442: if_icmple -> 1448
/*      */     //   1445: goto -> 1584
/*      */     //   1448: aload_0
/*      */     //   1449: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1452: getstatic net/minecraft/EnumSkyBlock.Sky : Lnet/minecraft/EnumSkyBlock;
/*      */     //   1455: iload #12
/*      */     //   1457: iconst_1
/*      */     //   1458: iadd
/*      */     //   1459: iload #13
/*      */     //   1461: iload #14
/*      */     //   1463: invokevirtual getSkyBlockTypeBrightness : (Lnet/minecraft/EnumSkyBlock;III)I
/*      */     //   1466: bipush #7
/*      */     //   1468: if_icmple -> 1474
/*      */     //   1471: goto -> 1584
/*      */     //   1474: aload_0
/*      */     //   1475: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1478: getstatic net/minecraft/EnumSkyBlock.Sky : Lnet/minecraft/EnumSkyBlock;
/*      */     //   1481: iload #12
/*      */     //   1483: iload #13
/*      */     //   1485: iconst_1
/*      */     //   1486: iadd
/*      */     //   1487: iload #14
/*      */     //   1489: invokevirtual getSkyBlockTypeBrightness : (Lnet/minecraft/EnumSkyBlock;III)I
/*      */     //   1492: bipush #7
/*      */     //   1494: if_icmple -> 1500
/*      */     //   1497: goto -> 1584
/*      */     //   1500: aload_0
/*      */     //   1501: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1504: getstatic net/minecraft/EnumSkyBlock.Sky : Lnet/minecraft/EnumSkyBlock;
/*      */     //   1507: iload #12
/*      */     //   1509: iload #13
/*      */     //   1511: iload #14
/*      */     //   1513: iconst_1
/*      */     //   1514: isub
/*      */     //   1515: invokevirtual getSkyBlockTypeBrightness : (Lnet/minecraft/EnumSkyBlock;III)I
/*      */     //   1518: bipush #7
/*      */     //   1520: if_icmple -> 1526
/*      */     //   1523: goto -> 1584
/*      */     //   1526: aload_0
/*      */     //   1527: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1530: getstatic net/minecraft/EnumSkyBlock.Sky : Lnet/minecraft/EnumSkyBlock;
/*      */     //   1533: iload #12
/*      */     //   1535: iload #13
/*      */     //   1537: iload #14
/*      */     //   1539: iconst_1
/*      */     //   1540: iadd
/*      */     //   1541: invokevirtual getSkyBlockTypeBrightness : (Lnet/minecraft/EnumSkyBlock;III)I
/*      */     //   1544: bipush #7
/*      */     //   1546: if_icmple -> 1552
/*      */     //   1549: goto -> 1584
/*      */     //   1552: aload_3
/*      */     //   1553: ifnonnull -> 1564
/*      */     //   1556: new java/util/ArrayList
/*      */     //   1559: dup
/*      */     //   1560: invokespecial <init> : ()V
/*      */     //   1563: astore_3
/*      */     //   1564: aload_3
/*      */     //   1565: new net/minecraft/ChunkPosition
/*      */     //   1568: dup
/*      */     //   1569: iload #12
/*      */     //   1571: iload #13
/*      */     //   1573: iload #14
/*      */     //   1575: invokespecial <init> : (III)V
/*      */     //   1578: invokeinterface add : (Ljava/lang/Object;)Z
/*      */     //   1583: pop
/*      */     //   1584: iinc #11, 1
/*      */     //   1587: goto -> 1138
/*      */     //   1590: iinc #10, 1
/*      */     //   1593: goto -> 1126
/*      */     //   1596: iinc #9, 1
/*      */     //   1599: goto -> 1114
/*      */     //   1602: aload_3
/*      */     //   1603: ifnull -> 1789
/*      */     //   1606: aload_3
/*      */     //   1607: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   1612: astore #9
/*      */     //   1614: aload #9
/*      */     //   1616: invokeinterface hasNext : ()Z
/*      */     //   1621: ifeq -> 1789
/*      */     //   1624: aload #9
/*      */     //   1626: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   1631: checkcast net/minecraft/ChunkPosition
/*      */     //   1634: astore #10
/*      */     //   1636: aload_0
/*      */     //   1637: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1640: invokevirtual getAsWorldServer : ()Lnet/minecraft/WorldServer;
/*      */     //   1643: aload #10
/*      */     //   1645: getfield x : I
/*      */     //   1648: aload #10
/*      */     //   1650: getfield y : I
/*      */     //   1653: aload #10
/*      */     //   1655: getfield z : I
/*      */     //   1658: getstatic net/minecraft/EnumBlockOperation.spawn_silverfish : Lnet/minecraft/EnumBlockOperation;
/*      */     //   1661: invokevirtual doesQueuedBlockOperationExist : (IIILnet/minecraft/EnumBlockOperation;)Z
/*      */     //   1664: ifne -> 1786
/*      */     //   1667: invokestatic obtainNextEntityID : ()I
/*      */     //   1670: istore #11
/*      */     //   1672: aload_1
/*      */     //   1673: iload #11
/*      */     //   1675: aload #10
/*      */     //   1677: getfield x : I
/*      */     //   1680: aload #10
/*      */     //   1682: getfield y : I
/*      */     //   1685: aload #10
/*      */     //   1687: getfield z : I
/*      */     //   1690: iconst_4
/*      */     //   1691: invokevirtual watchAnimal : (IIIII)V
/*      */     //   1694: aload_1
/*      */     //   1695: sipush #2001
/*      */     //   1698: aload #10
/*      */     //   1700: getfield x : I
/*      */     //   1703: aload #10
/*      */     //   1705: getfield y : I
/*      */     //   1708: aload #10
/*      */     //   1710: getfield z : I
/*      */     //   1713: getstatic net/minecraft/Block.silverfish : Lnet/minecraft/BlockSilverfish;
/*      */     //   1716: getfield blockID : I
/*      */     //   1719: aload_1
/*      */     //   1720: aload #10
/*      */     //   1722: getfield x : I
/*      */     //   1725: aload #10
/*      */     //   1727: getfield y : I
/*      */     //   1730: aload #10
/*      */     //   1732: getfield z : I
/*      */     //   1735: invokevirtual getBlockMetadata : (III)I
/*      */     //   1738: bipush #12
/*      */     //   1740: ishl
/*      */     //   1741: iadd
/*      */     //   1742: invokevirtual playAuxSFX : (IIIII)V
/*      */     //   1745: aload_0
/*      */     //   1746: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1749: invokevirtual getAsWorldServer : ()Lnet/minecraft/WorldServer;
/*      */     //   1752: getstatic net/minecraft/EnumBlockOperation.spawn_silverfish : Lnet/minecraft/EnumBlockOperation;
/*      */     //   1755: aload #10
/*      */     //   1757: getfield x : I
/*      */     //   1760: aload #10
/*      */     //   1762: getfield y : I
/*      */     //   1765: aload #10
/*      */     //   1767: getfield z : I
/*      */     //   1770: aload_0
/*      */     //   1771: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1774: invokevirtual getTotalWorldTime : ()J
/*      */     //   1777: ldc2_w 20
/*      */     //   1780: ladd
/*      */     //   1781: iconst_0
/*      */     //   1782: aload_0
/*      */     //   1783: invokevirtual addScheduledBlockOperation : (Lnet/minecraft/EnumBlockOperation;IIIJZLjava/lang/Object;)V
/*      */     //   1786: goto -> 1614
/*      */     //   1789: aload_0
/*      */     //   1790: getfield ticksExisted : I
/*      */     //   1793: bipush #60
/*      */     //   1795: if_icmplt -> 1880
/*      */     //   1798: aload_0
/*      */     //   1799: getfield ticksExisted : I
/*      */     //   1802: bipush #60
/*      */     //   1804: if_icmpeq -> 1841
/*      */     //   1807: aload_0
/*      */     //   1808: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1811: pop
/*      */     //   1812: aload_0
/*      */     //   1813: getfield posX : D
/*      */     //   1816: aload_0
/*      */     //   1817: getfield pos_x_at_last_world_map_update : D
/*      */     //   1820: dsub
/*      */     //   1821: dconst_0
/*      */     //   1822: aload_0
/*      */     //   1823: getfield posZ : D
/*      */     //   1826: aload_0
/*      */     //   1827: getfield pos_z_at_last_world_map_update : D
/*      */     //   1830: dsub
/*      */     //   1831: invokestatic getDistanceSqFromDeltas : (DDD)D
/*      */     //   1834: ldc2_w 16.0
/*      */     //   1837: dcmpl
/*      */     //   1838: ifle -> 1880
/*      */     //   1841: aload_0
/*      */     //   1842: invokevirtual isZevimrgvInTournament : ()Z
/*      */     //   1845: ifne -> 1880
/*      */     //   1848: aload_0
/*      */     //   1849: aload_0
/*      */     //   1850: getfield posX : D
/*      */     //   1853: putfield pos_x_at_last_world_map_update : D
/*      */     //   1856: aload_0
/*      */     //   1857: aload_0
/*      */     //   1858: getfield posZ : D
/*      */     //   1861: putfield pos_z_at_last_world_map_update : D
/*      */     //   1864: aload_1
/*      */     //   1865: aload_0
/*      */     //   1866: invokevirtual getBlockPosX : ()I
/*      */     //   1869: aload_0
/*      */     //   1870: invokevirtual getBlockPosZ : ()I
/*      */     //   1873: bipush #16
/*      */     //   1875: iconst_0
/*      */     //   1876: invokevirtual addWorldMapSurvey : (IIIZ)I
/*      */     //   1879: pop
/*      */     //   1880: aload_0
/*      */     //   1881: getfield initialInvulnerability : I
/*      */     //   1884: ifle -> 1897
/*      */     //   1887: aload_0
/*      */     //   1888: dup
/*      */     //   1889: getfield initialInvulnerability : I
/*      */     //   1892: iconst_1
/*      */     //   1893: isub
/*      */     //   1894: putfield initialInvulnerability : I
/*      */     //   1897: aload_0
/*      */     //   1898: getfield openContainer : Lnet/minecraft/Container;
/*      */     //   1901: invokevirtual detectAndSendChanges : ()V
/*      */     //   1904: aload_0
/*      */     //   1905: getfield worldObj : Lnet/minecraft/World;
/*      */     //   1908: getfield isRemote : Z
/*      */     //   1911: ifne -> 1937
/*      */     //   1914: aload_0
/*      */     //   1915: getfield openContainer : Lnet/minecraft/Container;
/*      */     //   1918: aload_0
/*      */     //   1919: invokevirtual canInteractWith : (Lnet/minecraft/EntityPlayer;)Z
/*      */     //   1922: ifne -> 1937
/*      */     //   1925: aload_0
/*      */     //   1926: invokevirtual closeScreen : ()V
/*      */     //   1929: aload_0
/*      */     //   1930: aload_0
/*      */     //   1931: getfield inventoryContainer : Lnet/minecraft/Container;
/*      */     //   1934: putfield openContainer : Lnet/minecraft/Container;
/*      */     //   1937: aload_0
/*      */     //   1938: getfield destroyedItemsNetCache : Ljava/util/List;
/*      */     //   1941: invokeinterface isEmpty : ()Z
/*      */     //   1946: ifne -> 2049
/*      */     //   1949: aload_0
/*      */     //   1950: getfield destroyedItemsNetCache : Ljava/util/List;
/*      */     //   1953: invokeinterface size : ()I
/*      */     //   1958: bipush #127
/*      */     //   1960: invokestatic min : (II)I
/*      */     //   1963: istore_3
/*      */     //   1964: iload_3
/*      */     //   1965: newarray int
/*      */     //   1967: astore #4
/*      */     //   1969: aload_0
/*      */     //   1970: getfield destroyedItemsNetCache : Ljava/util/List;
/*      */     //   1973: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   1978: astore #5
/*      */     //   1980: iconst_0
/*      */     //   1981: istore #6
/*      */     //   1983: aload #5
/*      */     //   1985: invokeinterface hasNext : ()Z
/*      */     //   1990: ifeq -> 2030
/*      */     //   1993: iload #6
/*      */     //   1995: iload_3
/*      */     //   1996: if_icmpge -> 2030
/*      */     //   1999: aload #4
/*      */     //   2001: iload #6
/*      */     //   2003: iinc #6, 1
/*      */     //   2006: aload #5
/*      */     //   2008: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   2013: checkcast java/lang/Integer
/*      */     //   2016: invokevirtual intValue : ()I
/*      */     //   2019: iastore
/*      */     //   2020: aload #5
/*      */     //   2022: invokeinterface remove : ()V
/*      */     //   2027: goto -> 1983
/*      */     //   2030: aload_0
/*      */     //   2031: getfield playerNetServerHandler : Lnet/minecraft/NetServerHandler;
/*      */     //   2034: new net/minecraft/Packet29DestroyEntity
/*      */     //   2037: dup
/*      */     //   2038: aload #4
/*      */     //   2040: invokespecial <init> : ([I)V
/*      */     //   2043: invokevirtual sendPacketToPlayer : (Lnet/minecraft/Packet;)V
/*      */     //   2046: goto -> 1937
/*      */     //   2049: aload_0
/*      */     //   2050: getfield loadedChunks : Ljava/util/List;
/*      */     //   2053: invokeinterface isEmpty : ()Z
/*      */     //   2058: ifne -> 2426
/*      */     //   2061: aload_0
/*      */     //   2062: getfield loadedChunks : Ljava/util/List;
/*      */     //   2065: invokeinterface size : ()I
/*      */     //   2070: sipush #500
/*      */     //   2073: if_icmple -> 2082
/*      */     //   2076: ldc_w 'EntityPlayerMP: loadedChunks.size() > 500'
/*      */     //   2079: invokestatic setErrorMessage : (Ljava/lang/String;)V
/*      */     //   2082: new java/util/ArrayList
/*      */     //   2085: dup
/*      */     //   2086: invokespecial <init> : ()V
/*      */     //   2089: astore_3
/*      */     //   2090: aload_0
/*      */     //   2091: getfield loadedChunks : Ljava/util/List;
/*      */     //   2094: invokeinterface iterator : ()Ljava/util/Iterator;
/*      */     //   2099: astore #4
/*      */     //   2101: new java/util/ArrayList
/*      */     //   2104: dup
/*      */     //   2105: invokespecial <init> : ()V
/*      */     //   2108: astore #5
/*      */     //   2110: invokestatic currentTimeMillis : ()J
/*      */     //   2113: lstore #6
/*      */     //   2115: aload #4
/*      */     //   2117: invokeinterface hasNext : ()Z
/*      */     //   2122: ifeq -> 2322
/*      */     //   2125: aload_3
/*      */     //   2126: invokevirtual size : ()I
/*      */     //   2129: iconst_5
/*      */     //   2130: if_icmpge -> 2322
/*      */     //   2133: aload #4
/*      */     //   2135: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   2140: checkcast net/minecraft/ChunkCoordIntPair
/*      */     //   2143: astore #8
/*      */     //   2145: aload_0
/*      */     //   2146: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2149: aload #8
/*      */     //   2151: getfield chunkXPos : I
/*      */     //   2154: aload #8
/*      */     //   2156: getfield chunkZPos : I
/*      */     //   2159: invokestatic considerNeighboringChunksInLightingArtifactPrevention : ()Z
/*      */     //   2162: ifeq -> 2169
/*      */     //   2165: iconst_2
/*      */     //   2166: goto -> 2170
/*      */     //   2169: iconst_1
/*      */     //   2170: iconst_0
/*      */     //   2171: invokevirtual doesChunkAndAllNeighborsExist : (IIIZ)Z
/*      */     //   2174: ifeq -> 2319
/*      */     //   2177: aload #4
/*      */     //   2179: invokeinterface remove : ()V
/*      */     //   2184: aload_0
/*      */     //   2185: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2188: aload #8
/*      */     //   2190: getfield chunkXPos : I
/*      */     //   2193: aload #8
/*      */     //   2195: getfield chunkZPos : I
/*      */     //   2198: invokevirtual getChunkFromChunkCoords : (II)Lnet/minecraft/Chunk;
/*      */     //   2201: astore #9
/*      */     //   2203: aload #9
/*      */     //   2205: invokevirtual isWithinBlockDomain : ()Z
/*      */     //   2208: ifne -> 2214
/*      */     //   2211: goto -> 2115
/*      */     //   2214: invokestatic preventLightingArtifacts : ()Z
/*      */     //   2217: ifeq -> 2225
/*      */     //   2220: aload #9
/*      */     //   2222: invokestatic checkLighting : (Lnet/minecraft/Chunk;)V
/*      */     //   2225: aload_3
/*      */     //   2226: aload #9
/*      */     //   2228: invokevirtual add : (Ljava/lang/Object;)Z
/*      */     //   2231: pop
/*      */     //   2232: aload #5
/*      */     //   2234: aload_0
/*      */     //   2235: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2238: checkcast net/minecraft/WorldServer
/*      */     //   2241: aload #8
/*      */     //   2243: getfield chunkXPos : I
/*      */     //   2246: bipush #16
/*      */     //   2248: imul
/*      */     //   2249: iconst_0
/*      */     //   2250: aload #8
/*      */     //   2252: getfield chunkZPos : I
/*      */     //   2255: bipush #16
/*      */     //   2257: imul
/*      */     //   2258: aload #8
/*      */     //   2260: getfield chunkXPos : I
/*      */     //   2263: bipush #16
/*      */     //   2265: imul
/*      */     //   2266: bipush #16
/*      */     //   2268: iadd
/*      */     //   2269: sipush #256
/*      */     //   2272: aload #8
/*      */     //   2274: getfield chunkZPos : I
/*      */     //   2277: bipush #16
/*      */     //   2279: imul
/*      */     //   2280: bipush #16
/*      */     //   2282: iadd
/*      */     //   2283: invokevirtual getAllTileEntityInBox : (IIIIII)Ljava/util/List;
/*      */     //   2286: invokevirtual addAll : (Ljava/util/Collection;)Z
/*      */     //   2289: pop
/*      */     //   2290: invokestatic currentTimeMillis : ()J
/*      */     //   2293: lload #6
/*      */     //   2295: lsub
/*      */     //   2296: ldc2_w 10
/*      */     //   2299: lcmp
/*      */     //   2300: ifgt -> 2322
/*      */     //   2303: invokestatic getServer : ()Lnet/minecraft/server/MinecraftServer;
/*      */     //   2306: invokevirtual getLoadOnServer : ()F
/*      */     //   2309: ldc_w 0.8
/*      */     //   2312: fcmpl
/*      */     //   2313: ifle -> 2319
/*      */     //   2316: goto -> 2322
/*      */     //   2319: goto -> 2115
/*      */     //   2322: aload_3
/*      */     //   2323: invokevirtual isEmpty : ()Z
/*      */     //   2326: ifne -> 2426
/*      */     //   2329: aload_0
/*      */     //   2330: getfield playerNetServerHandler : Lnet/minecraft/NetServerHandler;
/*      */     //   2333: new net/minecraft/Packet56MapChunks
/*      */     //   2336: dup
/*      */     //   2337: aload_3
/*      */     //   2338: invokespecial <init> : (Ljava/util/List;)V
/*      */     //   2341: invokevirtual sendPacketToPlayer : (Lnet/minecraft/Packet;)V
/*      */     //   2344: aload #5
/*      */     //   2346: invokevirtual iterator : ()Ljava/util/Iterator;
/*      */     //   2349: astore #8
/*      */     //   2351: aload #8
/*      */     //   2353: invokeinterface hasNext : ()Z
/*      */     //   2358: ifeq -> 2382
/*      */     //   2361: aload #8
/*      */     //   2363: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   2368: checkcast net/minecraft/TileEntity
/*      */     //   2371: astore #9
/*      */     //   2373: aload_0
/*      */     //   2374: aload #9
/*      */     //   2376: invokespecial sendTileEntityToPlayer : (Lnet/minecraft/TileEntity;)V
/*      */     //   2379: goto -> 2351
/*      */     //   2382: aload_3
/*      */     //   2383: invokevirtual iterator : ()Ljava/util/Iterator;
/*      */     //   2386: astore #8
/*      */     //   2388: aload #8
/*      */     //   2390: invokeinterface hasNext : ()Z
/*      */     //   2395: ifeq -> 2426
/*      */     //   2398: aload #8
/*      */     //   2400: invokeinterface next : ()Ljava/lang/Object;
/*      */     //   2405: checkcast net/minecraft/Chunk
/*      */     //   2408: astore #9
/*      */     //   2410: aload_0
/*      */     //   2411: invokevirtual getServerForPlayer : ()Lnet/minecraft/WorldServer;
/*      */     //   2414: invokevirtual getEntityTracker : ()Lnet/minecraft/EntityTracker;
/*      */     //   2417: aload_0
/*      */     //   2418: aload #9
/*      */     //   2420: invokevirtual func_85172_a : (Lnet/minecraft/ServerPlayer;Lnet/minecraft/Chunk;)V
/*      */     //   2423: goto -> 2388
/*      */     //   2426: aload_0
/*      */     //   2427: getfield field_143005_bX : J
/*      */     //   2430: lconst_0
/*      */     //   2431: lcmp
/*      */     //   2432: ifle -> 2482
/*      */     //   2435: aload_0
/*      */     //   2436: getfield mcServer : Lnet/minecraft/server/MinecraftServer;
/*      */     //   2439: invokevirtual func_143007_ar : ()I
/*      */     //   2442: ifle -> 2482
/*      */     //   2445: invokestatic getSystemTimeMillis : ()J
/*      */     //   2448: aload_0
/*      */     //   2449: getfield field_143005_bX : J
/*      */     //   2452: lsub
/*      */     //   2453: aload_0
/*      */     //   2454: getfield mcServer : Lnet/minecraft/server/MinecraftServer;
/*      */     //   2457: invokevirtual func_143007_ar : ()I
/*      */     //   2460: sipush #1000
/*      */     //   2463: imul
/*      */     //   2464: bipush #60
/*      */     //   2466: imul
/*      */     //   2467: i2l
/*      */     //   2468: lcmp
/*      */     //   2469: ifle -> 2482
/*      */     //   2472: aload_0
/*      */     //   2473: getfield playerNetServerHandler : Lnet/minecraft/NetServerHandler;
/*      */     //   2476: ldc_w 'You have been idle for too long!'
/*      */     //   2479: invokevirtual kickPlayerFromServer : (Ljava/lang/String;)V
/*      */     //   2482: invokestatic isTournamentThatUsesAllottedTimes : ()Z
/*      */     //   2485: ifeq -> 2601
/*      */     //   2488: aload_0
/*      */     //   2489: getfield allotted_time : I
/*      */     //   2492: ifle -> 2505
/*      */     //   2495: aload_0
/*      */     //   2496: dup
/*      */     //   2497: getfield allotted_time : I
/*      */     //   2500: iconst_1
/*      */     //   2501: isub
/*      */     //   2502: putfield allotted_time : I
/*      */     //   2505: aload_0
/*      */     //   2506: getfield allotted_time : I
/*      */     //   2509: ifne -> 2525
/*      */     //   2512: aload_0
/*      */     //   2513: invokevirtual isZevimrgvInTournament : ()Z
/*      */     //   2516: ifeq -> 2525
/*      */     //   2519: aload_0
/*      */     //   2520: bipush #20
/*      */     //   2522: putfield allotted_time : I
/*      */     //   2525: aload_0
/*      */     //   2526: getfield allotted_time : I
/*      */     //   2529: ifne -> 2570
/*      */     //   2532: getstatic net/minecraft/DedicatedServer.players_kicked_for_depleted_time_shares : Ljava/util/HashMap;
/*      */     //   2535: aload_0
/*      */     //   2536: getfield username : Ljava/lang/String;
/*      */     //   2539: new java/lang/Long
/*      */     //   2542: dup
/*      */     //   2543: aload_0
/*      */     //   2544: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2547: invokevirtual getTotalWorldTime : ()J
/*      */     //   2550: invokespecial <init> : (J)V
/*      */     //   2553: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   2556: pop
/*      */     //   2557: aload_0
/*      */     //   2558: getfield playerNetServerHandler : Lnet/minecraft/NetServerHandler;
/*      */     //   2561: ldc_w 'Time share depleted'
/*      */     //   2564: invokevirtual kickPlayerFromServer : (Ljava/lang/String;)V
/*      */     //   2567: goto -> 2601
/*      */     //   2570: aload_0
/*      */     //   2571: getfield allotted_time : I
/*      */     //   2574: bipush #20
/*      */     //   2576: irem
/*      */     //   2577: ifne -> 2601
/*      */     //   2580: aload_0
/*      */     //   2581: new net/minecraft/Packet85SimpleSignal
/*      */     //   2584: dup
/*      */     //   2585: getstatic net/minecraft/EnumSignal.allotted_time : Lnet/minecraft/EnumSignal;
/*      */     //   2588: invokespecial <init> : (Lnet/minecraft/EnumSignal;)V
/*      */     //   2591: aload_0
/*      */     //   2592: getfield allotted_time : I
/*      */     //   2595: invokevirtual setInteger : (I)Lnet/minecraft/Packet85SimpleSignal;
/*      */     //   2598: invokevirtual sendPacket : (Lnet/minecraft/Packet;)V
/*      */     //   2601: aload_0
/*      */     //   2602: iconst_0
/*      */     //   2603: invokevirtual getDistanceSqToWorldSpawnPoint : (Z)D
/*      */     //   2606: dstore_3
/*      */     //   2607: aload_0
/*      */     //   2608: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2611: invokevirtual isOverworld : ()Z
/*      */     //   2614: ifeq -> 2640
/*      */     //   2617: dload_3
/*      */     //   2618: ldc2_w 1.0E8
/*      */     //   2621: dcmpl
/*      */     //   2622: iflt -> 2640
/*      */     //   2625: dload_3
/*      */     //   2626: ldc2_w 4.0E8
/*      */     //   2629: dcmpg
/*      */     //   2630: ifge -> 2640
/*      */     //   2633: aload_0
/*      */     //   2634: getstatic net/minecraft/AchievementList.explorer : Lnet/minecraft/Achievement;
/*      */     //   2637: invokevirtual triggerAchievement : (Lnet/minecraft/StatBase;)V
/*      */     //   2640: aload_0
/*      */     //   2641: getfield ticksExisted : I
/*      */     //   2644: bipush #10
/*      */     //   2646: irem
/*      */     //   2647: ifne -> 2792
/*      */     //   2650: aload_0
/*      */     //   2651: getfield ridingEntity : Lnet/minecraft/Entity;
/*      */     //   2654: instanceof net/minecraft/EntityBoat
/*      */     //   2657: ifeq -> 2792
/*      */     //   2660: aload_0
/*      */     //   2661: invokevirtual getBlockPosX : ()I
/*      */     //   2664: istore #5
/*      */     //   2666: aload_0
/*      */     //   2667: invokevirtual getFootBlockPosY : ()I
/*      */     //   2670: istore #6
/*      */     //   2672: aload_0
/*      */     //   2673: invokevirtual getBlockPosZ : ()I
/*      */     //   2676: istore #7
/*      */     //   2678: iconst_1
/*      */     //   2679: istore #8
/*      */     //   2681: bipush #-4
/*      */     //   2683: istore #9
/*      */     //   2685: iload #8
/*      */     //   2687: ifeq -> 2780
/*      */     //   2690: iload #9
/*      */     //   2692: iconst_m1
/*      */     //   2693: if_icmpgt -> 2780
/*      */     //   2696: bipush #-8
/*      */     //   2698: istore #10
/*      */     //   2700: iload #8
/*      */     //   2702: ifeq -> 2774
/*      */     //   2705: iload #10
/*      */     //   2707: bipush #8
/*      */     //   2709: if_icmpgt -> 2774
/*      */     //   2712: bipush #-8
/*      */     //   2714: istore #11
/*      */     //   2716: iload #8
/*      */     //   2718: ifeq -> 2768
/*      */     //   2721: iload #11
/*      */     //   2723: bipush #8
/*      */     //   2725: if_icmpgt -> 2768
/*      */     //   2728: aload_0
/*      */     //   2729: getfield worldObj : Lnet/minecraft/World;
/*      */     //   2732: iload #5
/*      */     //   2734: iload #10
/*      */     //   2736: iadd
/*      */     //   2737: iload #6
/*      */     //   2739: iload #9
/*      */     //   2741: iadd
/*      */     //   2742: iload #7
/*      */     //   2744: iload #11
/*      */     //   2746: iadd
/*      */     //   2747: invokevirtual getBlockMaterial : (III)Lnet/minecraft/Material;
/*      */     //   2750: getstatic net/minecraft/Material.water : Lnet/minecraft/Material;
/*      */     //   2753: if_acmpeq -> 2762
/*      */     //   2756: iconst_0
/*      */     //   2757: istore #8
/*      */     //   2759: goto -> 2768
/*      */     //   2762: iinc #11, 1
/*      */     //   2765: goto -> 2716
/*      */     //   2768: iinc #10, 1
/*      */     //   2771: goto -> 2700
/*      */     //   2774: iinc #9, 1
/*      */     //   2777: goto -> 2685
/*      */     //   2780: iload #8
/*      */     //   2782: ifeq -> 2792
/*      */     //   2785: aload_0
/*      */     //   2786: getstatic net/minecraft/AchievementList.seaworthy : Lnet/minecraft/Achievement;
/*      */     //   2789: invokevirtual triggerAchievement : (Lnet/minecraft/StatBase;)V
/*      */     //   2792: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #898	-> 0
/*      */     //   #900	-> 7
/*      */     //   #902	-> 17
/*      */     //   #903	-> 39
/*      */     //   #904	-> 61
/*      */     //   #905	-> 84
/*      */     //   #906	-> 107
/*      */     //   #908	-> 131
/*      */     //   #911	-> 169
/*      */     //   #912	-> 179
/*      */     //   #914	-> 216
/*      */     //   #915	-> 228
/*      */     //   #917	-> 265
/*      */     //   #918	-> 275
/*      */     //   #920	-> 299
/*      */     //   #921	-> 309
/*      */     //   #923	-> 316
/*      */     //   #924	-> 327
/*      */     //   #927	-> 335
/*      */     //   #929	-> 342
/*      */     //   #931	-> 349
/*      */     //   #932	-> 353
/*      */     //   #935	-> 357
/*      */     //   #939	-> 362
/*      */     //   #941	-> 377
/*      */     //   #947	-> 385
/*      */     //   #950	-> 390
/*      */     //   #951	-> 404
/*      */     //   #953	-> 412
/*      */     //   #955	-> 425
/*      */     //   #957	-> 438
/*      */     //   #960	-> 469
/*      */     //   #962	-> 476
/*      */     //   #964	-> 485
/*      */     //   #966	-> 489
/*      */     //   #968	-> 497
/*      */     //   #970	-> 503
/*      */     //   #973	-> 507
/*      */     //   #976	-> 512
/*      */     //   #978	-> 522
/*      */     //   #980	-> 530
/*      */     //   #987	-> 534
/*      */     //   #988	-> 543
/*      */     //   #1014	-> 553
/*      */     //   #1016	-> 579
/*      */     //   #1018	-> 589
/*      */     //   #1022	-> 617
/*      */     //   #1023	-> 652
/*      */     //   #1034	-> 657
/*      */     //   #1036	-> 717
/*      */     //   #1038	-> 724
/*      */     //   #1039	-> 728
/*      */     //   #1042	-> 733
/*      */     //   #1045	-> 737
/*      */     //   #1047	-> 747
/*      */     //   #1048	-> 754
/*      */     //   #1053	-> 764
/*      */     //   #1054	-> 771
/*      */     //   #1056	-> 778
/*      */     //   #1057	-> 798
/*      */     //   #1071	-> 810
/*      */     //   #1073	-> 818
/*      */     //   #1076	-> 826
/*      */     //   #1078	-> 850
/*      */     //   #1080	-> 856
/*      */     //   #1082	-> 870
/*      */     //   #1084	-> 880
/*      */     //   #1085	-> 885
/*      */     //   #1087	-> 888
/*      */     //   #1102	-> 924
/*      */     //   #1103	-> 941
/*      */     //   #1080	-> 950
/*      */     //   #1108	-> 956
/*      */     //   #1110	-> 960
/*      */     //   #1112	-> 970
/*      */     //   #1114	-> 987
/*      */     //   #1116	-> 1014
/*      */     //   #1118	-> 1039
/*      */     //   #1119	-> 1050
/*      */     //   #1123	-> 1068
/*      */     //   #1125	-> 1079
/*      */     //   #1127	-> 1081
/*      */     //   #1128	-> 1087
/*      */     //   #1129	-> 1093
/*      */     //   #1131	-> 1099
/*      */     //   #1133	-> 1102
/*      */     //   #1135	-> 1109
/*      */     //   #1137	-> 1121
/*      */     //   #1139	-> 1133
/*      */     //   #1141	-> 1145
/*      */     //   #1142	-> 1152
/*      */     //   #1143	-> 1159
/*      */     //   #1146	-> 1166
/*      */     //   #1147	-> 1230
/*      */     //   #1149	-> 1233
/*      */     //   #1150	-> 1242
/*      */     //   #1151	-> 1251
/*      */     //   #1153	-> 1260
/*      */     //   #1154	-> 1386
/*      */     //   #1156	-> 1389
/*      */     //   #1158	-> 1404
/*      */     //   #1160	-> 1412
/*      */     //   #1162	-> 1422
/*      */     //   #1163	-> 1445
/*      */     //   #1165	-> 1448
/*      */     //   #1166	-> 1471
/*      */     //   #1168	-> 1474
/*      */     //   #1169	-> 1497
/*      */     //   #1171	-> 1500
/*      */     //   #1172	-> 1523
/*      */     //   #1174	-> 1526
/*      */     //   #1175	-> 1549
/*      */     //   #1178	-> 1552
/*      */     //   #1179	-> 1556
/*      */     //   #1181	-> 1564
/*      */     //   #1139	-> 1584
/*      */     //   #1137	-> 1590
/*      */     //   #1135	-> 1596
/*      */     //   #1187	-> 1602
/*      */     //   #1189	-> 1606
/*      */     //   #1191	-> 1614
/*      */     //   #1193	-> 1624
/*      */     //   #1200	-> 1636
/*      */     //   #1202	-> 1667
/*      */     //   #1204	-> 1672
/*      */     //   #1205	-> 1694
/*      */     //   #1207	-> 1745
/*      */     //   #1209	-> 1786
/*      */     //   #1218	-> 1789
/*      */     //   #1220	-> 1848
/*      */     //   #1221	-> 1856
/*      */     //   #1223	-> 1864
/*      */     //   #1229	-> 1880
/*      */     //   #1230	-> 1887
/*      */     //   #1232	-> 1897
/*      */     //   #1234	-> 1904
/*      */     //   #1236	-> 1925
/*      */     //   #1237	-> 1929
/*      */     //   #1240	-> 1937
/*      */     //   #1242	-> 1949
/*      */     //   #1243	-> 1964
/*      */     //   #1244	-> 1969
/*      */     //   #1245	-> 1980
/*      */     //   #1247	-> 1983
/*      */     //   #1249	-> 1999
/*      */     //   #1250	-> 2020
/*      */     //   #1253	-> 2030
/*      */     //   #1254	-> 2046
/*      */     //   #1256	-> 2049
/*      */     //   #1258	-> 2061
/*      */     //   #1259	-> 2076
/*      */     //   #1261	-> 2082
/*      */     //   #1262	-> 2090
/*      */     //   #1263	-> 2101
/*      */     //   #1293	-> 2110
/*      */     //   #1295	-> 2115
/*      */     //   #1297	-> 2133
/*      */     //   #1299	-> 2145
/*      */     //   #1302	-> 2177
/*      */     //   #1304	-> 2184
/*      */     //   #1309	-> 2203
/*      */     //   #1310	-> 2211
/*      */     //   #1312	-> 2214
/*      */     //   #1313	-> 2220
/*      */     //   #1315	-> 2225
/*      */     //   #1316	-> 2232
/*      */     //   #1318	-> 2290
/*      */     //   #1321	-> 2316
/*      */     //   #1324	-> 2319
/*      */     //   #1326	-> 2322
/*      */     //   #1328	-> 2329
/*      */     //   #1329	-> 2344
/*      */     //   #1331	-> 2351
/*      */     //   #1333	-> 2361
/*      */     //   #1334	-> 2373
/*      */     //   #1335	-> 2379
/*      */     //   #1337	-> 2382
/*      */     //   #1339	-> 2388
/*      */     //   #1341	-> 2398
/*      */     //   #1342	-> 2410
/*      */     //   #1343	-> 2423
/*      */     //   #1347	-> 2426
/*      */     //   #1349	-> 2472
/*      */     //   #1353	-> 2482
/*      */     //   #1355	-> 2488
/*      */     //   #1356	-> 2495
/*      */     //   #1358	-> 2505
/*      */     //   #1359	-> 2519
/*      */     //   #1361	-> 2525
/*      */     //   #1363	-> 2532
/*      */     //   #1364	-> 2557
/*      */     //   #1366	-> 2570
/*      */     //   #1368	-> 2580
/*      */     //   #1372	-> 2601
/*      */     //   #1376	-> 2607
/*      */     //   #1377	-> 2633
/*      */     //   #1379	-> 2640
/*      */     //   #1381	-> 2660
/*      */     //   #1382	-> 2666
/*      */     //   #1383	-> 2672
/*      */     //   #1385	-> 2678
/*      */     //   #1387	-> 2681
/*      */     //   #1389	-> 2696
/*      */     //   #1391	-> 2712
/*      */     //   #1393	-> 2728
/*      */     //   #1395	-> 2756
/*      */     //   #1396	-> 2759
/*      */     //   #1391	-> 2762
/*      */     //   #1389	-> 2768
/*      */     //   #1387	-> 2774
/*      */     //   #1402	-> 2780
/*      */     //   #1403	-> 2785
/*      */     //   #1405	-> 2792
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   438	31	1	load	I
/*      */     //   530	23	1	srt	Lnet/minecraft/SoonestReconnectionTime;
/*      */     //   880	70	4	item_stack	Lnet/minecraft/ItemStack;
/*      */     //   858	98	3	i	I
/*      */     //   1152	432	12	block_x	I
/*      */     //   1159	425	13	block_y	I
/*      */     //   1166	418	14	block_z	I
/*      */     //   1242	342	15	block_center_x	F
/*      */     //   1251	333	16	block_center_y	F
/*      */     //   1260	324	17	block_center_z	F
/*      */     //   1404	180	18	block	Lnet/minecraft/Block;
/*      */     //   1138	452	11	dy	I
/*      */     //   1126	470	10	dz	I
/*      */     //   1114	488	9	dx	I
/*      */     //   1672	114	11	fake_entity_id	I
/*      */     //   1636	150	10	block_pos	Lnet/minecraft/ChunkPosition;
/*      */     //   1614	175	9	i	Ljava/util/Iterator;
/*      */     //   1081	708	3	blocks	Ljava/util/List;
/*      */     //   1087	702	4	x	I
/*      */     //   1093	696	5	y	I
/*      */     //   1099	690	6	z	I
/*      */     //   1102	687	7	range	I
/*      */     //   1109	680	8	ranged_squared	I
/*      */     //   1964	82	3	var1	I
/*      */     //   1969	77	4	var2	[I
/*      */     //   1980	66	5	var3	Ljava/util/Iterator;
/*      */     //   1983	63	6	var4	I
/*      */     //   2203	116	9	chunk	Lnet/minecraft/Chunk;
/*      */     //   2145	174	8	var9	Lnet/minecraft/ChunkCoordIntPair;
/*      */     //   2373	6	9	var5	Lnet/minecraft/TileEntity;
/*      */     //   2410	13	9	var10	Lnet/minecraft/Chunk;
/*      */     //   2351	75	8	var11	Ljava/util/Iterator;
/*      */     //   2090	336	3	var6	Ljava/util/ArrayList;
/*      */     //   2101	325	4	var7	Ljava/util/Iterator;
/*      */     //   2110	316	5	var8	Ljava/util/ArrayList;
/*      */     //   2115	311	6	ms	J
/*      */     //   2716	52	11	dz	I
/*      */     //   2700	74	10	dx	I
/*      */     //   2685	95	9	dy	I
/*      */     //   2666	126	5	x	I
/*      */     //   2672	120	6	y	I
/*      */     //   2678	114	7	z	I
/*      */     //   2681	111	8	eligible	Z
/*      */     //   0	2793	0	this	Lnet/minecraft/ServerPlayer;
/*      */     //   818	1975	1	world	Lnet/minecraft/WorldServer;
/*      */     //   850	1943	2	chance_of_snow_items_melting	F
/*      */     //   2607	186	3	distance_sq_to_world_spawn	D
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
/*      */   public void onUpdateEntity() {
/*      */     try {
/* 1411 */       super.onUpdate();
/*      */       
/* 1413 */       for (int var1 = 0; var1 < this.inventory.getSizeInventory(); var1++) {
/*      */         
/* 1415 */         ItemStack var6 = this.inventory.getStackInSlot(var1);
/*      */         
/* 1417 */         if (var6 != null && Item.itemsList[var6.itemID].isMap() && this.playerNetServerHandler.packetSize() <= 5) {
/*      */           
/* 1419 */           Packet var8 = ((ItemMapBase)Item.itemsList[var6.itemID]).createMapDataPacket(var6, this.worldObj, this);
/*      */           
/* 1421 */           if (var8 != null)
/*      */           {
/* 1423 */             this.playerNetServerHandler.sendPacketToPlayer(var8);
/*      */           }
/*      */         } 
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
/* 1439 */       float health = getHealth();
/* 1440 */       int satiation = getSatiation();
/* 1441 */       int nutrition = getNutrition();
/* 1442 */       float hunger = this.foodStats.getHunger();
/*      */ 
/*      */       
/* 1445 */       if (health != this.lastHealth || satiation != this.last_satiation || nutrition != this.last_nutrition || this.vision_dimming > 0.0F) {
/*      */         
/* 1447 */         this.playerNetServerHandler.sendPacketToPlayer(new Packet8UpdateHealth(health, satiation, nutrition, this.vision_dimming));
/* 1448 */         this.lastHealth = health;
/*      */         
/* 1450 */         this.last_satiation = satiation;
/* 1451 */         this.last_nutrition = nutrition;
/* 1452 */         this.vision_dimming = 0.0F;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1457 */       if (getHealth() + getAbsorptionAmount() != this.field_130068_bO) {
/*      */         
/* 1459 */         this.field_130068_bO = getHealth() + getAbsorptionAmount();
/* 1460 */         Collection var5 = getWorldScoreboard().func_96520_a(ScoreObjectiveCriteria.health);
/* 1461 */         Iterator<ScoreObjective> var7 = var5.iterator();
/*      */         
/* 1463 */         while (var7.hasNext()) {
/*      */           
/* 1465 */           ScoreObjective var9 = var7.next();
/* 1466 */           getWorldScoreboard().func_96529_a(getEntityName(), var9).func_96651_a(Arrays.asList(new EntityPlayer[] { this }));
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1476 */       if (this.experience != this.last_experience)
/*      */       {
/* 1478 */         this.last_experience = this.experience;
/* 1479 */         this.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(this.experience));
/*      */       }
/*      */     
/* 1482 */     } catch (Throwable var4) {
/*      */       
/* 1484 */       CrashReport var2 = CrashReport.makeCrashReport(var4, "Ticking player");
/* 1485 */       CrashReportCategory var3 = var2.makeCategory("Player being ticked");
/* 1486 */       addEntityCrashInfo(var3);
/* 1487 */       throw new ReportedException(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource par1DamageSource) {
/* 1496 */     this.mcServer.getConfigurationManager().sendChatMsg(func_110142_aN().func_94546_b());
/*      */     
/* 1498 */     if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
/*      */     {
/* 1500 */       this.inventory.dropAllItems();
/*      */     }
/*      */     
/* 1503 */     Collection var2 = this.worldObj.getScoreboard().func_96520_a(ScoreObjectiveCriteria.deathCount);
/* 1504 */     Iterator<ScoreObjective> var3 = var2.iterator();
/*      */     
/* 1506 */     while (var3.hasNext()) {
/*      */       
/* 1508 */       ScoreObjective var4 = var3.next();
/* 1509 */       Score var5 = getWorldScoreboard().func_96529_a(getEntityName(), var4);
/* 1510 */       var5.func_96648_a();
/*      */     } 
/*      */     
/* 1513 */     EntityLivingBase var6 = func_94060_bK();
/*      */     
/* 1515 */     if (var6 != null)
/*      */     {
/* 1517 */       var6.addToPlayerScore(this, this.scoreValue);
/*      */     }
/*      */     
/* 1520 */     addStat(StatList.deathsStat, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1528 */     if (this.experience < getExperienceRequired(1)) {
/*      */       
/* 1530 */       this.respawn_experience = this.experience - getExperienceRequired(1);
/*      */       
/* 1532 */       if (this.respawn_experience < getExperienceRequired(-40)) {
/* 1533 */         this.respawn_experience = getExperienceRequired(-40);
/*      */       }
/* 1535 */       if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*      */         
/* 1537 */         (DedicatedServer.getOrCreateTournamentStanding(this)).experience = this.respawn_experience;
/* 1538 */         DedicatedServer.updateTournamentScoreOnClient(this, true);
/*      */       } 
/*      */     } 
/*      */     
/* 1542 */     this.respawn_countdown = 120;
/*      */     
/* 1544 */     if (this.mcServer instanceof IntegratedServer) {
/* 1545 */       ((IntegratedServer)this.mcServer).saveAllPlayersAndWorlds();
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
/*      */   public void afterRespawn() {
/* 1560 */     MinecraftServer.getServerConfigurationManager(this.mcServer).sendPlayerInfoToAllPlayers(true);
/*      */     
/* 1562 */     sendPacket(new Packet85SimpleSignal(EnumSignal.after_respawn));
/*      */     
/* 1564 */     this.initialInvulnerability = 60;
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
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 1616 */     boolean var3 = (this.mcServer.isDedicatedServer() && this.mcServer.isPVPEnabled() && "fall".equals((damage.getSource()).damageType));
/*      */     
/* 1618 */     if (!var3 && this.initialInvulnerability > 0 && damage.getSource() != DamageSource.outOfWorld)
/*      */     {
/*      */       
/* 1621 */       return null;
/*      */     }
/* 1623 */     if (damage.getSource() instanceof EntityDamageSource) {
/*      */       
/* 1625 */       Entity var4 = damage.getResponsibleEntity();
/*      */       
/* 1627 */       if (var4 instanceof EntityPlayer && !canAttackPlayer((EntityPlayer)var4))
/*      */       {
/*      */         
/* 1630 */         return null;
/*      */       }
/* 1632 */       if (var4 instanceof EntityArrow) {
/*      */         
/* 1634 */         EntityArrow arrow = (EntityArrow)var4;
/*      */         
/* 1636 */         if (arrow.shootingEntity instanceof EntityPlayer && !canAttackPlayer((EntityPlayer)arrow.shootingEntity))
/*      */         {
/*      */           
/* 1639 */           return null;
/*      */         }
/*      */       } 
/*      */     } 
/* 1643 */     return super.attackEntityFrom(damage);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canAttackPlayer(EntityPlayer par1EntityPlayer) {
/* 1648 */     return !this.mcServer.isPVPEnabled() ? false : super.canAttackPlayer(par1EntityPlayer);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onEntityDamaged(DamageSource damage_source, float amount) {
/* 1653 */     addHungerServerSide(damage_source.getHungerDamage());
/*      */     
/* 1655 */     sendPacket((new Packet85SimpleSignal(EnumSignal.damage_taken)).setShort(Math.min(Math.round(amount * 10.0F), 32767)));
/* 1656 */     addStat(StatList.damageTakenStat, Math.round(amount * 10.0F));
/*      */     
/* 1658 */     super.onEntityDamaged(damage_source, amount);
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
/*      */   public void travelInsideDimension(double x, double y, double z) {
/* 1672 */     this.last_experience = -1;
/* 1673 */     this.lastHealth = -1.0F;
/* 1674 */     this.last_nutrition = -1;
/*      */     
/* 1676 */     this.mcServer.getConfigurationManager().teleportPlayerInsideDimension(this, x, y, z, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travelToDimension(int par1) {
/* 1684 */     if (this.dimension == 1 && par1 == 1) {
/*      */       
/* 1686 */       triggerAchievement(AchievementList.theEnd2);
/* 1687 */       this.worldObj.removeEntity(this);
/* 1688 */       this.playerConqueredTheEnd = true;
/* 1689 */       this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(4, 0));
/*      */     }
/*      */     else {
/*      */       
/* 1693 */       WorldServer destination_world = this.mcServer.worldServerForDimension(par1);
/*      */       
/* 1695 */       if (destination_world.isUnderworld()) {
/* 1696 */         this.worldObj.worldInfo.setUnderworldVisited();
/*      */       }
/* 1698 */       if (destination_world.isTheNether()) {
/* 1699 */         this.worldObj.worldInfo.setNetherVisited();
/*      */       }
/* 1701 */       if (destination_world.isTheEnd() && destination_world.playerEntities.size() == 0) {
/* 1702 */         ((WorldProviderEnd)(this.mcServer.worldServerForDimension(par1)).provider).heal_ender_dragon = true;
/*      */       }
/* 1704 */       if (this.dimension == 0 && par1 == 1) {
/*      */         
/* 1706 */         triggerAchievement(AchievementList.theEnd);
/* 1707 */         ChunkCoordinates var2 = this.mcServer.worldServerForDimension(par1).getEntrancePortalLocation();
/*      */         
/* 1709 */         if (var2 != null)
/*      */         {
/* 1711 */           this.playerNetServerHandler.setPlayerLocation(var2.posX, var2.posY, var2.posZ, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1714 */         par1 = 1;
/*      */       }
/*      */       else {
/*      */         
/* 1718 */         triggerAchievement(AchievementList.portal);
/*      */       } 
/*      */       
/* 1721 */       this.mcServer.getConfigurationManager().transferPlayerToDimension(this, par1);
/*      */       
/* 1723 */       this.last_experience = -1;
/* 1724 */       this.lastHealth = -1.0F;
/*      */       
/* 1726 */       this.last_nutrition = -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendTileEntityToPlayer(TileEntity par1TileEntity) {
/* 1735 */     if (par1TileEntity != null) {
/*      */       
/* 1737 */       Packet var2 = par1TileEntity.getDescriptionPacket();
/*      */       
/* 1739 */       if (var2 != null)
/*      */       {
/* 1741 */         this.playerNetServerHandler.sendPacketToPlayer(var2);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onItemPickup(Entity par1Entity, int par2) {
/* 1751 */     super.onItemPickup(par1Entity, par2);
/* 1752 */     this.openContainer.detectAndSendChanges();
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
/*      */   public void tryToSleepInBedAt(int x, int y, int z) {
/* 1776 */     if (inBed() || !isEntityAlive() || !this.worldObj.provider.isSurfaceWorld()) {
/*      */       return;
/*      */     }
/* 1779 */     if (getHostileEntityNearBed(x, y, z) != null) {
/*      */       
/* 1781 */       addChatMessage("tile.bed.notSafe");
/*      */     }
/* 1783 */     else if (this.worldObj.isOutdoors(x, y, z)) {
/*      */       
/* 1785 */       addChatMessage("tile.bed.notSheltered");
/*      */     }
/* 1787 */     else if (isStarving()) {
/*      */       
/* 1789 */       addChatMessage("tile.bed.tooHungry");
/*      */     }
/* 1791 */     else if (!this.worldObj.isAirOrPassableBlock(x, y + 1, z, false)) {
/*      */       
/* 1793 */       addChatMessage("tile.bed.obstructed");
/*      */     }
/* 1795 */     else if (isPotionActive(Potion.poison)) {
/*      */       
/* 1797 */       addChatMessage("tile.bed.poisoned");
/*      */     }
/* 1799 */     else if (isHostileEntityDiggingNearBed(x, y, z)) {
/*      */       
/* 1801 */       addChatMessage("tile.bed.mobsDigging");
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1810 */       int direction = BlockBed.j(this.worldObj.getBlockMetadata(x, y, z));
/*      */       
/* 1812 */       getIntoBed(x, y, z, direction);
/*      */       
/* 1814 */       Packet17Sleep var5 = new Packet17Sleep(this, 0, x, y, z, direction);
/* 1815 */       getServerForPlayer().getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, var5);
/* 1816 */       this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 1817 */       this.playerNetServerHandler.sendPacketToPlayer(var5);
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
/*      */   public void wakeUpPlayer(boolean get_out_of_bed, Entity entity_to_look_at) {
/* 1842 */     if (this.conscious_state != EnumConsciousState.falling_asleep && this.conscious_state != EnumConsciousState.sleeping && !get_out_of_bed) {
/*      */       return;
/*      */     }
/*      */     
/* 1846 */     if (this.conscious_state == EnumConsciousState.falling_asleep || this.conscious_state == EnumConsciousState.sleeping) {
/*      */       
/* 1848 */       this.mcServer.sendWorldAgesToClient(this);
/*      */       
/* 1850 */       this.playerNetServerHandler.sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.start_waking_up));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1857 */     if (this.bed_location == null) {
/*      */       
/* 1859 */       this.conscious_state = EnumConsciousState.fully_awake;
/*      */       
/*      */       return;
/*      */     } 
/* 1863 */     if (get_out_of_bed) {
/* 1864 */       getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(this, 3, entity_to_look_at));
/*      */     }
/* 1866 */     super.wakeUpPlayer(get_out_of_bed, entity_to_look_at);
/*      */     
/* 1868 */     if (get_out_of_bed && this.playerNetServerHandler != null) {
/* 1869 */       this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity par1Entity) {
/* 1877 */     super.mountEntity(par1Entity);
/* 1878 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet39AttachEntity(0, this, this.ridingEntity));
/* 1879 */     this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFallState(double par1, boolean par3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateFlyingState(double par1, boolean par3) {
/* 1893 */     super.updateFallState(par1, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIEditSign(TileEntity par1TileEntity) {
/* 1901 */     if (par1TileEntity instanceof TileEntitySign) {
/*      */       
/* 1903 */       ((TileEntitySign)par1TileEntity).func_142010_a(this);
/* 1904 */       this.playerNetServerHandler.sendPacketToPlayer(new Packet133TileEditorOpen(0, par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void incrementWindowID() {
/* 1910 */     this.currentWindowId = this.currentWindowId % 100 + 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIWorkbench(int par1, int par2, int par3) {
/* 1918 */     incrementWindowID();
/*      */ 
/*      */     
/* 1921 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 1, "Crafting", 9, true)).setCoords(par1, par2, par3));
/*      */ 
/*      */     
/* 1924 */     this.openContainer = new ContainerWorkbench(this, par1, par2, par3);
/* 1925 */     this.openContainer.windowId = this.currentWindowId;
/* 1926 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIEnchantment(int par1, int par2, int par3, String par4Str) {
/* 1931 */     incrementWindowID();
/*      */ 
/*      */     
/* 1934 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 4, (par4Str == null) ? "" : par4Str, 9, (par4Str != null))).setCoords(par1, par2, par3));
/*      */     
/* 1936 */     this.openContainer = new ContainerEnchantment(this, this.worldObj, par1, par2, par3);
/* 1937 */     this.openContainer.windowId = this.currentWindowId;
/* 1938 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIAnvil(int x, int y, int z) {
/* 1948 */     incrementWindowID();
/*      */ 
/*      */     
/* 1951 */     TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
/*      */ 
/*      */ 
/*      */     
/* 1955 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 8, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
/*      */     
/* 1957 */     this.openContainer = new ContainerRepair(this, x, y, z);
/* 1958 */     this.openContainer.windowId = this.currentWindowId;
/* 1959 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIChestForMinecart(IInventory par1IInventory) {
/* 1964 */     if (this.openContainer != this.inventoryContainer) {
/* 1965 */       closeScreen();
/*      */     }
/* 1967 */     incrementWindowID();
/* 1968 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 12, par1IInventory.getCustomNameOrUnlocalized(), par1IInventory.getSizeInventory(), par1IInventory.hasCustomName()));
/* 1969 */     this.openContainer = new ContainerChest(this, par1IInventory);
/* 1970 */     this.openContainer.windowId = this.currentWindowId;
/* 1971 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIChest(int x, int y, int z, IInventory par1IInventory) {
/* 1980 */     if (this.openContainer != this.inventoryContainer)
/*      */     {
/* 1982 */       closeScreen();
/*      */     }
/*      */     
/* 1985 */     incrementWindowID();
/*      */     
/* 1987 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 0, par1IInventory.getCustomNameOrUnlocalized(), par1IInventory.getSizeInventory(), par1IInventory.hasCustomName())).setCoords(x, y, z));
/*      */     
/* 1989 */     this.openContainer = new ContainerChest(this, par1IInventory);
/* 1990 */     this.openContainer.windowId = this.currentWindowId;
/* 1991 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIHopper(TileEntityHopper par1TileEntityHopper) {
/* 1996 */     incrementWindowID();
/*      */     
/* 1998 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 9, par1TileEntityHopper.getCustomNameOrUnlocalized(), par1TileEntityHopper.getSizeInventory(), par1TileEntityHopper.hasCustomName())).setCoords(par1TileEntityHopper));
/*      */     
/* 2000 */     this.openContainer = new ContainerHopper(this, par1TileEntityHopper);
/* 2001 */     this.openContainer.windowId = this.currentWindowId;
/* 2002 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIHopperMinecart(EntityMinecartHopper par1EntityMinecartHopper) {
/* 2007 */     incrementWindowID();
/* 2008 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 13, par1EntityMinecartHopper.getCustomNameOrUnlocalized(), par1EntityMinecartHopper.getSizeInventory(), par1EntityMinecartHopper.hasCustomName()));
/*      */     
/* 2010 */     this.openContainer = new ContainerHopper(this, par1EntityMinecartHopper);
/* 2011 */     this.openContainer.windowId = this.currentWindowId;
/* 2012 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIFurnace(TileEntityFurnace par1TileEntityFurnace) {
/* 2020 */     incrementWindowID();
/*      */ 
/*      */     
/* 2023 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 2, par1TileEntityFurnace.getCustomNameOrUnlocalized(), par1TileEntityFurnace.getSizeInventory(), par1TileEntityFurnace.hasCustomName())).setCoords(par1TileEntityFurnace));
/*      */     
/* 2025 */     this.openContainer = new ContainerFurnace(this, par1TileEntityFurnace);
/* 2026 */     this.openContainer.windowId = this.currentWindowId;
/* 2027 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIDispenser(TileEntityDispenser par1TileEntityDispenser) {
/* 2035 */     incrementWindowID();
/*      */     
/* 2037 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, (par1TileEntityDispenser instanceof TileEntityDropper) ? 10 : 3, par1TileEntityDispenser.getCustomNameOrUnlocalized(), par1TileEntityDispenser.getSizeInventory(), par1TileEntityDispenser.hasCustomName())).setCoords(par1TileEntityDispenser));
/*      */     
/* 2039 */     this.openContainer = new ContainerDispenser(this, par1TileEntityDispenser);
/* 2040 */     this.openContainer.windowId = this.currentWindowId;
/* 2041 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBrewingStand(TileEntityBrewingStand par1TileEntityBrewingStand) {
/* 2049 */     incrementWindowID();
/*      */     
/* 2051 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 5, par1TileEntityBrewingStand.getCustomNameOrUnlocalized(), par1TileEntityBrewingStand.getSizeInventory(), par1TileEntityBrewingStand.hasCustomName())).setCoords(par1TileEntityBrewingStand));
/*      */     
/* 2053 */     this.openContainer = new ContainerBrewingStand(this, par1TileEntityBrewingStand);
/* 2054 */     this.openContainer.windowId = this.currentWindowId;
/* 2055 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBeacon(TileEntityBeacon par1TileEntityBeacon) {
/* 2063 */     incrementWindowID();
/*      */     
/* 2065 */     this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 7, par1TileEntityBeacon.getCustomNameOrUnlocalized(), par1TileEntityBeacon.getSizeInventory(), par1TileEntityBeacon.hasCustomName())).setCoords(par1TileEntityBeacon));
/*      */     
/* 2067 */     this.openContainer = new ContainerBeacon(this, par1TileEntityBeacon);
/* 2068 */     this.openContainer.windowId = this.currentWindowId;
/* 2069 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIMerchant(IMerchant par1IMerchant, String par2Str) {
/* 2074 */     incrementWindowID();
/*      */     
/* 2076 */     this.openContainer = new ContainerMerchant(this, par1IMerchant);
/* 2077 */     this.openContainer.windowId = this.currentWindowId;
/* 2078 */     this.openContainer.addCraftingToCrafters(this);
/* 2079 */     InventoryMerchant var3 = ((ContainerMerchant)this.openContainer).getMerchantInventory();
/* 2080 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 6, (par2Str == null) ? "" : par2Str, var3.getSizeInventory(), (par2Str != null)));
/* 2081 */     MerchantRecipeList var4 = par1IMerchant.getRecipes(this);
/*      */     
/* 2083 */     if (var4 != null) {
/*      */       
/*      */       try {
/*      */         
/* 2087 */         ByteArrayOutputStream var5 = new ByteArrayOutputStream();
/* 2088 */         DataOutputStream var6 = new DataOutputStream(var5);
/* 2089 */         var6.writeInt(this.currentWindowId);
/* 2090 */         var4.writeRecipiesToStream(var6);
/* 2091 */         this.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("MC|TrList", var5.toByteArray()));
/*      */       }
/* 2093 */       catch (IOException var7) {
/*      */         
/* 2095 */         var7.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIHorse(EntityHorse par1EntityHorse, IInventory par2IInventory) {
/* 2102 */     if (this.openContainer != this.inventoryContainer)
/*      */     {
/* 2104 */       closeScreen();
/*      */     }
/*      */     
/* 2107 */     incrementWindowID();
/* 2108 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 11, par2IInventory.getCustomNameOrUnlocalized(), par2IInventory.getSizeInventory(), par2IInventory.hasCustomName(), par1EntityHorse.entityId));
/*      */ 
/*      */     
/* 2111 */     this.openContainer = new ContainerHorseInventory(this, par2IInventory, par1EntityHorse);
/* 2112 */     this.openContainer.windowId = this.currentWindowId;
/* 2113 */     this.openContainer.addCraftingToCrafters(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSlotContents(Container par1Container, int par2, ItemStack par3ItemStack) {
/* 2122 */     if (!(par1Container.getSlot(par2) instanceof SlotCrafting))
/*      */     {
/* 2124 */       if (!this.playerInventoryBeingManipulated)
/*      */       {
/* 2126 */         this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(par1Container.windowId, par2, par3ItemStack));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendContainerToPlayer(Container par1Container) {
/* 2133 */     sendContainerAndContentsToPlayer(par1Container, par1Container.getInventory());
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendContainerAndContentsToPlayer(Container par1Container, List par2List) {
/* 2138 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet104WindowItems(par1Container.windowId, par2List));
/* 2139 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendProgressBarUpdate(Container par1Container, int par2, int par3) {
/* 2149 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet105UpdateProgressbar(par1Container.windowId, par2, par3));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeScreen() {
/* 2157 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet101CloseWindow(this.openContainer.windowId));
/* 2158 */     closeContainer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateHeldItem() {
/* 2166 */     if (!this.playerInventoryBeingManipulated)
/*      */     {
/* 2168 */       this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeContainer() {
/* 2177 */     this.openContainer.onContainerClosed(this);
/* 2178 */     this.openContainer = this.inventoryContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEntityActionState(float par1, float par2, boolean par3, boolean par4) {
/* 2183 */     if (this.ridingEntity != null) {
/*      */       
/* 2185 */       if (par1 >= -1.0F && par1 <= 1.0F)
/*      */       {
/* 2187 */         this.moveStrafing = par1;
/*      */       }
/*      */       
/* 2190 */       if (par2 >= -1.0F && par2 <= 1.0F)
/*      */       {
/* 2192 */         this.moveForward = par2;
/*      */       }
/*      */       
/* 2195 */       this.isJumping = par3;
/* 2196 */       setSneaking(par4);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addStatForThisWorldOnly(StatBase par1StatBase, int par2) {
/* 2202 */     int id = par1StatBase.statId;
/*      */     
/* 2204 */     if (StatList.isEitherZeroOrOne(par1StatBase)) {
/*      */       
/* 2206 */       int previous_value = this.stats.containsKey(Integer.valueOf(id)) ? ((Byte)this.stats.get(Integer.valueOf(id))).byteValue() : 0;
/*      */       
/* 2208 */       if (previous_value == 1)
/*      */         return; 
/* 2210 */       if (previous_value != 0) {
/* 2211 */         Minecraft.setErrorMessage("addStatForThisWorldOnly: invalid stat value for " + par1StatBase);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2216 */       if (par1StatBase.isAchievement()) {
/*      */         
/* 2218 */         sendPacket((new Packet85SimpleSignal(EnumSignal.achievement_unlocked)).setInteger(id));
/*      */         
/* 2220 */         this.worldObj.worldInfo.unlockAchievement((Achievement)par1StatBase, this);
/*      */       } 
/*      */       
/* 2223 */       this.stats.put(Integer.valueOf(id), new Byte((byte)1));
/* 2224 */       sendPacket(new Packet91PlayerStat(par1StatBase, 1L));
/*      */     }
/* 2226 */     else if (StatList.hasLongValue(par1StatBase)) {
/*      */       long value;
/*      */ 
/*      */       
/* 2230 */       if (this.stats.containsKey(Integer.valueOf(id))) {
/* 2231 */         value = par2 + ((Long)this.stats.get(Integer.valueOf(id))).longValue();
/*      */       } else {
/* 2233 */         value = par2;
/*      */       } 
/* 2235 */       this.stats.put(Integer.valueOf(id), new Long(value));
/* 2236 */       sendPacket(new Packet91PlayerStat(par1StatBase, value));
/*      */     }
/*      */     else {
/*      */       
/* 2240 */       if (this.stats.containsKey(Integer.valueOf(id))) {
/* 2241 */         par2 += ((Integer)this.stats.get(Integer.valueOf(id))).intValue();
/*      */       }
/* 2243 */       this.stats.put(Integer.valueOf(id), Integer.valueOf(par2));
/* 2244 */       sendPacket(new Packet91PlayerStat(par1StatBase, par2));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addStat(StatBase par1StatBase, int par2) {
/* 2253 */     if (par1StatBase != null) {
/*      */ 
/*      */       
/* 2256 */       if (!par1StatBase.isIndependent || par1StatBase == StatList.dropStat)
/*      */       {
/* 2258 */         this.playerNetServerHandler.sendPacketToPlayer(new Packet200Statistic(par1StatBase.statId, par2));
/*      */       }
/*      */       
/* 2261 */       if (par2 != 0)
/*      */       {
/* 2263 */         addStatForThisWorldOnly(par1StatBase, par2);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeStatsToNBT(NBTTagCompound par1NBTTagCompound) {
/* 2271 */     Iterator<Map.Entry> i = this.stats.entrySet().iterator();
/*      */     
/* 2273 */     while (i.hasNext()) {
/*      */       
/* 2275 */       Map.Entry entry = i.next();
/*      */       
/* 2277 */       int id = ((Integer)entry.getKey()).intValue();
/*      */       
/* 2279 */       String key = "" + id;
/*      */       
/* 2281 */       StatBase stat = StatList.getStat(id);
/*      */       
/* 2283 */       if (StatList.isEitherZeroOrOne(stat)) {
/* 2284 */         par1NBTTagCompound.setByte(key, ((Byte)entry.getValue()).byteValue()); continue;
/* 2285 */       }  if (StatList.hasLongValue(stat)) {
/* 2286 */         par1NBTTagCompound.setLong(key, ((Long)entry.getValue()).longValue()); continue;
/*      */       } 
/* 2288 */       par1NBTTagCompound.setInteger(key, ((Integer)entry.getValue()).intValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void readStatsFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 2294 */     Collection tags = par1NBTTagCompound.getTags();
/*      */     
/* 2296 */     Iterator<NBTBase> i = tags.iterator();
/*      */     
/* 2298 */     while (i.hasNext()) {
/*      */       
/* 2300 */       NBTBase tag = i.next();
/*      */       
/* 2302 */       int id = Integer.valueOf(tag.getName()).intValue();
/*      */       
/* 2304 */       StatBase stat = StatList.getStat(id);
/*      */       
/* 2306 */       if (StatList.isEitherZeroOrOne(stat)) {
/* 2307 */         this.stats.put(Integer.valueOf(id), Byte.valueOf(par1NBTTagCompound.getByte(tag.getName()))); continue;
/* 2308 */       }  if (StatList.hasLongValue(stat)) {
/* 2309 */         this.stats.put(Integer.valueOf(id), Long.valueOf(par1NBTTagCompound.getLong(tag.getName()))); continue;
/*      */       } 
/* 2311 */       this.stats.put(Integer.valueOf(id), Integer.valueOf(par1NBTTagCompound.getInteger(tag.getName())));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mountEntityAndWakeUp() {
/* 2317 */     if (this.riddenByEntity != null)
/*      */     {
/* 2319 */       this.riddenByEntity.mountEntity(this);
/*      */     }
/*      */ 
/*      */     
/* 2323 */     if (inBed())
/*      */     {
/*      */       
/* 2326 */       wakeUpPlayer(true, (Entity)null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerHealthUpdated() {
/* 2336 */     this.lastHealth = -1.0E8F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addChatMessage(String par1Str) {
/* 2344 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromTranslationKey(par1Str)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onItemUseFinish() {
/* 2354 */     super.onItemUseFinish();
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
/*      */   public void clonePlayer(EntityPlayer par1EntityPlayer, boolean par2) {
/* 2390 */     super.clonePlayer(par1EntityPlayer, par2);
/*      */     
/* 2392 */     this.last_experience = -1;
/* 2393 */     this.lastHealth = -1.0F;
/*      */     
/* 2395 */     this.last_nutrition = -1;
/* 2396 */     this.destroyedItemsNetCache.addAll(((ServerPlayer)par1EntityPlayer).destroyedItemsNetCache);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMalnourished() {
/* 2401 */     return (this.protein == 0 || this.phytonutrients == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDoubleMalnourished() {
/* 2406 */     return (this.protein == 0 && this.phytonutrients == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncClientPlayer() {
/* 2412 */     if (this.is_cursed) {
/*      */       
/* 2414 */       sendPacket((new Packet85SimpleSignal(EnumSignal.cursed)).setByte(this.curse_id));
/*      */       
/* 2416 */       if (this.curse_effect_known) {
/* 2417 */         sendPacket(new Packet85SimpleSignal(EnumSignal.curse_effect_learned));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2422 */     if (hasSkills()) {
/* 2423 */       sendPacket((new Packet85SimpleSignal(EnumSignal.skillset)).setInteger(getSkills()));
/*      */     }
/* 2425 */     sendPacket(new Packet70GameEvent(7, this.mcServer.getVillageConditions()));
/*      */     
/* 2427 */     sendAllStatsToClient();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onNewPotionEffect(PotionEffect par1PotionEffect) {
/* 2432 */     super.onNewPotionEffect(par1PotionEffect);
/* 2433 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.entityId, par1PotionEffect));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2) {
/* 2438 */     super.onChangedPotionEffect(par1PotionEffect, par2);
/* 2439 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.entityId, par1PotionEffect));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onFinishedPotionEffect(PotionEffect par1PotionEffect) {
/* 2444 */     super.onFinishedPotionEffect(par1PotionEffect);
/* 2445 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet42RemoveEntityEffect(this.entityId, par1PotionEffect));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndUpdate(double par1, double par3, double par5) {
/* 2453 */     this.playerNetServerHandler.setPlayerLocation(par1, par3, par5, this.rotationYaw, this.rotationPitch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCriticalHit(Entity par1Entity) {
/* 2461 */     getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(par1Entity, 6));
/*      */   }
/*      */ 
/*      */   
/*      */   public void onEnchantmentCritical(Entity par1Entity) {
/* 2466 */     getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(par1Entity, 7));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPlayerAbilities() {
/* 2474 */     if (this.playerNetServerHandler != null)
/*      */     {
/* 2476 */       this.playerNetServerHandler.sendPacketToPlayer(new Packet202PlayerAbilities(this.capabilities));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldServer getServerForPlayer() {
/* 2482 */     return (WorldServer)this.worldObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(EnumGameType par1EnumGameType) {
/* 2490 */     this.theItemInWorldManager.setGameType(par1EnumGameType);
/* 2491 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(3, par1EnumGameType.getID()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {
/* 2496 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(par1ChatMessageComponent));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCommandSenderUseCommand(int par1, String par2Str) {
/* 2504 */     if (Minecraft.inDevMode()) {
/* 2505 */       return true;
/*      */     }
/* 2507 */     return ("seed".equals(par2Str) && !this.mcServer.isDedicatedServer()) ? true : ((!"tell".equals(par2Str) && !"help".equals(par2Str) && !"me".equals(par2Str)) ? (this.mcServer.getConfigurationManager().isPlayerOpped(this.username) ? ((this.mcServer.func_110455_j() >= par1)) : false) : true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPlayerIP() {
/* 2515 */     String var1 = this.playerNetServerHandler.netManager.getSocketAddress().toString();
/* 2516 */     var1 = var1.substring(var1.indexOf("/") + 1);
/* 2517 */     var1 = var1.substring(0, var1.indexOf(":"));
/* 2518 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateClientInfo(Packet204ClientInfo par1Packet204ClientInfo) {
/* 2523 */     this.translator = par1Packet204ClientInfo.getLanguage();
/* 2524 */     int var2 = 256 >> par1Packet204ClientInfo.getRenderDistance();
/*      */     
/* 2526 */     if (var2 > 3 && var2 < 15)
/*      */     {
/* 2528 */       this.renderDistance = var2;
/*      */     }
/*      */     
/* 2531 */     this.chatVisibility = par1Packet204ClientInfo.getChatVisibility();
/* 2532 */     this.chatColours = par1Packet204ClientInfo.getChatColours();
/*      */     
/* 2534 */     if (this.mcServer.isSinglePlayer() && this.mcServer.getServerOwner().equals(this.username))
/*      */     {
/* 2536 */       this.mcServer.setDifficultyForAllWorlds(par1Packet204ClientInfo.getDifficulty());
/*      */     }
/*      */     
/* 2539 */     setHideCape(1, !par1Packet204ClientInfo.getShowCape());
/*      */   }
/*      */ 
/*      */   
/*      */   public int getChatVisibility() {
/* 2544 */     return this.chatVisibility;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestTexturePackLoad(String par1Str, int par2) {
/* 2552 */     String var3 = par1Str + "\000" + par2;
/* 2553 */     this.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("MC|TPack", var3.getBytes()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkCoordinates getPlayerCoordinates() {
/* 2561 */     return new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + 0.5D), MathHelper.floor_double(this.posZ));
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_143004_u() {
/* 2566 */     this.field_143005_bX = MinecraftServer.getSystemTimeMillis();
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
/*      */   private void updateMinecartFuelAmounts() {
/* 2580 */     if (!this.raS && this.ticks_logged_in >= 20 && this.ticks_logged_in % 20 == 0) {
/*      */ 
/*      */       
/* 2583 */       this.raS = true;
/*      */       
/* 2585 */       for (int i = 0; i < EntityMinecart.c.length; i++) {
/*      */ 
/*      */ 
/*      */         
/* 2589 */         if (!this.Sr[i]) {
/*      */           
/* 2591 */           this.raS = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/* 2597 */       if (!this.raS)
/*      */       {
/* 2599 */         if (this.ticks_logged_in < 1000) {
/*      */           
/* 2601 */           sendPacket((new Packet85SimpleSignal(EnumSignal.update_minecart_fuel)).setInteger(0).setEntityID(-100));
/*      */         }
/*      */         else {
/*      */           
/* 2605 */           EntityMinecart.notify(this);
/*      */           
/* 2607 */           this.raS = true;
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
/*      */   public boolean isSleeping() {
/* 2619 */     return (this.conscious_state == EnumConsciousState.sleeping);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacket(Packet packet) {
/* 2624 */     this.playerNetServerHandler.netManager.addToSendQueue(packet);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCursePending() {
/* 2629 */     if (this.worldObj.isRemote) {
/*      */       
/* 2631 */       Minecraft.setErrorMessage("hasCursePending: not meant to be called on client");
/* 2632 */       return false;
/*      */     } 
/*      */     
/* 2635 */     return ((WorldServer)this.worldObj).playerHasCursePending(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isImmuneByGrace() {
/* 2640 */     return (this.portal_grace_ticks > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onTransferToWorld() {
/* 2651 */     this.portal_grace_ticks = 600;
/*      */     
/* 2653 */     sendPacket(new Packet85SimpleSignal(EnumSignal.transfered_to_world));
/*      */     
/* 2655 */     super.onTransferToWorld();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOnHitList() {
/* 2660 */     if (this.is_not_on_hit_list) {
/* 2661 */       return false;
/*      */     }
/* 2663 */     if (this.username == null) {
/* 2664 */       return false;
/*      */     }
/* 2666 */     String[] hit_list = Minecraft.hit_list;
/*      */     
/* 2668 */     for (int i = 0; i < hit_list.length; i++) {
/*      */       
/* 2670 */       if (this.username.equalsIgnoreCase(hit_list[i])) {
/* 2671 */         return true;
/*      */       }
/*      */     } 
/* 2674 */     this.is_not_on_hit_list = true;
/*      */     
/* 2676 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendTryAutoSwitchOrRestockPacket(ItemStack item_stack) {
/* 2681 */     int item_subtype = item_stack.getHasSubtypes() ? item_stack.getItemSubtype() : 0;
/*      */     
/* 2683 */     if (item_subtype <= 127) {
/* 2684 */       sendPacket((new Packet85SimpleSignal(EnumSignal.try_auto_switch_or_restock)).setByte(item_subtype).setShort(item_stack.itemID));
/*      */     } else {
/* 2686 */       sendPacket((new Packet85SimpleSignal(EnumSignal.try_auto_switch_or_restock_large_subtype)).setInteger(item_subtype).setShort(item_stack.itemID));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void onKillEntity(EntityLivingBase victim) {
/* 2691 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*      */       
/* 2693 */       TournamentStanding ts = DedicatedServer.getOrCreateTournamentStanding(this);
/*      */       
/* 2695 */       if (victim instanceof EntitySkeleton) {
/* 2696 */         ts.killed_a_skeleton = true;
/* 2697 */       } else if (victim instanceof EntityZombie) {
/* 2698 */         ts.killed_a_zombie = true;
/* 2699 */       } else if (victim instanceof EntitySpider) {
/* 2700 */         ts.killed_a_spider = true;
/* 2701 */       } else if (victim instanceof EntityWoodSpider) {
/* 2702 */         ts.killed_a_wood_spider = true;
/* 2703 */       } else if (victim instanceof EntityCreeper) {
/* 2704 */         ts.killed_a_creeper = true;
/* 2705 */       } else if (victim instanceof EntitySlime) {
/* 2706 */         ts.killed_a_large_slime = (ts.killed_a_large_slime || ((EntitySlime)victim).getSize() == 4);
/* 2707 */       } else if (victim instanceof EntityGhoul) {
/* 2708 */         ts.killed_a_ghoul = true;
/* 2709 */       } else if (victim instanceof EntityWight) {
/* 2710 */         ts.killed_a_wight = true;
/* 2711 */       } else if (victim instanceof EntityInvisibleStalker) {
/* 2712 */         ts.killed_an_invisible_stalker = true;
/* 2713 */       } else if (victim instanceof EntityWitch) {
/* 2714 */         ts.killed_a_witch = true;
/* 2715 */       } else if (victim instanceof EntityShadow) {
/* 2716 */         ts.killed_a_shadow = true;
/* 2717 */       } else if (victim instanceof EntityHellhound) {
/* 2718 */         ts.killed_a_hellhound = true;
/* 2719 */       } else if (victim instanceof EntityDemonSpider) {
/* 2720 */         ts.killed_a_demon_spider = true;
/*      */       } 
/* 2722 */       DedicatedServer.updateTournamentScoreOnClient(this, true);
/*      */     } 
/*      */     
/* 2725 */     super.onKillEntity(victim);
/*      */   }
/*      */ 
/*      */   
/*      */   public String obf(String s) {
/* 2730 */     char[] chars = s.toCharArray();
/*      */     
/* 2732 */     for (int i = 0; i < chars.length; i++) {
/*      */       
/* 2734 */       int c = chars[i];
/*      */       
/* 2736 */       if (c >= 65 && c <= 90) {
/* 2737 */         c = 90 - c - 65;
/* 2738 */       } else if (c >= 97 && c <= 122) {
/* 2739 */         c = 122 - c - 97;
/* 2740 */       } else if (c >= 48 && c <= 57) {
/* 2741 */         c = 57 - c - 48;
/*      */       } 
/* 2743 */       chars[i] = (char)c;
/*      */     } 
/*      */     
/* 2746 */     return new String(chars);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getWetnessAndMalnourishmentHungerMultiplier() {
/* 2752 */     int x = getBlockPosX();
/* 2753 */     int y = getFootBlockPosY();
/* 2754 */     int z = getBlockPosZ();
/*      */ 
/*      */ 
/*      */     
/* 2758 */     float rain_factor = isInRain() ? (this.worldObj.isThundering(true) ? 0.5F : 0.25F) : 0.0F;
/* 2759 */     float immersion_factor = (this.worldObj.getBlockMaterial(x, y + 1, z) == Material.water) ? 0.5F : ((this.worldObj.getBlockMaterial(x, y, z) == Material.water) ? 0.25F : 0.0F);
/* 2760 */     float wetness_factor = Math.max(rain_factor, immersion_factor);
/*      */     
/* 2762 */     if (isInRain() && !this.worldObj.isThundering(true) && immersion_factor == 0.25F) {
/* 2763 */       wetness_factor += 0.125F;
/*      */     }
/* 2765 */     if (this.worldObj.isBiomeFreezing(x, z)) {
/* 2766 */       wetness_factor *= 2.0F;
/* 2767 */     } else if ((this.worldObj.getBiomeGenForCoords(x, z)).temperature >= BiomeGenBase.desertRiver.temperature) {
/* 2768 */       wetness_factor = 0.0F;
/*      */     } 
/* 2770 */     float malnourishment_factor = isMalnourished() ? 0.5F : 0.0F;
/*      */ 
/*      */     
/* 2773 */     return 1.0F + wetness_factor + malnourishment_factor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendWorldAgesToClient() {
/* 2779 */     this.mcServer.sendWorldAgesToClient(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public INetworkManager getNetManager() {
/* 2784 */     return this.playerNetServerHandler.getNetManager();
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */