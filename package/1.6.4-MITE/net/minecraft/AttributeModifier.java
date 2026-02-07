/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeModifier
/*     */ {
/*     */   private double amount;
/*     */   private final int operation;
/*     */   private final String name;
/*     */   private final UUID id;
/*     */   private boolean isSaved;
/*     */   
/*     */   public AttributeModifier(String par1Str, double par2, int par4) {
/*  21 */     this(UUID.randomUUID(), par1Str, par2, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributeModifier(UUID par1UUID, String par2Str, double par3, int par5) {
/*  26 */     this.isSaved = true;
/*  27 */     this.id = par1UUID;
/*  28 */     this.name = par2Str;
/*  29 */     this.amount = par3;
/*  30 */     this.operation = par5;
/*  31 */     Validate.notEmpty(par2Str, "Modifier name cannot be empty", new Object[0]);
/*  32 */     Validate.inclusiveBetween(Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(par5), "Invalid operation", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getID() {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  42 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOperation() {
/*  47 */     return this.operation;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAmount() {
/*  52 */     return this.amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAmount(double amount) {
/*  57 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSaved() {
/*  65 */     return this.isSaved;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeModifier setSaved(boolean par1) {
/*  73 */     this.isSaved = par1;
/*  74 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/*  79 */     if (this == par1Obj)
/*     */     {
/*  81 */       return true;
/*     */     }
/*  83 */     if (par1Obj != null && getClass() == par1Obj.getClass()) {
/*     */       
/*  85 */       AttributeModifier var2 = (AttributeModifier)par1Obj;
/*     */       
/*  87 */       if (this.id != null) {
/*     */         
/*  89 */         if (!this.id.equals(var2.id))
/*     */         {
/*  91 */           return false;
/*     */         }
/*     */       }
/*  94 */       else if (var2.id != null) {
/*     */         
/*  96 */         return false;
/*     */       } 
/*     */       
/*  99 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 109 */     return (this.id != null) ? this.id.hashCode() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     return "AttributeModifier{amount=" + this.amount + ", operation=" + this.operation + ", name='" + this.name + '\'' + ", id=" + this.id + ", serialize=" + this.isSaved + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AttributeModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */