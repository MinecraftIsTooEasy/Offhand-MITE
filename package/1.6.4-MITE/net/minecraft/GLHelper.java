/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class GLHelper
/*    */ {
/*    */   private static final int max_stack_depth = 16;
/* 15 */   private static final Object[] states_stack = new Object[16];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int stack_depth;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void pushState(int pname) {
/* 28 */     states_stack[stack_depth++] = new GLState(pname);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void popState(int pname) {
/* 43 */     GLState state = (GLState)states_stack[--stack_depth];
/*    */     
/* 45 */     states_stack[stack_depth] = null;
/*    */     
/* 47 */     if (state.pname != pname) {
/*    */       
/* 49 */       Minecraft.setErrorMessage("popState: pname mismatch");
/* 50 */       (new Exception()).printStackTrace();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 57 */     state.restore();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void restore(int pname) {
/* 62 */     popState(pname);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void enable(int pname) {
/* 68 */     pushState(pname);
/* 69 */     GL11.glEnable(pname);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void disable(int pname) {
/* 75 */     pushState(pname);
/* 76 */     GL11.glDisable(pname);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GLHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */