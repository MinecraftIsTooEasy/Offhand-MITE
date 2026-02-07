/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public class Packet1Login
/*     */   extends Packet
/*     */ {
/*     */   public int clientEntityId;
/*     */   public WorldType terrainType;
/*     */   public boolean hardcoreMode;
/*     */   public EnumGameType gameType;
/*     */   public int dimension;
/*     */   public byte difficultySetting;
/*     */   public byte worldHeight;
/*     */   public byte maxPlayers;
/*     */   public byte village_conditions;
/*     */   public short earliest_MITE_release_run_in;
/*     */   public short latest_MITE_release_run_in;
/*     */   public boolean are_skills_enabled;
/*     */   public HashMap achievements;
/*     */   public long world_creation_time;
/*     */   public long total_world_time;
/*     */   
/*     */   public Packet1Login() {}
/*     */   
/*     */   public Packet1Login(int par1, WorldType par2WorldType, EnumGameType par3EnumGameType, boolean par4, int par5, int par6, int par7, int par8, byte village_conditions, HashMap achievements, int earliest_MITE_release_run_in, int latest_MITE_release_run_in, boolean are_skills_enabled, long world_creation_time, long total_world_time) {
/*  46 */     this.clientEntityId = par1;
/*  47 */     this.terrainType = par2WorldType;
/*  48 */     this.dimension = par5;
/*  49 */     this.difficultySetting = (byte)par6;
/*  50 */     this.gameType = par3EnumGameType;
/*  51 */     this.worldHeight = (byte)par7;
/*  52 */     this.maxPlayers = (byte)par8;
/*  53 */     this.hardcoreMode = par4;
/*  54 */     this.village_conditions = village_conditions;
/*  55 */     this.achievements = (HashMap)achievements.clone();
/*  56 */     this.earliest_MITE_release_run_in = (short)earliest_MITE_release_run_in;
/*  57 */     this.latest_MITE_release_run_in = (short)latest_MITE_release_run_in;
/*     */     
/*  59 */     this.are_skills_enabled = are_skills_enabled;
/*  60 */     this.world_creation_time = world_creation_time;
/*  61 */     this.total_world_time = total_world_time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  69 */     this.clientEntityId = par1DataInput.readInt();
/*  70 */     String var2 = readString(par1DataInput, 16);
/*  71 */     this.terrainType = WorldType.parseWorldType(var2);
/*     */     
/*  73 */     if (this.terrainType == null)
/*     */     {
/*  75 */       this.terrainType = WorldType.DEFAULT;
/*     */     }
/*     */     
/*  78 */     byte var3 = par1DataInput.readByte();
/*  79 */     this.hardcoreMode = ((var3 & 0x8) == 8);
/*  80 */     int var4 = var3 & 0xFFFFFFF7;
/*  81 */     this.gameType = EnumGameType.getByID(var4);
/*  82 */     this.dimension = par1DataInput.readByte();
/*  83 */     this.difficultySetting = par1DataInput.readByte();
/*  84 */     this.worldHeight = par1DataInput.readByte();
/*  85 */     this.maxPlayers = par1DataInput.readByte();
/*     */     
/*  87 */     this.village_conditions = par1DataInput.readByte();
/*     */     
/*  89 */     int num_achievements = par1DataInput.readByte();
/*     */     
/*  91 */     this.achievements = new HashMap<Object, Object>();
/*     */     
/*  93 */     for (int i = 0; i < num_achievements; i++) {
/*     */       
/*  95 */       Achievement achievement = (Achievement)StatList.getStat(par1DataInput.readInt());
/*  96 */       String username = readString(par1DataInput, 16);
/*  97 */       int day = par1DataInput.readInt();
/*     */       
/*  99 */       this.achievements.put(achievement, new WorldAchievement(achievement, username, day));
/*     */     } 
/*     */     
/* 102 */     this.earliest_MITE_release_run_in = par1DataInput.readShort();
/* 103 */     this.latest_MITE_release_run_in = par1DataInput.readShort();
/*     */     
/* 105 */     this.are_skills_enabled = par1DataInput.readBoolean();
/*     */     
/* 107 */     this.world_creation_time = par1DataInput.readLong();
/* 108 */     this.total_world_time = par1DataInput.readLong();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 116 */     par1DataOutput.writeInt(this.clientEntityId);
/* 117 */     writeString((this.terrainType == null) ? "" : this.terrainType.getWorldTypeName(), par1DataOutput);
/* 118 */     int var2 = this.gameType.getID();
/*     */     
/* 120 */     if (this.hardcoreMode)
/*     */     {
/* 122 */       var2 |= 0x8;
/*     */     }
/*     */     
/* 125 */     par1DataOutput.writeByte(var2);
/* 126 */     par1DataOutput.writeByte(this.dimension);
/* 127 */     par1DataOutput.writeByte(this.difficultySetting);
/* 128 */     par1DataOutput.writeByte(this.worldHeight);
/* 129 */     par1DataOutput.writeByte(this.maxPlayers);
/*     */     
/* 131 */     par1DataOutput.writeByte(this.village_conditions);
/*     */     
/* 133 */     par1DataOutput.writeByte(this.achievements.size());
/*     */     
/* 135 */     Iterator<Map.Entry> i = this.achievements.entrySet().iterator();
/*     */     
/* 137 */     while (i.hasNext()) {
/*     */       
/* 139 */       Map.Entry entry = i.next();
/*     */       
/* 141 */       WorldAchievement wa = (WorldAchievement)entry.getValue();
/*     */       
/* 143 */       par1DataOutput.writeInt(wa.achievement.statId);
/* 144 */       writeString(wa.username, par1DataOutput);
/* 145 */       par1DataOutput.writeInt(wa.day);
/*     */     } 
/*     */     
/* 148 */     par1DataOutput.writeShort(this.earliest_MITE_release_run_in);
/* 149 */     par1DataOutput.writeShort(this.latest_MITE_release_run_in);
/*     */     
/* 151 */     par1DataOutput.writeBoolean(this.are_skills_enabled);
/*     */     
/* 153 */     par1DataOutput.writeLong(this.world_creation_time);
/* 154 */     par1DataOutput.writeLong(this.total_world_time);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 162 */     par1NetHandler.handleLogin(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 170 */     int var1 = 0;
/*     */     
/* 172 */     if (this.terrainType != null)
/*     */     {
/* 174 */       var1 = this.terrainType.getWorldTypeName().length();
/*     */     }
/*     */     
/* 177 */     int num_achievement_bytes = 1;
/*     */     
/* 179 */     Iterator<Map.Entry> i = this.achievements.entrySet().iterator();
/*     */     
/* 181 */     while (i.hasNext()) {
/*     */       
/* 183 */       Map.Entry entry = i.next();
/*     */       
/* 185 */       WorldAchievement wa = (WorldAchievement)entry.getValue();
/*     */       
/* 187 */       num_achievement_bytes += 4 + wa.username.length() * 2 + 4;
/*     */     } 
/*     */     
/* 190 */     return 6 + 2 * var1 + 4 + 4 + 1 + 1 + 1 + 1 + 4 + 1 + num_achievement_bytes + 16;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet1Login.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */