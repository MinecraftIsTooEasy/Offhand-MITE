/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SignalData
/*     */ {
/*     */   public static final byte NONE = 0;
/*     */   public static final byte BOOLEAN_OR_BYTE = 1;
/*     */   public static final byte SHORT = 2;
/*     */   public static final byte INTEGER = 4;
/*     */   public static final byte ENTITY_ID = 8;
/*     */   public static final byte FLOAT = 16;
/*     */   public static final byte BLOCK_COORDS = 32;
/*     */   public static final byte APPROX_POSITION = 64;
/*     */   public static final byte EXACT_POSITION = -128;
/*     */   private byte byte_data;
/*     */   private short short_data;
/*     */   private int integer_data;
/*     */   private int entity_id;
/*     */   private float float_data;
/*     */   private int block_x;
/*     */   private int block_y;
/*     */   private int block_z;
/*     */   private int scaled_pos_x;
/*     */   private int scaled_pos_y;
/*     */   private int scaled_pos_z;
/*     */   private double pos_x;
/*     */   private double pos_y;
/*     */   private double pos_z;
/*     */   private byte data_types_set;
/*     */   private static final int bits_for_compact_xz = 19;
/*     */   private static final int bits_for_compact_y = 9;
/*     */   private static final int bits_for_compact_block_coords = 47;
/*     */   private static final int largest_positive_compact_xz = 262143;
/*     */   private static final int largest_positive_compact_y = 511;
/*     */   
/*     */   public SignalData setBoolean(boolean boolean_data) {
/*  38 */     if (isBooleanOrByteSet()) {
/*  39 */       Minecraft.setErrorMessage("setBoolean: data already set");
/*     */     }
/*  41 */     this.byte_data = (byte)(boolean_data ? -1 : 0);
/*     */     
/*  43 */     this.data_types_set = (byte)(this.data_types_set | 0x1);
/*     */     
/*  45 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setByte(int byte_data) {
/*  50 */     if (isBooleanOrByteSet()) {
/*  51 */       Minecraft.setErrorMessage("setByte: data already set");
/*  52 */     } else if (byte_data < -128 || byte_data > 127) {
/*  53 */       Minecraft.setErrorMessage("setByte: byte data is out of range (" + byte_data + ")");
/*     */     } 
/*  55 */     this.byte_data = (byte)byte_data;
/*     */     
/*  57 */     this.data_types_set = (byte)(this.data_types_set | 0x1);
/*     */     
/*  59 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setShort(int short_data) {
/*  64 */     if (isShortSet()) {
/*  65 */       Minecraft.setErrorMessage("setShort: data already set");
/*  66 */     } else if (short_data < -32768 || short_data > 32767) {
/*  67 */       Minecraft.setErrorMessage("setShort: short data is out of range (" + short_data + ")");
/*     */     } 
/*  69 */     this.short_data = (short)short_data;
/*     */     
/*  71 */     this.data_types_set = (byte)(this.data_types_set | 0x2);
/*     */     
/*  73 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setInteger(int integer_data) {
/*  78 */     if (isIntegerSet()) {
/*  79 */       Minecraft.setErrorMessage("setInteger: data already set");
/*     */     }
/*  81 */     this.integer_data = integer_data;
/*     */     
/*  83 */     this.data_types_set = (byte)(this.data_types_set | 0x4);
/*     */     
/*  85 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setEntityID(int entity_id) {
/*  90 */     if (isEntityIDSet()) {
/*  91 */       Minecraft.setErrorMessage("setEntityID: data already set");
/*     */     }
/*  93 */     this.entity_id = entity_id;
/*     */     
/*  95 */     this.data_types_set = (byte)(this.data_types_set | 0x8);
/*     */     
/*  97 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setFloat(float float_data) {
/* 102 */     if (isFloatSet()) {
/* 103 */       Minecraft.setErrorMessage("setFloat: data already set");
/*     */     }
/* 105 */     this.float_data = float_data;
/*     */     
/* 107 */     this.data_types_set = (byte)(this.data_types_set | 0x10);
/*     */     
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setBlockCoords(int block_x, int block_y, int block_z) {
/* 114 */     if (isBlockCoordsSet()) {
/* 115 */       Minecraft.setErrorMessage("setBlockCoords: data already set");
/*     */     }
/* 117 */     this.block_x = block_x;
/* 118 */     this.block_y = block_y;
/* 119 */     this.block_z = block_z;
/*     */     
/* 121 */     this.data_types_set = (byte)(this.data_types_set | 0x20);
/*     */     
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockCoordsFromCompactedData() {
/* 128 */     setBlockCoords(getBlockXFromCompactedCoords(), getBlockYFromCompactedCoords(), getBlockZFromCompactedCoords());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canBlockCoordsBeCompacted(int block_x, int block_y, int block_z) {
/* 133 */     return (Math.abs(block_x) <= 262143 && block_y <= 511 && Math.abs(block_z) <= 262143);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockCoordsCompact(int block_x, int block_y, int block_z) {
/* 142 */     int sign_bit_x = (block_x < 0) ? 1 : 0;
/* 143 */     int sign_bit_z = (block_z < 0) ? 1 : 0;
/*     */     
/* 145 */     block_x = Math.abs(block_x);
/* 146 */     block_z = Math.abs(block_z);
/*     */     
/* 148 */     long long_data = 0L;
/*     */     
/* 150 */     long_data |= block_x;
/* 151 */     long_data |= block_y << 18L;
/* 152 */     long_data |= block_z << 27L;
/*     */     
/* 154 */     long_data |= sign_bit_x << 45L;
/* 155 */     long_data |= sign_bit_z << 46L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     short short_data = 0;
/* 166 */     int integer_data = 0;
/*     */     
/* 168 */     short_data = (short)(int)(short_data | long_data);
/* 169 */     integer_data = (int)(integer_data | long_data >> 16L);
/*     */     
/* 171 */     setShort(short_data);
/* 172 */     setInteger(integer_data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SignalData setApproxPosition(double pos_x, double pos_y, double pos_z) {
/* 183 */     return setScaledPosition(SpatialScaler.getScaledPosX(pos_x), SpatialScaler.getScaledPosY(pos_y), SpatialScaler.getScaledPosZ(pos_z));
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setScaledPosition(int scaled_pos_x, int scaled_pos_y, int scaled_pos_z) {
/* 188 */     if (isApproxPositionSet()) {
/* 189 */       Minecraft.setErrorMessage("setScaledPosition: data already set");
/*     */     }
/* 191 */     this.scaled_pos_x = scaled_pos_x;
/* 192 */     this.scaled_pos_y = scaled_pos_y;
/* 193 */     this.scaled_pos_z = scaled_pos_z;
/*     */     
/* 195 */     this.data_types_set = (byte)(this.data_types_set | 0x40);
/*     */     
/* 197 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignalData setExactPosition(double pos_x, double pos_y, double pos_z) {
/* 202 */     if (isExactPositionSet()) {
/* 203 */       Minecraft.setErrorMessage("setExactPosition: data already set");
/*     */     }
/* 205 */     this.pos_x = pos_x;
/* 206 */     this.pos_y = pos_y;
/* 207 */     this.pos_z = pos_z;
/*     */     
/* 209 */     this.data_types_set = (byte)(this.data_types_set | Byte.MIN_VALUE);
/*     */     
/* 211 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isDataTypeSet(byte data_type) {
/* 217 */     return ((this.data_types_set | data_type) == this.data_types_set);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isBooleanOrByteSet() {
/* 222 */     return isDataTypeSet((byte)1);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isShortSet() {
/* 227 */     return isDataTypeSet((byte)2);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isIntegerSet() {
/* 232 */     return isDataTypeSet((byte)4);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEntityIDSet() {
/* 237 */     return isDataTypeSet((byte)8);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isFloatSet() {
/* 242 */     return isDataTypeSet((byte)16);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isBlockCoordsSet() {
/* 247 */     return isDataTypeSet((byte)32);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isApproxPositionSet() {
/* 252 */     return isDataTypeSet((byte)64);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isExactPositionSet() {
/* 257 */     return isDataTypeSet(-128);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean() {
/* 262 */     if (!isBooleanOrByteSet()) {
/* 263 */       Minecraft.setErrorMessage("getBoolean: boolean or byte has not been set");
/*     */     }
/* 265 */     return (this.byte_data != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/* 270 */     if (!isBooleanOrByteSet()) {
/* 271 */       Minecraft.setErrorMessage("getByte: boolean or byte has not been set");
/*     */     }
/* 273 */     return this.byte_data;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/* 278 */     if (!isShortSet()) {
/* 279 */       Minecraft.setErrorMessage("getShort: short has not been set");
/*     */     }
/* 281 */     return this.short_data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInteger() {
/* 286 */     if (!isIntegerSet()) {
/* 287 */       Minecraft.setErrorMessage("getInteger: integer has not been set");
/*     */     }
/* 289 */     return this.integer_data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/* 294 */     if (!isEntityIDSet()) {
/* 295 */       Minecraft.setErrorMessage("getEntityID: entity_id has not been set");
/*     */     }
/* 297 */     return this.entity_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 302 */     if (!isFloatSet()) {
/* 303 */       Minecraft.setErrorMessage("getFloat: float has not been set");
/*     */     }
/* 305 */     return this.float_data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockX() {
/* 310 */     if (!isBlockCoordsSet()) {
/* 311 */       Minecraft.setErrorMessage("getBlockX: block coords data has not been set");
/*     */     }
/* 313 */     return this.block_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockY() {
/* 318 */     if (!isBlockCoordsSet()) {
/* 319 */       Minecraft.setErrorMessage("getBlockY: block coords data has not been set");
/*     */     }
/* 321 */     return this.block_y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockZ() {
/* 326 */     if (!isBlockCoordsSet()) {
/* 327 */       Minecraft.setErrorMessage("getBlockZ: block coords data has not been set");
/*     */     }
/* 329 */     return this.block_z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getCompactedCoords() {
/* 339 */     return (getShort() & 0xFFFF) | getInteger() << 16L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getBlockXFromCompactedCoords() {
/* 347 */     long long_data = getCompactedCoords();
/*     */ 
/*     */     
/* 350 */     int block_x = (int)(long_data & 0x3FFFFL);
/*     */     
/* 352 */     if ((long_data >> 45L & 0x1L) == 1L) {
/* 353 */       block_x = -block_x;
/*     */     }
/* 355 */     return block_x;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getBlockYFromCompactedCoords() {
/* 360 */     long long_data = getCompactedCoords();
/*     */ 
/*     */     
/* 363 */     this; int block_y = (int)(long_data >> 19 - 1 & 0x1FFL);
/*     */     
/* 365 */     return block_y;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getBlockZFromCompactedCoords() {
/* 370 */     long long_data = getCompactedCoords();
/*     */ 
/*     */     
/* 373 */     int block_z = (int)(long_data >> 27L & 0x3FFFFL);
/*     */     
/* 375 */     if ((long_data >> 46L & 0x1L) == 1L) {
/* 376 */       block_z = -block_z;
/*     */     }
/* 378 */     return block_z;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getScaledPosX() {
/* 383 */     if (!isApproxPositionSet()) {
/* 384 */       Minecraft.setErrorMessage("getScaledPosX: approx position has not been set");
/*     */     }
/* 386 */     return this.scaled_pos_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getScaledPosY() {
/* 391 */     if (!isApproxPositionSet()) {
/* 392 */       Minecraft.setErrorMessage("getScaledPosY: approx position has not been set");
/*     */     }
/* 394 */     return this.scaled_pos_y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getScaledPosZ() {
/* 399 */     if (!isApproxPositionSet()) {
/* 400 */       Minecraft.setErrorMessage("getScaledPosZ: approx position has not been set");
/*     */     }
/* 402 */     return this.scaled_pos_z;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosX() {
/* 407 */     if (!isExactPositionSet()) {
/* 408 */       Minecraft.setErrorMessage("getExactPosX: exact position has not been set");
/*     */     }
/* 410 */     return this.pos_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosY() {
/* 415 */     if (!isExactPositionSet()) {
/* 416 */       Minecraft.setErrorMessage("getExactPosY: exact position has not been set");
/*     */     }
/* 418 */     return this.pos_y;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosZ() {
/* 423 */     if (!isExactPositionSet()) {
/* 424 */       Minecraft.setErrorMessage("getExactPosZ: exact position has not been set");
/*     */     }
/* 426 */     return this.pos_z;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SignalData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */