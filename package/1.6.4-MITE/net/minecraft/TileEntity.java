/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TileEntity
/*     */ {
/*  16 */   private static Map nameToClassMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   private static Map classToNameMap = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   protected World worldObj;
/*     */ 
/*     */   
/*     */   public int xCoord;
/*     */   
/*     */   public int yCoord;
/*     */   
/*     */   public int zCoord;
/*     */   
/*     */   protected boolean tileEntityInvalid;
/*     */   
/*  35 */   public int blockMetadata = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Block blockType;
/*     */ 
/*     */ 
/*     */   
/*     */   private String custom_inv_name;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class<?> par0Class, String par1Str) {
/*  50 */     if (nameToClassMap.containsKey(par1Str))
/*     */     {
/*  52 */       throw new IllegalArgumentException("Duplicate id: " + par1Str);
/*     */     }
/*     */ 
/*     */     
/*  56 */     nameToClassMap.put(par1Str, par0Class);
/*  57 */     classToNameMap.put(par0Class, par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getWorldObj() {
/*  66 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldObj(World par1World) {
/*  74 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasWorldObj() {
/*  82 */     return (this.worldObj != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  90 */     this.xCoord = par1NBTTagCompound.getInteger("x");
/*  91 */     this.yCoord = par1NBTTagCompound.getInteger("y");
/*  92 */     this.zCoord = par1NBTTagCompound.getInteger("z");
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (par1NBTTagCompound.hasKey("CustomName")) {
/*  97 */       setCustomInvName(par1NBTTagCompound.getString("CustomName"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 105 */     String var2 = (String)classToNameMap.get(getClass());
/*     */     
/* 107 */     if (var2 == null)
/*     */     {
/* 109 */       throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
/*     */     }
/*     */ 
/*     */     
/* 113 */     par1NBTTagCompound.setString("id", var2);
/* 114 */     par1NBTTagCompound.setInteger("x", this.xCoord);
/* 115 */     par1NBTTagCompound.setInteger("y", this.yCoord);
/* 116 */     par1NBTTagCompound.setInteger("z", this.zCoord);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (hasCustomInvName()) {
/* 122 */       par1NBTTagCompound.setString("CustomName", getCustomInvName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TileEntity createAndLoadEntity(NBTTagCompound par0NBTTagCompound) {
/* 137 */     TileEntity var1 = null;
/*     */ 
/*     */     
/*     */     try {
/* 141 */       Class<TileEntity> var2 = (Class)nameToClassMap.get(par0NBTTagCompound.getString("id"));
/*     */       
/* 143 */       if (var2 != null)
/*     */       {
/* 145 */         var1 = var2.newInstance();
/*     */       }
/*     */     }
/* 148 */     catch (Exception var3) {
/*     */       
/* 150 */       var3.printStackTrace();
/*     */     } 
/*     */     
/* 153 */     if (var1 != null) {
/*     */       
/* 155 */       var1.readFromNBT(par0NBTTagCompound);
/*     */     }
/*     */     else {
/*     */       
/* 159 */       MinecraftServer.getServer().getLogAgent().logWarning("Skipping TileEntity with id " + par0NBTTagCompound.getString("id"));
/*     */     } 
/*     */     
/* 162 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockMetadata() {
/* 170 */     if (this.blockMetadata == -1)
/*     */     {
/* 172 */       this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
/*     */     }
/*     */     
/* 175 */     return this.blockMetadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {
/* 183 */     if (this.worldObj != null) {
/*     */       
/* 185 */       this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
/* 186 */       this.worldObj.markTileEntityChunkModified(this.xCoord, this.yCoord, this.zCoord, this);
/*     */       
/* 188 */       if (getBlockType() != null)
/*     */       {
/* 190 */         this.worldObj.func_96440_m(this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceFrom(double par1, double par3, double par5) {
/* 200 */     double var7 = this.xCoord + 0.5D - par1;
/* 201 */     double var9 = this.yCoord + 0.5D - par3;
/* 202 */     double var11 = this.zCoord + 0.5D - par5;
/* 203 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxRenderDistanceSquared() {
/* 208 */     return 4096.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBlockType() {
/* 216 */     if (this.blockType == null)
/*     */     {
/* 218 */       this.blockType = Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];
/*     */     }
/*     */     
/* 221 */     return this.blockType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlock(Block block) {
/* 226 */     this.blockType = block;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvalid() {
/* 242 */     return this.tileEntityInvalid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 250 */     this.tileEntityInvalid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate() {
/* 258 */     this.tileEntityInvalid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int par1, int par2) {
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateContainingBlockInfo() {
/* 275 */     this.blockType = null;
/* 276 */     this.blockMetadata = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_85027_a(CrashReportCategory par1CrashReportCategory) {
/* 281 */     par1CrashReportCategory.addCrashSectionCallable("Name", new CallableTileEntityName(this));
/* 282 */     CrashReportCategory.addBlockCrashInfo(par1CrashReportCategory, this.xCoord, this.yCoord, this.zCoord, (getBlockType()).blockID, getBlockMetadata());
/* 283 */     par1CrashReportCategory.addCrashSectionCallable("Actual block type", new CallableTileEntityID(this));
/* 284 */     par1CrashReportCategory.addCrashSectionCallable("Actual block data value", new CallableTileEntityData(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getBlockMaterial() {
/* 295 */     return (getBlockType()).blockMaterial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean hasCustomName() {
/* 304 */     return (this.custom_inv_name != null && this.custom_inv_name.length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCustomInvName(String custom_inv_name) {
/* 312 */     this.custom_inv_name = custom_inv_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getCustomInvName() {
/* 317 */     return this.custom_inv_name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedInvName() {
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getCustomNameOrUnlocalized() {
/* 331 */     return hasCustomName() ? getCustomInvName() : getUnlocalizedInvName();
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getCustomInvNameOrTranslated() {
/* 336 */     return hasCustomName() ? getCustomInvName() : I18n.getString(getUnlocalizedInvName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean hasCustomInvName() {
/* 342 */     return (this.custom_inv_name != null && !this.custom_inv_name.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getTranslatedStandardName() {
/* 347 */     return I18n.getString(getUnlocalizedInvName());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printTileEntitiesList(String title, List list) {
/* 352 */     Class<?> filter = null;
/*     */     
/* 354 */     System.out.println("\n" + title);
/* 355 */     System.out.println(StringHelper.repeat("-", title.length()));
/*     */     
/* 357 */     boolean items_outputted = false;
/*     */     
/* 359 */     Iterator<TileEntity> i = list.iterator();
/*     */     
/* 361 */     while (i.hasNext()) {
/*     */       
/* 363 */       TileEntity tile_entity = i.next();
/*     */       
/* 365 */       if (filter != null && tile_entity.getClass() != filter) {
/*     */         continue;
/*     */       }
/* 368 */       String name = tile_entity.getCustomInvNameOrTranslated();
/*     */       
/* 370 */       if (name == null) {
/* 371 */         name = tile_entity.toString();
/* 372 */       } else if (tile_entity.hasCustomInvName()) {
/* 373 */         name = tile_entity.getTranslatedStandardName() + " \"" + tile_entity.getCustomInvName() + "\"";
/*     */       } 
/* 375 */       System.out.println(name + " [" + StringHelper.getCoordsAsString(tile_entity.xCoord, tile_entity.yCoord, tile_entity.zCoord) + "]");
/*     */       
/* 377 */       items_outputted = true;
/*     */     } 
/*     */     
/* 380 */     if (!items_outputted) {
/* 381 */       System.out.println("(none)");
/*     */     }
/*     */   }
/*     */   
/*     */   static Map getClassToNameMap() {
/* 386 */     return classToNameMap;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 391 */     addMapping(TileEntityFurnace.class, "Furnace");
/* 392 */     addMapping(TileEntityChest.class, "Chest");
/* 393 */     addMapping(TileEntityStrongbox.class, "Strongbox");
/* 394 */     addMapping(TileEntityEnderChest.class, "EnderChest");
/* 395 */     addMapping(TileEntityRecordPlayer.class, "RecordPlayer");
/* 396 */     addMapping(TileEntityDispenser.class, "Trap");
/* 397 */     addMapping(TileEntityDropper.class, "Dropper");
/* 398 */     addMapping(TileEntitySign.class, "Sign");
/* 399 */     addMapping(TileEntityMobSpawner.class, "MobSpawner");
/* 400 */     addMapping(TileEntityNote.class, "Music");
/* 401 */     addMapping(TileEntityPiston.class, "Piston");
/* 402 */     addMapping(TileEntityBrewingStand.class, "Cauldron");
/* 403 */     addMapping(TileEntityEnchantmentTable.class, "EnchantTable");
/* 404 */     addMapping(TileEntityEndPortal.class, "Airportal");
/* 405 */     addMapping(TileEntityCommandBlock.class, "Control");
/* 406 */     addMapping(TileEntityBeacon.class, "Beacon");
/* 407 */     addMapping(TileEntitySkull.class, "Skull");
/* 408 */     addMapping(TileEntityDaylightDetector.class, "DLDetector");
/* 409 */     addMapping(TileEntityHopper.class, "Hopper");
/* 410 */     addMapping(TileEntityComparator.class, "Comparator");
/* 411 */     addMapping(TileEntityAnvil.class, "Anvil");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */