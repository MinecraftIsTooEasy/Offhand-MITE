/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class Packet44UpdateAttributes
/*    */   extends Packet
/*    */ {
/*    */   private int field_111005_a;
/* 14 */   private final List field_111004_b = new ArrayList();
/*    */ 
/*    */   
/*    */   public Packet44UpdateAttributes() {}
/*    */   
/*    */   public Packet44UpdateAttributes(int i, Collection collection) {
/* 20 */     this.field_111005_a = i;
/*    */     
/* 22 */     for (AttributeInstance attributeInstance : collection) {
/* 23 */       this.field_111004_b.add(new Packet44UpdateAttributesSnapshot(this, attributeInstance.func_111123_a().getAttributeUnlocalizedName(), attributeInstance.getBaseValue(), attributeInstance.func_111122_c()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 29 */     this.field_111005_a = dataInput.readInt();
/*    */     
/* 31 */     int i = dataInput.readInt();
/* 32 */     for (byte b = 0; b < i; b++) {
/* 33 */       String str = readString(dataInput, 64);
/* 34 */       double d = dataInput.readDouble();
/* 35 */       ArrayList<AttributeModifier> arrayList = new ArrayList();
/* 36 */       short s = dataInput.readShort();
/*    */       
/* 38 */       for (byte b1 = 0; b1 < s; b1++) {
/* 39 */         UUID uUID = new UUID(dataInput.readLong(), dataInput.readLong());
/* 40 */         arrayList.add(new AttributeModifier(uUID, "Unknown synced attribute modifier", dataInput.readDouble(), dataInput.readByte()));
/*    */       } 
/*    */       
/* 43 */       this.field_111004_b.add(new Packet44UpdateAttributesSnapshot(this, str, d, arrayList));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 49 */     dataOutput.writeInt(this.field_111005_a);
/* 50 */     dataOutput.writeInt(this.field_111004_b.size());
/*    */     
/* 52 */     for (Packet44UpdateAttributesSnapshot packet44UpdateAttributesSnapshot : this.field_111004_b) {
/* 53 */       writeString(packet44UpdateAttributesSnapshot.func_142040_a(), dataOutput);
/* 54 */       dataOutput.writeDouble(packet44UpdateAttributesSnapshot.func_142041_b());
/* 55 */       dataOutput.writeShort(packet44UpdateAttributesSnapshot.func_142039_c().size());
/*    */       
/* 57 */       for (AttributeModifier attributeModifier : packet44UpdateAttributesSnapshot.func_142039_c()) {
/* 58 */         dataOutput.writeLong(attributeModifier.getID().getMostSignificantBits());
/* 59 */         dataOutput.writeLong(attributeModifier.getID().getLeastSignificantBits());
/* 60 */         dataOutput.writeDouble(attributeModifier.getAmount());
/* 61 */         dataOutput.writeByte(attributeModifier.getOperation());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 68 */     netHandler.func_110773_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 73 */     return 8 + this.field_111004_b.size() * 24;
/*    */   }
/*    */   
/*    */   public int func_111002_d() {
/* 77 */     return this.field_111005_a;
/*    */   }
/*    */   
/*    */   public List func_111003_f() {
/* 81 */     return this.field_111004_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet44UpdateAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */