/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ public abstract class NBTBase
/*     */ {
/*  11 */   public static final String[] NBTTypes = new String[] { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]" };
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean use_new_NBTs = true;
/*     */ 
/*     */   
/*     */   public static boolean loading_world_info;
/*     */ 
/*     */   
/*     */   public final byte id;
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void write(DataOutput paramDataOutput) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void load(DataInput paramDataInput, int paramInt) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getId() {
/*  38 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected NBTBase(int id, String par1Str) {
/*  44 */     this.id = (byte)id;
/*     */     
/*  46 */     if (par1Str == null) {
/*     */       
/*  48 */       this.name = "";
/*     */     }
/*     */     else {
/*     */       
/*  52 */       this.name = par1Str;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final NBTBase setName(String par1Str) {
/*  62 */     if (par1Str == null) {
/*     */       
/*  64 */       this.name = "";
/*     */     }
/*     */     else {
/*     */       
/*  68 */       this.name = par1Str;
/*     */     } 
/*     */     
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getName() {
/*  80 */     return (this.name == null) ? "" : this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final NBTBase readNamedTag(DataInput par0DataInput) throws IOException {
/*  89 */     return func_130104_b(par0DataInput, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte convertIdFromDisk(int id) {
/*  95 */     return (byte)((id > 0) ? (128 - id) : id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final NBTBase func_130104_b(DataInput par0DataInput, int par1) throws IOException {
/* 101 */     byte var2 = par0DataInput.readByte();
/*     */     
/* 103 */     var2 = convertIdFromDisk(var2);
/*     */     
/* 105 */     if (var2 == 0)
/*     */     {
/* 107 */       return new NBTTagEnd();
/*     */     }
/*     */ 
/*     */     
/* 111 */     String var3 = par0DataInput.readUTF();
/* 112 */     NBTBase var4 = newTag(var2, var3);
/*     */     
/* 114 */     if (var4 == null && MinecraftServer.getServer() instanceof DedicatedServer) {
/*     */       
/* 116 */       System.out.println(fs("Lfgwzgvw Dliow"));
/* 117 */       System.exit(0);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 122 */       var4.load(par0DataInput, par1);
/* 123 */       return var4;
/*     */     }
/* 125 */     catch (IOException var8) {
/*     */       
/* 127 */       if (loading_world_info) {
/* 128 */         incompatibleWorldInfoFormat();
/*     */       }
/* 130 */       CrashReport var6 = CrashReport.makeCrashReport(var8, "Loading NBT data");
/* 131 */       CrashReportCategory var7 = var6.makeCategory("NBT Tag");
/* 132 */       var7.addCrashSection("Tag name", var3);
/* 133 */       var7.addCrashSection("Tag type", Byte.valueOf(var2));
/* 134 */       throw new ReportedException(var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void incompatibleWorldInfoFormat() {
/* 141 */     System.out.println("Incompatible world info format");
/* 142 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte convertIdForDisk(int id) {
/* 148 */     return (byte)((id > 0) ? (128 - id) : id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void writeNamedTag(NBTBase par0NBTBase, DataOutput par1DataOutput) throws IOException {
/* 159 */     par1DataOutput.writeByte(convertIdForDisk(par0NBTBase.id));
/*     */     
/* 161 */     if (par0NBTBase.getId() != 0) {
/*     */       
/* 163 */       par1DataOutput.writeUTF(par0NBTBase.getName());
/* 164 */       par0NBTBase.write(par1DataOutput);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final NBTBase newTag(byte par0, String par1Str) {
/* 174 */     switch (par0) {
/*     */       
/*     */       case 0:
/* 177 */         return new NBTTagEnd();
/*     */       
/*     */       case 1:
/* 180 */         return new NBTTagByte(par1Str);
/*     */       
/*     */       case 2:
/* 183 */         return new NBTTagShort(par1Str);
/*     */       
/*     */       case 3:
/* 186 */         return new NBTTagInt(par1Str);
/*     */       
/*     */       case 4:
/* 189 */         return new NBTTagLong(par1Str);
/*     */       
/*     */       case 5:
/* 192 */         return new NBTTagFloat(par1Str);
/*     */       
/*     */       case 6:
/* 195 */         return new NBTTagDouble(par1Str);
/*     */       
/*     */       case 7:
/* 198 */         return new NBTTagByteArray(par1Str);
/*     */       
/*     */       case 8:
/* 201 */         return new NBTTagString(par1Str);
/*     */       
/*     */       case 9:
/* 204 */         return new NBTTagList(par1Str);
/*     */       
/*     */       case 10:
/* 207 */         return new NBTTagCompound(par1Str);
/*     */       
/*     */       case 11:
/* 210 */         return new NBTTagIntArray(par1Str);
/*     */     } 
/*     */     
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String getTagName(byte par0) {
/* 223 */     switch (par0) {
/*     */       
/*     */       case 0:
/* 226 */         return "TAG_End";
/*     */       
/*     */       case 1:
/* 229 */         return "TAG_Byte";
/*     */       
/*     */       case 2:
/* 232 */         return "TAG_Short";
/*     */       
/*     */       case 3:
/* 235 */         return "TAG_Int";
/*     */       
/*     */       case 4:
/* 238 */         return "TAG_Long";
/*     */       
/*     */       case 5:
/* 241 */         return "TAG_Float";
/*     */       
/*     */       case 6:
/* 244 */         return "TAG_Double";
/*     */       
/*     */       case 7:
/* 247 */         return "TAG_Byte_Array";
/*     */       
/*     */       case 8:
/* 250 */         return "TAG_String";
/*     */       
/*     */       case 9:
/* 253 */         return "TAG_List";
/*     */       
/*     */       case 10:
/* 256 */         return "TAG_Compound";
/*     */       
/*     */       case 11:
/* 259 */         return "TAG_Int_Array";
/*     */     } 
/*     */     
/* 262 */     return "UNKNOWN";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NBTBase copy();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 273 */     if (!(par1Obj instanceof NBTBase))
/*     */     {
/* 275 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 279 */     NBTBase var2 = (NBTBase)par1Obj;
/* 280 */     return (getId() != var2.getId()) ? false : (((this.name != null || var2.name == null) && (this.name == null || var2.name != null)) ? ((this.name == null || this.name.equals(var2.name))) : false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 286 */     return this.name.hashCode() ^ getId();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String fs(String s) {
/* 291 */     char[] chars = s.toCharArray();
/*     */     
/* 293 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/* 295 */       int c = chars[i];
/*     */       
/* 297 */       if (c >= 65 && c <= 90) {
/* 298 */         c = 90 - c - 65;
/* 299 */       } else if (c >= 97 && c <= 122) {
/* 300 */         c = 122 - c - 97;
/* 301 */       } else if (c >= 48 && c <= 57) {
/* 302 */         c = 57 - c - 48;
/*     */       } 
/* 304 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/* 307 */     return new String(chars);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */