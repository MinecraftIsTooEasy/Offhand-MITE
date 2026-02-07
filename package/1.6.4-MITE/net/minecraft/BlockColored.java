/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockColored
/*    */   extends Block
/*    */ {
/*    */   private Icon[] iconArray;
/*    */   
/*    */   public BlockColored(int par1, Material par2Material, BlockConstants block_constants) {
/* 13 */     super(par1, par2Material, block_constants);
/* 14 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 22 */     return this.iconArray[par2 % this.iconArray.length];
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
/*    */   public String getMetadataNotes() {
/* 35 */     return "All bits used for color";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 40 */     return (metadata >= 0 && metadata < 16);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 45 */     return metadata;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getBlockFromDye(int par0) {
/* 53 */     return (par0 ^ 0xFFFFFFFF) & 0xF;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getDyeFromBlock(int par0) {
/* 61 */     return (par0 ^ 0xFFFFFFFF) & 0xF;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 81 */     this.iconArray = new Icon[16];
/*    */     
/* 83 */     for (int var2 = 0; var2 < this.iconArray.length; var2++)
/*    */     {
/* 85 */       this.iconArray[var2] = par1IconRegister.registerIcon(getTextureName() + "_" + ItemDye.dyeItemNames[getDyeFromBlock(var2)]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 91 */     return (this.blockMaterial == Material.hardened_clay) ? "colored" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockColored.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */