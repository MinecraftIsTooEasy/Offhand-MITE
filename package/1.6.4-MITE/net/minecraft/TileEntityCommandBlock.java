/*     */ package net.minecraft;
/*     */ 
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class TileEntityCommandBlock
/*     */   extends TileEntity
/*     */   implements ICommandSender
/*     */ {
/*     */   private int succesCount;
/*  10 */   private String command = "";
/*     */ 
/*     */   
/*  13 */   private String commandSenderName = "@";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(String par1Str) {
/*  20 */     this.command = par1Str;
/*  21 */     onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  29 */     return this.command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int executeCommandOnPowered(World par1World) {
/*  37 */     if (par1World.isRemote)
/*     */     {
/*  39 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  43 */     MinecraftServer var2 = MinecraftServer.getServer();
/*     */     
/*  45 */     if (var2 != null && var2.isCommandBlockEnabled()) {
/*     */       
/*  47 */       ICommandManager var3 = var2.getCommandManager();
/*     */       
/*  49 */       return var3.executeCommand(this, this.command, false);
/*     */     } 
/*     */ 
/*     */     
/*  53 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommandSenderName() {
/*  63 */     return this.commandSenderName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommandSenderName(String par1Str) {
/*  71 */     this.commandSenderName = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendChatToPlayer(ChatMessageComponent par1ChatMessageComponent) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int par1, String par2Str) {
/*  81 */     return (par1 <= 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  89 */     super.writeToNBT(par1NBTTagCompound);
/*  90 */     par1NBTTagCompound.setString("Command", this.command);
/*  91 */     par1NBTTagCompound.setInteger("SuccessCount", this.succesCount);
/*  92 */     par1NBTTagCompound.setString("CustomName", this.commandSenderName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 100 */     super.readFromNBT(par1NBTTagCompound);
/* 101 */     this.command = par1NBTTagCompound.getString("Command");
/* 102 */     this.succesCount = par1NBTTagCompound.getInteger("SuccessCount");
/*     */     
/* 104 */     if (par1NBTTagCompound.hasKey("CustomName"))
/*     */     {
/* 106 */       this.commandSenderName = par1NBTTagCompound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getPlayerCoordinates() {
/* 115 */     return new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public World getEntityWorld() {
/* 120 */     return getWorldObj();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/* 128 */     NBTTagCompound var1 = new NBTTagCompound();
/* 129 */     writeToNBT(var1);
/* 130 */     return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 2, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSignalStrength() {
/* 135 */     return this.succesCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSignalStrength(int par1) {
/* 140 */     this.succesCount = par1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityCommandBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */