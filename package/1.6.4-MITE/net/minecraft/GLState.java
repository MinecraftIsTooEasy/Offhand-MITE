/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GLState
/*    */ {
/*    */   public final int pname;
/*    */   public Object data;
/*    */   
/*    */   public GLState(int pname) {
/* 14 */     this.pname = pname;
/*    */     
/* 16 */     if (pname == 3042 || pname == 3553) {
/* 17 */       this.data = new Boolean(GL11.glIsEnabled(pname));
/*    */     } else {
/* 19 */       Minecraft.setErrorMessage("GLState: unhandled pname (" + pname + ")");
/*    */     } 
/*    */   }
/*    */   
/*    */   public void restore() {
/* 24 */     if (this.pname == 3042 || this.pname == 3553) {
/*    */       
/* 26 */       if (((Boolean)this.data).booleanValue()) {
/* 27 */         GL11.glEnable(this.pname);
/*    */       } else {
/* 29 */         GL11.glDisable(this.pname);
/*    */       } 
/*    */     } else {
/*    */       
/* 33 */       Minecraft.setErrorMessage("restore: unhandled pname (" + this.pname + ")");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GLState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */