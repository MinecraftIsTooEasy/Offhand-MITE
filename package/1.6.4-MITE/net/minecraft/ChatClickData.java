/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatClickData
/*    */ {
/* 13 */   public static final Pattern pattern = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$");
/*    */   private final FontRenderer fontR;
/*    */   private final ChatLine line;
/*    */   private final int field_78312_d;
/*    */   private final int field_78313_e;
/*    */   private final String field_78310_f;
/*    */   private final String clickedUrl;
/*    */   
/*    */   public ChatClickData(FontRenderer fontRenderer, ChatLine chatLine, int i, int j) {
/* 22 */     this.fontR = fontRenderer;
/* 23 */     this.line = chatLine;
/* 24 */     this.field_78312_d = i;
/* 25 */     this.field_78313_e = j;
/* 26 */     this.field_78310_f = fontRenderer.trimStringToWidth(chatLine.getChatLineString(), i);
/*    */     
/* 28 */     this.clickedUrl = findClickedUrl();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getClickedUrl() {
/* 52 */     return this.clickedUrl;
/*    */   }
/*    */   
/*    */   public URI getURI() {
/* 56 */     String str = getClickedUrl();
/* 57 */     if (str == null) return null;
/*    */     
/* 59 */     Matcher matcher = pattern.matcher(str);
/*    */     
/* 61 */     if (matcher.matches()) {
/*    */       try {
/* 63 */         String str1 = matcher.group(0);
/* 64 */         if (matcher.group(1) == null) {
/* 65 */           str1 = "http://" + str1;
/*    */         }
/*    */         
/* 68 */         return new URI(str1);
/* 69 */       } catch (URISyntaxException uRISyntaxException) {
/* 70 */         Minecraft.getMinecraft().getLogAgent().logSevereException("Couldn't create URI from chat", uRISyntaxException);
/*    */       } 
/*    */     }
/*    */     
/* 74 */     return null;
/*    */   }
/*    */   
/*    */   private String findClickedUrl() {
/* 78 */     int i = this.field_78310_f.lastIndexOf(" ", this.field_78310_f.length()) + 1;
/* 79 */     if (i < 0) i = 0; 
/* 80 */     int j = this.line.getChatLineString().indexOf(" ", i);
/* 81 */     if (j < 0) j = this.line.getChatLineString().length();
/*    */     
/* 83 */     return StringUtils.stripControlCodes(this.line.getChatLineString().substring(i, j));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChatClickData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */