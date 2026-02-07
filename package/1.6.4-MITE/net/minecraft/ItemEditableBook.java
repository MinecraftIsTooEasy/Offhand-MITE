/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemEditableBook
/*     */   extends Item
/*     */ {
/*     */   public ItemEditableBook(int par1) {
/*  12 */     super(par1, "book_written");
/*  13 */     setMaterial(new Material[] { Material.paper, Material.leather });
/*  14 */     setMaxStackSize(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean validBookTagContents(NBTTagCompound par0NBTTagCompound) {
/*  19 */     if (!ItemWritableBook.validBookTagPages(par0NBTTagCompound))
/*     */     {
/*  21 */       return false;
/*     */     }
/*  23 */     if (!par0NBTTagCompound.hasKey("title"))
/*     */     {
/*  25 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  29 */     String var1 = par0NBTTagCompound.getString("title");
/*  30 */     return (var1 != null && var1.length() <= 16) ? par0NBTTagCompound.hasKey("author") : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemDisplayName(ItemStack par1ItemStack) {
/*  37 */     if (par1ItemStack != null && par1ItemStack.hasTagCompound()) {
/*     */       
/*  39 */       NBTTagCompound var2 = par1ItemStack.getTagCompound();
/*  40 */       NBTTagString var3 = (NBTTagString)var2.getTag("title");
/*     */       
/*  42 */       if (var3 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  48 */         String title = var3.toString().trim();
/*     */         
/*  50 */         if (this instanceof ItemReferencedBook || title.isEmpty()) {
/*  51 */           return title;
/*     */         }
/*  53 */         title = StringHelper.stripLeading("\"", StringHelper.stripTrailing("\"", title)).trim();
/*     */         
/*  55 */         if (!title.startsWith("\"")) {
/*  56 */           title = "\"" + title;
/*     */         }
/*  58 */         if (!title.endsWith("\"")) {
/*  59 */           title = title + "\"";
/*     */         }
/*  61 */         return title;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  67 */     return super.getItemDisplayName(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/*  76 */     if (par1ItemStack.hasTagCompound()) {
/*     */       
/*  78 */       NBTTagCompound var5 = par1ItemStack.getTagCompound();
/*  79 */       NBTTagString var6 = (NBTTagString)var5.getTag("author");
/*     */       
/*  81 */       if (var6 != null)
/*     */       {
/*  83 */         par3List.add(EnumChatFormatting.GRAY + String.format(StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] { var6.data }), new Object[0]));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  99 */     ItemStack held_item_stack = player.getHeldItemStack();
/*     */     
/* 101 */     player.displayGUIBook(held_item_stack);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getShareTag() {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack par1ItemStack) {
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeRenamed() {
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEditableBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */