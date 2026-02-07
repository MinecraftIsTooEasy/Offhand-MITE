/*      */ package net.minecraft;
/*      */ 
/*      */ import com.google.common.collect.HashMultimap;
/*      */ import com.google.common.collect.Multimap;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class ItemStack
/*      */ {
/*   17 */   public static final DecimalFormat field_111284_a = new DecimalFormat("#.###");
/*      */ 
/*      */ 
/*      */   
/*      */   public int stackSize;
/*      */ 
/*      */ 
/*      */   
/*      */   public int animationsToGo;
/*      */ 
/*      */ 
/*      */   
/*      */   public int itemID;
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound stackTagCompound;
/*      */ 
/*      */   
/*      */   private int subtype;
/*      */ 
/*      */   
/*      */   private int damage;
/*      */ 
/*      */   
/*      */   private EntityItemFrame itemFrame;
/*      */ 
/*      */   
/*      */   private EnumQuality quality;
/*      */ 
/*      */   
/*      */   private boolean is_artifact;
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack(Block par1Block) {
/*   53 */     this(par1Block, 1);
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
/*      */   public ItemStack(int item_id) {
/*   96 */     this(item_id, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack(int item_id, int stack_size) {
/*  102 */     this(item_id, stack_size, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack(Block block, int stack_size) {
/*  108 */     this(block.blockID, stack_size, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Block block, int stack_size, int subtype) {
/*  113 */     this(block.blockID, stack_size, subtype);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack(Item item) {
/*  119 */     this(item.itemID, 1, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack(Item item, int stack_size) {
/*  125 */     this(item.itemID, stack_size, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Item item, int stack_size, int subtype) {
/*  130 */     this(item.itemID, stack_size, subtype);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(int id, int stack_size, int subtype) {
/*  135 */     this.itemID = id;
/*  136 */     this.stackSize = stack_size;
/*      */     
/*  138 */     setItemSubtype(subtype);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ItemStack loadItemStackFromNBT(NBTTagCompound par0NBTTagCompound) {
/*  143 */     if (!par0NBTTagCompound.hasKey("id")) {
/*  144 */       return null;
/*      */     }
/*  146 */     ItemStack var1 = new ItemStack();
/*  147 */     var1.readFromNBT(par0NBTTagCompound);
/*  148 */     return (var1.getItem() != null) ? var1 : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ItemStack() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack splitStack(int par1) {
/*  161 */     ItemStack var2 = (new ItemStack(this.itemID, par1, this.subtype)).setItemDamage(this.damage);
/*      */     
/*  163 */     var2.quality = this.quality;
/*  164 */     var2.is_artifact = this.is_artifact;
/*      */     
/*  166 */     if (this.stackTagCompound != null)
/*      */     {
/*  168 */       var2.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
/*      */     }
/*      */     
/*  171 */     this.stackSize -= par1;
/*  172 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getItem() {
/*  180 */     return Item.itemsList[this.itemID];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Icon getIconIndex() {
/*  188 */     return getItem().getIconIndex(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemSpriteNumber() {
/*  193 */     return getItem().getSpriteNumber();
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
/*      */   public float getStrVsBlock(Block block, int metadata) {
/*  239 */     return getItem().getStrVsBlock(block, metadata);
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
/*      */   public void onItemUseFinish(World par1World, EntityPlayer par2EntityPlayer) {
/*  259 */     getItem().onItemUseFinish(this, par1World, par2EntityPlayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  267 */     par1NBTTagCompound.setShort("id", (short)this.itemID);
/*  268 */     par1NBTTagCompound.setByte("Count", (byte)this.stackSize);
/*      */ 
/*      */     
/*  271 */     par1NBTTagCompound.setInteger("damage", this.damage);
/*  272 */     par1NBTTagCompound.setShort("subtype", (short)this.subtype);
/*      */     
/*  274 */     if (this.stackTagCompound != null) {
/*      */ 
/*      */ 
/*      */       
/*  278 */       NBTTagCompound effective_stackTagCompound = this.stackTagCompound;
/*      */       
/*  280 */       if (ItemReferencedBook.isReferencedBook(this)) {
/*      */         
/*  282 */         effective_stackTagCompound = new NBTTagCompound();
/*  283 */         effective_stackTagCompound.setInteger("reference_index", ItemReferencedBook.getReferenceIndex(this));
/*      */       } 
/*      */       
/*  286 */       par1NBTTagCompound.setTag("tag", effective_stackTagCompound);
/*      */     } 
/*      */     
/*  289 */     if (getItem().hasQuality()) {
/*  290 */       par1NBTTagCompound.setByte("quality", (byte)getQuality().ordinal());
/*      */     }
/*  292 */     if (this.is_artifact) {
/*  293 */       par1NBTTagCompound.setBoolean("is_artifact", this.is_artifact);
/*      */     }
/*  295 */     return par1NBTTagCompound;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  305 */     this.itemID = par1NBTTagCompound.getShort("id");
/*  306 */     this.stackSize = par1NBTTagCompound.getByte("Count");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  314 */     if (this.itemID <= 0) {
/*  315 */       (new Exception()).printStackTrace();
/*      */     }
/*  317 */     if (par1NBTTagCompound.hasKey("subtype")) {
/*      */       
/*  319 */       setItemSubtype(par1NBTTagCompound.getShort("subtype"));
/*  320 */       setItemDamage(par1NBTTagCompound.getInteger("damage"));
/*      */     }
/*      */     else {
/*      */       
/*  324 */       if (Minecraft.inDevMode()) {
/*  325 */         System.out.println("Importing item stack " + getItem() + ", id=" + this.itemID);
/*      */       }
/*  327 */       if (isItemStackDamageable() && getHasSubtypes()) {
/*      */         
/*  329 */         Item item1 = getItem();
/*      */         
/*  331 */         if (item1 instanceof ItemAnvilBlock)
/*      */         {
/*  333 */           setItemSubtype(par1NBTTagCompound.getShort("Damage"));
/*      */         }
/*      */         else
/*      */         {
/*  337 */           Minecraft.setErrorMessage("Unhandled item import, setting damage for: " + this);
/*  338 */           setItemDamage(par1NBTTagCompound.getShort("Damage"));
/*      */         }
/*      */       
/*  341 */       } else if (isItemStackDamageable()) {
/*      */         
/*  343 */         setItemDamage(par1NBTTagCompound.getShort("Damage"));
/*      */       }
/*      */       else {
/*      */         
/*  347 */         setItemSubtype(par1NBTTagCompound.getShort("Damage"));
/*      */       } 
/*      */     } 
/*      */     
/*  351 */     if (par1NBTTagCompound.hasKey("tag")) {
/*      */       
/*  353 */       this.stackTagCompound = par1NBTTagCompound.getCompoundTag("tag");
/*      */       
/*  355 */       if (ItemReferencedBook.isReferencedBook(this))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  364 */         setTagCompound(ItemReferencedBook.generateBookContents(ItemReferencedBook.getReferenceIndex(this)));
/*      */       }
/*      */     } 
/*      */     
/*  368 */     Item item = getItem();
/*      */     
/*  370 */     if (item == null) {
/*      */       
/*  372 */       this.quality = null;
/*      */     }
/*      */     else {
/*      */       
/*  376 */       if (par1NBTTagCompound.hasKey("quality")) {
/*  377 */         setQuality(EnumQuality.values()[par1NBTTagCompound.getByte("quality")]);
/*      */       } else {
/*  379 */         setQuality(null);
/*      */       } 
/*  381 */       if (isItemStackDamageable() && this.damage >= getMaxDamage()) {
/*  382 */         setItemDamage(getMaxDamage() - 1);
/*      */       }
/*      */     } 
/*  385 */     this.is_artifact = par1NBTTagCompound.getBoolean("is_artifact");
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
/*      */   public int getMaxStackSize() {
/*  399 */     return getItem().getItemStackLimit(this.subtype, this.damage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStackable() {
/*  407 */     return (getMaxStackSize() > 1 && (!isItemStackDamageable() || !isItemDamaged()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemStackDamageable() {
/*  417 */     Item item = getItem();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  423 */     return (item != null && item.isDamageable());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getHasSubtypes() {
/*  429 */     return Item.itemsList[this.itemID].getHasSubtypes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemDamaged() {
/*  439 */     if (this.damage < 0) {
/*  440 */       Minecraft.setErrorMessage("isItemDamaged: Why is damage less than zero? " + this);
/*      */     }
/*  442 */     if (!isItemStackDamageable() && this.damage != 0) {
/*  443 */       Minecraft.setErrorMessage("isItemDamaged: Why does non-damageable item have non-zero damage? " + this);
/*      */     }
/*  445 */     return (isItemStackDamageable() && this.damage != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemDamageForDisplay() {
/*  454 */     return this.damage;
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
/*      */   public int getItemSubtype() {
/*  468 */     return this.subtype;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemDamage() {
/*  473 */     return this.damage;
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
/*      */   public ItemStack setItemSubtype(int subtype) {
/*  503 */     if (subtype < 0) {
/*      */       
/*  505 */       Minecraft.setErrorMessage("setItemSubtype: setting subtype to " + subtype);
/*  506 */       subtype = 0;
/*      */     } 
/*      */     
/*  509 */     this.subtype = subtype;
/*      */     
/*  511 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack setItemDamage(int damage) {
/*  516 */     if (damage < 0) {
/*      */       
/*  518 */       Minecraft.setErrorMessage("setDamage: less than 0");
/*  519 */       damage = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  527 */     if (damage != 0 && !isItemStackDamageable()) {
/*      */       
/*  529 */       Minecraft.setErrorMessage("setItemDamage: setting non-zero damage for non-damageable ItemStack " + this);
/*  530 */       return this;
/*      */     } 
/*      */     
/*  533 */     this.damage = damage;
/*      */     
/*  535 */     if (getItem() instanceof ItemAnvilBlock) {
/*  536 */       ((ItemAnvilBlock)getItem()).updateSubtypeForDamage(this);
/*      */     }
/*  538 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRemainingDurability() {
/*  543 */     if (!isItemStackDamageable()) {
/*      */       
/*  545 */       Minecraft.setErrorMessage("getRemainingDurability: item stack is not damageable");
/*  546 */       return 0;
/*      */     } 
/*      */     
/*  549 */     return getMaxDamage() - getItemDamage();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxDamage() {
/*  558 */     return getItem().getMaxDamage(this);
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
/*      */   public ItemDamageResult tryDamageItem(World world, int damage, boolean prevent_destruction) {
/*  608 */     if (!isItemStackDamageable() || damage < 1)
/*      */     {
/*      */       
/*  611 */       return null;
/*      */     }
/*  613 */     float fraction_of_unbreaking = getEnchantmentLevelFraction(Enchantment.unbreaking);
/*      */     
/*  615 */     if (fraction_of_unbreaking > 0.0F) {
/*      */       
/*  617 */       Random random = new Random();
/*      */       
/*  619 */       int points_negated = 0;
/*      */       
/*  621 */       float chance_of_negation_per_point = fraction_of_unbreaking * 0.75F;
/*      */       
/*  623 */       for (int i = 0; i < damage; i++) {
/*      */         
/*  625 */         if (random.nextFloat() < chance_of_negation_per_point) {
/*  626 */           points_negated++;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  631 */       damage -= points_negated;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  637 */     if (prevent_destruction && this.damage + damage >= getMaxDamage()) {
/*  638 */       damage = getMaxDamage() - this.damage - 1;
/*      */     }
/*  640 */     if (damage <= 0) {
/*  641 */       return null;
/*      */     }
/*  643 */     ItemDamageResult result = (new ItemDamageResult()).setItemLostDurability();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  648 */     setItemDamage(this.damage + damage);
/*      */ 
/*      */     
/*  651 */     return (this.damage >= getMaxDamage()) ? result.setItemWasDestroyed(world, this) : result;
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
/*      */   public ItemDamageResult tryDamageItem(DamageSource damage_source, int damage, EntityLivingBase owner) {
/*      */     ItemDamageResult result;
/*  696 */     if (!isItemStackDamageable() || !isHarmedBy(damage_source))
/*      */     {
/*      */       
/*  699 */       return null;
/*      */     }
/*  701 */     World world = owner.worldObj;
/*      */     
/*  703 */     if (world.isRemote) {
/*  704 */       Minecraft.setErrorMessage("damageItem: not meant to be called on client");
/*      */     }
/*  706 */     boolean was_held_item = (owner != null && owner.getHeldItemStack() == this);
/*      */     
/*  708 */     boolean is_pepsin_or_acid_that_can_destroy_item = ((damage_source.isPepsinDamage() || damage_source.isAcidDamage()) && isHarmedBy(damage_source));
/*  709 */     boolean prevent_destruction = (owner.isWearing(this) && !is_pepsin_or_acid_that_can_destroy_item);
/*      */     
/*  711 */     if (prevent_destruction)
/*      */     {
/*  713 */       if (damage_source.isFireDamage()) {
/*      */         
/*  715 */         if (hasMaterial(Material.leather)) {
/*  716 */           prevent_destruction = false;
/*      */         }
/*  718 */       } else if (damage_source.isLavaDamage()) {
/*      */         
/*  720 */         prevent_destruction = false;
/*      */         
/*  722 */         if (getItem().containsMetal()) {
/*  723 */           damage *= 10;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  729 */     if (owner instanceof EntityPlayer) {
/*      */       
/*  731 */       EntityPlayer player = (EntityPlayer)owner;
/*      */       
/*  733 */       if (player.inCreativeMode())
/*      */       {
/*      */         
/*  736 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  740 */       if (player.hasCurse(Curse.equipment_decays_faster, true)) {
/*  741 */         damage *= 2;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  747 */       result = tryDamageItem(world, damage, prevent_destruction);
/*      */       
/*  749 */       if (result == null || !result.itemWasDestroyed()) {
/*  750 */         return result;
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
/*  762 */       if (player.onServer()) {
/*      */         
/*  764 */         player.addStat(StatList.objectBreakStats[this.itemID], 1);
/*      */         
/*  766 */         if (was_held_item) {
/*      */           
/*  768 */           ItemStack item_stack = getItem().getItemProducedWhenDestroyed(this, damage_source);
/*      */           
/*  770 */           if (item_stack == null) {
/*  771 */             player.causeBreakingItemEffect(getItem(), (player.getHeldItemStack() == this) ? player.inventory.currentItem : -1);
/*      */           }
/*      */           
/*  774 */           player.convertOneOfHeldItem(item_stack);
/*      */           
/*  776 */           if (!player.hasHeldItem()) {
/*  777 */             (player.getAsEntityPlayerMP()).prevent_item_pickup_due_to_held_item_breaking_until = System.currentTimeMillis() + 1500L;
/*      */           }
/*  779 */         } else if (--this.stackSize == 0) {
/*      */           
/*  781 */           player.causeBreakingItemEffect(getItem(), (player.getHeldItemStack() == this) ? player.inventory.currentItem : -1);
/*  782 */           player.inventory.destroyInventoryItemStack(this);
/*      */         }
/*      */       
/*      */       } 
/*  786 */     } else if (owner instanceof EntityLiving) {
/*      */       
/*  788 */       EntityLiving entity_living = (EntityLiving)owner;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  794 */       result = tryDamageItem(world, damage, prevent_destruction);
/*      */       
/*  796 */       if (result == null || !result.itemWasDestroyed()) {
/*  797 */         return result;
/*      */       }
/*  799 */       if (owner.onServer())
/*      */       {
/*  801 */         entity_living.causeBreakingItemEffect(getItem());
/*      */         
/*  803 */         if (--this.stackSize == 0) {
/*  804 */           entity_living.clearMatchingEquipmentSlot(this);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  812 */       Minecraft.setErrorMessage("tryDamageItem: no handler for " + owner);
/*  813 */       return null;
/*      */     } 
/*      */     
/*  816 */     if (this.stackSize < 0) {
/*  817 */       this.stackSize = 0;
/*      */     }
/*  819 */     setItemDamage(0);
/*      */ 
/*      */     
/*  822 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hitEntity(EntityLivingBase par1EntityLivingBase, EntityPlayer par2EntityPlayer) {
/*  830 */     boolean var3 = Item.itemsList[this.itemID].hitEntity(this, par1EntityLivingBase, par2EntityPlayer);
/*      */     
/*  832 */     if (var3)
/*      */     {
/*  834 */       par2EntityPlayer.addStat(StatList.objectUseStats[this.itemID], 1);
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
/*      */   public ItemStack copy() {
/*  889 */     ItemStack var1 = (new ItemStack(this.itemID, this.stackSize, this.subtype)).setItemDamage(this.damage);
/*      */     
/*  891 */     var1.setQuality(getQuality());
/*  892 */     var1.is_artifact = this.is_artifact;
/*      */     
/*  894 */     if (this.stackTagCompound != null)
/*      */     {
/*  896 */       var1.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
/*      */     }
/*      */     
/*  899 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack copyOver(ItemStack item_stack) {
/*  905 */     item_stack.itemID = this.itemID;
/*  906 */     item_stack.subtype = this.subtype;
/*  907 */     item_stack.setItemDamage(this.damage);
/*  908 */     item_stack.quality = this.quality;
/*  909 */     item_stack.is_artifact = this.is_artifact;
/*  910 */     item_stack.stackSize = this.stackSize;
/*      */     
/*  912 */     item_stack.stackTagCompound = (this.stackTagCompound == null) ? null : (NBTTagCompound)this.stackTagCompound.copy();
/*      */     
/*  914 */     if (!areItemStackTagsEqual(item_stack, this)) {
/*  915 */       Minecraft.setErrorMessage("copyOver: item_stacks are different after copy");
/*      */     }
/*  917 */     return item_stack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean areItemStackTagsEqual(ItemStack par0ItemStack, ItemStack par1ItemStack) {
/*  928 */     if (par0ItemStack == par1ItemStack) {
/*  929 */       return true;
/*      */     }
/*  931 */     if (par0ItemStack == null || par1ItemStack == null) {
/*  932 */       return false;
/*      */     }
/*      */     
/*  935 */     if (par0ItemStack.stackTagCompound == par1ItemStack.stackTagCompound) {
/*  936 */       return true;
/*      */     }
/*  938 */     if (par0ItemStack.stackTagCompound == null || par1ItemStack.stackTagCompound == null) {
/*  939 */       return false;
/*      */     }
/*  941 */     return par0ItemStack.stackTagCompound.equals(par1ItemStack.stackTagCompound);
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
/*      */   public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack) {
/*  954 */     return areItemStacksEqual(par0ItemStack, par1ItemStack, false, false, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack, boolean ignore_stack_size) {
/*  959 */     return areItemStacksEqual(par0ItemStack, par1ItemStack, ignore_stack_size, false, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality) {
/*  964 */     return areItemStacksEqual(par0ItemStack, par1ItemStack, ignore_stack_size, ignore_quality, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype) {
/*  969 */     return areItemStacksEqual(par0ItemStack, par1ItemStack, ignore_stack_size, ignore_quality, ignore_damage_but_not_subtype, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean areItemStacksEqual(ItemStack par0ItemStack, ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
/*  976 */     if (par0ItemStack == par1ItemStack) {
/*  977 */       return true;
/*      */     }
/*  979 */     if (par0ItemStack == null || par1ItemStack == null) {
/*  980 */       return false;
/*      */     }
/*  982 */     return par0ItemStack.isItemStackEqual(par1ItemStack, ignore_stack_size, ignore_quality, ignore_damage_but_not_subtype, ignore_tag_compound);
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
/*      */   protected boolean isItemStackEqual(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
/*  995 */     if (par1ItemStack == this) {
/*  996 */       return true;
/*      */     }
/*  998 */     if (this.itemID != par1ItemStack.itemID) {
/*  999 */       return false;
/*      */     }
/* 1001 */     if (getItemSubtype() != par1ItemStack.getItemSubtype()) {
/*      */       
/* 1003 */       if (!getHasSubtypes()) {
/* 1004 */         Minecraft.setErrorMessage("isItemStackEqual: subtypes are different but item does not have subtypes");
/*      */       }
/* 1006 */       return false;
/*      */     } 
/*      */     
/* 1009 */     if (getItemDamage() != par1ItemStack.getItemDamage()) {
/*      */       
/* 1011 */       if (!isItemStackDamageable()) {
/* 1012 */         Minecraft.setErrorMessage("isItemStackEqual: damages are different but item is not damageable");
/*      */       }
/* 1014 */       if (!ignore_damage_but_not_subtype) {
/* 1015 */         return false;
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
/* 1042 */     if (!ignore_quality && par1ItemStack.getQuality() != getQuality()) {
/* 1043 */       return false;
/*      */     }
/* 1045 */     if (par1ItemStack.is_artifact != this.is_artifact) {
/* 1046 */       return false;
/*      */     }
/* 1048 */     if (!ignore_stack_size && this.stackSize != par1ItemStack.stackSize) {
/* 1049 */       return false;
/*      */     }
/* 1051 */     if (!ignore_tag_compound && !areItemStackTagsEqual(this, par1ItemStack)) {
/* 1052 */       return false;
/*      */     }
/* 1054 */     return true;
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
/*      */   public String getUnlocalizedName() {
/* 1070 */     return Item.itemsList[this.itemID].getUnlocalizedName(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack copyItemStack(ItemStack par0ItemStack) {
/* 1078 */     return (par0ItemStack == null) ? null : par0ItemStack.copy();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1088 */     StringBuilder sb = new StringBuilder();
/*      */     
/* 1090 */     sb.append(this.stackSize + "x" + Item.itemsList[this.itemID].getUnlocalizedName() + "[" + this.itemID + ":" + this.subtype + "]");
/*      */     
/* 1092 */     if (isItemStackDamageable()) {
/* 1093 */       sb.append("@" + this.damage + "/" + getMaxDamage());
/*      */     }
/* 1095 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAnimation(World par1World, Entity par2Entity, int par3, boolean par4) {
/* 1106 */     if (this.animationsToGo > 0)
/*      */     {
/* 1108 */       this.animationsToGo--;
/*      */     }
/*      */     
/* 1111 */     Item.itemsList[this.itemID].onUpdate(this, par1World, par2Entity, par3, par4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onCrafting(World par1World, EntityPlayer par2EntityPlayer, int par3) {
/* 1116 */     par2EntityPlayer.addStat(StatList.objectCraftStats[this.itemID], par3);
/* 1117 */     Item.itemsList[this.itemID].onCreated(this, par1World, par2EntityPlayer);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxItemUseDuration() {
/* 1122 */     return getItem().getMaxItemUseDuration(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumItemInUseAction getItemInUseAction(EntityPlayer player) {
/* 1132 */     return getItem().getItemInUseAction(this, player);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onPlayerStoppedUsing(World par1World, EntityPlayer par2EntityPlayer, int par3) {
/* 1140 */     getItem().onPlayerStoppedUsing(this, par1World, par2EntityPlayer, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTagCompound() {
/* 1148 */     return (this.stackTagCompound != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getTagCompound() {
/* 1156 */     return this.stackTagCompound;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagList getEnchantmentTagList() {
/* 1165 */     return (this.stackTagCompound == null) ? null : (NBTTagList)this.stackTagCompound.getTag("ench");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagList getStoredEnchantmentTagList() {
/* 1171 */     if (getItem() == Item.enchantedBook)
/*      */     {
/* 1173 */       return (this.stackTagCompound != null && this.stackTagCompound.hasKey("StoredEnchantments")) ? (NBTTagList)this.stackTagCompound.getTag("StoredEnchantments") : null;
/*      */     }
/* 1175 */     Minecraft.setErrorMessage("getStoredEnchantmentTagList: called for an item that isn't an enchanted book");
/*      */     
/* 1177 */     return null;
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
/*      */   public boolean hasStoredEnchantments() {
/* 1195 */     NBTTagList stored_enchantments = getStoredEnchantmentTagList();
/*      */     
/* 1197 */     return (stored_enchantments != null && stored_enchantments.tagCount() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEnchantment(Enchantment enchantment, boolean exclusive) {
/* 1202 */     NBTTagList enchantments = getEnchantmentTagList();
/*      */     
/* 1204 */     if (enchantments == null || (exclusive && enchantments.tagCount() != 1)) {
/* 1205 */       return false;
/*      */     }
/* 1207 */     for (int i = 0; i < enchantments.tagCount(); i++) {
/*      */       
/* 1209 */       if (((NBTTagCompound)enchantments.tagAt(i)).getShort("id") == enchantment.effectId) {
/* 1210 */         return true;
/*      */       }
/*      */     } 
/* 1213 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearEnchantTagList() {
/* 1218 */     if (this.stackTagCompound == null) {
/*      */       return;
/*      */     }
/* 1221 */     this.stackTagCompound.removeTag("ench");
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
/*      */   public ItemStack setTagCompound(NBTTagCompound par1NBTTagCompound) {
/* 1234 */     this.stackTagCompound = par1NBTTagCompound;
/* 1235 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDisplayName() {
/* 1243 */     String var1 = getItem().getItemDisplayName(this);
/*      */     
/* 1245 */     if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display")) {
/*      */       
/* 1247 */       NBTTagCompound var2 = this.stackTagCompound.getCompoundTag("display");
/*      */       
/* 1249 */       if (var2.hasKey("Name"))
/*      */       {
/* 1251 */         var1 = var2.getString("Name");
/*      */       }
/*      */     } 
/*      */     
/* 1255 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNameForReferenceFile() {
/* 1260 */     return getItem().getNameForReferenceFile(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMITEStyleDisplayName() {
/* 1265 */     String standard_name = getItem().getItemDisplayName(this);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1270 */     if (hasDisplayName()) {
/* 1271 */       return standard_name + " \"" + getDisplayName() + "\"";
/*      */     }
/* 1273 */     return standard_name;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeRenamed() {
/* 1278 */     return getItem().canBeRenamed();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemName(String par1Str) {
/* 1286 */     if (!canBeRenamed()) {
/*      */       
/* 1288 */       Minecraft.setErrorMessage("setItemName: This item cannot be renamed " + getItem());
/*      */       
/*      */       return;
/*      */     } 
/* 1292 */     if (par1Str != null) {
/* 1293 */       par1Str = par1Str.trim();
/*      */     }
/* 1295 */     if (this.stackTagCompound == null)
/*      */     {
/* 1297 */       this.stackTagCompound = new NBTTagCompound("tag");
/*      */     }
/*      */     
/* 1300 */     if (!this.stackTagCompound.hasKey("display"))
/*      */     {
/* 1302 */       this.stackTagCompound.setCompoundTag("display", new NBTTagCompound());
/*      */     }
/*      */     
/* 1305 */     this.stackTagCompound.getCompoundTag("display").setString("Name", par1Str);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_135074_t() {
/* 1310 */     if (this.stackTagCompound != null)
/*      */     {
/* 1312 */       if (this.stackTagCompound.hasKey("display")) {
/*      */         
/* 1314 */         NBTTagCompound var1 = this.stackTagCompound.getCompoundTag("display");
/* 1315 */         var1.removeTag("Name");
/*      */         
/* 1317 */         if (var1.hasNoTags()) {
/*      */           
/* 1319 */           this.stackTagCompound.removeTag("display");
/*      */           
/* 1321 */           if (this.stackTagCompound.hasNoTags())
/*      */           {
/* 1323 */             setTagCompound((NBTTagCompound)null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDisplayName() {
/* 1335 */     return (this.stackTagCompound == null) ? false : (!this.stackTagCompound.hasKey("display") ? false : this.stackTagCompound.getCompoundTag("display").hasKey("Name"));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addTooltipsToList(EnumChatFormatting enum_chat_formatting, String[] lines, List<String> list) {
/* 1340 */     if (lines == null) {
/*      */       return;
/*      */     }
/* 1343 */     for (int i = 0; i < lines.length; i++) {
/* 1344 */       list.add((enum_chat_formatting == null) ? lines[i] : (enum_chat_formatting + lines[i]));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getTooltip(EntityPlayer par1EntityPlayer, boolean par2, Slot slot) {
/* 1353 */     ArrayList<String> var3 = new ArrayList();
/* 1354 */     Item var4 = Item.itemsList[this.itemID];
/*      */     
/* 1356 */     String var5 = EnumChatFormatting.WHITE + getMITEStyleDisplayName();
/*      */     
/* 1358 */     boolean is_map = (this.itemID == Item.map.itemID);
/*      */ 
/*      */     
/* 1361 */     if (par2 && par1EntityPlayer.inCreativeMode() && !is_map) {
/*      */       
/* 1363 */       String var6 = "";
/*      */       
/* 1365 */       if (var5.length() > 0) {
/*      */         
/* 1367 */         var5 = var5 + " (";
/* 1368 */         var6 = ")";
/*      */       } 
/*      */       
/* 1371 */       if (getHasSubtypes()) {
/*      */ 
/*      */         
/* 1374 */         var5 = var5 + String.format("#%04d/%d%s", new Object[] { Integer.valueOf(this.itemID), Integer.valueOf(this.subtype), var6 });
/*      */       }
/*      */       else {
/*      */         
/* 1378 */         var5 = var5 + String.format("#%04d%s", new Object[] { Integer.valueOf(this.itemID), var6 });
/*      */       } 
/*      */       
/* 1381 */       if (hasSignature()) {
/* 1382 */         var5 = var5 + " [" + getSignature() + "]";
/*      */       }
/*      */     }
/* 1385 */     else if (!hasDisplayName() && is_map) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1390 */       if (ItemMap.isBeingExtended(this)) {
/* 1391 */         var5 = "Extended Map";
/*      */       } else {
/*      */         
/* 1394 */         var5 = var5 + " #" + this.subtype;
/*      */       } 
/*      */     } 
/* 1397 */     var3.add(var5);
/*      */     
/* 1399 */     if (var4.hasQuality()) {
/* 1400 */       var3.add(EnumChatFormatting.GRAY + getQuality().getDescriptor());
/*      */     }
/* 1402 */     var4.addInformationBeforeEnchantments(this, par1EntityPlayer, var3, par2, slot);
/*      */     
/* 1404 */     if (hasTagCompound()) {
/*      */       
/* 1406 */       NBTTagList var14 = getEnchantmentTagList();
/*      */       
/* 1408 */       if (var14 != null) {
/*      */         
/* 1410 */         if (var14.tagCount() > 0) {
/* 1411 */           var3.add("");
/*      */         }
/* 1413 */         for (int var7 = 0; var7 < var14.tagCount(); var7++) {
/*      */           
/* 1415 */           short var8 = ((NBTTagCompound)var14.tagAt(var7)).getShort("id");
/* 1416 */           short var9 = ((NBTTagCompound)var14.tagAt(var7)).getShort("lvl");
/*      */           
/* 1418 */           if (Enchantment.enchantmentsList[var8] != null)
/*      */           {
/*      */             
/* 1421 */             var3.add(EnumChatFormatting.AQUA + Enchantment.enchantmentsList[var8].getTranslatedName(var9, this));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1428 */     var4.addInformation(this, par1EntityPlayer, var3, par2, slot);
/*      */     
/* 1430 */     if (hasTagCompound())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1452 */       if (this.stackTagCompound.hasKey("display")) {
/*      */         
/* 1454 */         NBTTagCompound var17 = this.stackTagCompound.getCompoundTag("display");
/*      */         
/* 1456 */         if (var17.hasKey("color"))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1467 */           if (par2) {
/*      */             
/* 1469 */             var3.add("");
/* 1470 */             var3.add("Dyed Color: #" + Integer.toHexString(var17.getInteger("color")).toUpperCase());
/*      */           } 
/*      */         }
/*      */         
/* 1474 */         if (var17.hasKey("Lore")) {
/*      */           
/* 1476 */           NBTTagList var19 = var17.getTagList("Lore");
/*      */           
/* 1478 */           if (var19.tagCount() > 0)
/*      */           {
/* 1480 */             for (int var20 = 0; var20 < var19.tagCount(); var20++)
/*      */             {
/* 1482 */               var3.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + ((NBTTagString)var19.tagAt(var20)).data);
/*      */             }
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1489 */     Multimap var16 = getAttributeModifiers();
/*      */ 
/*      */     
/* 1492 */     if (par2 && !var16.isEmpty()) {
/*      */       
/* 1494 */       var3.add("");
/* 1495 */       Iterator<Map.Entry> var15 = var16.entries().iterator();
/*      */       
/* 1497 */       while (var15.hasNext()) {
/*      */         double var12;
/* 1499 */         Map.Entry var18 = var15.next();
/* 1500 */         AttributeModifier var21 = (AttributeModifier)var18.getValue();
/* 1501 */         double var10 = var21.getAmount();
/*      */ 
/*      */         
/* 1504 */         if (var21.getOperation() != 1 && var21.getOperation() != 2) {
/*      */           
/* 1506 */           var12 = var21.getAmount();
/*      */         }
/*      */         else {
/*      */           
/* 1510 */           var12 = var21.getAmount() * 100.0D;
/*      */         } 
/*      */         
/* 1513 */         if (var10 > 0.0D) {
/*      */           
/* 1515 */           var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var21.getOperation(), new Object[] { field_111284_a.format(var12), StatCollector.translateToLocal("attribute.name." + (String)var18.getKey()) })); continue;
/*      */         } 
/* 1517 */         if (var10 < 0.0D) {
/*      */           
/* 1519 */           var12 *= -1.0D;
/* 1520 */           var3.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var21.getOperation(), new Object[] { field_111284_a.format(var12), StatCollector.translateToLocal("attribute.name." + (String)var18.getKey()) }));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1525 */     if (par2 && var4 instanceof ItemTool) {
/*      */       
/* 1527 */       ItemTool tool = (ItemTool)var4;
/*      */       
/* 1529 */       if (tool.getToolMaterial() == Material.silver) {
/* 1530 */         var3.add(EnumChatFormatting.WHITE + Translator.get("item.tooltip.bonusVsUndead"));
/*      */       }
/*      */     } 
/* 1533 */     if (par2 && getQuality() != null) {
/*      */       
/* 1535 */       float modifier = getQuality().getDurabilityModifier();
/*      */       
/* 1537 */       if (modifier < 1.0F) {
/*      */ 
/*      */         
/* 1540 */         var3.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.durabilityPenalty", new Object[] { Integer.valueOf((int)((1.0F - modifier) * 100.0F)) }));
/*      */       }
/* 1542 */       else if (modifier > 1.0F) {
/*      */ 
/*      */         
/* 1545 */         var3.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.durabilityBonus", new Object[] { Integer.valueOf((int)((modifier - 1.0F) * 100.0F)) }));
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
/* 1560 */     if (isArtifact()) {
/*      */       
/* 1562 */       var3.add("");
/* 1563 */       var3.add(EnumChatFormatting.AQUA + "Artifact");
/*      */     } 
/*      */     
/* 1566 */     if (hasTagCompound())
/*      */     {
/* 1568 */       if (par2 && this.stackTagCompound.hasKey("flavor_text")) {
/*      */         
/* 1570 */         String text = this.stackTagCompound.getString("flavor_text");
/*      */         
/* 1572 */         List<String> text_lines = Minecraft.theMinecraft.fontRenderer.listFormattedStringToWidth(text, 120);
/*      */         
/* 1574 */         var3.add("");
/*      */         
/* 1576 */         for (int i = 0; i < text_lines.size(); i++)
/*      */         {
/* 1578 */           var3.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + (String)text_lines.get(i));
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1588 */     if (par2 && (Minecraft.theMinecraft.gameSettings.advancedItemTooltips || par1EntityPlayer.inCreativeMode()) && isItemStackDamageable()) {
/*      */       
/* 1590 */       var3.add("");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1597 */       if (isItemDamaged()) {
/* 1598 */         var3.add(Translator.get("item.tooltip.durability") + " " + (getMaxDamage() - getItemDamageForDisplay()) + " / " + getMaxDamage());
/*      */       } else {
/* 1600 */         var3.add(Translator.get("item.tooltip.durability") + " " + getMaxDamage());
/*      */       } 
/*      */     } 
/* 1603 */     if (slot instanceof SlotCrafting) {
/*      */       
/* 1605 */       int experience_cost = ((EntityClientPlayerMP)par1EntityPlayer).crafting_experience_cost;
/*      */       
/* 1607 */       if (experience_cost == 0 && (par1EntityPlayer.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative > 0) {
/* 1608 */         experience_cost = (par1EntityPlayer.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative;
/*      */       }
/* 1610 */       SlotCrafting slot_crafting = (SlotCrafting)slot;
/*      */       
/* 1612 */       if (experience_cost == 0 && slot_crafting.getNumCraftingResults(par1EntityPlayer) > 1) {
/*      */         
/* 1614 */         var3.add("");
/*      */         
/* 1616 */         Item item = getItem();
/*      */         
/* 1618 */         if (item.hasQuality())
/*      */         {
/*      */ 
/*      */           
/* 1622 */           Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.differentQuality", var3);
/*      */         }
/* 1624 */         else if (item instanceof ItemRunestone)
/*      */         {
/*      */ 
/*      */           
/* 1628 */           Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.differentRunestone", var3);
/*      */         }
/*      */       
/* 1631 */       } else if (experience_cost > 0) {
/*      */         
/* 1633 */         int hypothetical_level = par1EntityPlayer.getExperienceLevel(par1EntityPlayer.experience - experience_cost);
/* 1634 */         int level_cost = par1EntityPlayer.getExperienceLevel() - hypothetical_level;
/*      */         
/* 1636 */         var3.add("");
/*      */ 
/*      */ 
/*      */         
/* 1640 */         if (level_cost == 0)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 1645 */           Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.qualityCostLessThanOneLevel", var3);
/*      */         
/*      */         }
/* 1648 */         else if (level_cost == 1)
/*      */         {
/*      */           
/* 1651 */           Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.qualityCostOneLevel", var3);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 1656 */           Translator.addToListFormatted(EnumChatFormatting.YELLOW, "container.crafting.qualityCostMoreThanOneLevel", var3, new Object[] { Integer.valueOf(level_cost) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
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
/*      */     }
/* 1682 */     else if (slot != null && slot.inventory instanceof TileEntityFurnace) {
/*      */       
/* 1684 */       TileEntityFurnace tile_entity_furnace = (TileEntityFurnace)slot.inventory;
/*      */       
/* 1686 */       if (tile_entity_furnace.getStackInSlot(0) == this) {
/*      */         
/* 1688 */         int required_heat_level = TileEntityFurnace.getHeatLevelRequired(this.itemID);
/* 1689 */         int current_or_potential_heat_level = (tile_entity_furnace.heat_level > 0) ? tile_entity_furnace.heat_level : tile_entity_furnace.getFuelHeatLevel();
/*      */         
/* 1691 */         if (current_or_potential_heat_level > 0 && current_or_potential_heat_level < required_heat_level)
/*      */         {
/* 1693 */           var3.add(EnumChatFormatting.GOLD + Translator.get("container.furnace.needsMoreHeat"));
/*      */         }
/*      */       } 
/*      */     } 
/* 1697 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEffect() {
/* 1702 */     return getItem().hasEffect(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumRarity getRarity() {
/* 1707 */     return getItem().getRarity(this);
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
/*      */   public boolean isEnchantable() {
/* 1721 */     if (getItem() == Item.book) {
/* 1722 */       return true;
/*      */     }
/* 1724 */     if (ItemPotion.isBottleOfWater(this) || ItemAppleGold.isUnenchantedGoldenApple(this)) {
/* 1725 */       return true;
/*      */     }
/* 1727 */     if (getMaxStackSize() != 1) {
/* 1728 */       return false;
/*      */     }
/* 1730 */     if (!isItemStackDamageable()) {
/* 1731 */       return false;
/*      */     }
/* 1733 */     return (getItem().getItemEnchantability() > 0 && !isItemEnchanted());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addEnchantment(Enchantment par1Enchantment, int par2) {
/* 1741 */     if (this.stackTagCompound == null)
/*      */     {
/* 1743 */       setTagCompound(new NBTTagCompound());
/*      */     }
/*      */     
/* 1746 */     if (!this.stackTagCompound.hasKey("ench"))
/*      */     {
/* 1748 */       this.stackTagCompound.setTag("ench", new NBTTagList("ench"));
/*      */     }
/*      */     
/* 1751 */     NBTTagList var3 = (NBTTagList)this.stackTagCompound.getTag("ench");
/* 1752 */     NBTTagCompound var4 = new NBTTagCompound();
/* 1753 */     var4.setShort("id", (short)par1Enchantment.effectId);
/* 1754 */     var4.setShort("lvl", (short)(byte)par2);
/* 1755 */     var3.appendTag(var4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemEnchanted() {
/* 1763 */     return (this.stackTagCompound != null && this.stackTagCompound.hasKey("ench"));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTagInfo(String par1Str, NBTBase par2NBTBase) {
/* 1768 */     if (this.stackTagCompound == null)
/*      */     {
/* 1770 */       setTagCompound(new NBTTagCompound());
/*      */     }
/*      */     
/* 1773 */     this.stackTagCompound.setTag(par1Str, par2NBTBase);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canEditBlocks() {
/* 1778 */     return getItem().canItemEditBlocks();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnItemFrame() {
/* 1786 */     return (this.itemFrame != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemFrame(EntityItemFrame par1EntityItemFrame) {
/* 1794 */     this.itemFrame = par1EntityItemFrame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItemFrame getItemFrame() {
/* 1802 */     return this.itemFrame;
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
/*      */   public int getRepairCost() {
/* 1816 */     return getItem().getRepairCost();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasRepairCost() {
/* 1822 */     return getItem().hasRepairCost();
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
/*      */   public Multimap getAttributeModifiers() {
/*      */     Object var1;
/* 1846 */     if (hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers")) {
/*      */       
/* 1848 */       var1 = HashMultimap.create();
/* 1849 */       NBTTagList var2 = this.stackTagCompound.getTagList("AttributeModifiers");
/*      */       
/* 1851 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*      */       {
/* 1853 */         NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 1854 */         AttributeModifier var5 = SharedMonsterAttributes.func_111259_a(var4);
/*      */         
/* 1856 */         if (var5.getID().getLeastSignificantBits() != 0L && var5.getID().getMostSignificantBits() != 0L)
/*      */         {
/* 1858 */           ((Multimap)var1).put(var4.getString("AttributeName"), var5);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1864 */       var1 = getItem().getItemAttributeModifiers();
/*      */     } 
/*      */     
/* 1867 */     return (Multimap)var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack setStackSize(int stack_size) {
/* 1872 */     this.stackSize = stack_size;
/* 1873 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void decrementStackSize(ItemStack item_stack) {
/* 1878 */     if (item_stack != null && item_stack.stackSize > 0) {
/* 1879 */       item_stack.stackSize--;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private ItemStack applyRandomItemStackDamageForMob(EntityLiving owner) {
/* 1885 */     if (isItemStackDamageable()) {
/*      */       
/* 1887 */       Item item = getItem();
/*      */       
/* 1889 */       if (item.hasQuality() && getQuality().isAverageOrHigher() && !isItemEnchanted()) {
/*      */         
/* 1891 */         Material material = null;
/*      */         
/* 1893 */         if (item instanceof ItemTool) {
/* 1894 */           material = ((ItemTool)item).getToolMaterial();
/* 1895 */         } else if (item instanceof ItemArmor) {
/* 1896 */           material = ((ItemArmor)item).getArmorMaterial();
/* 1897 */         } else if (item.materials.size() == 1) {
/* 1898 */           material = item.getExclusiveMaterial();
/*      */         } 
/* 1900 */         if (material == Material.wood || material == Material.leather) {
/* 1901 */           setQuality(getQuality().getNextLower());
/*      */         }
/*      */       } 
/* 1904 */       float fraction_damaged = (isItemEnchanted() ? 0.5F : 0.7F) + 0.3F * owner.rand.nextFloat();
/*      */ 
/*      */       
/* 1907 */       int damage = (int)(getMaxDamage() * fraction_damaged);
/*      */       
/* 1909 */       if (damage >= getMaxDamage()) {
/* 1910 */         damage = getMaxDamage() - 1;
/*      */       }
/* 1912 */       setItemDamage(damage);
/*      */     } 
/*      */     
/* 1915 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack applyRandomItemStackDamageForChest() {
/* 1920 */     if (isItemStackDamageable()) {
/*      */ 
/*      */       
/* 1923 */       int fraction_damaged = hasMaterial(Material.rusted_iron) ? (int)(3.0D + Math.random() * 7.0D) : (int)(Math.random() * 7.0D);
/* 1924 */       int damage = getMaxDamage() * fraction_damaged / 10;
/*      */       
/* 1926 */       if (damage >= getMaxDamage()) {
/* 1927 */         damage = getMaxDamage() - 1;
/*      */       }
/* 1929 */       setItemDamage(damage);
/*      */     } 
/*      */     
/* 1932 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack randomizeForMob(EntityLiving owner, boolean may_be_enchanted) {
/* 1938 */     if (may_be_enchanted) {
/* 1939 */       owner.enchantEquipment(this);
/*      */     }
/* 1941 */     applyRandomItemStackDamageForMob(owner);
/*      */     
/* 1943 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRepairItem() {
/* 1953 */     return getItem() instanceof ItemNugget;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getRepairItem() {
/* 1963 */     return getItem().getRepairItem();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMaterial(Material material) {
/* 1968 */     return hasMaterial(material, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMaterial(Material material, boolean exclusively) {
/* 1973 */     return getItem().hasMaterial(material, exclusively);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExperienceReward(int quantity) {
/* 1978 */     return getItem().getExperienceReward(getItemSubtype()) * quantity;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExperienceReward() {
/* 1983 */     return getExperienceReward(this.stackSize);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack setQuality(EnumQuality quality) {
/* 1988 */     Item item = getItem();
/*      */     
/* 1990 */     if (item == null) {
/*      */       
/* 1992 */       Minecraft.setErrorMessage("setQuality: item_stack.getItem()==null");
/* 1993 */       return this;
/*      */     } 
/*      */     
/* 1996 */     if (item.hasQuality()) {
/*      */       
/* 1998 */       if (quality == null) {
/*      */         
/* 2000 */         quality = item.getDefaultQuality();
/*      */       }
/* 2002 */       else if (quality.isHigherThan(item.getMaxQuality())) {
/*      */         
/* 2004 */         Minecraft.setErrorMessage("setQuality: quality is higher than item's max quality (" + getDisplayName() + ")");
/* 2005 */         quality = item.getMaxQuality();
/*      */       } 
/*      */ 
/*      */       
/* 2009 */       this.quality = quality;
/*      */     }
/* 2011 */     else if (quality != null) {
/*      */       
/* 2013 */       Minecraft.setErrorMessage("setQuality: item \"" + getItem().getItemDisplayName(this) + "\" does not have quality");
/* 2014 */       return this;
/*      */     } 
/*      */     
/* 2017 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumQuality getQuality() {
/* 2022 */     if (!getItem().hasQuality()) {
/* 2023 */       this.quality = null;
/* 2024 */     } else if (this.quality == null) {
/*      */       
/* 2026 */       this.quality = getItem().getDefaultQuality();
/*      */     } 
/* 2028 */     return this.quality;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int calcChecksum(int for_release_number) {
/* 2038 */     int checksum = this.itemID * this.stackSize;
/*      */     
/* 2040 */     if (for_release_number > 80) {
/* 2041 */       checksum += (getQuality() == null) ? 0 : (getQuality().ordinal() * 17);
/*      */     }
/* 2043 */     if (for_release_number > 120) {
/* 2044 */       checksum += this.is_artifact ? 531 : 0;
/*      */     }
/* 2046 */     checksum *= 3;
/*      */     
/* 2048 */     return checksum;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAsComponentOfCraftingProduct(ItemStack crafting_product) {
/* 2053 */     getItem().setAsComponentOfCraftingProduct(getHasSubtypes() ? getItemSubtype() : 0, crafting_product);
/*      */   }
/*      */ 
/*      */   
/*      */   public Material getMaterialForRepairs() {
/* 2058 */     return getItem().getMaterialForRepairs();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEnchantmentLevel(Enchantment enchantment) {
/* 2063 */     return EnchantmentHelper.getEnchantmentLevel(enchantment.effectId, this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getEnchantmentLevelFraction(Enchantment enchantment) {
/* 2069 */     return EnchantmentHelper.getEnchantmentLevelFraction(enchantment, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEnchantmentLevelFractionOfInteger(Enchantment enchantment, int integer) {
/* 2074 */     return EnchantmentHelper.getEnchantmentLevelFractionOfInteger(enchantment, this, integer);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canDouseFire() {
/* 2080 */     return getItem().canDouseFire();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCatchFire() {
/* 2086 */     return getItem().canCatchFire();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBurnAsFuelSource() {
/* 2092 */     return getItem().canBurnAsFuelSource();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByFire() {
/* 2097 */     return getItem().isHarmedByFire();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByLava() {
/* 2102 */     return getItem().isHarmedByLava();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player) {
/* 2107 */     return getItem().tryPlaceAsBlock(rc, block, player, this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasIngestionPriority(boolean ctrl_is_down) {
/* 2117 */     return getItem().hasIngestionPriority(this, ctrl_is_down);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player) {
/* 2123 */     return getItem().tryEntityInteraction(entity, player, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isIngestable() {
/* 2128 */     return getItem().isIngestable(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAlwaysEdible() {
/* 2133 */     return getItem().isAlwaysEdible();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSatiation(EntityPlayer player) {
/* 2138 */     return getItem().getSatiation(player);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNutrition() {
/* 2143 */     return getItem().getNutrition();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack subjectToChanceOfDisappearing(float chance, Random random) {
/* 2148 */     if (chance > 0.0F) {
/*      */       
/* 2150 */       int num_disappeared = 0;
/*      */       
/* 2152 */       for (int i = 0; i < this.stackSize; i++) {
/*      */         
/* 2154 */         if (random.nextFloat() < chance) {
/* 2155 */           num_disappeared++;
/*      */         }
/*      */       } 
/* 2158 */       this.stackSize -= num_disappeared;
/*      */     } 
/*      */     
/* 2161 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDyed() {
/* 2167 */     Item item = getItem();
/*      */     
/* 2169 */     if (item instanceof ItemBlock) {
/*      */       
/* 2171 */       ItemBlock item_block = (ItemBlock)item;
/* 2172 */       return item_block.getBlock() instanceof BlockColored;
/*      */     } 
/* 2174 */     if (item instanceof ItemArmor) {
/*      */       
/* 2176 */       ItemArmor item_armor = (ItemArmor)item;
/* 2177 */       return item_armor.hasColor(this);
/*      */     } 
/*      */ 
/*      */     
/* 2181 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDyedColor() {
/* 2188 */     Item item = getItem();
/*      */     
/* 2190 */     if (item instanceof ItemBlock) {
/*      */       
/* 2192 */       ItemBlock item_block = (ItemBlock)item;
/*      */       
/* 2194 */       if (item_block.getBlock() instanceof BlockColored) {
/* 2195 */         return getItemSubtype();
/*      */       }
/* 2197 */     } else if (item instanceof ItemArmor) {
/*      */       
/* 2199 */       ItemArmor item_armor = (ItemArmor)item;
/* 2200 */       return item_armor.getColor(this);
/*      */     } 
/*      */     
/* 2203 */     Minecraft.setErrorMessage("getDyedColor: don't know how to handle " + this);
/*      */     
/* 2205 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack copyDyedColor(ItemStack item_stack) {
/* 2211 */     if (item_stack.getItem() != getItem()) {
/*      */       
/* 2213 */       Minecraft.setErrorMessage("copyDyedColor: items aren't the same");
/* 2214 */       return this;
/*      */     } 
/*      */     
/* 2217 */     Item item = getItem();
/*      */     
/* 2219 */     if (item instanceof ItemBlock) {
/*      */       
/* 2221 */       ItemBlock item_block = (ItemBlock)item;
/*      */       
/* 2223 */       if (item_block.getBlock() instanceof BlockColored)
/*      */       {
/* 2225 */         setItemSubtype(item_stack.getItemSubtype());
/* 2226 */         return this;
/*      */       }
/*      */     
/* 2229 */     } else if (item instanceof ItemArmor) {
/*      */       
/* 2231 */       ItemArmor item_armor = (ItemArmor)item;
/*      */       
/* 2233 */       item_armor.func_82813_b(this, item_armor.getColor(item_stack));
/*      */       
/* 2235 */       return this;
/*      */     } 
/*      */     
/* 2238 */     Minecraft.setErrorMessage("copyDyedColor: don't know how to handle " + this);
/*      */     
/* 2240 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlock() {
/* 2245 */     return getItem().isBlock();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemBlock getItemAsBlock() {
/* 2250 */     return getItem().getAsItemBlock();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTool() {
/* 2255 */     return getItem().isTool();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemTool getItemAsTool() {
/* 2260 */     return getItem().getAsTool();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isArmor() {
/* 2266 */     return getItem().isArmor();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChainMail() {
/* 2271 */     return getItem().isChainMail();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemArmor getItemAsArmor() {
/* 2276 */     return getItem().getAsArmor();
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack setAsArtifact() {
/* 2281 */     this.is_artifact = true;
/* 2282 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isArtifact() {
/* 2287 */     return this.is_artifact;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int checkSignature(int signature, int min, int max) {
/* 2293 */     if ((signature < min || signature > max) && signature != 0) {
/* 2294 */       Minecraft.setErrorMessage("getSignature: invalid signature for " + this + " (" + signature + "), min=" + min + ", max=" + max);
/*      */     }
/* 2296 */     return signature;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSignature(boolean suppress_errors) {
/* 2302 */     int signature = 0;
/*      */     
/* 2304 */     if (ItemReferencedBook.isReferencedBook(this)) {
/*      */       
/* 2306 */       signature = checkSignature(ItemReferencedBook.getSignature(this), 1, 100);
/*      */     }
/* 2308 */     else if (ItemRecord.isUniqueRecord(this)) {
/*      */       
/* 2310 */       signature = checkSignature(ItemRecord.getSignature(this), 101, 200);
/*      */     } 
/*      */     
/* 2313 */     if (signature == 0 && !suppress_errors) {
/* 2314 */       Minecraft.setErrorMessage("getSignature: unhandled case " + this);
/*      */     }
/* 2316 */     return signature;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSignature() {
/* 2322 */     return getSignature(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasSignature() {
/* 2327 */     return (getSignature(true) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasSignatureThatHasBeenAddedToWorld(World world) {
/* 2332 */     return (hasSignature() && world.worldInfo.hasSignatureBeenAdded(getSignature()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSignatureToTheWorld(World world) {
/* 2338 */     world.worldInfo.addSignature(getSignature());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCraftingDifficultyAsComponent() {
/* 2343 */     return getItem().getCraftingDifficultyAsComponent(this);
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
/*      */   public boolean isHarmedByPepsin() {
/* 2366 */     return getItem().isHarmedByPepsin();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByAcid() {
/* 2371 */     if (getItem() == Item.dyePowder && getItemSubtype() == 4) {
/* 2372 */       return false;
/*      */     }
/* 2374 */     return getItem().isHarmedByAcid();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedBy(DamageSource damage_source) {
/* 2379 */     if (damage_source.isAcidDamage() && !isHarmedByAcid()) {
/* 2380 */       return false;
/*      */     }
/* 2382 */     return getItem().isHarmedBy(damage_source);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getScaledDamage(float damage) {
/* 2387 */     return getItem().getScaledDamage(damage);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getMeleeDamageBonus() {
/* 2392 */     return getItem().getMeleeDamageBonus();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeCompostedByWorms() {
/* 2397 */     return (getItem() != null && this.stackSize > 0 && getItem().canBeCompostedByWorms(this));
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */