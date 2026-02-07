/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class Packet100OpenWindow
/*     */   extends Packet
/*     */ {
/*     */   public int windowId;
/*     */   public int inventoryType;
/*     */   public String windowTitle;
/*     */   public int slotsCount;
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   public boolean has_set_coords;
/*     */   public static final int TYPE_CHEST_BLOCK = 0;
/*     */   public static final int TYPE_WORKBENCH = 1;
/*     */   public static final int TYPE_FURNACE = 2;
/*     */   public static final int TYPE_DISPENSER = 3;
/*     */   public static final int TYPE_ENCHANTMENT_TABLE = 4;
/*     */   public static final int TYPE_BREWING_STAND = 5;
/*     */   public static final int TYPE_MERCHANT = 6;
/*     */   public static final int TYPE_BEACON = 7;
/*     */   public static final int TYPE_ANVIL = 8;
/*     */   public static final int TYPE_HOPPER = 9;
/*     */   public static final int TYPE_DROPPER = 10;
/*     */   public static final int TYPE_HORSE = 11;
/*     */   public static final int TYPE_CHEST_MINECART = 12;
/*     */   public static final int TYPE_HOPPER_MINECART = 13;
/*     */   public boolean useProvidedWindowTitle;
/*     */   public int field_111008_f;
/*     */   
/*     */   public Packet100OpenWindow() {}
/*     */   
/*     */   public Packet100OpenWindow(int par1, int par2, String par3Str, int par4, boolean par5) {
/*  54 */     if (par3Str == null) {
/*  55 */       par3Str = "";
/*     */     }
/*  57 */     this.windowId = par1;
/*  58 */     this.inventoryType = par2;
/*  59 */     this.windowTitle = par3Str;
/*  60 */     this.slotsCount = par4;
/*  61 */     this.useProvidedWindowTitle = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet100OpenWindow(int par1, int par2, String par3Str, int par4, boolean par5, int entity_id) {
/*  71 */     this(par1, par2, par3Str, par4, par5);
/*     */ 
/*     */     
/*  74 */     this.field_111008_f = entity_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet100OpenWindow setCoords(int x, int y, int z) {
/*  79 */     this.x = x;
/*  80 */     this.y = y;
/*  81 */     this.z = z;
/*     */     
/*  83 */     this.has_set_coords = true;
/*     */     
/*  85 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet100OpenWindow setCoords(TileEntity tile_entity) {
/*  90 */     return setCoords(tile_entity.xCoord, tile_entity.yCoord, tile_entity.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  98 */     par1NetHandler.handleOpenWindow(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCoords() {
/* 103 */     return (this.inventoryType == 0 || this.inventoryType == 1 || this.inventoryType == 2 || this.inventoryType == 3 || this.inventoryType == 4 || this.inventoryType == 5 || this.inventoryType == 7 || this.inventoryType == 8 || this.inventoryType == 9 || this.inventoryType == 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity() {
/* 108 */     if (this.inventoryType == 1) {
/* 109 */       return false;
/*     */     }
/* 111 */     return hasCoords();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 119 */     this.windowId = par1DataInput.readByte() & 0xFF;
/* 120 */     this.inventoryType = par1DataInput.readByte() & 0xFF;
/* 121 */     this.windowTitle = readString(par1DataInput, 32);
/* 122 */     this.slotsCount = par1DataInput.readByte() & 0xFF;
/* 123 */     this.useProvidedWindowTitle = par1DataInput.readBoolean();
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (this.inventoryType == 11)
/*     */     {
/* 129 */       this.field_111008_f = par1DataInput.readInt();
/*     */     }
/*     */     
/* 132 */     if (hasCoords()) {
/*     */       
/* 134 */       this.x = par1DataInput.readInt();
/* 135 */       this.y = par1DataInput.readInt();
/* 136 */       this.z = par1DataInput.readInt();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 145 */     par1DataOutput.writeByte(this.windowId & 0xFF);
/* 146 */     par1DataOutput.writeByte(this.inventoryType & 0xFF);
/* 147 */     writeString(this.windowTitle, par1DataOutput);
/* 148 */     par1DataOutput.writeByte(this.slotsCount & 0xFF);
/* 149 */     par1DataOutput.writeBoolean(this.useProvidedWindowTitle);
/*     */ 
/*     */ 
/*     */     
/* 153 */     if (this.inventoryType == 11)
/*     */     {
/* 155 */       par1DataOutput.writeInt(this.field_111008_f);
/*     */     }
/*     */     
/* 158 */     if (hasCoords()) {
/*     */       
/* 160 */       if (!this.has_set_coords) {
/* 161 */         Minecraft.setErrorMessage("Packet100OpenWindow: coords not set for type " + this.inventoryType);
/*     */       }
/* 163 */       par1DataOutput.writeInt(this.x);
/* 164 */       par1DataOutput.writeInt(this.y);
/* 165 */       par1DataOutput.writeInt(this.z);
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
/*     */   public int getPacketSize() {
/* 180 */     int bytes = 2 + Packet.getPacketSizeOfString(this.windowTitle) + 2;
/*     */     
/* 182 */     if (this.inventoryType == 11) {
/* 183 */       bytes += 4;
/*     */     }
/* 185 */     if (hasCoords()) {
/* 186 */       bytes += 12;
/*     */     }
/* 188 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Entity getEntityByID(EntityPlayer player, int id) {
/* 195 */     return (id == player.entityId) ? player : player.worldObj.getEntityByID(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleOpenWindow(EntityClientPlayerMP player) {
/* 200 */     WorldClient world = player.worldObj.getAsWorldClient();
/*     */     
/* 202 */     TileEntity tile_entity = world.getBlockTileEntity(this.x, this.y, this.z);
/*     */     
/* 204 */     if (hasTileEntity() && tile_entity == null) {
/* 205 */       Minecraft.setErrorMessage("handleOpenWindow: no tile entity found at " + StringHelper.getCoordsAsString(this.x, this.y, this.z));
/*     */     }
/* 207 */     if (this.inventoryType == 0) {
/*     */ 
/*     */       
/* 210 */       player.displayGUIChest(this.x, this.y, this.z, new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
/* 211 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 213 */     else if (this.inventoryType == 1) {
/*     */ 
/*     */       
/* 216 */       player.displayGUIWorkbench(this.x, this.y, this.z);
/* 217 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 219 */     else if (this.inventoryType == 2) {
/*     */ 
/*     */       
/* 222 */       TileEntityFurnace var4 = (TileEntityFurnace)tile_entity;
/*     */       
/* 224 */       if (this.useProvidedWindowTitle) {
/* 225 */         var4.setCustomInvName(this.windowTitle);
/*     */       }
/* 227 */       player.displayGUIFurnace(var4);
/* 228 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 230 */     else if (this.inventoryType == 3) {
/*     */ 
/*     */       
/* 233 */       TileEntityDispenser var7 = (TileEntityDispenser)tile_entity;
/*     */       
/* 235 */       if (this.useProvidedWindowTitle) {
/* 236 */         var7.setCustomInvName(this.windowTitle);
/*     */       }
/* 238 */       player.displayGUIDispenser(var7);
/* 239 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 241 */     else if (this.inventoryType == 4) {
/*     */ 
/*     */       
/* 244 */       player.displayGUIEnchantment(this.x, this.y, this.z, this.useProvidedWindowTitle ? this.windowTitle : null);
/* 245 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 247 */     else if (this.inventoryType == 5) {
/*     */ 
/*     */       
/* 250 */       TileEntityBrewingStand var5 = (TileEntityBrewingStand)tile_entity;
/*     */       
/* 252 */       if (this.useProvidedWindowTitle) {
/* 253 */         var5.setCustomInvName(this.windowTitle);
/*     */       }
/* 255 */       player.displayGUIBrewingStand(var5);
/* 256 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 258 */     else if (this.inventoryType == 6) {
/*     */       
/* 260 */       player.displayGUIMerchant(new NpcMerchant(player), this.useProvidedWindowTitle ? this.windowTitle : null);
/* 261 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 263 */     else if (this.inventoryType == 7) {
/*     */ 
/*     */       
/* 266 */       TileEntityBeacon var8 = (TileEntityBeacon)tile_entity;
/* 267 */       player.displayGUIBeacon(var8);
/*     */       
/* 269 */       if (this.useProvidedWindowTitle) {
/* 270 */         var8.setCustomInvName(this.windowTitle);
/*     */       }
/* 272 */       player.openContainer.windowId = this.windowId;
/*     */     
/*     */     }
/* 275 */     else if (this.inventoryType == 8) {
/*     */ 
/*     */       
/* 278 */       tile_entity.setCustomInvName(this.windowTitle);
/*     */       
/* 280 */       player.displayGUIAnvil(this.x, this.y, this.z);
/*     */       
/* 282 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 284 */     else if (this.inventoryType == 9) {
/*     */ 
/*     */       
/* 287 */       TileEntityHopper var3 = (TileEntityHopper)tile_entity;
/*     */       
/* 289 */       if (this.useProvidedWindowTitle) {
/* 290 */         var3.setCustomInvName(this.windowTitle);
/*     */       }
/* 292 */       player.displayGUIHopper(var3);
/* 293 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 295 */     else if (this.inventoryType == 10) {
/*     */ 
/*     */       
/* 298 */       TileEntityDropper var6 = (TileEntityDropper)tile_entity;
/*     */       
/* 300 */       if (this.useProvidedWindowTitle) {
/* 301 */         var6.setCustomInvName(this.windowTitle);
/*     */       }
/* 303 */       player.displayGUIDispenser(var6);
/* 304 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 306 */     else if (this.inventoryType == 11) {
/*     */       
/* 308 */       Entity var9 = getEntityByID(player, this.field_111008_f);
/*     */       
/* 310 */       if (var9 != null && var9 instanceof EntityHorse)
/*     */       {
/* 312 */         player.displayGUIHorse((EntityHorse)var9, new AnimalChest(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
/* 313 */         player.openContainer.windowId = this.windowId;
/*     */       }
/*     */     
/* 316 */     } else if (this.inventoryType == 12) {
/*     */ 
/*     */       
/* 319 */       player.displayGUIChestForMinecart(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
/* 320 */       player.openContainer.windowId = this.windowId;
/*     */     }
/* 322 */     else if (this.inventoryType == 13) {
/*     */ 
/*     */       
/* 325 */       TileEntityHopper var3 = new TileEntityHopper();
/*     */       
/* 327 */       if (this.useProvidedWindowTitle) {
/* 328 */         var3.setCustomInvName(this.windowTitle);
/*     */       }
/* 330 */       player.displayGUIHopper(var3);
/* 331 */       player.openContainer.windowId = this.windowId;
/*     */     }
/*     */     else {
/*     */       
/* 335 */       Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.inventoryType);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet100OpenWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */