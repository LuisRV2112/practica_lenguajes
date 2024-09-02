/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel statusLabel;

    public TextEditor() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        statusLabel = new JLabel("Line: 1, Column: 1");

        textArea.addCaretListener(e -> {
            int line = textArea.getLineOfOffset(e.getDot()) + 1;
            int column = e.getDot() - textArea.getLineStartOffset(textArea.getLineOfOffset(e.getDot())) + 1;
            statusLabel.setText("Line: " + line + ", Column: " + column);
        });

        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.read(reader, null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (topFrame != null) {
            topFrame.setJMenuBar(menuBar);
        }
    }

    public String getText() {
        return textArea.getText();
    }
}