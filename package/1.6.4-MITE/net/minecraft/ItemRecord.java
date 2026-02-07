/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ItemRecord
/*     */   extends Item
/*     */ {
/*  10 */   private static final Map records = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public final String recordName;
/*     */ 
/*     */   
/*     */   private final String title;
/*     */   
/*     */   private final String composer;
/*     */ 
/*     */   
/*     */   protected ItemRecord(int par1, String par2Str, String texture, String title, String composer) {
/*  22 */     super(par1, Material.vinyl, "C418".equals(composer) ? texture : ("records/" + texture));
/*  23 */     this.recordName = par2Str;
/*  24 */     this.title = title;
/*  25 */     this.composer = composer;
/*     */     
/*  27 */     setMaxStackSize(1);
/*  28 */     setCreativeTab(CreativeTabs.tabMisc);
/*  29 */     records.put(par2Str, this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemRecord(int par1, String par2Str, String texture) {
/*  34 */     this(par1, par2Str, texture, par2Str, "C418");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/*  42 */     return this.itemIcon;
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  73 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/*  75 */     if (rc != null && rc.isBlock() && rc.getBlockHit() == Block.jukebox && rc.block_hit_metadata == 0 && rc.canPlayerEditBlockHit(player, player.getHeldItemStack()) && player.worldObj.isAirOrPassableBlock(rc.block_hit_x, rc.block_hit_y + 1, rc.block_hit_z, false)) {
/*     */       
/*  77 */       if (player.onClient()) {
/*     */         
/*  79 */         player.swingArm();
/*     */       }
/*     */       else {
/*     */         
/*  83 */         World world = player.getWorld();
/*     */         
/*  85 */         int x = rc.block_hit_x;
/*  86 */         int y = rc.block_hit_y;
/*  87 */         int z = rc.block_hit_z;
/*     */         
/*  89 */         Block.jukebox.insertRecord(world, x, y, z, player.getHeldItemStack());
/*  90 */         world.playAuxSFXAtEntity(null, 1005, x, y, z, this.itemID);
/*     */         
/*  92 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */       } 
/*     */       
/*  95 */       return true;
/*     */     } 
/*     */     
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/* 107 */     par3List.add(getRecordTitle());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRecordTitle() {
/* 116 */     return this.composer + " - " + this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack par1ItemStack) {
/* 124 */     return EnumRarity.rare;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemRecord getRecord(String par0Str) {
/* 132 */     return (ItemRecord)records.get(par0Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 137 */     return this.recordName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isUniqueRecord(ItemStack item_stack) {
/* 143 */     if (item_stack.getItem() instanceof ItemRecord) {
/*     */       
/* 145 */       ItemRecord item_record = (ItemRecord)item_stack.getItem();
/*     */       
/* 147 */       return !"C418".equals(item_record.composer);
/*     */     } 
/*     */     
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSignature(ItemStack item_stack) {
/* 156 */     return 101 + item_stack.itemID - Item.recordUnderworld.itemID;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */