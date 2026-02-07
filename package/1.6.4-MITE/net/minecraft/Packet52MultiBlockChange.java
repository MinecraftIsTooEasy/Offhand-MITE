/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet52MultiBlockChange
/*     */   extends Packet
/*     */ {
/*     */   public int xPosition;
/*     */   public int zPosition;
/*     */   public byte[] metadataArray;
/*     */   public int size;
/*  22 */   private static byte[] field_73449_e = new byte[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet52MultiBlockChange() {
/*  28 */     this.isChunkDataPacket = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet52MultiBlockChange(int par1, int par2, short[] par3ArrayOfShort, int par4, World par5World) {
/*  33 */     Minecraft.setErrorMessage("Packet52 created?");
/*     */     
/*  35 */     this.isChunkDataPacket = true;
/*  36 */     this.xPosition = par1;
/*  37 */     this.zPosition = par2;
/*  38 */     this.size = par4;
/*  39 */     int var6 = 4 * par4;
/*  40 */     Chunk var7 = par5World.getChunkFromChunkCoords(par1, par2);
/*     */ 
/*     */     
/*     */     try {
/*  44 */       if (par4 >= 64)
/*     */       {
/*  46 */         this.field_98193_m.logInfo("ChunkTilesUpdatePacket compress " + par4);
/*     */         
/*  48 */         if (field_73449_e.length < var6)
/*     */         {
/*  50 */           field_73449_e = new byte[var6];
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  55 */         ByteArrayOutputStream var8 = new ByteArrayOutputStream(var6);
/*  56 */         DataOutputStream var9 = new DataOutputStream(var8);
/*     */         
/*  58 */         for (int var10 = 0; var10 < par4; var10++) {
/*     */           
/*  60 */           int var11 = par3ArrayOfShort[var10] >> 12 & 0xF;
/*  61 */           int var12 = par3ArrayOfShort[var10] >> 8 & 0xF;
/*  62 */           int var13 = par3ArrayOfShort[var10] & 0xFF;
/*  63 */           var9.writeShort(par3ArrayOfShort[var10]);
/*  64 */           var9.writeShort((short)((var7.getBlockID(var11, var13, var12) & 0xFFF) << 4 | var7.getBlockMetadata(var11, var13, var12) & 0xF));
/*     */         } 
/*     */         
/*  67 */         this.metadataArray = var8.toByteArray();
/*     */         
/*  69 */         if (this.metadataArray.length != var6)
/*     */         {
/*  71 */           throw new RuntimeException("Expected length " + var6 + " doesn't match received length " + this.metadataArray.length);
/*     */         }
/*     */       }
/*     */     
/*  75 */     } catch (IOException var14) {
/*     */       
/*  77 */       this.field_98193_m.logSevereException("Couldn't create chunk packet", var14);
/*  78 */       this.metadataArray = null;
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
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 118 */     this.xPosition = par1DataInput.readInt();
/* 119 */     this.zPosition = par1DataInput.readInt();
/* 120 */     this.size = par1DataInput.readShort() & 0xFFFF;
/* 121 */     int var2 = par1DataInput.readInt();
/*     */     
/* 123 */     if (var2 > 0) {
/*     */       
/* 125 */       this.metadataArray = new byte[var2];
/* 126 */       par1DataInput.readFully(this.metadataArray);
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
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 141 */     par1DataOutput.writeInt(this.xPosition);
/* 142 */     par1DataOutput.writeInt(this.zPosition);
/* 143 */     par1DataOutput.writeShort((short)this.size);
/*     */     
/* 145 */     if (this.metadataArray != null) {
/*     */       
/* 147 */       par1DataOutput.writeInt(this.metadataArray.length);
/* 148 */       par1DataOutput.write(this.metadataArray);
/*     */     }
/*     */     else {
/*     */       
/* 152 */       par1DataOutput.writeInt(0);
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
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 164 */     par1NetHandler.handleMultiBlockChange(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 172 */     return 10 + this.size * 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet52MultiBlockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */