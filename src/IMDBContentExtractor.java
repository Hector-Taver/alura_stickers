import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor {
  public List<Content> contentExtractor(String json) {
    // Extrair só os dados que interessam (título, poster, classificação)
    JsonParser parser = new JsonParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contents = new ArrayList<>();

    // Popular a lista de conteúdos
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String urlImage = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

      Content content = new Content(title, urlImage);

      contents.add(content);
    }

    return contents;
  }
}
