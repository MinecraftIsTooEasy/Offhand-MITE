/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWritableBook
/*    */   extends Item
/*    */ {
/*    */   public ItemWritableBook(int par1) {
/*  9 */     super(par1, "book_writable");
/* 10 */     setMaterial(new Material[] { Material.paper, Material.leather });
/* 11 */     setMaxStackSize(1);
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
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 25 */     player.displayGUIBook(player.getHeldItemStack());
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getShareTag() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean validBookTagPages(NBTTagCompound par0NBTTagCompound) {
/* 40 */     if (par0NBTTagCompound == null)
/*    */     {
/* 42 */       return false;
/*    */     }
/* 44 */     if (!par0NBTTagCompound.hasKey("pages"))
/*    */     {
/* 46 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 50 */     NBTTagList var1 = (NBTTagList)par0NBTTagCompound.getTag("pages");
/*    */     
/* 52 */     for (int var2 = 0; var2 < var1.tagCount(); var2++) {
/*    */       
/* 54 */       NBTTagString var3 = (NBTTagString)var1.tagAt(var2);
/*    */       
/* 56 */       if (var3.data == null)
/*    */       {
/* 58 */         return false;
/*    */       }
/*    */       
/* 61 */       if (var3.data.length() > 256)
/*    */       {
/* 63 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeRenamed() {
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemWritableBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */