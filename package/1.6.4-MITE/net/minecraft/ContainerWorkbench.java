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
/*     */ public class ContainerWorkbench
/*     */   extends MITEContainerCrafting
/*     */ {
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   
/*     */   public ContainerWorkbench(EntityPlayer player, int x, int y, int z) {
/* 146 */     super(player);
/*     */     
/* 148 */     this.x = x;
/* 149 */     this.y = y;
/* 150 */     this.z = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMatrixSize() {
/* 155 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void createSlots() {
/* 160 */     addSlotToContainer(new SlotCrafting(this.player, this.craft_matrix, this.craft_result, 0, 124, 35));
/*     */     
/*     */     int var6;
/*     */     
/* 164 */     for (var6 = 0; var6 < 3; var6++) {
/*     */       
/* 166 */       for (int var7 = 0; var7 < 3; var7++)
/*     */       {
/* 168 */         addSlotToContainer(new Slot(this.craft_matrix, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
/*     */       }
/*     */     } 
/*     */     
/* 172 */     for (var6 = 0; var6 < 3; var6++) {
/*     */       
/* 174 */       for (int var7 = 0; var7 < 9; var7++)
/*     */       {
/*     */         
/* 177 */         addSlotToContainer(new Slot(this.player.inventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
/*     */       }
/*     */     } 
/*     */     
/* 181 */     for (var6 = 0; var6 < 9; var6++)
/*     */     {
/*     */       
/* 184 */       addSlotToContainer(new Slot(this.player.inventory, var6, 8 + var6 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/* 190 */     return (this.world.getBlockId(this.x, this.y, this.z) != Block.workbench.blockID) ? false : ((par1EntityPlayer.getDistanceSq(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 198 */     ItemStack var3 = null;
/* 199 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/* 201 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 203 */       ItemStack var5 = var4.getStack();
/* 204 */       var3 = var5.copy();
/*     */       
/* 206 */       if (par2 == 0) {
/*     */         
/* 208 */         if (!mergeItemStack(var5, 10, 46, true))
/*     */         {
/* 210 */           return null;
/*     */         }
/*     */         
/* 213 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 215 */       else if (par2 >= 10 && par2 < 37) {
/*     */         
/* 217 */         if (!mergeItemStack(var5, 37, 46, false))
/*     */         {
/* 219 */           return null;
/*     */         }
/*     */       }
/* 222 */       else if (par2 >= 37 && par2 < 46) {
/*     */         
/* 224 */         if (!mergeItemStack(var5, 10, 37, false))
/*     */         {
/* 226 */           return null;
/*     */         }
/*     */       }
/* 229 */       else if (!mergeItemStack(var5, 10, 46, false)) {
/*     */         
/* 231 */         return null;
/*     */       } 
/*     */       
/* 234 */       if (var5.stackSize == 0) {
/*     */         
/* 236 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 240 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 243 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 245 */         return null;
/*     */       }
/*     */       
/* 248 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 251 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRecipeForbidden(IRecipe recipe) {
/* 256 */     ItemStack output = recipe.getRecipeOutput();
/*     */     
/* 258 */     if (output == null) {
/* 259 */       return false;
/*     */     }
/* 261 */     if (output.getItem().getClass() == ItemKnife.class) {
/*     */       
/* 263 */       if (output.getItem().containsMetal()) {
/* 264 */         return true;
/*     */       }
/* 266 */     } else if ((output.getItem()).itemID == Block.sandStone.blockID) {
/*     */       
/* 268 */       if (output.getItemSubtype() == 2) {
/*     */         
/* 270 */         if (DedicatedServer.tournament_type == EnumTournamentType.wonder) {
/* 271 */           return true;
/*     */         }
/* 273 */       } else if (output.getItemSubtype() == 3) {
/*     */         
/* 275 */         if (DedicatedServer.tournament_type != EnumTournamentType.wonder) {
/* 276 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     return super.isRecipeForbidden(recipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockMetadata() {
/* 286 */     return this.world.getBlockMetadata(this.x, this.y, this.z);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */