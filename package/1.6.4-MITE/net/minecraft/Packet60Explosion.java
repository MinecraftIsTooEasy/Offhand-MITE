/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class Packet60Explosion
/*     */   extends Packet
/*     */ {
/*     */   public double explosionX;
/*     */   public double explosionY;
/*     */   public double explosionZ;
/*     */   public float explosion_size_vs_blocks;
/*     */   public float explosion_size_vs_living_entities;
/*     */   public List chunkPositionRecords;
/*     */   private float playerVelocityX;
/*     */   private float playerVelocityY;
/*     */   private float playerVelocityZ;
/*     */   
/*     */   public Packet60Explosion() {}
/*     */   
/*     */   public Packet60Explosion(double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, List<?> par8List, Vec3 par9Vec3) {
/*  48 */     this.explosionX = posX;
/*  49 */     this.explosionY = posY;
/*  50 */     this.explosionZ = posZ;
/*  51 */     this.explosion_size_vs_blocks = explosion_size_vs_blocks;
/*  52 */     this.explosion_size_vs_living_entities = explosion_size_vs_living_entities;
/*  53 */     this.chunkPositionRecords = new ArrayList(par8List);
/*     */     
/*  55 */     if (par9Vec3 != null) {
/*     */       
/*  57 */       this.playerVelocityX = (float)par9Vec3.xCoord;
/*  58 */       this.playerVelocityY = (float)par9Vec3.yCoord;
/*  59 */       this.playerVelocityZ = (float)par9Vec3.zCoord;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  68 */     this.explosionX = par1DataInput.readDouble();
/*  69 */     this.explosionY = par1DataInput.readDouble();
/*  70 */     this.explosionZ = par1DataInput.readDouble();
/*     */     
/*  72 */     this.explosion_size_vs_blocks = par1DataInput.readFloat();
/*  73 */     this.explosion_size_vs_living_entities = par1DataInput.readFloat();
/*  74 */     int var2 = par1DataInput.readInt();
/*  75 */     this.chunkPositionRecords = new ArrayList(var2);
/*  76 */     int var3 = (int)this.explosionX;
/*  77 */     int var4 = (int)this.explosionY;
/*  78 */     int var5 = (int)this.explosionZ;
/*     */     
/*  80 */     for (int var6 = 0; var6 < var2; var6++) {
/*     */       
/*  82 */       int var7 = par1DataInput.readByte() + var3;
/*  83 */       int var8 = par1DataInput.readByte() + var4;
/*  84 */       int var9 = par1DataInput.readByte() + var5;
/*  85 */       this.chunkPositionRecords.add(new ChunkPosition(var7, var8, var9));
/*     */     } 
/*     */     
/*  88 */     this.playerVelocityX = par1DataInput.readFloat();
/*  89 */     this.playerVelocityY = par1DataInput.readFloat();
/*  90 */     this.playerVelocityZ = par1DataInput.readFloat();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  98 */     par1DataOutput.writeDouble(this.explosionX);
/*  99 */     par1DataOutput.writeDouble(this.explosionY);
/* 100 */     par1DataOutput.writeDouble(this.explosionZ);
/*     */     
/* 102 */     par1DataOutput.writeFloat(this.explosion_size_vs_blocks);
/* 103 */     par1DataOutput.writeFloat(this.explosion_size_vs_living_entities);
/* 104 */     par1DataOutput.writeInt(this.chunkPositionRecords.size());
/* 105 */     int var2 = (int)this.explosionX;
/* 106 */     int var3 = (int)this.explosionY;
/* 107 */     int var4 = (int)this.explosionZ;
/* 108 */     Iterator<ChunkPosition> var5 = this.chunkPositionRecords.iterator();
/*     */     
/* 110 */     while (var5.hasNext()) {
/*     */       
/* 112 */       ChunkPosition var6 = var5.next();
/* 113 */       int var7 = var6.x - var2;
/* 114 */       int var8 = var6.y - var3;
/* 115 */       int var9 = var6.z - var4;
/* 116 */       par1DataOutput.writeByte(var7);
/* 117 */       par1DataOutput.writeByte(var8);
/* 118 */       par1DataOutput.writeByte(var9);
/*     */     } 
/*     */     
/* 121 */     par1DataOutput.writeFloat(this.playerVelocityX);
/* 122 */     par1DataOutput.writeFloat(this.playerVelocityY);
/* 123 */     par1DataOutput.writeFloat(this.playerVelocityZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 131 */     par1NetHandler.handleExplosion(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 140 */     return 36 + this.chunkPositionRecords.size() * 3 + 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlayerVelocityX() {
/* 148 */     return this.playerVelocityX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlayerVelocityY() {
/* 156 */     return this.playerVelocityY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlayerVelocityZ() {
/* 164 */     return this.playerVelocityZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet60Explosion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */