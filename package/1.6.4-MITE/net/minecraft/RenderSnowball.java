/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderSnowball
/*    */   extends Render
/*    */ {
/*    */   private Item field_94151_a;
/*    */   private int field_94150_f;
/*    */   
/*    */   public RenderSnowball(Item par1Item, int par2) {
/* 13 */     this.field_94151_a = par1Item;
/* 14 */     this.field_94150_f = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderSnowball(Item par1Item) {
/* 19 */     this(par1Item, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 30 */     if (par1Entity instanceof EntityBrick) {
/* 31 */       this.field_94151_a = ((EntityBrick)par1Entity).getModelItem();
/* 32 */     } else if (par1Entity instanceof EntityGelatinousSphere) {
/* 33 */       this.field_94150_f = ((EntityGelatinousSphere)par1Entity).getModelSubtype();
/*    */     } 
/* 35 */     Icon var10 = this.field_94151_a.getIconFromSubtype(this.field_94150_f);
/*    */     
/* 37 */     if (var10 != null) {
/*    */       
/* 39 */       GL11.glPushMatrix();
/* 40 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 41 */       GL11.glEnable(32826);
/* 42 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 43 */       bindEntityTexture(par1Entity);
/* 44 */       Tessellator var11 = Tessellator.instance;
/*    */       
/* 46 */       if (var10 == ItemPotion.func_94589_d("bottle_splash")) {
/*    */         
/* 48 */         int var12 = PotionHelper.func_77915_a(((EntityPotion)par1Entity).getPotionType(), false);
/* 49 */         float var13 = (var12 >> 16 & 0xFF) / 255.0F;
/* 50 */         float var14 = (var12 >> 8 & 0xFF) / 255.0F;
/* 51 */         float var15 = (var12 & 0xFF) / 255.0F;
/* 52 */         GL11.glColor3f(var13, var14, var15);
/* 53 */         GL11.glPushMatrix();
/* 54 */         func_77026_a(var11, ItemPotion.func_94589_d("overlay"));
/* 55 */         GL11.glPopMatrix();
/* 56 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */       } 
/*    */       
/* 59 */       func_77026_a(var11, var10);
/* 60 */       GL11.glDisable(32826);
/* 61 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 70 */     return TextureMap.locationItemsTexture;
/*    */   }
/*    */ 
/*    */   
/*    */   private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon) {
/* 75 */     float var3 = par2Icon.getMinU();
/* 76 */     float var4 = par2Icon.getMaxU();
/* 77 */     float var5 = par2Icon.getMinV();
/* 78 */     float var6 = par2Icon.getMaxV();
/* 79 */     float var7 = 1.0F;
/* 80 */     float var8 = 0.5F;
/* 81 */     float var9 = 0.25F;
/* 82 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 83 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 84 */     par1Tessellator.startDrawingQuads();
/* 85 */     par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 86 */     par1Tessellator.addVertexWithUV((0.0F - var8), (0.0F - var9), 0.0D, var3, var6);
/* 87 */     par1Tessellator.addVertexWithUV((var7 - var8), (0.0F - var9), 0.0D, var4, var6);
/* 88 */     par1Tessellator.addVertexWithUV((var7 - var8), (var7 - var9), 0.0D, var4, var5);
/* 89 */     par1Tessellator.addVertexWithUV((0.0F - var8), (var7 - var9), 0.0D, var3, var5);
/* 90 */     par1Tessellator.draw();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSnowball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */