package pro1.reports.report5.reportDataModel;

import java.util.List;

public class DepartmentExamsStats {
    public long realizedExamsCount;
    public List<Long> teacherIds;

    public DepartmentExamsStats(long realizedExamsCount, List<Long> teacherIds) {
        this.realizedExamsCount = realizedExamsCount;
        this.teacherIds = teacherIds;
    }
}