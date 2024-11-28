
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class HttpSample {
    public static void main(String[] args) throws Exception {
        String moeda = JOptionPane.showInputDialog(
                null,
                "Informe a moeda",
                "Cotação",
                JOptionPane.INFORMATION_MESSAGE);
        String jsonRespostaCotacao = getValorCotacao(moeda);
        Map<String, String> mapaJson = getChaveValor(jsonRespostaCotacao);
        JOptionPane.showMessageDialog(null,
                mapaJson.get("low"),
                "Valor cotação moeda " + moeda, 0);
    }
    public static String getValorCotacao(String moeda) throws Exception {
        // Nosso cliente http, precisamos para fazer request
        HttpClient client = HttpClient.newHttpClient();
        URI uri = new URI("https://economia.awesomeapi.com.br/json/last/" + moeda);
        // Parametros da nossa requisição
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        // Consumir a resposta
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }
    public static Map<String, String> getChaveValor(String jsonBody) {
        Pattern pattern = Pattern.compile("\"(\\w+)\"\\s*:\\s*(\"[^\"]*\"|\\d+|true|false|null)");
        Matcher matcher = pattern.matcher(jsonBody);
        Map<String, String> mapaJson = new HashMap<>();
        while (matcher.find()) {
            String chave = matcher.group(1);
            String valor = matcher.group(2);
            mapaJson.put(chave, valor);
        }
        return mapaJson;
    }
}