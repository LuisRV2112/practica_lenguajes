/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import org.example.Token;
import frontend.ImageGenerator;
import org.example.Lexer;

public class MainFrame extends JFrame {
   private JTextArea textEditor;
    private JPanel imagePanel;
    private List<Token> tokens;

    public MainFrame() {
        setTitle("Analizador Lexico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textEditor = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(textEditor);
        add(textScrollPane, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generar Imagen");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateAndShowImage();
            }
        });
        add(generateButton, BorderLayout.NORTH);

        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (tokens != null) {
                    drawTokens(g);
                }
            }
        };
        add(imagePanel, BorderLayout.EAST);

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Token token = getTokenAt(x, y);
                if (token != null) {
                    new TokenInfoDialog(MainFrame.this, token, x / ImageGenerator.CELL_SIZE, y / ImageGenerator.CELL_SIZE).setVisible(true);
                }
            }
        });
    }

    private void generateAndShowImage() {
        tokens = new Lexer(textEditor.getText()).tokenize();

        String outputPath = "output.png";
        ImageGenerator.generateImage(tokens, 10, outputPath);

        try {
            BufferedImage img = ImageIO.read(new File(outputPath));
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            JScrollPane scrollPane = new JScrollPane(imageLabel);
            JFrame imageFrame = new JFrame("Generated Image");
            imageFrame.add(scrollPane);
            imageFrame.setSize(800, 600);
            imageFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawTokens(Graphics g) {
        // This can be used if you want to draw tokens directly on the imagePanel
        // For now, we assume the image is displayed in a separate frame
    }

    private Token getTokenAt(int x, int y) {
        // Implement token retrieval based on x, y
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}