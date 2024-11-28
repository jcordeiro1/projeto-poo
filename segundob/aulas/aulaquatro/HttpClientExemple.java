import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;

public class HttpClientExemple {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI url = new URI("https://economia.awesomeapi.com.br/json/last/USD");

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                System.err.println("API request failed with status code: " + response.statusCode());
            }
        }
    }
}

