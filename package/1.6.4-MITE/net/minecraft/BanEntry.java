/*     */ package net.minecraft;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BanEntry
/*     */ {
/*  13 */   public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*     */   
/*     */   private final String username;
/*     */   
/*  17 */   private Date banStartDate = new Date();
/*  18 */   private String bannedBy = "(Unknown)";
/*     */   private Date banEndDate;
/*  20 */   private String reason = "Banned by an operator.";
/*     */   
/*     */   public BanEntry(String string) {
/*  23 */     this.username = string;
/*     */   }
/*     */   
/*     */   public String getBannedUsername() {
/*  27 */     return this.username;
/*     */   }
/*     */   
/*     */   public Date getBanStartDate() {
/*  31 */     return this.banStartDate;
/*     */   }
/*     */   
/*     */   public void setBanStartDate(Date date) {
/*  35 */     this.banStartDate = (date != null) ? date : new Date();
/*     */   }
/*     */   
/*     */   public String getBannedBy() {
/*  39 */     return this.bannedBy;
/*     */   }
/*     */   
/*     */   public void setBannedBy(String string) {
/*  43 */     this.bannedBy = string;
/*     */   }
/*     */   
/*     */   public Date getBanEndDate() {
/*  47 */     return this.banEndDate;
/*     */   }
/*     */   
/*     */   public void setBanEndDate(Date date) {
/*  51 */     this.banEndDate = date;
/*     */   }
/*     */   
/*     */   public boolean hasBanExpired() {
/*  55 */     if (this.banEndDate == null) return false; 
/*  56 */     return this.banEndDate.before(new Date());
/*     */   }
/*     */   
/*     */   public String getBanReason() {
/*  60 */     return this.reason;
/*     */   }
/*     */   
/*     */   public void setBanReason(String string) {
/*  64 */     this.reason = string;
/*     */   }
/*     */   
/*     */   public String buildBanString() {
/*  68 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*  70 */     stringBuilder.append(getBannedUsername());
/*  71 */     stringBuilder.append("|");
/*     */     
/*  73 */     stringBuilder.append(dateFormat.format(getBanStartDate()));
/*  74 */     stringBuilder.append("|");
/*     */     
/*  76 */     stringBuilder.append(getBannedBy());
/*  77 */     stringBuilder.append("|");
/*     */     
/*  79 */     stringBuilder.append((getBanEndDate() == null) ? "Forever" : dateFormat.format(getBanEndDate()));
/*  80 */     stringBuilder.append("|");
/*     */     
/*  82 */     stringBuilder.append(getBanReason());
/*     */     
/*  84 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static BanEntry parse(String string) {
/*  88 */     if (string.trim().length() < 2) return null;
/*     */     
/*  90 */     String[] arrayOfString = string.trim().split(Pattern.quote("|"), 5);
/*  91 */     BanEntry banEntry = new BanEntry(arrayOfString[0].trim());
/*  92 */     byte b = 0;
/*     */     
/*  94 */     if (arrayOfString.length <= ++b) return banEntry; 
/*     */     try {
/*  96 */       banEntry.setBanStartDate(dateFormat.parse(arrayOfString[b].trim()));
/*  97 */     } catch (ParseException parseException) {
/*  98 */       MinecraftServer.getServer().getLogAgent().logWarningException("Could not read creation date format for ban entry '" + banEntry.getBannedUsername() + "' (was: '" + arrayOfString[b] + "')", parseException);
/*     */     } 
/*     */     
/* 101 */     if (arrayOfString.length <= ++b) return banEntry; 
/* 102 */     banEntry.setBannedBy(arrayOfString[b].trim());
/*     */     
/* 104 */     if (arrayOfString.length <= ++b) return banEntry; 
/*     */     try {
/* 106 */       String str = arrayOfString[b].trim();
/* 107 */       if (!str.equalsIgnoreCase("Forever") && str.length() > 0) {
/* 108 */         banEntry.setBanEndDate(dateFormat.parse(str));
/*     */       }
/* 110 */     } catch (ParseException parseException) {
/* 111 */       MinecraftServer.getServer().getLogAgent().logWarningException("Could not read expiry date format for ban entry '" + banEntry.getBannedUsername() + "' (was: '" + arrayOfString[b] + "')", parseException);
/*     */     } 
/*     */     
/* 114 */     if (arrayOfString.length <= ++b) return banEntry; 
/* 115 */     banEntry.setBanReason(arrayOfString[b].trim());
/*     */     
/* 117 */     return banEntry;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BanEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */