/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
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
/*     */ public class EntityVillager
/*     */   extends EntityAgeable
/*     */   implements IMerchant, INpc
/*     */ {
/*     */   private int randomTickDivider;
/*     */   private boolean isMating;
/*     */   private boolean isPlaying;
/*     */   Village villageObj;
/*     */   private EntityPlayer buyingPlayer;
/*     */   private MerchantRecipeList buyingList;
/*     */   private int timeUntilReset;
/*     */   private boolean needsInitilization;
/*     */   private int wealth;
/*     */   private String lastBuyingPlayer;
/*     */   private boolean field_82190_bM;
/*     */   private float field_82191_bN;
/*  36 */   private static final Map villagerStockList = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private static final Map blacksmithSellingList = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public EntityVillager(World par1World) {
/*  46 */     this(par1World, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityVillager(World par1World, int par2) {
/*  51 */     super(par1World);
/*  52 */     setProfession(par2);
/*  53 */     setSize(0.6F, 1.8F);
/*  54 */     getNavigator().setBreakDoors(true);
/*  55 */     getNavigator().setAvoidsWater(true);
/*  56 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  57 */     this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
/*  58 */     this.tasks.addTask(1, new EntityAITradePlayer(this));
/*  59 */     this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
/*     */     
/*  61 */     this.tasks.addTask(2, new EntityAIMoveIndoors(this, 0.6F));
/*  62 */     this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
/*  63 */     this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
/*  64 */     this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
/*  65 */     this.tasks.addTask(6, new EntityAIVillagerMate(this));
/*  66 */     this.tasks.addTask(7, new EntityAIFollowGolem(this));
/*  67 */     this.tasks.addTask(8, new EntityAIPlay(this, 0.32D));
/*  68 */     this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/*  69 */     this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
/*  70 */     this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
/*  71 */     this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
/*     */     
/*  73 */     this.tasks.addTask(4, new EntityAISeekShelterFromRain(this, 0.6F, true));
/*  74 */     this.tasks.addTask(2, new EntityAIAvoidPotentialPredators(this, 0.6F, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  79 */     super.applyEntityAttributes();
/*  80 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITick() {
/*  96 */     if (--this.randomTickDivider <= 0) {
/*     */       
/*  98 */       this.worldObj.villageCollectionObj.addVillagerPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*  99 */       this.randomTickDivider = 70 + this.rand.nextInt(50);
/* 100 */       this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);
/*     */       
/* 102 */       if (this.villageObj == null) {
/*     */         
/* 104 */         detachHome();
/*     */       }
/*     */       else {
/*     */         
/* 108 */         ChunkCoordinates var1 = this.villageObj.getCenter();
/* 109 */         setHomeArea(var1.posX, var1.posY, var1.posZ, (int)(this.villageObj.getVillageRadius() * 0.6F));
/*     */         
/* 111 */         if (this.field_82190_bM) {
/*     */           
/* 113 */           this.field_82190_bM = false;
/* 114 */           this.villageObj.func_82683_b(5);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     if (!isTrading() && this.timeUntilReset > 0) {
/*     */       
/* 121 */       this.timeUntilReset--;
/*     */       
/* 123 */       if (this.timeUntilReset <= 0) {
/*     */         
/* 125 */         if (this.needsInitilization) {
/*     */           
/* 127 */           if (this.buyingList.size() > 1) {
/*     */             
/* 129 */             Iterator<E> var3 = this.buyingList.iterator();
/*     */             
/* 131 */             while (var3.hasNext()) {
/*     */               
/* 133 */               MerchantRecipe var2 = (MerchantRecipe)var3.next();
/*     */               
/* 135 */               if (var2.func_82784_g())
/*     */               {
/* 137 */                 var2.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 142 */           addDefaultEquipmentAndRecipies(1);
/* 143 */           this.needsInitilization = false;
/*     */           
/* 145 */           if (this.villageObj != null && this.lastBuyingPlayer != null) {
/*     */ 
/*     */             
/* 148 */             this.worldObj.setEntityState(this, EnumEntityState.villager_pleased);
/* 149 */             this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
/*     */           } 
/*     */         } 
/*     */         
/* 153 */         addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     super.updateAITick();
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 186 */     if (super.onEntityRightClicked(player, item_stack)) {
/* 187 */       return true;
/*     */     }
/* 189 */     if (isEntityAlive() && !isTrading() && !isChild()) {
/*     */       
/* 191 */       if (player.onServer()) {
/*     */         
/* 193 */         setCustomer(player);
/* 194 */         player.displayGUIMerchant(this, getCustomNameTag());
/*     */       } 
/*     */       
/* 197 */       return true;
/*     */     } 
/*     */     
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 205 */     super.entityInit();
/* 206 */     this.dataWatcher.addObject(16, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 214 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 215 */     par1NBTTagCompound.setInteger("Profession", getProfession());
/* 216 */     par1NBTTagCompound.setInteger("Riches", this.wealth);
/*     */     
/* 218 */     if (this.buyingList != null)
/*     */     {
/* 220 */       par1NBTTagCompound.setCompoundTag("Offers", this.buyingList.getRecipiesAsTags());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 229 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 230 */     setProfession(par1NBTTagCompound.getInteger("Profession"));
/* 231 */     this.wealth = par1NBTTagCompound.getInteger("Riches");
/*     */     
/* 233 */     if (par1NBTTagCompound.hasKey("Offers")) {
/*     */       
/* 235 */       NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Offers");
/* 236 */       this.buyingList = new MerchantRecipeList(var2);
/*     */       
/* 238 */       Iterator<E> i = this.buyingList.iterator();
/*     */       
/* 240 */       while (i.hasNext()) {
/*     */         
/* 242 */         MerchantRecipe recipe = (MerchantRecipe)i.next();
/*     */         
/* 244 */         if ((recipe.getItemToSell()).itemID == Item.expBottle.itemID) {
/* 245 */           recipe.setItemToSell(new ItemStack(Item.bottleOfDisenchanting)); continue;
/* 246 */         }  if ((recipe.getItemToSell()).itemID == Item.knifeCopper.itemID) {
/* 247 */           recipe.setItemToSell(new ItemStack(Item.daggerCopper)); continue;
/* 248 */         }  if ((recipe.getItemToSell()).itemID == Item.knifeIron.itemID) {
/* 249 */           recipe.setItemToSell(new ItemStack(Item.daggerIron));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 267 */     return isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 275 */     return "mob.villager.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 283 */     return "mob.villager.death";
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProfession(int par1) {
/* 288 */     this.dataWatcher.updateObject(16, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProfession() {
/* 293 */     return this.dataWatcher.getWatchableObjectInt(16);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMating() {
/* 298 */     return this.isMating;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMating(boolean par1) {
/* 303 */     this.isMating = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlaying(boolean par1) {
/* 308 */     this.isPlaying = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlaying() {
/* 313 */     return this.isPlaying;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRevengeTarget(EntityLivingBase par1EntityLivingBase) {
/* 318 */     super.setRevengeTarget(par1EntityLivingBase);
/*     */     
/* 320 */     if (this.villageObj != null && par1EntityLivingBase != null) {
/*     */       
/* 322 */       this.villageObj.addOrRenewAgressor(par1EntityLivingBase);
/*     */       
/* 324 */       if (par1EntityLivingBase instanceof EntityPlayer) {
/*     */         
/* 326 */         byte var2 = -1;
/*     */         
/* 328 */         if (isChild())
/*     */         {
/* 330 */           var2 = -3;
/*     */         }
/*     */         
/* 333 */         this.villageObj.setReputationForPlayer(((EntityPlayer)par1EntityLivingBase).getCommandSenderName(), var2);
/*     */         
/* 335 */         if (isEntityAlive())
/*     */         {
/*     */           
/* 338 */           this.worldObj.setEntityState(this, EnumEntityState.villager_displeased);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 349 */     if (this.villageObj != null) {
/*     */ 
/*     */       
/* 352 */       Entity var2 = par1DamageSource.getResponsibleEntity();
/*     */       
/* 354 */       if (var2 != null) {
/*     */         
/* 356 */         if (var2 instanceof EntityPlayer)
/*     */         {
/* 358 */           this.villageObj.setReputationForPlayer(((EntityPlayer)var2).getCommandSenderName(), -2);
/*     */         }
/* 360 */         else if (var2 instanceof IMob)
/*     */         {
/* 362 */           this.villageObj.endMatingSeason();
/*     */         }
/*     */       
/* 365 */       } else if (var2 == null) {
/*     */ 
/*     */         
/* 368 */         EntityPlayer var3 = this.worldObj.getClosestPlayerToEntity(this, 16.0D, false);
/*     */         
/* 370 */         if (var3 != null)
/*     */         {
/* 372 */           this.villageObj.endMatingSeason();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 377 */     super.onDeath(par1DamageSource);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomer(EntityPlayer par1EntityPlayer) {
/* 382 */     this.buyingPlayer = par1EntityPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer getCustomer() {
/* 387 */     return this.buyingPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTrading() {
/* 392 */     return (this.buyingPlayer != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void useRecipe(MerchantRecipe par1MerchantRecipe) {
/* 397 */     par1MerchantRecipe.incrementToolUses();
/* 398 */     this.livingSoundTime = -getTalkInterval();
/*     */     
/* 400 */     makeSound("mob.villager.yes");
/*     */     
/* 402 */     if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
/*     */       
/* 404 */       this.timeUntilReset = 40;
/* 405 */       this.needsInitilization = true;
/*     */       
/* 407 */       if (this.buyingPlayer != null) {
/*     */         
/* 409 */         this.lastBuyingPlayer = this.buyingPlayer.getCommandSenderName();
/*     */       }
/*     */       else {
/*     */         
/* 413 */         this.lastBuyingPlayer = null;
/*     */       } 
/*     */     } 
/*     */     
/* 417 */     if ((par1MerchantRecipe.getItemToBuy()).itemID == Item.emerald.itemID)
/*     */     {
/* 419 */       this.wealth += (par1MerchantRecipe.getItemToBuy()).stackSize;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110297_a_(ItemStack par1ItemStack) {
/* 425 */     if (!this.worldObj.isRemote && this.livingSoundTime > -getTalkInterval() + 20) {
/*     */       
/* 427 */       this.livingSoundTime = -getTalkInterval();
/*     */       
/* 429 */       if (par1ItemStack != null) {
/*     */ 
/*     */         
/* 432 */         makeSound("mob.villager.yes");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 437 */         makeSound("mob.villager.no");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
/* 444 */     if (this.buyingList == null)
/*     */     {
/* 446 */       addDefaultEquipmentAndRecipies(1);
/*     */     }
/*     */     
/* 449 */     return this.buyingList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float adjustProbability(float par1) {
/* 457 */     float var2 = par1 + this.field_82191_bN;
/* 458 */     return (var2 > 0.9F) ? (0.9F - var2 - 0.9F) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addDefaultEquipmentAndRecipies(int par1) {
/*     */     byte b;
/*     */     int var3[], var4[], var5;
/* 467 */     if (this.buyingList != null) {
/*     */       
/* 469 */       this.field_82191_bN = MathHelper.sqrt_float(this.buyingList.size()) * 0.2F;
/*     */     }
/*     */     else {
/*     */       
/* 473 */       this.field_82191_bN = 0.0F;
/*     */     } 
/*     */ 
/*     */     
/* 477 */     MerchantRecipeList var2 = new MerchantRecipeList();
/*     */ 
/*     */ 
/*     */     
/* 481 */     switch (getProfession()) {
/*     */       
/*     */       case 0:
/* 484 */         addMerchantItem(var2, Item.wheat.itemID, this.rand, adjustProbability(0.9F));
/* 485 */         addMerchantItem(var2, Block.cloth.blockID, this.rand, adjustProbability(0.5F));
/* 486 */         addMerchantItem(var2, Item.chickenRaw.itemID, this.rand, adjustProbability(0.5F));
/* 487 */         addMerchantItem(var2, Item.fishCooked.itemID, this.rand, adjustProbability(0.4F));
/* 488 */         addBlacksmithItem(var2, Item.bread.itemID, this.rand, adjustProbability(0.9F));
/* 489 */         addBlacksmithItem(var2, Item.melon.itemID, this.rand, adjustProbability(0.3F));
/* 490 */         addBlacksmithItem(var2, Item.appleRed.itemID, this.rand, adjustProbability(0.3F));
/* 491 */         addBlacksmithItem(var2, Item.cookie.itemID, this.rand, adjustProbability(0.3F));
/* 492 */         addBlacksmithItem(var2, Item.shears.itemID, this.rand, adjustProbability(0.3F));
/* 493 */         addBlacksmithItem(var2, Item.flintAndSteel.itemID, this.rand, adjustProbability(0.3F));
/* 494 */         addBlacksmithItem(var2, Item.chickenCooked.itemID, this.rand, adjustProbability(0.3F));
/*     */         
/* 496 */         addBlacksmithItem(var2, Item.arrowFlint.itemID, this.rand, adjustProbability(0.5F));
/*     */         
/* 498 */         if (this.rand.nextFloat() < adjustProbability(0.5F))
/*     */         {
/*     */           
/* 501 */           var2.add((E)new MerchantRecipe(new ItemStack(Block.gravel, 4), new ItemStack(Item.emerald), new ItemStack(Item.flint.itemID, 4 + this.rand.nextInt(2), 0)));
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 507 */         addMerchantItem(var2, Item.paper.itemID, this.rand, adjustProbability(0.8F));
/* 508 */         addMerchantItem(var2, Item.book.itemID, this.rand, adjustProbability(0.8F));
/* 509 */         addMerchantItem(var2, Item.writtenBook.itemID, this.rand, adjustProbability(0.3F));
/* 510 */         addBlacksmithItem(var2, Block.bookShelf.blockID, this.rand, adjustProbability(0.8F));
/* 511 */         addBlacksmithItem(var2, Block.glass.blockID, this.rand, adjustProbability(0.2F));
/* 512 */         addBlacksmithItem(var2, Item.compass.itemID, this.rand, adjustProbability(0.2F));
/* 513 */         addBlacksmithItem(var2, Item.pocketSundial.itemID, this.rand, adjustProbability(0.2F));
/*     */         
/* 515 */         if (this.rand.nextFloat() < adjustProbability(0.07F)) {
/*     */           
/* 517 */           Enchantment var8 = Enchantment.enchantmentsBookList[this.rand.nextInt(Enchantment.enchantmentsBookList.length)];
/*     */           
/* 519 */           int var10 = MathHelper.getRandomIntegerInRange(this.rand, 1, var8.getNumLevels());
/* 520 */           ItemStack var11 = Item.enchantedBook.getEnchantedItemStack(new EnchantmentData(var8, var10));
/* 521 */           int var6 = 2 + this.rand.nextInt(5 + var10 * 10) + 3 * var10;
/* 522 */           var2.add((E)new MerchantRecipe(new ItemStack(Item.book), new ItemStack(Item.emerald, var6), var11));
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 528 */         addBlacksmithItem(var2, Item.eyeOfEnder.itemID, this.rand, adjustProbability(0.3F));
/*     */         
/* 530 */         addBlacksmithItem(var2, Item.redstone.itemID, this.rand, adjustProbability(0.4F));
/* 531 */         addBlacksmithItem(var2, Block.glowStone.blockID, this.rand, adjustProbability(0.3F));
/*     */         
/* 533 */         var3 = new int[] { Item.swordCopper.itemID, Item.swordIron.itemID, Item.plateCopper.itemID, Item.plateIron.itemID, Item.axeCopper.itemID, Item.axeIron.itemID, Item.pickaxeCopper.itemID, Item.pickaxeIron.itemID };
/* 534 */         var4 = var3;
/* 535 */         var5 = var3.length;
/* 536 */         b = 0;
/*     */ 
/*     */ 
/*     */         
/* 540 */         while (b < var5) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 545 */           int var7 = var4[b];
/*     */           
/* 547 */           if (this.rand.nextFloat() < adjustProbability(0.05F))
/*     */           {
/* 549 */             var2.add((E)new MerchantRecipe(new ItemStack(var7, 1, 0), new ItemStack(Item.emerald, 2 + this.rand.nextInt(3), 0), EnchantmentHelper.addRandomEnchantment(this.rand, new ItemStack(var7, 1, 0), 5 + this.rand.nextInt(15))));
/*     */           }
/*     */           
/* 552 */           b++;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 557 */         addMerchantItem(var2, Item.coal.itemID, this.rand, adjustProbability(0.7F));
/* 558 */         addMerchantItem(var2, Item.ingotIron.itemID, this.rand, adjustProbability(0.5F));
/* 559 */         addMerchantItem(var2, Item.ingotGold.itemID, this.rand, adjustProbability(0.5F));
/*     */         
/* 561 */         addBlacksmithItem(var2, Item.swordIron.itemID, this.rand, adjustProbability(0.5F));
/*     */         
/* 563 */         addBlacksmithItem(var2, Item.axeIron.itemID, this.rand, adjustProbability(0.3F));
/*     */         
/* 565 */         addBlacksmithItem(var2, Item.pickaxeIron.itemID, this.rand, adjustProbability(0.5F));
/*     */         
/* 567 */         addBlacksmithItem(var2, Item.shovelIron.itemID, this.rand, adjustProbability(0.2F));
/*     */         
/* 569 */         addBlacksmithItem(var2, Item.hoeIron.itemID, this.rand, adjustProbability(0.2F));
/*     */         
/* 571 */         addBlacksmithItem(var2, Item.helmetIron.itemID, this.rand, adjustProbability(0.2F));
/* 572 */         addBlacksmithItem(var2, Item.plateIron.itemID, this.rand, adjustProbability(0.2F));
/* 573 */         addBlacksmithItem(var2, Item.legsIron.itemID, this.rand, adjustProbability(0.2F));
/* 574 */         addBlacksmithItem(var2, Item.bootsIron.itemID, this.rand, adjustProbability(0.2F));
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
/* 586 */         addBlacksmithItem(var2, Item.pickaxeCopper.itemID, this.rand, adjustProbability(0.5F));
/* 587 */         addBlacksmithItem(var2, Item.shovelCopper.itemID, this.rand, adjustProbability(0.2F));
/* 588 */         addBlacksmithItem(var2, Item.axeCopper.itemID, this.rand, adjustProbability(0.3F));
/* 589 */         addBlacksmithItem(var2, Item.hoeCopper.itemID, this.rand, adjustProbability(0.2F));
/*     */         
/* 591 */         addBlacksmithItem(var2, Item.daggerCopper.itemID, this.rand, adjustProbability(0.5F));
/* 592 */         addBlacksmithItem(var2, Item.swordCopper.itemID, this.rand, adjustProbability(0.5F));
/*     */ 
/*     */         
/* 595 */         addBlacksmithItem(var2, Item.daggerIron.itemID, this.rand, adjustProbability(0.5F));
/*     */         
/* 597 */         addBlacksmithItem(var2, Item.helmetCopper.itemID, this.rand, adjustProbability(0.2F));
/* 598 */         addBlacksmithItem(var2, Item.plateCopper.itemID, this.rand, adjustProbability(0.2F));
/* 599 */         addBlacksmithItem(var2, Item.legsCopper.itemID, this.rand, adjustProbability(0.2F));
/* 600 */         addBlacksmithItem(var2, Item.bootsCopper.itemID, this.rand, adjustProbability(0.2F));
/*     */         
/* 602 */         addBlacksmithItem(var2, Item.helmetChainCopper.itemID, this.rand, adjustProbability(0.1F));
/* 603 */         addBlacksmithItem(var2, Item.plateChainCopper.itemID, this.rand, adjustProbability(0.1F));
/* 604 */         addBlacksmithItem(var2, Item.legsChainCopper.itemID, this.rand, adjustProbability(0.1F));
/* 605 */         addBlacksmithItem(var2, Item.bootsChainCopper.itemID, this.rand, adjustProbability(0.1F));
/*     */         
/* 607 */         addBlacksmithItem(var2, Item.helmetChainIron.itemID, this.rand, adjustProbability(0.1F));
/* 608 */         addBlacksmithItem(var2, Item.plateChainIron.itemID, this.rand, adjustProbability(0.1F));
/* 609 */         addBlacksmithItem(var2, Item.legsChainIron.itemID, this.rand, adjustProbability(0.1F));
/* 610 */         addBlacksmithItem(var2, Item.bootsChainIron.itemID, this.rand, adjustProbability(0.1F));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 615 */         addMerchantItem(var2, Item.coal.itemID, this.rand, adjustProbability(0.7F));
/* 616 */         addMerchantItem(var2, Item.porkRaw.itemID, this.rand, adjustProbability(0.5F));
/* 617 */         addMerchantItem(var2, Item.beefRaw.itemID, this.rand, adjustProbability(0.5F));
/*     */ 
/*     */ 
/*     */         
/* 621 */         addMerchantItem(var2, Item.lambchopRaw.itemID, this.rand, adjustProbability(0.5F));
/*     */ 
/*     */ 
/*     */         
/* 625 */         addBlacksmithItem(var2, Item.saddle.itemID, this.rand, adjustProbability(0.1F));
/* 626 */         addBlacksmithItem(var2, Item.plateLeather.itemID, this.rand, adjustProbability(0.3F));
/* 627 */         addBlacksmithItem(var2, Item.bootsLeather.itemID, this.rand, adjustProbability(0.3F));
/* 628 */         addBlacksmithItem(var2, Item.helmetLeather.itemID, this.rand, adjustProbability(0.3F));
/* 629 */         addBlacksmithItem(var2, Item.legsLeather.itemID, this.rand, adjustProbability(0.3F));
/* 630 */         addBlacksmithItem(var2, Item.porkCooked.itemID, this.rand, adjustProbability(0.3F));
/* 631 */         addBlacksmithItem(var2, Item.beefCooked.itemID, this.rand, adjustProbability(0.3F));
/*     */ 
/*     */ 
/*     */         
/* 635 */         addBlacksmithItem(var2, Item.lambchopCooked.itemID, this.rand, adjustProbability(0.3F));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 640 */     if (var2.isEmpty())
/*     */     {
/* 642 */       addMerchantItem(var2, Item.ingotGold.itemID, this.rand, 1.0F);
/*     */     }
/*     */     
/* 645 */     Collections.shuffle(var2);
/*     */     
/* 647 */     if (this.buyingList == null)
/*     */     {
/* 649 */       this.buyingList = new MerchantRecipeList();
/*     */     }
/*     */     
/* 652 */     for (int var9 = 0; var9 < par1 && var9 < var2.size(); var9++)
/*     */     {
/* 654 */       this.buyingList.addToListWithCheck((MerchantRecipe)var2.get(var9));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
/* 665 */     if (par2Random.nextFloat() < par3)
/*     */     {
/* 667 */       par0MerchantRecipeList.add((E)new MerchantRecipe(getRandomSizedStack(par1, par2Random), Item.emerald));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack getRandomSizedStack(int par0, Random par1Random) {
/* 673 */     return new ItemStack(par0, getRandomCountForItem(par0, par1Random), 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getRandomCountForItem(int par0, Random par1Random) {
/* 681 */     Tuple var2 = (Tuple)villagerStockList.get(Integer.valueOf(par0));
/* 682 */     return (var2 == null) ? 1 : ((((Integer)var2.getFirst()).intValue() >= ((Integer)var2.getSecond()).intValue()) ? ((Integer)var2.getFirst()).intValue() : (((Integer)var2.getFirst()).intValue() + par1Random.nextInt(((Integer)var2.getSecond()).intValue() - ((Integer)var2.getFirst()).intValue())));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
/* 687 */     if (par2Random.nextFloat() < par3) {
/*     */       ItemStack var5, var6;
/* 689 */       int var4 = getRandomCountForBlacksmithItem(par1, par2Random);
/*     */ 
/*     */ 
/*     */       
/* 693 */       if (var4 < 0) {
/*     */         
/* 695 */         var5 = new ItemStack(Item.emerald.itemID, 1, 0);
/* 696 */         var6 = new ItemStack(par1, -var4, 0);
/*     */         
/* 698 */         if (var6.stackSize > var6.getMaxStackSize()) {
/* 699 */           var6.stackSize = var6.getMaxStackSize();
/*     */         }
/*     */       } else {
/*     */         
/* 703 */         var5 = new ItemStack(Item.emerald.itemID, var4, 0);
/* 704 */         var6 = new ItemStack(par1, 1, 0);
/*     */         
/* 706 */         if (var5.stackSize > var5.getMaxStackSize()) {
/* 707 */           var5.stackSize = var5.getMaxStackSize();
/*     */         }
/*     */       } 
/* 710 */       par0MerchantRecipeList.add((E)new MerchantRecipe(var5, var6));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getRandomCountForBlacksmithItem(int par0, Random par1Random) {
/* 716 */     Tuple var2 = (Tuple)blacksmithSellingList.get(Integer.valueOf(par0));
/* 717 */     return (var2 == null) ? 1 : ((((Integer)var2.getFirst()).intValue() >= ((Integer)var2.getSecond()).intValue()) ? ((Integer)var2.getFirst()).intValue() : (((Integer)var2.getFirst()).intValue() + par1Random.nextInt(((Integer)var2.getSecond()).intValue() - ((Integer)var2.getFirst()).intValue())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 724 */     if (par1 == EnumEntityState.villager_mated) {
/*     */ 
/*     */       
/* 727 */       generateRandomParticles(EnumParticle.heart);
/*     */     
/*     */     }
/* 730 */     else if (par1 == EnumEntityState.villager_displeased) {
/*     */ 
/*     */       
/* 733 */       generateRandomParticles(EnumParticle.angryVillager);
/*     */     
/*     */     }
/* 736 */     else if (par1 == EnumEntityState.villager_pleased) {
/*     */ 
/*     */       
/* 739 */       generateRandomParticles(EnumParticle.happyVillager);
/*     */     }
/*     */     else {
/*     */       
/* 743 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateRandomParticles(EnumParticle particle) {
/* 753 */     for (int var2 = 0; var2 < 5; var2++) {
/*     */       
/* 755 */       double var3 = this.rand.nextGaussian() * 0.02D;
/* 756 */       double var5 = this.rand.nextGaussian() * 0.02D;
/* 757 */       double var7 = this.rand.nextGaussian() * 0.02D;
/*     */       
/* 759 */       this.worldObj.spawnParticle(particle, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 1.0D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var3, var5, var7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 765 */     par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
/* 766 */     setProfession(this.worldObj.rand.nextInt(5));
/* 767 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82187_q() {
/* 772 */     this.field_82190_bM = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityVillager func_90012_b(EntityAgeable par1EntityAgeable) {
/* 777 */     EntityVillager var2 = new EntityVillager(this.worldObj);
/* 778 */     var2.onSpawnWithEgg((EntityLivingData)null);
/* 779 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowLeashing() {
/* 784 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 789 */     return func_90012_b(par1EntityAgeable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addToVillagerStockList(Item item, int min_count, int max_count) {
/* 796 */     if (min_count > item.getItemStackLimit(0, 0)) {
/* 797 */       min_count = item.getItemStackLimit(0, 0);
/*     */     }
/* 799 */     if (max_count > item.getItemStackLimit(0, 0)) {
/* 800 */       max_count = item.getItemStackLimit(0, 0);
/*     */     }
/* 802 */     villagerStockList.put(Integer.valueOf(item.itemID), new Tuple(Integer.valueOf(min_count), Integer.valueOf(max_count)));
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
/*     */   static {
/* 831 */     addToVillagerStockList(Item.coal, 16, 24);
/* 832 */     addToVillagerStockList(Item.ingotIron, 4, 8);
/* 833 */     addToVillagerStockList(Item.ingotGold, 4, 8);
/* 834 */     addToVillagerStockList(Item.paper, 24, 36);
/* 835 */     addToVillagerStockList(Item.book, 11, 13);
/* 836 */     addToVillagerStockList(Item.writtenBook, 1, 1);
/* 837 */     addToVillagerStockList(Item.enderPearl, 3, 4);
/* 838 */     addToVillagerStockList(Item.eyeOfEnder, 2, 3);
/* 839 */     addToVillagerStockList(Item.porkRaw, 14, 18);
/* 840 */     addToVillagerStockList(Item.beefRaw, 14, 18);
/* 841 */     addToVillagerStockList(Item.chickenRaw, 14, 18);
/* 842 */     addToVillagerStockList(Item.fishCooked, 9, 13);
/* 843 */     addToVillagerStockList(Item.seeds, 34, 48);
/* 844 */     addToVillagerStockList(Item.melonSeeds, 30, 38);
/* 845 */     addToVillagerStockList(Item.pumpkinSeeds, 30, 38);
/* 846 */     addToVillagerStockList(Item.wheat, 18, 22);
/* 847 */     addToVillagerStockList(Item.getItem(Block.cloth.blockID), 14, 22);
/* 848 */     addToVillagerStockList(Item.rottenFlesh, 36, 64);
/* 849 */     addToVillagerStockList(Item.lambchopRaw, 14, 18);
/*     */     
/* 851 */     blacksmithSellingList.put(Integer.valueOf(Item.flintAndSteel.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 852 */     blacksmithSellingList.put(Integer.valueOf(Item.shears.itemID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 853 */     blacksmithSellingList.put(Integer.valueOf(Item.swordIron.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/*     */     
/* 855 */     blacksmithSellingList.put(Integer.valueOf(Item.axeIron.itemID), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/*     */     
/* 857 */     blacksmithSellingList.put(Integer.valueOf(Item.pickaxeIron.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
/*     */     
/* 859 */     blacksmithSellingList.put(Integer.valueOf(Item.shovelIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 861 */     blacksmithSellingList.put(Integer.valueOf(Item.hoeIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 863 */     blacksmithSellingList.put(Integer.valueOf(Item.bootsIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 865 */     blacksmithSellingList.put(Integer.valueOf(Item.helmetIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 867 */     blacksmithSellingList.put(Integer.valueOf(Item.plateIron.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
/*     */     
/* 869 */     blacksmithSellingList.put(Integer.valueOf(Item.legsIron.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 877 */     blacksmithSellingList.put(Integer.valueOf(Item.helmetChainIron.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 878 */     blacksmithSellingList.put(Integer.valueOf(Item.plateChainIron.itemID), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
/* 879 */     blacksmithSellingList.put(Integer.valueOf(Item.legsChainIron.itemID), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
/* 880 */     blacksmithSellingList.put(Integer.valueOf(Item.bootsChainIron.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/*     */     
/* 882 */     blacksmithSellingList.put(Integer.valueOf(Item.bread.itemID), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
/* 883 */     blacksmithSellingList.put(Integer.valueOf(Item.melon.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 884 */     blacksmithSellingList.put(Integer.valueOf(Item.appleRed.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 885 */     blacksmithSellingList.put(Integer.valueOf(Item.cookie.itemID), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
/* 886 */     blacksmithSellingList.put(Integer.valueOf(Block.glass.blockID), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
/* 887 */     blacksmithSellingList.put(Integer.valueOf(Block.bookShelf.blockID), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 888 */     blacksmithSellingList.put(Integer.valueOf(Item.plateLeather.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
/* 889 */     blacksmithSellingList.put(Integer.valueOf(Item.bootsLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 890 */     blacksmithSellingList.put(Integer.valueOf(Item.helmetLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 891 */     blacksmithSellingList.put(Integer.valueOf(Item.legsLeather.itemID), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 892 */     blacksmithSellingList.put(Integer.valueOf(Item.saddle.itemID), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/*     */     
/* 894 */     blacksmithSellingList.put(Integer.valueOf(Item.redstone.itemID), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
/* 895 */     blacksmithSellingList.put(Integer.valueOf(Item.compass.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 896 */     blacksmithSellingList.put(Integer.valueOf(Item.pocketSundial.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 897 */     blacksmithSellingList.put(Integer.valueOf(Block.glowStone.blockID), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
/* 898 */     blacksmithSellingList.put(Integer.valueOf(Item.porkCooked.itemID), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 899 */     blacksmithSellingList.put(Integer.valueOf(Item.beefCooked.itemID), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 900 */     blacksmithSellingList.put(Integer.valueOf(Item.chickenCooked.itemID), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
/* 901 */     blacksmithSellingList.put(Integer.valueOf(Item.eyeOfEnder.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/*     */     
/* 903 */     blacksmithSellingList.put(Integer.valueOf(Item.arrowFlint.itemID), new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
/*     */ 
/*     */ 
/*     */     
/* 907 */     blacksmithSellingList.put(Integer.valueOf(Item.pickaxeCopper.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
/* 908 */     blacksmithSellingList.put(Integer.valueOf(Item.shovelCopper.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 909 */     blacksmithSellingList.put(Integer.valueOf(Item.axeCopper.itemID), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/* 910 */     blacksmithSellingList.put(Integer.valueOf(Item.hoeCopper.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 912 */     blacksmithSellingList.put(Integer.valueOf(Item.daggerCopper.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 913 */     blacksmithSellingList.put(Integer.valueOf(Item.swordCopper.itemID), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/*     */ 
/*     */     
/* 916 */     blacksmithSellingList.put(Integer.valueOf(Item.daggerIron.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 918 */     blacksmithSellingList.put(Integer.valueOf(Item.helmetCopper.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 919 */     blacksmithSellingList.put(Integer.valueOf(Item.plateCopper.itemID), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
/* 920 */     blacksmithSellingList.put(Integer.valueOf(Item.legsCopper.itemID), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 921 */     blacksmithSellingList.put(Integer.valueOf(Item.bootsCopper.itemID), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/*     */     
/* 923 */     blacksmithSellingList.put(Integer.valueOf(Item.helmetChainCopper.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 924 */     blacksmithSellingList.put(Integer.valueOf(Item.plateChainCopper.itemID), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
/* 925 */     blacksmithSellingList.put(Integer.valueOf(Item.legsChainCopper.itemID), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
/* 926 */     blacksmithSellingList.put(Integer.valueOf(Item.bootsChainCopper.itemID), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/*     */     
/* 928 */     blacksmithSellingList.put(Integer.valueOf(Item.lambchopCooked.itemID), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int x, int y, int z) {
/* 933 */     return this.worldObj.isInRain(x, y + 1, z) ? -1.0F : super.getBlockPathWeight(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 938 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */