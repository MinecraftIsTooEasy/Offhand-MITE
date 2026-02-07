/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.Proxy;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public class HttpUtil
/*     */ {
/*     */   public static String buildPostString(Map par0Map) {
/*  33 */     StringBuilder var1 = new StringBuilder();
/*  34 */     Iterator<Map.Entry> var2 = par0Map.entrySet().iterator();
/*     */     
/*  36 */     while (var2.hasNext()) {
/*     */       
/*  38 */       Map.Entry var3 = var2.next();
/*     */       
/*  40 */       if (var1.length() > 0)
/*     */       {
/*  42 */         var1.append('&');
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/*  47 */         var1.append(URLEncoder.encode((String)var3.getKey(), "UTF-8"));
/*     */       }
/*  49 */       catch (UnsupportedEncodingException var6) {
/*     */         
/*  51 */         var6.printStackTrace();
/*     */       } 
/*     */       
/*  54 */       if (var3.getValue() != null) {
/*     */         
/*  56 */         var1.append('=');
/*     */ 
/*     */         
/*     */         try {
/*  60 */           var1.append(URLEncoder.encode(var3.getValue().toString(), "UTF-8"));
/*     */         }
/*  62 */         catch (UnsupportedEncodingException var5) {
/*     */           
/*  64 */           var5.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String sendPost(ILogAgent par0ILogAgent, URL par1URL, Map par2Map, boolean par3) {
/*  77 */     return sendPost(par0ILogAgent, par1URL, buildPostString(par2Map), par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String sendPost(ILogAgent par0ILogAgent, URL par1URL, String par2Str, boolean par3) {
/*     */     try {
/*  87 */       Proxy var4 = (MinecraftServer.getServer() == null) ? null : MinecraftServer.getServer().getServerProxy();
/*     */       
/*  89 */       if (var4 == null)
/*     */       {
/*  91 */         var4 = Proxy.NO_PROXY;
/*     */       }
/*     */       
/*  94 */       HttpURLConnection var5 = (HttpURLConnection)par1URL.openConnection(var4);
/*  95 */       var5.setRequestMethod("POST");
/*  96 */       var5.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*  97 */       var5.setRequestProperty("Content-Length", "" + (par2Str.getBytes()).length);
/*  98 */       var5.setRequestProperty("Content-Language", "en-US");
/*  99 */       var5.setUseCaches(false);
/* 100 */       var5.setDoInput(true);
/* 101 */       var5.setDoOutput(true);
/* 102 */       DataOutputStream var6 = new DataOutputStream(var5.getOutputStream());
/* 103 */       var6.writeBytes(par2Str);
/* 104 */       var6.flush();
/* 105 */       var6.close();
/* 106 */       BufferedReader var7 = new BufferedReader(new InputStreamReader(var5.getInputStream()));
/* 107 */       StringBuffer var9 = new StringBuffer();
/*     */       
/*     */       String var8;
/* 110 */       while ((var8 = var7.readLine()) != null) {
/*     */         
/* 112 */         var9.append(var8);
/* 113 */         var9.append('\r');
/*     */       } 
/*     */       
/* 116 */       var7.close();
/* 117 */       return var9.toString();
/*     */     }
/* 119 */     catch (Exception var10) {
/*     */       
/* 121 */       if (!par3)
/*     */       {
/* 123 */         if (par0ILogAgent != null) {
/*     */           
/* 125 */           par0ILogAgent.logSevereException("Could not post to " + par1URL, var10);
/*     */         }
/*     */         else {
/*     */           
/* 129 */           Logger.getAnonymousLogger().log(Level.SEVERE, "Could not post to " + par1URL, var10);
/*     */         } 
/*     */       }
/*     */       
/* 133 */       return "";
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int func_76181_a() throws IOException {
/*     */     int var10;
/* 139 */     ServerSocket var0 = null;
/* 140 */     boolean var1 = true;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 145 */       var0 = new ServerSocket(0);
/* 146 */       var10 = var0.getLocalPort();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 152 */         if (var0 != null)
/*     */         {
/* 154 */           var0.close();
/*     */         }
/*     */       }
/* 157 */       catch (IOException var8) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     return var10;
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
/*     */   public static String performGetRequest(String url_string, int connection_timeout_ms, int read_timeout_ms) {
/*     */     try {
/* 227 */       URLConnection c = (new URL(url_string)).openConnection();
/*     */       
/* 229 */       c.setConnectTimeout(connection_timeout_ms);
/* 230 */       c.setReadTimeout(read_timeout_ms);
/*     */       
/* 232 */       InputStream is = c.getInputStream();
/*     */       
/* 234 */       StringWriter sw = new StringWriter();
/*     */       
/* 236 */       IOUtils.copy(is, sw, "UTF-8");
/*     */       
/* 238 */       String s = sw.toString();
/*     */       
/* 240 */       is.close();
/*     */       
/* 242 */       return s;
/*     */     }
/* 244 */     catch (Exception e) {
/*     */       
/* 246 */       if (Minecraft.inDevMode()) {
/* 247 */         System.out.println("performGetRequest(" + url_string + "): " + e.toString());
/*     */       }
/* 249 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\HttpUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */