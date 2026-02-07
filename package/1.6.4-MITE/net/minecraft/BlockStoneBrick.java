/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class BlockStoneBrick
/*    */   extends Block
/*    */ {
/*  7 */   public static final String[] STONE_BRICK_TYPES = new String[] { "default", "mossy", "cracked", "chiseled" };
/*  8 */   public static final String[] field_94407_b = new String[] { null, "mossy", "cracked", "carved" };
/*    */   
/*    */   private Icon[] field_94408_c;
/*    */ 
/*    */   
/*    */   public BlockStoneBrick(int par1) {
/* 14 */     super(par1, Material.stone, new BlockConstants());
/* 15 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 23 */     if (par2 < 0 || par2 >= field_94407_b.length)
/*    */     {
/* 25 */       par2 = 0;
/*    */     }
/*    */     
/* 28 */     return this.field_94408_c[par2];
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
/* 41 */     return "0=Regular, 1=Mossy, 2=Cracked, 3=Chiseled";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 46 */     return (metadata >= 0 && metadata < 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockSubtypeUnchecked(int metadata) {
/* 51 */     return metadata & 0x3;
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
/* 71 */     this.field_94408_c = new Icon[field_94407_b.length];
/*    */     
/* 73 */     for (int var2 = 0; var2 < this.field_94408_c.length; var2++) {
/*    */       
/* 75 */       String var3 = getTextureName();
/*    */       
/* 77 */       if (field_94407_b[var2] != null)
/*    */       {
/* 79 */         var3 = var3 + "_" + field_94407_b[var2];
/*    */       }
/*    */       
/* 82 */       this.field_94408_c[var2] = par1IconRegister.registerIcon(var3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 88 */     if (info.wasExploded()) {
/* 89 */       return dropBlockAsEntityItem(info, cobblestone);
/*    */     }
/* 91 */     return super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStoneBrick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */