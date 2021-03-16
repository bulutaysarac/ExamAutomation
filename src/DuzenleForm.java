import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class DuzenleForm extends JFrame 
{
    //Global Değişkenler.
    public JFrame ekran = this;
    private VeriTabani vt = new VeriTabani();
    private int seciliDersId;
    private int seciliKonuId;
    private int soruId;

    public DuzenleForm() 
    {
        formuKur();
        
        btnKaydet.addMouseListener(kaydetClick);
        
        txtDogru.getDocument().addDocumentListener(dogruTextChanged);
        
        cbDers.addItemListener(cbDersSelectedItemChanged);
        cbKonu.addItemListener(cbKonuSelectedItemChanged);
        
        this.addWindowListener(windowLoad);
        cbDersDoldur();
    }                      
    
    //Form'u kuran method.
    private void formuKur()
    {
        initComponents();
        setSize(new Dimension(905, 460));
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
    }
    
    //Alt windowdan gelen verileri bu formda doldur.
    private void formuDoldur()
    {
        try
        {
            soruId = Integer.parseInt(lblId.getText());
            ResultSet soru = vt.SoruGetir(soruId);
            TableModel tbModel = DbUtils.resultSetToTableModel(soru);
            
            seciliKonuId = Integer.parseInt(tbModel.getValueAt(0, 2).toString());
            seciliDersId = Integer.parseInt(tbModel.getValueAt(0, 1).toString());
            txtSoruMetni.setText(tbModel.getValueAt(0, 6).toString());
            txtA.setText(tbModel.getValueAt(0, 7).toString());
            txtB.setText(tbModel.getValueAt(0, 8).toString());
            txtC.setText(tbModel.getValueAt(0, 9).toString());
            txtD.setText(tbModel.getValueAt(0, 10).toString());
            txtE.setText(tbModel.getValueAt(0, 11).toString());
            txtDogru.setText(tbModel.getValueAt(0, 12).toString());
            String vtZorluk = tbModel.getValueAt(0, 3).toString();
            
            for(int i = 0; i < cbDers.getItemCount(); i++)
            {
                if(((OzelComboBoxItem)cbDers.getItemAt(i)).getId() == Integer.parseInt(tbModel.getValueAt(0, 1).toString()))
                {
                    cbDers.setSelectedIndex(i);
                    break;
                }
            }
            
            for(int i = 0; i < cbKonu.getItemCount(); i++)
            {
                if(((OzelComboBoxItem)cbKonu.getItemAt(i)).getId() == seciliKonuId)
                {
                    cbKonu.setSelectedIndex(i);
                    break;
                }
            }
            
            switch (vtZorluk)
            {
                case "Kolay":
                    rbKolay.setSelected(true);
                    break;
                case "Orta":
                    rbOrta.setSelected(true);
                    break;
                case "Zor":
                    rbZor.setSelected(true);
                    break;
            }
        }
        catch(NumberFormatException e){}
    }
    
    //Girilen verilerin boş olup olmadığını kontrol eden method.
    private boolean VerileriKontrolEt()
    {
        return !(txtA.getText().equals("") || txtB.getText().equals("") || txtC.getText().equals("") || txtD.getText().equals("") || txtE.getText().equals("") || txtDogru.getText().equals("") || txtSoruMetni.getText().equals("") || seciliDersId == 0 || seciliKonuId == 0);
    }
    
    //cbDers'i veritabanından gelen dersler tablosu ile dolduran method.
    private void cbDersDoldur()
    {
        try
        {
            ResultSet dersler = vt.DersGetir();
            while(dersler.next())
                cbDers.addItem(new OzelComboBoxItem(dersler.getInt("Id"), dersler.getString("DersAdi")));
        }
        catch(SQLException ex) {}
    }
    
    //cbKonu'yu veritabanından gelen konular tablosu ile dolduran method.
    private void cbKonuDoldur()
    {
        cbKonu.removeAllItems();
        try
        {
            ResultSet konular = vt.KonuGetir(seciliDersId);
            while(konular.next())
                cbKonu.addItem(new OzelComboBoxItem(konular.getInt("Id"), konular.getString("KonuAdi")));
        }
        catch(SQLException ex) {}
    }
    
    //Seçilen RadioButton'a göre zorluk bulan method.
    private int zorlukBul()
    {
        if(rbKolay.isSelected())
            return 1;
        else if(rbOrta.isSelected())
            return 2;
        else if(rbZor.isSelected())
            return 3;
        else
            return 0;
    }
    
    //Ekran Tam olarak yüklendiğinde işleme girmek için.
    WindowAdapter windowLoad = new WindowAdapter()
    {
        @Override
        public void windowOpened(WindowEvent e)
        {
            formuDoldur();
        }
    };
    
    //cbKonu seçim değiştiğinde seçilen konuId'yi bulmamız için kullanılan ItemListener.
    ItemListener cbKonuSelectedItemChanged = new ItemListener() 
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            //Javada bu olay 2 kere çalışıyor.Birisi seçilen için birisi seçimi kaldırılan için.
            if(e.getStateChange() == ItemEvent.SELECTED) //Eğer olay seçilen için çalışıyorsa işleme gir.
            {
                OzelComboBoxItem gelenItem = (OzelComboBoxItem) cbKonu.getSelectedItem();
                seciliKonuId = gelenItem.getId();
            }
        }
    };
    
    //cbDers seçim değiştiğinde seçilen dersId'yi bulmamız için kullanılan ItemListener.
    ItemListener cbDersSelectedItemChanged = new ItemListener() 
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            //Javada bu olay 2 kere çalışıyor.Birisi seçilen için birisi seçimi kaldırılan için.
            if(e.getStateChange() == ItemEvent.SELECTED) //Eğer olay seçilen için çalışıyorsa işleme gir.
            {
                OzelComboBoxItem gelenItem = (OzelComboBoxItem) cbDers.getSelectedItem();
                seciliDersId = gelenItem.getId();
                cbKonuDoldur();
            }
        }
    };
    
    //txtDogru alanına tek karakter ve büyük case sınırlaması getirmek için kullanılan DocumentListener.
    DocumentListener dogruTextChanged = new DocumentListener() 
    {
        @Override
        public void insertUpdate(DocumentEvent e) 
        {
            Runnable invokeIslem = () -> {
                if(txtDogru.getText().length() > 1) 
                {
                    String gelen = txtDogru.getText().substring(0, txtDogru.getText().length() - 1);
                    txtDogru.setText(gelen);
                }
                txtDogru.setText(txtDogru.getText().toUpperCase());
            };  
            SwingUtilities.invokeLater(invokeIslem);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {}
        @Override
        public void changedUpdate(DocumentEvent e) {}
    };
    
    
    //Kaydetme işlemini yapacak buton için MouseAdapter.
    MouseAdapter kaydetClick = new MouseAdapter()
    {
        @Override
        public void mouseEntered(MouseEvent e)  
        {  
            btnKaydet.setFont(new Font("Arial", Font.BOLD, 30));
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {  
            btnKaydet.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        
        @Override
        public void mouseClicked(MouseEvent e)  
        {  
            int cevap = JOptionPane.showConfirmDialog(ekran, "Emin misiniz?", "Seçin", 0);
            if(VerileriKontrolEt())                
                if(cevap == 0)
                {
                    vt.SoruGuncelle(soruId, seciliKonuId, zorlukBul(), txtSoruMetni.getText(), txtA.getText(), txtB.getText(), txtC.getText(), txtD.getText(), txtE.getText(), txtDogru.getText());
                    ekran.dispose();
                }
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez !!!");
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSoruMetni = new javax.swing.JTextArea();
        txtA = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        txtC = new javax.swing.JTextField();
        txtD = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        txtDogru = new javax.swing.JTextField();
        lbl2 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lbl10 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        rbOrta = new javax.swing.JRadioButton();
        rbZor = new javax.swing.JRadioButton();
        rbKolay = new javax.swing.JRadioButton();
        cbDers = new javax.swing.JComboBox<>();
        cbKonu = new javax.swing.JComboBox<>();
        lbl16 = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        btnKaydet = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Soru Düzenle");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jScrollPane2.setBorder(null);

        txtSoruMetni.setBackground(new java.awt.Color(177, 177, 177));
        txtSoruMetni.setColumns(20);
        txtSoruMetni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoruMetni.setLineWrap(true);
        txtSoruMetni.setRows(5);
        txtSoruMetni.setWrapStyleWord(true);
        txtSoruMetni.setBorder(null);
        jScrollPane2.setViewportView(txtSoruMetni);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(210, 20, 400, 94);

        txtA.setBackground(new java.awt.Color(177, 177, 177));
        txtA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtA.setBorder(null);
        jPanel2.add(txtA);
        txtA.setBounds(210, 138, 400, 30);

        txtB.setBackground(new java.awt.Color(177, 177, 177));
        txtB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtB.setBorder(null);
        jPanel2.add(txtB);
        txtB.setBounds(210, 188, 400, 30);

        txtC.setBackground(new java.awt.Color(177, 177, 177));
        txtC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtC.setBorder(null);
        jPanel2.add(txtC);
        txtC.setBounds(210, 238, 400, 30);

        txtD.setBackground(new java.awt.Color(177, 177, 177));
        txtD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtD.setBorder(null);
        jPanel2.add(txtD);
        txtD.setBounds(210, 288, 400, 30);

        txtE.setBackground(new java.awt.Color(177, 177, 177));
        txtE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtE.setBorder(null);
        jPanel2.add(txtE);
        txtE.setBounds(210, 338, 400, 30);

        txtDogru.setBackground(new java.awt.Color(177, 177, 177));
        txtDogru.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDogru.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDogru.setBorder(null);
        jPanel2.add(txtDogru);
        txtDogru.setBounds(203, 382, 43, 43);

        lbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtBuyuk.png"))); // NOI18N
        lbl2.setPreferredSize(new java.awt.Dimension(410, 161));
        jPanel2.add(lbl2);
        lbl2.setBounds(200, 10, 419, 115);

        lbl1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl1.setText("Seçenek A");
        jPanel2.add(lbl1);
        lbl1.setBounds(60, 144, 140, 20);

        lbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel2.add(lbl3);
        lbl3.setBounds(200, 130, 419, 47);

        lbl5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl5.setText("Seçenek B");
        jPanel2.add(lbl5);
        lbl5.setBounds(60, 194, 140, 20);

        lbl4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl4.setText("Soru Metni");
        jPanel2.add(lbl4);
        lbl4.setBounds(60, 60, 140, 20);

        lbl6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel2.add(lbl6);
        lbl6.setBounds(200, 180, 419, 47);

        lbl7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl7.setText("Ders");
        jPanel2.add(lbl7);
        lbl7.setBounds(640, 90, 140, 20);

        lbl8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel2.add(lbl8);
        lbl8.setBounds(200, 230, 419, 47);

        lbl9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl9.setText("Seçenek D");
        jPanel2.add(lbl9);
        lbl9.setBounds(60, 294, 140, 20);

        lbl10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel2.add(lbl10);
        lbl10.setBounds(200, 280, 419, 47);

        lbl11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl11.setText("Zorluk");
        jPanel2.add(lbl11);
        lbl11.setBounds(270, 396, 80, 20);

        lbl12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        jPanel2.add(lbl12);
        lbl12.setBounds(200, 380, 48, 47);

        lbl13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl13.setText("Seçenek E");
        jPanel2.add(lbl13);
        lbl13.setBounds(60, 344, 140, 20);

        lbl14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel2.add(lbl14);
        lbl14.setBounds(200, 330, 419, 47);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(630, 10, 10, 410);

        lbl15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl15.setText("Doğru Seçenek");
        jPanel2.add(lbl15);
        lbl15.setBounds(10, 394, 190, 20);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(256, 385, 10, 40);

        rbOrta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbOrta);
        rbOrta.setText("Orta");
        rbOrta.setToolTipText("Eklenecek soru orta ise seçin");
        jPanel2.add(rbOrta);
        rbOrta.setBounds(440, 394, 70, 23);

        rbZor.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbZor);
        rbZor.setText("Zor");
        rbZor.setToolTipText("Eklenecek soru zor ise seçin");
        jPanel2.add(rbZor);
        rbZor.setBounds(520, 394, 70, 23);

        rbKolay.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbKolay);
        rbKolay.setSelected(true);
        rbKolay.setText("Kolay");
        rbKolay.setToolTipText("Eklenecek soru kolay ise seçin");
        jPanel2.add(rbKolay);
        rbKolay.setBounds(360, 394, 70, 23);

        cbDers.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbDers.setBorder(null);
        jPanel2.add(cbDers);
        cbDers.setBounds(640, 120, 250, 47);

        cbKonu.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jPanel2.add(cbKonu);
        cbKonu.setBounds(640, 210, 250, 47);

        lbl16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl16.setText("Seçenek C");
        jPanel2.add(lbl16);
        lbl16.setBounds(60, 244, 140, 20);

        lbl17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbl17.setText("Konu");
        jPanel2.add(lbl17);
        lbl17.setBounds(640, 180, 140, 20);

        btnKaydet.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnKaydet.setForeground(new java.awt.Color(255, 255, 255));
        btnKaydet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnKaydet.setText("KAYDET");
        btnKaydet.setToolTipText("");
        btnKaydet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnKaydet);
        btnKaydet.setBounds(644, 285, 250, 52);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel1);
        jLabel1.setBounds(640, 280, 257, 61);

        jLabel2.setText("ID : ");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(40, 10, 30, 30);

        lblId.setText("1");
        jPanel2.add(lblId);
        lblId.setBounds(70, 10, 60, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
            java.util.logging.Logger.getLogger(DuzenleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new DuzenleForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnKaydet;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Object> cbDers;
    public javax.swing.JComboBox<Object> cbKonu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    public javax.swing.JLabel lblId;
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