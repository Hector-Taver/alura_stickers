import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StickerGenerator {
  public void create() throws IOException {
    // Leitura da imagem
    BufferedImage originalImage = ImageIO.read(new File("assets/input/movie.jpg"));

    // Criar nova imagem em memória com transparência e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeight = (int) (height + (height * 0.2));
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // Copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    // Escrever uma frase na nova imagem

    // Escrever a nova imagem em um novo arquivo
    ImageIO.write(newImage, "png", new File("assets/output/sticker.png"));
  }

  public static void main(String[] args) throws IOException {
    StickerGenerator generator = new StickerGenerator();
    generator.create();
  }
}