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
/*     */ public class Packet15Place
/*     */   extends Packet
/*     */ {
/*     */   private int xPosition;
/*     */   private int yPosition;
/*     */   private int zPosition;
/*     */   private EnumFace face;
/*     */   private ItemStack itemStack;
/*     */   private float xOffset;
/*     */   private float yOffset;
/*     */   private float zOffset;
/*     */   
/*     */   public Packet15Place() {}
/*     */   
/*     */   public Packet15Place(int par1, int par2, int par3, EnumFace face, ItemStack par5ItemStack, float par6, float par7, float par8) {
/*  32 */     this.xPosition = par1;
/*  33 */     this.yPosition = par2;
/*  34 */     this.zPosition = par3;
/*     */     
/*  36 */     this.face = face;
/*  37 */     this.itemStack = (par5ItemStack != null) ? par5ItemStack.copy() : null;
/*  38 */     this.xOffset = par6;
/*  39 */     this.yOffset = par7;
/*  40 */     this.zOffset = par8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  48 */     this.xPosition = par1DataInput.readInt();
/*  49 */     this.yPosition = par1DataInput.readUnsignedByte();
/*  50 */     this.zPosition = par1DataInput.readInt();
/*     */ 
/*     */ 
/*     */     
/*  54 */     int side_ordinal = par1DataInput.readUnsignedByte();
/*  55 */     this.face = EnumFace.isValidOrdinal(side_ordinal) ? EnumFace.get(side_ordinal) : null;
/*     */     
/*  57 */     this.itemStack = readItemStack(par1DataInput);
/*  58 */     this.xOffset = par1DataInput.readUnsignedByte() / 16.0F;
/*  59 */     this.yOffset = par1DataInput.readUnsignedByte() / 16.0F;
/*  60 */     this.zOffset = par1DataInput.readUnsignedByte() / 16.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  68 */     par1DataOutput.writeInt(this.xPosition);
/*  69 */     par1DataOutput.write(this.yPosition);
/*  70 */     par1DataOutput.writeInt(this.zPosition);
/*     */     
/*  72 */     par1DataOutput.write(this.face.ordinal());
/*  73 */     writeItemStack(this.itemStack, par1DataOutput);
/*  74 */     par1DataOutput.write((int)(this.xOffset * 16.0F));
/*  75 */     par1DataOutput.write((int)(this.yOffset * 16.0F));
/*  76 */     par1DataOutput.write((int)(this.zOffset * 16.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  84 */     par1NetHandler.handlePlace(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  93 */     return 13 + Packet.getPacketSizeOfItemStack(this.itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getXPosition() {
/*  98 */     return this.xPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getYPosition() {
/* 103 */     return this.yPosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZPosition() {
/* 108 */     return this.zPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFace getFace() {
/* 118 */     return this.face;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemStack() {
/* 123 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXOffset() {
/* 131 */     return this.xOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getYOffset() {
/* 139 */     return this.yOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getZOffset() {
/* 147 */     return this.zOffset;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet15Place.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */