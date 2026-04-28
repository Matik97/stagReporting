package pro1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebDataSource implements DataSource {

    private static HttpClient client = HttpClient.newBuilder().build();
    public static String getFromUri(String uri)
    {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    @Override
    public String getRozvrhByKatedra(String rok, String katedra) {
        var uri=String.format("https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByKatedra?rok=%s&semestr=%s&outputFormat=JSON&katedra=%s",rok, "%25", katedra);
        return getFromUri(uri);
    }

    @Override
    public String getPredmetyByUcitel(String rok, int ucitIdno, String katedra) {
        var uri=String.format("https://stag-demo.uhk.cz/ws/services/rest2/predmety/getPredmetyByUcitel?ucitIdno=%s&outputFormat=JSON&katedra=%s&rok=%s",ucitIdno, katedra, rok);
        return getFromUri(uri);
    }

    @Override
    public String getLiteraturaPredmetu(String zkratka, String katedra) {
        var uri=String.format("https://stag-demo.uhk.cz/ws/services/rest2/predmety/getLiteraturaPredmetu?zkratka=%s&outputFormat=JSON&katedra=%s",zkratka, katedra);
        return getFromUri(uri);
    }

    @Override
    public String getTerminyZkousek( String semestr,String zkratka, String katedra) {
        var uri=String.format("https://stag-demo.uhk.cz/ws/services/rest2/terminy/getTerminyZkousek?semestr=%s&zkratka=%s&outputFormat=JSON&katedra=%s",semestr, zkratka, katedra);
        return getFromUri(uri);
    }
    @Override
    public String getTerminyZkousek2(String katedra) {
        return "";
    }

    @Override
    public String getKvalifikacniPrace(String rokObhajoby, String katedra) {
        // Tady by bylo případně reálné volání STAG API
        return "";
    }
}

