/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class EntityMinecartHopper
/*     */   extends EntityMinecartContainer
/*     */   implements Hopper {
/*     */   private boolean isBlocked = true;
/*   9 */   private int transferTicker = -1;
/*     */ 
/*     */   
/*     */   public EntityMinecartHopper(World par1World) {
/*  13 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartHopper(World par1World, double par2, double par4, double par6) {
/*  18 */     super(par1World, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinecartType() {
/*  23 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getDefaultDisplayTile() {
/*  28 */     return Block.hopperBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultDisplayTileOffset() {
/*  33 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  41 */     return 5;
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/*  59 */     if (player.onServer()) {
/*  60 */       player.displayGUIHopperMinecart(this);
/*     */     }
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivatorRailPass(int par1, int par2, int par3, boolean par4) {
/*  70 */     boolean var5 = !par4;
/*     */     
/*  72 */     if (var5 != getBlocked())
/*     */     {
/*  74 */       setBlocked(var5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBlocked() {
/*  83 */     return this.isBlocked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlocked(boolean par1) {
/*  91 */     this.isBlocked = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getWorldObj() {
/*  99 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXPos() {
/* 107 */     return this.posX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYPos() {
/* 115 */     return this.posY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZPos() {
/* 123 */     return this.posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 128 */     return MathHelper.floor_double(this.posX);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 133 */     return MathHelper.floor_double(this.posY);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/* 138 */     return MathHelper.floor_double(this.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 146 */     super.onUpdate();
/*     */     
/* 148 */     if (!this.worldObj.isRemote && isEntityAlive() && getBlocked()) {
/*     */       
/* 150 */       this.transferTicker--;
/*     */       
/* 152 */       if (!canTransfer()) {
/*     */         
/* 154 */         setTransferTicker(0);
/*     */         
/* 156 */         if (func_96112_aD()) {
/*     */           
/* 158 */           setTransferTicker(4);
/* 159 */           onInventoryChanged();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96112_aD() {
/* 167 */     if (TileEntityHopper.suckItemsIntoHopper(this))
/*     */     {
/* 169 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 173 */     List<EntityItem> var1 = this.worldObj.selectEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(0.25D, 0.0D, 0.25D), IEntitySelector.selectAnything);
/*     */     
/* 175 */     if (var1.size() > 0)
/*     */     {
/* 177 */       TileEntityHopper.insertStackFromEntity(this, var1.get(0));
/*     */     }
/*     */     
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource par1DamageSource) {
/* 186 */     super.killMinecart(par1DamageSource);
/* 187 */     dropItem(Block.hopperBlock.blockID, 1, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 195 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 196 */     par1NBTTagCompound.setInteger("TransferCooldown", this.transferTicker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 204 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 205 */     this.transferTicker = par1NBTTagCompound.getInteger("TransferCooldown");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransferTicker(int par1) {
/* 213 */     this.transferTicker = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTransfer() {
/* 221 */     return (this.transferTicker > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 226 */     return Item.minecartHopper;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */