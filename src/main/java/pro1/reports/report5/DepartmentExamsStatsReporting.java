package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DepartmentExamsStatsReporting {

    // Vnitřní třídy pro mapování STAG JSONu
    private static class ExamTerm {
        public long ucitIdno;
        public String obsazeni;
    }

    private static class ExamTermList {
        public ExamTerm[] termin;
    }

    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        String json = dataSource.getTerminyZkousek2(katedra);
        ExamTermList list = new Gson().fromJson(json, ExamTermList.class);

        long realizedExamsCount = 0;

        // TreeSet se postará o to, že učitelé nebudou duplicitní a budou automaticky seřazeni
        Set<Long> teacherIdsSet = new TreeSet<>();

        if (list != null && list.termin != null) {
            for (ExamTerm term : list.termin) {
                int obsazeni = 0;
                try {
                    if (term.obsazeni != null) {
                        obsazeni = Integer.parseInt(term.obsazeni);
                    }
                } catch (NumberFormatException e) {
                    // Ignorujeme, pokud obsazení nejde převést na číslo
                }

                // Pokud je na termínu alespoň 1 student, je považován za realizovaný
                if (obsazeni > 0) {
                    realizedExamsCount++;
                }

                // Přidáme učitele do množiny
                teacherIdsSet.add(term.ucitIdno);
            }
        }

        // List je nutný, aby GSON vygeneroval hranaté závorky pro pole [123, 456, ...]
        return new DepartmentExamsStats(realizedExamsCount, new ArrayList<>(teacherIdsSet));
    }
}