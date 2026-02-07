/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.main.Main;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLookHelper
/*     */ {
/*     */   private EntityLiving entity;
/*     */   private float deltaLookYaw;
/*     */   private float deltaLookPitch;
/*     */   private boolean isLooking;
/*     */   private double posX;
/*     */   private double posY;
/*     */   private double posZ;
/*     */   private boolean performing_look_vector_override;
/*     */   
/*     */   public EntityLookHelper(EntityLiving par1EntityLiving) {
/*  36 */     this.entity = par1EntityLiving;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Class getTheClass() {
/*  41 */     return EntityLookHelper.class;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLookPositionWithEntity(Entity par1Entity, float par2, float par3) {
/*  49 */     if (!this.performing_look_vector_override && lookVectorOverridden()) {
/*     */       return;
/*     */     }
/*  52 */     this.posX = par1Entity.posX;
/*     */     
/*  54 */     if (par1Entity instanceof EntityLivingBase) {
/*     */       
/*  56 */       this.posY = par1Entity.posY + par1Entity.getEyeHeight();
/*     */     }
/*     */     else {
/*     */       
/*  60 */       this.posY = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2.0D;
/*     */     } 
/*     */     
/*  63 */     this.posZ = par1Entity.posZ;
/*  64 */     this.deltaLookYaw = par2;
/*  65 */     this.deltaLookPitch = par3;
/*  66 */     this.isLooking = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLookPosition(double par1, double par3, double par5, float par7, float par8) {
/*  74 */     if (lookVectorOverridden()) {
/*     */       return;
/*     */     }
/*  77 */     this.posX = par1;
/*  78 */     this.posY = par3;
/*  79 */     this.posZ = par5;
/*  80 */     this.deltaLookYaw = par7;
/*  81 */     this.deltaLookPitch = par8;
/*  82 */     this.isLooking = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean lookVectorOverridden() {
/*  87 */     if (this.entity instanceof EntityTameable) {
/*     */       
/*  89 */       EntityTameable entity_tameable = (EntityTameable)this.entity;
/*     */       
/*  91 */       EntityLiving threatening_entity = entity_tameable.getThreateningEntity();
/*     */       
/*  93 */       if (threatening_entity != null) {
/*     */         
/*  95 */         this.performing_look_vector_override = true;
/*  96 */         setLookPositionWithEntity(threatening_entity, 10.0F, this.entity.getVerticalFaceSpeed());
/*  97 */         this.performing_look_vector_override = false;
/*  98 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getYawForLookingAt(Vec3 eye_pos, Vec3 target_pos) {
/* 108 */     return (float)(Math.atan2(eye_pos.xCoord - target_pos.xCoord, target_pos.zCoord - eye_pos.zCoord) * 180.0D / Math.PI);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getPitchForLookingAt(Vec3 eye_pos, Vec3 target_pos) {
/* 113 */     double delta_x = target_pos.xCoord - eye_pos.xCoord;
/* 114 */     double delta_z = target_pos.zCoord - eye_pos.zCoord;
/*     */     
/* 116 */     double horizontal_distance = MathHelper.sqrt_double(delta_x * delta_x + delta_z * delta_z);
/*     */     
/* 118 */     double delta_y = target_pos.yCoord - eye_pos.yCoord;
/*     */     
/* 120 */     return (float)-(Math.atan2(delta_y, horizontal_distance) * 180.0D / Math.PI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateLook() {
/* 128 */     this.entity.rotationPitch = 0.0F;
/*     */     
/* 130 */     if (this.isLooking) {
/*     */       
/* 132 */       this.isLooking = false;
/* 133 */       double var1 = this.posX - this.entity.posX;
/* 134 */       double var3 = this.posY - this.entity.posY + this.entity.getEyeHeight();
/* 135 */       double var5 = this.posZ - this.entity.posZ;
/* 136 */       double var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
/* 137 */       float var9 = (float)(Math.atan2(var5, var1) * 180.0D / Math.PI) - 90.0F;
/* 138 */       float var10 = (float)-(Math.atan2(var3, var7) * 180.0D / Math.PI);
/* 139 */       this.entity.rotationPitch = updateRotation(this.entity.rotationPitch, var10, this.deltaLookPitch);
/* 140 */       this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, var9, this.deltaLookYaw);
/*     */     }
/*     */     else {
/*     */       
/* 144 */       this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, 10.0F);
/*     */     } 
/*     */     
/* 147 */     float var11 = MathHelper.wrapAngleTo180_float(this.entity.rotationYawHead - this.entity.renderYawOffset);
/*     */     
/* 149 */     if (!this.entity.getNavigator().noPath()) {
/*     */       
/* 151 */       if (var11 < -75.0F)
/*     */       {
/* 153 */         this.entity.rotationYawHead = this.entity.renderYawOffset - 75.0F;
/*     */       }
/*     */       
/* 156 */       if (var11 > 75.0F)
/*     */       {
/* 158 */         this.entity.rotationYawHead = this.entity.renderYawOffset + 75.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float updateRotation(float par1, float par2, float par3) {
/* 165 */     float var4 = MathHelper.wrapAngleTo180_float(par2 - par1);
/*     */     
/* 167 */     if (var4 > par3)
/*     */     {
/* 169 */       var4 = par3;
/*     */     }
/*     */     
/* 172 */     if (var4 < -par3)
/*     */     {
/* 174 */       var4 = -par3;
/*     */     }
/*     */     
/* 177 */     return par1 + var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String rv(String s) {
/* 182 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 184 */     int i = s.length();
/*     */     
/* 186 */     while (--i >= 0) {
/* 187 */       sb.append(s.charAt(i));
/*     */     }
/* 189 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String dC() {
/* 194 */     return rv("ssalc.");
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isDv() {
/* 199 */     InputStream is = Minecraft.class.getResourceAsStream(flip("zge") + dC());
/*     */     
/* 201 */     return (is == null);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getCSTtl(String[][] flipped_names) {
/* 206 */     int ttl = 0;
/*     */     
/* 208 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 210 */     String newline = new String(System.getProperty("line.separator").getBytes());
/*     */     
/* 212 */     for (int i = 0; i < flipped_names.length; i++) {
/*     */       
/* 214 */       int cs = getCC(Minecraft.class, flipped_names[i]);
/*     */ 
/*     */ 
/*     */       
/* 218 */       ttl += cs;
/*     */     } 
/*     */ 
/*     */     
/* 222 */     ttl += getCC(Main.class, new String[] { "Nzrm", "Nzrm" });
/*     */ 
/*     */     
/* 225 */     ttl += getCC(MinecraftServer.class, new String[] { "NrmvxizugHvievi", "NrmvxizugHvievi" });
/*     */     
/* 227 */     if (ttl < 0) {
/* 228 */       ttl = -ttl;
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
/* 243 */     Vec3.SPL(flip(rv("HXG")) + rv("" + ttl));
/*     */     
/* 245 */     return ttl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 253 */     if (!isDv()) {
/*     */ 
/*     */ 
/*     */       
/* 257 */       String[][] s = { { "ZmeroXsfmpOlzwvi", "zvv" }, { "Yolxp", "zja" }, { "YolxpXsvhg", "zmp" }, { "Xsfmp", "zwi" }, { "XsfmpOlzwvi", "zwa" }, { "XlnnzmwSzmwovi", "zz" }, { "WvwrxzgvwHvievi", "rh" }, { "VmgrgbXorvmgKozbviNK", "ywr" }, { "VmgrgbRgvn", "hh" }, { "VmgrgbOllpSvokvi", "kv" }, { "VmgrgbKozbvi", "fu" }, { "VmgrgbKozbviNK", "qe" }, { "VmgrgbKozbviHK", "yvc" }, { "VmfnVjfrknvmgNzgvirzo", "VmfnVjfrknvmgNzgvirzo" }, { "VmfnHrtmzo", "VmfnHrtmzo" }, { "UllwHgzgh", "fc" }, { "RmgvtizgvwHvievi", "ypa" }, { "RmevmglibKozbvi", "fw" }, { "Rgvn", "bx" }, { "RgvnZinli", "ds" }, { "RgvnYllgh", "RgvnYllgh" }, { "RgvnXfrizhh", "RgvnXfrizhh" }, { "RgvnSvonvg", "RgvnSvonvg" }, { "RgvnOvttrmth", "RgvnOvttrmth" }, { "RgvnRmDliowNzmztvi", "qd" }, { "RgvnHgzxp", "bv" }, { "RgvnGllo", "cq" }, { "RgvnZcv", "bz" }, { "RgvnYzggovZcv", "RgvnYzggovZcv" }, { "RgvnXofy", "RgvnXofy" }, { "RgvnXfwtvo", "RgvnXfwtvo" }, { "RgvnWzttvi", "RgvnWzttvi" }, { "RgvnSzgxsvg", "RgvnSzgxsvg" }, { "RgvnSlv", "by" }, { "RgvnPmruv", "RgvnPmruv" }, { "RgvnNzgglxp", "RgvnNzgglxp" }, { "RgvnKrxpzcv", "bm" }, { "RgvnHxbgsv", "RgvnHxbgsv" }, { "RgvnHsvzih", "bc" }, { "RgvnHslevo", "RgvnHslevo" }, { "RgvnHdliw", "ao" }, { "RgvnDziSznnvi", "RgvnDziSznnvi" }, { "NzgvirzoNrmSzievhgOvevo", "NzgvirzoNrmSzievhgOvevo" }, { "Nrmvxizug", "zge" }, { "NRGVXlmhgzmg", "NRGVXlmhgzmg" }, { "NRGVXlmgzrmviXizugrmt", "NRGVXlmgzrmviXizugrmt" }, { "NlevnvmgRmkfgUilnLkgrlmh", "yvd" }, { "MYGYzhv", "xo" }, { "MYGGztYbgv", "yc" }, { "MYGGztXlnklfmw", "yb" }, { "MvgXorvmgSzmwovi", "yxd" }, { "MvgSzmwovi", "va" }, { "MvgOltrmSzmwovi", "qb" }, { "MvgHvieviSzmwovi", "pz" }, { "Mlgrurxzgrlm", "Mlgrurxzgrlm" }, { "Kzxpvg7XorvmgKilglxlo", "wj" }, { "Kzxpvg86KozbviOllpNlev", "vd" }, { "Kzxpvg72KozbviRmkfg", "uv" }, { "Kzxpvg14HrnkovHrtmzo", "Kzxpvg14HrnkovHrtmzo" }, { "Kzxpvg797KozbviZyrorgrvh", "uz" }, { "KozbviXzkzyrorgrvh", "fx" }, { "KozbviXlmgilooviNK", "ywx" }, { "HvieviXlmurtfizgrlmNzmztvi", "sm" }, { "HllmvhgIvxlmmvxgrlmGrnv", "HllmvhgIvxlmmvxgrlmGrnv" }, { "HgirmtSvokvi", "HgirmtSvokvi" }, { "GxkXlmmvxgrlm", "xl" }, { "GvcgfivwJfzw", "yyh" }, { "GlloNzgvirzoWznztv", "GlloNzgvirzoWznztv" }, { "GlloNzgvirzoSzievhgVuurxrvmxb", "GlloNzgvirzoSzievhgVuurxrvmxb" }, { "Dliow", "zyd" }, { "DliowXorvmg", "yww" }, { "DliowRmul", "zoh" }, { "DliowHvievi", "qh" }, { "DliowHvggrmth", "zxw" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*     */         String cn;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 348 */         StringBuffer sb = new StringBuffer();
/*     */         
/* 350 */         if (isDv()) {
/*     */           
/* 352 */           cn = flip("mvg.nrmvxizug.hix.") + rv("khc");
/* 353 */           sb.append(flip(rv("ew")));
/*     */         }
/*     */         else {
/*     */           
/* 357 */           cn = rv("khc");
/* 358 */           sb.append(flip(rv("ik")));
/*     */         } 
/*     */         
/* 361 */         int cs = Class.forName(cn).getDeclaredField(sb.toString()).getInt(null);
/*     */         
/* 363 */         if (getCSTtl(s) != cs) {
/* 364 */           SEN();
/*     */         }
/* 366 */       } catch (Exception e) {
/*     */         
/* 368 */         SEN();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void SEN() {
/*     */     try {
/* 377 */       Class.forName(flip("qzez.ozmt.Hbhgvn")).getDeclaredMethod(flip("vcrg"), new Class[] { int.class }).invoke(null, new Object[] { Integer.valueOf(0) });
/*     */     }
/* 379 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static String flip(String s) {
/* 384 */     char[] chars = s.toCharArray();
/*     */     
/* 386 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/* 388 */       int c = chars[i];
/*     */       
/* 390 */       if (c >= 65 && c <= 90) {
/* 391 */         c = 90 - c - 65;
/* 392 */       } else if (c >= 97 && c <= 122) {
/* 393 */         c = 122 - c - 97;
/* 394 */       } else if (c >= 48 && c <= 57) {
/* 395 */         c = 57 - c - 48;
/*     */       } 
/* 397 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/* 400 */     return new String(chars);
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] getCD(Class peer, String[] cns) {
/* 405 */     InputStream is = peer.getResourceAsStream(flip(cns[0]) + dC());
/*     */     
/* 407 */     if (is == null) {
/* 408 */       is = peer.getResourceAsStream(flip(cns[1]) + dC());
/*     */     }
/* 410 */     if (is != null) {
/*     */       
/*     */       try {
/*     */         
/* 414 */         byte[] bytes = IOUtils.toByteArray(is);
/* 415 */         return bytes;
/*     */       }
/* 417 */       catch (Exception e) {}
/*     */     }
/*     */     
/* 420 */     SEN();
/*     */     
/* 422 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCC(Class peer, String[] cns) {
/* 427 */     byte[] bytes = getCD(peer, cns);
/*     */     
/* 429 */     if (bytes == null) {
/* 430 */       return (new Random()).nextInt();
/*     */     }
/* 432 */     int sum = 0;
/*     */     
/* 434 */     for (int i = 0; i < bytes.length; i++) {
/* 435 */       sum += bytes[i] * (i + 1);
/*     */     }
/* 437 */     return sum;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLookHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */