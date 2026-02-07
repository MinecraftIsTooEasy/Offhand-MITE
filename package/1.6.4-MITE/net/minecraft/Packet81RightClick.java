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
/*     */ public class Packet81RightClick
/*     */   extends Packet
/*     */ {
/*     */   public int slot_index;
/*     */   public int item_id;
/*     */   public int entity_id;
/*     */   public double pos_x;
/*     */   public double pos_y;
/*     */   public double pos_z;
/*     */   public float rotation_yaw;
/*     */   public float rotation_pitch;
/*     */   private boolean prev_values_are_identical;
/*     */   public double prev_pos_x;
/*     */   public double prev_pos_y;
/*     */   public double prev_pos_z;
/*     */   public float prev_rotation_yaw;
/*     */   public float prev_rotation_pitch;
/*     */   public float y_size;
/*     */   public AxisAlignedBB bb;
/*     */   float partial_tick;
/*     */   public boolean ctrl_is_down;
/*     */   RightClickFilter filter;
/*     */   
/*     */   public Packet81RightClick() {}
/*     */   
/*     */   public Packet81RightClick(EntityPlayer player, float partial_tick, RightClickFilter filter) {
/*  39 */     if (filter.allowsEntityInteraction()) {
/*  40 */       Minecraft.setErrorMessage("Packet81RightClick: this constructor doesn't allow entity interaction");
/*     */     }
/*  42 */     this.slot_index = player.inventory.currentItem;
/*  43 */     this.item_id = player.getHeldItemID();
/*     */ 
/*     */     
/*  46 */     this.pos_x = player.posX;
/*  47 */     this.pos_y = player.posY - EntityPlayer.y_offset_on_client_and_eye_height_on_server;
/*  48 */     this.pos_z = player.posZ;
/*     */     
/*  50 */     this.rotation_yaw = player.rotationYaw;
/*  51 */     this.rotation_pitch = player.rotationPitch;
/*     */     
/*  53 */     this.prev_pos_x = player.prevPosX;
/*  54 */     this.prev_pos_y = player.prevPosY - EntityPlayer.y_offset_on_client_and_eye_height_on_server;
/*  55 */     this.prev_pos_z = player.prevPosZ;
/*     */     
/*  57 */     this.prev_rotation_yaw = player.prevRotationYaw;
/*  58 */     this.prev_rotation_pitch = player.prevRotationPitch;
/*     */     
/*  60 */     this.prev_values_are_identical = (this.prev_pos_x == this.pos_x && this.prev_pos_y == this.pos_y && this.prev_pos_z == this.pos_z && this.prev_rotation_yaw == this.rotation_yaw && this.prev_rotation_pitch == this.rotation_pitch);
/*     */     
/*  62 */     this.y_size = player.ySize;
/*  63 */     this.bb = new AxisAlignedBB(player.boundingBox);
/*     */     
/*  65 */     this.partial_tick = partial_tick;
/*     */     
/*  67 */     this.ctrl_is_down = GuiScreen.isCtrlKeyDown();
/*     */     
/*  69 */     this.filter = filter;
/*     */     
/*  71 */     if (player.getFootPosY() != player.posY - EntityPlayer.y_offset_on_client_and_eye_height_on_server) {
/*  72 */       Minecraft.setErrorMessage("Packet81RightClick: y offset discrepency detected");
/*     */     }
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
/*     */   public Packet81RightClick(EntityPlayer player, Entity entity) {
/*  92 */     this.slot_index = player.inventory.currentItem;
/*  93 */     this.item_id = player.getHeldItemID();
/*     */     
/*  95 */     this.entity_id = entity.entityId;
/*     */     
/*  97 */     this.ctrl_is_down = GuiScreen.isCtrlKeyDown();
/*     */     
/*  99 */     this.filter = (new RightClickFilter()).setExclusive(2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresRaycasting() {
/* 104 */     if (this.filter.allowsEntityInteractionOnly() || this.filter.allowsIngestionOnly()) {
/* 105 */       return false;
/*     */     }
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean prevValuesAreIdentical() {
/* 112 */     return this.prev_values_are_identical;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 117 */     this.slot_index = par1DataInput.readByte();
/* 118 */     this.item_id = par1DataInput.readShort();
/*     */     
/* 120 */     this.ctrl_is_down = par1DataInput.readBoolean();
/*     */     
/* 122 */     this.filter = new RightClickFilter(par1DataInput.readByte());
/*     */     
/* 124 */     if (this.filter.allowsEntityInteractionOnly()) {
/*     */       
/* 126 */       this.entity_id = par1DataInput.readInt();
/*     */       
/*     */       return;
/*     */     } 
/* 130 */     if (!requiresRaycasting()) {
/*     */       return;
/*     */     }
/* 133 */     this.pos_x = par1DataInput.readDouble();
/* 134 */     this.pos_y = par1DataInput.readDouble();
/* 135 */     this.pos_z = par1DataInput.readDouble();
/*     */     
/* 137 */     this.rotation_yaw = par1DataInput.readFloat();
/* 138 */     this.rotation_pitch = par1DataInput.readFloat();
/*     */     
/* 140 */     this.prev_values_are_identical = par1DataInput.readBoolean();
/*     */     
/* 142 */     if (this.prev_values_are_identical) {
/*     */       
/* 144 */       this.prev_pos_x = this.pos_x;
/* 145 */       this.prev_pos_y = this.pos_y;
/* 146 */       this.prev_pos_z = this.pos_z;
/*     */       
/* 148 */       this.prev_rotation_yaw = this.rotation_yaw;
/* 149 */       this.prev_rotation_pitch = this.rotation_pitch;
/*     */     }
/*     */     else {
/*     */       
/* 153 */       this.prev_pos_x = par1DataInput.readDouble();
/* 154 */       this.prev_pos_y = par1DataInput.readDouble();
/* 155 */       this.prev_pos_z = par1DataInput.readDouble();
/*     */       
/* 157 */       this.prev_rotation_yaw = par1DataInput.readFloat();
/* 158 */       this.prev_rotation_pitch = par1DataInput.readFloat();
/*     */     } 
/*     */     
/* 161 */     this.y_size = par1DataInput.readFloat();
/* 162 */     this.bb = new AxisAlignedBB(par1DataInput.readDouble(), par1DataInput.readDouble(), par1DataInput.readDouble(), par1DataInput.readDouble(), par1DataInput.readDouble(), par1DataInput.readDouble());
/*     */     
/* 164 */     this.partial_tick = par1DataInput.readFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 169 */     par1DataOutput.writeByte(this.slot_index);
/* 170 */     par1DataOutput.writeShort(this.item_id);
/*     */     
/* 172 */     par1DataOutput.writeBoolean(this.ctrl_is_down);
/*     */     
/* 174 */     par1DataOutput.writeByte(this.filter.getAllowedActions());
/*     */     
/* 176 */     if (this.filter.allowsEntityInteractionOnly()) {
/*     */       
/* 178 */       par1DataOutput.writeInt(this.entity_id);
/*     */       
/*     */       return;
/*     */     } 
/* 182 */     if (!requiresRaycasting()) {
/*     */       return;
/*     */     }
/* 185 */     par1DataOutput.writeDouble(this.pos_x);
/* 186 */     par1DataOutput.writeDouble(this.pos_y);
/* 187 */     par1DataOutput.writeDouble(this.pos_z);
/*     */     
/* 189 */     par1DataOutput.writeFloat(this.rotation_yaw);
/* 190 */     par1DataOutput.writeFloat(this.rotation_pitch);
/*     */     
/* 192 */     par1DataOutput.writeBoolean(this.prev_values_are_identical);
/*     */     
/* 194 */     if (!prevValuesAreIdentical()) {
/*     */       
/* 196 */       par1DataOutput.writeDouble(this.prev_pos_x);
/* 197 */       par1DataOutput.writeDouble(this.prev_pos_y);
/* 198 */       par1DataOutput.writeDouble(this.prev_pos_z);
/*     */       
/* 200 */       par1DataOutput.writeFloat(this.prev_rotation_yaw);
/* 201 */       par1DataOutput.writeFloat(this.prev_rotation_pitch);
/*     */     } 
/*     */     
/* 204 */     par1DataOutput.writeFloat(this.y_size);
/*     */     
/* 206 */     par1DataOutput.writeDouble(this.bb.minX);
/* 207 */     par1DataOutput.writeDouble(this.bb.minY);
/* 208 */     par1DataOutput.writeDouble(this.bb.minZ);
/* 209 */     par1DataOutput.writeDouble(this.bb.maxX);
/* 210 */     par1DataOutput.writeDouble(this.bb.maxY);
/* 211 */     par1DataOutput.writeDouble(this.bb.maxZ);
/*     */     
/* 213 */     par1DataOutput.writeFloat(this.partial_tick);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler net_handler) {
/* 218 */     net_handler.handleRightClick(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 223 */     if (this.filter.allowsEntityInteractionOnly())
/*     */     {
/*     */ 
/*     */       
/* 227 */       return 9;
/*     */     }
/* 229 */     if (requiresRaycasting()) {
/*     */       
/* 231 */       if (prevValuesAreIdentical())
/*     */       {
/*     */         
/* 234 */         return 94;
/*     */       }
/*     */ 
/*     */       
/* 238 */       return 126;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     return 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet81RightClick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */