/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import javax.swing.*;
import java.awt.*;
import org.example.Token;

public class TokenInfoDialog extends JDialog {
    public TokenInfoDialog(Frame parent, Token token, int gridX, int gridY) {
        super(parent, "Token Information", true);
        setLayout(new GridLayout(0, 1));

        add(new JLabel("Token: " + token.getValue()));
        add(new JLabel("Type: " + token.getType()));
        add(new JLabel("Line: " + token.getLine()));
        add(new JLabel("Column: " + token.getColumn()));
        add(new JLabel("Grid X: " + gridX));
        add(new JLabel("Grid Y: " + gridY));

        // Example Graphviz code for displaying automata (to be replaced with actual Graphviz rendering)
        String dotCode = "digraph G { A -> B }";
        JTextArea graphvizTextArea = new JTextArea(dotCode);
        graphvizTextArea.setEditable(false);
        add(new JScrollPane(graphvizTextArea));

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}