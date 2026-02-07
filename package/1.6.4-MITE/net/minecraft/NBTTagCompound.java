/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NBTTagCompound
/*     */   extends NBTBase
/*     */ {
/*  17 */   private Map tagMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound() {
/*  22 */     super(10, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound(String par1Str) {
/*  28 */     super(10, par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput par1DataOutput) throws IOException {
/*  36 */     Iterator<NBTBase> var2 = this.tagMap.values().iterator();
/*     */     
/*  38 */     while (var2.hasNext()) {
/*     */       
/*  40 */       NBTBase var3 = var2.next();
/*  41 */       NBTBase.writeNamedTag(var3, par1DataOutput);
/*     */     } 
/*     */     
/*  44 */     par1DataOutput.writeByte(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void load(DataInput par1DataInput, int par2) throws IOException {
/*  52 */     if (par2 > 512)
/*     */     {
/*  54 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*     */ 
/*     */     
/*  58 */     this.tagMap.clear();
/*     */     
/*     */     NBTBase var3;
/*  61 */     while ((var3 = NBTBase.func_130104_b(par1DataInput, par2 + 1)).getId() != 0)
/*     */     {
/*  63 */       this.tagMap.put(var3.getName(), var3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getTags() {
/*  73 */     return this.tagMap.values();
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
/*     */   public void setTag(String par1Str, NBTBase par2NBTBase) {
/*  90 */     this.tagMap.put(par1Str, par2NBTBase.setName(par1Str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByte(String par1Str, byte par2) {
/*  98 */     this.tagMap.put(par1Str, new NBTTagByte(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShort(String par1Str, short par2) {
/* 106 */     this.tagMap.put(par1Str, new NBTTagShort(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteger(String par1Str, int par2) {
/* 114 */     this.tagMap.put(par1Str, new NBTTagInt(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(String par1Str, long par2) {
/* 122 */     this.tagMap.put(par1Str, new NBTTagLong(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(String par1Str, float par2) {
/* 130 */     this.tagMap.put(par1Str, new NBTTagFloat(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDouble(String par1Str, double par2) {
/* 138 */     this.tagMap.put(par1Str, new NBTTagDouble(par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String par1Str, String par2Str) {
/* 146 */     this.tagMap.put(par1Str, new NBTTagString(par1Str, par2Str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteArray(String par1Str, byte[] par2ArrayOfByte) {
/* 154 */     this.tagMap.put(par1Str, new NBTTagByteArray(par1Str, par2ArrayOfByte));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntArray(String par1Str, int[] par2ArrayOfInteger) {
/* 162 */     this.tagMap.put(par1Str, new NBTTagIntArray(par1Str, par2ArrayOfInteger));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompoundTag(String par1Str, NBTTagCompound par2NBTTagCompound) {
/* 170 */     this.tagMap.put(par1Str, par2NBTTagCompound.setName(par1Str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(String par1Str, boolean par2) {
/* 178 */     setByte(par1Str, (byte)(par2 ? 1 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase getTag(String par1Str) {
/* 186 */     return (NBTBase)this.tagMap.get(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(String par1Str) {
/* 194 */     return this.tagMap.containsKey(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String par1Str) {
/*     */     try {
/* 204 */       return !this.tagMap.containsKey(par1Str) ? 0 : ((NBTTagByte)this.tagMap.get(par1Str)).data;
/*     */     }
/* 206 */     catch (ClassCastException var3) {
/*     */       
/* 208 */       throw new ReportedException(createCrashReport(par1Str, 1, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String par1Str) {
/*     */     try {
/* 219 */       return !this.tagMap.containsKey(par1Str) ? 0 : ((NBTTagShort)this.tagMap.get(par1Str)).data;
/*     */     }
/* 221 */     catch (ClassCastException var3) {
/*     */       
/* 223 */       throw new ReportedException(createCrashReport(par1Str, 2, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInteger(String par1Str) {
/*     */     try {
/* 234 */       return !this.tagMap.containsKey(par1Str) ? 0 : ((NBTTagInt)this.tagMap.get(par1Str)).data;
/*     */     }
/* 236 */     catch (ClassCastException var3) {
/*     */       
/* 238 */       throw new ReportedException(createCrashReport(par1Str, 3, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIntegerWithDefault(String key, int default_value) {
/* 244 */     return hasKey(key) ? getInteger(key) : default_value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String par1Str) {
/*     */     try {
/* 254 */       return !this.tagMap.containsKey(par1Str) ? 0L : ((NBTTagLong)this.tagMap.get(par1Str)).data;
/*     */     }
/* 256 */     catch (ClassCastException var3) {
/*     */       
/* 258 */       throw new ReportedException(createCrashReport(par1Str, 4, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String par1Str) {
/*     */     try {
/* 269 */       return !this.tagMap.containsKey(par1Str) ? 0.0F : ((NBTTagFloat)this.tagMap.get(par1Str)).data;
/*     */     }
/* 271 */     catch (ClassCastException var3) {
/*     */       
/* 273 */       throw new ReportedException(createCrashReport(par1Str, 5, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String par1Str) {
/*     */     try {
/* 284 */       return !this.tagMap.containsKey(par1Str) ? 0.0D : ((NBTTagDouble)this.tagMap.get(par1Str)).data;
/*     */     }
/* 286 */     catch (ClassCastException var3) {
/*     */       
/* 288 */       throw new ReportedException(createCrashReport(par1Str, 6, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String par1Str) {
/*     */     try {
/* 299 */       return !this.tagMap.containsKey(par1Str) ? "" : ((NBTTagString)this.tagMap.get(par1Str)).data;
/*     */     }
/* 301 */     catch (ClassCastException var3) {
/*     */       
/* 303 */       throw new ReportedException(createCrashReport(par1Str, 8, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getByteArray(String par1Str) {
/*     */     try {
/* 314 */       return !this.tagMap.containsKey(par1Str) ? new byte[0] : ((NBTTagByteArray)this.tagMap.get(par1Str)).byteArray;
/*     */     }
/* 316 */     catch (ClassCastException var3) {
/*     */       
/* 318 */       throw new ReportedException(createCrashReport(par1Str, 7, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getIntArray(String par1Str) {
/*     */     try {
/* 329 */       return !this.tagMap.containsKey(par1Str) ? new int[0] : ((NBTTagIntArray)this.tagMap.get(par1Str)).intArray;
/*     */     }
/* 331 */     catch (ClassCastException var3) {
/*     */       
/* 333 */       throw new ReportedException(createCrashReport(par1Str, 11, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getCompoundTag(String par1Str) {
/*     */     try {
/* 345 */       return !this.tagMap.containsKey(par1Str) ? new NBTTagCompound(par1Str) : (NBTTagCompound)this.tagMap.get(par1Str);
/*     */     }
/* 347 */     catch (ClassCastException var3) {
/*     */       
/* 349 */       throw new ReportedException(createCrashReport(par1Str, 10, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getTagList(String par1Str) {
/*     */     try {
/* 360 */       return !this.tagMap.containsKey(par1Str) ? new NBTTagList(par1Str) : (NBTTagList)this.tagMap.get(par1Str);
/*     */     }
/* 362 */     catch (ClassCastException var3) {
/*     */       
/* 364 */       throw new ReportedException(createCrashReport(par1Str, 9, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String par1Str) {
/* 374 */     return (getByte(par1Str) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTag(String par1Str) {
/* 382 */     this.tagMap.remove(par1Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 387 */     String var1 = getName() + ":[";
/*     */ 
/*     */     
/* 390 */     for (Iterator<String> var2 = this.tagMap.keySet().iterator(); var2.hasNext(); var1 = var1 + var3 + ":" + this.tagMap.get(var3) + ",")
/*     */     {
/* 392 */       String var3 = var2.next();
/*     */     }
/*     */     
/* 395 */     return var1 + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNoTags() {
/* 403 */     return this.tagMap.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CrashReport createCrashReport(String par1Str, int par2, ClassCastException par3ClassCastException) {
/* 411 */     CrashReport var4 = CrashReport.makeCrashReport(par3ClassCastException, "Reading NBT data");
/* 412 */     CrashReportCategory var5 = var4.makeCategoryDepth("Corrupt NBT tag", 1);
/* 413 */     var5.addCrashSectionCallable("Tag type found", new CallableTagCompound1(this, par1Str));
/* 414 */     var5.addCrashSectionCallable("Tag type expected", new CallableTagCompound2(this, par2));
/* 415 */     var5.addCrashSection("Tag name", par1Str);
/*     */     
/* 417 */     if (getName() != null && getName().length() > 0)
/*     */     {
/* 419 */       var5.addCrashSection("Tag parent", getName());
/*     */     }
/*     */     
/* 422 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/* 430 */     NBTTagCompound var1 = new NBTTagCompound(getName());
/* 431 */     Iterator<String> var2 = this.tagMap.keySet().iterator();
/*     */     
/* 433 */     while (var2.hasNext()) {
/*     */       
/* 435 */       String var3 = var2.next();
/* 436 */       var1.setTag(var3, ((NBTBase)this.tagMap.get(var3)).copy());
/*     */     } 
/*     */     
/* 439 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 444 */     if (super.equals(par1Obj)) {
/*     */       
/* 446 */       NBTTagCompound var2 = (NBTTagCompound)par1Obj;
/* 447 */       return this.tagMap.entrySet().equals(var2.tagMap.entrySet());
/*     */     } 
/*     */ 
/*     */     
/* 451 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 457 */     return super.hashCode() ^ this.tagMap.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Map getTagMap(NBTTagCompound par0NBTTagCompound) {
/* 465 */     return par0NBTTagCompound.tagMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagCompound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */