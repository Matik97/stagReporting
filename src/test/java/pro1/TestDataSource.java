package pro1;

import java.nio.file.Files;


public class TestDataSource implements DataSource {
    @Override
    public String getRozvrhByKatedra(String rok, String katedra) {
        return ResourcesUtils.readResourceFile(
                String.format("testData/getRozvrhByKatedra_%s_%s.json",rok,katedra));

    }
    public String getTerminyZkousek2(String katedra) {
        return ResourcesUtils.readResourceFile(
                String.format("testData/getTerminyZkousek_%s.json",katedra));
    }

    public String getKvalifikacniPrace(String rokObhajoby, String katedra) {
        return ResourcesUtils.readResourceFile(
                String.format("testData/getKvalifikacniPrace_%s_%s.json",rokObhajoby,katedra));
    }
    @Override
    public String getPredmetyByUcitel(String rok, int ucitIdno, String katedra) {
        return ResourcesUtils.readResourceFile(
                String.format("testData/getPredmetyByUcitel_%s_%s_%s.json",rok,ucitIdno,katedra));

    }

    @Override
    public String getLiteraturaPredmetu(String zkratka, String katedra) {
        return ResourcesUtils.readResourceFile(
                String.format("testData/getLiteraturaPredmetu_%s_%s.json",zkratka,katedra));
    }

    @Override
    public String getTerminyZkousek(String semestr, String zkratka, String katedra) {
        return "";
    }
}
