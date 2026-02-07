/*     */ package net.minecraft;
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
/*     */ public class ItemSlab
/*     */   extends ItemBlock
/*     */ {
/*     */   private final boolean isFullBlock;
/*     */   private final BlockSlab theHalfSlab;
/*     */   private final BlockDoubleSlab doubleSlab;
/*     */   
/*     */   public ItemSlab(BlockSlab half_slab, BlockDoubleSlab double_slab, boolean is_full_block) {
/*  41 */     super(is_full_block ? double_slab : half_slab);
/*  42 */     this.theHalfSlab = half_slab;
/*  43 */     this.doubleSlab = double_slab;
/*  44 */     this.isFullBlock = is_full_block;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/*  52 */     return Block.blocksList[this.itemID].getIcon(2, par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata(int par1) {
/*  60 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/*  69 */     if (par1ItemStack == null) {
/*  70 */       return getUnlocalizedName();
/*     */     }
/*  72 */     return this.theHalfSlab.getFullSlabName(par1ItemStack.getItemSubtype());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player, ItemStack item_stack) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield isFullBlock : Z
/*     */     //   4: ifeq -> 17
/*     */     //   7: aload_0
/*     */     //   8: aload_1
/*     */     //   9: aload_2
/*     */     //   10: aload_3
/*     */     //   11: aload #4
/*     */     //   13: invokespecial tryPlaceAsBlock : (Lnet/minecraft/RaycastCollision;Lnet/minecraft/Block;Lnet/minecraft/EntityPlayer;Lnet/minecraft/ItemStack;)Z
/*     */     //   16: ireturn
/*     */     //   17: aload_1
/*     */     //   18: invokevirtual isBlock : ()Z
/*     */     //   21: ifne -> 31
/*     */     //   24: ldc 'tryPlaceAsBlock: raycast collision is not block'
/*     */     //   26: invokestatic setErrorMessage : (Ljava/lang/String;)V
/*     */     //   29: iconst_0
/*     */     //   30: ireturn
/*     */     //   31: aload_1
/*     */     //   32: getfield world : Lnet/minecraft/World;
/*     */     //   35: astore #5
/*     */     //   37: iconst_0
/*     */     //   38: istore #9
/*     */     //   40: iconst_0
/*     */     //   41: istore #10
/*     */     //   43: aload_1
/*     */     //   44: getfield block_hit_x : I
/*     */     //   47: istore #6
/*     */     //   49: aload_1
/*     */     //   50: getfield block_hit_y : I
/*     */     //   53: istore #7
/*     */     //   55: aload_1
/*     */     //   56: getfield block_hit_z : I
/*     */     //   59: istore #8
/*     */     //   61: aload_1
/*     */     //   62: invokevirtual getBlockHit : ()Lnet/minecraft/Block;
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual getTheHalfSlab : ()Lnet/minecraft/BlockSlab;
/*     */     //   69: if_acmpne -> 153
/*     */     //   72: aload_1
/*     */     //   73: invokevirtual getBlockHit : ()Lnet/minecraft/Block;
/*     */     //   76: checkcast net/minecraft/BlockSlab
/*     */     //   79: astore #11
/*     */     //   81: aload #11
/*     */     //   83: aload_1
/*     */     //   84: getfield block_hit_metadata : I
/*     */     //   87: invokevirtual getItemSubtype : (I)I
/*     */     //   90: aload #4
/*     */     //   92: invokevirtual getItemSubtype : ()I
/*     */     //   95: if_icmpne -> 153
/*     */     //   98: aload_1
/*     */     //   99: getfield face_hit : Lnet/minecraft/EnumFace;
/*     */     //   102: invokevirtual isTop : ()Z
/*     */     //   105: ifeq -> 127
/*     */     //   108: aload #11
/*     */     //   110: pop
/*     */     //   111: aload_1
/*     */     //   112: getfield block_hit_metadata : I
/*     */     //   115: invokestatic isBottom : (I)Z
/*     */     //   118: ifeq -> 127
/*     */     //   121: iconst_1
/*     */     //   122: istore #9
/*     */     //   124: goto -> 153
/*     */     //   127: aload_1
/*     */     //   128: getfield face_hit : Lnet/minecraft/EnumFace;
/*     */     //   131: invokevirtual isBottom : ()Z
/*     */     //   134: ifeq -> 153
/*     */     //   137: aload #11
/*     */     //   139: pop
/*     */     //   140: aload_1
/*     */     //   141: getfield block_hit_metadata : I
/*     */     //   144: invokestatic isTop : (I)Z
/*     */     //   147: ifeq -> 153
/*     */     //   150: iconst_1
/*     */     //   151: istore #9
/*     */     //   153: iload #9
/*     */     //   155: ifne -> 282
/*     */     //   158: aload_1
/*     */     //   159: invokevirtual getNeighborOfBlockHit : ()Lnet/minecraft/Block;
/*     */     //   162: aload_0
/*     */     //   163: invokevirtual getTheHalfSlab : ()Lnet/minecraft/BlockSlab;
/*     */     //   166: if_acmpne -> 282
/*     */     //   169: aload_1
/*     */     //   170: invokevirtual getNeighborOfBlockHit : ()Lnet/minecraft/Block;
/*     */     //   173: checkcast net/minecraft/BlockSlab
/*     */     //   176: astore #11
/*     */     //   178: aload_1
/*     */     //   179: invokevirtual getNeighborOfBlockHitMetadata : ()I
/*     */     //   182: istore #12
/*     */     //   184: aload #11
/*     */     //   186: iload #12
/*     */     //   188: invokevirtual getItemSubtype : (I)I
/*     */     //   191: aload #4
/*     */     //   193: invokevirtual getItemSubtype : ()I
/*     */     //   196: if_icmpne -> 282
/*     */     //   199: aload #11
/*     */     //   201: pop
/*     */     //   202: iload #12
/*     */     //   204: invokestatic isBottom : (I)Z
/*     */     //   207: ifeq -> 236
/*     */     //   210: aload_1
/*     */     //   211: getfield face_hit : Lnet/minecraft/EnumFace;
/*     */     //   214: invokevirtual isBottom : ()Z
/*     */     //   217: ifne -> 230
/*     */     //   220: aload_1
/*     */     //   221: getfield block_hit_offset_y : F
/*     */     //   224: ldc 0.5
/*     */     //   226: fcmpl
/*     */     //   227: ifle -> 259
/*     */     //   230: iconst_1
/*     */     //   231: istore #10
/*     */     //   233: goto -> 259
/*     */     //   236: aload_1
/*     */     //   237: getfield face_hit : Lnet/minecraft/EnumFace;
/*     */     //   240: invokevirtual isTop : ()Z
/*     */     //   243: ifne -> 256
/*     */     //   246: aload_1
/*     */     //   247: getfield block_hit_offset_y : F
/*     */     //   250: ldc 0.5
/*     */     //   252: fcmpg
/*     */     //   253: ifge -> 259
/*     */     //   256: iconst_1
/*     */     //   257: istore #10
/*     */     //   259: iload #10
/*     */     //   261: ifeq -> 282
/*     */     //   264: aload_1
/*     */     //   265: getfield neighbor_block_x : I
/*     */     //   268: istore #6
/*     */     //   270: aload_1
/*     */     //   271: getfield neighbor_block_y : I
/*     */     //   274: istore #7
/*     */     //   276: aload_1
/*     */     //   277: getfield neighbor_block_z : I
/*     */     //   280: istore #8
/*     */     //   282: iload #9
/*     */     //   284: ifne -> 292
/*     */     //   287: iload #10
/*     */     //   289: ifeq -> 327
/*     */     //   292: aload_0
/*     */     //   293: invokevirtual getTheDoubleSlab : ()Lnet/minecraft/BlockDoubleSlab;
/*     */     //   296: iload #6
/*     */     //   298: iload #7
/*     */     //   300: iload #8
/*     */     //   302: aload_1
/*     */     //   303: getfield face_hit : Lnet/minecraft/EnumFace;
/*     */     //   306: aload #4
/*     */     //   308: aload_3
/*     */     //   309: aload_1
/*     */     //   310: getfield block_hit_offset_x : F
/*     */     //   313: aload_1
/*     */     //   314: getfield block_hit_offset_y : F
/*     */     //   317: aload_1
/*     */     //   318: getfield block_hit_offset_z : F
/*     */     //   321: iconst_0
/*     */     //   322: iconst_0
/*     */     //   323: invokevirtual tryPlaceFromHeldItem : (IIILnet/minecraft/EnumFace;Lnet/minecraft/ItemStack;Lnet/minecraft/EntityPlayer;FFFZZ)Z
/*     */     //   326: ireturn
/*     */     //   327: aload_0
/*     */     //   328: aload_1
/*     */     //   329: aload_2
/*     */     //   330: aload_3
/*     */     //   331: aload #4
/*     */     //   333: invokespecial tryPlaceAsBlock : (Lnet/minecraft/RaycastCollision;Lnet/minecraft/Block;Lnet/minecraft/EntityPlayer;Lnet/minecraft/ItemStack;)Z
/*     */     //   336: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #125	-> 0
/*     */     //   #126	-> 7
/*     */     //   #128	-> 17
/*     */     //   #130	-> 24
/*     */     //   #131	-> 29
/*     */     //   #134	-> 31
/*     */     //   #138	-> 37
/*     */     //   #139	-> 40
/*     */     //   #141	-> 43
/*     */     //   #142	-> 49
/*     */     //   #143	-> 55
/*     */     //   #145	-> 61
/*     */     //   #147	-> 72
/*     */     //   #149	-> 81
/*     */     //   #151	-> 98
/*     */     //   #152	-> 121
/*     */     //   #153	-> 127
/*     */     //   #154	-> 150
/*     */     //   #158	-> 153
/*     */     //   #160	-> 169
/*     */     //   #162	-> 178
/*     */     //   #164	-> 184
/*     */     //   #166	-> 199
/*     */     //   #168	-> 210
/*     */     //   #169	-> 230
/*     */     //   #173	-> 236
/*     */     //   #174	-> 256
/*     */     //   #177	-> 259
/*     */     //   #179	-> 264
/*     */     //   #180	-> 270
/*     */     //   #181	-> 276
/*     */     //   #186	-> 282
/*     */     //   #187	-> 292
/*     */     //   #189	-> 327
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   81	72	11	block_half_slab	Lnet/minecraft/BlockSlab;
/*     */     //   178	104	11	block_half_slab	Lnet/minecraft/BlockSlab;
/*     */     //   184	98	12	metadata_of_neighbor	I
/*     */     //   0	337	0	this	Lnet/minecraft/ItemSlab;
/*     */     //   0	337	1	rc	Lnet/minecraft/RaycastCollision;
/*     */     //   0	337	2	block	Lnet/minecraft/Block;
/*     */     //   0	337	3	player	Lnet/minecraft/EntityPlayer;
/*     */     //   0	337	4	item_stack	Lnet/minecraft/ItemStack;
/*     */     //   37	300	5	world	Lnet/minecraft/World;
/*     */     //   49	288	6	x	I
/*     */     //   55	282	7	y	I
/*     */     //   61	276	8	z	I
/*     */     //   40	297	9	modifies_block_hit	Z
/*     */     //   43	294	10	modifies_neighbor_of_block_hit	Z
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockSlab getTheHalfSlab() {
/* 303 */     return this.theHalfSlab;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockDoubleSlab getTheDoubleSlab() {
/* 308 */     return this.doubleSlab;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullBlock() {
/* 313 */     return this.isFullBlock;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */