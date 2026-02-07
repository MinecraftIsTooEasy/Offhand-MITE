/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class RenderWitch
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   private final ModelWitch witchModel;
/*     */   
/*     */   public RenderWitch() {
/*  13 */     super(new ModelWitch(0.0F), 0.5F);
/*  14 */     this.witchModel = (ModelWitch)this.mainModel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  19 */     setTexture(0, "textures/entity/witch");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82412_a(EntityWitch par1EntityWitch, double par2, double par4, double par6, float par8, float par9) {
/*  24 */     ItemStack var10 = par1EntityWitch.getHeldItemStack();
/*  25 */     this.witchModel.field_82900_g = (var10 != null);
/*  26 */     super.doRenderLiving(par1EntityWitch, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getWitchTextures(EntityWitch par1EntityWitch) {
/*  32 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82411_a(EntityWitch par1EntityWitch, float par2) {
/*  37 */     float var3 = 1.0F;
/*  38 */     GL11.glColor3f(var3, var3, var3);
/*  39 */     super.renderEquippedItems(par1EntityWitch, par2);
/*  40 */     ItemStack var4 = par1EntityWitch.getHeldItemStack();
/*     */     
/*  42 */     if (var4 != null) {
/*     */       
/*  44 */       GL11.glPushMatrix();
/*     */ 
/*     */       
/*  47 */       if (this.mainModel.isChild) {
/*     */         
/*  49 */         float var5 = 0.5F;
/*  50 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/*  51 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/*  52 */         GL11.glScalef(var5, var5, var5);
/*     */       } 
/*     */       
/*  55 */       this.witchModel.villagerNose.postRender(0.0625F);
/*  56 */       GL11.glTranslatef(-0.0625F, 0.53125F, 0.21875F);
/*     */       
/*  58 */       if (var4.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
/*     */         
/*  60 */         float var5 = 0.5F;
/*  61 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/*  62 */         var5 *= 0.75F;
/*  63 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*  64 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  65 */         GL11.glScalef(var5, -var5, var5);
/*     */       
/*     */       }
/*  68 */       else if (var4.getItem() instanceof ItemBow) {
/*     */         
/*  70 */         float var5 = 0.625F;
/*  71 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/*  72 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*  73 */         GL11.glScalef(var5, -var5, var5);
/*  74 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  75 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*  77 */       else if (Item.itemsList[var4.itemID].isFull3D()) {
/*     */         
/*  79 */         float var5 = 0.625F;
/*     */         
/*  81 */         if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering()) {
/*     */           
/*  83 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  84 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         } 
/*     */         
/*  87 */         func_82410_b();
/*  88 */         GL11.glScalef(var5, -var5, var5);
/*  89 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  90 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else {
/*     */         
/*  94 */         float var5 = 0.375F;
/*  95 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/*  96 */         GL11.glScalef(var5, var5, var5);
/*  97 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/*  98 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  99 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */       
/* 102 */       GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
/* 103 */       GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
/* 104 */       this.renderManager.itemRenderer.renderItem(par1EntityWitch, var4, 0);
/*     */       
/* 106 */       if (var4.getItem().requiresMultipleRenderPasses())
/*     */       {
/* 108 */         this.renderManager.itemRenderer.renderItem(par1EntityWitch, var4, 1);
/*     */       }
/*     */       
/* 111 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82410_b() {
/* 117 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82409_b(EntityWitch par1EntityWitch, float par2) {
/* 122 */     float var3 = 0.9375F;
/* 123 */     GL11.glScalef(var3, var3, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 128 */     func_82412_a((EntityWitch)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 137 */     func_82409_b((EntityWitch)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 142 */     func_82411_a((EntityWitch)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 147 */     func_82412_a((EntityWitch)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 155 */     return getWitchTextures((EntityWitch)par1Entity);
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
/* 166 */     func_82412_a((EntityWitch)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderWitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */