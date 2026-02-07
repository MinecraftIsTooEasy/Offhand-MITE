/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StringHelper
/*     */ {
/*   9 */   public static final String newline = new String(System.getProperty("line.separator").getBytes());
/*     */ 
/*     */   
/*     */   public static String capitalize(String s) {
/*  13 */     return s.substring(0, 1).toUpperCase() + s.substring(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String capitalizeEachWord(String s) {
/*  18 */     char[] chars = s.toCharArray();
/*     */     
/*  20 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/*  22 */       if (i == 0 || chars[i - 1] == ' ') {
/*  23 */         chars[i] = ("" + chars[i]).toUpperCase().charAt(0);
/*     */       }
/*     */     } 
/*  26 */     return new String(chars);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFirstWord(String s) {
/*  31 */     int i = s.trim().indexOf(' ');
/*     */     
/*  33 */     if (i < 0) {
/*  34 */       return s;
/*     */     }
/*  36 */     return s.substring(0, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatFloat(float f, int min_num_decimal_places, int max_num_decimal_places) {
/*  41 */     if (min_num_decimal_places < 0 || max_num_decimal_places < 0) {
/*     */       
/*  43 */       Minecraft.setErrorMessage("formatFloat: min_num_decimal_places or max_num_decimal_places is less than 0");
/*  44 */       return Float.toString(f);
/*     */     } 
/*  46 */     if (min_num_decimal_places > max_num_decimal_places) {
/*     */       
/*  48 */       Minecraft.setErrorMessage("formatFloat: min_num_decimal_places > max_num_decimal_places");
/*  49 */       return Float.toString(f);
/*     */     } 
/*     */     
/*  52 */     String s = String.format("%." + max_num_decimal_places + "f", new Object[] { Float.valueOf(f) });
/*     */     
/*  54 */     for (int i = 0; i < max_num_decimal_places - min_num_decimal_places;) {
/*     */       
/*  56 */       if (s.endsWith("0")) {
/*  57 */         s = s.substring(0, s.length() - 1);
/*     */         
/*     */         i++;
/*     */       } 
/*     */     } 
/*  62 */     return s.endsWith(".") ? s.substring(0, s.length() - 1) : s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatFloat(float f) {
/*  67 */     return formatFloat(f, 0, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatDouble(double d) {
/*  72 */     return formatDouble(d, 0, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatDouble(double d, int min_num_decimal_places, int max_num_decimal_places) {
/*  77 */     return formatFloat((float)d, min_num_decimal_places, max_num_decimal_places);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCommaSeparatedList(String[] strings) {
/*  82 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  84 */     for (int i = 0; i < strings.length; i++) {
/*     */       
/*  86 */       sb.append(strings[i]);
/*     */       
/*  88 */       if (i < strings.length - 1) {
/*  89 */         sb.append(", ");
/*     */       }
/*     */     } 
/*  92 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCommaSeparatedList(Object[] objects) {
/*  97 */     String[] strings = new String[objects.length];
/*     */     
/*  99 */     for (int i = 0; i < objects.length; i++) {
/* 100 */       strings[i] = objects[i].toString();
/*     */     }
/* 102 */     return getCommaSeparatedList(strings);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getCommaSeparatedList(List<E> list) {
/* 107 */     String[] strings = new String[list.size()];
/*     */     
/* 109 */     for (int i = 0; i < list.size(); i++) {
/* 110 */       strings[i] = list.get(i).toString();
/*     */     }
/* 112 */     return getCommaSeparatedList(strings);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mirrorString(String s) {
/* 117 */     char[] chars = s.toCharArray();
/*     */     
/* 119 */     for (int i = 0; i < chars.length; i++) {
/*     */       
/* 121 */       int c = chars[i];
/*     */       
/* 123 */       if (c >= 65 && c <= 90) {
/* 124 */         c = 90 - c - 65;
/* 125 */       } else if (c >= 97 && c <= 122) {
/* 126 */         c = 122 - c - 97;
/* 127 */       } else if (c >= 48 && c <= 57) {
/* 128 */         c = 57 - c - 48;
/*     */       } 
/* 130 */       chars[i] = (char)c;
/*     */     } 
/*     */     
/* 133 */     return new String(chars);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String repeat(String text, int n) {
/* 138 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 140 */     for (int i = 0; i < n; i++) {
/* 141 */       sb.append(text);
/*     */     }
/* 143 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean startsWithVowel(String s) {
/* 148 */     if (s == null || s.isEmpty()) {
/* 149 */       return false;
/*     */     }
/* 151 */     char c = s.toLowerCase().charAt(0);
/*     */     
/* 153 */     return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean startsWithDigit(String s) {
/* 158 */     if (s == null || s.isEmpty()) {
/* 159 */       return false;
/*     */     }
/* 161 */     char c = s.charAt(0);
/*     */     
/* 163 */     return (c >= '0' && c <= '9');
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripLeading(String leading, String text) {
/* 168 */     if (leading == null || text == null || !text.startsWith(leading)) {
/* 169 */       return text;
/*     */     }
/* 171 */     return text.substring(leading.length());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stripTrailing(String trailing, String text) {
/* 176 */     if (trailing == null || text == null || !text.endsWith(trailing)) {
/* 177 */       return text;
/*     */     }
/* 179 */     return text.substring(0, text.length() - trailing.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String left(String s, int num_chars) {
/* 190 */     if (num_chars < 0) {
/* 191 */       num_chars = s.length() + num_chars;
/*     */     }
/* 193 */     if (num_chars < 0) {
/* 194 */       num_chars = 0;
/*     */     }
/* 196 */     return s.substring(0, num_chars);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String aOrAn(String next_word) {
/* 201 */     return (startsWithVowel(next_word) ? "an " : "a ") + next_word;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String yesOrNo(boolean b) {
/* 206 */     return b ? "yes" : "no";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCoordsAsString(int x, int y, int z) {
/* 211 */     return x + "," + y + "," + z;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCoordsAsString(int[] coords) {
/* 216 */     return coords[0] + "," + coords[1] + "," + coords[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPosAsString(double pos_x, double pos_y, double pos_z, int decimal_places) {
/* 221 */     return formatDouble(pos_x, 0, decimal_places) + "," + formatDouble(pos_y, 0, decimal_places) + "," + formatDouble(pos_z, 0, decimal_places);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOnOrOff(boolean on) {
/* 226 */     return on ? "ON" : "OFF";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addToStringArray(String s, String[] array) {
/* 232 */     for (int i = 0; i < array.length; i++) {
/*     */       
/* 234 */       if (array[i] == null) {
/*     */         
/* 236 */         array[i] = s;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 241 */     Minecraft.setErrorMessage("addToStringArray: No room left in array");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNumNonNullStrings(String[] array) {
/* 246 */     int num = 0;
/*     */     
/* 248 */     for (int i = 0; i < array.length; i++) {
/*     */       
/* 250 */       if (array[i] != null) {
/* 251 */         num++;
/*     */       }
/*     */     } 
/* 254 */     return num;
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
/*     */   public static String[] explode(String s, String delimiter) {
/* 275 */     if (s == null) {
/* 276 */       return null;
/*     */     }
/* 278 */     return s.split(delimiter);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String implode(String[] string_array, String delimiter, boolean include_empties, boolean include_nulls) {
/* 283 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 285 */     boolean include_delimiter = false;
/*     */     
/* 287 */     if (include_nulls || string_array[0] != null)
/*     */     {
/* 289 */       if (include_empties || !string_array[0].isEmpty()) {
/*     */         
/* 291 */         sb.append(string_array[0]);
/* 292 */         include_delimiter = true;
/*     */       } 
/*     */     }
/*     */     
/* 296 */     for (int i = 1; i < string_array.length; i++) {
/*     */       
/* 298 */       if (include_nulls || string_array[i] != null)
/*     */       {
/* 300 */         if (include_empties || !string_array[i].isEmpty()) {
/*     */           
/* 302 */           if (include_delimiter) {
/* 303 */             sb.append(delimiter);
/*     */           } else {
/* 305 */             include_delimiter = true;
/*     */           } 
/* 307 */           sb.append(string_array[i]);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 312 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String implode(String[] string_array, String delimiter) {
/* 317 */     return implode(string_array, delimiter, true, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getRomanNumeral(int n) {
/* 322 */     if (n == 1)
/* 323 */       return "I"; 
/* 324 */     if (n == 2)
/* 325 */       return "II"; 
/* 326 */     if (n == 3)
/* 327 */       return "III"; 
/* 328 */     if (n == 4)
/* 329 */       return "IV"; 
/* 330 */     if (n == 5) {
/* 331 */       return "V";
/*     */     }
/* 333 */     Minecraft.setErrorMessage("getRomanNumeral: unhandled number " + n);
/* 334 */     return "?";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getBooleanAsLetter(boolean b) {
/* 339 */     return b ? "T" : "F";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String convertUnderscoresToCamelCase(String s) {
/* 344 */     if (s == null) {
/* 345 */       return null;
/*     */     }
/* 347 */     if (s.contains("_")) {
/*     */       
/* 349 */       String[] arr = explode(s, "_");
/*     */       
/* 351 */       StringBuilder sb = new StringBuilder(arr[0]);
/*     */       
/* 353 */       for (int i = 1; i < arr.length; i++) {
/* 354 */         sb.append(capitalize(arr[i]));
/*     */       }
/* 356 */       return sb.toString();
/*     */     } 
/*     */ 
/*     */     
/* 360 */     return s;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StringHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */