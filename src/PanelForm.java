import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.proteanit.sql.DbUtils;

public class PanelForm extends JFrame 
{
    //Global Değişkenler.
    public JFrame ekran = this;
    private VeriTabani vt = new VeriTabani();
    
    private int seciliDersId;
    private int seciliKonuId;
    private int satir = 0;
            
    public PanelForm()
    {
        formuKur();

        //DatagGrid sağ click menu.(ContextMenu)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem sil = new JMenuItem("Sil");
        JMenuItem guncelle = new JMenuItem("Güncelle");
        
        guncelle.addActionListener(popupMenuGuncelleClick);
        sil.addActionListener(popupMenuSilClick);
        btnWord.addMouseListener(btnWordClick);
        
        popupMenu.add(sil);
        popupMenu.add(guncelle);

        dgListe.setComponentPopupMenu(popupMenu);
        dgListe.addMouseListener(dataGridClick);
        
        btnEkle.addMouseListener(btnEkleClick);
       
        txtDogru.getDocument().addDocumentListener(dogruTextChanged);
        
        cbDers.addItemListener(cbDersSelectedItemChanged);
        cbKonu.addItemListener(cbKonuSelectedItemChanged);
        
        menuDers.addActionListener(menuDersEkle);
        menuKonu.addActionListener(menuKonuEkle);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnl = new javax.swing.JPanel();
        dgListeScrollPane = new javax.swing.JScrollPane();
        dgListe = new javax.swing.JTable();
        pnl2 = new javax.swing.JPanel();
        txtSoruMetniScollPane = new javax.swing.JScrollPane();
        txtSoruMetni = new javax.swing.JTextArea();
        txtA = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        txtC = new javax.swing.JTextField();
        txtD = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        txtDogru = new javax.swing.JTextField();
        cbDers = new javax.swing.JComboBox<>();
        cbKonu = new javax.swing.JComboBox<>();
        rbKolay = new javax.swing.JRadioButton();
        rbOrta = new javax.swing.JRadioButton();
        rbZor = new javax.swing.JRadioButton();
        btnEkle = new javax.swing.JLabel();
        btnWord = new javax.swing.JLabel();
        lblSoruMetniArka = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        lblAArka = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblSoruMetni = new javax.swing.JLabel();
        lblBArka = new javax.swing.JLabel();
        lblDers = new javax.swing.JLabel();
        lblCArka = new javax.swing.JLabel();
        lblD = new javax.swing.JLabel();
        lblDArka = new javax.swing.JLabel();
        lblZorluk = new javax.swing.JLabel();
        lblDogruArka = new javax.swing.JLabel();
        lblE = new javax.swing.JLabel();
        lblEArka = new javax.swing.JLabel();
        lblDogru = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        lblKonu = new javax.swing.JLabel();
        lblBtnkleArka = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuDers = new javax.swing.JMenuItem();
        menuKonu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel");
        setResizable(false);

        pnl.setBackground(new java.awt.Color(255, 255, 255));
        pnl.setLayout(null);

        dgListeScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        dgListe.setAutoCreateRowSorter(true);
        dgListe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dgListe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Soru Id", "Zorluk Derecesi", "Ders Adı", "Konu Adı", "Soru Metni", "Secenek A", "Secenek B", "Secenek C", "Secenek D", "Secenek E", "Secenek Dogru"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgListe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dgListe.setGridColor(new java.awt.Color(0, 102, 204));
        dgListe.setRowHeight(30);
        dgListe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dgListe.setShowHorizontalLines(false);
        dgListe.setSurrendersFocusOnKeystroke(true);
        dgListeScrollPane.setViewportView(dgListe);

        pnl.add(dgListeScrollPane);
        dgListeScrollPane.setBounds(0, 0, 900, 220);

        pnl2.setBackground(new java.awt.Color(255, 255, 255));
        pnl2.setLayout(null);

        txtSoruMetniScollPane.setBorder(null);

        txtSoruMetni.setBackground(new java.awt.Color(177, 177, 177));
        txtSoruMetni.setColumns(20);
        txtSoruMetni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoruMetni.setLineWrap(true);
        txtSoruMetni.setRows(5);
        txtSoruMetni.setWrapStyleWord(true);
        txtSoruMetni.setBorder(null);
        txtSoruMetniScollPane.setViewportView(txtSoruMetni);

        pnl2.add(txtSoruMetniScollPane);
        txtSoruMetniScollPane.setBounds(210, 20, 400, 94);

        txtA.setBackground(new java.awt.Color(177, 177, 177));
        txtA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtA.setBorder(null);
        pnl2.add(txtA);
        txtA.setBounds(210, 138, 400, 30);

        txtB.setBackground(new java.awt.Color(177, 177, 177));
        txtB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtB.setBorder(null);
        pnl2.add(txtB);
        txtB.setBounds(210, 188, 400, 30);

        txtC.setBackground(new java.awt.Color(177, 177, 177));
        txtC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtC.setBorder(null);
        pnl2.add(txtC);
        txtC.setBounds(210, 238, 400, 30);

        txtD.setBackground(new java.awt.Color(177, 177, 177));
        txtD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtD.setBorder(null);
        pnl2.add(txtD);
        txtD.setBounds(210, 288, 400, 30);

        txtE.setBackground(new java.awt.Color(177, 177, 177));
        txtE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtE.setBorder(null);
        pnl2.add(txtE);
        txtE.setBounds(210, 338, 400, 30);

        txtDogru.setBackground(new java.awt.Color(177, 177, 177));
        txtDogru.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDogru.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDogru.setBorder(null);
        pnl2.add(txtDogru);
        txtDogru.setBounds(203, 382, 43, 43);

        cbDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbDers.setBorder(null);
        pnl2.add(cbDers);
        cbDers.setBounds(640, 50, 250, 47);

        cbKonu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pnl2.add(cbKonu);
        cbKonu.setBounds(640, 140, 250, 47);

        rbKolay.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbKolay);
        rbKolay.setSelected(true);
        rbKolay.setText("Kolay");
        rbKolay.setToolTipText("Eklenecek soru kolay ise seçin");
        pnl2.add(rbKolay);
        rbKolay.setBounds(360, 394, 70, 23);

        rbOrta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbOrta);
        rbOrta.setText("Orta");
        rbOrta.setToolTipText("Eklenecek soru orta ise seçin");
        pnl2.add(rbOrta);
        rbOrta.setBounds(440, 394, 70, 23);

        rbZor.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbZor);
        rbZor.setText("Zor");
        rbZor.setToolTipText("Eklenecek soru zor ise seçin");
        pnl2.add(rbZor);
        rbZor.setBounds(520, 394, 70, 23);

        btnEkle.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnEkle.setForeground(new java.awt.Color(255, 255, 255));
        btnEkle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEkle.setText("EKLE");
        btnEkle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl2.add(btnEkle);
        btnEkle.setBounds(650, 210, 240, 60);

        btnWord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/word.png"))); // NOI18N
        btnWord.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl2.add(btnWord);
        btnWord.setBounds(715, 295, 110, 120);

        lblSoruMetniArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtBuyuk.png"))); // NOI18N
        lblSoruMetniArka.setPreferredSize(new java.awt.Dimension(410, 161));
        pnl2.add(lblSoruMetniArka);
        lblSoruMetniArka.setBounds(200, 10, 419, 115);

        lblA.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblA.setText("Seçenek A");
        pnl2.add(lblA);
        lblA.setBounds(60, 144, 140, 20);

        lblAArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl2.add(lblAArka);
        lblAArka.setBounds(200, 130, 419, 47);

        lblB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblB.setText("Seçenek B");
        pnl2.add(lblB);
        lblB.setBounds(60, 194, 140, 20);

        lblSoruMetni.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSoruMetni.setText("Soru Metni");
        pnl2.add(lblSoruMetni);
        lblSoruMetni.setBounds(60, 60, 140, 20);

        lblBArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl2.add(lblBArka);
        lblBArka.setBounds(200, 180, 419, 47);

        lblDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDers.setText("Ders");
        pnl2.add(lblDers);
        lblDers.setBounds(640, 20, 140, 20);

        lblCArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl2.add(lblCArka);
        lblCArka.setBounds(200, 230, 419, 47);

        lblD.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblD.setText("Seçenek D");
        pnl2.add(lblD);
        lblD.setBounds(60, 294, 140, 20);

        lblDArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl2.add(lblDArka);
        lblDArka.setBounds(200, 280, 419, 47);

        lblZorluk.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblZorluk.setText("Zorluk");
        pnl2.add(lblZorluk);
        lblZorluk.setBounds(270, 396, 80, 20);

        lblDogruArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTek.png"))); // NOI18N
        pnl2.add(lblDogruArka);
        lblDogruArka.setBounds(200, 380, 48, 47);

        lblE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblE.setText("Seçenek E");
        pnl2.add(lblE);
        lblE.setBounds(60, 344, 140, 20);

        lblEArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl2.add(lblEArka);
        lblEArka.setBounds(200, 330, 419, 47);

        lblDogru.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDogru.setText("Doğru Seçenek");
        pnl2.add(lblDogru);
        lblDogru.setBounds(30, 390, 170, 22);

        lblC.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblC.setText("Seçenek C");
        pnl2.add(lblC);
        lblC.setBounds(60, 244, 140, 20);

        lblKonu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblKonu.setText("Konu");
        pnl2.add(lblKonu);
        lblKonu.setBounds(640, 110, 140, 20);

        lblBtnkleArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        lblBtnkleArka.setToolTipText("");
        lblBtnkleArka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl2.add(lblBtnkleArka);
        lblBtnkleArka.setBounds(640, 210, 257, 61);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnl2.add(jSeparator2);
        jSeparator2.setBounds(256, 385, 10, 40);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnl2.add(jSeparator1);
        jSeparator1.setBounds(630, 10, 10, 410);
        pnl2.add(jSeparator3);
        jSeparator3.setBounds(630, 280, 270, 10);

        pnl.add(pnl2);
        pnl2.setBounds(-1, 219, 900, 430);

        menu.setText("Diğer İşlemler");

        menuDers.setText("Ders Ekle");
        menu.add(menuDers);

        menuKonu.setText("Konu Ekle");
        menu.add(menuKonu);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Form'u kuran method
    private void formuKur()
    {
        initComponents();
        dgListe.setDefaultEditor(Object.class, null);
        setSize(new Dimension(905, 700));
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
        
        Listele();
        cbDersDoldur();
        Temizle();
    }
    
    //Girilen verilerin boş olup olmadığını kontrol eden method.
    private boolean VerileriKontrolEt()
    {
        return !(txtA.getText().equals("") || txtB.getText().equals("") || txtC.getText().equals("") || txtD.getText().equals("") || txtE.getText().equals("") || txtDogru.getText().equals("") || txtSoruMetni.getText().equals("") || seciliDersId == 0 || seciliKonuId == 0);
    }
    
    //DataGrid'e verileri listeleme işlemini yapan method.
    private void Listele()
    {
        dgListe.setModel(DbUtils.resultSetToTableModel(vt.SoruGetir()));
        dgColGizle();
    }
    
    //DataGrid'te ilk 3 sütunu görüntü kirliliği olmaması için gizleyen method.
    private void dgColGizle()
    {
        for(int i = 0; i < 3; i++)
            dgListe.removeColumn(dgListe.getColumnModel().getColumn(0));
    }
    
    //cbDers'i veritabanından gelen dersler tablosu ile dolduran method.
    private void cbDersDoldur()
    {
        cbDers.removeAllItems();
        try
        {
            ResultSet dersler = vt.DersGetir();
            while(dersler.next())
                cbDers.addItem(new OzelComboBoxItem(dersler.getInt("Id"), dersler.getString("DersAdi")));
        }
        catch(SQLException ex) {}
        cbDers.setSelectedIndex(-1);
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

    //Veri giriş yerlerini temzileyen method.
    private void Temizle()
    {
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        txtD.setText("");
        txtE.setText("");
        txtDogru.setText("");
        txtSoruMetni.setText("");
        cbDers.setSelectedIndex(-1);
        cbKonu.setSelectedIndex(-1);
        rbKolay.setSelected(true);
        seciliDersId =  0;
        seciliKonuId = 0;
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
    
    //Word'e yazma paneline geçiş için kullanılan MouseAdapter.
    MouseAdapter btnWordClick = new MouseAdapter()
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {
            WordForm wF = new WordForm();
            wF.setVisible(true);
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
            //Text değiştiğinde kod ile içini değiştirmek için Thread yardımı ile method invoke etmeliyiz.Yoksa throwluyor.
            Runnable invokeIslem = () -> {
                if(txtDogru.getText().length() > 1) //Text bir karakterden uzun olduğunda.
                {
                    String gelen = txtDogru.getText().substring(0, txtDogru.getText().length() - 1);//Sondakini sil.
                    txtDogru.setText(gelen);
                }
                txtDogru.setText(txtDogru.getText().toUpperCase());//Büyük harf yap.
            };  
            SwingUtilities.invokeLater(invokeIslem);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {}

        @Override
        public void changedUpdate(DocumentEvent e) {}
    };
    
    //Pop-up menü elemanlarının clickleri için kullanılan ActionListenerlar.
    ActionListener popupMenuGuncelleClick = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DuzenleForm dF = new DuzenleForm();
            dF.addWindowListener(duzenleFormAdap);
            dF.lblId.setText(dgListe.getModel().getValueAt(satir, 0).toString());
            dF.setVisible(true);
        }
    };

    ActionListener popupMenuSilClick = new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int cevap = JOptionPane.showConfirmDialog(ekran, "Emin misiniz?", "Seçin", 0);
            if(cevap == 0)
            {
                //Listedeki seçili olan satırın Id kısmını al.
                satir = dgListe.getSelectedRow();
                int deger = Integer.parseInt(dgListe.getModel().getValueAt(satir, 0).toString());
                vt.SoruSil(deger);
                Listele();
            }
        }
    };
    
    ActionListener menuDersEkle = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DersEkleForm dEF = new DersEkleForm();
            dEF.setVisible(true);
            dEF.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                    cbDersDoldur();
                    cbKonu.removeAllItems();
                }
            });
        }
    };
    
    ActionListener menuKonuEkle = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            KonuEkleForm kEF = new KonuEkleForm();
            kEF.setVisible(true);
            kEF.addWindowListener(new WindowAdapter() 
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                    cbDersDoldur();
                    cbKonu.removeAllItems();
                }
            });
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
    
    //Ekleme işlemini yapacak buton için MouseAdapter.
    MouseAdapter btnEkleClick = new MouseAdapter()
    {
        @Override
        public void mouseEntered(MouseEvent e)  
        {  
            btnEkle.setFont(new Font("Arial", Font.BOLD, 30));
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {  
            btnEkle.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        
        @Override
        public void mouseClicked(MouseEvent e)  
        {  
            if(VerileriKontrolEt())
            {
                vt.SoruEkle(seciliKonuId, zorlukBul(), txtSoruMetni.getText(), txtA.getText(), txtB.getText(), txtC.getText(), txtD.getText(), txtE.getText(), txtDogru.getText());
                Temizle();
                Listele();
            }
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
        }
    };
    
    //Düzenle form kapatılınca alt formda listeleme işlemini çağırmamız için kullanılan WindowAdapter.
    WindowAdapter duzenleFormAdap = new WindowAdapter() 
    {
        @Override
        public void windowClosed(WindowEvent e)
        {
            Listele();
            cbKonu.removeAllItems();
            cbDers.setSelectedIndex(-1);
        }
    };

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
            java.util.logging.Logger.getLogger(PanelForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new PanelForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEkle;
    private javax.swing.JLabel btnWord;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Object> cbDers;
    private javax.swing.JComboBox<Object> cbKonu;
    private javax.swing.JTable dgListe;
    private javax.swing.JScrollPane dgListeScrollPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblAArka;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblBArka;
    private javax.swing.JLabel lblBtnkleArka;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblCArka;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblDArka;
    private javax.swing.JLabel lblDers;
    private javax.swing.JLabel lblDogru;
    private javax.swing.JLabel lblDogruArka;
    private javax.swing.JLabel lblE;
    private javax.swing.JLabel lblEArka;
    private javax.swing.JLabel lblKonu;
    private javax.swing.JLabel lblSoruMetni;
    private javax.swing.JLabel lblSoruMetniArka;
    private javax.swing.JLabel lblZorluk;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuDers;
    private javax.swing.JMenuItem menuKonu;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnl2;
    private javax.swing.JRadioButton rbKolay;
    private javax.swing.JRadioButton rbOrta;
    private javax.swing.JRadioButton rbZor;
    private javax.swing.JTextField txtA;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextField txtC;
    private javax.swing.JTextField txtD;
    private javax.swing.JTextField txtDogru;
    private javax.swing.JTextField txtE;
    private javax.swing.JTextArea txtSoruMetni;
    private javax.swing.JScrollPane txtSoruMetniScollPane;
    // End of variables declaration//GEN-END:variables
}
