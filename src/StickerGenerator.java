import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
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
    Font font = new Font("Impact", Font.BOLD, 64);
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

    // Fazer um outline no texto
    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    TextLayout textLayout = new TextLayout(text, font, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(textPositionX, textPositionY);
    graphics.setTransform(transform);

    BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
    graphics.setStroke(outlineStroke);

    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);

    // Escrever a nova imagem em um novo arquivo
    ImageIO.write(newImage, "png", new File(fileName));
  }
}
