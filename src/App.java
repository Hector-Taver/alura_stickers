import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ContentExtractor extractor = new IMDBContentExtractor();

        // Nasa
        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // ContentExtractor extractor = new NasaContentExtractor();

        // Nossa API
        // String url = "http://localhost:8080/languages";
        // ContentExtractor extractor = new LanguageAPIContentExtractor();

        Client http = new Client();
        String json = http.findData(url);

        // Exibir e manipular os dados
        List<Content> contents = extractor.contentExtractor(json);

        StickerGenerator generator = new StickerGenerator();

        File directory = new File("stickers/");
        directory.mkdir();

        for (int i = 0; i < 3; i++) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();

            String fileName = "stickers/" + content.getTitle() + ".png";

            generator.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}
