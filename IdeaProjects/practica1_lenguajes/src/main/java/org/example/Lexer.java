package org.example;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String input;
    private int currentIndex;
    private int length;

    // Definición de operadores con sus símbolos, tipos y colores
    private static class Operator {
        String symbol;
        String type;
        String color;
        private final String input;
        private int position;

        public Lexer(String input) {
            this.input = input;
            this.position = 0;
        }

        public List<Token> tokenize() {
            List<Token> tokens = new ArrayList<>();

            while (position < input.length()) {
                char currentChar = peek();

                if (Character.isLetter(currentChar)) {
                    tokens.add(scanIdentifierOrKeyword());
                } else if (Character.isDigit(currentChar)) {
                    tokens.add(scanNumber());
                } else if (currentChar == '"') {
                    tokens.add(scanString());
                } else if (currentChar == '\'') {
                    tokens.add(scanChar());
                } else {
                    tokens.add(scanOperatorOrAssignment());
                }
            }

            return tokens;
        }

        private char peek() {
            return input.charAt(position);
        }

        private char peek(int offset) {
            return position + offset < input.length() ? input.charAt(position + offset) : '\0';
        }

        private Token scanIdentifierOrKeyword() {
            StringBuilder identifier = new StringBuilder();

            while (position < input.length() && (Character.isLetterOrDigit(peek()) || peek() == '_')) {
                identifier.append(peek());
                position++;
            }

            String value = identifier.toString();

            if (isKeyword(value)) {
                return new Token(TokenType.KEYWORD, value, "#60A917");
            }

            return new Token(TokenType.IDENTIFIER, value, "#FFD300");
        }

        private boolean isKeyword(String value) {
            return switch (value) {
                case "Module", "End", "Sub", "Main", "Dim", "As", "Integer", "String", "Boolean", "Double", "Char",
                     "Console.WriteLine", "Console.ReadLine", "If", "ElseIf", "Else", "Then", "While", "Do", "Loop",
                     "For", "To", "Next", "Function", "Return", "Const" -> true;
                default -> false;
            };
        }

        private Token scanNumber() {
            StringBuilder number = new StringBuilder();
            boolean isDecimal = false;

            while (position < input.length() && (Character.isDigit(peek()) || peek() == '.')) {
                if (peek() == '.') {
                    isDecimal = true;
                }
                number.append(peek());
                position++;
            }

            return new Token(isDecimal ? TokenType.DECIMAL : TokenType.INTEGER, number.toString(),
                    isDecimal ? "#FFFF88" : "#1BA1E2");
        }

        private Token scanString() {
            StringBuilder str = new StringBuilder();
            position++;  // skip the opening quote

            while (position < input.length() && peek() != '"') {
                str.append(peek());
                position++;
            }
            position++;  // skip the closing quote

            return new Token(TokenType.STRING, str.toString(), "#E51400");
        }

        private Token scanChar() {
            StringBuilder ch = new StringBuilder();
            position++;  // skip the opening quote

            while (position < input.length() && peek() != '\'') {
                ch.append(peek());
                position++;
            }
            position++;  // skip the closing quote

            return new Token(TokenType.CHAR, ch.toString(), "#0050EF");
        }

        private Token scanOperatorOrAssignment() {
            char currentChar = peek();

            switch (currentChar) {
                case '+' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.PLUS_ASSIGN, "+=", "#FFFFFF");
                    }
                    position++;
                    return new Token(TokenType.PLUS, "+", "#FF33FF");
                }
                case '-' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.MINUS_ASSIGN, "-=", "#FFFFFF");
                    }
                    position++;
                    return new Token(TokenType.MINUS, "-", "#C19A6B");
                }
                case '*' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.MULTIPLY_ASSIGN, "*=", "#FFFFFF");
                    }
                    position++;
                    return new Token(TokenType.MULTIPLICATION, "*", "#d80073");
                }
                case '/' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.DIVIDE_ASSIGN, "/=", "#FFFFFF");
                    }
                    position++;
                    return new Token(TokenType.DIVISION, "/", "#B4D941");
                }
                case '=' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.EQUALS, "==", "#6A00FF");
                    }
                    position++;
                    return new Token(TokenType.ASSIGN, "=", "#41D9D4");
                }
                case '<' -> {
                    if (peek(1) == '>') {
                        position += 2;
                        return new Token(TokenType.NOT_EQUALS, "<>", "#3F2212");
                    } else if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.LESS_EQUALS, "<=", "#F0A30A");
                    }
                    position++;
                    return new Token(TokenType.LESS_THAN, "<", "#D94A41");
                }
                case '>' -> {
                    if (peek(1) == '=') {
                        position += 2;
                        return new Token(TokenType.GREATER_EQUALS, ">=", "#E3C800");
                    }
                    position++;
                    return new Token(TokenType.GREATER_THAN, ">", "#D9D441");
                }
                default -> {
                    if (input.startsWith("And", position)) {
                        position += 3;
                        return new Token(TokenType.AND, "And", "#414ED9");
                    } else if (input.startsWith("Or", position)) {
                        position += 2;
                        return new Token(TokenType.OR, "Or", "#41D95D");
                    } else if (input.startsWith("Not", position)) {
                        position += 3;
                        return new Token(TokenType.NOT, "Not", "#A741D9");
                    } else if (input.startsWith("Mod", position)) {
                        position += 3;
                        return new Token(TokenType.MODULO, "Mod", "#d9ab41");
                    } else if (input.startsWith("^", position)) {
                        position++;
                        return new Token(TokenType.EXPONENT, "^", "#FCD0B4");
                    } else {
                        position++;
                    }
                    return null;
                }
            }
        }
    }
}