/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class GuiSmallButton
/*    */   extends GuiButton
/*    */ {
/*    */   private final EnumOptions enumOptions;
/*    */   
/*    */   public GuiSmallButton(int i, int j, int k, String string) {
/* 10 */     this(i, j, k, null, string);
/*    */   }
/*    */   
/*    */   public GuiSmallButton(int i, int j, int k, int l, int m, String string) {
/* 14 */     super(i, j, k, l, m, string);
/* 15 */     this.enumOptions = null;
/*    */   }
/*    */   
/*    */   public GuiSmallButton(int i, int j, int k, EnumOptions enumOptions, String string) {
/* 19 */     super(i, j, k, 150, 20, string);
/* 20 */     this.enumOptions = enumOptions;
/*    */   }
/*    */   
/*    */   public EnumOptions returnEnumOptions() {
/* 24 */     return this.enumOptions;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSmallButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */