/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet85SimpleSignal
/*     */   extends Packet
/*     */ {
/*     */   public EnumSignal signal_type;
/*     */   public ISignalSubtype signal_subtype;
/*     */   private SignalData signal_data;
/*     */   
/*     */   public Packet85SimpleSignal() {
/*  17 */     this(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal(EnumSignal signal_type) {
/*  22 */     this(signal_type, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal(EnumSignal signal_type, ISignalSubtype signal_subtype) {
/*  27 */     this.signal_type = signal_type;
/*  28 */     this.signal_subtype = signal_subtype;
/*  29 */     this.signal_data = new SignalData();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSubtype() {
/*  34 */     return this.signal_type.hasSubtype();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSubtype(byte subtype_ordinal) {
/*  39 */     this.signal_subtype = this.signal_type.getSubtype(subtype_ordinal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasDataType(byte data_type) {
/*  46 */     return this.signal_type.hasDataType(data_type, this.signal_subtype);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasBooleanOrByte() {
/*  51 */     return hasDataType((byte)1);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasShort() {
/*  56 */     return hasDataType((byte)2);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasInteger() {
/*  61 */     return hasDataType((byte)4);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasEntityID() {
/*  66 */     return hasDataType((byte)8);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasFloat() {
/*  71 */     return hasDataType((byte)16);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasBlockCoords() {
/*  76 */     return hasDataType((byte)32);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasApproxPosition() {
/*  81 */     return hasDataType((byte)64);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasExactPosition() {
/*  86 */     return hasDataType(-128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput data_input) throws IOException {
/*  91 */     this.signal_type = EnumSignal.get(data_input.readUnsignedByte());
/*     */     
/*  93 */     if (hasSubtype())
/*     */     {
/*  95 */       setSubtype(data_input.readByte());
/*     */     }
/*  97 */     if (hasBooleanOrByte()) {
/*  98 */       setByte(data_input.readByte());
/*     */     }
/* 100 */     if (hasShort()) {
/* 101 */       setShort(data_input.readShort());
/*     */     }
/* 103 */     if (hasInteger()) {
/* 104 */       setInteger(data_input.readInt());
/*     */     }
/* 106 */     if (hasEntityID()) {
/* 107 */       setEntityID(data_input.readInt());
/*     */     }
/* 109 */     if (hasFloat()) {
/* 110 */       setFloat(data_input.readFloat());
/*     */     }
/* 112 */     if (hasBlockCoords()) {
/* 113 */       setBlockCoords(data_input.readInt(), data_input.readShort(), data_input.readInt());
/*     */     }
/* 115 */     if (hasApproxPosition()) {
/* 116 */       this.signal_data.setScaledPosition(data_input.readInt(), data_input.readShort(), data_input.readInt());
/*     */     }
/* 118 */     if (hasExactPosition()) {
/* 119 */       setExactPosition(data_input.readDouble(), data_input.readDouble(), data_input.readDouble());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput data_output) throws IOException {
/* 127 */     data_output.writeByte(this.signal_type.ordinal());
/*     */     
/* 129 */     if (hasSubtype()) {
/* 130 */       data_output.writeByte(this.signal_subtype.getOrdinal());
/*     */     }
/* 132 */     if (hasBooleanOrByte()) {
/*     */       
/* 134 */       if (!this.signal_data.isBooleanOrByteSet()) {
/* 135 */         Minecraft.setErrorMessage("writePacketData: boolean or byte data required but never set for " + this);
/*     */       }
/* 137 */       data_output.writeByte(getByte());
/*     */     } 
/*     */     
/* 140 */     if (hasShort()) {
/*     */       
/* 142 */       if (!this.signal_data.isShortSet()) {
/* 143 */         Minecraft.setErrorMessage("writePacketData: short data required but never set for " + this);
/*     */       }
/* 145 */       data_output.writeShort(getShort());
/*     */     } 
/*     */     
/* 148 */     if (hasInteger()) {
/*     */       
/* 150 */       if (!this.signal_data.isIntegerSet()) {
/* 151 */         Minecraft.setErrorMessage("writePacketData: integer data required but never set for " + this);
/*     */       }
/* 153 */       data_output.writeInt(getInteger());
/*     */     } 
/*     */     
/* 156 */     if (hasEntityID()) {
/*     */       
/* 158 */       if (!this.signal_data.isEntityIDSet()) {
/* 159 */         Minecraft.setErrorMessage("writePacketData: entity ID required but never set for " + this);
/*     */       }
/* 161 */       data_output.writeInt(getEntityID());
/*     */     } 
/*     */     
/* 164 */     if (hasFloat()) {
/*     */       
/* 166 */       if (!this.signal_data.isFloatSet()) {
/* 167 */         Minecraft.setErrorMessage("writePacketData: float data required but never set for " + this);
/*     */       }
/* 169 */       data_output.writeFloat(getFloat());
/*     */     } 
/*     */     
/* 172 */     if (hasBlockCoords()) {
/*     */       
/* 174 */       if (!this.signal_data.isBlockCoordsSet()) {
/* 175 */         Minecraft.setErrorMessage("writePacketData: block coords required but never set for " + this);
/*     */       }
/* 177 */       data_output.writeInt(getBlockX());
/* 178 */       data_output.writeShort(getBlockY());
/* 179 */       data_output.writeInt(getBlockZ());
/*     */     } 
/*     */     
/* 182 */     if (hasApproxPosition()) {
/*     */       
/* 184 */       if (!this.signal_data.isApproxPositionSet()) {
/* 185 */         Minecraft.setErrorMessage("writePacketData: approx position required but never set for " + this);
/*     */       }
/* 187 */       data_output.writeInt(this.signal_data.getScaledPosX());
/* 188 */       data_output.writeShort(this.signal_data.getScaledPosY());
/* 189 */       data_output.writeInt(this.signal_data.getScaledPosZ());
/*     */     } 
/*     */     
/* 192 */     if (hasExactPosition()) {
/*     */       
/* 194 */       if (!this.signal_data.isExactPositionSet()) {
/* 195 */         Minecraft.setErrorMessage("writePacketData: exact position required but never set for " + this);
/*     */       }
/* 197 */       data_output.writeDouble(getExactPosX());
/* 198 */       data_output.writeDouble(getExactPosY());
/* 199 */       data_output.writeDouble(getExactPosZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler net_handler) {
/* 208 */     if (this.signal_type == EnumSignal.block_fx_compact) {
/*     */       
/* 210 */       this.signal_data.setBlockCoordsFromCompactedData();
/* 211 */       this.signal_type = EnumSignal.block_fx;
/*     */     } 
/*     */     
/* 214 */     net_handler.handleSimpleSignal(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 219 */     int size = 1;
/*     */     
/* 221 */     if (this.signal_type.hasSubtype()) {
/* 222 */       size++;
/*     */     }
/* 224 */     if (hasBooleanOrByte())
/*     */     {
/* 226 */       size++;
/*     */     }
/* 228 */     if (hasShort()) {
/* 229 */       size += 2;
/*     */     }
/* 231 */     if (hasInteger()) {
/* 232 */       size += 4;
/*     */     }
/* 234 */     if (hasEntityID()) {
/* 235 */       size += 4;
/*     */     }
/* 237 */     if (hasFloat()) {
/* 238 */       size += 4;
/*     */     }
/* 240 */     if (hasBlockCoords()) {
/* 241 */       size += 10;
/*     */     }
/* 243 */     if (hasApproxPosition()) {
/* 244 */       size += 10;
/*     */     }
/* 246 */     if (hasExactPosition()) {
/* 247 */       size += 24;
/*     */     }
/*     */ 
/*     */     
/* 251 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setBoolean(boolean boolean_data) {
/* 256 */     if (!hasBooleanOrByte()) {
/* 257 */       Minecraft.setErrorMessage("setBoolean: data not part of " + this);
/* 258 */     } else if (this.signal_data.isBooleanOrByteSet()) {
/* 259 */       Minecraft.setErrorMessage("setBoolean: data already set for " + this);
/*     */     } 
/* 261 */     this.signal_data.setBoolean(boolean_data);
/*     */     
/* 263 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setByte(int byte_data) {
/* 268 */     if (!hasBooleanOrByte()) {
/* 269 */       Minecraft.setErrorMessage("setByte: data not part of " + this);
/* 270 */     } else if (this.signal_data.isBooleanOrByteSet()) {
/* 271 */       Minecraft.setErrorMessage("setByte: data already set for " + this);
/*     */     } 
/* 273 */     if (byte_data < -128 || byte_data > 127) {
/* 274 */       Minecraft.setErrorMessage("setByte: data is out of range for " + this);
/*     */     }
/* 276 */     this.signal_data.setByte(byte_data);
/*     */     
/* 278 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setShort(int short_data) {
/* 283 */     if (!hasShort()) {
/* 284 */       Minecraft.setErrorMessage("setShort: data not part of " + this);
/* 285 */     } else if (this.signal_data.isShortSet()) {
/* 286 */       Minecraft.setErrorMessage("setShort: data already set for " + this);
/*     */     } 
/* 288 */     if (short_data < -32768 || short_data > 32767) {
/* 289 */       Minecraft.setErrorMessage("setShort: data is out of range for " + this);
/*     */     }
/* 291 */     this.signal_data.setShort(short_data);
/*     */     
/* 293 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setInteger(int integer_data) {
/* 298 */     if (!hasInteger()) {
/* 299 */       Minecraft.setErrorMessage("setInteger: data not part of " + this);
/* 300 */     } else if (this.signal_data.isIntegerSet()) {
/* 301 */       Minecraft.setErrorMessage("setInteger: data already set for " + this);
/*     */     } 
/* 303 */     this.signal_data.setInteger(integer_data);
/*     */     
/* 305 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setEntityID(int entity_id) {
/* 310 */     if (!hasEntityID()) {
/* 311 */       Minecraft.setErrorMessage("setEntityID: data not part of " + this);
/* 312 */     } else if (this.signal_data.isEntityIDSet()) {
/* 313 */       Minecraft.setErrorMessage("setEntityID: data already set for " + this);
/*     */     } 
/* 315 */     this.signal_data.setEntityID(entity_id);
/*     */     
/* 317 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setEntityID(Entity entity) {
/* 322 */     return setEntityID(entity.entityId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setFloat(float float_data) {
/* 327 */     if (!hasFloat()) {
/* 328 */       Minecraft.setErrorMessage("setFloat: data not part of " + this);
/* 329 */     } else if (this.signal_data.isFloatSet()) {
/* 330 */       Minecraft.setErrorMessage("setFloat: data already set for " + this);
/*     */     } 
/* 332 */     this.signal_data.setFloat(float_data);
/*     */     
/* 334 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setBlockCoords(int block_x, int block_y, int block_z) {
/* 340 */     if (!hasBlockCoords()) {
/* 341 */       Minecraft.setErrorMessage("setBlockCoords: data not part of " + this);
/*     */     }
/*     */ 
/*     */     
/* 345 */     if (this.signal_type == EnumSignal.block_fx && SignalData.canBlockCoordsBeCompacted(block_x, block_y, block_z) && !hasShort() && !hasInteger()) {
/*     */       
/* 347 */       if (this.signal_data.isShortSet() || this.signal_data.isIntegerSet()) {
/* 348 */         Minecraft.setErrorMessage("setBlockCoords: compacted data already set for " + this);
/*     */       }
/* 350 */       this.signal_type = EnumSignal.block_fx_compact;
/* 351 */       this.signal_data.setBlockCoordsCompact(block_x, block_y, block_z);
/*     */       
/* 353 */       return this;
/*     */     } 
/*     */     
/* 356 */     if (this.signal_data.isBlockCoordsSet()) {
/* 357 */       Minecraft.setErrorMessage("setBlockCoords: data already set for " + this);
/*     */     }
/* 359 */     this.signal_data.setBlockCoords(block_x, block_y, block_z);
/*     */     
/* 361 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setApproxPosition(double pos_x, double pos_y, double pos_z) {
/* 366 */     if (!hasApproxPosition()) {
/* 367 */       Minecraft.setErrorMessage("setApproxPosition: data not part of " + this);
/* 368 */     } else if (this.signal_data.isApproxPositionSet()) {
/* 369 */       Minecraft.setErrorMessage("setApproxPosition: data already set for " + this);
/*     */     } 
/* 371 */     this.signal_data.setApproxPosition(pos_x, pos_y, pos_z);
/*     */     
/* 373 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet85SimpleSignal setExactPosition(double pos_x, double pos_y, double pos_z) {
/* 378 */     if (!hasExactPosition()) {
/* 379 */       Minecraft.setErrorMessage("setExactPosition: data not part of " + this);
/* 380 */     } else if (this.signal_data.isExactPositionSet()) {
/* 381 */       Minecraft.setErrorMessage("setExactPosition: data already set for " + this);
/*     */     } 
/* 383 */     this.signal_data.setExactPosition(pos_x, pos_y, pos_z);
/*     */     
/* 385 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addData(SignalData data) {
/* 390 */     if (data.isBooleanOrByteSet()) {
/* 391 */       setByte(data.getByte());
/*     */     }
/* 393 */     if (data.isShortSet()) {
/* 394 */       setShort(data.getShort());
/*     */     }
/* 396 */     if (data.isIntegerSet()) {
/* 397 */       setInteger(data.getInteger());
/*     */     }
/* 399 */     if (data.isEntityIDSet()) {
/* 400 */       setEntityID(data.getEntityID());
/*     */     }
/* 402 */     if (data.isFloatSet()) {
/* 403 */       setFloat(data.getFloat());
/*     */     }
/* 405 */     if (data.isBlockCoordsSet()) {
/* 406 */       setBlockCoords(data.getBlockX(), data.getBlockY(), data.getBlockZ());
/*     */     }
/* 408 */     if (data.isApproxPositionSet()) {
/* 409 */       this.signal_data.setScaledPosition(data.getScaledPosX(), data.getScaledPosY(), data.getScaledPosZ());
/*     */     }
/* 411 */     if (data.isExactPositionSet()) {
/* 412 */       setExactPosition(data.getExactPosX(), data.getExactPosY(), data.getExactPosZ());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getBoolean() {
/* 417 */     if (!hasBooleanOrByte()) {
/* 418 */       Minecraft.setErrorMessage("getBoolean: data not part of " + this);
/* 419 */     } else if (!this.signal_data.isBooleanOrByteSet()) {
/* 420 */       Minecraft.setErrorMessage("getBoolean: boolean data required but not set for " + this);
/*     */     } 
/* 422 */     return this.signal_data.getBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/* 427 */     if (!hasBooleanOrByte()) {
/* 428 */       Minecraft.setErrorMessage("getByte: data not part of " + this);
/* 429 */     } else if (!this.signal_data.isBooleanOrByteSet()) {
/* 430 */       Minecraft.setErrorMessage("getByte: byte data required but not set for " + this);
/*     */     } 
/* 432 */     return this.signal_data.getByte();
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/* 437 */     if (!hasShort()) {
/* 438 */       Minecraft.setErrorMessage("getShort: data not part of " + this);
/* 439 */     } else if (!this.signal_data.isShortSet()) {
/* 440 */       Minecraft.setErrorMessage("getShort: short data required but not set for " + this);
/*     */     } 
/* 442 */     return this.signal_data.getShort();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInteger() {
/* 447 */     if (!hasInteger()) {
/* 448 */       Minecraft.setErrorMessage("getInteger: data not part of " + this);
/* 449 */     } else if (!this.signal_data.isIntegerSet()) {
/* 450 */       Minecraft.setErrorMessage("getInteger: integer data required but not set for " + this);
/*     */     } 
/* 452 */     return this.signal_data.getInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/* 457 */     if (!hasEntityID()) {
/* 458 */       Minecraft.setErrorMessage("getEntityID: data not part of " + this);
/* 459 */     } else if (!this.signal_data.isEntityIDSet()) {
/* 460 */       Minecraft.setErrorMessage("getEntityID: entity id data required but not set for " + this);
/*     */     } 
/* 462 */     return this.signal_data.getEntityID();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 467 */     if (!hasFloat()) {
/* 468 */       Minecraft.setErrorMessage("getFloat: data not part of " + this);
/* 469 */     } else if (!this.signal_data.isFloatSet()) {
/* 470 */       Minecraft.setErrorMessage("getFloat: float data required but not set for " + this);
/*     */     } 
/* 472 */     return this.signal_data.getFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockX() {
/* 477 */     if (!hasBlockCoords()) {
/* 478 */       Minecraft.setErrorMessage("getBlockX: data not part of " + this);
/* 479 */     } else if (!this.signal_data.isBlockCoordsSet()) {
/* 480 */       Minecraft.setErrorMessage("getBlockX: block coords data required but not set for " + this);
/*     */     } 
/* 482 */     return this.signal_data.getBlockX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockY() {
/* 487 */     if (!hasBlockCoords()) {
/* 488 */       Minecraft.setErrorMessage("getBlockY: data not part of " + this);
/* 489 */     } else if (!this.signal_data.isBlockCoordsSet()) {
/* 490 */       Minecraft.setErrorMessage("getBlockY: block coords data required but not set for " + this);
/*     */     } 
/* 492 */     return this.signal_data.getBlockY();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockZ() {
/* 497 */     if (!hasBlockCoords()) {
/* 498 */       Minecraft.setErrorMessage("getBlockZ: data not part of " + this);
/* 499 */     } else if (!this.signal_data.isBlockCoordsSet()) {
/* 500 */       Minecraft.setErrorMessage("getBlockZ: block coords data required but not set for " + this);
/*     */     } 
/* 502 */     return this.signal_data.getBlockZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getApproxPosX() {
/* 507 */     if (!hasApproxPosition()) {
/* 508 */       Minecraft.setErrorMessage("getApproxPosX: data not part of " + this);
/* 509 */     } else if (!this.signal_data.isApproxPositionSet()) {
/* 510 */       Minecraft.setErrorMessage("getApproxPosX: approx positon data required but not set for " + this);
/*     */     } 
/* 512 */     return SpatialScaler.getPosX(this.signal_data.getScaledPosX());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getApproxPosY() {
/* 517 */     if (!hasApproxPosition()) {
/* 518 */       Minecraft.setErrorMessage("getApproxPosY: data not part of " + this);
/* 519 */     } else if (!this.signal_data.isApproxPositionSet()) {
/* 520 */       Minecraft.setErrorMessage("getApproxPosY: approx positon data required but not set for " + this);
/*     */     } 
/* 522 */     return SpatialScaler.getPosY(this.signal_data.getScaledPosY());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getApproxPosZ() {
/* 527 */     if (!hasApproxPosition()) {
/* 528 */       Minecraft.setErrorMessage("getApproxPosZ: data not part of " + this);
/* 529 */     } else if (!this.signal_data.isApproxPositionSet()) {
/* 530 */       Minecraft.setErrorMessage("getApproxPosZ: approx positon data required but not set for " + this);
/*     */     } 
/* 532 */     return SpatialScaler.getPosZ(this.signal_data.getScaledPosZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosX() {
/* 537 */     if (!hasExactPosition()) {
/* 538 */       Minecraft.setErrorMessage("getExactPosX: data not part of " + this);
/* 539 */     } else if (!this.signal_data.isExactPositionSet()) {
/* 540 */       Minecraft.setErrorMessage("getExactPosX: exact positon data required but not set for " + this);
/*     */     } 
/* 542 */     return this.signal_data.getExactPosX();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosY() {
/* 547 */     if (!hasExactPosition()) {
/* 548 */       Minecraft.setErrorMessage("getExactPosY: data not part of " + this);
/* 549 */     } else if (!this.signal_data.isExactPositionSet()) {
/* 550 */       Minecraft.setErrorMessage("getExactPosY: exact positon data required but not set for " + this);
/*     */     } 
/* 552 */     return this.signal_data.getExactPosY();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getExactPosZ() {
/* 557 */     if (!hasExactPosition()) {
/* 558 */       Minecraft.setErrorMessage("getExactPosZ: data not part of " + this);
/* 559 */     } else if (!this.signal_data.isExactPositionSet()) {
/* 560 */       Minecraft.setErrorMessage("getExactPosZ: exact positon data required but not set for " + this);
/*     */     } 
/* 562 */     return this.signal_data.getExactPosZ();
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
/*     */   public String toString() {
/* 663 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 665 */     sb.append(this.signal_type);
/*     */     
/* 667 */     if (hasSubtype()) {
/* 668 */       sb.append(":" + this.signal_subtype);
/*     */     }
/* 670 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet85SimpleSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */