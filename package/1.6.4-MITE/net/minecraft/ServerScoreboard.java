/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class ServerScoreboard extends Scoreboard {
/*  11 */   private final Set field_96553_b = new HashSet(); private final MinecraftServer field_96555_a;
/*     */   private ScoreboardSaveData field_96554_c;
/*     */   
/*     */   public ServerScoreboard(MinecraftServer minecraftServer) {
/*  15 */     this.field_96555_a = minecraftServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_96536_a(Score score) {
/*  24 */     super.func_96536_a(score);
/*     */     
/*  26 */     if (this.field_96553_b.contains(score.func_96645_d())) {
/*  27 */       this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet207SetScore(score, 0));
/*     */     }
/*     */     
/*  30 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96516_a(String string) {
/*  35 */     super.func_96516_a(string);
/*  36 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet207SetScore(string));
/*  37 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96530_a(int i, ScoreObjective scoreObjective) {
/*  42 */     ScoreObjective scoreObjective1 = func_96539_a(i);
/*     */     
/*  44 */     super.func_96530_a(i, scoreObjective);
/*     */     
/*  46 */     if (scoreObjective1 != scoreObjective && scoreObjective1 != null) {
/*  47 */       if (func_96552_h(scoreObjective1) > 0) {
/*  48 */         this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet208SetDisplayObjective(i, scoreObjective));
/*     */       } else {
/*  50 */         func_96546_g(scoreObjective1);
/*     */       } 
/*     */     }
/*     */     
/*  54 */     if (scoreObjective != null) {
/*  55 */       if (this.field_96553_b.contains(scoreObjective)) {
/*  56 */         this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet208SetDisplayObjective(i, scoreObjective));
/*     */       } else {
/*  58 */         func_96549_e(scoreObjective);
/*     */       } 
/*     */     }
/*     */     
/*  62 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayerToTeam(String string, ScorePlayerTeam scorePlayerTeam) {
/*  67 */     super.addPlayerToTeam(string, scorePlayerTeam);
/*     */     
/*  69 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet209SetPlayerTeam(scorePlayerTeam, Arrays.asList(new String[] { string }, ), 3));
/*     */     
/*  71 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String string, ScorePlayerTeam scorePlayerTeam) {
/*  76 */     super.removePlayerFromTeam(string, scorePlayerTeam);
/*     */     
/*  78 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet209SetPlayerTeam(scorePlayerTeam, Arrays.asList(new String[] { string }, ), 4));
/*     */     
/*  80 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96522_a(ScoreObjective scoreObjective) {
/*  85 */     super.func_96522_a(scoreObjective);
/*  86 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96532_b(ScoreObjective scoreObjective) {
/*  91 */     super.func_96532_b(scoreObjective);
/*     */     
/*  93 */     if (this.field_96553_b.contains(scoreObjective)) {
/*  94 */       this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet206SetObjective(scoreObjective, 2));
/*     */     }
/*     */     
/*  97 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96533_c(ScoreObjective scoreObjective) {
/* 102 */     super.func_96533_c(scoreObjective);
/*     */     
/* 104 */     if (this.field_96553_b.contains(scoreObjective)) {
/* 105 */       func_96546_g(scoreObjective);
/*     */     }
/*     */     
/* 108 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96523_a(ScorePlayerTeam scorePlayerTeam) {
/* 113 */     super.func_96523_a(scorePlayerTeam);
/*     */     
/* 115 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet209SetPlayerTeam(scorePlayerTeam, 0));
/*     */     
/* 117 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96538_b(ScorePlayerTeam scorePlayerTeam) {
/* 122 */     super.func_96538_b(scorePlayerTeam);
/*     */     
/* 124 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet209SetPlayerTeam(scorePlayerTeam, 2));
/*     */     
/* 126 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96513_c(ScorePlayerTeam scorePlayerTeam) {
/* 131 */     super.func_96513_c(scorePlayerTeam);
/*     */     
/* 133 */     this.field_96555_a.getConfigurationManager().sendPacketToAllPlayers(new Packet209SetPlayerTeam(scorePlayerTeam, 1));
/*     */     
/* 135 */     func_96551_b();
/*     */   }
/*     */   
/*     */   public void func_96547_a(ScoreboardSaveData scoreboardSaveData) {
/* 139 */     this.field_96554_c = scoreboardSaveData;
/*     */   }
/*     */   
/*     */   protected void func_96551_b() {
/* 143 */     if (this.field_96554_c != null) {
/* 144 */       this.field_96554_c.markDirty();
/*     */     }
/*     */   }
/*     */   
/*     */   public List func_96550_d(ScoreObjective scoreObjective) {
/* 149 */     ArrayList<Packet206SetObjective> arrayList = new ArrayList();
/* 150 */     arrayList.add(new Packet206SetObjective(scoreObjective, 0));
/*     */     
/* 152 */     for (byte b = 0; b < 3; b++) {
/* 153 */       if (func_96539_a(b) == scoreObjective) arrayList.add(new Packet208SetDisplayObjective(b, scoreObjective));
/*     */     
/*     */     } 
/* 156 */     for (Score score : func_96534_i(scoreObjective)) {
/* 157 */       arrayList.add(new Packet207SetScore(score, 0));
/*     */     }
/*     */     
/* 160 */     return arrayList;
/*     */   }
/*     */   
/*     */   public void func_96549_e(ScoreObjective scoreObjective) {
/* 164 */     List list = func_96550_d(scoreObjective);
/*     */     
/* 166 */     for (ServerPlayer serverPlayer : (this.field_96555_a.getConfigurationManager()).playerEntityList) {
/* 167 */       for (Packet packet : list) {
/* 168 */         serverPlayer.playerNetServerHandler.sendPacketToPlayer(packet);
/*     */       }
/*     */     } 
/*     */     
/* 172 */     this.field_96553_b.add(scoreObjective);
/*     */   }
/*     */   
/*     */   public List func_96548_f(ScoreObjective scoreObjective) {
/* 176 */     ArrayList<Packet206SetObjective> arrayList = new ArrayList();
/* 177 */     arrayList.add(new Packet206SetObjective(scoreObjective, 1));
/*     */     
/* 179 */     for (byte b = 0; b < 3; b++) {
/* 180 */       if (func_96539_a(b) == scoreObjective) arrayList.add(new Packet208SetDisplayObjective(b, scoreObjective));
/*     */     
/*     */     } 
/* 183 */     return arrayList;
/*     */   }
/*     */   
/*     */   public void func_96546_g(ScoreObjective scoreObjective) {
/* 187 */     List list = func_96548_f(scoreObjective);
/*     */     
/* 189 */     for (ServerPlayer serverPlayer : (this.field_96555_a.getConfigurationManager()).playerEntityList) {
/* 190 */       for (Packet packet : list) {
/* 191 */         serverPlayer.playerNetServerHandler.sendPacketToPlayer(packet);
/*     */       }
/*     */     } 
/*     */     
/* 195 */     this.field_96553_b.remove(scoreObjective);
/*     */   }
/*     */   
/*     */   public int func_96552_h(ScoreObjective scoreObjective) {
/* 199 */     byte b1 = 0;
/*     */     
/* 201 */     for (byte b2 = 0; b2 < 3; b2++) {
/* 202 */       if (func_96539_a(b2) == scoreObjective) b1++;
/*     */     
/*     */     } 
/* 205 */     return b1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */