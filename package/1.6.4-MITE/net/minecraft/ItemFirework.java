/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemFirework
/*     */   extends Item
/*     */ {
/*     */   public ItemFirework(int par1) {
/*  11 */     super(par1, new Material[] { Material.gunpowder, Material.blaze, Material.coal }, "fireworks");
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  53 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/*  55 */     if (rc != null && rc.isBlock()) {
/*     */       
/*  57 */       if (player.onClient()) {
/*     */         
/*  59 */         player.swingArm();
/*     */       }
/*     */       else {
/*     */         
/*  63 */         World world = player.getWorld();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  68 */         if (world.spawnEntityInWorld(new EntityFireworkRocket(world, (rc.block_hit_x + rc.block_hit_offset_x), (rc.block_hit_y + rc.block_hit_offset_y), (rc.block_hit_z + rc.block_hit_offset_z), player.getHeldItemStack()))) {
/*     */           
/*  70 */           if (!player.inCreativeMode()) {
/*  71 */             player.convertOneOfHeldItem((ItemStack)null);
/*     */           }
/*     */         } else {
/*     */           
/*  75 */           return false;
/*     */         } 
/*     */       } 
/*     */       
/*  79 */       return true;
/*     */     } 
/*     */     
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/*  91 */     if (par1ItemStack.hasTagCompound()) {
/*     */       
/*  93 */       NBTTagCompound var5 = par1ItemStack.getTagCompound().getCompoundTag("Fireworks");
/*     */       
/*  95 */       if (var5 != null) {
/*     */         
/*  97 */         if (var5.hasKey("Flight"))
/*     */         {
/*  99 */           par3List.add(StatCollector.translateToLocal("item.fireworks.flight") + " " + var5.getByte("Flight"));
/*     */         }
/*     */         
/* 102 */         NBTTagList var6 = var5.getTagList("Explosions");
/*     */         
/* 104 */         if (var6 != null && var6.tagCount() > 0)
/*     */         {
/* 106 */           for (int var7 = 0; var7 < var6.tagCount(); var7++) {
/*     */             
/* 108 */             NBTTagCompound var8 = (NBTTagCompound)var6.tagAt(var7);
/* 109 */             ArrayList<String> var9 = new ArrayList();
/* 110 */             ItemFireworkCharge.func_92107_a(var8, var9);
/*     */             
/* 112 */             if (var9.size() > 0) {
/*     */               
/* 114 */               for (int var10 = 1; var10 < var9.size(); var10++)
/*     */               {
/* 116 */                 var9.set(var10, "  " + (String)var9.get(var10));
/*     */               }
/*     */               
/* 119 */               par3List.addAll(var9);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFirework.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */