import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor {
  public List<Content> contentExtractor(String json) {
    // Extrair só os dados que interessam (título, poster, classificação)
    JsonParser parser = new JsonParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    // Popular a lista de conteúdos
    return attributesList.stream()
        .map(attributes -> new Content(attributes.get("title"),
            attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg")))
        .toList();
  }
}
