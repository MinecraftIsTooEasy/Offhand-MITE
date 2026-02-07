/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class ItemSkull
/*     */   extends Item {
/*   7 */   private static final String[] skullTypes = new String[] { "skeleton", "wither", "zombie", "char", "creeper" };
/*   8 */   public static final String[] field_94587_a = new String[] { "skeleton", "wither", "zombie", "steve", "creeper" };
/*     */   
/*     */   private Icon[] field_94586_c;
/*     */ 
/*     */   
/*     */   public ItemSkull(int par1) {
/*  14 */     super(par1, Material.bone, "skull");
/*  15 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/* 104 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/* 106 */     if (rc == null || !rc.isBlock()) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (rc.face_hit.isBottom()) {
/* 110 */       return false;
/*     */     }
/* 112 */     return player.tryPlaceHeldItemAsBlock(rc, Block.skull);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 120 */     for (int var4 = 0; var4 < skullTypes.length; var4++)
/*     */     {
/* 122 */       par3List.add(new ItemStack(par1, 1, var4));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/* 131 */     if (par1 < 0 || par1 >= skullTypes.length)
/*     */     {
/* 133 */       par1 = 0;
/*     */     }
/*     */     
/* 136 */     return this.field_94586_c[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata(int par1) {
/* 144 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 153 */     int var2 = par1ItemStack.getItemSubtype();
/*     */     
/* 155 */     if (var2 < 0 || var2 >= skullTypes.length)
/*     */     {
/* 157 */       var2 = 0;
/*     */     }
/*     */     
/* 160 */     return getUnlocalizedName() + "." + skullTypes[var2];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemDisplayName(ItemStack par1ItemStack) {
/* 165 */     if (par1ItemStack == null) {
/* 166 */       return "Skull";
/*     */     }
/* 168 */     return (par1ItemStack.getItemSubtype() == 3 && par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner")) ? StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[] { par1ItemStack.getTagCompound().getString("SkullOwner") }) : super.getItemDisplayName(par1ItemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 173 */     this.field_94586_c = new Icon[field_94587_a.length];
/*     */     
/* 175 */     for (int var2 = 0; var2 < field_94587_a.length; var2++)
/*     */     {
/* 177 */       this.field_94586_c[var2] = par1IconRegister.registerIcon(getIconString() + "_" + field_94587_a[var2]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */