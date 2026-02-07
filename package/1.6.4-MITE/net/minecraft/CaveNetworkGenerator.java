/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CaveNetworkGenerator
/*     */ {
/*     */   private static boolean use_optimized_code = true;
/*     */   private final CaveNetworkStub stub;
/*     */   private final int size_x;
/*     */   private final int size_z;
/*     */   private final int size_y;
/*     */   private final int volume;
/*     */   private final byte[] cells;
/*     */   private final byte[] cells_copy;
/*     */   private final byte[] wall_mask;
/*     */   private final Random rand;
/*     */   private final int size_xz;
/*     */   private static final int CELL_AIR = 0;
/*     */   private static final int CELL_WALL = 1;
/*     */   private final boolean has_mycelium;
/*     */   private boolean has_raised_shell_floor;
/*     */   
/*     */   public CaveNetworkGenerator(CaveNetworkStub stub) {
/*  35 */     this.stub = stub;
/*     */     
/*  37 */     this.size_x = stub.getSizeX();
/*  38 */     this.size_y = stub.getSizeY();
/*  39 */     this.size_z = stub.getSizeZ();
/*     */     
/*  41 */     this.volume = this.size_x * this.size_y * this.size_z;
/*     */     
/*  43 */     this.cells = new byte[this.volume];
/*  44 */     this.cells_copy = new byte[this.cells.length];
/*     */     
/*  46 */     this.wall_mask = new byte[this.cells.length];
/*     */     
/*  48 */     if (stub.hasLegacySeed()) {
/*     */       
/*  50 */       this.rand = new Random(stub.getLegacySeed());
/*     */       
/*  52 */       this.rand.nextInt(200);
/*  53 */       this.rand.nextInt(2);
/*  54 */       this.rand.nextLong();
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  60 */       this.rand = new Random(stub.getSeed());
/*  61 */       this.rand.nextInt();
/*     */     } 
/*     */     
/*  64 */     this.size_xz = this.size_x * this.size_z;
/*     */     
/*  66 */     this.has_mycelium = stub.hasMycelium();
/*     */     
/*  68 */     generate();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOriginChunkX() {
/*  73 */     return this.stub.getOriginChunkX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOriginChunkZ() {
/*  78 */     return this.stub.getOriginChunkZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public void fill(int value) {
/*  83 */     Arrays.fill(this.cells, (byte)value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fill(int value, float percentage) {
/*  88 */     for (int i = 0; i < this.cells.length; i++) {
/*     */       
/*  90 */       if (this.rand.nextFloat() < percentage) {
/*  91 */         this.cells[i] = (byte)value;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fillRandomSubVolumeWithBias(float bias, float min_volume_fraction, float max_volume_fraction) {
/*     */     int sub_size_x, sub_size_y, sub_size_z;
/*     */     float volume_fraction;
/*     */     do {
/* 106 */       sub_size_x = this.rand.nextInt(this.size_x) + 1;
/* 107 */       sub_size_y = this.rand.nextInt(this.size_y) + 1;
/* 108 */       sub_size_z = this.rand.nextInt(this.size_z) + 1;
/*     */       
/* 110 */       volume_fraction = (sub_size_x * sub_size_y * sub_size_z) / this.volume;
/*     */     }
/* 112 */     while (volume_fraction < min_volume_fraction || volume_fraction > max_volume_fraction);
/*     */     
/* 114 */     int x = this.rand.nextBoolean() ? 0 : (this.size_x - sub_size_x);
/* 115 */     int y = this.rand.nextBoolean() ? 0 : (this.size_y - sub_size_y);
/* 116 */     int z = this.rand.nextBoolean() ? 0 : (this.size_z - sub_size_z);
/*     */     
/* 118 */     for (int dx = 0; dx < sub_size_x; dx++) {
/*     */       
/* 120 */       for (int dz = 0; dz < sub_size_z; dz++) {
/*     */         
/* 122 */         for (int dy = 0; dy < sub_size_y; dy++) {
/* 123 */           setValueAt(x + dx, y + dy, z + dz, (this.rand.nextFloat() < bias) ? 0 : 1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillBottomHalfWithAirBias() {
/* 130 */     int x = 0;
/* 131 */     int y = 0;
/* 132 */     int z = 0;
/*     */     
/* 134 */     float bias = 0.51F + this.rand.nextFloat() * 0.01F;
/*     */     
/* 136 */     for (int dx = 0; dx < this.size_x; dx++) {
/*     */       
/* 138 */       for (int dz = 0; dz < this.size_z; dz++) {
/*     */         
/* 140 */         for (int dy = 0; dy < this.size_y / 2; dy++) {
/* 141 */           setValueAt(x + dx, y + dy, z + dz, (this.rand.nextFloat() < bias) ? 0 : 1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void raiseFloor() {
/* 149 */     int amount = this.rand.nextInt(this.size_y / 2 + 1);
/*     */     
/* 151 */     for (int x = 0; x < this.size_x; x++) {
/*     */       
/* 153 */       for (int z = 0; z < this.size_z; z++) {
/*     */         
/* 155 */         for (int y = 0; y < amount; y++) {
/* 156 */           setValueAt(x, y, z, (this.rand.nextFloat() < 0.47F) ? 0 : 1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void lowerCeiling() {
/* 164 */     int amount = this.rand.nextInt(this.size_y / 2 + 1);
/*     */     
/* 166 */     for (int x = 0; x < this.size_x; x++) {
/*     */       
/* 168 */       for (int z = 0; z < this.size_z; z++) {
/*     */         
/* 170 */         for (int y = this.size_y - amount; y < this.size_y; y++) {
/* 171 */           setValueAt(x, y, z, (this.rand.nextFloat() < 0.47F) ? 0 : 1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateInterior(int smoothening) {
/* 179 */     fill(1);
/* 180 */     fill(0, 0.5F);
/*     */     
/* 182 */     if (!this.stub.hasLegacySeed() && this.rand.nextBoolean()) {
/* 183 */       fillRandomSubVolumeWithBias(0.51F, 0.0F, 0.5F);
/*     */     }
/* 185 */     if (!this.stub.hasLegacySeed() && this.rand.nextBoolean()) {
/* 186 */       fillRandomSubVolumeWithBias(0.49F, 0.0F, 0.5F);
/*     */     }
/* 188 */     if (this.has_mycelium) {
/*     */       
/* 190 */       if (!this.stub.hasLegacySeed() && this.rand.nextBoolean()) {
/* 191 */         lowerCeiling();
/*     */       }
/* 193 */       fillBottomHalfWithAirBias();
/*     */     }
/*     */     else {
/*     */       
/* 197 */       if (this.rand.nextBoolean()) {
/* 198 */         fillRandomSubVolumeWithBias(0.48F, 0.0F, 0.5F);
/*     */       }
/* 200 */       if (this.rand.nextFloat() < 0.8F) {
/*     */         
/* 202 */         if (this.rand.nextBoolean()) {
/* 203 */           raiseFloor();
/*     */         } else {
/* 205 */           lowerCeiling();
/*     */         } 
/* 207 */       } else if (this.rand.nextBoolean()) {
/*     */         
/* 209 */         fillBottomHalfWithAirBias();
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     smoothen(smoothening);
/*     */   }
/*     */ 
/*     */   
/*     */   private void excavate(int iterations) {
/* 218 */     if (use_optimized_code) {
/*     */       
/* 220 */       excavate_optimized(iterations);
/*     */       
/*     */       return;
/*     */     } 
/* 224 */     for (int i = 0; i < iterations; i++) {
/*     */       
/* 226 */       System.arraycopy(this.cells, 0, this.cells_copy, 0, this.cells.length);
/*     */       
/* 228 */       for (int x = 1; x < this.size_x - 1; x++) {
/*     */         
/* 230 */         for (int z = 1; z < this.size_z - 1; z++) {
/*     */           
/* 232 */           for (int y = 1; y < this.size_y - 1; y++) {
/*     */             
/* 234 */             if (this.cells_copy[getIndex(x, y, z)] == 0) {
/*     */               
/* 236 */               int ran = this.rand.nextInt(6);
/*     */               
/* 238 */               if (ran == 0) {
/* 239 */                 setValueAt(x, y - 1, z, 0);
/* 240 */               } else if (ran == 1) {
/* 241 */                 setValueAt(x, y + 1, z, 0);
/* 242 */               } else if (ran == 2) {
/* 243 */                 setValueAt(x - 1, y, z, 0);
/* 244 */               } else if (ran == 3) {
/* 245 */                 setValueAt(x + 1, y, z, 0);
/* 246 */               } else if (ran == 4) {
/* 247 */                 setValueAt(x, y, z - 1, 0);
/*     */               } else {
/* 249 */                 setValueAt(x, y, z + 1, 0);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void excavate_optimized(int iterations) {
/* 259 */     for (int i = 0; i < iterations; i++) {
/*     */       
/* 261 */       System.arraycopy(this.cells, 0, this.cells_copy, 0, this.cells.length);
/*     */       
/* 263 */       for (int x = 1; x < this.size_x - 1; x++) {
/*     */         
/* 265 */         for (int z = 1; z < this.size_z - 1; z++) {
/*     */           
/* 267 */           for (int y = 1; y < this.size_y - 1; y++) {
/*     */             
/* 269 */             int index = x + z * this.size_x + y * this.size_xz;
/*     */             
/* 271 */             if (this.cells_copy[index] == 0) {
/*     */               
/* 273 */               int ran = this.rand.nextInt(6);
/*     */               
/* 275 */               if (ran == 0) {
/* 276 */                 this.cells[index - this.size_xz] = 0;
/* 277 */               } else if (ran == 1) {
/* 278 */                 this.cells[index + this.size_xz] = 0;
/* 279 */               } else if (ran == 2) {
/* 280 */                 this.cells[index - 1] = 0;
/* 281 */               } else if (ran == 3) {
/* 282 */                 this.cells[index + 1] = 0;
/* 283 */               } else if (ran == 4) {
/* 284 */                 this.cells[index - this.size_x] = 0;
/*     */               } else {
/* 286 */                 this.cells[index + this.size_x] = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateShell(int smoothening) {
/* 297 */     fill(1);
/*     */     
/* 299 */     float center_x = this.size_x * 0.5F;
/* 300 */     float center_y = this.size_y * 0.5F;
/* 301 */     float center_z = this.size_z * 0.5F;
/*     */     
/* 303 */     this.has_raised_shell_floor = (this.has_mycelium || this.rand.nextBoolean());
/*     */     
/* 305 */     for (int x = 16; x < this.size_x - 16; x++) {
/*     */       
/* 307 */       for (int z = 16; z < this.size_z - 16; z++) {
/*     */         
/* 309 */         for (int y = this.has_raised_shell_floor ? 24 : 16; y < this.size_y - 16; y++) {
/*     */           
/* 311 */           float dx = x + 0.5F - center_x;
/*     */           
/* 313 */           float dz = z + 0.5F - center_z;
/*     */           
/* 315 */           if (dx * dx + dz * dz <= 256.0F)
/*     */           {
/*     */             
/* 318 */             setValueAt(x, y, z, 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 323 */     excavate(Math.max(this.size_x / 2, Math.max(this.size_z / 2, this.size_y / 2)));
/*     */     
/* 325 */     smoothen(smoothening);
/*     */     
/* 327 */     System.arraycopy(this.cells, 0, this.wall_mask, 0, this.cells.length);
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
/*     */   public int getIndex(int x, int y, int z) {
/* 339 */     return x + (z << 6) + (y << 12);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValueAt(int x, int y, int z, int value) {
/* 344 */     this.cells[getIndex(x, y, z)] = (byte)value;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getValueAt(int x, int y, int z) {
/* 349 */     return this.cells[getIndex(x, y, z)];
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getValueAt(byte[] cells, int x, int y, int z) {
/* 354 */     return cells[getIndex(x, y, z)];
/*     */   }
/*     */ 
/*     */   
/*     */   public void smoothen() {
/* 359 */     if (use_optimized_code) {
/*     */       
/* 361 */       smoothen_optimized();
/*     */       
/*     */       return;
/*     */     } 
/* 365 */     System.arraycopy(this.cells, 0, this.cells_copy, 0, this.cells.length);
/*     */     
/* 367 */     for (int x = 1; x < this.size_x - 1; x++) {
/*     */       
/* 369 */       for (int z = 1; z < this.size_z - 1; z++) {
/*     */         
/* 371 */         for (int y = 1; y < this.size_y - 1; y++) {
/*     */           
/* 373 */           byte wall_value = getValueAt(this.cells_copy, x, y, z);
/*     */           
/* 375 */           if (wall_value == 0) {
/* 376 */             wall_value = 1;
/*     */           }
/* 378 */           int num_walls = 0;
/*     */           
/* 380 */           for (int dx = -1; dx <= 1 && num_walls < 14; dx++) {
/*     */             
/* 382 */             for (int dz = -1; dz <= 1 && num_walls < 14; dz++) {
/*     */               
/* 384 */               for (int dy = -1; dy <= 1 && num_walls < 14; dy++) {
/*     */                 
/* 386 */                 if (getValueAt(this.cells_copy, x + dx, y + dy, z + dz) != 0) {
/* 387 */                   num_walls++;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/* 392 */           setValueAt(x, y, z, (num_walls < 14) ? 0 : wall_value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void smoothen_optimized() {
/* 400 */     System.arraycopy(this.cells, 0, this.cells_copy, 0, this.cells.length);
/*     */     
/* 402 */     int[] index_deltas = new int[27];
/* 403 */     int index_deltas_index = -1;
/*     */     
/* 405 */     for (int dx = -1; dx <= 1; dx++) {
/*     */       
/* 407 */       for (int dz = -1; dz <= 1; dz++) {
/*     */         
/* 409 */         for (int dy = -1; dy <= 1; dy++) {
/* 410 */           index_deltas[++index_deltas_index] = dx + dz * this.size_x + dy * this.size_xz;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 416 */     for (int y = 1; y < this.size_y - 1; y++) {
/*     */       
/* 418 */       int index = y * this.size_xz;
/*     */       
/* 420 */       for (int z = 1; z < this.size_z - 1; z++) {
/*     */         
/* 422 */         index += this.size_x;
/*     */         
/* 424 */         for (int x = 1; x < this.size_x - 1; x++) {
/*     */           
/* 426 */           index++;
/*     */           
/* 428 */           byte wall_value = this.cells_copy[index];
/*     */           
/* 430 */           if (wall_value == 0) {
/* 431 */             wall_value = 1;
/*     */           }
/* 433 */           int num_walls = 0;
/*     */           
/* 435 */           for (int i = 0; i < index_deltas.length; i++) {
/*     */             
/* 437 */             if (this.cells_copy[index + index_deltas[i]] != 0 && ++num_walls > 13) {
/*     */               break;
/*     */             }
/*     */           } 
/* 441 */           this.cells[index] = (num_walls < 14) ? 0 : wall_value;
/*     */         } 
/*     */         
/* 444 */         index -= this.size_x - 2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void smoothen(int num_iterations) {
/* 451 */     while (--num_iterations >= 0) {
/* 452 */       smoothen();
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyWallMask(int smoothening) {
/* 457 */     for (int i = 0; i < this.cells.length; i++) {
/*     */       
/* 459 */       if (this.wall_mask[i] != 0) {
/* 460 */         this.cells[i] = this.wall_mask[i];
/*     */       }
/*     */     } 
/* 463 */     smoothen(smoothening);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void removePockmarks() {
/*     */     int x;
/* 470 */     for (x = 0; x < this.size_x; x++) {
/*     */       
/* 472 */       for (int i = 0; i < this.size_z; i++) {
/*     */         
/* 474 */         int index = getIndex(x, 0, i);
/*     */         
/* 476 */         if (this.cells[index] == 0 && this.cells[index + this.size_xz] != 0) {
/* 477 */           this.cells[index] = 1;
/*     */         }
/* 479 */         index = getIndex(x, this.size_y - 1, i);
/*     */         
/* 481 */         if (this.cells[index] == 0 && this.cells[index - this.size_xz] != 0) {
/* 482 */           this.cells[index] = 1;
/*     */         }
/*     */       } 
/*     */     } 
/* 486 */     for (x = 0; x < this.size_x; x++) {
/*     */       
/* 488 */       for (int y = 0; y < this.size_y; y++) {
/*     */         
/* 490 */         int index = getIndex(x, y, 0);
/*     */         
/* 492 */         if (this.cells[index] == 0 && this.cells[index + this.size_x] != 0) {
/* 493 */           this.cells[index] = 1;
/*     */         }
/* 495 */         index = getIndex(x, y, this.size_z - 1);
/*     */         
/* 497 */         if (this.cells[index] == 0 && this.cells[index - this.size_x] != 0) {
/* 498 */           this.cells[index] = 1;
/*     */         }
/*     */       } 
/*     */     } 
/* 502 */     for (int z = 0; z < this.size_z; z++) {
/*     */       
/* 504 */       for (int y = 0; y < this.size_y; y++) {
/*     */         
/* 506 */         int index = getIndex(0, y, z);
/*     */         
/* 508 */         if (this.cells[index] == 0 && this.cells[index + 1] != 0) {
/* 509 */           this.cells[index] = 1;
/*     */         }
/* 511 */         index = getIndex(this.size_x - 1, y, z);
/*     */         
/* 513 */         if (this.cells[index] == 0 && this.cells[index - 1] != 0) {
/* 514 */           this.cells[index] = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generate() {
/* 521 */     int[] smoothening = new int[3];
/*     */     
/* 523 */     int ran = this.rand.nextInt(3);
/*     */     
/* 525 */     if (ran == 0) {
/*     */       
/* 527 */       smoothening[0] = 2;
/* 528 */       smoothening[1] = this.rand.nextInt(57) + 8;
/* 529 */       smoothening[2] = 2 - smoothening[0];
/*     */     }
/* 531 */     else if (ran == 1) {
/*     */       
/* 533 */       smoothening[0] = this.rand.nextInt(3);
/*     */       
/* 535 */       int total_smoothening = this.rand.nextInt(57) + 8;
/*     */       
/* 537 */       smoothening[1] = this.rand.nextInt(total_smoothening + 1);
/* 538 */       smoothening[2] = total_smoothening - smoothening[1];
/*     */       
/* 540 */       if (smoothening[0] + smoothening[2] < 2)
/*     */       {
/* 542 */         smoothening[1] = smoothening[1] - 2;
/* 543 */         smoothening[2] = smoothening[2] + 2;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 548 */       smoothening[0] = 0;
/* 549 */       smoothening[1] = 0;
/* 550 */       smoothening[2] = this.rand.nextInt(57) + 8;
/*     */     } 
/*     */     
/* 553 */     generateShell(smoothening[0]);
/* 554 */     generateInterior(smoothening[1]);
/* 555 */     applyWallMask(smoothening[2]);
/*     */     
/* 557 */     removePockmarks();
/*     */     
/* 559 */     Debug.println("generateCaveNetwork: " + this.stub);
/*     */   }
/*     */ 
/*     */   
/*     */   public void apply(World world, int x, int y, int z) {
/* 564 */     if (this.has_mycelium) {
/*     */       
/* 566 */       int lowest_air_cell = Integer.MAX_VALUE;
/*     */       int dx;
/* 568 */       for (dx = 0; dx < this.size_x; dx++) {
/*     */         
/* 570 */         for (int dz = 0; dz < this.size_z; dz++) {
/*     */           
/* 572 */           for (int dy = 0; dy < this.size_y; dy++) {
/*     */             
/* 574 */             int value = getValueAt(dx, dy, dz);
/*     */             
/* 576 */             world.setBlock(x + dx, y + dy, z + dz, value);
/*     */             
/* 578 */             if (value == 0 && dy < lowest_air_cell) {
/* 579 */               lowest_air_cell = dy;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 587 */       for (dx = 0; dx < this.size_x; dx++) {
/*     */         
/* 589 */         for (int dz = 0; dz < this.size_z; dz++) {
/*     */           
/* 591 */           for (int dy = Math.max(lowest_air_cell, 1); dy < Math.min(lowest_air_cell + 6, this.size_y - 1); dy++) {
/*     */             
/* 593 */             if (getValueAt(dx, dy, dz) == 0 && getValueAt(dx, dy - 1, dz) == 1) {
/* 594 */               world.setBlock(x + dx, y + dy, z + dz, world.isAirBlock(x + dx, y + dy + 1, z + dz) ? Block.mycelium.blockID : Block.stone.blockID);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 601 */       for (int dx = 0; dx < this.size_x; dx++) {
/*     */         
/* 603 */         for (int dz = 0; dz < this.size_z; dz++) {
/*     */           
/* 605 */           for (int dy = 0; dy < this.size_y; dy++)
/*     */           {
/* 607 */             world.setBlock(x + dx, y + dy, z + dz, getValueAt(dx, dy, dz));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void replaceBlockAboveIfUnstable(int local_x, int y, int local_z, byte[] block_ids) {
/* 620 */     int block_above_id = block_ids[(local_x * 16 + local_z) * 128 + y + 1];
/*     */     
/* 622 */     if (block_above_id > 0) {
/*     */       
/* 624 */       Block block = Block.getBlock(block_above_id);
/*     */       
/* 626 */       if (block instanceof BlockFalling)
/*     */       {
/* 628 */         if (block_ids[(local_x * 16 + local_z) * 128 + y + 2] == Block.waterStill.blockID) {
/* 629 */           block_ids[(local_x * 16 + local_z) * 128 + y + 1] = (byte)Block.blockClay.blockID;
/*     */         } else {
/* 631 */           block_ids[(local_x * 16 + local_z) * 128 + y + 1] = (byte)((block == Block.sand) ? Block.sandStone.blockID : Block.stone.blockID);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void apply(int chunk_x, int chunk_z, int origin_chunk_x, int origin_chunk_z, byte[] block_ids) {
/* 638 */     int shift_x = (chunk_x - origin_chunk_x) * 16;
/* 639 */     int shift_z = (chunk_z - origin_chunk_z) * 16;
/*     */     
/* 641 */     if (this.has_mycelium) {
/*     */       
/* 643 */       int lowest_air_cell = this.size_y;
/*     */       
/* 645 */       for (int j = 0; j < 16; j++) {
/*     */         
/* 647 */         for (int local_z = 0; local_z < 16; local_z++) {
/*     */           
/* 649 */           for (int cell_y = 0; cell_y < this.size_y; cell_y++) {
/*     */             
/* 651 */             int value = getValueAt(shift_x + j, cell_y, shift_z + local_z);
/*     */             
/* 653 */             if (value == 0) {
/*     */               
/* 655 */               block_ids[(j * 16 + local_z) * 128 + cell_y + 8] = 0;
/*     */               
/* 657 */               if (cell_y < lowest_air_cell) {
/* 658 */                 lowest_air_cell = cell_y;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 664 */       for (int x = 0; x < this.size_x; x++) {
/*     */         
/* 666 */         for (int z = 0; z < this.size_z; z++) {
/*     */           
/* 668 */           for (int y = 0; y < lowest_air_cell; y++) {
/*     */             
/* 670 */             if (getValueAt(x, y, z) == 0) {
/*     */               
/* 672 */               lowest_air_cell = y;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 679 */       for (int i = 0; i < 16; i++) {
/*     */         
/* 681 */         for (int local_z = 0; local_z < 16; local_z++) {
/*     */           
/* 683 */           for (int cell_y = Math.max(lowest_air_cell, 1); cell_y < Math.min(lowest_air_cell + 6, this.size_y - 1); cell_y++) {
/*     */             
/* 685 */             if (getValueAt(shift_x + i, cell_y, shift_z + local_z) == 0 && getValueAt(shift_x + i, cell_y - 1, shift_z + local_z) == 1)
/*     */             {
/* 687 */               block_ids[(i * 16 + local_z) * 128 + cell_y + 8] = (byte)((block_ids[(i * 16 + local_z) * 128 + cell_y + 8 + 1] == 0) ? Block.mycelium.blockID : Block.stone.blockID);
/* 688 */               block_ids[(i * 16 + local_z) * 128 + cell_y + 8 - 1] = (byte)Block.stone.blockID;
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 696 */       for (int i = 0; i < 16; i++) {
/*     */         
/* 698 */         for (int local_z = 0; local_z < 16; local_z++) {
/*     */           
/* 700 */           for (int y = 8; y < 8 + this.size_y; y++) {
/*     */             
/* 702 */             if (getValueAt(shift_x + i, y - 8, shift_z + local_z) == 0) {
/* 703 */               block_ids[(i * 16 + local_z) * 128 + y] = 0;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 709 */     for (int local_x = 0; local_x < 16; local_x++) {
/*     */       
/* 711 */       for (int local_z = 0; local_z < 16; local_z++) {
/*     */         
/* 713 */         for (int y = 8 + this.size_y - 1; y >= 8; y--) {
/*     */           
/* 715 */           if (getValueAt(shift_x + local_x, y - 8, shift_z + local_z) == 0) {
/*     */             
/* 717 */             replaceBlockAboveIfUnstable(local_x, y, local_z, block_ids);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CaveNetworkGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */