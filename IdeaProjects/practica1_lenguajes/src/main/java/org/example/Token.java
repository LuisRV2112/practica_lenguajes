package org.example;

public class Token {
    private final TokenType type;
    private final String value;
    private final String color;

    public Token(TokenType type, String value, String color) {
        this.type = type;
        this.value = value;
        this.color = color;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", type, value, color);
    }
}
