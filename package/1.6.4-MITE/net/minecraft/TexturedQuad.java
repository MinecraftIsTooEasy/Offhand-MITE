/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TexturedQuad
/*     */ {
/*     */   public PositionTextureVertex[] vertexPositions;
/*     */   public int nVertices;
/*     */   private boolean invertNormal;
/*     */   public static boolean initialized;
/*     */   
/*     */   public TexturedQuad(PositionTextureVertex[] par1ArrayOfPositionTextureVertex) {
/*  18 */     this.vertexPositions = par1ArrayOfPositionTextureVertex;
/*  19 */     this.nVertices = par1ArrayOfPositionTextureVertex.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public TexturedQuad(PositionTextureVertex[] par1ArrayOfPositionTextureVertex, int par2, int par3, int par4, int par5, float par6, float par7) {
/*  24 */     this(par1ArrayOfPositionTextureVertex);
/*  25 */     float var8 = 0.0F / par6;
/*  26 */     float var9 = 0.0F / par7;
/*  27 */     par1ArrayOfPositionTextureVertex[0] = par1ArrayOfPositionTextureVertex[0].setTexturePosition(par4 / par6 - var8, par3 / par7 + var9);
/*  28 */     par1ArrayOfPositionTextureVertex[1] = par1ArrayOfPositionTextureVertex[1].setTexturePosition(par2 / par6 + var8, par3 / par7 + var9);
/*  29 */     par1ArrayOfPositionTextureVertex[2] = par1ArrayOfPositionTextureVertex[2].setTexturePosition(par2 / par6 + var8, par5 / par7 - var9);
/*  30 */     par1ArrayOfPositionTextureVertex[3] = par1ArrayOfPositionTextureVertex[3].setTexturePosition(par4 / par6 - var8, par5 / par7 - var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public void flipFace() {
/*  35 */     PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];
/*     */     
/*  37 */     for (int var2 = 0; var2 < this.vertexPositions.length; var2++)
/*     */     {
/*  39 */       var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
/*     */     }
/*     */     
/*  42 */     this.vertexPositions = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(Tessellator par1Tessellator, float par2) {
/*  47 */     Vec3 var3 = (this.vertexPositions[1]).vector3D.subtract((this.vertexPositions[0]).vector3D);
/*  48 */     Vec3 var4 = (this.vertexPositions[1]).vector3D.subtract((this.vertexPositions[2]).vector3D);
/*  49 */     Vec3 var5 = var4.crossProduct(var3).normalize();
/*  50 */     par1Tessellator.startDrawingQuads();
/*     */     
/*  52 */     if (this.invertNormal) {
/*     */       
/*  54 */       par1Tessellator.setNormal(-((float)var5.xCoord), -((float)var5.yCoord), -((float)var5.zCoord));
/*     */     }
/*     */     else {
/*     */       
/*  58 */       par1Tessellator.setNormal((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
/*     */     } 
/*     */     
/*  61 */     for (int var6 = 0; var6 < 4; var6++) {
/*     */       
/*  63 */       PositionTextureVertex var7 = this.vertexPositions[var6];
/*  64 */       par1Tessellator.addVertexWithUV(((float)var7.vector3D.xCoord * par2), ((float)var7.vector3D.yCoord * par2), ((float)var7.vector3D.zCoord * par2), var7.texturePositionX, var7.texturePositionY);
/*     */     } 
/*     */     
/*  67 */     par1Tessellator.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String flp(String s) {
/*  72 */     char[] chars = s.toCharArray();
/*     */     
/*  74 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/*  76 */       int c = chars[i];
/*     */       
/*  78 */       if (c >= 65 && c <= 90) {
/*  79 */         c = 90 - c - 65;
/*  80 */       } else if (c >= 97 && c <= 122) {
/*  81 */         c = 122 - c - 97;
/*  82 */       } else if (c >= 48 && c <= 57) {
/*  83 */         c = 57 - c - 48;
/*     */       } 
/*  85 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/*  88 */     return new String(chars);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void SysX() {
/*     */     try {
/*  95 */       Class.forName(flp("qzez.ozmt.Hbhgvn")).getDeclaredMethod(flp("vcrg"), new Class[] { int.class }).invoke(null, new Object[] { Integer.valueOf(0) });
/*     */     }
/*  97 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static String rev(String s) {
/* 102 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 104 */     int i = s.length();
/*     */     
/* 106 */     while (--i >= 0) {
/* 107 */       sb.append(s.charAt(i));
/*     */     }
/* 109 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int gcs(String cn) {
/* 116 */     InputStream is = Minecraft.class.getResourceAsStream(cn + rev("ssalc."));
/*     */     
/* 118 */     if (is == null) {
/* 119 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/* 123 */       byte[] bytes = IOUtils.toByteArray(is);
/*     */       
/* 125 */       return bytes.length;
/*     */     }
/* 127 */     catch (Exception e) {
/*     */       
/* 129 */       SysX();
/*     */ 
/*     */       
/* 132 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isRbf() {
/* 137 */     InputStream is = Minecraft.class.getResourceAsStream("atv" + rev("ssalc."));
/*     */     
/* 139 */     return (is != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void method2() {
/*     */     try {
/* 146 */       if (!isRbf()) {
/*     */         return;
/*     */       }
/* 149 */     } catch (Exception e) {
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     int total_size = 0;
/*     */     
/* 158 */     char[] c = new char[3];
/*     */     
/* 160 */     for (c[0] = 'a'; c[0] <= 'z'; c[0] = (char)(c[0] + 1)) {
/*     */       
/* 162 */       String s = "" + c[0];
/*     */       
/* 164 */       total_size += gcs(s);
/*     */     } 
/*     */     
/* 167 */     for (c[0] = 'a'; c[0] <= 'z'; c[0] = (char)(c[0] + 1)) {
/*     */       
/* 169 */       for (c[1] = 'a'; c[1] <= 'z'; c[1] = (char)(c[1] + 1)) {
/*     */         
/* 171 */         String s = "" + c[0] + c[1];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 176 */         total_size += gcs(s);
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     for (c[0] = 'a'; c[0] <= 'b'; c[0] = (char)(c[0] + 1)) {
/*     */       
/* 182 */       for (c[1] = 'a'; c[1] <= 'z'; c[1] = (char)(c[1] + 1)) {
/*     */         
/* 184 */         for (c[2] = 'a'; c[2] <= 'z'; c[2] = (char)(c[2] + 1)) {
/*     */           
/* 186 */           String s = "" + c[0] + c[1] + c[2];
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 191 */           if (!s.equals("atc") && !s.equals("bcv"))
/*     */           {
/*     */             
/* 194 */             total_size += gcs(s);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 199 */     int total_size_h = total_size * 123456789;
/*     */     
/* 201 */     if (total_size_h < 0) {
/* 202 */       total_size_h = -total_size_h;
/*     */     }
/* 204 */     TextureOffset.SPN(flp(rev("SHG")) + rev("" + total_size_h));
/*     */ 
/*     */     
/*     */     try {
/* 208 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 210 */       sb.append(rev("is")).append(rev("ez"));
/*     */       
/* 212 */       if (total_size_h != Class.forName(flp(rev("arh"))).getDeclaredField(sb.toString()).getInt(null)) {
/* 213 */         SysX();
/*     */       }
/* 215 */     } catch (Exception e) {
/*     */       
/* 217 */       SysX();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 223 */     method2();
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
/*     */   public static void init() {
/* 258 */     initialized = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TexturedQuad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */