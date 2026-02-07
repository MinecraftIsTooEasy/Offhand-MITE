/*     */ package net.minecraft;
/*     */ 
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class McoClient
/*     */ {
/*     */   private final String field_96390_a;
/*     */   private final String field_100007_c;
/*  17 */   private static String field_96388_b = "https://mcoapi.minecraft.net/";
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
/*     */   public McoClient(Session session) {
/*  48 */     this.field_96390_a = session.getSessionID();
/*  49 */     this.field_100007_c = session.getUsername();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueObjectList func_96382_a() {
/*  54 */     String str = func_96377_a(Request.func_96358_a(field_96388_b + "worlds"));
/*  55 */     return ValueObjectList.func_98161_a(str);
/*     */   }
/*     */   
/*     */   public McoServer func_98176_a(long l) {
/*  59 */     String str = func_96377_a(Request.func_96358_a(field_96388_b + "worlds" + "/$ID".replace("$ID", String.valueOf(l))));
/*  60 */     return McoServer.func_98165_c(str);
/*     */   }
/*     */   
/*     */   public McoServerAddress func_96374_a(long l) {
/*  64 */     String str1 = field_96388_b + "worlds" + "/$ID/join".replace("$ID", "" + l);
/*  65 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/*  66 */     return McoServerAddress.func_98162_a(str2);
/*     */   }
/*     */   
/*     */   public void func_96386_a(String string, String string2, String string3, String string4) {
/*  70 */     StringBuilder stringBuilder = new StringBuilder();
/*  71 */     stringBuilder.append(field_96388_b).append("worlds").append("/$NAME/$LOCATION_ID".replace("$NAME", func_96380_a(string)));
/*     */     
/*  73 */     HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/*  74 */     if (string2 != null && !string2.trim().equals("")) {
/*  75 */       hashMap.put("motd", string2);
/*     */     }
/*  77 */     if (string3 != null && !string3.equals("")) {
/*  78 */       hashMap.put("seed", string3);
/*     */     }
/*  80 */     hashMap.put("template", string4);
/*     */     
/*  82 */     if (!hashMap.isEmpty()) {
/*  83 */       boolean bool = true;
/*  84 */       for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
/*  85 */         if (bool) {
/*  86 */           stringBuilder.append("?");
/*  87 */           bool = false;
/*     */         } else {
/*  89 */           stringBuilder.append("&");
/*     */         } 
/*  91 */         stringBuilder.append((String)entry.getKey()).append("=").append(func_96380_a((String)entry.getValue()));
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     func_96377_a(Request.func_104064_a(stringBuilder.toString(), "", 5000, 30000));
/*     */   }
/*     */   
/*     */   public Boolean func_96375_b() {
/*  99 */     String str1 = field_96388_b + "mco" + "/available";
/* 100 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/* 101 */     return Boolean.valueOf(str2);
/*     */   }
/*     */   
/*     */   public Boolean func_140054_c() {
/* 105 */     String str1 = field_96388_b + "mco" + "/client/outdated";
/* 106 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/* 107 */     return Boolean.valueOf(str2);
/*     */   }
/*     */   
/*     */   public int func_96379_c() {
/* 111 */     String str1 = field_96388_b + "payments" + "/unused";
/* 112 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/* 113 */     return Integer.valueOf(str2).intValue();
/*     */   }
/*     */   
/*     */   public void func_96381_a(long l, String string) {
/* 117 */     String str = field_96388_b + "invites" + "/$WORLD_ID/invite/$USER_NAME".replace("$WORLD_ID", String.valueOf(l)).replace("$USER_NAME", string);
/* 118 */     func_96377_a(Request.func_96355_b(str));
/*     */   }
/*     */   
/*     */   public void func_140055_c(long l) {
/* 122 */     String str = field_96388_b + "invites" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(l));
/* 123 */     func_96377_a(Request.func_96355_b(str));
/*     */   }
/*     */   
/*     */   public McoServer func_96387_b(long l, String string) {
/* 127 */     String str1 = field_96388_b + "invites" + "/$WORLD_ID/invite/$USER_NAME".replace("$WORLD_ID", String.valueOf(l)).replace("$USER_NAME", string);
/* 128 */     String str2 = func_96377_a(Request.func_96361_b(str1, ""));
/* 129 */     return McoServer.func_98165_c(str2);
/*     */   }
/*     */   
/*     */   public BackupList func_111232_c(long l) {
/* 133 */     String str1 = field_96388_b + "worlds" + "/$WORLD_ID/backups".replace("$WORLD_ID", String.valueOf(l));
/* 134 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/* 135 */     return BackupList.func_111222_a(str2);
/*     */   }
/*     */   
/*     */   public void func_96384_a(long l, String string, String string2, int i, int j) {
/* 139 */     StringBuilder stringBuilder = new StringBuilder();
/* 140 */     stringBuilder.append(field_96388_b).append("worlds").append("/$WORLD_ID/$NAME".replace("$WORLD_ID", String.valueOf(l)).replace("$NAME", func_96380_a(string)));
/*     */     
/* 142 */     if (string2 != null && !string2.trim().equals("")) { stringBuilder.append("?motd=").append(func_96380_a(string2)); }
/* 143 */     else { stringBuilder.append("?motd="); }
/* 144 */      stringBuilder.append("&difficulty=").append(i).append("&gameMode=").append(j);
/* 145 */     func_96377_a(Request.func_96363_c(stringBuilder.toString(), ""));
/*     */   }
/*     */   
/*     */   public void func_111235_c(long l, String string) {
/* 149 */     String str = field_96388_b + "worlds" + "/$WORLD_ID/backups".replace("$WORLD_ID", String.valueOf(l)) + "?backupId=" + string;
/* 150 */     func_96377_a(Request.func_96363_c(str, ""));
/*     */   }
/*     */   
/*     */   public WorldTemplateList func_111231_d() {
/* 154 */     String str1 = field_96388_b + "worlds" + "/templates";
/* 155 */     String str2 = func_96377_a(Request.func_96358_a(str1));
/* 156 */     return WorldTemplateList.func_110735_a(str2);
/*     */   }
/*     */   
/*     */   public Boolean func_96383_b(long l) {
/* 160 */     String str1 = field_96388_b + "worlds" + "/$WORLD_ID/open".replace("$WORLD_ID", String.valueOf(l));
/* 161 */     String str2 = func_96377_a(Request.func_96363_c(str1, ""));
/* 162 */     return Boolean.valueOf(str2);
/*     */   }
/*     */   
/*     */   public Boolean func_96378_c(long l) {
/* 166 */     String str1 = field_96388_b + "worlds" + "/$WORLD_ID/close".replace("$WORLD_ID", String.valueOf(l));
/* 167 */     String str2 = func_96377_a(Request.func_96363_c(str1, ""));
/* 168 */     return Boolean.valueOf(str2);
/*     */   }
/*     */   
/*     */   public Boolean func_96376_d(long l, String string) {
/* 172 */     StringBuilder stringBuilder = new StringBuilder();
/* 173 */     stringBuilder.append(field_96388_b).append("worlds").append("/$WORLD_ID/reset".replace("$WORLD_ID", String.valueOf(l)));
/* 174 */     if (string != null && string.length() > 0) stringBuilder.append("?seed=").append(func_96380_a(string));
/*     */     
/* 176 */     String str = func_96377_a(Request.func_96353_a(stringBuilder.toString(), "", 30000, 80000));
/* 177 */     return Boolean.valueOf(str);
/*     */   }
/*     */   
/*     */   public Boolean func_111233_e(long l, String string) {
/* 181 */     StringBuilder stringBuilder = new StringBuilder();
/* 182 */     stringBuilder.append(field_96388_b).append("worlds").append("/$WORLD_ID/reset".replace("$WORLD_ID", String.valueOf(l)));
/* 183 */     if (string != null) stringBuilder.append("?template=").append(string);
/*     */     
/* 185 */     String str = func_96377_a(Request.func_96353_a(stringBuilder.toString(), "", 30000, 80000));
/* 186 */     return Boolean.valueOf(str);
/*     */   }
/*     */   
/*     */   public ValueObjectSubscription func_98177_f(long l) {
/* 190 */     String str = func_96377_a(Request.func_96358_a(field_96388_b + "subscriptions" + "/$WORLD_ID".replace("$WORLD_ID", String.valueOf(l))));
/* 191 */     return ValueObjectSubscription.func_98169_a(str);
/*     */   }
/*     */   
/*     */   public int func_130106_e() {
/* 195 */     String str = func_96377_a(Request.func_96358_a(field_96388_b + "invites" + "/count/pending"));
/* 196 */     return Integer.parseInt(str);
/*     */   }
/*     */   
/*     */   public PendingInvitesList func_130108_f() {
/* 200 */     String str = func_96377_a(Request.func_96358_a(field_96388_b + "invites" + "/pending"));
/* 201 */     return PendingInvitesList.func_130095_a(str);
/*     */   }
/*     */   
/*     */   public void func_130107_a(String string) {
/* 205 */     func_96377_a(Request.func_96363_c(field_96388_b + "invites" + "/accept/$INVITATION_ID".replace("$INVITATION_ID", string), ""));
/*     */   }
/*     */   
/*     */   public void func_130109_b(String string) {
/* 209 */     func_96377_a(Request.func_96363_c(field_96388_b + "invites" + "/reject/$INVITATION_ID".replace("$INVITATION_ID", string), ""));
/*     */   }
/*     */   
/*     */   private String func_96380_a(String string) {
/* 213 */     return URLEncoder.encode(string, "UTF-8");
/*     */   }
/*     */   
/*     */   private String func_96377_a(Request request) {
/* 217 */     request.func_100006_a("sid", this.field_96390_a);
/* 218 */     request.func_100006_a("user", this.field_100007_c);
/* 219 */     request.func_100006_a("version", "1.6.4");
/*     */     try {
/* 221 */       int i = request.func_96362_a();
/* 222 */       if (i == 503) {
/* 223 */         int j = request.func_111221_b();
/* 224 */         throw new ExceptionRetryCall(j);
/*     */       } 
/*     */       
/* 227 */       if (i < 200 || i >= 300) {
/* 228 */         throw new ExceptionMcoService(request.func_96362_a(), request.func_96364_c(), request.func_130110_g());
/*     */       }
/* 230 */       return request.func_96364_c();
/* 231 */     } catch (ExceptionMcoHttp exceptionMcoHttp) {
/* 232 */       throw new ExceptionMcoService(500, "Server not available!", -1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */