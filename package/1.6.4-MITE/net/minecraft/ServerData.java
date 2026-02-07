/*     */ package net.minecraft;
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
/*     */ public class ServerData
/*     */ {
/*     */   public String serverName;
/*     */   public String serverIP;
/*     */   public String populationInfo;
/*     */   public String serverMOTD;
/*     */   public long pingToServer;
/*  22 */   public int field_82821_f = 78;
/*     */ 
/*     */   
/*  25 */   public String gameVersion = "1.6.4";
/*     */   
/*     */   public boolean field_78841_f;
/*     */   
/*     */   private boolean field_78842_g = true;
/*     */   
/*     */   private boolean acceptsTextures;
/*     */   
/*     */   private boolean hideAddress;
/*     */   
/*     */   public final boolean is_preset;
/*     */   
/*     */   public String start_date;
/*     */   
/*     */   public String description;
/*     */   public String website;
/*     */   public String image_url;
/*     */   public int theme_color;
/*     */   public float backdrop_opacity;
/*     */   
/*     */   public ServerData(String par1Str, String par2Str) {
/*  46 */     this(par1Str, par2Str, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerData(String par1Str, String par2Str, boolean is_preset) {
/*  51 */     this.serverName = par1Str;
/*  52 */     this.serverIP = par2Str;
/*     */     
/*  54 */     this.is_preset = is_preset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTCompound() {
/*  62 */     NBTTagCompound var1 = new NBTTagCompound();
/*  63 */     var1.setString("name", this.serverName);
/*  64 */     var1.setString("ip", this.serverIP);
/*  65 */     var1.setBoolean("hideAddress", this.hideAddress);
/*     */     
/*  67 */     if (!this.field_78842_g)
/*     */     {
/*  69 */       var1.setBoolean("acceptTextures", this.acceptsTextures);
/*     */     }
/*     */     
/*  72 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAcceptsTextures(boolean par1) {
/*  77 */     this.acceptsTextures = par1;
/*  78 */     this.field_78842_g = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHidingAddress() {
/*  83 */     return this.hideAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHideAddress(boolean par1) {
/*  88 */     this.hideAddress = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerData getServerDataFromNBTCompound(NBTTagCompound par0NBTTagCompound) {
/*  96 */     ServerData var1 = new ServerData(par0NBTTagCompound.getString("name"), par0NBTTagCompound.getString("ip"));
/*  97 */     var1.hideAddress = par0NBTTagCompound.getBoolean("hideAddress");
/*     */     
/*  99 */     if (par0NBTTagCompound.hasKey("acceptTextures"))
/*     */     {
/* 101 */       var1.setAcceptsTextures(par0NBTTagCompound.getBoolean("acceptTextures"));
/*     */     }
/*     */     
/* 104 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerData setInfo(String start_date, String description, String website, String image_url, int theme_color, float backdrop_opacity) {
/* 109 */     this.start_date = start_date;
/* 110 */     this.description = description;
/* 111 */     this.website = website;
/* 112 */     this.image_url = image_url;
/* 113 */     this.theme_color = theme_color;
/* 114 */     this.backdrop_opacity = backdrop_opacity;
/*     */     
/* 116 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInfo() {
/* 121 */     if (this.start_date != null && !this.start_date.isEmpty()) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (this.description != null && !this.description.isEmpty()) {
/* 125 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (this.image_url != null && !this.image_url.isEmpty()) {
/* 131 */       return true;
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasWebsite() {
/* 138 */     return (this.website != null && !this.website.isEmpty());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */