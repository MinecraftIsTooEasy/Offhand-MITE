/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.Timer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class McoServerList
/*    */ {
/*    */   private volatile boolean field_98259_a;
/*    */   private McoServerListUpdateTask field_98257_b;
/* 26 */   private Timer field_98258_c = new Timer();
/* 27 */   private Set field_140060_d = Sets.newHashSet();
/* 28 */   private List field_98255_d = Lists.newArrayList();
/*    */   private int field_130130_e;
/*    */   private boolean field_140059_g;
/*    */   private Session field_98254_f;
/*    */   private int field_140061_i;
/*    */   
/*    */   public McoServerList() {
/* 35 */     this.field_98257_b = new McoServerListUpdateTask(this, null);
/* 36 */     this.field_98258_c.schedule(this.field_98257_b, 0L, 10000L);
/* 37 */     this.field_98254_f = Minecraft.getMinecraft().getSession();
/*    */   }
/*    */   
/*    */   public synchronized void func_130129_a(Session session) {
/* 41 */     this.field_98254_f = session;
/*    */     
/* 43 */     if (this.field_98259_a) {
/* 44 */       this.field_98259_a = false;
/* 45 */       this.field_98257_b = new McoServerListUpdateTask(this, null);
/* 46 */       this.field_98258_c = new Timer();
/* 47 */       this.field_98258_c.schedule(this.field_98257_b, 0L, 10000L);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized boolean func_130127_a() {
/* 53 */     return this.field_140059_g;
/*    */   }
/*    */   
/*    */   public synchronized void func_98250_b() {
/* 57 */     this.field_140059_g = false;
/*    */   }
/*    */   
/*    */   public synchronized List func_98252_c() {
/* 61 */     return Lists.newArrayList(this.field_98255_d);
/*    */   }
/*    */   
/*    */   public int func_130124_d() {
/* 65 */     return this.field_130130_e;
/*    */   }
/*    */   
/*    */   public int func_140056_e() {
/* 69 */     return this.field_140061_i;
/*    */   }
/*    */   
/*    */   public synchronized void func_98248_d() {
/* 73 */     this.field_98259_a = true;
/* 74 */     this.field_98257_b.cancel();
/* 75 */     this.field_98258_c.cancel();
/*    */   }
/*    */   
/*    */   private synchronized void func_96426_a(List list) {
/* 79 */     byte b = 0;
/* 80 */     for (McoServer mcoServer : this.field_140060_d) {
/* 81 */       if (list.remove(mcoServer)) {
/* 82 */         b++;
/*    */       }
/*    */     } 
/* 85 */     if (b == 0) {
/* 86 */       this.field_140060_d.clear();
/*    */     }
/* 88 */     this.field_98255_d = list;
/* 89 */     this.field_140059_g = true;
/*    */   }
/*    */   
/*    */   public synchronized void func_140058_a(McoServer mcoServer) {
/* 93 */     this.field_98255_d.remove(mcoServer);
/* 94 */     this.field_140060_d.add(mcoServer);
/*    */   }
/*    */   
/*    */   private void func_130123_a(int i) {
/* 98 */     this.field_130130_e = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoServerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */