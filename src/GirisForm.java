import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GirisForm extends JFrame 
{
    //Global Değişkenler
    public JFrame ekran = this;
    private VeriTabani vt = new VeriTabani();
    
    public GirisForm() 
    {
        formuKur();
        
        btnGiris.addMouseListener(btnGirisAdapter);
        btnSifreGor.addMouseListener(btnSifreGorMouseDown);
        btnTemizle.addMouseListener(btnTemizleAdapter);
        txtKullaniciAdi.getDocument().addDocumentListener(txtKullaniciAdiAdapter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl = new javax.swing.JLabel();
        btnTemizle = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();
        txtSifre = new javax.swing.JPasswordField();
        btnSifreGor = new javax.swing.JLabel();
        btnGiris = new javax.swing.JLabel();
        lblBackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sınav Kağıdı Hazırlama v 0.1.1");
        setBackground(new java.awt.Color(242, 242, 242));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(null);

        lbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setText("GİRİŞ");
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lbl);
        lbl.setBounds(93, 200, 417, 40);

        btnTemizle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/txtTemizle.png"))); // NOI18N
        btnTemizle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnTemizle);
        btnTemizle.setBounds(430, 265, 32, 32);

        txtKullaniciAdi.setBackground(new java.awt.Color(242, 242, 242));
        txtKullaniciAdi.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtKullaniciAdi.setBorder(null);
        getContentPane().add(txtKullaniciAdi);
        txtKullaniciAdi.setBounds(110, 261, 320, 40);

        txtSifre.setBackground(new java.awt.Color(242, 242, 242));
        txtSifre.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        txtSifre.setBorder(null);
        txtSifre.setEchoChar('\u2022');
        getContentPane().add(txtSifre);
        txtSifre.setBounds(110, 344, 355, 40);

        btnSifreGor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/sifreKapali.png"))); // NOI18N
        btnSifreGor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnSifreGor);
        btnSifreGor.setBounds(515, 348, 32, 32);

        btnGiris.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnGiris.setForeground(new java.awt.Color(242, 242, 242));
        btnGiris.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGiris.setText("GİRİŞ YAP");
        btnGiris.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGiris.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(btnGiris);
        btnGiris.setBounds(93, 417, 417, 60);

        lblBackGround.setBackground(new java.awt.Color(242, 242, 242));
        lblBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/giris.png"))); // NOI18N
        getContentPane().add(lblBackGround);
        lblBackGround.setBounds(0, 2, 606, 622);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Form'u kuran method.
    private void formuKur()
    {
        initComponents();
        btnTemizle.setVisible(false);
        setSize(new Dimension(611, 655));
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/kynk/icon.png")).getImage());
    }
    
    //Girilen verilerin boş olup olmadığını kontrol eden method.
    private boolean VerileriKontrolEt()
    {
        return !(txtKullaniciAdi.getText().equals("") || new String(txtSifre.getPassword()).equals(""));
    }
    
    //KullanıcıAdı TextBox'undaki temizle butonunu içinde veri olup olmadığına göre görünür/gizli yapmamız için Listener.
    DocumentListener txtKullaniciAdiAdapter = new DocumentListener() 
    {
        @Override
        public void insertUpdate(DocumentEvent e) 
        {
            if(txtKullaniciAdi.getText().length() == 1)
                btnTemizle.setVisible(true);
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            if(txtKullaniciAdi.getText().length() == 0)
                btnTemizle.setVisible(false);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {}
    };
    
    //BtnGiriş mouseClicked, mouseEntered, mouseExited.
    MouseAdapter btnGirisAdapter = new MouseAdapter() 
    {
        @Override
        public void mouseClicked(MouseEvent e)  
        {  
            if(VerileriKontrolEt())
                if(vt.Giris(txtKullaniciAdi.getText(), new String(txtSifre.getPassword())))
                    try
                    {
                        PanelForm pF = new PanelForm();
                        pF.setVisible(true);
                    }
                    catch (Exception ex){}
                else
                    JOptionPane.showMessageDialog(ekran, "Kullanıcı Adı veya Şifre Hatalı!!");
            else
                JOptionPane.showMessageDialog(ekran, "Veriler Boş Geçilemez!!");
        }
        
        @Override
        public void mouseEntered(MouseEvent e)  
        {  
            btnGiris.setFont(new Font("Arial", Font.BOLD, 30));
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {  
            btnGiris.setFont(new Font("Arial", Font.PLAIN, 24));
        }
    };
    
    //BtnTemizle mouseClicked, mouseEntered, mouseExited.
    MouseAdapter btnTemizleAdapter = new MouseAdapter() 
    {
        @Override
        public void mouseClicked(MouseEvent e)  
        {  
            txtKullaniciAdi.setText("");
            txtKullaniciAdi.requestFocus();
        }
         
        @Override
        public void mouseEntered(MouseEvent e)  
        {
            btnTemizle.setForeground(new Color(0,0,0,255));
            btnTemizle.setOpaque(true);
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {
            btnTemizle.setForeground(new Color(0,0,0,0));
            btnTemizle.setOpaque(false);
        }
    };
    
    //BtnSifreGor mousePressed, mouseReleased, mouseEntered, mouseExited.
    MouseAdapter btnSifreGorMouseDown = new MouseAdapter() 
    {
        @Override
        public void mousePressed(MouseEvent e)  
        {  
            btnSifreGor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/sifreAcik.png")));
            txtSifre.setEchoChar((char)0);
        }
        
        @Override
        public void mouseReleased(MouseEvent e)  
        {  
            btnSifreGor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kynk/sifreKapali.png")));
            txtSifre.setEchoChar('\u2022');
        }
        
        @Override
        public void mouseEntered(MouseEvent e)  
        {
            btnSifreGor.setForeground(new Color(0,0,0,255));
            btnSifreGor.setOpaque(true);
        }
        
        @Override
        public void mouseExited(MouseEvent e)  
        {
            btnSifreGor.setForeground(new Color(0,0,0,0));
            btnSifreGor.setOpaque(false);
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
            java.util.logging.Logger.getLogger(GirisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new GirisForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnGiris;
    public javax.swing.JLabel btnSifreGor;
    public javax.swing.JLabel btnTemizle;
    public javax.swing.JLabel lbl;
    public javax.swing.JLabel lblBackGround;
    public javax.swing.JTextField txtKullaniciAdi;
    public javax.swing.JPasswordField txtSifre;
    // End of variables declaration//GEN-END:variables
}
