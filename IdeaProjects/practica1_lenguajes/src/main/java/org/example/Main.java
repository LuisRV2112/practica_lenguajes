package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Imprimir y recibir en consola
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // Código de ejemplo
        String code = "userName + MaxUsers - totalMod / count == 10 >= threshold";

        // Analizar el código fuente
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();

        // Definir el número de columnas en la cuadrícula
        int columns = 5;  // Puedes ajustar este valor según la cantidad de tokens

        // Ruta donde se guardará la imagen
        String outputPath = "output_image.png";

        // Generar la imagen
        ImageGenerator.generateImage(tokens, columns, outputPath);

        System.out.println("Imagen generada en: " + outputPath);
    }
}