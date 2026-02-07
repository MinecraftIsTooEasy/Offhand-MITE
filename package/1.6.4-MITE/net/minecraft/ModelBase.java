/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ 
/*    */ public abstract class ModelBase {
/*    */   public float onGround;
/* 11 */   public List boxList = new ArrayList(); public boolean isRiding;
/*    */   public boolean isChild = true;
/* 13 */   private Map modelTextureMap = new HashMap<Object, Object>();
/* 14 */   public int textureWidth = 64;
/* 15 */   public int textureHeight = 32;
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {}
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {}
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {}
/*    */   
/*    */   public ModelRenderer getRandomModelBox(Random random) {
/* 27 */     return this.boxList.get(random.nextInt(this.boxList.size()));
/*    */   }
/*    */   
/*    */   protected void setTextureOffset(String string, int i, int j) {
/* 31 */     this.modelTextureMap.put(string, new TextureOffset(i, j));
/*    */   }
/*    */   
/*    */   public TextureOffset getTextureOffset(String string) {
/* 35 */     return (TextureOffset)this.modelTextureMap.get(string);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */