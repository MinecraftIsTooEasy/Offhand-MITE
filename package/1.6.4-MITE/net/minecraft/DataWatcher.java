/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.locks.ReadWriteLock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ 
/*     */ public class DataWatcher
/*     */ {
/*     */   private boolean isBlank = true;
/*  18 */   private static final HashMap dataTypes = new HashMap<Object, Object>();
/*  19 */   private final Map watchedObjects = new HashMap<Object, Object>();
/*     */   
/*     */   private boolean objectChanged;
/*     */   
/*  23 */   private ReadWriteLock lock = new ReentrantReadWriteLock();
/*     */   
/*     */   private int next_available_id;
/*     */ 
/*     */   
/*     */   public int getNextAvailableId() {
/*  29 */     return this.next_available_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addObject(int par1, Object par2Obj) {
/*  39 */     Integer var3 = (Integer)dataTypes.get(par2Obj.getClass());
/*     */     
/*  41 */     if (var3 == null)
/*     */     {
/*  43 */       throw new IllegalArgumentException("Unknown data type: " + par2Obj.getClass());
/*     */     }
/*  45 */     if (par1 > 31)
/*     */     {
/*  47 */       throw new IllegalArgumentException("Data value id is too big with " + par1 + "! (Max is " + '\037' + ")");
/*     */     }
/*  49 */     if (this.watchedObjects.containsKey(Integer.valueOf(par1)))
/*     */     {
/*  51 */       throw new IllegalArgumentException("Duplicate id value for " + par1 + "!");
/*     */     }
/*     */ 
/*     */     
/*  55 */     WatchableObject var4 = new WatchableObject(var3.intValue(), par1, par2Obj);
/*  56 */     this.lock.writeLock().lock();
/*  57 */     this.watchedObjects.put(Integer.valueOf(par1), var4);
/*  58 */     this.lock.writeLock().unlock();
/*  59 */     this.isBlank = false;
/*     */ 
/*     */     
/*  62 */     if (par1 >= this.next_available_id) {
/*  63 */       this.next_available_id = par1 + 1;
/*     */     }
/*  65 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addObjectByDataType(int par1, int par2) {
/*  74 */     WatchableObject var3 = new WatchableObject(par2, par1, null);
/*  75 */     this.lock.writeLock().lock();
/*  76 */     this.watchedObjects.put(Integer.valueOf(par1), var3);
/*  77 */     this.lock.writeLock().unlock();
/*  78 */     this.isBlank = false;
/*     */     
/*  80 */     if (par1 >= this.next_available_id) {
/*  81 */       this.next_available_id = par1 + 1;
/*     */     }
/*  83 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getWatchableObjectByte(int par1) {
/*  91 */     return ((Byte)getWatchedObject(par1).getObject()).byteValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public short getWatchableObjectShort(int par1) {
/*  96 */     return ((Short)getWatchedObject(par1).getObject()).shortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWatchableObjectInt(int par1) {
/* 104 */     return ((Integer)getWatchedObject(par1).getObject()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWatchableObjectFloat(int par1) {
/* 109 */     return ((Float)getWatchedObject(par1).getObject()).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWatchableObjectString(int par1) {
/* 117 */     return (String)getWatchedObject(par1).getObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getWatchableObjectItemStack(int par1) {
/* 125 */     return (ItemStack)getWatchedObject(par1).getObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WatchableObject getWatchedObject(int par1) {
/*     */     WatchableObject var2;
/* 133 */     this.lock.readLock().lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 138 */       var2 = (WatchableObject)this.watchedObjects.get(Integer.valueOf(par1));
/*     */     }
/* 140 */     catch (Throwable var6) {
/*     */       
/* 142 */       CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting synched entity data");
/* 143 */       CrashReportCategory var5 = var4.makeCategory("Synched entity data");
/* 144 */       var5.addCrashSection("Data ID", Integer.valueOf(par1));
/* 145 */       throw new ReportedException(var4);
/*     */     } 
/*     */     
/* 148 */     this.lock.readLock().unlock();
/* 149 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject(int par1, Object par2Obj) {
/* 157 */     WatchableObject var3 = getWatchedObject(par1);
/*     */     
/* 159 */     if (!par2Obj.equals(var3.getObject())) {
/*     */       
/* 161 */       var3.setObject(par2Obj);
/* 162 */       var3.setWatched(true);
/* 163 */       this.objectChanged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectWatched(int par1) {
/* 169 */     WatchableObject.setWatchableObjectWatched(getWatchedObject(par1), true);
/* 170 */     this.objectChanged = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChanges() {
/* 175 */     return this.objectChanged;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeObjectsInListToStream(List par0List, DataOutput par1DataOutput) throws IOException {
/* 183 */     if (par0List != null) {
/*     */       
/* 185 */       Iterator<WatchableObject> var2 = par0List.iterator();
/*     */       
/* 187 */       while (var2.hasNext()) {
/*     */         
/* 189 */         WatchableObject var3 = var2.next();
/* 190 */         writeWatchableObject(par1DataOutput, var3);
/*     */       } 
/*     */     } 
/*     */     
/* 194 */     par1DataOutput.writeByte(127);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPacketSizeOfObjectsInListToStream(List list) {
/* 200 */     int bytes = 1;
/*     */     
/* 202 */     if (list != null) {
/*     */       
/* 204 */       Iterator<WatchableObject> i = list.iterator();
/*     */       
/* 206 */       while (i.hasNext()) {
/* 207 */         bytes += getPacketSizeOfWatchableObject(i.next());
/*     */       }
/*     */     } 
/* 210 */     return bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public List unwatchAndReturnAllWatched() {
/* 215 */     ArrayList<WatchableObject> var1 = null;
/*     */     
/* 217 */     if (this.objectChanged) {
/*     */       
/* 219 */       this.lock.readLock().lock();
/* 220 */       Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator();
/*     */       
/* 222 */       while (var2.hasNext()) {
/*     */         
/* 224 */         WatchableObject var3 = var2.next();
/*     */         
/* 226 */         if (var3.isWatched()) {
/*     */           
/* 228 */           var3.setWatched(false);
/*     */           
/* 230 */           if (var1 == null)
/*     */           {
/* 232 */             var1 = new ArrayList();
/*     */           }
/*     */           
/* 235 */           var1.add(var3);
/*     */         } 
/*     */       } 
/*     */       
/* 239 */       this.lock.readLock().unlock();
/*     */     } 
/*     */     
/* 242 */     this.objectChanged = false;
/* 243 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSizeOfWatchableObjects() {
/* 249 */     int bytes = 0;
/*     */     
/* 251 */     Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator();
/*     */     
/* 253 */     while (var2.hasNext()) {
/* 254 */       bytes += getPacketSizeOfWatchableObject(var2.next());
/*     */     }
/* 256 */     bytes++;
/*     */     
/* 258 */     return bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeWatchableObjects(DataOutput par1DataOutput) throws IOException {
/* 263 */     this.lock.readLock().lock();
/* 264 */     Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator();
/*     */     
/* 266 */     while (var2.hasNext()) {
/*     */       
/* 268 */       WatchableObject var3 = var2.next();
/* 269 */       writeWatchableObject(par1DataOutput, var3);
/*     */     } 
/*     */     
/* 272 */     this.lock.readLock().unlock();
/* 273 */     par1DataOutput.writeByte(127);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getAllWatched() {
/* 278 */     ArrayList<WatchableObject> var1 = null;
/* 279 */     this.lock.readLock().lock();
/*     */ 
/*     */     
/* 282 */     for (Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator(); var2.hasNext(); var1.add(var3)) {
/*     */       
/* 284 */       WatchableObject var3 = var2.next();
/*     */       
/* 286 */       if (var1 == null)
/*     */       {
/* 288 */         var1 = new ArrayList();
/*     */       }
/*     */     } 
/*     */     
/* 292 */     this.lock.readLock().unlock();
/* 293 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getPacketSizeOfWatchableObject(WatchableObject wo) {
/* 299 */     int bytes = 1;
/*     */     
/* 301 */     switch (wo.getObjectType()) {
/*     */       
/*     */       case 0:
/* 304 */         bytes++;
/*     */         break;
/*     */       
/*     */       case 1:
/* 308 */         bytes += 2;
/*     */         break;
/*     */       
/*     */       case 2:
/* 312 */         bytes += 4;
/*     */         break;
/*     */       
/*     */       case 3:
/* 316 */         bytes += 4;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 321 */         bytes += Packet.getPacketSizeOfString((String)wo.getObject());
/*     */         break;
/*     */       
/*     */       case 5:
/* 325 */         bytes += Packet.getPacketSizeOfItemStack((ItemStack)wo.getObject());
/*     */         break;
/*     */       
/*     */       case 6:
/* 329 */         bytes += 12;
/*     */         break;
/*     */     } 
/* 332 */     return bytes;
/*     */   }
/*     */   private static void writeWatchableObject(DataOutput par0DataOutput, WatchableObject par1WatchableObject) throws IOException {
/*     */     ItemStack var4;
/*     */     ChunkCoordinates var3;
/* 337 */     int var2 = (par1WatchableObject.getObjectType() << 5 | par1WatchableObject.getDataValueId() & 0x1F) & 0xFF;
/* 338 */     par0DataOutput.writeByte(var2);
/*     */     
/* 340 */     switch (par1WatchableObject.getObjectType()) {
/*     */       
/*     */       case 0:
/* 343 */         par0DataOutput.writeByte(((Byte)par1WatchableObject.getObject()).byteValue());
/*     */         break;
/*     */       
/*     */       case 1:
/* 347 */         par0DataOutput.writeShort(((Short)par1WatchableObject.getObject()).shortValue());
/*     */         break;
/*     */       
/*     */       case 2:
/* 351 */         par0DataOutput.writeInt(((Integer)par1WatchableObject.getObject()).intValue());
/*     */         break;
/*     */       
/*     */       case 3:
/* 355 */         par0DataOutput.writeFloat(((Float)par1WatchableObject.getObject()).floatValue());
/*     */         break;
/*     */       
/*     */       case 4:
/* 359 */         Packet.writeString((String)par1WatchableObject.getObject(), par0DataOutput);
/*     */         break;
/*     */       
/*     */       case 5:
/* 363 */         var4 = (ItemStack)par1WatchableObject.getObject();
/* 364 */         Packet.writeItemStack(var4, par0DataOutput);
/*     */         break;
/*     */       
/*     */       case 6:
/* 368 */         var3 = (ChunkCoordinates)par1WatchableObject.getObject();
/* 369 */         par0DataOutput.writeInt(var3.posX);
/* 370 */         par0DataOutput.writeInt(var3.posY);
/* 371 */         par0DataOutput.writeInt(var3.posZ);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List readWatchableObjects(DataInput par0DataInput) throws IOException {
/* 377 */     ArrayList<WatchableObject> var1 = null;
/*     */     
/* 379 */     for (byte var2 = par0DataInput.readByte(); var2 != Byte.MAX_VALUE; var2 = par0DataInput.readByte()) {
/*     */       int var6, var7, var8;
/* 381 */       if (var1 == null)
/*     */       {
/* 383 */         var1 = new ArrayList();
/*     */       }
/*     */       
/* 386 */       int var3 = (var2 & 0xE0) >> 5;
/* 387 */       int var4 = var2 & 0x1F;
/* 388 */       WatchableObject var5 = null;
/*     */       
/* 390 */       switch (var3) {
/*     */         
/*     */         case 0:
/* 393 */           var5 = new WatchableObject(var3, var4, Byte.valueOf(par0DataInput.readByte()));
/*     */           break;
/*     */         
/*     */         case 1:
/* 397 */           var5 = new WatchableObject(var3, var4, Short.valueOf(par0DataInput.readShort()));
/*     */           break;
/*     */         
/*     */         case 2:
/* 401 */           var5 = new WatchableObject(var3, var4, Integer.valueOf(par0DataInput.readInt()));
/*     */           break;
/*     */         
/*     */         case 3:
/* 405 */           var5 = new WatchableObject(var3, var4, Float.valueOf(par0DataInput.readFloat()));
/*     */           break;
/*     */         
/*     */         case 4:
/* 409 */           var5 = new WatchableObject(var3, var4, Packet.readString(par0DataInput, 64));
/*     */           break;
/*     */         
/*     */         case 5:
/* 413 */           var5 = new WatchableObject(var3, var4, Packet.readItemStack(par0DataInput));
/*     */           break;
/*     */         
/*     */         case 6:
/* 417 */           var6 = par0DataInput.readInt();
/* 418 */           var7 = par0DataInput.readInt();
/* 419 */           var8 = par0DataInput.readInt();
/* 420 */           var5 = new WatchableObject(var3, var4, new ChunkCoordinates(var6, var7, var8));
/*     */           break;
/*     */       } 
/* 423 */       var1.add(var5);
/*     */     } 
/*     */     
/* 426 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWatchedObjectsFromList(List par1List) {
/* 431 */     if (par1List == null) {
/*     */       return;
/*     */     }
/* 434 */     this.lock.writeLock().lock();
/* 435 */     Iterator<WatchableObject> var2 = par1List.iterator();
/*     */     
/* 437 */     while (var2.hasNext()) {
/*     */       
/* 439 */       WatchableObject var3 = var2.next();
/* 440 */       WatchableObject var4 = (WatchableObject)this.watchedObjects.get(Integer.valueOf(var3.getDataValueId()));
/*     */       
/* 442 */       if (var4 != null)
/*     */       {
/* 444 */         var4.setObject(var3.getObject());
/*     */       }
/*     */     } 
/*     */     
/* 448 */     this.lock.writeLock().unlock();
/* 449 */     this.objectChanged = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsBlank() {
/* 454 */     return this.isBlank;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_111144_e() {
/* 459 */     this.objectChanged = false;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 464 */     dataTypes.put(Byte.class, Integer.valueOf(0));
/* 465 */     dataTypes.put(Short.class, Integer.valueOf(1));
/* 466 */     dataTypes.put(Integer.class, Integer.valueOf(2));
/* 467 */     dataTypes.put(Float.class, Integer.valueOf(3));
/* 468 */     dataTypes.put(String.class, Integer.valueOf(4));
/* 469 */     dataTypes.put(ItemStack.class, Integer.valueOf(5));
/* 470 */     dataTypes.put(ChunkCoordinates.class, Integer.valueOf(6));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DataWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */