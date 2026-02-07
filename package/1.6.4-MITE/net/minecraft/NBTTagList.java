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
/*     */ public final class NBTTagList
/*     */   extends NBTBase
/*     */ {
/*  14 */   private List tagList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private byte tagType;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList() {
/*  24 */     super(9, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList(String par1Str) {
/*  30 */     super(9, par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput par1DataOutput) throws IOException {
/*  38 */     if (!this.tagList.isEmpty()) {
/*     */       
/*  40 */       this.tagType = ((NBTBase)this.tagList.get(0)).getId();
/*     */     }
/*     */     else {
/*     */       
/*  44 */       this.tagType = 1;
/*     */     } 
/*     */ 
/*     */     
/*  48 */     par1DataOutput.writeByte(convertIdForDisk(this.tagType));
/*  49 */     par1DataOutput.writeInt(this.tagList.size());
/*     */     
/*  51 */     for (int var2 = 0; var2 < this.tagList.size(); var2++)
/*     */     {
/*  53 */       ((NBTBase)this.tagList.get(var2)).write(par1DataOutput);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void load(DataInput par1DataInput, int par2) throws IOException {
/*  62 */     if (par2 > 512)
/*     */     {
/*  64 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*     */ 
/*     */     
/*  68 */     this.tagType = par1DataInput.readByte();
/*     */     
/*  70 */     this.tagType = convertIdFromDisk(this.tagType);
/*     */     
/*  72 */     int var3 = par1DataInput.readInt();
/*  73 */     this.tagList = new ArrayList();
/*     */     
/*  75 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  77 */       NBTBase var5 = NBTBase.newTag(this.tagType, (String)null);
/*     */       
/*  79 */       if (var5 == null && NBTBase.loading_world_info) {
/*  80 */         NBTBase.incompatibleWorldInfoFormat();
/*     */       }
/*  82 */       var5.load(par1DataInput, par2 + 1);
/*  83 */       this.tagList.add(var5);
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
/*     */   public String toString() {
/*  99 */     return "" + this.tagList.size() + " entries of type " + NBTBase.getTagName(this.tagType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendTag(NBTBase par1NBTBase) {
/* 108 */     this.tagType = par1NBTBase.getId();
/* 109 */     this.tagList.add(par1NBTBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase removeTag(int par1) {
/* 117 */     return this.tagList.remove(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase tagAt(int par1) {
/* 125 */     return this.tagList.get(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tagCount() {
/* 133 */     return this.tagList.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/* 141 */     NBTTagList var1 = new NBTTagList(getName());
/* 142 */     var1.tagType = this.tagType;
/* 143 */     Iterator<NBTBase> var2 = this.tagList.iterator();
/*     */     
/* 145 */     while (var2.hasNext()) {
/*     */       
/* 147 */       NBTBase var3 = var2.next();
/* 148 */       NBTBase var4 = var3.copy();
/* 149 */       var1.tagList.add(var4);
/*     */     } 
/*     */     
/* 152 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 157 */     if (super.equals(par1Obj)) {
/*     */       
/* 159 */       NBTTagList var2 = (NBTTagList)par1Obj;
/*     */       
/* 161 */       if (this.tagType == var2.tagType)
/*     */       {
/* 163 */         return this.tagList.equals(var2.tagList);
/*     */       }
/*     */     } 
/*     */     
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 172 */     return super.hashCode() ^ this.tagList.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */