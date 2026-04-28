package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

import java.util.HashSet;
import java.util.Set;

public class DepartmentStatsReporting {

    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);

        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList)
        );
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {
        long max = 0;
        if (actionsList != null && actionsList.rozvrhovaAkce != null) {
            for (Action akce : actionsList.rozvrhovaAkce) {
                long pocetStudentu = parseLongSafely(akce.obsazeni);
                if (pocetStudentu > max) {
                    max = pocetStudentu;
                }
            }
        }
        return max;
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        long count = 0;
        if (actionsList != null && actionsList.rozvrhovaAkce != null) {
            for (Action akce : actionsList.rozvrhovaAkce) {
                if (akce.obsazeni == null || akce.obsazeni.trim().isEmpty() || parseLongSafely(akce.obsazeni) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static long maxTeacherScore(ActionsList actionsList) {
        long maxScore = 0;
        if (actionsList != null && actionsList.rozvrhovaAkce != null) {
            Set<Long> teacherIds = new HashSet<>();
            for (Action akce : actionsList.rozvrhovaAkce) {
                if (akce.ucitIdno != null && !akce.ucitIdno.trim().isEmpty()) {
                    String[] ucitele = akce.ucitIdno.split(",");
                    for (String id : ucitele) {
                        try {
                            teacherIds.add(Long.parseLong(id.trim()));
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }

            for (Long tid : teacherIds) {
                long score = teacherScore(tid, actionsList);
                if (score > maxScore) {
                    maxScore = score;
                }
            }
        }
        return maxScore;
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        long sum = 0;
        if (actionsList != null && actionsList.rozvrhovaAkce != null) {
            for (Action akce : actionsList.rozvrhovaAkce) {
                if (akce.ucitIdno != null) {
                    String[] ucitele = akce.ucitIdno.split(",");
                    for (String id : ucitele) {
                        try {
                            if (Long.parseLong(id.trim()) == teacherId) {
                                sum += parseLongSafely(akce.obsazeni);
                                break;
                            }
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }
        }
        return sum;
    }

    private static long parseLongSafely(String hodnota) {
        if (hodnota == null || hodnota.trim().isEmpty()) {
            return 0;
        }
        try {
            return Long.parseLong(hodnota.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}