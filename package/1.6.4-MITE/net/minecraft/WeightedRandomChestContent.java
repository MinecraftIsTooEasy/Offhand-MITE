/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightedRandomChestContent
/*     */   extends WeightedRandomItem
/*     */ {
/*     */   private final ItemStack theItemId;
/*     */   private int min_quantity;
/*     */   private int max_quantity;
/*     */   
/*     */   public WeightedRandomChestContent(int item_id, int subtype, int min_quantity, int max_quantity, int weight) {
/*  28 */     super(weight);
/*  29 */     this.theItemId = new ItemStack(item_id, 1, subtype);
/*  30 */     this.min_quantity = min_quantity;
/*  31 */     this.max_quantity = max_quantity;
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
/*     */   public WeightedRandomChestContent(ItemStack item_stack, int min_quantity, int max_quantity, int weight) {
/*  44 */     super(weight);
/*  45 */     this.theItemId = item_stack;
/*  46 */     this.min_quantity = min_quantity;
/*  47 */     this.max_quantity = max_quantity;
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
/*     */   private static ItemStack tryAddArtifact(World world, Random random, IInventory inventory, float[] chances_of_artifact) {
/*  81 */     if (chances_of_artifact == null) {
/*  82 */       return null;
/*     */     }
/*  84 */     int artifact_type = -1;
/*     */     
/*  86 */     for (int i = 0; i < chances_of_artifact.length; i++) {
/*     */       
/*  88 */       if (random.nextFloat() < chances_of_artifact[i]) {
/*     */         
/*  90 */         artifact_type = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  95 */     if (artifact_type < 0) {
/*  96 */       return null;
/*     */     }
/*  98 */     ItemStack item_stack = null;
/*     */     
/* 100 */     if (artifact_type == 0 && world.getDayOfWorld() >= 40 && world.worldInfo.hasAchievementUnlockedOrIsNull(AchievementList.bookcase))
/*     */     {
/* 102 */       for (int attempt = 0; attempt < 10; attempt++) {
/*     */         
/* 104 */         int index = random.nextInt(9) + 1;
/*     */         
/* 106 */         ItemStack trial = ItemReferencedBook.generateBook(index);
/*     */         
/* 108 */         if (!world.worldInfo.hasSignatureBeenAdded(trial.getSignature())) {
/*     */           
/* 110 */           item_stack = trial;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 116 */     if (item_stack == null) {
/* 117 */       return null;
/*     */     }
/* 119 */     if (item_stack.stackSize < 1 || item_stack.stackSize > item_stack.getMaxStackSize()) {
/*     */       
/* 121 */       Minecraft.setErrorMessage("tryAddArtifact: stackSize of " + item_stack.stackSize + " is not suitable for " + item_stack);
/* 122 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     for (int j = 0; j < chances_of_artifact.length; j++) {
/* 130 */       chances_of_artifact[j] = 0.0F;
/*     */     }
/* 132 */     return item_stack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateChestContents(World world, int y, Random par0Random, WeightedRandomChestContent[] par1ArrayOfWeightedRandomChestContent, IInventory par2IInventory, int par3, float[] chances_of_artifact) {
/* 138 */     ItemStack unique_item_stack = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (par3 > 0) {
/*     */       
/* 145 */       unique_item_stack = tryAddArtifact(world, par0Random, par2IInventory, chances_of_artifact);
/*     */       
/* 147 */       if (unique_item_stack != null) {
/* 148 */         par3--;
/*     */       }
/*     */     } 
/* 151 */     for (int var4 = 0; var4 < par3; var4++) {
/*     */       
/* 153 */       WeightedRandomChestContent var5 = null;
/*     */       
/* 155 */       for (int attempt = 0; attempt < 4; attempt++) {
/*     */         
/* 157 */         var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(par0Random, (WeightedRandomItem[])par1ArrayOfWeightedRandomChestContent);
/*     */         
/* 159 */         Item item = var5.theItemId.getItem();
/*     */         
/* 161 */         if (var5.theItemId.hasSignature()) {
/*     */           
/* 163 */           if (unique_item_stack == null)
/*     */           {
/*     */             
/* 166 */             if (!var5.theItemId.hasSignatureThatHasBeenAddedToWorld(world))
/*     */             {
/*     */ 
/*     */               
/* 170 */               unique_item_stack = var5.theItemId;
/*     */               
/* 172 */               var5 = null;
/*     */             }
/*     */           
/*     */           }
/* 176 */         } else if ((item instanceof ItemHoe || item instanceof ItemFishingRod) && y < 48) {
/*     */           
/* 178 */           var5 = null;
/*     */         } else {
/*     */           int min_day_of_world;
/*     */ 
/*     */ 
/*     */           
/* 184 */           if (item instanceof ItemAxe || item instanceof ItemHoe || item instanceof ItemMattock) {
/* 185 */             min_day_of_world = 10;
/* 186 */           } else if (item instanceof ItemPickaxe || item instanceof ItemIngot || item instanceof ItemCoin) {
/* 187 */             min_day_of_world = 20;
/*     */           } else {
/* 189 */             min_day_of_world = 0;
/*     */           } 
/* 191 */           if (world.isOverworld() && world.getDayOfWorld() < min_day_of_world) {
/*     */             
/* 193 */             var5 = null;
/*     */ 
/*     */           
/*     */           }
/* 197 */           else if (Minecraft.isInTournamentMode()) {
/*     */             
/* 199 */             if (item instanceof ItemIngot || item instanceof ItemNugget || item instanceof ItemAxe || item instanceof ItemPickaxe || item instanceof ItemCoin) {
/*     */               
/* 201 */               var5 = null;
/*     */             } else {
/*     */               break;
/*     */             } 
/*     */           } else {
/*     */             break;
/*     */           } 
/*     */         } 
/* 209 */       }  if (var5 != null) {
/*     */ 
/*     */         
/* 212 */         int var6 = var5.min_quantity + par0Random.nextInt(var5.max_quantity - var5.min_quantity + 1);
/*     */         
/* 214 */         ItemStack item_stack = var5.theItemId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 236 */         if (item_stack.getMaxStackSize() >= var6) {
/*     */           
/* 238 */           ItemStack var7 = item_stack.copy();
/* 239 */           var7.stackSize = var6;
/*     */ 
/*     */ 
/*     */           
/* 243 */           var7.applyRandomItemStackDamageForChest();
/*     */           
/* 245 */           par2IInventory.setInventorySlotContents(par0Random.nextInt(par2IInventory.getSizeInventory()), var7);
/*     */         }
/*     */         else {
/*     */           
/* 249 */           for (int var9 = 0; var9 < var6; var9++) {
/*     */             
/* 251 */             ItemStack var8 = item_stack.copy();
/* 252 */             var8.stackSize = 1;
/*     */ 
/*     */ 
/*     */             
/* 256 */             var8.applyRandomItemStackDamageForChest();
/*     */             
/* 258 */             par2IInventory.setInventorySlotContents(par0Random.nextInt(par2IInventory.getSizeInventory()), var8);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 263 */     if (unique_item_stack != null) {
/*     */       
/* 265 */       Debug.println("Adding unique to chest: " + unique_item_stack);
/*     */       
/* 267 */       unique_item_stack.addSignatureToTheWorld(world);
/* 268 */       par2IInventory.setInventorySlotContents(par0Random.nextInt(par2IInventory.getSizeInventory()), unique_item_stack.copy());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void generateDispenserContents(Random par0Random, WeightedRandomChestContent[] par1ArrayOfWeightedRandomChestContent, TileEntityDispenser par2TileEntityDispenser, int par3) {
/* 277 */     for (int var4 = 0; var4 < par3; var4++) {
/*     */       
/* 279 */       WeightedRandomChestContent var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(par0Random, (WeightedRandomItem[])par1ArrayOfWeightedRandomChestContent);
/* 280 */       int var6 = var5.min_quantity + par0Random.nextInt(var5.max_quantity - var5.min_quantity + 1);
/*     */       
/* 282 */       if (var5.theItemId.getMaxStackSize() >= var6) {
/*     */         
/* 284 */         ItemStack var7 = var5.theItemId.copy();
/* 285 */         var7.stackSize = var6;
/* 286 */         par2TileEntityDispenser.setInventorySlotContents(par0Random.nextInt(par2TileEntityDispenser.getSizeInventory()), var7);
/*     */       }
/*     */       else {
/*     */         
/* 290 */         for (int var9 = 0; var9 < var6; var9++) {
/*     */           
/* 292 */           ItemStack var8 = var5.theItemId.copy();
/* 293 */           var8.stackSize = 1;
/* 294 */           par2TileEntityDispenser.setInventorySlotContents(par0Random.nextInt(par2TileEntityDispenser.getSizeInventory()), var8);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static WeightedRandomChestContent[] func_92080_a(WeightedRandomChestContent[] par0ArrayOfWeightedRandomChestContent, WeightedRandomChestContent... par1ArrayOfWeightedRandomChestContent) {
/* 302 */     WeightedRandomChestContent[] var2 = new WeightedRandomChestContent[par0ArrayOfWeightedRandomChestContent.length + par1ArrayOfWeightedRandomChestContent.length];
/* 303 */     int var3 = 0;
/*     */     
/* 305 */     for (int var4 = 0; var4 < par0ArrayOfWeightedRandomChestContent.length; var4++)
/*     */     {
/* 307 */       var2[var3++] = par0ArrayOfWeightedRandomChestContent[var4];
/*     */     }
/*     */     
/* 310 */     WeightedRandomChestContent[] var8 = par1ArrayOfWeightedRandomChestContent;
/* 311 */     int var5 = par1ArrayOfWeightedRandomChestContent.length;
/*     */     
/* 313 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 315 */       WeightedRandomChestContent var7 = var8[var6];
/* 316 */       var2[var3++] = var7;
/*     */     } 
/*     */     
/* 319 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WeightedRandomChestContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */