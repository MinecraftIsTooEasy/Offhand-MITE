/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MerchantRecipeList
/*     */   extends ArrayList
/*     */ {
/*     */   public MerchantRecipeList() {}
/*     */   
/*     */   public MerchantRecipeList(NBTTagCompound par1NBTTagCompound) {
/*  14 */     readRecipiesFromTags(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MerchantRecipe canRecipeBeUsed(ItemStack par1ItemStack, ItemStack par2ItemStack, int par3) {
/*  22 */     if (par3 > 0 && par3 < size()) {
/*     */       
/*  24 */       MerchantRecipe var6 = (MerchantRecipe)get(par3);
/*  25 */       return (par1ItemStack.itemID == (var6.getItemToBuy()).itemID && ((par2ItemStack == null && !var6.hasSecondItemToBuy()) || (var6.hasSecondItemToBuy() && par2ItemStack != null && (var6.getSecondItemToBuy()).itemID == par2ItemStack.itemID)) && par1ItemStack.stackSize >= (var6.getItemToBuy()).stackSize && (!var6.hasSecondItemToBuy() || par2ItemStack.stackSize >= (var6.getSecondItemToBuy()).stackSize)) ? var6 : null;
/*     */     } 
/*     */ 
/*     */     
/*  29 */     for (int var4 = 0; var4 < size(); var4++) {
/*     */       
/*  31 */       MerchantRecipe var5 = (MerchantRecipe)get(var4);
/*     */       
/*  33 */       if (par1ItemStack.itemID == (var5.getItemToBuy()).itemID && par1ItemStack.stackSize >= (var5.getItemToBuy()).stackSize && ((!var5.hasSecondItemToBuy() && par2ItemStack == null) || (var5.hasSecondItemToBuy() && par2ItemStack != null && (var5.getSecondItemToBuy()).itemID == par2ItemStack.itemID && par2ItemStack.stackSize >= (var5.getSecondItemToBuy()).stackSize)))
/*     */       {
/*  35 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/*  39 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToListWithCheck(MerchantRecipe par1MerchantRecipe) {
/*  48 */     for (int var2 = 0; var2 < size(); var2++) {
/*     */       
/*  50 */       MerchantRecipe var3 = (MerchantRecipe)get(var2);
/*     */       
/*  52 */       if (par1MerchantRecipe.hasSameIDsAs(var3)) {
/*     */         
/*  54 */         if (par1MerchantRecipe.hasSameItemsAs(var3))
/*     */         {
/*  56 */           set(var2, (E)par1MerchantRecipe);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     add((E)par1MerchantRecipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSizeOfMerchantRecipeList() {
/*  68 */     int bytes = 1;
/*     */     
/*  70 */     for (int i = 0; i < size(); i++) {
/*     */       
/*  72 */       MerchantRecipe recipe = (MerchantRecipe)get(i);
/*     */       
/*  74 */       bytes += Packet.getPacketSizeOfItemStack(recipe.getItemToBuy());
/*  75 */       bytes += Packet.getPacketSizeOfItemStack(recipe.getItemToSell());
/*     */       
/*  77 */       bytes++;
/*     */       
/*  79 */       if (recipe.getSecondItemToBuy() != null) {
/*  80 */         bytes += Packet.getPacketSizeOfItemStack(recipe.getSecondItemToBuy());
/*     */       }
/*  82 */       bytes++;
/*     */     } 
/*     */     
/*  85 */     return bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeRecipiesToStream(DataOutputStream par1DataOutputStream) throws IOException {
/*  90 */     par1DataOutputStream.writeByte((byte)(size() & 0xFF));
/*     */     
/*  92 */     for (int var2 = 0; var2 < size(); var2++) {
/*     */       
/*  94 */       MerchantRecipe var3 = (MerchantRecipe)get(var2);
/*  95 */       Packet.writeItemStack(var3.getItemToBuy(), par1DataOutputStream);
/*  96 */       Packet.writeItemStack(var3.getItemToSell(), par1DataOutputStream);
/*  97 */       ItemStack var4 = var3.getSecondItemToBuy();
/*  98 */       par1DataOutputStream.writeBoolean((var4 != null));
/*     */       
/* 100 */       if (var4 != null)
/*     */       {
/* 102 */         Packet.writeItemStack(var4, par1DataOutputStream);
/*     */       }
/*     */       
/* 105 */       par1DataOutputStream.writeBoolean(var3.func_82784_g());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static MerchantRecipeList readRecipiesFromStream(DataInputStream par0DataInputStream) throws IOException {
/* 111 */     MerchantRecipeList var1 = new MerchantRecipeList();
/* 112 */     int var2 = par0DataInputStream.readByte() & 0xFF;
/*     */     
/* 114 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 116 */       ItemStack var4 = Packet.readItemStack(par0DataInputStream);
/* 117 */       ItemStack var5 = Packet.readItemStack(par0DataInputStream);
/* 118 */       ItemStack var6 = null;
/*     */       
/* 120 */       if (par0DataInputStream.readBoolean())
/*     */       {
/* 122 */         var6 = Packet.readItemStack(par0DataInputStream);
/*     */       }
/*     */       
/* 125 */       boolean var7 = par0DataInputStream.readBoolean();
/* 126 */       MerchantRecipe var8 = new MerchantRecipe(var4, var6, var5);
/*     */       
/* 128 */       if (var7)
/*     */       {
/* 130 */         var8.func_82785_h();
/*     */       }
/*     */       
/* 133 */       var1.add((E)var8);
/*     */     } 
/*     */     
/* 136 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readRecipiesFromTags(NBTTagCompound par1NBTTagCompound) {
/* 141 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Recipes");
/*     */     
/* 143 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 145 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 146 */       add((E)new MerchantRecipe(var4));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getRecipiesAsTags() {
/* 152 */     NBTTagCompound var1 = new NBTTagCompound();
/* 153 */     NBTTagList var2 = new NBTTagList("Recipes");
/*     */     
/* 155 */     for (int var3 = 0; var3 < size(); var3++) {
/*     */       
/* 157 */       MerchantRecipe var4 = (MerchantRecipe)get(var3);
/* 158 */       var2.appendTag(var4.writeToTags());
/*     */     } 
/*     */     
/* 161 */     var1.setTag("Recipes", var2);
/* 162 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MerchantRecipeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */