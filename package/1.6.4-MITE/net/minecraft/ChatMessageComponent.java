/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ChatMessageComponent
/*     */ {
/*  11 */   private static final Gson field_111089_a = (new GsonBuilder()).registerTypeAdapter(ChatMessageComponent.class, new MessageComponentSerializer()).create();
/*     */   
/*     */   private EnumChatFormatting color;
/*     */   private Boolean bold;
/*     */   private Boolean italic;
/*     */   private Boolean underline;
/*     */   private Boolean obfuscated;
/*     */   private String text;
/*     */   private String translationKey;
/*     */   private List field_111091_i;
/*     */   
/*     */   public ChatMessageComponent() {}
/*     */   
/*     */   public ChatMessageComponent(ChatMessageComponent par1ChatMessageComponent) {
/*  25 */     this.color = par1ChatMessageComponent.color;
/*  26 */     this.bold = par1ChatMessageComponent.bold;
/*  27 */     this.italic = par1ChatMessageComponent.italic;
/*  28 */     this.underline = par1ChatMessageComponent.underline;
/*  29 */     this.obfuscated = par1ChatMessageComponent.obfuscated;
/*  30 */     this.text = par1ChatMessageComponent.text;
/*  31 */     this.translationKey = par1ChatMessageComponent.translationKey;
/*  32 */     this.field_111091_i = (par1ChatMessageComponent.field_111091_i == null) ? null : Lists.newArrayList(par1ChatMessageComponent.field_111091_i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent setColor(EnumChatFormatting par1EnumChatFormatting) {
/*  37 */     if (par1EnumChatFormatting != null && !par1EnumChatFormatting.isColor())
/*     */     {
/*  39 */       throw new IllegalArgumentException("Argument is not a valid color!");
/*     */     }
/*     */ 
/*     */     
/*  43 */     this.color = par1EnumChatFormatting;
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumChatFormatting getColor() {
/*  50 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent setBold(Boolean par1) {
/*  55 */     this.bold = par1;
/*  56 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isBold() {
/*  61 */     return this.bold;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent setItalic(Boolean par1) {
/*  66 */     this.italic = par1;
/*  67 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isItalic() {
/*  72 */     return this.italic;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent setUnderline(Boolean par1) {
/*  77 */     this.underline = par1;
/*  78 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isUnderline() {
/*  83 */     return this.underline;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent setObfuscated(Boolean par1) {
/*  88 */     this.obfuscated = par1;
/*  89 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isObfuscated() {
/*  94 */     return this.obfuscated;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getText() {
/*  99 */     return this.text;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTranslationKey() {
/* 104 */     return this.translationKey;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List getSubComponents() {
/* 109 */     return this.field_111091_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent appendComponent(ChatMessageComponent par1ChatMessageComponent) {
/* 114 */     if (this.text == null && this.translationKey == null) {
/*     */       
/* 116 */       if (this.field_111091_i != null)
/*     */       {
/* 118 */         this.field_111091_i.add(par1ChatMessageComponent);
/*     */       }
/*     */       else
/*     */       {
/* 122 */         this.field_111091_i = Lists.newArrayList((Object[])new ChatMessageComponent[] { par1ChatMessageComponent });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 127 */       this.field_111091_i = Lists.newArrayList((Object[])new ChatMessageComponent[] { new ChatMessageComponent(this), par1ChatMessageComponent });
/* 128 */       this.text = null;
/* 129 */       this.translationKey = null;
/*     */     } 
/*     */     
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessageComponent addText(String par1Str) {
/* 137 */     if (this.text == null && this.translationKey == null) {
/*     */       
/* 139 */       if (this.field_111091_i != null)
/*     */       {
/* 141 */         this.field_111091_i.add(createFromText(par1Str));
/*     */       }
/*     */       else
/*     */       {
/* 145 */         this.text = par1Str;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 150 */       this.field_111091_i = Lists.newArrayList((Object[])new ChatMessageComponent[] { new ChatMessageComponent(this), createFromText(par1Str) });
/* 151 */       this.text = null;
/* 152 */       this.translationKey = null;
/*     */     } 
/*     */     
/* 155 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatMessageComponent addKey(String par1Str) {
/* 163 */     if (this.text == null && this.translationKey == null) {
/*     */       
/* 165 */       if (this.field_111091_i != null)
/*     */       {
/* 167 */         this.field_111091_i.add(createFromTranslationKey(par1Str));
/*     */       }
/*     */       else
/*     */       {
/* 171 */         this.translationKey = par1Str;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 176 */       this.field_111091_i = Lists.newArrayList((Object[])new ChatMessageComponent[] { new ChatMessageComponent(this), createFromTranslationKey(par1Str) });
/* 177 */       this.text = null;
/* 178 */       this.translationKey = null;
/*     */     } 
/*     */     
/* 181 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatMessageComponent addFormatted(String par1Str, Object... par2ArrayOfObj) {
/* 190 */     if (this.text == null && this.translationKey == null) {
/*     */       
/* 192 */       if (this.field_111091_i != null) {
/*     */         
/* 194 */         this.field_111091_i.add(createFromTranslationWithSubstitutions(par1Str, par2ArrayOfObj));
/*     */       }
/*     */       else {
/*     */         
/* 198 */         this.translationKey = par1Str;
/* 199 */         this.field_111091_i = Lists.newArrayList();
/* 200 */         Object[] var3 = par2ArrayOfObj;
/* 201 */         int var4 = par2ArrayOfObj.length;
/*     */         
/* 203 */         for (int var5 = 0; var5 < var4; var5++) {
/*     */           
/* 205 */           Object var6 = var3[var5];
/*     */           
/* 207 */           if (var6 instanceof ChatMessageComponent)
/*     */           {
/* 209 */             this.field_111091_i.add((ChatMessageComponent)var6);
/*     */           }
/*     */           else
/*     */           {
/* 213 */             this.field_111091_i.add(createFromText(var6.toString()));
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 220 */       this.field_111091_i = Lists.newArrayList((Object[])new ChatMessageComponent[] { new ChatMessageComponent(this), createFromTranslationWithSubstitutions(par1Str, par2ArrayOfObj) });
/* 221 */       this.text = null;
/* 222 */       this.translationKey = null;
/*     */     } 
/*     */     
/* 225 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 230 */     return toStringWithFormatting(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toStringWithFormatting(boolean par1) {
/* 235 */     return toStringWithDefaultFormatting(par1, (EnumChatFormatting)null, false, false, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringWithDefaultFormatting(boolean par1, EnumChatFormatting par2EnumChatFormatting, boolean par3, boolean par4, boolean par5, boolean par6) {
/* 243 */     StringBuilder var7 = new StringBuilder();
/* 244 */     EnumChatFormatting var8 = (this.color == null) ? par2EnumChatFormatting : this.color;
/* 245 */     boolean var9 = (this.bold == null) ? par3 : this.bold.booleanValue();
/* 246 */     boolean var10 = (this.italic == null) ? par4 : this.italic.booleanValue();
/* 247 */     boolean var11 = (this.underline == null) ? par5 : this.underline.booleanValue();
/* 248 */     boolean var12 = (this.obfuscated == null) ? par6 : this.obfuscated.booleanValue();
/*     */     
/* 250 */     if (this.translationKey != null) {
/*     */       
/* 252 */       if (par1)
/*     */       {
/* 254 */         appendFormattingToString(var7, var8, var9, var10, var11, var12);
/*     */       }
/*     */       
/* 257 */       if (this.field_111091_i != null)
/*     */       {
/* 259 */         String[] var13 = new String[this.field_111091_i.size()];
/*     */         
/* 261 */         for (int var14 = 0; var14 < this.field_111091_i.size(); var14++)
/*     */         {
/* 263 */           var13[var14] = ((ChatMessageComponent)this.field_111091_i.get(var14)).toStringWithDefaultFormatting(par1, var8, var9, var10, var11, var12);
/*     */         }
/*     */ 
/*     */         
/* 267 */         var7.append(StatCollector.translateToLocalFormatted(this.translationKey, (Object[])var13));
/*     */       }
/*     */       else
/*     */       {
/* 271 */         var7.append(StatCollector.translateToLocal(this.translationKey));
/*     */       }
/*     */     
/* 274 */     } else if (this.text != null) {
/*     */       
/* 276 */       if (par1)
/*     */       {
/* 278 */         appendFormattingToString(var7, var8, var9, var10, var11, var12);
/*     */       }
/*     */       
/* 281 */       var7.append(this.text);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 287 */     else if (this.field_111091_i != null) {
/*     */       
/* 289 */       for (Iterator<ChatMessageComponent> var15 = this.field_111091_i.iterator(); var15.hasNext(); var7.append(var16.toStringWithDefaultFormatting(par1, var8, var9, var10, var11, var12))) {
/*     */         
/* 291 */         ChatMessageComponent var16 = var15.next();
/*     */         
/* 293 */         if (par1)
/*     */         {
/* 295 */           appendFormattingToString(var7, var8, var9, var10, var11, var12);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 301 */     return var7.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendFormattingToString(StringBuilder par0StringBuilder, EnumChatFormatting par1EnumChatFormatting, boolean par2, boolean par3, boolean par4, boolean par5) {
/* 306 */     if (par1EnumChatFormatting != null) {
/*     */       
/* 308 */       par0StringBuilder.append(par1EnumChatFormatting);
/*     */     }
/* 310 */     else if (par2 || par3 || par4 || par5) {
/*     */       
/* 312 */       par0StringBuilder.append(EnumChatFormatting.RESET);
/*     */     } 
/*     */     
/* 315 */     if (par2)
/*     */     {
/* 317 */       par0StringBuilder.append(EnumChatFormatting.BOLD);
/*     */     }
/*     */     
/* 320 */     if (par3)
/*     */     {
/* 322 */       par0StringBuilder.append(EnumChatFormatting.ITALIC);
/*     */     }
/*     */     
/* 325 */     if (par4)
/*     */     {
/* 327 */       par0StringBuilder.append(EnumChatFormatting.UNDERLINE);
/*     */     }
/*     */     
/* 330 */     if (par5)
/*     */     {
/* 332 */       par0StringBuilder.append(EnumChatFormatting.OBFUSCATED);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChatMessageComponent createFromJson(String par0Str) {
/*     */     try {
/* 340 */       return (ChatMessageComponent)field_111089_a.fromJson(par0Str, ChatMessageComponent.class);
/*     */     }
/* 342 */     catch (Throwable var4) {
/*     */       
/* 344 */       CrashReport var2 = CrashReport.makeCrashReport(var4, "Deserializing Message");
/* 345 */       CrashReportCategory var3 = var2.makeCategory("Serialized Message");
/* 346 */       var3.addCrashSection("JSON string", par0Str);
/* 347 */       throw new ReportedException(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChatMessageComponent createFromText(String par0Str) {
/* 353 */     ChatMessageComponent var1 = new ChatMessageComponent();
/* 354 */     var1.addText(par0Str);
/* 355 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChatMessageComponent createFromTranslationKey(String par0Str) {
/* 360 */     ChatMessageComponent var1 = new ChatMessageComponent();
/* 361 */     var1.addKey(par0Str);
/* 362 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ChatMessageComponent createFromTranslationWithSubstitutions(String par0Str, Object... par1ArrayOfObj) {
/* 367 */     ChatMessageComponent var2 = new ChatMessageComponent();
/* 368 */     var2.addFormatted(par0Str, par1ArrayOfObj);
/* 369 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toJson() {
/* 374 */     return field_111089_a.toJson(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChatMessageComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */