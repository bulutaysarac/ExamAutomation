
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KonuEkleForm extends JFrame 
{
    VeriTabani vt = new VeriTabani();
    JFrame ekran = this;
    int seciliDersId = 0;
    
    public KonuEkleForm()
    {
        formuKur();
        
        cbDersDoldur();
        
        btnEkle.addMouseListener(btnEkleClick);
        
        cbDers.addItemListener(cbDersSelectedItemChanged);
    }

    private void formuKur()
    {
        initComponents();
        setSize(new Dimension(445, 265));
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
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
            }
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
            if(!(txtKonuAdi.getText().equals("")) && cbDers.getSelectedIndex() != -1)
            {
                vt.KonuEkle(seciliDersId, txtKonuAdi.getText());
                JOptionPane.showMessageDialog(ekran, "Konu Eklendi!");
                ekran.dispose();
            }
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDers = new javax.swing.JLabel();
        txtKonuAdi = new javax.swing.JTextField();
        lblDersArka = new javax.swing.JLabel();
        btnEkle = new javax.swing.JLabel();
        lblBtnkleArka = new javax.swing.JLabel();
        cbDers = new javax.swing.JComboBox<>();
        lblDers1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Konu Ekle");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        lblDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDers.setText("Konu Adı");
        jPanel1.add(lblDers);
        lblDers.setBounds(20, 90, 80, 20);

        txtKonuAdi.setBackground(new java.awt.Color(177, 177, 177));
        txtKonuAdi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKonuAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKonuAdi.setBorder(null);
        jPanel1.add(txtKonuAdi);
        txtKonuAdi.setBounds(20, 130, 400, 30);

        lblDersArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        jPanel1.add(lblDersArka);
        lblDersArka.setBounds(10, 120, 419, 47);

        btnEkle.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnEkle.setForeground(new java.awt.Color(255, 255, 255));
        btnEkle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEkle.setText("EKLE");
        btnEkle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnEkle);
        btnEkle.setBounds(100, 170, 240, 60);

        lblBtnkleArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        lblBtnkleArka.setToolTipText("");
        lblBtnkleArka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblBtnkleArka);
        lblBtnkleArka.setBounds(90, 170, 257, 61);

        cbDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbDers.setBorder(null);
        jPanel1.add(cbDers);
        cbDers.setBounds(10, 40, 420, 47);

        lblDers1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDers1.setText("Ders");
        jPanel1.add(lblDers1);
        lblDers1.setBounds(20, 10, 80, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 450, 240);

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
            java.util.logging.Logger.getLogger(KonuEkleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new KonuEkleForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEkle;
    private javax.swing.JComboBox<Object> cbDers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBtnkleArka;
    private javax.swing.JLabel lblDers;
    private javax.swing.JLabel lblDers1;
    private javax.swing.JLabel lblDersArka;
    private javax.swing.JTextField txtKonuAdi;
    // End of variables declaration//GEN-END:variables
}
