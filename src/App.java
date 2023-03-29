import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP com a API e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI apiAddress = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(apiAddress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // Exibir e manipular os dados
        StickerGenerator generator = new StickerGenerator();
        for (Map<String, String> movie : moviesList) {
            String urlImage = movie.get("image");
            String title = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String fileName = title + ".png";

            generator.create(inputStream, fileName);

            System.out.println(title);
            System.out.println(urlImage);
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
