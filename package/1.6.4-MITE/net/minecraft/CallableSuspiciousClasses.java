/*     */ package net.minecraft;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import java.util.concurrent.Callable;
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
/*     */ class CallableSuspiciousClasses
/*     */   implements Callable
/*     */ {
/*     */   CallableSuspiciousClasses(CrashReport crashReport) {}
/*     */   
/*     */   public String callSuspiciousClasses() {
/* 114 */     StringBuilder stringBuilder = new StringBuilder();
/* 115 */     Field field = ClassLoader.class.getDeclaredField("classes");
/* 116 */     field.setAccessible(true);
/* 117 */     ArrayList<?> arrayList = new ArrayList((Vector)field.get(CrashReport.class.getClassLoader()));
/* 118 */     boolean bool1 = true;
/* 119 */     boolean bool2 = !CrashReport.class.getCanonicalName().equals("net.minecraft.CrashReport") ? true : false;
/* 120 */     HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/* 121 */     String str = "";
/*     */     
/* 123 */     Collections.sort(arrayList, new ComparatorClassSorter(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     for (Class clazz : arrayList) {
/* 133 */       if (clazz == null)
/* 134 */         continue;  String str1 = clazz.getCanonicalName();
/* 135 */       if (str1 == null || 
/* 136 */         str1.startsWith("org.lwjgl.") || 
/* 137 */         str1.startsWith("paulscode.") || 
/* 138 */         str1.startsWith("org.bouncycastle.") || 
/* 139 */         str1.startsWith("argo.") || 
/* 140 */         str1.startsWith("com.jcraft.") || 
/* 141 */         str1.startsWith("com.fasterxml.") || 
/* 142 */         str1.startsWith("com.google.") || 
/* 143 */         str1.startsWith("joptsimple.") || 
/* 144 */         str1.startsWith("org.apache.") || 
/* 145 */         str1.equals("util.GLX"))
/*     */         continue; 
/* 147 */       if (bool2 ? (
/* 148 */         str1.length() <= 3 || 
/* 149 */         str1.equals("net.minecraft.client.main.Main") || 
/* 150 */         str1.equals("net.minecraft.client.Minecraft") || 
/* 151 */         str1.equals("net.minecraft.client.ClientBrandRetriever") || 
/* 152 */         str1.equals("net.minecraft.server.MinecraftServer")) : 
/*     */         
/* 154 */         str1.startsWith("net.minecraft")) {
/*     */         continue;
/*     */       }
/* 157 */       Package package_ = clazz.getPackage();
/* 158 */       String str2 = (package_ == null) ? "" : package_.getName();
/*     */       
/* 160 */       if (hashMap.containsKey(str2)) {
/* 161 */         int i = ((Integer)hashMap.get(str2)).intValue();
/* 162 */         hashMap.put(str2, Integer.valueOf(i + 1));
/*     */         
/* 164 */         if (i == 3) {
/* 165 */           if (!bool1) stringBuilder.append(", "); 
/* 166 */           stringBuilder.append("...");
/* 167 */           bool1 = false; continue;
/*     */         } 
/* 169 */         if (i > 3) {
/*     */           continue;
/*     */         }
/*     */       } else {
/* 173 */         hashMap.put(str2, Integer.valueOf(1));
/*     */       } 
/*     */       
/* 176 */       if (!str.equals(str2) && str.length() > 0) stringBuilder.append("], "); 
/* 177 */       if (!bool1 && str.equals(str2)) stringBuilder.append(", "); 
/* 178 */       if (!str.equals(str2)) {
/* 179 */         stringBuilder.append("[");
/* 180 */         stringBuilder.append(str2);
/* 181 */         stringBuilder.append(".");
/*     */       } 
/* 183 */       stringBuilder.append(clazz.getSimpleName());
/* 184 */       str = str2;
/* 185 */       bool1 = false;
/*     */     } 
/*     */     
/* 188 */     if (bool1) {
/* 189 */       stringBuilder.append("No suspicious classes found.");
/*     */     } else {
/* 191 */       stringBuilder.append("]");
/*     */     } 
/*     */     
/* 194 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableSuspiciousClasses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */