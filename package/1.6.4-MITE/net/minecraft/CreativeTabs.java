/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class CreativeTabs
/*     */ {
/*   7 */   public static final CreativeTabs[] creativeTabArray = new CreativeTabs[12];
/*   8 */   public static final CreativeTabs tabBlock = new CreativeTabCombat(0, "buildingBlocks");
/*   9 */   public static final CreativeTabs tabDecorations = new CreativeTabBlock(1, "decorations");
/*  10 */   public static final CreativeTabs tabRedstone = new CreativeTabDeco(2, "redstone");
/*  11 */   public static final CreativeTabs tabTransport = new CreativeTabRedstone(3, "transportation");
/*     */   
/*  13 */   public static final CreativeTabs tabMisc = new CreativeTabTransport(4, "misc");
/*  14 */   public static final CreativeTabs tabAllSearch = (new CreativeTabMisc(5, "search")).setBackgroundImageName("item_search.png");
/*  15 */   public static final CreativeTabs tabFood = new CreativeTabSearch(6, "food");
/*     */   
/*  17 */   public static final CreativeTabs tabTools = new CreativeTabFood(7, "tools");
/*     */   
/*  19 */   public static final CreativeTabs tabCombat = new CreativeTabTools(8, "combat");
/*  20 */   public static final CreativeTabs tabBrewing = new CreativeTabBrewing(9, "brewing");
/*  21 */   public static final CreativeTabs tabMaterials = new CreativeTabMaterial(10, "materials");
/*  22 */   public static final CreativeTabs tabInventory = (new CreativeTabInventory(11, "inventory")).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
/*     */   
/*     */   private final int tabIndex;
/*     */   
/*     */   private final String tabLabel;
/*  27 */   private String backgroundImageName = "items.png";
/*     */ 
/*     */   
/*     */   private boolean hasScrollbar = true;
/*     */   
/*     */   private boolean drawTitle = true;
/*     */ 
/*     */   
/*     */   public CreativeTabs(int par1, String par2Str) {
/*  36 */     this.tabIndex = par1;
/*  37 */     this.tabLabel = par2Str;
/*  38 */     creativeTabArray[par1] = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabIndex() {
/*  43 */     return this.tabIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTabLabel() {
/*  48 */     return this.tabLabel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTranslatedTabLabel() {
/*  56 */     return "itemGroup." + getTabLabel();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getTabIconItem() {
/*  61 */     return Item.itemsList[getTabIconItemIndex()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTabIconItemIndex() {
/*  69 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBackgroundImageName() {
/*  74 */     return this.backgroundImageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setBackgroundImageName(String par1Str) {
/*  79 */     this.backgroundImageName = par1Str;
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawInForegroundOfTab() {
/*  85 */     return this.drawTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setNoTitle() {
/*  90 */     this.drawTitle = false;
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldHidePlayerInventory() {
/*  96 */     return this.hasScrollbar;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setNoScrollbar() {
/* 101 */     this.hasScrollbar = false;
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTabColumn() {
/* 110 */     return this.tabIndex % 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTabInFirstRow() {
/* 118 */     return (this.tabIndex < 6);
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
/*     */   public void displayAllReleventItems(List par1List) {
/* 162 */     Item[] var2 = Item.itemsList;
/* 163 */     int var3 = var2.length;
/*     */     
/* 165 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 167 */       Item var5 = var2[var4];
/*     */       
/* 169 */       if (var5 != null && var5.getCreativeTab() == this)
/*     */       {
/* 171 */         var5.getSubItems(var5.itemID, this, par1List);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     addEnchantmentBooksToList(par1List);
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
/*     */   public void addEnchantmentBooksToList(List<ItemStack> par1List) {
/* 217 */     for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
/*     */       
/* 219 */       Enchantment enchantment = Enchantment.get(i);
/*     */       
/* 221 */       if (enchantment != null && enchantment.isOnCreativeTab(this))
/*     */       {
/* 223 */         par1List.add(Item.enchantedBook.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getNumLevels())));
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CreativeTabs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */