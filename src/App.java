import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Pegar a URL de uma das APIs (IMDB, Nasa, LanguageAPI)
        API api = API.IMDB_TOP_MOVIES;

        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        Client http = new Client();
        String json = http.findData(url);

        // Exibir e manipular os dados
        List<Content> contents = extractor.contentExtractor(json);

        StickerGenerator generator = new StickerGenerator();

        File directory = new File("stickers/");
        directory.mkdir();

        for (int i = 0; i < 3; i++) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.urlImage()).openStream();

            String fileName = "stickers/" + content.title() + ".png";

            generator.create(inputStream, fileName);

            System.out.println(content.title());
            System.out.println();
        }
    }
}
