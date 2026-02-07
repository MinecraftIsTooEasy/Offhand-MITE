/*     */ package net.minecraft;
/*     */ 
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
/*     */ public class TileEntityEnchantmentTable
/*     */   extends TileEntity
/*     */ {
/*     */   public int tickCount;
/*     */   public float pageFlip;
/*     */   public float pageFlipPrev;
/*     */   public float field_70373_d;
/*     */   public float field_70374_e;
/*     */   public float bookSpread;
/*     */   public float bookSpreadPrev;
/*     */   public float bookRotation2;
/*     */   public float bookRotationPrev;
/*     */   public float bookRotation;
/*  26 */   private static Random rand = new Random();
/*     */ 
/*     */   
/*     */   private String field_94136_s;
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  34 */     super.writeToNBT(par1NBTTagCompound);
/*     */     
/*  36 */     if (func_94135_b())
/*     */     {
/*  38 */       par1NBTTagCompound.setString("CustomName", this.field_94136_s);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  47 */     super.readFromNBT(par1NBTTagCompound);
/*     */     
/*  49 */     if (par1NBTTagCompound.hasKey("CustomName"))
/*     */     {
/*  51 */       this.field_94136_s = par1NBTTagCompound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/*  61 */     super.updateEntity();
/*  62 */     this.bookSpreadPrev = this.bookSpread;
/*  63 */     this.bookRotationPrev = this.bookRotation2;
/*     */     
/*  65 */     EntityPlayer var1 = this.worldObj.getClosestPlayer((this.xCoord + 0.5F), (this.yCoord + 0.5F), (this.zCoord + 0.5F), 3.0D, true);
/*     */     
/*  67 */     if (var1 != null) {
/*     */       
/*  69 */       double var2 = var1.posX - (this.xCoord + 0.5F);
/*  70 */       double var4 = var1.posZ - (this.zCoord + 0.5F);
/*  71 */       this.bookRotation = (float)Math.atan2(var4, var2);
/*  72 */       this.bookSpread += 0.1F;
/*     */       
/*  74 */       if (this.bookSpread < 0.5F || rand.nextInt(40) == 0)
/*     */       {
/*  76 */         float var6 = this.field_70373_d;
/*     */ 
/*     */         
/*     */         do {
/*  80 */           this.field_70373_d += (rand.nextInt(4) - rand.nextInt(4));
/*     */         }
/*  82 */         while (var6 == this.field_70373_d);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  87 */       this.bookRotation += 0.02F;
/*  88 */       this.bookSpread -= 0.1F;
/*     */     } 
/*     */     
/*  91 */     while (this.bookRotation2 >= 3.1415927F)
/*     */     {
/*  93 */       this.bookRotation2 -= 6.2831855F;
/*     */     }
/*     */     
/*  96 */     while (this.bookRotation2 < -3.1415927F)
/*     */     {
/*  98 */       this.bookRotation2 += 6.2831855F;
/*     */     }
/*     */     
/* 101 */     while (this.bookRotation >= 3.1415927F)
/*     */     {
/* 103 */       this.bookRotation -= 6.2831855F;
/*     */     }
/*     */     
/* 106 */     while (this.bookRotation < -3.1415927F)
/*     */     {
/* 108 */       this.bookRotation += 6.2831855F;
/*     */     }
/*     */     
/*     */     float var7;
/*     */     
/* 113 */     for (var7 = this.bookRotation - this.bookRotation2; var7 >= 3.1415927F; var7 -= 6.2831855F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     while (var7 < -3.1415927F)
/*     */     {
/* 120 */       var7 += 6.2831855F;
/*     */     }
/*     */     
/* 123 */     this.bookRotation2 += var7 * 0.4F;
/*     */     
/* 125 */     if (this.bookSpread < 0.0F)
/*     */     {
/* 127 */       this.bookSpread = 0.0F;
/*     */     }
/*     */     
/* 130 */     if (this.bookSpread > 1.0F)
/*     */     {
/* 132 */       this.bookSpread = 1.0F;
/*     */     }
/*     */     
/* 135 */     this.tickCount++;
/* 136 */     this.pageFlipPrev = this.pageFlip;
/* 137 */     float var3 = (this.field_70373_d - this.pageFlip) * 0.4F;
/* 138 */     float var8 = 0.2F;
/*     */     
/* 140 */     if (var3 < -var8)
/*     */     {
/* 142 */       var3 = -var8;
/*     */     }
/*     */     
/* 145 */     if (var3 > var8)
/*     */     {
/* 147 */       var3 = var8;
/*     */     }
/*     */     
/* 150 */     this.field_70374_e += (var3 - this.field_70374_e) * 0.9F;
/* 151 */     this.pageFlip += this.field_70374_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_94133_a() {
/* 156 */     return func_94135_b() ? this.field_94136_s : "container.enchant";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94135_b() {
/* 161 */     return (this.field_94136_s != null && this.field_94136_s.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94134_a(String par1Str) {
/* 166 */     this.field_94136_s = par1Str;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityEnchantmentTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */