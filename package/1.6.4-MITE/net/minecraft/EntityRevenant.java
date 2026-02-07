/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityRevenant
/*    */   extends EntityZombie
/*    */ {
/*    */   public EntityRevenant(World world) {
/* 14 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 19 */     super.applyEntityAttributes();
/*    */     
/* 21 */     setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
/* 22 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
/* 23 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 7.0D);
/* 24 */     setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612D);
/*    */     
/* 26 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 30.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRandomWeapon() {
/* 31 */     List<RandomItemListEntry> items = new ArrayList();
/*    */     
/* 33 */     items.add(new RandomItemListEntry(Item.swordRustedIron, 2));
/*    */     
/* 35 */     if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
/* 36 */       items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
/*    */     }
/* 38 */     if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
/* 39 */       items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1));
/*    */     }
/* 41 */     RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
/*    */     
/* 43 */     setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addRandomEquipment() {
/* 48 */     addRandomWeapon();
/*    */     
/* 50 */     setBoots((new ItemStack(Item.bootsRustedIron)).randomizeForMob(this, true));
/* 51 */     setLeggings((new ItemStack(Item.legsRustedIron)).randomizeForMob(this, true));
/* 52 */     setCuirass((new ItemStack(Item.plateRustedIron)).randomizeForMob(this, true));
/* 53 */     setHelmet((new ItemStack(Item.helmetRustedIron)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRevenant() {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVillager(boolean villager, int profession) {
/* 63 */     Minecraft.setErrorMessage("setVillager: why setting villager for revenant?");
/*    */     
/* 65 */     (new Exception()).printStackTrace();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVillager() {
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 75 */     return super.getExperienceValue() * 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxSpawnedInChunk() {
/* 80 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityRevenant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */