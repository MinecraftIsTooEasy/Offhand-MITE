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
/*     */ public class BlockConstants
/*     */ {
/*     */   private boolean is_always_legal = true;
/*     */   private boolean never_hides_adjacent_faces;
/*     */   private boolean uses_new_sand_physics;
/*     */   private boolean is_always_immutable = false;
/*     */   private boolean uses_alpha_blending;
/*     */   private Boolean connects_with_fence;
/*     */   
/*     */   public void validate(Block block) {
/*  29 */     String msg = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (!(block instanceof BlockStairs))
/*     */     {
/*  90 */       if (this.uses_alpha_blending) {
/*     */         
/*  92 */         if (block.getRenderBlockPass() != 1) {
/*  93 */           msg = block + " uses alpha blending but getRenderBlockPass()==" + block.getRenderBlockPass();
/*     */         
/*     */         }
/*     */       }
/*  97 */       else if (block.getRenderBlockPass() != 0) {
/*  98 */         msg = block + " does not use alpha blending but getRenderBlockPass()==" + block.getRenderBlockPass();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (msg == null) {
/*     */       return;
/*     */     }
/* 125 */     Minecraft.setErrorMessage("validate: " + msg);
/* 126 */     (new Exception()).printStackTrace();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockConstants setNotAlwaysLegal() {
/* 131 */     this.is_always_legal = false;
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockConstants setAlwaysImmutable() {
/* 138 */     this.is_always_legal = true;
/* 139 */     this.is_always_immutable = true;
/* 140 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockConstants setUsesAlphaBlending() {
/* 146 */     this.uses_alpha_blending = true;
/* 147 */     return setNeverHidesAdjacentFaces();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockConstants setNeverHidesAdjacentFaces() {
/* 152 */     this.connects_with_fence = Boolean.valueOf(false);
/* 153 */     this.never_hides_adjacent_faces = true;
/* 154 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockConstants setUseNewSandPhysics() {
/* 160 */     if (!Minecraft.allow_new_sand_physics) {
/* 161 */       return this;
/*     */     }
/* 163 */     this.uses_new_sand_physics = true;
/* 164 */     this.is_always_legal = false;
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockConstants setAlwaysConnectsWithFence() {
/* 170 */     this.connects_with_fence = Boolean.valueOf(true);
/* 171 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockConstants setNeverConnectsWithFence() {
/* 176 */     this.connects_with_fence = Boolean.valueOf(false);
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlwaysLegal() {
/* 182 */     return this.is_always_legal;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean neverHidesAdjacentFaces() {
/* 187 */     return this.never_hides_adjacent_faces;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlwaysImmutable() {
/* 192 */     return this.is_always_immutable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean usesNewSandPhysics() {
/* 197 */     return this.uses_new_sand_physics;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean connectsWithFence() {
/* 202 */     return this.connects_with_fence;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */