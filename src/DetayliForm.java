import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DetayliForm extends JFrame
{
    public DetayliForm()
    {
        formuKur();
    }

    public DetayliForm(String soru, String a, String b, String c, String d, String e, String dogru, String zorluk, String ders, String konu)
    {
        formuKur();
        txtSoruMetni.setText(soru);
        txtA.setText(a);
        txtB.setText(b);
        txtC.setText(c);
        txtD.setText(d);
        txtE.setText(e);
        txtDogru.setText(dogru);
        switch (zorluk)
        {
            case "1":
                rbKolay.setSelected(true);
                break;
            case "2":
                rbOrta.setSelected(true);
                break;   
            case "3":
                rbZor.setSelected(true);
                break;
        }
        cbDers.addItem(ders);
        cbKonu.addItem(konu);
    }
    
    //Form'u kuran method.
    private void formuKur()
    {
        initComponents();
        setSize(new Dimension(916, 460));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSoruMetni = new javax.swing.JTextArea();
        txtA = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        txtC = new javax.swing.JTextField();
        txtD = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        txtDogru = new javax.swing.JTextField();
        lblC = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        lblDogru = new javax.swing.JLabel();
        lblSoru = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblD = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        lblE = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        rbOrta = new javax.swing.JRadioButton();
        rbZor = new javax.swing.JRadioButton();
        rbKolay = new javax.swing.JRadioButton();
        lbl16 = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        cbDers = new javax.swing.JComboBox<>();
        cbKonu = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detaylı Soru Gör");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jScrollPane2.setBorder(null);

        txtSoruMetni.setEditable(false);
        txtSoruMetni.setBackground(new java.awt.Color(177, 177, 177));
        txtSoruMetni.setColumns(20);
        txtSoruMetni.setLineWrap(true);
        txtSoruMetni.setRows(5);
        txtSoruMetni.setWrapStyleWord(true);
        txtSoruMetni.setBorder(null);
        jScrollPane2.setViewportView(txtSoruMetni);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(210, 20, 400, 94);

        txtA.setEditable(false);
        txtA.setBackground(new java.awt.Color(177, 177, 177));
        txtA.setBorder(null);
        jPanel1.add(txtA);
        txtA.setBounds(210, 138, 400, 30);

        txtB.setEditable(false);
        txtB.setBackground(new java.awt.Color(177, 177, 177));
        txtB.setBorder(null);
        jPanel1.add(txtB);
        txtB.setBounds(210, 188, 400, 30);

        txtC.setEditable(false);
        txtC.setBackground(new java.awt.Color(177, 177, 177));
        txtC.setBorder(null);
        jPanel1.add(txtC);
        txtC.setBounds(210, 238, 400, 30);

        txtD.setEditable(false);
        txtD.setBackground(new java.awt.Color(177, 177, 177));
        txtD.setBorder(null);
        jPanel1.add(txtD);
        txtD.setBounds(210, 288, 400, 30);

        txtE.setEditable(false);
        txtE.setBackground(new java.awt.Color(177, 177, 177));
        txtE.setBorder(null);
        jPanel1.add(txtE);
        txtE.setBounds(210, 338, 400, 30);

        txtDogru.setEditable(false);
        txtDogru.setBackground(new java.awt.Color(177, 177, 177));
        txtDogru.setBorder(null);
        jPanel1.add(txtDogru);
        txtDogru.setBounds(210, 388, 30, 30);

        lblC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblC);
        lblC.setBounds(200, 230, 419, 47);

        lbl1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl1.setText("Seçenek A");
        jPanel1.add(lbl1);
        lbl1.setBounds(60, 144, 140, 20);

        lbl13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl13.setText("Seçenek E");
        jPanel1.add(lbl13);
        lbl13.setBounds(60, 344, 140, 20);

        lblDogru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        jPanel1.add(lblDogru);
        lblDogru.setBounds(200, 380, 48, 47);

        lblSoru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtBuyuk.png"))); // NOI18N
        lblSoru.setPreferredSize(new java.awt.Dimension(410, 161));
        jPanel1.add(lblSoru);
        lblSoru.setBounds(200, 10, 419, 115);

        lbl4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl4.setText("Soru Metni");
        jPanel1.add(lbl4);
        lbl4.setBounds(60, 60, 140, 20);

        lblD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblD);
        lblD.setBounds(200, 280, 419, 47);

        lbl7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl7.setText("Ders");
        jPanel1.add(lbl7);
        lbl7.setBounds(640, 110, 140, 20);

        lblA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblA);
        lblA.setBounds(200, 130, 419, 47);

        lbl5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl5.setText("Seçenek B");
        jPanel1.add(lbl5);
        lbl5.setBounds(60, 194, 140, 20);

        lbl9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl9.setText("Seçenek D");
        jPanel1.add(lbl9);
        lbl9.setBounds(60, 294, 140, 20);

        lbl11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl11.setText("Zorluk");
        jPanel1.add(lbl11);
        lbl11.setBounds(270, 396, 80, 20);

        lblE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblE);
        lblE.setBounds(200, 330, 419, 47);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(630, 10, 10, 410);

        lbl15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl15.setText("Doğru Seçenek");
        jPanel1.add(lbl15);
        lbl15.setBounds(30, 390, 170, 22);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(256, 385, 10, 40);

        rbOrta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbOrta);
        rbOrta.setText("Orta");
        rbOrta.setToolTipText("Eklenecek soru orta ise seçin");
        rbOrta.setEnabled(false);
        jPanel1.add(rbOrta);
        rbOrta.setBounds(440, 394, 70, 23);

        rbZor.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbZor);
        rbZor.setText("Zor");
        rbZor.setToolTipText("Eklenecek soru zor ise seçin");
        rbZor.setEnabled(false);
        jPanel1.add(rbZor);
        rbZor.setBounds(520, 394, 70, 23);

        rbKolay.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbKolay);
        rbKolay.setSelected(true);
        rbKolay.setText("Kolay");
        rbKolay.setToolTipText("Eklenecek soru kolay ise seçin");
        rbKolay.setEnabled(false);
        jPanel1.add(rbKolay);
        rbKolay.setBounds(360, 394, 70, 23);

        lbl16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl16.setText("Seçenek C");
        jPanel1.add(lbl16);
        lbl16.setBounds(60, 244, 140, 20);

        lbl17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl17.setText("Konu");
        jPanel1.add(lbl17);
        lbl17.setBounds(640, 200, 140, 20);

        lblB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblB);
        lblB.setBounds(200, 180, 419, 47);

        cbDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbDers.setBorder(null);
        cbDers.setEnabled(false);
        jPanel1.add(cbDers);
        cbDers.setBounds(640, 140, 250, 47);

        cbKonu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbKonu.setEnabled(false);
        jPanel1.add(cbKonu);
        cbKonu.setBounds(640, 230, 250, 47);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 910, 440);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(DetayliForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new DetayliForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Object> cbDers;
    public javax.swing.JComboBox<Object> cbKonu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl9;
    public javax.swing.JLabel lblA;
    public javax.swing.JLabel lblB;
    public javax.swing.JLabel lblC;
    public javax.swing.JLabel lblD;
    public javax.swing.JLabel lblDogru;
    public javax.swing.JLabel lblE;
    public javax.swing.JLabel lblSoru;
    public javax.swing.JRadioButton rbKolay;
    public javax.swing.JRadioButton rbOrta;
    public javax.swing.JRadioButton rbZor;
    public javax.swing.JTextField txtA;
    public javax.swing.JTextField txtB;
    public javax.swing.JTextField txtC;
    public javax.swing.JTextField txtD;
    public javax.swing.JTextField txtDogru;
    public javax.swing.JTextField txtE;
    public javax.swing.JTextArea txtSoruMetni;
    // End of variables declaration//GEN-END:variables
}
