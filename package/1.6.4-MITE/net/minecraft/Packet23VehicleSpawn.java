/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet23VehicleSpawn
/*     */   extends Packet
/*     */ {
/*     */   public int entityId;
/*     */   public int scaled_pos_x;
/*     */   public int scaled_pos_y;
/*     */   public int scaled_pos_z;
/*     */   public int scaled_pitch;
/*     */   public int scaled_yaw;
/*     */   public int type;
/*     */   public int throwerEntityId;
/* 143 */   public int arrow_item_id = -1;
/*     */   
/*     */   public boolean launcher_was_enchanted;
/*     */   
/*     */   public boolean arrow_stuck_in_block;
/*     */   
/*     */   public int arrow_tile_x;
/*     */   
/*     */   public int arrow_tile_y;
/*     */   
/*     */   public int arrow_tile_z;
/*     */   
/*     */   public int arrow_in_tile;
/*     */   public int arrow_in_data;
/*     */   public double exact_pos_x;
/*     */   public double exact_pos_y;
/*     */   public double exact_pos_z;
/*     */   
/*     */   public Packet23VehicleSpawn(Entity par1Entity, int par2) {
/* 162 */     this(par1Entity, par2, 0);
/*     */   }
/*     */   public double exact_motion_x; public double exact_motion_y; public double exact_motion_z; public float approx_motion_x; public float approx_motion_y; public float approx_motion_z; public int unscaled_pos_x; public int unscaled_pos_y; public int unscaled_pos_z;
/*     */   public boolean position_set_using_unscaled_integers;
/*     */   
/*     */   public Packet23VehicleSpawn() {}
/*     */   
/*     */   public Packet23VehicleSpawn(Entity entity, int type, int thrower_entity_id) {
/* 170 */     this.entityId = entity.entityId;
/* 171 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(entity);
/* 172 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(entity);
/* 173 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(entity);
/* 174 */     this.scaled_yaw = (byte)SpatialScaler.getScaledRotation(entity.rotationYaw);
/* 175 */     this.scaled_pitch = (byte)SpatialScaler.getScaledRotation(entity.rotationPitch);
/* 176 */     this.type = type;
/* 177 */     this.throwerEntityId = thrower_entity_id;
/*     */     
/* 179 */     if (entity instanceof EntityArrow && thrower_entity_id < 1) {
/* 180 */       Minecraft.setErrorMessage("WARNING: motion not sent for arrow!");
/*     */     }
/* 182 */     if (thrower_entity_id > 0) {
/*     */       
/* 184 */       this.approx_motion_x = (float)entity.motionX;
/* 185 */       this.approx_motion_y = (float)entity.motionY;
/* 186 */       this.approx_motion_z = (float)entity.motionZ;
/*     */     } 
/*     */     
/* 189 */     if (entity instanceof EntityArrow) {
/*     */       
/* 191 */       EntityArrow arrow = (EntityArrow)entity;
/*     */       
/* 193 */       this.arrow_tile_x = arrow.xTile;
/* 194 */       this.arrow_tile_y = arrow.yTile;
/* 195 */       this.arrow_tile_z = arrow.zTile;
/*     */       
/* 197 */       this.arrow_in_tile = arrow.getInTile();
/* 198 */       this.arrow_in_data = arrow.getInData();
/*     */ 
/*     */ 
/*     */       
/* 202 */       this.exact_pos_x = arrow.posX;
/* 203 */       this.exact_pos_y = arrow.posY;
/* 204 */       this.exact_pos_z = arrow.posZ;
/*     */       
/* 206 */       this.exact_motion_x = arrow.motionX;
/* 207 */       this.exact_motion_y = arrow.motionY;
/* 208 */       this.exact_motion_z = arrow.motionZ;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet23VehicleSpawn setUnscaledPositionWithIntegers(int x, int y, int z) {
/* 214 */     this.unscaled_pos_x = x;
/* 215 */     this.unscaled_pos_y = y;
/* 216 */     this.unscaled_pos_z = z;
/*     */     
/* 218 */     this.position_set_using_unscaled_integers = true;
/*     */     
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 225 */     this.entityId = par1DataInput.readInt();
/* 226 */     this.type = par1DataInput.readByte();
/* 227 */     this.position_set_using_unscaled_integers = par1DataInput.readBoolean();
/*     */     
/* 229 */     if (this.position_set_using_unscaled_integers) {
/*     */       
/* 231 */       this.unscaled_pos_x = par1DataInput.readInt();
/* 232 */       this.unscaled_pos_y = par1DataInput.readInt();
/* 233 */       this.unscaled_pos_z = par1DataInput.readInt();
/*     */     }
/*     */     else {
/*     */       
/* 237 */       this.scaled_pos_x = par1DataInput.readInt();
/* 238 */       this.scaled_pos_y = par1DataInput.readInt();
/* 239 */       this.scaled_pos_z = par1DataInput.readInt();
/*     */     } 
/*     */     
/* 242 */     this.scaled_pitch = par1DataInput.readByte();
/* 243 */     this.scaled_yaw = par1DataInput.readByte();
/*     */     
/* 245 */     this.throwerEntityId = par1DataInput.readInt();
/*     */     
/* 247 */     if (this.throwerEntityId > 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       if (this.type == 60) {
/*     */         
/* 255 */         this.arrow_item_id = par1DataInput.readShort();
/* 256 */         this.launcher_was_enchanted = par1DataInput.readBoolean();
/* 257 */         this.arrow_stuck_in_block = par1DataInput.readBoolean();
/*     */         
/* 259 */         this.arrow_tile_x = par1DataInput.readInt();
/* 260 */         this.arrow_tile_y = par1DataInput.readInt();
/* 261 */         this.arrow_tile_z = par1DataInput.readInt();
/*     */         
/* 263 */         this.arrow_in_tile = par1DataInput.readUnsignedByte();
/* 264 */         this.arrow_in_data = par1DataInput.readUnsignedByte();
/*     */ 
/*     */ 
/*     */         
/* 268 */         this.exact_pos_x = par1DataInput.readDouble();
/* 269 */         this.exact_pos_y = par1DataInput.readDouble();
/* 270 */         this.exact_pos_z = par1DataInput.readDouble();
/*     */         
/* 272 */         this.exact_motion_x = par1DataInput.readDouble();
/* 273 */         this.exact_motion_y = par1DataInput.readDouble();
/* 274 */         this.exact_motion_z = par1DataInput.readDouble();
/*     */         
/* 276 */         this.approx_motion_x = (float)this.exact_motion_x;
/* 277 */         this.approx_motion_y = (float)this.exact_motion_y;
/* 278 */         this.approx_motion_z = (float)this.exact_motion_z;
/*     */       }
/*     */       else {
/*     */         
/* 282 */         this.approx_motion_x = par1DataInput.readFloat();
/* 283 */         this.approx_motion_y = par1DataInput.readFloat();
/* 284 */         this.approx_motion_z = par1DataInput.readFloat();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 291 */     par1DataOutput.writeInt(this.entityId);
/* 292 */     par1DataOutput.writeByte(this.type);
/* 293 */     par1DataOutput.writeBoolean(this.position_set_using_unscaled_integers);
/*     */     
/* 295 */     if (this.position_set_using_unscaled_integers) {
/*     */       
/* 297 */       par1DataOutput.writeInt(this.unscaled_pos_x);
/* 298 */       par1DataOutput.writeInt(this.unscaled_pos_y);
/* 299 */       par1DataOutput.writeInt(this.unscaled_pos_z);
/*     */     }
/*     */     else {
/*     */       
/* 303 */       par1DataOutput.writeInt(this.scaled_pos_x);
/* 304 */       par1DataOutput.writeInt(this.scaled_pos_y);
/* 305 */       par1DataOutput.writeInt(this.scaled_pos_z);
/*     */     } 
/*     */     
/* 308 */     par1DataOutput.writeByte(this.scaled_pitch);
/* 309 */     par1DataOutput.writeByte(this.scaled_yaw);
/*     */     
/* 311 */     par1DataOutput.writeInt(this.throwerEntityId);
/*     */     
/* 313 */     if (this.throwerEntityId > 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       if (this.type == 60) {
/*     */         
/* 321 */         par1DataOutput.writeShort(this.arrow_item_id);
/* 322 */         par1DataOutput.writeBoolean(this.launcher_was_enchanted);
/* 323 */         par1DataOutput.writeBoolean(this.arrow_stuck_in_block);
/*     */         
/* 325 */         par1DataOutput.writeInt(this.arrow_tile_x);
/* 326 */         par1DataOutput.writeInt(this.arrow_tile_y);
/* 327 */         par1DataOutput.writeInt(this.arrow_tile_z);
/*     */         
/* 329 */         par1DataOutput.writeByte(this.arrow_in_tile);
/* 330 */         par1DataOutput.writeByte(this.arrow_in_data);
/*     */ 
/*     */ 
/*     */         
/* 334 */         par1DataOutput.writeDouble(this.exact_pos_x);
/* 335 */         par1DataOutput.writeDouble(this.exact_pos_y);
/* 336 */         par1DataOutput.writeDouble(this.exact_pos_z);
/*     */         
/* 338 */         par1DataOutput.writeDouble(this.exact_motion_x);
/* 339 */         par1DataOutput.writeDouble(this.exact_motion_y);
/* 340 */         par1DataOutput.writeDouble(this.exact_motion_z);
/*     */       }
/*     */       else {
/*     */         
/* 344 */         par1DataOutput.writeFloat(this.approx_motion_x);
/* 345 */         par1DataOutput.writeFloat(this.approx_motion_y);
/* 346 */         par1DataOutput.writeFloat(this.approx_motion_z);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 353 */     par1NetHandler.handleVehicleSpawn(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 360 */     int size = 24;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 370 */     if (this.type == 60) {
/* 371 */       size += 66;
/* 372 */     } else if (this.throwerEntityId > 0) {
/* 373 */       size += 12;
/*     */     } 
/* 375 */     return size;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet23VehicleSpawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */