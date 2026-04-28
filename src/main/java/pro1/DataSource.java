package pro1;

public interface DataSource {
    public String getRozvrhByKatedra(String rok, String katedra);
    public String getPredmetyByUcitel(String rok, int ucitIdno, String katedra);
    public String getLiteraturaPredmetu(String zkratka, String katedra);
    public String getTerminyZkousek( String semestr,String zkratka, String katedra);
    public String getTerminyZkousek2(String katedra);
    public String getKvalifikacniPrace(String rokObhajoby, String katedra);
}