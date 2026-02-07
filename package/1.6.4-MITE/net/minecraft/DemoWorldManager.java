/*     */ package net.minecraft;
/*     */ 
/*     */ public class DemoWorldManager
/*     */   extends ItemInWorldManager
/*     */ {
/*     */   private boolean field_73105_c;
/*     */   private boolean demoTimeExpired;
/*     */   private int field_73104_e;
/*     */   private int field_73102_f;
/*     */   
/*     */   public DemoWorldManager(World par1World) {
/*  12 */     super(par1World);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendDemoReminder() {
/*  68 */     if (this.field_73104_e > 100) {
/*     */       
/*  70 */       this.thisPlayerMP.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("demo.reminder"));
/*  71 */       this.field_73104_e = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockClicked(int x, int y, int z, EnumFace face) {
/*  93 */     if (this.demoTimeExpired) {
/*  94 */       sendDemoReminder();
/*     */     } else {
/*  96 */       super.onBlockClicked(x, y, z, face);
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
/*     */   
/*     */   public boolean tryHarvestBlock(int par1, int par2, int par3) {
/* 112 */     return this.demoTimeExpired ? false : super.tryHarvestBlock(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DemoWorldManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */