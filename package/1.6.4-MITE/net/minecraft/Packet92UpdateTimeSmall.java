/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Packet92UpdateTimeSmall
/*     */   extends Packet
/*     */ {
/*  15 */   public long[] world_age = new long[4];
/*     */ 
/*     */   
/*     */   public Packet92UpdateTimeSmall() {}
/*     */   
/*     */   public Packet92UpdateTimeSmall(long[] par1) {
/*  21 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  23 */       if (isTimeSuitable(par1[i])) {
/*     */         
/*  25 */         this.world_age[i] = (int)par1[i];
/*     */       }
/*     */       else {
/*     */         
/*  29 */         Minecraft.setErrorMessage("Packet92UpdateTimeSmall: time is too large!");
/*  30 */         this.world_age[i] = getLargestSuitableTime();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getLargestSuitableTime() {
/*  37 */     return 2147483647L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTimeSuitable(long time) {
/*  43 */     return (time <= getLargestSuitableTime());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areAllWorldTotalTimesSuitable(WorldServer[] world_servers) {
/*  48 */     for (int i = 0; i < world_servers.length; i++) {
/*     */       
/*  50 */       if (!isTimeSuitable(world_servers[i].getTotalWorldTime())) {
/*  51 */         return false;
/*     */       }
/*     */     } 
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long[] getTotalWorldTimes(MinecraftServer mc_server) {
/*  59 */     long[] total_world_times = new long[4];
/*     */     
/*  61 */     for (int i = 0; i < 4; i++) {
/*  62 */       total_world_times[i] = mc_server.worldServers[i].getTotalWorldTime();
/*     */     }
/*  64 */     return total_world_times;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet92UpdateTimeSmall(MinecraftServer mc_server) {
/*  69 */     this(getTotalWorldTimes(mc_server));
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  74 */     for (int i = 0; i < 4; i++) {
/*  75 */       this.world_age[i] = par1DataInput.readInt();
/*     */     }
/*     */   }
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  80 */     for (int i = 0; i < 4; i++) {
/*  81 */       par1DataOutput.writeInt((int)this.world_age[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  86 */     par1NetHandler.handleUpdateTimeSmall(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  91 */     return 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProcessAsync() {
/* 107 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet92UpdateTimeSmall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */