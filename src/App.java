import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP com a API e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        Client http = new Client();
        String json = http.findData(url);

        // Extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);

        // Exibir e manipular os dados
        StickerGenerator generator = new StickerGenerator();
        for (int i = 0; i < 10; i++) {
            Map<String, String> content = contentList.get(i);

            String urlImage = content.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String title = content.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String fileName = "assets/output/" + title + ".png";

            generator.create(inputStream, fileName);

            System.out.println(title);
            System.out.println(urlImage);
            System.out.println(content.get("imDbRating"));
            System.out.println();
        }
    }
}
