import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileSystemView;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordForm extends JFrame 
{
    //Global Değişkenler.
    public JFrame ekran = this;
    private VeriTabani vt = new VeriTabani();
    private int seciliDersId;
    private int seciliKonuId;
    private int satir;
    
    public WordForm() 
    {
        formuKur();

        //DatagGrid sağ click menu.(ContextMenu)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem detay = new JMenuItem("Detaylı Gör");
        
        detay.addActionListener(popupMenuDetayClick);
        
        popupMenu.add(detay);
        dgListe.setComponentPopupMenu(popupMenu);
        dgListe.addMouseListener(dataGridClick);
        
        cbDers.addItemListener(cbDersSelectedItemChanged);
        cbKonu.addItemListener(cbKonuSelectedItemChanged);
        
        btnOnayla.addMouseListener(btnOnaylaClick);
        btnGetir.addMouseListener(btnGetirClick);
    }

    //Form'u kuran method.
    private void formuKur()
    {
        initComponents();
        cbDersDoldur();
        
        dgListe.setDefaultEditor(Object.class, null); //dgListe'yi readOnly yapar.
        setSize(new Dimension(615, 675));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
    }
    
    //DataGrid'e verileri listeleme işlemini yapan method.
    private void Listele()
    {
        ResultSet sorular = vt.SoruGetir(seciliKonuId, Integer.parseInt(txtKolayAdet.getText()), Integer.parseInt(txtOrtaAdet.getText()), Integer.parseInt(txtZorAdet.getText()));
        dgListe.setModel(DbUtils.resultSetToTableModel(sorular));
        dgColGizle();
    }
    
    //Girilen verilerin boş olup olmadığını kontrol eden method.
    private boolean VerileriKontrolEt()
    {
        return !(txtKolayAdet.getText().equals("") || txtOrtaAdet.getText().equals("") || txtKolayAdet.getText().equals("") || txtZorAdet.getText().equals("") || seciliDersId == 0 || seciliKonuId == 0);
    }
    
    //DataGrid'te ilk 2 sütunu görüntü kirliliği olmaması için gizleyen method.
    private void dgColGizle()
    {
        for(int i = 0; i < 2; i++)
            dgListe.removeColumn(dgListe.getColumnModel().getColumn(0));
    }
    
    //cbDers'i veritabanından gelen dersler tablosu ile dolduran method.
    public void cbDersDoldur()
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
    public void cbKonuDoldur()
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
    
    //Seçilen RadioButton'a göre sınav türü bulan method.
    private String sinavTuruBul()
    {
        if(rbFinal.isSelected())
            return "FİNAL";
        else if(rbMazeret.isSelected())
            return "MAZERET";
        else if(rbVize.isSelected())
            return "VİZE";
        else
            return "";
    }

    //Word dosyasını oluşturan işlemleri barındıran method.
    private void wordKagitOlustur(String universiteBaslik, String fakulteBaslik, String dersAdi, String sinavTuru)
    {
        try
        {
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r = p.createRun();

            r.setBold(true);
            r.setFontSize(14);
            r.setText(universiteBaslik.toUpperCase());
            r.addBreak();
            r.setText(fakulteBaslik.toUpperCase());
            r.addBreak();
            r.setText(dersAdi.toUpperCase() + " DERSİ " + sinavTuru.toUpperCase() + " SINAVI");
            wordMultiBreakEkle(r, 2);
            r.setText("Ad Soyad : ");
            wordMultiTabEkle(r, 4);
            r.setText("Numara : ");
            wordMultiTabEkle(r, 4);
            r.setText("Süre : " + txtSure.getText() + " dk.");
            

            p = doc.createParagraph();
            r = p.createRun();
            r.setBold(false);
            r.setFontSize(10);
            r.setUnderline(UnderlinePatterns.NONE);
            wordMultiBreakEkle(r, 2);

            //Tablodaki verileri Word'e sıra sıra yazma.
            for(int i = 1; i <= dgListe.getRowCount(); i++)
            {
                r.setText("Soru " + i + ")     " + dgListe.getModel().getValueAt(i-1, 3).toString());
                r.addBreak(); r.addBreak();
                r.setText("A-) " + dgListe.getModel().getValueAt(i-1, 4).toString());
                r.addBreak();
                r.setText("B-) " + dgListe.getModel().getValueAt(i-1, 5).toString());
                r.addBreak();
                r.setText("C-) " + dgListe.getModel().getValueAt(i-1, 6).toString());
                r.addBreak();
                r.setText("D-) " + dgListe.getModel().getValueAt(i-1, 7).toString());
                r.addBreak();
                r.setText("E-) " + dgListe.getModel().getValueAt(i-1, 8).toString());
                wordMultiBreakEkle(r, 3);
            }

            //En son dosyayı oluşturma.
            FileSystemView filesys = FileSystemView.getFileSystemView();
            FileOutputStream fileOS = new FileOutputStream(filesys.getHomeDirectory().getPath() + "/Sinav.docx");
            doc.write(fileOS);
            fileOS.close();
            
            Thread.sleep(50);
            
            dosyayiAc(filesys.getHomeDirectory().getPath() + "/Sinav.docx");
        }
        catch (Exception ex) {}
    }
    
    //Gelen yoldaki dosyayı açan method.
    private void dosyayiAc(String yol)
    {
        try 
        {
            if (Desktop.isDesktopSupported())
                Desktop.getDesktop().open(new File(yol));
        }
        catch (IOException e) {}
    }
    
    //Tek tek r.addBreak(); yazmak yerine topluca yapan method.
    private void wordMultiBreakEkle(XWPFRun r, int adet)
    {
        for (int i = 0; i < adet; i++)
            r.addBreak();
    }
    
    //Tek tek r.addTab(); yazmak yerine topluca yapan method.
    private void wordMultiTabEkle(XWPFRun r, int adet)
    {
        for (int i = 0; i < adet; i++)
            r.addTab();
    }
    
    //Pop-up menü Detay elemanının clickleri için kullanılan ActionListenerlar.
    ActionListener popupMenuDetayClick = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String soru = dgListe.getModel().getValueAt(satir, 3).toString();
            String secenekA = dgListe.getModel().getValueAt(satir, 4).toString();
            String secenekB = dgListe.getModel().getValueAt(satir, 5).toString();
            String secenekC = dgListe.getModel().getValueAt(satir, 6).toString();
            String secenekD = dgListe.getModel().getValueAt(satir, 7).toString();
            String secenekE = dgListe.getModel().getValueAt(satir, 8).toString();
            String secenekDogru = dgListe.getModel().getValueAt(satir, 9).toString();
            String zorlukId = dgListe.getModel().getValueAt(satir, 2).toString();
            DetayliForm detayF = new DetayliForm(soru, secenekA, secenekB, secenekC, secenekD, secenekE, secenekDogru, zorlukId, cbDers.getSelectedItem().toString(), cbKonu.getSelectedItem().toString());
            detayF.setVisible(true);
        }
    };
    
    //DataGrid'e sağ click yapılınca işaretçinin üstündeki satırı seçtirmek için kullanılan MouseAdapter.
    MouseAdapter dataGridClick = new MouseAdapter() 
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {
            satir = dgListe.rowAtPoint(e.getPoint());
            dgListe.setRowSelectionInterval(satir, satir);
        }
    };
    
    //btnGetir'in Eventleri.
    MouseAdapter btnGetirClick = new MouseAdapter()
    {
        @Override
        public void mouseEntered(MouseEvent e)  
        {  
            btnGetir.setFont(new Font("Arial", Font.BOLD, 26));
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {  
            btnGetir.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(VerileriKontrolEt())
                Listele();
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
        }
    };
    
    //btnOnayla'nın Eventleri.
    MouseAdapter btnOnaylaClick = new MouseAdapter()
    {
        @Override
        public void mouseEntered(MouseEvent e)  
        {  
            btnOnayla.setFont(new Font("Arial", Font.BOLD, 30));
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {  
            btnOnayla.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(VerileriKontrolEt())
                wordKagitOlustur("DÜZCE ÜNİVERSİTESİ", "TEKNOLOJİ FAKÜLTESİ", cbKonu.getSelectedItem().toString(), sinavTuruBul());
            else
               JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnl = new javax.swing.JPanel();
        txtZorAdet = new javax.swing.JTextField();
        txtOrtaAdet = new javax.swing.JTextField();
        txtKolayAdet = new javax.swing.JTextField();
        cbDers = new javax.swing.JComboBox<>();
        cbKonu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgListe = new javax.swing.JTable();
        rbVize = new javax.swing.JRadioButton();
        rbFinal = new javax.swing.JRadioButton();
        rbMazeret = new javax.swing.JRadioButton();
        txtSure = new javax.swing.JTextField();
        btnOnayla = new javax.swing.JLabel();
        btnGetir = new javax.swing.JLabel();
        lblZorArka = new javax.swing.JLabel();
        lblZor = new javax.swing.JLabel();
        lblOrtaArka = new javax.swing.JLabel();
        lblOrta = new javax.swing.JLabel();
        lblKolayArka = new javax.swing.JLabel();
        lblSure = new javax.swing.JLabel();
        lblOnayArka = new javax.swing.JLabel();
        lblDers = new javax.swing.JLabel();
        lblKonu = new javax.swing.JLabel();
        lblBtnGetirArka = new javax.swing.JLabel();
        lblKolay = new javax.swing.JLabel();
        lblMazeret = new javax.swing.JLabel();
        lblSureArka = new javax.swing.JLabel();
        lblDk = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Word'e Yazma");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        pnl.setBackground(new java.awt.Color(255, 255, 255));
        pnl.setLayout(null);

        txtZorAdet.setBackground(new java.awt.Color(177, 177, 177));
        txtZorAdet.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtZorAdet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtZorAdet.setBorder(null);
        pnl.add(txtZorAdet);
        txtZorAdet.setBounds(163, 12, 43, 43);

        txtOrtaAdet.setBackground(new java.awt.Color(177, 177, 177));
        txtOrtaAdet.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtOrtaAdet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOrtaAdet.setBorder(null);
        pnl.add(txtOrtaAdet);
        txtOrtaAdet.setBounds(163, 62, 43, 43);

        txtKolayAdet.setBackground(new java.awt.Color(177, 177, 177));
        txtKolayAdet.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtKolayAdet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKolayAdet.setBorder(null);
        pnl.add(txtKolayAdet);
        txtKolayAdet.setBounds(163, 112, 43, 43);

        cbDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbDers.setBorder(null);
        pnl.add(cbDers);
        cbDers.setBounds(320, 10, 260, 47);

        cbKonu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pnl.add(cbKonu);
        cbKonu.setBounds(320, 60, 260, 47);

        dgListe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        dgListe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dgListe.setRowHeight(35);
        dgListe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(dgListe);

        pnl.add(jScrollPane1);
        jScrollPane1.setBounds(0, 180, 610, 300);

        rbVize.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbVize);
        rbVize.setSelected(true);
        rbVize.setText("Vize");
        rbVize.setToolTipText("Eklenecek soru kolay ise seçin");
        pnl.add(rbVize);
        rbVize.setBounds(20, 520, 70, 23);

        rbFinal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbFinal);
        rbFinal.setText("Final");
        rbFinal.setToolTipText("Eklenecek soru orta ise seçin");
        pnl.add(rbFinal);
        rbFinal.setBounds(100, 520, 70, 23);

        rbMazeret.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbMazeret);
        rbMazeret.setText("Mazeret");
        rbMazeret.setToolTipText("Eklenecek soru zor ise seçin");
        pnl.add(rbMazeret);
        rbMazeret.setBounds(180, 520, 70, 23);

        txtSure.setBackground(new java.awt.Color(177, 177, 177));
        txtSure.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtSure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSure.setBorder(null);
        pnl.add(txtSure);
        txtSure.setBounds(493, 492, 43, 43);

        btnOnayla.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnOnayla.setForeground(new java.awt.Color(255, 255, 255));
        btnOnayla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnOnayla.setText("ONAYLA");
        btnOnayla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(btnOnayla);
        btnOnayla.setBounds(179, 585, 250, 50);

        btnGetir.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnGetir.setForeground(new java.awt.Color(255, 255, 255));
        btnGetir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGetir.setText("SORULARI GETİR");
        btnGetir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(btnGetir);
        btnGetir.setBounds(324, 115, 250, 52);

        lblZorArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        pnl.add(lblZorArka);
        lblZorArka.setBounds(160, 10, 48, 47);

        lblZor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblZor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblZor.setText("Zor Soru");
        pnl.add(lblZor);
        lblZor.setBounds(80, 20, 70, 30);

        lblOrtaArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        pnl.add(lblOrtaArka);
        lblOrtaArka.setBounds(160, 60, 48, 47);

        lblOrta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblOrta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOrta.setText("Orta Soru");
        pnl.add(lblOrta);
        lblOrta.setBounds(70, 70, 80, 30);

        lblKolayArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        pnl.add(lblKolayArka);
        lblKolayArka.setBounds(160, 110, 48, 47);

        lblSure.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSure.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSure.setText("Sınav Süresi");
        pnl.add(lblSure);
        lblSure.setBounds(360, 500, 120, 22);

        lblOnayArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        lblOnayArka.setToolTipText("");
        lblOnayArka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(lblOnayArka);
        lblOnayArka.setBounds(175, 580, 257, 60);

        lblDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDers.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDers.setText("Ders");
        pnl.add(lblDers);
        lblDers.setBounds(270, 20, 40, 22);

        lblKonu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblKonu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKonu.setText("Konu");
        pnl.add(lblKonu);
        lblKonu.setBounds(260, 70, 50, 30);

        lblBtnGetirArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        lblBtnGetirArka.setToolTipText("");
        lblBtnGetirArka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(lblBtnGetirArka);
        lblBtnGetirArka.setBounds(320, 110, 257, 61);

        lblKolay.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblKolay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKolay.setText("Kolay Soru");
        pnl.add(lblKolay);
        lblKolay.setBounds(60, 120, 90, 30);

        lblMazeret.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMazeret.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMazeret.setText("Sınav Türü");
        pnl.add(lblMazeret);
        lblMazeret.setBounds(80, 490, 100, 22);

        lblSureArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        pnl.add(lblSureArka);
        lblSureArka.setBounds(490, 490, 48, 47);

        lblDk.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDk.setText("dk.");
        pnl.add(lblDk);
        lblDk.setBounds(540, 510, 34, 14);

        getContentPane().add(pnl);
        pnl.setBounds(0, 0, 610, 660);

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
            java.util.logging.Logger.getLogger(WordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new WordForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnGetir;
    private javax.swing.JLabel btnOnayla;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Object> cbDers;
    public javax.swing.JComboBox<Object> cbKonu;
    private javax.swing.JTable dgListe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBtnGetirArka;
    private javax.swing.JLabel lblDers;
    private javax.swing.JLabel lblDk;
    private javax.swing.JLabel lblKolay;
    private javax.swing.JLabel lblKolayArka;
    private javax.swing.JLabel lblKonu;
    private javax.swing.JLabel lblMazeret;
    private javax.swing.JLabel lblOnayArka;
    private javax.swing.JLabel lblOrta;
    private javax.swing.JLabel lblOrtaArka;
    private javax.swing.JLabel lblSure;
    private javax.swing.JLabel lblSureArka;
    private javax.swing.JLabel lblZor;
    private javax.swing.JLabel lblZorArka;
    private javax.swing.JPanel pnl;
    public javax.swing.JRadioButton rbFinal;
    public javax.swing.JRadioButton rbMazeret;
    public javax.swing.JRadioButton rbVize;
    public javax.swing.JTextField txtKolayAdet;
    public javax.swing.JTextField txtOrtaAdet;
    public javax.swing.JTextField txtSure;
    public javax.swing.JTextField txtZorAdet;
    // End of variables declaration//GEN-END:variables
}
