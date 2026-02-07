/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ThreadedFileIOBase
/*     */   implements Runnable
/*     */ {
/*  10 */   public static final ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
/*  11 */   private List threadedIOQueue = Collections.synchronizedList(new ArrayList());
/*     */ 
/*     */   
/*     */   private volatile boolean isThreadWaiting;
/*     */   
/*     */   public final Thread thread;
/*     */ 
/*     */   
/*     */   private ThreadedFileIOBase() {
/*  20 */     Thread var1 = new Thread(this, "File IO Thread");
/*  21 */     var1.setPriority(1);
/*  22 */     var1.start();
/*     */     
/*  24 */     this.thread = var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*  31 */       processQueue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processQueue() {
/*  40 */     for (int var1 = 0; var1 < this.threadedIOQueue.size(); var1++) {
/*     */       
/*  42 */       IThreadedFileIO var2 = this.threadedIOQueue.get(var1);
/*  43 */       boolean var3 = var2.writeNextIO();
/*     */       
/*  45 */       if (!var3)
/*     */       {
/*  47 */         this.threadedIOQueue.remove(var1--);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  53 */         Thread.sleep(this.isThreadWaiting ? 0L : 10L);
/*     */       }
/*  55 */       catch (InterruptedException var6) {
/*     */         
/*  57 */         var6.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     if (this.threadedIOQueue.isEmpty()) {
/*     */       
/*     */       try {
/*     */         
/*  65 */         Thread.sleep(25L);
/*     */       }
/*  67 */       catch (InterruptedException var5) {
/*     */         
/*  69 */         var5.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void queueIO(IThreadedFileIO par1IThreadedFileIO) {
/*  79 */     if (!this.threadedIOQueue.contains(par1IThreadedFileIO))
/*     */     {
/*     */       
/*  82 */       this.threadedIOQueue.add(par1IThreadedFileIO);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void waitForFinish() {
/* 100 */     threadedIOInstance.isThreadWaiting = true;
/*     */ 
/*     */     
/*     */     try {
/* 104 */       while (!isFinished()) {
/* 105 */         Thread.sleep(10L);
/*     */       }
/* 107 */     } catch (InterruptedException e) {
/*     */       
/* 109 */       e.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 113 */       threadedIOInstance.isThreadWaiting = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFinished() {
/* 119 */     return threadedIOInstance.threadedIOQueue.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reportErrorIfNotFinished() {
/* 124 */     if (!isFinished())
/* 125 */       Minecraft.setErrorMessage("Warning: Not all pending chunks were saved to disk!"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadedFileIOBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */