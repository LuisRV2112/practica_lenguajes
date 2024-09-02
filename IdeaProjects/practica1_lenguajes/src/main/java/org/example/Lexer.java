package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    private String input;
    private int currentIndex;
    private int length;

    public boolean Lexer (String input) {
        this.input = input;
        private String input;
        private int currentIndex;
        private int length;

        // Definición de operadores con sus símbolos, tipos y colores
        private static class Operator {
            String symbol;
            String type;
            String color;

            Operator(String symbol, String type, String color) {
                this.symbol = symbol;
                this.type = type;
                this.color = color;
            }
        }

        private static final List<Operator> OPERATORS = Arrays.asList(
                new Operator("==", "IGUAL", "#6A00FF"),                         // Igual
                new Operator("<>", "DIFERENTE", "#3F2212"),                    // Diferente
                new Operator(">=", "MAYOR_O_IGUAL", "#E3C800"),                 // Mayor o igual que
                new Operator("<=", "MENOR_O_IGUAL", "#F0A30A"),                 // Menor o igual que
                new Operator("Mod", "MODULO", "#d9ab41"),                       // Módulo
                new Operator("+", "SUMA", "#FF33FF"),                           // Suma
                new Operator("-", "RESTA", "#C19A6B"),                          // Resta
                new Operator("^", "EXPONENTE", "#FCD0B4"),                      // Exponente
                new Operator("/", "DIVISION", "#B4D941"),                       // División
                new Operator("*", "MULTIPLICACION", "#d80073"),                // Multiplicación
                new Operator(">", "MAYOR_QUE", "#D9D441"),                      // Mayor que
                new Operator("<", "MENOR_QUE", "#D94A41")                       // Menor que
        );

    public Lexer(String input) {
            this.input = input;
            this.currentIndex = 0;
            this.length = input.length();
        }

        // Método para saltar espacios en blanco
        private void skipWhitespace(){
            while (currentIndex < length && Character.isWhitespace(input.charAt(currentIndex))) {
                currentIndex++;
            }
        }

        // Métodos auxiliares para identificar tipos de caracteres
        private boolean isLetter(char c) {
            return Character.isLetter(c);
        }

        private boolean isDigit(char c) {
            return Character.isDigit(c);
        }

        private boolean isIdentifierStart(char c) {
            return isLetter(c);
        }

        private boolean isIdentifierPart(char c) {
            return isLetter(c) || isDigit(c) || c == '_';
        }

        // Método para obtener el siguiente token
        public Token nextToken() {
            skipWhitespace();

            if (currentIndex >= length) {
                return null; // Fin de la entrada
            }

            char currentChar = input.charAt(currentIndex);

            // Reconocer Identificadores
            if (isIdentifierStart(currentChar)) {
                StringBuilder identifier = new StringBuilder();
                identifier.append(currentChar);
                currentIndex++;

                while (currentIndex < length && isIdentifierPart(input.charAt(currentIndex))) {
                    identifier.append(input.charAt(currentIndex));
                    currentIndex++;
                }

                return new Token("IDENTIFIER", identifier.toString(), "#FFD300"); // Color amarillo para identificadores
            }

            // Reconocer Operadores
            // Intentar coincidir con el operador más largo primero
            for (Operator op : OPERATORS) {
                int opLength = op.symbol.length();
                if (currentIndex + opLength <= length) {
                    String substr = input.substring(currentIndex, currentIndex + opLength);
                    if (substr.equals(op.symbol)) {
                        currentIndex += opLength;
                        return new Token(op.type, op.symbol, op.color);
                    }
                }
            }

            throw new RuntimeException("Unexpected character: " + currentChar);
        }

        // Método para obtener todos los tokens
        public List<Token> tokenize() {
            List<Token> tokens = new ArrayList<>();
            Token token;
            while ((token = nextToken()) != null) {
                tokens.add(token);
            }
            return tokens;
        }
    }
}