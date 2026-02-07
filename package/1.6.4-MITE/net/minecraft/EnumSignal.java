/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumSignal
/*     */ {
/*   8 */   boolean_test(1),
/*   9 */   byte_test(1),
/*  10 */   short_test(2),
/*  11 */   integer_test(4),
/*  12 */   float_test(16),
/*  13 */   complex_test(23),
/*  14 */   approx_pos_test(64),
/*  15 */   exact_pos_test(-128),
/*     */ 
/*     */ 
/*     */   
/*  19 */   achievement_unlocked(4),
/*  20 */   increment_stat_for_this_world_only(4),
/*  21 */   crafting_completed(4),
/*  22 */   slot_locked,
/*  23 */   unlock_slots,
/*  24 */   start_falling_asleep,
/*  25 */   start_waking_up,
/*  26 */   sleeping,
/*  27 */   fully_awake,
/*  28 */   stop_rain_and_thunder_immediately,
/*  29 */   send_nearby_chunk_report,
/*  30 */   clear_inventory,
/*  31 */   terraform,
/*     */   
/*  33 */   tournament_mode(1),
/*  34 */   reconnection_delay(7),
/*  35 */   save_world_maps,
/*  36 */   cpu_overburdened,
/*  37 */   runegate_start,
/*  38 */   runegate_execute,
/*  39 */   runegate_finished,
/*  40 */   curse_realized(1),
/*  41 */   cursed(1),
/*  42 */   curse_effect_learned,
/*  43 */   curse_lifted,
/*  44 */   damage_taken(2),
/*  45 */   block_fx(true, 32),
/*  46 */   entity_fx(true, 8),
/*  47 */   block_fx_compact(true, 6),
/*     */ 
/*     */   
/*  50 */   transfered_to_world,
/*  51 */   change_world_time(5),
/*     */ 
/*     */   
/*  54 */   after_respawn,
/*     */   
/*  56 */   take_screenshot_of_world_seed,
/*     */ 
/*     */   
/*  59 */   drop_one_item(1),
/*  60 */   stopped_using_item,
/*  61 */   digging_block_start(33),
/*     */   
/*  63 */   digging_block_cancel(32),
/*  64 */   digging_block_complete(32),
/*  65 */   block_hit_fx(33),
/*  66 */   try_auto_switch_or_restock(3),
/*  67 */   try_auto_switch_or_restock_large_subtype(6),
/*     */ 
/*     */   
/*  70 */   toggle_night_vision_override,
/*     */   
/*  72 */   update_minecart_fuel(12),
/*  73 */   confirm_or_cancel_item_in_use,
/*     */   
/*  75 */   left_click_entity(8),
/*  76 */   malnourished(4),
/*  77 */   tournament_score(5),
/*  78 */   prize_key_code(4),
/*  79 */   put_out_fire,
/*  80 */   item_in_use(12),
/*  81 */   nocked_arrow(10),
/*  82 */   mh(4),
/*  83 */   see,
/*  84 */   allotted_time(4),
/*  85 */   server_load(2),
/*  86 */   block_hit_sound(32),
/*  87 */   clear_tentative_bounding_box(32),
/*  88 */   dedicated_server,
/*  89 */   sync_pos(-128),
/*  90 */   arrow_hit_block(-120),
/*  91 */   fish_hook_in_entity(12),
/*  92 */   fireball_reversal(72),
/*  93 */   in_love(10),
/*  94 */   update_potion_effect(7),
/*  95 */   toggle_mute,
/*  96 */   tag_entity(9),
/*  97 */   skills(1),
/*  98 */   skillset(4),
/*  99 */   respawn_screen(2),
/* 100 */   loaded_tile_entities,
/* 101 */   vision_dimming_to_server(16),
/* 102 */   entity_stats_dump(8),
/* 103 */   last_issued_map_id(2),
/* 104 */   list_commands,
/* 105 */   delete_selection,
/* 106 */   furnace_heat_level(1),
/* 107 */   picked_up_held_item,
/* 108 */   teleport_away(8);
/*     */ 
/*     */   
/*     */   private boolean has_subtype;
/*     */ 
/*     */   
/*     */   private byte data_types;
/*     */ 
/*     */   
/*     */   EnumSignal(boolean has_subtypes, int data_types) {
/* 118 */     this.has_subtype = has_subtypes;
/* 119 */     this.data_types = (byte)data_types;
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
/*     */   static EnumSignal get(int ordinal) {
/* 134 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSubtype() {
/* 139 */     return this.has_subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   public ISignalSubtype getSubtype(byte sub_type_ordinal) {
/* 144 */     if (this == block_fx || this == block_fx_compact)
/* 145 */       return EnumBlockFX.get(sub_type_ordinal); 
/* 146 */     if (this == entity_fx) {
/* 147 */       return EnumEntityFX.get(sub_type_ordinal);
/*     */     }
/* 149 */     Minecraft.setErrorMessage("getSubtype: no handler for " + this);
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   byte getDataTypes() {
/* 155 */     return this.data_types;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean hasDataType(byte data_type, ISignalSubtype signal_subtype) {
/* 160 */     byte data_types = this.data_types;
/*     */     
/* 162 */     if (hasSubtype()) {
/*     */       
/* 164 */       byte subsignal_data_types = signal_subtype.getDataTypes();
/*     */       
/* 166 */       if (data_types + subsignal_data_types != (data_types | subsignal_data_types)) {
/* 167 */         Minecraft.setErrorMessage("hasDataType: conflict between signal and sub signal data types");
/*     */       }
/* 169 */       data_types = (byte)(data_types | subsignal_data_types);
/*     */     } 
/*     */     
/* 172 */     return ((data_types | data_type) == data_types);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */