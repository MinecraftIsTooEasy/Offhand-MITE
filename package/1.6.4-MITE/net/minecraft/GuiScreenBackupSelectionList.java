/*     */ package net.minecraft;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
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
/*     */ class GuiScreenBackupSelectionList
/*     */   extends GuiScreenSelectLocation
/*     */ {
/*     */   public GuiScreenBackupSelectionList(GuiScreenBackup guiScreenBackup) {
/* 160 */     super(GuiScreenBackup.func_130036_f(guiScreenBackup), guiScreenBackup.width, guiScreenBackup.height, 32, guiScreenBackup.height - 64, 36);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 165 */     return GuiScreenBackup.func_110370_e(this.field_111249_a).size() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/* 171 */     if (i >= GuiScreenBackup.func_110370_e(this.field_111249_a).size()) {
/*     */       return;
/*     */     }
/*     */     
/* 175 */     GuiScreenBackup.func_130029_a(this.field_111249_a, i);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 180 */     return (i == GuiScreenBackup.func_130034_h(this.field_111249_a));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_104086_b(int i) {
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_130003_b() {
/* 190 */     return getSize() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130004_c() {
/* 195 */     this.field_111249_a.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 200 */     if (i < GuiScreenBackup.func_110370_e(this.field_111249_a).size()) {
/* 201 */       func_111246_b(i, j, k, l, tessellator);
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_111246_b(int i, int j, int k, int l, Tessellator tessellator) {
/* 206 */     Backup backup = GuiScreenBackup.func_110370_e(this.field_111249_a).get(i);
/*     */     
/* 208 */     this.field_111249_a.drawString(GuiScreenBackup.func_130032_i(this.field_111249_a), "Backup (" + func_111248_a(Long.valueOf(MinecraftServer.getSystemTimeMillis() - backup.field_110725_b.getTime())) + ")", j + 2, k + 1, 16777215);
/* 209 */     this.field_111249_a.drawString(GuiScreenBackup.func_130033_j(this.field_111249_a), func_111247_a(backup.field_110725_b), j + 2, k + 12, 7105644);
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_111247_a(Date date) {
/* 214 */     return DateFormat.getDateTimeInstance(3, 3).format(date);
/*     */   }
/*     */   
/*     */   private String func_111248_a(Long long_) {
/* 218 */     if (long_.longValue() < 0L) {
/* 219 */       return "right now";
/*     */     }
/* 221 */     long l1 = long_.longValue() / 1000L;
/*     */     
/* 223 */     if (l1 < 60L) {
/* 224 */       return ((l1 == 1L) ? "1 second" : (l1 + " seconds")) + " ago";
/*     */     }
/* 226 */     if (l1 < 3600L) {
/* 227 */       long l = l1 / 60L;
/* 228 */       return ((l == 1L) ? "1 minute" : (l + " minutes")) + " ago";
/*     */     } 
/* 230 */     if (l1 < 86400L) {
/* 231 */       long l = l1 / 3600L;
/* 232 */       return ((l == 1L) ? "1 hour" : (l + " hours")) + " ago";
/*     */     } 
/* 234 */     long l2 = l1 / 86400L;
/* 235 */     return ((l2 == 1L) ? "1 day" : (l2 + " days")) + " ago";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenBackupSelectionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */