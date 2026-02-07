/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSubtypes
/*    */ {
/*    */   private final String[] textures;
/*    */   private final String[] names;
/*    */   private Icon[] icons;
/*    */   
/*    */   public BlockSubtypes(String[] types) {
/* 13 */     this(types, types);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockSubtypes(String[] textures, String[] names) {
/* 18 */     this.textures = textures;
/* 19 */     this.names = names;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getTextures() {
/* 24 */     return this.textures;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getNames() {
/* 29 */     return this.names;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIcons(Icon[] icons) {
/* 34 */     this.icons = icons;
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon[] getIcons() {
/* 39 */     return this.icons;
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int index) {
/* 44 */     return this.icons[index];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSubtypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */