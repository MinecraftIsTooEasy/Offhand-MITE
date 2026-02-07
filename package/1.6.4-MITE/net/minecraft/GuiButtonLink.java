/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.URI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiButtonLink
/*    */   extends GuiButton
/*    */ {
/*    */   public GuiButtonLink(int i, int j, int k, int l, int m, String string) {
/* 12 */     super(i, j, k, l, m, string);
/*    */   }
/*    */   
/*    */   public void func_96135_a(String string) {
/*    */     try {
/* 17 */       URI uRI = new URI(string);
/* 18 */       Class<?> clazz = Class.forName("java.awt.Desktop");
/* 19 */       Object object = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 20 */       clazz.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { uRI });
/* 21 */     } catch (Throwable throwable) {
/* 22 */       throwable.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiButtonLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */