/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class RecipeFireworks
/*     */   implements IRecipe
/*     */ {
/*     */   private ItemStack field_92102_a;
/*   9 */   private float difficulty = 100.0F;
/*     */ 
/*     */   
/*     */   private int[] skillsets;
/*     */ 
/*     */   
/*     */   private Material material_to_check_tool_bench_hardness_against;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  20 */     this.field_92102_a = null;
/*  21 */     int var3 = 0;
/*  22 */     int var4 = 0;
/*  23 */     int var5 = 0;
/*  24 */     int var6 = 0;
/*  25 */     int var7 = 0;
/*  26 */     int var8 = 0;
/*     */     
/*  28 */     for (int var9 = 0; var9 < par1InventoryCrafting.getSizeInventory(); var9++) {
/*     */       
/*  30 */       ItemStack var10 = par1InventoryCrafting.getStackInSlot(var9);
/*     */       
/*  32 */       if (var10 != null)
/*     */       {
/*  34 */         if (var10.itemID == Item.gunpowder.itemID) {
/*     */           
/*  36 */           var4++;
/*     */         }
/*  38 */         else if (var10.itemID == Item.fireworkCharge.itemID) {
/*     */           
/*  40 */           var6++;
/*     */         }
/*  42 */         else if (var10.itemID == Item.dyePowder.itemID) {
/*     */           
/*  44 */           var5++;
/*     */         }
/*  46 */         else if (var10.itemID == Item.paper.itemID) {
/*     */           
/*  48 */           var3++;
/*     */         }
/*  50 */         else if (var10.itemID == Item.glowstone.itemID) {
/*     */           
/*  52 */           var7++;
/*     */         }
/*  54 */         else if (var10.itemID == Item.diamond.itemID) {
/*     */           
/*  56 */           var7++;
/*     */         }
/*  58 */         else if (var10.itemID == Item.fireballCharge.itemID) {
/*     */           
/*  60 */           var8++;
/*     */         }
/*  62 */         else if (var10.itemID == Item.feather.itemID) {
/*     */           
/*  64 */           var8++;
/*     */         }
/*  66 */         else if (var10.itemID == Item.goldNugget.itemID) {
/*     */           
/*  68 */           var8++;
/*     */         }
/*     */         else {
/*     */           
/*  72 */           if (var10.itemID != Item.skull.itemID)
/*     */           {
/*  74 */             return false;
/*     */           }
/*     */           
/*  77 */           var8++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  82 */     var7 += var5 + var8;
/*     */     
/*  84 */     if (var4 <= 3 && var3 <= 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       if (var4 >= 1 && var3 == 1 && var7 == 0) {
/*     */         
/*  91 */         this.field_92102_a = new ItemStack(Item.firework);
/*     */         
/*  93 */         if (var6 > 0) {
/*     */           
/*  95 */           NBTTagCompound var15 = new NBTTagCompound();
/*  96 */           NBTTagCompound var18 = new NBTTagCompound("Fireworks");
/*  97 */           NBTTagList var25 = new NBTTagList("Explosions");
/*     */           
/*  99 */           for (int var22 = 0; var22 < par1InventoryCrafting.getSizeInventory(); var22++) {
/*     */             
/* 101 */             ItemStack var26 = par1InventoryCrafting.getStackInSlot(var22);
/*     */             
/* 103 */             if (var26 != null && var26.itemID == Item.fireworkCharge.itemID && var26.hasTagCompound() && var26.getTagCompound().hasKey("Explosion"))
/*     */             {
/* 105 */               var25.appendTag(var26.getTagCompound().getCompoundTag("Explosion"));
/*     */             }
/*     */           } 
/*     */           
/* 109 */           var18.setTag("Explosions", var25);
/* 110 */           var18.setByte("Flight", (byte)var4);
/* 111 */           var15.setTag("Fireworks", var18);
/* 112 */           this.field_92102_a.setTagCompound(var15);
/*     */         } 
/*     */         
/* 115 */         return true;
/*     */       } 
/* 117 */       if (var4 == 1 && var3 == 0 && var6 == 0 && var5 > 0 && var8 <= 1) {
/*     */         
/* 119 */         this.field_92102_a = new ItemStack(Item.fireworkCharge);
/* 120 */         NBTTagCompound var15 = new NBTTagCompound();
/* 121 */         NBTTagCompound var18 = new NBTTagCompound("Explosion");
/* 122 */         byte var21 = 0;
/* 123 */         ArrayList<Integer> var12 = new ArrayList();
/*     */         
/* 125 */         for (int var13 = 0; var13 < par1InventoryCrafting.getSizeInventory(); var13++) {
/*     */           
/* 127 */           ItemStack var14 = par1InventoryCrafting.getStackInSlot(var13);
/*     */           
/* 129 */           if (var14 != null)
/*     */           {
/* 131 */             if (var14.itemID == Item.dyePowder.itemID) {
/*     */               
/* 133 */               var12.add(Integer.valueOf(ItemDye.dyeColors[var14.getItemSubtype()]));
/*     */             }
/* 135 */             else if (var14.itemID == Item.glowstone.itemID) {
/*     */               
/* 137 */               var18.setBoolean("Flicker", true);
/*     */             }
/* 139 */             else if (var14.itemID == Item.diamond.itemID) {
/*     */               
/* 141 */               var18.setBoolean("Trail", true);
/*     */             }
/* 143 */             else if (var14.itemID == Item.fireballCharge.itemID) {
/*     */               
/* 145 */               var21 = 1;
/*     */             }
/* 147 */             else if (var14.itemID == Item.feather.itemID) {
/*     */               
/* 149 */               var21 = 4;
/*     */             }
/* 151 */             else if (var14.itemID == Item.goldNugget.itemID) {
/*     */               
/* 153 */               var21 = 2;
/*     */             }
/* 155 */             else if (var14.itemID == Item.skull.itemID) {
/*     */               
/* 157 */               var21 = 3;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 162 */         int[] var24 = new int[var12.size()];
/*     */         
/* 164 */         for (int var27 = 0; var27 < var24.length; var27++)
/*     */         {
/* 166 */           var24[var27] = ((Integer)var12.get(var27)).intValue();
/*     */         }
/*     */         
/* 169 */         var18.setIntArray("Colors", var24);
/* 170 */         var18.setByte("Type", var21);
/* 171 */         var15.setTag("Explosion", var18);
/* 172 */         this.field_92102_a.setTagCompound(var15);
/* 173 */         return true;
/*     */       } 
/* 175 */       if (var4 == 0 && var3 == 0 && var6 == 1 && var5 > 0 && var5 == var7) {
/*     */         
/* 177 */         ArrayList<Integer> var16 = new ArrayList();
/*     */         
/* 179 */         for (int var20 = 0; var20 < par1InventoryCrafting.getSizeInventory(); var20++) {
/*     */           
/* 181 */           ItemStack var11 = par1InventoryCrafting.getStackInSlot(var20);
/*     */           
/* 183 */           if (var11 != null)
/*     */           {
/* 185 */             if (var11.itemID == Item.dyePowder.itemID) {
/*     */               
/* 187 */               var16.add(Integer.valueOf(ItemDye.dyeColors[var11.getItemSubtype()]));
/*     */             }
/* 189 */             else if (var11.itemID == Item.fireworkCharge.itemID) {
/*     */               
/* 191 */               this.field_92102_a = var11.copy();
/* 192 */               this.field_92102_a.stackSize = 1;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 197 */         int[] var17 = new int[var16.size()];
/*     */         
/* 199 */         for (int var19 = 0; var19 < var17.length; var19++)
/*     */         {
/* 201 */           var17[var19] = ((Integer)var16.get(var19)).intValue();
/*     */         }
/*     */         
/* 204 */         if (this.field_92102_a != null && this.field_92102_a.hasTagCompound()) {
/*     */           
/* 206 */           NBTTagCompound var23 = this.field_92102_a.getTagCompound().getCompoundTag("Explosion");
/*     */           
/* 208 */           if (var23 == null)
/*     */           {
/* 210 */             return false;
/*     */           }
/*     */ 
/*     */           
/* 214 */           var23.setIntArray("FadeColors", var17);
/* 215 */           return true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 220 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 225 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 230 */     return false;
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
/*     */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/* 244 */     return new CraftingResult(this.field_92102_a.copy(), this.difficulty, this.skillsets, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 252 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 257 */     return this.field_92102_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe setDifficulty(float difficulty) {
/* 267 */     this.difficulty = difficulty;
/* 268 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe scaleDifficulty(float factor) {
/* 273 */     this.difficulty *= factor;
/* 274 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/* 279 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeInLowestCraftingDifficultyDetermination() {}
/*     */   
/*     */   public boolean getIncludeInLowestCraftingDifficultyDetermination() {
/* 286 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsets(int[] skillsets) {
/* 291 */     this.skillsets = skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillset(int skillset) {
/* 296 */     (new int[1])[0] = skillset; this.skillsets = (skillset == 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSkillsets() {
/* 301 */     return this.skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialToCheckToolBenchHardnessAgainst(Material material_to_check_tool_bench_hardness_against) {
/* 306 */     this.material_to_check_tool_bench_hardness_against = material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialToCheckToolBenchHardnessAgainst() {
/* 311 */     return this.material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipeFireworks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */