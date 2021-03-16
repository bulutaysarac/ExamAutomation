public class OzelComboBoxItem 
{
    private int Id;
    private String Icerik;
    
    public OzelComboBoxItem(int Id, String Icerik)
    {
        this.Icerik = Icerik;
        this.Id = Id;
    }
    
    public int getId()
    {
        return this.Id;
    }
    
    public String getIcerik()
    {
        return this.Icerik;
    }
    
    @Override
    public String toString()
    {
        return this.Icerik;
    }
}
