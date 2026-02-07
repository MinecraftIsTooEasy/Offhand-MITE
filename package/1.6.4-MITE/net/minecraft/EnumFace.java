/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumFace
/*     */ {
/*   7 */   BOTTOM,
/*   8 */   TOP,
/*   9 */   NORTH,
/*  10 */   SOUTH,
/*  11 */   WEST,
/*  12 */   EAST;
/*     */ 
/*     */   
/*     */   public static boolean isValidOrdinal(int ordinal) {
/*  16 */     return (ordinal >= 0 && ordinal < (values()).length);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFace get(int ordinal) {
/*  21 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBottom() {
/*  26 */     return (this == BOTTOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTop() {
/*  31 */     return (this == TOP);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNorth() {
/*  36 */     return (this == NORTH);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSouth() {
/*  41 */     return (this == SOUTH);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWest() {
/*  46 */     return (this == WEST);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEast() {
/*  51 */     return (this == EAST);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTopOrBottom() {
/*  56 */     return (this == TOP || this == BOTTOM);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSide() {
/*  62 */     return (this == NORTH || this == SOUTH || this == WEST || this == EAST);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNorthOrSouth() {
/*  67 */     return (this == NORTH || this == SOUTH);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEastOrWest() {
/*  72 */     return (this == EAST || this == WEST);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNeighborX(int x) {
/*  77 */     return (this == WEST) ? (x - 1) : ((this == EAST) ? (x + 1) : x);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNeighborY(int y) {
/*  82 */     return (this == BOTTOM) ? (y - 1) : ((this == TOP) ? (y + 1) : y);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNeighborZ(int z) {
/*  87 */     return (this == NORTH) ? (z - 1) : ((this == SOUTH) ? (z + 1) : z);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFace getOpposite() {
/*  92 */     if (this == BOTTOM)
/*  93 */       return TOP; 
/*  94 */     if (this == TOP)
/*  95 */       return BOTTOM; 
/*  96 */     if (this == NORTH)
/*  97 */       return SOUTH; 
/*  98 */     if (this == SOUTH)
/*  99 */       return NORTH; 
/* 100 */     if (this == WEST) {
/* 101 */       return EAST;
/*     */     }
/* 103 */     return WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getNormal() {
/* 108 */     if (this == BOTTOM)
/* 109 */       return EnumDirection.DOWN; 
/* 110 */     if (this == TOP)
/* 111 */       return EnumDirection.UP; 
/* 112 */     if (this == NORTH)
/* 113 */       return EnumDirection.NORTH; 
/* 114 */     if (this == SOUTH)
/* 115 */       return EnumDirection.SOUTH; 
/* 116 */     if (this == WEST) {
/* 117 */       return EnumDirection.WEST;
/*     */     }
/* 119 */     return EnumDirection.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDirection getAntiNormal() {
/* 124 */     if (this == BOTTOM)
/* 125 */       return EnumDirection.UP; 
/* 126 */     if (this == TOP)
/* 127 */       return EnumDirection.DOWN; 
/* 128 */     if (this == NORTH)
/* 129 */       return EnumDirection.SOUTH; 
/* 130 */     if (this == SOUTH)
/* 131 */       return EnumDirection.NORTH; 
/* 132 */     if (this == WEST) {
/* 133 */       return EnumDirection.EAST;
/*     */     }
/* 135 */     return EnumDirection.WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescriptor(boolean capitalized) {
/* 140 */     return capitalized ? StringHelper.capitalize(name().toLowerCase()) : name().toLowerCase();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */