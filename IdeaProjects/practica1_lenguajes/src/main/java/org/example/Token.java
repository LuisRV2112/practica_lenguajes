package org.example;

public class Token {
    private String type;
    private String value;
    private String color;

    public Token(String type, String value, String color) {
        this.type = type;
        this.value = value;
        this.color = color;
    }

    public String getType() {
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
        return "Token{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
