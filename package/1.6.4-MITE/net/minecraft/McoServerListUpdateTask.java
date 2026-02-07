/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.TimerTask;
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
/*     */ class McoServerListUpdateTask
/*     */   extends TimerTask
/*     */ {
/*     */   private McoClient field_140066_b;
/*     */   
/*     */   private McoServerListUpdateTask(McoServerList mcoServerList) {}
/*     */   
/*     */   public void run() {
/* 110 */     if (!McoServerList.func_98249_b(this.field_140067_a)) {
/* 111 */       func_140064_c();
/* 112 */       func_140062_a();
/* 113 */       func_140063_b();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140062_a() {
/*     */     try {
/* 119 */       if (McoServerList.func_100014_a(this.field_140067_a) != null) {
/* 120 */         this.field_140066_b = new McoClient(McoServerList.func_100014_a(this.field_140067_a));
/* 121 */         List list = (this.field_140066_b.func_96382_a()).field_96430_d;
/* 122 */         if (list != null) {
/* 123 */           func_140065_a(list);
/* 124 */           McoServerList.func_98247_a(this.field_140067_a, list);
/*     */         } 
/*     */       } 
/* 127 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 128 */       Minecraft.getMinecraft().getLogAgent().logSevere(exceptionMcoService.toString());
/* 129 */     } catch (IOException iOException) {
/* 130 */       Minecraft.getMinecraft().getLogAgent().logWarning("Realms: could not parse response from server");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140063_b() {
/*     */     try {
/* 136 */       if (McoServerList.func_100014_a(this.field_140067_a) != null) {
/* 137 */         int i = this.field_140066_b.func_130106_e();
/* 138 */         McoServerList.func_130122_a(this.field_140067_a, i);
/*     */       } 
/* 140 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 141 */       Minecraft.getMinecraft().getLogAgent().logSevere(exceptionMcoService.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140064_c() {
/*     */     try {
/* 147 */       if (McoServerList.func_100014_a(this.field_140067_a) != null) {
/* 148 */         McoClient mcoClient = new McoClient(McoServerList.func_100014_a(this.field_140067_a));
/* 149 */         McoServerList.func_140057_b(this.field_140067_a, mcoClient.func_96379_c());
/*     */       } 
/* 151 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 152 */       Minecraft.getMinecraft().getLogAgent().logSevere(exceptionMcoService.toString());
/* 153 */       McoServerList.func_140057_b(this.field_140067_a, 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140065_a(List<?> list) {
/* 158 */     Collections.sort(list, new McoServerListUpdateTaskComparator(this, McoServerList.func_100014_a(this.field_140067_a).getUsername(), null));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoServerListUpdateTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */