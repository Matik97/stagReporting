package pro1.reports.report4;

import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import pro1.ResourcesUtils;
import pro1.TestDataSource;

import java.io.IOException;

public class ThesisDurationReportingTest
{
    @Test
    void test01() throws IOException, JSONException {
        var actual = ThesisDurationReporting.GetReport(new TestDataSource(),  "KIKM",new String[]{"2022","2023","2024","2025"});
        var actualJson = new Gson().toJson(actual);
        var expectedJson = ResourcesUtils.readResourceFile("expectedReports/thesisDurations_2022-2023-2024-2025_KIKM.json");
        JSONAssert.assertEquals(
                expectedJson,
                actualJson,
                JSONCompareMode.LENIENT);
    }
}