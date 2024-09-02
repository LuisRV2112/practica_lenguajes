package org.example;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
   private final String text;
    private int position = 0;
    private int line = 1;
    private int column = 1;

    public Lexer(String text) {
        this.text = text;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < text.length()) {
            char currentChar = text.charAt(position);
            if (Character.isWhitespace(currentChar)) {
                handleWhitespace();
            } else if (Character.isLetter(currentChar)) {
                tokens.add(handleIdentifier());
            } else if (Character.isDigit(currentChar)) {
                tokens.add(handleNumber());
            } else {
                tokens.add(handleSymbol());
            }
        }
        return tokens;
    }

    private void handleWhitespace() {
        char currentChar = text.charAt(position);
        if (currentChar == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
        advance();
    }

    private Token handleIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (position < text.length() && (Character.isLetterOrDigit(text.charAt(position)) || text.charAt(position) == '_')) {
            identifier.append(text.charAt(position));
            column++;
            advance();
        }
        String value = identifier.toString();
        String color = "#FFD300"; // Default color for identifiers
        return new Token(TokenType.IDENTIFIER, value, line, column - value.length(), color);
    }

    private Token handleNumber() {
        StringBuilder number = new StringBuilder();
        while (position < text.length() && Character.isDigit(text.charAt(position))) {
            number.append(text.charAt(position));
            column++;
            advance();
        }
        String value = number.toString();
        String color = "#1BA1E2"; // Default color for integers
        return new Token(TokenType.DATA_TYPE, value, line, column - value.length(), color);
    }

    private Token handleSymbol() {
        char currentChar = text.charAt(position);
        Token token;
        switch (currentChar) {
            case '+':
                token = new Token(TokenType.ARITHMETIC_OPERATOR, "+", line, column, "#FF33FF");
                break;
            case '-':
                token = new Token(TokenType.ARITHMETIC_OPERATOR, "-", line, column, "#C19A6B");
                break;
            case '*':
                token = new Token(TokenType.ARITHMETIC_OPERATOR, "*", line, column, "#d80073");
                break;
            case '/':
                token = new Token(TokenType.ARITHMETIC_OPERATOR, "/", line, column, "#B4D941");
                break;
            case '^':
                token = new Token(TokenType.ARITHMETIC_OPERATOR, "^", line, column, "#FCD0B4");
                break;
            case '<':
                token = handleLessThan();
                break;
            case '>':
                token = handleGreaterThan();
                break;
            case '=':
                token = handleEquals();
                break;
            case '"':
                token = handleString();
                break;
            case '\'':
                token = handleChar();
                break;
            default:
                token = new Token(TokenType.UNKNOWN, Character.toString(currentChar), line, column, "#000000");
                advance();
                break;
        }
        return token;
    }

    private Token handleLessThan() {
        if (position + 1 < text.length() && text.charAt(position + 1) == '=') {
            advance();
            advance();
            column += 2;
            return new Token(TokenType.ARITHMETIC_OPERATOR, "<=", line, column - 2, "#F0A30A");
        } else if (position + 1 < text.length() && text.charAt(position + 1) == '>') {
            advance();
            advance();
            column += 2;
            return new Token(TokenType.ARITHMETIC_OPERATOR, "<>", line, column - 2, "#3F2212");
        } else {
            advance();
            column++;
            return new Token(TokenType.ARITHMETIC_OPERATOR, "<", line, column - 1, "#D94A41");
        }
    }

    private Token handleGreaterThan() {
        if (position + 1 < text.length() && text.charAt(position + 1) == '=') {
            advance();
            advance();
            column += 2;
            return new Token(TokenType.ARITHMETIC_OPERATOR, ">=", line, column - 2, "#E3C800");
        } else {
            advance();
            column++;
            return new Token(TokenType.ARITHMETIC_OPERATOR, ">", line, column - 1, "#D9D441");
        }
    }

    private Token handleEquals() {
        if (position + 1 < text.length() && text.charAt(position + 1) == '=') {
            advance();
            advance();
            column += 2;
            return new Token(TokenType.ARITHMETIC_OPERATOR, "==", line, column - 2, "#6A00FF");
        } else {
            advance();
            column++;
            return new Token(TokenType.LOGICAL_OPERATOR, "=", line, column - 1, "#41D9D4");
        }
    }

    private Token handleString() {
        StringBuilder str = new StringBuilder();
        advance(); // Skip the opening quote
        while (position < text.length() && text.charAt(position) != '"') {
            str.append(text.charAt(position));
            column++;
            advance();
        }
        if (position < text.length()) {
            advance(); // Skip the closing quote
            column++;
        }
        return new Token(TokenType.DATA_TYPE, str.toString(), line, column, "#E51400");
    }

    private Token handleChar() {
        StringBuilder ch = new StringBuilder();
        advance(); // Skip the opening quote
        if (position < text.length()) {
            ch.append(text.charAt(position));
            column++;
            advance();
        }
        if (position < text.length() && text.charAt(position) == '\'') {
            advance(); // Skip the closing quote
            column++;
        }
        return new Token(TokenType.DATA_TYPE, ch.toString(), line, column, "#0050EF");
    }

    private void advance() {
        position++;
    }
}