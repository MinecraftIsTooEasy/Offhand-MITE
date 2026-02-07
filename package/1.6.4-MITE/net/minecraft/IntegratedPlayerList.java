/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.SocketAddress;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class IntegratedPlayerList
/*    */   extends ServerConfigurationManager
/*    */ {
/*    */   private NBTTagCompound hostPlayerData;
/*    */   
/*    */   public IntegratedPlayerList(IntegratedServer integratedServer) {
/* 13 */     super(integratedServer);
/*    */     
/* 15 */     this.viewDistance = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writePlayerData(ServerPlayer serverPlayer) {
/* 20 */     if (serverPlayer.getCommandSenderName().equals(getIntegratedServer().getServerOwner())) {
/* 21 */       this.hostPlayerData = new NBTTagCompound();
/* 22 */       serverPlayer.writeToNBT(this.hostPlayerData);
/*    */     } 
/*    */     
/* 25 */     super.writePlayerData(serverPlayer);
/*    */   }
/*    */ 
/*    */   
/*    */   public String allowUserToConnect(SocketAddress socketAddress, String string) {
/* 30 */     if (string.equalsIgnoreCase(getIntegratedServer().getServerOwner())) {
/* 31 */       return "That name is already taken.";
/*    */     }
/*    */     
/* 34 */     return super.allowUserToConnect(socketAddress, string);
/*    */   }
/*    */ 
/*    */   
/*    */   public IntegratedServer getIntegratedServer() {
/* 39 */     return (IntegratedServer)super.getServerInstance();
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound getHostPlayerData() {
/* 44 */     return this.hostPlayerData;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntegratedPlayerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */