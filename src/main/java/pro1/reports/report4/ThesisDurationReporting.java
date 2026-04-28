package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.reports.report4.reportDataModel.ThesisDuration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {

    private static class QualWork {
        public String datumZadani;
        public String datumObhajoby;
    }

    private static class QualWorkList {
        public QualWork[] kvalifikacniPrace;
    }

    public static ThesisDuration[] GetReport(DataSource dataSource, String katedra, String[] roky) {
        ThesisDuration[] results = new ThesisDuration[roky.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        for (int i = 0; i < roky.length; i++) {
            String rok = roky[i];
            String json = dataSource.getKvalifikacniPrace(rok, katedra);
            QualWorkList list = new Gson().fromJson(json, QualWorkList.class);

            long sumDelka = 0;
            int count = 0;

            if (list != null && list.kvalifikacniPrace != null) {
                for (QualWork prace : list.kvalifikacniPrace) {
                    if (prace.datumZadani != null && prace.datumObhajoby != null) {
                        try {
                            LocalDate zadani = LocalDate.parse(prace.datumZadani, formatter);
                            LocalDate obhajoba = LocalDate.parse(prace.datumObhajoby, formatter);
                            sumDelka += ChronoUnit.DAYS.between(zadani, obhajoba);
                            count++;
                        } catch (Exception ignored) {}
                    }
                }
            }
            results[i] = new ThesisDuration(rok, count > 0 ? Math.round((double) sumDelka / count) : 0);
        }
        return results;
    }
}