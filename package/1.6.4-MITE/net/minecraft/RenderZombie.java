/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderZombie
/*     */   extends RenderBiped
/*     */ {
/*     */   public static final int texture_normal = 0;
/*     */   public static final int texture_villager = 1;
/*     */   public static final int texture_pigman = 2;
/*     */   public static final int texture_revenant = 3;
/*     */   private ModelBiped field_82434_o;
/*     */   private ModelZombieVillager zombieVillagerModel;
/*     */   protected ModelBiped field_82437_k;
/*     */   protected ModelBiped field_82435_l;
/*     */   protected ModelBiped field_82436_m;
/*     */   protected ModelBiped field_82433_n;
/*  21 */   private int field_82431_q = 1;
/*     */ 
/*     */   
/*     */   public RenderZombie() {
/*  25 */     super(new ModelZombie(), 0.5F, 1.0F);
/*  26 */     this.field_82434_o = this.modelBipedMain;
/*  27 */     this.zombieVillagerModel = new ModelZombieVillager();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  32 */     setTexture(0, "textures/entity/zombie/zombie");
/*  33 */     setTexture(1, "textures/entity/zombie/zombie_villager");
/*  34 */     setTexture(2, "textures/entity/zombie_pigman");
/*     */     
/*  36 */     setTexture(3, "textures/entity/zombie/revenant");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82421_b() {
/*  41 */     this.field_82423_g = new ModelZombie(1.0F, true);
/*  42 */     this.field_82425_h = new ModelZombie(0.5F, true);
/*  43 */     this.field_82437_k = this.field_82423_g;
/*  44 */     this.field_82435_l = this.field_82425_h;
/*  45 */     this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
/*  46 */     this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_82429_a(EntityZombie par1EntityZombie, int par2, float par3) {
/*  51 */     func_82427_a(par1EntityZombie);
/*  52 */     return super.func_130006_a(par1EntityZombie, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82426_a(EntityZombie par1EntityZombie, double par2, double par4, double par6, float par8, float par9) {
/*  57 */     func_82427_a(par1EntityZombie);
/*  58 */     super.doRenderLiving(par1EntityZombie, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110863_a(EntityZombie par1EntityZombie) {
/*  66 */     return this.textures[(par1EntityZombie instanceof EntityPigZombie) ? 2 : (par1EntityZombie.isRevenant() ? 3 : (par1EntityZombie.isVillager() ? 1 : 0))];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82428_a(EntityZombie par1EntityZombie, float par2) {
/*  71 */     func_82427_a(par1EntityZombie);
/*  72 */     super.func_130005_c(par1EntityZombie, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82427_a(EntityZombie par1EntityZombie) {
/*  77 */     if (par1EntityZombie.isVillager()) {
/*     */       
/*  79 */       if (this.field_82431_q != this.zombieVillagerModel.func_82897_a()) {
/*     */         
/*  81 */         this.zombieVillagerModel = new ModelZombieVillager();
/*  82 */         this.field_82431_q = this.zombieVillagerModel.func_82897_a();
/*  83 */         this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
/*  84 */         this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
/*     */       } 
/*     */       
/*  87 */       this.mainModel = this.zombieVillagerModel;
/*  88 */       this.field_82423_g = this.field_82436_m;
/*  89 */       this.field_82425_h = this.field_82433_n;
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.mainModel = this.field_82434_o;
/*  94 */       this.field_82423_g = this.field_82437_k;
/*  95 */       this.field_82425_h = this.field_82435_l;
/*     */     } 
/*     */     
/*  98 */     this.modelBipedMain = (ModelBiped)this.mainModel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82430_a(EntityZombie par1EntityZombie, float par2, float par3, float par4) {
/* 103 */     if (par1EntityZombie.isConverting())
/*     */     {
/* 105 */       par3 += (float)(Math.cos(par1EntityZombie.ticksExisted * 3.25D) * Math.PI * 0.25D);
/*     */     }
/*     */     
/* 108 */     super.rotateCorpse(par1EntityZombie, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130005_c(EntityLiving par1EntityLiving, float par2) {
/* 113 */     func_82428_a((EntityZombie)par1EntityLiving, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
/* 118 */     return func_110863_a((EntityZombie)par1EntityLiving);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 123 */     func_82426_a((EntityZombie)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3) {
/* 128 */     return func_82429_a((EntityZombie)par1EntityLiving, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 136 */     return func_82429_a((EntityZombie)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 141 */     func_82428_a((EntityZombie)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 146 */     func_82430_a((EntityZombie)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 151 */     func_82426_a((EntityZombie)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 159 */     return func_110863_a((EntityZombie)par1Entity);
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
/* 170 */     func_82426_a((EntityZombie)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */