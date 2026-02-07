/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAIBeg
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityWolf theWolf;
/*    */   private EntityPlayer thePlayer;
/*    */   private World worldObject;
/*    */   private float minPlayerDistance;
/*    */   private int field_75384_e;
/*    */   
/*    */   public EntityAIBeg(EntityWolf par1EntityWolf, float par2) {
/* 13 */     this.theWolf = par1EntityWolf;
/* 14 */     this.worldObject = par1EntityWolf.worldObj;
/* 15 */     this.minPlayerDistance = par2;
/* 16 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 24 */     if (this.theWolf.isAttacking() || this.theWolf.isHostileToPlayers()) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance, true);
/* 29 */     return (this.thePlayer == null) ? false : hasPlayerGotBoneInHand(this.thePlayer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 37 */     if (this.theWolf.isAttacking() || this.theWolf.isHostileToPlayers()) {
/* 38 */       return false;
/*    */     }
/* 40 */     return !this.thePlayer.isEntityAlive() ? false : ((this.theWolf.getDistanceSqToEntity(this.thePlayer) > (this.minPlayerDistance * this.minPlayerDistance)) ? false : ((this.field_75384_e > 0 && hasPlayerGotBoneInHand(this.thePlayer))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 48 */     this.theWolf.func_70918_i(true);
/* 49 */     this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 57 */     this.theWolf.func_70918_i(false);
/* 58 */     this.thePlayer = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 66 */     this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
/* 67 */     this.field_75384_e--;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean hasPlayerGotBoneInHand(EntityPlayer par1EntityPlayer) {
/* 75 */     ItemStack var2 = par1EntityPlayer.inventory.getCurrentItemStack();
/*    */     
/* 77 */     return (var2 == null) ? false : ((!this.theWolf.isTamed() && var2.itemID == Item.bone.itemID) ? true : this.theWolf.isFoodItem(var2));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIBeg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */