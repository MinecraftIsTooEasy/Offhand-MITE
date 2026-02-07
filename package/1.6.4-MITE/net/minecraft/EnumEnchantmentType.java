/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public enum EnumEnchantmentType
/*    */ {
/*  6 */   all,
/*    */   
/*  8 */   armor,
/*  9 */   armor_feet,
/* 10 */   armor_legs,
/* 11 */   armor_torso,
/* 12 */   armor_head,
/*    */   
/* 14 */   weapon,
/* 15 */   digger,
/* 16 */   bow;
/*    */   
/*    */   public boolean canEnchantItem(Item item) {
/* 19 */     if (this == all) return true;
/*    */     
/* 21 */     if (item instanceof ItemArmor) {
/* 22 */       if (this == armor) return true; 
/* 23 */       ItemArmor itemArmor = (ItemArmor)item;
/* 24 */       if (itemArmor.armorType == 0) return (this == armor_head); 
/* 25 */       if (itemArmor.armorType == 2) return (this == armor_legs); 
/* 26 */       if (itemArmor.armorType == 1) return (this == armor_torso); 
/* 27 */       if (itemArmor.armorType == 3) return (this == armor_feet); 
/* 28 */       return false;
/* 29 */     }  if (item instanceof ItemSword)
/* 30 */       return (this == weapon); 
/* 31 */     if (item instanceof ItemTool)
/* 32 */       return (this == digger); 
/* 33 */     if (item instanceof ItemBow) {
/* 34 */       return (this == bow);
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumEnchantmentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */