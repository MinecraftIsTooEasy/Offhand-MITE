/*     */ package net.minecraft;
/*     */ 
/*     */ import argo.jdom.JdomParser;
/*     */ import argo.jdom.JsonNode;
/*     */ import argo.saj.InvalidSyntaxException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang3.builder.HashCodeBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class McoServer
/*     */ {
/*     */   public long field_96408_a;
/*     */   public String field_96406_b;
/*     */   public String field_96407_c;
/*     */   public String field_96404_d;
/*     */   public String field_96405_e;
/*     */   public List field_96402_f;
/*     */   public String field_96403_g;
/*     */   public boolean field_98166_h;
/*     */   public int field_110729_i;
/*     */   public int field_110728_j;
/*     */   public int field_104063_i;
/*     */   public int field_96415_h;
/*  30 */   public String field_96414_k = "";
/*     */   
/*     */   public boolean field_96411_l;
/*     */   public boolean field_102022_m;
/*     */   public long field_96412_m;
/*     */   private String field_96409_n;
/*     */   private String field_96410_o;
/*     */   
/*     */   public String func_96397_a() {
/*  39 */     if (this.field_96409_n == null) {
/*     */       
/*     */       try {
/*     */         
/*  43 */         this.field_96409_n = URLDecoder.decode(this.field_96407_c, "UTF-8");
/*     */       }
/*  45 */       catch (UnsupportedEncodingException var2) {
/*     */         
/*  47 */         this.field_96409_n = this.field_96407_c;
/*     */       } 
/*     */     }
/*     */     
/*  51 */     return this.field_96409_n;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_96398_b() {
/*  56 */     if (this.field_96410_o == null) {
/*     */       
/*     */       try {
/*     */         
/*  60 */         this.field_96410_o = URLDecoder.decode(this.field_96406_b, "UTF-8");
/*     */       }
/*  62 */       catch (UnsupportedEncodingException var2) {
/*     */         
/*  64 */         this.field_96410_o = this.field_96406_b;
/*     */       } 
/*     */     }
/*     */     
/*  68 */     return this.field_96410_o;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96399_a(String par1Str) {
/*  73 */     this.field_96406_b = par1Str;
/*  74 */     this.field_96410_o = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96400_b(String par1Str) {
/*  79 */     this.field_96407_c = par1Str;
/*  80 */     this.field_96409_n = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96401_a(McoServer par1McoServer) {
/*  85 */     this.field_96414_k = par1McoServer.field_96414_k;
/*  86 */     this.field_96412_m = par1McoServer.field_96412_m;
/*  87 */     this.field_96411_l = par1McoServer.field_96411_l;
/*  88 */     this.field_96415_h = par1McoServer.field_96415_h;
/*  89 */     this.field_102022_m = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static McoServer func_98163_a(JsonNode par0JsonNode) {
/*  94 */     McoServer var1 = new McoServer();
/*     */ 
/*     */     
/*     */     try {
/*  98 */       var1.field_96408_a = Long.parseLong(par0JsonNode.getNumberValue(new Object[] { "id" }));
/*  99 */       var1.field_96406_b = par0JsonNode.getStringValue(new Object[] { "name" });
/* 100 */       var1.field_96407_c = par0JsonNode.getStringValue(new Object[] { "motd" });
/* 101 */       var1.field_96404_d = par0JsonNode.getStringValue(new Object[] { "state" });
/* 102 */       var1.field_96405_e = par0JsonNode.getStringValue(new Object[] { "owner" });
/*     */       
/* 104 */       if (par0JsonNode.isArrayNode(new Object[] { "invited" })) {
/*     */         
/* 106 */         var1.field_96402_f = func_98164_a(par0JsonNode.getArrayNode(new Object[] { "invited" }));
/*     */       }
/*     */       else {
/*     */         
/* 110 */         var1.field_96402_f = new ArrayList();
/*     */       } 
/*     */       
/* 113 */       var1.field_104063_i = Integer.parseInt(par0JsonNode.getNumberValue(new Object[] { "daysLeft" }));
/* 114 */       var1.field_96403_g = par0JsonNode.getStringValue(new Object[] { "ip" });
/* 115 */       var1.field_98166_h = par0JsonNode.getBooleanValue(new Object[] { "expired" }).booleanValue();
/* 116 */       var1.field_110729_i = Integer.parseInt(par0JsonNode.getNumberValue(new Object[] { "difficulty" }));
/* 117 */       var1.field_110728_j = Integer.parseInt(par0JsonNode.getNumberValue(new Object[] { "gameMode" }));
/*     */     }
/* 119 */     catch (IllegalArgumentException var3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_98164_a(List par0List) {
/* 129 */     ArrayList<String> var1 = new ArrayList();
/* 130 */     Iterator<JsonNode> var2 = par0List.iterator();
/*     */     
/* 132 */     while (var2.hasNext()) {
/*     */       
/* 134 */       JsonNode var3 = var2.next();
/* 135 */       var1.add(var3.getStringValue(new Object[0]));
/*     */     } 
/*     */     
/* 138 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static McoServer func_98165_c(String par0Str) {
/* 143 */     McoServer var1 = new McoServer();
/*     */ 
/*     */     
/*     */     try {
/* 147 */       var1 = func_98163_a((JsonNode)(new JdomParser()).parse(par0Str));
/*     */     }
/* 149 */     catch (InvalidSyntaxException var3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 159 */     return (new HashCodeBuilder(17, 37)).append(this.field_96408_a).append(this.field_96406_b).append(this.field_96407_c).append(this.field_96404_d).append(this.field_96405_e).append(this.field_98166_h).toHashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 164 */     if (par1Obj == null)
/*     */     {
/* 166 */       return false;
/*     */     }
/* 168 */     if (par1Obj == this)
/*     */     {
/* 170 */       return true;
/*     */     }
/* 172 */     if (par1Obj.getClass() != getClass())
/*     */     {
/* 174 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 178 */     McoServer var2 = (McoServer)par1Obj;
/* 179 */     return (new EqualsBuilder()).append(this.field_96408_a, var2.field_96408_a).append(this.field_96406_b, var2.field_96406_b).append(this.field_96407_c, var2.field_96407_c).append(this.field_96404_d, var2.field_96404_d).append(this.field_96405_e, var2.field_96405_e).append(this.field_98166_h, var2.field_98166_h).isEquals();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String field) {
/*     */     try {
/* 187 */       return getClass().getDeclaredField(field).getBoolean(this);
/*     */     }
/* 189 */     catch (Exception e) {
/*     */       
/* 191 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String field) {
/*     */     try {
/* 199 */       return getClass().getDeclaredField(field).getInt(this);
/*     */     }
/* 201 */     catch (Exception e) {
/*     */       
/* 203 */       return 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */