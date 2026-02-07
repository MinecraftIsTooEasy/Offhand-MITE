/*     */ package net.minecraft;
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
/*     */ public class ContainerPlayer
/*     */   extends MITEContainerCrafting
/*     */ {
/*     */   public ContainerPlayer(EntityPlayer player) {
/* 168 */     super(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMatrixSize() {
/* 173 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void createSlots() {
/* 178 */     addSlotToContainer(new SlotCrafting(this.player, this.craft_matrix, this.craft_result, 0, 144, 36));
/*     */     int y;
/* 180 */     for (y = 0; y < 2; y++) {
/*     */       
/* 182 */       for (int x = 0; x < 2; x++) {
/* 183 */         addSlotToContainer(new Slot(this.craft_matrix, x + y * 2, 88 + x * 18, 26 + y * 18));
/*     */       }
/*     */     } 
/* 186 */     for (y = 0; y < 4; y++) {
/* 187 */       addSlotToContainer(new SlotArmor(this, this.player.inventory, this.player.inventory.getSizeInventory() - 1 - y, 8, 8 + y * 18, y));
/*     */     }
/* 189 */     for (y = 0; y < 3; y++) {
/*     */       
/* 191 */       for (int x = 0; x < 9; x++) {
/* 192 */         addSlotToContainer(new Slot(this.player.inventory, x + (y + 1) * 9, 8 + x * 18, 84 + y * 18));
/*     */       }
/*     */     } 
/* 195 */     for (int hotbar_index = 0; hotbar_index < 9; hotbar_index++) {
/* 196 */       addSlotToContainer(new Slot(this.player.inventory, hotbar_index, 8 + hotbar_index * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer entity_player) {
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer entity_player, int slot_index) {
/* 209 */     ItemStack var3 = null;
/* 210 */     Slot var4 = this.inventorySlots.get(slot_index);
/*     */     
/* 212 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 214 */       ItemStack var5 = var4.getStack();
/* 215 */       var3 = var5.copy();
/*     */       
/* 217 */       if (slot_index == 0) {
/*     */         
/* 219 */         if (!mergeItemStack(var5, 9, 45, true))
/*     */         {
/* 221 */           return null;
/*     */         }
/*     */         
/* 224 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 226 */       else if (slot_index >= 1 && slot_index < 5) {
/*     */         
/* 228 */         if (!mergeItemStack(var5, 9, 45, false))
/*     */         {
/* 230 */           return null;
/*     */         }
/*     */       }
/* 233 */       else if (slot_index >= 5 && slot_index < 9) {
/*     */         
/* 235 */         if (!mergeItemStack(var5, 9, 45, false))
/*     */         {
/* 237 */           return null;
/*     */         
/*     */         }
/*     */       }
/* 241 */       else if (var3.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)var3.getItem()).armorType)).getHasStack() && !this.player.hasCurse(Curse.cannot_wear_armor, true)) {
/*     */         
/* 243 */         int var6 = 5 + ((ItemArmor)var3.getItem()).armorType;
/*     */         
/* 245 */         if (!mergeItemStack(var5, var6, var6 + 1, false))
/*     */         {
/* 247 */           return null;
/*     */         }
/*     */       }
/* 250 */       else if (slot_index >= 9 && slot_index < 36) {
/*     */         
/* 252 */         if (!mergeItemStack(var5, 36, 45, false))
/*     */         {
/* 254 */           return null;
/*     */         }
/*     */       }
/* 257 */       else if (slot_index >= 36 && slot_index < 45) {
/*     */         
/* 259 */         if (!mergeItemStack(var5, 9, 36, false))
/*     */         {
/* 261 */           return null;
/*     */         }
/*     */       }
/* 264 */       else if (!mergeItemStack(var5, 9, 45, false)) {
/*     */         
/* 266 */         return null;
/*     */       } 
/*     */       
/* 269 */       if (var5.stackSize == 0) {
/*     */         
/* 271 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 275 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 278 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 280 */         return null;
/*     */       }
/*     */       
/* 283 */       var4.onPickupFromSlot(entity_player, var5);
/*     */     } 
/*     */     
/* 286 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRecipeForbidden(IRecipe recipe) {
/* 291 */     ItemStack output = recipe.getRecipeOutput();
/*     */     
/* 293 */     if (output == null) {
/* 294 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     if (output.getItem().getClass() == ItemKnife.class) {
/*     */       
/* 302 */       if (output.getItem().containsMetal()) {
/* 303 */         return true;
/*     */       }
/* 305 */     } else if ((output.getItem()).itemID == Block.sandStone.blockID) {
/*     */       
/* 307 */       if (output.getItemSubtype() == 2) {
/*     */         
/* 309 */         if (DedicatedServer.tournament_type == EnumTournamentType.wonder) {
/* 310 */           return true;
/*     */         }
/* 312 */       } else if (output.getItemSubtype() == 3) {
/*     */         
/* 314 */         if (DedicatedServer.tournament_type != EnumTournamentType.wonder) {
/* 315 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     return super.isRecipeForbidden(recipe);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */