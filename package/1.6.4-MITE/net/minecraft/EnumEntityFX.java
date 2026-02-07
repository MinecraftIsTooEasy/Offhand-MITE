/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumEntityFX
/*    */   implements ISignalSubtype
/*    */ {
/*  8 */   steam_with_hiss,
/*  9 */   single_steam_particle_with_hiss,
/* 10 */   summoned,
/* 11 */   burned_up_in_lava,
/* 12 */   smoke,
/* 13 */   smoke_and_steam,
/* 14 */   smoke_and_steam_with_hiss,
/* 15 */   frags,
/* 16 */   curse_effect_learned,
/* 17 */   item_breaking(3),
/* 18 */   splash,
/*    */   
/* 20 */   heal,
/* 21 */   vampiric_gain,
/* 22 */   repair,
/* 23 */   item_vanish,
/* 24 */   crafting(2);
/*    */ 
/*    */   
/*    */   private byte data_types;
/*    */ 
/*    */   
/*    */   EnumEntityFX(int signal_data_type) {
/* 31 */     this.data_types = (byte)signal_data_type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static EnumEntityFX get(int ordinal) {
/* 41 */     return values()[ordinal];
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getDataTypes() {
/* 46 */     return this.data_types;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOrdinal() {
/* 51 */     return ordinal();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumEntityFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */