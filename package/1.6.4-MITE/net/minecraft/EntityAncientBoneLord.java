/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAncientBoneLord
/*    */   extends EntityBoneLord
/*    */ {
/*    */   public EntityAncientBoneLord(World world) {
/* 12 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 17 */     super.applyEntityAttributes();
/*    */     
/* 19 */     setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
/* 20 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606D);
/* 21 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 8.0D);
/*    */     
/* 23 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 24.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRandomWeapon() {
/* 28 */     List<RandomItemListEntry> items = new ArrayList();
/*    */     
/* 30 */     items.add(new RandomItemListEntry(Item.swordAncientMetal, 2));
/*    */     
/* 32 */     if (!Minecraft.isInTournamentMode()) {
/*    */       
/* 34 */       items.add(new RandomItemListEntry(Item.battleAxeAncientMetal, 1));
/* 35 */       items.add(new RandomItemListEntry(Item.warHammerAncientMetal, 1));
/*    */     } 
/*    */     
/* 38 */     RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
/*    */     
/* 40 */     setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addRandomEquipment() {
/* 45 */     addRandomWeapon();
/*    */     
/* 47 */     setBoots((new ItemStack(Item.bootsAncientMetal)).randomizeForMob(this, true));
/* 48 */     setLeggings((new ItemStack(Item.legsAncientMetal)).randomizeForMob(this, true));
/* 49 */     setCuirass((new ItemStack(Item.plateAncientMetal)).randomizeForMob(this, true));
/* 50 */     setHelmet((new ItemStack(Item.helmetAncientMetal)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 55 */     return super.getExperienceValue() * 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAncientBoneLord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */