package frontend;
import org.example.Token;
import org.example.TokenType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageGenerator {
     public static final int CELL_SIZE = 50;

    public static void generateImage(List<Token> tokens, int gridSize, String outputPath) {
        int imageSize = gridSize * CELL_SIZE;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        System.out.println("Generando imagen con tamaño: " + imageSize + "x" + imageSize);

        // Fill background with white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageSize, imageSize);

        int tokenIndex = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (tokenIndex < tokens.size()) {
                    Token token = tokens.get(tokenIndex);
                    g2d.setColor(getColorForToken(token));
                    g2d.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    System.out.println("Pintando token en posición (" + row + "," + col + "): " + token.getLexeme() + " con color " + g2d.getColor());
                    tokenIndex++;
                }
            }
        }

        g2d.dispose();
        try {
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Imagen generada exitosamente en: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar la imagen: " + e.getMessage());
        }
    }

    private static Color getColorForToken(Token token) {
        // Define colors according to the token type
        TokenType type = token.getType();
        String lexeme = token.getLexeme();

        switch (type) {
            case IDENTIFIER:
                return Color.decode("#FFD300");

            case ARITHMETIC_OPERATOR:
                switch (lexeme) {
                    case "+":
                        return Color.decode("#FF33FF");
                    case "-":
                        return Color.decode("#C19A6B");
                    case "^":
                        return Color.decode("#FCD0B4");
                    case "/":
                        return Color.decode("#B4D941");
                    case "Mod":
                        return Color.decode("#d9ab41");
                    case "*":
                        return Color.decode("#d80073");
                    case "==":
                        return Color.decode("#6A00FF");
                    case "<>":
                        return Color.decode("#3F2212");
                    case ">":
                        return Color.decode("#D9D441");
                    case "<":
                        return Color.decode("#D94A41");
                    case ">=":
                        return Color.decode("#E3C800");
                    case "<=":
                        return Color.decode("#F0A30A");
                    default:
                        return Color.WHITE;
                }

            case LOGICAL_OPERATOR:
                switch (lexeme) {
                    case "And":
                        return Color.decode("#414ED9");
                    case "Or":
                        return Color.decode("#41D95D");
                    case "Not":
                        return Color.decode("#A741D9");
                    case "=":
                        return Color.decode("#41D9D4");
                    case "+=":
                    case "-=":
                    case "*=":
                    case "/=":
                        return Color.WHITE;
                    default:
                        return Color.WHITE;
                }

            case RESERVED_WORD:
                return Color.decode("#60A917");

            case DATA_TYPE:
                switch (lexeme) {
                    case "Integer":
                        return Color.decode("#1BA1E2");
                    case "Decimal":
                        return Color.decode("#FFFF88");
                    case "String":
                        return Color.decode("#E51400");
                    case "Boolean":
                        return Color.decode("#FA6800");
                    case "Char":
                        return Color.decode("#0050EF");
                    default:
                        return Color.WHITE;
                }

            default:
                return Color.WHITE; // Default to white if no match found
        }
    }
}
