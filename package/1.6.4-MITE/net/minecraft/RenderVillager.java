/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderVillager
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   public static final int body_texture_farmer = 1;
/*     */   public static final int body_texture_librarian = 2;
/*     */   public static final int body_texture_priest = 3;
/*     */   public static final int body_texture_smith = 4;
/*     */   public static final int body_texture_butcher = 5;
/*     */   protected ModelVillager villagerModel;
/*     */   
/*     */   public RenderVillager() {
/*  26 */     super(new ModelVillager(0.0F), 0.5F);
/*  27 */     this.villagerModel = (ModelVillager)this.mainModel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  32 */     setTexture(0, "textures/entity/villager/villager");
/*  33 */     setTexture(1, "textures/entity/villager/farmer");
/*  34 */     setTexture(2, "textures/entity/villager/librarian");
/*  35 */     setTexture(3, "textures/entity/villager/priest");
/*  36 */     setTexture(4, "textures/entity/villager/smith");
/*  37 */     setTexture(5, "textures/entity/villager/butcher");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldVillagerRenderPass(EntityVillager par1EntityVillager, int par2, float par3) {
/*  45 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderVillager(EntityVillager par1EntityVillager, double par2, double par4, double par6, float par8, float par9) {
/*  50 */     super.doRenderLiving(par1EntityVillager, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110902_a(EntityVillager par1EntityVillager) {
/*  55 */     switch (par1EntityVillager.getProfession()) {
/*     */ 
/*     */       
/*     */       case 0:
/*  59 */         return this.textures[1];
/*     */ 
/*     */       
/*     */       case 1:
/*  63 */         return this.textures[2];
/*     */ 
/*     */       
/*     */       case 2:
/*  67 */         return this.textures[3];
/*     */ 
/*     */       
/*     */       case 3:
/*  71 */         return this.textures[4];
/*     */ 
/*     */       
/*     */       case 4:
/*  75 */         return this.textures[5];
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderVillagerEquipedItems(EntityVillager par1EntityVillager, float par2) {
/*  85 */     super.renderEquippedItems(par1EntityVillager, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void preRenderVillager(EntityVillager par1EntityVillager, float par2) {
/*  90 */     float var3 = 0.9375F;
/*     */     
/*  92 */     if (par1EntityVillager.getGrowingAge() < 0) {
/*     */       
/*  94 */       var3 = (float)(var3 * 0.5D);
/*  95 */       this.shadowSize = 0.25F;
/*     */     }
/*     */     else {
/*     */       
/*  99 */       this.shadowSize = 0.5F;
/*     */     } 
/*     */     
/* 102 */     GL11.glScalef(var3, var3, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 107 */     renderVillager((EntityVillager)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 116 */     preRenderVillager((EntityVillager)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 124 */     return shouldVillagerRenderPass((EntityVillager)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 129 */     renderVillagerEquipedItems((EntityVillager)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 134 */     renderVillager((EntityVillager)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 142 */     return func_110902_a((EntityVillager)par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 153 */     renderVillager((EntityVillager)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */