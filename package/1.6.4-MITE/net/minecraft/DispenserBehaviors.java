/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DispenserBehaviors
/*    */ {
/*    */   public static void registerDispenserBehaviours() {
/* 19 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*    */       
/* 21 */       Item item = Item.getItem(i);
/*    */       
/* 23 */       if (item != null) {
/*    */ 
/*    */         
/* 26 */         IBehaviorDispenseItem dispenser_behavior = item.getDispenserBehavior();
/*    */         
/* 28 */         if (dispenser_behavior != null)
/* 29 */           BlockDispenser.dispenseBehaviorRegistry.putObject(item, dispenser_behavior); 
/*    */       } 
/*    */     } 
/* 32 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.egg, new DispenserBehaviorEgg());
/* 33 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.snowball, new DispenserBehaviorSnowball());
/* 34 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.expBottle, new DispenserBehaviorExperience());
/* 35 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.potion, new DispenserBehaviorPotion());
/* 36 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.monsterPlacer, new DispenserBehaviorMobEgg());
/* 37 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.firework, new DispenserBehaviorFireworks());
/* 38 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.fireballCharge, new DispenserBehaviorFireball());
/* 39 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.boat, new DispenserBehaviorBoat());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.flintAndSteel, new DispenserBehaviorFire());
/* 69 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.dyePowder, new DispenserBehaviorDye());
/* 70 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.itemsList[Block.tnt.blockID], new DispenserBehaviorTNT());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DispenserBehaviors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */