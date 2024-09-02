package frontend;
import org.example.Token;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageGenerator {
    public static final int CELL_SIZE = 50;  // Tamaño de cada celda en la cuadrícula
    private static final int PADDING = 5;    // Espacio entre las celdas

    public static void generateImage(List<Token> tokens, int columns, String outputPath) {
        int rows = (int) Math.ceil((double) tokens.size() / columns);

        int imageWidth = columns * (CELL_SIZE + PADDING) - PADDING;
        int imageHeight = rows * (CELL_SIZE + PADDING) - PADDING;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Clear the image with a white background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageWidth, imageHeight);

        for (int i = 0; i < tokens.size(); i++) {
            int x = (i % columns) * (CELL_SIZE + PADDING);
            int y = (i / columns) * (CELL_SIZE + PADDING);

            Token token = tokens.get(i);
            Color color = Color.decode(token.getColor());

            g2d.setColor(color);
            g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }

        g2d.dispose();

        try {
            ImageIO.write(image, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
