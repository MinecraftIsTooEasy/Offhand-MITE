/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.Timer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ public class StatsComponentMITE
/*     */   extends JComponent
/*     */ {
/*  15 */   private static final DecimalFormat field_120040_a = new DecimalFormat("########0.000");
/*  16 */   private int[] field_120038_b = new int[256];
/*     */   private int field_120039_c;
/*  18 */   private String[] field_120036_d = new String[11];
/*     */   
/*     */   private final MinecraftServer field_120037_e;
/*     */   
/*     */   public StatsComponentMITE(MinecraftServer par1MinecraftServer) {
/*  23 */     this.field_120037_e = par1MinecraftServer;
/*  24 */     setPreferredSize(new Dimension(456, 246));
/*  25 */     setMinimumSize(new Dimension(456, 246));
/*  26 */     setMaximumSize(new Dimension(456, 246));
/*  27 */     (new Timer(500, new StatsComponentINNER1MITE(this))).start();
/*  28 */     setBackground(Color.BLACK);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_120034_a() {
/*  33 */     long var1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/*  34 */     System.gc();
/*  35 */     this.field_120036_d[0] = "Memory use: " + (var1 / 1024L / 1024L) + " mb (" + (Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory()) + "% free)";
/*  36 */     this.field_120036_d[1] = "Threads: " + TcpConnection.field_74471_a.get() + " + " + TcpConnection.field_74469_b.get();
/*  37 */     this.field_120036_d[2] = "Avg tick: " + field_120040_a.format(func_120035_a(this.field_120037_e.tickTimeArray) * 1.0E-6D) + " ms";
/*  38 */     this.field_120036_d[3] = "Avg sent: " + (int)func_120035_a(this.field_120037_e.sentPacketCountArray) + ", Avg size: " + (int)func_120035_a(this.field_120037_e.sentPacketSizeArray);
/*  39 */     this.field_120036_d[4] = "Avg rec: " + (int)func_120035_a(this.field_120037_e.receivedPacketCountArray) + ", Avg size: " + (int)func_120035_a(this.field_120037_e.receivedPacketSizeArray);
/*     */     
/*  41 */     if (this.field_120037_e.worldServers != null)
/*     */     {
/*  43 */       for (int var3 = 0; var3 < this.field_120037_e.worldServers.length; var3++) {
/*     */         
/*  45 */         this.field_120036_d[5 + var3] = "Lvl " + var3 + " tick: " + field_120040_a.format(func_120035_a(this.field_120037_e.timeOfLastDimensionTick[var3]) * 1.0E-6D) + " ms";
/*     */         
/*  47 */         if (this.field_120037_e.worldServers[var3] != null && (this.field_120037_e.worldServers[var3]).theChunkProviderServer != null) {
/*     */           
/*  49 */           this.field_120036_d[5 + var3] = this.field_120036_d[5 + var3] + ", " + (this.field_120037_e.worldServers[var3]).theChunkProviderServer.makeString();
/*  50 */           this.field_120036_d[5 + var3] = this.field_120036_d[5 + var3] + ", Vec3: " + this.field_120037_e.worldServers[var3].getWorldVec3Pool().func_82590_d() + " / " + this.field_120037_e.worldServers[var3].getWorldVec3Pool().getPoolSize();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  55 */     double var5 = 12500.0D;
/*  56 */     this.field_120038_b[this.field_120039_c++ & 0xFF] = (int)(func_120035_a(this.field_120037_e.sentPacketSizeArray) * 100.0D / 12500.0D);
/*  57 */     repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_120035_a(long[] par1ArrayOfLong) {
/*  62 */     long var2 = 0L;
/*     */     
/*  64 */     for (int var4 = 0; var4 < par1ArrayOfLong.length; var4++)
/*     */     {
/*  66 */       var2 += par1ArrayOfLong[var4];
/*     */     }
/*     */     
/*  69 */     return var2 / par1ArrayOfLong.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics par1Graphics) {
/*  74 */     par1Graphics.setColor(new Color(16777215));
/*  75 */     par1Graphics.fillRect(0, 0, 456, 246);
/*     */     
/*     */     int var2;
/*  78 */     for (var2 = 0; var2 < 256; var2++) {
/*     */       
/*  80 */       int var3 = this.field_120038_b[var2 + this.field_120039_c & 0xFF];
/*  81 */       par1Graphics.setColor(new Color(var3 + 28 << 16));
/*  82 */       par1Graphics.fillRect(var2, 100 - var3, 1, var3);
/*     */     } 
/*     */     
/*  85 */     par1Graphics.setColor(Color.BLACK);
/*     */     
/*  87 */     for (var2 = 0; var2 < this.field_120036_d.length; var2++) {
/*     */       
/*  89 */       String var4 = this.field_120036_d[var2];
/*     */       
/*  91 */       if (var4 != null)
/*     */       {
/*  93 */         par1Graphics.drawString(var4, 32, 116 + var2 * 16);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void func_120033_a(StatsComponentMITE par0StatsComponent) {
/* 100 */     par0StatsComponent.func_120034_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatsComponentMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */