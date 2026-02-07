/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RNG
/*     */ {
/*     */   public static final int MAX_INDEX = 32767;
/*     */   private static final int MAX_INDEX_PLUS_1 = 32768;
/*  12 */   public static int[] int_max = new int[32768];
/*     */   
/*  14 */   public static int[] int_2 = new int[32768];
/*  15 */   public static int[] int_4 = new int[32768];
/*  16 */   public static int[] int_8 = new int[32768];
/*  17 */   public static int[] int_16 = new int[32768];
/*  18 */   public static int[] int_32 = new int[32768];
/*  19 */   public static int[] int_64 = new int[32768];
/*  20 */   public static int[] int_128 = new int[32768];
/*  21 */   public static int[] int_256 = new int[32768];
/*     */   
/*  23 */   public static int[] int_3 = new int[32768];
/*  24 */   public static int[] int_5 = new int[32768];
/*  25 */   public static int[] int_6 = new int[32768];
/*  26 */   public static int[] int_7 = new int[32768];
/*  27 */   public static int[] int_9 = new int[32768];
/*  28 */   public static int[] int_10 = new int[32768];
/*  29 */   public static int[] int_13 = new int[32768];
/*  30 */   public static int[] int_14 = new int[32768];
/*  31 */   public static int[] int_17 = new int[32768];
/*  32 */   public static int[] int_25 = new int[32768];
/*  33 */   public static int[] int_33 = new int[32768];
/*  34 */   public static int[] int_49 = new int[32768];
/*  35 */   public static int[] int_65 = new int[32768];
/*  36 */   public static int[] int_100 = new int[32768];
/*  37 */   public static int[] int_126 = new int[32768];
/*     */   
/*  39 */   public static int[] int_4_minus_int_4 = new int[32768];
/*  40 */   public static int[] int_8_minus_int_8 = new int[32768];
/*  41 */   public static int[] int_16_plus_8 = new int[32768];
/*     */   
/*  43 */   public static int[] int_7_minus_3 = new int[32768];
/*  44 */   public static int[] int_9_minus_4 = new int[32768];
/*  45 */   public static int[] int_13_minus_6 = new int[32768];
/*  46 */   public static int[] int_14_plus_1 = new int[32768];
/*  47 */   public static int[] int_17_minus_8 = new int[32768];
/*  48 */   public static int[] int_25_minus_12 = new int[32768];
/*  49 */   public static int[] int_33_minus_16 = new int[32768];
/*  50 */   public static int[] int_49_minus_24 = new int[32768];
/*  51 */   public static int[] int_65_minus_32 = new int[32768];
/*  52 */   public static int[] int_126_plus_1 = new int[32768];
/*     */   
/*  54 */   public static boolean[] chance_in_2 = new boolean[32768];
/*  55 */   public static boolean[] chance_in_3 = new boolean[32768];
/*  56 */   public static boolean[] chance_in_4 = new boolean[32768];
/*  57 */   public static boolean[] chance_in_6 = new boolean[32768];
/*  58 */   public static boolean[] chance_in_8 = new boolean[32768];
/*  59 */   public static boolean[] chance_in_10 = new boolean[32768];
/*  60 */   public static boolean[] chance_in_16 = new boolean[32768];
/*  61 */   public static boolean[] chance_in_32 = new boolean[32768];
/*  62 */   public static boolean[] chance_in_100 = new boolean[32768];
/*     */   
/*  64 */   public static float[] float_1 = new float[32768];
/*  65 */   public static float[] float_1_minus_float_1 = new float[32768];
/*  66 */   public static float[] float_1_times_float_1 = new float[32768];
/*  67 */   public static float[] float_2Pi = new float[32768];
/*  68 */   public static float[] float_1_minus_a_half_times_a_quarter = new float[32768];
/*  69 */   public static float[] float_2_plus_float_1 = new float[32768];
/*     */   
/*  71 */   public static double[] double_1 = new double[32768];
/*     */ 
/*     */   
/*     */   public static boolean random_numbers_have_been_initialized = false;
/*     */   
/*     */   public static int random_number_index;
/*     */ 
/*     */   
/*     */   protected static void init(World world) {
/*  80 */     if (random_numbers_have_been_initialized) {
/*     */       return;
/*     */     }
/*  83 */     random_numbers_have_been_initialized = true;
/*     */     
/*  85 */     Random rand = new Random();
/*  86 */     rand.setSeed(world.getSeed());
/*     */     
/*  88 */     int i = -1;
/*     */ 
/*     */     
/*  91 */     while (++i < 32768) {
/*     */       
/*  93 */       int int_a = rand.nextInt() & Integer.MAX_VALUE;
/*  94 */       int int_b = rand.nextInt() & Integer.MAX_VALUE;
/*     */       
/*  96 */       int_max[i] = int_a;
/*     */       
/*  98 */       int_2[i] = int_a & 0x1;
/*  99 */       int_4[i] = int_a & 0x3;
/* 100 */       int_8[i] = int_a & 0x7;
/* 101 */       int_16[i] = int_a & 0xF;
/* 102 */       int_32[i] = int_a & 0x1F;
/* 103 */       int_64[i] = int_a & 0x3F;
/* 104 */       int_128[i] = int_a & 0x7F;
/* 105 */       int_256[i] = int_a & 0xFF;
/*     */       
/* 107 */       int_3[i] = int_a % 3;
/* 108 */       int_5[i] = int_a % 5;
/* 109 */       int_6[i] = int_a % 6;
/* 110 */       int_7[i] = int_a % 7;
/* 111 */       int_9[i] = int_a % 9;
/* 112 */       int_10[i] = int_a % 10;
/* 113 */       int_13[i] = int_a % 13;
/* 114 */       int_14[i] = int_a % 14;
/* 115 */       int_17[i] = int_a % 17;
/* 116 */       int_25[i] = int_a % 25;
/* 117 */       int_33[i] = int_a % 33;
/* 118 */       int_49[i] = int_a % 49;
/* 119 */       int_65[i] = int_a % 65;
/* 120 */       int_100[i] = int_a % 100;
/* 121 */       int_126[i] = int_a % 126;
/*     */       
/* 123 */       int_4_minus_int_4[i] = int_4[i] - (int_b & 0x3);
/* 124 */       int_8_minus_int_8[i] = int_8[i] - (int_b & 0x7);
/* 125 */       int_16_plus_8[i] = int_16[i] + 8;
/*     */       
/* 127 */       int_7_minus_3[i] = int_7[i] - 3;
/* 128 */       int_9_minus_4[i] = int_9[i] - 4;
/* 129 */       int_13_minus_6[i] = int_13[i] - 6;
/* 130 */       int_14_plus_1[i] = int_14[i] + 1;
/* 131 */       int_17_minus_8[i] = int_17[i] - 8;
/* 132 */       int_25_minus_12[i] = int_25[i] - 12;
/* 133 */       int_33_minus_16[i] = int_33[i] - 16;
/* 134 */       int_49_minus_24[i] = int_49[i] - 24;
/* 135 */       int_65_minus_32[i] = int_65[i] - 32;
/* 136 */       int_126_plus_1[i] = int_126[i] + 1;
/*     */       
/* 138 */       chance_in_2[i] = (int_2[i] == 0);
/* 139 */       chance_in_3[i] = (int_3[i] == 0);
/* 140 */       chance_in_4[i] = (int_4[i] == 0);
/* 141 */       chance_in_6[i] = (int_6[i] == 0);
/* 142 */       chance_in_8[i] = (int_8[i] == 0);
/* 143 */       chance_in_10[i] = (int_10[i] == 0);
/* 144 */       chance_in_16[i] = (int_16[i] == 0);
/* 145 */       chance_in_32[i] = (int_32[i] == 0);
/* 146 */       chance_in_100[i] = (int_100[i] == 0);
/*     */       
/* 148 */       float float_a = rand.nextFloat();
/* 149 */       float float_b = rand.nextFloat();
/*     */       
/* 151 */       float_1[i] = float_a;
/* 152 */       float_1_minus_float_1[i] = float_a - float_b;
/* 153 */       float_1_times_float_1[i] = float_a * float_b;
/* 154 */       float_2Pi[i] = (float)((float_a * 2.0F) * Math.PI);
/* 155 */       float_1_minus_a_half_times_a_quarter[i] = (float_a - 0.5F) * 0.25F;
/* 156 */       float_2_plus_float_1[i] = float_a * 2.0F + float_b;
/*     */       
/* 158 */       double double_a = rand.nextDouble();
/*     */       
/* 160 */       double_1[i] = double_a;
/*     */     } 
/*     */     
/* 163 */     random_number_index = rand.nextInt();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RNG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */