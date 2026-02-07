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
/*     */ public final class Packet4UpdateTime
/*     */   extends Packet
/*     */ {
/*  15 */   public long[] world_age = new long[4];
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
/*     */   public Packet4UpdateTime() {}
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
/*     */   public Packet4UpdateTime(long[] par1) {
/*  40 */     boolean use_small_packet_instead = true;
/*     */     
/*  42 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  44 */       if (!Packet92UpdateTimeSmall.isTimeSuitable(par1[i])) {
/*  45 */         use_small_packet_instead = false;
/*     */       }
/*  47 */       this.world_age[i] = par1[i];
/*     */     } 
/*     */     
/*  50 */     if (use_small_packet_instead) {
/*     */       
/*  52 */       Minecraft.setErrorMessage("Packet4UpdateTime: use Packet92UpdateTimeSmall instead");
/*  53 */       (new Exception()).printStackTrace();
/*     */     } 
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
/*     */   public Packet4UpdateTime(MinecraftServer mc_server) {
/*  69 */     this(getTotalWorldTimes(mc_server));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  80 */     for (int i = 0; i < 4; i++) {
/*  81 */       this.world_age[i] = par1DataInput.readLong();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  92 */     for (int i = 0; i < 4; i++) {
/*  93 */       par1DataOutput.writeLong(this.world_age[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 101 */     par1NetHandler.handleUpdateTime(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 110 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProcessAsync() {
/* 137 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet4UpdateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */