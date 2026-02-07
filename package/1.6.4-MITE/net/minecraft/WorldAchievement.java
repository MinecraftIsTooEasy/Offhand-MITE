/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WorldAchievement
/*    */ {
/*    */   public String username;
/*    */   public Achievement achievement;
/*    */   public int day;
/*    */   
/*    */   public WorldAchievement(Achievement achievement, String username, int day) {
/* 14 */     this.achievement = achievement;
/* 15 */     this.username = username;
/* 16 */     this.day = day;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldAchievement(int stat_id, String username, int day) {
/* 21 */     this((Achievement)StatList.getStat(stat_id), username, day);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldAchievement(NBTTagCompound nbt) {
/* 26 */     this(nbt.getInteger("stat_id"), nbt.getString("username"), nbt.getInteger("day"));
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound getAsNBTTagCompound() {
/* 31 */     NBTTagCompound nbt = new NBTTagCompound();
/*    */     
/* 33 */     nbt.setString("username", this.username);
/* 34 */     nbt.setInteger("stat_id", this.achievement.statId);
/* 35 */     nbt.setInteger("day", this.day);
/*    */     
/* 37 */     return nbt;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldAchievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */