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
/*     */ public final class RaycastPolicies
/*     */ {
/*     */   public static final int TERMINATE = -1;
/*     */   public static final int IGNORE = 0;
/*     */   public static final int IMPEDE_BY_10 = 1;
/*     */   public static final int IMPEDE_BY_50 = 5;
/*     */   public static final int IMPEDE_BY_90 = 9;
/*     */   public static final int TERMINATE_10_PERCENT = 11;
/*     */   public static final int TERMINATE_50_PERCENT = 15;
/*  22 */   public static final RaycastPolicies for_selection_hit_liquids = (new RaycastPolicies()).setImmutable();
/*  23 */   public static final RaycastPolicies for_selection_ignore_liquids = (new RaycastPolicies()).setLiquidsPolicy(0).setImmutable();
/*     */   
/*  25 */   public static final RaycastPolicies for_vision_standard = (new RaycastPolicies()).setForVision().setImmutable();
/*  26 */   public static final RaycastPolicies for_vision_ignore_leaves = (new RaycastPolicies()).setForVision().setLeavesPolicy(0).setImmutable();
/*     */   
/*  28 */   public static final RaycastPolicies for_physical_reach = (new RaycastPolicies()).setForPhysicalReach().setImmutable();
/*  29 */   public static final RaycastPolicies for_physical_reach_narrow = (new RaycastPolicies()).setForPhysicalReach().setMetalBarsPolicy(0).setImmutable();
/*     */   
/*  31 */   public static final RaycastPolicies for_entity_item_pickup = (new RaycastPolicies()).setForPhysicalReach().setLeavesPolicy(0).setImmutable();
/*     */ 
/*     */   
/*  34 */   public static final RaycastPolicies for_blunt_projectile = (new RaycastPolicies()).setForBluntProjectile(null, new Raycast()).setImmutable();
/*     */ 
/*     */   
/*  37 */   public static final RaycastPolicies for_piercing_projectile = (new RaycastPolicies()).setForPiercingProjectile(null, new Raycast()).setImmutable();
/*     */   
/*  39 */   public static final RaycastPolicies for_third_person_view = (new RaycastPolicies()).setForThirdPersonView().setImmutable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private int liquids_policy = -1;
/*  47 */   private int glass_and_ice_policy = -1;
/*     */   
/*  49 */   private int all_portals_policy = -1;
/*  50 */   private int open_portals_policy = -1;
/*  51 */   private int open_gates_policy = -1;
/*     */   
/*  53 */   private int tall_grass_policy = -1;
/*  54 */   private int leaves_policy = -1;
/*  55 */   private int reeds_policy = -1;
/*  56 */   private int vines_policy = -1;
/*     */   
/*  58 */   private int fence_policy = -1;
/*     */   
/*  60 */   private int metal_bars_policy = -1;
/*     */   
/*  62 */   private int non_solid_block_policy = -1;
/*     */ 
/*     */   
/*  65 */   private int uncovered_cauldron_policy = -1;
/*     */ 
/*     */   
/*     */   private boolean multiple_entities;
/*     */ 
/*     */   
/*     */   private boolean include_non_collidable_entities;
/*     */ 
/*     */   
/*     */   private boolean immutable;
/*     */ 
/*     */   
/*     */   public RaycastPolicies getMutableCopy() {
/*  78 */     RaycastPolicies policies = new RaycastPolicies();
/*     */     
/*  80 */     policies.liquids_policy = this.liquids_policy;
/*  81 */     policies.glass_and_ice_policy = this.glass_and_ice_policy;
/*     */     
/*  83 */     policies.all_portals_policy = this.all_portals_policy;
/*  84 */     policies.open_portals_policy = this.open_portals_policy;
/*  85 */     policies.open_gates_policy = this.open_gates_policy;
/*     */     
/*  87 */     policies.tall_grass_policy = this.tall_grass_policy;
/*  88 */     policies.leaves_policy = this.leaves_policy;
/*  89 */     policies.reeds_policy = this.reeds_policy;
/*  90 */     policies.vines_policy = this.vines_policy;
/*     */     
/*  92 */     policies.fence_policy = this.fence_policy;
/*     */     
/*  94 */     policies.metal_bars_policy = this.metal_bars_policy;
/*     */     
/*  96 */     policies.non_solid_block_policy = this.non_solid_block_policy;
/*     */     
/*  98 */     policies.uncovered_cauldron_policy = this.uncovered_cauldron_policy;
/*     */     
/* 100 */     policies.multiple_entities = this.multiple_entities;
/* 101 */     policies.include_non_collidable_entities = this.include_non_collidable_entities;
/*     */     
/* 103 */     return policies;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RaycastPolicies for_selection(boolean hit_liquids) {
/* 108 */     return hit_liquids ? for_selection_hit_liquids : for_selection_ignore_liquids;
/*     */   }
/*     */ 
/*     */   
/*     */   public static RaycastPolicies for_vision(boolean ignore_leaves) {
/* 113 */     return ignore_leaves ? for_vision_ignore_leaves : for_vision_standard;
/*     */   }
/*     */ 
/*     */   
/*     */   private void raiseAttemptingToChangeImmutablePoliciesErrorMessage() {
/* 118 */     Minecraft.setErrorMessage("RaycastPolicies: attempting to change immutable policies");
/* 119 */     (new Exception()).printStackTrace();
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setLiquidsPolicy(int policy) {
/* 124 */     if (this.immutable) {
/*     */       
/* 126 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 127 */       return this;
/*     */     } 
/*     */     
/* 130 */     this.liquids_policy = policy;
/* 131 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setGlassAndIcePolicy(int policy) {
/* 136 */     if (this.immutable) {
/*     */       
/* 138 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 139 */       return this;
/*     */     } 
/*     */     
/* 142 */     this.glass_and_ice_policy = policy;
/* 143 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setAllPortalsPolicy(int policy) {
/* 148 */     if (this.immutable) {
/*     */       
/* 150 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 151 */       return this;
/*     */     } 
/*     */     
/* 154 */     this.all_portals_policy = policy;
/* 155 */     this.open_portals_policy = policy;
/* 156 */     this.open_gates_policy = policy;
/*     */     
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setOpenPortalsPolicy(int policy) {
/* 163 */     if (this.immutable) {
/*     */       
/* 165 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 166 */       return this;
/*     */     } 
/*     */     
/* 169 */     this.open_portals_policy = policy;
/* 170 */     this.open_gates_policy = policy;
/* 171 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setOpenGatesPolicy(int policy) {
/* 176 */     if (this.immutable) {
/*     */       
/* 178 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 179 */       return this;
/*     */     } 
/*     */     
/* 182 */     this.open_gates_policy = policy;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setTallGrassPolicy(int policy) {
/* 188 */     if (this.immutable) {
/*     */       
/* 190 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 191 */       return this;
/*     */     } 
/*     */     
/* 194 */     this.tall_grass_policy = policy;
/* 195 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setLeavesPolicy(int policy) {
/* 200 */     if (this.immutable) {
/*     */       
/* 202 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 203 */       return this;
/*     */     } 
/*     */     
/* 206 */     this.leaves_policy = policy;
/* 207 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setReedsPolicy(int policy) {
/* 212 */     if (this.immutable) {
/*     */       
/* 214 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 215 */       return this;
/*     */     } 
/*     */     
/* 218 */     this.reeds_policy = policy;
/* 219 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setVinesPolicy(int policy) {
/* 224 */     if (this.immutable) {
/*     */       
/* 226 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 227 */       return this;
/*     */     } 
/*     */     
/* 230 */     this.vines_policy = policy;
/* 231 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setFencePolicy(int policy) {
/* 236 */     if (this.immutable) {
/*     */       
/* 238 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 239 */       return this;
/*     */     } 
/*     */     
/* 242 */     this.fence_policy = policy;
/* 243 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setMetalBarsPolicy(int policy) {
/* 248 */     if (this.immutable) {
/*     */       
/* 250 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 251 */       return this;
/*     */     } 
/*     */     
/* 254 */     this.metal_bars_policy = policy;
/* 255 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setNonSolidBlockPolicy(int policy) {
/* 260 */     if (this.immutable) {
/*     */       
/* 262 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 263 */       return this;
/*     */     } 
/*     */     
/* 266 */     this.non_solid_block_policy = policy;
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setUncoveredCauldronPolicy(int policy) {
/* 272 */     if (this.immutable) {
/*     */       
/* 274 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 275 */       return this;
/*     */     } 
/*     */     
/* 278 */     this.uncovered_cauldron_policy = policy;
/* 279 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private RaycastPolicies setForVision() {
/* 284 */     if (this.immutable) {
/*     */       
/* 286 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 287 */       return this;
/*     */     } 
/*     */     
/* 290 */     setLiquidsPolicy(0);
/* 291 */     setGlassAndIcePolicy(0);
/* 292 */     setAllPortalsPolicy(0);
/* 293 */     setTallGrassPolicy(5);
/* 294 */     setLeavesPolicy(5);
/* 295 */     setReedsPolicy(5);
/* 296 */     setVinesPolicy(5);
/* 297 */     setFencePolicy(0);
/* 298 */     setMetalBarsPolicy(0);
/*     */     
/* 300 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RaycastPolicies setForPhysicalReach() {
/* 306 */     if (this.immutable) {
/*     */       
/* 308 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 309 */       return this;
/*     */     } 
/*     */     
/* 312 */     setLiquidsPolicy(0);
/* 313 */     setOpenGatesPolicy(0);
/* 314 */     setTallGrassPolicy(0);
/* 315 */     setReedsPolicy(0);
/* 316 */     setVinesPolicy(0);
/* 317 */     setNonSolidBlockPolicy(0);
/* 318 */     setUncoveredCauldronPolicy(0);
/*     */     
/* 320 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RaycastPolicies setForBluntProjectile(Entity entity, Raycast raycast) {
/* 326 */     if (this.immutable) {
/*     */       
/* 328 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 329 */       return this;
/*     */     } 
/*     */     
/* 332 */     setLiquidsPolicy(0);
/* 333 */     setOpenGatesPolicy(0);
/* 334 */     setTallGrassPolicy(0);
/* 335 */     setVinesPolicy(15);
/*     */     
/* 337 */     raycast.setOriginator(entity);
/*     */     
/* 339 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RaycastPolicies setForPiercingProjectile(Entity entity, Raycast raycast) {
/* 345 */     if (this.immutable) {
/*     */       
/* 347 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 348 */       return this;
/*     */     } 
/*     */     
/* 351 */     setForBluntProjectile(entity, raycast);
/* 352 */     setLeavesPolicy(11);
/* 353 */     setReedsPolicy(0);
/* 354 */     setVinesPolicy(0);
/* 355 */     setMetalBarsPolicy(0);
/*     */     
/* 357 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private RaycastPolicies setForThirdPersonView() {
/* 362 */     if (this.immutable) {
/*     */       
/* 364 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 365 */       return this;
/*     */     } 
/*     */     
/* 368 */     setLiquidsPolicy(0);
/* 369 */     setGlassAndIcePolicy(0);
/* 370 */     setAllPortalsPolicy(0);
/* 371 */     setTallGrassPolicy(0);
/* 372 */     setReedsPolicy(5);
/* 373 */     setVinesPolicy(0);
/* 374 */     setFencePolicy(0);
/* 375 */     setMetalBarsPolicy(0);
/*     */     
/* 377 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private RaycastPolicies setImmutable() {
/* 382 */     this.immutable = true;
/* 383 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getLiquidsPolicy() {
/* 388 */     return this.liquids_policy;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean alwaysIgnoreLiquids() {
/* 393 */     return (this.liquids_policy == 0);
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
/*     */   public RaycastPolicies setMultipleEntities(boolean multiple_entities) {
/* 498 */     if (this.immutable) {
/*     */       
/* 500 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 501 */       return this;
/*     */     } 
/*     */     
/* 504 */     this.multiple_entities = multiple_entities;
/* 505 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public RaycastPolicies setIncludeNonCollidableEntities(boolean include_non_collidable_entities) {
/* 510 */     if (this.immutable) {
/*     */       
/* 512 */       raiseAttemptingToChangeImmutablePoliciesErrorMessage();
/* 513 */       return this;
/*     */     } 
/*     */     
/* 516 */     this.include_non_collidable_entities = include_non_collidable_entities;
/* 517 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getMultipleEntities() {
/* 522 */     return this.multiple_entities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getNonCollidableEntityPolicy() {
/* 527 */     return this.include_non_collidable_entities;
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
/*     */   public boolean ignoreBlock(World world, int x, int y, int z) {
/* 547 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 549 */     return (block == null) ? true : ignoreBlock(block, world, x, y, z, new Raycast());
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
/*     */   public boolean ignoreBlock(Block block, World world, int x, int y, int z, Raycast raycast) {
/* 627 */     if (this.glass_and_ice_policy != -1 && (block.blockMaterial == Material.glass || block.blockMaterial == Material.ice) && !raycast.isFullyImpeded(this.glass_and_ice_policy)) {
/*     */       
/* 629 */       if (this == for_third_person_view && block.blockMaterial == Material.ice) {
/* 630 */         return false;
/*     */       }
/* 632 */       return true;
/*     */     } 
/*     */     
/* 635 */     if (block.isPortal()) {
/*     */       
/* 637 */       if (this.open_gates_policy != -1 && block instanceof BlockFenceGate && BlockFenceGate.isFenceGateOpen(world.getBlockMetadata(x, y, z)) && !raycast.isFullyImpeded(this.open_gates_policy)) {
/* 638 */         return true;
/*     */       }
/* 640 */       if (this.open_portals_policy != -1 && block.isOpenPortal(world, x, y, z) && !raycast.isFullyImpeded(this.open_portals_policy)) {
/* 641 */         return true;
/*     */       }
/* 643 */       if (this.all_portals_policy != -1 && !raycast.isFullyImpeded(this.all_portals_policy)) {
/* 644 */         return true;
/*     */       }
/*     */     } 
/* 647 */     if (this.tall_grass_policy != -1)
/*     */     {
/* 649 */       if (block == Block.tallGrass || block == Block.plantRed || block == Block.plantYellow || block instanceof BlockCrops || block instanceof BlockWeb)
/*     */       {
/* 651 */         if (!raycast.isFullyImpeded(this.tall_grass_policy)) {
/* 652 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 656 */     if (this.non_solid_block_policy != -1 && !block.is_always_solid)
/*     */     {
/*     */ 
/*     */       
/* 660 */       if (block.is_never_solid || !block.isSolid(world.getBlockMetadata(x, y, z)) || block.getCollisionBoundsCombined(world, x, y, z, null, true) == null)
/*     */       {
/* 662 */         if (!raycast.isFullyImpeded(this.non_solid_block_policy)) {
/* 663 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 667 */     if (this.uncovered_cauldron_policy != -1 && (block == Block.cauldron || block == Block.hopperBlock) && !world.isBlockFaceFlatAndSolid(x, y + 1, z, EnumFace.BOTTOM) && !raycast.isFullyImpeded(this.uncovered_cauldron_policy)) {
/* 668 */       return true;
/*     */     }
/* 670 */     if (this.leaves_policy != -1 && block instanceof BlockLeaves && !raycast.isFullyImpeded(this.leaves_policy)) {
/* 671 */       return true;
/*     */     }
/* 673 */     if (this.reeds_policy != -1 && block instanceof BlockReed && !raycast.isFullyImpeded(this.reeds_policy)) {
/* 674 */       return true;
/*     */     }
/* 676 */     if (this.vines_policy != -1 && block instanceof BlockVine && !raycast.isFullyImpeded(this.vines_policy)) {
/* 677 */       return true;
/*     */     }
/* 679 */     if (this.fence_policy != -1 && block instanceof BlockFence && !raycast.isFullyImpeded(this.fence_policy)) {
/* 680 */       return true;
/*     */     }
/* 682 */     if (this.metal_bars_policy != -1 && block instanceof BlockPane && block.blockMaterial.isMetal() && !raycast.isFullyImpeded(this.metal_bars_policy)) {
/* 683 */       return true;
/*     */     }
/* 685 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RaycastPolicies.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */