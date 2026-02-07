/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public enum EnumBlockFX
/*    */   implements ISignalSubtype
/*    */ {
/*  7 */   lava_mixing_with_water,
/*  8 */   water_evaporation_in_hell,
/*  9 */   steam,
/* 10 */   steam_particles_only,
/* 11 */   smoke_and_steam,
/* 12 */   manure,
/* 13 */   particle_trail(67),
/* 14 */   destroy(4),
/* 15 */   item_consumed_by_lava;
/*    */   
/*    */   private byte data_types;
/*    */ 
/*    */   
/*    */   EnumBlockFX(int signal_data_type) {
/* 21 */     this.data_types = (byte)signal_data_type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static EnumBlockFX get(int ordinal) {
/* 31 */     return values()[ordinal];
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getDataTypes() {
/* 36 */     return this.data_types;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOrdinal() {
/* 41 */     return ordinal();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumBlockFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */