/*    */ package net.minecraft;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderHelper
/*    */ {
/* 10 */   private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);
/* 11 */   private static final Vec3 field_82884_b = Vec3.createVectorHelper(0.20000000298023224D, 1.0D, -0.699999988079071D).normalize();
/* 12 */   private static final Vec3 field_82885_c = Vec3.createVectorHelper(-0.20000000298023224D, 1.0D, 0.699999988079071D).normalize();
/*    */   
/*    */   public static void disableStandardItemLighting() {
/* 15 */     GL11.glDisable(2896);
/* 16 */     GL11.glDisable(16384);
/* 17 */     GL11.glDisable(16385);
/* 18 */     GL11.glDisable(2903);
/*    */   }
/*    */   
/*    */   public static void enableStandardItemLighting() {
/* 22 */     GL11.glEnable(2896);
/* 23 */     GL11.glEnable(16384);
/* 24 */     GL11.glEnable(16385);
/* 25 */     GL11.glEnable(2903);
/* 26 */     GL11.glColorMaterial(1032, 5634);
/* 27 */     float f1 = 0.4F;
/* 28 */     float f2 = 0.6F;
/* 29 */     float f3 = 0.0F;
/*    */     
/* 31 */     GL11.glLight(16384, 4611, setColorBuffer(field_82884_b.xCoord, field_82884_b.yCoord, field_82884_b.zCoord, 0.0D));
/* 32 */     GL11.glLight(16384, 4609, setColorBuffer(f2, f2, f2, 1.0F));
/* 33 */     GL11.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 34 */     GL11.glLight(16384, 4610, setColorBuffer(f3, f3, f3, 1.0F));
/*    */     
/* 36 */     GL11.glLight(16385, 4611, setColorBuffer(field_82885_c.xCoord, field_82885_c.yCoord, field_82885_c.zCoord, 0.0D));
/* 37 */     GL11.glLight(16385, 4609, setColorBuffer(f2, f2, f2, 1.0F));
/* 38 */     GL11.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 39 */     GL11.glLight(16385, 4610, setColorBuffer(f3, f3, f3, 1.0F));
/*    */     
/* 41 */     GL11.glShadeModel(7424);
/* 42 */     GL11.glLightModel(2899, setColorBuffer(f1, f1, f1, 1.0F));
/*    */   }
/*    */   
/*    */   private static FloatBuffer setColorBuffer(double d, double e, double f, double g) {
/* 46 */     return setColorBuffer((float)d, (float)e, (float)f, (float)g);
/*    */   }
/*    */   
/*    */   private static FloatBuffer setColorBuffer(float f, float g, float h, float i) {
/* 50 */     colorBuffer.clear();
/* 51 */     colorBuffer.put(f).put(g).put(h).put(i);
/* 52 */     colorBuffer.flip();
/* 53 */     return colorBuffer;
/*    */   }
/*    */   
/*    */   public static void enableGUIStandardItemLighting() {
/* 57 */     GL11.glPushMatrix();
/* 58 */     GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
/* 59 */     GL11.glRotatef(165.0F, 1.0F, 0.0F, 0.0F);
/* 60 */     enableStandardItemLighting();
/* 61 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */