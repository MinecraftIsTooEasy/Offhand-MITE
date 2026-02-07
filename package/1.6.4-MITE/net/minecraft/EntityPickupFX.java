/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityPickupFX
/*    */   extends EntityFX
/*    */ {
/*    */   private Entity entityToPickUp;
/*    */   private Entity entityPickingUp;
/*    */   private int age;
/*    */   private int maxAge;
/*    */   private float yOffs;
/*    */   
/*    */   public EntityPickupFX(World world, Entity entity, Entity entity2, float f) {
/* 21 */     super(world, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
/* 22 */     this.entityToPickUp = entity;
/* 23 */     this.entityPickingUp = entity2;
/* 24 */     this.maxAge = 3;
/* 25 */     this.yOffs = f;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 30 */     float f1 = (this.age + f) / this.maxAge;
/* 31 */     f1 *= f1;
/*    */     
/* 33 */     double d1 = this.entityToPickUp.posX;
/* 34 */     double d2 = this.entityToPickUp.posY;
/* 35 */     double d3 = this.entityToPickUp.posZ;
/*    */     
/* 37 */     double d4 = this.entityPickingUp.lastTickPosX + (this.entityPickingUp.posX - this.entityPickingUp.lastTickPosX) * f;
/* 38 */     double d5 = this.entityPickingUp.lastTickPosY + (this.entityPickingUp.posY - this.entityPickingUp.lastTickPosY) * f + this.yOffs;
/* 39 */     double d6 = this.entityPickingUp.lastTickPosZ + (this.entityPickingUp.posZ - this.entityPickingUp.lastTickPosZ) * f;
/*    */     
/* 41 */     double d7 = d1 + (d4 - d1) * f1;
/* 42 */     double d8 = d2 + (d5 - d2) * f1;
/* 43 */     double d9 = d3 + (d6 - d3) * f1;
/*    */     
/* 45 */     int m = MathHelper.floor_double(d7);
/* 46 */     int n = MathHelper.floor_double(d8 + (this.yOffset / 2.0F));
/* 47 */     int i1 = MathHelper.floor_double(d9);
/*    */ 
/*    */     
/* 50 */     int i2 = getBrightnessForRender(f);
/* 51 */     int i3 = i2 % 65536;
/* 52 */     int i4 = i2 / 65536;
/* 53 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, i3 / 1.0F, i4 / 1.0F);
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     d7 -= interpPosX;
/* 61 */     d8 -= interpPosY;
/* 62 */     d9 -= interpPosZ;
/*    */     
/* 64 */     RenderManager.instance.renderEntityWithPosYaw(this.entityToPickUp, (float)d7, (float)d8, (float)d9, this.entityToPickUp.rotationYaw, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 69 */     this.age++;
/* 70 */     if (this.age == this.maxAge) setDead();
/*    */   
/*    */   }
/*    */   
/*    */   public int getFXLayer() {
/* 75 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPickupFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */