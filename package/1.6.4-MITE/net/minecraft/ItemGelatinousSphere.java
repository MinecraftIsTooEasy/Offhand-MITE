/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class ItemGelatinousSphere
/*    */   extends Item
/*    */ {
/*  9 */   public static final String[] names = new String[] { "green", "ochre", "crimson", "gray", "black" };
/*    */   
/*    */   private Icon[] icons;
/*    */   public static final int GREEN = 0;
/*    */   public static final int OCHRE = 1;
/*    */   
/*    */   public ItemGelatinousSphere(int par1) {
/* 16 */     super(par1, Material.slime, "gelatinous_sphere");
/* 17 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */     
/* 19 */     setUnlocalizedName("gelatinousSphere");
/* 20 */     setCraftingDifficultyAsComponent(100.0F);
/*    */   }
/*    */   public static final int CRIMSON = 2; public static final int GRAY = 3; public static final int BLACK = 4;
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 25 */     int var2 = MathHelper.clamp_int(par1, 0, names.length - 1);
/* 26 */     return this.icons[var2];
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 31 */     if (par1ItemStack == null) {
/* 32 */       return getUnlocalizedName();
/*    */     }
/* 34 */     int var2 = MathHelper.clamp_int(par1ItemStack.getItemSubtype(), 0, names.length - 1);
/* 35 */     return getUnlocalizedName() + "." + names[var2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 40 */     for (int var4 = 0; var4 < names.length; var4++)
/*    */     {
/* 42 */       par3List.add(new ItemStack(par1, 1, var4));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 48 */     this.icons = new Icon[names.length];
/*    */     
/* 50 */     for (int var2 = 0; var2 < names.length; var2++)
/*    */     {
/* 52 */       this.icons[var2] = par1IconRegister.registerIcon(getIconString() + "/" + names[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 58 */     if (player.onServer()) {
/*    */       
/* 60 */       int subtype = player.getHeldItemStack().getItemSubtype();
/*    */       
/* 62 */       if (!player.inCreativeMode()) {
/*    */         
/* 64 */         player.convertOneOfHeldItem((ItemStack)null);
/* 65 */         player.addHungerServerSide(0.25F * EnchantmentHelper.getEnduranceModifier(player));
/*    */       } 
/*    */       
/* 68 */       WorldServer world = player.getWorldServer();
/*    */       
/* 70 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 71 */       world.spawnEntityInWorld(new EntityGelatinousSphere(world, player, this, subtype));
/*    */     }
/*    */     else {
/*    */       
/* 75 */       player.bobItem();
/*    */     } 
/*    */     
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAttackDamage(int subtype) {
/* 83 */     return (subtype < 3) ? (subtype + 1) : subtype;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 88 */     if (extended_info) {
/*    */       
/* 90 */       info.add("");
/*    */       
/* 92 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[] { Integer.valueOf(getAttackDamage(item_stack.getItemSubtype())) }));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getDamageType(int subtype) {
/* 98 */     return (subtype == 3 || subtype == 4) ? DamageSource.acid : DamageSource.pepsin;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemGelatinousSphere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */