package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.KvalifikacniPrace;
import pro1.apiDataModel.KvalifikacniPraceList;
import pro1.reports.report4.reportDataModel.ThesisDuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThesisDurationReporting {

    public static List<ThesisDuration> GetReport(DataSource dataSource, String katedra, List<String> roky) {
        List<ThesisDuration> result = new ArrayList<>();
        Gson gson = new Gson();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        for (String rok : roky) {
            String json = dataSource.getKvalifikacniPrace(rok, katedra);
            KvalifikacniPraceList list = gson.fromJson(json, KvalifikacniPraceList.class);

            long totalDays = 0;
            int count = 0;

            if (list != null && list.kvalifikacniPrace != null) {
                for (KvalifikacniPrace prace : list.kvalifikacniPrace) {
                    if (prace.datumZadani != null && prace.datumObhajoby != null
                            && prace.datumZadani.value != null && prace.datumObhajoby.value != null) {
                        try {
                            String d1 = prace.datumZadani.value.trim().split(" ")[0];
                            String d2 = prace.datumObhajoby.value.trim().split(" ")[0];
                            LocalDate zadani = LocalDate.parse(d1, formatter);
                            LocalDate obhajoba = LocalDate.parse(d2, formatter);
                            totalDays += ChronoUnit.DAYS.between(zadani, obhajoba);
                            count++;
                        } catch (Exception ignored) {
                        }
                    }
                }
            }

            long average = count > 0 ? totalDays / count : 0;
            result.add(new ThesisDuration(rok, average));
        }

        return result;
    }

    public static List<ThesisDuration> GetReport(DataSource dataSource, String katedra, String[] roky) {
        return GetReport(dataSource, katedra, Arrays.asList(roky));
    }
}