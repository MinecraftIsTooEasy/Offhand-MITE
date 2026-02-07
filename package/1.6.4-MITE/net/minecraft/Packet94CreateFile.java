/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.Desktop;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
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
/*     */ public class Packet94CreateFile
/*     */   extends Packet
/*     */ {
/*     */   public static final int CONTEXT_ENTITY_STATS_DUMP = 1;
/*     */   private String filepath;
/*     */   private PacketComponentBytes content;
/*     */   private byte context;
/*     */   private boolean open_in_editor;
/*     */   
/*     */   public Packet94CreateFile() {}
/*     */   
/*     */   public Packet94CreateFile(String filepath, byte[] content) {
/*  32 */     this(filepath, content, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet94CreateFile(String filepath, byte[] content, int compression_level) {
/*  37 */     this.filepath = filepath;
/*  38 */     this.content = new PacketComponentBytes(content, compression_level, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void compressPayload() {
/*  43 */     this.content.compress();
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  48 */     this.filepath = readString(par1DataInput, 255);
/*     */     
/*  50 */     this.content = new PacketComponentBytes(this);
/*  51 */     this.content.readData(par1DataInput);
/*     */     
/*  53 */     this.context = par1DataInput.readByte();
/*  54 */     this.open_in_editor = par1DataInput.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  59 */     writeString(this.filepath, par1DataOutput);
/*     */     
/*  61 */     this.content.writeData(par1DataOutput);
/*     */     
/*  63 */     par1DataOutput.writeByte(this.context);
/*  64 */     par1DataOutput.writeBoolean(this.open_in_editor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler net_handler) {
/*  69 */     net_handler.handleCreateFile(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  74 */     return Packet.getPacketSizeOfString(this.filepath) + this.content.getSize() + 1 + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProcessAsync() {
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet94CreateFile setOptions(int context, boolean open_in_editor) {
/*  94 */     this.context = (byte)context;
/*  95 */     this.open_in_editor = open_in_editor;
/*     */     
/*  97 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContext() {
/* 102 */     return this.context;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilepath() {
/* 107 */     return this.filepath;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean writeFile() {
/* 112 */     File file = new File(this.filepath);
/* 113 */     file.getParentFile().mkdirs();
/*     */ 
/*     */     
/*     */     try {
/* 117 */       FileWriter fw = new FileWriter(file);
/*     */       
/* 119 */       fw.write(this.content.getBytesAsString());
/* 120 */       fw.close();
/*     */ 
/*     */ 
/*     */       
/* 124 */       if (this.open_in_editor && Minecraft.theMinecraft != null && !Minecraft.theMinecraft.isFullScreen()) {
/*     */         
/*     */         try {
/*     */           
/* 128 */           if (Desktop.isDesktopSupported()) {
/* 129 */             Desktop.getDesktop().edit(file);
/*     */           }
/* 131 */         } catch (Exception e) {
/*     */           
/* 133 */           if (Minecraft.inDevMode()) {
/* 134 */             e.printStackTrace();
/*     */           }
/*     */         } 
/*     */       }
/* 138 */       return true;
/*     */     }
/* 140 */     catch (Exception e) {
/*     */       
/* 142 */       System.out.println("Writing file to \"" + this.filepath + "\" [failed]\n");
/*     */       
/* 144 */       if (Minecraft.inDevMode()) {
/* 145 */         e.printStackTrace();
/*     */       }
/*     */       
/* 148 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet94CreateFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */