package pro1.reports.report5;

import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pro1.ResourcesUtils;
import pro1.TestDataSource;

import java.io.IOException;

public class DepartmentExamsStatsReportingTest
{
    @Test
    void test01() throws IOException, JSONException {
        var actual = DepartmentExamsStatsReporting.GetReport(new TestDataSource(), "KIT");
        var actualJson = new Gson().toJson(actual);
        var expectedJson = ResourcesUtils.readResourceFile("expectedReports/departmentExamsStats_KIT.json");
        JSONAssert.assertEquals(
                expectedJson,
                actualJson,
                JSONCompareMode.LENIENT);
    }
}