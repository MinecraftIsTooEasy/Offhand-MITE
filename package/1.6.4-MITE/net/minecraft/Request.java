/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Request
/*     */ {
/*     */   protected HttpURLConnection field_96367_a;
/*     */   private boolean field_96366_c;
/*     */   protected String field_96365_b;
/*     */   
/*     */   public Request(String string, int i, int j) {
/*     */     try {
/*  24 */       this.field_96365_b = string;
/*  25 */       this.field_96367_a = (HttpURLConnection)(new URL(string)).openConnection(Minecraft.getMinecraft().getProxy());
/*  26 */       this.field_96367_a.setConnectTimeout(i);
/*  27 */       this.field_96367_a.setReadTimeout(j);
/*  28 */     } catch (Exception exception) {
/*  29 */       throw new ExceptionMcoHttp("Failed URL: " + string, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_100006_a(String string, String string2) {
/*  34 */     String str = this.field_96367_a.getRequestProperty("Cookie");
/*  35 */     if (str == null) {
/*  36 */       this.field_96367_a.setRequestProperty("Cookie", string + "=" + string2);
/*     */     } else {
/*  38 */       this.field_96367_a.setRequestProperty("Cookie", str + ";" + string + "=" + string2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_96362_a() {
/*     */     try {
/*  50 */       func_96354_d();
/*  51 */       return this.field_96367_a.getResponseCode();
/*  52 */     } catch (Exception exception) {
/*  53 */       throw new ExceptionMcoHttp("Failed URL: " + this.field_96365_b, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int func_111221_b() {
/*  58 */     String str = this.field_96367_a.getHeaderField("Retry-After");
/*     */     try {
/*  60 */       return Integer.valueOf(str).intValue();
/*  61 */     } catch (Exception exception) {
/*  62 */       return 5;
/*     */     } 
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
/*     */   public String func_96364_c() {
/*     */     try {
/*  78 */       func_96354_d();
/*  79 */       String str = (func_96362_a() >= 400) ? func_96352_a(this.field_96367_a.getErrorStream()) : func_96352_a(this.field_96367_a.getInputStream());
/*  80 */       func_96360_f();
/*  81 */       return str;
/*  82 */     } catch (IOException iOException) {
/*  83 */       throw new ExceptionMcoHttp("Failed URL: " + this.field_96365_b, iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String func_96352_a(InputStream inputStream) {
/*  88 */     if (inputStream == null) throw new IOException("No response (null)");
/*     */     
/*  90 */     StringBuilder stringBuilder = new StringBuilder();
/*  91 */     for (int i = inputStream.read(); i != -1; ) { stringBuilder.append((char)i); i = inputStream.read(); }
/*     */     
/*  93 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private void func_96360_f() {
/*  97 */     byte[] arrayOfByte = new byte[1024];
/*     */     try {
/*  99 */       int i = 0;
/* 100 */       InputStream inputStream = this.field_96367_a.getInputStream();
/* 101 */       while ((i = inputStream.read(arrayOfByte)) > 0);
/*     */ 
/*     */       
/* 104 */       inputStream.close();
/* 105 */     } catch (Exception exception) {
/*     */       try {
/* 107 */         InputStream inputStream = this.field_96367_a.getErrorStream();
/* 108 */         int i = 0;
/*     */         
/* 110 */         while ((i = inputStream.read(arrayOfByte)) > 0);
/*     */ 
/*     */         
/* 113 */         inputStream.close();
/* 114 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Request func_96354_d() {
/* 121 */     if (!this.field_96366_c) {
/* 122 */       Request request = func_96359_e();
/* 123 */       this.field_96366_c = true;
/* 124 */       return request;
/*     */     } 
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract Request func_96359_e();
/*     */   
/*     */   public static Request func_96358_a(String string) {
/* 133 */     return new RequestGet(string, 5000, 10000);
/*     */   }
/*     */   
/*     */   public static Request func_96361_b(String string, String string2) {
/* 137 */     return new RequestPost(string, string2.getBytes(), 5000, 10000);
/*     */   }
/*     */   
/*     */   public static Request func_104064_a(String string, String string2, int i, int j) {
/* 141 */     return new RequestPost(string, string2.getBytes(), i, j);
/*     */   }
/*     */   
/*     */   public static Request func_96355_b(String string) {
/* 145 */     return new RequestDelete(string, 5000, 10000);
/*     */   }
/*     */   
/*     */   public static Request func_96363_c(String string, String string2) {
/* 149 */     return new RequestPut(string, string2.getBytes(), 5000, 10000);
/*     */   }
/*     */   
/*     */   public static Request func_96353_a(String string, String string2, int i, int j) {
/* 153 */     return new RequestPut(string, string2.getBytes(), i, j);
/*     */   }
/*     */   
/*     */   public int func_130110_g() {
/* 157 */     String str = this.field_96367_a.getHeaderField("Error-Code");
/*     */     try {
/* 159 */       return Integer.valueOf(str).intValue();
/* 160 */     } catch (Exception exception) {
/* 161 */       return -1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */