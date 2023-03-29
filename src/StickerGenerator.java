import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StickerGenerator {
  public void create() throws IOException {
    // Leitura da imagem
    BufferedImage originalImage = ImageIO.read(new File("assets/movie.jpg"));

    // Criar nova imagem em memória com transparência e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeight = height + 200;
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // Copiar a imagem original para nova imagem (em memória)

    // Escrever uma frase na nova imagem

    // Escrever a nova imagem em um novo arquivo
  }
}
