/*     */ package net.minecraft;
/*     */ 
/*     */ import net.minecraft.server.MinecraftServer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConvertingProgressUpdate
/*     */   implements IProgressUpdate
/*     */ {
/* 115 */   private long field_96245_b = MinecraftServer.getSystemTimeMillis();
/*     */ 
/*     */ 
/*     */   
/*     */   public ConvertingProgressUpdate(MinecraftServer minecraftServer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayProgressMessage(String string) {}
/*     */ 
/*     */   
/*     */   public void setLoadingProgress(int i) {
/* 127 */     if (MinecraftServer.getSystemTimeMillis() - this.field_96245_b >= 1000L) {
/* 128 */       this.field_96245_b = MinecraftServer.getSystemTimeMillis();
/* 129 */       this.mcServer.getLogAgent().logInfo("Converting... " + i + "%");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetProgresAndWorkingMessage(String string) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ConvertingProgressUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */