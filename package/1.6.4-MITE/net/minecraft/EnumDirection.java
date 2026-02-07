/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumDirection
/*     */ {
/*   8 */   UP(0, 1, 0),
/*     */   
/*  10 */   DOWN(0, -1, 0),
/*     */   
/*  12 */   SOUTH(0, 0, 1),
/*     */   
/*  14 */   NORTH(0, 0, -1),
/*     */   
/*  16 */   EAST(1, 0, 0),
/*     */   
/*  18 */   WEST(-1, 0, 0);
/*     */   final int dx;
/*     */   final int dy;
/*     */   final int dz;
/*     */   
/*     */   EnumDirection(int dx, int dy, int dz) {
/*  24 */     this.dx = dx;
/*  25 */     this.dy = dy;
/*  26 */     this.dz = dz;
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumDirection get(int ordinal) {
/*  31 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumDirection getDirectionFromYaw(float yaw) {
/*  36 */     int direction = MathHelper.floor_double((yaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*     */     
/*  38 */     if (direction == 0)
/*  39 */       return SOUTH; 
/*  40 */     if (direction == 1)
/*  41 */       return WEST; 
/*  42 */     if (direction == 2)
/*  43 */       return NORTH; 
/*  44 */     if (direction == 3) {
/*  45 */       return EAST;
/*     */     }
/*  47 */     Minecraft.setErrorMessage("getDirectionFromYaw: invalid result");
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   static float getNormalizedYaw(float yaw) {
/*  53 */     while (yaw < 0.0F) {
/*  54 */       yaw += 360.0F;
/*     */     }
/*  56 */     while (yaw >= 360.0F) {
/*  57 */       yaw -= 360.0F;
/*     */     }
/*  59 */     return yaw;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean doesYawHaveNorthComponent(float yaw) {
/*  65 */     yaw = getNormalizedYaw(yaw);
/*  66 */     return (yaw > 90.0F && yaw < 270.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean doesYawHaveSouthComponent(float yaw) {
/*  72 */     yaw = getNormalizedYaw(yaw);
/*  73 */     return (yaw > 270.0F || yaw < 90.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean doesYawHaveWestComponent(float yaw) {
/*  79 */     yaw = getNormalizedYaw(yaw);
/*  80 */     return (yaw > 0.0F && yaw < 180.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean doesYawHaveEastComponent(float yaw) {
/*  86 */     yaw = getNormalizedYaw(yaw);
/*  87 */     return (yaw > 180.0F && yaw < 360.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumDirection getDirectionFromPitch(float pitch) {
/*  92 */     return (pitch < 0.0F) ? UP : DOWN;
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isUp() {
/*  97 */     return (this == UP);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isDown() {
/* 102 */     return (this == DOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isSouth() {
/* 107 */     return (this == SOUTH);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isNorth() {
/* 112 */     return (this == NORTH);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isEast() {
/* 117 */     return (this == EAST);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isWest() {
/* 122 */     return (this == WEST);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isUpOrDown() {
/* 127 */     return (this == UP || this == DOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isHorizontal() {
/* 132 */     return (this == SOUTH || this == NORTH || this == EAST || this == WEST);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isNorthOrSouth() {
/* 137 */     return (this == NORTH || this == SOUTH);
/*     */   }
/*     */ 
/*     */   
/*     */   final boolean isEastOrWest() {
/* 142 */     return (this == EAST || this == WEST);
/*     */   }
/*     */ 
/*     */   
/*     */   final EnumDirection getOpposite() {
/* 147 */     if (this == UP)
/* 148 */       return DOWN; 
/* 149 */     if (this == DOWN)
/* 150 */       return UP; 
/* 151 */     if (this == SOUTH)
/* 152 */       return NORTH; 
/* 153 */     if (this == NORTH)
/* 154 */       return SOUTH; 
/* 155 */     if (this == EAST) {
/* 156 */       return WEST;
/*     */     }
/* 158 */     return EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   final EnumFace getFace() {
/* 163 */     if (this == UP)
/* 164 */       return EnumFace.TOP; 
/* 165 */     if (this == DOWN)
/* 166 */       return EnumFace.BOTTOM; 
/* 167 */     if (this == SOUTH)
/* 168 */       return EnumFace.SOUTH; 
/* 169 */     if (this == NORTH)
/* 170 */       return EnumFace.NORTH; 
/* 171 */     if (this == EAST) {
/* 172 */       return EnumFace.EAST;
/*     */     }
/* 174 */     return EnumFace.WEST;
/*     */   }
/*     */ 
/*     */   
/*     */   final EnumFace getOppositeFace() {
/* 179 */     if (this == UP)
/* 180 */       return EnumFace.BOTTOM; 
/* 181 */     if (this == DOWN)
/* 182 */       return EnumFace.TOP; 
/* 183 */     if (this == SOUTH)
/* 184 */       return EnumFace.NORTH; 
/* 185 */     if (this == NORTH)
/* 186 */       return EnumFace.SOUTH; 
/* 187 */     if (this == EAST) {
/* 188 */       return EnumFace.WEST;
/*     */     }
/* 190 */     return EnumFace.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNeighborX(int x) {
/* 195 */     return (this == WEST) ? (x - 1) : ((this == EAST) ? (x + 1) : x);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNeighborY(int y) {
/* 200 */     return (this == DOWN) ? (y - 1) : ((this == UP) ? (y + 1) : y);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNeighborZ(int z) {
/* 205 */     return (this == NORTH) ? (z - 1) : ((this == SOUTH) ? (z + 1) : z);
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection adjustForCoordBaseMode(int coord_base_mode) {
/* 210 */     if (coord_base_mode == 0)
/* 211 */       return isNorthOrSouth() ? getOpposite() : this; 
/* 212 */     if (coord_base_mode == 1)
/* 213 */       return isNorth() ? WEST : (isSouth() ? EAST : (isWest() ? NORTH : (isEast() ? SOUTH : this))); 
/* 214 */     if (coord_base_mode == 2)
/* 215 */       return this; 
/* 216 */     if (coord_base_mode == 3) {
/* 217 */       return isNorth() ? EAST : (isSouth() ? WEST : (isWest() ? NORTH : (isEast() ? SOUTH : this)));
/*     */     }
/* 219 */     Minecraft.setErrorMessage("adjustForCoordBaseMode: invalid coord_base_mode " + coord_base_mode);
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescriptor(boolean capitalized) {
/* 225 */     return capitalized ? StringHelper.capitalize(name().toLowerCase()) : name().toLowerCase();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isAlignedWith(Axis axis) {
/* 230 */     return isUpOrDown() ? axis.isUpDown() : (isNorthOrSouth() ? axis.isNorthSouth() : axis.isEastWest());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumDirection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */