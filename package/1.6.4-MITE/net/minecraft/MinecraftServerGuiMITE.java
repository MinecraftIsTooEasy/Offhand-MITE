/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextArea;
/*    */ import javax.swing.JTextField;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.EtchedBorder;
/*    */ import javax.swing.border.TitledBorder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MinecraftServerGuiMITE
/*    */   extends JComponent
/*    */ {
/*    */   private static boolean field_120022_a;
/*    */   private DedicatedServer field_120021_b;
/*    */   
/*    */   public static void func_120016_a(DedicatedServer par0DedicatedServer) {
/*    */     try {
/* 27 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*    */     }
/* 29 */     catch (Exception var3) {}
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     MinecraftServerGuiMITE var1 = new MinecraftServerGuiMITE(par0DedicatedServer);
/* 35 */     field_120022_a = true;
/* 36 */     JFrame var2 = new JFrame("Minecraft server");
/* 37 */     var2.add(var1);
/* 38 */     var2.pack();
/* 39 */     var2.setLocationRelativeTo((Component)null);
/* 40 */     var2.setVisible(true);
/* 41 */     var2.addWindowListener(new MinecraftServerGuiINNER1MITE(par0DedicatedServer));
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftServerGuiMITE(DedicatedServer par1DedicatedServer) {
/* 46 */     this.field_120021_b = par1DedicatedServer;
/* 47 */     setPreferredSize(new Dimension(854, 480));
/* 48 */     setLayout(new BorderLayout());
/*    */ 
/*    */     
/*    */     try {
/* 52 */       add(func_120018_d(), "Center");
/* 53 */       add(func_120019_b(), "West");
/*    */     }
/* 55 */     catch (Exception var3) {
/*    */       
/* 57 */       var3.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private JComponent func_120019_b() {
/* 63 */     JPanel var1 = new JPanel(new BorderLayout());
/* 64 */     var1.add(new StatsComponentMITE(this.field_120021_b), "North");
/* 65 */     var1.add(func_120020_c(), "Center");
/* 66 */     var1.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
/* 67 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   private JComponent func_120020_c() {
/* 72 */     PlayerListComponentMITE var1 = new PlayerListComponentMITE(this.field_120021_b);
/* 73 */     JScrollPane var2 = new JScrollPane(var1, 22, 30);
/* 74 */     var2.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
/* 75 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   private JComponent func_120018_d() {
/* 80 */     JPanel var1 = new JPanel(new BorderLayout());
/* 81 */     JTextArea var2 = new JTextArea();
/* 82 */     this.field_120021_b.getLogAgent().func_120013_a().addHandler(new TextAreaLogHandlerMITE(var2));
/* 83 */     JScrollPane var3 = new JScrollPane(var2, 22, 30);
/* 84 */     var2.setEditable(false);
/* 85 */     JTextField var4 = new JTextField();
/* 86 */     var4.addActionListener(new MinecraftServerGuiINNER2MITE(this, var4));
/* 87 */     var2.addFocusListener(new MinecraftServerGuiINNER3MITE(this));
/* 88 */     var1.add(var3, "Center");
/* 89 */     var1.add(var4, "South");
/* 90 */     var1.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
/* 91 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   static DedicatedServer func_120017_a(MinecraftServerGuiMITE par0MinecraftServerGui) {
/* 96 */     return par0MinecraftServerGui.field_120021_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MinecraftServerGuiMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */