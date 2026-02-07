/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.input.Mouse;
/*    */ import org.lwjgl.opengl.Display;
/*    */ 
/*    */ public class MouseHelper
/*    */ {
/*    */   public int deltaX;
/*    */   public int deltaY;
/*    */   
/*    */   public void grabMouseCursor() {
/* 12 */     Mouse.setGrabbed(true);
/* 13 */     this.deltaX = 0;
/* 14 */     this.deltaY = 0;
/*    */   }
/*    */   
/*    */   public void ungrabMouseCursor() {
/* 18 */     Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
/* 19 */     Mouse.setGrabbed(false);
/*    */   }
/*    */   
/*    */   public void mouseXYChange() {
/* 23 */     this.deltaX = Mouse.getDX();
/* 24 */     this.deltaY = Mouse.getDY();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MouseHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */