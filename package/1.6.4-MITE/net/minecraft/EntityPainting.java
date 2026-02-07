/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPainting
/*     */   extends EntityHanging
/*     */ {
/*     */   public EnumArt art;
/*     */   
/*     */   public EntityPainting(World par1World) {
/*  13 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPainting(World par1World, int par2, int par3, int par4, int par5) {
/*  18 */     super(par1World, par2, par3, par4, par5);
/*  19 */     ArrayList<EnumArt> var6 = new ArrayList();
/*  20 */     EnumArt[] var7 = EnumArt.values();
/*  21 */     int var8 = var7.length;
/*     */     
/*  23 */     for (int var9 = 0; var9 < var8; var9++) {
/*     */       
/*  25 */       EnumArt var10 = var7[var9];
/*  26 */       this.art = var10;
/*  27 */       setDirection(par5);
/*     */       
/*  29 */       if (onValidSurface())
/*     */       {
/*  31 */         var6.add(var10);
/*     */       }
/*     */     } 
/*     */     
/*  35 */     if (!var6.isEmpty()) {
/*     */       do
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  78 */         this.art = var6.get(this.rand.nextInt(var6.size()));
/*     */       }
/*  80 */       while (var6.size() != 1 && this.art.rarity > 1 && this.rand.nextInt(this.art.rarity) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  85 */     setDirection(par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPainting(World par1World, int par2, int par3, int par4, int par5, String par6Str) {
/*  90 */     this(par1World, par2, par3, par4, par5);
/*  91 */     EnumArt[] var7 = EnumArt.values();
/*  92 */     int var8 = var7.length;
/*     */     
/*  94 */     for (int var9 = 0; var9 < var8; var9++) {
/*     */       
/*  96 */       EnumArt var10 = var7[var9];
/*     */       
/*  98 */       if (var10.title.equals(par6Str)) {
/*     */         
/* 100 */         this.art = var10;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 105 */     setDirection(par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 113 */     par1NBTTagCompound.setString("Motive", this.art.title);
/* 114 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 122 */     String var2 = par1NBTTagCompound.getString("Motive");
/* 123 */     EnumArt[] var3 = EnumArt.values();
/* 124 */     int var4 = var3.length;
/*     */     
/* 126 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 128 */       EnumArt var6 = var3[var5];
/*     */       
/* 130 */       if (var6.title.equals(var2))
/*     */       {
/* 132 */         this.art = var6;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     if (this.art == null)
/*     */     {
/* 138 */       this.art = EnumArt.Kebab;
/*     */     }
/*     */     
/* 141 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidthPixels() {
/* 146 */     return this.art.sizeX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/* 151 */     return this.art.sizeY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity par1Entity) {
/* 159 */     if (par1Entity instanceof EntityPlayer) {
/*     */       
/* 161 */       EntityPlayer var2 = (EntityPlayer)par1Entity;
/*     */       
/* 163 */       if (var2.capabilities.isCreativeMode) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 169 */     dropItemStack(new ItemStack(Item.painting), 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 174 */     return Item.painting;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 179 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPainting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */