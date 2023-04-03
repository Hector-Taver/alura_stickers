import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
  public void create(InputStream inputStream, String fileName) throws IOException {
    // Leitura da imagem
    BufferedImage originalImage = ImageIO.read(inputStream);

    // Criar nova imagem em memória com transparência e com tamanho novo
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeight = (int) (height + (height * 0.2));
    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // Copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    // Configurar a fonte
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // Escrever uma frase na nova imagem
    String text = "TOPZERA";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);

    int textWidth = (int) rectangle.getWidth();
    int textPositionX = (width - textWidth) / 2;

    int textPositionY = (int) (newHeight - (newHeight * 0.07));

    graphics.drawString(text, textPositionX, textPositionY);

    // Escrever a nova imagem em um novo arquivo
    ImageIO.write(newImage, "png", new File(fileName));
  }
}
