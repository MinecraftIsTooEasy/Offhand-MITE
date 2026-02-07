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
/*     */ public class FoodStats
/*     */ {
/*     */   private int satiation;
/*     */   private int nutrition;
/*     */   private float hunger;
/*     */   private float hunger_for_nutrition_only;
/*     */   private float heal_progress;
/*     */   private float starve_progress;
/*     */   private EntityPlayer player;
/* 145 */   private float global_hunger_rate = 1.0F;
/*     */ 
/*     */   
/*     */   public FoodStats(EntityPlayer player) {
/* 149 */     this.player = player;
/* 150 */     this.satiation = this.nutrition = getNutritionLimit();
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
/*     */   public void addFoodValue(Item item) {
/* 164 */     addSatiation(item.getSatiation(this.player));
/* 165 */     addNutrition(item.getNutrition());
/*     */     
/* 167 */     if (this.player instanceof ServerPlayer) {
/*     */       
/* 169 */       this.player.getAsEntityPlayerMP().addInsulinResistance(item.getInsulinResponse());
/* 170 */       this.player.getAsEntityPlayerMP().addNutrients(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getHungerPerTick() {
/* 176 */     return 0.002F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getHungerPerFoodUnit() {
/* 181 */     return 4.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ServerPlayer par1EntityPlayer) {
/* 189 */     if (par1EntityPlayer.isGhost() || par1EntityPlayer.isZevimrgvInTournament()) {
/*     */       return;
/*     */     }
/* 192 */     if (par1EntityPlayer.isDead || par1EntityPlayer.getHealth() <= 0.0F) {
/*     */       return;
/*     */     }
/* 195 */     par1EntityPlayer.decrementNutrients();
/*     */     
/* 197 */     par1EntityPlayer.decrementInsulinResistance();
/*     */     
/* 199 */     float hunger_factor = par1EntityPlayer.getWetnessAndMalnourishmentHungerMultiplier();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     addHungerServerSide(getHungerPerTick() * hunger_factor);
/*     */     
/* 206 */     if (!par1EntityPlayer.inCreativeMode()) {
/* 207 */       this.hunger_for_nutrition_only += getHungerPerTick() * 0.25F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (this.hunger >= getHungerPerFoodUnit()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 220 */       this.hunger -= getHungerPerFoodUnit();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if (this.satiation > 0 || this.nutrition > 0)
/*     */       {
/* 229 */         if (this.satiation < 1 || (this.hunger_for_nutrition_only + 0.001F >= getHungerPerFoodUnit() && this.nutrition > 0)) {
/*     */           
/* 231 */           this.nutrition--;
/* 232 */           this.hunger_for_nutrition_only = 0.0F;
/*     */         }
/*     */         else {
/*     */           
/* 236 */           this.satiation--;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     if (par1EntityPlayer.inBed() && par1EntityPlayer.isOnHitList()) {
/* 249 */       par1EntityPlayer.addHungerServerSide(getHungerPerTick() * 20.0F);
/*     */     }
/* 251 */     if (this.player.isStarving()) {
/*     */       
/* 253 */       this.heal_progress = 0.0F;
/*     */       
/* 255 */       this.starve_progress += 0.002F;
/*     */       
/* 257 */       if (this.starve_progress >= 1.0F)
/*     */       {
/* 259 */         if (par1EntityPlayer.getHealth() > 10.0F || this.player.worldObj.difficultySetting >= 3 || (par1EntityPlayer.getHealth() > 1.0F && this.player.worldObj.difficultySetting >= 2)) {
/* 260 */           par1EntityPlayer.attackEntityFrom(new Damage(DamageSource.starve, 1.0F));
/*     */         }
/* 262 */         this.starve_progress--;
/* 263 */         this.hunger_for_nutrition_only = 0.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 268 */       this.heal_progress += (4.0E-4F + this.nutrition * 2.0E-5F) * (par1EntityPlayer.isMalnourished() ? 0.25F : 1.0F) * (par1EntityPlayer.inBed() ? 4.0F : 1.0F) * EnchantmentHelper.getRegenerationModifier(this.player);
/* 269 */       this.starve_progress = 0.0F;
/*     */       
/* 271 */       if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
/*     */         
/* 273 */         if (this.heal_progress >= 1.0F)
/*     */         {
/* 275 */           par1EntityPlayer.heal(1.0F);
/* 276 */           addHungerServerSide(1.0F);
/*     */           
/* 278 */           this.heal_progress--;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 283 */         this.heal_progress = 0.0F;
/*     */       } 
/*     */     } 
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
/*     */   public void readNBT(NBTTagCompound par1NBTTagCompound) {
/* 328 */     if (par1NBTTagCompound.hasKey("nutrition")) {
/*     */       
/* 330 */       this.satiation = par1NBTTagCompound.getInteger("fullness");
/* 331 */       this.nutrition = par1NBTTagCompound.getInteger("nutrition");
/*     */       
/* 333 */       this.heal_progress = par1NBTTagCompound.getFloat("heal_progress");
/* 334 */       this.starve_progress = par1NBTTagCompound.getFloat("starve_progress");
/* 335 */       this.hunger = par1NBTTagCompound.getFloat("hunger");
/* 336 */       this.hunger_for_nutrition_only = par1NBTTagCompound.getFloat("hunger_for_nutrition_only");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeNBT(NBTTagCompound par1NBTTagCompound) {
/* 342 */     par1NBTTagCompound.setInteger("fullness", this.satiation);
/* 343 */     par1NBTTagCompound.setInteger("nutrition", this.nutrition);
/*     */     
/* 345 */     par1NBTTagCompound.setFloat("heal_progress", this.heal_progress);
/* 346 */     par1NBTTagCompound.setFloat("starve_progress", this.starve_progress);
/* 347 */     par1NBTTagCompound.setFloat("hunger", this.hunger);
/* 348 */     par1NBTTagCompound.setFloat("hunger_for_nutrition_only", this.hunger_for_nutrition_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSatiation() {
/* 353 */     return this.satiation;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNutrition() {
/* 358 */     return this.nutrition;
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
/*     */   private void addHunger(float hunger) {
/* 409 */     if (this.player.capabilities.isCreativeMode || this.player.capabilities.disableDamage || this.player.isGhost() || this.player.isZevimrgvInTournament()) {
/*     */       return;
/*     */     }
/* 412 */     hunger *= this.global_hunger_rate;
/*     */     
/* 414 */     this.hunger = Math.min(this.hunger + hunger, 40.0F);
/*     */     
/* 416 */     if (this.player.worldObj.isRemote && this.hunger > 0.2F) {
/*     */       
/* 418 */       (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue(new Packet82AddHunger(this.hunger));
/* 419 */       this.hunger = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addHungerClientSide(float hunger) {
/* 425 */     if (!this.player.worldObj.isRemote) {
/*     */       
/* 427 */       Minecraft.setErrorMessage("addHungerClientSide: cannot add hunger to client if not remote");
/*     */       
/*     */       return;
/*     */     } 
/* 431 */     addHunger(hunger);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addHungerServerSide(float hunger) {
/* 436 */     if (this.player.worldObj.isRemote) {
/*     */       
/* 438 */       Minecraft.setErrorMessage("addHungerServerSide: cannot add hunger to server if remote");
/*     */       
/*     */       return;
/*     */     } 
/* 442 */     addHunger(hunger);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHunger() {
/* 447 */     return this.hunger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHungerServerSide(float hunger) {
/* 452 */     if (this.player.worldObj.isRemote) {
/*     */       
/* 454 */       Minecraft.setErrorMessage("setHunger: cannot set hunger on server if remote");
/*     */       
/*     */       return;
/*     */     } 
/* 458 */     this.hunger = hunger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSatiation(int satiation, boolean check_limit) {
/* 463 */     if (check_limit) {
/* 464 */       this.satiation = Math.min(satiation, getSatiationLimit());
/*     */     } else {
/* 466 */       this.satiation = satiation;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int addSatiation(int satiation) {
/* 471 */     setSatiation(this.satiation + satiation, true);
/*     */     
/* 473 */     return this.satiation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNutrition(int nutrition, boolean check_limit) {
/* 478 */     if (check_limit) {
/* 479 */       this.nutrition = Math.min(nutrition, getNutritionLimit());
/*     */     } else {
/* 481 */       this.nutrition = nutrition;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int addNutrition(int nutrition) {
/* 486 */     setNutrition(this.nutrition + nutrition, true);
/*     */     
/* 488 */     return this.nutrition;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSatiationLimit() {
/* 493 */     return getNutritionLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNutritionLimit() {
/* 498 */     return Math.max(Math.min(6 + this.player.getExperienceLevel() / 5 * 2, 20), 6);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FoodStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */