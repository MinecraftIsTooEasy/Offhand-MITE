/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CombatTracker
/*     */ {
/*   9 */   private final List field_94556_a = new ArrayList();
/*     */   
/*     */   private final EntityLivingBase fighter;
/*     */   
/*     */   private int field_94555_c;
/*     */   
/*     */   private boolean field_94552_d;
/*     */   private boolean field_94553_e;
/*     */   private String field_94551_f;
/*     */   
/*     */   public CombatTracker(EntityLivingBase par1EntityLivingBase) {
/*  20 */     this.fighter = par1EntityLivingBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94545_a() {
/*  25 */     func_94542_g();
/*     */     
/*  27 */     if (this.fighter.isOnLadder()) {
/*     */       
/*  29 */       int var1 = this.fighter.worldObj.getBlockId(MathHelper.floor_double(this.fighter.posX), MathHelper.floor_double(this.fighter.boundingBox.minY), MathHelper.floor_double(this.fighter.posZ));
/*     */       
/*  31 */       if (var1 == Block.ladder.blockID)
/*     */       {
/*  33 */         this.field_94551_f = "ladder";
/*     */       }
/*  35 */       else if (var1 == Block.vine.blockID)
/*     */       {
/*  37 */         this.field_94551_f = "vines";
/*     */       }
/*     */     
/*  40 */     } else if (this.fighter.isInWater()) {
/*     */       
/*  42 */       this.field_94551_f = "water";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94547_a(DamageSource par1DamageSource, float par2, float par3) {
/*  48 */     func_94549_h();
/*  49 */     func_94545_a();
/*  50 */     CombatEntry var4 = new CombatEntry(par1DamageSource, this.fighter.ticksExisted, par2, par3, this.field_94551_f, this.fighter.fallDistance);
/*  51 */     this.field_94556_a.add(var4);
/*  52 */     this.field_94555_c = this.fighter.ticksExisted;
/*  53 */     this.field_94553_e = true;
/*  54 */     this.field_94552_d |= var4.func_94559_f();
/*     */   }
/*     */   
/*     */   public ChatMessageComponent func_94546_b() {
/*     */     ChatMessageComponent var3;
/*  59 */     if (this.field_94556_a.size() == 0)
/*     */     {
/*  61 */       return ChatMessageComponent.createFromTranslationWithSubstitutions("death.attack.generic", new Object[] { this.fighter.getTranslatedEntityName() });
/*     */     }
/*     */ 
/*     */     
/*  65 */     CombatEntry var1 = func_94544_f();
/*  66 */     CombatEntry var2 = this.field_94556_a.get(this.field_94556_a.size() - 1);
/*  67 */     String var4 = var2.func_94558_h();
/*     */     
/*  69 */     Entity var5 = var2.getDamageSrc().getResponsibleEntity();
/*     */ 
/*     */     
/*  72 */     if (var1 != null && var2.getDamageSrc() == DamageSource.fall) {
/*     */       
/*  74 */       String var6 = var1.func_94558_h();
/*     */       
/*  76 */       if (var1.getDamageSrc() != DamageSource.fall && var1.getDamageSrc() != DamageSource.outOfWorld) {
/*     */         
/*  78 */         if (var6 != null && (var4 == null || !var6.equals(var4))) {
/*     */ 
/*     */           
/*  81 */           Entity var9 = var1.getDamageSrc().getResponsibleEntity();
/*  82 */           ItemStack var8 = (var9 instanceof EntityLivingBase) ? ((EntityLivingBase)var9).getHeldItemStack() : null;
/*     */           
/*  84 */           if (var8 != null && var8.hasDisplayName())
/*     */           {
/*  86 */             var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.assist.item", new Object[] { this.fighter.getTranslatedEntityName(), var6, var8.getDisplayName() });
/*     */           }
/*     */           else
/*     */           {
/*  90 */             var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.assist", new Object[] { this.fighter.getTranslatedEntityName(), var6 });
/*     */           }
/*     */         
/*  93 */         } else if (var4 != null) {
/*     */           
/*  95 */           ItemStack var7 = (var5 instanceof EntityLivingBase) ? ((EntityLivingBase)var5).getHeldItemStack() : null;
/*     */           
/*  97 */           if (var7 != null && var7.hasDisplayName())
/*     */           {
/*  99 */             var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.finish.item", new Object[] { this.fighter.getTranslatedEntityName(), var4, var7.getDisplayName() });
/*     */           }
/*     */           else
/*     */           {
/* 103 */             var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.finish", new Object[] { this.fighter.getTranslatedEntityName(), var4 });
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 108 */           var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.killer", new Object[] { this.fighter.getTranslatedEntityName() });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 113 */         var3 = ChatMessageComponent.createFromTranslationWithSubstitutions("death.fell.accident." + func_94548_b(var1), new Object[] { this.fighter.getTranslatedEntityName() });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 118 */       var3 = var2.getDamageSrc().getDeathMessage(this.fighter);
/*     */     } 
/*     */     
/* 121 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_94550_c() {
/* 127 */     EntityLivingBase var1 = null;
/* 128 */     EntityPlayer var2 = null;
/* 129 */     float var3 = 0.0F;
/* 130 */     float var4 = 0.0F;
/* 131 */     Iterator<CombatEntry> var5 = this.field_94556_a.iterator();
/*     */     
/* 133 */     while (var5.hasNext()) {
/*     */       
/* 135 */       CombatEntry var6 = var5.next();
/*     */ 
/*     */       
/* 138 */       if (var6.getDamageSrc().getResponsibleEntity() instanceof EntityPlayer && (var2 == null || var6.func_94563_c() > var4)) {
/*     */         
/* 140 */         var4 = var6.func_94563_c();
/*     */         
/* 142 */         var2 = (EntityPlayer)var6.getDamageSrc().getResponsibleEntity();
/*     */       } 
/*     */ 
/*     */       
/* 146 */       if (var6.getDamageSrc().getResponsibleEntity() instanceof EntityLivingBase && (var1 == null || var6.func_94563_c() > var3)) {
/*     */         
/* 148 */         var3 = var6.func_94563_c();
/*     */         
/* 150 */         var1 = (EntityLivingBase)var6.getDamageSrc().getResponsibleEntity();
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     if (var2 != null && var4 >= var3 / 3.0F)
/*     */     {
/* 156 */       return var2;
/*     */     }
/*     */ 
/*     */     
/* 160 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CombatEntry func_94544_f() {
/* 166 */     CombatEntry var1 = null;
/* 167 */     CombatEntry var2 = null;
/* 168 */     byte var3 = 0;
/* 169 */     float var4 = 0.0F;
/*     */     
/* 171 */     for (int var5 = 0; var5 < this.field_94556_a.size(); var5++) {
/*     */       
/* 173 */       CombatEntry var6 = this.field_94556_a.get(var5);
/* 174 */       CombatEntry var7 = (var5 > 0) ? this.field_94556_a.get(var5 - 1) : null;
/*     */       
/* 176 */       if ((var6.getDamageSrc() == DamageSource.fall || var6.getDamageSrc() == DamageSource.outOfWorld) && var6.func_94561_i() > 0.0F && (var1 == null || var6.func_94561_i() > var4)) {
/*     */         
/* 178 */         if (var5 > 0) {
/*     */           
/* 180 */           var1 = var7;
/*     */         }
/*     */         else {
/*     */           
/* 184 */           var1 = var6;
/*     */         } 
/*     */         
/* 187 */         var4 = var6.func_94561_i();
/*     */       } 
/*     */       
/* 190 */       if (var6.func_94562_g() != null && (var2 == null || var6.func_94563_c() > var3))
/*     */       {
/* 192 */         var2 = var6;
/*     */       }
/*     */     } 
/*     */     
/* 196 */     if (var4 > 5.0F && var1 != null)
/*     */     {
/* 198 */       return var1;
/*     */     }
/* 200 */     if (var3 > 5 && var2 != null)
/*     */     {
/* 202 */       return var2;
/*     */     }
/*     */ 
/*     */     
/* 206 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String func_94548_b(CombatEntry par1CombatEntry) {
/* 212 */     return (par1CombatEntry.func_94562_g() == null) ? "generic" : par1CombatEntry.func_94562_g();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_94542_g() {
/* 217 */     this.field_94551_f = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_94549_h() {
/* 222 */     int var1 = this.field_94552_d ? 300 : 100;
/*     */     
/* 224 */     if (this.field_94553_e && this.fighter.ticksExisted - this.field_94555_c > var1) {
/*     */       
/* 226 */       this.field_94556_a.clear();
/* 227 */       this.field_94553_e = false;
/* 228 */       this.field_94552_d = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CombatTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */