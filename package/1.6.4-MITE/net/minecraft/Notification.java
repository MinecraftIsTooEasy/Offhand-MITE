/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Notification
/*     */   extends Thread
/*     */ {
/*     */   final EntityClientPlayerMP player;
/*     */   
/*     */   Notification(EntityClientPlayerMP player) {
/*  18 */     this.player = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  23 */     if (this.player == null || this.player.username == null) {
/*     */       return;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  56 */       String enc = "nrmvxizug-rh-gll-vzhb.xln/orhgvmvi.ksk";
/*     */       
/*  58 */       String url_string = "http://" + gmos(enc) + "?op=player&r=" + 'Ä';
/*     */       
/*  60 */       if (this.player.sendQueue.getNetManager() instanceof TcpConnection) {
/*     */         
/*  62 */         url_string = url_string + "&rs=" + StringHelper.stripLeading("/", "" + this.player.sendQueue.getNetManager().getSocketAddress());
/*     */         
/*  64 */         url_string = url_string + "&st=" + (Minecraft.is_dedicated_server_running ? "DS" : "LAN");
/*     */       } 
/*     */       
/*  67 */       url_string = url_string + "&un=" + this.player.username;
/*     */       
/*  69 */       URL url = new URL(url_string);
/*     */       
/*  71 */       InputStream is = url.openStream();
/*     */       
/*  73 */       StringWriter sw = new StringWriter();
/*     */       
/*  75 */       IOUtils.copy(is, sw, "UTF-8");
/*     */       
/*  77 */       String s = sw.toString();
/*     */       
/*  79 */       if (is != null) {
/*  80 */         is.close();
/*     */       }
/*  82 */     } catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static String gmos(String s) {
/*  87 */     char[] chars = s.toCharArray();
/*     */     
/*  89 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/*  91 */       int c = chars[i];
/*     */       
/*  93 */       if (c >= 65 && c <= 90) {
/*  94 */         c = 90 - c - 65;
/*  95 */       } else if (c >= 97 && c <= 122) {
/*  96 */         c = 122 - c - 97;
/*  97 */       } else if (c >= 48 && c <= 57) {
/*  98 */         c = 57 - c - 48;
/*     */       } 
/* 100 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/* 103 */     return new String(chars);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Notification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */