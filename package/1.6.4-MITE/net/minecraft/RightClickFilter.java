/*    */ package net.minecraft;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RightClickFilter
/*    */ {
/*    */   public static final byte ALL = -1;
/*    */   public static final byte NO_ACTION = 0;
/*    */   public static final byte BLOCK_ACTIVATION = 1;
/*    */   public static final byte ENTITY_INTERACTION = 2;
/*    */   public static final byte INGESTION = 4;
/*    */   public static final byte ON_ITEM_RIGHT_CLICK = 8;
/*    */   private byte allowed_actions;
/*    */   
/*    */   public RightClickFilter() {
/* 30 */     this(-1);
/*    */   }
/*    */ 
/*    */   
/*    */   public RightClickFilter(int allowed_actions) {
/* 35 */     this.allowed_actions = (byte)allowed_actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAllowedActions(int allowed_actions) {
/* 40 */     this.allowed_actions = (byte)allowed_actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAllowedActions() {
/* 45 */     return this.allowed_actions;
/*    */   }
/*    */ 
/*    */   
/*    */   public RightClickFilter setExclusive(int allowed_action) {
/* 50 */     this.allowed_actions = (byte)allowed_action;
/* 51 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsBlockActivation() {
/* 56 */     return ((this.allowed_actions & 0x1) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsEntityInteraction() {
/* 61 */     return ((this.allowed_actions & 0x2) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsEntityInteractionOnly() {
/* 66 */     return (this.allowed_actions == 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsIngestion() {
/* 71 */     return ((this.allowed_actions & 0x4) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsIngestionOnly() {
/* 76 */     return (this.allowed_actions == 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsOnItemRightClick() {
/* 81 */     return ((this.allowed_actions & 0x8) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public RightClickFilter setNoActionAllowed() {
/* 86 */     return setExclusive(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean allowsNoActions() {
/* 91 */     return (this.allowed_actions == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RightClickFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */