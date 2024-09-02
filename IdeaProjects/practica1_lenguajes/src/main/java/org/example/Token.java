package org.example;

public class Token {
    private final TokenType type;
    private final String value;
    private final int line;
    private final int column;
    private final String color;

    public Token(TokenType type, String value, int line, int column, String color) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
        this.color = color;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getColor() {
        return color;
    }

    public String getLexeme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
