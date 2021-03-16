import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

//VERİTABANI İŞLEMLERİ BU SINIFTAN YÖNETİLECEK...
public class VeriTabani 
{
    private String baglantiMetni = "jdbc:mysql://localhost:3306/sorular?useUnicode=true&characterEncoding=utf-8";
    private String veriTabaniKullaniciAdi = "root";
    private String veriTabaniSifre = "";
    
    private Connection baglanti = null;
    private Statement komut;
    private ResultSet veriler;
    
    //GİRİŞ İŞLEMİ
    public boolean Giris(String kullaniciAdi, String sifre)
    {
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery("SELECT * FROM kullanicilar WHERE KullaniciAdi = '" + kullaniciAdi + "' AND Sifre = '" + sifre + "'");
            return veriler.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    //TÜM SORULAR
    public ResultSet SoruGetir()
    {
        String sorgu = "SELECT s.Id, k.DersId, s.KonuId, z.ZorlukDerecesi AS 'Zorluk', d.DersAdi  AS 'Ders', k.KonuAdi  AS 'Konu', s.SoruMetni  AS 'Soru', s.SecenekA  AS 'A', s.SecenekB  AS 'B', s.SecenekC  AS 'C', s.SecenekD  AS 'D', s.SecenekE  AS 'E', s.SecenekDogru  AS 'Doğru' FROM sorular s INNER JOIN konular k ON k.Id = s.KonuId INNER JOIN zorlukdereceleri z ON s.ZorlukId = z.Id INNER JOIN dersler d ON k.DersId = d.Id";
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return veriler;
    }
    
    //ID'YE GÖRE SORU (GÜNCELLE SAYFASINDA KULLANILACAK)
    public ResultSet SoruGetir(int Id)
    {
        String sorgu = "SELECT s.Id, k.DersId, s.KonuId, z.ZorlukDerecesi, d.DersAdi, k.KonuAdi, s.SoruMetni, s.SecenekA, s.SecenekB, s.SecenekC, s.SecenekD, s.SecenekE, s.SecenekDogru FROM sorular s INNER JOIN konular k ON k.Id = s.KonuId INNER JOIN zorlukdereceleri z ON s.ZorlukId = z.Id INNER JOIN dersler d ON k.DersId = d.Id WHERE s.Id = " + Id;
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return veriler;
    }
    
    //TÜM DERSLER
    public ResultSet DersGetir()
    {
        String sorgu = "SELECT * FROM Dersler ORDER BY Id";
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return veriler;
    }
    
    //SEÇİLEN DERSE GÖRE KONULAR
    public ResultSet KonuGetir(int dersId)
    {
        String sorgu = "SELECT k.Id, k.KonuAdi FROM Konular k INNER JOIN Dersler d ON d.Id = k.DersId WHERE d.Id = " + dersId;
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return veriler;
    }
    
    //SORU EKLE
    public void SoruEkle(int konuId, int zorlukId, String soruMetni, String secenekA, String secenekB, String secenekC, String secenekD, String secenekE, String secenekDogru)
    {
        //KESME İŞARETİ İÇEREN ALANLARI DÜZELTME.
        soruMetni = soruMetni.replace("'", "''");
        secenekA = secenekA.replace("'", "''");
        secenekB = secenekB.replace("'", "''");
        secenekC = secenekC.replace("'", "''");
        secenekD = secenekD.replace("'", "''");
        secenekE = secenekE.replace("'", "''");
        String sorgu = "INSERT INTO Sorular (KonuId, ZorlukId, SoruMetni, SecenekA, SecenekB, SecenekC, SecenekD, SecenekE, SecenekDogru) VALUES(" + konuId + ", " + zorlukId + ", '" + soruMetni + "', '" + secenekA + "', '" + secenekB + "', '" + secenekC + "', '" + secenekD + "', '" + secenekE + "', '" + secenekDogru + "')";
        
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            komut.executeUpdate(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //SORU GÜNCELLE
    public void SoruGuncelle(int Id, int konuId, int zorlukId, String soruMetni, String secenekA, String secenekB, String secenekC, String secenekD, String secenekE, String secenekDogru)
    {
        //KESME İŞARETİ İÇEREN ALANLARI DÜZELTME.
        soruMetni = soruMetni.replace("'", "''");
        secenekA = secenekA.replace("'", "''");
        secenekB = secenekB.replace("'", "''");
        secenekC = secenekC.replace("'", "''");
        secenekD = secenekD.replace("'", "''");
        secenekE = secenekE.replace("'", "''");
        String sorgu = "UPDATE Sorular SET KonuId = " + konuId + ", ZorlukId = " + zorlukId + ", SoruMetni = '" + soruMetni + "', SecenekA = '" + secenekA + "', SecenekB = '" + secenekB + "', SecenekC = '" + secenekC + "', SecenekD = '" + secenekD + "', SecenekE = '" + secenekE + "', SecenekDogru = '" + secenekDogru + "' WHERE Id = " + Id;
        
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            komut.executeUpdate(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //SORU SİL
    public void SoruSil(int Id)
    {
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            komut.executeUpdate("DELETE FROM sorular WHERE Id = " + Id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //WORD'E SORU GETİR
    public ResultSet SoruGetir(int konuId, int kolayAdet, int ortaAdet, int zorAdet)
    {
        String sorgu = "(SELECT s.Id, s.KonuId, z.ZorlukDerecesi AS 'Zorluk', s.SoruMetni AS 'Soru', s.SecenekA AS 'A', s.SecenekB AS 'B', s.SecenekC AS 'C', s.SecenekD AS 'D', s.SecenekE AS 'E', s.SecenekDogru as 'Doğru' FROM sorular s INNER JOIN zorlukdereceleri z ON z.Id = s.ZorlukId WHERE s.ZorlukId = 1 AND s.KonuId = " + konuId + " ORDER BY RAND() LIMIT " + kolayAdet + ") UNION ALL (SELECT s.Id, s.KonuId, z.ZorlukDerecesi AS 'Zorluk', s.SoruMetni AS 'Soru', s.SecenekA AS 'A', s.SecenekB AS 'B', s.SecenekC AS 'C', s.SecenekD AS 'D', s.SecenekE AS 'E', s.SecenekDogru as 'Doğru' FROM sorular s INNER JOIN zorlukdereceleri z ON z.Id = s.ZorlukId WHERE s.ZorlukId = 2 AND s.KonuId = " + konuId + " ORDER BY RAND() LIMIT " + ortaAdet + ") UNION ALL (SELECT s.Id, s.KonuId, z.ZorlukDerecesi AS 'Zorluk', s.SoruMetni AS 'Soru', s.SecenekA AS 'A', s.SecenekB AS 'B', s.SecenekC AS 'C', s.SecenekD AS 'D', s.SecenekE AS 'E', s.SecenekDogru as 'Doğru' FROM sorular s INNER JOIN zorlukdereceleri z ON z.Id = s.ZorlukId WHERE s.ZorlukId = 3 AND s.KonuId = " + konuId + " ORDER BY RAND() LIMIT " + zorAdet + ")";
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            veriler = komut.executeQuery(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return veriler;
    }
    
    public void DersEkle(String dersAdi)
    {
        String sorgu = "INSERT INTO Dersler (DersAdi) VALUES ('" + dersAdi + "')";
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            komut.executeUpdate(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void KonuEkle(int dersId, String konuAdi)
    {
        String sorgu = "INSERT INTO Konular (DersId, KonuAdi) VALUES (" + dersId + ", '" + konuAdi + "')";
        try
        {
            baglanti = (Connection) DriverManager.getConnection(baglantiMetni, veriTabaniKullaniciAdi, veriTabaniSifre);
            komut = (Statement) baglanti.createStatement();
            komut.executeUpdate(sorgu);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
