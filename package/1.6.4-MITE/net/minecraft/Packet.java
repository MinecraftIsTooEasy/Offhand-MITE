/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.Inflater;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Packet
/*     */ {
/*  21 */   public static IntHashMap packetIdToClassMap = new IntHashMap();
/*     */ 
/*     */   
/*  24 */   private static Map packetClassToIdMap = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  27 */   private static Set clientPacketIdList = new HashSet();
/*     */ 
/*     */   
/*  30 */   private static Set serverPacketIdList = new HashSet();
/*     */   
/*     */   protected ILogAgent field_98193_m;
/*     */   
/*  34 */   public final long creationTimeMillis = MinecraftServer.getSystemTimeMillis();
/*     */ 
/*     */   
/*     */   public static long receivedID;
/*     */ 
/*     */   
/*     */   public static long receivedSize;
/*     */ 
/*     */   
/*     */   public static long sentID;
/*     */ 
/*     */   
/*     */   public static long sentSize;
/*     */ 
/*     */   
/*     */   public boolean isChunkDataPacket;
/*     */   
/*     */   private boolean has_been_added_to_tcp_send_queue;
/*     */ 
/*     */   
/*     */   static void addIdClassMapping(int par0, boolean par1, boolean par2, Class<?> par3Class) {
/*  55 */     if (packetIdToClassMap.containsItem(par0))
/*     */     {
/*  57 */       throw new IllegalArgumentException("Duplicate packet id:" + par0);
/*     */     }
/*  59 */     if (packetClassToIdMap.containsKey(par3Class))
/*     */     {
/*  61 */       throw new IllegalArgumentException("Duplicate packet class:" + par3Class);
/*     */     }
/*     */ 
/*     */     
/*  65 */     packetIdToClassMap.addKey(par0, par3Class);
/*  66 */     packetClassToIdMap.put(par3Class, Integer.valueOf(par0));
/*     */     
/*  68 */     if (par1)
/*     */     {
/*  70 */       clientPacketIdList.add(Integer.valueOf(par0));
/*     */     }
/*     */     
/*  73 */     if (par2)
/*     */     {
/*  75 */       serverPacketIdList.add(Integer.valueOf(par0));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Packet getNewPacket(ILogAgent par0ILogAgent, int par1) {
/*     */     try {
/*  87 */       Class<Packet> var2 = (Class)packetIdToClassMap.lookup(par1);
/*  88 */       return (var2 == null) ? null : var2.newInstance();
/*     */     }
/*  90 */     catch (Exception var3) {
/*     */       
/*  92 */       var3.printStackTrace();
/*     */ 
/*     */       
/*  95 */       if (par0ILogAgent == null) {
/*  96 */         Minecraft.setErrorMessage("getNewPacket: was not able to create an instance of packet with id " + par1);
/*     */       } else {
/*  98 */         par0ILogAgent.logSevere("Skipping packet with id " + par1);
/*     */       } 
/* 100 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeByteArray(DataOutput par0DataOutput, byte[] par1ArrayOfByte) throws IOException {
/* 109 */     par0DataOutput.writeShort(par1ArrayOfByte.length);
/* 110 */     par0DataOutput.write(par1ArrayOfByte);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] readBytesFromStream(DataInput par0DataInput) throws IOException {
/* 118 */     short var1 = par0DataInput.readShort();
/*     */     
/* 120 */     if (var1 < 0)
/*     */     {
/* 122 */       throw new IOException("Key was smaller than nothing!  Weird key!");
/*     */     }
/*     */ 
/*     */     
/* 126 */     byte[] var2 = new byte[var1];
/* 127 */     par0DataInput.readFully(var2);
/* 128 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPacketId() {
/* 137 */     return ((Integer)packetClassToIdMap.get(getClass())).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Packet readPacket(ILogAgent par0ILogAgent, DataInput par1DataInput, boolean par2, Socket par3Socket) throws IOException {
/* 146 */     Packet var5 = null;
/* 147 */     int var6 = par3Socket.getSoTimeout();
/*     */     
/* 149 */     int var9 = -1;
/*     */ 
/*     */     
/*     */     try {
/* 153 */       var9 = par1DataInput.readUnsignedByte();
/*     */       
/* 155 */       if ((par2 && !serverPacketIdList.contains(Integer.valueOf(var9))) || (!par2 && !clientPacketIdList.contains(Integer.valueOf(var9))))
/*     */       {
/* 157 */         throw new IOException("Bad packet id " + var9);
/*     */       }
/*     */       
/* 160 */       var5 = getNewPacket(par0ILogAgent, var9);
/*     */       
/* 162 */       if (var5 == null)
/*     */       {
/* 164 */         throw new IOException("Bad packet id " + var9);
/*     */       }
/*     */       
/* 167 */       var5.field_98193_m = par0ILogAgent;
/*     */       
/* 169 */       if (var5 instanceof Packet254ServerPing)
/*     */       {
/* 171 */         par3Socket.setSoTimeout(1500);
/*     */       }
/*     */       
/* 174 */       var5.readPacketData(par1DataInput);
/* 175 */       receivedID++;
/* 176 */       receivedSize += var5.getPacketSize();
/*     */     }
/* 178 */     catch (EOFException var8) {
/*     */ 
/*     */       
/* 181 */       par0ILogAgent.logSevere("Reached end of stream for " + par3Socket.getInetAddress() + ", packet id=" + var9);
/* 182 */       return null;
/*     */     } 
/*     */     
/* 185 */     PacketCount.countPacket(var9, var5.getPacketSize());
/*     */ 
/*     */     
/* 188 */     par3Socket.setSoTimeout(var6);
/* 189 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writePacket(Packet par0Packet, DataOutput par1DataOutput) throws IOException {
/* 197 */     par1DataOutput.write(par0Packet.getPacketId());
/* 198 */     par0Packet.writePacketData(par1DataOutput);
/* 199 */     sentID++;
/* 200 */     sentSize += par0Packet.getPacketSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPacketSizeOfString(String string) {
/* 211 */     return 2 + (string.getBytes()).length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeString(String par0Str, DataOutput par1DataOutput) throws IOException {
/* 219 */     if (par0Str.length() > 32767)
/*     */     {
/* 221 */       throw new IOException("String too big");
/*     */     }
/*     */ 
/*     */     
/* 225 */     par1DataOutput.writeShort(par0Str.length());
/* 226 */     par1DataOutput.writeChars(par0Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readString(DataInput par0DataInput, int par1) throws IOException {
/* 236 */     short var2 = par0DataInput.readShort();
/*     */     
/* 238 */     if (var2 > par1)
/*     */     {
/* 240 */       throw new IOException("Received string length longer than maximum allowed (" + var2 + " > " + par1 + ")");
/*     */     }
/*     */     
/* 243 */     if (var2 < 0)
/*     */     {
/* 245 */       throw new IOException("Received string length is less than zero! Weird string!");
/*     */     }
/*     */ 
/*     */     
/* 249 */     StringBuilder var3 = new StringBuilder();
/*     */     
/* 251 */     for (int var4 = 0; var4 < var2; var4++)
/*     */     {
/* 253 */       var3.append(par0DataInput.readChar());
/*     */     }
/*     */     
/* 256 */     return var3.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void readPacketData(DataInput paramDataInput) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void writePacketData(DataOutput paramDataOutput) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void processPacket(NetHandler paramNetHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getPacketSize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 300 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProcessAsync() {
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 314 */     String var1 = getClass().getSimpleName();
/* 315 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack readItemStack(DataInput par0DataInput) throws IOException {
/* 323 */     ItemStack var1 = null;
/* 324 */     short var2 = par0DataInput.readShort();
/*     */     
/* 326 */     if (var2 >= 0) {
/*     */       
/* 328 */       byte var3 = par0DataInput.readByte();
/* 329 */       short var4 = par0DataInput.readShort();
/*     */       
/* 331 */       var1 = new ItemStack(var2, var3, var4);
/*     */       
/* 333 */       int quality_ordinal = par0DataInput.readByte();
/*     */       
/* 335 */       if (quality_ordinal >= 0) {
/* 336 */         var1.setQuality(EnumQuality.values()[quality_ordinal]);
/*     */       }
/* 338 */       var1.stackTagCompound = readNBTTagCompound(par0DataInput);
/*     */       
/* 340 */       if (var1.isItemStackDamageable()) {
/* 341 */         var1.setItemDamage(readItemStackDamage(var1, par0DataInput));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeItemStack(ItemStack par0ItemStack, DataOutput par1DataOutput) throws IOException {
/* 356 */     if (par0ItemStack == null) {
/*     */       
/* 358 */       par1DataOutput.writeShort(-1);
/*     */     }
/*     */     else {
/*     */       
/* 362 */       par1DataOutput.writeShort(par0ItemStack.itemID);
/* 363 */       par1DataOutput.writeByte(par0ItemStack.stackSize);
/*     */       
/* 365 */       par1DataOutput.writeShort(par0ItemStack.getItemSubtype());
/*     */       
/* 367 */       par1DataOutput.writeByte((par0ItemStack.getQuality() == null) ? -1 : par0ItemStack.getQuality().ordinal());
/*     */       
/* 369 */       NBTTagCompound var2 = null;
/*     */       
/* 371 */       if (par0ItemStack.getItem().isDamageable() || par0ItemStack.getItem().getShareTag())
/*     */       {
/* 373 */         var2 = par0ItemStack.stackTagCompound;
/*     */       }
/*     */       
/* 376 */       writeNBTTagCompound(var2, par1DataOutput);
/*     */       
/* 378 */       if (par0ItemStack.isItemStackDamageable()) {
/* 379 */         writeItemStackDamage(par0ItemStack, par1DataOutput);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPacketSizeOfItemStack(ItemStack item_stack) {
/* 386 */     if (item_stack == null) {
/* 387 */       return 2;
/*     */     }
/* 389 */     if (!item_stack.isItemStackDamageable()) {
/* 390 */       return 6;
/*     */     }
/* 392 */     return 6 + getPacketSizeOfItemStackDamage(item_stack.getMaxDamage());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getPacketSizeOfItemStackDamage(int max_damage) {
/* 398 */     return (max_damage <= 127) ? 1 : ((max_damage <= 32767) ? 2 : 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeItemStackDamage(ItemStack item_stack, DataOutput dos) throws IOException {
/* 404 */     int bytes = getPacketSizeOfItemStackDamage(item_stack.getMaxDamage());
/*     */ 
/*     */ 
/*     */     
/* 408 */     int damage = item_stack.getItemDamage();
/*     */     
/* 410 */     int min_allowed_damage = (bytes == 1) ? -128 : ((bytes == 2) ? -32768 : Integer.MIN_VALUE);
/* 411 */     int max_allowed_damage = (bytes == 1) ? 127 : ((bytes == 2) ? 32767 : Integer.MAX_VALUE);
/*     */     
/* 413 */     if (damage < min_allowed_damage) {
/*     */       
/* 415 */       Minecraft.setErrorMessage("writeItemStack: damage value of " + damage + " is out of range for " + item_stack);
/* 416 */       damage = min_allowed_damage;
/*     */     }
/* 418 */     else if (damage > max_allowed_damage) {
/*     */       
/* 420 */       Minecraft.setErrorMessage("writeItemStack: damage value of " + damage + " is out of range for " + item_stack);
/* 421 */       damage = max_allowed_damage;
/*     */     } 
/*     */     
/* 424 */     if (bytes == 1) {
/* 425 */       dos.writeByte(damage);
/* 426 */     } else if (bytes == 2) {
/* 427 */       dos.writeShort(damage);
/*     */     } else {
/* 429 */       dos.writeInt(damage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int readItemStackDamage(ItemStack item_stack, DataInput dis) throws IOException {
/* 435 */     int bytes = getPacketSizeOfItemStackDamage(item_stack.getMaxDamage());
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (bytes == 1)
/* 440 */       return dis.readByte(); 
/* 441 */     if (bytes == 2) {
/* 442 */       return dis.readShort();
/*     */     }
/* 444 */     return dis.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NBTTagCompound readNBTTagCompound(DataInput par0DataInput) throws IOException {
/* 452 */     short var1 = par0DataInput.readShort();
/*     */     
/* 454 */     if (var1 < 0)
/*     */     {
/* 456 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 460 */     byte[] var2 = new byte[var1];
/* 461 */     par0DataInput.readFully(var2);
/* 462 */     return CompressedStreamTools.decompress(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutput par1DataOutput) throws IOException {
/* 472 */     if (par0NBTTagCompound == null) {
/*     */       
/* 474 */       par1DataOutput.writeShort(-1);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 480 */       byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
/* 481 */       par1DataOutput.writeShort((short)var2.length);
/* 482 */       par1DataOutput.write(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final CompressionResult tryCompress(byte[] input, int compression_level, Packet packet) {
/* 491 */     return tryCompress(input, 0, input.length, compression_level, packet);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final CompressionResult tryCompress(byte[] input, int offset, int length, int compression_level, Packet packet) {
/* 497 */     if (offset >= input.length) {
/*     */       
/* 499 */       Minecraft.setErrorMessage("tryCompress: offset was >= input.length");
/* 500 */       offset = input.length - 1;
/*     */     } 
/*     */     
/* 503 */     if (length > input.length - offset) {
/*     */       
/* 505 */       Minecraft.setErrorMessage("tryCompress: length was greater than input.length - offset");
/* 506 */       length = input.length - offset;
/*     */     } 
/*     */     
/* 509 */     if (compression_level != -1 && (compression_level < 0 || compression_level > 9)) {
/*     */       
/* 511 */       Minecraft.setErrorMessage("tryCompress: invalid compression_level " + compression_level);
/* 512 */       compression_level = -1;
/*     */     } 
/*     */     
/* 515 */     if (compression_level == 0)
/*     */     {
/* 517 */       if (offset == 0 && length == input.length) {
/* 518 */         return new CompressionResult(input, input.length, false);
/*     */       }
/*     */     }
/* 521 */     Deflater deflater = new Deflater(compression_level);
/*     */ 
/*     */     
/*     */     try {
/* 525 */       int size_of_input = length - offset;
/*     */       
/* 527 */       deflater.setInput(input, offset, length);
/* 528 */       deflater.finish();
/*     */       
/* 530 */       byte[] output = new byte[size_of_input];
/*     */       
/* 532 */       int size_of_output = deflater.deflate(output);
/*     */       
/* 534 */       if (size_of_output > 0 && size_of_output < size_of_input)
/*     */       {
/*     */ 
/*     */         
/* 538 */         return new CompressionResult(output, size_of_output, true);
/*     */       }
/* 540 */       if (offset == 0 && length == input.length)
/*     */       {
/* 542 */         return new CompressionResult(input, size_of_input, false);
/*     */       }
/*     */ 
/*     */       
/* 546 */       System.arraycopy(input, offset, output, 0, length);
/* 547 */       return new CompressionResult(output, length, false);
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 552 */       deflater.end();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final DecompressionResult decompress(byte[] input, int expected_output_size, Packet packet) {
/* 559 */     return decompress(input, 0, input.length, expected_output_size, packet);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final DecompressionResult decompress(byte[] input, int offset, int length, int expected_output_size, Packet packet) {
/* 565 */     if (offset >= input.length) {
/*     */       
/* 567 */       Minecraft.setErrorMessage("decompress: offset was >= input.length");
/* 568 */       offset = input.length - 1;
/*     */     } 
/*     */     
/* 571 */     if (length > input.length - offset) {
/*     */       
/* 573 */       Minecraft.setErrorMessage("decompress: length was greater than input.length - offset");
/* 574 */       length = input.length - offset;
/*     */     } 
/*     */     
/* 577 */     if (length >= expected_output_size) {
/* 578 */       Minecraft.setErrorMessage("decompress: length >= expected_output_size?");
/*     */     }
/* 580 */     Inflater inflater = new Inflater();
/*     */ 
/*     */     
/*     */     try {
/* 584 */       inflater.setInput(input, offset, length);
/*     */       
/* 586 */       byte[] arrayOfByte = new byte[expected_output_size];
/*     */       
/* 588 */       int output_size = inflater.inflate(arrayOfByte);
/*     */       
/* 590 */       if (output_size == expected_output_size)
/*     */       {
/*     */ 
/*     */         
/* 594 */         return new DecompressionResult(arrayOfByte, output_size, true);
/*     */       }
/*     */ 
/*     */       
/* 598 */       Minecraft.setErrorMessage("decompress: size_of_bytes_uncompressed discrepency, " + output_size + " vs " + expected_output_size);
/*     */     
/*     */     }
/* 601 */     catch (DataFormatException e) {
/*     */       
/* 603 */       Minecraft.setErrorMessage("decompress: Bad compressed data format");
/*     */     }
/*     */     finally {
/*     */       
/* 607 */       inflater.end();
/*     */     } 
/*     */     
/* 610 */     if (offset == 0 && length == input.length)
/*     */     {
/* 612 */       return new DecompressionResult(input, input.length, false);
/*     */     }
/*     */ 
/*     */     
/* 616 */     byte[] output = new byte[length];
/* 617 */     System.arraycopy(input, offset, output, 0, length);
/*     */     
/* 619 */     return new DecompressionResult(output, length, false);
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
/*     */   public void compressPayload() {}
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
/*     */   public final boolean hasBeenAddedToTcpSendQueue() {
/* 644 */     return this.has_been_added_to_tcp_send_queue;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setHasBeenAddedToTcpSendQueue() {
/* 649 */     this.has_been_added_to_tcp_send_queue = true;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 654 */     addIdClassMapping(0, true, true, Packet0KeepAlive.class);
/* 655 */     addIdClassMapping(1, true, true, Packet1Login.class);
/* 656 */     addIdClassMapping(2, false, true, Packet2ClientProtocol.class);
/* 657 */     addIdClassMapping(3, true, true, Packet3Chat.class);
/* 658 */     addIdClassMapping(4, true, false, Packet4UpdateTime.class);
/*     */     
/* 660 */     addIdClassMapping(5, true, true, Packet5PlayerInventory.class);
/* 661 */     addIdClassMapping(6, true, false, Packet6SpawnPosition.class);
/*     */     
/* 663 */     addIdClassMapping(8, true, false, Packet8UpdateHealth.class);
/* 664 */     addIdClassMapping(9, true, true, Packet9Respawn.class);
/* 665 */     addIdClassMapping(10, true, true, Packet10Flying.class);
/* 666 */     addIdClassMapping(11, true, true, Packet11PlayerPosition.class);
/* 667 */     addIdClassMapping(12, true, true, Packet12PlayerLook.class);
/* 668 */     addIdClassMapping(13, true, true, Packet13PlayerLookMove.class);
/* 669 */     addIdClassMapping(14, false, true, Packet14BlockDig.class);
/* 670 */     addIdClassMapping(15, false, true, Packet15Place.class);
/* 671 */     addIdClassMapping(16, true, true, Packet16BlockItemSwitch.class);
/* 672 */     addIdClassMapping(17, true, false, Packet17Sleep.class);
/* 673 */     addIdClassMapping(18, true, true, Packet18Animation.class);
/* 674 */     addIdClassMapping(19, false, true, Packet19EntityAction.class);
/* 675 */     addIdClassMapping(20, true, false, Packet20NamedEntitySpawn.class);
/* 676 */     addIdClassMapping(22, true, false, Packet22Collect.class);
/* 677 */     addIdClassMapping(23, true, false, Packet23VehicleSpawn.class);
/* 678 */     addIdClassMapping(24, true, false, Packet24MobSpawn.class);
/* 679 */     addIdClassMapping(25, true, false, Packet25EntityPainting.class);
/* 680 */     addIdClassMapping(26, true, false, Packet26EntityExpOrb.class);
/* 681 */     addIdClassMapping(27, false, true, Packet27PlayerInput.class);
/* 682 */     addIdClassMapping(28, true, false, Packet28EntityVelocity.class);
/* 683 */     addIdClassMapping(29, true, false, Packet29DestroyEntity.class);
/* 684 */     addIdClassMapping(30, true, false, Packet30Entity.class);
/* 685 */     addIdClassMapping(31, true, false, Packet31RelEntityMove.class);
/* 686 */     addIdClassMapping(32, true, false, Packet32EntityLook.class);
/* 687 */     addIdClassMapping(33, true, false, Packet33RelEntityMoveLook.class);
/*     */     
/* 689 */     addIdClassMapping(34, true, true, Packet34EntityTeleport.class);
/* 690 */     addIdClassMapping(35, true, false, Packet35EntityHeadRotation.class);
/* 691 */     addIdClassMapping(38, true, false, Packet38EntityStatus.class);
/* 692 */     addIdClassMapping(39, true, false, Packet39AttachEntity.class);
/* 693 */     addIdClassMapping(40, true, false, Packet40EntityMetadata.class);
/* 694 */     addIdClassMapping(41, true, false, Packet41EntityEffect.class);
/* 695 */     addIdClassMapping(42, true, false, Packet42RemoveEntityEffect.class);
/* 696 */     addIdClassMapping(43, true, false, Packet43Experience.class);
/* 697 */     addIdClassMapping(44, true, false, Packet44UpdateAttributes.class);
/* 698 */     addIdClassMapping(51, true, false, Packet51MapChunk.class);
/* 699 */     addIdClassMapping(52, true, false, Packet52MultiBlockChange.class);
/* 700 */     addIdClassMapping(53, true, false, Packet53BlockChange.class);
/* 701 */     addIdClassMapping(54, true, false, Packet54PlayNoteBlock.class);
/*     */     
/* 703 */     addIdClassMapping(55, true, true, Packet55BlockDestroy.class);
/* 704 */     addIdClassMapping(56, true, false, Packet56MapChunks.class);
/* 705 */     addIdClassMapping(60, true, false, Packet60Explosion.class);
/* 706 */     addIdClassMapping(61, true, false, Packet61DoorChange.class);
/* 707 */     addIdClassMapping(62, true, false, Packet62LevelSound.class);
/*     */     
/* 709 */     addIdClassMapping(70, true, false, Packet70GameEvent.class);
/* 710 */     addIdClassMapping(71, true, false, Packet71Weather.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 717 */     addIdClassMapping(80, true, false, Packet80LongDistanceSound.class);
/*     */     
/* 719 */     addIdClassMapping(81, false, true, Packet81RightClick.class);
/* 720 */     addIdClassMapping(82, false, true, Packet82AddHunger.class);
/* 721 */     addIdClassMapping(83, true, false, Packet83EntityTeleportCompact.class);
/* 722 */     addIdClassMapping(84, true, false, Packet84EntityStateWithData.class);
/* 723 */     addIdClassMapping(85, true, true, Packet85SimpleSignal.class);
/* 724 */     addIdClassMapping(86, true, false, Packet86EntityTeleportWithMotion.class);
/* 725 */     addIdClassMapping(87, false, true, Packet87SetDespawnCounters.class);
/* 726 */     addIdClassMapping(88, true, false, Packet88UpdateStrongboxOwner.class);
/* 727 */     addIdClassMapping(89, false, true, Packet89PlaySoundOnServerAtEntity.class);
/* 728 */     addIdClassMapping(90, false, true, Packet90BroadcastToAssociatedPlayers.class);
/* 729 */     addIdClassMapping(91, true, false, Packet91PlayerStat.class);
/* 730 */     addIdClassMapping(92, true, false, Packet92UpdateTimeSmall.class);
/* 731 */     addIdClassMapping(93, true, false, Packet93WorldAchievement.class);
/* 732 */     addIdClassMapping(94, true, false, Packet94CreateFile.class);
/*     */ 
/*     */     
/* 735 */     addIdClassMapping(97, true, false, Packet97MultiBlockChange.class);
/*     */ 
/*     */ 
/*     */     
/* 739 */     addIdClassMapping(100, true, false, Packet100OpenWindow.class);
/* 740 */     addIdClassMapping(101, true, true, Packet101CloseWindow.class);
/* 741 */     addIdClassMapping(102, false, true, Packet102WindowClick.class);
/* 742 */     addIdClassMapping(103, true, false, Packet103SetSlot.class);
/* 743 */     addIdClassMapping(104, true, false, Packet104WindowItems.class);
/* 744 */     addIdClassMapping(105, true, false, Packet105UpdateProgressbar.class);
/* 745 */     addIdClassMapping(106, true, true, Packet106Transaction.class);
/* 746 */     addIdClassMapping(107, true, true, Packet107CreativeSetSlot.class);
/* 747 */     addIdClassMapping(108, false, true, Packet108EnchantItem.class);
/* 748 */     addIdClassMapping(130, true, true, Packet130UpdateSign.class);
/* 749 */     addIdClassMapping(131, true, false, Packet131MapData.class);
/* 750 */     addIdClassMapping(132, true, false, Packet132TileEntityData.class);
/* 751 */     addIdClassMapping(133, true, false, Packet133TileEditorOpen.class);
/* 752 */     addIdClassMapping(200, true, false, Packet200Statistic.class);
/* 753 */     addIdClassMapping(201, true, false, Packet201PlayerInfo.class);
/* 754 */     addIdClassMapping(202, true, true, Packet202PlayerAbilities.class);
/* 755 */     addIdClassMapping(203, true, true, Packet203AutoComplete.class);
/* 756 */     addIdClassMapping(204, false, true, Packet204ClientInfo.class);
/* 757 */     addIdClassMapping(205, false, true, Packet205ClientCommand.class);
/* 758 */     addIdClassMapping(206, true, false, Packet206SetObjective.class);
/* 759 */     addIdClassMapping(207, true, false, Packet207SetScore.class);
/* 760 */     addIdClassMapping(208, true, false, Packet208SetDisplayObjective.class);
/* 761 */     addIdClassMapping(209, true, false, Packet209SetPlayerTeam.class);
/* 762 */     addIdClassMapping(250, true, true, Packet250CustomPayload.class);
/* 763 */     addIdClassMapping(252, true, true, Packet252SharedKey.class);
/* 764 */     addIdClassMapping(253, true, false, Packet253ServerAuthData.class);
/* 765 */     addIdClassMapping(254, false, true, Packet254ServerPing.class);
/* 766 */     addIdClassMapping(255, true, true, Packet255KickDisconnect.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */