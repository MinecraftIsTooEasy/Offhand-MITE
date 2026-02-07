/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RenderingScheme
/*    */ {
/*    */   public static final int VANILLA_164 = 0;
/*    */   public static final int MITE_164 = 1;
/*    */   public static final int MITE_164_EXPERIMENTAL_1 = 101;
/*    */   public static final int MITE_164_EXPERIMENTAL_2 = 102;
/* 15 */   private static String[] descriptor = new String[128];
/*    */ 
/*    */   
/*    */   static {
/* 19 */     descriptor[0] = "Vanilla 1.6.4";
/* 20 */     descriptor[1] = "MITE 1.6.4";
/*    */ 
/*    */ 
/*    */     
/* 24 */     descriptor[101] = "MITE 1.6.4 Experimental #1";
/* 25 */     descriptor[102] = "MITE 1.6.4 Experimental #2";
/*    */   }
/*    */   
/* 28 */   public static int current = 1;
/*    */ 
/*    */   
/*    */   public static String getSchemeDescriptor(int scheme_index) {
/* 32 */     return (scheme_index < 0 || scheme_index >= descriptor.length) ? null : descriptor[scheme_index];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setCurrent(int scheme_index) {
/* 38 */     if (getSchemeDescriptor(scheme_index) == null) {
/*    */       
/* 40 */       if (Minecraft.theMinecraft != null) {
/* 41 */         Minecraft.theMinecraft.getLogAgent().logWarning("Invalid rendering scheme (" + scheme_index + "), reverting to " + getSchemeDescriptor(1) + " (" + '\001' + ")");
/*    */       }
/* 43 */       scheme_index = 1;
/*    */     }
/*    */     else {
/*    */       
/* 47 */       Minecraft.theMinecraft.getLogAgent().logInfo("Rendering scheme: " + getSchemeDescriptor(scheme_index));
/*    */     } 
/*    */     
/* 50 */     current = scheme_index;
/*    */     
/* 52 */     Tessellator.instance = (current == 0) ? new Tessellator() : new TessellatorMITE();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderingScheme.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */