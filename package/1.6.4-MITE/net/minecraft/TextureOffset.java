/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextureOffset
/*    */ {
/*    */   public final int textureOffsetX;
/*    */   public final int textureOffsetY;
/*    */   
/*    */   public TextureOffset(int par1, int par2) {
/* 13 */     this.textureOffsetX = par1;
/* 14 */     this.textureOffsetY = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String swtch(String s) {
/* 19 */     char[] chars = s.toCharArray();
/*    */     
/* 21 */     for (int i = 0; i < chars.length; i++) {
/*    */       
/* 23 */       int c = chars[i];
/*    */       
/* 25 */       if (c >= 65 && c <= 90) {
/* 26 */         c = 90 - c - 65;
/* 27 */       } else if (c >= 97 && c <= 122) {
/* 28 */         c = 122 - c - 97;
/* 29 */       } else if (c >= 48 && c <= 57) {
/* 30 */         c = 57 - c - 48;
/*    */       } 
/* 32 */       chars[i] = (char)c;
/*    */     } 
/*    */     
/* 35 */     return new String(chars);
/*    */   }
/*    */   
/*    */   public static void SPN(String s) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureOffset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */