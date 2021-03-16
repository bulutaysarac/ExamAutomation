
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DersEkleForm extends JFrame
{
    VeriTabani vt = new VeriTabani();
    JFrame ekran = this;
    
    public DersEkleForm()
    {
        formuKur();
        
        btnEkle.addMouseListener(btnEkleClick);
    }

    private void formuKur()
    {
        initComponents();
        setSize(new Dimension(440, 190));
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
    }
    
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
            if(!(txtDersAdi.getText().equals("")))
            {
                vt.DersEkle(txtDersAdi.getText());
                JOptionPane.showMessageDialog(ekran, "Ders Eklendi!");
                ekran.dispose();
            }
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
        }
    };
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl = new javax.swing.JPanel();
        lblDers = new javax.swing.JLabel();
        txtDersAdi = new javax.swing.JTextField();
        lblDersArka = new javax.swing.JLabel();
        btnEkle = new javax.swing.JLabel();
        lblBtnkleArka = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ders Ekle");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        pnl.setBackground(new java.awt.Color(255, 255, 255));
        pnl.setLayout(null);

        lblDers.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDers.setText("Ders Adı");
        pnl.add(lblDers);
        lblDers.setBounds(20, 10, 80, 20);

        txtDersAdi.setBackground(new java.awt.Color(177, 177, 177));
        txtDersAdi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDersAdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDersAdi.setBorder(null);
        pnl.add(txtDersAdi);
        txtDersAdi.setBounds(20, 50, 400, 30);

        lblDersArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtKucuk.png"))); // NOI18N
        pnl.add(lblDersArka);
        lblDersArka.setBounds(10, 40, 419, 47);

        btnEkle.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnEkle.setForeground(new java.awt.Color(255, 255, 255));
        btnEkle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEkle.setText("EKLE");
        btnEkle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(btnEkle);
        btnEkle.setBounds(100, 90, 240, 60);

        lblBtnkleArka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/btnKucuk.png"))); // NOI18N
        lblBtnkleArka.setToolTipText("");
        lblBtnkleArka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl.add(lblBtnkleArka);
        lblBtnkleArka.setBounds(90, 90, 257, 61);

        getContentPane().add(pnl);
        pnl.setBounds(0, 0, 440, 160);

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
            java.util.logging.Logger.getLogger(DersEkleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new DersEkleForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEkle;
    private javax.swing.JLabel lblBtnkleArka;
    private javax.swing.JLabel lblDers;
    private javax.swing.JLabel lblDersArka;
    private javax.swing.JPanel pnl;
    private javax.swing.JTextField txtDersAdi;
    // End of variables declaration//GEN-END:variables
}
