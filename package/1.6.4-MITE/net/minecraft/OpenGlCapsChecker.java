/*   */ package net.minecraft;
/*   */ 
/*   */ import org.lwjgl.opengl.GLContext;
/*   */ 
/*   */ public class OpenGlCapsChecker {
/*   */   public static boolean checkARBOcclusion() {
/* 7 */     return (GLContext.getCapabilities()).GL_ARB_occlusion_query;
/*   */   }
/*   */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\OpenGlCapsChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */