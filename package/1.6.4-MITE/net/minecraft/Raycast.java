/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Raycast
/*      */   implements Comparator
/*      */ {
/*      */   private final World world;
/*      */   private final Vec3 origin;
/*      */   private final Vec3 limit;
/*      */   private RaycastPolicies policies;
/*      */   public static final int MAX_IMPEDANCE = 100;
/*      */   private int impedance;
/*      */   private boolean has_been_performed_vs_blocks;
/*      */   private boolean has_been_performed_vs_entities;
/*      */   private RaycastCollision block_collision;
/*      */   private List entity_collisions;
/*      */   private Entity originator;
/*      */   private boolean has_produced_collisions;
/*      */   private boolean is_for_player_selection;
/*      */   
/*      */   public Raycast() {
/*   44 */     this.world = null;
/*   45 */     this.origin = null;
/*   46 */     this.limit = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast(World world) {
/*   52 */     this.world = world;
/*   53 */     this.origin = world.getVec3();
/*   54 */     this.limit = world.getVec3();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast(World world, Vec3 origin) {
/*   60 */     this.world = world;
/*   61 */     this.origin = origin.copy();
/*   62 */     this.limit = world.getVec3();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast(World world, Vec3 origin, Vec3 limit) {
/*   68 */     this.world = world;
/*   69 */     this.origin = origin.copy();
/*   70 */     this.limit = limit.copy();
/*      */   }
/*      */ 
/*      */   
/*      */   private Raycast(World world, Vec3 origin, Vec3 normalized_direction, double distance_to_limit) {
/*   75 */     this(world, origin, origin.applyDirectionAndDistance(normalized_direction, distance_to_limit));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast(EntityLivingBase observer, float partial_tick) {
/*   81 */     this(observer, partial_tick, 16.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast(EntityLivingBase observer, float partial_tick, double distance_to_limit) {
/*   87 */     this(observer.worldObj, observer.getEyePosition(partial_tick), observer.getLook(partial_tick), distance_to_limit);
/*      */     
/*   89 */     this.originator = observer;
/*      */   }
/*      */ 
/*      */   
/*      */   public World getWorld() {
/*   94 */     return this.world;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getOrigin() {
/*   99 */     return this.origin;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getLimit() {
/*  104 */     return this.limit;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setOrigin(Vec3 vec3) {
/*  110 */     if (this.has_produced_collisions) {
/*      */       
/*  112 */       Minecraft.setErrorMessage("setOrigin: cannot change origin after raycast has produced collisions");
/*  113 */       (new Exception()).printStackTrace();
/*      */       
/*  115 */       return this;
/*      */     } 
/*      */     
/*  118 */     this.origin.setComponents(vec3);
/*  119 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setLimit(Vec3 vec3) {
/*  124 */     this.limit.setComponents(vec3);
/*  125 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setLimitToBlockCollisionPoint() {
/*  130 */     setLimit(this.block_collision.position_hit);
/*  131 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setOriginAndLimit(Vec3 origin, Vec3 limit) {
/*  137 */     setOrigin(origin);
/*  138 */     setLimit(limit);
/*      */     
/*  140 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setOriginAndLimitForLookVector(EntityLivingBase observer, float partial_tick) {
/*  146 */     return setOriginAndLimitForLookVector(observer, partial_tick, 16.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setOriginAndLimitForLookVector(EntityLivingBase observer, float partial_tick, double distance_to_limit) {
/*  152 */     setOriginator(observer);
/*      */     
/*  154 */     Vec3 origin = observer.getEyePosition(partial_tick);
/*      */     
/*  156 */     return setOriginAndLimit(origin, origin.applyDirectionAndDistance(observer.getLook(partial_tick), distance_to_limit));
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setOriginator(Entity originator) {
/*  161 */     this.originator = originator;
/*  162 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getOriginator() {
/*  167 */     return this.originator;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setHasProducedCollisions() {
/*  172 */     this.has_produced_collisions = true;
/*  173 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setPolicies(RaycastPolicies policies) {
/*  179 */     if (this.policies != null) {
/*      */       
/*  181 */       Minecraft.setErrorMessage("setPolicies: raycast already has a policies object");
/*  182 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  185 */     this.policies = policies;
/*  186 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setForSelection(boolean hit_liquids) {
/*  214 */     setPolicies(RaycastPolicies.for_selection(hit_liquids));
/*  215 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setForPlayerSelection() {
/*  220 */     this.is_for_player_selection = true;
/*  221 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isForPlayerSelection() {
/*  226 */     return this.is_for_player_selection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setForVision(boolean ignore_leaves) {
/*  249 */     setPolicies(RaycastPolicies.for_vision(ignore_leaves));
/*  250 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setForPhysicalReach() {
/*  265 */     setPolicies(RaycastPolicies.for_physical_reach);
/*  266 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setForBluntProjectile(Entity entity) {
/*  281 */     setPolicies(RaycastPolicies.for_blunt_projectile);
/*  282 */     setOriginator(entity);
/*      */     
/*  284 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setForThrownWeb(Entity entity) {
/*  289 */     setPolicies(RaycastPolicies.for_selection_hit_liquids);
/*  290 */     setOriginator(entity);
/*      */     
/*  292 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast setForPiercingProjectile(Entity entity) {
/*  307 */     setPolicies(RaycastPolicies.for_piercing_projectile);
/*  308 */     setOriginator(entity);
/*      */     
/*  310 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean alwaysIgnoreLiquids() {
/*  315 */     return this.policies.alwaysIgnoreLiquids();
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setMultipleEntities(boolean multiple_entities) {
/*  320 */     this.policies.setMultipleEntities(multiple_entities);
/*  321 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast setIncludeNonCollidableEntities(boolean include_non_collidable_entities) {
/*  326 */     this.policies.setIncludeNonCollidableEntities(include_non_collidable_entities);
/*  327 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast clearImpedance() {
/*  332 */     this.impedance = 0;
/*  333 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   private int addImpedance(int impedance) {
/*  338 */     this.impedance += impedance;
/*      */     
/*  340 */     return this.impedance;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFullyImpeded(int policy) {
/*  346 */     if (policy == -1)
/*      */     {
/*  348 */       return true;
/*      */     }
/*  350 */     if (policy == 0)
/*      */     {
/*  352 */       return false;
/*      */     }
/*  354 */     if (policy >= 1 && policy <= 9)
/*      */     {
/*  356 */       return (addImpedance(policy * 10) >= 100);
/*      */     }
/*  358 */     if (policy == 11)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  363 */       return (this.originator == null) ? false : ((RNG.chance_in_10[++this.originator.raycast_seed_offset + this.originator.entityId & 0x7FFF] && RNG.chance_in_4[++this.originator.raycast_seed_offset + this.originator.entityId & 0x7FFF]));
/*      */     }
/*  365 */     if (policy == 15)
/*      */     {
/*  367 */       return (this.originator == null) ? false : ((RNG.chance_in_2[++this.originator.raycast_seed_offset + this.originator.entityId & 0x7FFF] && RNG.chance_in_4[++this.originator.raycast_seed_offset + this.originator.entityId & 0x7FFF]));
/*      */     }
/*      */ 
/*      */     
/*  371 */     Minecraft.setErrorMessage("isFullyImpeded: unhandled policy " + policy);
/*      */ 
/*      */     
/*  374 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ignoreBlock(Block block, World world, int x, int y, int z) {
/*  379 */     return this.policies.ignoreBlock(block, world, x, y, z, this);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setBlockCollision(RaycastCollision rc) {
/*  384 */     if (rc != null && rc.raycast != this) {
/*      */       
/*  386 */       Minecraft.setErrorMessage("setBlockCollision: rc.raycast!=this");
/*  387 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  390 */     this.block_collision = rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast performVsBlocks() {
/*  408 */     this.has_been_performed_vs_blocks = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     RaycastCollision[] rc = new RaycastCollision[4];
/*      */     
/*  418 */     double x_coord = this.origin.xCoord;
/*  419 */     double z_coord = this.origin.zCoord;
/*      */     
/*  421 */     this.origin.xCoord = x_coord - 1.0E-4D;
/*      */     
/*  423 */     rc[0] = this.world.tryRaycastVsBlocks(this);
/*      */     
/*  425 */     this.origin.xCoord = x_coord + 1.0E-4D;
/*      */     
/*  427 */     rc[1] = this.world.tryRaycastVsBlocks(this);
/*      */     
/*  429 */     this.origin.xCoord = x_coord;
/*  430 */     this.origin.zCoord = z_coord - 1.0E-4D;
/*      */     
/*  432 */     rc[2] = this.world.tryRaycastVsBlocks(this);
/*      */     
/*  434 */     this.origin.zCoord = z_coord + 1.0E-4D;
/*      */     
/*  436 */     rc[3] = this.world.tryRaycastVsBlocks(this);
/*      */     
/*  438 */     this.origin.zCoord = z_coord;
/*      */     
/*  440 */     double shortest_distance_to_collision = 0.0D;
/*  441 */     this.block_collision = null;
/*      */     
/*  443 */     for (int i = 0; i < rc.length; i++) {
/*      */       
/*  445 */       if (rc[i] != null) {
/*      */ 
/*      */         
/*  448 */         double distance = rc[i].getDistanceFromOriginToCollisionPoint();
/*      */         
/*  450 */         if (this.block_collision == null || distance < shortest_distance_to_collision) {
/*      */ 
/*      */           
/*  453 */           setBlockCollision(rc[i]);
/*  454 */           shortest_distance_to_collision = distance;
/*      */         } 
/*      */       } 
/*      */     } 
/*  458 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast performVsBlocksSingle() {
/*  469 */     this.has_been_performed_vs_blocks = true;
/*      */ 
/*      */     
/*  472 */     setBlockCollision(this.world.tryRaycastVsBlocks(this));
/*      */     
/*  474 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Raycast performVsEntities() {
/*      */     double min_x, min_y, min_z, max_x, max_y, max_z;
/*  486 */     this.has_been_performed_vs_entities = true;
/*      */     
/*  488 */     this.entity_collisions = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  493 */     if (this.origin.xCoord < this.limit.xCoord) {
/*      */       
/*  495 */       min_x = this.origin.xCoord;
/*  496 */       max_x = this.limit.xCoord;
/*      */     }
/*      */     else {
/*      */       
/*  500 */       min_x = this.limit.xCoord;
/*  501 */       max_x = this.origin.xCoord;
/*      */     } 
/*      */     
/*  504 */     if (this.origin.yCoord < this.limit.yCoord) {
/*      */       
/*  506 */       min_y = this.origin.yCoord;
/*  507 */       max_y = this.limit.yCoord;
/*      */     }
/*      */     else {
/*      */       
/*  511 */       min_y = this.limit.yCoord;
/*  512 */       max_y = this.origin.yCoord;
/*      */     } 
/*      */     
/*  515 */     if (this.origin.zCoord < this.limit.zCoord) {
/*      */       
/*  517 */       min_z = this.origin.zCoord;
/*  518 */       max_z = this.limit.zCoord;
/*      */     }
/*      */     else {
/*      */       
/*  522 */       min_z = this.limit.zCoord;
/*  523 */       max_z = this.origin.zCoord;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  529 */     AxisAlignedBB bb = new AxisAlignedBB(min_x, min_y, min_z, max_x, max_y, max_z);
/*      */     
/*  531 */     List<Entity> entities = this.world.getEntitiesWithinAABBExcludingEntity(this.originator, bb.expand(1.0D, 1.0D, 1.0D));
/*      */     
/*  533 */     if (this.policies.getMultipleEntities()) {
/*      */       
/*  535 */       for (int i = 0; i < entities.size(); i++) {
/*      */         
/*  537 */         Entity entity = entities.get(i);
/*      */         
/*  539 */         if (this.policies.getNonCollidableEntityPolicy() || entity.canBeCollidedWith())
/*      */         {
/*      */           
/*  542 */           if (this.originator == null || !this.originator.cannotRaycastCollideWith(entity)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  548 */             float cbs = entity.getCollisionBorderSize(this.originator);
/*      */             
/*  550 */             AxisAlignedBB effective_collision_box = entity.boundingBox.expand(cbs, cbs, cbs);
/*      */             
/*  552 */             entity.modifyEffectiveCollisionBoxForRaycastFromEntity(effective_collision_box, this.originator);
/*      */             
/*  554 */             if (effective_collision_box != null)
/*      */             {
/*      */               
/*  557 */               if (effective_collision_box.isVecInside(this.origin)) {
/*      */                 
/*  559 */                 this.entity_collisions.add(new RaycastCollision(this, entity, new AABBIntercept(this.origin.copy(), null)));
/*      */               }
/*      */               else {
/*      */                 
/*  563 */                 AABBIntercept intercept = effective_collision_box.calculateIntercept(this.world, this.origin, this.limit);
/*      */                 
/*  565 */                 if (intercept != null)
/*      */                 {
/*      */                   
/*  568 */                   this.entity_collisions.add(new RaycastCollision(this, entity, intercept)); } 
/*      */               }  } 
/*      */           }  } 
/*  571 */       }  Collections.sort(this.entity_collisions, this);
/*      */     }
/*      */     else {
/*      */       
/*  575 */       Entity nearest_collided_entity = null;
/*  576 */       AABBIntercept nearest_intercept = null;
/*  577 */       double distance_to_nearest_collision = 0.0D;
/*      */       
/*  579 */       for (int i = 0; i < entities.size(); i++) {
/*      */         
/*  581 */         Entity entity = entities.get(i);
/*      */         
/*  583 */         if (this.policies.getNonCollidableEntityPolicy() || entity.canBeCollidedWith())
/*      */         {
/*      */           
/*  586 */           if (this.originator == null || !this.originator.cannotRaycastCollideWith(entity)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  592 */             float cbs = entity.getCollisionBorderSize(this.originator);
/*      */             
/*  594 */             AxisAlignedBB effective_collision_box = entity.boundingBox.expand(cbs, cbs, cbs);
/*      */             
/*  596 */             entity.modifyEffectiveCollisionBoxForRaycastFromEntity(effective_collision_box, this.originator);
/*      */             
/*  598 */             if (effective_collision_box != null) {
/*      */ 
/*      */               
/*  601 */               if (effective_collision_box.isVecInside(this.origin)) {
/*      */                 
/*  603 */                 nearest_collided_entity = entity;
/*  604 */                 nearest_intercept = new AABBIntercept(this.origin.copy(), null);
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  609 */               AABBIntercept intercept = effective_collision_box.calculateIntercept(this.world, this.origin, this.limit);
/*      */               
/*  611 */               if (intercept != null) {
/*      */ 
/*      */                 
/*  614 */                 double distance_to_collision = this.origin.distanceTo(intercept.position_hit);
/*      */                 
/*  616 */                 if (nearest_collided_entity == null || distance_to_collision < distance_to_nearest_collision)
/*      */                 
/*  618 */                 { nearest_collided_entity = entity;
/*  619 */                   nearest_intercept = intercept;
/*      */                   
/*  621 */                   distance_to_nearest_collision = distance_to_collision; } 
/*      */               } 
/*      */             } 
/*      */           }  } 
/*  625 */       }  if (nearest_collided_entity != null) {
/*  626 */         this.entity_collisions.add(new RaycastCollision(this, nearest_collided_entity, nearest_intercept));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  635 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raycast performVsBlocksAndEntities() {
/*  640 */     performVsBlocks();
/*  641 */     performVsEntities();
/*      */     
/*  643 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int compare(Object a, Object b) {
/*  648 */     RaycastCollision rc_a = (RaycastCollision)a;
/*  649 */     RaycastCollision rc_b = (RaycastCollision)b;
/*      */     
/*  651 */     double distance_a = this.origin.distanceTo(rc_a.position_hit);
/*  652 */     double distance_b = this.origin.distanceTo(rc_b.position_hit);
/*      */     
/*  654 */     return (distance_a < distance_b) ? -1 : ((distance_a > distance_b) ? 1 : 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean hasBeenPerformedVsBlocksOrEntities() {
/*  659 */     return (this.has_been_performed_vs_blocks || this.has_been_performed_vs_entities);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasBlockCollision() {
/*  665 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*      */       
/*  667 */       Minecraft.setErrorMessage("hasBlockCollision: raycast was never performed vs blocks or entities");
/*  668 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  671 */     return (this.block_collision != null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasNoBlockCollision() {
/*  677 */     return !hasBlockCollision();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getNearestEntityCollision() {
/*  683 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*      */       
/*  685 */       Minecraft.setErrorMessage("getNearestEntityCollision: raycast was never performed vs blocks or entities");
/*  686 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  689 */     return this.entity_collisions.isEmpty() ? null : this.entity_collisions.get(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasEntityCollisions() {
/*  695 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*      */       
/*  697 */       Minecraft.setErrorMessage("hasEntityCollisions: raycast was never performed vs blocks or entities");
/*  698 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  701 */     return (this.entity_collisions != null && !this.entity_collisions.isEmpty());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCollisions() {
/*  707 */     return (hasBlockCollision() || hasEntityCollisions());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasNoCollisions() {
/*  713 */     return !hasCollisions();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getBlockCollision() {
/*  719 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*      */       
/*  721 */       Minecraft.setErrorMessage("getBlockCollision: raycast was never performed vs blocks or entities");
/*  722 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  725 */     return this.block_collision;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getBlockCollision(Vec3 limit) {
/*  731 */     return setLimit(limit).performVsBlocks().getBlockCollision();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkForNoBlockCollision(Vec3 limit) {
/*  737 */     return (getBlockCollision(limit) == null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getBlockCollision(Vec3 origin, Vec3 limit) {
/*  743 */     return setOriginAndLimit(origin, limit).performVsBlocks().getBlockCollision();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkForNoBlockCollision(Vec3 origin, Vec3 limit) {
/*  749 */     return (getBlockCollision(origin, limit) == null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List getEntityCollisions() {
/*  755 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*      */       
/*  757 */       Minecraft.setErrorMessage("getEntityCollisions: raycast was never performed vs blocks or entities");
/*  758 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/*  761 */     return this.entity_collisions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float calcEntityBiasMethod2(double distance_to_entity_intersection, Entity collided_entity) {
/*  787 */     float entity_bias = -collided_entity.getCollisionBorderSize(this.originator) * 3.0F;
/*      */     
/*  789 */     if (this.originator instanceof EntityPlayer) {
/*      */ 
/*      */       
/*  792 */       RaycastCollision rc = this.world.getBlockCollisionForPhysicalReach(this.origin, this.limit);
/*      */       
/*  794 */       if (rc == null || rc.getDistanceFromOriginToCollisionPoint() > distance_to_entity_intersection + (collided_entity.getCollisionBorderSize(this.originator) * 3.0F)) {
/*  795 */         entity_bias += 0.75F;
/*      */       }
/*  797 */     } else if (this.originator instanceof EntityArrow) {
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  804 */     return entity_bias;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getNearestCollision() {
/*  810 */     if (hasNoCollisions()) {
/*  811 */       return null;
/*      */     }
/*  813 */     if (!hasEntityCollisions()) {
/*  814 */       return this.block_collision;
/*      */     }
/*  816 */     RaycastCollision nearest_entity_collision = getNearestEntityCollision();
/*      */     
/*  818 */     if (!hasBlockCollision()) {
/*  819 */       return nearest_entity_collision;
/*      */     }
/*      */ 
/*      */     
/*  823 */     double distance_to_block_intersection = this.block_collision.getDistanceFromOriginToCollisionPoint();
/*  824 */     double distance_to_entity_intersection = nearest_entity_collision.getDistanceFromOriginToCollisionPoint();
/*      */     
/*  826 */     float entity_bias = calcEntityBiasMethod2(distance_to_entity_intersection, nearest_entity_collision.getEntityHit());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  831 */     return (distance_to_entity_intersection - entity_bias <= distance_to_block_intersection) ? nearest_entity_collision : this.block_collision;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RaycastCollision getNearestCollisionReachableByObserver(EnumEntityReachContext entity_reach_context, float partial_tick) {
/*  837 */     RaycastCollision nearest_rc = getNearestCollision();
/*      */     
/*  839 */     if (nearest_rc == null) {
/*  840 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  844 */     if (nearest_rc.isBlock()) {
/*      */       double d;
/*      */       
/*      */       Vec3[] block_reach_from_points;
/*  848 */       if (this.originator.isEntityPlayer()) {
/*      */         
/*  850 */         EntityPlayer player = (EntityPlayer)this.originator;
/*      */         
/*  852 */         d = player.getReach(nearest_rc.getBlockHit(), nearest_rc.block_hit_metadata);
/*  853 */         block_reach_from_points = player.getBlockReachFromPoints(partial_tick);
/*      */       }
/*      */       else {
/*      */         
/*  857 */         d = 16.0D;
/*  858 */         block_reach_from_points = new Vec3[] { this.originator.isEntityLivingBase() ? this.originator.getAsEntityLivingBase().getPrimaryPointOfAttack() : this.originator.getCenterPoint() };
/*      */       } 
/*      */       
/*  861 */       for (int i = 0; i < block_reach_from_points.length; i++) {
/*      */         
/*  863 */         if (block_reach_from_points[i].distanceTo(nearest_rc.block_hit_x + 0.5D, nearest_rc.block_hit_y + 0.5D, nearest_rc.block_hit_z + 0.5D) <= d) {
/*  864 */           return nearest_rc;
/*      */         }
/*      */       } 
/*  867 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  871 */     double reach = (entity_reach_context == null) ? 16.0D : (this.originator.isEntityPlayer() ? this.originator.getAsPlayer().getReach(entity_reach_context, nearest_rc.getEntityHit()) : this.originator.getAsEntityLiving().getReach());
/*      */ 
/*      */     
/*  874 */     return (nearest_rc.getDistanceFromOriginToCollisionPoint() <= reach) ? nearest_rc : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static RaycastCollision getShortestRaycastCollision(RaycastCollision[] rc) {
/*  898 */     RaycastCollision shortest_rc = null;
/*  899 */     double shortest_distance = 0.0D;
/*      */     
/*  901 */     for (int i = 0; i < rc.length; i++) {
/*      */       
/*  903 */       if (rc[i] != null) {
/*      */ 
/*      */         
/*  906 */         double distance = rc[i].getDistanceFromOriginToCollisionPoint();
/*      */         
/*  908 */         if (shortest_rc == null || distance < shortest_distance) {
/*      */           
/*  910 */           shortest_rc = rc[i];
/*  911 */           shortest_distance = distance;
/*      */         } 
/*      */       } 
/*      */     } 
/*  915 */     return shortest_rc;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  920 */     if (!hasBeenPerformedVsBlocksOrEntities()) {
/*  921 */       return null;
/*      */     }
/*  923 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  925 */     if (this.block_collision != null) {
/*  926 */       sb.append(this.block_collision + (hasEntityCollisions() ? ", " : ""));
/*      */     }
/*  928 */     if (hasEntityCollisions())
/*      */     {
/*  930 */       if (this.policies.getMultipleEntities()) {
/*      */         
/*  932 */         Iterator<RaycastCollision> i = this.entity_collisions.iterator();
/*      */         
/*  934 */         sb.append(i.next());
/*      */         
/*  936 */         while (i.hasNext())
/*      */         {
/*  938 */           RaycastCollision rc = i.next();
/*      */           
/*  940 */           sb.append(", " + rc);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  945 */         RaycastCollision rc = getNearestEntityCollision();
/*      */         
/*  947 */         sb.append(rc + " @ " + StringHelper.formatFloat((float)rc.getDistanceFromOriginToCollisionPoint()));
/*      */       } 
/*      */     }
/*      */     
/*  951 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean coordsExistInArray(int x, int y, int z, int[] coords, int num_coord_triplets) {
/*  956 */     for (int i = 0; i < num_coord_triplets; i++) {
/*      */       
/*  958 */       int offset = i * 3;
/*      */       
/*  960 */       if (coords[offset] == x && coords[offset + 1] == y && coords[offset + 2] == z) {
/*  961 */         return true;
/*      */       }
/*      */     } 
/*  964 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int[] getFullBlockIntercepts(Vec3 origin, Vec3 limit) {
/*  970 */     double distance = origin.distanceTo(limit);
/*      */     
/*  972 */     int[] coords = new int[((int)distance + 3) * 4 * 3];
/*  973 */     int num_coord_triplets = 1;
/*      */     
/*  975 */     coords[0] = origin.getBlockX();
/*  976 */     coords[1] = origin.getBlockY();
/*  977 */     coords[2] = origin.getBlockZ();
/*      */     
/*  979 */     int limit_x = limit.getBlockX();
/*  980 */     int limit_y = limit.getBlockY();
/*  981 */     int limit_z = limit.getBlockZ();
/*      */     
/*  983 */     Vec3 pos = origin.copy();
/*  984 */     Vec3 normalized_vector = origin.copy().setComponents(limit.xCoord - origin.xCoord, limit.yCoord - origin.yCoord, limit.zCoord - origin.zCoord).normalize();
/*      */     
/*  986 */     AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB();
/*      */ 
/*      */     
/*      */     while (true) {
/*  990 */       int x = pos.getBlockX();
/*  991 */       int y = pos.getBlockY();
/*  992 */       int z = pos.getBlockZ();
/*      */       
/*  994 */       for (int dx = -1; dx <= 1; dx++) {
/*      */         
/*  996 */         for (int dy = -1; dy <= 1; dy++) {
/*      */           
/*  998 */           for (int dz = -1; dz <= 1; dz++) {
/*      */             
/* 1000 */             if (!coordsExistInArray(x + dx, y + dy, z + dz, coords, num_coord_triplets)) {
/*      */ 
/*      */               
/* 1003 */               bb.setBounds(x + dx, y + dy, z + dz, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*      */               
/* 1005 */               AABBIntercept intercept = bb.calculateIntercept(null, origin, limit);
/*      */               
/* 1007 */               if (intercept != null) {
/*      */                 
/* 1009 */                 int offset = num_coord_triplets * 3;
/*      */                 
/* 1011 */                 coords[offset] = x + dx;
/* 1012 */                 coords[offset + 1] = y + dy;
/* 1013 */                 coords[offset + 2] = z + dz;
/*      */                 
/* 1015 */                 num_coord_triplets++;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1021 */       if (x == limit_x && y == limit_y && z == limit_z) {
/*      */         break;
/*      */       }
/* 1024 */       if (pos.distanceTo(origin) > distance) {
/*      */         break;
/*      */       }
/* 1027 */       pos.xCoord += normalized_vector.xCoord;
/* 1028 */       pos.yCoord += normalized_vector.yCoord;
/* 1029 */       pos.zCoord += normalized_vector.zCoord;
/*      */     } 
/*      */     
/* 1032 */     int[] coords_trimmed = new int[num_coord_triplets * 3];
/*      */     
/* 1034 */     System.arraycopy(coords, 0, coords_trimmed, 0, num_coord_triplets * 3);
/*      */     
/* 1036 */     return coords_trimmed;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Raycast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */