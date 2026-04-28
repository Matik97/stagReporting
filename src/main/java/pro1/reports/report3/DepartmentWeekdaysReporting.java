package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdaysStat;

public class DepartmentWeekdaysReporting {

    public static WeekdaysStat[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        String rozvrhJson = dataSource.getRozvrhByKatedra(rok, katedra);
        ActionsList actionsList = new Gson().fromJson(rozvrhJson, ActionsList.class);

        WeekdaysStat[] report = new WeekdaysStat[days.length];

        for (int i = 0; i < days.length; i++) {
            String pozadovanyDen = days[i];
            long pocet = 0;

            if (actionsList != null && actionsList.rozvrhovaAkce != null) {
                for (Action akce : actionsList.rozvrhovaAkce) {
                    if (pozadovanyDen.equals(akce.den)) {
                        pocet++;
                    }
                }
            }
            report[i] = new WeekdaysStat(pozadovanyDen, pocet);
        }

        return report;
    }
}