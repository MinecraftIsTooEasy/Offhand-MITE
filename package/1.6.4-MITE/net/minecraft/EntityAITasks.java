/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public final class EntityAITasks
/*     */ {
/*  10 */   private List taskEntries = new ArrayList();
/*     */ 
/*     */   
/*  13 */   private List executingTaskEntries = new ArrayList();
/*     */   
/*     */   private final Profiler theProfiler;
/*     */   
/*     */   private int tickCount;
/*  18 */   private int tickRate = 3;
/*     */ 
/*     */   
/*     */   public EntityAITasks(Profiler par1Profiler) {
/*  22 */     this.theProfiler = par1Profiler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTask(int par1, EntityAIBase par2EntityAIBase) {
/*  27 */     this.taskEntries.add(new EntityAITaskEntry(this, par1, par2EntityAIBase));
/*     */   }
/*     */ 
/*     */   
/*     */   public List getTaskEntries() {
/*  32 */     return this.taskEntries;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAIBase getTask(Class<?> _class) {
/*  38 */     Iterator<EntityAITaskEntry> i = this.taskEntries.iterator();
/*     */     
/*  40 */     while (i.hasNext()) {
/*     */       
/*  42 */       EntityAITaskEntry entry = i.next();
/*     */       
/*  44 */       if (entry.action.getClass() == _class) {
/*  45 */         return entry.action;
/*     */       }
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTask(EntityAIBase par1EntityAIBase) {
/*  56 */     Iterator<EntityAITaskEntry> var2 = this.taskEntries.iterator();
/*     */     
/*  58 */     while (var2.hasNext()) {
/*     */       
/*  60 */       EntityAITaskEntry var3 = var2.next();
/*  61 */       EntityAIBase var4 = var3.action;
/*     */       
/*  63 */       if (var4 == par1EntityAIBase) {
/*     */         
/*  65 */         if (this.executingTaskEntries.contains(var3)) {
/*     */           
/*  67 */           var4.resetTask();
/*  68 */           this.executingTaskEntries.remove(var3);
/*     */         } 
/*     */         
/*  71 */         var2.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  78 */     this.taskEntries.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateTasks() {
/*  83 */     ArrayList<EntityAITaskEntry> var1 = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (this.tickCount++ % this.tickRate == 0) {
/*     */       
/*  89 */       Iterator<EntityAITaskEntry> var2 = this.taskEntries.iterator();
/*     */       
/*  91 */       while (var2.hasNext()) {
/*     */         
/*  93 */         EntityAITaskEntry var3 = var2.next();
/*  94 */         boolean var4 = this.executingTaskEntries.contains(var3);
/*     */         
/*  96 */         if (var4) {
/*     */           
/*  98 */           if (canUse(var3) && canContinue(var3)) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 103 */           var3.action.resetTask();
/* 104 */           this.executingTaskEntries.remove(var3);
/*     */         } 
/*     */         
/* 107 */         if (canUse(var3) && var3.action.shouldExecute())
/*     */         {
/* 109 */           var1.add(var3);
/* 110 */           this.executingTaskEntries.add(var3);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 116 */       Iterator<EntityAITaskEntry> var2 = this.executingTaskEntries.iterator();
/*     */       
/* 118 */       while (var2.hasNext()) {
/*     */         
/* 120 */         EntityAITaskEntry var3 = var2.next();
/*     */         
/* 122 */         if (!var3.action.continueExecuting()) {
/*     */           
/* 124 */           var3.action.resetTask();
/* 125 */           var2.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     this.theProfiler.startSection("goalStart");
/* 131 */     Iterator<EntityAITaskEntry> iterator = var1.iterator();
/*     */     
/* 133 */     while (iterator.hasNext()) {
/*     */       
/* 135 */       EntityAITaskEntry var3 = iterator.next();
/* 136 */       this.theProfiler.startSection(var3.action.getClass().getSimpleName());
/* 137 */       var3.action.startExecuting();
/* 138 */       this.theProfiler.endSection();
/*     */     } 
/*     */     
/* 141 */     this.theProfiler.endSection();
/* 142 */     this.theProfiler.startSection("goalTick");
/* 143 */     iterator = this.executingTaskEntries.iterator();
/*     */     
/* 145 */     while (iterator.hasNext()) {
/*     */       
/* 147 */       EntityAITaskEntry var3 = iterator.next();
/* 148 */       var3.action.updateTask();
/*     */     } 
/*     */     
/* 151 */     this.theProfiler.endSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canContinue(EntityAITaskEntry par1EntityAITaskEntry) {
/* 159 */     this.theProfiler.startSection("canContinue");
/* 160 */     boolean var2 = par1EntityAITaskEntry.action.continueExecuting();
/* 161 */     this.theProfiler.endSection();
/* 162 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canUse(EntityAITaskEntry par1EntityAITaskEntry) {
/* 171 */     this.theProfiler.startSection("canUse");
/* 172 */     Iterator<EntityAITaskEntry> var2 = this.taskEntries.iterator();
/*     */     
/* 174 */     while (var2.hasNext()) {
/*     */       
/* 176 */       EntityAITaskEntry var3 = var2.next();
/*     */       
/* 178 */       if (var3 != par1EntityAITaskEntry) {
/*     */         
/* 180 */         if (par1EntityAITaskEntry.priority >= var3.priority) {
/*     */           
/* 182 */           if (this.executingTaskEntries.contains(var3) && !areTasksCompatible(par1EntityAITaskEntry, var3)) {
/*     */             
/* 184 */             this.theProfiler.endSection();
/* 185 */             return false;
/*     */           }  continue;
/*     */         } 
/* 188 */         if (this.executingTaskEntries.contains(var3) && !var3.action.isInterruptible()) {
/*     */           
/* 190 */           this.theProfiler.endSection();
/* 191 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     this.theProfiler.endSection();
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean areTasksCompatible(EntityAITaskEntry par1EntityAITaskEntry, EntityAITaskEntry par2EntityAITaskEntry) {
/* 205 */     return ((par1EntityAITaskEntry.action.getMutexBits() & par2EntityAITaskEntry.action.getMutexBits()) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTaskExecuting(Class<?> class_of_task) {
/* 211 */     Iterator<EntityAITaskEntry> i = this.executingTaskEntries.iterator();
/*     */     
/* 213 */     while (i.hasNext()) {
/*     */       
/* 215 */       EntityAITaskEntry entry = i.next();
/*     */       
/* 217 */       if (entry.action.getClass() == class_of_task) {
/* 218 */         return true;
/*     */       }
/*     */     } 
/* 221 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */