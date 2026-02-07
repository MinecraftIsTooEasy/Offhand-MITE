/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class RenderTntMinecart
/*    */   extends RenderMinecart
/*    */ {
/*    */   protected void func_94146_a(EntityMinecartTNT par1EntityMinecartTNT, float par2, Block par3Block, int par4) {
/*  9 */     int var5 = par1EntityMinecartTNT.func_94104_d();
/*    */     
/* 11 */     if (var5 > -1 && var5 - par2 + 1.0F < 10.0F) {
/*    */       
/* 13 */       float var6 = 1.0F - (var5 - par2 + 1.0F) / 10.0F;
/*    */       
/* 15 */       if (var6 < 0.0F)
/*    */       {
/* 17 */         var6 = 0.0F;
/*    */       }
/*    */       
/* 20 */       if (var6 > 1.0F)
/*    */       {
/* 22 */         var6 = 1.0F;
/*    */       }
/*    */       
/* 25 */       var6 *= var6;
/* 26 */       var6 *= var6;
/* 27 */       float var7 = 1.0F + var6 * 0.3F;
/* 28 */       GL11.glScalef(var7, var7, var7);
/*    */     } 
/*    */     
/* 31 */     super.renderBlockInMinecart(par1EntityMinecartTNT, par2, par3Block, par4);
/*    */     
/* 33 */     if (var5 > -1 && var5 / 5 % 2 == 0) {
/*    */       
/* 35 */       GL11.glDisable(3553);
/* 36 */       GL11.glDisable(2896);
/* 37 */       GL11.glEnable(3042);
/* 38 */       GL11.glBlendFunc(770, 772);
/* 39 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, (1.0F - (var5 - par2 + 1.0F) / 100.0F) * 0.8F);
/* 40 */       GL11.glPushMatrix();
/* 41 */       this.field_94145_f.renderBlockAsItem(Block.tnt, 0, 1.0F);
/* 42 */       GL11.glPopMatrix();
/* 43 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 44 */       GL11.glDisable(3042);
/* 45 */       GL11.glEnable(2896);
/* 46 */       GL11.glEnable(3553);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderBlockInMinecart(EntityMinecart par1EntityMinecart, float par2, Block par3Block, int par4) {
/* 55 */     func_94146_a((EntityMinecartTNT)par1EntityMinecart, par2, par3Block, par4);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderTntMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */