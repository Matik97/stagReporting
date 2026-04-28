package pro1.reports.report3;

import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pro1.ResourcesUtils;
import pro1.TestDataSource;

public class WeekdaysReportingTest
{
    @Test
    void test01() throws JSONException {
        var actual = DepartmentWeekdaysReporting.GetReport(new TestDataSource(), "2025", "KIKM",new String[]{"Po","Út","St"});
        var actualJson = new Gson().toJson(actual);
        var expectedJson = ResourcesUtils.readResourceFile("expectedReports/departmentWeekdays_2025_KIKM_Po-Út-St.json");
        JSONAssert.assertEquals(
                expectedJson,
                actualJson,
                JSONCompareMode.LENIENT);
    }
}