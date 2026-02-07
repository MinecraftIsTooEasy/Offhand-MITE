/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
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
/*     */ public class MapInfo
/*     */ {
/*     */   public final EntityPlayer entityplayerObj;
/*     */   public int[] field_76209_b;
/*     */   public int[] field_76210_c;
/*     */   private int currentRandomNumber;
/*     */   private int ticksUntilPlayerLocationMapUpdate;
/*     */   private byte[] lastPlayerLocationOnMap;
/*     */   public int field_82569_d;
/*     */   private boolean field_82570_i;
/*     */   final MapData mapDataObj;
/*     */   public boolean world_map_survey_finished;
/*     */   
/*     */   public MapInfo(MapData par1MapData, EntityPlayer par2EntityPlayer) {
/*  32 */     this.mapDataObj = par1MapData;
/*  33 */     this.field_76209_b = new int[128];
/*  34 */     this.field_76210_c = new int[128];
/*  35 */     this.entityplayerObj = par2EntityPlayer;
/*     */     
/*  37 */     for (int var3 = 0; var3 < this.field_76209_b.length; var3++) {
/*     */       
/*  39 */       this.field_76209_b[var3] = 0;
/*  40 */       this.field_76210_c[var3] = 127;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPlayersOnMap(ItemStack par1ItemStack) {
/*  52 */     if (!this.field_82570_i) {
/*     */       
/*  54 */       byte[] var2 = { 2, this.mapDataObj.scale };
/*  55 */       this.field_82570_i = true;
/*  56 */       return var2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (--this.ticksUntilPlayerLocationMapUpdate < 0) {
/*     */       
/*  65 */       this.ticksUntilPlayerLocationMapUpdate = 4;
/*  66 */       byte[] var2 = new byte[this.mapDataObj.playersVisibleOnMap.size() * 3 + 1];
/*  67 */       var2[0] = 1;
/*  68 */       int var3 = 0;
/*     */       
/*  70 */       for (Iterator<MapCoord> var4 = this.mapDataObj.playersVisibleOnMap.values().iterator(); var4.hasNext(); var3++) {
/*     */         
/*  72 */         MapCoord var5 = var4.next();
/*  73 */         var2[var3 * 3 + 1] = (byte)(var5.iconSize << 4 | var5.iconRotation & 0xF);
/*  74 */         var2[var3 * 3 + 2] = var5.centerX;
/*  75 */         var2[var3 * 3 + 3] = var5.centerZ;
/*     */       } 
/*     */       
/*  78 */       boolean var9 = !par1ItemStack.isOnItemFrame();
/*     */       
/*  80 */       if (this.lastPlayerLocationOnMap != null && this.lastPlayerLocationOnMap.length == var2.length) {
/*     */         
/*  82 */         for (int var10 = 0; var10 < var2.length; var10++) {
/*     */           
/*  84 */           if (var2[var10] != this.lastPlayerLocationOnMap[var10]) {
/*     */             
/*  86 */             var9 = false;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  93 */         var9 = false;
/*     */       } 
/*     */       
/*  96 */       if (!var9) {
/*     */         
/*  98 */         this.lastPlayerLocationOnMap = var2;
/*  99 */         return var2;
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     for (int var8 = 0; var8 < 1; var8++) {
/*     */       
/* 105 */       int var3 = this.currentRandomNumber++ * 11 % 128;
/*     */       
/* 107 */       if (this.field_76209_b[var3] >= 0) {
/*     */         
/* 109 */         int var11 = this.field_76210_c[var3] - this.field_76209_b[var3] + 1;
/* 110 */         int var10 = this.field_76209_b[var3];
/* 111 */         byte[] var6 = new byte[var11 + 3];
/* 112 */         var6[0] = 0;
/* 113 */         var6[1] = (byte)var3;
/* 114 */         var6[2] = (byte)var10;
/*     */         
/* 116 */         for (int var7 = 0; var7 < var6.length - 3; var7++)
/*     */         {
/* 118 */           var6[var7 + 3] = this.mapDataObj.colors[(var7 + var10) * 128 + var3];
/*     */         }
/*     */         
/* 121 */         this.field_76210_c[var3] = -1;
/* 122 */         this.field_76209_b[var3] = -1;
/* 123 */         return var6;
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */